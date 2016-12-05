/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.image.BufferedImage;
import java.rmi.RemoteException;

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
    public BufferedImage getPicture()throws RemoteException;     

    /**
     *
     * @param p
     */
    public void setPicture(BufferedImage p)throws RemoteException;
}
