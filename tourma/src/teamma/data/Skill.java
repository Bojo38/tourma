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

    public static final int _single_cost=20000;
    public static final int _double_cost=30000;
    public static final int _strenght_cost=50000;
    public static final int _agility_cost=40000;
    public static final int _armor_cost=30000;
    public static final int _movement_cost=30000;
    
    public String _name;
    public SkillType _category;
    public Color _color;

    public Skill(String name, SkillType category) {
        _name = name;
        _category = category;
        _color=Color.BLACK;
    }
}
