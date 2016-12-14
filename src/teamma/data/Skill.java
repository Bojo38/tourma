/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author WFMJ7631
 */
public class Skill implements Serializable{

/**
 * 
 */
    public static final int _C_SINGLE_COST=20000;
    /**
     * 
     */
    public static final int _C_DOUBLE_COST=30000;
    /**
     * 
     */
    public static final int _C_STRENGHT_COST=50000;
    /**
     * 
     */
    public static final int _C_AGILITY_COST=40000;
    /**
     * 
     */
    public static final int _C_ARMOR_COST=30000;
    /**
     * 
     */
    public static final int _C_MOVEMENT_COST=30000;
    
    
    
       
    /**
     * 
     */
    private String mName;
    /**
     * 
     */
    private SkillType mCategory;
    /**
     * 
     */
    private Color mColor;

    /**
     *
     * @param name
     * @param category
     */
    public Skill(final String name, final SkillType category) {
        mName = name;
        mCategory = category;
        mColor=Color.BLACK;
    }
    
    public Skill(Skill sk) {
        mName = sk.getmName();
        mCategory = sk.getmCategory();
        mColor=Color.BLACK;
    }

    /**
     * @return the mName
     */
    public String getmName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setmName(String mName) {
        this.mName = mName;
    }

    /**
     * @return the mCategory
     */
    public SkillType getmCategory() {
        return mCategory;
    }

    /**
     * @param mCategory the mCategory to set
     */
    public void setmCategory(SkillType mCategory) {
        this.mCategory = mCategory;
    }

    /**
     * @return the mColor
     */
    public Color getmColor() {
        return mColor;
    }

    /**
     * @param mColor the mColor to set
     */
    public void setmColor(Color mColor) {
        this.mColor = mColor;
    }
}
