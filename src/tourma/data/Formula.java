/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import org.jdom.Element;
import tourma.utility.Pair;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Formula implements IXMLExport, Serializable {

    String _formula = "";
    String _name = "";
    Stack<Object> _npi = null;

    protected static AtomicInteger sGenUID = new AtomicInteger(0);
    protected int UID = sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getFormula() {
        return _formula;
    }

    public void setFormula(String _formula) {
        if (isValid(_formula)) {
            this._formula = _formula;
        }
    }

    public void pull(Formula f) {
        this.UID = f.UID;
        this._name = f._name;
        this._formula = f._formula;
        this._npi = f._npi;
    }

    /**
     *
     * @param name
     */
    public Formula(final String name) {
        _name = name;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element form = new Element(StringConstants.CS_FORMULA);
        form.setAttribute(StringConstants.CS_NAME, this.getName());
        form.setAttribute(StringConstants.CS_FORMULA, this.getFormula());
        return form;
    }

    /**
     *
     * @param formula
     */
    @Override
    public void setXMLElement(final Element formula) {
        try {
            this.setFormula(formula.getAttributeValue(StringConstants.CS_FORMULA));
            this.setName(formula.getAttributeValue(StringConstants.CS_NAME));

        } catch (NullPointerException npe) {
            this.setFormula("0");
            this.setName("F");

        }
    }

    public boolean isValid(String formula) {
        boolean valid = true;

        Stack<Object> npi = transformToNPI(formula);

        Stack<Object> npi_crit = new Stack();

        // Replace String by Criteria ?
        for (Object obj : npi) {
            if (obj instanceof Integer) {
                npi_crit.add(obj);
            } else if (obj instanceof String) {
                String object = (String) obj;
                {
                    boolean isop = false;
                    if (object.length() == 1) {
                        if (isOperator(object.charAt(0))) {
                            isop = true;
                            npi_crit.add(obj);
                        }
                    }
                    /*
                        Check that last character is 1 o 2
                        Check that the word except last character correspond to
                     */
                    if (!isop) {
                        char last = object.charAt(object.length() - 1);
                        if ((last == '1') || (last == '2')) {
                            Criteria c = null;
                            String variable = object.substring(0, object.length() - 1);
                            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                                Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                                if (crit.getAccronym().equals(variable)) {
                                    c = crit;
                                    break;
                                }
                            }
                            if (c != null) {
                                Pair<Integer, Criteria> pair;
                                String sub = object.substring(object.length() - 1, object.length());
                                int ext = Integer.parseInt(sub);
                                pair = new Pair<>(ext, c);
                                npi_crit.add(pair);
                            } else {
                                JOptionPane.showMessageDialog(null, "Variable " + variable + " not found among criteras' accronyms", "Error", JOptionPane.ERROR_MESSAGE);
                                valid = false;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Last Character of variable " + object + " is not 1 or 2", "Error", JOptionPane.ERROR_MESSAGE);
                            valid = false;
                        }
                    }
                }
            }
        }

        if (valid) {
            _npi = npi_crit;
        }
        return valid;
    }

    public int evaluate(HashMap<Criteria, Value> values, int side) {

        int value = 0;

        // First Substitute Criterias by values in NPI Stack
        Stack<Object> form = new Stack<>();

        for (Object obj : _npi) {
            if (obj instanceof Pair) {
                Integer fside = (Integer) ((Pair) obj).getFirst();
                Criteria crit = (Criteria) ((Pair) obj).getSecond();

                Value val = values.get(crit);

                if ((fside == side)) {
                    form.add(val.getValue1());
                } else {
                    form.add(val.getValue2());
                }
            } else {
                form.add(obj);
            }
        }

        // Evaluate Formula in NPI
        Stack<Integer> result = new Stack<>();
        for (Object obj : form) {
            if (obj instanceof Integer) {
                result.push((Integer) obj);
            }
            if (obj instanceof String) {
                int value1 = result.pop();
                int value2 = result.pop();
                int val = 0;
                char op = ((String) obj).charAt(0);
                switch (op) {
                    case '+':
                        val = value1 + value2;
                        break;
                    case '-':
                        val = value1 - value2;
                        break;
                    case '*':
                        val = value1 * value2;
                        break;
                    case '/':
                        val = value1 / value2;
                        break;
                    case '^':
                        val = value1 ^ value2;
                        break;
                }
                result.push(val);
            }
        }
        value = result.pop();
        return value;
    }

    public void updateCriteria(String oldValue, String newValue) {
        _formula = _formula.replace(oldValue, newValue);
    }

    private static Stack<Object> transformToNPI(String text) {
        Stack<Object> npi = new Stack();

        if (text == null) {
            return null;
        }
        String res = "";
        int len = text.length();
        Stack<Character> operator = new Stack<Character>();
        Stack<Object> reversePolish = new Stack<Object>();

        text = text.replace(" ", "");

        //avoid checking empty
        operator.push('#');
        for (int i = 0; i < len;) {
            //deal with space
            while (i < len && text.charAt(i) == ' ') {
                i++;
            }
            if (i == len) {
                break;
            }
            //if is number
            if (isNum(text.charAt(i))) {
                String num = "";
                while (i < len && isNum(text.charAt(i))) {
                    num += text.charAt(i++);
                }
                int number = Integer.parseInt(num);
                reversePolish.push(number);
                //is operator
            } else {
                if (isOperator(text.charAt(i))) {
                    char op = text.charAt(i);
                    switch (op) {
                        case '(':
                            operator.push(op);
                            break;
                        case ')':
                            while (operator.peek() != '(') {
                                reversePolish.push(Character.toString(operator.pop()));
                            }
                            operator.pop();
                            break;
                        case '+':
                        case '-':
                            if (operator.peek() == '(') {
                                operator.push(op);
                            } else {
                                while (operator.peek() != '#' && operator.peek() != '(') {
                                    reversePolish.push(Character.toString(operator.pop()));
                                }
                                operator.push(op);
                            }
                            break;
                        case '*':
                        case '/':
                        case '^':
                            if (operator.peek() == '(') {
                                operator.push(op);
                            } else {
                                while (operator.peek() != '#' && operator.peek() != '+'
                                        && operator.peek() != '-' && operator.peek() != '(') {
                                    reversePolish.push(Character.toString(operator.pop()));
                                }
                                operator.push(op);
                            }
                            break;
                    }
                    i++;
                } else {
                    String var = "";
                    while (i < len && !isOperator(text.charAt(i))) {
                        var += text.charAt(i++);
                    }
                    reversePolish.push(var);
                }
            }
        }
        while (operator.peek() != '#') {
            reversePolish.push(Character.toString(operator.pop()));
        }
        /* while (!reversePolish.isEmpty()) {
            res = res.length() == 0 ? reversePolish.pop() + res : reversePolish.pop() + " " + res;
        }*/

        System.out.println(res);
        return reversePolish;
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '^';
    }

    public static boolean isNum(char c) {
        return c - '0' >= 0 && c - '0' <= 9;
    }

}
