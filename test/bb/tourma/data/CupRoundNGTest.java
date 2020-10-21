/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import java.util.ArrayList;
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
public class CupRoundNGTest {
    
    public CupRoundNGTest() {
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
     * Test of getNbMatchs method, of class CupRound.
     */
    @Test
    public void testGetNbMatchs() {
        System.out.println("getNbMatchs");
        CupRound instance = new CupRound();
        int expResult = 0;
        int result = instance.getNbMatchs();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNbMatchs method, of class CupRound.
     */
    @Test
    public void testSetNbMatchs() {
        System.out.println("setNbMatchs");
        int mNbMatchs = 0;
        CupRound instance = new CupRound();
        instance.setNbMatchs(mNbMatchs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatchs method, of class CupRound.
     */
    @Test
    public void testGetMatchs() {
        System.out.println("getMatchs");
        CupRound instance = new CupRound();
        ArrayList expResult = null;
        ArrayList result = instance.getMatchs();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMatchs method, of class CupRound.
     */
    @Test
    public void testSetMatchs() {
        System.out.println("setMatchs");
        ArrayList<Match> mMatchs = null;
        CupRound instance = new CupRound();
        instance.setMatchs(mMatchs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateEmptyMatchs method, of class CupRound.
     */
    @Test
    public void testGenerateEmptyMatchs() {
        System.out.println("generateEmptyMatchs");
        CupRound instance = new CupRound();
        instance.generateEmptyMatchs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class CupRound.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        CupRound instance = new CupRound();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class CupRound.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        CupRound instance = new CupRound();
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
