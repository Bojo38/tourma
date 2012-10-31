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
public class PlayerType {
    public String _position;
    public int _limit;
    public int _movement;
    public int _strength;
    public int _agility;
    public int _armor;
    public int _cost;
    public Vector<Skill> _skills;
    public Vector<SkillType> _single;
    public Vector<SkillType> _double;
    
    public PlayerType (String position){
        _position=position;
        _skills=new Vector<Skill>();
        _single=new Vector<SkillType>();
        _double=new Vector<SkillType>();
    }
    
    
}
