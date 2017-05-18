/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author WFMJ7631
 */
public class PlayerType implements Serializable{
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
    private final ArrayList<SkillType> _single;
    /**
     * 
     */
    private final ArrayList<SkillType> _double;
    
    
    /**
     * 
     * @param position 
     */
    public PlayerType (String position){
        _position=position;
        _skills=new ArrayList<>();
        _single=new ArrayList<>();
        _double=new ArrayList<>();
    }

    /**
     * @return the _position
     */
    public String getPosition() {
        return _position;
    }

    /**
     * @return the _limit
     */
    public int getLimit() {
        return _limit;
    }

    /**
     * @return the _movement
     */
    public int getMovement() {
        return _movement;
    }

    /**
     * @return the _strength
     */
    public int getStrength() {
        return _strength;
    }

    /**
     * @return the _agility
     */
    public int getAgility() {
        return _agility;
    }

    /**
     * @return the _armor
     */
    public int getArmor() {
        return _armor;
    }

    /**
     * @return the _cost
     */
    public int getCost() {
        return _cost;
    }

    /**
     * @param i
     * @return the _skills
     */
    public Skill getSkill(int i) {
        return _skills.get(i);
    }
    
    /**
     * 
     * @param s 
     */
    public void addSkill(Skill s)
    {
        _skills.add(s);
    }
    
    /**
     * 
     * @return 
     */
    public int getSkillCount()
    {
        return _skills.size();
    }

    /**
     * @param i
     * @return the _single
     */
    public SkillType getSingle(int i) {
        return _single.get(i);
    }
    
    /**
     * @return the _single
     */
    public int getSingleCount() {
        return _single.size();
    }
    
    /**
     * 
     * @param s
     * @return 
     */
    public boolean containedBySingle(SkillType s)
    {
        return _single.contains(s);
    }
    
    /**
     * 
     * @param s
     * @return 
     */
    public boolean containedByDouble(SkillType s)
    {
        return _double.contains(s);
    }
    
    /**
     * 
     * @param s 
     */
    public void addSingle(SkillType s)
    {
        _single.add(s);
    }

    /**
     * 
     * @param s 
     */
    public void addDouble(SkillType s)
    {
        _double.add(s);
    }
    
    


    /**
     * @param i
     * @return the _single
     */
    public SkillType getDouble(int i) {
        return _double.get(i);
    }
    
    /**
     * @return the _single
     */
    public int getDoubleCount() {
        return _double.size();
    }
    


    /**
     *
     * @param _position
     */
    public void setPosition(String _position) {
        this._position = _position;
    }

    /**
     *
     * @param _limit
     */
    public void setLimit(int _limit) {
        this._limit = _limit;
    }

    /**
     *
     * @param _movement
     */
    public void setMovement(int _movement) {
        this._movement = _movement;
    }

    /**
     *
     * @param _strength
     */
    public void setStrength(int _strength) {
        this._strength = _strength;
    }

    /**
     *
     * @param _agility
     */
    public void setAgility(int _agility) {
        this._agility = _agility;
    }

    /**
     *
     * @param _armor
     */
    public void setArmor(int _armor) {
        this._armor = _armor;
    }

    /**
     *
     * @param _cost
     */
    public void setCost(int _cost) {
        this._cost = _cost;
    }
    
    
}
