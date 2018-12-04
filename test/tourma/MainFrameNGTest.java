/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.beans.PropertyChangeEvent;
import java.io.File;
import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.FrameFixture;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
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
public class MainFrameNGTest {
    
    public MainFrameNGTest() {
    }

    private FrameFixture window;
    MainFrame frame;
    private static Robot robot;
    

    @BeforeClass
    public static void setUpClass() throws Exception {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(50);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        robot.cleanUp();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/team.xml"));
        this.frame = new MainFrame(0);
        window = new FrameFixture(robot,frame);
        window.show();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(enabled=false)
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }     

    /**
     * Test of update method, of class MainFrame.
     */
    @Test(enabled=false)
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
    @Test(enabled=false)
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
    @Test(enabled=false)
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
    @Test(enabled=false)
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
    @Test(enabled=false)
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
    @Test(enabled=false)
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
    @Test(enabled=false)
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
    @Test(enabled=false)
    public void testPropertyChange() {
        System.out.println("propertyChange");
        PropertyChangeEvent evt = null;
        MainFrame instance = null;
        instance.propertyChange(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPath method, of class MainFrame.
     */
    @Test
    public void testGetCurrentPath() {
        System.out.println("getCurrentPath");
        MainFrame instance = null;
        String expResult = "";
        String result = instance.getCurrentPath();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateMenus method, of class MainFrame.
     */
    @Test
    public void testUpdateMenus() {
        System.out.println("updateMenus");
        MainFrame instance = null;
        instance.updateMenus();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of splashProgress method, of class MainFrame.
     */
    @Test
    public void testSplashProgress() {
        System.out.println("splashProgress");
        int pct = 0;
        MainFrame.splashProgress(pct);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of splashText method, of class MainFrame.
     */
    @Test
    public void testSplashText() {
        System.out.println("splashText");
        String str = "";
        MainFrame.splashText(str);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
