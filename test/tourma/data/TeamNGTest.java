/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.HashMap;
import org.jdom2.Element;
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
        Team expResult = null;
        Team result = Team.getNullTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNullTeam method, of class Team.
     */
    @Test
    public void testSetNullTeam() {
        System.out.println("setNullTeam");
        Team asNullTeam = null;
        Team.setNullTeam(asNullTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newTeamMap method, of class Team.
     */
    @Test
    public void testNewTeamMap() {
        System.out.println("newTeamMap");
        Team.newTeamMap();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putTeam method, of class Team.
     */
    @Test
    public void testPutTeam() {
        System.out.println("putTeam");
        String n = "";
        Team t = null;
        Team.putTeam(n, t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeam method, of class Team.
     */
    @Test
    public void testGetTeam() {
        System.out.println("getTeam");
        String n = "";
        Team expResult = null;
        Team result = Team.getTeam(n);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Team.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Team instance = new Team();
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Team.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object obj = null;
        Team instance = new Team();
        int expResult = 0;
        int result = instance.compareTo(obj);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Team.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        Team instance = new Team();
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Team.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Team instance = new Team();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivePlayerNumber method, of class Team.
     */
    @Test
    public void testGetActivePlayerNumber() {
        System.out.println("getActivePlayerNumber");
        Team instance = new Team();
        int expResult = 0;
        int result = instance.getActivePlayerNumber();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivePlayers method, of class Team.
     */
    @Test
    public void testGetActivePlayers() {
        System.out.println("getActivePlayers");
        Team instance = new Team();
        ArrayList expResult = null;
        ArrayList result = instance.getActivePlayers();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Team.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Team instance = new Team();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Team.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element team = null;
        Team instance = new Team();
        instance.setXMLElement(team);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatch method, of class Team.
     */
    @Test
    public void testAddMatch() {
        System.out.println("addMatch");
        Competitor opponent = null;
        Round r = null;
        Team instance = new Team();
        instance.addMatch(opponent, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of havePlayed method, of class Team.
     */
    @Test
    public void testHavePlayed() {
        System.out.println("havePlayed");
        Competitor opponent = null;
        Team instance = new Team();
        boolean expResult = false;
        boolean result = instance.havePlayed(opponent);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleOpponents method, of class Team.
     */
    @Test
    public void testGetPossibleOpponents() {
        System.out.println("getPossibleOpponents");
        ArrayList<Competitor> opponents = null;
        Round r = null;
        Team instance = new Team();
        ArrayList expResult = null;
        ArrayList result = instance.getPossibleOpponents(opponents, r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDecoratedName method, of class Team.
     */
    @Test
    public void testGetDecoratedName() {
        System.out.println("getDecoratedName");
        Team instance = new Team();
        String expResult = "";
        String result = instance.getDecoratedName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatchRoundRobin method, of class Team.
     */
    @Test
    public void testAddMatchRoundRobin() {
        System.out.println("addMatchRoundRobin");
        Competitor c = null;
        Round r = null;
        Team instance = new Team();
        instance.addMatchRoundRobin(c, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canPlay method, of class Team.
     */
    @Test
    public void testCanPlay() {
        System.out.println("canPlay");
        Team opponent = null;
        Round r = null;
        Team instance = new Team();
        boolean expResult = false;
        boolean result = instance.canPlay(opponent, r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of roundCheck method, of class Team.
     */
    @Test
    public void testRoundCheck() {
        System.out.println("roundCheck");
        Round round = null;
        Team instance = new Team();
        instance.roundCheck(round);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamOppositionCount method, of class Team.
     */
    @Test
    public void testGetTeamOppositionCount() {
        System.out.println("getTeamOppositionCount");
        ArrayList<Team> teams = null;
        Round current = null;
        Team instance = new Team();
        HashMap expResult = null;
        HashMap result = instance.getTeamOppositionCount(teams, current);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoach method, of class Team.
     */
    @Test
    public void testGetCoach() {
        System.out.println("getCoach");
        int i = 0;
        Team instance = new Team();
        Coach expResult = null;
        Coach result = instance.getCoach(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoachCount method, of class Team.
     */
    @Test
    public void testGetCoachCount() {
        System.out.println("getCoachCount");
        Team instance = new Team();
        int expResult = 0;
        int result = instance.getCoachCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsCoach method, of class Team.
     */
    @Test
    public void testContainsCoach() {
        System.out.println("containsCoach");
        Coach c = null;
        Team instance = new Team();
        boolean expResult = false;
        boolean result = instance.containsCoach(c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCoach method, of class Team.
     */
    @Test
    public void testAddCoach() {
        System.out.println("addCoach");
        Coach c = null;
        Team instance = new Team();
        instance.addCoach(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCoach method, of class Team.
     */
    @Test
    public void testRemoveCoach() {
        System.out.println("removeCoach");
        int i = 0;
        Team instance = new Team();
        instance.removeCoach(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearCoachs method, of class Team.
     */
    @Test
    public void testClearCoachs() {
        System.out.println("clearCoachs");
        Team instance = new Team();
        instance.clearCoachs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElementForDisplay method, of class Team.
     */
    @Test
    public void testGetXMLElementForDisplay() {
        System.out.println("getXMLElementForDisplay");
        Team instance = new Team();
        Element expResult = null;
        Element result = instance.getXMLElementForDisplay();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElementForDisplay method, of class Team.
     */
    @Test
    public void testSetXMLElementForDisplay() {
        System.out.println("setXMLElementForDisplay");
        Element team = null;
        Team instance = new Team();
        instance.setXMLElementForDisplay(team);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
