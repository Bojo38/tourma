/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.display;

import java.rmi.RemoteException;
import tourma.data.ObjectRanking;

/**
 *
 * @author WFMJ7631
 */
public interface IRanked {
    public int getRowCount() throws RemoteException;
    public ObjectRanking getSortedObject(int i)throws RemoteException;
    public int getSortedValue(int i,int valIndex)throws RemoteException;
    public String getDetail()throws RemoteException;
    public void setDetail(String s)throws RemoteException;
}
