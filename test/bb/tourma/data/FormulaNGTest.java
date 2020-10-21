/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

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
        Formula instance = null;
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class Formula.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Formula instance = null;
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Formula.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Formula instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Formula.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String _name = "";
        Formula instance = null;
        instance.setName(_name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFormula method, of class Formula.
     */
    @Test
    public void testGetFormula() {
        System.out.println("getFormula");
        Formula instance = null;
        String expResult = "";
        String result = instance.getFormula();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFormula method, of class Formula.
     */
    @Test
    public void testSetFormula() {
        System.out.println("setFormula");
        String _formula = "";
        Formula instance = null;
        instance.setFormula(_formula);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pull method, of class Formula.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        Formula f = null;
        Formula instance = null;
        instance.pull(f);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Formula.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Formula instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Formula.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element formula = null;
        Formula instance = null;
        instance.setXMLElement(formula);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValid method, of class Formula.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        String formula = "";
        Formula instance = null;
        boolean expResult = false;
        boolean result = instance.isValid(formula);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of evaluate method, of class Formula.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        HashMap<Criterion, Value> values = null;
        int side = 0;
        Formula instance = null;
        int expResult = 0;
        int result = instance.evaluate(values, side);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCriteria method, of class Formula.
     */
    @Test
    public void testUpdateCriteria() {
        System.out.println("updateCriteria");
        String oldValue = "";
        String newValue = "";
        Formula instance = null;
        instance.updateCriteria(oldValue, newValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOperator method, of class Formula.
     */
    @Test
    public void testIsOperator() {
        System.out.println("isOperator");
        char c = ' ';
        boolean expResult = false;
        boolean result = Formula.isOperator(c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNum method, of class Formula.
     */
    @Test
    public void testIsNum() {
        System.out.println("isNum");
        char c = ' ';
        boolean expResult = false;
        boolean result = Formula.isNum(c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
