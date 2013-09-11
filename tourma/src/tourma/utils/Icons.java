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
public class Icons {
    protected static ImageIcon sDICES=null;
    protected static ImageIcon sPARAMS=null;
    protected static ImageIcon sSTAR=null;
    protected static ImageIcon sSTAT=null;
    
    public static ImageIcon getDices()
    {
        if (sDICES==null)
        {
            sDICES=new javax.swing.ImageIcon(Icons.class.getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/DICE.PNG")));
        }
        return sDICES;
    }
    
     public static ImageIcon getParams()
    {
        if (sPARAMS==null)
        {
            sPARAMS=new javax.swing.ImageIcon(Icons.class.getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/TOOLS.PNG")));
        }
        return sPARAMS;
    }
     
      public static ImageIcon getStar()
    {
        if (sSTAR==null)
        {
            sSTAR=new javax.swing.ImageIcon(Icons.class.getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/STAR.PNG")));
        }
        return sSTAR;
    }
      
       public static ImageIcon getStats()
    {
        if (sSTAT==null)
        {
            sSTAT=new javax.swing.ImageIcon(Icons.class.getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/STATIS.PNG")));
        }
        return sSTAT;
    }
}
