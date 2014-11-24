/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import teamma.data.Roster;
import tourma.MainFrame;
import tourma.utility.StringConstants;

/**
 * This class contains data relative to coach
 *
 * @author Frederic Berger
 */
public final class Coach extends Competitor implements XMLExport {

    /**
     *
     */
    private static Coach sNullCoach = null;

    /**
     *
     */
    private static HashMap<String, Coach> sCoachMap = new HashMap<>();
    private final static Object myLock = new Object();
    private static final Logger LOG = Logger.getLogger(Coach.class.getName());

    /**
     *
     * @param s
     * @return
     */
    public static Coach getCoach(String s) {
        return sCoachMap.get(s);
    }

    /**
     * new Coach map
     */
    public static void newCoachMap() {
        sCoachMap = new HashMap<>();
    }

    /**
     *
     * @param s
     * @param c
     */
    public static void putCoach(String s, Coach c) {
        sCoachMap.put(s, c);
    }

    /**
     *
     * @return
     */
    public static Coach getNullCoach() {
        synchronized (Coach.myLock) {
            if (sNullCoach == null) {
                sNullCoach = new Coach(StringConstants.CS_NONE);
                sNullCoach.setTeamMates(Team.getNullTeam());
            }
            if ((Team.getNullTeam() != null) && ((sNullCoach.getTeam() == null))) {
                sNullCoach.setTeamMates(Team.getNullTeam());
            }
        }

        return sNullCoach;
    }

    /**
     *
     */
    private Category mCategory;

    /**
     *
     */
    private String mTeam;

    /**
     *
     */
    private RosterType mRoster;

    /**
     *
     */
    private int mNaf;

    /**
     *
     */
    private int mRank;

    /**
     *
     */
    private boolean mActive = true;

    /**
     *
     */
    private Team mTeamMates = null;

    /**
     *
     */
    private final ArrayList<teamma.data.Roster> mCompositions;

    /**
     *
     */
    private double mNafRank = 150.0;

    /**
     *
     */
    private int mHandicap = 0;

    /**
     * New Coach constructor
     */
    public Coach() {
        super();
        mActive = true;
        mCompositions = new ArrayList<>();
    }

    /**
     *
     * @param name
     */
    public Coach(final String name) {
        super(name);
        mActive = false;
        mTeam = StringConstants.CS_NONE;
        mCompositions = new ArrayList<>();
        mRoster = new RosterType(StringConstants.CS_NONE);
        mTeamMates = null;
    }

    /**
     *
     * @return
     */
    public int getCompositionCount() {
        return mCompositions.size();
    }

    /**
     *
     * @param i
     * @return
     */
    public teamma.data.Roster getComposition(int i) {
        return mCompositions.get(i);
    }

    /**
     *
     * @param r
     */
    public void addComposition(teamma.data.Roster r) {
        mCompositions.add(r);
    }

    /**
     *
     * @param i
     */
    public void removeComposition(int i) {
        mCompositions.remove(i);
    }

    @Override
    public int compareTo(final Object obj) {
        int result = -1;

        if (obj instanceof Coach) {
            result = ((Double) getNafRank()).compareTo(((Coach) obj).getNafRank());
            if (result == 0) {
                result = getName().compareTo(((IWithNameAndPicture) obj).getName());
            }
        }
        return result;
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean equals(Object c) {
        if (c instanceof Coach) {
            return getName().equals(((IWithNameAndPicture) c).getName());
        }
        return false;
    }

    @Override
    public int hashCode() {

        return getName().hashCode();
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {

        final Element coach = new Element(StringConstants.CS_COACH);
        coach.setAttribute(StringConstants.CS_NAME, this.getName());
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"), this.getTeam());
        coach.setAttribute(StringConstants.CS_ROSTER, this.getRoster().getName());
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF"), Integer.toString(this.getNaf()));
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK"), Integer.toString(this.getRank()));
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN"), this.getClan().getName());
        if (this.getCategory() != null) {
            coach.setAttribute(StringConstants.CS_CATEGORY, this.getCategory().getName());
        } else {
            coach.setAttribute(StringConstants.CS_CATEGORY, StringConstants.CS_NONE);
        }
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ACTIVE"), Boolean.toString(this.isActive()));

        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HANDICAP"), Integer.toString(this.getHandicap()));

        for (Roster mComposition : mCompositions) {
            final Element compo = mComposition.getXMLElement();
            coach.addContent(compo);
        }

        Element image = new Element("Picture");

        if (getPicture() != null) {
            try {
                String encodedImage;
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    ImageIO.write(getPicture(), "png", baos);
                    baos.flush();
                    encodedImage = DatatypeConverter.printBase64Binary(baos.toByteArray());
                    // should be inside a finally block
                }
                image.addContent(encodedImage);
                coach.addContent(image);
            } catch (IOException e) {
            }
        }

        return coach;
    }

    /**
     *
     * @return
     */
    /* @Override
     public String getName() {
     return mName;
     }*/
    /**
     *
     * @return
     */
    public String getStringRoster() {
        if (this.getMatchCount() == 0) {
            return getRoster().getName();
        }
        ArrayList<RosterType> rosters = new ArrayList<>();
        for (int i = 0; i < getMatchCount(); i++) {
            Match m = getMatch(i);
            if (this == m.getCompetitor1()) {
                if (((CoachMatch) m).getRoster1() != null) {
                    if (!rosters.contains(((CoachMatch) m).getRoster1())) {
                        rosters.add(((CoachMatch) m).getRoster1());
                    } else {
                    }
                } else {
                    if (!rosters.contains(this.mRoster)) {
                        rosters.add(this.getRoster());
                    }
                }
            }

            if (this == m.getCompetitor2()) {
                if (((CoachMatch) m).getRoster2() != null) {
                    if (!rosters.contains(((CoachMatch) m).getRoster2())) {
                        rosters.add(((CoachMatch) m).getRoster2());
                    }
                } else {
                    if (!rosters.contains(this.mRoster)) {
                        rosters.add(this.getRoster());
                    }
                }
            }
        }

        StringBuilder buf = new StringBuilder(32);
        for (int i = 0; i < rosters.size(); i++) {
            if (i > 0) {
                buf.append(" / ");
            }
            buf.append(rosters.get(i).getName());
        }

        return buf.toString();
    }

    /**
     *
     * @param coach
     */
    @Override
    public void setXMLElement(final Element coach) {
        try {
            this.setName(coach.getAttributeValue(StringConstants.CS_NAME));
            this.setTeam(coach.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM")));
            String rName = coach.getAttributeValue(StringConstants.CS_ROSTER);
            String rosterName = RosterType.getRosterName(rName);
            this.setRoster(RosterType.getRosterType(rosterName));

            this.setNaf(coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF")).getIntValue());
            this.setRank(coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK")).getIntValue());
            this.setClan(Clan.getClan(coach.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN"))));
            if (coach.getAttributeValue(StringConstants.CS_CATEGORY) != null) {
                this.setCategory(Category.getCategory(coach.getAttributeValue(StringConstants.CS_CATEGORY)));
            } else {
                this.setCategory(Category.getCategory(coach.getAttributeValue(StringConstants.CS_NONE)));
            }
            Coach.sCoachMap.put(getName(), this);

            try {
                this.setActive(coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ACTIVE")).getBooleanValue());
                this.setHandicap(coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HANDICAP")).getIntValue());
            } catch (NullPointerException npe) {
                // Do nothing
            }

            if (this.getClan() == null) {
                if (Tournament.getTournament().getClansCount() == 0) {
                    final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE); // NOI18N
                    Tournament.getTournament().addClan(new Clan(bundle.getString("NoneKey")));

                }
                this.setClan(Tournament.getTournament().getClan(0));
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

        try {
            Element image = coach.getChild("Picture");
            if (image != null) {
                String encodedImage = image.getText();
                if (!encodedImage.isEmpty()) {
                    byte[] bytes = DatatypeConverter.parseBase64Binary(encodedImage);
                    setPicture(ImageIO.read(new ByteArrayInputStream(bytes)));
                }
            }
        } catch (IOException e) {
            LOG.log(Level.FINE, e.getLocalizedMessage());
        }

    }

    /**
     *
     * @param opponent
     * @param r
     */
    @Override
    public void addMatch(Competitor opponent, Round r) {
        CoachMatch m = new CoachMatch(r);
        m.setCompetitor1(this);
        m.setCompetitor2(opponent);
        r.addMatch(m);
    }

    /**
     *
     * @param opponent
     * @param r
     * @return
     */
    public CoachMatch createMatch(Competitor opponent, Round r) {
        CoachMatch m = new CoachMatch(r);
        m.setCompetitor1(this);
        m.setCompetitor2(opponent);
        return m;
    }

    /**
     *
     * @param opponent
     * @return
     */
    @Override
    public boolean havePlayed(Competitor opponent) {
        boolean have_played = false;
        for (int i = 0; i < getMatchCount(); i++) {
            Match mMatch = getMatch(i);
            if ((mMatch.getCompetitor1() == opponent) || (mMatch.getCompetitor2() == opponent)) {
                have_played = true;
                break;
            }
        }
        return have_played;
    }
    /*
     public ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents, Round r) {
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
     for (int i = 0; i < possible.size(); i++) {
     if (((Coach) possible.get(i)).mTeamMates.getActivePlayers().contains(this)) {
     possible.remove(i);
     i--;
     }
     }
     for (int i = 0; i < possible.size(); i++) {
     if (possible.get(i).havePlayed(this)) {
     possible.remove(i);
     i--;
     }
     }
     ArrayList<Competitor> balanced = new ArrayList<>(possible);
     ArrayList<Team> oppteam = new ArrayList<>();
     HashMap<Team, Integer> map = new HashMap<>();
     // Build opponents map
     for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
     Team t = Tournament.getTournament().getTeam(i);
     if (t != this.mTeamMates) {
     map.put(t, 0);
     oppteam.add(t);
     }
     }
     if (params.mIndivPairingTeamBalanced) {
     // compute the number of matches between the coach team
     // and the other teams
     // Compute the number of match per opponent
     Team OwnTeam = this.mTeamMates;
     for (int i = 0; i < OwnTeam.mCoachs.size(); i++) {
     Coach c = OwnTeam.mCoachs.get(i);
     for (int j = 0; j < c.mMatchs.size(); j++) {
     CoachMatch m = (CoachMatch) c.mMatchs.get(j);
     Coach opp;
     if (c == m.mCompetitor1) {
     opp = (Coach) m.mCompetitor2;
     } else {
     opp = (Coach) m.mCompetitor1;
     }
     Team Other = opp.mTeamMates;
     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     }
     }
     // Take account current round
     for (int i = 0; i < r.mMatchs.size(); i++) {
     Match m = r.mMatchs.get(i);
     if (m instanceof CoachMatch) {
     Team Other = null;
     if (this.mTeamMates == ((Coach) m.mCompetitor1).mTeamMates) {
     Other = ((Coach) m.mCompetitor2).mTeamMates;
     }
     if (this.mTeamMates == ((Coach) m.mCompetitor2).mTeamMates) {
     Other = ((Coach) m.mCompetitor1).mTeamMates;
     }
     if (Other != null) {
     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     }
     }
     }
     // Compute the minimum
     Iterator it = map.keySet().iterator();
     int minimum = 65535;
     while (it.hasNext()) {
     Team en = (Team) it.next();
     int nb = map.get(en);
     if (nb < minimum) {
     minimum = nb;
     }
     }
     // Remove the teams which are not corresondig to minimum
     it = map.keySet().iterator();
     while (it.hasNext()) {
     Team en = (Team) it.next();
     int nb = map.get(en);
     if (nb > minimum) {
     LOG.log(Level.FINER,"Remove " + en.getDecoratedName() + " from possible opponents for " + getDecoratedName() + "remaining " + (oppteam.size() - 1));
     oppteam.remove(en);
     }
     }
     }
     ArrayList<Competitor> indivbalanced = new ArrayList<>(balanced);
     if (params.mIndivPairingIndivBalanced) {
     // compute the number of matches between the coach and the other teams
     map = new HashMap<>();
     // Build opponents map
     for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
     Team t = Tournament.getTournament().getTeam(i);
     if (t != this.mTeamMates) {
     map.put(t, 0);
     }
     }
     // Compute the number of match per opponent
     for (int j = 0; j < mMatchs.size(); j++) {
     CoachMatch m = (CoachMatch) mMatchs.get(j);
     Coach opp;
     if (this == m.mCompetitor1) {
     opp = (Coach) m.mCompetitor2;
     } else {
     opp = (Coach) m.mCompetitor1;
     }
     Team Other = opp.mTeamMates;
     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     }
     // Compute the minimum
     Iterator it = map.keySet().iterator();
     int minimum = 65535;
     while (it.hasNext()) {
     Team en = (Team) it.next();
     int nb = map.get(en);
     if (nb < minimum) {
     minimum = nb;
     }
     }
     // Remove the teams which are not corresondig to minimum
     it = map.keySet().iterator();
     while (it.hasNext()) {
     Team en = (Team) it.next();
     int nb = map.get(en);
     if (nb > minimum) {
     LOG.log(Level.FINER,"Remove " + en.getDecoratedName() + " from possible opponents for " + getDecoratedName() + "remaining " + (oppteam.size() - 1));
     oppteam.remove(en);
     }
     }
     }
     // Keep only the players in possible list who are in team
     // which are still in the map
     ArrayList<Competitor> buffer = new ArrayList<>(balanced);
     for (int i = 0; i < buffer.size(); i++) {
     Coach c = (Coach) buffer.get(i);
     if (!oppteam.contains(c.mTeamMates)) {
     balanced.remove(c);
     }
     }
     if (balanced.isEmpty()) {
     balanced = indivbalanced;
     }
     if (!balanced.isEmpty()) {
     possible = balanced;
     }
     }
     if (possible.isEmpty()) {
     if (params.mEnableClans) {
     JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OnlyOneClanCoachKey"));
     }
     possible = new ArrayList<>(opponents);
     }
     return possible;
     }*/

    /*
     * @Override*/
    /**
     *
     * @param opponents
     * @param r
     * @return
     */
    @Override
    public ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents, Round r) {

        Tournament tour = Tournament.getTournament();

        Parameters params = tour.getParams();
        ArrayList<Competitor> possible = new ArrayList<>(opponents);

        if (this.getClan() != tour.getClan(0)) {

            if ((params.isEnableClans()) && ((params.isAvoidClansFirstMatch() && tour.indexOfRound(r) == 0) || (params.isAvoidClansMatch()))) {

                int i = 0;
                while (i < possible.size()) {

                    if (possible.get(i).getClan().getName().equals(this.getClan().getName())) {
                        possible.remove(i);
                    } else {
                        i++;
                    }
                }
            }
        }

        if ((params.isTeamTournament()) && (params.getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {

            int i = 0;
            while (i < possible.size()) {
                if (((Coach) possible.get(i)).getTeamMates().containsCoach(this)) {
                    possible.remove(i);
                } else {
                    i++;
                }
            }

            i = 0;
            while (i < possible.size()) {
                if (possible.get(i).havePlayed(this)) {
                    possible.remove(i);
                } else {
                    i++;
                }
            }

            ArrayList<Team> oppteam = getPossibleTeams(r, null);

            ArrayList<Competitor> balanced = new ArrayList<>(possible);

            // Keep only the players in possible list who are in team
            // which are still in the map
            ArrayList<Competitor> buffer = new ArrayList<>(balanced);
            for (Competitor buffer1 : buffer) {
                Coach c = (Coach) buffer1;
                if (!oppteam.contains(c.getTeamMates())) {
                    balanced.remove(c);
                }
            }

            if (params.isIndivPairingIndivBalanced()) {
                ArrayList<Competitor> indivbalanced = new ArrayList<>(balanced);

                // Keep only the players in possible list who are in team
                // which are still in the map
                buffer = new ArrayList<>(indivbalanced);
                for (Competitor buffer1 : buffer) {
                    Coach c = (Coach) buffer1;
                    ArrayList<Team> oppteam2 = c.getPossibleTeams(r, null);
                    if (!oppteam2.contains(this.mTeamMates)) {
                        //LOG.log(Level.FINER,"Remove " + c.getDecoratedName() + " from possible opponents for " + getDecoratedName() + " remaining " + (oppteam.size() - 1));
                        indivbalanced.remove(c);
                    }
                }

                if (!indivbalanced.isEmpty()) {
                    balanced = indivbalanced;
                } else {
                    LOG.log(Level.FINER, "Individual balanced empty, using only team balanced");
                }
            }

            if (!balanced.isEmpty()) {
                possible = balanced;
            } else {
                LOG.log(Level.FINER, "Balanced empty, using only possible");
            }
        }

        if (possible.isEmpty()) {
            if (params.isEnableClans()) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OnlyOneClanCoachKey"));
            }
            possible = new ArrayList<>(opponents);
        }
        return possible;
    }

    /**
     *
     * @param current
     * @param currentOpponent
     * @return
     */
    private ArrayList<Team> getPossibleTeams(Round current, Coach currentOpponent) {

        ArrayList<Team> teams = new ArrayList<>();
        for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
            teams.add(Tournament.getTournament().getTeam(cpt));
        }

        ArrayList<Team> oppteam = new ArrayList<>(teams);
        oppteam.remove(this.getTeamMates());

        HashMap<Team, Integer> map;

        if (Tournament.getTournament().getParams().isIndivPairingTeamBalanced()) {

            map = this.getTeamMates().getTeamOppositionCount(oppteam, current);

            // Compute the minimum
            int minimum = getMinimumFromHash(new HashMap<Competitor, Integer>(map));

            for (Entry<Team, Integer> en : map.entrySet()) {
                int nb = en.getValue();
                if (currentOpponent != null) {
                    if (en.getKey().equals(currentOpponent.getTeamMates())) {
                        nb--;
                    }
                }

                if (nb > minimum) {
                    oppteam.remove(en.getKey());
                }

            }
        }

        if (Tournament.getTournament().getParams().isIndivPairingIndivBalanced()) {
            // compute the number of matches between the coach and the other teams
            map = getTeamOppositionCount(oppteam, current);
            // Build opponents map

            // Compute the minimum
            int minimum = getMinimumFromHash(new HashMap<Competitor, Integer>(map));

            for (Entry<Team, Integer> en : map.entrySet()) {
                int nb = en.getValue();
                if (currentOpponent != null) {
                    if (en.getKey().equals(currentOpponent.getTeamMates())) {
                        nb--;
                    }
                }
                if (nb > minimum) {
                    //LOG.log(Level.FINER,"Remove " + en.getDecoratedName() + "(" + nb + ">" + minimum + " matchs) from possible opponents for " + getDecoratedName() + " remaining " + (oppteam.size() - 1));
                    oppteam.remove(en.getKey());
                }
            }
        }

        return oppteam;
    }

    /**
     *
     * @param hash
     * @return
     */
    private int getMinimumFromHash(HashMap<Competitor, Integer> hash) {
        Iterator<Entry<Competitor, Integer>> it2 = hash.entrySet().iterator();
        int minimum2 = 65535;
        while (it2.hasNext()) {
            Entry<Competitor, Integer> en2 = it2.next();
            int nb2 = en2.getValue();
            if (nb2 < minimum2) {
                minimum2 = nb2;
            }
        }
        return minimum2;
    }

    /* protected HashMap<Team, Integer> getTeamOppositionCount(Team current, ArrayList<Team> oppteam, Round r) {
     HashMap<Team, Integer> map = new HashMap<>();
     // Build opponents map
     for (int i = 0; i < oppteam.size(); i++) {
     Team t = oppteam.get(i);
     if (!t.mName.equals(current.mName)) {
     map.put(t, 0);
     }
     }
     // compute the number of matches between the coach team and the other teams
     // Compute the number of match per opponent
     for (int i = 0; i < current.mCoachs.size(); i++) {
     Coach coach = current.mCoachs.get(i);
     for (int j = 0; j < coach.mMatchs.size(); j++) {
     CoachMatch m = (CoachMatch) coach.mMatchs.get(j);
     Coach opp;
     if (coach.mName.equals(m.mCompetitor1.mName)) {
     opp = (Coach) m.mCompetitor2;
     } else {
     opp = (Coach) m.mCompetitor1;
     }
     Team Other = opp.mTeamMates;
     if (oppteam.contains(Other)) {
     try {
     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     } catch (NullPointerException npe) {
     LOG.log(Level.FINER,"Impossible to manage " + current.mName + " vs " + Other.mName);
     }
     }
     }
     }
     // Take account current round
     for (int i = 0; i < r.mMatchs.size(); i++) {
     Match m = r.mMatchs.get(i);
     if (m instanceof CoachMatch) {
     Team Other = null;
     if (current.mName.equals(((Coach) m.mCompetitor1).mTeamMates.mName)) {
     Other = ((Coach) m.mCompetitor2).mTeamMates;
     }
     if (current.mName.equals(((Coach) m.mCompetitor2).mTeamMates.mName)) {
     Other = ((Coach) m.mCompetitor1).mTeamMates;
     }
     if (Other != null) {
     try {
     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     } catch (NullPointerException npe) {
     LOG.log(Level.FINER,"Impossible to manage " + current.mName + " vs " + Other.mName);
     }
     }
     }
     }
     return map;
     }
     protected HashMap<Team, Integer> getTeamOppositionCount(Coach current, ArrayList<Team> oppteam, Round r) {
     HashMap<Team, Integer> map = new HashMap<>();
     // Build opponents map
     for (int i = 0; i < oppteam.size(); i++) {
     Team t = oppteam.get(i);
     if (!t.mName.equals(current.mTeamMates.mName)) {
     map.put(t, 0);
     }
     }
     // Compute the number of match per opponent
     for (int j = 0; j < mMatchs.size(); j++) {
     CoachMatch m = (CoachMatch) mMatchs.get(j);
     Coach opp;
     if (this == m.mCompetitor1) {
     opp = (Coach) m.mCompetitor2;
     } else {
     opp = (Coach) m.mCompetitor1;
     }
     Team Other = opp.mTeamMates;
     if (oppteam.contains(Other)) {
     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     }
     }
     return map;
     }
     */
    /**
     *
     * @param teams
     * @param r
     * @return
     */
    @Override
    public HashMap<Team, Integer> getTeamOppositionCount(ArrayList<Team> teams, Round r) {

        HashMap<Team, Integer> map = new HashMap<>();

        // Build opponents map
        for (Team t : teams) {
            if (!t.getName().equals(mTeamMates.getName())) {
                map.put(t, 0);
            }
        }

        // Compute the number of match per opponent
        for (int i = 0; i < getMatchCount(); i++) {
            Match mMatch = getMatch(i);
            CoachMatch m = (CoachMatch) mMatch;
            Coach opp;
            if (this == m.getCompetitor1()) {
                opp = (Coach) m.getCompetitor2();
            } else {
                opp = (Coach) m.getCompetitor1();
            }
            Team Other = opp.getTeamMates();
            int nb = 0;
            if (map.get(Other) != null) {
                nb = map.get(Other);
            }
            nb += 1;
            map.put(Other, nb);
        }

        if (r != null) {
            for (int i = 0; i < r.getCoachMatchs().size(); i++) {
                CoachMatch cm = r.getCoachMatchs().get(i);

                Coach opp = null;
                if (this == cm.getCompetitor1()) {
                    opp = (Coach) cm.getCompetitor2();
                }
                if (this == cm.getCompetitor2()) {
                    opp = (Coach) cm.getCompetitor1();
                }
                if (opp != null) {
                    Team Other = opp.getTeamMates();

                    int nb = 0;
                    if (map.get(Other) != null) {
                        nb = map.get(Other);
                    }
                    nb += 1;
                    map.put(Other, nb);
                }
            }
        }

        return map;
    }

    /**
     *
     * @return
     */
    @Override
    public String getDecoratedName() {
        String tmp = getName();
        Tournament tour = Tournament.getTournament();
        ArrayList<Clan> clans;
        Parameters params = tour.getParams();
        if (params.isEnableClans()) {
            tmp = getName() + " / " + getClan().getName();
        }
        if (params.isTeamTournament()) {
            tmp = getName() + " / " + getTeamMates().getName();
        }
        return tmp;
    }

    /**
     *
     * @param c
     * @param r
     */
    @Override
    public void addMatchRoundRobin(Competitor c, Round r) {
        addMatch(c, r);
    }

    /**
     *
     * @param opp
     * @param round
     * @return
     */
    private boolean isBalanced(Coach opp, Round round) {

        boolean balanced = true;
        ArrayList<Team> teams = new ArrayList<>();

        if (this.getTeamMates() == opp.getTeamMates()) {
            return false;
        }

        for (int i = 0; i < round.getMatchsCount(); i++) {
            Match mMatch = round.getMatch(i);
            CoachMatch m = (CoachMatch) mMatch;
            Coach c1 = (Coach) m.getCompetitor1();
            Coach c2 = (Coach) m.getCompetitor2();
            if (c1.getTeamMates() != this.getTeamMates()) {
                if (!teams.contains(c1.getTeamMates())) {
                    teams.add(c1.getTeamMates());
                }
            }
            if (c2.getTeamMates() != this.getTeamMates()) {
                if (!teams.contains(c2.getTeamMates())) {
                    teams.add(c2.getTeamMates());
                }
            }
        }

        HashMap<Team, Integer> hash = this.getTeamOppositionCount(teams, round);

        Iterator it2 = hash.keySet().iterator();
        int minimum = 65535;
        int maximum = 0;
        while (it2.hasNext()) {
            Competitor en2 = (Competitor) it2.next();
            if (en2 instanceof Team) {
                Team t2 = (Team) en2;
                int nb2 = hash.get(t2);
                if (nb2 < minimum) {
                    minimum = nb2;
                }
                if (nb2 > maximum) {
                    maximum = nb2;
                }
            }
        }

        int nb = hash.get(opp.getTeamMates());

        if ((maximum == nb) && (maximum - minimum > 1)) {
            balanced = false;
        }
        return balanced;
    }

    /**
     *
     * @param round
     * @return
     */
    ArrayList<Team> getMinimumTeamsBalanced(Round round) {

        ArrayList<Team> possible = new ArrayList<>();

        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < round.getMatchsCount(); i++) {
            Match mMatch = round.getMatch(i);
            CoachMatch m = (CoachMatch) mMatch;
            Coach c1 = (Coach) m.getCompetitor1();
            Coach c2 = (Coach) m.getCompetitor2();
            if (c1.getTeamMates() != this.getTeamMates()) {
                if (!teams.contains(c2.getTeamMates())) {
                    teams.add(c2.getTeamMates());
                }
            }
            if (c2.getTeamMates() != this.getTeamMates()) {
                if (!teams.contains(c1.getTeamMates())) {
                    teams.add(c1.getTeamMates());
                }
            }
        }

        HashMap<Team, Integer> hash = this.getTeamOppositionCount(teams, round);

        Iterator<Entry<Team, Integer>> it2 = hash.entrySet().iterator();
        int minimum = 65535;
        int maximum = 0;
        while (it2.hasNext()) {
            Entry<Team, Integer> en2 = it2.next();
            if (en2.getKey() instanceof Team) {
                int nb2 = en2.getValue();
                if (nb2 < minimum) {
                    minimum = nb2;
                }
                if (nb2 > maximum) {
                    maximum = nb2;
                }
            }
        }

        it2 = hash.entrySet().iterator();
        while (it2.hasNext()) {
            Entry<Team, Integer> en2 = it2.next();
            int nb2 = en2.getValue();
            if (nb2 == minimum) {
                possible.add(en2.getKey());
            }
        }

        return possible;
    }

    /**
     *
     * @param Opponent
     * @param opponentOpponent
     * @param currentOpp
     * @param current
     * @return
     */
    private boolean canMatch(Coach Opponent, Coach opponentOpponent, Coach currentOpp, Round current) {
        boolean canMatch;

        // Already played
        boolean have_played = havePlayed(Opponent);

        Tournament tour = Tournament.getTournament();
        canMatch = !have_played;

        // Same clan 
        if ((tour.getParams().isEnableClans()) && (tour.getParams().isAvoidClansMatch())) {
            if (!Opponent.getClan().getName().equals(StringConstants.CS_NONE)) {
                if (getClan() == Opponent.getClan()) {
                    canMatch = false;
                }
            }
            if ((!opponentOpponent.getClan().getName().equals(StringConstants.CS_NONE)) && (!currentOpp.getClan().getName().equals(StringConstants.CS_NONE))) {
                if (opponentOpponent.getClan() == currentOpp.getClan()) {
                    canMatch = false;
                }
            }
        }

        // Same team
        if ((tour.getParams().isTeamTournament()) && (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {
            if (Opponent.getTeamMates() != Team.getNullTeam()) {
                if (getTeamMates() == Opponent.getTeamMates()) {
                    canMatch = false;
                }
            }
            if ((!opponentOpponent.getTeamMates().getName().equals(StringConstants.CS_NONE)) && (!currentOpp.getTeamMates().getName().equals(StringConstants.CS_NONE))) {
                if (opponentOpponent.getTeamMates() == currentOpp.getTeamMates()) {
                    canMatch = false;
                }
            }
        }

        // Balancing pairing
        if ((tour.getParams().isTeamTournament()) && (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {

            ArrayList<Team> teams = new ArrayList<>();
            for (int i = 0; i < current.getMatchsCount(); i++) {
                Match mMatch = current.getMatch(i);
                CoachMatch m = (CoachMatch) mMatch;
                Coach c1 = (Coach) m.getCompetitor1();
                Coach c2 = (Coach) m.getCompetitor2();
                if (c1.getTeamMates() != this.getTeamMates()) {
                    if (!teams.contains(c2.getTeamMates())) {
                        teams.add(c2.getTeamMates());
                    }
                }
                if (c2.getTeamMates() != this.getTeamMates()) {
                    if (!teams.contains(c1.getTeamMates())) {
                        teams.add(c1.getTeamMates());
                    }
                }
            }
            teams.remove(getTeamMates());

            // Team balancing
            if (tour.getParams().isIndivPairingTeamBalanced()) {
                HashMap<Team, Integer> hash = getTeamMates().getTeamOppositionCount(teams, current);

                Iterator it2 = hash.keySet().iterator();
                int minimum = 65535;
                int maximum = 0;
                while (it2.hasNext()) {
                    Competitor en2 = (Competitor) it2.next();
                    if (en2 instanceof Team) {
                        Team t2 = (Team) en2;
                        int nb2 = hash.get(t2);
                        if (nb2 < minimum) {
                            minimum = nb2;
                        }
                        if (nb2 > maximum) {
                            maximum = nb2;
                        }
                    }
                }

                // Check if team is perfectly balanced. If yes, the opponent can only 
                // be among teammates opponents
                if (maximum - minimum == 0) {
                    if (!mTeamMates.containsCoach(opponentOpponent)) {
                        canMatch = false;
                    }
                }

                // Check if team is balanced, by One. If yes, the opponent can  
                // be among teammates opponents or among minimum times encountered teams
                if (maximum - minimum == 1) {
                    if (!mTeamMates.containsCoach(opponentOpponent)) {
                        // search for minimum teams
                        if (hash.get(Opponent.getTeamMates()) > minimum) {
                            canMatch = false;
                        }
                    }
                }

                if (maximum - minimum > 1) {
                    canMatch = false;
                }
            }

            /*if (canMatch) {
             if (tour.getParams().mIndivPairingIndivBalanced) {
            
             // Search teams against which the player has played less match
             ArrayList<Team> possible = getPossibleTeams(current, currentOpp);
             if (!possible.contains(Opponent.mTeamMates)) {
             canMatch = false;
             }
            
             if (canMatch) {
             possible = Opponent.getPossibleTeams(current, opponentOpponent);
             if (!possible.contains(mTeamMates)) {
             canMatch = false;
             }
             }
            
             if (canMatch) {
            
             if (tour.getParams().mIndivPairingTeamBalanced) {
             HashMap<Team, Integer> hash = mTeamMates.getTeamOppositionCount(teams, current);
            
             Iterator it2 = hash.keySet().iterator();
             int minimum = 65535;
             int maximum = 0;
             while (it2.hasNext()) {
             Competitor en2 = (Competitor) it2.next();
             int nb2 = hash.get(en2);
             if (nb2 < minimum) {
             minimum = nb2;
             }
             if (nb2 > maximum) {
             maximum = nb2;
             }
             }
            
             if (hash.get(Opponent.mTeamMates) == minimum) {
             if (maximum - minimum == 0) {
             if (opponentOpponent.mTeamMates != mTeamMates) {
             canMatch = false;
             }
             }
             } else {
             canMatch = false;
             }
             }
             }
             }
             }*/
        }

        return canMatch;
    }

    /**
     * This method arrange matchs to avoid double meet and balanced matches
     *
     * @param round
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void roundCheck(Round round) {

        Tournament tour = Tournament.getTournament();
        //ArrayList<Match> matchs = round.getMatchs();

        int balancingTries = 10000;
        int triesThreshold = 10;
        boolean totallyBalanced = false;
        while ((!totallyBalanced) && (balancingTries > 0)) {
            totallyBalanced = true;
            balancingTries--;

            if (balancingTries % triesThreshold == 0) {
                round.shuffleMatchs();
            }
            LOG.log(Level.FINER, "Remaining tries: {0}", balancingTries);

            boolean balanced;
            for (int i = round.getMatchsCount() - 1; i > 0; i--) {

                Match current = round.getMatch(i);
                final Coach c1 = (Coach) current.getCompetitor1();
                final Coach c2 = (Coach) current.getCompetitor2();
                boolean have_played = c1.havePlayed(c2);

                balanced = true;
                boolean loop = false;
                if ((tour.getParams().isTeamTournament())
                        && (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {
                    if ((tour.getParams().isIndivPairingTeamBalanced()) || (tour.getParams().isIndivPairingIndivBalanced())) {
                        balanced = c1.isBalanced(c2, round) && c2.isBalanced(c1, round);
                        if (!balanced) {
                            LOG.log(Level.FINER, "{0} is not balanced", c1.getName());
                            totallyBalanced = false;
                        }
                    }
                }

                if ((have_played) || (!balanced)) {
                    //ArrayList<Competitor> possible = new ArrayList<>();
                    int k = i - 1;
                    while (k >= 0) {
                    //for (int k = i - 1; k >= 0; k--) {

                        // Get previous match opponent
                        Coach c1_tmp = (Coach) round.getMatch(k).getCompetitor1();
                        Coach c2_tmp = (Coach) round.getMatch(k).getCompetitor2();

                        //LOG.log(Level.FINER,"Testing " + c1_tmp.mName + " vs " + c2_tmp.mName);
                        boolean canMatch = c1.canMatch(c2_tmp, c1_tmp, c2, round);
                        if (loop) {
                            canMatch = canMatch && c2.canMatch(c1_tmp, c2_tmp, c1, round);
                        }

                        if (canMatch) {
                            round.getMatch(i).setCompetitor2(c2_tmp);
                            round.getMatch(k).setCompetitor2(c2);

                            LOG.log(Level.FINER, "{0} vs {1} becomes {2} vs {3}", new Object[]{c1.getName(), c2.getName(), c1.getName(), c2_tmp.getName()});
                            LOG.log(Level.FINER, "And {0} vs {1} becomes {2} vs {3}", new Object[]{c1_tmp.getName(), c2_tmp.getName(), c1_tmp.getName(), c2.getName()});

                            break;
                        } else {
                            canMatch = c1.canMatch(c1_tmp, c2_tmp, c2, round);
                            if (loop) {
                                canMatch = canMatch && c2.canMatch(c2_tmp, c1_tmp, c1, round);
                            }

                            if (canMatch) {
                                round.getMatch(i).setCompetitor2(c1_tmp);
                                round.getMatch(k).setCompetitor1(c2);

                                LOG.log(Level.FINER, "{0} vs {1} becomes {2} vs {3}", new Object[]{c1.getName(), c2.getName(), c1.getName(), c1_tmp.getName()});
                                LOG.log(Level.FINER, "And {0} vs {1} becomes {2} vs {3}", new Object[]{c1_tmp.getName(), c2_tmp.getName(), c2.getName(), c2_tmp.getName()});
                                break;
                            }
                        }

                        if (k == 0) {
                            if ((tour.getParams().isTeamTournament())
                                    && (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {
                                if ((tour.getParams().isIndivPairingTeamBalanced()) || (tour.getParams().isIndivPairingIndivBalanced())) {
                                    balanced = c1.isBalanced(c2, round) && c2.isBalanced(c1, round);
                                    if (!balanced) {
                                        LOG.log(Level.FINER, "{0} is still not balanced, loop", c1.getName());
                                        if (!loop) {
                                            k = round.getMatchsCount() - 1;
                                            loop = true;
                                        }
                                    }
                                }
                            }
                        }
                        k--;
                    }
                }
            }

            if ((tour.getParams().isTeamTournament())
                    && (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {
                Match cm = round.getMatch(0);
                if ((tour.getParams().isIndivPairingTeamBalanced()) || (tour.getParams().isIndivPairingIndivBalanced())) {
                    Coach c1 = (Coach) cm.getCompetitor1();
                    Coach c2 = (Coach) cm.getCompetitor2();
                    balanced = c1.isBalanced(c2, round) && c2.isBalanced(c1, round);
                    if (!balanced) {
                        LOG.log(Level.FINER, "{0} is not balanced", c1.getName());
                        totallyBalanced = false;
                    }
                }
            }

            if ((!totallyBalanced)
                    && (balancingTries == 0)) {
                int answer = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Le calcul de la ronde n'a pas abouti à un résultat satisfaisant\n"
                        + "Le calcul de ce type de ronde nécessite énromément de puissance\n"
                        + "de calcul. Voulez-vous continuer ?", "Ronde non satisfaisante", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    balancingTries = 10000;
                }
            }
        }
    }

    /**
     *
     * @param p
     */
    //@Override
    /*public void setPicture(BufferedImage p) {
     setPicture(p);
     }*/
    /**
     *
     * @param name
     */
    /*@Override
     public void setName(String name) {
     setName(name);
     }*/
    /**
     * @return the mCategory
     */
    public Category getCategory() {
        return mCategory;
    }

    /**
     * @param mCategory the mCategory to set
     */
    public void setCategory(Category mCategory) {
        this.mCategory = mCategory;
    }

    /**
     * @return the mTeam
     */
    public String getTeam() {
        return mTeam;
    }

    /**
     * @param mTeam the mTeam to set
     */
    public void setTeam(String mTeam) {
        this.mTeam = mTeam;
    }

    /**
     * @return the mRoster
     */
    public RosterType getRoster() {
        return mRoster;
    }

    /**
     * @param mRoster the mRoster to set
     */
    public void setRoster(RosterType mRoster) {
        this.mRoster = mRoster;
    }

    /**
     * @return the mNaf
     */
    public int getNaf() {
        return mNaf;
    }

    /**
     * @param mNaf the mNaf to set
     */
    public void setNaf(int mNaf) {
        this.mNaf = mNaf;
    }

    /**
     * @return the mRank
     */
    public int getRank() {
        return mRank;
    }

    /**
     * @param mRank the mRank to set
     */
    public void setRank(int mRank) {
        this.mRank = mRank;
    }

    /**
     * @return the mActive
     */
    public boolean isActive() {
        return mActive;
    }

    /**
     * @param mActive the mActive to set
     */
    public void setActive(boolean mActive) {
        this.mActive = mActive;
    }

    /**
     * @return the mTeamMates
     */
    public Team getTeamMates() {
        return mTeamMates;
    }

    /**
     * @param mTeamMates the mTeamMates to set
     */
    public void setTeamMates(Team mTeamMates) {
        this.mTeamMates = mTeamMates;
    }

    /**
     * @return the mNafRank
     */
    public double getNafRank() {
        return mNafRank;
    }

    /**
     * @param mNafRank the mNafRank to set
     */
    public void setNafRank(double mNafRank) {
        this.mNafRank = mNafRank;
    }

    /**
     * @return the mHandicap
     */
    public int getHandicap() {
        return mHandicap;
    }

    /**
     * @param mHandicap the mHandicap to set
     */
    public void setHandicap(int mHandicap) {
        this.mHandicap = mHandicap;
    }

}
