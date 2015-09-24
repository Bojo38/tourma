/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import static org.testng.Assert.assertEquals;
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
        Criteria crit=new Criteria("Test");
        Criteria crit2=new Criteria("Test2");
        Value instance = new Value(crit);
        instance.setCriteria(crit2);
        assertEquals(crit2,instance.getCriteria());
    }

    /**
     * Test of setCriteria method, of class Value.
     */
    @Test
    public void testSetCriteria() {
        System.out.println("setCriteria");
               Criteria crit=new Criteria("Test");
        Criteria crit2=new Criteria("Test2");
        Value instance = new Value(crit);
        instance.setCriteria(crit2);
        assertEquals(crit2,instance.getCriteria());
    }

    /**
     * Test of getValue1 method, of class Value.
     */
    @Test
    public void testGetValue1() {
        System.out.println("getValue1");
        int mValue1 = 19;
        Criteria crit=new Criteria("Test");
        Value instance = new Value(crit);
        instance.setValue1(mValue1);
        assertEquals(19,instance.getValue1());
    }

    /**
     * Test of setValue1 method, of class Value.
     */
    @Test
    public void testSetValue1() {
        System.out.println("setValue1");
        int mValue1 = 19;
        Criteria crit=new Criteria("Test");
        Value instance = new Value(crit);
        instance.setValue1(mValue1);
        assertEquals(19,instance.getValue1());
    }

    /**
     * Test of getValue2 method, of class Value.
     */
    @Test
    public void testGetValue2() {
        System.out.println("getValue2");
        int mValue2 = 19;
        Criteria crit=new Criteria("Test");
        Value instance = new Value(crit);
        instance.setValue2(mValue2);
        assertEquals(19,instance.getValue2());
    }

    /**
     * Test of setValue2 method, of class Value.
     */
    @Test
    public void testSetValue2() {
        System.out.println("setValue2");
        int mValue2 = 19;
        Criteria crit=new Criteria("Test");
        Value instance = new Value(crit);
        instance.setValue2(mValue2);
        assertEquals(19,instance.getValue2());
    }
    
}
