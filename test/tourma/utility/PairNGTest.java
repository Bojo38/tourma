/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;

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
public class PairNGTest {
    
    public PairNGTest() {
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
     * Test of hashCode method, of class Pair.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Pair instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Pair.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object other = null;
        Pair instance = null;
        boolean expResult = false;
        boolean result = instance.equals(other);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Pair.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Pair instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirst method, of class Pair.
     */
    @Test
    public void testGetFirst() {
        System.out.println("getFirst");
        Pair instance = null;
        Object expResult = null;
        Object result = instance.getFirst();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirst method, of class Pair.
     */
    @Test
    public void testSetFirst() {
        System.out.println("setFirst");
        Object first = null;
        Pair instance = null;
        instance.setFirst(first);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSecond method, of class Pair.
     */
    @Test
    public void testGetSecond() {
        System.out.println("getSecond");
        Pair instance = null;
        Object expResult = null;
        Object result = instance.getSecond();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSecond method, of class Pair.
     */
    @Test
    public void testSetSecond() {
        System.out.println("setSecond");
        Object second = null;
        Pair instance = null;
        instance.setSecond(second);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
