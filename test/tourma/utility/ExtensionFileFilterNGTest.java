/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;

import java.io.File;
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
public class ExtensionFileFilterNGTest {
    
    public ExtensionFileFilterNGTest() {
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
     * Test of getDescription method, of class ExtensionFileFilter.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        ExtensionFileFilter instance = new ExtensionFileFilter("Description", "extension");
        String expResult = "Description";
        String result = instance.getDescription();
        assertEquals(result, expResult);
    }

    /**
     * Test of accept method, of class ExtensionFileFilter.
     */
    @Test
    public void testAccept() {
        System.out.println("accept");
        File file = null;
        ExtensionFileFilter instance = null;
        boolean expResult = false;
        boolean result = instance.accept(file);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class ExtensionFileFilter.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        ExtensionFileFilter instance = new ExtensionFileFilter("Description", "extension");
        String expResult = "Description2";
        instance.setDescription(expResult);
        String result = instance.getDescription();
        assertEquals(result, expResult);
    }
    
}
