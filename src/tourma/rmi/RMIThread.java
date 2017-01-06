/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import tourma.data.ITournament;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class RMIThread implements Runnable {

    String mHostAddress;
    static boolean mStop=false;
    int period = 30; // 30 seconds

    public static void stop()
    {
        mStop=true;
    }
    
    public RMIThread(String address) {
        mHostAddress = address;
    }

    @Override
    public void run() {
        try {
            //System.setProperty("java.rmi.sewrver.hostname", address);
            Registry registry = LocateRegistry.getRegistry(mHostAddress);
            ITournament r = (ITournament) registry.lookup("TourMa");
            //Remote r = Naming.lookup("rmi://" + address + "/TourMa");
            if (r instanceof ITournament) {
                Tournament.pull(r.getTournament());
            }

            
            while (!mStop)
            {
                Tournament.pull(r.getTournament());
                r.setTournament(Tournament.getTournament());
                try {
                    Thread.sleep(period*1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RMIThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // Start Synchronization thread
        } catch (RemoteException | NotBoundException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

}
