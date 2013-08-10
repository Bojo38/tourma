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
public class Roster {
    /*
     * Pointer to coach owning the team
     */

    public RosterType _roster;
    /**
     * Standard creation team
     */
    public ArrayList<Player> _players;
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
    public int _cards;
    /*
     * No mercenary ?
     */
    public ArrayList<StarPlayer> _champions;

    public Roster() {
        _players = new ArrayList<Player>();
        _champions = new ArrayList<StarPlayer>();
    }

    public int getValue(boolean bWithSkill) {
        int cost = 0;
        /**
         * Add player cost
         */
        for (int i = 0; i < _players.size(); i++) {
            
            
            cost += _players.get(i).getValue(bWithSkill);
        }


        /**
         * Add Team Goods
         */
        cost += _assistants * RosterType._assistant_cost;
        cost += _cheerleaders * RosterType._cheerleader_cost;
        cost += _fanfactor * RosterType._fan_factor_cost;
        cost += _apothecary?RosterType._apothecary_cost:0;
        cost+=_roster!=null?_rerolls*_roster._reroll_cost:0;

        /**
         * Add Inducements
         */
        for (int i = 0; i < _champions.size(); i++) {
            cost += _champions.get(i)._cost;
        }
        
        cost+=_bloodweiserbabes*RosterType._babe_cost;
        cost+=_cards;
        cost+=_roster!=null? _corruptions*_roster._bribe_cost:0;
        cost+=_extrarerolls*RosterType._extra_reroll_cost;
        cost+=_localapothecary*RosterType._local_apo_cost;
        cost+=(_chef)&&(_roster!=null)?_roster._chef_cost:0;
        cost+=_igor?RosterType._igor_cost:0;
        cost+=_wizard?RosterType._wizard_cost:0;
        
        return cost;
    }
}
