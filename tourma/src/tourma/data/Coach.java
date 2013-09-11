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
import org.jdom2.DataConversionException;
import org.jdom2.Element;
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

    public static Coach sNullCoach = new Coach(StringConstants.CS_NONE);
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
    public double mNafRank = 150.0;
    public int mHandicap = 0;

    protected Color generateRandomColor(final Color mix) {
        final Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        // mix the color
        if (mix != null) {
            red = (red + mix.getRed()) / 2;
            green = (green + mix.getGreen()) / 2;
            blue = (blue + mix.getBlue()) / 2;
        }

        return new Color(red, green, blue);
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
        mTeam = StringConstants.CS_NONE;
        mRoster = new RosterType(StringConstants.CS_NONE);
        mTeamMates = Team.sNullTeam;
        if (mName.equals(StringConstants.CS_NONE)) {
            mColor = Color.GRAY;
        } else {
            mColor = generateRandomColor(Color.WHITE);
        }
    }

    public int compareTo(final Object obj) {
        int result = -1;

        if (obj instanceof Coach) {
            result = ((Double) mNafRank).compareTo(((Coach) obj).mNafRank);
            if (result == 0) {
                result = mName.compareTo(((Coach) obj).mName);
            }
        }
        return result;
    }

    public Element getXMLElement() {

        final Element coach = new Element(StringConstants.CS_COACH);
        coach.setAttribute(StringConstants.CS_NAME, this.mName);
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"), this.mTeam);
        coach.setAttribute(StringConstants.CS_ROSTER, this.mRoster.mName);
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF"), Integer.toString(this.mNaf));
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK"), Integer.toString(this.mRank));
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN"), this.mClan.mName);
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ACTIVE"), Boolean.toString(this.mActive));

        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HANDICAP"), Integer.toString(this.mHandicap));

        if (this.mComposition != null) {
            final Element compo = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COMPOSITION"));
            final teamma.data.Roster roster = this.mComposition;

            compo.setAttribute(StringConstants.CS_ROSTER, roster._roster._name);
            compo.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("APOTHECARY"), Boolean.toString(roster._apothecary));
            compo.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ASSISTANTS"), Integer.toString(roster._assistants));
            compo.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHEERLEADERS"), Integer.toString(roster._cheerleaders));
            compo.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FANFACTOR"), Integer.toString(roster._fanfactor));
            compo.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("REROLLS"), Integer.toString(roster._rerolls));

            final Element inducements = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INDUCEMENTS"));
            inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHEF"), Boolean.toString(roster._chef));
            inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("IGOR"), Boolean.toString(roster._igor));
            inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WIZARD"), Boolean.toString(roster._wizard));
            inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BABES"), Integer.toString(roster._bloodweiserbabes));
            inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CARDS"), Integer.toString(roster._cards));
            inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BRIBE"), Integer.toString(roster._corruptions));
            inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EXTRAREROLLS"), Integer.toString(roster._extrarerolls));
            inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LOCALAPOTHECARY"), Integer.toString(roster._localapothecary));


            for (int j = 0; j < roster._champions.size(); j++) {
                final Element st = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("STARPLAYER"));
                st.setAttribute(StringConstants.CS_NAME, roster._champions.get(j)._name);
                inducements.addContent(st);
            }

            compo.addContent(inducements);

            for (int j = 0; j < roster._players.size(); j++) {
                final Element p = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PLAYER"));
                final teamma.data.Player pl = roster._players.get(j);
                p.setAttribute(StringConstants.CS_NAME, pl._name);
                p.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITION"), pl._playertype._position);

                for (int k = 0; k < pl._skills.size(); k++) {
                    final Element s = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SKILL"));
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
            this.mTeam = coach.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"));
            String rosterName=RosterType.getRosterName(coach.getAttributeValue(StringConstants.CS_ROSTER));
            this.mRoster = new RosterType(rosterName);
            this.mNaf = coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF")).getIntValue();
            this.mRank = coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK")).getIntValue();
            this.mClan = Clan.sClanMap.get(coach.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN")));
            Coach.sCoachMap.put(mName, this);

            try {
                this.mActive = coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ACTIVE")).getBooleanValue();
                this.mHandicap = coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HANDICAP")).getIntValue();
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

            final Element compo = coach.getChild(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COMPOSITION"));
            if (compo != null) {
                this.mComposition = new teamma.data.Roster();
                this.mComposition._roster = teamma.data.lrb.getLRB().getRosterType(compo.getAttributeValue(StringConstants.CS_ROSTER));
                this.mComposition._apothecary = Boolean.parseBoolean(compo.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("APOTHECARY")));
                this.mComposition._assistants = Integer.parseInt(compo.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ASSISTANTS")));
                this.mComposition._cheerleaders = Integer.parseInt(compo.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHEERLEADERS")));
                this.mComposition._fanfactor = Integer.parseInt(compo.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FANFACTOR")));
                this.mComposition._rerolls = Integer.parseInt(compo.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("REROLLS")));

                final Element inducements = compo.getChild(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INDUCEMENTS"));
                if (inducements != null) {
                    this.mComposition._bloodweiserbabes = Integer.parseInt(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BABES")));
                    this.mComposition._cards = Integer.parseInt(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CARDS")));
                    this.mComposition._chef = Boolean.parseBoolean(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHEF")));
                    this.mComposition._corruptions = Integer.parseInt(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BRIBE")));
                    this.mComposition._extrarerolls = Integer.parseInt(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EXTRAREROLLS")));
                    this.mComposition._igor = Boolean.parseBoolean(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("IGOR")));
                    this.mComposition._localapothecary = Integer.parseInt(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LOCALAPOTHECARY")));
                    this.mComposition._wizard = Boolean.parseBoolean(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WIZARD")));

                    final List stars = inducements.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("STARPLAYERS"));
                    final Iterator s = stars.iterator();
                    while (s.hasNext()) {
                        final Element star = (Element) s.next();
                        final StarPlayer t = lrb.getLRB().getStarPlayer(star.getAttributeValue(StringConstants.CS_NAME));
                        this.mComposition._champions.add(t);
                    }
                }

                final List players = compo.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PLAYER"));
                final Iterator ip = players.iterator();
                while (ip.hasNext()) {
                    final Element p = (Element) ip.next();

                    final teamma.data.Player pl = new Player(this.mComposition._roster.getPlayerType(p.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITION"))));
                    pl._name = p.getAttributeValue(StringConstants.CS_NAME);

                    final List skills = compo.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SKILL"));
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
