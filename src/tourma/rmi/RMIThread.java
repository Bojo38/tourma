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
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.ITournament;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.Team;
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
                
                RosterType.pull(r.getRosterTypes());
                
                Tournament.pull(r.getTournament());
            }
            Tournament tour=Tournament.getTournament();
            tour.resetUpdated();
            
            while (!mStop)
            {
                
                
                
                // check what was updated
                for (int i=0; i<tour.getClansCount(); i++)
                {
                    Clan c=tour.getClan(i);
                    if (c.isUpdated())
                    {
                        tour.setClansUpdated(true);
                        break;
                    }
                }
                
                for (int i=0; i<tour.getCoachsCount(); i++)
                {
                    Coach c=tour.getCoach(i);
                    if (c.isUpdated())
                    {
                        tour.setCoachsUpdated(true);
                        break;
                    }
                }
                
                for (int i=0; i<tour.getTeamsCount(); i++)
                {
                    Team c=tour.getTeam(i);
                    if (c.isUpdated())
                    {
                        tour.setTeamsUpdated(true);
                        break;
                    }
                }
                
                for (int i=0; i<tour.getRoundsCount(); i++)
                {
                    Round c=tour.getRound(i);
                    if (c.isUpdated())
                    {
                        tour.setRoundsUpdated(true);
                        break;
                    }
                }
                
                r.setTournament(tour);
                
                tour.pull(r.getTournament());
                tour.resetUpdated();
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
