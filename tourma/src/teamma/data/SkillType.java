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
    public String _accronym;
    public Vector<Skill> _skills;
    public boolean _special;

    public SkillType(String name,String accronym) {
        _skills = new Vector<Skill>();
        _name = name;
        _accronym=accronym;
    }
}
