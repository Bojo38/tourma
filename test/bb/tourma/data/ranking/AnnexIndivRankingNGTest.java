/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Criterion;
import bb.tourma.data.ObjectAnnexRanking;
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
public class AnnexIndivRankingNGTest {

    public AnnexIndivRankingNGTest() {
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
     * Test of sortDatas method, of class AnnexIndivRanking.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        AnnexIndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getAnnexPosRanking().get(crit);
        Element result = instance.getXMLElement();
        assertNotNull(result);

        AnnexIndivRanking other = new AnnexIndivRanking(result);

        assertEquals(other.getRankingType1(), instance.getRankingType1());
        assertEquals(other.getRankingType2(), instance.getRankingType2());
        assertEquals(other.getRankingType3(), instance.getRankingType3());
        assertEquals(other.getRankingType4(), instance.getRankingType4());
        assertEquals(other.getRankingType5(), instance.getRankingType5());
        //System.out.println("Criterion: " + instance.getCriterion().getName());
        assertEquals(other.getCriterion(), instance.getCriterion());
        assertEquals(other.getCount(), instance.getCount());
        assertEquals(other.getFormula(), instance.getFormula());
        assertEquals(other.getRound(), instance.getRound());

        other.sortDatas();
        instance.sortDatas();

        for (int i = 0; i < other.getRowCount(); i++) {
            //System.out.println(other.getObject(i).toString()+ " / "+ instance.getObject(i).toString());
            assertEquals(other.getObject(i), instance.getObject(i));
        }
    }

    /**
     * Test of getXMLElement method, of class AnnexIndivRanking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        AnnexIndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getAnnexPosRanking().get(crit);
        Element result = instance.getXMLElement();
        assertNotNull(result);

        AnnexIndivRanking other = new AnnexIndivRanking(result);

        assertEquals(other.getRankingType1(), instance.getRankingType1());
        assertEquals(other.getRankingType2(), instance.getRankingType2());
        assertEquals(other.getRankingType3(), instance.getRankingType3());
        assertEquals(other.getRankingType4(), instance.getRankingType4());
        assertEquals(other.getRankingType5(), instance.getRankingType5());
        //System.out.println("Criterion: " + instance.getCriterion().getName());
        assertEquals(other.getCriterion(), instance.getCriterion());
        assertEquals(other.getCount(), instance.getCount());
        assertEquals(other.getFormula(), instance.getFormula());
        assertEquals(other.getRound(), instance.getRound());

        other.sortDatas();
        instance.sortDatas();

        for (int i = 0; i < other.getRowCount(); i++) {
            //System.out.println(other.getObject(i).toString()+ " / "+ instance.getObject(i).toString());
            assertEquals(other.getObject(i), instance.getObject(i));
        }
    }

    /**
     * Test of setXMLElement method, of class AnnexIndivRanking.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        AnnexIndivRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getAnnexPosRanking().get(crit);
        Element result = instance.getXMLElement();
        assertNotNull(result);

        AnnexIndivRanking other = new AnnexIndivRanking(result);

        assertEquals(other.getRankingType1(), instance.getRankingType1());
        assertEquals(other.getRankingType2(), instance.getRankingType2());
        assertEquals(other.getRankingType3(), instance.getRankingType3());
        assertEquals(other.getRankingType4(), instance.getRankingType4());
        assertEquals(other.getRankingType5(), instance.getRankingType5());
        //System.out.println("Criterion: " + instance.getCriterion().getName());
        assertEquals(other.getCriterion(), instance.getCriterion());
        assertEquals(other.getCount(), instance.getCount());
        assertEquals(other.getFormula(), instance.getFormula());
        assertEquals(other.getRound(), instance.getRound());

        other.sortDatas();
        instance.sortDatas();

        for (int i = 0; i < other.getRowCount(); i++) {
            //System.out.println(other.getObject(i).toString()+ " / "+ instance.getObject(i).toString());
            assertEquals(other.getObject(i), instance.getObject(i));
        }
    }

}
