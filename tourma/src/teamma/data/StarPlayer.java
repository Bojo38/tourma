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
public class StarPlayer {

    public String _name;
    public String _position;
    public int _limit;
    public int _movement;
    public int _strength;
    public int _agility;
    public int _armor;
    public int _cost;
    public ArrayList<Skill> _skills;
    public ArrayList<RosterType> _rosters;
    
    public StarPlayer(String name)
    {
        _name=name;
        _skills=new ArrayList<Skill>();
        _rosters=new ArrayList<RosterType>();
    }
}
