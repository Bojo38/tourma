/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.ObjectRanking;
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
        IndivRanking instance = null;
        boolean expResult = false;
        boolean result = instance.isTeamTournament();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeamTournament method, of class IndivRanking.
     */
    @Test
    public void testSetTeamTournament() {
        System.out.println("setTeamTournament");
        boolean mTeamTournament = false;
        IndivRanking instance = null;
        instance.setTeamTournament(mTeamTournament);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isForPool method, of class IndivRanking.
     */
    @Test
    public void testIsForPool() {
        System.out.println("isForPool");
        IndivRanking instance = null;
        boolean expResult = false;
        boolean result = instance.isForPool();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setForPool method, of class IndivRanking.
     */
    @Test
    public void testSetForPool() {
        System.out.println("setForPool");
        boolean mForPool = false;
        IndivRanking instance = null;
        instance.setForPool(mForPool);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isForCup method, of class IndivRanking.
     */
    @Test
    public void testIsForCup() {
        System.out.println("isForCup");
        IndivRanking instance = null;
        boolean expResult = false;
        boolean result = instance.isForCup();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setForCup method, of class IndivRanking.
     */
    @Test
    public void testSetForCup() {
        System.out.println("setForCup");
        boolean mForCup = false;
        IndivRanking instance = null;
        instance.setForCup(mForCup);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortDatas method, of class IndivRanking.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        IndivRanking instance = null;
        instance.sortDatas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateHeadByHeadValue method, of class IndivRanking.
     */
    @Test
    public void testUpdateHeadByHeadValue() {
        System.out.println("updateHeadByHeadValue");
        int round_index = 0;
        int valueIndex = 0;
        ObjectRanking or1 = null;
        ObjectRanking or2 = null;
        IndivRanking instance = null;
        instance.updateHeadByHeadValue(round_index, valueIndex, or1, or2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class IndivRanking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        IndivRanking instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class IndivRanking.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        IndivRanking instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
