/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import org.jdom.Element;
import tourma.tableModel.MjtRanking;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class TeamMatch extends Match implements Serializable {

    protected static AtomicInteger sGenUID = new AtomicInteger(0);
    protected int UID = sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    private static final Logger LOG = Logger.getLogger(TeamMatch.class.getName());

    /**
     *
     */
    private final ArrayList<CoachMatch> mMatchs;

    public void setUpdated(boolean updated) {
        super.setUpdated(updated);
        for (CoachMatch cm : mMatchs) {
            cm.setUpdated(updated);
        }
    }

    @Override
    public boolean isUpdated() {
        for (CoachMatch m : mMatchs) {
            if (m.isUpdated()) {
                updated = true;
                break;
            }
        }
        return updated;
    }

    /**
     *
     * @param round
     */
    public TeamMatch(Round round) {
        super(round);
        mMatchs = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    @Override
    public Competitor getWinner() {

        if (super.getWinner() != null) {
            return super.getWinner();
        } else {
            Tournament tour = Tournament.getTournament();
            final Team team1 = (Team) getCompetitor1();
            final Team team2 = (Team) getCompetitor2();

            Team winner;
            Team looser;

            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().getCriteria(0);
            for (CoachMatch m : mMatchs) {
                if (m.getValue(td).getValue1() > m.getValue(td).getValue2()) {
                    nbVictory++;
                } else if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                    nbLost++;
                }
            }

            if (team1 == Team.getNullTeam()) {
                winner = team2;
                looser = team1;
            } else if (team2 == Team.getNullTeam()) {
                winner = team1;
                looser = team2;
            } else if (nbVictory > nbLost) {
                winner = team1;
                looser = team2;
            } else if (nbVictory < nbLost) {
                winner = team2;
                looser = team1;
            } else {
                Random ran = new Random();
                final int r = ran.nextInt() % 2;
                if (r == 0) {
                    winner = team1;
                    looser = team2;
                } else {
                    winner = team2;
                    looser = team1;
                }
            }
            super.setWinner(winner);
            super.setLooser(looser);
            return winner;
        }
    }

    public void pull(Match match) {
        if (match instanceof TeamMatch) {
            TeamMatch teammatch = (TeamMatch) match;
            this.UID = teammatch.UID;
            this.c1value1 = teammatch.c1value1;
            this.c1value2 = teammatch.c1value2;
            this.c1value3 = teammatch.c1value3;
            this.c1value4 = teammatch.c1value4;
            this.c1value5 = teammatch.c1value5;
            this.c1value1 = teammatch.c1value1;
            this.c2value2 = teammatch.c2value2;
            this.c2value3 = teammatch.c2value3;
            this.c2value4 = teammatch.c2value4;
            this.c2value5 = teammatch.c2value5;

            this.mCompetitor1 = Tournament.getTournament().getTeam(teammatch.getCompetitor1().getName());
            this.mCompetitor2 = Tournament.getTournament().getTeam(teammatch.getCompetitor2().getName());

            // Manage CoachMatches
            for (CoachMatch cm : teammatch.mMatchs) {
                boolean bFound = false;
                for (CoachMatch coachmatch : mMatchs) {
                    if (cm.getUID() == coachmatch.getUID()) {
                        bFound = true;
                        coachmatch.pull(cm);
                        break;
                    }
                }

                if (!bFound) {
                    CoachMatch coachmatch = new CoachMatch(this.getRound());
                    coachmatch.pull(cm);
                    mMatchs.add(coachmatch);
                }
            }

            // add Match to competitor
            boolean bFound = false;
            for (int i = 0; i < mCompetitor1.getMatchCount(); i++) {
                Match m = mCompetitor1.getMatch(i);
                if (m.getUID() == UID) {
                    bFound = true;
                    break;
                }
            }
            if (!bFound) {
                mCompetitor1.addMatch(this);
            }

            bFound = false;
            for (int i = 0; i < mCompetitor2.getMatchCount(); i++) {
                Match m = mCompetitor2.getMatch(i);
                if (m.getUID() == UID) {
                    bFound = true;
                    break;
                }
            }
            if (!bFound) {
                mCompetitor2.addMatch(this);
            }
        }
    }

    public void push(Match match) {
        if (match.isUpdated()) {
            if (match instanceof TeamMatch) {
                TeamMatch teammatch = (TeamMatch) match;
                this.UID = teammatch.UID;
                this.c1value1 = teammatch.c1value1;
                this.c1value2 = teammatch.c1value2;
                this.c1value3 = teammatch.c1value3;
                this.c1value4 = teammatch.c1value4;
                this.c1value5 = teammatch.c1value5;
                this.c1value1 = teammatch.c1value1;
                this.c2value2 = teammatch.c2value2;
                this.c2value3 = teammatch.c2value3;
                this.c2value4 = teammatch.c2value4;
                this.c2value5 = teammatch.c2value5;

                this.mCompetitor1 = Tournament.getTournament().getTeam(teammatch.getCompetitor1().getName());
                this.mCompetitor2 = Tournament.getTournament().getTeam(teammatch.getCompetitor2().getName());

                // Manage CoachMatches
                for (CoachMatch cm : teammatch.mMatchs) {
                    boolean bFound = false;
                    for (CoachMatch coachmatch : mMatchs) {
                        if (cm.getUID() == coachmatch.getUID()) {
                            bFound = true;
                            coachmatch.push(cm);
                            break;
                        }
                    }

                    /*if (!bFound)
                {
                    CoachMatch coachmatch=new CoachMatch(this.getRound());
                    coachmatch.pull(cm);
                    mMatchs.add(coachmatch);
                }*/
                }

                // add Match to competitor
                boolean bFound = false;
                for (int i = 0; i < mCompetitor1.getMatchCount(); i++) {
                    Match m = mCompetitor1.getMatch(i);
                    if (m.getUID() == UID) {
                        bFound = true;
                        break;
                    }
                }
                /*if (!bFound) {
                mCompetitor1.addMatch(this);
            }*/

                bFound = false;
                for (int i = 0; i < mCompetitor2.getMatchCount(); i++) {
                    Match m = mCompetitor2.getMatch(i);
                    if (m.getUID() == UID) {
                        bFound = true;
                        break;
                    }
                }
                /*if (!bFound) {
                mCompetitor2.addMatch(this);
            }*/
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Competitor getLooser() {
        if (super.getLooser() != null) {
            return super.getLooser();
        } else {
            Tournament tour = Tournament.getTournament();
            final Team team1 = (Team) getCompetitor1();
            final Team team2 = (Team) getCompetitor2();

            Team looser;
            Team winner;

            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().getCriteria(0);
            for (CoachMatch m : mMatchs) {
                if (m.getValue(td).getValue1() > m.getValue(td).getValue2()) {
                    nbVictory++;
                } else if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                    nbLost++;
                }
            }

            if (team1 == Team.getNullTeam()) {
                looser = team1;
                winner = team2;
            } else if (team2 == Team.getNullTeam()) {
                looser = team2;
                winner = team1;
            } else if (nbVictory > nbLost) {
                looser = team2;
                winner = team1;
            } else if (nbVictory < nbLost) {
                looser = team1;
                winner = team2;
            } else {
                Random ran = new Random();
                final int r = ran.nextInt() % 2;
                if (r == 0) {
                    looser = team2;
                    winner = team1;
                } else {
                    looser = team1;
                    winner = team2;
                }
            }
            super.setWinner(winner);
            super.setLooser(looser);
            return looser;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element match = new Element(StringConstants.CS_MATCH);
        Competitor cmp1 = this.getCompetitor1();
        Competitor cmp2 = this.getCompetitor2();

        match.setAttribute(StringConstants.CS_TEAM + 1, cmp1.getRawName());
        match.setAttribute(StringConstants.CS_TEAM + 2, cmp2.getRawName());

        for (CoachMatch mMatch : mMatchs) {
            Element subMatch = mMatch.getXMLElement();
            match.addContent(subMatch);
        }
        return match;
    }

    @Override
    public Element getXMLElementForDisplay() {
        Element match = getXMLElement();

        Element t1 = ((Team) this.getCompetitor1()).getXMLElementForDisplay();
        Element t2 = ((Team) this.getCompetitor2()).getXMLElementForDisplay();
        match.addContent(t1);
        match.addContent(t2);

        return match;
    }

    /**
     *
     * @param match
     */
    @Override
    public void setXMLElement(final Element match) throws NullPointerException {

        String c1 = "";
        String c2 = "";
        if (match.getAttribute(StringConstants.CS_TEAM + 1) != null) {
            c1 = match.getAttribute(StringConstants.CS_TEAM + 1).getValue();
        } else {
            c1 = match.getAttribute("team" + 1).getValue();
        }
        if (match.getAttribute(StringConstants.CS_TEAM + 1) != null) {
            c2 = match.getAttribute(StringConstants.CS_TEAM + 2).getValue();
        } else {
            c2 = match.getAttribute("team" + 2).getValue();
        }
        this.setCompetitor1(Team.getTeam(c1));
        this.setCompetitor2(Team.getTeam(c2));

        if (((Team) getCompetitor1()) != null) {
            if (getCompetitor1().isMatchsNotNull()) {
                getCompetitor1().addMatch(this);
            }
        } else {
            setCompetitor1(Team.getNullTeam());
        }

        if (((Team) getCompetitor2()) != null) {
            if (getCompetitor2().isMatchsNotNull()) {
                getCompetitor2().addMatch(this);
            }
        } else {
            setCompetitor2(Team.getNullTeam());
        }

        final List<Element> values = match.getChildren(StringConstants.CS_MATCH);
        final Iterator<Element> v = values.iterator();

        while (v.hasNext()) {
            CoachMatch m = new CoachMatch(getRound());
            final Element val = v.next();
            m.setXMLElement(val);

            mMatchs.add(m);
        }

    }

    /**
     *
     * @param t1
     * @return
     */
    public int getVictories(Team t1) {
        Tournament tour = Tournament.getTournament();
        final Team team1 = t1;
        Team team2;

        int nbVictories = 0;

        if (t1 == getCompetitor1()) {
            team2 = (Team) getCompetitor2();
        } else if (t1 == getCompetitor2()) {
            team2 = (Team) getCompetitor1();
        } else {
            team2 = null;
        }

        if (team2 == Team.getNullTeam()) {
            return 8;
        }
        if (team1 == Team.getNullTeam()) {
            return 0;
        }

        if (team2 != null) {
            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().getCriteria(0);
            for (CoachMatch m : mMatchs) {
                if (m.getValue(td).getValue1() > m.getValue(td).getValue2()) {
                    nbVictory++;
                } else if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                    nbLost++;
                }
            }

            if (this.getCompetitor2() == t1) {
                nbVictories = nbLost;
            } else {
                nbVictories = nbVictory;
            }
        }
        return nbVictories;
    }

    /**
     *
     * @param t1
     * @return
     */
    public int getLoss(Team t1) {
        Tournament tour = Tournament.getTournament();
        final Team team1 = t1;
        Team team2;

        int nbLoose = 0;

        if (t1 == getCompetitor1()) {
            team2 = (Team) getCompetitor2();
        } else if (t1 == getCompetitor2()) {
            team2 = (Team) getCompetitor1();
        } else {
            team2 = null;
        }

        if (team2 == Team.getNullTeam()) {
            return 0;
        }
        if (team1 == Team.getNullTeam()) {
            return 8;
        }

        if (t1 == getCompetitor1()) {
            team2 = (Team) getCompetitor2();
        } else if (t1 == getCompetitor2()) {
            team2 = (Team) getCompetitor1();
        } else {
            team2 = null;
        }

        if (team2 != null) {
            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().getCriteria(0);
            for (CoachMatch m : mMatchs) {
                if (m.getValue(td).getValue1() > m.getValue(td).getValue2()) {
                    nbVictory++;
                } else if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                    nbLost++;
                }
            }

            if (team2 == t1) {
                nbLoose = nbVictory;
            } else {
                nbLoose = nbLost;
            }
        }
        return nbLoose;
    }

    /**
     *
     * @param t1
     * @return
     */
    public int getDraw(Team t1) {
        Tournament tour = Tournament.getTournament();
        final Team team1 = t1;
        Team team2;

        int nbDraw = 0;

        if (t1 == getCompetitor1()) {
            team2 = (Team) getCompetitor2();
        } else if (t1 == getCompetitor2()) {
            team2 = (Team) getCompetitor1();
        } else {
            team2 = null;
        }

        if (team2 == Team.getNullTeam()) {
            return 0;
        }
        if (team1 == Team.getNullTeam()) {
            return 0;
        }

        if (team2 != null) {
            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().getCriteria(0);
            for (CoachMatch m : mMatchs) {
                if ((m.getValue(td).getValue1() == m.getValue(td).getValue2()) && (m.getValue(td).getValue2() != -1)) {
                    nbDraw++;
                }
            }
        }
        return nbDraw;
    }

    /**
     * @return the mMatchs count
     */
    public int getMatchCount() {
        return mMatchs.size();
    }

    /**
     * @param i
     * @return the mMatchs
     */
    public CoachMatch getMatch(int i) {
        return mMatchs.get(i);
    }

    /**
     * Clear the match list
     */
    public void clearMatchs() {
        mMatchs.clear();
    }

    /**
     *
     * @param c
     * @return
     */
    public boolean containsMatch(CoachMatch c) {
        return mMatchs.contains(c);
    }

    /**
     *
     * @param c
     */
    public void addMatch(CoachMatch c) {
        mMatchs.add(c);
    }

    @Override
    public void setXMLElementForDisplay(Element match) {

        List<Element> elts = match.getChildren(StringConstants.CS_TEAM);
        if (elts.size() == 2) {
            Element t1 = elts.get(0);
            Team team1 = new Team();
            team1.setXMLElementForDisplay(t1);
            if (Team.getTeam(team1.getName()) == null) {
                Tournament.getTournament().addTeam(team1);
            }

            Element t2 = elts.get(1);
            Team team2 = new Team();
            team2.setXMLElementForDisplay(t2);
            if (Team.getTeam(team2.getName()) == null) {
                Tournament.getTournament().addTeam(team2);
            }

        }

        setXMLElement(match);
    }

    @Override
    public boolean equals(Object c) {
        if (c instanceof TeamMatch) {
            TeamMatch tm = (TeamMatch) c;
            boolean equality = true;
            if ((getCompetitor1() != null) && (getCompetitor2() != null)) {
                equality &= (this.getCompetitor1().equals(tm.getCompetitor1()));
                equality &= (this.getCompetitor2().equals(tm.getCompetitor2()));
            }

            for (int i = 0; i < mMatchs.size(); i++) {
                if (i < tm.getMatchCount()) {
                    equality &= mMatchs.get(i).equals(tm.getMatch(i));
                } else {
                    equality = false;
                    break;
                }
            }
            return equality;
        }

        return false;

    }

    protected static int getCriteriaBonusPoints(Coach c, CoachMatch m, Criteria crit) {
        int value = 0;
        Value v = m.getValue(crit);
        if (m.getCompetitor1() == c) {
            if (v.getValue1() >= crit.getOffensiveThreshold()) {
                value += crit.getOffensiveBonusesForTeam();
            }

            if (v.getValue1() >= v.getValue2() + crit.getOffensiveDiffThreshold()) {
                value += crit.getOffensiveDiffBonusesForTeam();
            }

            if ((v.getValue1() < v.getValue2()) && (v.getValue1() + crit.getDefensiveDiffThreshold() >= v.getValue2())) {
                value += crit.getDefensiveDiffBonusesForTeam();
            }
        }
        if (m.getCompetitor2() == c) {
            if (v.getValue2() >= crit.getOffensiveThreshold()) {
                value += crit.getOffensiveBonusesForTeam();
            }

            if (v.getValue2() >= v.getValue1() + crit.getOffensiveDiffThreshold()) {
                value += crit.getOffensiveDiffBonusesForTeam();
            }

            if ((v.getValue2() < v.getValue1()) && (v.getValue2() + crit.getDefensiveDiffThreshold() >= v.getValue1())) {
                value += crit.getDefensiveDiffBonusesForTeam();
            }
        }
        return value;
    }

    protected static int getCriteriasBonusPoints(Team t, TeamMatch tm) {
        int value = 0;
        // loop on Criterias for individual addition
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            for (int j = 0; j < tm.getMatchCount(); j++) {
                CoachMatch m = tm.getMatch(j);
                if (((Coach) m.getCompetitor1()).getTeamMates() == t) {
                    value += getCriteriaBonusPoints((Coach) m.getCompetitor1(), m, crit);
                }
                if (((Coach) m.getCompetitor2()).getTeamMates() == t) {
                    value += getCriteriaBonusPoints((Coach) m.getCompetitor2(), m, crit);
                }
            }
        }

        // loop on Criterias for building Sum
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            int value1 = 0;
            int value2 = 0;
            for (int j = 0; j < tm.getMatchCount(); j++) {
                CoachMatch m = tm.getMatch(j);
                value1 += m.getValue(crit).getValue1();
                value2 += m.getValue(crit).getValue2();
            }

            if (tm.getCompetitor1() == t) {
                if (value1 >= crit.getOffensiveThresholdByTeam()) {
                    value += crit.getOffensiveBonusesByTeam();
                }

                if (value1 >= value2 + crit.getOffensiveDiffThresholdByTeam()) {
                    value += crit.getOffensiveDiffBonusesByTeam();
                }

                if ((value1 < value2) && (value1 + crit.getDefensiveDiffThresholdByTeam() >= value2)) {
                    value += crit.getDefensiveDiffBonusesByTeam();
                }
            }
            if (tm.getCompetitor2() == t) {
                if (value2 >= crit.getOffensiveThresholdByTeam()) {
                    value += crit.getOffensiveBonusesByTeam();
                }

                if (value2 >= value1 + crit.getOffensiveDiffThresholdByTeam()) {
                    value += crit.getOffensiveDiffBonusesByTeam();
                }

                if ((value2 < value1) && (value2 + crit.getDefensiveDiffThresholdByTeam() >= value1)) {
                    value += crit.getDefensiveDiffBonusesByTeam();
                }
            }
        }
        return value;
    }

    public int getPointsByTeam(final Team t, TeamMatch tm, boolean withMainPoints, boolean withBonus) {

        int value = 0;
        int countTeamVictories = 0;
        int countTeamLargeVictories = 0;
        int countTeamHugeVictories = 0;
        int countTeamLoss = 0;
        int countTeamLittleLoss = 0;
        int countTeamHugeLoss = 0;
        int countTeamDraw = 0;

        int i = 0;
        int victories = 0;
        int loss = 0;
        int draw = 0;

        for (int j = 0; j < t.getCoachsCount(); j++) {
            final Coach c = t.getCoach(j);
            boolean matchFound = false;
            for (i = 0; (i < c.getMatchCount()) && (!matchFound); i++) {
                final CoachMatch m = (CoachMatch) c.getMatch(i);
                if (tm != null) {
                    if (tm.containsMatch(m)) {
                        matchFound = true;
                        final Criteria crit = Tournament.getTournament().getParams().getCriteria(0);
                        final Value val = m.getValue(crit);
                        if (m.getCompetitor1() == c) {
                            if (val.getValue1() > val.getValue2()) {
                                victories++;
                            } else {
                                if (val.getValue1() < val.getValue2()) {
                                    loss++;
                                } else {
                                    draw++;
                                }
                            }
                        } else {
                            if (val.getValue1() < val.getValue2()) {
                                victories++;
                            } else {
                                if (val.getValue1() > val.getValue2()) {
                                    loss++;
                                } else {
                                    draw++;
                                }
                            }
                        }
                        if (withBonus) {
                            int bonus = 0;
                            for (int k = 0; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
                                final Criteria criteria = Tournament.getTournament().getParams().getCriteria(k);
                                if (m.getCompetitor1() == c) {
                                    bonus += Math.max(m.getValue(criteria).getValue1(), 0) * criteria.getPointsTeamFor();
                                    bonus += Math.max(m.getValue(criteria).getValue2(), 0) * criteria.getPointsTeamAgainst();
                                }
                                if (m.getCompetitor2() == c) {
                                    bonus += Math.max(m.getValue(criteria).getValue2(), 0) * criteria.getPointsTeamFor();
                                    bonus += Math.max(m.getValue(criteria).getValue1(), 0) * criteria.getPointsTeamAgainst();
                                }
                            }
                            if (Tournament.getTournament().getParams().isTableBonus()) {
                                double coef = Tournament.getTournament().getParams().getTableBonusCoef();
                                bonus += Math.round(CoachMatch.getCoachTablePoints(c, m) * coef);
                            }

                            if (Tournament.getTournament().getParams().isTableBonusPerRound()) {
                                // Find Round
                                Round round = Tournament.getTournament().getRound(i);

                                if (round != null) {
                                    if (round.getMatchsCount() > 0) {
                                        double fBonus = bonus * round.getCoef(m);
                                        bonus = (int) Math.round(fBonus);
                                    }

                                }
                            }
                            value += bonus;

                            value += getCriteriasBonusPoints(t, tm);

                        }

                    }
                } else {
                    System.err.println("Error detected, Team is null");
                }
            }
        }

        if (victories > loss) {
            if (Tournament.getTournament().getParams().isUseTeamHugeVictory()) {

                if (victories - loss - Tournament.getTournament().getParams().getGapTeamHugeVictory() >= -0.1) {
                    countTeamHugeVictories++;
                } else {
                    if (Tournament.getTournament().getParams().isUseTeamLargeVictory()) {
                        if (victories - loss - Tournament.getTournament().getParams().getGapTeamLargeVictory() >= -0.1) {
                            countTeamLargeVictories++;
                        } else {
                            countTeamVictories++;
                        }
                    } else {
                        countTeamVictories++;
                    }
                }
            } else {
                if (Tournament.getTournament().getParams().isUseTeamLargeVictory()) {
                    if (victories - loss - Tournament.getTournament().getParams().getGapTeamLargeVictory() >= -0.1) {
                        countTeamLargeVictories++;
                    } else {
                        countTeamVictories++;
                    }
                } else {
                    countTeamVictories++;
                }
            }
        } else if (victories < loss) {

            if (Tournament.getTournament().getParams().isUseTeamLittleLoss()) {
                if (loss - victories - Tournament.getTournament().getParams().getGapTeamLittleLost() <= 0.1) {
                    countTeamLittleLoss++;
                } else {
                    if (Tournament.getTournament().getParams().isUseTeamHugeLoss()) {
                        if (loss - victories - Tournament.getTournament().getParams().getGapTeamHugeLost() < 0.1) {
                            countTeamLoss++;
                        } else {
                            countTeamHugeLoss++;
                        }
                    } else {
                        countTeamLoss++;
                    }
                }
            } else {
                if (Tournament.getTournament().getParams().isUseTeamHugeLoss()) {
                    if (loss - victories - Tournament.getTournament().getParams().getGapTeamHugeLost() < 0.1) {
                        countTeamLoss++;
                    } else {
                        countTeamHugeLoss++;
                    }
                } else {
                    countTeamLoss++;
                }
            }


            /*if (Tournament.getTournament().getParams().isUseTeamLittleLoss()) {
                if (loss - victories <= Tournament.getTournament().getParams().getGapTeamLittleLost()) {
                    countTeamLittleLoss++;
                } else {
                    countTeamLoss++;
                }
            } else {
                countTeamLoss++;
            }*/
        } else {
            countTeamDraw++;
        }

        //i++;
        if (withMainPoints) {
            value += countTeamVictories * Tournament.getTournament().getParams().getPointsTeamVictory();
            value += countTeamLargeVictories * Tournament.getTournament().getParams().getPointsTeamLargeVictory();
            value += countTeamHugeVictories * Tournament.getTournament().getParams().getPointsTeamHugeVictory();
            value += countTeamLoss * Tournament.getTournament().getParams().getPointsTeamLost();
            value += countTeamLittleLoss * Tournament.getTournament().getParams().getPointsTeamLittleLost();
            value += countTeamHugeLoss * Tournament.getTournament().getParams().getPointsTeamHugeLost();
            value += countTeamDraw * Tournament.getTournament().getParams().getPointsTeamDraw();
        }

        return value;
    }

    public static final int C_STARTING_RANK = 1000;
    /**
     *
     */
    public static final int C_ELO_K = 256;

    int getELOByTeam(final Team t, TeamMatch tm, int roundIndex) {
        int value;

        int nbVic = tm.getVictories(t);
        int nbDraw = tm.getVictories(t);

        int lastTeamRank = C_STARTING_RANK;
        int lastOppRank = C_STARTING_RANK;

        Team opp = null;
        if (tm.getCompetitor1() == t) {
            opp = (Team) tm.getCompetitor2();

        }
        if (tm.getCompetitor2() == t) {
            opp = (Team) tm.getCompetitor1();
        }
        if (roundIndex >= 0) {
            // Find Previous Match for current player

            if (roundIndex > 0) {
                lastTeamRank = getELOByTeam(t, tm, roundIndex - 1);
            }

            // Find Previous Match for oponnent player
            if (roundIndex > 0) {
                lastOppRank = getELOByTeam(opp, tm, roundIndex - 1);
            }
        }

        double tmp = ((lastOppRank - lastTeamRank) / (double) 400);
        double EA = 1 / (1 + Math.pow(10.0, tmp));

        // Compute real score
        // Victory is 1000
        // All bonuses to 1
        double SA = 0;
        if (nbVic > nbDraw) {
            SA = 1000;
        }
        if (nbVic < nbDraw) {
            SA = 0;
        }
        if (nbVic == nbDraw) {
            SA = 500;
        }

        // Add/Remove Bonuses
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            for (int j = 0; j < tm.getMatchCount(); j++) {
                CoachMatch m = tm.getMatch(j);
                Value val = m.getValue(crit);
                if (tm.getCompetitor1() == t) {
                    SA += val.getValue1();
                    SA -= val.getValue2();
                }
                if (tm.getCompetitor2() == t) {
                    SA -= val.getValue1();
                    SA += val.getValue2();
                }
            }
        }
        value = Math.round((float) (lastTeamRank + C_ELO_K * (SA - EA)));

        return value;
    }

    public int getVNDByTeam(final Team t, TeamMatch tm, boolean includeCurrent) {

        int value = 0;
        int countTeamVictories = 0;
        int countTeamLoss = 0;
        int countTeamDraw = 0;

        int i = 0;
        /*if (mRoundOnly) {
            i = mRound;
        }*/

        int matchFound = 0;
        //while (i <= mRound) {
        //for (int i = 0; i <= mRound; i++) {
        int victories = 0;
        int loss = 0;

        for (int j = 0; j < t.getCoachsCount(); j++) {
            //matchFound = false;
            i = 0;
            final Coach c = t.getCoach(j);
            while (i < c.getMatchCount()) {
                if (c.getMatchCount() > i) {
                    final CoachMatch m = (CoachMatch) c.getMatch(i);
                    if (includeCurrent && tm.containsMatch(m)) {
                        matchFound++;
                        final Criteria crit = Tournament.getTournament().getParams().getCriteria(0);
                        final Value val = m.getValue(crit);
                        if (m.getCompetitor1() == c) {
                            if (val.getValue1() > val.getValue2()) {
                                victories++;
                            } else if (val.getValue1() < val.getValue2()) {
                                loss++;
                            }
                        } else {
                            if (val.getValue1() < val.getValue2()) {
                                victories++;
                            } else if (val.getValue1() > val.getValue2()) {
                                loss++;
                            }
                        }
                    }
                    /*else {
                        System.out.println("Match not found !! " + tm.getCompetitor1().getName() + "vs  " + tm.getCompetitor2().getName() + " for " + c.getName());
                    }*/
                }
                i++;
            }
        }
        if (matchFound > 0) {
            if (victories > loss) {
                countTeamVictories++;
            } else if (victories < loss) {
                countTeamLoss++;
            } else {
                countTeamDraw++;
            }
        }
        /*else {
            System.out.println("Match not found ????");
        }*/
 /*  i++;
        }*/

        value += countTeamVictories * 1000000;
        value += countTeamLoss * 1;
        value += countTeamDraw * 1000;
        return value;
    }

    int getOppPointsByTeam(final Team t, TeamMatch tm, boolean includeCurrent) {

        int index = 0;
        TeamMatch tmp_m = (TeamMatch) t.getMatch(index);
        while (tmp_m != tm) {
            index++;
            tmp_m = (TeamMatch) t.getMatch(index);
        }

        int value = 0;
        Competitor opponent;
        if (tm.getCompetitor1() == t) {
            opponent = tm.getCompetitor2();
        } else {
            opponent = tm.getCompetitor1();
        }

        if (((Team) opponent) != null) {
            if (opponent.isMatchsNotNull()) {
                for (int i = 0; i < opponent.getMatchCount(); i++) {
                    TeamMatch om = (TeamMatch) opponent.getMatch(i);
                    if ((includeCurrent) || ((!includeCurrent) && (om != tm))) {
                        value += getPointsByTeam((Team) opponent, om, true, true);
                    }
                    /*if (om == tm) {
                        break;
                    }*/
                }
            }
        }

        return value;
    }

    int getOppELOByTeam(final Team t, TeamMatch tm, int roundIndex) {
        int value;
        Competitor opponent;
        if (tm.getCompetitor1() == t) {
            opponent = tm.getCompetitor2();
        } else {
            opponent = tm.getCompetitor1();
        }
        value = getELOByTeam((Team) opponent, tm, roundIndex);

        return value;
    }

    public int getTeamTable(final Team t, TeamMatch tm) {

        for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
            Round r = Tournament.getTournament().getRound(i);
            if (r.containsMatch(tm)) {
                // No point for first round
                if (i == 0) {
                    return 0;
                } else {
                    int maxvalue = r.getMatchsCount();
                    return maxvalue - r.indexOf(tm);
                }
            }
        }
        return 0;

    }

    int getTeamNbMatch(final Team t, TeamMatch tm) {
        int index = t.matchIndex(tm);
        return index + 1;
    }

    int getTeammatesPoints(Team t) {
        int value = 0;
        for (int i = 0; i < getMatchCount(); i++) {
            CoachMatch cm = getMatch(i);
            Coach c = null;
            Coach c1 = (Coach) cm.getCompetitor1();
            Coach c2 = (Coach) cm.getCompetitor2();
            if (c1.getTeamMates() == t) {
                c = c1;
            }
            if (c2.getTeamMates() == t) {
                c = c2;
            }
            if (c != null) {
                value += CoachMatch.getPointsByCoach(c, cm, true, true);

            }
        }
        return value;
    }

    int getTeammatesVND(Team t) {
        int value = 0;
        for (int i = 0; i < getMatchCount(); i++) {
            CoachMatch cm = getMatch(i);
            Coach c = null;
            Coach c1 = (Coach) cm.getCompetitor1();
            Coach c2 = (Coach) cm.getCompetitor2();
            if (c1.getTeamMates() == t) {
                c = c1;
            }
            if (c2.getTeamMates() == t) {
                c = c2;
            }
            if (c != null) {
                value += CoachMatch.getVNDByCoach(c, cm);
            }
        }
        return value;
    }

    private int getTeamRosterGroups(Team t) {
        int value = 0;
        if (this == t.getMatch(0)) {
            for (int i = 0; i < Tournament.getTournament().getParams().getTeamMatesNumber(); i++) {
                Coach c = t.getCoach(i);
                RosterType rt = c.getRoster();
                for (int j = 0; j < Tournament.getTournament().getGroupsCount(); j++) {
                    Group g = Tournament.getTournament().getGroup(j);
                    if (g.containsRoster(rt)) {
                        value += g.getPoints();
                        break;
                    }
                }
            }
        }
        return value;
    }

    public int getValue(final Team t, final int rankingType, boolean teamVictory) {
        int value = 0;

        boolean splitted = false;
        if (this.getMatchCount() == t.getCoachsCount() / 2) {
            splitted = true;
        }

        int roundIndex = -1;
        for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
            Round r = Tournament.getTournament().getRound(i);
            if (r.containsMatch(this)) {
                roundIndex = i;
                break;
            }
        }

        // Find opposing team in using first Coach
        if (teamVictory) {
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS:
                    value = getPointsByTeam(t, this, true, true);
                    value = splitted ? value / 2 : value;
                    break;
                case Parameters.C_RANKING_POINTS_WITHOUT_BONUS:
                    value = getPointsByTeam(t, this, true, false);
                    value = splitted ? value / 2 : value;
                    break;
                case Parameters.C_RANKING_BONUS_POINTS:
                    value = getPointsByTeam(t, this, false, true);
                    value = splitted ? value / 2 : value;
                    break;
                case Parameters.C_RANKING_NONE:
                    value = 0;
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                    value = getOppPointsByTeam(t, this, true);
                    value = splitted ? value / 2 : value;
                    break;
                case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                    value = getOppPointsByTeam(t, this, false);
                    value = splitted ? value / 2 : value;
                    break;
                case Parameters.C_RANKING_VND:
                    value = getVNDByTeam(t, this, true);
                    value = splitted ? value / 2 : value;
                    break;
                case Parameters.C_RANKING_ELO:
                    value = getELOByTeam(t, this, roundIndex);
                    break;
                case Parameters.C_RANKING_ELO_OPP:
                    value = getOppELOByTeam(t, this, roundIndex);
                    break;
                case Parameters.C_RANKING_NB_MATCHS:
                    value = getTeamNbMatch(t, this);
                    value = splitted ? value / 2 : value;
                    break;
                case Parameters.C_RANKING_TABLES:
                    value = getTeamTable(t, this);
                    value = splitted ? value / 2 : value;
                    break;
                case Parameters.C_RANKING_HEAD_BY_HEAD:
                    value = 0;
                    break;
                case Parameters.C_RANKING_TIER:
                    value = getTeamRosterGroups(t);
                    break;
                case Parameters.C_RANKING_TEAMMATES_POINTS:
                    value = getTeammatesPoints(t);
                    break;
                case Parameters.C_RANKING_TEAMMATES_VND:
                    value = getTeammatesVND(t);
                    break;
                default:
                    if (rankingType > Parameters.C_MAX_RANKING) {
                        if (rankingType<Parameters.C_MAX_RANKING+Tournament.getTournament().getParams().getCriteriaCount()*3)
                        {
                            value += getValue(MjtRanking.getCriteriaByValue(rankingType), MjtRanking.getSubtypeByValue(rankingType), t);
                        }
                        else
                        {
                            int f=rankingType-Parameters.C_MAX_RANKING-Tournament.getTournament().getParams().getCriteriaCount()*3-1;
                            value+=getValue(Tournament.getTournament().getParams().getFormula(f),t);
                        }
                    }
            }
        } else {
            for (int i = 0; i < getMatchCount(); i++) {
                CoachMatch cm = getMatch(i);
                Coach c = null;
                Coach c1 = (Coach) cm.getCompetitor1();
                Coach c2 = (Coach) cm.getCompetitor2();
                if (c1.getTeamMates() == t) {
                    c = c1;
                }
                if (c2.getTeamMates() == t) {
                    c = c2;
                }
                if (c != null) {
                    switch (rankingType) {
                        case Parameters.C_RANKING_POINTS:
                            value += CoachMatch.getPointsByCoach(c, cm, true, true);
                            break;
                        case Parameters.C_RANKING_POINTS_WITHOUT_BONUS:
                            value += CoachMatch.getPointsByCoach(c, cm, true, false);
                            break;
                        case Parameters.C_RANKING_BONUS_POINTS:
                            value += CoachMatch.getPointsByCoach(c, cm, false, true);
                            break;
                        case Parameters.C_RANKING_NONE:
                            value += 0;
                            break;
                        case Parameters.C_RANKING_OPP_POINTS:
                            value += CoachMatch.getOppPointsByCoach(c, cm, true);
                            break;
                        case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                            value += CoachMatch.getOppPointsByCoach(c, cm, false);
                            break;
                        case Parameters.C_RANKING_VND:
                            value += CoachMatch.getVNDByCoach(c, cm);
                            break;
                        case Parameters.C_RANKING_ELO:
                            value += CoachMatch.getELOByCoach(c, cm);
                            break;
                        case Parameters.C_RANKING_ELO_OPP:
                            value += CoachMatch.getOppELOByCoach(c, cm);
                            break;
                        case Parameters.C_RANKING_NB_MATCHS:
                            value += CoachMatch.getCoachNbMatchs(c, cm);
                            break;
                        case Parameters.C_RANKING_TABLES:
                            if (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
                                value = getTeamTable(t, this);
                            } else {
                                value += CoachMatch.getCoachTablePoints(c, cm);
                            }
                            break;
                        case Parameters.C_RANKING_TEAMMATES_POINTS:
                            value = 0;
                            break;
                        case Parameters.C_RANKING_TEAMMATES_VND:
                            value = 0;
                            break;
                        default:

                    }
                }
            }
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS: {
                    int VND = getVNDByTeam(t, this, true);
                    int nbVictory = (VND / 1000000);
                    int nbDraw = (VND % 1000000) / 1000;
                    if (splitted) {
                        value += nbVictory * Tournament.getTournament().getParams().getPointsTeamVictoryBonus() / 2;
                        value += nbDraw * Tournament.getTournament().getParams().getPointsTeamDrawBonus() / 2;
                    } else {
                        value += nbVictory * Tournament.getTournament().getParams().getPointsTeamVictoryBonus();
                        value += nbDraw * Tournament.getTournament().getParams().getPointsTeamDrawBonus();
                    }
                }
                break;
                case Parameters.C_RANKING_POINTS_WITHOUT_BONUS: {
                    int VND = getVNDByTeam(t, this, true);
                    int nbVictory = (VND / 1000000);
                    int nbDraw = (VND % 1000000) / 1000;
                    if (splitted) {
                        value += nbVictory * Tournament.getTournament().getParams().getPointsTeamVictoryBonus() / 2;
                        value += nbDraw * Tournament.getTournament().getParams().getPointsTeamDrawBonus() / 2;
                    } else {
                        value += nbVictory * Tournament.getTournament().getParams().getPointsTeamVictoryBonus();
                        value += nbDraw * Tournament.getTournament().getParams().getPointsTeamDrawBonus();
                    }
                }
                break;
                case Parameters.C_RANKING_TIER:
                    value = getTeamRosterGroups(t);
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                    if (t.getCoachsCount() > 0) {
                        final Coach c = t.getCoach(0);
                        int i = 0;
                        if (c.getMatchCount() > i) {
                            final CoachMatch m = (CoachMatch) c.getMatch(i);
                            if (m.getCompetitor1() == null) {
                                m.setCompetitor1(Coach.getNullCoach());
                            }
                            if (m.getCompetitor2() == null) {
                                m.setCompetitor2(Coach.getNullCoach());
                            }
                            if (m.getCompetitor1() == c) {
                                long tmp = getVNDByTeam(((Coach) m.getCompetitor2()).getTeamMates(), this, rankingType != Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS);
                                if (splitted) {
                                    value += (tmp / 1000000) * Tournament.getTournament().getParams().getPointsTeamVictoryBonus() / 2;
                                    value += ((tmp % 1000000) / 1000) * Tournament.getTournament().getParams().getPointsTeamDrawBonus() / 2;
                                } else {
                                    value += (tmp / 1000000) * Tournament.getTournament().getParams().getPointsTeamVictoryBonus();
                                    value += ((tmp % 1000000) / 1000) * Tournament.getTournament().getParams().getPointsTeamDrawBonus();
                                }
                            } else {
                                long tmp = getVNDByTeam(((Coach) m.getCompetitor1()).getTeamMates(), this, rankingType != Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS);

                                if (splitted) {
                                    value += (tmp / 1000000) * Tournament.getTournament().getParams().getPointsTeamVictoryBonus() / 2;
                                    value += ((tmp % 1000000) / 1000) * Tournament.getTournament().getParams().getPointsTeamDrawBonus() / 2;
                                } else {
                                    value += (tmp / 1000000) * Tournament.getTournament().getParams().getPointsTeamVictoryBonus();
                                    value += ((tmp % 1000000) / 1000) * Tournament.getTournament().getParams().getPointsTeamDrawBonus();
                                }
                            }
                        }
                    }
                    break;
                default:
                    if (rankingType > Parameters.C_MAX_RANKING) {
                        if (rankingType < Parameters.C_MAX_RANKING + 3 * Tournament.getTournament().getParams().getCriteriaCount()) {
                            value += getValue(MjtRanking.getCriteriaByValue(rankingType), MjtRanking.getSubtypeByValue(rankingType), t);
                        } else {
                            int f = rankingType - 3 * Tournament.getTournament().getParams().getCriteriaCount() - Parameters.C_MAX_RANKING - 1;
                            Formula formula = Tournament.getTournament().getParams().getFormula(f);
                            value += getValue(formula, t);
                        }
                    }
            }
        }
        return value;
    }

    int getValue(final Team t, TeamMatch tm,
            final Criteria crit, final int subtype
    ) {
        int value = 0;
        for (int i = 0; i < tm.getMatchCount(); i++) {
            CoachMatch cm = tm.getMatch(i);

            Coach c = null;

            Coach c1 = (Coach) cm.getCompetitor1();
            Coach c2 = (Coach) cm.getCompetitor2();

            if (c1.getTeamMates() == t) {
                c = c1;
            }
            if (c2.getTeamMates() == t) {
                c = c2;
            }
            if (c != null) {
                value += cm.getValue(crit, subtype, c);
            }
        }
        return value;

    }

    public int getValue(Criteria crit, int subtype, Competitor c) {
        int value = 0;
        if (c == mCompetitor1) {
            for (int i = 0; i < this.getMatchCount(); i++) {
                CoachMatch cm = getMatch(i);
                value += cm.getValue(crit, subtype, cm.getCompetitor1());
            }
        }
        if (c == mCompetitor2) {
            for (int i = 0; i < this.getMatchCount(); i++) {
                CoachMatch cm = getMatch(i);
                value += cm.getValue(crit, subtype, cm.getCompetitor2());
            }
        }
        return value;
    }
    
    public int getValue(Formula  form, Competitor c) {
        int value = 0;
        if (c == mCompetitor1) {
            for (int i = 0; i < this.getMatchCount(); i++) {
                CoachMatch cm = getMatch(i);
                value += cm.getValue(form, cm.getCompetitor1());
            }
        }
        if (c == mCompetitor2) {
            for (int i = 0; i < this.getMatchCount(); i++) {
                CoachMatch cm = getMatch(i);
                value += cm.getValue(form, cm.getCompetitor2());
            }
        }
        return value;
    }

    /**
     * Recalculate the values fot this match
     */
    public void recomputeValues() {
        this.c1value1 = recomputeValue(1, mCompetitor1);
        this.c2value1 = recomputeValue(1, mCompetitor2);
        this.c1value2 = recomputeValue(2, mCompetitor1);
        this.c2value2 = recomputeValue(2, mCompetitor2);
        this.c1value3 = recomputeValue(3, mCompetitor1);
        this.c2value3 = recomputeValue(3, mCompetitor2);
        this.c1value4 = recomputeValue(4, mCompetitor1);
        this.c2value4 = recomputeValue(4, mCompetitor2);
        this.c1value5 = recomputeValue(5, mCompetitor1);
        this.c2value5 = recomputeValue(5, mCompetitor2);

        for (CoachMatch cm : mMatchs) {
            cm.recomputeValues();
        }

        values_computed = true;
    }

    protected int recomputeValue(int index, Competitor c) {
        int value = 0;
        int valueType = Parameters.C_RANKING_NONE;
        if (Tournament.getTournament().getParams().isTeamVictoryOnly()) {
            valueType = Tournament.getTournament().getParams().getTeamRankingType(index - 1);
        } else {
            valueType = Tournament.getTournament().getParams().getIndivRankingType(index - 1);
        }
        value = getValue((Team) c, valueType, Tournament.getTournament().getParams().isTeamVictoryOnly());
        return value;
    }

    public boolean isEntered() {
        for (int i = 0; i < getMatchCount(); i++) {
            CoachMatch cm = getMatch(i);
            if (!cm.isEntered()) {
                return false;
            }
        }
        return true;
    }
}
