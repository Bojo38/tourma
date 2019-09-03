/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
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
        Tournament.getTournament().loadXML(new File("./test/coach.xml"));
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
        Color result = Competitor.generateRandomColor(mix);
        Assert.assertNotNull(result);
    }

    /**
     * Test of addMatch method, of class Competitor.
     */
    @Test
    public void testAddMatch_Competitor_Round() {
        System.out.println("addMatch");
        Competitor opponent = new CompetitorImpl();
        Round r = new Round();
        Competitor instance = new CompetitorImpl();
        instance.addMatch(opponent, r);
        Assert.assertTrue(instance.getMatchCount() == 1);
    }

    /**
     * Test of addMatchRoundRobin method, of class Competitor.
     */
    @Test
    public void testAddMatchRoundRobin() {
        System.out.println("addMatchRoundRobin");
        Competitor opponent = new CompetitorImpl();
        Round r = new Round();
        Competitor instance = new CompetitorImpl();
        instance.addMatchRoundRobin(opponent, r,true);
        Assert.assertTrue(instance.getMatchCount() == 1);
    }

    /**
     * Test of havePlayed method, of class Competitor.
     */
    @Test(enabled = false)
    public void testHavePlayed() {
        System.out.println("havePlayed");
        Competitor opponent = null;
        Competitor instance = new CompetitorImpl();
        boolean expResult = false;
        boolean result = instance.havePlayed(opponent);
        assertEquals(result, expResult);

    }

    /**
     * Test of getPossibleOpponents method, of class Competitor.
     */
    @Test(enabled = false)
    public void testGetPossibleOpponents() {
        System.out.println("getPossibleOpponents");
        ArrayList<Competitor> opponents = null;
        Round r = null;
        Competitor instance = new CompetitorImpl();
        ArrayList expResult = null;
        ArrayList result = instance.getPossibleOpponents(opponents, r);
        assertEquals(result, expResult);
    }

    /**
     * Test of getDecoratedName method, of class Competitor.
     */
    @Test(enabled = false)
    public void testGetDecoratedName() {
        System.out.println("getDecoratedName");
        Competitor instance = new CompetitorImpl();
        String expResult = "";
        String result = instance.getDecoratedName();
        assertEquals(result, expResult);
    }

    /**
     * Test of roundCheck method, of class Competitor.
     */
    @Test(enabled = false)
    public void testRoundCheck() {
        System.out.println("roundCheck");
        Round r = null;
        Competitor instance = new CompetitorImpl();
        instance.roundCheck(r);

    }

    /**
     * Test of getTeamOppositionCount method, of class Competitor.
     */
    @Test(enabled = false)
    public void testGetTeamOppositionCount() {
        System.out.println("getTeamOppositionCount");
        ArrayList<Team> teams = null;
        Round current = null;
        Competitor instance = new CompetitorImpl();
        HashMap expResult = null;
        HashMap result = instance.getTeamOppositionCount(teams, current);
        assertEquals(result, expResult);
    }

    /**
     * Test of toString method, of class Competitor.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Competitor instance = new CompetitorImpl();
        String expResult = "Test";
        instance.setName(expResult);
        String result = instance.toString();
        assertEquals(result, expResult);

    }

    /**
     * Test of getName method, of class Competitor.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Competitor instance = new CompetitorImpl();
        String expResult = "Test";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPicture method, of class Competitor.
     */
    @Test
    public void testGetPicture() {
        System.out.println("getPicture");
        Competitor instance = new CompetitorImpl();
        ImageIcon icon = new ImageIcon("./test/clan.png");
        //BufferedImage p = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        instance.setPicture(icon);

        assertNotNull(instance.getPicture());
        assertEquals(icon, instance.getPicture());
    }

    /**
     * Test of setName method, of class Competitor.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        Competitor instance = new CompetitorImpl();
        String expResult = "Test";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColor method, of class Competitor.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Competitor instance = new CompetitorImpl();
        Color expResult = new Color(456789);
        instance.setColor(expResult);
        assertEquals(instance.getColor(), expResult);
    }

    /**
     * Test of setColor method, of class Competitor.
     */
    @Test
    public void testSetColor() {
        System.out.println("setColor");
        Competitor instance = new CompetitorImpl();
        Color expResult = new Color(456789);
        instance.setColor(expResult);
        assertEquals(instance.getColor(), expResult);
    }

    /**
     * Test of setPicture method, of class Competitor.
     */
    @Test
    public void testSetPicture() {
        System.out.println("setPicture");
        Competitor instance = new CompetitorImpl();
        ImageIcon icon = new ImageIcon("./test/clan.png");
        //BufferedImage p = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        instance.setPicture(icon);

        assertNotNull(instance.getPicture());
        assertEquals(icon, instance.getPicture());
    }

    /**
     * Test of getClan method, of class Competitor.
     */
    @Test
    public void testGetClan() {
        System.out.println("getClan");
        Competitor instance = new CompetitorImpl();
        Clan expResult = new Clan("Aucun");
        assertEquals(instance.getClan(), Tournament.getTournament().getClan(0));
        instance.setClan(expResult);
        assertEquals(instance.getClan(), expResult);

    }

    /**
     * Test of setClan method, of class Competitor.
     */
    @Test
    public void testSetClan() {
        System.out.println("setClan");
        Competitor instance = new CompetitorImpl();
        Clan expResult = new Clan("Test");
        assertEquals(instance.getClan(), Tournament.getTournament().getClan(0));
        instance.setClan(expResult);
        assertEquals(instance.getClan(), expResult);
    }

    /**
     * Test of getMatch method, of class Competitor.
     */
    @Test
    public void testGetMatch() {
        System.out.println("getMatch");
        Competitor opponent = new CompetitorImpl();
        Round r = new Round();
        Competitor instance = new CompetitorImpl();
        Match m = new CoachMatch(r);
        m.setCompetitor1(instance);
        m.setCompetitor2(opponent);
        instance.addMatch(m);
        Match result = instance.getMatch(0);
        Assert.assertTrue(result == m);
    }

    /**
     * Test of getMatchCount method, of class Competitor.
     */
    @Test
    public void testGetMatchCount() {
        System.out.println("getMatchCount");
        Competitor opponent = new CompetitorImpl();
        Round r = new Round();
        Competitor instance = new CompetitorImpl();
        Match m = new CoachMatch(r);
        m.setCompetitor1(instance);
        m.setCompetitor2(opponent);
        instance.addMatch(m);
        int count = instance.getMatchCount();
        assertEquals(count, 1);
    }

    /**
     * Test of addMatch method, of class Competitor.
     */
    @Test
    public void testAddMatch_Match() {
        System.out.println("addMatch");
        Competitor opponent = new CompetitorImpl();
        Round r = new Round();
        Competitor instance = new CompetitorImpl();
        Match m = new CoachMatch(r);
        m.setCompetitor1(instance);
        m.setCompetitor2(opponent);
        instance.addMatch(m);
        Assert.assertTrue(instance.getMatchCount() == 1);
    }

    /**
     * Test of removeMatch method, of class Competitor.
     */
    @Test
    public void testRemoveMatch() {
        System.out.println("removeMatch");
        Competitor opponent = new CompetitorImpl();
        Round r = new Round();
        Competitor instance = new CompetitorImpl();
        instance.setName("Test");
        opponent.setName("Test2");
        instance.newMatchs();
        Match m = new CoachMatch(r);
        m.setCompetitor1(instance);
        m.setCompetitor2(opponent);
        instance.addMatch(m);
        Assert.assertTrue(instance.getMatchCount() == 1);
        instance.removeMatch(m);
        Assert.assertTrue(instance.getMatchCount() == 0);
    }

    /**
     * Test of isMatchsNotNull method, of class Competitor.
     */
    @Test
    public void testIsMatchsNotNull() {
        System.out.println("isMatchsNotNull");
        Competitor instance = new CompetitorImpl();
        boolean expResult = true;
        instance.newMatchs();
        boolean result = instance.isMatchsNotNull();        
        assertEquals(result, expResult);
    }

    /**
     * Test of newMatchs method, of class Competitor.
     */
    @Test
    public void testNewMatchs() {
        System.out.println("newMatchs");
        Competitor instance = new CompetitorImpl();
        instance.newMatchs();
        assertEquals(instance.getMatchCount(), 0);
    }

    /**
     * Test of matchIndex method, of class Competitor.
     */
    @Test
    public void testMatchIndex() {
        System.out.println("matchIndex");
        Competitor opponent = new CompetitorImpl();
        Round r = new Round();
        Competitor instance = new CompetitorImpl();
        Match m = new CoachMatch(r);
        m.setCompetitor1(instance);
        m.setCompetitor2(opponent);
        instance.addMatch(m);
         instance.setName("Test");
          opponent.setName("Test2");
        int index=instance.matchIndex(m) ;
        Assert.assertTrue(index== 0);

    }

    /**
     * Test of clearMatchs method, of class Competitor.
     */
    @Test
    public void testClearMatchs() {
        System.out.println("clearMatchs");
        Competitor opponent = new CompetitorImpl();
        Round r = new Round();
        Competitor instance = new CompetitorImpl();
        Match m = new CoachMatch(r);
        m.setCompetitor1(instance);
        m.setCompetitor2(opponent);
        instance.addMatch(m);
        Assert.assertTrue(instance.getMatchCount() == 1);
        instance.clearMatchs();
        Assert.assertTrue(instance.getMatchCount() == 0);
    }

    public class CompetitorImpl extends Competitor {

        public void addMatch(Competitor opponent, Round r) {
            Match m = new CoachMatch(r);
            m.setCompetitor1(this);
            m.setCompetitor2(opponent);
            mMatchs.add(m);
        }

        public void addMatchRoundRobin(Competitor opponent, Round r, boolean complete) {
            Match m = new CoachMatch(r);
            m.setCompetitor1(this);
            m.setCompetitor2(opponent);
            mMatchs.add(m);
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
        Competitor instance = new CompetitorImpl();
        Category expResult = new Category("Test");
        Assert.assertFalse(instance.containsCategory(expResult));
        instance.addCategory(expResult);
        Assert.assertTrue(instance.containsCategory(expResult));

    }

    /**
     * Test of getCategoryCount method, of class Competitor.
     */
    @Test
    public void testGetCategoryCount() {
        System.out.println("getCategoryCount");
        Competitor instance = new CompetitorImpl();
        Category expResult = new Category("Test");
        instance.addCategory(expResult);
        assertEquals(instance.getCategoryCount(), 1);
        instance.delCategory(expResult);
        assertEquals(instance.getCategoryCount(), 0);
    }

    /**
     * Test of getCategory method, of class Competitor.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        Competitor instance = new CompetitorImpl();
        Category expResult = new Category("Test");
        instance.addCategory(expResult);
        Category result = instance.getCategory(0);
        assertEquals(result, expResult);
    }

    /**
     * Test of addCategory method, of class Competitor.
     */
    @Test
    public void testAddCategory() {
        System.out.println("addCategory");
        Competitor instance = new CompetitorImpl();
        Category expResult = new Category("Test");
        instance.addCategory(expResult);
        Category result = instance.getCategory(0);
        assertEquals(result, expResult);
    }

    /**
     * Test of delCategory method, of class Competitor.
     */
    @Test
    public void testDelCategory() {
        System.out.println("delCategory");
        Competitor instance = new CompetitorImpl();
        Category expResult = new Category("Test");
        instance.addCategory(expResult);
        Category result = instance.getCategory(0);
        assertEquals(result, expResult);
        instance.delCategory(expResult);
        result = instance.getCategory(0);
        assertEquals(result, null);
    }

    /**
     * Test of clearCategory method, of class Competitor.
     */
    @Test
    public void testClearCategory() {
        System.out.println("clearCategory");
        Competitor instance = new CompetitorImpl();
        Category expResult = new Category("Test");
        instance.addCategory(expResult);
        Category result = instance.getCategory(0);
        assertEquals(result, expResult);
        instance.clearCategory();
        result = instance.getCategory(0);
        assertEquals(result, null);
    }

    /**
     * Test of equals method, of class Competitor.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object c = null;
        Competitor instance = new CompetitorImpl();
        instance.setName("Test");
        Competitor instance2 = new CompetitorImpl();
        instance2.setName("Test");
        assertEquals(instance, instance2);

    }

    /**
     * Test of hashCode method, of class Competitor.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Competitor instance = new CompetitorImpl();
        instance.setName("Test");;

        int result = instance.hashCode();
        assertEquals(result, ("Test").hashCode());

    }

    /**
     * Test of isUpdated method, of class Competitor.
     */
    @Test
    public void testIsUpdated() {
        System.out.println("isUpdated");
        Competitor instance = new CompetitorImpl();
        boolean expResult = false;
        boolean result = instance.isUpdated();
        assertEquals(result, expResult);
        instance.setUpdated(true);
        result = instance.isUpdated();
        assertEquals(result, true);
    }

    /**
     * Test of setUpdated method, of class Competitor.
     */
    @Test
    public void testSetUpdated() {
        System.out.println("setUpdated");
        Competitor instance = new CompetitorImpl();
        boolean expResult = false;
        boolean result = instance.isUpdated();
        assertEquals(result, expResult);
        instance.setUpdated(true);
        result = instance.isUpdated();
        assertEquals(result, true);
    }

    /**
     * Test of pull method, of class Competitor.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
/*        Competitor comp = null;
        Competitor instance = new CompetitorImpl();
        instance.pull(comp);*/
    }

    /**
     * Test of push method, of class Competitor.
     */
    @Test
    public void testPush() {
        System.out.println("push");
/*        Competitor comp = null;
        Competitor instance = new CompetitorImpl();
        instance.push(comp);*/
    }

    /**
     * Test of getRawName method, of class Competitor.
     */
    @Test
    public void testGetRawName() {
        System.out.println("getRawName");
        Competitor instance = new CompetitorImpl();
        
        String result = instance.getRawName();
        assertEquals(result, null);
        instance.setName("toto");
        result = instance.getRawName();
        assertEquals(result, "toto");
    }

    /**
     * Test of containsMatch method, of class Competitor.
     */
    @Test
    public void testContainsMatch() {
        System.out.println("containsMatch");
        Match m = new CoachMatch(Tournament.getTournament().getRound(0));
        Competitor instance = Tournament.getTournament().getCoach(0);
        
        boolean expResult = false;
        boolean result = instance.containsMatch(m);
        assertEquals(result, expResult);
        
        m=instance.getMatch(0);
        expResult = true;
        result = instance.containsMatch(m);
        assertEquals(result, expResult);
    }

    /**
     * Test of enableNafAvg method, of class Competitor.
     */
    @Test
    public void testEnableNafAvg() {
        System.out.println("enableNafAvg");
        boolean avg = false;
        Competitor instance = Tournament.getTournament().getCoach(0);
        instance.enableNafAvg(avg);        

    }

}
