/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Coach;
import tourma.data.RosterType;
/**
 *
 * @author WFMJ7631
 */
public class NAFNGTest {
    
    public NAFNGTest() {
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
     * Test of getRanking method, of class NAF.
     */
    @Test
    public void testGetRanking() {
        System.out.println("getRanking");
        String Name = "lord_bojo";
        Coach coach = new Coach();
        coach.setRoster(new RosterType("Chaos Pact"));
        double expResult = 147.0;
        double result = NAF.getRanking(Name, coach);
        assertEquals(result, expResult, 0.0);
    }

    /**
     * Test of getCoachs method, of class NAF.
     */
    @Test
    public void testGetCoachs() {
        System.out.println("getCoachs");
        ArrayList expResult = null;
        ArrayList result = NAF.getCoachs();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoachByName method, of class NAF.
     */
    @Test
    public void testGetCoachByName() {
        System.out.println("getCoachByName");
        String name = "";
        NAFCoach expResult = null;
        NAFCoach result = NAF.getCoachByName(name);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoachById method, of class NAF.
     */
    @Test
    public void testGetCoachById() {
        System.out.println("getCoachById");
        int id = 0;
        NAFCoach expResult = null;
        NAFCoach result = NAF.getCoachById(id);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileList method, of class NAF.
     */
    @Test
    public void testGetFileList() {
        System.out.println("getFileList");
        ArrayList expResult = null;
        ArrayList result = NAF.getFileList();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initCoachs method, of class NAF.
     */
    @Test
    public void testInitCoachs() {
        System.out.println("initCoachs");
        File file = null;
        NAF.initCoachs(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRanking method, of class NAF.
     */
    @Test
    public void testGetRanking_int_Coach() {
        System.out.println("getRanking");
        int id = 0;
        Coach coach = null;
        double expResult = 0.0;
        double result = NAF.getRanking(id, coach);
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRanking method, of class NAF.
     */
    @Test
    public void testGetRanking_String_Coach() {
        System.out.println("getRanking");
        String Name = "";
        Coach coach = null;
        double expResult = 0.0;
        double result = NAF.getRanking(Name, coach);
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRanking method, of class NAF.
     */
    @Test
    public void testGetRanking_URL_Coach() {
        System.out.println("getRanking");
        URL url = null;
        Coach coach = null;
        double expResult = 0.0;
        double result = NAF.getRanking(url, coach);
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCoachID method, of class NAF.
     */
    @Test
    public void testUpdateCoachID() {
        System.out.println("updateCoachID");
        Coach coach = null;
        NAF.updateCoachID(coach);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameProposals method, of class NAF.
     */
    @Test
    public void testGetNameProposals() {
        System.out.println("getNameProposals");
        String name = "";
        ArrayList expResult = null;
        ArrayList result = NAF.getNameProposals(name);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistance method, of class NAF.
     */
    @Test
    public void testGetDistance() {
        System.out.println("getDistance");
        String target = "";
        String other = "";
        float expResult = 0.0F;
        float result = NAF.getDistance(target, other);
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIgnoreCaps method, of class NAF.
     */
    @Test
    public void testSetIgnoreCaps() {
        System.out.println("setIgnoreCaps");
        boolean b = false;
        NAF.setIgnoreCaps(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
