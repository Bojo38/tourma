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
public class TeamRankingNGTest {
    
    public TeamRankingNGTest() {
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
     * Test of ismTeamVictory method, of class TeamRanking.
     */
    @Test
    public void testIsmTeamVictory() {
        System.out.println("ismTeamVictory");
        TeamRanking instance = null;
        boolean expResult = false;
        boolean result = instance.ismTeamVictory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setmTeamVictory method, of class TeamRanking.
     */
    @Test
    public void testSetmTeamVictory() {
        System.out.println("setmTeamVictory");
        boolean mTeamVictory = false;
        TeamRanking instance = null;
        instance.setmTeamVictory(mTeamVictory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortDatas method, of class TeamRanking.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        TeamRanking instance = null;
        instance.sortDatas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateHeadByHeadValue method, of class TeamRanking.
     */
    @Test
    public void testUpdateHeadByHeadValue() {
        System.out.println("updateHeadByHeadValue");
        int round_index = 0;
        int valueIndex = 0;
        ObjectRanking or1 = null;
        ObjectRanking or2 = null;
        TeamRanking instance = null;
        instance.updateHeadByHeadValue(round_index, valueIndex, or1, or2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class TeamRanking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        TeamRanking instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class TeamRanking.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        TeamRanking instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
