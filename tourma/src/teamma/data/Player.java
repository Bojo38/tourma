/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * 
 * @author WFMJ7631
 */
public class Player {
    private static final Logger LOG = Logger.getLogger(Player.class.getName());

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
    
    /**
     * 
     * @return
     */
     public int getMovement() {
         int i;
         int value = _playertype.getMovement();
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals("+1 Movement")) {
                 value++;
             }
         }
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals("-1 Movement")) {
                 value--;
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
             if (_skills.get(i).getmName().equals("+1 Strength")) {
                 value++;
             }
         }
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals("-1 Strength")) {
                 value--;
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
             if (_skills.get(i).getmName().equals("+1 Agility")) {
                 value++;
             }
         }
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals("-1 Agility")) {
                 value--;
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
             if (_skills.get(i).getmName().equals("+1 Armor")) {
                 value++;
             }
         }
         for (i = 0; i < _skills.size(); i++) {
             if (_skills.get(i).getmName().equals("-1 Armor")) {
                 value--;
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
                 
                 if (s.getmName().equals("+1 Strength"))
                 {
                     cost+=Skill._C_STRENGHT_COST;
                 }
                 if (s.getmName().equals("+1 Movement"))
                 {
                     cost+=Skill._C_MOVEMENT_COST;
                 }
                 if (s.getmName().equals("+1 Armor"))
                 {
                     cost+=Skill._C_ARMOR_COST;
                 }
                 if (s.getmName().equals("+1 Agility"))
                 {
                     cost+=Skill._C_AGILITY_COST;
                 }
                 /**
                  * TODO: Detects the cost of the skill
                  */
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
