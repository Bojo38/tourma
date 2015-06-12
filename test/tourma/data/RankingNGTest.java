/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom2.Element;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.tableModel.MjtRanking;
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

    /**
     * Test of getRank method, of class Ranking.
     */
    @Test
    public void testGetRank() {
        System.out.println("getRank");
        Ranking instance = null;
        MjtRanking expResult = null;
        MjtRanking result = instance.getRank();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRank method, of class Ranking.
     */
    @Test
    public void testSetRank() {
        System.out.println("setRank");
        MjtRanking mRank = null;
        Ranking instance = null;
        instance.setRank(mRank);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Ranking.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Ranking instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Ranking.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String mName = "";
        Ranking instance = null;
        instance.setName(mName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class Ranking.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Ranking instance = null;
        String expResult = "";
        String result = instance.getType();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class Ranking.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String mType = "";
        Ranking instance = null;
        instance.setType(mType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueType method, of class Ranking.
     */
    @Test
    public void testGetValueType() {
        System.out.println("getValueType");
        Ranking instance = null;
        String expResult = "";
        String result = instance.getValueType();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValueType method, of class Ranking.
     */
    @Test
    public void testSetValueType() {
        System.out.println("setValueType");
        String mValueType = "";
        Ranking instance = null;
        instance.setValueType(mValueType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriteria method, of class Ranking.
     */
    @Test
    public void testGetCriteria() {
        System.out.println("getCriteria");
        Ranking instance = null;
        Criteria expResult = null;
        Criteria result = instance.getCriteria();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCriteria method, of class Ranking.
     */
    @Test
    public void testSetCriteria() {
        System.out.println("setCriteria");
        Criteria c = null;
        Ranking instance = null;
        instance.setCriteria(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingNumber method, of class Ranking.
     */
    @Test
    public void testGetRankingNumber() {
        System.out.println("getRankingNumber");
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getRankingNumber();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of getSortedObject method, of class Ranking.
     */
    @Test
    public void testGetSortedObject() {
        System.out.println("getSortedObject");
        int i = 0;
        Ranking instance = null;
        ObjectRanking expResult = null;
        ObjectRanking result = instance.getSortedObject(i);
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
        int i = 0;
        int valIndex = 0;
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getSortedValue(i, valIndex);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
