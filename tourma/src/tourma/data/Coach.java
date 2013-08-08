/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jdom.DataConversionException;
import org.jdom.Element;
import teamma.data.Player;
import teamma.data.StarPlayer;
import teamma.data.lrb;
import tourma.MainFrame;

/**
 * This class contains data relative to coach
 *
 * @author Frederic Berger
 */
public class Coach implements Comparable, XMLExport {

    public static Coach NullCoach=new Coach("None");
    public static HashMap<String,Coach> _map=new HashMap<String,Coach>();
    /**
     * Clan
     */
    public Clan _clan;
    public String _name;
    public String _team;
    public RosterType _roster;
    public int _naf;
    public int _rank;
    public boolean _active = true;
    public Team _teamMates = null;
    public Vector<Match> _matchs;
    public teamma.data.Roster _composition;

    public Coach() {
        _matchs = new Vector<Match>();
        _active = true;
    }
    
     public Coach(String name) {
        _matchs = new Vector<Match>();
        _active = false;
        _name=name;
        _team="None";
        _roster=new RosterType("None");
        _teamMates=Team.NullTeam;
    }

    public int compareTo(Object obj) {
        if (obj instanceof Coach) {
            return _name.compareTo(((Coach) obj)._name);
        } else {
            return -1;
        }
    }

    public Element getXMLElement() {

        Element coach = new Element("Coach");
        coach.setAttribute("Name", this._name);
        coach.setAttribute("Team", this._team);
        coach.setAttribute("Roster", this._roster._name);
        coach.setAttribute("NAF", Integer.toString(this._naf));
        coach.setAttribute("Rank", Integer.toString(this._rank));
        coach.setAttribute("Clan", this._clan._name);
        coach.setAttribute("Active", Boolean.toString(this._active));

        if (this._composition != null) {
            Element compo = new Element("Composition");
            teamma.data.Roster roster = this._composition;

            compo.setAttribute("Roster", roster._roster._name);
            compo.setAttribute("Apothecary", Boolean.toString(roster._apothecary));
            compo.setAttribute("Assistants", Integer.toString(roster._assistants));
            compo.setAttribute("Cheerleaders", Integer.toString(roster._cheerleaders));
            compo.setAttribute("FanFactor", Integer.toString(roster._fanfactor));
            compo.setAttribute("Rerolls", Integer.toString(roster._rerolls));

            Element inducements = new Element("Inducements");
            inducements.setAttribute("Chef", Boolean.toString(roster._chef));
            inducements.setAttribute("Igor", Boolean.toString(roster._igor));
            inducements.setAttribute("Wizard", Boolean.toString(roster._wizard));
            inducements.setAttribute("Babes", Integer.toString(roster._bloodweiserbabes));
            inducements.setAttribute("Cards", Integer.toString(roster._cards));
            inducements.setAttribute("Bribe", Integer.toString(roster._corruptions));
            inducements.setAttribute("ExtraRerolls", Integer.toString(roster._extrarerolls));
            inducements.setAttribute("LocalApothecary", Integer.toString(roster._localapothecary));

            for (int j = 0; j < roster._champions.size(); j++) {
                Element st = new Element("StarPlayer");
                st.setAttribute("Name", roster._champions.get(j)._name);
                inducements.addContent(st);
            }

            compo.addContent(inducements);

            for (int j = 0; j < roster._players.size(); j++) {
                Element p = new Element("Player");
                teamma.data.Player pl = roster._players.get(j);
                p.setAttribute("Name", pl._name);
                p.setAttribute("Position", pl._playertype._position);

                for (int k = 0; k < pl._skills.size(); k++) {
                    Element s = new Element("Skill");
                    teamma.data.Skill sk = pl._skills.get(k);
                    s.setAttribute("Name", sk._name);
                    p.addContent(s);
                }
                compo.addContent(p);
            }

            coach.addContent(compo);

        }
        return coach;
    }

    public void setXMLElement(Element coach) {
        try {
            this._name = coach.getAttributeValue("Name");
            this._team = coach.getAttributeValue("Team");
            this._roster = new RosterType(coach.getAttributeValue("Roster"));
            this._naf = coach.getAttribute("NAF").getIntValue();
            this._rank = coach.getAttribute("Rank").getIntValue();
            this._clan = Clan._clanMap.get(coach.getAttributeValue("Clan"));
            Coach._map.put(_name, this);
            
            try {
                this._active = coach.getAttribute("Active").getBooleanValue();
            } catch (NullPointerException npe) {
            }

            if (this._clan == null) {
                if (Tournament.getTournament().getClans().size() == 0) {
                    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
                    Tournament.getTournament().getClans().add(new Clan(bundle.getString("NoneKey")));

                }
                this._clan = Tournament.getTournament().getClans().get(0);
            }

            Element compo = coach.getChild("Composition");
            if (compo != null) {
                this._composition = new teamma.data.Roster();
                this._composition._roster = teamma.data.lrb.getLRB().getRosterType(compo.getAttributeValue("Roster"));
                this._composition._apothecary = Boolean.parseBoolean(compo.getAttributeValue("Apothecary"));
                this._composition._assistants = Integer.parseInt(compo.getAttributeValue("Assistants"));
                this._composition._cheerleaders = Integer.parseInt(compo.getAttributeValue("Cheerleaders"));
                this._composition._fanfactor = Integer.parseInt(compo.getAttributeValue("FanFactor"));
                this._composition._rerolls = Integer.parseInt(compo.getAttributeValue("Rerolls"));

                Element inducements = compo.getChild("Inducements");
                if (inducements != null) {
                    this._composition._bloodweiserbabes = Integer.parseInt(inducements.getAttributeValue("Babes"));
                    this._composition._cards = Integer.parseInt(inducements.getAttributeValue("Cards"));
                    this._composition._chef = Boolean.parseBoolean(inducements.getAttributeValue("Chef"));
                    this._composition._corruptions = Integer.parseInt(inducements.getAttributeValue("Bribe"));
                    this._composition._extrarerolls = Integer.parseInt(inducements.getAttributeValue("ExtraRerolls"));
                    this._composition._igor = Boolean.parseBoolean(inducements.getAttributeValue("Igor"));
                    this._composition._localapothecary = Integer.parseInt(inducements.getAttributeValue("LocalApothecary"));
                    this._composition._wizard = Boolean.parseBoolean(inducements.getAttributeValue("Wizard"));

                    List stars = inducements.getChildren("StarPlayers");
                    Iterator s = stars.iterator();
                    while (s.hasNext()) {
                        Element star = (Element) s.next();
                        StarPlayer t = lrb.getLRB().getStarPlayer(star.getAttributeValue("Name"));
                        this._composition._champions.add(t);
                    }
                }

                List players = compo.getChildren("Player");
                Iterator ip = players.iterator();
                while (ip.hasNext()) {
                    Element p = (Element) ip.next();

                    teamma.data.Player pl = new Player(this._composition._roster.getPlayerType(p.getAttributeValue("Position")));
                    pl._name = p.getAttributeValue("Name");

                    List skills = compo.getChildren("Skill");
                    Iterator is = skills.iterator();
                    while (is.hasNext()) {
                        Element s = (Element) is.next();

                        teamma.data.Skill sl = lrb.getLRB().getSkill(s.getAttributeValue("Name"));
                        pl._skills.add(sl);
                    }

                    this._composition._players.add(pl);
                    this._rank =this._composition.getValue(false) / 10000;
                }
            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }
}
