/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.fullscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
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
    @Test
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
    @Test
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
    }
    
}