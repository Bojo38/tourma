/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.util.ArrayList;
import static org.testng.Assert.assertEquals;

import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Coach;
import tourma.data.Round;
import tourma.data.Team;
/**
 *
 * @author WFMJ7631
 */
public class GenerationNGTest {
    
    public GenerationNGTest() {
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
     * Test of nextRound method, of class Generation.
     */
    @Test(enabled=false)
    public void testNextRound() {
        System.out.println("nextRound");
        Round round = null;
        int choice = 0;
        int roundnumber = 0;
        Generation.nextRound(round, choice, roundnumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateFirstRoundFree method, of class Generation.
     */
    @Test(enabled=false)
    public void testGenerateFirstRoundFree() {
        System.out.println("generateFirstRoundFree");
        Generation.generateFirstRoundFree();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateFirstRound method, of class Generation.
     */
    @Test(enabled=false)
    public void testGenerateFirstRound() {
        System.out.println("generateFirstRound");
        int choice = 0;
        Generation.generateFirstRound(choice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of subRanking method, of class Generation.
     */
    @Test(enabled=false)
    public void testSubRanking_Team_ArrayList() {
        System.out.println("subRanking");
        Team team = null;
        ArrayList<Round> rounds = null;
        ArrayList expResult = null;
        ArrayList result = Generation.subRanking(team, rounds,true);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of subRanking method, of class Generation.
     */
    @Test(enabled=false)
    public void testSubRanking_ArrayList_ArrayList() {
        System.out.println("subRanking");
        ArrayList<Coach> coachs = null;
        ArrayList<Round> rounds = null;
        ArrayList expResult = null;
        ArrayList result = Generation.subRanking(coachs, rounds,true);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of subRanking method, of class Generation.
     */
    @Test
    public void testSubRanking_3args_1() {
        System.out.println("subRanking");
        Team team = null;
        ArrayList<Round> rounds = null;
        boolean sum = false;
        ArrayList expResult = null;
        ArrayList result = Generation.subRanking(team, rounds, sum);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of subRanking method, of class Generation.
     */
    @Test
    public void testSubRanking_3args_2() {
        System.out.println("subRanking");
        ArrayList<Coach> coachs = null;
        ArrayList<Round> rounds = null;
        boolean sum = false;
        ArrayList expResult = null;
        ArrayList result = Generation.subRanking(coachs, rounds, sum);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
