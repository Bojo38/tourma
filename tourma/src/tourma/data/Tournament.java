/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import tourma.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import java.util.Vector;
import javax.swing.JOptionPane;
import org.jdom.Element;
import org.jdom.JDOMException;
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

    private Tournament() {
        _params = new Parameters();
        _rounds = new Vector<Round>();
        _coachs = new Vector<Coach>();
        _clans = new Vector<Clan>();
        _teams = new Vector();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        _clans.add(new Clan(bundle.getString("NoneKey")));
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

    public Parameters getParams() {
        return _params;
    }

    public Vector<Coach> getCoachs() {
        return _coachs;
    }

    public Vector<Team> getTeams() {
        return _teams;
    }

    public Vector<Round> getRounds() {
        return _rounds;
    }

    public void saveXML(java.io.File file) {
        Element document = new Element("Tournament");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Element params = new Element("Parameters");
        params.setAttribute("Organizer", _params._tournament_orga);
        params.setAttribute("Name", _params._tournament_name);
        params.setAttribute("Date", format.format(_params._date));
        params.setAttribute("Place", _params._tournament_name);
        params.setAttribute("Bonus_Neg_Foul", Integer.toString(_params._bonus_neg_foul_points));
        params.setAttribute("Bonus_Pos_Foul", Integer.toString(_params._bonus_foul_points));
        params.setAttribute("Bonus_Neg_Td", Integer.toString(_params._bonus_neg_td_points));
        params.setAttribute("Bonus_Pos_Td", Integer.toString(_params._bonus_td_points));
        params.setAttribute("Bonus_Neg_Sor", Integer.toString(_params._bonus_neg_sor_points));
        params.setAttribute("Bonus_Pos_Sor", Integer.toString(_params._bonus_sor_points));

        params.setAttribute("Bonus_Neg_Sor", Integer.toString(_params._bonus_neg_sor_points));
        params.setAttribute("Bonus_Pos_Sor", Integer.toString(_params._bonus_sor_points));

        params.setAttribute("Bonus_Neg_Pas", Integer.toString(_params._bonus_neg_pas_points));
        params.setAttribute("Bonus_Pos_Pas", Integer.toString(_params._bonus_pas_points));
        params.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_NEG_INT"), Integer.toString(_params._bonus_neg_int_points));
        params.setAttribute("Bonus_Pos_Int", Integer.toString(_params._bonus_int_points));

        params.setAttribute("Bonus_Neg_Foul_Team", Integer.toString(_params._bonus_neg_foul_points_team));
        params.setAttribute("Bonus_Pos_Foul_Team", Integer.toString(_params._bonus_foul_points_team));
        params.setAttribute("Bonus_Neg_Td_Team", Integer.toString(_params._bonus_neg_td_points_team));
        params.setAttribute("Bonus_Pos_Td_Team", Integer.toString(_params._bonus_td_points_team));
        params.setAttribute("Bonus_Neg_Sor_Team", Integer.toString(_params._bonus_neg_sor_points_team));
        params.setAttribute("Bonus_Pos_Sor_Team", Integer.toString(_params._bonus_sor_points_team));

        params.setAttribute("Bonus_Neg_Pas_Team", Integer.toString(_params._bonus_neg_pas_points_team));
        params.setAttribute("Bonus_Pos_Pas_Team", Integer.toString(_params._bonus_pas_points_team));
        params.setAttribute("Bonus_Neg_Int_Team", Integer.toString(_params._bonus_neg_int_points_team));
        params.setAttribute("Bonus_Pos_Int_Team", Integer.toString(_params._bonus_int_points_team));

        params.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VICTORY"), Integer.toString(_params._victory_points));
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

        /*
         * Clan parameters 
         */
        params.setAttribute("ActvateClans", Boolean.toString(_params._enableClans));
        params.setAttribute("AvoidFirstMatch", Boolean.toString(_params._avoidClansFirstMatch));
        params.setAttribute("AvoidMatch", Boolean.toString(_params._avoidClansMatch));
        params.setAttribute("ClanTeammatesNumber", Integer.toString(_params._teamMatesClansNumber));

        document.addContent(params);

        for (int i = 0; i < _clans.size(); i++) {
            Element clan = new Element("Clan");
            clan.setAttribute("Name", _clans.get(i)._name);
            document.addContent(clan);
        }

        for (int i = 0; i < _coachs.size(); i++) {
            Element coach = new Element("Coach");
            coach.setAttribute("Name", _coachs.get(i)._name);
            coach.setAttribute("Team", _coachs.get(i)._team);
            coach.setAttribute("Roster", _coachs.get(i)._roster);
            coach.setAttribute("NAF", Integer.toString(_coachs.get(i)._naf));
            coach.setAttribute("Rank", Integer.toString(_coachs.get(i)._rank));
            coach.setAttribute("Clan", _coachs.get(i)._clan._name);
            document.addContent(coach);
        }

        for (int i = 0; i < _teams.size(); i++) {
            Element team = new Element("Team");
            team.setAttribute("Name", _teams.get(i)._name);
            for (int j = 0; j < _teams.get(i)._coachs.size(); j++) {
                Element coach = new Element("Coach");
                coach.setAttribute("Name", _teams.get(i)._coachs.get(j)._name);
                team.addContent(coach);
            }
            document.addContent(team);
        }



        for (int i = 0; i < _rounds.size(); i++) {
            Element round = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND"));
            round.setAttribute("Date", format.format(_rounds.get(i)._heure));

            for (int j = 0; j < _rounds.get(i)._matchs.size(); j++) {
                Element match = new Element("Match");
                match.setAttribute("Coach1", _rounds.get(i)._matchs.get(j)._coach1._name);
                match.setAttribute("Coach2", _rounds.get(i)._matchs.get(j)._coach2._name);
                match.setAttribute("Td1", Integer.toString(_rounds.get(i)._matchs.get(j)._td1));
                match.setAttribute("Td2", Integer.toString(_rounds.get(i)._matchs.get(j)._td2));
                match.setAttribute("Sor1", Integer.toString(_rounds.get(i)._matchs.get(j)._sor1));
                match.setAttribute("Sor2", Integer.toString(_rounds.get(i)._matchs.get(j)._sor2));
                match.setAttribute("Foul1", Integer.toString(_rounds.get(i)._matchs.get(j)._foul1));
                match.setAttribute("Foul2", Integer.toString(_rounds.get(i)._matchs.get(j)._foul2));
                match.setAttribute("Pas1", Integer.toString(_rounds.get(i)._matchs.get(j)._pas1));
                match.setAttribute("Pas2", Integer.toString(_rounds.get(i)._matchs.get(j)._pas2));
                match.setAttribute("Int1", Integer.toString(_rounds.get(i)._matchs.get(j)._int1));
                match.setAttribute("Int2", Integer.toString(_rounds.get(i)._matchs.get(j)._int2));
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

        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AMAZONE"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AMAZONS");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BAS FONDS"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UNDERWORLD");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHAOS"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHAOS");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ELFE"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ELVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ELFE SYLVAIN"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WOOD ELVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ELFE NOIR"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DARK ELVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GOBELIN"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GOBLINS");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HALFLING"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HALFLINGS");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HAUT ELFE"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HIGH ELVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HOMME LÉZARD"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LIZARDMEN");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HUMAIN"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HUMANS");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("KHEMRI"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("KHEMRI");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MORT-VIVANT"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UNDEAD");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAIN"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DWARVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAIN DU CHAOS"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHAOS DWARVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NECROMANTIQUE"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NECROMANTIC");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NORDIQUE"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NORSE");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NURGLE"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NURGLE'S ROTTERS");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OGRE"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OGRES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ORQUE"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ORC");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PACTE CHAOTIQUE"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHAOS PACT");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SKAVEN"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SKAVEN");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SLANN"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SLANN");
        }
        if (source.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VAMPIRE"))) {
            return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VAMPIRES");
        }
        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UNKNOWN");
    }

    public void exportResults(java.io.File file) {
        Element document = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAFREPORT"));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");

        Element orgas = new Element("organiser");
        orgas.setText(_params._tournament_orga);

        document.addContent(orgas);

        Element coaches = new Element("coaches");

        for (int i = 0; i < _coachs.size(); i++) {
            if (_coachs.get(i)._naf > 0) {
                Element coach = new Element("coach");
                Element name = new Element("name");
                Element team = new Element("team");
                name.setText(_coachs.get(i)._name);
                String roster = getRosterTranslation(_coachs.get(i)._roster);
                team.setText(roster);
                coach.addContent(name);
                coach.addContent(team);
                coaches.addContent(coach);
            }
        }

        document.addContent(coaches);


        for (int i = 0; i < _rounds.size(); i++) {
            for (int j = 0; j < _rounds.get(i)._matchs.size(); j++) {
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
                    td1.setText(Integer.toString(_rounds.get(i)._matchs.get(j)._td1));
                    td2.setText(Integer.toString(_rounds.get(i)._matchs.get(j)._td2));
                    bh1.setText(Integer.toString(_rounds.get(i)._matchs.get(j)._sor1));
                    bh2.setText(Integer.toString(_rounds.get(i)._matchs.get(j)._sor2));
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
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        }
    }

    public void loadXML(java.io.File file) {
        SAXBuilder sxb = new SAXBuilder();
        try {
            org.jdom.Document document = sxb.build(file);
            Element racine = document.getRootElement();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            /* Utilisateur */
            Element params = racine.getChild("Parameters");
            if (params != null) {
                _params._tournament_orga = params.getAttribute("Organizer").getValue();
                _params._tournament_name = params.getAttribute("Name").getValue();
                _params._bonus_foul_points = params.getAttribute("Bonus_Pos_Foul").getIntValue();
                _params._bonus_neg_foul_points = params.getAttribute("Bonus_Neg_Foul").getIntValue();
                _params._bonus_td_points = params.getAttribute("Bonus_Pos_Td").getIntValue();
                _params._bonus_neg_td_points = params.getAttribute("Bonus_Neg_Td").getIntValue();
                _params._bonus_sor_points = params.getAttribute("Bonus_Pos_Sor").getIntValue();
                _params._bonus_neg_sor_points = params.getAttribute("Bonus_Neg_Sor").getIntValue();

                try {
                    _params._bonus_pas_points = params.getAttribute("Bonus_Pos_Pas").getIntValue();
                    _params._bonus_neg_pas_points = params.getAttribute("Bonus_Neg_Pas").getIntValue();
                    _params._bonus_int_points = params.getAttribute("Bonus_Pos_Int").getIntValue();
                    _params._bonus_neg_int_points = params.getAttribute("Bonus_Neg_Int").getIntValue();
                } catch (NullPointerException npe) {
                    _params._bonus_pas_points = 0;
                    _params._bonus_neg_pas_points = 0;
                    _params._bonus_int_points = 0;
                    _params._bonus_neg_int_points = 0;
                }

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
                    _params._bonus_foul_points_team = params.getAttribute("Bonus_Pos_Foul_Team").getIntValue();
                    _params._bonus_neg_foul_points_team = params.getAttribute("Bonus_Neg_Foul_Team").getIntValue();
                    _params._bonus_td_points_team = params.getAttribute("Bonus_Pos_Td_Team").getIntValue();
                    _params._bonus_neg_td_points_team = params.getAttribute("Bonus_Neg_Td_Team").getIntValue();
                    _params._bonus_sor_points_team = params.getAttribute("Bonus_Pos_Sor_Team").getIntValue();
                    _params._bonus_neg_sor_points_team = params.getAttribute("Bonus_Neg_Sor_Team").getIntValue();
                    _params._bonus_pas_points_team = params.getAttribute("Bonus_Pos_Pas_Team").getIntValue();
                    _params._bonus_neg_pas_points_team = params.getAttribute("Bonus_Neg_Pas_Team").getIntValue();
                    _params._bonus_int_points_team = params.getAttribute("Bonus_Pos_Int_Team").getIntValue();
                    _params._bonus_neg_int_points_team = params.getAttribute("Bonus_Neg_Int_Team").getIntValue();
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
                c._roster = coach.getAttributeValue("Roster");
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
                    /*for (int cpt = 0; cpt < _coachs.size(); cpt++) {
                    if (c1.equals(_coachs.get(cpt)._name)) {
                    m._coach1 = _coachs.get(cpt);
                    break;
                    }
                    }
                    for (int cpt = 0; cpt < _coachs.size(); cpt++) {
                    if (c2.equals(_coachs.get(cpt)._name)) {
                    m._coach2 = _coachs.get(cpt);
                    break;
                    }
                    }*/
                    m._foul1 = match.getAttribute("Foul1").getIntValue();
                    m._foul2 = match.getAttribute("Foul2").getIntValue();
                    m._sor1 = match.getAttribute("Sor1").getIntValue();
                    m._sor2 = match.getAttribute("Sor2").getIntValue();
                    m._td1 = match.getAttribute("Td1").getIntValue();
                    m._td2 = match.getAttribute("Td2").getIntValue();
                    try {
                        m._pas1 = match.getAttribute("Pas1").getIntValue();
                        m._pas2 = match.getAttribute("Pas2").getIntValue();
                        m._int1 = match.getAttribute("Int1").getIntValue();
                        m._int2 = match.getAttribute("Int2").getIntValue();
                    } catch (NullPointerException npe) {
                    }
                    r._matchs.add(m);
                }

                _rounds.add(r);
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
                            for (int i = 0; i < possibleCoachs.size(); i++) {
                                if (possibleCoachs.get(i)._clan._name.equals(m._coach1._clan._name)) {
                                    possibleCoachs.remove(i);
                                    i--;
                                }
                            }
                        }
                        if (possibleCoachs.size() == 0) {
                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OnlyOneClanCoachKey"));
                            possibleCoachs=coachs;
                        }
                    }

                    Object[] possibilities = new Object[possibleCoachs.size()];
                    for (int i = 0; i < possibleCoachs.size(); i++) {
                        String tmpString=possibleCoachs.get(i)._name + " (" + possibleCoachs.get(i)._roster + ")";
                        if (_params._enableClans)
                        {
                            tmpString=tmpString+" ("+possibleCoachs.get(i)._clan._name+")";
                        }
                        possibilities[i] = tmpString;
                    }

                    ResourceBundle bundle=java.util.ResourceBundle.getBundle("tourma/languages/language");

                    String coachString=m._coach1._name + " (" + m._coach1._roster + ")";
                    if (_params._enableClans)
                    {
                        coachString=coachString+" ("+m._coach1._clan._name+")";
                    }

                    String opp = (String) JOptionPane.showInputDialog(
                            MainFrame.getMainFrame(),
                            bundle.getString("ChooseOpponentFor")
                            + coachString,
                            bundle.getString("ChoosOpponent"),
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            possibilities,
                            possibilities[0]);

                    for (int i = 0; i < coachs.size(); i++) {
                        String tmpString=possibleCoachs.get(i)._name + " (" + possibleCoachs.get(i)._roster + ")";
                        if (_params._enableClans)
                        {
                            tmpString=tmpString+" ("+possibleCoachs.get(i)._clan._name+")";
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

                    /*
                     * Check the clans
                     *
                     */
                    for (int i = 0; i < r._matchs.size(); i++) {
                        Match m = r._matchs.get(i);
                        if (m._coach1._clan != _clans.get(0)) {
                            if ((_params._enableClans) && ((_params._avoidClansFirstMatch) || (_params._avoidClansMatch))) {
                                if (m._coach1._clan == m._coach2._clan) {
                                    NotOK = true;
                                }
                            }
                        }
                    }
                    counter++;
                }

                if (counter == 500) {
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
            _rounds.add(r);

        } /**
         * Si tournoi par équipe
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
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHOISISSEZ L'ADVERSAIRE POUR:")
                            + team1._name,
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHOISIR ADVERSAIRE"),
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
                 * Le premier appariement est aléatoire par équipe
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
    }
}
