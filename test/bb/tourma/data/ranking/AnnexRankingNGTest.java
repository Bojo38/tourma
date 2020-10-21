/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
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
public class AnnexRankingNGTest {
    
    public AnnexRankingNGTest() {
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
     * Test of sortDatas method, of class AnnexRanking.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        AnnexRanking instance = null;
        instance.sortDatas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriterion method, of class AnnexRanking.
     */
    @Test
    public void testGetCriterion() {
        System.out.println("getCriterion");
        AnnexRanking instance = null;
        Criterion expResult = null;
        Criterion result = instance.getCriterion();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCriterion method, of class AnnexRanking.
     */
    @Test
    public void testSetCriterion() {
        System.out.println("setCriterion");
        Criterion c = null;
        AnnexRanking instance = null;
        instance.setCriterion(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFormula method, of class AnnexRanking.
     */
    @Test
    public void testGetFormula() {
        System.out.println("getFormula");
        AnnexRanking instance = null;
        Formula expResult = null;
        Formula result = instance.getFormula();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFormula method, of class AnnexRanking.
     */
    @Test
    public void testSetFormula() {
        System.out.println("setFormula");
        Formula f = null;
        AnnexRanking instance = null;
        instance.setFormula(f);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSubtype method, of class AnnexRanking.
     */
    @Test
    public void testSetSubtype() {
        System.out.println("setSubtype");
        int mSubtype = 0;
        AnnexRanking instance = null;
        instance.setSubtype(mSubtype);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubtype method, of class AnnexRanking.
     */
    @Test
    public void testGetSubtype() {
        System.out.println("getSubtype");
        AnnexRanking instance = null;
        int expResult = 0;
        int result = instance.getSubtype();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class AnnexRanking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        AnnexRanking instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class AnnexRanking.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        AnnexRanking instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AnnexRankingImpl extends AnnexRanking {

        public AnnexRankingImpl() {
            super(null);
        }

        public void sortDatas() {
        }
    }
    
}
