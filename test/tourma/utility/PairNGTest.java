/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;

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
        String f = "First";
        String s = "Second";
        Pair instance = new Pair(f, s);
        int expResult = (f.hashCode() + s.hashCode()) * s.hashCode() + f.hashCode();
        
        int result = instance.hashCode();
        assertEquals(result, expResult);
    }

    /**
     * Test of equals method, of class Pair.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        String f = "First";
        String s = "Second";
        Pair instance = new Pair(f, s);
        
        String fo = "First";
        String so = "Second";
        Pair other = new Pair(f, s);
        
        boolean expResult = true;
        boolean result = instance.equals(other);
        assertEquals(result, expResult);
        
        other.setFirst("Other");
        expResult = false;
        result = instance.equals(other);
        assertEquals(result, expResult);
        
    }

    /**
     * Test of toString method, of class Pair.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String f = "First";
        String s = "Second";
        Pair instance = new Pair(f, s);
        String expResult = "("+f+", "+s+")";
        String result = instance.toString();
        assertEquals(result, expResult);
    }

    /**
     * Test of getFirst method, of class Pair.
     */
    @Test
    public void testGetFirst() {
        System.out.println("getFirst");
        String f = "First";
        String s = "Second";
        Pair instance = new Pair(f, s);
        Object expResult = "First";
        Object result = instance.getFirst();
        assertEquals(result, expResult);

    }

    /**
     * Test of setFirst method, of class Pair.
     */
    @Test
    public void testSetFirst() {
        System.out.println("setFirst");
        String f = "First";
        String f2 = "First2";
        String s = "Second";
        Pair instance = new Pair(f, s);
        instance.setFirst(f2);
        Object expResult = f2;
        Object result = instance.getFirst();
        assertEquals(result, expResult);
    }

    /**
     * Test of getSecond method, of class Pair.
     */
    @Test
    public void testGetSecond() {
        System.out.println("getSecond");
        String f = "First";
        String s = "Second";
                String s2 = "Second2";
        Pair instance = new Pair(f, s);
        instance.setSecond(s2);
        Object expResult = s2;
        Object result = instance.getSecond();
        assertEquals(result, expResult);
    }

    /**
     * Test of setSecond method, of class Pair.
     */
    @Test
    public void testSetSecond() {
        System.out.println("setSecond");
       String f = "First";
        String s = "Second";
                String s2 = "Second2";
        Pair instance = new Pair(f, s);
        instance.setSecond(s2);
        Object expResult = s2;
        Object result = instance.getSecond();
        assertEquals(result, expResult);
    }

}
