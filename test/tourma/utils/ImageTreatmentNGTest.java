/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import javax.swing.ImageIcon;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 *
 * @author WFMJ7631
 */
public class ImageTreatmentNGTest {
    
    public ImageTreatmentNGTest() {
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
     * Test of resize method, of class ImageTreatment.
     */
    @Test
    public void testResize() {
        System.out.println("resize");
        ImageIcon image = Icons.getParams();
        int heigth = image.getIconHeight()/2;
        int width = image.getIconWidth()/2;
        ImageIcon result = ImageTreatment.resize(image, heigth, width);
        assertEquals(result.getIconHeight(), image.getIconHeight()/2);
        assertEquals(result.getIconWidth(), image.getIconWidth()/2);
    }
    
}
