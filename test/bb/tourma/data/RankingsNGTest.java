/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import bb.tourma.data.ranking.ClanRankingsSet;
import bb.tourma.data.ranking.IndivRanking;
import bb.tourma.data.ranking.IndivRankingsSet;
import bb.tourma.data.ranking.TeamRanking;
import bb.tourma.data.ranking.TeamRankingsSet;
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
public class RankingsNGTest {
    
    public RankingsNGTest() {
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
     * Test of setRoundOnly method, of class Rankings.
     */
    @Test
    public void testSetRoundOnly() {
        System.out.println("setRoundOnly");
        boolean roundOnly = false;
        Rankings instance = null;
        instance.setRoundOnly(roundOnly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamRankingSet method, of class Rankings.
     */
    @Test
    public void testGetTeamRankingSet() {
        System.out.println("getTeamRankingSet");
        Rankings instance = null;
        TeamRankingsSet expResult = null;
        TeamRankingsSet result = instance.getTeamRankingSet();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeamRankingSet method, of class Rankings.
     */
    @Test
    public void testSetTeamRankingSet() {
        System.out.println("setTeamRankingSet");
        TeamRankingsSet mTeamRankingSet = null;
        Rankings instance = null;
        instance.setTeamRankingSet(mTeamRankingSet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClanRankingSet method, of class Rankings.
     */
    @Test
    public void testGetClanRankingSet() {
        System.out.println("getClanRankingSet");
        Rankings instance = null;
        ClanRankingsSet expResult = null;
        ClanRankingsSet result = instance.getClanRankingSet();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClanRankingSet method, of class Rankings.
     */
    @Test
    public void testSetClanRankingSet() {
        System.out.println("setClanRankingSet");
        ClanRankingsSet mClanRankingSet = null;
        Rankings instance = null;
        instance.setClanRankingSet(mClanRankingSet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroupRanking method, of class Rankings.
     */
    @Test
    public void testGetGroupRanking() {
        System.out.println("getGroupRanking");
        Rankings instance = null;
        HashMap expResult = null;
        HashMap result = instance.getGroupRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGroupRanking method, of class Rankings.
     */
    @Test
    public void testSetGroupRanking() {
        System.out.println("setGroupRanking");
        HashMap<Group, IndivRanking> mGroupRanking = null;
        Rankings instance = null;
        instance.setGroupRanking(mGroupRanking);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoryIndivRanking method, of class Rankings.
     */
    @Test
    public void testGetCategoryIndivRanking() {
        System.out.println("getCategoryIndivRanking");
        Rankings instance = null;
        HashMap expResult = null;
        HashMap result = instance.getCategoryIndivRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategoryIndivRanking method, of class Rankings.
     */
    @Test
    public void testSetCategoryIndivRanking() {
        System.out.println("setCategoryIndivRanking");
        HashMap<Category, IndivRanking> mCategoryIndivRanking = null;
        Rankings instance = null;
        instance.setCategoryIndivRanking(mCategoryIndivRanking);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoryTeamRanking method, of class Rankings.
     */
    @Test
    public void testGetCategoryTeamRanking() {
        System.out.println("getCategoryTeamRanking");
        Rankings instance = null;
        HashMap expResult = null;
        HashMap result = instance.getCategoryTeamRanking();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategoryTeamRanking method, of class Rankings.
     */
    @Test
    public void testSetCategoryTeamRanking() {
        System.out.println("setCategoryTeamRanking");
        HashMap<Category, TeamRanking> mCategoryTeamRanking = null;
        Rankings instance = null;
        instance.setCategoryTeamRanking(mCategoryTeamRanking);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPoolIndivRankings method, of class Rankings.
     */
    @Test
    public void testGetPoolIndivRankings() {
        System.out.println("getPoolIndivRankings");
        Rankings instance = null;
        HashMap expResult = null;
        HashMap result = instance.getPoolIndivRankings();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPoolIndivRankings method, of class Rankings.
     */
    @Test
    public void testSetPoolIndivRankings() {
        System.out.println("setPoolIndivRankings");
        HashMap<Pool, IndivRankingsSet> mPoolIndivRankings = null;
        Rankings instance = null;
        instance.setPoolIndivRankings(mPoolIndivRankings);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPoolTeamRankings method, of class Rankings.
     */
    @Test
    public void testGetPoolTeamRankings() {
        System.out.println("getPoolTeamRankings");
        Rankings instance = null;
        HashMap expResult = null;
        HashMap result = instance.getPoolTeamRankings();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPoolTeamRankings method, of class Rankings.
     */
    @Test
    public void testSetPoolTeamRankings() {
        System.out.println("setPoolTeamRankings");
        HashMap<Pool, TeamRankingsSet> mPoolTeamRankings = null;
        Rankings instance = null;
        instance.setPoolTeamRankings(mPoolTeamRankings);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Rankings.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Rankings instance = null;
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRankings method, of class Rankings.
     */
    @Test
    public void testCreateRankings() {
        System.out.println("createRankings");
        int rNumber = 0;
        Tournament tour = null;
        Rankings instance = null;
        instance.createRankings(rNumber, tour);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndivRankingSet method, of class Rankings.
     */
    @Test
    public void testGetIndivRankingSet() {
        System.out.println("getIndivRankingSet");
        Rankings instance = null;
        IndivRankingsSet expResult = null;
        IndivRankingsSet result = instance.getIndivRankingSet();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIndivRankingSet method, of class Rankings.
     */
    @Test
    public void testSetIndivRankingSet() {
        System.out.println("setIndivRankingSet");
        IndivRankingsSet mIndivRankingSet = null;
        Rankings instance = null;
        instance.setIndivRankingSet(mIndivRankingSet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Rankings.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Rankings instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Rankings.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        Rankings instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
