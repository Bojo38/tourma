/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jdom.DataConversionException;
import org.jdom.Element;
import tourma.MainFrame;

/**
 *
 * @author Frederic Berger
 */
public class Parameters implements XMLExport {

    public int _game = RosterType.C_BLOOD_BOWL;
    public int _victory_points = 1000;
    public int _large_victory_points = 1100;
    public int _draw_points = 400;
    public int _little_lost_points = 100;
    public int _lost_points = 0;
    public int _team_victory_points = 0;
    public int _team_draw_points = 0;
    public boolean _team_victory_only = false;
    public int _large_victory_gap = 3;
    public int _little_lost_gap = 1;
    public boolean _substitutes = false;
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
    /* IndivPairing:
     0: Classement
     1: Libre
     2: Aléatoire
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
    /**
     * *********************
     * Roster group management *********************
     */
    public boolean _groupsEnable = false;
    /**
     * *********************
     * Clan management *********************
     */
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
        _criterias = new Vector<Criteria>();

        Criteria c = new Criteria(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Touchdowns"));
        c._pointsFor = 2;
        _criterias.add(c);
        c = new Criteria(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Injuries"));
        c._pointsFor = 1;
        _criterias.add(c);
    }

    public Element getXMLElement() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Element params = new Element("Parameters");
        params.setAttribute("Organizer", this._tournament_orga);
        params.setAttribute("Name", this._tournament_name);
        params.setAttribute("Date", format.format(this._date));
        params.setAttribute("Place", this._tournament_name);

        for (int i = 0; i < this._criterias.size(); i++) {
            Element crit = _criterias.get(i).getXMLElement();
            params.addContent(crit);
        }

        params.setAttribute("Victory", Integer.toString(this._victory_points));
        params.setAttribute("Large_Victory", Integer.toString(this._large_victory_points));
        params.setAttribute("Draw", Integer.toString(this._draw_points));
        params.setAttribute("Lost", Integer.toString(this._lost_points));
        params.setAttribute("Little_Lost", Integer.toString(this._little_lost_points));

        params.setAttribute("Victory_Team", Integer.toString(this._victory_points_team));
        params.setAttribute("Draw_Team", Integer.toString(this._draw_points_team));
        params.setAttribute("Lost_Team", Integer.toString(this._lost_points_team));

        params.setAttribute("Large_Victory_Gap", Integer.toString(this._large_victory_gap));
        params.setAttribute("Little_Lost_Gap", Integer.toString(this._little_lost_gap));

        params.setAttribute("Rank1", Integer.toString(this._ranking1));
        params.setAttribute("Rank2", Integer.toString(this._ranking2));
        params.setAttribute("Rank3", Integer.toString(this._ranking3));
        params.setAttribute("Rank4", Integer.toString(this._ranking4));
        params.setAttribute("Rank5", Integer.toString(this._ranking5));

        params.setAttribute("Rank1_Team", Integer.toString(this._ranking1_team));
        params.setAttribute("Rank2_Team", Integer.toString(this._ranking2_team));
        params.setAttribute("Rank3_Team", Integer.toString(this._ranking3_team));
        params.setAttribute("Rank4_Team", Integer.toString(this._ranking4_team));
        params.setAttribute("Rank5_Team", Integer.toString(this._ranking5_team));

        params.setAttribute("ByTeam", Boolean.toString(this._teamTournament));
        params.setAttribute("TeamMates", Integer.toString(this._teamMatesNumber));
        params.setAttribute("TeamPairing", Integer.toString(this._teamPairing));
        params.setAttribute("TeamIndivPairing", Integer.toString(this._teamIndivPairing));

        params.setAttribute("TeamVictoryPoints", Integer.toString(this._team_victory_points));
        params.setAttribute("TeamDrawPoints", Integer.toString(this._team_draw_points));
        params.setAttribute("TeamVictoryOnly", Boolean.toString(this._team_victory_only));

        params.setAttribute("GroupEnable", Boolean.toString(this._groupsEnable));
        params.setAttribute("Substitutes", Boolean.toString(this._substitutes));
        params.setAttribute("GameType", Integer.toString(this._game));

        params.setAttribute("ActvateClans", Boolean.toString(this._enableClans));
        params.setAttribute("AvoidFirstMatch", Boolean.toString(this._avoidClansFirstMatch));
        params.setAttribute("AvoidMatch", Boolean.toString(this._avoidClansMatch));
        params.setAttribute("ClanTeammatesNumber", Integer.toString(this._teamMatesClansNumber));

        return params;
    }

    public void setXMLElement(Element params) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            this._tournament_orga = params.getAttribute("Organizer").getValue();
            this._tournament_name = params.getAttribute("Name").getValue();

            this._victory_points = params.getAttribute("Victory").getIntValue();
            this._large_victory_points = params.getAttribute("Large_Victory").getIntValue();
            this._draw_points = params.getAttribute("Draw").getIntValue();
            this._lost_points = params.getAttribute("Lost").getIntValue();
            this._little_lost_points = params.getAttribute("Little_Lost").getIntValue();
            this._ranking1 = params.getAttribute("Rank1").getIntValue();
            this._ranking2 = params.getAttribute("Rank2").getIntValue();
            this._ranking3 = params.getAttribute("Rank3").getIntValue();
            this._ranking4 = params.getAttribute("Rank4").getIntValue();
            this._ranking5 = params.getAttribute("Rank5").getIntValue();

            try {
                this._large_victory_gap = params.getAttribute("Large_Victory_Gap").getIntValue();
                this._little_lost_gap = params.getAttribute("Little_Lost_Gap").getIntValue();
                this._place = params.getAttribute("Place").getValue();
                this._teamTournament = params.getAttribute("ByTeam").getBooleanValue();
                this._teamMatesNumber = params.getAttribute("TeamMates").getIntValue();
                this._teamPairing = params.getAttribute("TeamPairing").getIntValue();
                this._teamIndivPairing = params.getAttribute("TeamIndivPairing").getIntValue();
                this._team_victory_points = params.getAttribute("TeamVictoryPoints").getIntValue();
                this._teamIndivPairing = params.getAttribute("TeamIndivPairing").getIntValue();
                this._team_victory_only = params.getAttribute("TeamVictoryOnly").getBooleanValue();
                try {
                    this._team_draw_points = params.getAttribute("TeamDrawPoints").getIntValue();
                } catch (Exception e) {
                    this._team_draw_points = 0;
                }

                try {
                    this._date = format.parse(params.getAttribute("Date").getValue());
                } catch (ParseException pe) {
                }

                try {
                    this._game = params.getAttribute("GameType").getIntValue();
                } catch (Exception pe) {
                    this._game = 1;
                }

                this._groupsEnable = params.getAttribute("GroupEnable").getBooleanValue();

            } catch (NullPointerException ne) {
                this._large_victory_gap = 3;
                this._little_lost_gap = 1;
                this._place = "";
                this._teamTournament = false;
                this._teamMatesNumber = 6;
                this._teamPairing = 1;
                this._teamIndivPairing = 0;
            }
            try {
                this._victory_points_team = params.getAttribute("Victory_Team").getIntValue();
                this._draw_points_team = params.getAttribute("Draw_Team").getIntValue();
                this._lost_points_team = params.getAttribute("Lost_Team").getIntValue();
                this._ranking1_team = params.getAttribute("Rank1_Team").getIntValue();
                this._ranking2_team = params.getAttribute("Rank2_Team").getIntValue();
                this._ranking3_team = params.getAttribute("Rank3_Team").getIntValue();
                this._ranking4_team = params.getAttribute("Rank4_Team").getIntValue();
                this._ranking5_team = params.getAttribute("Rank5_Team").getIntValue();
            } catch (NullPointerException ne2) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ne2.getLocalizedMessage());
            }

            try {
                this._enableClans = params.getAttribute("ActvateClans").getBooleanValue();
                this._avoidClansFirstMatch = params.getAttribute("AvoidFirstMatch").getBooleanValue();
                this._avoidClansMatch = params.getAttribute("AvoidMatch").getBooleanValue();
                this._teamMatesClansNumber = params.getAttribute("ClanTeammatesNumber").getIntValue();
                this._substitutes = params.getAttribute("Substitutes").getBooleanValue();

            } catch (NullPointerException ne3) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ne3.getLocalizedMessage());
            }

            List criterias = params.getChildren("Criteria");
            Iterator cr = criterias.iterator();

            this._criterias.removeAllElements();

            while (cr.hasNext()) {
                Element criteria = (Element) cr.next();
                Criteria crit = new Criteria(criteria.getAttributeValue("Name"));
                crit.setXMLElement(criteria);
                this._criterias.add(crit);
            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }
}
