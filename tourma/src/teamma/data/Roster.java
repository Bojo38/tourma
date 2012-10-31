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
public class Roster {
    /*
     * Pointer to coach owning the team
     */
    public RosterType _roster;
    
    /**
     * Standard creation team
     */
    public Vector<Player> _players;
    public int _rerolls;
    public boolean _apothecary;
    public int _fanfactor;
    public int _cheerleaders;
    public int _assistants;
    
    /**
     * Inducements
     */
    public int _extrarerolls;
    public int _localapothecary;
    public int _bloodweiserbabes;
    public int _corruptions;
    public boolean _chef;
    public boolean _igor;
    public boolean _wizard;
    /*
     * No mercenary ?
     */
    public Vector<StarPlayer> _champions;
}
