/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.HashMap;
import org.jdom2.Element;
import tourma.utility.StringConstants;

/**
 * This class contains data relative to a clan
 *
 * @author Administrateur
 */
public class Category implements Comparable, XMLExport {

    public static HashMap<String, Category> sCategoryMap = new HashMap();
    /**
     * Name of the clan
     */
    public String mName;

    /**
     * Constructor by name
     *
     * @param name Name of the clan
     */
    public Category(final String name) {
        mName = name;
    }

    @Override
    public int compareTo(final Object obj) {
        int result=-1;
        if (obj instanceof Coach) {
            result=mName.compareTo(((Category) obj).mName);
        } 
        return result;
    }

    @Override
    public Element getXMLElement() {
        final Element clan = new Element(StringConstants.CS_CATEGORY);
        clan.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), mName);
        return clan;
    }

    @Override
    public void setXMLElement(final Element e) {
        this.mName=e.getAttributeValue("Name");
        if (sCategoryMap!=null)
        {
            sCategoryMap.put(mName, this);
        }        
    }
    
    
}
