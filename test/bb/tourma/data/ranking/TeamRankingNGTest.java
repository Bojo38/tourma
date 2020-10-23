/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.ObjectRanking;
import bb.tourma.data.Tournament;
import java.io.File;
import org.jdom.Element;
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
public class TeamRankingNGTest {
    
    public TeamRankingNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
         Tournament.getTournament().loadXML(new File("./test/team.xml"));
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
     * Test of isTeamVictory method, of class TeamRanking.
     */
    @Test
    public void testIsTeamVictory() {
        System.out.println("ismTeamVictory");
        TeamRanking instance = Tournament.getTournament().getRound(1).getRankings(false).getTeamRankingSet().getRanking();;
        boolean expResult = true;
        boolean result = instance.isTeamVictory();
        assertEquals(result, false);

    }

    /**
     * Test of setTeamVictory method, of class TeamRanking.
     */
    @Test
    public void testSetTeamVictory() {
        System.out.println("setmTeamVictory");
       TeamRanking instance = Tournament.getTournament().getRound(1).getRankings(false).getTeamRankingSet().getRanking();;
        boolean expResult = false;
        boolean result = instance.isTeamVictory();
        assertEquals(result, expResult);

        instance.setTeamVictory(true);
        result = instance.isTeamVictory();
        assertEquals(result, true);
    }

    /**
     * Test of sortDatas method, of class TeamRanking.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        TeamRanking instance = Tournament.getTournament().getRound(1).getRankings(false).getTeamRankingSet().getRanking();
        Element result = instance.getXMLElement();
        assertNotNull(result);

        TeamRanking other = new TeamRanking(result);

        assertEquals(other.getRankingType1(), instance.getRankingType1());
        assertEquals(other.getRankingType2(), instance.getRankingType2());
        assertEquals(other.getRankingType3(), instance.getRankingType3());
        assertEquals(other.getRankingType4(), instance.getRankingType4());
        assertEquals(other.getRankingType5(), instance.getRankingType5());
        assertEquals(other.getCount(), instance.getCount());
        assertEquals(other.getRound(), instance.getRound());

        other.sortDatas();
        instance.sortDatas();

        for (int i = 0; i < other.getRowCount(); i++) {
            System.out.println(other.getObject(i).toString()+ " / "+ instance.getObject(i).toString());
            assertEquals(other.getObject(i), instance.getObject(i));
        }
    }


    /**
     * Test of getXMLElement method, of class TeamRanking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        TeamRanking instance = Tournament.getTournament().getRound(1).getRankings(false).getTeamRankingSet().getRanking();
        Element result = instance.getXMLElement();
        assertNotNull(result);

        TeamRanking other = new TeamRanking(result);

        assertEquals(other.getRankingType1(), instance.getRankingType1());
        assertEquals(other.getRankingType2(), instance.getRankingType2());
        assertEquals(other.getRankingType3(), instance.getRankingType3());
        assertEquals(other.getRankingType4(), instance.getRankingType4());
        assertEquals(other.getRankingType5(), instance.getRankingType5());
        assertEquals(other.getCount(), instance.getCount());
        assertEquals(other.getRound(), instance.getRound());

        other.sortDatas();
        instance.sortDatas();

        for (int i = 0; i < other.getRowCount(); i++) {
            System.out.println(other.getObject(i).toString()+ " / "+ instance.getObject(i).toString());
            assertEquals(other.getObject(i), instance.getObject(i));
        }
    }

    /**
     * Test of setXMLElement method, of class TeamRanking.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        TeamRanking instance = Tournament.getTournament().getRound(1).getRankings(false).getTeamRankingSet().getRanking();
        Element result = instance.getXMLElement();
        assertNotNull(result);

        TeamRanking other = new TeamRanking(result);

        assertEquals(other.getRankingType1(), instance.getRankingType1());
        assertEquals(other.getRankingType2(), instance.getRankingType2());
        assertEquals(other.getRankingType3(), instance.getRankingType3());
        assertEquals(other.getRankingType4(), instance.getRankingType4());
        assertEquals(other.getRankingType5(), instance.getRankingType5());
        assertEquals(other.getCount(), instance.getCount());
        assertEquals(other.getRound(), instance.getRound());

        other.sortDatas();
        instance.sortDatas();

        for (int i = 0; i < other.getRowCount(); i++) {
            System.out.println(other.getObject(i).toString()+ " / "+ instance.getObject(i).toString());
            assertEquals(other.getObject(i), instance.getObject(i));
        }
    }
    
}
