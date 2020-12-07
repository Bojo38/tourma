/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import bb.tourma.data.exceptions.FormulaValidityException;
import java.io.File;
import java.util.HashMap;
import org.jdom.Element;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author WFMJ7631
 */
public class FormulaNGTest {

    public FormulaNGTest() {
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getUID method, of class Formula.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Formula instance = new Formula("Test");
        int expResult = 0;
        int result = instance.getUID();

    }

    /**
     * Test of setUID method, of class Formula.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Formula instance = new Formula("Test");
        instance.setUID(UID);

    }

    /**
     * Test of getName method, of class Formula.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String _name = "Test";
        Formula instance = Tournament.getTournament().getParams().getFormula(0);
        instance.setName(_name);

        assertEquals(instance.getName(), "Test");
    }

    /**
     * Test of setName method, of class Formula.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String _name = "Test";
        Formula instance = Tournament.getTournament().getParams().getFormula(0);
        instance.setName(_name);

        assertEquals(instance.getName(), "Test");

    }

    /**
     * Test of getFormula method, of class Formula.
     */
    @Test
    public void testGetFormula() {
        System.out.println("getFormula");
        String formula = "Tds1";
        Formula instance = Tournament.getTournament().getParams().getFormula(0);
        try {
            instance.setFormula(formula);
        } catch (FormulaValidityException fve) {
            fail(fve.getMessage());
        }
        assertEquals(instance.getFormula(), formula);
    }

    /**
     * Test of setFormula method, of class Formula.
     */
    @Test
    public void testSetFormula() {
        System.out.println("setFormula");
        String formula = "Tds1";
        Formula instance = Tournament.getTournament().getParams().getFormula(0);
        try {
            instance.setFormula(formula);
        } catch (FormulaValidityException fve) {
            fail(fve.getMessage());
        }

        assertEquals(instance.getFormula(), formula);
    }

    /**
     * Test of pull method, of class Formula.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        Formula f = null;
        Formula instance = null;
        // instance.pull(f);

    }

    /**
     * Test of getXMLElement method, of class Formula.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Formula instance = Tournament.getTournament().getParams().getFormula(0);

        Element result = instance.getXMLElement();

        Formula f = new Formula("Test");
        f.setXMLElement(result);

        assertEquals(result.toString(), f.getXMLElement().toString());

    }

    /**
     * Test of setXMLElement method, of class Formula.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Formula instance = Tournament.getTournament().getParams().getFormula(0);

        Element result = instance.getXMLElement();

        Formula f = new Formula("Test");
        f.setXMLElement(result);

        assertEquals(result.toString(), f.getXMLElement().toString());
    }

    /**
     * Test of isValid method, of class Formula.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        HashMap<Criterion, Value> values = new HashMap<>();

        Criterion crit0 = Tournament.getTournament().getParams().getCriterion(0);

        Value v0 = new Value(crit0);
        v0.setValue1(3);
        v0.setValue2(1);

        Criterion crit1 = Tournament.getTournament().getParams().getCriterion(1);

        Value v1 = new Value(crit1);
        v1.setValue1(3);
        v1.setValue2(1);

        Criterion crit2 = Tournament.getTournament().getParams().getCriterion(2);

        Value v2 = new Value(crit1);
        v2.setValue1(3);
        v2.setValue2(1);

        values.put(crit0, v0);
        values.put(crit1, v1);
        values.put(crit2, v2);

        v0.setCriteria(crit0);
        v1.setCriteria(crit1);
        v2.setCriteria(crit2);

        int side = 0;
        Formula instance = new Formula("Formula");
        try {
            instance.setFormula("3*Tds1+2*Sor1+1*Agg1");
            instance.isValid(instance.getFormula());
        } catch (FormulaValidityException fve) {
            fail(fve.getMessage());
        }
        
        try {
            instance.setFormula("3*Tds1+2*Sor1+1*AggXYZ1");
           
        } catch (FormulaValidityException fve) {
           /**
            * Exception must be catched
            */
        }

    }

    /**
     * Test of evaluate method, of class Formula.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        
        HashMap<Criterion, Value> values = new HashMap<>();

        Criterion crit0 = Tournament.getTournament().getParams().getCriterion(0);

        Value v0 = new Value(crit0);
        v0.setValue1(3);
        v0.setValue2(1);

        Criterion crit1 = Tournament.getTournament().getParams().getCriterion(1);

        Value v1 = new Value(crit1);
        v1.setValue1(3);
        v1.setValue2(1);

        Criterion crit2 = Tournament.getTournament().getParams().getCriterion(2);

        Value v2 = new Value(crit1);
        v2.setValue1(3);
        v2.setValue2(1);

        values.put(crit0, v0);
        values.put(crit1, v1);
        values.put(crit2, v2);

        v0.setCriteria(crit0);
        v1.setCriteria(crit1);
        v2.setCriteria(crit2);

        int side = 0;
        Formula instance = new Formula("Formula");
        try {
            instance.setFormula("3*Tds1+2*Sor1+1*Agg1");
            instance.isValid(instance.getFormula());
        } catch (FormulaValidityException fve) {
            fail(fve.getMessage());
        }

        int expResult = 18;
        int result = instance.evaluate(values, 1);
        assertEquals(result, expResult);

    }

    /**
     * Test of updateCriteria method, of class Formula.
     */
    @Test
    public void testUpdateCriteria() {
        System.out.println("updateCriteria");
        HashMap<Criterion, Value> values = new HashMap<>();

        Criterion crit0 = Tournament.getTournament().getParams().getCriterion(0);

        Value v0 = new Value(crit0);
        v0.setValue1(3);
        v0.setValue2(1);

        Criterion crit1 = Tournament.getTournament().getParams().getCriterion(1);

        Value v1 = new Value(crit1);
        v1.setValue1(3);
        v1.setValue2(1);

        Criterion crit2 = Tournament.getTournament().getParams().getCriterion(2);

        Value v2 = new Value(crit1);
        v2.setValue1(3);
        v2.setValue2(1);

        values.put(crit0, v0);
        values.put(crit1, v1);
        values.put(crit2, v2);

        v0.setCriteria(crit0);
        v1.setCriteria(crit1);
        v2.setCriteria(crit2);

        Formula instance = new Formula("Formula");
        try {
            instance.setFormula("3*Tds1+2*Sor1+1*Agg1");
            instance.isValid(instance.getFormula());

        } catch (FormulaValidityException fve) {
            fail(fve.getMessage());
        }
        crit2.setAccronym("Foul");
        instance.updateCriteria("Agg", "Foul");

        try {
            instance.isValid(instance.getFormula());
        } catch (FormulaValidityException fve) {
            fail(fve.getMessage());
        }
        assertEquals(instance.getFormula(), "3*Tds1+2*Sor1+1*Foul1");

    }

    /**
     * Test of isOperator method, of class Formula.
     */
    @Test
    public void testIsOperator() {
        System.out.println("isOperator");
        boolean result = Formula.isOperator('7');
        assertEquals(result, false);

        result = Formula.isOperator('&');
        assertEquals(result, false);

        result = Formula.isOperator('+');
        assertEquals(result, true);

        result = Formula.isOperator('-');
        assertEquals(result, true);

        result = Formula.isOperator('*');
        assertEquals(result, true);

        result = Formula.isOperator('/');
        assertEquals(result, true);

        result = Formula.isOperator('^');
        assertEquals(result, true);
    }

    /**
     * Test of isNum method, of class Formula.
     */
    @Test
    public void testIsNum() {
        System.out.println("isNum");

        boolean result = Formula.isNum('7');
        assertEquals(result, true);

        result = Formula.isNum('&');
        assertEquals(result, false);

        result = Formula.isNum('+');
        assertEquals(result, false);

    }

}
