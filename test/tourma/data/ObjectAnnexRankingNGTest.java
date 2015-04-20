/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom2.Element;
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
public class ObjectAnnexRankingNGTest {
    
    public ObjectAnnexRankingNGTest() {
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
     * Test of getValue method, of class ObjectAnnexRanking.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        ObjectAnnexRanking instance = null;
        int expResult = 0;
        int result = instance.getValue();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class ObjectAnnexRanking.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object o = null;
        ObjectAnnexRanking instance = null;
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class ObjectAnnexRanking.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        ObjectAnnexRanking instance = null;
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class ObjectAnnexRanking.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ObjectAnnexRanking instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class ObjectAnnexRanking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        ObjectAnnexRanking instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class ObjectAnnexRanking.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        ObjectAnnexRanking instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValue method, of class ObjectAnnexRanking.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        int mValue = 0;
        ObjectAnnexRanking instance = null;
        instance.setValue(mValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
