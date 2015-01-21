/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.views.report;

import java.io.File;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teamma.data.Roster;
import tourma.data.Coach;
/**
 *
 * @author WFMJ7631
 */
public class JdgPrintableRosterNGTest {
    
    public JdgPrintableRosterNGTest() {
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
     * Test of getmRoster method, of class JdgPrintableRoster.
     */
    @Test
    public void testGetmRoster() {
        System.out.println("getmRoster");
        JdgPrintableRoster instance = null;
        Roster expResult = null;
        Roster result = instance.getmRoster();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setmRoster method, of class JdgPrintableRoster.
     */
    @Test
    public void testSetmRoster() {
        System.out.println("setmRoster");
        Roster mRoster = null;
        JdgPrintableRoster instance = null;
        instance.setmRoster(mRoster);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getmCoach method, of class JdgPrintableRoster.
     */
    @Test
    public void testGetmCoach() {
        System.out.println("getmCoach");
        JdgPrintableRoster instance = null;
        Coach expResult = null;
        Coach result = instance.getmCoach();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setmCoach method, of class JdgPrintableRoster.
     */
    @Test
    public void testSetmCoach() {
        System.out.println("setmCoach");
        Coach mCoach = null;
        JdgPrintableRoster instance = null;
        instance.setmCoach(mCoach);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getmFilename method, of class JdgPrintableRoster.
     */
    @Test
    public void testGetmFilename() {
        System.out.println("getmFilename");
        JdgPrintableRoster instance = null;
        File expResult = null;
        File result = instance.getmFilename();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setmFilename method, of class JdgPrintableRoster.
     */
    @Test
    public void testSetmFilename() {
        System.out.println("setmFilename");
        File mFilename = null;
        JdgPrintableRoster instance = null;
        instance.setmFilename(mFilename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isWithSkill method, of class JdgPrintableRoster.
     */
    @Test
    public void testIsWithSkill() {
        System.out.println("isWithSkill");
        JdgPrintableRoster instance = null;
        boolean expResult = false;
        boolean result = instance.isWithSkill();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWithSkill method, of class JdgPrintableRoster.
     */
    @Test
    public void testSetWithSkill() {
        System.out.println("setWithSkill");
        boolean mWithSkill = false;
        JdgPrintableRoster instance = null;
        instance.setWithSkill(mWithSkill);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
