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
 * @author WFMJ7631
 */
public class StarPlayer implements Serializable{
    private static final Logger LOG = Logger.getLogger(StarPlayer.class.getName());

    /**
     * 
     */
    private String _name;
    /**
     * 
     */
    private String _position;
    /**
     * 
     */
    private int _limit;
    /**
     * 
     */
    private int _movement;
    /**
     * 
     */
    private int _strength;
    /**
     * 
     */
    private int _agility;
    /**
     * 
     */
    private int _armor;
    /**
     * 
     */
    private int _cost;
    /**
     * 
     */
    private final ArrayList<Skill> _skills;
    /**
     * 
     */
    private final ArrayList<RosterType> _rosters;
    
    /**
     * 
     * @param name 
     */
    public StarPlayer(String name)
    {
        _name=name;
        _skills=new ArrayList<>();
        _rosters=new ArrayList<>();
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
     * @return the _position
     */
    public String getPosition() {
        return _position;
    }

    /**
     * @param _position the _position to set
     */
    public void setPosition(String _position) {
        this._position = _position;
    }

    /**
     * @return the _limit
     */
    public int getLimit() {
        return _limit;
    }

    /**
     * @param _limit the _limit to set
     */
    public void setLimit(int _limit) {
        this._limit = _limit;
    }

    /**
     * @return the _movement
     */
    public int getMovement() {
        return _movement;
    }

    /**
     * @param _movement the _movement to set
     */
    public void setMovement(int _movement) {
        this._movement = _movement;
    }

    /**
     * @return the _strength
     */
    public int getStrength() {
        return _strength;
    }

    /**
     * @param _strength the _strength to set
     */
    public void setStrength(int _strength) {
        this._strength = _strength;
    }

    /**
     * @return the _agility
     */
    public int getAgility() {
        return _agility;
    }

    /**
     * @param _agility the _agility to set
     */
    public void setAgility(int _agility) {
        this._agility = _agility;
    }

    /**
     * @return the _armor
     */
    public int getArmor() {
        return _armor;
    }

    /**
     * @param _armor the _armor to set
     */
    public void setArmor(int _armor) {
        this._armor = _armor;
    }

    /**
     * @return the _cost
     */
    public int getCost() {
        return _cost;
    }

    /**
     * @param _cost the _cost to set
     */
    public void setCost(int _cost) {
        this._cost = _cost;
    }

    /**
     * @return the _skills
     */
    /*public ArrayList<Skill> getSkills() {
        return _skills;
    }*/
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
     * @param r
     */
    public void addRoster(RosterType r) {
         _rosters.add(r);
    }
    
     /**
     * @return the _rosters
     */
    public int getRosterCount() {
        return _rosters.size();
    }
    
     /**
     * @param i
     * @return the _rosters
     */
    public RosterType getRoster(int i) {
        return _rosters.get(i);
    }

}
