/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.teamma.data;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author WFMJ7631
 */
public class Skill implements Serializable {

    protected boolean random = false;

    ArrayList<Skill> forbidden = new ArrayList<>();
    /**
     *
     */
    public static final int _C_SINGLE_COST = 20000;
    /**
     *
     */
    public static final int _C_DOUBLE_COST = 30000;

    public static final int _C_SINGLE_COST_2020 = 20000;
    public static final int _C_DOUBLE_COST_2020 = 40000;

    public static final int _C_SINGLE_COST_2020_RANDOM = 10000;
    public static final int _C_DOUBLE_COST_2020_RANDOM = 20000;

    /**
     *
     */
    public static final int _C_STRENGHT_COST = 50000;
    public static final int _C_STRENGHT_COST_2020 = 80000;
    /**
     *
     */
    public static final int _C_AGILITY_COST = 40000;
    public static final int _C_AGILITY_COST_2020 = 40000;

    public static final int _C_PASS_COST = 40000;
    public static final int _C_PASS_COST_2020 = 20000;
    /**
     *
     */
    public static final int _C_ARMOR_COST = 30000;
    public static final int _C_ARMOR_COST_2020 = 10000;
    /**
     *
     */
    public static final int _C_MOVEMENT_COST = 30000;
    public static final int _C_MOVEMENT_COST_2020 = 20000;

    /**
     *
     */
    private String mName;
    
    
    /**
     *
     */
    private String mForbiddenText="";
    
    
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
        mColor = Color.BLACK;
    }

    public Skill(Skill sk) {
        mName = sk.getName();
        mCategory = sk.getmCategory();
        mColor = Color.BLACK;
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    public int getForbiddenCount()
    {
        return forbidden.size();
    }
    
     public Skill getForbidden(int i)
    {
        return forbidden.get(i);
    }
    
    /**
     * @param mName the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
    }
    
      /**
     * @return the mName
     */
    public String getForbiddenText() {
        return mForbiddenText;
    }

    /**
     * @param mName the mName to set
     */
    public void setForbiddenText(String text) {
        this.mForbiddenText = text;
    }

    /**
     * @return the mName
     */
    public void setRandom(boolean r) {
        random = r;
    }

    /**
     * @param mName the mName to set
     */
    public boolean isRandom() {
        return this.random;
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
    
    public void addForbiddenSkill(Skill skill)
    {
        forbidden.add(skill);
    }
    
    public void removeForbiddenSkill(Skill skill)
    {
        forbidden.remove(skill);
    }
}
