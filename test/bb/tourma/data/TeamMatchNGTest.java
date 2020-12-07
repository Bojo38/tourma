/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

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
        Criterion td = Tournament.getTournament().getParams().getCriterion(0);
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            bb.tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(0);
        }
        instance.resetWL();
        Competitor comp = instance.getWinner();
        assertEquals(comp, instance.getCompetitor1());
        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            bb.tourma.data.Value val = cm.getValue(td);
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
        Criterion td = Tournament.getTournament().getParams().getCriterion(0);
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            bb.tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(0);
        }
        instance.resetWL();
        Competitor comp = instance.getLooser();
        assertEquals(comp, instance.getCompetitor2());
        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            bb.tourma.data.Value val = cm.getValue(td);
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
        /*For performance purpose Match equality compares UID, so the equality test will be done here*/
        if ((instance.getCompetitor1() != null) && (instance.getCompetitor2() != null)) {
            assertEquals(instance.getCompetitor1(), cm.getCompetitor1());
            assertEquals(instance.getCompetitor2(), cm.getCompetitor2());
        }

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm1 = instance.getMatch(i);
            if (i < cm.getMatchCount()) {
                CoachMatch cm2 = cm.getMatch(i);

                assertEquals(cm1.getCompetitor1(), cm2.getCompetitor1());
                assertEquals(cm1.getCompetitor2(), cm2.getCompetitor2());
                assertEquals(cm1.getComputedValueCount(), cm2.getComputedValueCount());
                assertEquals(cm1.getLooser(), cm2.getLooser());
                assertEquals(cm1.getRoster1(), cm2.getRoster2());
                assertEquals(cm1.getRound(), cm2.getRound());
                assertEquals(cm1.getSubstitute1(), cm2.getSubstitute1());
                assertEquals(cm1.getSubstitute2(), cm2.getSubstitute2());
                assertEquals(cm1.getValueCount(), cm2.getValueCount());
                assertEquals(cm1.getWinner(), cm2.getWinner());
                for (int j = 0; j < cm1.getValueCount(); j++) {
                    int cm1_c1 = cm1.getValue((Coach) cm1.getCompetitor1(), j);
                    int cm2_c1 = cm1.getValue((Coach) cm2.getCompetitor1(), j);
                    assertEquals(cm1_c1, cm2_c1);

                    int cm1_c2 = cm1.getValue((Coach) cm1.getCompetitor2(), j);
                    int cm2_c2 = cm1.getValue((Coach) cm2.getCompetitor2(), j);

                    assertEquals(cm1_c2, cm2_c2);
                }
            } else {
                fail("Different number of coachmatch");
                break;
            }
        }
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
        if ((instance.getCompetitor1() != null) && (instance.getCompetitor2() != null)) {
            assertEquals(instance.getCompetitor1(), cm.getCompetitor1());
            assertEquals(instance.getCompetitor2(), cm.getCompetitor2());
        }

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm1 = instance.getMatch(i);
            if (i < cm.getMatchCount()) {
                CoachMatch cm2 = cm.getMatch(i);

                assertEquals(cm1.getCompetitor1(), cm2.getCompetitor1());
                assertEquals(cm1.getCompetitor2(), cm2.getCompetitor2());
                assertEquals(cm1.getComputedValueCount(), cm2.getComputedValueCount());
                assertEquals(cm1.getLooser(), cm2.getLooser());
                assertEquals(cm1.getRoster1(), cm2.getRoster2());
                assertEquals(cm1.getRound(), cm2.getRound());
                assertEquals(cm1.getSubstitute1(), cm2.getSubstitute1());
                assertEquals(cm1.getSubstitute2(), cm2.getSubstitute2());
                assertEquals(cm1.getValueCount(), cm2.getValueCount());
                assertEquals(cm1.getWinner(), cm2.getWinner());
                for (int j = 0; j < cm1.getValueCount(); j++) {
                    int cm1_c1 = cm1.getValue((Coach) cm1.getCompetitor1(), j);
                    int cm2_c1 = cm1.getValue((Coach) cm2.getCompetitor1(), j);
                    assertEquals(cm1_c1, cm2_c1);

                    int cm1_c2 = cm1.getValue((Coach) cm1.getCompetitor2(), j);
                    int cm2_c2 = cm1.getValue((Coach) cm2.getCompetitor2(), j);

                    assertEquals(cm1_c2, cm2_c2);
                }
            } else {
                fail("Different number of coachmatch");
                break;
            }
        }
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
        Criterion td = Tournament.getTournament().getParams().getCriterion(0);
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            bb.tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(0);
        }
        assertEquals(instance.getVictories((Team) instance.getCompetitor1()), 8);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            bb.tourma.data.Value val = cm.getValue(td);
            val.setValue1(0);
            val.setValue2(1);
        }
        assertEquals(instance.getVictories((Team) instance.getCompetitor1()), 0);
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
        Criterion td = Tournament.getTournament().getParams().getCriterion(0);
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            bb.tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(0);
        }
        assertEquals(instance.getLoss((Team) instance.getCompetitor1()), 0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            bb.tourma.data.Value val = cm.getValue(td);
            val.setValue1(0);
            val.setValue2(1);
        }
        instance.resetWL();
        assertEquals(instance.getLoss((Team) instance.getCompetitor1()), 8);
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
        Criterion td = Tournament.getTournament().getParams().getCriterion(0);
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            bb.tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(0);
        }
        assertEquals(instance.getDraw((Team) instance.getCompetitor1()), 0);

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm = instance.getMatch(i);
            bb.tourma.data.Value val = cm.getValue(td);
            val.setValue1(1);
            val.setValue2(1);
        }
        assertEquals(instance.getDraw((Team) instance.getCompetitor1()), 8);
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
        Criterion td = Tournament.getTournament().getParams().getCriterion(0);
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

        for (int i = 0; i < instance.getMatchCount(); i++) {
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

        for (int i = 0; i < instance.getMatchCount(); i++) {
            Assert.assertTrue(instance.containsMatch(instance.getMatch(i)));
        }

        TeamMatch instance2 = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(1);
        for (int i = 0; i < instance2.getMatchCount(); i++) {
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
        ArrayList<CoachMatch> acm = new ArrayList<>();
        for (int i = 0; i < instance.getMatchCount(); i++) {
            acm.add(instance.getMatch(i));
        }
        instance.clearMatchs();
        assertEquals(instance.getMatchCount(), 0);
        for (CoachMatch cm : acm) {
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
        if ((instance.getCompetitor1() != null) && (instance.getCompetitor2() != null)) {
            assertEquals(instance.getCompetitor1(), cm.getCompetitor1());
            assertEquals(instance.getCompetitor2(), cm.getCompetitor2());
        }

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm1 = instance.getMatch(i);
            if (i < cm.getMatchCount()) {
                CoachMatch cm2 = cm.getMatch(i);

                assertEquals(cm1.getCompetitor1(), cm2.getCompetitor1());
                assertEquals(cm1.getCompetitor2(), cm2.getCompetitor2());
                
            } else {
                fail("Different number of coachmatch");
                break;
            }
        }
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
        if ((instance.getCompetitor1() != null) && (instance.getCompetitor2() != null)) {
            assertEquals(instance.getCompetitor1(), cm.getCompetitor1());
            assertEquals(instance.getCompetitor2(), cm.getCompetitor2());
        }

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm1 = instance.getMatch(i);
            if (i < cm.getMatchCount()) {
                CoachMatch cm2 = cm.getMatch(i);

                assertEquals(cm1.getCompetitor1(), cm2.getCompetitor1());
                assertEquals(cm1.getCompetitor2(), cm2.getCompetitor2());
                
            } else {
                fail("Different number of coachmatch");
                break;
            }
        }
    }

    /**
     * Test of getUID method, of class TeamMatch.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        int expResult = 0;
        int result = instance.getUID();
    }

    /**
     * Test of setUID method, of class TeamMatch.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        instance.setUID(UID);
    }

    /**
     * Test of setUpdated method, of class TeamMatch.
     */
    @Test
    public void testSetUpdated() {
        System.out.println("setUpdated");
        boolean updated = false;
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        instance.setUpdated(updated);
        boolean result = instance.isUpdated();
        assertEquals(result, false);

        instance.setUpdated(true);
        result = instance.isUpdated();
        assertEquals(result, true);

    }

    /**
     * Test of isUpdated method, of class TeamMatch.
     */
    @Test
    public void testIsUpdated() {
        System.out.println("isUpdated");
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        boolean expResult = true;
        boolean result = instance.isUpdated();
        assertEquals(result, expResult);
    }

    /**
     * Test of pull method, of class TeamMatch.
     */
    @Test(enabled = false)
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
    @Test(enabled = false)
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

        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        Element element = instance.getXMLElement();
        TeamMatch expResult = new TeamMatch(Tournament.getTournament().getRound(0));
        expResult.setXMLElement(element);

        /*For performance purpose Match equality compares UID, so the equality test will be done here*/
        if ((instance.getCompetitor1() != null) && (instance.getCompetitor2() != null)) {
            assertEquals(instance.getCompetitor1(), expResult.getCompetitor1());
            assertEquals(instance.getCompetitor2(), expResult.getCompetitor2());
        }

        for (int i = 0; i < instance.getMatchCount(); i++) {
            CoachMatch cm1 = instance.getMatch(i);
            if (i < expResult.getMatchCount()) {
                CoachMatch cm2 = expResult.getMatch(i);

                assertEquals(cm1.getCompetitor1(), cm2.getCompetitor1());
                assertEquals(cm1.getCompetitor2(), cm2.getCompetitor2());
                assertEquals(cm1.getComputedValueCount(), cm2.getComputedValueCount());
                assertEquals(cm1.getLooser(), cm2.getLooser());
                assertEquals(cm1.getRoster1(), cm2.getRoster2());
                assertEquals(cm1.getRound(), cm2.getRound());
                assertEquals(cm1.getSubstitute1(), cm2.getSubstitute1());
                assertEquals(cm1.getSubstitute2(), cm2.getSubstitute2());
                assertEquals(cm1.getValueCount(), cm2.getValueCount());
                assertEquals(cm1.getWinner(), cm2.getWinner());
                for (int j = 0; j < cm1.getValueCount(); j++) {
                    int cm1_c1 = cm1.getValue((Coach) cm1.getCompetitor1(), j);
                    int cm2_c1 = cm1.getValue((Coach) cm2.getCompetitor1(), j);
                    assertEquals(cm1_c1, cm2_c1);

                    int cm1_c2 = cm1.getValue((Coach) cm1.getCompetitor2(), j);
                    int cm2_c2 = cm1.getValue((Coach) cm2.getCompetitor2(), j);

                    assertEquals(cm1_c2, cm2_c2);
                }
            } else {
                fail("Different number of coachmatch");
                break;
            }
        }

    }

    /**
     * Test of getCriteriaBonusPoints method, of class TeamMatch.
     */
    @Test
    public void testGetCriteriaBonusPoints() {
        System.out.println("getCriteriaBonusPoints");

        CoachMatch m = Tournament.getTournament().getRound(0).getCoachMatchs().get(0);
        Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        int expResult = 0;
        int result = TeamMatch.getCriteriaBonusPoints((Coach) m.getCompetitor1(), m, crit);
        assertEquals(result, expResult);
    }

    /**
     * Test of getCriteriasBonusPoints method, of class TeamMatch.
     */
    @Test
    public void testGetCriteriasBonusPoints() {
        System.out.println("getCriteriasBonusPoints");
        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        int expResult = 0;
        int result = TeamMatch.getCriteriasBonusPoints((Team) m.getCompetitor1(), m);
        assertEquals(result, expResult);

    }

    /**
     * Test of getPointsByTeam method, of class TeamMatch.
     */
    @Test
    public void testGetPointsByTeam() {
        System.out.println("getPointsByTeam");

        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        int expResult = 0;
        int result = m.getPointsByTeam((Team) m.getCompetitor1(), m, true, true);
        assertEquals(result, expResult);

    }

    /**
     * Test of getELOByTeam method, of class TeamMatch.
     */
    @Test
    public void testGetELOByTeam() {
        System.out.println("getELOByTeam");

        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        int expResult = 132456;
        int result = m.getELOByTeam((Team) m.getCompetitor1(), m, 0);
        assertEquals(result, expResult);

    }

    /**
     * Test of getVNDByTeam method, of class TeamMatch.
     */
    @Test
    public void testGetVNDByTeam() {
        System.out.println("getVNDByTeam");

        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        int expResult = 1;
        int result = m.getVNDByTeam((Team) m.getCompetitor1(), m, true);
        assertEquals(result, expResult);
    }

    /**
     * Test of getOppPointsByTeam method, of class TeamMatch.
     */
    @Test
    public void testGetOppPointsByTeam() {
        System.out.println("getOppPointsByTeam");

        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        int expResult = 1400;
        int result = m.getOppPointsByTeam((Team) m.getCompetitor1(), m, true);
        assertEquals(result, expResult);

    }

    /**
     * Test of getOppELOByTeam method, of class TeamMatch.
     */
    @Test
    public void testGetOppELOByTeam() {
        System.out.println("getOppELOByTeam");

        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        int expResult = 125288;
        int result = m.getOppELOByTeam((Team) m.getCompetitor1(), m, 0);
        assertEquals(result, expResult);

    }

    /**
     * Test of getTeamTable method, of class TeamMatch.
     */
    @Test
    public void testGetTeamTable() {
        System.out.println("getTeamTable");

        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        int expResult = 0;
        int result = m.getTeamTable((Team) m.getCompetitor1(), m);
        assertEquals(result, expResult);

    }

    /**
     * Test of getTeamNbMatch method, of class TeamMatch.
     */
    @Test
    public void testGetTeamNbMatch() {
        System.out.println("getTeamNbMatch");

        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        int expResult = 1;
        int result = m.getTeamNbMatch((Team) m.getCompetitor1(), m);
        assertEquals(result, expResult);

    }

    /**
     * Test of getValue method, of class TeamMatch.
     */
    @Test
    public void testGetValue_3args_1() {
        System.out.println("getValue");
        
        int rankingType = 0;
        boolean teamVictory = true;
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        Team t = (Team) instance.getCompetitor1();
        int expResult = 0;
        int result = instance.getValue(t, rankingType, teamVictory);
        assertEquals(result, expResult);
    }

    /**
     * Test of getValue method, of class TeamMatch.
     */
    @Test
    public void testGetValue_4args() {
        System.out.println("getValue");
        Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        int subtype = 0;
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        Team t = (Team) instance.getCompetitor1();
        int expResult = 7;
        int result = instance.getValue(t, instance, crit, subtype);
        assertEquals(result, expResult);
    }

    /**
     * Test of getValue method, of class TeamMatch.
     */
    @Test
    public void testGetValue_3args_2() {
        System.out.println("getValue");
        Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        int subtype = 0;
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        Team t = (Team) instance.getCompetitor1();
        int expResult = 7;
        int result = instance.getValue(crit, subtype, t);
        assertEquals(result, expResult);
    }

    /**
     * Test of recomputeValues method, of class TeamMatch.
     */
    @Test
    public void testRecomputeValues() {
        System.out.println("recomputeValues");
        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        m.setUpdated(false);
        m.recomputeValues();

        assertEquals(m.isUpdated(), true);
    }

    /**
     * Test of recomputeValue method, of class TeamMatch.
     */
    @Test
    public void testRecomputeValue() {
        System.out.println("recomputeValue");
        int index = 0;

        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        Competitor c = m.getCompetitor1();
        int expResult = 0;
        int result = m.recomputeValue(index, c);
        assertEquals(result, expResult);
    }

    /**
     * Test of isEntered method, of class TeamMatch.
     */
    @Test
    public void testIsEntered() {
        System.out.println("isEntered");
        TeamMatch instance = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        boolean expResult = true;
        boolean result = instance.isEntered();
        assertEquals(result, expResult);

    }

    /**
     * Test of getTeammatesPoints method, of class TeamMatch.
     */
    @Test
    public void testGetTeammatesPoints() {
        System.out.println("getTeammatesPoints");

        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        int expResult = 4;
        int result = m.getTeammatesPoints((Team) m.getCompetitor1());
        assertEquals(result, expResult);

    }

    /**
     * Test of getTeammatesVND method, of class TeamMatch.
     */
    @Test
    public void testGetTeammatesVND() {
        System.out.println("getTeammatesVND");

        TeamMatch m = (TeamMatch) Tournament.getTournament().getRound(0).getMatch(0);
        int expResult = 1002005;
        int result = m.getTeammatesVND((Team) m.getCompetitor1());
        assertEquals(result, expResult);

    }

    /**
     * Test of getValue method, of class TeamMatch.
     */
    @Test
    public void testGetValue_Formula_Competitor() {
        System.out.println("getValue");
        Formula form = null;
        Competitor c = null;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getValue(form, c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
