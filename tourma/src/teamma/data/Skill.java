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

    public String _name;
    public SkillType _category;
    public Color _color;

    public Skill(String name, SkillType category) {
        _name = name;
        _category = category;
        _color=Color.BLACK;
    }
}
