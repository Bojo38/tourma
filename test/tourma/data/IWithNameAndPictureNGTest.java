/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;
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
    @Test(enabled=false)
    public void testGetName() throws RemoteException {
        System.out.println("getName");
        IWithNameAndPicture instance = new IWithNameAndPictureImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
    }

    /**
     * Test of setName method, of class IWithNameAndPicture.
     */
    @Test(enabled=false)
    public void testSetName() throws RemoteException {
        System.out.println("setName");
        String name = "";
        IWithNameAndPicture instance = new IWithNameAndPictureImpl();
        instance.setName(name);
    }

    /**
     * Test of getPicture method, of class IWithNameAndPicture.
     */
    @Test(enabled=false)
    public void testGetPicture() throws RemoteException {
        System.out.println("getPicture");
        IWithNameAndPicture instance = new IWithNameAndPictureImpl();
        BufferedImage expResult = null;
        ImageIcon result = instance.getPicture();
        assertEquals(result, expResult);
    }

    /**
     * Test of setPicture method, of class IWithNameAndPicture.
     */
    @Test(enabled=false)
    public void testSetPicture() throws RemoteException {
        System.out.println("setPicture");
        ImageIcon p = null;
        IWithNameAndPicture instance = new IWithNameAndPictureImpl();
        instance.setPicture(p);
    }

    public class IWithNameAndPictureImpl implements IWithNameAndPicture {

        public String getName() {
            return "";
        }

        public void setName(String name) {
        }

        public ImageIcon getPicture() {
            return null;
        }

        public void setPicture(BufferedImage p) {
        }

        @Override
        public void setPicture(ImageIcon p) throws RemoteException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
