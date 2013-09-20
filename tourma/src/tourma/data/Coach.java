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
public final class Coach extends Competitor implements XMLExport {

    protected static Coach sNullCoach = null;
    public static HashMap<String, Coach> sCoachMap = new HashMap<>();
    /**
     * Clan
     */
    public Clan mClan;
    public String mTeam;
    public RosterType mRoster;
    public int mNaf;
    public int mRank;
    public boolean mActive = true;
    public Team mTeamMates = null;
    
    public teamma.data.Roster mComposition;
    
    public double mNafRank = 150.0;
    public int mHandicap = 0;

    

    public Coach() {
        super();
        mActive = true;
            }

    public Coach(final String name) {
        super(name);
        mActive = false;        
        mTeam = StringConstants.CS_NONE;
        mRoster = new RosterType(StringConstants.CS_NONE);
        mTeamMates = null;
    }

    public static Coach getNullCoach() {
        if (sNullCoach == null) {
            sNullCoach = new Coach(StringConstants.CS_NONE);
        }
        if ((Team.getNullTeam() != null) && ((sNullCoach.mTeam == null))) {
            sNullCoach.mTeamMates = Team.getNullTeam();
        }
        return sNullCoach;
    }

    @Override
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

    @Override
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

    @Override
    public void setXMLElement(final Element coach) {
        try {
            this.mName = coach.getAttributeValue(StringConstants.CS_NAME);
            this.mTeam = coach.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"));
            String rosterName = RosterType.getRosterName(coach.getAttributeValue(StringConstants.CS_ROSTER));
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
                if (Tournament.getTournament().getClans().isEmpty()) {
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

    @Override
    public void AddMatch(Competitor opponent, Round r) {
        CoachMatch m = new CoachMatch(r);
        m.mCompetitor1 = this;
        m.mCompetitor2 = opponent;
        r.mMatchs.add(m);
    }

    public boolean havePlayed(Competitor opponent) {
        boolean have_played = false;
        for (int i = 0; i < mMatchs.size(); i++) {
            if ((mMatchs.get(i).mCompetitor1 == opponent) || (mMatchs.get(i).mCompetitor2 == opponent)) {
                have_played = true;
                break;
            }
        }
        return have_played;
    }

    public ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents) {
        Tournament tour = Tournament.getTournament();
        ArrayList<Clan> clans = tour.getClans();
        Parameters params = tour.getParams();
        ArrayList<Competitor> possible = new ArrayList<Competitor>(opponents);
        if (this.mClan != clans.get(0)) {
            if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
                for (int i = 0; i
                        < possible.size(); i++) {
                    if (((Coach) possible.get(i)).mClan.mName.equals(this.mClan.mName)) {
                        possible.remove(i);
                        i--;
                    }
                }
            }
        }

        if ((params.mTeamTournament) && (params.mTeamPairing == 0)) {
            for (int i = 0; i
                    < possible.size(); i++) {
                if (((Coach) possible.get(i)).mTeamMates.getActivePlayers().contains(this)) {
                    possible.remove(i);
                    i--;
                }
            }
        }


        for (int i = 0; i
                < possible.size(); i++) {
            if (possible.get(i).havePlayed(this)) {
                possible.remove(i);
                i--;
            }
        }


        if (possible.isEmpty()) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OnlyOneClanCoachKey"));
            possible = new ArrayList<Competitor>(opponents);
        }
        return possible;
    }

    public String getDecoratedName() {
        String tmp = mName;
        Tournament tour = Tournament.getTournament();
        ArrayList<Clan> clans = tour.getClans();
        Parameters params = tour.getParams();
        if (params.mEnableClans) {
            tmp = mName + " / " + mClan.mName;
        }
        if (params.mTeamTournament) {
            tmp = mName + " / " + mTeamMates.mName;
        }
        return tmp;
    }

    @Override
    public void AddMatchRoundRobin(Competitor c, Round r) {
        AddMatch(c, r);
    }

    /**
     * This method arrange matchs to avoid double meet
     *
     * @param r
     */
    @Override
    public void RoundCheck(Round round) {

        Tournament tour = Tournament.getTournament();
        ArrayList<CoachMatch> matchs = round.getMatchs();

        for (int i = matchs.size() - 1; i > 0; i--) {

            final Coach c1 = (Coach)matchs.get(i).mCompetitor1;
            final Coach c2 = (Coach)matchs.get(i).mCompetitor2;
            boolean have_played = c1.havePlayed(c2);

            if (have_played) {
                for (int k = i - 1; k >= 0; k--) {

                    Coach c1_tmp = (Coach)matchs.get(k).mCompetitor1;
                    Coach c2_tmp = (Coach)matchs.get(k).mCompetitor2;

                    have_played = c1.havePlayed(c2_tmp);

                    boolean canMatch = !have_played;
                    if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
                        if (!c2_tmp.mClan.mName.equals(StringConstants.CS_NONE)) {
                            if (c1.mClan == c2_tmp.mClan) {
                                canMatch = false;
                            }
                        }
                    }
                    if ((tour.getParams().mTeamTournament) && (tour.getParams().mTeamIndivPairing == 0)) {
                        if (c2_tmp.mTeamMates != Team.getNullTeam()) {
                            if (c1.mTeamMates == c2_tmp.mTeamMates) {
                                canMatch = false;
                            }
                        }
                    }
                    if (canMatch) {
                        matchs.get(i).mCompetitor2 = c2_tmp;
                        matchs.get(k).mCompetitor2 = c2;
                        break;
                    } else {
                        have_played = c1.havePlayed(c1_tmp);

                        canMatch = !have_played;
                        if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
                            if (!c1_tmp.mClan.mName.equals(StringConstants.CS_NONE)) {
                                if (c1_tmp.mClan == c1_tmp.mClan) {
                                    canMatch = false;
                                }
                            }
                        }
                        if ((tour.getParams().mTeamTournament) && (tour.getParams().mTeamIndivPairing == 0)) {
                            if (c1_tmp.mTeamMates != Team.getNullTeam()) {
                                if (c1.mTeamMates == c1_tmp.mTeamMates) {
                                    canMatch = false;
                                }
                            }
                        }
                        if (canMatch) {
                            matchs.get(i).mCompetitor2 = c1_tmp;
                            matchs.get(k).mCompetitor1 = c2;
                            break;
                        }
                    }
                }
            }
        }
    }
}
