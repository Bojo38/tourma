/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author WFMJ7631
 */
public interface ITournament extends Remote {
        
    public Tournament getTournament() throws RemoteException ;
    public HashMap<String,RosterType> getRosterTypes() throws RemoteException ;
    public void setTournament(Tournament object) throws RemoteException ;
}
