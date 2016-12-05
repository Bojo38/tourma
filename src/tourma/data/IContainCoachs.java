/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.rmi.RemoteException;

/**
 *
 * @author WFMJ7631
 */
public interface IContainCoachs {
    
    /**
     * 
     * @param i
     * @return 
     */
     public Coach getCoach(int i) throws RemoteException;
    
     /**
     * @return the mCoachs
     */
    public int getCoachsCount() throws RemoteException;
    
    
    /**
     * 
     * @param c
     * @return 
     */
    public boolean containsCoach(Coach c) throws RemoteException;
    
    /**
     * 
     * @param c 
     */
    public void addCoach(Coach c) throws RemoteException;
    
    /**
     * 
     * @param i
     */
    public void removeCoach(int i) throws RemoteException;
    
    /**
     * Clear the Coach list
     */
   public void clearCoachs() throws RemoteException;
}
