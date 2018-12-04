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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.xerces.impl.dv.util.Base64;
import org.jdom.Element;
import tourma.JdgPairing;
import tourma.MainFrame;
import tourma.utility.StringConstants;
import tourma.utils.Generation;

/**
 *
 * @author Frederic Berger
 */
public class Team extends Competitor implements IXMLExport, IContainCoachs, Serializable {

    protected static AtomicInteger sGenUID = new AtomicInteger(0);
    protected int UID = sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void pull(Team t) {
        super.pull(t);
        this.UID = t.getUID();

    }

    public void push(Team t) {
        super.push(t);
        if (t.isUpdated()) {
            this.UID = t.getUID();
        }

    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    /**
     *
     */
    private static Team sNullTeam;

    /**
     *
     */
    private static HashMap<String, Team> sTeamMap = new HashMap<>();

    private static final Object myLock = new Object();
    private static final Logger LOG = Logger.getLogger(Team.class.getName());

    /**
     *
     * @return
     */
    public static Team getNullTeam() {

        synchronized (Team.myLock) {
            if (sNullTeam == null) {
                sNullTeam = new Team(StringConstants.CS_NONE);
                int nbTeamMates = Tournament.getTournament().getParams().getTeamMatesNumber();

                for (int i = 0; i < nbTeamMates; i++) {
                    sNullTeam.addCoach(Coach.getNullCoach());
                    if (Coach.getNullCoach().getTeamMates() == null) {
                        Coach.getNullCoach().setTeamMates(sNullTeam);
                    }
                }
                sNullTeam.setName(StringConstants.CS_NONE);
            }

        }
        return sNullTeam;
    }

    /**
     * @param asNullTeam the sNullTeam to set
     */
    public static void setNullTeam(Team asNullTeam) {
        sNullTeam = asNullTeam;
    }

    /**
     * Renew Hashmap of teams
     */
    public static void newTeamMap() {
        sTeamMap = new HashMap<>();
    }

    /**
     *
     * @param n
     * @param t
     */
    public static void putTeam(String n, Team t) {
        sTeamMap.put(n, t);
    }

    /**
     *
     * @param n
     * @return
     */
    public static Team getTeam(String n) {
        return sTeamMap.get(n);
    }
    /**
     *
     */
    private final ArrayList<Coach> mCoachs;

    /**
     * Default constructor
     */
    public Team() {
        super();
        mCoachs = new ArrayList<>();

    }

    /**
     *
     * @param name
     */
    public Team(final String name) {
        super(name);
        mCoachs = new ArrayList<>();

    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        String text = super.getName();
        /*    if (Tournament.getTournament().getParams().isEnableClans()) {
            if (getClan() != null) {
                text += " (" + getClan().getName() + ")";
            }
        }*/
        return text;
    }

    @Override
    public int compareTo(final Object obj) {
        int result = -1;
        if (obj instanceof Team) {
            Team team = (Team) obj;
            double rank = 0.0;
            for (Coach mCoach : mCoachs) {
                if (_naf_avg) {
                    rank += mCoach.getNafRankAvg();
                } else {
                    rank += mCoach.getNafRank();
                }
            }
            rank /= getCoachsCount();

            double rankobj = 0;
            for (Coach mCoach : team.mCoachs) {
                if (_naf_avg) {
                    rankobj += mCoach.getNafRankAvg();
                } else {
                    rankobj += mCoach.getNafRank();
                }
            }
            rankobj /= team.getCoachsCount();

            result = ((Double) rank).compareTo(rankobj);
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Team) {
            Team t = (Team) o;
            if (t.getName().equals(this.getName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.mCoachs);
        return hash;
    }

    /**
     *
     * @return
     */
    public int getActivePlayerNumber() {
        int nb = 0;

        for (int i = 0; i < getCoachsCount(); i++) {
            if (getCoach(i).isActive()) {
                nb++;
            }
        }
        return nb;
    }

    /**
     *
     * @return
     */
    public ArrayList<Coach> getActivePlayers() {
        final ArrayList<Coach> v = new ArrayList<>();
        if (this == getNullTeam()) {
            for (int i = 0; i < Tournament.getTournament().getParams().getTeamMatesNumber(); i++) {
                v.add(Coach.getNullCoach());
            }
        } else {
            for (Coach mCoach : mCoachs) {
                if (mCoach.isActive()) {
                    v.add(mCoach);
                }
            }
        }
        return v;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element team = new Element(StringConstants.CS_TEAM);
        team.setAttribute(StringConstants.CS_NAME, super.getName());

        if (this.getClan() != null) {
            team.setAttribute(StringConstants.CS_CLAN, this.getClan().getName());
        }

        for (Coach mCoach : this.mCoachs) {
            final Element coach = new Element(StringConstants.CS_COACH);
            coach.setAttribute(StringConstants.CS_NAME, mCoach.getName());
            team.addContent(coach);
        }

        for (int i = 0; i < getCategoryCount(); i++) {
            Element ec = new Element(StringConstants.CS_CATEGORY);
            ec.setAttribute(StringConstants.CS_NAME, getCategory(i).getName());
            team.addContent(ec);
        }

        try {
            Element image = new Element(StringConstants.CS_PICTURE);
            String encodedImage;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                if (getPicture() != null) {
                    BufferedImage bi = new BufferedImage(getPicture().getIconWidth(), getPicture().getIconHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics g = bi.createGraphics();
                    getPicture().paintIcon(null, g, 0, 0);
                    g.dispose();
                    ImageIO.write(bi, "png", baos);
                    baos.flush();
                    encodedImage = Base64.encode(baos.toByteArray());
                    image.addContent(encodedImage);
                    team.addContent(image);
                }
                // should be inside a finally block
            }

        } catch (IOException e) {
            LOG.log(Level.INFO, e.getLocalizedMessage());
        }
        return team;
    }

    public Element getXMLElementForDisplay() {
        final Element team = this.getXMLElement();

        // Remove all Coachs
        team.removeChildren(StringConstants.CS_COACH);

        // Adding complete coachs
        for (Coach mCoach : this.mCoachs) {
            final Element coach = mCoach.getXMLElement();
            team.addContent(coach);
        }
        return team;
    }

    /**
     *
     * @param team
     */
    @Override
    public void setXMLElement(final Element team) {
        this.setName(team.getAttributeValue(StringConstants.CS_NAME));
        final List<Element> coachs2 = team.getChildren(StringConstants.CS_COACH);
        final Iterator<Element> m = coachs2.iterator();
        this.mCoachs.clear();

        Team.sTeamMap.put(getName(), this);
        while (m.hasNext()) {
            final Element coach = m.next();
            Coach c = Coach.getCoach(coach.getAttribute(StringConstants.CS_NAME).getValue());
            if (c == null) {
                c = new Coach(coach.getAttribute(StringConstants.CS_NAME).getValue());
                Tournament.getTournament().addCoach(c);
            }
            c.setTeamMates(this);
            this.mCoachs.add(c);
        }

        final List<Element> cats = team.getChildren(StringConstants.CS_CATEGORY);
        final Iterator<Element> itCat = cats.iterator();
        while (itCat.hasNext()) {
            Element cat = itCat.next();
            Category category = Category.getCategory(cat.getAttributeValue(StringConstants.CS_NAME));
            this.addCategory(category);
        }

        try {
            Element image = team.getChild(StringConstants.CS_PICTURE);
            if (image != null) {
                String encodedImage = image.getText();
                byte[] bytes = Base64.decode(encodedImage);
                BufferedImage bi = ImageIO.read(new ByteArrayInputStream(bytes));
                ImageIcon ii = new ImageIcon(bi);
                setPicture(ii);
            }
        } catch (IOException e) {
        }

        try {
            String ClanName = team.getAttributeValue(StringConstants.CS_CLAN);
            this.setClan(Clan.getClan(ClanName));
        } catch (Exception e1) {
        }
    }

    public void setXMLElementForDisplay(final Element team) {

        List<Element> childs = team.getChildren(StringConstants.CS_COACH);
        Iterator<Element> it = childs.iterator();
        while (it.hasNext()) {
            Element child = it.next();
            Coach c = new Coach();
            c.setXMLElement(child);
            Tournament.getTournament().addCoach(c);
        }

        setXMLElement(team);
    }

    /**
     *
     * @param opponent
     * @param r
     */
    @Override
    public void addMatch(Competitor opponent, Round r) {
        Tournament tour = Tournament.getTournament();

        final ArrayList<Round> vs = new ArrayList<>();
        for (int i = 0; i < tour.getRoundsCount(); i++) {
            if (tour.getRound(i).getHour().before(r.getHour())) {
                vs.add(tour.getRound(i));
            }
        }
        vs.add(r);

        Team team1 = this;
        Team team2;
        if (opponent instanceof Team) {
            team2 = (Team) opponent;
        } else {
            return;
        }

        TeamMatch m = new TeamMatch(r);
        m.setCompetitor1(team1);
        m.setCompetitor2(team2);
        r.addMatch(m);

        this.addMatch(m);
        opponent.addMatch(m);

        switch (tour.getParams().getTeamIndivPairing()) {
            // Ranking
            case RANKING:
                if (vs.size() == 1) {
                    final ArrayList<Coach> shuffle2 = new ArrayList<>(team2.getActivePlayers());
                    Collections.shuffle(shuffle2);
                    for (int k = 0; k < tour.getParams().getTeamMatesNumber(); k++) {
                        m.addMatch(team1.getActivePlayers().get(k).createMatch(shuffle2.get(k), r));
                    }
                } else {
                    final ArrayList<ObjectRanking> coachs1 = Generation.subRanking(team1, vs, true);
                    final ArrayList<ObjectRanking> coachs2 = Generation.subRanking(team2, vs, true);
                    for (int k = 0; k < coachs1.size(); k++) {
                        m.addMatch(((Coach) coachs1.get(k).getObject()).createMatch((Competitor) coachs2.get(k).getObject(), r));
                    }
                }
                break;
            // Manual
            case FREE:
                final JdgPairing jdg = new JdgPairing(MainFrame.getMainFrame(), true, team1, team2, r, m);
                jdg.setVisible(true);
                break;
            // genRandom
            case RANDOM:
                final ArrayList<Coach> shuffle2 = new ArrayList<>(team2.getActivePlayers());
                Collections.shuffle(shuffle2);
                for (int k = 0; k < tour.getParams().getTeamMatesNumber(); k++) {
                    m.addMatch(team1.getActivePlayers().get(k).createMatch(shuffle2.get(k), r));
                }
                break;
            // NAF
            case NAF:
                final ArrayList<Coach> sort1 = new ArrayList<>(team1.getActivePlayers());
                final ArrayList<Coach> sort2 = new ArrayList<>(team2.getActivePlayers());
                Collections.sort(sort1);
                Collections.sort(sort2);

                for (int k = 0; k < tour.getParams().getTeamMatesNumber(); k++) {
                    m.addMatch(sort1.get(k).createMatch(sort2.get(k), r));
                }
                break;
        }
    }

    /**
     *
     * @param opponent
     * @return
     */
    @Override
    public boolean havePlayed(Competitor opponent) {
        boolean have_played = false;
        for (Coach mCoach : mCoachs) {
            if (opponent instanceof Team) {
                for (int k = 0; k < ((IContainCoachs) opponent).getCoachsCount(); k++) {
                    for (int i = 0; i < mCoach.getMatchCount(); i++) {
                        Coach c = ((IContainCoachs) opponent).getCoach(k);
                        if ((mCoach.getMatch(i).getCompetitor1() == c) || (mCoach.getMatch(i).getCompetitor2() == c)) {
                            have_played = true;
                            break;
                        }
                    }
                    if (have_played) {
                        break;
                    }
                }

            }
            if (have_played) {
                break;
            }
        }
        return have_played;
    }

    /**
     *
     * @param opponents
     * @param r
     * @return
     */
    @Override
    public ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents, Round r
    ) {

        Tournament tour = Tournament.getTournament();
        Parameters params = tour.getParams();

        ArrayList<Competitor> possible = new ArrayList<>(opponents);

        if (this.getClan() != null) {
            if (this.getClan() != tour.getClan(0)) {
                if ((params.isEnableClans()) && ((params.isAvoidClansFirstMatch() && tour.getRoundsCount() == 0) || (params.isAvoidClansMatch()))) {
                    int i = 0;
                    while (i < possible.size()) {
                        if ((possible.get(i).getClan() != null) && (this.getClan() != null)) {
                            if (possible.get(i).getClan().getName().equals(this.getClan().getName())) {
                                possible.remove(i);
                                i--;
                            }
                        }
                        i++;
                    }
                }
            }
        }

        int i = 0;
        while (i < possible.size()) {
            if (possible.get(i).havePlayed(this)) {
                possible.remove(i);
                i--;
            }
            i++;
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
     * @return
     */
    @Override
    public String getDecoratedName() {
        return getName();
    }

    /**
     *
     * @param c
     * @param r
     */
    @Override
    public void addMatchRoundRobin(Competitor c, Round r,
            boolean complete
    ) {
        if (!complete) {
            addMatch(c, r);
        } else {
            Tournament tour = Tournament.getTournament();
            Parameters params = tour.getParams();
            TeamMatch tm = new TeamMatch(r);
            tm.setCompetitor1(this);
            tm.setCompetitor2(c);
            for (int n = 0; n < params.getTeamMatesNumber(); n++) {

                final ArrayList<Coach> t1players = this.getActivePlayers();
                if (c instanceof Team) {
                    final ArrayList<Coach> t2players = ((Team) c).getActivePlayers();

                    // Arrange player list using ribbon method
                    for (int p = 0; p < n; p++) {
                        final Coach c_tmp = t2players.get(0);
                        t2players.remove(0);
                        t2players.add(c_tmp);
                    }

                    for (int l = 0; l < t1players.size(); l++) {

                        final Coach c1 = t1players.get(l);
                        final Coach c2 = t2players.get(l);

                        CoachMatch m = new CoachMatch(r);
                        m.setCompetitor1(c1);
                        m.setCompetitor2(c2);

                        tm.addMatch(m);
                    }

                }
            }
            r.addMatch(tm);
            this.mMatchs.add(tm);
            c.mMatchs.add(tm);
        }
    }

    /**
     *
     * @param opponent
     * @param r
     * @return
     */
    public boolean canPlay(Team opponent, Round r) {
        boolean have_played = this.havePlayed(opponent);
        Parameters params = Tournament.getTournament().getParams();

        boolean same_clan = false;
        if (params.isEnableClans()) {
            if ((getClan() != null) && (opponent.getClan() != null)) {
                if ((params.isAvoidClansFirstMatch() && (Tournament.getTournament().getRoundsCount() == 0))
                        || (params.isAvoidClansMatch())) {
                    if (getClan().equals(opponent.getClan())) {
                        same_clan = true;
                    }
                }
            }
        }
        return !have_played && !same_clan;
    }

    /**
     *
     * @param round
     */
    @Override
    public void roundCheck(Round round) {

        Tournament tour = Tournament.getTournament();

        for (int i = round.getMatchsCount() - 1; i > 0; i--) {
            TeamMatch team_match = (TeamMatch) round.getMatch(i);
            if (team_match.getMatchCount() == tour.getParams().getTeamMatesNumber()) {
                final Team t1 = (Team) team_match.getCompetitor1();
                final Team t2 = (Team) team_match.getCompetitor2();
                boolean have_played = !t1.canPlay(t2, round);

                if (have_played) {
                    for (int k = i - 1; k >= 0; k--) {

                        Team t1_tmp = (Team) round.getMatch(k).getCompetitor1();
                        Team t2_tmp = (Team) round.getMatch(k).getCompetitor2();

                        have_played = !t1.canPlay(t2_tmp, round);

                        boolean canMatch = !have_played;

                        if (canMatch) {

                            // Switch coachs into matchs 
                            TeamMatch tm1 = ((TeamMatch) round.getMatch(i));
                            TeamMatch tm2 = ((TeamMatch) round.getMatch(k));

                            // If team is splited
                            //Switch Team
                            tm1.setCompetitor2(t2_tmp);
                            tm2.setCompetitor2(t2);

                            // Reallocate matches
                            t2.removeMatch(tm1);
                            t2.addMatch(tm2);

                            t2_tmp.removeMatch(tm2);
                            t2_tmp.addMatch(tm1);

                            for (int j = 0; (j < tm1.getMatchCount()) && (j < tm2.getMatchCount()); j++) {
                                Match m1 = tm1.getMatch(j);
                                Match m2 = tm2.getMatch(j);
                                Coach c2_tmp = (Coach) m2.getCompetitor2();
                                Coach c2 = (Coach) m1.getCompetitor2();

                                m1.setCompetitor2(c2_tmp);
                                m2.setCompetitor2(c2);

                                c2_tmp.removeMatch(m2);
                                c2_tmp.addMatch(m1);

                                c2.removeMatch(m1);
                                c2.addMatch(m2);
                            }
                            break;

                        } else {
                            have_played = !t1.canPlay(t1_tmp, round);

                            canMatch = !have_played;
                            if (canMatch) {
                                //Switch Team
                                round.getMatch(i).setCompetitor2(t1_tmp);
                                round.getMatch(k).setCompetitor1(t2);

                                // Switch coachs into matchs 
                                TeamMatch tm1 = ((TeamMatch) round.getMatch(i));
                                TeamMatch tm2 = ((TeamMatch) round.getMatch(k));

                                t2.removeMatch(round.getMatch(i));
                                t2.addMatch(round.getMatch(k));

                                t1_tmp.removeMatch(round.getMatch(k));
                                t1_tmp.addMatch(round.getMatch(i));

                                for (int j = 0; (j < tm1.getMatchCount()) && (j < tm2.getMatchCount()); j++) {
                                    Match m = tm1.getMatch(j);
                                    Match m_tmp = tm2.getMatch(j);
                                    Coach c1_tmp = (Coach) m_tmp.getCompetitor1();
                                    Coach c2 = (Coach) m.getCompetitor2();
                                    m.setCompetitor2(c1_tmp);
                                    m_tmp.setCompetitor1(c2);

                                    c1_tmp.removeMatch(m_tmp);
                                    c1_tmp.addMatch(m);

                                    c2.removeMatch(m);
                                    c2.addMatch(m_tmp);
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param teams
     * @param current
     * @return
     */
    @Override
    public HashMap<Team, Integer> getTeamOppositionCount(ArrayList<Team> teams, Round current
    ) {

        HashMap<Team, Integer> map = new HashMap<>();

        // Build opponents map
        for (Team t : teams) {
            if (!t.getName().equals(this.getName())) {
                map.put(t, 0);
            }
        }

        // compute the number of matches between the coach team and the other teams
        // Compute the number of match per opponent
        for (Coach coach : mCoachs) {
            for (int j = 0; j < coach.getMatchCount(); j++) {
                CoachMatch m = (CoachMatch) coach.getMatch(j);
                Coach opp;
                if (coach.getName().equals(m.getCompetitor1().getName())) {
                    opp = (Coach) m.getCompetitor2();
                } else {
                    opp = (Coach) m.getCompetitor1();
                }

                try {
                    int nb = map.get(opp.getTeamMates());
                    nb += 1;
                    map.put(opp.getTeamMates(), nb);
                } catch (NullPointerException npe) {
                    LOG.log(Level.FINE, "Impossible to manage {0} vs {1}", new Object[]{getName(), opp.getTeamMates().getName()});
                }
            }
        }

        /// Add current Round
        if (current != null) {
            for (Coach coach : mCoachs) {
                ArrayList<CoachMatch> matchs = current.getCoachMatchs();
                for (CoachMatch m : matchs) {
                    Coach opp = null;
                    if (coach.getName().equals(m.getCompetitor1().getName())) {
                        opp = (Coach) m.getCompetitor2();
                    }
                    if (coach.getName().equals(m.getCompetitor2().getName())) {
                        opp = (Coach) m.getCompetitor1();
                    }

                    if (opp != null) {
                        try {
                            int nb = map.get(opp.getTeamMates());
                            nb += 1;
                            map.put(opp.getTeamMates(), nb);
                        } catch (NullPointerException npe) {
                            LOG.log(Level.FINE, "Impossible to manage {0} vs {1}", new Object[]{getName(), opp.getTeamMates().getName()});
                        }
                    }
                }
            }
        }

        return map;
    }

    /**
     * @return the mCoachs
     */
    @Override
    public Coach getCoach(int i
    ) {
        return mCoachs.get(i);
    }

    /**
     * @return the mCoachs
     */
    @Override
    public int getCoachsCount() {
        return mCoachs.size();
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean containsCoach(Coach c
    ) {
        return mCoachs.contains(c);
    }

    /**
     *
     * @param c
     */
    @Override
    public void addCoach(Coach c
    ) {
        mCoachs.add(c);
        updated = true;
    }

    /**
     *
     */
    @Override
    public void removeCoach(int i
    ) {
        mCoachs.remove(i);
        updated = true;
    }

    /**
     */
    @Override
    public void clearCoachs() {
        this.mCoachs.clear();
        updated = true;
    }

    public boolean isBalanced(Team opp, Round round) {

        boolean balanced = true;
        ArrayList<Team> teams = new ArrayList<>();

        if (this == opp) {
            return false;
        }

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team team = Tournament.getTournament().getTeam(i);
            if (team != this) {
                teams.add(team);
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

        it2 = hash.keySet().iterator();
        while (it2.hasNext()) {
            Competitor en2 = it2.next();
            if (en2 instanceof Team) {
                Team t2 = (Team) en2;
                int nb2 = hash.get(t2);
            }
        }

        if ((maximum == nb) && (maximum - minimum > 1)) {
            balanced = false;
        }
        return balanced;
    }

}
