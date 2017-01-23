/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author fberger
 */
public class SkillType implements Serializable{
    private static final Logger LOG = Logger.getLogger(SkillType.class.getName());

    /**
     * 
     */
    private String _name;
    /**
     * 
     */
    private String _accronym;
    /**
     * 
     */
    private final ArrayList<Skill> _skills;
    /**
     * 
     */
    private boolean _special;

    /**
     * 
     * @param name
     * @param accronym 
     */
    public SkillType(String name,String accronym) {
        _skills = new ArrayList<>();
        _name = name;
        _accronym=accronym;
    }

    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * @return the _accronym
     */
    public String getAccronym() {
        return _accronym;
    }

    /**
     * @param _accronym the _accronym to set
     */
    public void setAccronym(String _accronym) {
        this._accronym = _accronym;
    }

    /**
     * Clear the skill collections
     */
    public void clearSkills() {
        _skills.clear();
    }
    
    
    /**
     * @param s
     */
    public void addSkill(Skill s) {
        _skills.add(s);
    }
    
    
    /**
     * @return the _skills
     */
    public int getSkillCount() {
        return _skills.size();
    }
    
    
    /**
     * @param i
     * @return the _skills
     */
    public Skill getSkill(int i) {
        return _skills.get(i);
    }

    /**
     * @return the _special
     */
    public boolean isSpecial() {
        return _special;
    }

    /**
     * @param _special the _special to set
     */
    public void setSpecial(boolean _special) {
        this._special = _special;
    }
}
