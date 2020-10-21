/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Team;
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
public class TeamRankingsSetNGTest {
    
    public TeamRankingsSetNGTest() {
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
     * Test of setRoundOnly method, of class TeamRankingsSet.
     */
    @Test
    public void testSetRoundOnly() {
        System.out.println("setRoundOnly");
        boolean roundOnly = false;
        TeamRankingsSet instance = new TeamRankingsSet();
        instance.setRoundOnly(roundOnly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class TeamRankingsSet.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        TeamRankingsSet instance = new TeamRankingsSet();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getmRankingForPool method, of class TeamRankingsSet.
     */
    @Test
    public void testGetmRankingForPool() {
        System.out.println("getmRankingForPool");
        TeamRankingsSet instance = new TeamRankingsSet();
        TeamRanking expResult = null;
        TeamRanking result = instance.getmRankingForPool();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getmRankingForCup method, of class TeamRankingsSet.
     */
    @Test
    public void testGetmRankingForCup() {
        System.out.println("getmRankingForCup");
        TeamRankingsSet instance = new TeamRankingsSet();
        TeamRanking expResult = null;
        TeamRanking result = instance.getmRankingForCup();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testGetRanking() {
        System.out.println("getRanking");
        TeamRankingsSet instance = new TeamRankingsSet();
        TeamRanking expResult = null;
        TeamRanking result = instance.getRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnnexPosRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testGetAnnexPosRanking() {
        System.out.println("getAnnexPosRanking");
        TeamRankingsSet instance = new TeamRankingsSet();
        HashMap expResult = null;
        HashMap result = instance.getAnnexPosRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnnexNegRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testGetAnnexNegRanking() {
        System.out.println("getAnnexNegRanking");
        TeamRankingsSet instance = new TeamRankingsSet();
        HashMap expResult = null;
        HashMap result = instance.getAnnexNegRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnnexDifRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testGetAnnexDifRanking() {
        System.out.println("getAnnexDifRanking");
        TeamRankingsSet instance = new TeamRankingsSet();
        HashMap expResult = null;
        HashMap result = instance.getAnnexDifRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnnexFormRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testGetAnnexFormRanking() {
        System.out.println("getAnnexFormRanking");
        TeamRankingsSet instance = new TeamRankingsSet();
        HashMap expResult = null;
        HashMap result = instance.getAnnexFormRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testCreateRanking_3args() {
        System.out.println("createRanking");
        int rNumber = 0;
        Tournament tour = null;
        boolean roundOnly = false;
        TeamRankingsSet instance = new TeamRankingsSet();
        instance.createRanking(rNumber, tour, roundOnly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testCreateRanking_4args() {
        System.out.println("createRanking");
        int rNumber = 0;
        Tournament tour = null;
        boolean roundOnly = false;
        ArrayList<Team> teams = null;
        TeamRankingsSet instance = new TeamRankingsSet();
        instance.createRanking(rNumber, tour, roundOnly, teams);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class TeamRankingsSet.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        TeamRankingsSet instance = new TeamRankingsSet();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class TeamRankingsSet.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        TeamRankingsSet instance = new TeamRankingsSet();
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
