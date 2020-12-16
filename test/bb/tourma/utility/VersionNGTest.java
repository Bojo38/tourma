/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.utility;

import bb.tourma.utility.Version;
import org.testng.Assert;
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
public class VersionNGTest {
    
    public VersionNGTest() {
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
     * Test of getSingleton method, of class Version.
     */
    @Test
    public void testGetSingleton() {
        System.out.println("getSingleton");
        Version result = Version.getSingleton();
        Assert.assertNotNull(result);
    }

    /**
     * Test of getProperty method, of class Version.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        String key = "name";
        Version instance = Version.getSingleton();
        String expResult = "TourMa";
        String result = instance.getProperty(key);
        assertEquals(result, expResult);
    }
    
}