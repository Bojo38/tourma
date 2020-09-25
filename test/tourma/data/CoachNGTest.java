/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.jdom.Element;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teamma.data.Roster;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class CoachNGTest {

    public CoachNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/coach.xml"));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getCoach method, of class Coach.
     */
    @Test
    public void testGetCoach() {
        System.out.println("getCoach");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            String name = Tournament.getTournament().getCoach(i).getName();
            Assert.assertNotNull(Coach.getCoach(name));

        }
    }

    /**
     * Test of newCoachMap method, of class Coach.
     */
    @Test
    public void testNewCoachMap() {
        System.out.println("newCoachMap");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        ArrayList<Coach> list = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            Coach c = Tournament.getTournament().getCoach(i);
            list.add(c);
        }
        Coach.newCoachMap();
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            Coach c = Tournament.getTournament().getCoach(i);
            Coach tmp = Coach.getCoach(c.getName());
            Assert.assertNull(tmp);
            Coach.putCoach(c.getName(), c);
            tmp = Coach.getCoach(c.getName());
            Assert.assertEquals(c, tmp);
        }
    }

    /**
     * Test of putCoach method, of class Coach.
     */
    @Test
    public void testPutCoach() {
        System.out.println("putCoach");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        ArrayList<Coach> list = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            Coach c = Tournament.getTournament().getCoach(i);
            list.add(c);
        }
        Coach.newCoachMap();
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            Coach c = Tournament.getTournament().getCoach(i);
            Coach tmp = Coach.getCoach(c.getName());
            Assert.assertNull(tmp);
            Coach.putCoach(c.getName(), c);
            tmp = Coach.getCoach(c.getName());
            Assert.assertEquals(c, tmp);
        }
    }

    /**
     * Test of getNullCoach method, of class Coach.
     */
    @Test
    public void testGetNullCoach() {
        System.out.println("getNullCoach");
        Coach result = Coach.getNullCoach();
        assertEquals(result.getName(), StringConstants.CS_NONE);
    }

    /**
     * Test of getCompositionCount method, of class Coach.
     */
    @Test
    public void testGetCompositionCount() {
        System.out.println("getCompositionCount");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        Assert.assertTrue(instance.getCompositionCount() > 0);
    }

    /**
     * Test of getComposition method, of class Coach.
     */
    @Test
    public void testGetComposition() {
        System.out.println("getComposition");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        Assert.assertTrue(instance.getCompositionCount() > 0);
        for (int i = 0; i < instance.getCompositionCount(); i++) {
            Assert.assertNotNull(instance.getComposition(i));
        }
    }

    /**
     * Test of addComposition method, of class Coach.
     */
    @Test
    public void testAddComposition() {
        System.out.println("addComposition");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        int nb = instance.getCompositionCount();
        Roster r = new Roster();
        instance.addComposition(r);
        Assert.assertTrue(nb + 1 == instance.getCompositionCount());
        instance.removeComposition(nb);
        Assert.assertTrue(nb == instance.getCompositionCount());
    }

    /**
     * Test of removeComposition method, of class Coach.
     */
    @Test
    public void testRemoveComposition() {
        System.out.println("removeComposition");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        int nb = instance.getCompositionCount();
        Roster r = new Roster();
        instance.addComposition(r);
        Assert.assertTrue(nb + 1 == instance.getCompositionCount());
        instance.removeComposition(nb);
        Assert.assertTrue(nb == instance.getCompositionCount());
    }

    /**
     * Test of compareTo method, of class Coach.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        instance.setNaf(0);
        Coach instance2 = new Coach("Lord Bojo");
        int result = instance.compareTo(instance2);
        assertEquals(result, 0);
    }

    /**
     * Test of equals method, of class Coach.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        Coach instance2 = new Coach("Lord Bojo");
        assertEquals(instance, instance2);
    }

    /**
     * Test of hashCode method, of class Coach.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        String s = "Lord Bojo";
        int result = instance.hashCode();
        assertEquals(result, s.hashCode());
    }

    /**
     * Test of getXMLElement method, of class Coach.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        Element result = instance.getXMLElement();

        Coach cm = new Coach();
        cm.setXMLElement(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of getStringRoster method, of class Coach.
     */
    @Test
    public void testGetStringRoster() {
        System.out.println("getStringRoster");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        assertEquals(instance.getStringRoster(), instance.getRoster().getName());
    }

    /**
     * Test of setXMLElement method, of class Coach.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        Element result = instance.getXMLElement();

        Coach cm = new Coach();
        cm.setXMLElement(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of addMatch method, of class Coach.
     */
    @Test
    public void testAddMatch() {
        System.out.println("addMatch");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        CoachMatch cm = new CoachMatch(new Round());
        int nb = instance.getMatchCount();
        instance.addMatch(cm);
        assertEquals(nb + 1, instance.getMatchCount());
        instance.removeMatch(cm);
        assertEquals(nb, instance.getMatchCount());
    }

    /**
     * Test of createMatch method, of class Coach.
     */
    @Test
    public void testCreateMatch() {
        System.out.println("createMatch");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        Coach c = new Coach("None");
        int nb = instance.getMatchCount();
        CoachMatch cm = instance.createMatch(c, new Round());
        instance.addMatch(cm);
        assertEquals(nb + 1, instance.getMatchCount());
        instance.removeMatch(cm);
        assertEquals(nb, instance.getMatchCount());
    }

    /**
     * Test of havePlayed method, of class Coach.
     */
    @Test
    public void testHavePlayed() {
        System.out.println("havePlayed");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            Coach opp = Tournament.getTournament().getCoach(i);
            if (opp != instance) {
                boolean found = false;
                for (int j = 0; j < instance.getMatchCount(); j++) {
                    Match m = instance.getMatch(j);
                    Coach c2;
                    if (m.getCompetitor1() == instance) {
                        c2 = (Coach) m.getCompetitor2();
                    } else {
                        c2 = (Coach) m.getCompetitor1();
                    }
                    if (c2 == opp) {
                        found = true;
                        break;
                    }
                }
                assertEquals(found, instance.havePlayed(opp));
            }

        }

    }

    /**
     * Test of getPossibleOpponents method, of class Coach.
     */
    @Test
    public void testGetPossibleOpponents() {
        System.out.println("getPossibleOpponents");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }

        Coach instance = Coach.getCoach("Lord Bojo");

        ArrayList<Competitor> opponents = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            Coach opp = Tournament.getTournament().getCoach(i);
            opponents.add(opp);
        }

        opponents.remove(instance);

        ArrayList<Competitor> result = instance.getPossibleOpponents(opponents, Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1));
        for (int j = 0; j < instance.getMatchCount(); j++) {
            Match m = instance.getMatch(j);
            Coach c2;
            if (m.getCompetitor1() == instance) {
                c2 = (Coach) m.getCompetitor2();
            } else {
                c2 = (Coach) m.getCompetitor1();
            }
            Assert.assertFalse(result.contains(c2));
        }
        assertEquals(result.size(), Tournament.getTournament().getCoachsCount() - instance.getMatchCount() - 1);

    }

    /**
     * Test of getTeamOppositionCount method, of class Coach.
     */
    @Test
    public void testGetTeamOppositionCount() {
        System.out.println("getTeamOppositionCount");
        Tournament.getTournament().loadXML(new File("./test/coach2.xml"));
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }

        Coach instance = Coach.getCoach("Jeff");
        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            if (!Tournament.getTournament().getTeam(i).equals(instance.getTeamMates())) {
                teams.add(Tournament.getTournament().getTeam(i));
            }
        }
        HashMap<Team, Integer> map = instance.getTeamOppositionCount(teams, Tournament.getTournament().getRound(0));
        for (Team t : map.keySet()) {
            if (t.getName().equals("Italy")) {
                assertEquals(map.get(t).intValue(), 2);
            } else {
                assertEquals(map.get(t).intValue(), 0);
            }
        }
    }

    /**
     * Test of getDecoratedName method, of class Coach.
     */
    @Test
    public void testGetDecoratedName() {
        System.out.println("getDecoratedName");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = new Coach("Lord Bojo");
        assertEquals("Lord Bojo", instance.getDecoratedName());

        Tournament.getTournament().getParams().setEnableClans(true);
        instance.setClan(new Clan("Clan"));
        assertEquals("Lord Bojo / Clan", instance.getDecoratedName());
        Tournament.getTournament().getParams().setEnableClans(false);

        Tournament.getTournament().getParams().setTeamTournament(true);
        instance.setTeamMates(new Team("Team"));
        assertEquals("Lord Bojo / Team", instance.getDecoratedName());
        Tournament.getTournament().getParams().setTeamTournament(false);

    }

    /**
     * Test of addMatchRoundRobin method, of class Coach.
     */
    @Test
    public void testAddMatchRoundRobin() {
        System.out.println("addMatchRoundRobin");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        Coach n = new Coach("Opponent");
        Round r = new Round();
        CoachMatch cm = new CoachMatch(r);
        int nb = instance.getMatchCount();
        instance.addMatchRoundRobin(n, r,true);
        assertEquals(1, r.getMatchsCount());
    }

    /**
     * Test of getMinimumTeamsBalanced method, of class Coach.
     */
    @Test
    public void testGetMinimumTeamsBalanced() {
        System.out.println("getMinimumTeamsBalanced");
        Tournament.getTournament().loadXML(new File("./test/coach3.xml"));
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }

        Coach instance = Coach.getCoach("Jeff");

        ArrayList<Team> teams = instance.getMinimumTeamsBalanced(Tournament.getTournament().getRound(0));
        for (Team t : teams) {
            Assert.assertNotEquals(t.getName(), "Austria");
        }
    }

    /**
     * Test of roundCheck method, of class Coach.
     */
    @Test
    public void testRoundCheck() {
        System.out.println("roundCheck");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        CoachMatch cm4 = (CoachMatch) instance.getMatch(instance.getMatchCount() - 1);
        CoachMatch cm0 = (CoachMatch) instance.getMatch(0);

        Coach opponent;
        if (cm4.getCompetitor1() == instance) {
            cm0.setCompetitor1(instance);
            cm0.setCompetitor2(cm4.getCompetitor2());
            opponent = (Coach) cm4.getCompetitor2();
        } else {
            cm0.setCompetitor1(instance);
            cm0.setCompetitor2(cm4.getCompetitor1());
            opponent = (Coach) cm4.getCompetitor1();
        }

        instance.roundCheck(cm4.getRound());

        CoachMatch cm4bis = (CoachMatch) instance.getMatch(instance.getMatchCount() - 1);
        if (cm4bis.getCompetitor1() == instance) {
            Assert.assertNotEquals(opponent, cm4bis.getCompetitor2());
        } else {
            boolean b=opponent.equals(cm4bis.getCompetitor1());
            Assert.assertFalse(b);
        }

    }

    /**
     * Test of getTeam method, of class Coach.
     */
    @Test
    public void testGetTeam() {
        System.out.println("getTeam");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        Assert.assertNotNull(instance.getTeam());

    }

    /**
     * Test of setTeam method, of class Coach.
     */
    @Test
    public void testSetTeam() {
        System.out.println("setTeam");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");
        Assert.assertNotNull(instance.getTeam());
        String name = instance.getTeam();
        instance.setTeam("Test");
        assertEquals("Test", instance.getTeam());
        instance.setTeam(name);
    }

    /**
     * Test of getRoster method, of class Coach.
     */
    @Test
    public void testGetRoster() {
        System.out.println("getRoster");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        RosterType save = instance.getRoster();
        instance.setRoster(new RosterType("Toto"));
        assertEquals(instance.getRoster().getName(), "Toto");
        instance.setRoster(save);
    }

    /**
     * Test of setRoster method, of class Coach.
     */
    @Test
    public void testSetRoster() {
        System.out.println("setRoster");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        RosterType save = instance.getRoster();
        instance.setRoster(new RosterType("Toto"));
        assertEquals(instance.getRoster().getName(), "Toto");
        instance.setRoster(save);
    }

    /**
     * Test of getNaf method, of class Coach.
     */
    @Test
    public void testGetNaf() {
        System.out.println("getNaf");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        int save = instance.getNaf();
        instance.setNaf(50);
        assertEquals(50, instance.getNaf());
        instance.setNaf(save);
    }

    /**
     * Test of setNaf method, of class Coach.
     */
    @Test
    public void testSetNaf() {
        System.out.println("setNaf");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        int save = instance.getNaf();
        instance.setNaf(50);
        assertEquals(50, instance.getNaf());
        instance.setNaf(save);
    }

    /**
     * Test of getRank method, of class Coach.
     */
    @Test
    public void testGetRank() {
        System.out.println("getRank");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        int save = instance.getRank();
        instance.setRank(50);
        assertTrue(instance.getRank() == 50);
        instance.setRank(save);
    }

    /**
     * Test of setRank method, of class Coach.
     */
    @Test
    public void testSetRank() {
        System.out.println("setRank");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        int save = instance.getRank();
        instance.setRank(50);
        assertTrue(instance.getRank() == 50);
        instance.setRank(save);
    }

    /**
     * Test of isActive method, of class Coach.
     */
    @Test
    public void testIsActive() {
        System.out.println("isActive");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        boolean save = instance.isActive();
        instance.setActive(true);
        assertEquals(true, instance.isActive());
        instance.setActive(false);
        assertEquals(false, instance.isActive());

        instance.setActive(save);
    }

    /**
     * Test of setActive method, of class Coach.
     */
    @Test
    public void testSetActive() {
        System.out.println("setActive");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        boolean save = instance.isActive();
        instance.setActive(true);
        assertEquals(true, instance.isActive());
        instance.setActive(false);
        assertEquals(false, instance.isActive());

        instance.setActive(save);
    }

    /**
     * Test of getTeamMates method, of class Coach.
     */
    @Test
    public void testGetTeamMates() {
        System.out.println("getTeamMates");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        Team save = instance.getTeamMates();
        instance.setTeamMates(Team.getNullTeam());
        assertEquals(instance.getTeamMates().getName(), StringConstants.CS_NONE);
        instance.setTeamMates(save);
    }

    /**
     * Test of setTeamMates method, of class Coach.
     */
    @Test
    public void testSetTeamMates() {
        System.out.println("setTeamMates");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        Team save = instance.getTeamMates();
        instance.setTeamMates(Team.getNullTeam());
        assertEquals(instance.getTeamMates().getName(), StringConstants.CS_NONE);
        instance.setTeamMates(save);
    }

    /**
     * Test of getNafRank method, of class Coach.
     */
    @Test
    public void testGetNafRank() {
        System.out.println("getNafRank");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        double save = instance.getNafRank();
        instance.setNafRank(50.0);
        assertEquals(50.0, instance.getNafRank());
        instance.setNafRank(save);
    }

    /**
     * Test of setNafRank method, of class Coach.
     */
    @Test
    public void testSetNafRank() {
        System.out.println("setNafRank");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        double save = instance.getNafRank();
        instance.setNafRank(50);
        assertTrue(Math.abs(50.0 - instance.getNafRank()) < 0.01);
        instance.setNafRank(save);
    }

    /**
     * Test of getHandicap method, of class Coach.
     */
    @Test
    public void testGetHandicap() {
        System.out.println("getHandicap");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        int save = instance.getHandicap();
        instance.setHandicap(50);
        assertEquals(50, instance.getHandicap());
        instance.setHandicap(save);
    }

    /**
     * Test of setHandicap method, of class Coach.
     */
    @Test
    public void testSetHandicap() {
        System.out.println("setHandicap");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        if (Tournament.getTournament().getCoachsCount() == 0) {
            fail("No coach in tournament");
        }
        Coach instance = Coach.getCoach("Lord Bojo");

        int save = instance.getHandicap();
        instance.setHandicap(50);
        assertEquals(50, instance.getHandicap());
        instance.setHandicap(save);
    }

    /**
     * Test of getUID method, of class Coach.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Coach instance = new Coach();
        int expResult = 0;
        instance.setUID(0);
        int result = instance.getUID();
        assertEquals(result, expResult);
    }

    /**
     * Test of setUID method, of class Coach.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
         Coach instance = Tournament.getTournament().getCoach(0);
        instance.setUID(UID);
        assertEquals(instance.getUID(), UID);
    }

    /**
     * Test of pull method, of class Coach.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
         Coach instance = Tournament.getTournament().getCoach(0);
        /*Coach instance = new Coach();
        instance.pull(c);*/
    }

    /**
     * Test of push method, of class Coach.
     */
    @Test
    public void testPush() {
        System.out.println("push");
/*        Coach c = null;
        Coach instance = new Coach();
        instance.push(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of isBalanced method, of class Coach.
     */
    @Test
    public void testIsBalanced() {
        System.out.println("isBalanced");
        Coach opp = null;
        Round round = null;
        Coach instance = new Coach();
        boolean expResult = true;
        boolean result = instance.isBalanced(opp, round);
        assertEquals(result, expResult);
    }

    /**
     * Test of printBalanced method, of class Coach.
     */
    @Test
    public void testPrintBalanced() {
        System.out.println("printBalanced");
        Round round = null;
        Coach instance = new Coach();
        instance.printBalanced(round);
    }

    /**
     * Test of getNafRankAvg method, of class Coach.
     */
    @Test
    public void testGetNafRankAvg() {
        System.out.println("getNafRankAvg");
         Coach instance = Tournament.getTournament().getCoach(0);
        double expResult = 150.0;
        double result = instance.getNafRankAvg();
        assertEquals(result, expResult, 0.0);
    }

    /**
     * Test of setNafAvg method, of class Coach.
     */
    @Test
    public void testSetNafAvg() {
        System.out.println("setNafAvg");
        double mNafRank = 0.0;
        Coach instance = Tournament.getTournament().getCoach(0);
        instance.setNafAvg(mNafRank);
        assertEquals(instance.getNafRankAvg(), mNafRank);

    }

    /**
     * Test of getPinCode method, of class Coach.
     */
    @Test
    public void testGetPinCode() {
        System.out.println("getPinCode");
        Coach instance = Tournament.getTournament().getCoach(0);
        int expResult = 0;
        int result = instance.getPinCode();
        assertEquals(result, expResult);
    }

    /**
     * Test of setPinCode method, of class Coach.
     */
    @Test
    public void testSetPinCode() {
        System.out.println("setPinCode");
        int pin = 1234;
         Coach instance = Tournament.getTournament().getCoach(0);
        instance.setPinCode(pin);
        assertEquals(instance.getPinCode(), pin);
    }

    /**
     * Test of getMatchRoster method, of class Coach.
     */
    @Test
    public void testGetMatchRoster() {
        System.out.println("getMatchRoster");
        int i = 0;
        Coach instance = Tournament.getTournament().getCoach(0);
        String expResult = "Humains";
        String result = instance.getMatchRoster(i);
        assertEquals(result, expResult);        
    }

}
