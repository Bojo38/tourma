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
public class Player implements Serializable{

    /**
     * 
     */
    private PlayerType _playertype;

    /**
     * 
     */
    private String _name;
    /**
     * 
     */
    private final ArrayList<Skill> _skills;

/**
 * 
 * @param pt 
 */
    public Player(PlayerType pt)
    {
        _playertype=pt;
        _skills=new ArrayList<>();
        _name="";
    }
    
    public void pull(Player player,LRB.E_Version version)
    {
        this._name=player._name;
        this._skills.clear();
        for (int i=0; i<player.getSkillCount(); i++)
        {
            Skill skill=LRB.getLRB(version).getSkill(player.getSkill(i).getmName(), false);
            _skills.add(skill);
        }
    }
    
    /**
     *
     * @return
     */
    public PlayerType getPlayertype() {
        return _playertype;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return _name;
    }

    /**
     *
     * @param i
     * @return
     */
    public Skill getSkill(int i) {
        return _skills.get(i);
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
     * 
     * 
     * @param i 
     */
    public void removeSkill(int i)
    {
        _skills.remove(i);
    }
    
    /**
     *
     * @param s
     */
    public void addSkill(Skill s) {
        _skills.add(s);
    }

    
    public static final String CS_Plus1Movement="+1 Movement";
    public static final String CS_Minus1Movement="-1 Movement";
    public static final String CS_Plus1Strength="+1 Strength";
    public static final String CS_Minus1Strength="-1 Strength";
    public static final String CS_Plus1Agility="+1 Agility";
    public static final String CS_Minus1Agility="-1 Agility";
    public static final String CS_Plus1Armor="+1 Armor";
    public static final String CS_Minus1Armor="-1 Armor";
    
    /**
     * 
     * @return
     */
     public int getMovement() {
         int i;
         int value = _playertype.getMovement();
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals(CS_Plus1Movement)) {
                 value++;
             }
         }
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals(CS_Minus1Movement)) {
                 value--;
                 if (value<1)
                 {
                     value=1;
                 }
             }
         }
         return value;
     }
     
     /**
     *
     * @return
     */
     public int getStrength()
     {
         int i;
         int value = _playertype.getStrength();
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals(CS_Plus1Strength)) {
                 value++;
             }
         }
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals(CS_Minus1Strength)) {
                 value--;
                 if (value<1)
                 {
                     value=1;
                 }
             }
         }
         return value;
    }
    
    /**
     *
     * @return 
     */
     public int getAgility() {
         int i;
         int value = _playertype.getAgility();
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals(CS_Plus1Agility)) {
                 value++;
             }
         }
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals(CS_Minus1Agility)) {
                 value--;
                 if (value<1)
                 {
                     value=1;
                 }
             }
         }
        return value;
    }

    /**
     *
     * @return 
     */
     public int getArmor() {
         int i;
         int value = _playertype.getArmor();
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals(CS_Plus1Armor)) {
                 value++;
             }
         }
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals(CS_Minus1Armor)) {
                 value--;
                 if (value<1)
                 {
                     value=1;
                 }
             }
        }
        return value;
    }

    /**
     *
     * @param bWithSkill 
     * @return
     */
     public int getValue(boolean bWithSkill) {
         int cost=_playertype.getCost();
         
         if (bWithSkill)
         {
             for (Skill s : _skills) {
                 cost+=_playertype.containedBySingle(s.getmCategory())?Skill._C_SINGLE_COST:0;
                 cost+=_playertype.containedByDouble(s.getmCategory())?Skill._C_DOUBLE_COST:0;
                 
                 if (s.getmName().equals(CS_Plus1Strength))
                 {
                     cost+=Skill._C_STRENGHT_COST;
                 }
                 if (s.getmName().equals(CS_Plus1Movement))
                 {
                     cost+=Skill._C_MOVEMENT_COST;
                 }
                 if (s.getmName().equals(CS_Plus1Armor))
                 {
                     cost+=Skill._C_ARMOR_COST;
                 }
                 if (s.getmName().equals(CS_Plus1Agility))
                 {
                     cost+=Skill._C_AGILITY_COST;
                 }
             }
        }
        
        return cost;
    }

    /**
     *
     * @param _playertype
     */
     public void setPlayertype(PlayerType _playertype) {
        this._playertype = _playertype;
    }

     /**
      *
      * @param _name
     */
    public void setName(String _name) {
        this._name = _name;
    }

}
