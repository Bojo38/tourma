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
public class StarPlayer {

    public String _name;
    public String _position;
    public int _limit;
    public int _movement;
    public int _strength;
    public int _agility;
    public int _armor;
    public int _cost;
    public Vector<Skill> _skills;
    public Vector<RosterType> _rosters;
    
    public StarPlayer(String name)
    {
        _name=name;
        _skills=new Vector<Skill>();
        _rosters=new Vector<RosterType>();
    }
}
