/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Coach;
import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.ObjectRanking;
import bb.tourma.data.Parameters;
import static bb.tourma.data.Parameters.C_RANKING_ELO;
import static bb.tourma.data.Parameters.C_RANKING_POINTS;
import bb.tourma.data.Tournament;
import bb.tourma.languages.Translate;
import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class RankingNGTest {

    public RankingNGTest() {
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
     * Test of getDetail method, of class Ranking.
     */
    @Test
    public void testGetDetail() {
        System.out.println("getDetail");
        Ranking instance = Tournament.getTournament().getRound(1).getRankings(false).getIndivRankingSet().getRanking();
        String expResult = "";
        String result = instance.getDetail();
        assertEquals(result, expResult);

        instance.setDetail("Test");
        result = instance.getDetail();
        assertEquals(result, "Test");
    }

    /**
     * Test of setDetail method, of class Ranking.
     */
    @Test
    public void testSetDetail() {
        System.out.println("setDetail");
        Ranking instance = Tournament.getTournament().getRound(1).getRankings(false).getIndivRankingSet().getRanking();
        String expResult = "Test";
        String result = instance.getDetail();
        assertEquals(result, expResult);

        instance.setDetail("Test");
        result = instance.getDetail();
        assertEquals(result, "Test");
    }

    /**
     * Test of setRoundOnly method, of class Ranking.
     */
    @Test
    public void testSetRoundOnly() {
        System.out.println("setRoundOnly");
        boolean mRoundOnly = false;
        Ranking instance = Tournament.getTournament().getRound(1).getRankings(false).getIndivRankingSet().getRanking();;
        instance.setRoundOnly(mRoundOnly);
    }

    /**
     * Test of getRowCount method, of class Ranking.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        Ranking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        int expResult = Tournament.getTournament().getCoachsCount();
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getSubtypeByValue method, of class Ranking.
     */
    @Test
    public void testGetSubtypeByValue() {
        System.out.println("getSubtypeByValue");

        int result = Ranking.getSubtypeByValue(Parameters.C_MAX_RANKING + 1);
        assertEquals(result, 0);

        result = Ranking.getSubtypeByValue(1);
        assertEquals(result, -1);

        result = Ranking.getSubtypeByValue(Parameters.C_MAX_RANKING + 2);
        assertEquals(result, 1);

        result = Ranking.getSubtypeByValue(Parameters.C_MAX_RANKING + 3);
        assertEquals(result, 2);

        result = Ranking.getSubtypeByValue(Parameters.C_MAX_RANKING + 4);
        assertEquals(result, 0);
    }

    /**
     * Test of convertVND method, of class Ranking.
     */
    @Test
    public void testConvertVND() {
        System.out.println("convertVND");
        int value = 0;
        String expResult = "0/0/0";
        String result = Ranking.convertVND(value);
        assertEquals(result, expResult);

        expResult = "0/1/0";
        result = Ranking.convertVND(1000);
        assertEquals(result, expResult);

        expResult = "1/0/0";
        result = Ranking.convertVND(1000000);
        assertEquals(result, expResult);

        expResult = "1/1/1";
        result = Ranking.convertVND(1001001);
        assertEquals(result, expResult);
    }

    /**
     * Test of getFormulaByValue method, of class Ranking.
     */
    @Test
    public void testGetFormulaByValue() {
        System.out.println("getFormulaByValue");
        Formula expResult = Tournament.getTournament().getParams().getFormula(0);
        Formula result = Ranking.getFormulaByValue(Parameters.C_MAX_RANKING + 1 + Tournament.getTournament().getParams().getCriteriaCount() * 3);
        assertEquals(result, expResult);

        expResult = null;
        result = Ranking.getFormulaByValue(Parameters.C_MAX_RANKING + 1);
        assertEquals(result, expResult);

        expResult = null;
        result = Ranking.getFormulaByValue(1);
        assertEquals(result, expResult);

    }

    /**
     * Test of getCriterionByValue method, of class Ranking.
     */
    @Test
    public void testGetCriterionByValue() {
        System.out.println("getCriteriaByValue");

        Criterion expResult = Tournament.getTournament().getParams().getCriterion(0);
        Criterion result = Ranking.getCriterionByValue(Parameters.C_MAX_RANKING + 1);
        assertEquals(result, expResult);

        expResult = null;
        result = Ranking.getCriterionByValue(1);
        assertEquals(result, expResult);

        expResult = Tournament.getTournament().getParams().getCriterion(0);
        result = Ranking.getCriterionByValue(Parameters.C_MAX_RANKING + 2);
        assertEquals(result, expResult);

        expResult = Tournament.getTournament().getParams().getCriterion(0);
        result = Ranking.getCriterionByValue(Parameters.C_MAX_RANKING + 3);
        assertEquals(result, expResult);

        expResult = Tournament.getTournament().getParams().getCriterion(1);
        result = Ranking.getCriterionByValue(Parameters.C_MAX_RANKING + 4);
        assertEquals(result, expResult);
    }

    /**
     * Test of getRankingFromString method, of class Ranking.
     */
    @Test
    public void testGetRankingFromString() {
        System.out.println("getRankingFromString");
        String ranking = Translate.translate(Translate.CS_Points);
        ArrayList<String> criterias = null;
        int expResult = C_RANKING_POINTS;
        int result = Ranking.getRankingFromString(ranking, criterias);
        assertEquals(result, expResult);

    }

    /**
     * Test of getRankingString method, of class Ranking.
     */
    @Test
    public void testGetRankingString() {
        System.out.println("getRankingString");
        int rankingType = 0;
        String expResult = Translate.translate(Translate.CS_Points);
        String result = Ranking.getRankingString(C_RANKING_POINTS);
        assertEquals(result, expResult);
    }

    /**
     * Test of getValueFromArray method, of class Ranking.
     */
    @Test
    public void testGetValueFromArray() {
        System.out.println("getValueFromArray");
        int rt = 0;
        Integer[] ar = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Integer> av = new ArrayList(Arrays.asList(ar));

        int expResult = 9;
        int result = Ranking.getValueFromArray(C_RANKING_ELO, av);
        assertEquals(result, expResult);
        expResult = 45;
        result = Ranking.getValueFromArray(C_RANKING_POINTS, av);
        assertEquals(result, expResult);
    }

    /**
     * Test of removeMinValue method, of class Ranking.
     */
    @Test
    public void testRemoveMinValue() {
        System.out.println("removeMinValue");
        
        Integer[] ar = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Integer> av = new ArrayList(Arrays.asList(ar));
        
        Ranking.removeMinValue(av);
        int[] result=new int[av.size()];
        for (int i=0; i<av.size(); i++)
        {
            result[i]=av.get(i);
        }
        int[] exp = { 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(result,exp);        
    }

    /**
     * Test of removeMaxValue method, of class Ranking.
     */
    @Test
    public void testRemoveMaxValue() {
        System.out.println("removeMaxValue");
       Integer[] ar = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Integer> av = new ArrayList(Arrays.asList(ar));
        
        Ranking.removeMaxValue(av);
        int[] result=new int[av.size()];
        for (int i=0; i<av.size(); i++)
        {
            result[i]=av.get(i);
        }
        int[] exp = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        assertEquals(result,exp);   
    }

    /**
     * Test of getRound method, of class Ranking.
     */
    @Test
    public void testGetRound() {
        System.out.println("getRound");

        Ranking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        int expResult = 0;
        int result = instance.getRound();
        assertEquals(result, expResult);

        instance = Tournament.getTournament().getRound(1).getRankings(false).getIndivRankingSet().getRanking();
        expResult = 1;
        result = instance.getRound();
        assertEquals(result, expResult);
    }

    /**
     * Test of getSortedValue method, of class Ranking.
     */
    @Test
    public void testGetSortedValue() {
        System.out.println("getSortedValue");
        Ranking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        int expResult = 1006;
        int result = instance.getSortedValue(0, 1);
        assertEquals(result, expResult);
    }

    /**
     * Test of getSortedObject method, of class Ranking.
     */
    @Test
    public void testGetSortedObject() {
        System.out.println("getSortedObject");

        Ranking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        String expResult = "papaGégé";
        ObjectRanking result = instance.getSortedObject(0);
        assertEquals(((Coach) result.getObject()).getName(), expResult);
    }

    /**
     * Test of sortDatas method, of class Ranking. useless test, already tested
     * by child classes
     */
    @Test(enabled = false)
    public void testSortDatas() throws Exception {
        System.out.println("sortDatas");
        Ranking instance = null;
        instance.sortDatas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingType1 method, of class Ranking.
     */
    @Test
    public void testGetRankingType1() {
        System.out.println("getRankingType1");
        Ranking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        int expResult = C_RANKING_POINTS;
        int result = instance.getRankingType1();
        assertEquals(result, expResult);

    }

    /**
     * Test of getRankingType2 method, of class Ranking.
     */
    @Test
    public void testGetRankingType2() {
        System.out.println("getRankingType2");
        Ranking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        int expResult = Parameters.C_RANKING_OPP_POINTS;
        int result = instance.getRankingType2();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRankingType3 method, of class Ranking.
     */
    @Test
    public void testGetRankingType3() {
        System.out.println("getRankingType3");
        Ranking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        int expResult = Parameters.C_RANKING_NONE;
        int result = instance.getRankingType3();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRankingType4 method, of class Ranking.
     */
    @Test
    public void testGetRankingType4() {
        System.out.println("getRankingType4");
        Ranking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        int expResult = Parameters.C_RANKING_NONE;
        int result = instance.getRankingType4();
        assertEquals(result, expResult);
    }

    /**
     * Test of getObject method, of class Ranking.
     */
    @Test
    public void testGetObject() {
        System.out.println("getObject");
        int i = 0;
        Ranking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();

        Object expResult = Tournament.getTournament().getCoach(8);
        Object result = instance.getObject(0);
        assertEquals(result, expResult);

    }

    /**
     * Test of getRankingType5 method, of class Ranking.
     */
    @Test
    public void testGetRankingType5() {
        System.out.println("getRankingType5");
        Ranking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getRanking();
        int expResult = Parameters.C_RANKING_NONE;
        int result = instance.getRankingType5();
        assertEquals(result, expResult);
    }

    /**
     * Test of getCount method, of class Ranking.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        Ranking instance = Tournament.getTournament().getRound(1).getRankings(false).getIndivRankingSet().getRanking();
        int expResult = Tournament.getTournament().getCoachsCount();
        int result = instance.getCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getXMLElement method, of class Ranking. Useless test beacaus
     * already passed by child classes
     */
    @Test(enabled = false)
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Ranking instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Ranking. Useless test beacaus
     * already passed by child classes
     */
    @Test(enabled = false)
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        Ranking instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class RankingImpl extends Ranking {

        public RankingImpl() {
            super(null);
        }

        public void sortDatas() throws RemoteException {
        }
    }

}
