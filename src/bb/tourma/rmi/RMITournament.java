/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.rmi;

import java.rmi.RemoteException;
import java.util.HashMap;
import bb.tourma.data.ITournament;
import bb.tourma.data.RosterType;
import bb.tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class RMITournament implements ITournament {

    private static RMITournament mSingleton = null;

    public static RMITournament getInstance() {
        if (mSingleton == null) {
            mSingleton = new RMITournament();
        }
        return mSingleton;
    }

    @Override
    public Tournament getTournament() throws RemoteException {
        return Tournament.getTournament();
    }

    @Override
    public void setTournament(Tournament object) throws RemoteException {
        Tournament.push(object);
        object.resetUpdated();
    }

    @Override
    public HashMap<String,RosterType> getRosterTypes() throws RemoteException {
        return RosterType.getRosters();
    }

}
