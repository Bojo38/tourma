/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.awt.Color;

/**
 *
 * @author WFMJ7631
 */
public class Skill {

    public static final int C_SINGLE_COST=20000;
    public static final int C_DOUBLE_COST=30000;
    public static final int C_STRENGHT_COST=50000;
    public static final int C_AGILITY_COST=40000;
    public static final int C_ARMOR_COST=30000;
    public static final int C_MOVEMENT_COST=30000;
    
    public String mName;
    public SkillType mCategory;
    public Color mColor;

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
}
