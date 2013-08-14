/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.images;

import javax.swing.ImageIcon;

/**
 *
 * @author WFMJ7631
 */
public class Icons {
    protected static ImageIcon sDICES=null;
    protected static ImageIcon sPARAMS=null;
    protected static ImageIcon sSTAR=null;
    protected static ImageIcon sSTAT=null;
    
    public static ImageIcon getDices()
    {
        if (sDICES==null)
        {
            sDICES=new javax.swing.ImageIcon(Icons.class.getResource("/tourma/images/Dice.png"));
        }
        return sDICES;
    }
    
     public static ImageIcon getParams()
    {
        if (sPARAMS==null)
        {
            sPARAMS=new javax.swing.ImageIcon(Icons.class.getResource("/tourma/images/Tools.png"));
        }
        return sPARAMS;
    }
     
      public static ImageIcon getStar()
    {
        if (sSTAR==null)
        {
            sSTAR=new javax.swing.ImageIcon(Icons.class.getResource("/tourma/images/Star.png"));
        }
        return sSTAR;
    }
      
       public static ImageIcon getStats()
    {
        if (sSTAT==null)
        {
            sSTAT=new javax.swing.ImageIcon(Icons.class.getResource("/tourma/images/Statis.png"));
        }
        return sSTAT;
    }
}
