/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.File;
import java.util.ArrayList;
import org.jdom2.Element;
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

}
