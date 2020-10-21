/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.ObjectRanking;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
        Ranking instance = null;
        String expResult = "";
        String result = instance.getDetail();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDetail method, of class Ranking.
     */
    @Test
    public void testSetDetail() {
        System.out.println("setDetail");
        String d = "";
        Ranking instance = null;
        instance.setDetail(d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoundOnly method, of class Ranking.
     */
    @Test
    public void testSetRoundOnly() {
        System.out.println("setRoundOnly");
        boolean mRoundOnly = false;
        Ranking instance = null;
        instance.setRoundOnly(mRoundOnly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRowCount method, of class Ranking.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubtypeByValue method, of class Ranking.
     */
    @Test
    public void testGetSubtypeByValue() {
        System.out.println("getSubtypeByValue");
        int valueType = 0;
        int expResult = 0;
        int result = Ranking.getSubtypeByValue(valueType);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertVND method, of class Ranking.
     */
    @Test
    public void testConvertVND() {
        System.out.println("convertVND");
        int value = 0;
        String expResult = "";
        String result = Ranking.convertVND(value);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFormulaByValue method, of class Ranking.
     */
    @Test
    public void testGetFormulaByValue() {
        System.out.println("getFormulaByValue");
        int valueType = 0;
        Formula expResult = null;
        Formula result = Ranking.getFormulaByValue(valueType);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriteriaByValue method, of class Ranking.
     */
    @Test
    public void testGetCriteriaByValue() {
        System.out.println("getCriteriaByValue");
        int valueType = 0;
        Criterion expResult = null;
        Criterion result = Ranking.getCriteriaByValue(valueType);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingFromString method, of class Ranking.
     */
    @Test
    public void testGetRankingFromString() {
        System.out.println("getRankingFromString");
        String ranking = "";
        ArrayList<String> criterias = null;
        int expResult = 0;
        int result = Ranking.getRankingFromString(ranking, criterias);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingString method, of class Ranking.
     */
    @Test
    public void testGetRankingString() {
        System.out.println("getRankingString");
        int rankingType = 0;
        String expResult = "";
        String result = Ranking.getRankingString(rankingType);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueFromArray method, of class Ranking.
     */
    @Test
    public void testGetValueFromArray() {
        System.out.println("getValueFromArray");
        int rt = 0;
        ArrayList<Integer> av = null;
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getValueFromArray(rt, av);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMinValue method, of class Ranking.
     */
    @Test
    public void testRemoveMinValue() {
        System.out.println("removeMinValue");
        ArrayList<Integer> aValue = null;
        Ranking instance = null;
        instance.removeMinValue(aValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMaxValue method, of class Ranking.
     */
    @Test
    public void testRemoveMaxValue() {
        System.out.println("removeMaxValue");
        ArrayList<Integer> aValue = null;
        Ranking instance = null;
        instance.removeMaxValue(aValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRound method, of class Ranking.
     */
    @Test
    public void testGetRound() {
        System.out.println("getRound");
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getRound();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSortedValue method, of class Ranking.
     */
    @Test
    public void testGetSortedValue() {
        System.out.println("getSortedValue");
        int index = 0;
        int valIndex = 0;
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getSortedValue(index, valIndex);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSortedObject method, of class Ranking.
     */
    @Test
    public void testGetSortedObject() {
        System.out.println("getSortedObject");
        int index = 0;
        Ranking instance = null;
        ObjectRanking expResult = null;
        ObjectRanking result = instance.getSortedObject(index);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortDatas method, of class Ranking.
     */
    @Test
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
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getRankingType1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingType2 method, of class Ranking.
     */
    @Test
    public void testGetRankingType2() {
        System.out.println("getRankingType2");
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getRankingType2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingType3 method, of class Ranking.
     */
    @Test
    public void testGetRankingType3() {
        System.out.println("getRankingType3");
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getRankingType3();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingType4 method, of class Ranking.
     */
    @Test
    public void testGetRankingType4() {
        System.out.println("getRankingType4");
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getRankingType4();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObject method, of class Ranking.
     */
    @Test
    public void testGetObject() {
        System.out.println("getObject");
        int i = 0;
        Ranking instance = null;
        Object expResult = null;
        Object result = instance.getObject(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingType5 method, of class Ranking.
     */
    @Test
    public void testGetRankingType5() {
        System.out.println("getRankingType5");
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getRankingType5();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCount method, of class Ranking.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Ranking.
     */
    @Test
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
     * Test of setXMLElement method, of class Ranking.
     */
    @Test
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
