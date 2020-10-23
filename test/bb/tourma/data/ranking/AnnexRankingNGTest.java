/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
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
public class AnnexRankingNGTest {

    public AnnexRankingNGTest() {
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
     * Test of sortDatas method, of class AnnexRanking. test is useless because
     * included by child classes tests
     */
    @Test(enabled = false)
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
        Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        AnnexRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getAnnexPosRanking().get(crit);
        Criterion result = instance.getCriterion();
        assertEquals(result, crit);
    }

    /**
     * Test of setCriterion method, of class AnnexRanking.
     */
    @Test
    public void testSetCriterion() {
        System.out.println("setCriterion");
        Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        Criterion crit2 = Tournament.getTournament().getParams().getCriterion(1);
        AnnexRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getAnnexPosRanking().get(crit);
        Criterion result = instance.getCriterion();
        assertEquals(result, crit);
        instance.setCriterion(crit2);
        result = instance.getCriterion();
        assertEquals(result, crit2);
    }

    /**
     * Test of getFormula method, of class AnnexRanking.
     */
    @Test
    public void testGetFormula() {
        System.out.println("getFormula");
        Formula form = Tournament.getTournament().getParams().getFormula(0);
        AnnexRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getAnnexFormRanking().get(form);
        Formula result = instance.getFormula();
        assertEquals(result, form);
    }

    /**
     * Test of setFormula method, of class AnnexRanking.
     */
    @Test
    public void testSetFormula() {
        System.out.println("setFormula");
        Formula form = Tournament.getTournament().getParams().getFormula(0);
        AnnexRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getAnnexFormRanking().get(form);
        Formula result = instance.getFormula();
        assertEquals(result, form);
        Formula f = new Formula("Test");
        instance.setFormula(f);
        result = instance.getFormula();
        assertEquals(result, f);
    }

    /**
     * Test of setSubtype method, of class AnnexRanking.
     */
    @Test
    public void testSetSubtype() {
        System.out.println("setSubtype");
       Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        AnnexRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getAnnexPosRanking().get(crit);
        int expResult = 0;
        int result = instance.getSubtype();
        assertEquals(result, expResult);
        instance.setSubtype(2);
        result = instance.getSubtype();
        assertEquals(result, 2);
    }

    /**
     * Test of getSubtype method, of class AnnexRanking.
     */
    @Test
    public void testGetSubtype() {
        System.out.println("getSubtype");
        Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        AnnexRanking instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getAnnexPosRanking().get(crit);
        int expResult = 0;
        int result = instance.getSubtype();
        assertEquals(result, expResult);
        
        instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getAnnexNegRanking().get(crit);
        expResult = 1;
        result = instance.getSubtype();
        assertEquals(result, expResult);
        
        instance = Tournament.getTournament().getRound(0).getRankings(false).getIndivRankingSet().getAnnexDifRanking().get(crit);
        expResult = 2;
        result = instance.getSubtype();
        assertEquals(result, expResult);

    }

    /**
     * Test of getXMLElement method, of class AnnexRanking. test is useless
     * because included by child classes tests
     */
    @Test(enabled = false)
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
     * Test of setXMLElement method, of class AnnexRanking. test is useless
     * because included by child classes tests
     */
    @Test(enabled = false)
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
