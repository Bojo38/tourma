/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import bb.tourma.data.ranking.Ranking;
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
public class RankingForExportNGTest {
    
    public RankingForExportNGTest() {
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
     * Test of getUID method, of class RankingForExport.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        RankingForExport instance = null;
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class RankingForExport.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        RankingForExport instance = null;
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriterion method, of class RankingForExport.
     */
    @Test
    public void testGetCriterion() {
        System.out.println("getCriterion");
        RankingForExport instance = null;
        Criterion expResult = null;
        Criterion result = instance.getCriterion();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCriterion method, of class RankingForExport.
     */
    @Test
    public void testSetCriterion() {
        System.out.println("setCriterion");
        Criterion c = null;
        RankingForExport instance = null;
        instance.setCriterion(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFormula method, of class RankingForExport.
     */
    @Test
    public void testGetFormula() {
        System.out.println("getFormula");
        RankingForExport instance = null;
        Formula expResult = null;
        Formula result = instance.getFormula();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFormula method, of class RankingForExport.
     */
    @Test
    public void testSetFormula() {
        System.out.println("setFormula");
        Formula c = null;
        RankingForExport instance = null;
        instance.setFormula(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class RankingForExport.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        RankingForExport instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class RankingForExport.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        RankingForExport instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingNumber method, of class RankingForExport.
     */
    @Test
    public void testGetRankingNumber() {
        System.out.println("getRankingNumber");
        RankingForExport instance = null;
        int expResult = 0;
        int result = instance.getRankingNumber();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRank method, of class RankingForExport.
     */
    @Test
    public void testGetRank() {
        System.out.println("getRank");
        RankingForExport instance = null;
        Ranking expResult = null;
        Ranking result = instance.getRank();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRank method, of class RankingForExport.
     */
    @Test
    public void testSetRank() {
        System.out.println("setRank");
        Ranking mRank = null;
        RankingForExport instance = null;
        instance.setRank(mRank);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class RankingForExport.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        RankingForExport instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class RankingForExport.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String mName = "";
        RankingForExport instance = null;
        instance.setName(mName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class RankingForExport.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        RankingForExport instance = null;
        String expResult = "";
        String result = instance.getType();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class RankingForExport.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String mType = "";
        RankingForExport instance = null;
        instance.setType(mType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueType method, of class RankingForExport.
     */
    @Test
    public void testGetValueType() {
        System.out.println("getValueType");
        RankingForExport instance = null;
        String expResult = "";
        String result = instance.getValueType();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValueType method, of class RankingForExport.
     */
    @Test
    public void testSetValueType() {
        System.out.println("setValueType");
        String mValueType = "";
        RankingForExport instance = null;
        instance.setValueType(mValueType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRowCount method, of class RankingForExport.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        RankingForExport instance = null;
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSortedObject method, of class RankingForExport.
     */
    @Test
    public void testGetSortedObject() {
        System.out.println("getSortedObject");
        int i = 0;
        RankingForExport instance = null;
        ObjectRanking expResult = null;
        ObjectRanking result = instance.getSortedObject(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSortedValue method, of class RankingForExport.
     */
    @Test
    public void testGetSortedValue() {
        System.out.println("getSortedValue");
        int i = 0;
        int valIndex = 0;
        RankingForExport instance = null;
        int expResult = 0;
        int result = instance.getSortedValue(i, valIndex);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetail method, of class RankingForExport.
     */
    @Test
    public void testGetDetail() {
        System.out.println("getDetail");
        RankingForExport instance = null;
        String expResult = "";
        String result = instance.getDetail();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDetail method, of class RankingForExport.
     */
    @Test
    public void testSetDetail() {
        System.out.println("setDetail");
        String d = "";
        RankingForExport instance = null;
        instance.setDetail(d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
