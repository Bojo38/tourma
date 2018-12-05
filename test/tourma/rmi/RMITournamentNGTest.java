/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.rmi;

import java.util.HashMap;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class RMITournamentNGTest {
    
    public RMITournamentNGTest() {
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
     * Test of getInstance method, of class RMITournament.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        RMITournament expResult = null;
        RMITournament result = RMITournament.getInstance();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTournament method, of class RMITournament.
     */
    @Test
    public void testGetTournament() throws Exception {
        System.out.println("getTournament");
        RMITournament instance = new RMITournament();
        Tournament expResult = null;
        Tournament result = instance.getTournament();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTournament method, of class RMITournament.
     */
    @Test
    public void testSetTournament() throws Exception {
        System.out.println("setTournament");
        Tournament object = null;
        RMITournament instance = new RMITournament();
        instance.setTournament(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosterTypes method, of class RMITournament.
     */
    @Test
    public void testGetRosterTypes() throws Exception {
        System.out.println("getRosterTypes");
        RMITournament instance = new RMITournament();
        HashMap expResult = null;
        HashMap result = instance.getRosterTypes();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
