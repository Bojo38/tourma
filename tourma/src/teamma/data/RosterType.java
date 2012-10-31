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
public class RosterType {
    
    public String _name;
    public int _reroll_cost;
    public boolean _apothecary;
    public int _bribe_cost;
    public int _chef_cost;
    public boolean _igor;
    public Vector<PlayerType> _player_types;
    public Vector<StarPlayer> _available_starplayers;
    
    public RosterType(String name)
    {
        _name=name;
        _player_types=new  Vector<PlayerType>();
        _available_starplayers=new Vector<StarPlayer>();
    }
    
}
