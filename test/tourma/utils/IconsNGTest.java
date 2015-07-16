/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import javax.swing.ImageIcon;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 *
 * @author WFMJ7631
 */
public class IconsNGTest {
    
    public IconsNGTest() {
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
     * Test of getDices method, of class Icons.
     */
    @Test
    public void testGetDices() {
        System.out.println("getDices");
        ImageIcon result = Icons.getDices();
        Assert.assertNotNull(result);
    }

    /**
     * Test of getParams method, of class Icons.
     */
    @Test
    public void testGetParams() {
        System.out.println("getParams");
        ImageIcon result = Icons.getParams();
        Assert.assertNotNull(result);
    }

    /**
     * Test of getStar method, of class Icons.
     */
    @Test
    public void testGetStar() {
        System.out.println("getStar");
        ImageIcon result = Icons.getStar();
        Assert.assertNotNull(result);
    }

    /**
     * Test of getStats method, of class Icons.
     */
    @Test
    public void testGetStats() {
        System.out.println("getStats");
        ImageIcon result = Icons.getStats();
        Assert.assertNotNull(result);
    }
    
}
