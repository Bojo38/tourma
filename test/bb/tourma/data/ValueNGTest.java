/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import bb.tourma.data.Value;
import bb.tourma.data.Criterion;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author WFMJ7631
 */
public class ValueNGTest {

    public ValueNGTest() {
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
     * Test of getCriteria method, of class Value.
     */
    @Test
    public void testGetCriteria() {
        System.out.println("getCriteria");
        Criterion crit = new Criterion("Test");
        Criterion crit2 = new Criterion("Test2");
        Value instance = new Value(crit);
        instance.setCriteria(crit2);
        assertEquals(crit2, instance.getCriteria());
    }

    /**
     * Test of setCriteria method, of class Value.
     */
    @Test
    public void testSetCriteria() {
        System.out.println("setCriteria");
        Criterion crit = new Criterion("Test");
        Criterion crit2 = new Criterion("Test2");
        Value instance = new Value(crit);
        instance.setCriteria(crit2);
        assertEquals(crit2, instance.getCriteria());
    }

    /**
     * Test of getValue1 method, of class Value.
     */
    @Test
    public void testGetValue1() {
        System.out.println("getValue1");
        int mValue1 = 19;
        Criterion crit = new Criterion("Test");
        Value instance = new Value(crit);
        instance.setValue1(mValue1);
        assertEquals(19, instance.getValue1());
    }

    /**
     * Test of setValue1 method, of class Value.
     */
    @Test
    public void testSetValue1() {
        System.out.println("setValue1");
        int mValue1 = 19;
        Criterion crit = new Criterion("Test");
        Value instance = new Value(crit);
        instance.setValue1(mValue1);
        assertEquals(19, instance.getValue1());
    }

    /**
     * Test of getValue2 method, of class Value.
     */
    @Test
    public void testGetValue2() {
        System.out.println("getValue2");
        int mValue2 = 19;
        Criterion crit = new Criterion("Test");
        Value instance = new Value(crit);
        instance.setValue2(mValue2);
        assertEquals(19, instance.getValue2());
    }

    /**
     * Test of setValue2 method, of class Value.
     */
    @Test
    public void testSetValue2() {
        System.out.println("setValue2");
        int mValue2 = 19;
        Criterion crit = new Criterion("Test");
        Value instance = new Value(crit);
        instance.setValue2(mValue2);
        assertEquals(19, instance.getValue2());
    }

    /**
     * Test of isUpdated method, of class Value.
     */
    @Test
    public void testIsUpdated() {
        System.out.println("isUpdated");
        Value instance = new Value(new Criterion("Test"));
        boolean expResult = false;
        boolean result = instance.isUpdated();
        assertEquals(result, expResult);

    }

    /**
     * Test of setUpdated method, of class Value.
     */
    @Test
    public void testSetUpdated() {
        System.out.println("setUpdated");
        Value instance = new Value(new Criterion("Test"));
        boolean expResult = false;
        boolean result = instance.isUpdated();
        assertEquals(result, expResult);

        instance.setUpdated(true);
        result = instance.isUpdated();
        assertEquals(result, true);
    }

    /**
     * Test of getUID method, of class Value.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Value instance = new Value(new Criterion("Test"));
        int expResult = 0;
        int result = instance.getUID();
        //assertEquals(result, expResult);

    }

    /**
     * Test of setUID method, of class Value.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Value instance = new Value(new Criterion("Test"));
        instance.setUID(UID);

    }

    /**
     * Test of pull method, of class Value.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        Value value = null;
        Value instance = null;
        //instance.pull(value);

    }

    /**
     * Test of push method, of class Value.
     */
    @Test
    public void testPush() {
        System.out.println("push");
        Value value = null;
        Value instance = null;
        //instance.push(value);
    }

    /**
     * Test of getFormula method, of class Value.
     */
    @Test
    public void testGetFormula() {
        System.out.println("getFormula");
        Criterion crit = new Criterion("test");
        Formula form = new Formula("test");
        Value instance = new Value(form);
        Formula result = instance.getFormula();
        assertEquals(result, form);
    }

}