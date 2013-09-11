/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.HashMap;
import org.jdom2.Element;

/**
 * This class contains data relative to a clan
 *
 * @author Administrateur
 */
public class Clan implements Comparable, XMLExport {

    public static HashMap<String, Clan> sClanMap = new HashMap();
    /**
     * Name of the clan
     */
    public String mName;

    /**
     * Constructor by name
     *
     * @param name Name of the clan
     */
    public Clan(final String name) {
        mName = name;
    }

    public int compareTo(final Object obj) {
        int result=-1;
        if (obj instanceof Coach) {
            result=mName.compareTo(((Clan) obj).mName);
        } 
        return result;
    }

    public Element getXMLElement() {
        final Element clan = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN"));
        clan.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), mName);
        return clan;
    }

    public void setXMLElement(final Element e) {
        this.mName=e.getAttributeValue("Name");
        if (sClanMap!=null)
        {
            sClanMap.put(mName, this);
        }        
    }
    
    
}
