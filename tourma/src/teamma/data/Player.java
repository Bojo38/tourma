/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.util.Vector;

/**
 *
 * @author WFMJ7631
 */
public class Player {

    public PlayerType _playertype;
    public String _name;
    public Vector<Skill> _skills;

    public Player(PlayerType pt)
    {
        _playertype=pt;
        _skills=new Vector<Skill>();
        _name="";
    }
    
    public int getMovement() {
        int i;
        int value = _playertype._movement;
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i)._name.equals("+1 Movement")) {
                value++;
            }
        }
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i)._name.equals("-1 Movement")) {
                value--;
            }
        }
        return value;
    }

    public int getStrength() {
        int i;
        int value = _playertype._strength;
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i)._name.equals("+1 Strength")) {
                value++;
            }
        }
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i)._name.equals("-1 Strength")) {
                value--;
            }
        }
        return value;
    }

    public int getAgility() {
        int i;
        int value = _playertype._agility;
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i)._name.equals("+1 Agility")) {
                value++;
            }
        }
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i)._name.equals("-1 Agility")) {
                value--;
            }
        }
        return value;
    }
    
     public int getArmor() {
        int i;
        int value = _playertype._armor;
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i)._name.equals("+1 Armor")) {
                value++;
            }
        }
        for (i = 0; i < _skills.size(); i++) {
            if (_skills.get(i)._name.equals("-1 Armor")) {
                value--;
            }
        }
        return value;
    }
}
