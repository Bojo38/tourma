/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;
import org.jdom2.Element;
import tourma.utility.StringConstants;

/**
 * This class contains data relative to a clan
 *
 * @author Administrateur
 */
public class Category implements Comparable, XMLExport {

    /**
     *
     */
    private static HashMap<String, Category> sCategoryMap = new HashMap<>();
    
    private static final Logger LOG = Logger.getLogger(Category.class.getName());

    /**
     * @param s
     * @param c
     */
    /*public static HashMap<String, Category> getsCategoryMap() {
        return sCategoryMap;
    }*/

    public static void putCategory(String s, Category c)
    {
        sCategoryMap.put(s, c);
    }
    
    /**
     * Create a new Category Map
     */
    public static void newCategoryMap(){
        sCategoryMap=new HashMap<>();                
    }
    /**
     * 
     * @param s
     * @return 
     */
    public static Category getCategory(String s)
    {
        return sCategoryMap.get(s);
    }
    
    /**
     * @param asCategoryMap the sCategoryMap to set
     */
    /*public static void setsCategoryMap(HashMap<String, Category> asCategoryMap) {
        sCategoryMap = asCategoryMap;
    }*/
    /**
     * Name of the clan
     */
    private String mName;

    /**
     * Constructor by name
     *
     * @param name Name of the clan
     */
    public Category(final String name) {
        mName = name;
    }

    
    @Override
    public boolean equals(final Object obj) {
        
        boolean result;
        result = false;
        if (obj instanceof Category) {
            Category cat=(Category) obj;
            result=this.getName().equals(cat.getName());
        } 
        return result;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.mName);
        return hash;
    }
    
    @Override
    public int compareTo(final Object obj) {
        int result;
        result = this.getName().compareTo("");
        if (obj instanceof Category) {
            Category cat=(Category) obj;
            result=this.getName().compareTo(cat.getName());
        } 
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element clan = new Element(StringConstants.CS_CATEGORY);
        clan.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), getName());
        return clan;
    }

    /**
     * 
     * @return 
     */
    public boolean isCategoryMapNull()
    {
        return sCategoryMap==null;
    }
    
    
    
    /**
     *
     * @param e
     */
    @Override
    public void setXMLElement(final Element e) {
        this.setmName(e.getAttributeValue("Name"));
        if (!isCategoryMapNull())
        {
            putCategory(getName(), this);
        }        
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setmName(String mName) {
        this.mName = mName;
    }
    
    
}