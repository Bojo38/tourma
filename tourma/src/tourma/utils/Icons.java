/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author WFMJ7631
 */
public class Icons {

    /**
     *
     */
    protected static ImageIcon sDICES=null;

    /**
     *
     */
    protected static ImageIcon sPARAMS=null;

    /**
     *
     */
    protected static ImageIcon sSTAR=null;

    /**
     *
     */
    protected static ImageIcon sSTAT=null;
    
    /**
     *
     */
    protected static final ResourceBundle bundle=ResourceBundle.getBundle("tourma/languages/language");
    private static final Logger LOG = Logger.getLogger(Icons.class.getName());
    
    /**
     *
     * @return
     */
    public static ImageIcon getDices()
    {
        if (sDICES==null)
        {
            sDICES=new javax.swing.ImageIcon(Icons.class.getResource(bundle.getString("/TOURMA/IMAGES/DICE.PNG")));
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
            sPARAMS=new javax.swing.ImageIcon(Icons.class.getResource(bundle.getString("/TOURMA/IMAGES/TOOLS.PNG")));
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
            sSTAR=new javax.swing.ImageIcon(Icons.class.getResource(bundle.getString("/TOURMA/IMAGES/STAR.PNG")));
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
            sSTAT=new javax.swing.ImageIcon(Icons.class.getResource(bundle.getString("/TOURMA/IMAGES/STATIS.PNG")));
        }
        return sSTAT;
    }
}
