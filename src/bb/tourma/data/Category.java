/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import org.jdom.Element;
import bb.tourma.utility.StringConstants;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class contains data relative to a clan
 *
 * @author Administrateur
 */
public class Category implements Comparable<Object>, IXMLExport, Serializable {

    protected static AtomicInteger sGenUID=new AtomicInteger(0);
    protected int UID=sGenUID.incrementAndGet();

    /**
     * Unique ID getter
     * @return UID
     */
    public int getUID() {
        return UID;
    }

    /**
     * Unique ID Setter
     * @param UID Unique ID
     */
    public void setUID(int UID) {
        this.UID = UID;
    }
    
     protected LocalDateTime createDateTime;

    protected LocalDateTime updateDateTime;

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
    
    
    /**
     * Category map indexed by category name
     */
    private static HashMap<String, Category> sCategoryMap = new HashMap<>();
    

    /**
     * Category Puller
     * @param cat Category to pull
     */
    public void pull(Category cat)
    {
        this.UID=cat.UID;
        this.mName=cat.mName;
    }
    
    /**
     * Category Map Putter
     * @param s Key of the map
     * @param c Catgory toput
     */
    public static void putCategory(String s, Category c)
    {
        sCategoryMap.put(s, c);
    }
    
    /**
     * Remove catgory from map
     * @param s Key
     */
    public static void delCategory(String s)
    {
        sCategoryMap.remove(s);
    }
    
    /**
     * Create a new Category Map
     */
    public static void newCategoryMap(){
        sCategoryMap=new HashMap<>();                
    }
    /**
     * Get Category from Map
     * @param s Key
     * @return Category
     */
    public static Category getCategory(String s)
    {
        return sCategoryMap.get(s);
    }
    

    /**
     * Name of the category
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
     * Hashcode of the instance
     * @return hascode
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

    @Override
    public Element getXMLElement() {
        final Element clan = new Element(StringConstants.CS_CATEGORY);
        clan.setAttribute(StringConstants.CS_NAME, getName());
        return clan;
    }

    /**
     * Test if Category map is null
     * @return 
     */
    public static boolean isCategoryMapNull()
    {
        return sCategoryMap==null;
    }
    
    @Override
    public void setXMLElement(final Element e) {
        this.setName(e.getAttributeValue(StringConstants.CS_NAME));
        if (!isCategoryMapNull())
        {
            putCategory(getName(), this);
        }        
    }

    /**
     * Category name Getter
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * Category name Setter
     * @param mName the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    @Override
    public String toString()
    {
        return getName();
    }
    
    public void updateFromJSON(JSONObject object) {

        Object obj = object.get("createDateTime");
        if (obj != JSONObject.NULL) {
            createDateTime = LocalDateTime.parse(object.get("createDateTime").toString());
        }
        obj = object.get("updateDateTime");
        if (obj != JSONObject.NULL) {
            updateDateTime = LocalDateTime.parse(object.get("updateDateTime").toString());
        }

        this.mName = object.get("name").toString();

    }

    public JSONObject getJSON() {

        JSONObject json = new JSONObject();
        if (createDateTime == null) {
            createDateTime = LocalDateTime.now();
        }
        if (updateDateTime == null) {
            updateDateTime = LocalDateTime.now();
        }

        json.put("createDateTime", createDateTime.toString());
        json.put("updateDateTime", updateDateTime.toString());

        json.put("name", mName);

        

        return json;
    }
}
