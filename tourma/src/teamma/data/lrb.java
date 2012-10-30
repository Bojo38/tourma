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
public class lrb {
    
    public Vector<RosterType> _rosterTypes=null;
    public Vector<StarPlayer> _starPlayers=null;
    
    private static lrb _singleton=null;
    
    private lrb()
    {
        _rosterTypes=new Vector<RosterType>();
        _starPlayers=new Vector<StarPlayer>();
    }
    
    public static lrb getLRB()
    {
        if (_singleton==null)
        {
            _singleton=new lrb();
        }
        return _singleton;
    }
}
