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
public class CupNGTest {
    
    public CupNGTest() {
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
     * Test of getTables method, of class Cup.
     */
    @Test
    public void testGetTables() {
        System.out.println("getTables");
        Cup instance = new Cup();
        ArrayList expResult = null;
        ArrayList result = instance.getTables();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanRound method, of class Cup.
     */
    @Test
    public void testCleanRound() {
        System.out.println("cleanRound");
        Round r = null;
        Cup instance = new Cup();
        instance.cleanRound(r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Cup.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Cup instance = new Cup();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Cup.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element cup = null;
        Cup instance = new Cup();
        instance.setXMLElement(cup);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInitialDraw method, of class Cup.
     */
    @Test
    public void testGetInitialDraw() {
        System.out.println("getInitialDraw");
        Cup instance = new Cup();
        Cup.INITIAL_DRAW expResult = null;
        Cup.INITIAL_DRAW result = instance.getInitialDraw();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInitialDraw method, of class Cup.
     */
    @Test
    public void testSetInitialDraw() {
        System.out.println("setInitialDraw");
        Cup.INITIAL_DRAW mInitialDraw = null;
        Cup instance = new Cup();
        instance.setInitialDraw(mInitialDraw);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isShuffle method, of class Cup.
     */
    @Test
    public void testIsShuffle() {
        System.out.println("isShuffle");
        Cup instance = new Cup();
        boolean expResult = false;
        boolean result = instance.isShuffle();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setShuffle method, of class Cup.
     */
    @Test
    public void testSetShuffle() {
        System.out.println("setShuffle");
        boolean mShuffle = false;
        Cup instance = new Cup();
        instance.setShuffle(mShuffle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class Cup.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Cup instance = new Cup();
        Cup.ROUND_TYPE expResult = null;
        Cup.ROUND_TYPE result = instance.getType();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class Cup.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        Cup.ROUND_TYPE mType = null;
        Cup instance = new Cup();
        instance.setType(mType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoundsCount method, of class Cup.
     */
    @Test
    public void testGetRoundsCount() {
        System.out.println("getRoundsCount");
        Cup instance = new Cup();
        int expResult = 0;
        int result = instance.getRoundsCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoundsCount method, of class Cup.
     */
    @Test
    public void testSetRoundsCount() {
        System.out.println("setRoundsCount");
        int mRoundsCount = 0;
        Cup instance = new Cup();
        instance.setRoundsCount(mRoundsCount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSwissForLoosers method, of class Cup.
     */
    @Test
    public void testIsSwissForLoosers() {
        System.out.println("isSwissForLoosers");
        Cup instance = new Cup();
        boolean expResult = false;
        boolean result = instance.isSwissForLoosers();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSwissForLoosers method, of class Cup.
     */
    @Test
    public void testSetSwissForLoosers() {
        System.out.println("setSwissForLoosers");
        boolean mSwissForLoosers = false;
        Cup instance = new Cup();
        instance.setSwissForLoosers(mSwissForLoosers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateMatches method, of class Cup.
     */
    @Test
    public void testGenerateMatches() {
        System.out.println("generateMatches");
        int roundIndex = 0;
        Round r = null;
        ArrayList<Match> previousRoundMatches = null;
        ArrayList<Competitor> competitors = null;
        Cup instance = new Cup();
        ArrayList expResult = null;
        ArrayList result = instance.generateMatches(roundIndex, r, previousRoundMatches, competitors);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
