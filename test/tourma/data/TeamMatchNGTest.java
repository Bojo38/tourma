/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.File;
import java.util.ArrayList;
import org.jdom.Element;
import org.testng.Assert;
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
public class TeamMatchNGTest {

    public TeamMatchNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/teamMatch.xml"));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getWinner method, of class TeamMatch.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(0);
        }
        instance.resetWL();
        Competitor comp = instance.getWinner();
        assertEquals(comp, instance.getCompetitor1());
        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            tourma.data.Value val = cm.getValue(td);
            val.setValue1(0);
            val.setValue2(1);
        }
        instance.resetWL();
        comp = instance.getWinner();
        assertEquals(comp, instance.getCompetitor2());
    }

    /**
     * Test of getLooser method, of class TeamMatch.
     */
    @Test
    public void testGetLooser() {
        System.out.println("getLooser");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(0);
        }
        instance.resetWL();
        Competitor comp = instance.getLooser();
        assertEquals(comp, instance.getCompetitor2());
        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            tourma.data.Value val = cm.getValue(td);
            val.setValue1(0);
            val.setValue2(1);
        }
        instance.resetWL();
        comp = instance.getLooser();
        assertEquals(comp, instance.getCompetitor1());
    }

    /**
     * Test of getXMLElement method, of class TeamMatch.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        Round r = instance.getRound();
        Element result = instance.getXMLElement();

        TeamMatch cm = new TeamMatch(r);
        cm.setXMLElement(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of setXMLElement method, of class TeamMatch.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        System.out.println("getXMLElement");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        Round r = instance.getRound();
        Element result = instance.getXMLElement();

        TeamMatch cm = new TeamMatch(r);
        cm.setXMLElement(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of getVictories method, of class TeamMatch.
     */
    @Test
    public void testGetVictories() {
        System.out.println("getVictories");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(0);
        }        
        assertEquals(instance.getVictories((Team)instance.getCompetitor1()),8);
        
        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            tourma.data.Value val = cm.getValue(td);
            val.setValue1(0);
            val.setValue2(1);
        }        
        assertEquals(instance.getVictories((Team)instance.getCompetitor1()),0);
    }

    /**
     * Test of getLoss method, of class TeamMatch.
     */
    @Test
    public void testGetLoss() {
        System.out.println("getLoss");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(0);
        }
        assertEquals(instance.getLoss((Team)instance.getCompetitor1()),0);
        
        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            tourma.data.Value val = cm.getValue(td);
            val.setValue1(0);
            val.setValue2(1);
        }
        instance.resetWL();
        assertEquals(instance.getLoss((Team)instance.getCompetitor1()),8);
    }

    /**
     * Test of getDraw method, of class TeamMatch.
     */
    @Test
    public void testGetDraw() {
        System.out.println("getDraw");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(0);
        }
        assertEquals(instance.getDraw((Team)instance.getCompetitor1()),0);
        
        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(1);
        }
        assertEquals(instance.getDraw((Team)instance.getCompetitor1()),8);
    }

    /**
     * Test of getMatchCount method, of class TeamMatch.
     */
    @Test
    public void testGetMatchCount() {
        System.out.println("getMatchCount");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        assertEquals(instance.getMatchCount(), Tournament.getTournament().getParams().getTeamMatesNumber());
    }

    /**
     * Test of getMatch method, of class TeamMatch.
     */
    @Test
    public void testGetMatch() {
        System.out.println("getMatch");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        assertEquals(instance.getMatchCount(), Tournament.getTournament().getParams().getTeamMatesNumber());

        for (int i=0; i<instance.getMatchCount(); i++)
        {
            Assert.assertNotNull(instance.getMatch(i));
        }
    }

    /**
     * Test of clearMatchs method, of class TeamMatch.
     */
    @Test
    public void testClearMatchs() {
        System.out.println("clearMatchs");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        assertEquals(instance.getMatchCount(), Tournament.getTournament().getParams().getTeamMatesNumber());
        instance.clearMatchs();
        assertEquals(instance.getMatchCount(), 0);
    }

    /**
     * Test of containsMatch method, of class TeamMatch.
     */
    @Test
    public void testContainsMatch() {
        System.out.println("containsMatch");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        assertEquals(instance.getMatchCount(), Tournament.getTournament().getParams().getTeamMatesNumber());

        for (int i=0; i<instance.getMatchCount(); i++)
        {
            Assert.assertTrue(instance.containsMatch(instance.getMatch(i)));
        }
        
        TeamMatch instance2 = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(1);
         for (int i=0; i<instance2.getMatchCount(); i++)
        {
            Assert.assertFalse(instance.containsMatch(instance2.getMatch(i)));
        }
    }

    /**
     * Test of addMatch method, of class TeamMatch.
     */
    @Test
    public void testAddMatch() {
        System.out.println("addMatch");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        assertEquals(instance.getMatchCount(), Tournament.getTournament().getParams().getTeamMatesNumber());
        ArrayList<CoachMatch> acm=new ArrayList<>();
        for (int i=0; i<instance.getMatchCount(); i++)
        {
            acm.add(instance.getMatch(i));
        }
        instance.clearMatchs();
        assertEquals(instance.getMatchCount(), 0);
        for (CoachMatch cm:acm)
        {
            instance.addMatch(cm);
        }
        assertEquals(instance.getMatchCount(), Tournament.getTournament().getParams().getTeamMatesNumber());
    }

    /**
     * Test of getXMLElementForDisplay method, of class TeamMatch.
     */
    @Test
    public void testGetXMLElementForDisplay() {
        System.out.println("getXMLElementForDisplay");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        Round r = instance.getRound();
        Element result = instance.getXMLElementForDisplay();

        TeamMatch cm = new TeamMatch(r);
        cm.setXMLElementForDisplay(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of setXMLElementForDisplay method, of class TeamMatch.
     */
    @Test
    public void testSetXMLElementForDisplay() {
        System.out.println("setXMLElementForDisplay");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getRound(0).getMatchsCount() == 0) {
            fail("No match in round");
        }
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        Round r = instance.getRound();
        Element result = instance.getXMLElementForDisplay();

        TeamMatch cm = new TeamMatch(r);
        cm.setXMLElementForDisplay(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of getUID method, of class TeamMatch.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class TeamMatch.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        TeamMatch instance = null;
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUpdated method, of class TeamMatch.
     */
    @Test
    public void testSetUpdated() {
        System.out.println("setUpdated");
        boolean updated = false;
        TeamMatch instance = null;
        instance.setUpdated(updated);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUpdated method, of class TeamMatch.
     */
    @Test
    public void testIsUpdated() {
        System.out.println("isUpdated");
        TeamMatch instance = null;
        boolean expResult = false;
        boolean result = instance.isUpdated();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pull method, of class TeamMatch.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        Match match = null;
        TeamMatch instance = null;
        instance.pull(match);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of push method, of class TeamMatch.
     */
    @Test
    public void testPush() {
        System.out.println("push");
        Match match = null;
        TeamMatch instance = null;
        instance.push(match);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class TeamMatch.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object c = null;
        TeamMatch instance = null;
        boolean expResult = false;
        boolean result = instance.equals(c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriteriaBonusPoints method, of class TeamMatch.
     */
    @Test
    public void testGetCriteriaBonusPoints() {
        System.out.println("getCriteriaBonusPoints");
        Coach c = null;
        CoachMatch m = null;
        Criteria crit = null;
        int expResult = 0;
        int result = TeamMatch.getCriteriaBonusPoints(c, m, crit);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriteriasBonusPoints method, of class TeamMatch.
     */
    @Test
    public void testGetCriteriasBonusPoints() {
        System.out.println("getCriteriasBonusPoints");
        Team t = null;
        TeamMatch tm = null;
        int expResult = 0;
        int result = TeamMatch.getCriteriasBonusPoints(t, tm);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsByTeam method, of class TeamMatch.
     */
    @Test
    public void testGetPointsByTeam() {
        System.out.println("getPointsByTeam");
        Team t = null;
        TeamMatch tm = null;
        boolean withMainPoints = false;
        boolean withBonus = false;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getPointsByTeam(t, tm, withMainPoints, withBonus);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getELOByTeam method, of class TeamMatch.
     */
    @Test
    public void testGetELOByTeam() {
        System.out.println("getELOByTeam");
        Team t = null;
        TeamMatch tm = null;
        int roundIndex = 0;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getELOByTeam(t, tm, roundIndex);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVNDByTeam method, of class TeamMatch.
     */
    @Test
    public void testGetVNDByTeam() {
        System.out.println("getVNDByTeam");
        Team t = null;
        TeamMatch tm = null;
        boolean includeCurrent = false;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getVNDByTeam(t, tm, includeCurrent);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOppPointsByTeam method, of class TeamMatch.
     */
    @Test
    public void testGetOppPointsByTeam() {
        System.out.println("getOppPointsByTeam");
        Team t = null;
        TeamMatch tm = null;
        boolean includeCurrent = false;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getOppPointsByTeam(t, tm, includeCurrent);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOppELOByTeam method, of class TeamMatch.
     */
    @Test
    public void testGetOppELOByTeam() {
        System.out.println("getOppELOByTeam");
        Team t = null;
        TeamMatch tm = null;
        int roundIndex = 0;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getOppELOByTeam(t, tm, roundIndex);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamTable method, of class TeamMatch.
     */
    @Test
    public void testGetTeamTable() {
        System.out.println("getTeamTable");
        Team t = null;
        TeamMatch tm = null;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getTeamTable(t, tm);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamNbMatch method, of class TeamMatch.
     */
    @Test
    public void testGetTeamNbMatch() {
        System.out.println("getTeamNbMatch");
        Team t = null;
        TeamMatch tm = null;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getTeamNbMatch(t, tm);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class TeamMatch.
     */
    @Test
    public void testGetValue_3args_1() {
        System.out.println("getValue");
        Team t = null;
        int rankingType = 0;
        boolean teamVictory = false;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getValue(t, rankingType, teamVictory);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class TeamMatch.
     */
    @Test
    public void testGetValue_4args() {
        System.out.println("getValue");
        Team t = null;
        TeamMatch tm = null;
        Criteria crit = null;
        int subtype = 0;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getValue(t, tm, crit, subtype);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class TeamMatch.
     */
    @Test
    public void testGetValue_3args_2() {
        System.out.println("getValue");
        Criteria crit = null;
        int subtype = 0;
        Competitor c = null;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getValue(crit, subtype, c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recomputeValues method, of class TeamMatch.
     */
    @Test
    public void testRecomputeValues() {
        System.out.println("recomputeValues");
        TeamMatch instance = null;
        instance.recomputeValues();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recomputeValue method, of class TeamMatch.
     */
    @Test
    public void testRecomputeValue() {
        System.out.println("recomputeValue");
        int index = 0;
        Competitor c = null;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.recomputeValue(index, c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEntered method, of class TeamMatch.
     */
    @Test
    public void testIsEntered() {
        System.out.println("isEntered");
        TeamMatch instance = null;
        boolean expResult = false;
        boolean result = instance.isEntered();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeammatesPoints method, of class TeamMatch.
     */
    @Test
    public void testGetTeammatesPoints() {
        System.out.println("getTeammatesPoints");
        Team t = null;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getTeammatesPoints(t);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeammatesVND method, of class TeamMatch.
     */
    @Test
    public void testGetTeammatesVND() {
        System.out.println("getTeammatesVND");
        Team t = null;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getTeammatesVND(t);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
