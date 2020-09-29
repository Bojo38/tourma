/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.xerces.impl.dv.util.Base64;
import org.jdom.DataConversionException;
import org.jdom.Element;
import teamma.data.Roster;
import tourma.MainFrame;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 * This class contains data relative to coach
 *
 * @author Frederic Berger
 */
public final class Coach extends Competitor implements IXMLExport, Serializable {

    protected static AtomicInteger sGenUID = new AtomicInteger(0);
    protected int UID = sGenUID.incrementAndGet();

    /**
     * Unique ID Getter
     *
     * @return Unique iD
     */
    public int getUID() {
        return UID;
    }

    /**
     * Unique ID Setter
     *
     * @param UID Unique ID
     */
    public void setUID(int UID) {
        this.UID = UID;
    }

    /**
     * Null Coach
     */
    private static Coach sNullCoach = null;

    /**
     * Coach Message 1 Identifier
     */
    private static final String CS_MESSAGE1 = "CoachMessage1";
    /**
     * Coach Message 2 Identifier
     */
    private static final String CS_MESSAGE2 = "CoachMessage2";

    /**
     * Coach Hashmap
     */
    private static HashMap<String, Coach> sCoachMap = new HashMap<>();
    /**
     * Coach Lock
     */
    private final static Object myLock = new Object();
    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(Coach.class.getName());

    /**
     * Coach Getter from hashmap
     *
     * @param s Key (name)
     * @return Coach
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
     * Coach Puller
     *
     * @param c
     */
    public void pull(Coach c) {
        super.pull(c);
        this.UID = c.getUID();

        this._PinCode = c._PinCode;
        this.mActive = c.mActive;
        this.mHandicap = c.mHandicap;
        this.mNaf = c.mNaf;
        this.mNafRank = c.mNafRank;
        this.mRank = c.mRank;
        this.mTeam = c.mTeam;

        //RosterType
        this.mRoster = RosterType.getRosterType(c.getRoster().getName());

        //Teammates
        if (c.getTeamMates() != null) {
            this.mTeamMates = Tournament.getTournament().getTeam(c.getTeamMates().getName());
            if (!mTeamMates.containsCoach(this)) {
                mTeamMates.addCoach(this);
            }
        }

        //Compositions
        mCompositions.clear();

        for (int i = 0; i < c.getCompositionCount(); i++) {
            teamma.data.Roster roster = new teamma.data.Roster();
            roster.pull(c.getComposition(i));
            mCompositions.add(roster);
        }

    }

    /**
     * Coach Pusher
     *
     * @param c Coach
     */
    public void push(Coach c) {

        super.push(c);
        if (c.isUpdated()) {
            this.UID = c.getUID();

            this._PinCode = c._PinCode;
            this.mActive = c.mActive;
            this.mHandicap = c.mHandicap;
            this.mNaf = c.mNaf;
            this.mNafRank = c.mNafRank;
            this.mRank = c.mRank;
            this.mTeam = c.mTeam;

            //RosterType
            this.mRoster = RosterType.getRosterType(c.getRoster().getName());

            //Teammates
            this.mTeamMates = Tournament.getTournament().getTeam(c.getTeamMates().getName());

            //Compositions
            mCompositions.clear();

            for (int i = 0; i < c.getCompositionCount(); i++) {
                teamma.data.Roster roster = new teamma.data.Roster();
                roster.pull(c.getComposition(i));
                mCompositions.add(roster);
            }
        }

    }

    /**
     * Put Coach in the Map
     *
     * @param s Key
     * @param c Coach
     */
    public static void putCoach(String s, Coach c) {
        sCoachMap.put(s, c);
    }

    /**
     * Get Null Coach
     *
     * @return Coach
     */
    public static Coach getNullCoach() {
        synchronized (Coach.myLock) {
            if (sNullCoach == null) {
                sNullCoach = new Coach(StringConstants.CS_NONE);
                sNullCoach.setTeamMates(Team.getNullTeam());
            }
        }
        return sNullCoach;
    }

    /**
     * Pin Code
     */
    private int _PinCode = 0;

    /**
     * Roster Team Name
     */
    private String mTeam;

    /**
     * Roster type
     */
    private RosterType mRoster;

    /**
     * Naf Number
     */
    private int mNaf;

    /**
     * Team Ranking
     */
    private int mRank;

    /**
     * Player is active or not
     */
    private boolean mActive = true;

    /**
     * Teammates
     */
    private Team mTeamMates = null;

    /**
     * List of roster manager by this coach
     */
    private final ArrayList<teamma.data.Roster> mCompositions;

    /**
     * Naf default ranking
     */
    private double mNafRank = 150.0;
    /**
     * Naf average ranking
     */
    private double mNafRankAvg = 150.0;
    /**
     * Handicap points
     */
    private int mHandicap = 0;
    /**
     * Log Indiv Balanced empty
     */
    private final String LOG_INDIV_EMPTY = "Individual balanced empty, using only team balanced";
    /**
     * Log Balanced empty
     */
    private final String LOG_BALANCED_EMPTY = "Balanced empty, using only possible";

    /**
     * New Coach constructor
     */
    public Coach() {
        super();
        mActive = true;
        mCompositions = new ArrayList<>();
        Random random = new Random();
        _PinCode = random.nextInt(10000);
    }

    /**
     * New Coach with name
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
     * Get count of composition
     *
     * @return
     */
    public int getCompositionCount() {
        return mCompositions.size();
    }

    /**
     * Get Composition by Index
     *
     * @param i Index
     * @return Composition
     */
    public teamma.data.Roster getComposition(int i) {
        return mCompositions.get(i);
    }

    /**
     * Add a compostion
     *
     * @param r New composition
     */
    public void addComposition(teamma.data.Roster r) {
        mCompositions.add(r);
        updated = true;
    }

    /**
     * Remove composition by Index
     *
     * @param i index
     */
    public void removeComposition(int i) {
        mCompositions.remove(i);
        updated = true;
    }

    @Override
    public int compareTo(final Object obj) {
        int result = -1;

        if (obj instanceof Coach) {
            if (_naf_avg) {
                result = ((Double) getNafRankAvg()).compareTo(((Coach) obj).getNafRankAvg());
            } else {
                result = ((Double) getNafRank()).compareTo(((Coach) obj).getNafRank());
            }
            if (result == 0) {
                try {
                    result = getName().compareTo(((IWithNameAndPicture) obj).getName());
                } catch (RemoteException re) {
                    JOptionPane.showMessageDialog(null, re.getLocalizedMessage());
                }
            }
        }
        return result;
    }

    @Override
    public Element getXMLElement() {

        final Element coach = new Element(StringConstants.CS_COACH);
        coach.setAttribute(StringConstants.CS_NAME, this.getName());
        coach.setAttribute(StringConstants.CS_TEAM, this.getTeam());
        coach.setAttribute(StringConstants.CS_ROSTER, this.getRoster().getName());
        coach.setAttribute(StringConstants.CS_NAF, Integer.toString(this.getNaf()));
        coach.setAttribute(StringConstants.CS_RANK, Integer.toString(this.getRank()));
        coach.setAttribute(StringConstants.CS_CLAN, this.getClan().getName());

        coach.setAttribute(StringConstants.CS_RANK_AVG, Double.toString(this.getNafRankAvg()));

        for (int i = 0; i < getCategoryCount(); i++) {
            if (getCategory(i) != null) {
                if (getCategory(i).getName() != null) {
                    Element ec = new Element(StringConstants.CS_CATEGORY);
                    ec.setAttribute(StringConstants.CS_NAME, getCategory(i).getName());
                    coach.addContent(ec);
                }
            }
        }

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
                    BufferedImage bi = new BufferedImage(getPicture().getIconWidth(), getPicture().getIconHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics g = bi.createGraphics();
                    getPicture().paintIcon(null, g, 0, 0);
                    g.dispose();
                    ImageIO.write(bi, "png", baos);
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
     * Get Roster description
     *
     * @return Description
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
                } else if (!rosters.contains(this.mRoster)) {
                    rosters.add(this.getRoster());
                }
            }

            if (this == m.getCompetitor2()) {
                if (((CoachMatch) m).getRoster2() != null) {
                    if (!rosters.contains(((CoachMatch) m).getRoster2())) {
                        rosters.add(((CoachMatch) m).getRoster2());
                    }
                } else if (!rosters.contains(this.mRoster)) {
                    rosters.add(this.getRoster());
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
                this.setNafAvg(coach.getAttribute(StringConstants.CS_RANK_AVG).getDoubleValue());
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
            //    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }

        try {
            Element image = coach.getChild(StringConstants.CS_PICTURE);
            if (image != null) {
                String encodedImage = image.getText();
                if (!encodedImage.isEmpty()) {
                    //byte[] bytes = DatatypeConverter.parseBase64Binary(encodedImage);
                    byte[] bytes = Base64.decode(encodedImage);
                    BufferedImage bi = ImageIO.read(new ByteArrayInputStream(bytes));
                    ImageIcon ii = new ImageIcon(bi);
                    setPicture(ii);
                }
            }
        } catch (IOException e) {
            LOG.log(Level.FINE, e.getLocalizedMessage());
        }

        try {
            this.setPinCode(Integer.parseInt(coach.getAttributeValue(StringConstants.CS_PINCODE)));
        } catch (NullPointerException npe) {
            _PinCode = 0;
        } catch (NumberFormatException npe) {
            _PinCode = 0;
        }

    }

    @Override
    public void addMatch(Competitor opponent, Round r) {
        CoachMatch m = new CoachMatch(r);
        m.setCompetitor1(this);
        m.setCompetitor2(opponent);
        r.addMatch(m);
        updated = true;
    }

    /**
     * Create a Match for this coach
     *
     * @param opponent Coach opponent
     * @param r Round
     * @return New Coach Match
     */
    public CoachMatch createMatch(Competitor opponent, Round r) {
        CoachMatch m = new CoachMatch(r);
        m.setCompetitor1(this);
        m.setCompetitor2(opponent);
        return m;
    }

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

    @Override
    public ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents, Round r) {

        Tournament tour = Tournament.getTournament();

        Parameters params = tour.getParams();
        ArrayList<Competitor> possible = new ArrayList<>(opponents);

        if (this.getClan() != tour.getClan(0)) {

            boolean avoidFirstMatch = params.isAvoidClansFirstMatch();
            int roundCount = tour.getRoundsCount();
            boolean avoidClansMatch = params.isAvoidClansMatch();
            boolean clansEnable = params.isEnableClans();
            if ((clansEnable) && ((avoidFirstMatch && (roundCount <= 0)) || (avoidClansMatch))) {

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
            i = 0;
            while (i < possible.size()) {
                Team t = ((Coach) possible.get(i)).getTeamMates();
                if (t.containsCoach(this)) {
                    possible.remove(i);
                } else {
                    i++;
                }
            }

            ArrayList<Team> oppteam = getPossibleTeams(r, null);

            if (oppteam.isEmpty()) {
                System.err.println("Empry possible opposing team");
            }

            ArrayList<Competitor> balanced = new ArrayList<>(possible);

            // Keep only the players in possible list who are in team
            // which are still in the map
            ArrayList<Competitor> buffer = new ArrayList<>(balanced);

            if (params.isIndivPairingTeamBalanced()) {
                for (Competitor buffer1 : buffer) {
                    if (buffer1 instanceof Coach) {
                        Coach c = (Coach) buffer1;
                        if (!oppteam.contains(c.getTeamMates())) {
                            balanced.remove(c);
                        }
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
                    System.err.println(LOG_INDIV_EMPTY);
                }
            }

            if (!balanced.isEmpty()) {
                possible = balanced;
            } else {
                LOG.log(Level.FINER, LOG_BALANCED_EMPTY);
                System.err.println(LOG_BALANCED_EMPTY);
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
     * Get possible opponent's team
     *
     * @param current Current Round
     * @param currentOpponent Current Opponent
     * @return List of possible teams
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
     * Return the minimum value from the Hashmap
     *
     * @param hash Hasmap
     * @return hash
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

    @Override
    public HashMap<Team, Integer> getTeamOppositionCount(ArrayList<Team> teams, Round r) {

        HashMap<Team, Integer> map = new HashMap<>();

        // Build opponents map
        for (Team t : teams) {
            if (!t.getName().equals(mTeamMates.getName())) {
                map.put(t, 0);
            }
        }

        for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
            Round round = Tournament.getTournament().getRound(i);
            for (CoachMatch m : round.getCoachMatchs()) {
                Coach opp = null;
                if (this == m.getCompetitor1()) {
                    opp = (Coach) m.getCompetitor2();
                }
                if (this == m.getCompetitor2()) {
                    opp = (Coach) m.getCompetitor1();
                }
                if (opp != null) {
                    Team Other = opp.getTeamMates();
                    if (map.get(Other) != null) {
                        int nb = map.get(Other);
                        nb += 1;
                        map.put(Other, nb);
                    }
                }
            }
        }
        // Compute the number of match per opponent
        /*for (int i = 0; i < getMatchCount(); i++) {
            Match mMatch = getMatch(i);
            CoachMatch m = (CoachMatch) mMatch;
            Coach opp;
            if (this == m.getCompetitor1()) {
                opp = (Coach) m.getCompetitor2();
            } else {
                opp = (Coach) m.getCompetitor1();
            }
            Team Other = opp.getTeamMates();
            if (map.get(Other) != null) {
                int nb = map.get(Other);
                nb += 1;
                map.put(Other, nb);
            }
        }*/

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
                        nb += 1;
                        map.put(Other, nb);
                    }

                }
            }
        }

        return map;
    }

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

    @Override
    public void addMatchRoundRobin(Competitor c, Round r, boolean complete) {
        addMatch(c, r);
    }

    /**
     * test if the draw is balanced
     *
     * @param opp Opponent Coach
     * @param round Round
     * @return the draw is balanced
     */
    public boolean isBalanced(Coach opp, Round round) {
        Tournament tour = Tournament.getTournament();
        boolean balanced = true;
        if ((tour.getParams().isTeamTournament())
                && (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {

            //System.out.println(this.getName() + "(" + this.getTeamMates().getName() + ") vs " + opp.getName() + "(" + opp.getTeamMates().getName() + ")");
            if (tour.getParams().isIndivPairingTeamBalanced()) {
                balanced = this.getTeamMates().isBalanced(opp.getTeamMates(), round);
            }

            if (tour.getParams().isIndivPairingIndivBalanced()) {
                balanced = balanced & this.isBalanced(opp.getTeamMates(), round);
            }
        }
        return balanced;
    }

    /**
     * Print if balanced
     *
     * @param round Round concerned
     */
    public void printBalanced(Round round) {
        Tournament tour = Tournament.getTournament();

        if ((tour.getParams().isTeamTournament())
                && (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {

            /*if (tour.getParams().isIndivPairingTeamBalanced()) {
                balanced = this.getTeamMates().isBalanced(opp.getTeamMates(), round);
            }*/
            if (tour.getParams().isIndivPairingIndivBalanced()) {

                ArrayList<Team> teams = new ArrayList<>();

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

                System.out.println(this.getName() + " Max: " + maximum + " Min: " + minimum);
            }
        }
    }

    /**
     * Test if the Draw is teambalanced
     *
     * @param opp Opponent's team
     * @param round Current Round
     * @return The draw is balanced
     */
    private boolean isBalanced(Team opp, Round round) {
        boolean balanced = true;
        ArrayList<Team> teams = new ArrayList<>();

        if (this.getTeamMates() == opp) {
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

        int nb = hash.get(opp);

        if ((maximum == nb) && (maximum - minimum > 1)) {
            balanced = false;
        }
        return balanced;
    }

    /**
     * Get the minimum Balanced team
     *
     * @param round Current Round
     * @return teams
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
     * Check if Then opponent can match
     * @param Opponent Opponent
     * @param opponentOpponent Last opponent opponent
     * @param currentOpp Current opponent
     * @param current Current Round
     * @return if it can match
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

        int balancingTries = 100000;
        int triesThreshold = 10;
        boolean totallyBalanced = false;
        while ((!totallyBalanced) && (balancingTries > 0)) {

            //System.out.println("Retry");
            totallyBalanced = true;
            balancingTries--;

            if (balancingTries % triesThreshold == 0) {
                round.shuffleMatchs();
            }
            //LOG.log(Level.FINER, "Remaining tries: {0}", balancingTries);

            for (int i = round.getMatchsCount() - 1; i > 0; i--) {

                Match current = round.getMatch(i);
                Coach c1 = (Coach) current.getCompetitor1();
                Coach c2 = (Coach) current.getCompetitor2();
                boolean have_played = c1.havePlayed(c2);

                boolean balanced = c1.isBalanced(c2, round);

                if ((have_played) || (!balanced)) {
                    //ArrayList<Competitor> possible = new ArrayList<>();
                    int k = i - 1;
                    while (k >= 0) {
                        //for (int k = i - 1; k >= 0; k--) {

                        Match m1 = round.getMatch(i);
                        Match m2 = round.getMatch(k);

                        // Get previous match opponent
                        Coach c1_tmp = (Coach) m2.getCompetitor1();
                        Coach c2_tmp = (Coach) m2.getCompetitor2();

                        c1 = (Coach) m1.getCompetitor1();
                        c2 = (Coach) m1.getCompetitor2();

                        //LOG.log(Level.FINER,"Testing " + c1_tmp.mName + " vs " + c2_tmp.mName);
                        boolean canMatch = c1.canMatch(c2_tmp, c1_tmp, c2, round);
                        //canMatch = canMatch && c2.canMatch(c1_tmp, c2_tmp, c1, round);

                        if (canMatch) {
                            m1.setCompetitor2(c2_tmp);
                            m2.setCompetitor2(c2);

                            if (!c1.isBalanced(c2_tmp, round) || !c1_tmp.isBalanced(c2, round)) {
                                m1.setCompetitor2(c2);
                                m2.setCompetitor2(c2_tmp);
                                canMatch = false;
                                totallyBalanced = false;
                            } else {
                                //System.err.println("Swap done");
                                break;
                            }
                        }
                        if (!canMatch) {
                            canMatch = c1.canMatch(c1_tmp, c2_tmp, c2, round);
                            //canMatch = canMatch && c2.canMatch(c2_tmp, c1_tmp, c1, round);

                            if (canMatch) {
                                m1.setCompetitor2(c1_tmp);
                                m2.setCompetitor1(c2);

                                if (!c1.isBalanced(c1_tmp, round) || !c2_tmp.isBalanced(c2, round)) {
                                    m1.setCompetitor2(c2);
                                    m2.setCompetitor1(c1_tmp);
                                    totallyBalanced = false;
                                } else {
                                    //System.err.println("Swap done");
                                    break;
                                }
                            }
                        }

                        k--;
                    }
                }
            }

            //totallyBalanced = true;
            for (int i = round.getMatchsCount() - 1; i >= 0; i--) {
                Match current = round.getMatch(i);
                Coach c1 = (Coach) current.getCompetitor1();
                Coach c2 = (Coach) current.getCompetitor2();
                boolean have_played = c1.havePlayed(c2);
                boolean balanced = c1.isBalanced(c2, round) && c2.isBalanced(c1, round);

                totallyBalanced = totallyBalanced && (!have_played) && balanced;

                if (!totallyBalanced) {
                    //System.err.println("Round not balanced: " + c1.getName() + "(" + c1.getTeamMates() + ") vs " + c2.getName() + "(" + c2.getTeamMates() + ")");
                    break;
                }
            }

            if ((!totallyBalanced) && (balancingTries == 0)) {

                int answer = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), Translate.translate(CS_MESSAGE1), Translate.translate(CS_MESSAGE2), JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    balancingTries = 100000;
                }
            }

            if (!totallyBalanced) {
                //System.err.println("Not Balanced");

                Random ran = new Random();
                int index = ran.nextInt(round.getMatchsCount() - 1) + 1;

                Coach c1 = (Coach) round.getMatch(0).getCompetitor1();
                Coach c2 = (Coach) round.getMatch(0).getCompetitor2();
                Coach c1_tmp = (Coach) round.getMatch(index).getCompetitor1();
                Coach c2_tmp = (Coach) round.getMatch(index).getCompetitor2();
                round.getMatch(0).setCompetitor2(c1_tmp);
                round.getMatch(index).setCompetitor1(c2);

                Match m = round.getMatch(0);
                round.removeMatch(m);
                round.addMatch(m);

            }
        }
    }

    /**
     * Team Getter
     * @return the Team
     */
    public String getTeam() {
        return mTeam;
    }

    /**
     * Team Setter
     * @param mTeam the mTeam to set
     */
    public void setTeam(String mTeam) {
        this.mTeam = mTeam;
        updated = true;
    }

    /**
     * Roster Getter
     * @return the mRoster
     */
    public RosterType getRoster() {
        return mRoster;
    }

    /**
     * Roster Setter
     * @param mRoster the mRoster to set
     */
    public void setRoster(RosterType mRoster) {
        this.mRoster = mRoster;
        updated = true;
    }

    /**
     * Naf number Getter
     * @return the mNaf
     */
    public int getNaf() {
        return mNaf;
    }

    /**
     * Naf Number setter
     * @param mNaf the mNaf to set
     */
    public void setNaf(int mNaf) {
        this.mNaf = mNaf;
        updated = true;
    }

    /**
     * Rank Getter
     * @return the mRank
     */
    public int getRank() {
        return mRank;
    }

    /**
     * Rank Setter
     * @param mRank the mRank to set
     */
    public void setRank(int mRank) {
        this.mRank = mRank;
        updated = true;
    }

    /**
     * Is the coach active ?
     * @return the mActive
     */
    public boolean isActive() {
        return mActive;
    }

    /**
     * Active player Setter
     * @param mActive the mActive to set
     */
    public void setActive(boolean mActive) {
        this.mActive = mActive;
    }

    /**
     * Teammates Getter
     * @return the mTeamMates
     */
    public Team getTeamMates() {
        return mTeamMates;
    }

    /**
     * Teammates Setter
     * @param mTeamMates the mTeamMates to set
     */
    public void setTeamMates(Team mTeamMates) {
        this.mTeamMates = mTeamMates;
        updated = true;
    }

    
    /**
     * Naf Ranking Getter
     * @return the mNafRank
     */
    public double getNafRank() {
        return mNafRank;
    }

    /**
     * Average Naf ranking getter
     * @return 
     */
    public double getNafRankAvg() {
        return mNafRankAvg;
    }

    /**
     * Naf ranking setter
     * @param mNafRank the mNafRank to set
     */
    public void setNafRank(double mNafRank) {
        this.mNafRank = mNafRank;
        updated = true;
    }

    /**
     * Naf ranking average Setter
     * @param mNafRank 
     */
    public void setNafAvg(double mNafRank) {
        this.mNafRankAvg = mNafRank;
        updated = true;
    }

    /**
     * Handicap Getter
     * @return the mHandicap
     */
    public int getHandicap() {
        return mHandicap;
    }

    /**
     * Handicap Setter
     * @param mHandicap the mHandicap to set
     */
    public void setHandicap(int mHandicap) {
        this.mHandicap = mHandicap;
    }

    /**
     * Pin code Getter
     * @return 
     */
    public int getPinCode() {
        return _PinCode;
    }

    /**
     * Pin code Setter
     * @param pin 
     */
    public void setPinCode(int pin) {
        _PinCode = pin;
        updated = true;
    }

    /**
     * Match Roster name Getter
     * @param i match index
     * @return 
     */
    public String getMatchRoster(int i) {
        String res = mRoster.getName();
        if (i < mMatchs.size()) {
            CoachMatch cm = (CoachMatch) mMatchs.get(i);
            if (cm.getCompetitor1() == this) {
                if (cm.getRoster1() != null) {
                    res = cm.getRoster1().getName();
                }
            }
            if (cm.getCompetitor2() == this) {
                if (cm.getRoster2() != null) {
                    res = cm.getRoster2().getName();
                }
            }
        }
        return res;
    }
}
