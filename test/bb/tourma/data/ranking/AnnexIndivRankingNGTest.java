/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.ObjectAnnexRanking;
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
        AnnexIndivRanking instance = null;
        instance.sortDatas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateHeadByHeadValue method, of class AnnexIndivRanking.
     */
    @Test
    public void testUpdateHeadByHeadValue() {
        System.out.println("updateHeadByHeadValue");
        int round_index = 0;
        int valueIndex = 0;
        ObjectAnnexRanking or1 = null;
        ObjectAnnexRanking or2 = null;
        AnnexIndivRanking instance = null;
        instance.updateHeadByHeadValue(round_index, valueIndex, or1, or2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class AnnexIndivRanking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        AnnexIndivRanking instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class AnnexIndivRanking.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        AnnexIndivRanking instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
