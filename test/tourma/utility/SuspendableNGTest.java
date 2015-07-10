/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SuspendableNGTest {
    
    public SuspendableNGTest() {
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
     * Test of setSuspended method, of class Suspendable.
     */
    @Test
    public void testSetSuspended() {
        System.out.println("setSuspended");
        SuspendableImpl sus = new SuspendableImpl();
        try {
            System.out.println("sleep");
            Date d = new Date();            
            sus.start();
            sus.join(2000);            
            Date d2 = new Date();
            long nb = d.getTime();
            long nb2 = d2.getTime();
            Assert.assertTrue(nb2 - nb > 1000);
        } catch (InterruptedException ex) {
            sus.notify();
            Logger.getLogger(SleepingNGTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail("Exception");
        }
    }

     public class SuspendableImpl extends Thread implements Suspendable {

        private boolean stop = false;
        private boolean suspended = false;

        public void Stop() {
            stop = true;
        }

        public void setSuspended(boolean s) {
            suspended = s;
        }

        public void run() {
            Sleeping sleeping = new Sleeping(this);

            sleeping.sleep(1000, 0);
            synchronized (this) {
                suspended = true;
                while (suspended) {
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                    }
                }
            }

        }
    }
    
}
