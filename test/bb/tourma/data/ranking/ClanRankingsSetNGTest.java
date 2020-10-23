/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Clan;
import bb.tourma.data.Tournament;
import bb.tourma.data.Formula;
import bb.tourma.data.Criterion;
import java.io.File;
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
public class ClanRankingsSetNGTest {

    public ClanRankingsSetNGTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
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
     * Test of setRoundOnly method, of class ClanRankingsSet.
     */
    @Test
    public void testSetRoundOnly() {
        System.out.println("setRoundOnly");
        boolean roundOnly = false;
        Tournament tour = Tournament.getTournament();
        ClanRankingsSet instance = tour.getRound(0).getRankings(false).getClanRankingSet();
        instance.setRoundOnly(roundOnly);
    }

    /**
     * Test of createRanking method, of class ClanRankingsSet. Subcase of other
     * function
     */
    @Test(enabled = false)
    public void testCreateRanking_3args() {
        System.out.println("createRanking");
        int rNumber = 0;
        Tournament tour = null;
        boolean roundOnly = false;
        ClanRankingsSet instance = new ClanRankingsSet();
        instance.createRanking(rNumber, tour, roundOnly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRanking method, of class ClanRankingsSet.
     */
    @Test
    public void testGetRanking() {
        System.out.println("getRanking");
        Tournament tour = Tournament.getTournament();
        ClanRankingsSet instance = tour.getRound(0).getRankings(false).getClanRankingSet();

        ClanRanking result = instance.getRanking();
        assertNotNull(result);
    }

    /**
     * Test of getAnnexPosRanking method, of class ClanRankingsSet.
     */
    @Test
    public void testGetAnnexPosRanking() {
        System.out.println("getAnnexPosRanking");
        Tournament tour = Tournament.getTournament();
        ClanRankingsSet instance = tour.getRound(0).getRankings(false).getClanRankingSet();

        HashMap<Criterion, AnnexClanRanking> result = instance.getAnnexPosRanking();
        AnnexClanRanking res = result.get(tour.getParams().getCriterion(0));
        assertNotNull(res);
    }

    /**
     * Test of getAnnexNegRanking method, of class ClanRankingsSet.
     */
    @Test
    public void testGetAnnexNegRanking() {
        System.out.println("getAnnexNegRanking");
        Tournament tour = Tournament.getTournament();
        ClanRankingsSet instance = tour.getRound(0).getRankings(false).getClanRankingSet();

        HashMap<Criterion, AnnexClanRanking> result = instance.getAnnexNegRanking();
        AnnexClanRanking res = result.get(tour.getParams().getCriterion(0));
        assertNotNull(res);
    }

    /**
     * Test of getAnnexDifRanking method, of class ClanRankingsSet.
     */
    @Test
    public void testGetAnnexDifRanking() {
        System.out.println("getAnnexDifRanking");
        Tournament tour = Tournament.getTournament();
        ClanRankingsSet instance = tour.getRound(0).getRankings(false).getClanRankingSet();

        HashMap<Criterion, AnnexClanRanking> result = instance.getAnnexPosRanking();
        AnnexClanRanking res = result.get(tour.getParams().getCriterion(0));
        assertNotNull(res);
    }

    /**
     * Test of getAnnexFormRanking method, of class ClanRankingsSet.
     */
    @Test
    public void testGetAnnexFormRanking() {
        System.out.println("getAnnexFormRanking");
        Tournament tour = Tournament.getTournament();
        ClanRankingsSet instance = tour.getRound(0).getRankings(false).getClanRankingSet();

        HashMap<Formula, AnnexClanRanking> result = instance.getAnnexFormRanking();
        AnnexClanRanking res = result.get(tour.getParams().getFormula(0));
        assertNotNull(res);
    }

    /**
     * Test of createRanking method, of class ClanRankingsSet.
     */
    @Test
    public void testCreateRanking_4args() {
        System.out.println("createRanking");
        int rNumber = 0;
        Tournament tour = Tournament.getTournament();
        boolean roundOnly = false;
        ArrayList<Clan> clans = tour.getClans();
        ClanRankingsSet instance = new ClanRankingsSet();
        instance.createRanking(rNumber, tour, roundOnly, clans);

        assertNotNull(instance.getRanking());
    }

    /**
     * Test of update method, of class ClanRankingsSet.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Tournament tour = Tournament.getTournament();
        ClanRankingsSet instance = tour.getRound(0).getRankings(false).getClanRankingSet();
        instance.update();
    }

    /**
     * Test of getXMLElement method, of class ClanRankingsSet.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Tournament tour = Tournament.getTournament();
        ClanRankingsSet instance = tour.getRound(0).getRankings(false).getClanRankingSet();
        Element result = instance.getXMLElement();

        ClanRankingsSet other = new ClanRankingsSet(result);

        assertNotNull(other);
    }

    /**
     * Test of setXMLElement method, of class ClanRankingsSet.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Tournament tour = Tournament.getTournament();
        ClanRankingsSet instance = tour.getRound(0).getRankings(false).getClanRankingSet();
        Element result = instance.getXMLElement();

        ClanRankingsSet other = new ClanRankingsSet(result);

        assertNotNull(other);
    }

}
