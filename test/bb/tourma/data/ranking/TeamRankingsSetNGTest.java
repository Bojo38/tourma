/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Criterion;
import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.Formula;
import bb.tourma.data.Team;
import bb.tourma.data.Team;
import bb.tourma.data.Tournament;
import bb.tourma.data.Tournament;
import bb.tourma.data.ranking.AnnexTeamRanking;
import bb.tourma.data.ranking.TeamRanking;
import bb.tourma.data.ranking.TeamRankingsSet;
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
public class TeamRankingsSetNGTest {

    public TeamRankingsSetNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        Tournament.clear();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/pools_team.xml"));
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
        Tournament tour = Tournament.getTournament();
        TeamRankingsSet instance = tour.getRound(0).getRankings(false).getTeamRankingSet();
        instance.setRoundOnly(roundOnly);
    }

    /**
     * Test of update method, of class TeamRankingsSet.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Tournament tour = Tournament.getTournament();
        TeamRankingsSet instance = tour.getRound(0).getRankings(false).getTeamRankingSet();
        instance.update();
    }

    /**
     * Test of getRankingForPool method, of class TeamRankingsSet.
     */
    @Test
    public void testGetRankingForPool() {
        System.out.println("getmRankingForPool");

        Tournament tour = Tournament.getTournament();

        TeamRankingsSet instance = tour.getRound(0).getRankings(false).getTeamRankingSet();

        TeamRanking result = instance.getRanking();
        assertNotNull(result);

        result = instance.getRankingForPool();
        assertNotNull(result);
    }

    /**
     * Test of getRankingForCup method, of class TeamRankingsSet.
     */
    @Test
    public void testGetRankingForCup() {
        System.out.println("getmRankingForCup");
        TeamRankingsSet instance = Tournament.getTournament().getRound(0).getRankings(false).getTeamRankingSet();
        TeamRanking result = instance.getRanking();
        assertNotNull(result);
    }

    /**
     * Test of getRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testGetRanking() {
        System.out.println("getRanking");
        TeamRankingsSet instance = Tournament.getTournament().getRound(0).getRankings(false).getTeamRankingSet();
        TeamRanking result = instance.getRanking();
        assertNotNull(result);
    }

    /**
     * Test of getAnnexPosRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testGetAnnexPosRanking() {
        System.out.println("getAnnexPosRanking");
        Tournament tour = Tournament.getTournament();
        TeamRankingsSet instance = tour.getRound(0).getRankings(false).getTeamRankingSet();

        HashMap<Criterion, AnnexTeamRanking> result = instance.getAnnexPosRanking();
        AnnexTeamRanking res = result.get(tour.getParams().getCriterion(0));
        assertNotNull(res);
    }

    /**
     * Test of getAnnexNegRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testGetAnnexNegRanking() {
        System.out.println("getAnnexNegRanking");
        Tournament tour = Tournament.getTournament();
        TeamRankingsSet instance = tour.getRound(0).getRankings(false).getTeamRankingSet();

        HashMap<Criterion, AnnexTeamRanking> result = instance.getAnnexNegRanking();
        AnnexTeamRanking res = result.get(tour.getParams().getCriterion(0));
        assertNotNull(res);
    }

    /**
     * Test of getAnnexDifRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testGetAnnexDifRanking() {
        System.out.println("getAnnexDifRanking");
        Tournament tour = Tournament.getTournament();
        TeamRankingsSet instance = tour.getRound(0).getRankings(false).getTeamRankingSet();

        HashMap<Criterion, AnnexTeamRanking> result = instance.getAnnexDifRanking();
        AnnexTeamRanking res = result.get(tour.getParams().getCriterion(0));
        assertNotNull(res);
    }

    /**
     * Test of getAnnexFormRanking method, of class TeamRankingsSet.
     */
    @Test
    public void testGetAnnexFormRanking() {
        System.out.println("getAnnexFormRanking");
        Tournament tour = Tournament.getTournament();
        TeamRankingsSet instance = tour.getRound(0).getRankings(false).getTeamRankingSet();

        HashMap<Formula, AnnexTeamRanking> result = instance.getAnnexFormRanking();
        AnnexTeamRanking res = result.get(tour.getParams().getFormula(0));
        assertNotNull(res);
    }

    /**
     * Test of createRanking method, of class TeamRankingsSet.
     */
    @Test(enabled = false)
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
        Tournament tour = Tournament.getTournament();
        boolean roundOnly = false;
        ArrayList<Team> teams = tour.getTeams();
        TeamRankingsSet instance = new TeamRankingsSet();
        instance.createRanking(0, tour, roundOnly, teams);

        assertNotNull(instance.getRanking());
    }

    /**
     * Test of getXMLElement method, of class TeamRankingsSet.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Tournament tour = Tournament.getTournament();
        TeamRankingsSet instance = tour.getRound(0).getRankings(false).getTeamRankingSet();
        Element result = instance.getXMLElement();

        TeamRankingsSet other = new TeamRankingsSet(result);

        assertNotNull(other);
    }

    /**
     * Test of setXMLElement method, of class TeamRankingsSet.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Tournament tour = Tournament.getTournament();
        TeamRankingsSet instance = tour.getRound(0).getRankings(false).getTeamRankingSet();
        Element result = instance.getXMLElement();

        TeamRankingsSet other = new TeamRankingsSet(result);

        assertNotNull(other);
    }

}
