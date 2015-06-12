/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
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
public class CompetitorNGTest {
    
    public CompetitorNGTest() {
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
     * Test of generateRandomColor method, of class Competitor.
     */
    @Test
    public void testGenerateRandomColor() {
        System.out.println("generateRandomColor");
        Color mix = null;
        Color expResult = null;
        Color result = Competitor.generateRandomColor(mix);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatch method, of class Competitor.
     */
    @Test
    public void testAddMatch_Competitor_Round() {
        System.out.println("addMatch");
        Competitor opponent = null;
        Round r = null;
        Competitor instance = new CompetitorImpl();
        instance.addMatch(opponent, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatchRoundRobin method, of class Competitor.
     */
    @Test
    public void testAddMatchRoundRobin() {
        System.out.println("addMatchRoundRobin");
        Competitor opponent = null;
        Round r = null;
        Competitor instance = new CompetitorImpl();
        instance.addMatchRoundRobin(opponent, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of havePlayed method, of class Competitor.
     */
    @Test
    public void testHavePlayed() {
        System.out.println("havePlayed");
        Competitor opponent = null;
        Competitor instance = new CompetitorImpl();
        boolean expResult = false;
        boolean result = instance.havePlayed(opponent);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleOpponents method, of class Competitor.
     */
    @Test
    public void testGetPossibleOpponents() {
        System.out.println("getPossibleOpponents");
        ArrayList<Competitor> opponents = null;
        Round r = null;
        Competitor instance = new CompetitorImpl();
        ArrayList expResult = null;
        ArrayList result = instance.getPossibleOpponents(opponents, r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDecoratedName method, of class Competitor.
     */
    @Test
    public void testGetDecoratedName() {
        System.out.println("getDecoratedName");
        Competitor instance = new CompetitorImpl();
        String expResult = "";
        String result = instance.getDecoratedName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of roundCheck method, of class Competitor.
     */
    @Test
    public void testRoundCheck() {
        System.out.println("roundCheck");
        Round r = null;
        Competitor instance = new CompetitorImpl();
        instance.roundCheck(r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamOppositionCount method, of class Competitor.
     */
    @Test
    public void testGetTeamOppositionCount() {
        System.out.println("getTeamOppositionCount");
        ArrayList<Team> teams = null;
        Round current = null;
        Competitor instance = new CompetitorImpl();
        HashMap expResult = null;
        HashMap result = instance.getTeamOppositionCount(teams, current);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Competitor.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Competitor instance = new CompetitorImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Competitor.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Competitor instance = new CompetitorImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPicture method, of class Competitor.
     */
    @Test
    public void testGetPicture() {
        System.out.println("getPicture");
        Competitor instance = new CompetitorImpl();
        BufferedImage expResult = null;
        BufferedImage result = instance.getPicture();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Competitor.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String mName = "";
        Competitor instance = new CompetitorImpl();
        instance.setName(mName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColor method, of class Competitor.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Competitor instance = new CompetitorImpl();
        Color expResult = null;
        Color result = instance.getColor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColor method, of class Competitor.
     */
    @Test
    public void testSetColor() {
        System.out.println("setColor");
        Color mColor = null;
        Competitor instance = new CompetitorImpl();
        instance.setColor(mColor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPicture method, of class Competitor.
     */
    @Test
    public void testSetPicture() {
        System.out.println("setPicture");
        BufferedImage picture = null;
        Competitor instance = new CompetitorImpl();
        instance.setPicture(picture);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClan method, of class Competitor.
     */
    @Test
    public void testGetClan() {
        System.out.println("getClan");
        Competitor instance = new CompetitorImpl();
        Clan expResult = null;
        Clan result = instance.getClan();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClan method, of class Competitor.
     */
    @Test
    public void testSetClan() {
        System.out.println("setClan");
        Clan mClan = null;
        Competitor instance = new CompetitorImpl();
        instance.setClan(mClan);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatch method, of class Competitor.
     */
    @Test
    public void testGetMatch() {
        System.out.println("getMatch");
        int i = 0;
        Competitor instance = new CompetitorImpl();
        Match expResult = null;
        Match result = instance.getMatch(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatchCount method, of class Competitor.
     */
    @Test
    public void testGetMatchCount() {
        System.out.println("getMatchCount");
        Competitor instance = new CompetitorImpl();
        int expResult = 0;
        int result = instance.getMatchCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatch method, of class Competitor.
     */
    @Test
    public void testAddMatch_Match() {
        System.out.println("addMatch");
        Match m = null;
        Competitor instance = new CompetitorImpl();
        instance.addMatch(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMatch method, of class Competitor.
     */
    @Test
    public void testRemoveMatch() {
        System.out.println("removeMatch");
        Match m = null;
        Competitor instance = new CompetitorImpl();
        instance.removeMatch(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMatchsNotNull method, of class Competitor.
     */
    @Test
    public void testIsMatchsNotNull() {
        System.out.println("isMatchsNotNull");
        Competitor instance = new CompetitorImpl();
        boolean expResult = false;
        boolean result = instance.isMatchsNotNull();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newMatchs method, of class Competitor.
     */
    @Test
    public void testNewMatchs() {
        System.out.println("newMatchs");
        Competitor instance = new CompetitorImpl();
        instance.newMatchs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of matchIndex method, of class Competitor.
     */
    @Test
    public void testMatchIndex() {
        System.out.println("matchIndex");
        Match m = null;
        Competitor instance = new CompetitorImpl();
        int expResult = 0;
        int result = instance.matchIndex(m);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearMatchs method, of class Competitor.
     */
    @Test
    public void testClearMatchs() {
        System.out.println("clearMatchs");
        Competitor instance = new CompetitorImpl();
        instance.clearMatchs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CompetitorImpl extends Competitor {

        public void addMatch(Competitor opponent, Round r) {
        }

        public void addMatchRoundRobin(Competitor opponent, Round r) {
        }

        public boolean havePlayed(Competitor opponent) {
            return false;
        }

        public ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents, Round r) {
            return null;
        }

        public String getDecoratedName() {
            return "";
        }

        public void roundCheck(Round r) {
        }

        public HashMap<Team, Integer> getTeamOppositionCount(ArrayList<Team> teams, Round current) {
            return null;
        }

        @Override
        public int compareTo(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        
        
    }

    /**
     * Test of containsCategory method, of class Competitor.
     */
    @Test
    public void testContainsCategory() {
        System.out.println("containsCategory");
        Category cat = null;
        Competitor instance = new CompetitorImpl();
        boolean expResult = false;
        boolean result = instance.containsCategory(cat);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoryCount method, of class Competitor.
     */
    @Test
    public void testGetCategoryCount() {
        System.out.println("getCategoryCount");
        Competitor instance = new CompetitorImpl();
        int expResult = 0;
        int result = instance.getCategoryCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategory method, of class Competitor.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        int i = 0;
        Competitor instance = new CompetitorImpl();
        Category expResult = null;
        Category result = instance.getCategory(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCategory method, of class Competitor.
     */
    @Test
    public void testAddCategory() {
        System.out.println("addCategory");
        Category mCategory = null;
        Competitor instance = new CompetitorImpl();
        instance.addCategory(mCategory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delCategory method, of class Competitor.
     */
    @Test
    public void testDelCategory() {
        System.out.println("delCategory");
        Category mCategory = null;
        Competitor instance = new CompetitorImpl();
        instance.delCategory(mCategory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearCategory method, of class Competitor.
     */
    @Test
    public void testClearCategory() {
        System.out.println("clearCategory");
        Competitor instance = new CompetitorImpl();
        instance.clearCategory();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
