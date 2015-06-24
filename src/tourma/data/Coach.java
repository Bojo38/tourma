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
import org.apache.xerces.impl.dv.util.Base64;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import teamma.data.Roster;
import tourma.MainFrame;
import tourma.languages.Translate;
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
    private static final String CS_MESSAGE1 = "CoachMessage1";
    private static final String CS_MESSAGE2 = "CoachMessage2";

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
            /*if ((Team.getNullTeam() != null) && ((sNullCoach.getTeam() == null))) {
             sNullCoach.setTeamMates(Team.getNullTeam());
             }*/
        }

        return sNullCoach;
    }

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
    private final String LOG_INDIV_EMPTY = "Individual balanced empty, using only team balanced";
    private final String LOG_BALANCED_EMPTY = "Balanced empty, using only possible";

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
     * @return
     */
    @Override
    public Element getXMLElement() {

        final Element coach = new Element(StringConstants.CS_COACH);
        coach.setAttribute(StringConstants.CS_NAME, this.getName());
        coach.setAttribute(StringConstants.CS_TEAM, this.getTeam());
        coach.setAttribute(StringConstants.CS_ROSTER, this.getRoster().getName());
        coach.setAttribute(StringConstants.CS_NAF, Integer.toString(this.getNaf()));
        coach.setAttribute(StringConstants.CS_RANK, Integer.toString(this.getRank()));
        coach.setAttribute(StringConstants.CS_CLAN, this.getClan().getName());

        for (int i = 0; i < getCategoryCount(); i++) {
            if (getCategory(i) != null) {
                if (getCategory(i).getName() != null) {
                    Element ec = new Element(StringConstants.CS_CATEGORY);
                    ec.setAttribute(StringConstants.CS_NAME, getCategory(i).getName());
                }
            }
        }
        /*if (this.getCategory() != null) {
         coach.setAttribute(StringConstants.CS_CATEGORY, this.getCategory().getName());
         } else {
         coach.setAttribute(StringConstants.CS_CATEGORY, StringConstants.CS_None);
         }*/
        coach.setAttribute(StringConstants.CS_ACTIVE, Boolean.toString(this.isActive()));

        coach.setAttribute(StringConstants.CS_HANDICAP, Integer.toString(this.getHandicap()));

        for (Roster mComposition : mCompositions) {
            final Element compo = mComposition.getXMLElement();
            coach.addContent(compo);
        }

        Element image = new Element(StringConstants.CS_PICTURE);

        if (getPicture() != null) {
            try {
                String encodedImage;
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    ImageIO.write(getPicture(), "png", baos);
                    baos.flush();
                    //encodedImage = DatatypeConverter.printBase64Binary(baos.toByteArray());                    
                    encodedImage = Base64.encode(baos.toByteArray());
                    baos.close();
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
            this.setTeam(coach.getAttributeValue(StringConstants.CS_TEAM));
            String rName = coach.getAttributeValue(StringConstants.CS_ROSTER);
            String rosterName = RosterType.getRosterName(rName);
            RosterType tmpRoster = RosterType.getRosterType(rosterName);
            if (tmpRoster == null) {
                tmpRoster = new RosterType(rName);
            }
            this.setRoster(tmpRoster);

            this.setNaf(coach.getAttribute(StringConstants.CS_NAF).getIntValue());
            this.setRank(coach.getAttribute(StringConstants.CS_RANK).getIntValue());
            this.setClan(Clan.getClan(coach.getAttributeValue(StringConstants.CS_CLAN)));

            if (coach.getAttributeValue(StringConstants.CS_CATEGORY) != null) {
                this.addCategory(Category.getCategory(coach.getAttributeValue(StringConstants.CS_CATEGORY)));
            }
            final List<Element> cats = coach.getChildren(StringConstants.CS_CATEGORY);
            final Iterator<Element> itCat = cats.iterator();
            while (itCat.hasNext()) {
                Element cat = itCat.next();
                Category category = Category.getCategory(cat.getAttributeValue(StringConstants.CS_NAME));
                this.addCategory(category);
            }

            Coach.sCoachMap.put(getName(), this);

            try {
                this.setActive(coach.getAttribute(StringConstants.CS_ACTIVE).getBooleanValue());
                this.setHandicap(coach.getAttribute(StringConstants.CS_HANDICAP).getIntValue());
            } catch (NullPointerException npe) {
                // Do nothing
            }

            if (this.getClan() == null) {
                if (Tournament.getTournament().getClansCount() == 0) {
                    Tournament.getTournament().addClan(new Clan(Translate.translate(Translate.CS_None)));
                }
                this.setClan(Tournament.getTournament().getClan(0));
            }

            final List<Element> compos = coach.getChildren(StringConstants.CS_COMPOSITION);
            final Iterator<Element> itC = compos.iterator();
            while (itC.hasNext()) {
                Element compo = itC.next();
                teamma.data.Roster c = new teamma.data.Roster();
                c.setXMLElement(compo);
                this.mCompositions.add(c);
            }

        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }

        try {
            Element image = coach.getChild(StringConstants.CS_PICTURE);
            if (image != null) {
                String encodedImage = image.getText();
                if (!encodedImage.isEmpty()) {
                    //byte[] bytes = DatatypeConverter.parseBase64Binary(encodedImage);
                    byte[] bytes = Base64.decode(encodedImage);
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

        int i = 0;

        while (i < possible.size()) {
            if (possible.get(i).havePlayed(this)) {
                possible.remove(i);
            } else {
                i++;
            }
        }

        if ((params.isTeamTournament()) && (params.getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {
            i=0;
            while (i < possible.size()) {
                Team t=((Coach) possible.get(i)).getTeamMates();
                if (t.containsCoach(this)) {
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

            if (params.isIndivPairingTeamBalanced()) {
                for (Competitor buffer1 : buffer) {
                    Coach c = (Coach) buffer1;
                    if (!oppteam.contains(c.getTeamMates())) {
                        balanced.remove(c);
                    }
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
                        indivbalanced.remove(c);
                    }
                }

                if (!indivbalanced.isEmpty()) {
                    balanced = indivbalanced;
                } else {
                    LOG.log(Level.FINER, LOG_INDIV_EMPTY);
                }
            }

            if (!balanced.isEmpty()) {
                possible = balanced;
            } else {
                LOG.log(Level.FINER, LOG_BALANCED_EMPTY);
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
            if (getClan() != null) {
                tmp = getName() + " / " + getClan().getName();
            }
        }
        if (params.isTeamTournament()) {
            if (getTeamMates() != null) {
                tmp = getName() + " / " + getTeamMates().getName();
            }

        }
        return tmp;
    }

    /**
     *
     * @param c
     * @param r
     */
    @Override
    public void addMatchRoundRobin(Competitor c, Round r,boolean complete) {
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

        Iterator<Team> it2 = hash.keySet().iterator();
        int minimum = 65535;
        int maximum = 0;
        while (it2.hasNext()) {
            Competitor en2 = it2.next();
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

                Iterator<Team> it2 = hash.keySet().iterator();
                int minimum = 65535;
                int maximum = 0;
                while (it2.hasNext()) {
                    Competitor en2 = it2.next();
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
            //LOG.log(Level.FINER, "Remaining tries: {0}", balancingTries);

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
                            //               LOG.log(Level.FINER, "{0} is not balanced", c1.getName());
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

              //              LOG.log(Level.FINER, "{0} vs {1} becomes {2} vs {3}", new Object[]{c1.getName(), c2.getName(), c1.getName(), c2_tmp.getName()});
                            //              LOG.log(Level.FINER, "And {0} vs {1} becomes {2} vs {3}", new Object[]{c1_tmp.getName(), c2_tmp.getName(), c1_tmp.getName(), c2.getName()});
                            break;
                        } else {
                            canMatch = c1.canMatch(c1_tmp, c2_tmp, c2, round);
                            if (loop) {
                                canMatch = canMatch && c2.canMatch(c2_tmp, c1_tmp, c1, round);
                            }

                            if (canMatch) {
                                round.getMatch(i).setCompetitor2(c1_tmp);
                                round.getMatch(k).setCompetitor1(c2);

                //                LOG.log(Level.FINER, "{0} vs {1} becomes {2} vs {3}", new Object[]{c1.getName(), c2.getName(), c1.getName(), c1_tmp.getName()});
                                //                LOG.log(Level.FINER, "And {0} vs {1} becomes {2} vs {3}", new Object[]{c1_tmp.getName(), c2_tmp.getName(), c2.getName(), c2_tmp.getName()});
                                break;
                            }
                        }

                        if (k == 0) {
                            if ((tour.getParams().isTeamTournament())
                                    && (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {
                                if ((tour.getParams().isIndivPairingTeamBalanced()) || (tour.getParams().isIndivPairingIndivBalanced())) {
                                    balanced = c1.isBalanced(c2, round) && c2.isBalanced(c1, round);
                                    if (!balanced) {
                                        //                      LOG.log(Level.FINER, "{0} is still not balanced, loop", c1.getName());
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
                int answer = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), Translate.translate(CS_MESSAGE1), Translate.translate(CS_MESSAGE2), JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    balancingTries = 10000;
                }
            }
        }
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
