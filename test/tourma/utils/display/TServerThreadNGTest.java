/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.display;

import java.net.Socket;
import tourma.utils.display.TServerThread;
import static org.testng.Assert.fail;
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
public class TServerThreadNGTest {
    
    public TServerThreadNGTest() {
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
     * Test of run method, of class TServerThread.
     */
    @Test(enabled=false)
    public void testRun() {
        System.out.println("run");
        TServerThread instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSocket method, of class TServerThread.
     */
    @Test
    public void testGetSocket() {
        System.out.println("getSocket");
        TServerThread instance = null;
        Socket expResult = null;
        Socket result = instance.getSocket();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
