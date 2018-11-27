/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.rmi.RemoteException;
import javax.swing.ImageIcon;

/**
 *
 * @author WFMJ7631
 */
public interface IWithNameAndPicture {

    /**
     *
     * @return
     */
    public String getName()throws RemoteException;

    /**
     *
     * @param name
     */
    public void setName(String name)throws RemoteException;

    /**
     *
     * @return
     */
    public ImageIcon getPicture()throws RemoteException;     

    /**
     *
     * @param p
     */
    public void setPicture(ImageIcon p)throws RemoteException;
}
