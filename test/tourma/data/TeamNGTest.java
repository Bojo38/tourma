/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import org.jdom.Element;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class TeamNGTest {

    public TeamNGTest() {
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
     * Test of getNullTeam method, of class Team.
     */
    @Test
    public void testGetNullTeam() {
        System.out.println("getNullTeam");
        Team expResult = new Team(StringConstants.CS_NONE);
        Team result = Team.getNullTeam();
        assertEquals(result.getName(), expResult.getName());
    }

    /**
     * Test of setNullTeam method, of class Team.
     */
    @Test
    public void testSetNullTeam() {
        System.out.println("setNullTeam");
        Team asNullTeam = new Team("Test");
        Team.setNullTeam(asNullTeam);
        Team result = Team.getNullTeam();
        assertEquals(result.getName(), asNullTeam.getName());
    }

    /**
     * Test of newTeamMap method, of class Team.
     */
    @Test
    public void testNewTeamMap() {
        System.out.println("newTeamMap");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        ArrayList<Team> list = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team c = Tournament.getTournament().getTeam(i);
            list.add(c);
        }
        Team.newTeamMap();

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team c = Tournament.getTournament().getTeam(i);
            Team tmp = Team.getTeam(c.getName());
            Assert.assertNull(tmp);
            Team.putTeam(c.getName(), c);
            tmp = Team.getTeam(c.getName());
            Assert.assertEquals(c, tmp);
        }
    }

    /**
     * Test of putTeam method, of class Team.
     */
    @Test
    public void testPutTeam() {
        System.out.println("putTeam");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        ArrayList<Team> list = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team c = Tournament.getTournament().getTeam(i);
            list.add(c);
        }
        Team.newTeamMap();

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team c = Tournament.getTournament().getTeam(i);
            Team tmp = Team.getTeam(c.getName());
            Assert.assertNull(tmp);
            Team.putTeam(c.getName(), c);
            tmp = Team.getTeam(c.getName());
            Assert.assertEquals(c, tmp);
        }
    }

    /**
     * Test of getTeam method, of class Team.
     */
    @Test
    public void testGetTeam() {
        System.out.println("getTeam");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        ArrayList<Team> list = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team c = Tournament.getTournament().getTeam(i);
            list.add(c);
        }
        Team.newTeamMap();

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team c = Tournament.getTournament().getTeam(i);
            Team tmp = Team.getTeam(c.getName());
            Assert.assertNull(tmp);
            Team.putTeam(c.getName(), c);
            tmp = Team.getTeam(c.getName());
            Assert.assertEquals(c, tmp);
        }
    }

    /**
     * Test of getName method, of class Team.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Team instance = new Team();
        String expResult = "Test";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(result, expResult);
    }

    /**
     * Test of compareTo method, of class Team.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        System.out.println("equals");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);
        Team instance2 = Tournament.getTournament().getTeam(1);

        for (int i = 0; i < instance.getCoachsCount(); i++) {
            instance.getCoach(i).setNafRank(200.0);
        }
        for (int i = 0; i < instance2.getCoachsCount(); i++) {
            instance2.getCoach(i).setNafRank(100.0);
        }

        int result = instance.compareTo(instance2);
        Assert.assertTrue(result > 0);

        for (int i = 0; i < instance.getCoachsCount(); i++) {
            instance.getCoach(i).setNafRank(100.0);
        }
        for (int i = 0; i < instance2.getCoachsCount(); i++) {
            instance2.getCoach(i).setNafRank(200.0);
        }

        result = instance.compareTo(instance2);
        Assert.assertTrue(result < 0);

        for (int i = 0; i < instance.getCoachsCount(); i++) {
            instance.getCoach(i).setNafRank(150.0);
        }
        for (int i = 0; i < instance2.getCoachsCount(); i++) {
            instance2.getCoach(i).setNafRank(150.0);
        }

        result = instance.compareTo(instance2);
        Assert.assertTrue(result == 0);

    }

    /**
     * Test of equals method, of class Team.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);

        Element result = instance.getXMLElement();

        Team cm = new Team();
        cm.setXMLElement(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of hashCode method, of class Team.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);
        int expResult = 19 * 7 + Objects.hashCode(instance.getActivePlayers());
        int result = instance.hashCode();
        assertEquals(result, expResult);
    }

    /**
     * Test of getActivePlayerNumber method, of class Team.
     */
    @Test
    public void testGetActivePlayerNumber() {
        System.out.println("getActivePlayerNumber");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);
        int expResult = Tournament.getTournament().getParams().getTeamMatesNumber();
        int result = instance.getActivePlayerNumber();
        assertEquals(result, expResult);
        instance.getCoach(0).setActive(false);
        result = instance.getActivePlayerNumber();
        assertEquals(result, expResult - 1);
    }

    /**
     * Test of getActivePlayers method, of class Team.
     */
    @Test
    public void testGetActivePlayers() {
        System.out.println("getActivePlayers");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);
        int expResult = Tournament.getTournament().getParams().getTeamMatesNumber();
        ArrayList result = instance.getActivePlayers();
        assertEquals(result.size(), expResult);
        instance.getCoach(0).setActive(false);
        result = instance.getActivePlayers();
        assertEquals(result.size(), expResult - 1);
        Assert.assertFalse(result.contains(instance.getCoach(0)));
    }

    /**
     * Test of getXMLElement method, of class Team.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);

        Element result = instance.getXMLElement();

        Team cm = new Team();
        cm.setXMLElement(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of setXMLElement method, of class Team.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);

        Element result = instance.getXMLElement();

        Team cm = new Team();
        cm.setXMLElement(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of addMatch method, of class Team.
     */
    @Test
    public void testAddMatch() {
        System.out.println("addMatch");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            while (t.getMatchCount() > 1) {
                Match m = t.getMatch(1);
                t.removeMatch(m);
            }
            for (int j = 0; j < t.getCoachsCount(); j++) {
                Coach c = t.getCoach(j);
                while (c.getMatchCount() > 1) {
                    Match m = c.getMatch(1);
                    c.removeMatch(m);
                }
            }
        }

        Team instance = Tournament.getTournament().getTeam(0);
        Team instance2 = Tournament.getTournament().getTeam(1);
        Round r = Tournament.getTournament().getRound(0);

        int nb = instance.getMatchCount();
        int nb2 = instance2.getMatchCount();
        instance.addMatch(instance2, r);

        Assert.assertEquals(nb + 1, instance.getMatchCount());
        Assert.assertEquals(nb2 + 1, instance2.getMatchCount());

    }

    /**
     * Test of havePlayed method, of class Team.
     */
    @Test
    public void testHavePlayed() {
        System.out.println("havePlayed");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Round r = Tournament.getTournament().getRound(0);
        TeamMatch tm = (TeamMatch) r.getMatch(0);
        TeamMatch tm2 = (TeamMatch) r.getMatch(1);

        // remove round 1
        while (Tournament.getTournament().getRoundsCount() > 1) {
            Tournament.getTournament().removeRound(1);
        }
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            while (t.getMatchCount() > 1) {
                Match m = t.getMatch(1);
                t.removeMatch(m);
            }
            for (int j = 0; j < t.getCoachsCount(); j++) {
                Coach c = t.getCoach(j);
                while (c.getMatchCount() > 1) {
                    Match m = c.getMatch(1);
                    c.removeMatch(m);
                }
            }
        }

        Assert.assertTrue(((Team) tm.getCompetitor1()).havePlayed(((Team) tm.getCompetitor2())));
        Assert.assertFalse(((Team) tm.getCompetitor1()).havePlayed(((Team) tm2.getCompetitor2())));
    }

    /**
     * Test of getPossibleOpponents method, of class Team.
     */
    @Test
    public void testGetPossibleOpponents() {
        System.out.println("getPossibleOpponents");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Round r = Tournament.getTournament().getRound(0);
        TeamMatch tm = (TeamMatch) r.getMatch(0);
        TeamMatch tm2 = (TeamMatch) r.getMatch(1);

        // remove round 1
        while (Tournament.getTournament().getRoundsCount() > 1) {
            Tournament.getTournament().removeRound(1);
        }
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            while (t.getMatchCount() > 1) {
                Match m = t.getMatch(1);
                t.removeMatch(m);
            }
            for (int j = 0; j < t.getCoachsCount(); j++) {
                Coach c = t.getCoach(j);
                while (c.getMatchCount() > 1) {
                    Match m = c.getMatch(1);
                    c.removeMatch(m);
                }
            }
        }

        ArrayList<Competitor> opponents = new ArrayList<>();
        for (int i = 1; i < Tournament.getTournament().getTeamsCount(); i++) {
            opponents.add(Tournament.getTournament().getTeam(i));
        }

        Team instance = Tournament.getTournament().getTeam(0);
        ArrayList result = instance.getPossibleOpponents(opponents, r);
        assertEquals(result.size(), Tournament.getTournament().getTeamsCount() - 2);

        while (Tournament.getTournament().getRoundsCount() > 0) {
            Tournament.getTournament().removeRound(0);
        }
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            while (t.getMatchCount() > 0) {
                Match m = t.getMatch(0);
                t.removeMatch(m);
            }
            for (int j = 0; j < t.getCoachsCount(); j++) {
                Coach c = t.getCoach(j);
                while (c.getMatchCount() > 0) {
                    Match m = c.getMatch(0);
                    c.removeMatch(m);
                }
            }
        }

        result = instance.getPossibleOpponents(opponents, r);
        assertEquals(result.size(), Tournament.getTournament().getTeamsCount() - 1);

    }

    /**
     * Test of getDecoratedName method, of class Team.
     */
    @Test
    public void testGetDecoratedName() {
        System.out.println("getDecoratedName");
        Team instance = new Team();
        String expResult = "Test";
        instance.setName(expResult);
        String result = instance.getDecoratedName();
        assertEquals(result, expResult);
    }

    /**
     * Test of addMatchRoundRobin method, of class Team.
     */
    @Test
    public void testAddMatchRoundRobin() {
        System.out.println("addMatchRoundRobin");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            while (t.getMatchCount() > 1) {
                Match m = t.getMatch(1);
                t.removeMatch(m);
            }
            for (int j = 0; j < t.getCoachsCount(); j++) {
                Coach c = t.getCoach(j);
                while (c.getMatchCount() > 1) {
                    Match m = c.getMatch(1);
                    c.removeMatch(m);
                }
            }
        }

        Team instance = Tournament.getTournament().getTeam(0);
        Team instance2 = Tournament.getTournament().getTeam(1);
        Round r = Tournament.getTournament().getRound(0);

        int nb = instance.getMatchCount();
        int nb2 = instance2.getMatchCount();
        instance.addMatchRoundRobin(instance2, r, false);

        Assert.assertEquals(nb + 1, instance.getMatchCount());
        Assert.assertEquals(nb2 + 1, instance2.getMatchCount());

        nb = instance.getMatchCount();
        nb2 = instance2.getMatchCount();
        instance.addMatchRoundRobin(instance2, r, true);

        Assert.assertEquals(nb + 1, instance.getMatchCount());
        Assert.assertEquals(nb2 + 1, instance2.getMatchCount());

        TeamMatch tm = (TeamMatch) instance.getMatch(instance.getMatchCount() - 1);
        assertEquals(tm.getMatchCount(), instance.getCoachsCount() * instance2.getCoachsCount());

    }

    /**
     * Test of canPlay method, of class Team.
     */
    @Test
    public void testCanPlay() {
        System.out.println("canPlay");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Round r = Tournament.getTournament().getRound(0);
        while (Tournament.getTournament().getRoundsCount() > 1) {
            Tournament.getTournament().removeRound(1);
        }
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            while (t.getMatchCount() > 1) {
                Match m = t.getMatch(1);
                t.removeMatch(m);
            }
            for (int j = 0; j < t.getCoachsCount(); j++) {
                Coach c = t.getCoach(j);
                while (c.getMatchCount() > 1) {
                    Match m = c.getMatch(1);
                    c.removeMatch(m);
                }
            }
        }
        TeamMatch tm = (TeamMatch) r.getMatch(0);
        Round r2 = new Round();
        Assert.assertFalse(((Team) tm.getCompetitor1()).canPlay(((Team) tm.getCompetitor2()), r2));

        TeamMatch tm2 = (TeamMatch) r.getMatch(1);
        Assert.assertTrue(((Team) tm.getCompetitor1()).canPlay(((Team) tm2.getCompetitor2()), r2));
    }

    /**
     * Test of roundCheck method, of class Team.
     */
    @Test
    public void testRoundCheck() {
        System.out.println("roundCheck");

        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);

        TeamMatch cm4 = (TeamMatch) instance.getMatch(instance.getMatchCount() - 1);
        TeamMatch cm0 = (TeamMatch) instance.getMatch(0);

        Team opponent;
        if (cm4.getCompetitor1() == instance) {
            cm0.setCompetitor1(instance);
            cm0.setCompetitor2(cm4.getCompetitor2());
            opponent = (Team) cm4.getCompetitor2();
        } else {
            cm0.setCompetitor1(instance);
            cm0.setCompetitor2(cm4.getCompetitor1());
            opponent = (Team) cm4.getCompetitor1();
        }

        instance.roundCheck(cm4.getRound());

        TeamMatch cm4bis = (TeamMatch) instance.getMatch(instance.getMatchCount() - 1);
        if (cm4bis.getCompetitor1() == instance) {
            Assert.assertNotEquals(opponent, cm4bis.getCompetitor2());
        } else {
            boolean b = opponent.equals(cm4bis.getCompetitor1());
            Assert.assertTrue(b);
        }

    }

    /**
     * Test of getTeamOppositionCount method, of class Team.
     */
    @Test
    public void testGetTeamOppositionCount() {
        System.out.println("getTeamOppositionCount");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Round r = Tournament.getTournament().getRound(0);
        TeamMatch tm = (TeamMatch) r.getMatch(0);
        TeamMatch tm2 = (TeamMatch) r.getMatch(1);

        // remove round 1
        while (Tournament.getTournament().getRoundsCount() > 1) {
            Tournament.getTournament().removeRound(1);
        }
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            while (t.getMatchCount() > 1) {
                Match m = t.getMatch(1);
                t.removeMatch(m);
            }
            for (int j = 0; j < t.getCoachsCount(); j++) {
                Coach c = t.getCoach(j);
                while (c.getMatchCount() > 1) {
                    Match m = c.getMatch(1);
                    c.removeMatch(m);
                }
            }
        }

        ArrayList<Team> opponents = new ArrayList<>();
        for (int i = 1; i < Tournament.getTournament().getTeamsCount(); i++) {
            opponents.add(Tournament.getTournament().getTeam(i));
        }

        Team instance = (Team) tm.getCompetitor1();
        HashMap<Team, Integer> result = instance.getTeamOppositionCount(opponents, new Round());
        assertEquals(result.get((Team) tm.getCompetitor2()).intValue(), instance.getCoachsCount());
        assertEquals(result.get((Team) tm2.getCompetitor2()).intValue(), 0);

    }

    /**
     * Test of getCoach method, of class Team.
     */
    @Test
    public void testGetCoach() {
        System.out.println("getCoach");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);

        for (int i = 0; i < instance.getCoachsCount(); i++) {
            Assert.assertNotNull(instance.getCoach(i));
        }
    }

    /**
     * Test of getCoachCount method, of class Team.
     */
    @Test
    public void testgetCoachsCount() {
        System.out.println("getCoachCount");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);

        for (int i = 0; i < instance.getCoachsCount(); i++) {
            Assert.assertNotNull(instance.getCoach(i));
        }
        assertEquals(instance.getCoachsCount(), Tournament.getTournament().getParams().getTeamMatesNumber());
    }

    /**
     * Test of containsCoach method, of class Team.
     */
    @Test
    public void testContainsCoach() {
        System.out.println("containsCoach");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);

        for (int i = 0; i < instance.getCoachsCount(); i++) {
            Assert.assertTrue(instance.containsCoach(instance.getCoach(i)));
        }

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            Coach c = Tournament.getTournament().getCoach(i);
            if (c.getTeamMates().getName().equals(instance.getName())) {
                Assert.assertTrue(instance.containsCoach(c));
            } else {
                Assert.assertFalse(instance.containsCoach(c));
            }
        }

    }

    /**
     * Test of addCoach method, of class Team.
     */
    @Test
    public void testAddCoach() {
        System.out.println("addCoach");
        Coach c = new Coach("Test");
        Team instance = new Team("Test");
        assertEquals(instance.getCoachsCount(), 0);
        instance.addCoach(c);
        assertEquals(instance.getCoachsCount(), 1);
    }

    /**
     * Test of removeCoach method, of class Team.
     */
    @Test
    public void testRemoveCoach() {
        System.out.println("removeCoach");
        Coach c = new Coach("Test");
        Team instance = new Team("Test");
        assertEquals(instance.getCoachsCount(), 0);
        instance.addCoach(c);
        assertEquals(instance.getCoachsCount(), 1);
        instance.removeCoach(0);
        assertEquals(instance.getCoachsCount(), 0);

    }

    /**
     * Test of clearCoachs method, of class Team.
     */
    @Test
    public void testClearCoachs() {
        System.out.println("clearCoachs");
        Coach c = new Coach("Test");
        Team instance = new Team("Test");
        assertEquals(instance.getCoachsCount(), 0);
        instance.addCoach(c);
        assertEquals(instance.getCoachsCount(), 1);
        instance.clearCoachs();
        assertEquals(instance.getCoachsCount(), 0);
    }

    /**
     * Test of getXMLElementForDisplay method, of class Team.
     */
    @Test
    public void testGetXMLElementForDisplay() {
        System.out.println("getXMLElementForDisplay");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);

        Element result = instance.getXMLElementForDisplay();

        Team cm = new Team();
        cm.setXMLElementForDisplay(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of setXMLElementForDisplay method, of class Team.
     */
    @Test
    public void testSetXMLElementForDisplay() {
        System.out.println("setXMLElementForDisplay");
        if (Tournament.getTournament().getTeamsCount() == 0) {
            fail("No team in tournament");
        }
        Team instance = Tournament.getTournament().getTeam(0);

        Element result = instance.getXMLElementForDisplay();

        Team cm = new Team();
        cm.setXMLElementForDisplay(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of getUID method, of class Team.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Team instance = new Team();
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pull method, of class Team.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        Team t = null;
        Team instance = new Team();
        instance.pull(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of push method, of class Team.
     */
    @Test
    public void testPush() {
        System.out.println("push");
        Team t = null;
        Team instance = new Team();
        instance.push(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class Team.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Team instance = new Team();
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoachsCount method, of class Team.
     */
    @Test
    public void testGetCoachsCount() {
        System.out.println("getCoachsCount");
        Team instance = new Team();
        int expResult = 0;
        int result = instance.getCoachsCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBalanced method, of class Team.
     */
    @Test
    public void testIsBalanced() {
        System.out.println("isBalanced");
        Team opp = null;
        Round round = null;
        Team instance = new Team();
        boolean expResult = false;
        boolean result = instance.isBalanced(opp, round);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
