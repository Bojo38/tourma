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
    
    public static final int _extra_reroll_cost=100000;
    public static final int _wizard_cost=150000;
    public static final int _local_apo_cost=100000;
    public static final int  _igor_cost=100000;
    
    public String _name;
    public int _reroll_cost;
    public boolean _apothecary;
    public int _bribe_cost;
    public int _chef_cost;
    public boolean _igor;
    public String _image;
    public Vector<PlayerType> _player_types;
    public Vector<StarPlayer> _available_starplayers;
    
    public RosterType(String name)
    {
        _name=name;
        _player_types=new  Vector<PlayerType>();
        _available_starplayers=new Vector<StarPlayer>();
    }
    
    public PlayerType getPlayerType(String name) {
        int i;
        for (i = 0; i < _player_types.size(); i++) {
            PlayerType rt = _player_types.get(i);

            if (name.equals(rt._position)) {
                return rt;
            }

        }
        return null;
    }
    
}
