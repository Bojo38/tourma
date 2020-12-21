/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import bb.tourma.data.ranking.ClanRankingsSet;
import bb.tourma.data.ranking.IndivRanking;
import bb.tourma.data.ranking.IndivRankingsSet;
import bb.tourma.data.ranking.TeamRanking;
import bb.tourma.data.ranking.TeamRankingsSet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author WFMJ7631
 */
public class RankingsNGTest {

    public RankingsNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of setRoundOnly method, of class Rankings.
     */
    @Test
    public void testSetRoundOnly() {
        System.out.println("setRoundOnly");
        boolean roundOnly = false;
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(roundOnly);

        instance.setRoundOnly(roundOnly);
    }

    /**
     * Test of getTeamRankingSet method, of class Rankings.
     */
    @Test
    public void testGetTeamRankingSet() {
        System.out.println("getTeamRankingSet");
        Tournament.getTournament().loadXML(new File("./test/team.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);

        TeamRankingsSet result = instance.getTeamRankingSet();
        assertTrue(result instanceof TeamRankingsSet);

    }

    /**
     * Test of setTeamRankingSet method, of class Rankings.
     */
    @Test
    public void testSetTeamRankingSet() {
        System.out.println("setTeamRankingSet");

        Tournament.getTournament().loadXML(new File("./test/team.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);

        TeamRankingsSet result = instance.getTeamRankingSet();
        assertTrue(result instanceof TeamRankingsSet);

        instance.setTeamRankingSet(null);
        result = instance.getTeamRankingSet();
        assertNull(result);
    }

    /**
     * Test of getClanRankingSet method, of class Rankings.
     */
    @Test
    public void testGetClanRankingSet() {
        System.out.println("getClanRankingSet");
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);
        ClanRankingsSet expResult = null;
        ClanRankingsSet result = instance.getClanRankingSet();
        assertTrue(result instanceof ClanRankingsSet);

    }

    /**
     * Test of setClanRankingSet method, of class Rankings.
     */
    @Test
    public void testSetClanRankingSet() {
        System.out.println("setClanRankingSet");
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);

        ClanRankingsSet result = instance.getClanRankingSet();
        assertTrue(result instanceof ClanRankingsSet);

        instance.setClanRankingSet(null);
        result = instance.getClanRankingSet();
        assertNull(result);
    }

    /**
     * Test of getGroupRanking method, of class Rankings.
     */
    @Test
    public void testGetGroupRanking() {
        System.out.println("getGroupRanking");
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);
        HashMap expResult = null;
        HashMap result = instance.getGroupRanking();
        assertTrue(result instanceof HashMap);

    }

    /**
     * Test of setGroupRanking method, of class Rankings.
     */
    @Test
    public void testSetGroupRanking() {
        System.out.println("setGroupRanking");
        HashMap<Group, IndivRanking> mGroupRanking = null;
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);
        HashMap expResult = null;
        HashMap result = instance.getGroupRanking();
        assertTrue(result instanceof HashMap);

        instance.setGroupRanking(null);
        result = instance.getGroupRanking();
        assertNull(result);
    }

    /**
     * Test of getCategoryIndivRanking method, of class Rankings.
     */
    @Test
    public void testGetCategoryIndivRanking() {
        System.out.println("getCategoryIndivRanking");
        Tournament.getTournament().loadXML(new File("./test/category.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);
        HashMap expResult = null;
        HashMap result = instance.getCategoryIndivRanking();
        assertNotNull(result);
    }

    /**
     * Test of setCategoryIndivRanking method, of class Rankings.
     */
    @Test
    public void testSetCategoryIndivRanking() {
        System.out.println("setCategoryIndivRanking");
        Tournament.getTournament().loadXML(new File("./test/category.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);
        HashMap expResult = null;
        HashMap result = instance.getCategoryIndivRanking();
        assertNotNull(result);
        instance.setCategoryIndivRanking(expResult);
        result = instance.getCategoryIndivRanking();
        assertNull(result);
    }

    /**
     * Test of getCategoryTeamRanking method, of class Rankings.
     */
    @Test
    public void testGetCategoryTeamRanking() {
        System.out.println("getCategoryTeamRanking");

        HashMap<Group, IndivRanking> mGroupRanking = null;
        Tournament.getTournament().loadXML(new File("./test/category.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);

        HashMap result = instance.getCategoryTeamRanking();
        assertTrue(result instanceof HashMap);

        instance.setGroupRanking(null);
        result = instance.getGroupRanking();
        assertNull(result);
    }

    /**
     * Test of setCategoryTeamRanking method, of class Rankings.
     */
    @Test
    public void testSetCategoryTeamRanking() {
        System.out.println("setCategoryTeamRanking");

        HashMap<Group, IndivRanking> mGroupRanking = null;
        Tournament.getTournament().loadXML(new File("./test/category.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);

        HashMap result = instance.getCategoryTeamRanking();
        assertTrue(result instanceof HashMap);

        instance.setGroupRanking(null);
        result = instance.getGroupRanking();
        assertNull(result);

    }

    /**
     * Test of getPoolIndivRankings method, of class Rankings.
     */
    @Test
    public void testGetPoolIndivRankings() {
        System.out.println("getPoolIndivRankings");

        Tournament.getTournament().loadXML(new File("./test/pools.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);

        HashMap result = instance.getPoolIndivRankings();
        assertTrue(result instanceof HashMap);

        instance.setPoolIndivRankings(null);
        result = instance.getPoolIndivRankings();
        assertNull(result);
    }

    /**
     * Test of setPoolIndivRankings method, of class Rankings.
     */
    @Test
    public void testSetPoolIndivRankings() {
        System.out.println("setPoolIndivRankings");
        Tournament.getTournament().loadXML(new File("./test/pools.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);

        HashMap result = instance.getPoolIndivRankings();
        assertTrue(result instanceof HashMap);

        instance.setPoolIndivRankings(null);
        result = instance.getPoolIndivRankings();
        assertNull(result);
    }

    /**
     * Test of getPoolTeamRankings method, of class Rankings.
     */
    @Test
    public void testGetPoolTeamRankings() {
        System.out.println("getPoolTeamRankings");
        Tournament.getTournament().loadXML(new File("./test/pools_team.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);

        HashMap result = instance.getPoolTeamRankings();
        assertTrue(result instanceof HashMap);

        instance.setPoolTeamRankings(null);
        result = instance.getPoolTeamRankings();
        assertNull(result);
    }

    /**
     * Test of setPoolTeamRankings method, of class Rankings.
     */
    @Test
    public void testSetPoolTeamRankings() {
        System.out.println("setPoolTeamRankings");
        Tournament.getTournament().loadXML(new File("./test/pools_team.xml"));
        Rankings instance = Tournament.getTournament().getRound(0).getRankings(false);

        HashMap result = instance.getPoolTeamRankings();
        assertTrue(result instanceof HashMap);

        instance.setPoolTeamRankings(null);
        result = instance.getPoolTeamRankings();
        assertNull(result);
    }

    /**
     * Test of update method, of class Rankings.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Round r = Tournament.getTournament().getRound(0);
        Rankings instance = r.getRankings(false);
        instance.update();
        Element el = instance.getXMLElement();

        Document doc1 = new Document(el);

        XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
        OutputStream output = new OutputStream() {
            private StringBuilder string = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            //Netbeans IDE automatically overrides this toString()
            public String toString() {
                return this.string.toString();
            }
        };
        try {
            sortie.output(doc1, output);
        } catch (IOException ioe) {
            fail("Exception while comparing string");
        }
        String s1 = output.toString();

        CoachMatch cm = (CoachMatch) Tournament.getTournament().getRound(0).getMatch(0);
        Criterion c = Tournament.getTournament().getParams().getCriterion(0);
        cm.getValues().get(c).setValue1(10);
        instance.update();
        Element el2 = instance.getXMLElement();

        Document doc2 = new Document(el2);
        try {
            sortie.output(doc2, output);
        } catch (IOException ioe) {
            fail("Exception while comparing string");
        }
        String s2 = output.toString();
        assertNotEquals(s1, s2);
    }

    /**
     * Test of createRankings method, of class Rankings.
     */
    @Test
    public void testCreateRankings() {
        System.out.println("createRankings");
        int rNumber = 0;
        Tournament tour = Tournament.getTournament();
        Rankings instance = tour.getRound(rNumber).getRankings(false);
        instance.createRankings(rNumber, tour);

    }

    /**
     * Test of getIndivRankingSet method, of class Rankings.
     */
    @Test
    public void testGetIndivRankingSet() {
        System.out.println("getIndivRankingSet");
        int rNumber = 0;
        Tournament tour = Tournament.getTournament();
        Rankings instance = tour.getRound(rNumber).getRankings(false);

        IndivRankingsSet result = instance.getIndivRankingSet();

        assertTrue(result instanceof IndivRankingsSet);
    }

    /**
     * Test of setIndivRankingSet method, of class Rankings.
     */
    @Test
    public void testSetIndivRankingSet() {
        System.out.println("setIndivRankingSet");
        IndivRankingsSet mIndivRankingSet = null;

        Tournament tour = Tournament.getTournament();
        Rankings instance = tour.getRound(0).getRankings(false);
        IndivRankingsSet result = instance.getIndivRankingSet();
        assertTrue(result instanceof IndivRankingsSet);

        instance.setIndivRankingSet(mIndivRankingSet);
        assertNull(instance.getIndivRankingSet());
    }

    /**
     * Test of getXMLElement method, of class Rankings.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        int rNumber = 0;
        Tournament tour = Tournament.getTournament();

        Rankings instance = tour.getRound(rNumber).getRankings(false);

        Element expResult = instance.getXMLElement();

        assertNotNull(expResult);

        instance.setXMLElement(expResult);

        Element expResult2 = instance.getXMLElement();
        assertEquals(expResult.toString(), expResult2.toString());
    }

    /**
     * Test of setXMLElement method, of class Rankings.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Tournament tour = Tournament.getTournament();

        Rankings instance = tour.getRound(0).getRankings(false);
        instance.update();

        Element expResult = instance.getXMLElement();

        assertNotNull(expResult);

        instance.setXMLElement(expResult);
        instance.update();

        Element expResult2 = instance.getXMLElement();

        Document doc1 = new Document(expResult);
        Document doc2 = new Document(expResult2);

        XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
        OutputStream output1 = new OutputStream() {
            private StringBuilder string = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            //Netbeans IDE automatically overrides this toString()
            public String toString() {
                return this.string.toString();
            }
        };
        OutputStream output2 = new OutputStream() {
            private StringBuilder string = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            //Netbeans IDE automatically overrides this toString()
            public String toString() {
                return this.string.toString();
            }
        };
        try {
            sortie.output(doc1, output1);
        } catch (IOException ioe) {
            fail("Exception while comparing string");
        }
        String s1 = output1.toString();

        try {
            sortie.output(doc2, output2);
        } catch (IOException ioe) {
            fail("Exception while comparing string");
        }
        String s2 = output2.toString();

        System.out.println("S1 ---");
        System.out.println(s1);
        System.out.println("S2 ---");
        System.out.println(s2);
        assertEquals(s1, s2);
    }

}
