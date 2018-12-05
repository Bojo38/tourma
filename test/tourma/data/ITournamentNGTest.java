/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.rmi.RemoteException;
import java.util.HashMap;
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
public class ITournamentNGTest {
    
    public ITournamentNGTest() {
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
     * Test of getTournament method, of class ITournament.
     */
    @Test
    public void testGetTournament() throws Exception {
        System.out.println("getTournament");
        ITournament instance = new ITournamentImpl();
        Tournament expResult = null;
        Tournament result = instance.getTournament();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosterTypes method, of class ITournament.
     */
    @Test
    public void testGetRosterTypes() throws Exception {
        System.out.println("getRosterTypes");
        ITournament instance = new ITournamentImpl();
        HashMap expResult = null;
        HashMap result = instance.getRosterTypes();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTournament method, of class ITournament.
     */
    @Test
    public void testSetTournament() throws Exception {
        System.out.println("setTournament");
        Tournament object = null;
        ITournament instance = new ITournamentImpl();
        instance.setTournament(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ITournamentImpl implements ITournament {

        public Tournament getTournament() throws RemoteException {
            return null;
        }

        public HashMap<String, RosterType> getRosterTypes() throws RemoteException {
            return null;
        }

        public void setTournament(Tournament object) throws RemoteException {
        }
    }
    
}
