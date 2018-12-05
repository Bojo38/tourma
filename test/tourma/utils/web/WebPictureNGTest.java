/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
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
public class WebPictureNGTest {
    
    public WebPictureNGTest() {
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
     * Test of getPictureAsHTML method, of class WebPicture.
     */
    @Test
    public void testGetPictureAsHTML_3args_1() {
        System.out.println("getPictureAsHTML");
        ImageIcon pic = null;
        int width = 0;
        int heigth = 0;
        String expResult = "";
        String result = WebPicture.getPictureAsHTML(pic, width, heigth);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPictureAsHTML method, of class WebPicture.
     */
    @Test
    public void testGetPictureAsHTML_3args_2() {
        System.out.println("getPictureAsHTML");
        BufferedImage pic = null;
        int width = 0;
        int heigth = 0;
        String expResult = "";
        String result = WebPicture.getPictureAsHTML(pic, width, heigth);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPictureAsHTML method, of class WebPicture.
     */
    @Test
    public void testGetPictureAsHTML_4args() {
        System.out.println("getPictureAsHTML");
        BufferedImage pic = null;
        int width = 0;
        int heigth = 0;
        boolean use_image = false;
        String expResult = "";
        String result = WebPicture.getPictureAsHTML(pic, width, heigth, use_image);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
