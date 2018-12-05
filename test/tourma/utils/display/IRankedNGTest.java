/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.display;

import java.rmi.RemoteException;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.ObjectRanking;

/**
 *
 * @author WFMJ7631
 */
public class IRankedNGTest {
    
    public IRankedNGTest() {
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
    @Test
    public void testGetRowCount() throws Exception {
        System.out.println("getRowCount");
        IRanked instance = new IRankedImpl();
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSortedObject method, of class IRanked.
     */
    @Test
    public void testGetSortedObject() throws Exception {
        System.out.println("getSortedObject");
        int i = 0;
        IRanked instance = new IRankedImpl();
        ObjectRanking expResult = null;
        ObjectRanking result = instance.getSortedObject(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSortedValue method, of class IRanked.
     */
    @Test
    public void testGetSortedValue() throws Exception {
        System.out.println("getSortedValue");
        int i = 0;
        int valIndex = 0;
        IRanked instance = new IRankedImpl();
        int expResult = 0;
        int result = instance.getSortedValue(i, valIndex);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetail method, of class IRanked.
     */
    @Test
    public void testGetDetail() throws Exception {
        System.out.println("getDetail");
        IRanked instance = new IRankedImpl();
        String expResult = "";
        String result = instance.getDetail();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDetail method, of class IRanked.
     */
    @Test
    public void testSetDetail() throws Exception {
        System.out.println("setDetail");
        String s = "";
        IRanked instance = new IRankedImpl();
        instance.setDetail(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IRankedImpl implements IRanked {

        public int getRowCount() throws RemoteException {
            return 0;
        }

        public ObjectRanking getSortedObject(int i) throws RemoteException {
            return null;
        }

        public int getSortedValue(int i, int valIndex) throws RemoteException {
            return 0;
        }

        public String getDetail() throws RemoteException {
            return "";
        }

        public void setDetail(String s) throws RemoteException {
        }
    }
    
}
