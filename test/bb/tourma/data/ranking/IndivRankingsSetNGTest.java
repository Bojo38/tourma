/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Coach;
import bb.tourma.data.Tournament;
import java.util.ArrayList;
import java.util.HashMap;
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
public class IndivRankingsSetNGTest {
    
    public IndivRankingsSetNGTest() {
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
     * Test of setRoundOnly method, of class IndivRankingsSet.
     */
    @Test
    public void testSetRoundOnly() {
        System.out.println("setRoundOnly");
        boolean roundOnly = false;
        IndivRankingsSet instance = new IndivRankingsSet();
        instance.setRoundOnly(roundOnly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class IndivRankingsSet.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        IndivRankingsSet instance = new IndivRankingsSet();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testCreateRanking_3args() {
        System.out.println("createRanking");
        int rNumber = 0;
        Tournament tour = null;
        boolean roundOnly = false;
        IndivRankingsSet instance = new IndivRankingsSet();
        instance.createRanking(rNumber, tour, roundOnly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testCreateRanking_4args() {
        System.out.println("createRanking");
        int rNumber = 0;
        Tournament tour = null;
        boolean roundOnly = false;
        ArrayList<Coach> coachs = null;
        IndivRankingsSet instance = new IndivRankingsSet();
        instance.createRanking(rNumber, tour, roundOnly, coachs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testGetRanking() {
        System.out.println("getRanking");
        IndivRankingsSet instance = new IndivRankingsSet();
        IndivRanking expResult = null;
        IndivRanking result = instance.getRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnnexPosRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testGetAnnexPosRanking() {
        System.out.println("getAnnexPosRanking");
        IndivRankingsSet instance = new IndivRankingsSet();
        HashMap expResult = null;
        HashMap result = instance.getAnnexPosRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnnexNegRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testGetAnnexNegRanking() {
        System.out.println("getAnnexNegRanking");
        IndivRankingsSet instance = new IndivRankingsSet();
        HashMap expResult = null;
        HashMap result = instance.getAnnexNegRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnnexDifRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testGetAnnexDifRanking() {
        System.out.println("getAnnexDifRanking");
        IndivRankingsSet instance = new IndivRankingsSet();
        HashMap expResult = null;
        HashMap result = instance.getAnnexDifRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnnexFormRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testGetAnnexFormRanking() {
        System.out.println("getAnnexFormRanking");
        IndivRankingsSet instance = new IndivRankingsSet();
        HashMap expResult = null;
        HashMap result = instance.getAnnexFormRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingForPool method, of class IndivRankingsSet.
     */
    @Test
    public void testGetRankingForPool() {
        System.out.println("getRankingForPool");
        IndivRankingsSet instance = new IndivRankingsSet();
        IndivRanking expResult = null;
        IndivRanking result = instance.getRankingForPool();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingForCup method, of class IndivRankingsSet.
     */
    @Test
    public void testGetRankingForCup() {
        System.out.println("getRankingForCup");
        IndivRankingsSet instance = new IndivRankingsSet();
        IndivRanking expResult = null;
        IndivRanking result = instance.getRankingForCup();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class IndivRankingsSet.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        IndivRankingsSet instance = new IndivRankingsSet();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class IndivRankingsSet.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        IndivRankingsSet instance = new IndivRankingsSet();
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
