/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import java.util.ArrayList;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Category;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.Group;
import tourma.data.Pool;
import tourma.data.Round;
import tourma.data.Team;

/**
 *
 * @author WFMJ7631
 */
public class WebRoundNGTest {
    
    public WebRoundNGTest() {
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
     * Test of getMenu method, of class WebRound.
     */
    @Test
    public void testGetMenu() {
        System.out.println("getMenu");
        int nb = 0;
        boolean withExtension = false;
        String expResult = "";
        String result = WebRound.getMenu(nb, withExtension);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHTML method, of class WebRound.
     */
    @Test
    public void testGetHTML() {
        System.out.println("getHTML");
        int nb = 0;
        String command = "";
        String expResult = "";
        String result = WebRound.getHTML(nb, command);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createIndividualMatch method, of class WebRound.
     */
    @Test
    public void testCreateIndividualMatch() {
        System.out.println("createIndividualMatch");
        Round r = null;
        String expResult = "";
        String result = WebRound.createIndividualMatch(r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createIndividualRanking method, of class WebRound.
     */
    @Test
    public void testCreateIndividualRanking_3args() {
        System.out.println("createIndividualRanking");
        Round r = null;
        ArrayList<Coach> coachs = null;
        String rankName = "";
        String expResult = "";
        String result = WebRound.createIndividualRanking(r, coachs, rankName);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createIndividualRanking method, of class WebRound.
     */
    @Test
    public void testCreateIndividualRanking_Round() {
        System.out.println("createIndividualRanking");
        Round r = null;
        String expResult = "";
        String result = WebRound.createIndividualRanking(r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createIndividualCriteria method, of class WebRound.
     */
    @Test
    public void testCreateIndividualCriteria() {
        System.out.println("createIndividualCriteria");
        Round r = null;
        Criteria crit = null;
        String expResult = "";
        String result = WebRound.createIndividualCriteria(r, crit);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTeamMatchs method, of class WebRound.
     */
    @Test
    public void testCreateTeamMatchs() {
        System.out.println("createTeamMatchs");
        Round r = null;
        String expResult = "";
        String result = WebRound.createTeamMatchs(r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTeamRanking method, of class WebRound.
     */
    @Test
    public void testCreateTeamRanking_Round() {
        System.out.println("createTeamRanking");
        Round r = null;
        String expResult = "";
        String result = WebRound.createTeamRanking(r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTeamRanking method, of class WebRound.
     */
    @Test
    public void testCreateTeamRanking_3args() {
        System.out.println("createTeamRanking");
        Round r = null;
        ArrayList<Team> teams = null;
        String rankName = "";
        String expResult = "";
        String result = WebRound.createTeamRanking(r, teams, rankName);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTeamCriteria method, of class WebRound.
     */
    @Test
    public void testCreateTeamCriteria() {
        System.out.println("createTeamCriteria");
        Round r = null;
        Criteria crit = null;
        String expResult = "";
        String result = WebRound.createTeamCriteria(r, crit);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createClanRanking method, of class WebRound.
     */
    @Test
    public void testCreateClanRanking() {
        System.out.println("createClanRanking");
        Round r = null;
        String expResult = "";
        String result = WebRound.createClanRanking(r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createClanCriteria method, of class WebRound.
     */
    @Test
    public void testCreateClanCriteria() {
        System.out.println("createClanCriteria");
        Round r = null;
        Criteria crit = null;
        String expResult = "";
        String result = WebRound.createClanCriteria(r, crit);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createGroupRanking method, of class WebRound.
     */
    @Test
    public void testCreateGroupRanking() {
        System.out.println("createGroupRanking");
        Round r = null;
        Group g = null;
        String expResult = "";
        String result = WebRound.createGroupRanking(r, g);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCategoryRanking method, of class WebRound.
     */
    @Test
    public void testCreateCategoryRanking() {
        System.out.println("createCategoryRanking");
        Round r = null;
        Category cat = null;
        String expResult = "";
        String result = WebRound.createCategoryRanking(r, cat);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPoolRanking method, of class WebRound.
     */
    @Test
    public void testCreatePoolRanking() {
        System.out.println("createPoolRanking");
        Round r = null;
        Pool p = null;
        String expResult = "";
        String result = WebRound.createPoolRanking(r, p);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
