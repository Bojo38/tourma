/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.image.BufferedImage;

/**
 *
 * @author WFMJ7631
 */
public interface IWithNameAndPicture {

    /**
     *
     * @return
     */
    public String getName();

    /**
     *
     * @param name
     */
    public void setName(String name);

    /**
     *
     * @return
     */
    public BufferedImage getPicture();     

    /**
     *
     * @param p
     */
    public void setPicture(BufferedImage p);
}
