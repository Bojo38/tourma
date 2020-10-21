/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Competitor;
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
public class ManualRankingNGTest {
    
    public ManualRankingNGTest() {
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
     * Test of sortDatas method, of class ManualRanking.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        ManualRanking instance = null;
        instance.sortDatas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addData method, of class ManualRanking.
     */
    @Test
    public void testAddData() {
        System.out.println("addData");
        Competitor obj = null;
        ManualRanking instance = null;
        instance.addData(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delData method, of class ManualRanking.
     */
    @Test
    public void testDelData() {
        System.out.println("delData");
        Competitor obj = null;
        ManualRanking instance = null;
        instance.delData(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDatas method, of class ManualRanking.
     */
    @Test
    public void testAddDatas() {
        System.out.println("addDatas");
        ArrayList<Competitor> objs = null;
        ManualRanking instance = null;
        instance.addDatas(objs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class ManualRanking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        ManualRanking instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class ManualRanking.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        ManualRanking instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
