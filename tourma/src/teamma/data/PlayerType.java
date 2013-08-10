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
public class PlayerType {
    public String _position;
    public int _limit;
    public int _movement;
    public int _strength;
    public int _agility;
    public int _armor;
    public int _cost;
    public ArrayList<Skill> _skills;
    public ArrayList<SkillType> _single;
    public ArrayList<SkillType> _double;
    
    public PlayerType (String position){
        _position=position;
        _skills=new ArrayList<Skill>();
        _single=new ArrayList<SkillType>();
        _double=new ArrayList<SkillType>();
    }
    
    
}
