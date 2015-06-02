/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import javax.swing.ImageIcon;

/**
 *
 * @author WFMJ7631
 */
public final class Icons {

    /**
     *
     */
    private static ImageIcon sDICES=null;

    /**
     *
     */
    private static ImageIcon sPARAMS=null;

    /**
     *
     */
    private static ImageIcon sSTAR=null;

    /**
     *
     */
    private static ImageIcon sSTAT=null;
    
    /**
     *
     */
    
    /**
     *
     * @return
     */
    public static ImageIcon getDices()
    {
        if (sDICES==null)
        {
            sDICES=new javax.swing.ImageIcon(Icons.class.getResource("/tourma/images/Dice.png"));
        }
        return sDICES;
    }
    
    /**
     *
     * @return
     */
    public static ImageIcon getParams()
    {
        if (sPARAMS==null)
        {
            sPARAMS=new javax.swing.ImageIcon(Icons.class.getResource("/tourma/images/Tools.png"));
        }
        return sPARAMS;
    }
     
    /**
     *
     * @return
     */
    public static ImageIcon getStar()
    {
        if (sSTAR==null)
        {
            sSTAR=new javax.swing.ImageIcon(Icons.class.getResource("/tourma/images/Star.png"));
        }
        return sSTAR;
    }
      
    /**
     *
     * @return
     */
    public static ImageIcon getStats()
    {
        if (sSTAT==null)
        {
            sSTAT=new javax.swing.ImageIcon(Icons.class.getResource("/tourma/images/Statis.png"));
        }
        return sSTAT;
    }

    private Icons() {
    }
}
