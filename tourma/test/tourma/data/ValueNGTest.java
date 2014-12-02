/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

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
        Value instance = null;
        Criteria expResult = null;
        Criteria result = instance.getCriteria();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCriteria method, of class Value.
     */
    @Test
    public void testSetCriteria() {
        System.out.println("setCriteria");
        Criteria mCriteria = null;
        Value instance = null;
        instance.setCriteria(mCriteria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue1 method, of class Value.
     */
    @Test
    public void testGetValue1() {
        System.out.println("getValue1");
        Value instance = null;
        int expResult = 0;
        int result = instance.getValue1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValue1 method, of class Value.
     */
    @Test
    public void testSetValue1() {
        System.out.println("setValue1");
        int mValue1 = 0;
        Value instance = null;
        instance.setValue1(mValue1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue2 method, of class Value.
     */
    @Test
    public void testGetValue2() {
        System.out.println("getValue2");
        Value instance = null;
        int expResult = 0;
        int result = instance.getValue2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValue2 method, of class Value.
     */
    @Test
    public void testSetValue2() {
        System.out.println("setValue2");
        int mValue2 = 0;
        Value instance = null;
        instance.setValue2(mValue2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
