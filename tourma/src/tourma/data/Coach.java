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
    public Category mCategory;
    public String mTeam;
    public RosterType mRoster;
    public int mNaf;
    public int mRank;
    public boolean mActive = true;
    public Team mTeamMates = null;
    //public teamma.data.Roster mComposition;
    public ArrayList<teamma.data.Roster> mCompositions;
    public double mNafRank = 150.0;
    public int mHandicap = 0;

    public Coach() {
        super();
        mActive = true;
        mCompositions = new ArrayList<>();
    }

    public Coach(final String name) {
        super(name);
        mActive = false;
        mTeam = StringConstants.CS_NONE;
        mCompositions = new ArrayList<>();
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
        if (this.mCategory != null) {
            coach.setAttribute(StringConstants.CS_CATEGORY, this.mCategory.mName);
        } else {
            coach.setAttribute(StringConstants.CS_CATEGORY, StringConstants.CS_NONE);
        }
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ACTIVE"), Boolean.toString(this.mActive));

        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HANDICAP"), Integer.toString(this.mHandicap));

        for (int i = 0; i < mCompositions.size(); i++) {
            final Element compo = this.mCompositions.get(i).getXMLElement();
            coach.addContent(compo);
        }
        return coach;
    }

    public String getStringRoster() {
        if (this.mMatchs.size() == 0) {
            return mRoster.mName;
        }
        ArrayList<RosterType> rosters = new ArrayList<>();
        for (int i = 0; i < mMatchs.size(); i++) {
            Match m = mMatchs.get(i);
            if (this == m.mCompetitor1) {
                if (((CoachMatch) m).mRoster1 != null) {
                    if (!rosters.contains(((CoachMatch) m).mRoster1)) {
                        rosters.add(((CoachMatch) m).mRoster1);
                    }
                } else {
                    if (!rosters.contains(this.mRoster)) {
                        rosters.add(this.mRoster);
                    }
                }
            }

            if (this == m.mCompetitor2) {
                if (((CoachMatch) m).mRoster2 != null) {
                    if (!rosters.contains(((CoachMatch) m).mRoster2)) {
                        rosters.add(((CoachMatch) m).mRoster2);
                    }
                } else {
                    if (!rosters.contains(this.mRoster)) {
                        rosters.add(this.mRoster);
                    }
                }
            }
        }

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < rosters.size(); i++) {
            if (i > 0) {
                buf.append(" / ");
            }
            buf.append(rosters.get(i).mName);
        }

        return buf.toString();
    }

    @Override
    public void setXMLElement(final Element coach) {
        try {
            this.mName = coach.getAttributeValue(StringConstants.CS_NAME);
            this.mTeam = coach.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"));
            String rosterName = RosterType.getRosterName(coach.getAttributeValue(StringConstants.CS_ROSTER));
            this.mRoster = RosterType.mRosterTypes.get(rosterName);
            this.mNaf = coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF")).getIntValue();
            this.mRank = coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK")).getIntValue();
            this.mClan = Clan.sClanMap.get(coach.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN")));
            if (coach.getAttributeValue(StringConstants.CS_CATEGORY) != null) {
                this.mCategory = Category.sCategoryMap.get(coach.getAttributeValue(StringConstants.CS_CATEGORY));
            } else {
                this.mCategory = Category.sCategoryMap.get(coach.getAttributeValue(StringConstants.CS_NONE));
            }
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

            final List compos = coach.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COMPOSITION"));
            final Iterator itC = compos.iterator();
            while (itC.hasNext()) {
                Element compo = (Element) itC.next();
                teamma.data.Roster c = new teamma.data.Roster();
                c.setXMLElement(compo);
                this.mCompositions.add(c);
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

    public CoachMatch CreateMatch(Competitor opponent, Round r) {
        CoachMatch m = new CoachMatch(r);
        m.mCompetitor1 = this;
        m.mCompetitor2 = opponent;
        return m;
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
            if (params.mEnableClans) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OnlyOneClanCoachKey"));
            }
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
        ArrayList<Match> matchs = round.getMatchs();

        for (int i = matchs.size() - 1; i > 0; i--) {

            final Coach c1 = (Coach) matchs.get(i).mCompetitor1;
            final Coach c2 = (Coach) matchs.get(i).mCompetitor2;
            boolean have_played = c1.havePlayed(c2);

            if (have_played) {
                for (int k = i - 1; k >= 0; k--) {

                    Coach c1_tmp = (Coach) matchs.get(k).mCompetitor1;
                    Coach c2_tmp = (Coach) matchs.get(k).mCompetitor2;

                    have_played = c1.havePlayed(c2_tmp);

                    boolean canMatch = !have_played;
                    if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
                        if (!c2_tmp.mClan.mName.equals(StringConstants.CS_NONE)) {
                            if (c1.mClan == c2_tmp.mClan) {
                                canMatch = false;
                            }
                        }
                    }
                    if ((tour.getParams().mTeamTournament) && (tour.getParams().mTeamPairing == 0)) {
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
                        if ((tour.getParams().mTeamTournament) && (tour.getParams().mTeamPairing == 0)) {
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
