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
public class SubstituteNGTest {
    
    public SubstituteNGTest() {
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
     * Test of getXMLElement method, of class Substitute.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Substitute instance = new Substitute();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Substitute.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        Substitute instance = new Substitute();
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatch method, of class Substitute.
     */
    @Test
    public void testGetMatch() {
        System.out.println("getMatch");
        Substitute instance = new Substitute();
        CoachMatch expResult = null;
        CoachMatch result = instance.getMatch();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMatch method, of class Substitute.
     */
    @Test
    public void testSetMatch() {
        System.out.println("setMatch");
        CoachMatch mMatch = null;
        Substitute instance = new Substitute();
        instance.setMatch(mMatch);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubstitute method, of class Substitute.
     */
    @Test
    public void testGetSubstitute() {
        System.out.println("getSubstitute");
        Substitute instance = new Substitute();
        Coach expResult = null;
        Coach result = instance.getSubstitute();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSubstitute method, of class Substitute.
     */
    @Test
    public void testSetSubstitute() {
        System.out.println("setSubstitute");
        Coach mSubstitute = null;
        Substitute instance = new Substitute();
        instance.setSubstitute(mSubstitute);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitular method, of class Substitute.
     */
    @Test
    public void testGetTitular() {
        System.out.println("getTitular");
        Substitute instance = new Substitute();
        Coach expResult = null;
        Coach result = instance.getTitular();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitular method, of class Substitute.
     */
    @Test
    public void testSetTitular() {
        System.out.println("setTitular");
        Coach mTitular = null;
        Substitute instance = new Substitute();
        instance.setTitular(mTitular);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
