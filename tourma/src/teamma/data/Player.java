/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.util.ArrayList;

/**
 *
 * @author WFMJ7631
 */
public class Player {

    public PlayerType _playertype;
    public String _name;
    public ArrayList<Skill> _skills;

    public Player(PlayerType pt)
    {
        _playertype=pt;
        _skills=new ArrayList<Skill>();
        _name="";
    }
    
    public int getMovement() {
        int i;
        int value = _playertype._movement;
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i).mName.equals("+1 Movement")) {
                value++;
            }
        }
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i).mName.equals("-1 Movement")) {
                value--;
            }
        }
        return value;
    }

    public int getStrength() {
        int i;
        int value = _playertype._strength;
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i).mName.equals("+1 Strength")) {
                value++;
            }
        }
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i).mName.equals("-1 Strength")) {
                value--;
            }
        }
        return value;
    }

    public int getAgility() {
        int i;
        int value = _playertype._agility;
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i).mName.equals("+1 Agility")) {
                value++;
            }
        }
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i).mName.equals("-1 Agility")) {
                value--;
            }
        }
        return value;
    }
    
     public int getArmor() {
        int i;
        int value = _playertype._armor;
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i).mName.equals("+1 Armor")) {
                value++;
            }
        }
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i).mName.equals("-1 Armor")) {
                value--;
            }
        }
        return value;
    }
     
    public int getValue(boolean bWithSkill)
    {
        int cost=_playertype._cost;
        
        if (bWithSkill)
        {
            for (int i=0 ;i<_skills.size(); i++)
            {
                Skill s=_skills.get(i);
                cost+=_playertype._single.contains(s.mCategory)?Skill.C_SINGLE_COST:0;
                cost+=_playertype._double.contains(s.mCategory)?Skill.C_DOUBLE_COST:0;
                
                if (s.mName.equals("+1 Strength"))
                {
                    cost+=Skill.C_STRENGHT_COST;
                }
                if (s.mName.equals("+1 Movement"))
                {
                    cost+=Skill.C_MOVEMENT_COST;
                }
                if (s.mName.equals("+1 Armor"))
                {
                    cost+=Skill.C_ARMOR_COST;
                }
                if (s.mName.equals("+1 Agility"))
                {
                    cost+=Skill.C_AGILITY_COST;
                }
                /**
                 * TODO: Detects the cost of the skill
                 */
            }
        }
        
        return cost;
    }
}
