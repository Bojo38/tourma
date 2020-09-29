/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.Formula;
import tourma.data.Match;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author Administrateur
 */
@SuppressWarnings("serial")
public class MjtFormulas extends AbstractTableModel implements TableCellRenderer {

    /**
     * String Key about Criterias that already exists
     */
    private static final String CS_FormulaAlreadyExists = "FormulaAlreadyExists0";

    /**
     *
     */
    private Parameters mParams;

    /**
     *
     * @param tour
     */
    public MjtFormulas(final Tournament tour) {

        mParams = tour.getParams();
    }

    @Override
    public int getColumnCount() {
        int result = 2;
        return result;
    }

    @Override
    public int getRowCount() {
        return mParams.getFormulaCount();
    }

    @Override
    public String getColumnName(final int col) {
        String result = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                result = Translate.translate(Translate.CS_Formula_Name);
                break;
            case 1:
                result = Translate.translate(Translate.CS_Formula_Formula);
                break;
            default:
        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {

        Object result = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                result = mParams.getFormula(row).getName();
                break;
            case 1:
                result = mParams.getFormula(row).getFormula();
                break;
            default:
        }
        return result;
    }

    @Override
    public void setValueAt(final Object value, final int row, final int col) {
        if (value != null) {
            boolean exists = false;
            for (int i = 0; i < mParams.getFormulaCount(); i++) {
                if (i != row) {
                    if (value.toString().equals(mParams.getFormula(i).getName())) {
                        exists = true;
                        break;
                    }
                }
            }

            if (exists) {
                JOptionPane.showMessageDialog(null, Translate.translate(Translate.CS_Error),
                        Translate.translate(CS_FormulaAlreadyExists), JOptionPane.ERROR_MESSAGE);

            } else {
                String tmp = value.toString();
                final Formula f = mParams.getFormula(row);
                switch (col) {
                    case 0:
                        f.setName(tmp);
                        break;
                    case 1:
                        if (f.isValid(tmp)) {
                            f.setFormula(tmp);
                            for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
                                Round round = Tournament.getTournament().getRound(i);
                                for (int j = 0; j < round.getMatchsCount(); j++) {
                                    Match match = round.getMatch(j);
                                    if (match instanceof TeamMatch) {
                                        for (int k = 0; k < ((TeamMatch) match).getMatchCount(); k++) {
                                            CoachMatch cm = ((TeamMatch) match).getMatch(k);
                                            for (int l = 0; l < Tournament.getTournament().getParams().getFormulaCount(); l++) {
                                                Formula formula = Tournament.getTournament().getParams().getFormula(l);
                                                Value v = cm.getValue(formula);
                                                v.setValue1(formula.evaluate(cm.getValues(), 1));
                                                v.setValue2(formula.evaluate(cm.getValues(), 2));
                                            }
                                        }
                                    }
                                    if (match instanceof CoachMatch) {
                                        CoachMatch cm = (CoachMatch) match;
                                        for (int l = 0; l < Tournament.getTournament().getParams().getFormulaCount(); l++) {
                                            Formula formula = Tournament.getTournament().getParams().getFormula(l);
                                            Value v = cm.getValue(formula);
                                            v.setValue1(formula.evaluate(cm.getValues(), 1));
                                            v.setValue2(formula.evaluate(cm.getValues(), 2));
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    default:
                }
            }
        }
    }

    @Override
    public Class getColumnClass(final int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(final int row, final int col) {

        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return true;// mTour.getRoundsCount() <= 0;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final JTextField jlb = new JTextField();

        jlb.setEditable(false);
        if (value instanceof String) {
            jlb.setText((String) value);
        }

        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }

        Color bkg = new Color(255, 255, 255);
        final Color frg = new Color(0, 0, 0);
        if (isSelected) {
            bkg = new Color(200, 200, 200);
        }
        jlb.setBackground(bkg);
        jlb.setForeground(frg);
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
