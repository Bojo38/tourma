/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.util.Vector;

/**
 *
 * @author fberger
 */
public class SkillType {

    public String _name;
    public Vector<Skill> _skills;

    public SkillType(String name) {
        _skills = new Vector<Skill>();
        _name = name;
    }
}
