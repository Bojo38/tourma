/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.rmi;

import java.rmi.RemoteException;
import tourma.data.ITournament;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class RMITournament implements ITournament{

    private static RMITournament mSingleton=null;
    
    public static RMITournament getInstance()
    {
        if (mSingleton==null)
        {
            mSingleton=new RMITournament();
        }
        return mSingleton;
    }
    
    @Override
    public Tournament getTournament() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTournament(Tournament object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    }
