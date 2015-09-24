/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;

import java.util.Timer;
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
public class TimerTaskSleepNGTest {

    public TimerTaskSleepNGTest() {
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
     * Test of setTimer method, of class TimerTaskSleep.
     */
    @Test
    public void testSetTimer() {
        System.out.println("setTimer");
        SuspendableImpl sus = new SuspendableImpl();
        sus.setSuspended(true);
        TimerTaskSleep task = new TimerTaskSleep(sus);
        Timer timer = new Timer();
        task.setTimer(timer);
        timer.schedule(task, 1000);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerTaskSleepNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertFalse(sus.getSuspended());

    }

    /**
     * Test of run method, of class TimerTaskSleep.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        SuspendableImpl sus = new SuspendableImpl();
        sus.setSuspended(true);
        TimerTaskSleep task = new TimerTaskSleep(sus);
        task.setTimer(new Timer());
        task.run();
        
        Assert.assertFalse(sus.getSuspended());
    }

    public class SuspendableImpl extends Thread implements Suspendable {

        private boolean stop = false;
        private boolean suspended = false;

        public void Stop() {
            stop = true;
        }

        public boolean getSuspended() {
            return suspended;
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
