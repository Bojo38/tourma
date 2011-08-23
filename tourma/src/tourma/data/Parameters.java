/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Frederic Berger
 */
public class Parameters {

    public int _victory_points = 1000;
    public int _large_victory_points = 1100;
    public int _draw_points = 400;
    public int _little_lost_points = 100;
    public int _lost_points = 0;
    public int _team_victory_points = 0;
    public boolean _team_victory_only = false;
    public int _large_victory_gap = 3;
    public int _little_lost_gap = 1;
   

    public Vector<Criteria> _criterias;

    public String _tournament_name;
    public String _tournament_orga;
    public String _place;
    public Date _date = new Date();
    public int _ranking1 = 1;
    public int _ranking2 = 2;
    public int _ranking3 = 3;
    public int _ranking4 = 4;
    public int _ranking5 = 5;
    public boolean _teamTournament = false;
    /* Pairing: 0: Individuel
    1: ByTeam
     */
    public int _teamPairing = 0;
    /* IndivPairing:    0: Classement
    1: Libre
     */
    public int _teamIndivPairing = 0;
    public int _teamMatesNumber = 1;
    public int _victory_points_team = 1000;
    public int _draw_points_team = 400;
    public int _lost_points_team = 0;
    public int _ranking1_team = 1;
    public int _ranking2_team = 2;
    public int _ranking3_team = 3;
    public int _ranking4_team = 4;
    public int _ranking5_team = 5;
  
     /***********************
     * Roster group management
     ***********************/
    public boolean _groupsEnable=false;

    /***********************
     * Clan management
     ***********************/

    /**
     * Activate clan management
     */
    public boolean _enableClans = false;
    /**
     * Avoid match between members of a clan
     */
    public boolean _avoidClansFirstMatch = true;
    /**
     * Avoid match between members of the same clan
     */
    public boolean _avoidClansMatch = false;
    /**
     * Number of player of one clan used for clan ranking
     */
    public int _teamMatesClansNumber = 3;

    public static final int C_RANKING_SUBTYPE_POSITIVE = 0;
    public static final int C_RANKING_SUBTYPE_NEGATIVE = 1;
    public static final int C_RANKING_SUBTYPE_DIFFERENCE = 2;

    public static final int C_RANKING_NONE = 0;
    public static final int C_RANKING_POINTS = 1;
    public static final int C_RANKING_OPP_POINTS = 2;
    public static final int C_RANKING_VND = 3;

    public static final int C_MAX_RANKING = 3;

    /*public static final int C_RANKING_TD = 3;
    public static final int C_RANKING_SOR = 4;
    public static final int C_RANKING_FOUL = 5;
    public static final int C_RANKING_DIFF_TD = 6;
    public static final int C_RANKING_DIFF_SOR = 7;
    public static final int C_RANKING_DIFF_FOUL = 8;
    
    public static final int C_RANKING_PAS = 10;
    public static final int C_RANKING_INT = 11;
    public static final int C_RANKING_DIFF_PAS = 12;
    public static final int C_RANKING_DIFF_INT = 13;*/

    public Parameters() {
        _tournament_name = "";
        _tournament_orga = "";
        _criterias=new Vector<Criteria>();
        
        Criteria c=new Criteria(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Touchdowns"));
        c._pointsFor=2;
        _criterias.add(c);
        c=new Criteria(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Injuries"));
        c._pointsFor=1;
        _criterias.add(c);
    }
}
