/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.BufferedWriter;
import java.io.File;
import tourma.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import java.util.Vector;
import javax.swing.JOptionPane;
import org.jdom.DataConversionException;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Frederic Berger
 */
public class Tournament {

    protected Vector<Round> _rounds;
    protected Vector<Coach> _coachs;
    protected Vector<Team> _teams;
    protected Parameters _params;
    protected static Tournament _singleton;
    /**
     * Clans used in the tournement
     */
    protected Vector<Clan> _clans;
    /**
     * Rosters Groups
     */
    protected Vector<Group> _groups;

    private Tournament() {
        _params = new Parameters();
        _rounds = new Vector<Round>();
        _coachs = new Vector<Coach>();
        _clans = new Vector<Clan>();
        _teams = new Vector();
        _groups = new Vector<Group>();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        _clans.add(new Clan(bundle.getString("NoneKey")));
        Group group = new Group(bundle.getString("NoneKey"));
        _groups.add(group);

        for (int i = 0; i < Roster.Rosters.size(); i++) {
            group._rosters.add(new Roster(Roster.Rosters.get(i)));
        }
    }

    public static Tournament resetTournament() {
        _singleton = new Tournament();
        return _singleton;
    }

    public static Tournament getTournament() {
        if (_singleton == null) {
            _singleton = new Tournament();
        }
        return _singleton;
    }

    public Vector<Clan> getClans() {
        return _clans;
    }

    public Vector<Clan> getDisplayClans() {

        // Remove clans which do not have members
        HashMap<Clan, Integer> counts = new HashMap();
        for (int i = 0; i < _clans.size(); i++) {
            counts.put(_clans.get(i), 0);
        }
        Vector<Clan> clans = new Vector<Clan>();
        for (int i = 0; i < _coachs.size(); i++) {
            Coach c = _coachs.get(i);
            counts.put(c._clan, counts.get(c._clan) + 1);
        }

        for (int i = 0; i < _clans.size(); i++) {
            if (counts.get(_clans.get(i)) > 0) {
                clans.add(_clans.get(i));
            }

        }

        return clans;
    }

    public Parameters getParams() {
        return _params;




    }

    public Vector<Coach> getCoachs() {
        return _coachs;




    }

    public Vector<Group> getGroups() {
        return _groups;
    }

    public Vector<Team> getTeams() {
        return _teams;
    }

    public Vector<Round> getRounds() {
        return _rounds;
    }

    /*    public Vector<Group> getGroups() {
    return _groups;
    }*/
    public void saveXML(java.io.File file) {
        Element document = new Element("Tournament");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        document.setAttribute("Version", "3");

        for (int i = 0; i
                < Roster.Rosters.size(); i++) {
            Element ros = new Element("Roster");
            ros.setAttribute("Name", Roster.Rosters.get(i));
            document.addContent(ros);
        }

        Element params = new Element("Parameters");
        params.setAttribute("Organizer", _params._tournament_orga);
        params.setAttribute("Name", _params._tournament_name);
        params.setAttribute("Date", format.format(_params._date));
        params.setAttribute("Place", _params._tournament_name);

        for (int i = 0; i
                < _params._criterias.size(); i++) {
            Element crit = new Element("Criteria");
            Criteria c = _params._criterias.get(i);
            crit.setAttribute("Name", c._name);
            crit.setAttribute("PointsFor", Integer.toString(c._pointsFor));
            crit.setAttribute("PointsTeamFor", Integer.toString(c._pointsTeamFor));
            crit.setAttribute("PointsAgainst", Integer.toString(c._pointsAgainst));
            crit.setAttribute("PointsTeamAgainst", Integer.toString(c._pointsTeamAgainst));

            params.addContent(crit);
        }

        params.setAttribute("Victory", Integer.toString(_params._victory_points));
        params.setAttribute("Large_Victory", Integer.toString(_params._large_victory_points));
        params.setAttribute("Draw", Integer.toString(_params._draw_points));
        params.setAttribute("Lost", Integer.toString(_params._lost_points));
        params.setAttribute("Little_Lost", Integer.toString(_params._little_lost_points));

        params.setAttribute("Victory_Team", Integer.toString(_params._victory_points_team));
        params.setAttribute("Draw_Team", Integer.toString(_params._draw_points_team));
        params.setAttribute("Lost_Team", Integer.toString(_params._lost_points_team));

        params.setAttribute("Large_Victory_Gap", Integer.toString(_params._large_victory_gap));
        params.setAttribute("Little_Lost_Gap", Integer.toString(_params._little_lost_gap));

        params.setAttribute("Rank1", Integer.toString(_params._ranking1));
        params.setAttribute("Rank2", Integer.toString(_params._ranking2));
        params.setAttribute("Rank3", Integer.toString(_params._ranking3));
        params.setAttribute("Rank4", Integer.toString(_params._ranking4));
        params.setAttribute("Rank5", Integer.toString(_params._ranking5));

        params.setAttribute("Rank1_Team", Integer.toString(_params._ranking1_team));
        params.setAttribute("Rank2_Team", Integer.toString(_params._ranking2_team));
        params.setAttribute("Rank3_Team", Integer.toString(_params._ranking3_team));
        params.setAttribute("Rank4_Team", Integer.toString(_params._ranking4_team));
        params.setAttribute("Rank5_Team", Integer.toString(_params._ranking5_team));

        params.setAttribute("ByTeam", Boolean.toString(_params._teamTournament));
        params.setAttribute("TeamMates", Integer.toString(_params._teamMatesNumber));
        params.setAttribute("TeamPairing", Integer.toString(_params._teamPairing));
        params.setAttribute("TeamIndivPairing", Integer.toString(_params._teamIndivPairing));

        params.setAttribute("TeamVictoryPoints", Integer.toString(_params._team_victory_points));
        params.setAttribute("TeamVictoryOnly", Boolean.toString(_params._team_victory_only));

        params.setAttribute("GroupEnable", Boolean.toString(_params._groupsEnable));

        /*
         * Clan parameters 
         */
        params.setAttribute("ActvateClans", Boolean.toString(_params._enableClans));
        params.setAttribute("AvoidFirstMatch", Boolean.toString(_params._avoidClansFirstMatch));
        params.setAttribute("AvoidMatch", Boolean.toString(_params._avoidClansMatch));
        params.setAttribute("ClanTeammatesNumber", Integer.toString(_params._teamMatesClansNumber));

        document.addContent(params);

        for (int i = 0; i
                < _clans.size(); i++) {
            Element clan = new Element("Clan");
            clan.setAttribute("Name", _clans.get(i)._name);
            document.addContent(clan);
        }

        for (int i = 0; i < _groups.size(); i++) {
            Element group = new Element("Group");
            group.setAttribute("Name", _groups.get(i)._name);
            for (int j = 0; j < _groups.get(i)._rosters.size(); j++) {
                Element roster = new Element("Roster");
                roster.setAttribute("Name", _groups.get(i)._rosters.get(j)._name);
                group.addContent(roster);
            }
            document.addContent(group);
        }

        for (int i = 0; i
                < _coachs.size(); i++) {
            Element coach = new Element("Coach");
            coach.setAttribute("Name", _coachs.get(i)._name);
            coach.setAttribute("Team", _coachs.get(i)._team);
            coach.setAttribute("Roster", _coachs.get(i)._roster._name);
            coach.setAttribute("NAF", Integer.toString(_coachs.get(i)._naf));
            coach.setAttribute("Rank", Integer.toString(_coachs.get(i)._rank));
            coach.setAttribute("Clan", _coachs.get(i)._clan._name);
            document.addContent(coach);
        }

        for (int i = 0; i
                < _teams.size(); i++) {
            Element team = new Element("Team");
            team.setAttribute("Name", _teams.get(i)._name);
            for (int j = 0; j
                    < _teams.get(i)._coachs.size(); j++) {
                Element coach = new Element("Coach");
                coach.setAttribute("Name", _teams.get(i)._coachs.get(j)._name);
                team.addContent(coach);
            }
            document.addContent(team);
        }

        for (int i = 0; i
                < _rounds.size(); i++) {
            Element round = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Round"));
            round.setAttribute("Date", format.format(_rounds.get(i)._heure));

            for (int j = 0; j
                    < _rounds.get(i)._matchs.size(); j++) {
                Element match = new Element("Match");
                Match m = _rounds.get(i)._matchs.get(j);
                match.setAttribute("Coach1", m._coach1._name);
                match.setAttribute("Coach2", m._coach2._name);

                for (int k = 0; k
                        < _params._criterias.size(); k++) {
                    Value val = m._values.get(_params._criterias.get(k));
                    Element value = new Element("Value");
                    value.setAttribute("Name", val._criteria._name);
                    value.setAttribute("Value1", Integer.toString(val._value1));
                    value.setAttribute("Value2", Integer.toString(val._value2));
                    match.addContent(value);
                }
                round.addContent(match);
            }
            document.addContent(round);
        }

        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, new FileOutputStream(file));

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        }
    }

    private String getRosterTranslation(String source) {

        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AmazonKey"))) {
            return "Amazons";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UnderworldKey"))) {
            return "Underworld";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChaosKey"))) {
            return "Chaos";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ElfKey"))) {
            return "Elves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WoodElfKey"))) {
            return "Wood Elves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DarkElfKey"))) {
            return "Dark Elves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GoblinKey"))) {
            return "Goblins";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HalflingKey"))) {
            return "Halflings";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HighElfKey"))) {
            return "High Elves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LizardmenKey"))) {
            return "Lizardmen";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HumanKey"))) {
            return "Humans";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("KhemriKey"))) {
            return "Khemri";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UndeadKey"))) {
            return "Undead";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DwarfKey"))) {
            return "Dwarves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChaosDwarfKey"))) {
            return "Chaos Dwarves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NecromanticKey"))) {
            return "Necromantic";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NorseKey"))) {
            return "Norse";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NurgleKey"))) {
            return "Nurgle's Rotters";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OgreKey"))) {
            return "Ogres";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OrcKey"))) {
            return "Orc";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChaosPactKey"))) {
            return "Chaos Pact";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SkavenKey"))) {
            return "Skaven";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SlannKey"))) {
            return "Slann";
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VampireKey"))) {
            return "Vampires";
        }
        return "Unknown";
    }

    public void exportResults(java.io.File file) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");

        Criteria critTd = null;
        Criteria critInj = null;

        for (int i = 0; i < Tournament.getTournament()._params._criterias.size(); i++) {
            Criteria crit = Tournament.getTournament()._params._criterias.get(i);
            if (i == 0) {
                critTd = crit;
            }
            if (i == 1) {
                critInj = crit;
            }
        }

        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));

            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<nafReport xmlns='http://www.bloodbowl.net' xsi:schemaLocation='http://www.bloodbowl.net ../../../test/naf.xsd'>");
            writer.println("<organiser>" + _params._tournament_orga + "</organiser>");
            writer.println("<coaches>");
            for (int i = 0; i < _coachs.size(); i++) {
                if (_coachs.get(i)._naf > 0) {
                    writer.println("<coach>");
                    writer.println("<name>" + _coachs.get(i)._name + "</name>");
                    writer.println("<number>" + _coachs.get(i)._naf + "</number>");
                    writer.println("<team>" + getRosterTranslation(_coachs.get(i)._roster._name) + "</team>");
                    writer.println("</coach>");
                }
            }
            writer.println("</coaches>");

            for (int i = 0; i
                    < _rounds.size(); i++) {
                for (int j = 0; j
                        < _rounds.get(i)._matchs.size(); j++) {
                    if ((_rounds.get(i)._matchs.get(j)._coach1._naf > 0)
                            && (_rounds.get(i)._matchs.get(j)._coach2._naf > 0)) {
                        writer.println("<game>");
                        Match m = _rounds.get(i)._matchs.get(j);
                        writer.println("<timeStamp>" + format.format(_rounds.get(i)._heure) + "</timeStamp>");
                        Coach p = m._coach1;
                        writer.println("<playerRecord>");
                        writer.println("<name>" + p._name + "</name>");
                        writer.println("<number>" + p._naf + "</number>");
                        writer.println("<teamRating>" + p._rank + "</teamRating>");
                        writer.println("<touchDowns>" + m._values.get(critTd)._value1 + "</touchDowns>");
                        writer.println("<badlyHurt>" + m._values.get(critInj)._value1 + "</badlyHurt>");
                        writer.println("<seriouslyInjured></seriouslyInjured>");
                        writer.println("<dead></dead>");
                        writer.println("<winnings></winnings>");
                        writer.println("</playerRecord>");
                        p = m._coach2;
                        writer.println("<playerRecord>");
                        writer.println("<name>" + p._name + "</name>");
                        writer.println("<number>" + p._naf + "</number>");
                        writer.println("<teamRating>" + p._rank + "</teamRating>");
                        writer.println("<touchDowns>" + m._values.get(critTd)._value2 + "</touchDowns>");
                        writer.println("<badlyHurt>" + m._values.get(critInj)._value2 + "</badlyHurt>");
                        writer.println("<seriouslyInjured></seriouslyInjured>");
                        writer.println("<dead></dead>");
                        writer.println("<winnings></winnings>");
                        writer.println("</playerRecord>");
                        writer.println("<gate></gate>");
                        writer.println("</game>");
                    }
                }
            }
            writer.println("</nafReport>");
            writer.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        }

        /* Element document = new Element("nafReport");

        Element orgas = new Element("organiser");
        orgas.setText(_params._tournament_orga);

        document.addContent(orgas);

        Element coaches = new Element("coaches");
        for (int i = 0; i
        < _coachs.size(); i++) {
        if (_coachs.get(i)._naf > 0) {
        Element coach = new Element("coach");
        Element name = new Element("name");
        Element team = new Element("team");
        name.setText(_coachs.get(i)._name);
        String roster = getRosterTranslation(_coachs.get(i)._roster._name);
        team.setText(roster);
        coach.addContent(name);
        coach.addContent(team);
        coaches.addContent(coach);
        }
        }
        document.addContent(coaches);

        for (int i = 0; i
        < _rounds.size(); i++) {
        for (int j = 0; j
        < _rounds.get(i)._matchs.size(); j++) {
        if ((_rounds.get(i)._matchs.get(j)._coach1._naf > 0)
        && (_rounds.get(i)._matchs.get(j)._coach2._naf > 0)) {
        Element game = new Element("game");
        Element timeStamp = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TIMESTAMP"));
        timeStamp.setText(format.format(_rounds.get(i)._heure));
        Element playerRecord1 = new Element("playerRecord");
        Element playerRecord2 = new Element("playerRecord");
        Element name1 = new Element("name");
        Element rank1 = new Element("teamRating");
        Element td1 = new Element("touchDowns");
        Element bh1 = new Element("badlyHurt");
        Element name2 = new Element("name");
        Element rank2 = new Element("teamRating");
        Element td2 = new Element("touchDowns");
        Element bh2 = new Element("badlyHurt");

        name1.setText(_rounds.get(i)._matchs.get(j)._coach1._name);
        name2.setText(_rounds.get(i)._matchs.get(j)._coach2._name);
        td1.setText(Integer.toString(_rounds.get(i)._matchs.get(j)._values.get("Touchdowns")._value1));
        td2.setText(Integer.toString(_rounds.get(i)._matchs.get(j)._values.get("Touchdowns")._value2));
        bh1.setText(Integer.toString(_rounds.get(i)._matchs.get(j)._values.get("Sorties")._value1));
        bh2.setText(Integer.toString(_rounds.get(i)._matchs.get(j)._values.get("Sorties")._value2));
        rank1.setText(Integer.toString(_rounds.get(i)._matchs.get(j)._coach1._rank));
        rank2.setText(Integer.toString(_rounds.get(i)._matchs.get(j)._coach2._rank));

        playerRecord1.addContent(name1);
        playerRecord1.addContent(td1);
        playerRecord1.addContent(bh1);
        playerRecord1.addContent(rank1);

        playerRecord2.addContent(name2);
        playerRecord2.addContent(td2);
        playerRecord2.addContent(bh2);
        playerRecord2.addContent(rank2);

        game.addContent(timeStamp);
        game.addContent(playerRecord1);
        game.addContent(playerRecord2);
        document.addContent(game);

        }
        }
        }

        try {
        Format f = Format.getPrettyFormat();
        XMLOutputter sortie = new XMLOutputter(f);
        sortie.output(document, new FileOutputStream(file));

        } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());

        } catch (IOException e) {
        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());

        }*/
    }

    protected void LoadXMLv2(Element Root) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        /* Utilisateur */
        Element params = Root.getChild("Parameters");
        Criteria c1 = new Criteria("Touchdowns");
        Criteria c2 = new Criteria("Sorties");
        Criteria c3 = new Criteria("Fouls");
        Criteria c4 = new Criteria("Passes");
        Criteria c5 = new Criteria("Interceptions");

        try {
            if (params != null) {
                _params._tournament_orga = params.getAttribute("Organizer").getValue();
                _params._tournament_name = params.getAttribute("Name").getValue();

                _params._criterias.removeAllElements();
                c1._pointsFor = params.getAttribute("Bonus_Pos_Td").getIntValue();
                c1._pointsAgainst = params.getAttribute("Bonus_Neg_Td").getIntValue();
                c2._pointsFor = params.getAttribute("Bonus_Pos_Sor").getIntValue();
                c2._pointsAgainst = params.getAttribute("Bonus_Neg_Sor").getIntValue();
                c3._pointsFor = params.getAttribute("Bonus_Pos_Foul").getIntValue();
                c3._pointsAgainst = params.getAttribute("Bonus_Neg_Foul").getIntValue();


                try {
                    c4._pointsFor = params.getAttribute("Bonus_Pos_Pas").getIntValue();
                    c4._pointsAgainst = params.getAttribute("Bonus_Neg_Pas").getIntValue();

                    c5._pointsFor = params.getAttribute("Bonus_Pos_Int").getIntValue();
                    c5._pointsAgainst = params.getAttribute("Bonus_Neg_Int").getIntValue();


                } catch (NullPointerException npe) {
                }


                _params._criterias.add(c1);
                _params._criterias.add(c2);
                _params._criterias.add(c3);
                _params._criterias.add(c4);
                _params._criterias.add(c5);

                _params._victory_points = params.getAttribute("Victory").getIntValue();
                _params._large_victory_points = params.getAttribute("Large_Victory").getIntValue();
                _params._draw_points = params.getAttribute("Draw").getIntValue();
                _params._lost_points = params.getAttribute("Lost").getIntValue();
                _params._little_lost_points = params.getAttribute("Little_Lost").getIntValue();
                _params._ranking1 = params.getAttribute("Rank1").getIntValue();
                _params._ranking2 = params.getAttribute("Rank2").getIntValue();
                _params._ranking3 = params.getAttribute("Rank3").getIntValue();
                _params._ranking4 = params.getAttribute("Rank4").getIntValue();
                _params._ranking5 = params.getAttribute("Rank5").getIntValue();





                try {
                    _params._large_victory_gap = params.getAttribute("Large_Victory_Gap").getIntValue();
                    _params._little_lost_gap = params.getAttribute("Little_Lost_Gap").getIntValue();
                    _params._place = params.getAttribute("Place").getValue();
                    _params._teamTournament = params.getAttribute("ByTeam").getBooleanValue();
                    _params._teamMatesNumber = params.getAttribute("TeamMates").getIntValue();
                    _params._teamPairing = params.getAttribute("TeamPairing").getIntValue();
                    _params._teamIndivPairing = params.getAttribute("TeamIndivPairing").getIntValue();
                    _params._team_victory_points = params.getAttribute("TeamVictoryPoints").getIntValue();
                    _params._teamIndivPairing = params.getAttribute("TeamIndivPairing").getIntValue();
                    _params._team_victory_only = params.getAttribute("TeamVictoryOnly").getBooleanValue();





                    try {
                        _params._date = format.parse(params.getAttribute("Date").getValue());




                    } catch (ParseException pe) {
                    }

                } catch (NullPointerException ne) {
                    _params._large_victory_gap = 3;
                    _params._little_lost_gap = 1;
                    _params._place = "";
                    _params._teamTournament = false;
                    _params._teamMatesNumber = 6;
                    _params._teamPairing = 1;
                    _params._teamIndivPairing = 0;




                }
                try {

                    c1._pointsTeamFor = params.getAttribute("Bonus_Pos_Td_Team").getIntValue();
                    c1._pointsTeamAgainst = params.getAttribute("Bonus_Neg_Td_Team").getIntValue();
                    c2._pointsTeamFor = params.getAttribute("Bonus_Pos_Sor_Team").getIntValue();
                    c2._pointsTeamAgainst = params.getAttribute("Bonus_Neg_Sor_Team").getIntValue();
                    c3._pointsTeamFor = params.getAttribute("Bonus_Pos_Foul_Team").getIntValue();
                    c3._pointsTeamAgainst = params.getAttribute("Bonus_Neg_Foul_Team").getIntValue();





                    try {
                        c4._pointsTeamFor = params.getAttribute("Bonus_Pos_Pas_Team").getIntValue();
                        c4._pointsTeamAgainst = params.getAttribute("Bonus_Neg_Pas_Team").getIntValue();

                        c5._pointsTeamFor = params.getAttribute("Bonus_Pos_Int_Team").getIntValue();
                        c5._pointsTeamAgainst = params.getAttribute("Bonus_Neg_Int_Team").getIntValue();





                    } catch (NullPointerException npe) {
                    }

                    _params._victory_points_team = params.getAttribute("Victory_Team").getIntValue();
                    _params._draw_points_team = params.getAttribute("Draw_Team").getIntValue();
                    _params._lost_points_team = params.getAttribute("Lost_Team").getIntValue();
                    _params._ranking1_team = params.getAttribute("Rank1_Team").getIntValue();
                    _params._ranking2_team = params.getAttribute("Rank2_Team").getIntValue();
                    _params._ranking3_team = params.getAttribute("Rank3_Team").getIntValue();
                    _params._ranking4_team = params.getAttribute("Rank4_Team").getIntValue();
                    _params._ranking5_team = params.getAttribute("Rank5_Team").getIntValue();




                } catch (NullPointerException ne2) {
                }
                try {
                    _params._enableClans = params.getAttribute("ActvateClans").getBooleanValue();
                    _params._avoidClansFirstMatch = params.getAttribute("AvoidFirstMatch").getBooleanValue();
                    _params._avoidClansMatch = params.getAttribute("AvoidMatch").getBooleanValue();
                    _params._teamMatesClansNumber = params.getAttribute("ClanTeammatesNumber").getIntValue();




                } catch (NullPointerException ne3) {
                }
            }

            List clans = Root.getChildren("Clan");
            Iterator h = clans.iterator();
            _clans.clear();
            HashMap<String, Clan> clanMap = new HashMap();




            while (h.hasNext()) {
                Element clan = (Element) h.next();
                Clan c = new Clan(clan.getAttributeValue("Name"));
                _clans.addElement(c);
                clanMap.put(c._name, c);




            }

            List coachs = Root.getChildren("Coach");
            Iterator i = coachs.iterator();
            _coachs.clear();
            HashMap<String, Coach> map = new HashMap();




            while (i.hasNext()) {
                Element coach = (Element) i.next();
                Coach c = new Coach();
                c._name = coach.getAttributeValue("Name");
                c._team = coach.getAttributeValue("Team");
                c._roster = new Roster(coach.getAttributeValue("Roster"));
                c._naf = coach.getAttribute("NAF").getIntValue();
                c._rank = coach.getAttribute("Rank").getIntValue();
                c._clan = clanMap.get(coach.getAttributeValue("Clan"));




                if (c._clan == null) {
                    if (_clans.size() == 0) {
                        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
                        _clans.add(new Clan(bundle.getString("NoneKey")));
                    }
                    c._clan = _clans.get(0);

                }
                _coachs.add(c);
                map.put(c._name, c);
            }

            List teams = Root.getChildren("Team");
            Iterator l = teams.iterator();
            _teams.clear();

            while (l.hasNext()) {
                Element team = (Element) l.next();
                Team t = new Team();
                t._name = team.getAttributeValue("Name");
                List coachs2 = team.getChildren("Coach");
                Iterator m = coachs2.iterator();
                t._coachs.clear();

                while (m.hasNext()) {
                    Element coach = (Element) m.next();
                    Coach c = map.get(coach.getAttribute("Name").getValue());
                    c._teamMates = t;
                    t._coachs.add(c);

                }
                _teams.add(t);




            }

            List rounds = Root.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND"));
            Iterator j = rounds.iterator();
            _rounds.clear();




            while (j.hasNext()) {
                Element round = (Element) j.next();
                Round r = new Round();
                String date = round.getAttributeValue("Date");
                try {
                    r._heure = format.parse(date);
                } catch (ParseException e) {
                }
                List matchs = round.getChildren("Match");
                Iterator k = matchs.iterator();
                r._matchs.clear();
                while (k.hasNext()) {
                    Element match = (Element) k.next();
                    Match m = new Match();
                    String coach1 = match.getAttribute("Coach1").getValue();
                    String coach2 = match.getAttribute("Coach2").getValue();
                    m._coach1 = map.get(coach1);
                    m._coach2 = map.get(coach2);
                    for (int cpt = 0; cpt
                            < _coachs.size(); cpt++) {
                        if (c1.equals(_coachs.get(cpt)._name)) {
                            m._coach1 = _coachs.get(cpt);
                            break;
                        }
                    }
                    for (int cpt = 0; cpt
                            < _coachs.size(); cpt++) {
                        if (c2.equals(_coachs.get(cpt)._name)) {
                            m._coach2 = _coachs.get(cpt);
                            break;

                        }
                    }
                    Value v1 = new Value(c1);
                    v1._value1 = match.getAttribute("Td1").getIntValue();
                    v1._value2 = match.getAttribute("Td2").getIntValue();
                    m._values.put(c1, v1);

                    Value v2 = new Value(c2);
                    v2._value1 = match.getAttribute("Sor1").getIntValue();
                    v2._value2 = match.getAttribute("Sor2").getIntValue();
                    m._values.put(c2, v2);

                    Value v3 = new Value(c3);
                    v3._value1 = match.getAttribute("Foul1").getIntValue();
                    v3._value2 = match.getAttribute("Foul2").getIntValue();
                    m._values.put(c3, v3);

                    Value v4 = new Value(c4);
                    Value v5 = new Value(c5);

                    try {
                        v4._value1 = match.getAttribute("Pas1").getIntValue();
                        v4._value2 = match.getAttribute("Pas2").getIntValue();

                        v5._value1 = match.getAttribute("Int1").getIntValue();
                        v5._value2 = match.getAttribute("Int2").getIntValue();


                    } catch (NullPointerException npe) {
                    }
                    m._values.put(c4, v4);
                    m._values.put(c5, v5);

                    m._coach1._matchs.add(m);
                    m._coach2._matchs.add(m);

                    r._matchs.add(m);




                }

                _rounds.add(r);




            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());




        }
    }

    protected void LoadXMLv3(Element racine) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            List ros = racine.getChildren("Roster");
            if (ros != null) {
                if (ros.size() > 0) {
                    Roster.Rosters = new Vector<String>();
                    Iterator it_ros = ros.iterator();
                    while (it_ros.hasNext()) {
                        Element r = (Element) it_ros.next();
                        Roster.Rosters.add(r.getAttributeValue("Name"));
                    }
                } else {
                    Roster.initCollection();
                }
            } else {
                Roster.initCollection();
            }
        } catch (NullPointerException ne) {
            Roster.initCollection();
        }


        /* Utilisateur */
        Element params = racine.getChild("Parameters");

        try {
            if (params != null) {
                _params._tournament_orga = params.getAttribute("Organizer").getValue();
                _params._tournament_name = params.getAttribute("Name").getValue();

                _params._victory_points = params.getAttribute("Victory").getIntValue();
                _params._large_victory_points = params.getAttribute("Large_Victory").getIntValue();
                _params._draw_points = params.getAttribute("Draw").getIntValue();
                _params._lost_points = params.getAttribute("Lost").getIntValue();
                _params._little_lost_points = params.getAttribute("Little_Lost").getIntValue();
                _params._ranking1 = params.getAttribute("Rank1").getIntValue();
                _params._ranking2 = params.getAttribute("Rank2").getIntValue();
                _params._ranking3 = params.getAttribute("Rank3").getIntValue();
                _params._ranking4 = params.getAttribute("Rank4").getIntValue();
                _params._ranking5 = params.getAttribute("Rank5").getIntValue();




                try {
                    _params._large_victory_gap = params.getAttribute("Large_Victory_Gap").getIntValue();
                    _params._little_lost_gap = params.getAttribute("Little_Lost_Gap").getIntValue();
                    _params._place = params.getAttribute("Place").getValue();
                    _params._teamTournament = params.getAttribute("ByTeam").getBooleanValue();
                    _params._teamMatesNumber = params.getAttribute("TeamMates").getIntValue();
                    _params._teamPairing = params.getAttribute("TeamPairing").getIntValue();
                    _params._teamIndivPairing = params.getAttribute("TeamIndivPairing").getIntValue();
                    _params._team_victory_points = params.getAttribute("TeamVictoryPoints").getIntValue();
                    _params._teamIndivPairing = params.getAttribute("TeamIndivPairing").getIntValue();
                    _params._team_victory_only = params.getAttribute("TeamVictoryOnly").getBooleanValue();





                    try {
                        _params._date = format.parse(params.getAttribute("Date").getValue());




                    } catch (ParseException pe) {
                    }

                    _params._groupsEnable = params.getAttribute("GroupEnable").getBooleanValue();

                } catch (NullPointerException ne) {
                    _params._large_victory_gap = 3;
                    _params._little_lost_gap = 1;
                    _params._place = "";
                    _params._teamTournament = false;
                    _params._teamMatesNumber = 6;
                    _params._teamPairing = 1;
                    _params._teamIndivPairing = 0;
                }
                try {
                    _params._victory_points_team = params.getAttribute("Victory_Team").getIntValue();
                    _params._draw_points_team = params.getAttribute("Draw_Team").getIntValue();
                    _params._lost_points_team = params.getAttribute("Lost_Team").getIntValue();
                    _params._ranking1_team = params.getAttribute("Rank1_Team").getIntValue();
                    _params._ranking2_team = params.getAttribute("Rank2_Team").getIntValue();
                    _params._ranking3_team = params.getAttribute("Rank3_Team").getIntValue();
                    _params._ranking4_team = params.getAttribute("Rank4_Team").getIntValue();
                    _params._ranking5_team = params.getAttribute("Rank5_Team").getIntValue();
                } catch (NullPointerException ne2) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ne2.getLocalizedMessage());
                }

                try {
                    _params._enableClans = params.getAttribute("ActvateClans").getBooleanValue();
                    _params._avoidClansFirstMatch = params.getAttribute("AvoidFirstMatch").getBooleanValue();
                    _params._avoidClansMatch = params.getAttribute("AvoidMatch").getBooleanValue();
                    _params._teamMatesClansNumber = params.getAttribute("ClanTeammatesNumber").getIntValue();

                } catch (NullPointerException ne3) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ne3.getLocalizedMessage());
                }
            }

            List criterias = params.getChildren("Criteria");
            Iterator cr = criterias.iterator();
            _params._criterias.removeAllElements();

            while (cr.hasNext()) {
                Element criteria = (Element) cr.next();
                Criteria crit = new Criteria(criteria.getAttributeValue("Name"));
                crit._pointsFor = criteria.getAttribute("PointsFor").getIntValue();
                crit._pointsTeamFor = criteria.getAttribute("PointsTeamFor").getIntValue();
                crit._pointsAgainst = criteria.getAttribute("PointsAgainst").getIntValue();
                crit._pointsTeamAgainst = criteria.getAttribute("PointsTeamAgainst").getIntValue();
                _params._criterias.add(crit);
            }

            try {
                List groups = racine.getChildren("Group");
                Iterator gr = groups.iterator();
                _groups.removeAllElements();

                while (gr.hasNext()) {
                    Element group = (Element) gr.next();
                    Group groupe = new Group(group.getAttributeValue("Name"));

                    List rosters = group.getChildren("Roster");
                    Iterator ro = rosters.iterator();
                    while (ro.hasNext()) {
                        Element roster = (Element) ro.next();
                        Roster rost = new Roster(roster.getAttributeValue("Name"));
                        groupe._rosters.add(rost);
                    }
                    _groups.add(groupe);
                }
            } catch (NullPointerException npe) {
            }

            List clans = racine.getChildren("Clan");
            Iterator h = clans.iterator();
            _clans.clear();
            HashMap<String, Clan> clanMap = new HashMap();

            while (h.hasNext()) {
                Element clan = (Element) h.next();
                Clan c = new Clan(clan.getAttributeValue("Name"));
                _clans.addElement(c);
                clanMap.put(c._name, c);
            }

            List coachs = racine.getChildren("Coach");
            Iterator i = coachs.iterator();
            _coachs.clear();
            HashMap<String, Coach> map = new HashMap();

            while (i.hasNext()) {
                Element coach = (Element) i.next();
                Coach c = new Coach();
                c._name = coach.getAttributeValue("Name");
                c._team = coach.getAttributeValue("Team");
                c._roster = new Roster(coach.getAttributeValue("Roster"));
                c._naf = coach.getAttribute("NAF").getIntValue();
                c._rank = coach.getAttribute("Rank").getIntValue();
                c._clan = clanMap.get(coach.getAttributeValue("Clan"));

                if (c._clan == null) {
                    if (_clans.size() == 0) {
                        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
                        _clans.add(new Clan(bundle.getString("NoneKey")));

                    }
                    c._clan = _clans.get(0);

                }
                _coachs.add(c);
                c._matchs.removeAllElements();
                map.put(c._name, c);
            }

            List teams = racine.getChildren("Team");
            Iterator l = teams.iterator();
            _teams.clear();
            while (l.hasNext()) {
                Element team = (Element) l.next();
                Team t = new Team();
                t._name = team.getAttributeValue("Name");
                List coachs2 = team.getChildren("Coach");
                Iterator m = coachs2.iterator();
                t._coachs.clear();





                while (m.hasNext()) {
                    Element coach = (Element) m.next();
                    Coach c = map.get(coach.getAttribute("Name").getValue());
                    c._teamMates = t;
                    t._coachs.add(c);




                }
                _teams.add(t);




            }

            List rounds = racine.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND"));
            Iterator j = rounds.iterator();
            _rounds.clear();




            while (j.hasNext()) {
                Element round = (Element) j.next();
                Round r = new Round();
                String date = round.getAttributeValue("Date");




                try {
                    r._heure = format.parse(date);




                } catch (ParseException e) {
                }

                List matchs = round.getChildren("Match");
                Iterator k = matchs.iterator();
                r._matchs.clear();





                while (k.hasNext()) {
                    Element match = (Element) k.next();
                    Match m = new Match();
                    String c1 = match.getAttribute("Coach1").getValue();
                    String c2 = match.getAttribute("Coach2").getValue();
                    m._coach1 = map.get(c1);
                    m._coach2 = map.get(c2);




                    for (int cpt = 0; cpt
                            < _coachs.size(); cpt++) {
                        if (c1.equals(_coachs.get(cpt)._name)) {
                            m._coach1 = _coachs.get(cpt);




                            break;




                        }
                    }
                    for (int cpt = 0; cpt
                            < _coachs.size(); cpt++) {
                        if (c2.equals(_coachs.get(cpt)._name)) {
                            m._coach2 = _coachs.get(cpt);




                            break;




                        }
                    }

                    m._coach1._matchs.add(m);
                    m._coach2._matchs.add(m);

                    List values = match.getChildren("Value");
                    Iterator v = values.iterator();




                    while (v.hasNext()) {
                        Element val = (Element) v.next();
                        Criteria crit = null;




                        for (int cpt = 0; cpt
                                < _params._criterias.size(); cpt++) {
                            Criteria criteria = _params._criterias.get(cpt);
                            String tmp = val.getAttribute("Name").getValue();




                            if (criteria._name.equals(tmp)) {
                                crit = criteria;




                            }
                        }
                        Value value = new Value(crit);
                        value._value1 = val.getAttribute("Value1").getIntValue();
                        value._value2 = val.getAttribute("Value2").getIntValue();
                        m._values.put(crit, value);




                    }

                    r._matchs.add(m);




                }

                _rounds.add(r);




            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());




        }

    }

    public void loadXML(java.io.File file) {
        SAXBuilder sxb = new SAXBuilder();





        try {
            org.jdom.Document document = sxb.build(file);
            Element racine = document.getRootElement();





            try {
                String version = racine.getAttributeValue("Version");




                if (Integer.parseInt(version) == 3) {
                    LoadXMLv3(racine);




                }
            } catch (NumberFormatException npe) {
                LoadXMLv2(racine);




            }

        } catch (JDOMException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getLocalizedMessage());




        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());




        }
    }

    public void generateFirstRound(int choice) {
        _rounds.clear();
        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r._heure = cal.getTime();

        /*
         * Si tournoi individuel
         */
        if (!_params._teamTournament) {
            if (choice == 2) {
                Vector<Coach> coachs = new Vector<Coach>(_coachs);
                while (coachs.size() > 0) {
                    /*
                     * Choisir un coach pour l'adversaire.
                     */
                    Match m = new Match();
                    m._coach1 = coachs.get(0);
                    coachs.remove(m._coach1);
                    Vector<Coach> possibleCoachs = new Vector(coachs);

                    if (m._coach1._clan != _clans.get(0)) {
                        if ((_params._enableClans) && ((_params._avoidClansFirstMatch) || (_params._avoidClansMatch))) {
                            for (int i = 0; i
                                    < possibleCoachs.size(); i++) {
                                if (possibleCoachs.get(i)._clan._name.equals(m._coach1._clan._name)) {
                                    possibleCoachs.remove(i);
                                    i--;
                                }
                            }
                        }
                        if (possibleCoachs.size() == 0) {
                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OnlyOneClanCoachKey"));
                            possibleCoachs = coachs;
                        }
                    }

                    Object[] possibilities = new Object[possibleCoachs.size()];
                    for (int i = 0; i
                            < possibleCoachs.size(); i++) {
                        String tmpString = possibleCoachs.get(i)._name + " (" + possibleCoachs.get(i)._roster._name + ")";
                        if (_params._enableClans) {
                            tmpString = tmpString + " (" + possibleCoachs.get(i)._clan._name + ")";
                        }
                        possibilities[i] = tmpString;
                    }
                    ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language");
                    String coachString = m._coach1._name + " (" + m._coach1._roster._name + ")";
                    if (_params._enableClans) {
                        coachString = coachString + " (" + m._coach1._clan._name + ")";
                    }

                    String opp = null;

                    while (opp == null) {
                        opp = (String) JOptionPane.showInputDialog(
                                MainFrame.getMainFrame(),
                                bundle.getString("ChooseOpponentFor")
                                + coachString,
                                bundle.getString("ChoosOpponent"),
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                possibilities,
                                possibilities[0]);
                    }

                    for (int i = 0; i
                            < coachs.size(); i++) {
                        String tmpString = possibleCoachs.get(i)._name + " (" + possibleCoachs.get(i)._roster._name + ")";
                        if (_params._enableClans) {
                            tmpString = tmpString + " (" + possibleCoachs.get(i)._clan._name + ")";
                        }

                        if (opp.equals(tmpString)) {
                            m._coach2 = possibleCoachs.get(i);
                            coachs.remove(m._coach2);
                            break;
                        }
                    }
                    r._matchs.add(m);
                }
            } else {

                boolean NotOK = true;
                int counter = 0;

                while ((NotOK) && (counter < 500)) {

                    NotOK = false;
                    Vector<Coach> shuffle = new Vector<Coach>(_coachs);
                    if (choice == 0) /* Aléatoire */ {
                        Collections.shuffle(shuffle);
                    }

                    r._matchs.removeAllElements();

                    while (shuffle.size() > 0) {
                        Match m = new Match();
                        m._coach1 = shuffle.get(0);
                        shuffle.remove(m._coach1);
                        if (m._coach1._clan != _clans.get(0)) {
                            if ((_params._enableClans) && ((_params._avoidClansFirstMatch) || (_params._avoidClansMatch))) {
                                int j = 0;
                                while (j < shuffle.size() && (m._coach1._clan == shuffle.get(j)._clan)) {
                                    j++;
                                }
                                if (j == shuffle.size()) {
                                    NotOK = true;
                                    j = 0;
                                }
                                m._coach2 = shuffle.get(j);
                                shuffle.remove(j);
                            } else {
                                m._coach2 = shuffle.get(0);
                                shuffle.remove(0);
                            }
                        } else {
                            m._coach2 = shuffle.get(0);
                            shuffle.remove(0);
                        }
                        r._matchs.add(m);
                    }

                    /*  for (int i = 0; i < shuffle.size() / 2; i++) {
                    Match m = new Match();
                    m._coach1 = shuffle.get(2 * i);
                    m._coach2 = shuffle.get(2 * i + 1);
                    r._matchs.add(m);
                    }*/
                    counter++;
                }

                /*
                 * Check the clans
                 *
                 */
                boolean bSameClan = false;
                for (int i = 0; i < r._matchs.size(); i++) {
                    Match m = r._matchs.get(i);
                    if (m._coach1._clan != _clans.get(0)) {
                        if ((_params._enableClans) && ((_params._avoidClansFirstMatch) || (_params._avoidClansMatch))) {
                            if (m._coach1._clan == m._coach2._clan) {
                                bSameClan = true;
                                break;
                            }
                        }
                    }
                }

                if (bSameClan) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OnlyOneClanCoachKey"));
                }


                if (counter == 500) {
                    if ((_params._enableClans) && ((_params._avoidClansFirstMatch) || (_params._avoidClansMatch))) {
                        Vector<Coach> shuffle = new Vector<Coach>(_coachs);
                        if (choice == 0) /* Aléatoire */ {
                            Collections.shuffle(shuffle);
                        }

                        r._matchs.removeAllElements();

                        while (shuffle.size() > 0) {
                            Match m = new Match();
                            m._coach1 = shuffle.get(0);
                            shuffle.remove(m._coach1);
                            if (m._coach1._clan != _clans.get(0)) {
                                if ((_params._enableClans) && ((_params._avoidClansFirstMatch) || (_params._avoidClansMatch))) {
                                    int j = 0;
                                    while (j < shuffle.size() && (m._coach1._clan == shuffle.get(j)._clan)) {
                                        j++;
                                    }
                                    if (j == shuffle.size()) {

                                        j = 0;
                                    }
                                    m._coach2 = shuffle.get(j);
                                    shuffle.remove(j);
                                } else {
                                    m._coach2 = shuffle.get(0);
                                    shuffle.remove(0);
                                }
                            } else {
                                m._coach2 = shuffle.get(0);
                                shuffle.remove(0);
                            }
                            r._matchs.add(m);
                        }
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CanMatchKey"));
                        Vector<Coach> shuffle = new Vector<Coach>(_coachs);
                        if (choice == 0) /* Aléatoire */ {
                            Collections.shuffle(shuffle);
                        }
                        for (int i = 0; i
                                < shuffle.size() / 2; i++) {
                            Match m = new Match();
                            m._coach1 = shuffle.get(2 * i);
                            m._coach2 = shuffle.get(2 * i + 1);
                            r._matchs.add(m);
                        }
                    }
                }
            }
            _rounds.add(r);

        } /**
         * Si tournoi ByTeam
         */
        else {

            Vector<Team> teams1 = new Vector<Team>();
            Vector<Team> teams2 = new Vector<Team>();

            if (choice == 2) {
                Vector<Team> teams = new Vector<Team>(_teams);
                while (teams.size() > 0) {

                    Team team1 = teams.get(0);
                    teams1.add(team1);
                    teams.remove(team1);

                    Object[] possibilities = new Object[teams.size()];
                    for (int i = 0; i
                            < teams.size(); i++) {
                        possibilities[i] = teams.get(i)._name;
                    }

                    String opp = (String) JOptionPane.showInputDialog(
                            MainFrame.getMainFrame(),
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChooseOpponentFor") + ": "
                            + team1._name,
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChooseOpponent"),
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            possibilities,
                            possibilities[0]);
                    for (int i = 0; i
                            < teams.size(); i++) {
                        if (opp.equals(teams.get(i)._name)) {
                            Team team2 = teams.get(i);
                            teams2.add(team2);
                            teams.remove(team2);
                            break;
                        }
                    }
                }
            } else {
                /*
                 * Le premier appariement est aléatoire ByTeam
                 */
                Vector<Team> shuffle = new Vector<Team>(_teams);
                if (choice == 0) /* Aléatoire */ {
                    Collections.shuffle(shuffle);
                }
                for (int i = 0; i
                        < shuffle.size() / 2; i++) {
                    Team team1 = shuffle.get(2 * i);
                    Team team2 = shuffle.get(2 * i + 1);
                    teams1.add(team1);
                    teams2.add(team2);
                }
            }

            if (_params._teamIndivPairing == 1) {
                jdgTeamPairing jdg = new jdgTeamPairing(MainFrame.getMainFrame(), true, teams1, teams2, r);
                jdg.setVisible(true);
            } else {
                for (int i = 0; i
                        < teams1.size(); i++) {
                    Team team1 = teams1.get(i);
                    Team team2 = teams2.get(i);
                    Vector<Coach> shuffle2 = new Vector<Coach>(team2._coachs);
                    if (choice == 0) /* Aléatoire */ {
                        Collections.shuffle(shuffle2);
                    }
                    for (int j = 0; j
                            < shuffle2.size(); j++) {
                        Match m = new Match();
                        m._coach1 = team1._coachs.get(j);
                        m._coach2 = shuffle2.get(j);
                        r._matchs.add(m);
                    }
                }
            }
            _rounds.add(r);
        }
        if (_rounds.size() > 0) {
            for (int i = 0; i
                    < _rounds.get(0)._matchs.size(); i++) {
                Match m = _rounds.get(0)._matchs.get(i);
                m._coach1._matchs = new Vector<Match>();
                m._coach1._matchs.add(m);
                m._coach2._matchs = new Vector<Match>();
                m._coach2._matchs.add(m);

                for (int j = 0; j
                        < _params._criterias.size(); j++) {
                    Criteria criteria = _params._criterias.get(j);
                    Value val = new Value(criteria);
                    if (j == 0) {
                        val._value1 = -1;
                        val._value2 = -1;
                    }
                    m._values.put(criteria, val);
                }
            }
        }
        String filename = Tournament.getTournament().getParams()._tournament_name;
        filename = filename + "." + Tournament.getTournament().getRounds().size();
        Date date = new Date();
        filename = filename + "." + Long.toString(date.getTime()) + ".xml";
        File file = new File(filename);

        Tournament.getTournament().saveXML(file);
    }
}
