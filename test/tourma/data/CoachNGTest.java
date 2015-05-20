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
import teamma.data.Roster;
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
        String s = "";
        Coach expResult = null;
        Coach result = Coach.getCoach(s);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newCoachMap method, of class Coach.
     */
    @Test
    public void testNewCoachMap() {
        System.out.println("newCoachMap");
        Coach.newCoachMap();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putCoach method, of class Coach.
     */
    @Test
    public void testPutCoach() {
        System.out.println("putCoach");
        String s = "";
        Coach c = null;
        Coach.putCoach(s, c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNullCoach method, of class Coach.
     */
    @Test
    public void testGetNullCoach() {
        System.out.println("getNullCoach");
        Coach expResult = null;
        Coach result = Coach.getNullCoach();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompositionCount method, of class Coach.
     */
    @Test
    public void testGetCompositionCount() {
        System.out.println("getCompositionCount");
        Coach instance = new Coach();
        int expResult = 0;
        int result = instance.getCompositionCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getComposition method, of class Coach.
     */
    @Test
    public void testGetComposition() {
        System.out.println("getComposition");
        int i = 0;
        Coach instance = new Coach();
        Roster expResult = null;
        Roster result = instance.getComposition(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addComposition method, of class Coach.
     */
    @Test
    public void testAddComposition() {
        System.out.println("addComposition");
        Roster r = null;
        Coach instance = new Coach();
        instance.addComposition(r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeComposition method, of class Coach.
     */
    @Test
    public void testRemoveComposition() {
        System.out.println("removeComposition");
        int i = 0;
        Coach instance = new Coach();
        instance.removeComposition(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Coach.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object obj = null;
        Coach instance = new Coach();
        int expResult = 0;
        int result = instance.compareTo(obj);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Coach.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object c = null;
        Coach instance = new Coach();
        boolean expResult = false;
        boolean result = instance.equals(c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Coach.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Coach instance = new Coach();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Coach.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Coach instance = new Coach();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringRoster method, of class Coach.
     */
    @Test
    public void testGetStringRoster() {
        System.out.println("getStringRoster");
        Coach instance = new Coach();
        String expResult = "";
        String result = instance.getStringRoster();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Coach.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element coach = null;
        Coach instance = new Coach();
        instance.setXMLElement(coach);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatch method, of class Coach.
     */
    @Test
    public void testAddMatch() {
        System.out.println("addMatch");
        Competitor opponent = null;
        Round r = null;
        Coach instance = new Coach();
        instance.addMatch(opponent, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMatch method, of class Coach.
     */
    @Test
    public void testCreateMatch() {
        System.out.println("createMatch");
        Competitor opponent = null;
        Round r = null;
        Coach instance = new Coach();
        CoachMatch expResult = null;
        CoachMatch result = instance.createMatch(opponent, r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of havePlayed method, of class Coach.
     */
    @Test
    public void testHavePlayed() {
        System.out.println("havePlayed");
        Competitor opponent = null;
        Coach instance = new Coach();
        boolean expResult = false;
        boolean result = instance.havePlayed(opponent);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleOpponents method, of class Coach.
     */
    @Test
    public void testGetPossibleOpponents() {
        System.out.println("getPossibleOpponents");
        ArrayList<Competitor> opponents = null;
        Round r = null;
        Coach instance = new Coach();
        ArrayList expResult = null;
        ArrayList result = instance.getPossibleOpponents(opponents, r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamOppositionCount method, of class Coach.
     */
    @Test
    public void testGetTeamOppositionCount() {
        System.out.println("getTeamOppositionCount");
        ArrayList<Team> teams = null;
        Round r = null;
        Coach instance = new Coach();
        HashMap expResult = null;
        HashMap result = instance.getTeamOppositionCount(teams, r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDecoratedName method, of class Coach.
     */
    @Test
    public void testGetDecoratedName() {
        System.out.println("getDecoratedName");
        Coach instance = new Coach();
        String expResult = "";
        String result = instance.getDecoratedName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatchRoundRobin method, of class Coach.
     */
    @Test
    public void testAddMatchRoundRobin() {
        System.out.println("addMatchRoundRobin");
        Competitor c = null;
        Round r = null;
        Coach instance = new Coach();
        instance.addMatchRoundRobin(c, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinimumTeamsBalanced method, of class Coach.
     */
    @Test
    public void testGetMinimumTeamsBalanced() {
        System.out.println("getMinimumTeamsBalanced");
        Round round = null;
        Coach instance = new Coach();
        ArrayList expResult = null;
        ArrayList result = instance.getMinimumTeamsBalanced(round);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of roundCheck method, of class Coach.
     */
    @Test
    public void testRoundCheck() {
        System.out.println("roundCheck");
        Round round = null;
        Coach instance = new Coach();
        instance.roundCheck(round);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    

    /**
     * Test of getTeam method, of class Coach.
     */
    @Test
    public void testGetTeam() {
        System.out.println("getTeam");
        Coach instance = new Coach();
        String expResult = "";
        String result = instance.getTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeam method, of class Coach.
     */
    @Test
    public void testSetTeam() {
        System.out.println("setTeam");
        String mTeam = "";
        Coach instance = new Coach();
        instance.setTeam(mTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoster method, of class Coach.
     */
    @Test
    public void testGetRoster() {
        System.out.println("getRoster");
        Coach instance = new Coach();
        RosterType expResult = null;
        RosterType result = instance.getRoster();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoster method, of class Coach.
     */
    @Test
    public void testSetRoster() {
        System.out.println("setRoster");
        RosterType mRoster = null;
        Coach instance = new Coach();
        instance.setRoster(mRoster);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNaf method, of class Coach.
     */
    @Test
    public void testGetNaf() {
        System.out.println("getNaf");
        Coach instance = new Coach();
        int expResult = 0;
        int result = instance.getNaf();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNaf method, of class Coach.
     */
    @Test
    public void testSetNaf() {
        System.out.println("setNaf");
        int mNaf = 0;
        Coach instance = new Coach();
        instance.setNaf(mNaf);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRank method, of class Coach.
     */
    @Test
    public void testGetRank() {
        System.out.println("getRank");
        Coach instance = new Coach();
        int expResult = 0;
        int result = instance.getRank();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRank method, of class Coach.
     */
    @Test
    public void testSetRank() {
        System.out.println("setRank");
        int mRank = 0;
        Coach instance = new Coach();
        instance.setRank(mRank);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isActive method, of class Coach.
     */
    @Test
    public void testIsActive() {
        System.out.println("isActive");
        Coach instance = new Coach();
        boolean expResult = false;
        boolean result = instance.isActive();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActive method, of class Coach.
     */
    @Test
    public void testSetActive() {
        System.out.println("setActive");
        boolean mActive = false;
        Coach instance = new Coach();
        instance.setActive(mActive);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamMates method, of class Coach.
     */
    @Test
    public void testGetTeamMates() {
        System.out.println("getTeamMates");
        Coach instance = new Coach();
        Team expResult = null;
        Team result = instance.getTeamMates();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeamMates method, of class Coach.
     */
    @Test
    public void testSetTeamMates() {
        System.out.println("setTeamMates");
        Team mTeamMates = null;
        Coach instance = new Coach();
        instance.setTeamMates(mTeamMates);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNafRank method, of class Coach.
     */
    @Test
    public void testGetNafRank() {
        System.out.println("getNafRank");
        Coach instance = new Coach();
        double expResult = 0.0;
        double result = instance.getNafRank();
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNafRank method, of class Coach.
     */
    @Test
    public void testSetNafRank() {
        System.out.println("setNafRank");
        double mNafRank = 0.0;
        Coach instance = new Coach();
        instance.setNafRank(mNafRank);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHandicap method, of class Coach.
     */
    @Test
    public void testGetHandicap() {
        System.out.println("getHandicap");
        Coach instance = new Coach();
        int expResult = 0;
        int result = instance.getHandicap();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHandicap method, of class Coach.
     */
    @Test
    public void testSetHandicap() {
        System.out.println("setHandicap");
        int mHandicap = 0;
        Coach instance = new Coach();
        instance.setHandicap(mHandicap);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
