/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.beans.PropertyChangeEvent;
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
public class MainFrameNGTest {
    
    public MainFrameNGTest() {
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
     * Test of update method, of class MainFrame.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        MainFrame instance = null;
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTree method, of class MainFrame.
     */
    @Test
    public void testUpdateTree() {
        System.out.println("updateTree");
        MainFrame instance = null;
        instance.updateTree();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class MainFrame.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        MainFrame.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMainFrame method, of class MainFrame.
     */
    @Test
    public void testGetMainFrame_0args() {
        System.out.println("getMainFrame");
        MainFrame expResult = null;
        MainFrame result = MainFrame.getMainFrame();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMainFrame method, of class MainFrame.
     */
    @Test
    public void testGetMainFrame_int() {
        System.out.println("getMainFrame");
        int res = 0;
        MainFrame expResult = null;
        MainFrame result = MainFrame.getMainFrame(res);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRoundOnly method, of class MainFrame.
     */
    @Test
    public void testIsRoundOnly() {
        System.out.println("isRoundOnly");
        MainFrame instance = null;
        boolean expResult = false;
        boolean result = instance.isRoundOnly();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNafOnly method, of class MainFrame.
     */
    @Test
    public void testIsNafOnly() {
        System.out.println("isNafOnly");
        MainFrame instance = null;
        boolean expResult = false;
        boolean result = instance.isNafOnly();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of propertyChange method, of class MainFrame.
     */
    @Test
    public void testPropertyChange() {
        System.out.println("propertyChange");
        PropertyChangeEvent evt = null;
        MainFrame instance = null;
        instance.propertyChange(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
