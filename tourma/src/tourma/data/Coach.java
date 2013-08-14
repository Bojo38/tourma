/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import org.jdom.DataConversionException;
import org.jdom.Element;
import teamma.data.Player;
import teamma.data.StarPlayer;
import teamma.data.lrb;
import tourma.MainFrame;
import tourma.utility.StringConstants;

/**
 * This class contains data relative to coach
 *
 * @author Frederic Berger
 */
public class Coach implements Comparable, XMLExport {

    public static Coach sNullCoach = new Coach("None");
    public static HashMap<String, Coach> sCoachMap = new HashMap<String, Coach>();
    /**
     * Clan
     */
    public Clan mClan;
    public String mName;
    public String mTeam;
    public RosterType mRoster;
    public int mNaf;
    public int mRank;
    public boolean mActive = true;
    public Team mTeamMates = null;
    public ArrayList<Match> mMatchs;
    public teamma.data.Roster mComposition;
    public Color mColor;

    protected Color generateRandomColor(Color mix) {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        // mix the color
        if (mix != null) {
            red = (red + mix.getRed()) / 2;
            green = (green + mix.getGreen()) / 2;
            blue = (blue + mix.getBlue()) / 2;
        }

        Color color = new Color(red, green, blue);
        return color;
    }

    public Coach() {
        mMatchs = new ArrayList<Match>();
        mActive = true;
        mColor = generateRandomColor(Color.WHITE);

    }

    public Coach(final String name) {
        mMatchs = new ArrayList<Match>();
        mActive = false;
        mName = name;
        mTeam = "None";
        mRoster = new RosterType("None");
        mTeamMates = Team.sNullTeam;
        if (mName.equals("None")) {
            mColor = Color.GRAY;
        } else {
            mColor = generateRandomColor(Color.WHITE);
        }
    }

    public int compareTo(final Object obj) {
        int result = -1;

        if (obj instanceof Coach) {
            result = mName.compareTo(((Coach) obj).mName);
        }
        return result;
    }

    public Element getXMLElement() {

        final Element coach = new Element(StringConstants.CS_COACH);
        coach.setAttribute(StringConstants.CS_NAME, this.mName);
        coach.setAttribute("Team", this.mTeam);
        coach.setAttribute(StringConstants.CS_ROSTER, this.mRoster.mName);
        coach.setAttribute("NAF", Integer.toString(this.mNaf));
        coach.setAttribute("Rank", Integer.toString(this.mRank));
        coach.setAttribute("Clan", this.mClan.mName);
        coach.setAttribute("Active", Boolean.toString(this.mActive));

        if (this.mComposition != null) {
            final Element compo = new Element("Composition");
            final teamma.data.Roster roster = this.mComposition;

            compo.setAttribute(StringConstants.CS_ROSTER, roster._roster._name);
            compo.setAttribute("Apothecary", Boolean.toString(roster._apothecary));
            compo.setAttribute("Assistants", Integer.toString(roster._assistants));
            compo.setAttribute("Cheerleaders", Integer.toString(roster._cheerleaders));
            compo.setAttribute("FanFactor", Integer.toString(roster._fanfactor));
            compo.setAttribute("Rerolls", Integer.toString(roster._rerolls));

            final Element inducements = new Element("Inducements");
            inducements.setAttribute("Chef", Boolean.toString(roster._chef));
            inducements.setAttribute("Igor", Boolean.toString(roster._igor));
            inducements.setAttribute("Wizard", Boolean.toString(roster._wizard));
            inducements.setAttribute("Babes", Integer.toString(roster._bloodweiserbabes));
            inducements.setAttribute("Cards", Integer.toString(roster._cards));
            inducements.setAttribute("Bribe", Integer.toString(roster._corruptions));
            inducements.setAttribute("ExtraRerolls", Integer.toString(roster._extrarerolls));
            inducements.setAttribute("LocalApothecary", Integer.toString(roster._localapothecary));


            for (int j = 0; j < roster._champions.size(); j++) {
                final Element st = new Element("StarPlayer");
                st.setAttribute(StringConstants.CS_NAME, roster._champions.get(j)._name);
                inducements.addContent(st);
            }

            compo.addContent(inducements);

            for (int j = 0; j < roster._players.size(); j++) {
                final Element p = new Element("Player");
                final teamma.data.Player pl = roster._players.get(j);
                p.setAttribute(StringConstants.CS_NAME, pl._name);
                p.setAttribute("Position", pl._playertype._position);

                for (int k = 0; k < pl._skills.size(); k++) {
                    final Element s = new Element("Skill");
                    final teamma.data.Skill sk = pl._skills.get(k);
                    s.setAttribute(StringConstants.CS_NAME, sk._name);
                    p.addContent(s);
                }
                compo.addContent(p);
            }

            coach.addContent(compo);

        }
        return coach;
    }

    public void setXMLElement(final Element coach) {
        try {
            this.mName = coach.getAttributeValue(StringConstants.CS_NAME);
            this.mTeam = coach.getAttributeValue("Team");
            this.mRoster = new RosterType(coach.getAttributeValue(StringConstants.CS_ROSTER));
            this.mNaf = coach.getAttribute("NAF").getIntValue();
            this.mRank = coach.getAttribute("Rank").getIntValue();
            this.mClan = Clan.sClanMap.get(coach.getAttributeValue("Clan"));
            Coach.sCoachMap.put(mName, this);

            try {
                this.mActive = coach.getAttribute("Active").getBooleanValue();
            } catch (NullPointerException npe) {
                // Do nothing
            }

            if (this.mClan == null) {
                if (Tournament.getTournament().getClans().size() == 0) {
                    final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE); // NOI18N
                    Tournament.getTournament().getClans().add(new Clan(bundle.getString("NoneKey")));

                }
                this.mClan = Tournament.getTournament().getClans().get(0);
            }

            final Element compo = coach.getChild("Composition");
            if (compo != null) {
                this.mComposition = new teamma.data.Roster();
                this.mComposition._roster = teamma.data.lrb.getLRB().getRosterType(compo.getAttributeValue(StringConstants.CS_ROSTER));
                this.mComposition._apothecary = Boolean.parseBoolean(compo.getAttributeValue("Apothecary"));
                this.mComposition._assistants = Integer.parseInt(compo.getAttributeValue("Assistants"));
                this.mComposition._cheerleaders = Integer.parseInt(compo.getAttributeValue("Cheerleaders"));
                this.mComposition._fanfactor = Integer.parseInt(compo.getAttributeValue("FanFactor"));
                this.mComposition._rerolls = Integer.parseInt(compo.getAttributeValue("Rerolls"));

                final Element inducements = compo.getChild("Inducements");
                if (inducements != null) {
                    this.mComposition._bloodweiserbabes = Integer.parseInt(inducements.getAttributeValue("Babes"));
                    this.mComposition._cards = Integer.parseInt(inducements.getAttributeValue("Cards"));
                    this.mComposition._chef = Boolean.parseBoolean(inducements.getAttributeValue("Chef"));
                    this.mComposition._corruptions = Integer.parseInt(inducements.getAttributeValue("Bribe"));
                    this.mComposition._extrarerolls = Integer.parseInt(inducements.getAttributeValue("ExtraRerolls"));
                    this.mComposition._igor = Boolean.parseBoolean(inducements.getAttributeValue("Igor"));
                    this.mComposition._localapothecary = Integer.parseInt(inducements.getAttributeValue("LocalApothecary"));
                    this.mComposition._wizard = Boolean.parseBoolean(inducements.getAttributeValue("Wizard"));

                    final List stars = inducements.getChildren("StarPlayers");
                    final Iterator s = stars.iterator();
                    while (s.hasNext()) {
                        final Element star = (Element) s.next();
                        final StarPlayer t = lrb.getLRB().getStarPlayer(star.getAttributeValue(StringConstants.CS_NAME));
                        this.mComposition._champions.add(t);
                    }
                }

                final List players = compo.getChildren("Player");
                final Iterator ip = players.iterator();
                while (ip.hasNext()) {
                    final Element p = (Element) ip.next();

                    final teamma.data.Player pl = new Player(this.mComposition._roster.getPlayerType(p.getAttributeValue("Position")));
                    pl._name = p.getAttributeValue(StringConstants.CS_NAME);

                    final List skills = compo.getChildren("Skill");
                    final Iterator is = skills.iterator();
                    while (is.hasNext()) {
                        final Element s = (Element) is.next();

                        final teamma.data.Skill sl = lrb.getLRB().getSkill(s.getAttributeValue(StringConstants.CS_NAME));
                        pl._skills.add(sl);
                    }

                    this.mComposition._players.add(pl);
                    this.mRank = this.mComposition.getValue(false) / 10000;
                }
            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }
}
