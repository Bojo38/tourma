/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.display;

import java.rmi.RemoteException;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.ObjectRanking;
import tourma.utils.display.IRanked;

/**
 *
 * @author WFMJ7631
 */
public class RankedNGTest {

    public RankedNGTest() {
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
     * Test of getRowCount method, of class IRanked.
     */
    @Test(enabled = false)
    public void testGetRowCount() {
        System.out.println("getRowCount");
        IRanked instance = new RankedImpl();
        int expResult = 0;
        try {
            int result = instance.getRowCount();
            assertEquals(result, expResult);
        } catch (RemoteException e) {

        }
    }

    /**
     * Test of getSortedObject method, of class IRanked.
     */
    @Test(enabled = false)
    public void testGetSortedObject() {
        System.out.println("getSortedObject");
        int i = 0;
        IRanked instance = new RankedImpl();
        ObjectRanking expResult = null;
        try {
            ObjectRanking result = instance.getSortedObject(i);
            //assertEquals(result, expResult);
            // TODO review the generated test code and remove the default call to fail.
        } catch (RemoteException e) {

        }
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSortedValue method, of class IRanked.
     */
    @Test(enabled = false)
    public void testGetSortedValue() {
        System.out.println("getSortedValue");
        int i = 0;
        int valIndex = 0;
        IRanked instance = new RankedImpl();
        int expResult = 0;
        try {
            int result = instance.getSortedValue(i, valIndex);
            //assertEquals(result, expResult);
            // TODO review the generated test code and remove the default call to fail.
        } catch (RemoteException e) {

        }
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetail method, of class IRanked.
     */
    @Test(enabled = false)
    public void testGetDetail() {
        System.out.println("getDetail");
        IRanked instance = new RankedImpl();
        String expResult = "";
        try {
            String result = instance.getDetail();
            //assertEquals(result, expResult);
            // TODO review the generated test code and remove the default call to fail.
        } catch (RemoteException e) {

        }
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDetail method, of class IRanked.
     */
    @Test(enabled = false)
    public void testSetDetail() {
        System.out.println("setDetail");
        String s = "";
        IRanked instance = new RankedImpl();
        try {
            instance.setDetail(s);
            // TODO review the generated test code and remove the default call to fail.
        } catch (RemoteException e) {

        }
        fail("The test case is a prototype.");
    }

    public class RankedImpl implements IRanked {

        public int getRowCount() {
            return 0;
        }

        public ObjectRanking getSortedObject(int i) {
            return null;
        }

        public int getSortedValue(int i, int valIndex) {
            return 0;
        }

        public String getDetail() {
            return "";
        }

        public void setDetail(String s) {
        }
    }

}
