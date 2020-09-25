/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.fullscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.IWithNameAndPicture;
/**
 *
 * @author WFMJ7631
 */
public class JFullScreenNGTest {
    
    public JFullScreenNGTest() {
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
     * Test of getLabelForObject method, of class JFullScreen.
     */
    @Test(enabled=false)
    public void testGetLabelForObject() {
        System.out.println("getLabelForObject");
        IWithNameAndPicture object = null;
        int height = 0;
        int width = 0;
        Font f = null;
        Color bkg = null;
        JFullScreen instance = new JFullScreenImpl();
        JLabel expResult = null;
        JLabel result = instance.getLabelForObject(object, height, width, f, bkg);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGridbBagConstraints method, of class JFullScreen.
     */
    @Test(enabled=false)
    public void testGetGridbBagConstraints() {
        System.out.println("getGridbBagConstraints");
        int x = 0;
        int y = 0;
        int height = 0;
        int width = 0;
        JFullScreen instance = new JFullScreenImpl();
        GridBagConstraints expResult = null;
        GridBagConstraints result = instance.getGridbBagConstraints(x, y, height, width);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class JFullScreenImpl extends JFullScreen {


        @Override
        protected void setStop(boolean s) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        protected void clientLoop(int screen) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
     
    }

    /**
     * Test of clientLoop method, of class JFullScreen.
     */
    @Test(enabled=false)
    public void testClientLoop() throws Exception {
        System.out.println("clientLoop");
        JFullScreen instance = new JFullScreenImpl();
        instance.clientLoop(0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStop method, of class JFullScreen.
     */
    @Test(enabled=false)
    public void testSetStop() {
        System.out.println("setStop");
        boolean s = false;
        JFullScreen instance = new JFullScreenImpl();
        instance.setStop(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyPressed method, of class JFullScreen.
     */
    @Test(enabled=false)
    public void testKeyPressed() {
        System.out.println("keyPressed");
        KeyEvent evt = null;
        JFullScreen instance = new JFullScreenImpl();
        instance.keyPressed(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLabelForObject method, of class JFullScreen.
     */
    @Test
    public void testGetLabelForObject_5args() {
        System.out.println("getLabelForObject");
        IWithNameAndPicture object = null;
        int height = 0;
        int width = 0;
        Font f = null;
        Color bkg = null;
        JFullScreen instance = new JFullScreenImpl();
        JLabel expResult = null;
        JLabel result = instance.getLabelForObject(object, height, width, f, bkg);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLabelForObject method, of class JFullScreen.
     */
    @Test
    public void testGetLabelForObject_7args() {
        System.out.println("getLabelForObject");
        IWithNameAndPicture object = null;
        int height = 0;
        int width = 0;
        Font f = null;
        Color bkg = null;
        boolean right = false;
        int matchIndex = 0;
        JFullScreen instance = new JFullScreenImpl();
        JLabel expResult = null;
        JLabel result = instance.getLabelForObject(object, height, width, f, bkg, right, matchIndex);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
