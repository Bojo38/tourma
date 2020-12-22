/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Clan;
import bb.tourma.data.Coach;
import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.Tournament;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
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
     * Test of setRoundOnly method, of class IndivRankingsSet.
     */
    @Test
    public void testSetRoundOnly() {
        System.out.println("setRoundOnly");
        boolean roundOnly = false;
        Tournament tour = Tournament.getTournament();
        IndivRankingsSet instance = tour.getRound(0).getRankings(false).getIndivRankingSet();
        instance.setRoundOnly(roundOnly);
    }

    /**
     * Test of update method, of class IndivRankingsSet.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Tournament tour = Tournament.getTournament();
        IndivRankingsSet instance = tour.getRound(0).getRankings(false).getIndivRankingSet();
        instance.update();
    }

    /**
     * Test of createRanking method, of class IndivRankingsSet.
     */
    @Test(enabled = false)
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
        Tournament tour = Tournament.getTournament();
        boolean roundOnly = false;
        ArrayList<Coach> clans = tour.getCoachs();
        IndivRankingsSet instance = new IndivRankingsSet();
        instance.createRanking(rNumber, tour, roundOnly, clans);

        assertNotNull(instance.getRanking());
    }

    /**
     * Test of getRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testGetRanking() {
        System.out.println("getRanking");
        Tournament tour = Tournament.getTournament();
        IndivRankingsSet instance = tour.getRound(0).getRankings(false).getIndivRankingSet();

        IndivRanking result = instance.getRanking();
        assertNotNull(result);
    }

    /**
     * Test of getAnnexPosRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testGetAnnexPosRanking() {
        System.out.println("getAnnexPosRanking");
        Tournament tour = Tournament.getTournament();
        IndivRankingsSet instance = tour.getRound(0).getRankings(false).getIndivRankingSet();

        TreeMap<Criterion, AnnexIndivRanking> result = instance.getAnnexPosRanking();
        AnnexIndivRanking res = result.get(tour.getParams().getCriterion(0));
        assertNotNull(res);
    }

    /**
     * Test of getAnnexNegRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testGetAnnexNegRanking() {
        System.out.println("getAnnexNegRanking");
        Tournament tour = Tournament.getTournament();
        IndivRankingsSet instance = tour.getRound(0).getRankings(false).getIndivRankingSet();

        TreeMap<Criterion, AnnexIndivRanking> result = instance.getAnnexNegRanking();
        AnnexIndivRanking res = result.get(tour.getParams().getCriterion(0));
        assertNotNull(res);
    }

    /**
     * Test of getAnnexDifRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testGetAnnexDifRanking() {
        System.out.println("getAnnexDifRanking");
        Tournament tour = Tournament.getTournament();
        IndivRankingsSet instance = tour.getRound(0).getRankings(false).getIndivRankingSet();

        TreeMap<Criterion, AnnexIndivRanking> result = instance.getAnnexDifRanking();
        AnnexIndivRanking res = result.get(tour.getParams().getCriterion(0));
        assertNotNull(res);
    }

    /**
     * Test of getAnnexFormRanking method, of class IndivRankingsSet.
     */
    @Test
    public void testGetAnnexFormRanking() {
        System.out.println("getAnnexFormRanking");
        Tournament tour = Tournament.getTournament();
        IndivRankingsSet instance = tour.getRound(0).getRankings(false).getIndivRankingSet();

        TreeMap<Formula, AnnexIndivRanking> result = instance.getAnnexFormRanking();
        AnnexIndivRanking res = result.get(tour.getParams().getFormula(0));
        assertNotNull(res);
    }

    /**
     * Test of getRankingForPool method, of class IndivRankingsSet.
     */
    @Test
    public void testGetRankingForPool() {
        System.out.println("getRankingForPool");
       Tournament.getTournament().loadXML(new File("./test/pools.xml"));
        Tournament tour = Tournament.getTournament();
        IndivRankingsSet instance = tour.getRound(0).getRankings(false).getIndivRankingSet();

        IndivRanking result = instance.getRanking();
        assertNotNull(result);

        result = instance.getRankingForPool();
        assertNotNull(result);
    }

    /**
     * Test of getRankingForCup method, of class IndivRankingsSet.
     */
    @Test
    public void testGetRankingForCup() {
        System.out.println("getRankingForCup");
        Tournament.getTournament().loadXML(new File("./test/cup_with_looser.xml"));
        Tournament tour = Tournament.getTournament();
        IndivRankingsSet instance = tour.getRound(Tournament.getTournament().getRoundsCount()-1).getRankings(false).getIndivRankingSet();

        IndivRanking result = instance.getRanking();
        assertNotNull(result);

        result = instance.getRankingForCup();
        assertNotNull(result);
    }

    /**
     * Test of getXMLElement method, of class IndivRankingsSet.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Tournament tour = Tournament.getTournament();
        IndivRankingsSet instance = tour.getRound(0).getRankings(false).getIndivRankingSet();
        Element result = instance.getXMLElement();

        IndivRankingsSet other = new IndivRankingsSet(result);

        assertNotNull(other);
    }

    /**
     * Test of setXMLElement method, of class IndivRankingsSet.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Tournament tour = Tournament.getTournament();
        IndivRankingsSet instance = tour.getRound(0).getRankings(false).getIndivRankingSet();
        Element result = instance.getXMLElement();

        IndivRankingsSet other = new IndivRankingsSet(result);

        assertNotNull(other);
    }

}
