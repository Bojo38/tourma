/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdom.Element;
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
public class ParametersNGTest {

    public ParametersNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/params.xml"));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getXMLElement method, of class Parameters.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Parameters instance = Tournament.getTournament().getParams();
        Element result = instance.getXMLElement();

        Parameters instance2 = new Parameters();
        instance2.setXMLElement(result);
        assertEquals(instance, instance2);
    }

    /**
     * Test of setXMLElement method, of class Parameters.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Parameters instance = Tournament.getTournament().getParams();
        Element result = instance.getXMLElement();

        Parameters instance2 = new Parameters();
        instance2.setXMLElement(result);
        assertEquals(instance, instance2);
    }

    /**
     * Test of toString method, of class Parameters.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Parameters instance = new Parameters();
        String expResult = "Paramètres";
        String result = instance.toString();
        assertEquals(result, expResult);
    }

    /**
     * Test of getTeamRankingType method, of class Parameters.
     */
    @Test
    public void testGetTeamRankingType() {
        System.out.println("getTeamRankingType");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getIndivRankingType(0);
        instance.setRankingIndiv1(Parameters.C_RANKING_POINTS);
        val = instance.getIndivRankingType(0);
        assertEquals(Parameters.C_RANKING_POINTS, val);

        instance.setRankingIndiv2(Parameters.C_RANKING_NB_MATCHS);
        val = instance.getIndivRankingType(1);
        assertEquals(Parameters.C_RANKING_NB_MATCHS, val);

        instance.setRankingIndiv3(Parameters.C_RANKING_VND);
        val = instance.getIndivRankingType(2);
        assertEquals(Parameters.C_RANKING_VND, val);

        instance.setRankingIndiv4(Parameters.C_RANKING_ELO);
        val = instance.getIndivRankingType(3);
        assertEquals(Parameters.C_RANKING_ELO, val);

        instance.setRankingIndiv5(Parameters.C_RANKING_ELO_OPP);
        val = instance.getIndivRankingType(4);
        assertEquals(Parameters.C_RANKING_ELO_OPP, val);
    }

    /**
     * Test of getIndivRankingType method, of class Parameters.
     */
    @Test
    public void testGetIndivRankingType() {
        System.out.println("getIndivRankingType");
        Parameters instance = Tournament.getTournament().getParams();

        int val = instance.getTeamRankingType(0);
        instance.setRankingTeam1(Parameters.C_RANKING_POINTS);
        val = instance.getTeamRankingType(0);
        assertEquals(Parameters.C_RANKING_POINTS, val);

        instance.setRankingTeam2(Parameters.C_RANKING_NB_MATCHS);
        val = instance.getTeamRankingType(1);
        assertEquals(Parameters.C_RANKING_NB_MATCHS, val);

        instance.setRankingTeam3(Parameters.C_RANKING_VND);
        val = instance.getTeamRankingType(2);
        assertEquals(Parameters.C_RANKING_VND, val);

        instance.setRankingTeam4(Parameters.C_RANKING_ELO);
        val = instance.getTeamRankingType(3);
        assertEquals(Parameters.C_RANKING_ELO, val);

        instance.setRankingTeam5(Parameters.C_RANKING_ELO_OPP);
        val = instance.getTeamRankingType(4);
        assertEquals(Parameters.C_RANKING_ELO_OPP, val);

    }

    /**
     * Test of getIndivRankingNumber method, of class Parameters.
     */
    @Test
    public void testGetIndivRankingNumber() {
        System.out.println("getIndivRankingNumber");
        Parameters instance = new Parameters();
        int expResult = 5;
        instance.setRankingIndiv1(Parameters.C_RANKING_POINTS);
        instance.setRankingIndiv2(Parameters.C_RANKING_POINTS);
        instance.setRankingIndiv3(Parameters.C_RANKING_POINTS);
        instance.setRankingIndiv4(Parameters.C_RANKING_POINTS);
        instance.setRankingIndiv5(Parameters.C_RANKING_POINTS);
        int result = instance.getIndivRankingNumber();
        assertEquals(result, expResult);
        instance.setRankingIndiv4(Parameters.C_RANKING_NONE);
        instance.setRankingIndiv5(Parameters.C_RANKING_NONE);
        expResult = 3;
        result = instance.getIndivRankingNumber();
        assertEquals(result, expResult);

    }

    /**
     * Test of getTeamRankingNumber method, of class Parameters.
     */
    @Test
    public void testGetTeamRankingNumber() {
        System.out.println("getTeamRankingNumber");
        Parameters instance = new Parameters();
        int expResult = 5;
        instance.setRankingTeam1(Parameters.C_RANKING_ELO);
        instance.setRankingTeam2(Parameters.C_RANKING_ELO);
        instance.setRankingTeam3(Parameters.C_RANKING_ELO);
        instance.setRankingTeam4(Parameters.C_RANKING_ELO);
        instance.setRankingTeam5(Parameters.C_RANKING_ELO);
        int result = instance.getTeamRankingNumber();
        assertEquals(result, expResult);
        expResult = 3;
        instance.setRankingTeam4(Parameters.C_RANKING_NONE);
        instance.setRankingTeam5(Parameters.C_RANKING_NONE);
        result = instance.getTeamRankingNumber();
        assertEquals(result, expResult);

    }

    /**
     * Test of getCriteriaCount method, of class Parameters.
     */
    @Test
    public void testGetCriteriaCount() {
        System.out.println("getCriteriaCount");
        Criteria c = new Criteria("T");
        Parameters instance = new Parameters();
        int nb = instance.getCriteriaCount();
        instance.addCriteria(c);
        assertEquals(nb + 1, instance.getCriteriaCount());
    }

    /**
     * Test of getCriteria method, of class Parameters.
     */
    @Test
    public void testGetCriteria() {
        System.out.println("getCriteria");
        Criteria c = new Criteria("T");
        Parameters instance = new Parameters();
        int nb = instance.getCriteriaCount();
        instance.addCriteria(c);
        assertEquals(c, instance.getCriteria(nb));
    }

    /**
     * Test of clearCiterias method, of class Parameters.
     */
    @Test
    public void testClearCiterias() {
        System.out.println("clearCiterias");
        Criteria c = new Criteria("T");
        Parameters instance = new Parameters();
        int nb = instance.getCriteriaCount();
        instance.addCriteria(c);
        assertEquals(nb + 1, instance.getCriteriaCount());
        instance.clearCiterias();
        assertEquals(0, instance.getCriteriaCount());
    }

    /**
     * Test of addCriteria method, of class Parameters.
     */
    @Test
    public void testAddCriteria() {
        System.out.println("addCriteria");
        Criteria c = new Criteria("T");
        Parameters instance = new Parameters();
        int nb = instance.getCriteriaCount();
        instance.addCriteria(c);
        assertEquals(nb + 1, instance.getCriteriaCount());
    }

    /**
     * Test of removeCriteria method, of class Parameters.
     */
    @Test
    public void testRemoveCriteria() {
        System.out.println("removeCriteria");
        Criteria c = new Criteria("T");
        Parameters instance = new Parameters();
        int nb = instance.getCriteriaCount();
        instance.addCriteria(c);
        assertEquals(nb + 1, instance.getCriteriaCount());
        instance.removeCriteria(nb);
        assertEquals(nb, instance.getCriteriaCount());
    }


    /**
     * Test of getPointsIndivVictory method, of class Parameters.
     */
    @Test
    public void testGetPointsIndivVictory() {
        System.out.println("getPointsIndivVictory");
        Parameters instance = new Parameters();
        int expResult = 584;
        instance.setPointsIndivVictory(expResult);
        int result = instance.getPointsIndivVictory();
        assertEquals(result, expResult);
    }

    /**
     * Test of setPointsIndivVictory method, of class Parameters.
     */
    @Test
    public void testSetPointsIndivVictory() {
        System.out.println("setPointsIndivVictory");
        Parameters instance = new Parameters();
        int expResult = 584;
        instance.setPointsIndivVictory(expResult);
        int result = instance.getPointsIndivVictory();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPointsIndivLargeVictory method, of class Parameters.
     */
    @Test
    public void testGetPointsIndivLargeVictory() {
        System.out.println("getPointsIndivLargeVictory");
        Parameters instance = new Parameters();
        int expResult = 3512;
        instance.setPointsIndivLargeVictory(expResult);
        int result = instance.getPointsIndivLargeVictory();
        assertEquals(result, expResult);

    }

    /**
     * Test of setPointsIndivLargeVictory method, of class Parameters.
     */
    @Test
    public void testSetPointsIndivLargeVictory() {
        System.out.println("setPointsIndivLargeVictory");
        Parameters instance = new Parameters();
        int expResult = 3512;
        instance.setPointsIndivLargeVictory(expResult);
        int result = instance.getPointsIndivLargeVictory();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPointsIndivDraw method, of class Parameters.
     */
    @Test
    public void testGetPointsIndivDraw() {
        System.out.println("getPointsIndivDraw");
        Parameters instance = new Parameters();
        int expResult = 123;
        instance.setPointsIndivDraw(expResult);
        int result = instance.getPointsIndivDraw();
        assertEquals(result, expResult);
    }

    /**
     * Test of setPointsIndivDraw method, of class Parameters.
     */
    @Test
    public void testSetPointsIndivDraw() {
        System.out.println("setPointsIndivDraw");
        Parameters instance = new Parameters();
        int expResult = 123;
        instance.setPointsIndivDraw(expResult);
        int result = instance.getPointsIndivDraw();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPointsIndivLittleLost method, of class Parameters.
     */
    @Test
    public void testGetPointsIndivLittleLost() {
        System.out.println("getPointsIndivLittleLost");
        Parameters instance = new Parameters();
        int expResult = 456;
        instance.setPointsIndivLittleLost(expResult);
        int result = instance.getPointsIndivLittleLost();
        assertEquals(result, expResult);
    }

    /**
     * Test of setPointsIndivLittleLost method, of class Parameters.
     */
    @Test
    public void testSetPointsIndivLittleLost() {
        System.out.println("setPointsIndivLittleLost");
        Parameters instance = new Parameters();
        int expResult = 456;
        instance.setPointsIndivLittleLost(expResult);
        int result = instance.getPointsIndivLittleLost();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPointsIndivLost method, of class Parameters.
     */
    @Test
    public void testGetPointsIndivLost() {
        System.out.println("getPointsIndivLost");
        Parameters instance = new Parameters();
        int expResult = 789;
        instance.setPointsIndivLost(expResult);
        int result = instance.getPointsIndivLost();
        assertEquals(result, expResult);
    }

    /**
     * Test of setPointsIndivLost method, of class Parameters.
     */
    @Test
    public void testSetPointsIndivLost() {
        System.out.println("setPointsIndivLost");
        Parameters instance = new Parameters();
        int expResult = 789;
        instance.setPointsIndivLost(expResult);
        int result = instance.getPointsIndivLost();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPointsRefused method, of class Parameters.
     */
    @Test
    public void testGetPointsRefused() {
        System.out.println("getPointsRefused");
        Parameters instance = new Parameters();
        int expResult = -9812;
        instance.setPointsRefused(expResult);
        int result = instance.getPointsRefused();
        assertEquals(result, expResult);

    }

    /**
     * Test of setPointsRefused method, of class Parameters.
     */
    @Test
    public void testSetPointsRefused() {
        System.out.println("setPointsRefused");
        Parameters instance = new Parameters();
        int expResult = -9812;
        instance.setPointsRefused(expResult);
        int result = instance.getPointsRefused();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPointsConcedeed method, of class Parameters.
     */
    @Test
    public void testGetPointsConcedeed() {
        System.out.println("getPointsConcedeed");
        Parameters instance = new Parameters();
        int expResult = -1200;
        instance.setPointsConcedeed(expResult);
        int result = instance.getPointsConcedeed();
        assertEquals(result, expResult);
    }

    /**
     * Test of setPointsConcedeed method, of class Parameters.
     */
    @Test
    public void testSetPointsConcedeed() {
        System.out.println("setPointsConcedeed");
        Parameters instance = new Parameters();
        int expResult = -1200;
        instance.setPointsConcedeed(expResult);
        int result = instance.getPointsConcedeed();
        assertEquals(result, expResult);
    }

    /**
     * Test of isTeamVictoryOnly method, of class Parameters.
     */
    @Test
    public void testIsTeamVictoryOnly() {
        System.out.println("isTeamVictoryOnly");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setTeamVictoryOnly(expResult);
        boolean result = instance.isTeamVictoryOnly();
        assertEquals(result, expResult);

    }

    /**
     * Test of setTeamVictoryOnly method, of class Parameters.
     */
    @Test
    public void testSetTeamVictoryOnly() {
        System.out.println("setTeamVictoryOnly");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setTeamVictoryOnly(expResult);
        boolean result = instance.isTeamVictoryOnly();
        assertEquals(result, expResult);
    }

    /**
     * Test of isPortugal method, of class Parameters.
     */
    @Test
    public void testIsPortugal() {
        System.out.println("isPortugal");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setPortugal(expResult);
        boolean result = instance.isPortugal();
        assertEquals(result, expResult);
    }

    /**
     * Test of setPortugal method, of class Parameters.
     */
    @Test
    public void testSetPortugal() {
        System.out.println("setPortugal");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setPortugal(expResult);
        boolean result = instance.isPortugal();
        assertEquals(result, expResult);
    }

    /**
     * Test of getGapLargeVictory method, of class Parameters.
     */
    @Test
    public void testGetGapLargeVictory() {
        System.out.println("getGapLargeVictory");
        Parameters instance = new Parameters();
        int expResult = 3;
        instance.setGapLargeVictory(3);
        int result = instance.getGapLargeVictory();
        assertEquals(result, expResult);

    }

    /**
     * Test of setGapLargeVictory method, of class Parameters.
     */
    @Test
    public void testSetGapLargeVictory() {
        System.out.println("setGapLargeVictory");
        Parameters instance = new Parameters();
        int expResult = 3;
        instance.setGapLargeVictory(3);
        int result = instance.getGapLargeVictory();
        assertEquals(result, expResult);
    }

    /**
     * Test of getGapLittleLost method, of class Parameters.
     */
    @Test
    public void testGetGapLittleLost() {
        System.out.println("getGapLittleLost");
        Parameters instance = new Parameters();
        int expResult = 1;
        instance.setGapLittleLost(expResult);
        int result = instance.getGapLittleLost();
        assertEquals(result, expResult);
    }

    /**
     * Test of setGapLittleLost method, of class Parameters.
     */
    @Test
    public void testSetGapLittleLost() {
        System.out.println("setGapLittleLost");
        Parameters instance = new Parameters();
        int expResult = 1;
        instance.setGapLittleLost(expResult);
        int result = instance.getGapLittleLost();
        assertEquals(result, expResult);
    }

    /**
     * Test of isSubstitutes method, of class Parameters.
     */
    @Test
    public void testIsSubstitutes() {
        System.out.println("isSubstitutes");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setSubstitutes(expResult);
        boolean result = instance.isSubstitutes();
        assertEquals(result, expResult);
    }

    /**
     * Test of setSubstitutes method, of class Parameters.
     */
    @Test
    public void testSetSubstitutes() {
        System.out.println("setSubstitutes");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setSubstitutes(expResult);
        boolean result = instance.isSubstitutes();
        assertEquals(result, expResult);
    }

    /**
     * Test of getTournamentName method, of class Parameters.
     */
    @Test
    public void testGetTournamentName() {
        System.out.println("getTournamentName");
        Parameters instance = new Parameters();
        String expResult = "BlaBla";
        instance.setTournamentName(expResult);
        String result = instance.getTournamentName();
        assertEquals(result, expResult);

    }

    /**
     * Test of setTournamentName method, of class Parameters.
     */
    @Test
    public void testSetTournamentName() {
        System.out.println("setTournamentName");
        Parameters instance = new Parameters();
        String expResult = "BlaBla";
        instance.setTournamentName(expResult);
        String result = instance.getTournamentName();
        assertEquals(result, expResult);
    }

    /**
     * Test of getTournamentOrga method, of class Parameters.
     */
    @Test
    public void testGetTournamentOrga() {
        System.out.println("getTournamentOrga");
        Parameters instance = new Parameters();
        String expResult = "Moi";
        instance.semTournamentOrga(expResult);
        String result = instance.getTournamentOrga();
        assertEquals(result, expResult);
    }

    /**
     * Test of semTournamentOrga method, of class Parameters.
     */
    @Test
    public void testSemTournamentOrga() {
        System.out.println("semTournamentOrga");
        Parameters instance = new Parameters();
        String expResult = "Moi";
        instance.semTournamentOrga(expResult);
        String result = instance.getTournamentOrga();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPlace method, of class Parameters.
     */
    @Test
    public void testGetPlace() {
        System.out.println("getPlace");
        Parameters instance = new Parameters();
        String expResult = "Ici";
        instance.setPlace(expResult);
        String result = instance.getPlace();
        assertEquals(result, expResult);
    }

    /**
     * Test of setPlace method, of class Parameters.
     */
    @Test
    public void testSetPlace() {
        System.out.println("setPlace");
        Parameters instance = new Parameters();
        String expResult = "Ici";
        instance.setPlace(expResult);
        String result = instance.getPlace();
        assertEquals(result, expResult);
    }

    /**
     * Test of getStringDate method, of class Parameters.
     */
    @Test
    public void testGetStringDate() {
        System.out.println("getStringDate");
        Parameters instance = new Parameters();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:MM:SS");
        String expResult = sdf.format(d);
        String result = instance.getStringDate(sdf);
        assertEquals(result, expResult);

    }

    /**
     * Test of getDateTime method, of class Parameters.
     */
    @Test
    public void testGetDateTime() {
        System.out.println("getDateTime");
        Parameters instance = new Parameters();
        Date d = new Date();
        instance.setDate(d);
        long result = instance.getDateTime();
        assertEquals(result, d.getTime());
    }

    /**
     * Test of setDate method, of class Parameters.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Parameters instance = new Parameters();
        Date d = new Date();
        instance.setDate(d);
        long result = instance.getDateTime();
        assertEquals(result, d.getTime());
    }

    /**
     * Test of getRankingIndiv1 method, of class Parameters.
     */
    @Test
    public void testGetRankingIndiv1() {
        System.out.println("getRankingIndiv1");
        Parameters instance = Tournament.getTournament().getParams();

        int val = instance.getRankingIndiv1();
        instance.setRankingIndiv1(Parameters.C_RANKING_POINTS);
        val = instance.getRankingIndiv1();
        assertEquals(Parameters.C_RANKING_POINTS, val);

    }

    /**
     * Test of setRankingIndiv1 method, of class Parameters.
     */
    @Test
    public void testSetRankingIndiv1() {
        System.out.println("setRankingIndiv1");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingIndiv1();
        instance.setRankingIndiv1(Parameters.C_RANKING_POINTS);
        val = instance.getRankingIndiv1();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of getRankingIndiv2 method, of class Parameters.
     */
    @Test
    public void testGetRankingIndiv2() {
        System.out.println("getRankingIndiv2");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingIndiv2();
        instance.setRankingIndiv2(Parameters.C_RANKING_POINTS);
        val = instance.getRankingIndiv2();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of setRankingIndiv2 method, of class Parameters.
     */
    @Test
    public void testSetRankingIndiv2() {
        System.out.println("setRankingIndiv2");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingIndiv2();
        instance.setRankingIndiv2(Parameters.C_RANKING_POINTS);
        val = instance.getRankingIndiv2();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of getRankingIndiv3 method, of class Parameters.
     */
    @Test
    public void testGetRankingIndiv3() {
        System.out.println("getRankingIndiv3");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingIndiv3();
        instance.setRankingIndiv3(Parameters.C_RANKING_POINTS);
        val = instance.getRankingIndiv3();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of setRankingIndiv3 method, of class Parameters.
     */
    @Test
    public void testSetRankingIndiv3() {
        System.out.println("setRankingIndiv3");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingIndiv3();
        instance.setRankingIndiv3(Parameters.C_RANKING_POINTS);
        val = instance.getRankingIndiv3();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of getRankingIndiv4 method, of class Parameters.
     */
    @Test
    public void testGetRankingIndiv4() {
        System.out.println("getRankingIndiv4");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingIndiv4();
        instance.setRankingIndiv4(Parameters.C_RANKING_POINTS);
        val = instance.getRankingIndiv4();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of setRankingIndiv4 method, of class Parameters.
     */
    @Test
    public void testSetRankingIndiv4() {
        System.out.println("setRankingIndiv4");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingIndiv4();
        instance.setRankingIndiv4(Parameters.C_RANKING_POINTS);
        val = instance.getRankingIndiv4();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of getRankingIndiv5 method, of class Parameters.
     */
    @Test
    public void testGetRankingIndiv5() {
        System.out.println("getRankingIndiv5");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingIndiv5();
        instance.setRankingIndiv5(Parameters.C_RANKING_POINTS);
        val = instance.getRankingIndiv5();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of setRankingIndiv5 method, of class Parameters.
     */
    @Test
    public void testSetRankingIndiv5() {
        System.out.println("setRankingIndiv5");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingIndiv5();
        instance.setRankingIndiv5(Parameters.C_RANKING_POINTS);
        val = instance.getRankingIndiv5();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of isTeamTournament method, of class Parameters.
     */
    @Test
    public void testIsTeamTournament() {
        System.out.println("isTeamTournament");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setTeamTournament(expResult);
        boolean result = instance.isTeamTournament();
        assertEquals(result, expResult);

    }

    /**
     * Test of setTeamTournament method, of class Parameters.
     */
    @Test
    public void testSetTeamTournament() {
        System.out.println("setTeamTournament");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setTeamTournament(expResult);
        boolean result = instance.isTeamTournament();
        assertEquals(result, expResult);
    }

    /**
     * Test of isMultiRoster method, of class Parameters.
     */
    @Test
    public void testIsMultiRoster() {
        System.out.println("isMultiRoster");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setMultiRoster(expResult);
        boolean result = instance.isMultiRoster();
        assertEquals(result, expResult);
    }

    /**
     * Test of setMultiRoster method, of class Parameters.
     */
    @Test
    public void testSetMultiRoster() {
        System.out.println("setMultiRoster");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setMultiRoster(expResult);
        boolean result = instance.isMultiRoster();
        assertEquals(result, expResult);
    }

    /**
     * Test of getTeamPairing method, of class Parameters.
     */
    @Test
    public void testGetTeamPairing() {
        System.out.println("getTeamPairing");
        Parameters instance = new Parameters();
        ETeamPairing expResult = ETeamPairing.INDIVIDUAL_PAIRING;
        instance.setTeamPairing(expResult);
        ETeamPairing result = instance.getTeamPairing();
        assertEquals(result, expResult);

    }

    /**
     * Test of setTeamPairing method, of class Parameters.
     */
    @Test
    public void testSetTeamPairing() {
        System.out.println("setTeamPairing");
        Parameters instance = new Parameters();
        ETeamPairing expResult = ETeamPairing.INDIVIDUAL_PAIRING;
        instance.setTeamPairing(expResult);
        ETeamPairing result = instance.getTeamPairing();
        assertEquals(result, expResult);
    }

    /**
     * Test of getTeamIndivPairing method, of class Parameters.
     */
    @Test
    public void testGetTeamIndivPairing() {
        System.out.println("getTeamIndivPairing");
        Parameters instance = new Parameters();
        EIndivPairing expResult = EIndivPairing.RANDOM;
        instance.setTeamIndivPairing(expResult);
        EIndivPairing result = instance.getTeamIndivPairing();
        assertEquals(result, expResult);

    }

    /**
     * Test of setTeamIndivPairing method, of class Parameters.
     */
    @Test
    public void testSetTeamIndivPairing() {
        System.out.println("setTeamIndivPairing");
        Parameters instance = new Parameters();
        EIndivPairing expResult = EIndivPairing.RANDOM;
        instance.setTeamIndivPairing(expResult);
        EIndivPairing result = instance.getTeamIndivPairing();
        assertEquals(result, expResult);
    }

    /**
     * Test of getTeamMatesNumber method, of class Parameters.
     */
    @Test
    public void testGetTeamMatesNumber() {
        System.out.println("getTeamMatesNumber");
        Parameters instance = new Parameters();
        int expResult = 3;
        instance.setTeamMatesNumber(expResult);
        int result = instance.getTeamMatesNumber();
        assertEquals(result, expResult);
    }

    /**
     * Test of setTeamMatesNumber method, of class Parameters.
     */
    @Test
    public void testSetTeamMatesNumber() {
        System.out.println("setTeamMatesNumber");
        Parameters instance = new Parameters();
        int expResult = 3;
        instance.setTeamMatesNumber(expResult);
        int result = instance.getTeamMatesNumber();
        assertEquals(result, expResult);
    }

    /**
     * Test of getClansMembersNumber method, of class Parameters.
     */
    @Test
    public void testGetClansMembersNumber() {
        System.out.println("getClansMembersNumber");
        Parameters instance = new Parameters();
        int expResult = 3;
        instance.setClansMembersNumber(expResult);
        int result = instance.getClansMembersNumber();
        assertEquals(result, expResult);

    }

    /**
     * Test of setClansMembersNumber method, of class Parameters.
     */
    @Test
    public void testSetClansMembersNumber() {
        System.out.println("setClansMembersNumber");
        Parameters instance = new Parameters();
        int expResult = 3;
        instance.setClansMembersNumber(expResult);
        int result = instance.getClansMembersNumber();
        assertEquals(result, expResult);
    }

    /**
     * Test of isIndivPairingTeamBalanced method, of class Parameters.
     */
    @Test
    public void testIsIndivPairingTeamBalanced() {
        System.out.println("isIndivPairingTeamBalanced");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setIndivPairingTeamBalanced(expResult);
        boolean result = instance.isIndivPairingTeamBalanced();
        assertEquals(result, expResult);
    }

    /**
     * Test of setIndivPairingTeamBalanced method, of class Parameters.
     */
    @Test
    public void testSetIndivPairingTeamBalanced() {
        System.out.println("setIndivPairingTeamBalanced");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setIndivPairingTeamBalanced(expResult);
        boolean result = instance.isIndivPairingTeamBalanced();
        assertEquals(result, expResult);
    }

    /**
     * Test of isIndivPairingIndivBalanced method, of class Parameters.
     */
    @Test
    public void testIsIndivPairingIndivBalanced() {
        System.out.println("isIndivPairingIndivBalanced");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setIndivPairingIndivBalanced(expResult);
        boolean result = instance.isIndivPairingIndivBalanced();
        assertEquals(result, expResult);
    }

    /**
     * Test of setIndivPairingIndivBalanced method, of class Parameters.
     */
    @Test
    public void testSetIndivPairingIndivBalanced() {
        System.out.println("setIndivPairingIndivBalanced");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setIndivPairingIndivBalanced(expResult);
        boolean result = instance.isIndivPairingIndivBalanced();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPointsTeamVictory method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamVictory() {
        System.out.println("getPointsTeamVictory");
        Parameters instance = new Parameters();
        int expResult = 4587;
        instance.setPointsTeamVictory(expResult);
        int result = instance.getPointsTeamVictory();
        assertEquals(result, expResult);

    }

    /**
     * Test of setPointsTeamVictory method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamVictory() {
        System.out.println("setPointsTeamVictory");
        Parameters instance = new Parameters();
        int expResult = 4587;
        instance.setPointsTeamVictory(expResult);
        int result = instance.getPointsTeamVictory();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPointsTeamDraw method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamDraw() {
        System.out.println("getPointsTeamDraw");
        Parameters instance = new Parameters();
        int expResult = 54;
        instance.setPointsTeamDraw(expResult);
        int result = instance.getPointsTeamDraw();
        assertEquals(result, expResult);

    }

    /**
     * Test of setPointsTeamDraw method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamDraw() {
        System.out.println("setPointsTeamDraw");
        Parameters instance = new Parameters();
        int expResult = 54;
        instance.setPointsTeamDraw(expResult);
        int result = instance.getPointsTeamDraw();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPointsTeamLost method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamLost() {
        System.out.println("getPointsTeamLost");
        Parameters instance = new Parameters();
        int expResult = -1;
        instance.setPointsTeamLost(expResult);
        int result = instance.getPointsTeamLost();
        assertEquals(result, expResult);

    }

    /**
     * Test of setPointsTeamLost method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamLost() {
        System.out.println("setPointsTeamLost");
        Parameters instance = new Parameters();
        int expResult = -1;
        instance.setPointsTeamLost(expResult);
        int result = instance.getPointsTeamLost();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRankingTeam1 method, of class Parameters.
     */
    @Test
    public void testGetRankingTeam1() {
        System.out.println("getRankingTeam1");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingTeam1();
        instance.setRankingTeam1(Parameters.C_RANKING_POINTS);
        val = instance.getRankingTeam1();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of setRankingTeam1 method, of class Parameters.
     */
    @Test
    public void testSetRankingTeam1() {
        System.out.println("setRankingTeam1");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingTeam1();
        instance.setRankingTeam1(Parameters.C_RANKING_POINTS);
        val = instance.getRankingTeam1();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of gemRankingTeam2 method, of class Parameters.
     */
    @Test
    public void testGetRankingTeam2() {
        System.out.println("getRankingTeam2");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingTeam2();
        instance.setRankingTeam2(Parameters.C_RANKING_POINTS);
        val = instance.getRankingTeam2();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of setRankingTeam2 method, of class Parameters.
     */
    @Test
    public void testSetRankingTeam2() {
        System.out.println("setRankingTeam2");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingTeam2();
        instance.setRankingTeam2(Parameters.C_RANKING_POINTS);
        val = instance.getRankingTeam2();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of getRankingTeam3 method, of class Parameters.
     */
    @Test
    public void testGetRankingTeam3() {
        System.out.println("getRankingTeam3");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingTeam3();
        instance.setRankingTeam3(Parameters.C_RANKING_POINTS);
        val = instance.getRankingTeam3();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of setRankingTeam3 method, of class Parameters.
     */
    @Test
    public void testSetRankingTeam3() {
        System.out.println("setRankingTeam3");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingTeam3();
        instance.setRankingTeam3(Parameters.C_RANKING_POINTS);
        val = instance.getRankingTeam3();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of getRankingTeam4 method, of class Parameters.
     */
    @Test
    public void testGetRankingTeam4() {
        System.out.println("getRankingTeam4");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingTeam4();
        instance.setRankingTeam4(Parameters.C_RANKING_POINTS);
        val = instance.getRankingTeam4();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of setRankingTeam4 method, of class Parameters.
     */
    @Test
    public void testSetRankingTeam4() {
        System.out.println("setRankingTeam4");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingTeam4();
        instance.setRankingTeam4(Parameters.C_RANKING_POINTS);
        val = instance.getRankingTeam4();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of getRankingTeam5 method, of class Parameters.
     */
    @Test
    public void testGetRankingTeam5() {
        System.out.println("getRankingTeam5");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingTeam5();
        instance.setRankingTeam5(Parameters.C_RANKING_POINTS);
        val = instance.getRankingTeam5();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of setRankingTeam5 method, of class Parameters.
     */
    @Test
    public void testSetRankingTeam5() {
        System.out.println("setRankingTeam5");
        Parameters instance = Tournament.getTournament().getParams();
        int val = instance.getRankingTeam5();
        instance.setRankingTeam5(Parameters.C_RANKING_POINTS);
        val = instance.getRankingTeam5();
        assertEquals(Parameters.C_RANKING_POINTS, val);
    }

    /**
     * Test of getPointsTeamVictoryBonus method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamVictoryBonus() {
        System.out.println("getPointsTeamVictoryBonus");
        Parameters instance = new Parameters();
        int expResult = 9;
        instance.setPointsTeamVictoryBonus(expResult);
        int result = instance.getPointsTeamVictoryBonus();
        assertEquals(result, expResult);
    }

    /**
     * Test of setPointsTeamVictoryBonus method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamVictoryBonus() {
        System.out.println("setPointsTeamVictoryBonus");
        Parameters instance = new Parameters();
        int expResult = 9;
        instance.setPointsTeamVictoryBonus(expResult);
        int result = instance.getPointsTeamVictoryBonus();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPointsTeamDrawBonus method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamDrawBonus() {
        System.out.println("getPointsTeamDrawBonus");
        Parameters instance = new Parameters();
        int expResult = 5;
        instance.setPointsTeamDrawBonus(expResult);
        int result = instance.getPointsTeamDrawBonus();
        assertEquals(result, expResult);
    }

    /**
     * Test of setPointsTeamDrawBonus method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamDrawBonus() {
        System.out.println("setPointsTeamDrawBonus");
        Parameters instance = new Parameters();
        int expResult = 5;
        instance.setPointsTeamDrawBonus(expResult);
        int result = instance.getPointsTeamDrawBonus();
        assertEquals(result, expResult);
    }

    /**
     * Test of isGroupsEnable method, of class Parameters.
     */
    @Test
    public void testIsGroupsEnable() {
        System.out.println("isGroupsEnable");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setGroupsEnable(expResult);
        boolean result = instance.isGroupsEnable();
        assertEquals(result, expResult);
    }

    /**
     * Test of setGroupsEnable method, of class Parameters.
     */
    @Test
    public void testSetGroupsEnable() {
        System.out.println("setGroupsEnable");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setGroupsEnable(expResult);
        boolean result = instance.isGroupsEnable();
        assertEquals(result, expResult);
    }

    /**
     * Test of isEnableClans method, of class Parameters.
     */
    @Test
    public void testIsEnableClans() {
        System.out.println("isEnableClans");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setEnableClans(expResult);
        boolean result = instance.isEnableClans();
        assertEquals(result, expResult);
    }

    /**
     * Test of setEnableClans method, of class Parameters.
     */
    @Test
    public void testSetEnableClans() {
        System.out.println("setEnableClans");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setEnableClans(expResult);
        boolean result = instance.isEnableClans();
        assertEquals(result, expResult);
    }

    /**
     * Test of isAvoidClansFirstMatch method, of class Parameters.
     */
    @Test
    public void testIsAvoidClansFirstMatch() {
        System.out.println("isAvoidClansFirstMatch");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setAvoidClansFirstMatch(expResult);
        boolean result = instance.isAvoidClansFirstMatch();
        assertEquals(result, expResult);

    }

    /**
     * Test of setAvoidClansFirstMatch method, of class Parameters.
     */
    @Test
    public void testSetAvoidClansFirstMatch() {
        System.out.println("setAvoidClansFirstMatch");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setAvoidClansFirstMatch(expResult);
        boolean result = instance.isAvoidClansFirstMatch();
        assertEquals(result, expResult);

    }

    /**
     * Test of isAvoidClansMatch method, of class Parameters.
     */
    @Test
    public void testIsAvoidClansMatch() {
        System.out.println("isAvoidClansMatch");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setAvoidClansMatch(expResult);
        boolean result = instance.isAvoidClansMatch();
        assertEquals(result, expResult);

    }

    /**
     * Test of setAvoidClansMatch method, of class Parameters.
     */
    @Test
    public void testSetAvoidClansMatch() {
        System.out.println("setAvoidClansMatch");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setAvoidClansMatch(expResult);
        boolean result = instance.isAvoidClansMatch();
        assertEquals(result, expResult);
    }

    /**
     * Test of getTeamMatesClansNumber method, of class Parameters.
     */
    @Test
    public void testGetTeamMatesClansNumber() {
        System.out.println("getTeamMatesClansNumber");
        Parameters instance = new Parameters();
        int expResult = 3;
        instance.setTeamMatesClansNumber(expResult);
        int result = instance.getTeamMatesClansNumber();
        assertEquals(result, expResult);
    }

    /**
     * Test of setTeamMatesClansNumber method, of class Parameters.
     */
    @Test
    public void testSetTeamMatesClansNumber() {
        System.out.println("setTeamMatesClansNumber");
        Parameters instance = new Parameters();
        int expResult = 3;
        instance.setTeamMatesClansNumber(expResult);
        int result = instance.getTeamMatesClansNumber();
        assertEquals(result, expResult);
    }

    /**
     * Test of isUseColor method, of class Parameters.
     */
    @Test
    public void testIsUseColor() {
        System.out.println("isUseColor");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setUseColor(expResult);
        boolean result = instance.isUseColor();
        assertEquals(result, expResult);
    }

    /**
     * Test of setUseColor method, of class Parameters.
     */
    @Test
    public void testSetUseColor() {
        System.out.println("setUseColor");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setUseColor(expResult);
        boolean result = instance.isUseColor();
        assertEquals(result, expResult);

    }

    /**
     * Test of isUseImage method, of class Parameters.
     */
    @Test
    public void testIsUseImage() {
        System.out.println("isUseImage");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setUseImage(expResult);
        boolean result = instance.isUseImage();
        assertEquals(result, expResult);
    }

    /**
     * Test of setUseImage method, of class Parameters.
     */
    @Test
    public void testSetUseImage() {
        System.out.println("setUseImage");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setUseImage(expResult);
        boolean result = instance.isUseImage();
        assertEquals(result, expResult);
    }

    /**
     * Test of isApplyToAnnexIndiv method, of class Parameters.
     */
    @Test
    public void testIsApplyToAnnexIndiv() {
        System.out.println("isApplyToAnnexIndiv");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setApplyToAnnexIndiv(expResult);
        boolean result = instance.isApplyToAnnexIndiv();
        assertEquals(result, expResult);

    }

    /**
     * Test of setApplyToAnnexIndiv method, of class Parameters.
     */
    @Test
    public void testSetApplyToAnnexIndiv() {
        System.out.println("setApplyToAnnexIndiv");
        Parameters instance = new Parameters();
        boolean expResult = true;
        instance.setApplyToAnnexIndiv(expResult);
        boolean result = instance.isApplyToAnnexIndiv();
        assertEquals(result, expResult);
    }

    /**
     * Test of isExceptBestAndWorstIndiv method, of class Parameters.
     */
    @Test
    public void testIsExceptBestAndWorstIndiv() {
        System.out.println("isExceptBestAndWorstIndiv");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setExceptBestAndWorstIndiv(expResult);
        boolean result = instance.isExceptBestAndWorstIndiv();
        assertEquals(result, expResult);
    }

    /**
     * Test of setExceptBestAndWorstIndiv method, of class Parameters.
     */
    @Test
    public void testSetExceptBestAndWorstIndiv() {
        System.out.println("setExceptBestAndWorstIndiv");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setExceptBestAndWorstIndiv(expResult);
        boolean result = instance.isExceptBestAndWorstIndiv();
        assertEquals(result, expResult);
    }

    /**
     * Test of isApplyToAnnexTeam method, of class Parameters.
     */
    @Test
    public void testIsApplyToAnnexTeam() {
        System.out.println("isApplyToAnnexTeam");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setApplyToAnnexTeam(expResult);
        boolean result = instance.isApplyToAnnexTeam();
        assertEquals(result, expResult);

    }

    /**
     * Test of setApplyToAnnexTeam method, of class Parameters.
     */
    @Test
    public void testSetApplyToAnnexTeam() {
        System.out.println("setApplyToAnnexTeam");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setApplyToAnnexTeam(expResult);
        boolean result = instance.isApplyToAnnexTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of isExceptBestAndWorstTeam method, of class Parameters.
     */
    @Test
    public void testIsExceptBestAndWorstTeam() {
        System.out.println("isExceptBestAndWorstTeam");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setExceptBestAndWorstTeam(expResult);
        boolean result = instance.isExceptBestAndWorstTeam();
        assertEquals(result, expResult);

    }

    /**
     * Test of setExceptBestAndWorstTeam method, of class Parameters.
     */
    @Test
    public void testSetExceptBestAndWorstTeam() {
        System.out.println("setExceptBestAndWorstTeam");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setExceptBestAndWorstTeam(expResult);
        boolean result = instance.isExceptBestAndWorstTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of isUseBestResultIndiv method, of class Parameters.
     */
    @Test
    public void testIsUseBestResultIndiv() {
        System.out.println("isUseBestResultIndiv");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setUseBestResultIndiv(expResult);
        boolean result = instance.isUseBestResultIndiv();
        assertEquals(result, expResult);
    }

    /**
     * Test of isUseBestResultTeam method, of class Parameters.
     */
    @Test
    public void testIsUseBestResultTeam() {
        System.out.println("isUseBestResultTeam");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setUseBestResultTeam(expResult);
        boolean result = instance.isUseBestResultTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of setUseBestResultIndiv method, of class Parameters.
     */
    @Test
    public void testSetUseBestResultIndiv() {
        System.out.println("setUseBestResultIndiv");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setUseBestResultIndiv(expResult);
        boolean result = instance.isUseBestResultIndiv();
        assertEquals(result, expResult);
    }

    /**
     * Test of setUseBestResultTeam method, of class Parameters.
     */
    @Test
    public void testSetUseBestResultTeam() {
        System.out.println("setUseBestResultTeam");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setUseBestResultTeam(expResult);
        boolean result = instance.isUseBestResultTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of getBestResultIndiv method, of class Parameters.
     */
    @Test
    public void testGetBestResultIndiv() {
        System.out.println("getBestResultIndiv");
        Parameters instance = new Parameters();
        int expResult = 3;

        instance.setBestResultIndiv(expResult);
        int result = instance.getBestResultIndiv();
        assertEquals(result, expResult);

    }

    /**
     * Test of getBestResultTeam method, of class Parameters.
     */
    @Test
    public void testGetBestResultTeam() {
        System.out.println("getBestResultTeam");
        Parameters instance = new Parameters();
        int expResult = 4;

        instance.setBestResultTeam(expResult);
        int result = instance.getBestResultTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of setBestResultIndiv method, of class Parameters.
     */
    @Test
    public void testSetBestResultIndiv() {
        System.out.println("setBestResultIndiv");
        Parameters instance = new Parameters();
        int expResult = 3;

        instance.setBestResultIndiv(expResult);
        int result = instance.getBestResultIndiv();
        assertEquals(result, expResult);
    }

    /**
     * Test of setBestResultTeam method, of class Parameters.
     */
    @Test
    public void testSetBestResultTeam() {
        System.out.println("setBestResultTeam");
        Parameters instance = new Parameters();
        int expResult = 4;

        instance.setBestResultTeam(expResult);
        int result = instance.getBestResultTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of getIndexOfCriteria method, of class Parameters.
     */
    @Test
    public void testGetIndexOfCriteria() {
        System.out.println("getIndexOfCriteria");
        Criteria c = new Criteria("T");
        Parameters instance = new Parameters();
        int expResult = instance.getCriteriaCount();
        instance.addCriteria(c);
        int result = instance.getIndexOfCriteria(c);
        assertEquals(result, expResult);

    }

    /**
     * Test of isTableBonus method, of class Parameters.
     */
    @Test
    public void testIsTableBonus() {
        System.out.println("isTableBonus");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setTableBonus(expResult);
        boolean result = instance.isTableBonus();
        assertEquals(result, expResult);

    }

    /**
     * Test of isTableBonusPerRound method, of class Parameters.
     */
    @Test
    public void testIsTableBonusPerRound() {
        System.out.println("isTableBonusPerRound");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setTableBonus(expResult);
        boolean result = instance.isTableBonus();
        assertEquals(result, expResult);
    }

    /**
     * Test of getTableBonusCoef method, of class Parameters.
     */
    @Test
    public void testGetTableBonusCoef() {
        System.out.println("getTableBonusCoef");
        Parameters instance = new Parameters();
        double expResult = 0.9;
        instance.setTableBonusCoef(expResult);
        double result = instance.getTableBonusCoef();
        assertEquals(result, expResult, 0.0001);
    }

    /**
     * Test of setTableBonus method, of class Parameters.
     */
    @Test
    public void testSetTableBonus() {
        System.out.println("setTableBonus");
        Parameters instance = new Parameters();
        double expResult = 0.9;
        instance.setTableBonusCoef(expResult);
        double result = instance.getTableBonusCoef();
        assertEquals(result, expResult, 0.0001);
    }

    /**
     * Test of setTableBonusPerRound method, of class Parameters.
     */
    @Test
    public void testSetTableBonusPerRound() {
        System.out.println("setTableBonusPerRound");
        boolean b = false;        
        Parameters instance = new Parameters();
        instance.setTableBonusPerRound(b);
        
        assertEquals(b, instance.isTableBonusPerRound());
    }

    /**
     * Test of setTableBonusCoef method, of class Parameters.
     */
    @Test
    public void testSetTableBonusCoef() {
        System.out.println("setTableBonusCoef");
        double val = 3.1;
        Parameters instance = new Parameters();
        instance.setTableBonusCoef(val);
        double result=instance.getTableBonusCoef();
        assertEquals(val, result,0.0001);
    }

    /**
     * Test of setUseLargeVictory method, of class Parameters.
     */
    @Test
    public void testSetUseLargeVictory() {
        System.out.println("setUseLargeVictory");
       Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setUseLargeVictory(expResult);
        boolean result = instance.isUseLargeVictory();
        assertEquals(result, expResult);
    }

    /**
     * Test of setUseLittleLoss method, of class Parameters.
     */
    @Test
    public void testSetUseLittleLoss() {
        System.out.println("setUseLittleLoss");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setUseLittleLoss(expResult);
        boolean result = instance.isUseLittleLoss();
        assertEquals(result, expResult);
    }

    /**
     * Test of isUseLargeVictory method, of class Parameters.
     */
    @Test
    public void testIsUseLargeVictory() {
        System.out.println("isUseLargeVictory");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setUseLargeVictory(expResult);
        boolean result = instance.isUseLargeVictory();
        assertEquals(result, expResult);
    }

    /**
     * Test of isUseLittleLoss method, of class Parameters.
     */
    @Test
    public void testIsUseLittleLoss() {
        System.out.println("isUseLittleLoss");
        Parameters instance = new Parameters();
        boolean expResult = false;
        instance.setUseLittleLoss(expResult);
        boolean result = instance.isUseLittleLoss();
        assertEquals(result, expResult);
    }

    /**
     * Test of getUID method, of class Parameters.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class Parameters.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Parameters instance = new Parameters();
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriteria method, of class Parameters.
     */
    @Test
    public void testGetCriteria_String() {
        System.out.println("getCriteria");
        String name = "";
        Parameters instance = new Parameters();
        Criteria expResult = null;
        Criteria result = instance.getCriteria(name);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDisplayRoster method, of class Parameters.
     */
    @Test
    public void testIsDisplayRoster() {
        System.out.println("isDisplayRoster");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isDisplayRoster();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDisplayRoster method, of class Parameters.
     */
    @Test
    public void testSetDisplayRoster() {
        System.out.println("setDisplayRoster");
        boolean d = false;
        Parameters instance = new Parameters();
        instance.setDisplayRoster(d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pull method, of class Parameters.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        Parameters params = null;
        Parameters instance = new Parameters();
        instance.pull(params);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriteria method, of class Parameters.
     */
    @Test
    public void testGetCriteria_int() {
        System.out.println("getCriteria");
        int i = 0;
        Parameters instance = new Parameters();
        Criteria expResult = null;
        Criteria result = instance.getCriteria(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamLargeVictory method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamLargeVictory() {
        System.out.println("getPointsTeamLargeVictory");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsTeamLargeVictory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamHugeVictory method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamHugeVictory() {
        System.out.println("getPointsTeamHugeVictory");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsTeamHugeVictory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamLargeVictory method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamLargeVictory() {
        System.out.println("setPointsTeamLargeVictory");
        int mPointsTeamLargeVictory = 0;
        Parameters instance = new Parameters();
        instance.setPointsTeamLargeVictory(mPointsTeamLargeVictory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamHugeVictory method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamHugeVictory() {
        System.out.println("setPointsTeamHugeVictory");
        int points = 0;
        Parameters instance = new Parameters();
        instance.setPointsTeamHugeVictory(points);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamLittleLost method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamLittleLost() {
        System.out.println("getPointsTeamLittleLost");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsTeamLittleLost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamLittleLost method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamLittleLost() {
        System.out.println("setPointsTeamLittleLost");
        int mPointsTeamLittleLost = 0;
        Parameters instance = new Parameters();
        instance.setPointsTeamLittleLost(mPointsTeamLittleLost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGapTeamLargeVictory method, of class Parameters.
     */
    @Test
    public void testGetGapTeamLargeVictory() {
        System.out.println("getGapTeamLargeVictory");
        Parameters instance = new Parameters();
        float expResult = 0.0F;
        float result = instance.getGapTeamLargeVictory();
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGapTeamHugeVictory method, of class Parameters.
     */
    @Test
    public void testGetGapTeamHugeVictory() {
        System.out.println("getGapTeamHugeVictory");
        Parameters instance = new Parameters();
        float expResult = 0.0F;
        float result = instance.getGapTeamHugeVictory();
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGapTeamLargeVictory method, of class Parameters.
     */
    @Test
    public void testSetGapTeamLargeVictory() {
        System.out.println("setGapTeamLargeVictory");
        float mGapLargeVictory = 0.0F;
        Parameters instance = new Parameters();
        instance.setGapTeamLargeVictory(mGapLargeVictory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGapTeamHugeVictory method, of class Parameters.
     */
    @Test
    public void testSetGapTeamHugeVictory() {
        System.out.println("setGapTeamHugeVictory");
        float mGapLargeVictory = 0.0F;
        Parameters instance = new Parameters();
        instance.setGapTeamHugeVictory(mGapLargeVictory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGapTeamLittleLost method, of class Parameters.
     */
    @Test
    public void testGetGapTeamLittleLost() {
        System.out.println("getGapTeamLittleLost");
        Parameters instance = new Parameters();
        float expResult = 0.0F;
        float result = instance.getGapTeamLittleLost();
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGapTeamHugeLost method, of class Parameters.
     */
    @Test
    public void testGetGapTeamHugeLost() {
        System.out.println("getGapTeamHugeLost");
        Parameters instance = new Parameters();
        float expResult = 0.0F;
        float result = instance.getGapTeamHugeLost();
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGapTeamLittleLost method, of class Parameters.
     */
    @Test
    public void testSetGapTeamLittleLost() {
        System.out.println("setGapTeamLittleLost");
        float mGapLittleLost = 0.0F;
        Parameters instance = new Parameters();
        instance.setGapTeamLittleLost(mGapLittleLost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGapTeamHugeLost method, of class Parameters.
     */
    @Test
    public void testSetGapTeamHugeLost() {
        System.out.println("setGapTeamHugeLost");
        float mGapLittleLost = 0.0F;
        Parameters instance = new Parameters();
        instance.setGapTeamHugeLost(mGapLittleLost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamHugeLost method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamHugeLost() {
        System.out.println("getPointsTeamHugeLost");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsTeamHugeLost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamHugeLost method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamHugeLost() {
        System.out.println("setPointsTeamHugeLost");
        int mPointsTeamLost = 0;
        Parameters instance = new Parameters();
        instance.setPointsTeamHugeLost(mPointsTeamLost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gemRankingTeam2 method, of class Parameters.
     */
    @Test
    public void testGemRankingTeam2() {
        System.out.println("gemRankingTeam2");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.gemRankingTeam2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUseTeamLargeVictory method, of class Parameters.
     */
    @Test
    public void testSetUseTeamLargeVictory() {
        System.out.println("setUseTeamLargeVictory");
        boolean use = false;
        Parameters instance = new Parameters();
        instance.setUseTeamLargeVictory(use);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUseTeamHugeVictory method, of class Parameters.
     */
    @Test
    public void testSetUseTeamHugeVictory() {
        System.out.println("setUseTeamHugeVictory");
        boolean use = false;
        Parameters instance = new Parameters();
        instance.setUseTeamHugeVictory(use);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUseTeamLittleLoss method, of class Parameters.
     */
    @Test
    public void testSetUseTeamLittleLoss() {
        System.out.println("setUseTeamLittleLoss");
        boolean use = false;
        Parameters instance = new Parameters();
        instance.setUseTeamLittleLoss(use);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUseTeamHugeLoss method, of class Parameters.
     */
    @Test
    public void testSetUseTeamHugeLoss() {
        System.out.println("setUseTeamHugeLoss");
        boolean use = false;
        Parameters instance = new Parameters();
        instance.setUseTeamHugeLoss(use);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUseTeamLargeVictory method, of class Parameters.
     */
    @Test
    public void testIsUseTeamLargeVictory() {
        System.out.println("isUseTeamLargeVictory");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isUseTeamLargeVictory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUseTeamHugeVictory method, of class Parameters.
     */
    @Test
    public void testIsUseTeamHugeVictory() {
        System.out.println("isUseTeamHugeVictory");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isUseTeamHugeVictory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUseTeamLittleLoss method, of class Parameters.
     */
    @Test
    public void testIsUseTeamLittleLoss() {
        System.out.println("isUseTeamLittleLoss");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isUseTeamLittleLoss();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUseTeamHugeLoss method, of class Parameters.
     */
    @Test
    public void testIsUseTeamHugeLoss() {
        System.out.println("isUseTeamHugeLoss");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isUseTeamHugeLoss();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWebServerPort method, of class Parameters.
     */
    @Test
    public void testGetWebServerPort() {
        System.out.println("getWebServerPort");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getWebServerPort();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWebServerPort method, of class Parameters.
     */
    @Test
    public void testSetWebServerPort() {
        System.out.println("setWebServerPort");
        int port = 0;
        Parameters instance = new Parameters();
        instance.setWebServerPort(port);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Parameters.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWebEdit method, of class Parameters.
     */
    @Test
    public void testSetWebEdit() {
        System.out.println("setWebEdit");
        boolean WebEdit = false;
        Parameters instance = new Parameters();
        instance.setWebEdit(WebEdit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isWebEdit method, of class Parameters.
     */
    @Test
    public void testIsWebEdit() {
        System.out.println("isWebEdit");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isWebEdit();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringColor1 method, of class Parameters.
     */
    @Test
    public void testGetStringColor1() {
        System.out.println("getStringColor1");
        Parameters instance = new Parameters();
        String expResult = "";
        String result = instance.getStringColor1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringColor2 method, of class Parameters.
     */
    @Test
    public void testGetStringColor2() {
        System.out.println("getStringColor2");
        Parameters instance = new Parameters();
        String expResult = "";
        String result = instance.getStringColor2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringBorderColor method, of class Parameters.
     */
    @Test
    public void testGetStringBorderColor() {
        System.out.println("getStringBorderColor");
        Parameters instance = new Parameters();
        String expResult = "";
        String result = instance.getStringBorderColor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringForeColor method, of class Parameters.
     */
    @Test
    public void testGetStringForeColor() {
        System.out.println("getStringForeColor");
        Parameters instance = new Parameters();
        String expResult = "";
        String result = instance.getStringForeColor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColor1 method, of class Parameters.
     */
    @Test
    public void testGetColor1() {
        System.out.println("getColor1");
        Parameters instance = new Parameters();
        Color expResult = null;
        Color result = instance.getColor1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColor2 method, of class Parameters.
     */
    @Test
    public void testGetColor2() {
        System.out.println("getColor2");
        Parameters instance = new Parameters();
        Color expResult = null;
        Color result = instance.getColor2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBorderColor method, of class Parameters.
     */
    @Test
    public void testGetBorderColor() {
        System.out.println("getBorderColor");
        Parameters instance = new Parameters();
        Color expResult = null;
        Color result = instance.getBorderColor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getForeColor method, of class Parameters.
     */
    @Test
    public void testGetForeColor() {
        System.out.println("getForeColor");
        Parameters instance = new Parameters();
        Color expResult = null;
        Color result = instance.getForeColor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColor1 method, of class Parameters.
     */
    @Test
    public void testSetColor1() {
        System.out.println("setColor1");
        Color c = null;
        Parameters instance = new Parameters();
        instance.setColor1(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColor2 method, of class Parameters.
     */
    @Test
    public void testSetColor2() {
        System.out.println("setColor2");
        Color c = null;
        Parameters instance = new Parameters();
        instance.setColor2(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBorderColor method, of class Parameters.
     */
    @Test
    public void testSetBorderColor() {
        System.out.println("setBorderColor");
        Color c = null;
        Parameters instance = new Parameters();
        instance.setBorderColor(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setForeColor method, of class Parameters.
     */
    @Test
    public void testSetForeColor() {
        System.out.println("setForeColor");
        Color c = null;
        Parameters instance = new Parameters();
        instance.setForeColor(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
