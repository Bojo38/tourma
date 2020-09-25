/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Competitor;
import tourma.data.Match;
import tourma.data.Team;
import tourma.data.Tournament;
/**
 *
 * @author WFMJ7631
 */
public class BalancingNGTest {
    
    public BalancingNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
             Tournament.getTournament().loadXML(new File("./test/coach.xml"));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of balanceCoachMatchs method, of class Balancing.
     */
    @Test(enabled=false)
    public void testBalanceCoachMatchs() {
        System.out.println("balanceCoachMatchs");
        ArrayList<Match> matchs = null;
        try
        {
        Balancing.balanceCoachMatchs(matchs);
        }
        catch (RemoteException e)
        {
            
        }
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRoundValid method, of class Balancing.
     */
    @Test(enabled=false)
    public void testIsRoundValid() {
        System.out.println("isRoundValid");
        ArrayList<Match> matchs = null;
        HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC = null;
        HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT = null;
        boolean expResult = false;
        boolean result = Balancing.isRoundValid(matchs, evaluationPreviousC, evaluationPreviousT);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinimumFromHash method, of class Balancing.
     */
    @Test(enabled=false)
    public void testGetMinimumFromHash() {
        System.out.println("getMinimumFromHash");
        HashMap<Competitor, HashMap<Team, Integer>> hash = new HashMap<>();
        int expResult = 0;
        int result = Balancing.getMinimumFromHash(hash);
        assertEquals(result, expResult);

    }

    /**
     * Test of getMaximumFromHash method, of class Balancing.
     */
    @Test(enabled=false)
    public void testGetMaximumFromHash() {
        System.out.println("getMaximumFromHash");
        HashMap<Competitor, HashMap<Team, Integer>> hash = null;
        int expResult = 0;
        int result = Balancing.getMaximumFromHash(hash);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
