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
public class IndivRankingNGTest {
    
    public IndivRankingNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
         Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
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
     * Test of isTeamTournament method, of class IndivRanking.
     */
    @Test
    public void testIsTeamTournament() {
        System.out.println("isTeamTournament");
        IndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        boolean expResult = false;
        boolean result = instance.isTeamTournament();
        assertEquals(result, expResult);
    }

    /**
     * Test of setTeamTournament method, of class IndivRanking.
     */
    @Test
    public void testSetTeamTournament() {
        System.out.println("setTeamTournament");
         IndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        boolean expResult = false;
        boolean result = instance.isTeamTournament();
        assertEquals(result, expResult);
        instance.setTeamTournament(true);
        // TODO review the generated test code and remove the default call to fail.
        result = instance.isTeamTournament();
        assertEquals(result, true);
    }

    /**
     * Test of isForPool method, of class IndivRanking.
     */
    @Test
    public void testIsForPool() {
        System.out.println("isForPool");
        IndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        boolean expResult = false;
        boolean result = instance.isForPool();
        assertEquals(result, expResult);
    }

    /**
     * Test of setForPool method, of class IndivRanking.
     */
    @Test
    public void testSetForPool() {
        System.out.println("setForPool");
         IndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        boolean expResult = false;
        boolean result = instance.isForPool();
        assertEquals(result, expResult);
        instance.setForPool(true);
        // TODO review the generated test code and remove the default call to fail.
        result = instance.isForPool();
        assertEquals(result, true);
    }

    /**
     * Test of isForCup method, of class IndivRanking.
     */
    @Test
    public void testIsForCup() {
        System.out.println("isForCup");
        IndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        boolean expResult = false;
        boolean result = instance.isForCup();
        assertEquals(result, expResult);
    }

    /**
     * Test of setForCup method, of class IndivRanking.
     */
    @Test
    public void testSetForCup() {
        System.out.println("setForCup");
         IndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        boolean expResult = false;
        boolean result = instance.isForCup();
        assertEquals(result, expResult);
        instance.setForCup(true);
        // TODO review the generated test code and remove the default call to fail.
        result = instance.isForCup();
        assertEquals(result, true);
    }

    /**
     * Test of sortDatas method, of class IndivRanking.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
       IndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        Element result = instance.getXMLElement();
        assertNotNull(result);

        IndivRanking other = new IndivRanking(result);

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
            //System.out.println(other.getObject(i).toString()+ " / "+ instance.getObject(i).toString());
            assertEquals(other.getObject(i), instance.getObject(i));
        }
    }


    /**
     * Test of getXMLElement method, of class IndivRanking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
       IndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        Element result = instance.getXMLElement();
        assertNotNull(result);

        IndivRanking other = new IndivRanking(result);

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
            //System.out.println(other.getObject(i).toString()+ " / "+ instance.getObject(i).toString());
            assertEquals(other.getObject(i), instance.getObject(i));
        }
    }

    /**
     * Test of setXMLElement method, of class IndivRanking.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
       IndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        Element result = instance.getXMLElement();
        assertNotNull(result);

        IndivRanking other = new IndivRanking(result);

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
            //System.out.println(other.getObject(i).toString()+ " / "+ instance.getObject(i).toString());
            assertEquals(other.getObject(i), instance.getObject(i));
        }
    }
    
}
