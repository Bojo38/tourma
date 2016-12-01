/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;

import java.util.Timer;
import java.util.TimerTask;

class TimerTaskSleep extends TimerTask {

    Suspendable thread;
    Timer timer;

    public TimerTaskSleep(Suspendable t) {
        thread = t;
    }

    public void setTimer(Timer t) {
        timer = t;
    }

    public void run() {
        synchronized (thread) {
            thread.setSuspended(false);
            thread.notify();
            timer.cancel();
        }
    }
}

/**
 *
 * @author WFMJ7631
 */
public class Sleeping {

    Suspendable t;
    Timer timer;

    public Sleeping(Suspendable parent) {
        t = parent;
    }

    public void sleep(long millis, int nano) {

        /*Calendar cal = Calendar.getInstance();
         Date d=cal.getTime();
         d.*/
        long m;
        if (millis<=0)
        {
            m=1;
        }
        else
        {
            m=millis;
        }
        TimerTaskSleep task = new TimerTaskSleep(t);
        timer = new Timer();
        task.setTimer(timer);
        timer.schedule(task, m);
    }
}
