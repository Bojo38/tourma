/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 *
 * @author WFMJ7631
 */
public class IContainCoachsNGTest {
    
    public IContainCoachsNGTest() {
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
     * Test of getCoach method, of class IContainCoachs.
     */
    @Test(enabled=false)
    public void testGetCoach() {
        System.out.println("getCoach");
        int i = 0;
        IContainCoachs instance = new IContainCoachsImpl();
        Coach expResult = null;
        Coach result = instance.getCoach(i);
        assertEquals(result, expResult);
    }

    /**
     * Test of getCoachCount method, of class IContainCoachs.
     */
    @Test(enabled=false)
    public void testgetCoachsCount() {
        System.out.println("getCoachCount");
        IContainCoachs instance = new IContainCoachsImpl();
        int expResult = 0;
        int result = instance.getCoachsCount();
        assertEquals(result, expResult);

    }

    /**
     * Test of containsCoach method, of class IContainCoachs.
     */
    @Test(enabled=false)
    public void testContainsCoach() {
        System.out.println("containsCoach");
        Coach c = null;
        IContainCoachs instance = new IContainCoachsImpl();
        boolean expResult = false;
        boolean result = instance.containsCoach(c);
        assertEquals(result, expResult);
    }

    /**
     * Test of addCoach method, of class IContainCoachs.
     */
    @Test(enabled=false)
    public void testAddCoach() {
        System.out.println("addCoach");
        Coach c = null;
        IContainCoachs instance = new IContainCoachsImpl();
        instance.addCoach(c);
    }

    /**
     * Test of removeCoach method, of class IContainCoachs.
     */
    @Test(enabled=false)
    public void testRemoveCoach() {
        System.out.println("removeCoach");
        int i = 0;
        IContainCoachs instance = new IContainCoachsImpl();
        instance.removeCoach(i);
    }

    /**
     * Test of clearCoachs method, of class IContainCoachs.
     */
    @Test(enabled=false)
    public void testClearCoachs() {
        System.out.println("clearCoachs");
        IContainCoachs instance = new IContainCoachsImpl();
        instance.clearCoachs();
    }

    public class IContainCoachsImpl implements IContainCoachs {

        public Coach getCoach(int i) {
            return null;
        }

        public int getCoachsCount() {
            return 0;
        }

        public boolean containsCoach(Coach c) {
            return false;
        }

        public void addCoach(Coach c) {
        }

        public void removeCoach(int i) {
        }

        public void clearCoachs() {
        }
    }

    /**
     * Test of getCoachsCount method, of class IContainCoachs.
     */
    @Test
    public void testGetCoachsCount() {
        System.out.println("getCoachsCount");
        IContainCoachs instance = new IContainCoachsImpl();
        int expResult = 0;
        int result = instance.getCoachsCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
