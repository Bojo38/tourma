/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.jdom2.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class TeamMatch extends Match {

    private static final Logger LOG = Logger.getLogger(TeamMatch.class.getName());

    /**
     *
     */
    private final ArrayList<CoachMatch> mMatchs;

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
                } else {
                    if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                        nbLost++;
                    }
                }
            }

            if (team1 == Team.getNullTeam()) {
                winner = team2;
                looser = team1;
            } else {
                if (team2 == Team.getNullTeam()) {
                    winner = team1;
                    looser = team2;
                } else {
                    if (nbVictory > nbLost) {
                        winner = team1;
                        looser = team2;
                    } else {
                        if (nbVictory < nbLost) {
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
                    }
                }
            }
            super.setWinner(winner);
            super.setLooser(looser);
            return winner;
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
                } else {
                    if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                        nbLost++;
                    }
                }
            }

            if (team1 == Team.getNullTeam()) {
                looser = team1;
                winner = team2;
            } else {
                if (team2 == Team.getNullTeam()) {
                    looser = team2;
                    winner = team1;
                } else {
                    if (nbVictory > nbLost) {
                        looser = team2;
                        winner = team1;
                    } else {
                        if (nbVictory < nbLost) {
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
                    }
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
        match.setAttribute(StringConstants.CS_TEAM + 1, this.getCompetitor1().getName());
        match.setAttribute(StringConstants.CS_TEAM + 2, this.getCompetitor2().getName());

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
    public void setXMLElement(final Element match) {

        final String c1 = match.getAttribute(StringConstants.CS_TEAM + 1).getValue();
        final String c2 = match.getAttribute(StringConstants.CS_TEAM + 2).getValue();
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
        } else {
            if (t1 == getCompetitor2()) {
                team2 = (Team) getCompetitor1();
            } else {
                team2 = null;
            }
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
                } else {
                    if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                        nbLost++;
                    }
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
        } else {
            if (t1 == getCompetitor2()) {
                team2 = (Team) getCompetitor1();
            } else {
                team2 = null;
            }
        }

        if (team2 == Team.getNullTeam()) {
            return 0;
        }
        if (team1 == Team.getNullTeam()) {
            return 8;
        }

        if (t1 == getCompetitor1()) {
            team2 = (Team) getCompetitor2();
        } else {
            if (t1 == getCompetitor2()) {
                team2 = (Team) getCompetitor1();
            } else {
                team2 = null;
            }
        }

        if (team2 != null) {
            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().getCriteria(0);
            for (CoachMatch m : mMatchs) {
                if (m.getValue(td).getValue1() > m.getValue(td).getValue2()) {
                    nbVictory++;
                } else {
                    if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                        nbLost++;
                    }
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
        } else {
            if (t1 == getCompetitor2()) {
                team2 = (Team) getCompetitor1();
            } else {
                team2 = null;
            }
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
                equality &= mMatchs.get(i).equals(tm.getMatch(i));
            }
            return equality;
        }
        return false;

    }

}
