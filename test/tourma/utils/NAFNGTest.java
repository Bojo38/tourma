/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import static org.testng.Assert.assertEquals;
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
    
}
