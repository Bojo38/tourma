/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.image.BufferedImage;
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
public class IWithNameAndPictureNGTest {
    
    public IWithNameAndPictureNGTest() {
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
     * Test of getName method, of class IWithNameAndPicture.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        IWithNameAndPicture instance = new IWithNameAndPictureImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class IWithNameAndPicture.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        IWithNameAndPicture instance = new IWithNameAndPictureImpl();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPicture method, of class IWithNameAndPicture.
     */
    @Test
    public void testGetPicture() {
        System.out.println("getPicture");
        IWithNameAndPicture instance = new IWithNameAndPictureImpl();
        BufferedImage expResult = null;
        BufferedImage result = instance.getPicture();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPicture method, of class IWithNameAndPicture.
     */
    @Test
    public void testSetPicture() {
        System.out.println("setPicture");
        BufferedImage p = null;
        IWithNameAndPicture instance = new IWithNameAndPictureImpl();
        instance.setPicture(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IWithNameAndPictureImpl implements IWithNameAndPicture {

        public String getName() {
            return "";
        }

        public void setName(String name) {
        }

        public BufferedImage getPicture() {
            return null;
        }

        public void setPicture(BufferedImage p) {
        }
    }
    
}
