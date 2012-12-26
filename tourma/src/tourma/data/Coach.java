/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

import java.util.Vector;

/**
 * This class contains data relative to coach
 * @author Frederic Berger
 */
public class Coach implements Comparable{

    /**
     * Clan
     */
    public Clan _clan;
    public String _name;
    public String _team;
    public RosterType _roster;
    public int _naf;
    public int _rank;
    public boolean _active=true;

    public Team _teamMates=null;

    public Vector<Match> _matchs;

    public teamma.data.Roster _composition;
    
    public Coach()
    {
        _matchs=new Vector<Match>();
        _active=true;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof Coach)
        {
            return _name.compareTo(((Coach)obj)._name);
        }
        else
            return -1;
    }
}
