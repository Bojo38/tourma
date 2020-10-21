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
public class CupTableNGTest {
    
    public CupTableNGTest() {
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
     * Test of getCupRounds method, of class CupTable.
     */
    @Test
    public void testGetCupRounds() {
        System.out.println("getCupRounds");
        CupTable instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getCupRounds();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCupRounds method, of class CupTable.
     */
    @Test
    public void testSetCupRounds() {
        System.out.println("setCupRounds");
        ArrayList<CupRound> mCupRounds = null;
        CupTable instance = null;
        instance.setCupRounds(mCupRounds);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class CupTable.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        CupTable instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class CupTable.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        CupTable instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
