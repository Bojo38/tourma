/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
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
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import org.jdesktop.swingx.multisplitpane.DefaultSplitPaneModel;
import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import teamma.data.Player;
import teamma.data.StarPlayer;
import teamma.data.lrb;
import tourma.tableModel.mjtAnnexRankClan;
import tourma.tableModel.mjtAnnexRankIndiv;
import tourma.tableModel.mjtAnnexRankTeam;
import tourma.tableModel.mjtRanking;
import tourma.tableModel.mjtRankingClan;
import tourma.tableModel.mjtRankingIndiv;
import tourma.tableModel.mjtRankingTeam;

/**
 *
 * @author Frederic Berger
 */
public class Tournament {

    protected Vector<Round> _rounds;
    protected Vector<Coach> _coachs;
    protected Vector<Team> _teams;
    protected Vector<Pool> _pools;
    protected Parameters _params;
    protected static Tournament _singleton;
    public boolean _roundrobin = false;
    /**
     * Clans used in the tournement
     */
    protected Vector<Clan> _clans;
    /**
     * RostersNames Groups
     */
    protected Vector<Group> _groups;

    private Tournament() {
        _params = new Parameters();
        _rounds = new Vector<Round>();
        _coachs = new Vector<Coach>();
        _clans = new Vector<Clan>();
        _teams = new Vector();
        _groups = new Vector<Group>();
        _pools = new Vector<Pool>();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        _clans.add(new Clan(bundle.getString("NoneKey")));
        Group group = new Group(bundle.getString("NoneKey"));
        _groups.add(group);

        for (int i = 0; i < RosterType.RostersNames.size(); i++) {
            group._rosters.add(new RosterType(RosterType.RostersNames.get(i)));
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

    public Coach getCoach(String input) {
        for (int i = 0; i < _coachs.size(); i++) {
            if (_coachs.get(i).equals(input)) {
                return _coachs.get(i);
            }
        }
        return null;
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

    public void saveXML(java.io.File file) {
        this.saveXML(file, false);
    }

    /*    public Vector<Group> getGroups() {
     return _groups;
     }*/
    public void saveXML(java.io.File file, boolean withRanking) {
        Element document = new Element("Tournament");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        document.setAttribute("Version", "3");
        document.setAttribute("RoundRobin", Boolean.toString(_roundrobin));

        // Tounament data
        for (int i = 0; i
                < RosterType.RostersNames.size(); i++) {
            Element ros = new Element("Roster");
            ros.setAttribute("Name", RosterType.RostersNames.get(i));
            document.addContent(ros);
        }

        // Save parameters
        Element params = _params.getXMLElement();
        document.addContent(params);

        // Save Clans
        for (int i = 0; i < _clans.size(); i++) {
            Element clan = _clans.get(i).getXMLElement();
            document.addContent(clan);
        }

        // Save roster groups
        for (int i = 0; i < _groups.size(); i++) {
            Element group = _groups.get(i).getXMLElement();
            document.addContent(group);
        }
        
         // Save Pool 
        for (int i = 0; i < _pools.size(); i++) {
            Element pool = _pools.get(i).getXMLElement();
            document.addContent(pool);
        }

        // Save coachs
        for (int i = 0; i < _coachs.size(); i++) {
            Element coach = _coachs.get(i).getXMLElement();
            document.addContent(coach);
        }

        // Save teams
        for (int i = 0; i
                < _teams.size(); i++) {
            Element team = _teams.get(i).getXMLElement();
            document.addContent(team);
        }

        // Save rounds
        for (int i = 0; i < _rounds.size(); i++) {

            Element round = _rounds.get(i).getXMLElement();

            if (withRanking) {

                // Build list of rankings
                Vector<Ranking> rankings = new Vector<Ranking>();
                rankings.add(new Ranking("Individual", "general", "", new mjtRankingIndiv(i, this.getParams()._ranking1, this.getParams()._ranking2, this.getParams()._ranking3, this.getParams()._ranking4, this.getParams()._ranking5, this.getCoachs(), false, false)));
                if (this.getParams()._teamTournament) {
                    rankings.add(new Ranking("Team", "general", "", new mjtRankingTeam(this.getParams()._team_victory_only, i, this.getParams()._ranking1_team, this.getParams()._ranking2_team, this.getParams()._ranking3_team, this.getParams()._ranking4_team, this.getParams()._ranking5_team, this.getTeams(), false)));
                }
                if (_params._enableClans) {
                    rankings.add(new Ranking("Clan", "general", "", new mjtRankingClan(i, this.getParams()._ranking1, this.getParams()._ranking2, this.getParams()._ranking3, this.getParams()._ranking4, this.getParams()._ranking5, this.getDisplayClans(), false)));

                }
                if (_params._groupsEnable) {
                    for (int j = 0; j < _groups.size(); j++) {
                        Group g = _groups.get(j);
                        Vector<Coach> vector = new Vector<Coach>();
                        for (int k = 0; k < this.getCoachs().size(); k++) {
                            Coach c = this.getCoachs().get(k);
                            for (int l = 0; l < g._rosters.size(); l++) {
                                if (g._rosters.get(l)._name.equals(c._roster._name)) {
                                    vector.add(c);
                                    break;
                                }
                            }
                        }
                        rankings.add(new Ranking("Group", g._name, "", new mjtRankingIndiv(i, this.getParams()._ranking1, this.getParams()._ranking2, this.getParams()._ranking3, this.getParams()._ranking4, this.getParams()._ranking5, vector, false, false)));
                    }
                }
                // Annex ranking
                for (int j = 0; j < _params._criterias.size(); j++) {
                    Criteria criteria = _params._criterias.get(j);
                    rankings.add(new Ranking("Individual", criteria._name, "positive", new mjtAnnexRankIndiv(i, criteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, getCoachs(), true, this.getParams()._ranking1, this.getParams()._ranking2, this.getParams()._ranking3, this.getParams()._ranking4, this.getParams()._ranking5, _params._teamTournament, false)));
                    rankings.add(new Ranking("Individual", criteria._name, "negative", new mjtAnnexRankIndiv(i, criteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, getCoachs(), true, this.getParams()._ranking1, this.getParams()._ranking2, this.getParams()._ranking3, this.getParams()._ranking4, this.getParams()._ranking5, _params._teamTournament, false)));

                    if (_params._teamTournament) {
                        rankings.add(new Ranking("Team", criteria._name, "positive", new mjtAnnexRankTeam(i, criteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, getTeams(), true, this.getParams()._ranking1, this.getParams()._ranking2, this.getParams()._ranking3, this.getParams()._ranking4, this.getParams()._ranking5, false)));
                        rankings.add(new Ranking("Team", criteria._name, "negativs", new mjtAnnexRankTeam(i, criteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, getTeams(), true, this.getParams()._ranking1, this.getParams()._ranking2, this.getParams()._ranking3, this.getParams()._ranking4, this.getParams()._ranking5, false)));
                    }

                    if (_params._enableClans) {
                        rankings.add(new Ranking("Clan", criteria._name, "positive", new mjtAnnexRankClan(i, criteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, getClans(), true, this.getParams()._ranking1, this.getParams()._ranking2, this.getParams()._ranking3, this.getParams()._ranking4, this.getParams()._ranking5, false)));
                        rankings.add(new Ranking("Clan", criteria._name, "negative", new mjtAnnexRankClan(i, criteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, getClans(), true, this.getParams()._ranking1, this.getParams()._ranking2, this.getParams()._ranking3, this.getParams()._ranking4, this.getParams()._ranking5, false)));
                    }
                }

                // Store rankings
                for (int j = 0; j < rankings.size(); j++) {
                    Element rank = rankings.get(j).getXMLElement();
                    round.addContent(rank);
                }
            }

            document.addContent(round);
        }

        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            FileOutputStream os = new FileOutputStream(file);
            sortie.output(document, os);
            os.close();


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

    protected String generateCSVRanking(int round, boolean withRoster, boolean withNaf) {
        String s = this._params._tournament_name + ";" + this._params._date.toString() + ";" + this._params._teamTournament + "\n";
        s = s + ";\n";
        if (this._params._teamTournament) {
            mjtRankingTeam rt = new mjtRankingTeam(true, round - 1,
                    _params._ranking1_team,
                    _params._ranking2_team,
                    _params._ranking3_team,
                    _params._ranking4_team,
                    _params._ranking5_team,
                    _teams, false);

            for (int i = 0; i < rt.getRowCount(); i++) {
                String team = (String) rt.getValueAt(i, 1);
                s = s + (i + 1) + ";" + team;
                for (int j = 0; j < _teams.size(); j++) {
                    if (_teams.get(j)._name.equals(team)) {
                        for (int k = 0; k < _teams.get(j)._coachs.size(); k++) {
                            s = s + ";" + _teams.get(j)._coachs.get(k)._name;
                        }
                    }
                }
                s = s + "\n";
            }

            s = s + ";\n";
        }

        mjtRankingIndiv ri = new mjtRankingIndiv(round,
                _params._ranking1,
                _params._ranking2,
                _params._ranking3,
                _params._ranking4,
                _params._ranking5,
                _coachs, false, false);
        for (int i = 0; i < ri.getRowCount(); i++) {
            String coach = (String) ri.getValueAt(i, 2);
            s = s + (i + 1) + ";" + coach;
            for (int j = 0; j < _coachs.size(); j++) {
                if (_coachs.get(j)._name.equals(coach)) {
                    if (withNaf) {
                        s = s + ";" + _coachs.get(j)._naf;
                    }
                    if (withRoster) {
                        s = s + ";" + _coachs.get(j)._roster._name;
                    }
                }
            }
            s = s + "\n";
        }
        return s;
    }

    public RenderedImage generateRankingQRCode(int round) {
        String s = generateCSVRanking(round, false, false);
        QRCode qrcode = null;;
        try {
            qrcode = Encoder.encode(s, ErrorCorrectionLevel.H);

            int magnify = 10; //The resolution of the QRCode 
            byte[][] matrix = qrcode.getMatrix().getArray();
            int size = qrcode.getMatrix().getWidth() * magnify;

            //Make the BufferedImage that are to hold the QRCode 
            BufferedImage im = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            im.createGraphics();
            Graphics2D g = (Graphics2D) im.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, size * magnify, size * magnify);

            //paint the image using the ByteMatrik 
            for (int h = 0; h < qrcode.getMatrix().getHeight(); h++) {
                for (int w = 0; w < qrcode.getMatrix().getWidth(); w++) {
                    //Find the colour of the dot 
                    if (matrix[h][w] == 0) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }

                    //Paint the dot 
                    g.fillRect(h * magnify, w * magnify, magnify, magnify);
                }
            }
            return im;

        } catch (WriterException e) {

            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
            return null;
        }
    }

    public void exportFullFBB(java.io.File file) {
        this.saveXML(file, true);
    }

    public void exportFBB(java.io.File file) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            String s = generateCSVRanking(_rounds.size(), true, true);
            String s_tmp = s;
            while (s_tmp.length() > 0) {
                writer.print(s_tmp.substring(0, Math.min(255, s_tmp.length() - 1)));
                s_tmp = s_tmp.substring(Math.min(256, s_tmp.length()));
            }

            writer.close();

            RenderedImage im = generateRankingQRCode(_rounds.size());

            try {
                ImageIO.write(im, "png", new File(file.getAbsoluteFile() + ".png"));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
            }

            /*writer.println(this._params._tournament_name + ";" + this._params._date.toString() + ";" + this._params._teamTournament);
             writer.println(";");
             if (this._params._teamTournament) {
             mjtRankingTeam rt = new mjtRankingTeam(true, this._rounds.size() - 1,
             _params._ranking1_team,
             _params._ranking2_team,
             _params._ranking3_team,
             _params._ranking4_team,
             _params._ranking5_team,
             _teams);

             for (int i = 0; i < rt.getRowCount(); i++) {
             String team = (String) rt.getValueAt(i, 1);
             writer.print((i + 1) + ";" + team);
             for (int j = 0; j < _teams.size(); j++) {
             if (_teams.get(j)._name.equals(team)) {
             for (int k = 0; k < _teams.get(j)._coachs.size(); k++) {
             writer.print(";" + _teams.get(j)._coachs.get(k)._name);
             }
             }
             }
             writer.print("\n");
             }

             writer.println(";");
             }

             mjtRankingIndiv ri = new mjtRankingIndiv(_rounds.size(),
             _params._ranking1,
             _params._ranking2,
             _params._ranking3,
             _params._ranking4,
             _params._ranking5,
             _coachs, false);
             for (int i = 0; i < ri.getRowCount(); i++) {
             String coach = (String) ri.getValueAt(i, 2);
             writer.print((i + 1) + ";" + coach);
             for (int j = 0; j < _coachs.size(); j++) {
             if (_coachs.get(j)._name.equals(coach)) {
             writer.print(";" + _coachs.get(j)._naf);
             writer.print(";" + _coachs.get(j)._roster._name);
             }
             }
             writer.print("\n");
             }*/

        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        }
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
            Clan._clanMap = new HashMap();




            while (h.hasNext()) {
                Element clan = (Element) h.next();
                Clan c = new Clan(clan.getAttributeValue("Name"));
                _clans.addElement(c);
                Clan._clanMap.put(c._name, c);




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
                c._roster = new RosterType(coach.getAttributeValue("Roster"));
                c._naf = coach.getAttribute("NAF").getIntValue();
                c._rank = coach.getAttribute("Rank").getIntValue();
                c._clan = Clan._clanMap.get(coach.getAttributeValue("Clan"));




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
            _roundrobin = Boolean.parseBoolean(racine.getAttributeValue("RoundRobin"));
        } catch (Exception e) {
            _roundrobin = false;
        }

        try {
            List ros = racine.getChildren("Roster");
            if (ros != null) {
                if (ros.size() > 0) {
                    RosterType.RostersNames = new Vector<String>();
                    Iterator it_ros = ros.iterator();
                    while (it_ros.hasNext()) {
                        Element r = (Element) it_ros.next();
                        RosterType.RostersNames.add(r.getAttributeValue("Name"));
                    }
                } else {
                    RosterType.initCollection();
                }
            } else {
                RosterType.initCollection();
            }
        } catch (NullPointerException ne) {
            RosterType.initCollection();
        }

        /* Parameters */
        Element params = racine.getChild("Parameters");
        _params.setXMLElement(params);

        /* Groups */
        try {
            List groups = racine.getChildren("Group");
            Iterator gr = groups.iterator();
            _groups.removeAllElements();

            while (gr.hasNext()) {
                Element group = (Element) gr.next();
                Group groupe = new Group(group.getAttributeValue("Name"));
                groupe.setXMLElement(group);
                _groups.add(groupe);
            }
        } catch (NullPointerException npe) {
        }

        /* Clans */
        List clans = racine.getChildren("Clan");
        Iterator h = clans.iterator();
        _clans.clear();
        Clan._clanMap = new HashMap();

        while (h.hasNext()) {
            Element clan = (Element) h.next();
            Clan c = new Clan(clan.getAttributeValue("Name"));
            c.setXMLElement(clan);
            _clans.addElement(c);
            //Clan._clanMap.put(c._name, c);
        }

        /* Coachs */
        List coachs = racine.getChildren("Coach");
        Iterator i = coachs.iterator();
        _coachs.clear();
        Coach._map = new HashMap();

        while (i.hasNext()) {
            Element coach = (Element) i.next();
            Coach c = new Coach();
            c.setXMLElement(coach);
            _coachs.add(c);
            c._matchs.removeAllElements();
        }

        /* Teams */
        List teams = racine.getChildren("Team");
        Team._map = new HashMap();
        Iterator l = teams.iterator();
        _teams.clear();
        while (l.hasNext()) {
            Element team = (Element) l.next();
            Team t = new Team();
            t.setXMLElement(team);
            _teams.add(t);
        }

        /* */
        List pools=racine.getChildren("Pool");
        Iterator p = teams.iterator();
        _pools.clear();
        while (p.hasNext()) {
            Element pool = (Element) p.next();
            Pool po = new Pool();
            po.setXMLElement(pool);
            _pools.add(po);
        }
        
        /* Rounds */
        List rounds = racine.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND"));
        Iterator j = rounds.iterator();
        _rounds.clear();

        while (j.hasNext()) {
            Element round = (Element) j.next();
            Round r = new Round();
            r.setXMLElement(round);
            _rounds.add(r);
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

    public int GetActiveCoachNumber() {
        int nb = 0;
        for (int i = 0; i < _coachs.size(); i++) {
            if (_coachs.get(i)._active) {
                nb++;
            }
        }
        return nb;
    }

    public Vector<Coach> GetActiveCoaches() {
        Vector<Coach> v = new Vector<Coach>();

        for (int i = 0; i < _coachs.size(); i++) {
            if (_coachs.get(i)._active) {
                v.add(_coachs.get(i));
            }
        }
        return v;
    }

    protected void generateIndividualFirstRoundPool() {

        JPanel message = new JPanel();
        message.setLayout(new BorderLayout());

        JLabel jlb = new JLabel("Nombre de joueur par Poule: ");

        JSpinner jspNbPlayers = new JSpinner();
        SpinnerNumberModel model = new SpinnerNumberModel(4, 2, GetActiveCoachNumber() / 2, 2);
        jspNbPlayers.setModel(model);

        message.add(jlb, BorderLayout.NORTH);
        message.add(jspNbPlayers, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), message, "Poule", JOptionPane.QUESTION_MESSAGE);

        int nbPlayers = (Integer) model.getValue();

        if (GetActiveCoachNumber() % nbPlayers != 0) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Le nombre de joueur n'est pas un multiple de votre choix", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }


        int nbPools = GetActiveCoachNumber() / nbPlayers;
        for (int i = 0; i < nbPools; i++) {
            Pool p = new Pool();
            _pools.add(p);
            p._name = Integer.toString(i + 1);
        }

        int nbCounter = nbPlayers;

        int response = JOptionPane.YES_OPTION;
        Vector<Coach> coachs = new Vector<Coach>(GetActiveCoaches());
        while (nbCounter > 0) {

            if (response == JOptionPane.YES_OPTION) {
                response = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Voulez vous choisir le coach du rang" + (nbPlayers - nbCounter + 1) + " ?", "Poule", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            }
            if (response == JOptionPane.YES_OPTION) {
                for (int i = 0; i < nbPools; i++) {
                    Vector<String> names = new Vector<String>();
                    for (int j = 0; j < coachs.size(); j++) {
                        names.add(coachs.get(j)._name);
                    }
                    int index = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), "Choisissez un coach", "Poule", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, names.toArray(), 0);
                    _pools.get(i)._coachs.add(coachs.get(index));
                    coachs.remove(index);
                }
            } else {
                for (int i = 0; i < nbPools; i++) {
                    int index = ((int) Math.random()) % _coachs.size();
                    _pools.get(i)._coachs.add(coachs.get(index));
                    coachs.remove(index);
                }
            }
            nbCounter--;
        }

        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r._heure = cal.getTime();
        r._matchs.removeAllElements();

        for (int k = 0; k < nbPools; k++) {
            coachs = new Vector<Coach>(_pools.get(k)._coachs);

            int counter = 0;
            boolean NotOK = true;
            while ((NotOK) && (counter < 500)) {

                NotOK = false;
                Vector<Coach> shuffle = new Vector<Coach>(coachs);
                Collections.shuffle(shuffle);

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

                counter++;
            }
        }
        _rounds.add(r);

    }

    protected void generateIndividualFirstRoundManual() {
        _roundrobin = false;
        Vector<Coach> coachs = new Vector<Coach>(GetActiveCoaches());
        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r._heure = cal.getTime();
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
                if (possibleCoachs.isEmpty()) {
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
        _rounds.add(r);
    }

    protected void generateIndividualFirstRoundOrder(boolean random) {
        _roundrobin = false;
        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r._heure = cal.getTime();
        boolean NotOK = true;
        int counter = 0;

        while ((NotOK) && (counter < 500)) {

            NotOK = false;
            Vector<Coach> shuffle = new Vector<Coach>(GetActiveCoaches());
            if (random) /* Aléatoire */ {
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
                Vector<Coach> shuffle = new Vector<Coach>(GetActiveCoaches());
                if (random) /* Aléatoire */ {
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
                Vector<Coach> shuffle = new Vector<Coach>(GetActiveCoaches());
                if (random) /* Aléatoire */ {
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
    }

    protected void generateIndividualFirstRoundRobin() {
        boolean home_away = false;
        _roundrobin = true;
        home_away = (JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Match Aller-Retour ?", "Aller-Retour", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        Vector<Coach> coachs = GetActiveCoaches();
        Collections.shuffle(coachs);

        Vector<Coach> c1part = new Vector<Coach>();
        Vector<Coach> c2part = new Vector<Coach>();

        for (int i = 0; i < coachs.size() / 2; i++) {
            c1part.add(coachs.get(2 * i));
            c2part.add(coachs.get(2 * i + 1));
        }

        int nb_rounds = coachs.size() - 1;
        if (home_away) {
            nb_rounds = 2 * nb_rounds;
        }

        for (int i = 0; i < nb_rounds; i++) {
            Round r = new Round();
            Calendar cal = Calendar.getInstance();
            r._heure = cal.getTime();

            for (int j = 0; j < c2part.size(); j++) {
                Match m = new Match();
                m._coach1 = c1part.get(j);
                m._coach2 = c2part.get(j);
                r.getMatchs().add(m);
            }

            // Move coaches for round robin / ribbon method

            Coach c_tmp = c2part.get(0);
            c2part.remove(c_tmp);
            c1part.insertElementAt(c_tmp, 1);
            c_tmp = c1part.get(c1part.size() - 1);
            c1part.remove(c1part.size() - 1);
            c2part.add(c_tmp);

            _rounds.add(r);
        }
    }

    protected void generateIndividualFirstRoundCup() {

        // Analyze number of players
        int nb_coachs = GetActiveCoachNumber();
        Vector<Coach> coachs = new Vector<Coach>(GetActiveCoaches());
        Collections.shuffle(coachs);
        int nb_tmp = 1;
        int nb_rounds = 0;
        while (nb_tmp < nb_coachs) {
            nb_tmp = nb_tmp * 2;
            nb_rounds++;
        }

        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r._heure = cal.getTime();

        r._looser_cup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Sagit-il d'un tournoi à double élimination ?", "Coupe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        // there is nb_tmp/matchs
        for (int i = 0; i < nb_tmp / 2; i++) {
            Match m = new Match();
            m._coach1 = coachs.get(i);
            m._coach2 = Coach.NullCoach;
            if (nb_tmp / 2 + i < nb_coachs) {
                m._coach2 = coachs.get(i + nb_tmp / 2);
            }
            r._matchs.add(m);
        }

        // Comme les matchs sans adversaires sont à la fin, on réordonne pour les faire glisser
        for (int i = 0; i < r._matchs.size() / 2; i++) {
            if (i % 2 == 0) {
                Match m1 = r._matchs.get(i);
                Match m2 = r._matchs.get(r._matchs.size() - 1 - i);
                r.getMatchs().set(i, m2);
                r.getMatchs().set(r._matchs.size() - 1 - i, m1);
            }
        }


        r._cup = true;
        r._cup_max_tour = nb_rounds - 1;
        r._cup_tour = 0;
        r._looser_cup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Sagit-il d'un tournoi à double élimination ?", "Coupe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        _rounds.add(r);
    }

    protected void generateIndividualFirstRound(int choice) {

        _roundrobin = false;
        int nb_coach = GetActiveCoachNumber();
        if (nb_coach % 2 > 0) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre impair de coach actifs", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
        } else {
            switch (choice) {
                // Manual Choice
                case 2:
                    generateIndividualFirstRoundManual();
                    break;
                // Toute Ronde
                case 3:
                    generateIndividualFirstRoundRobin();
                    break;
                case 0:
                    generateIndividualFirstRoundOrder(true);
                    break;
                case 1:
                    generateIndividualFirstRoundOrder(false);
                    break;
                case 4:
                    generateIndividualFirstRoundPool();
                    break;
                case 5:
                    generateIndividualFirstRoundCup();
                    break;
                default:
                    break;
            }
        }
    }

    protected void generateTeamFirstRoundOrder(boolean random) {
        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r._heure = cal.getTime();

        _roundrobin = false;

        Vector<Team> teams1 = new Vector<Team>();
        Vector<Team> teams2 = new Vector<Team>();
        /*
         * Le premier appariement est aléatoire ByTeam
         */
        Vector<Team> shuffle = new Vector<Team>(_teams);
        if (random) /* Aléatoire */ {
            Collections.shuffle(shuffle);
        }
        for (int i = 0; i
                < shuffle.size() / 2; i++) {
            Team team1 = shuffle.get(2 * i);
            Team team2 = shuffle.get(2 * i + 1);
            teams1.add(team1);
            teams2.add(team2);
        }

        TeamIndivPairing(teams1, teams2, r, JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation des joueurs aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Affecation des joueurs", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        _rounds.add(r);
    }

    public void TeamIndivPairing(Vector<Team> teams1, Vector<Team> teams2, Round r, boolean random) {
        if (_params._teamIndivPairing == 1) {
            jdgTeamPairing jdg = new jdgTeamPairing(MainFrame.getMainFrame(), true, teams1, teams2, r);
            jdg.setVisible(true);
        } else {
            if (_params._teamIndivPairing == 2) {
                random = true;
            }
            for (int i = 0; i
                    < teams1.size(); i++) {
                Team team1 = teams1.get(i);
                Team team2 = teams2.get(i);
                Vector<Coach> coachs1 = team1.getActivePlayers();
                Vector<Coach> shuffle2 = new Vector<Coach>(team2.getActivePlayers());

                if (random) /* Aléatoire */ {
                    Collections.shuffle(shuffle2);
                }
                for (int j = 0; j
                        < shuffle2.size(); j++) {
                    Match m = new Match();
                    m._coach1 = coachs1.get(j);
                    m._coach2 = shuffle2.get(j);
                    r._matchs.add(m);
                }

            }
        }
    }

    protected void generateTeamFirstRoundRobin() {

        boolean complete = (JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Round Robin integral (incluant tous les joueurs)?", "Round Robin integral", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        boolean home_away = (JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Match Aller-Retour ?", "Aller-Retour", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        boolean random = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation des joueurs aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Affecation des joueurs", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        Vector<Team> teams = getTeams();

        if (random) {
            Collections.shuffle(teams);
        }
        _roundrobin = true;


        Vector<Team> t1part = new Vector<Team>();
        Vector<Team> t2part = new Vector<Team>();

        for (int i = 0; i < teams.size() / 2; i++) {
            t1part.add(teams.get(2 * i));
            t2part.add(teams.get(2 * i + 1));
        }


        int nb_team_rounds = teams.size() - 1;
        if (home_away) {
            nb_team_rounds = 2 * nb_team_rounds;
        }

        for (int i = 0; i < nb_team_rounds; i++) {

            Vector<Team> teams1 = t1part;
            Vector<Team> teams2 = t2part;


            if (!complete) {
                Round r = new Round();
                Calendar cal = Calendar.getInstance();
                r._heure = cal.getTime();

                TeamIndivPairing(teams1, teams2, r, random);

                _rounds.add(r);
            } else {

                for (int n = 0; n < _params._teamMatesNumber; n++) {

                    Round r = new Round();
                    Calendar cal = Calendar.getInstance();
                    r._heure = cal.getTime();

                    for (int k = 0; k < teams1.size(); k++) {
                        Team team1 = teams1.get(k);
                        Team team2 = teams2.get(k);

                        Vector<Coach> t1players = team1.getActivePlayers();
                        Vector<Coach> t2players = team2.getActivePlayers();

                        // Arrange player list using ribbon method
                        for (int p = 0; p < n; p++) {
                            Coach c_tmp = t2players.get(0);
                            t2players.remove(0);
                            t2players.add(c_tmp);
                        }

                        for (int l = 0; l < t1players.size(); l++) {

                            Coach c1 = t1players.get(l);
                            Coach c2 = t2players.get(l);

                            Match m = new Match();
                            m._coach1 = c1;
                            m._coach2 = c2;

                            r.getMatchs().add(m);
                        }
                    }
                    _rounds.add(r);

                }

            }

            // Rolls teams with ribbon method
            Team t_tmp = t2part.get(0);
            t2part.remove(0);
            t1part.insertElementAt(t_tmp, 1);
            t_tmp = t1part.get(t1part.size() - 1);
            t1part.remove(t1part.size() - 1);
            t2part.add(t_tmp);
        }

    }

    protected void generateTeamFirstRoundCup() {
        // Analyze number of players
        int nb_teams = getTeams().size();
        Vector<Team> teams = new Vector<Team>(getTeams());
        Collections.shuffle(teams);
        int nb_tmp = 1;
        int nb_rounds = 0;
        while (nb_tmp < nb_teams) {
            nb_tmp = nb_tmp * 2;
            nb_rounds++;
        }

        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r._heure = cal.getTime();

        r._looser_cup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Sagit-il d'un tournoi à double élimination ?", "Coupe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        Vector<Team> teams1 = new Vector<Team>();
        Vector<Team> teams2 = new Vector<Team>();

        for (int i = 0; i < nb_tmp / 2; i++) {
            teams1.add(teams.get(i));
            if (nb_tmp / 2 + i < nb_teams) {
                teams2.add(teams.get(i + nb_tmp / 2));
            } else {
                teams2.add(Team.NullTeam);
            }
        }

        // Comme les matchs sans adversaires sont à la fin, on réordonne pour les faire glisser
        for (int i = 0; i < teams1.size() / 2; i++) {
            if (i % 2 == 0) {
                Team t1 = teams1.get(i);
                Team t2 = teams2.get(teams1.size() - 1 - i);
                teams1.set(i, t2);
                teams2.set(teams1.size() - 1 - i, t1);
            }
        }

        TeamIndivPairing(teams1, teams2, r, JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation des joueurs aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Affecation des joueurs", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        r._cup = true;
        r._cup_max_tour = nb_rounds - 1;
        r._cup_tour = 0;

        _rounds.add(r);
    }

    protected void generateTeamFirstRoundManual() {

        _roundrobin = false;

        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r._heure = cal.getTime();


        Vector<Team> teams1 = new Vector<Team>();
        Vector<Team> teams2 = new Vector<Team>();

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
            for (int i = 0; i < teams.size(); i++) {
                if (opp.equals(teams.get(i)._name)) {
                    Team team2 = teams.get(i);
                    teams2.add(team2);
                    teams.remove(team2);
                    break;
                }
            }
        }

        TeamIndivPairing(teams1, teams2, r, JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation des joueurs aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Affecation des joueurs", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        _rounds.add(r);
    }

    protected void generateTeamFirstRoundPool() {
        JPanel message = new JPanel();
        message.setLayout(new BorderLayout());

        JLabel jlb = new JLabel("Nombre d'équipes par Poule: ");

        JSpinner jspNbTeams = new JSpinner();
        SpinnerNumberModel model = new SpinnerNumberModel(4, 2, getTeams().size() / 2, 2);
        jspNbTeams.setModel(model);

        message.add(jlb, BorderLayout.NORTH);
        message.add(jspNbTeams, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), message, "Poule", JOptionPane.QUESTION_MESSAGE);

        int nbTeams = (Integer) model.getValue();

        if (getTeams().size() % nbTeams != 0) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Le nombre dd'équipe n'est pas un multiple de votre choix", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }


        int nbPools = getTeams().size() / nbTeams;
        for (int i = 0; i < nbPools; i++) {
            Pool p = new Pool();
            _pools.add(p);
            p._name = Integer.toString(i + 1);
        }

        int nbCounter = nbTeams;

        int response = JOptionPane.YES_OPTION;
        Vector<Team> teams = new Vector<Team>(getTeams());
        while (nbCounter > 0) {

            if (response == JOptionPane.YES_OPTION) {
                response = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Voulez vous choisir l'équipe du rang " + (nbTeams - nbCounter + 1) + " ?", "Poule", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            }
            if (response == JOptionPane.YES_OPTION) {
                for (int i = 0; i < nbPools; i++) {
                    Vector<String> names = new Vector<String>();
                    for (int j = 0; j < _teams.size(); j++) {
                        names.add(_teams.get(j)._name);
                    }
                    int index = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), "Choisissez un coach", "Poule", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, names.toArray(), 0);
                    _pools.get(i)._teams.add(teams.get(index));
                    teams.remove(index);
                }
            } else {
                for (int i = 0; i < nbPools; i++) {
                    int index = ((int) Math.random()) % _coachs.size();
                    _pools.get(i)._teams.add(teams.get(index));
                    teams.remove(index);
                }
            }
            nbCounter--;
        }

        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r._heure = cal.getTime();
        r._matchs.removeAllElements();

        boolean random = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation des joueurs aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Affecation des joueurs", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        for (int k = 0; k < nbPools; k++) {
            teams = new Vector<Team>(_pools.get(k)._teams);

            Vector<Team> teams1 = new Vector<Team>();
            Vector<Team> teams2 = new Vector<Team>();
            /*
             * Le premier appariement est aléatoire ByTeam
             */
            Vector<Team> shuffle = new Vector<Team>(teams);
            Collections.shuffle(shuffle);

            for (int i = 0; i < shuffle.size() / 2; i++) {
                Team team1 = shuffle.get(2 * i);
                Team team2 = shuffle.get(2 * i + 1);
                teams1.add(team1);
                teams2.add(team2);
            }

            TeamIndivPairing(teams1, teams2, r, random);
        }
        _rounds.add(r);
    }

    protected void generateTeamFirstRound(int choice) {

        /**
         * First check the number of active players
         */
        _roundrobin = false;
        for (int i = 0; i < _teams.size(); i++) {
            if (_teams.get(i).getActivePlayerNumber() != _params._teamMatesNumber) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Mauvais nombre de joueurs actif pour l'équipe " + _teams.get(i)._name);
                return;
            }
        }

        switch (choice) {
            // Manual
            case 2:
                generateTeamFirstRoundManual();
                break;
            case 0:
                generateTeamFirstRoundOrder(true);
                break;
            case 1:
                generateTeamFirstRoundOrder(false);
                break;
            case 3:
                generateTeamFirstRoundRobin();
            case 4:
                generateTeamFirstRoundPool();
                break;
            case 5:
                generateTeamFirstRoundCup();
                break;
            default:
                break;
        }
    }

    public void generateFirstRound(int choice) {
        _rounds.clear();


        /*
         * Si tournoi individuel
         */
        if (!_params._teamTournament) {

            generateIndividualFirstRound(choice);

        } /**
         * Si tournoi ByTeam
         */
        else {
            generateTeamFirstRound(choice);
        }

        for (int i = 0; i < _coachs.size(); i++) {
            _coachs.get(i)._matchs = new Vector<Match>();
        }

        if (_rounds.size() > 0) {
            for (int k = 0; k < _rounds.size(); k++) {
                for (int i = 0; i < _rounds.get(k)._matchs.size(); i++) {
                    Match m = _rounds.get(k)._matchs.get(i);
                    m._coach1._matchs.add(m);
                    m._coach2._matchs.add(m);

                    for (int j = 0; j < _params._criterias.size(); j++) {
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
        }
        String filename = Tournament.getTournament().getParams()._tournament_name;
        filename = filename + "." + Tournament.getTournament().getRounds().size();
        Date date = new Date();
        filename = filename + "." + Long.toString(date.getTime()) + ".xml";
        File file = new File(filename);

        Tournament.getTournament().saveXML(file, false);

    }

    public Vector<Pool> getPools() {
        return _pools;
    }
}
