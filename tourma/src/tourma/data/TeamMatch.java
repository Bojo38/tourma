/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import tourma.MainFrame;

/**
 *
 * @author Frederic Berger
 */
public class TeamMatch extends Match {

    public ArrayList<CoachMatch> mMatchs;

    public TeamMatch(Round round) {
        super(round);
        mMatchs = new ArrayList<>();
    }

    public Competitor getWinner() {

        Tournament tour = Tournament.getTournament();
        final Team team1 = (Team) mCompetitor1;
        final Team team2 = (Team) mCompetitor2;

        Team winner;

        int nbVictory = 0;
        int nbLost = 0;

        Criteria td = tour.getParams().mCriterias.get(0);
        for (int j = 0; j < mMatchs.size(); j++) {
            CoachMatch m = mMatchs.get(j);
            if (m.mValues.get(td).mValue1 > m.mValues.get(td).mValue2) {
                nbVictory++;
            } else {
                if (m.mValues.get(td).mValue1 < m.mValues.get(td).mValue2) {
                    nbLost++;
                }
            }
        }

        if (team1 == Team.sNullTeam) {
            winner = team2;
        } else {
            if (team2 == Team.sNullTeam) {
                winner = team1;
            } else {
                if (nbVictory > nbLost) {
                    winner = team1;
                } else {
                    if (nbVictory < nbLost) {
                        winner = team2;
                    } else {
                        if (((int) Math.random()) % 2 == 0) {
                            winner = team1;
                        } else {
                            winner = team2;
                        }
                    }
                }
            }
        }
        return winner;
    }

    public Competitor getLooser() {
        Tournament tour = Tournament.getTournament();
        final Team team1 = (Team) mCompetitor1;
        final Team team2 = (Team) mCompetitor2;

        Team looser;

        int nbVictory = 0;
        int nbLost = 0;

        Criteria td = tour.getParams().mCriterias.get(0);
        for (int j = 0; j < mMatchs.size(); j++) {
            CoachMatch m = mMatchs.get(j);
            if (m.mValues.get(td).mValue1 > m.mValues.get(td).mValue2) {
                nbVictory++;
            } else {
                if (m.mValues.get(td).mValue1 < m.mValues.get(td).mValue2) {
                    nbLost++;
                }
            }
        }

        if (team1 == Team.sNullTeam) {
            looser = team1;
        } else {
            if (team2 == Team.sNullTeam) {
                looser = team2;
            } else {
                if (nbVictory > nbLost) {
                    looser = team2;
                } else {
                    if (nbVictory < nbLost) {
                        looser = team1;
                    } else {
                        if (((int) Math.random()) % 2 == 0) {
                            looser = team2;
                        } else {
                            looser = team1;
                        }
                    }
                }
            }
        }
        return looser;

    }

    /* public static Team getTeamMatchWinner(final int teamMatesNumber, final int matchIndex, final ArrayList<CoachMatch> matchs) {
     final Criteria td = Tournament.getTournament().getParams().mCriterias.get(0);
     CoachMatch m = matchs.get(matchIndex * teamMatesNumber);
     final Team team1 = m.mCoach1.mTeamMates;
     final Team team2 = m.mCoach2.mTeamMates;

     Team winner;

     int nbVictory = 0;
     int nbLost = 0;

     for (int j = 0; j < teamMatesNumber; j++) {
     m = matchs.get(matchIndex * teamMatesNumber + j);
     if (m.mValues.get(td).mValue1 > m.mValues.get(td).mValue2) {
     nbVictory++;
     } else {
     if (m.mValues.get(td).mValue1 < m.mValues.get(td).mValue2) {
     nbLost++;
     }
     }
     }

     if (team1 == Team.sNullTeam) {
     winner = team2;
     } else {
     if (team2 == Team.sNullTeam) {
     winner = team1;
     } else {
     if (nbVictory > nbLost) {
     winner = team1;
     } else {
     if (nbVictory < nbLost) {
     winner = team2;
     } else {
     if (((int) Math.random()) % 2 == 0) {
     winner = team1;
     } else {
     winner = team2;
     }
     }
     }
     }
     }
     return winner;
     }*/
    @Override
    public Element getXMLElement() {
        final Element match = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH"));
        match.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM1"), this.mCompetitor1.mName);
        match.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM2"), this.mCompetitor1.mName);

        for (int k = 0; k < mMatchs.size(); k++) {
            Element subMatch = mMatchs.get(k).getXMLElement();
            match.addContent(subMatch);
        }
        return match;
    }

    @Override
    public void setXMLElement(final Element match) {

        final String c1 = match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM1")).getValue();
        final String c2 = match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM2")).getValue();
        this.mCompetitor1 = Team.sTeamMap.get(c1);
        this.mCompetitor2 = Team.sTeamMap.get(c2);

        final List values = match.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH"));
        final Iterator v = values.iterator();

        while (v.hasNext()) {
            CoachMatch m = new CoachMatch(mRound);
            final Element val = (Element) v.next();
            m.setXMLElement(val);

            mMatchs.add(m);
        }
    }

    public int getVictories(Team t1) {
        Tournament tour = Tournament.getTournament();
        final Team team1 = (Team) t1;
        Team team2;

        int nbVictories = 0;

        if (t1 == mCompetitor1) {
            team2 = (Team) mCompetitor2;
        } else {
            if (t1 == mCompetitor2) {
                team2 = (Team) mCompetitor1;
            } else {
                team2 = null;
            }
        }

        if (team2 != null) {
            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().mCriterias.get(0);
            for (int j = 0; j < mMatchs.size(); j++) {
                CoachMatch m = mMatchs.get(j);
                if (m.mValues.get(td).mValue1 > m.mValues.get(td).mValue2) {
                    nbVictory++;
                } else {
                    if (m.mValues.get(td).mValue1 < m.mValues.get(td).mValue2) {
                        nbLost++;
                    }
                }
            }
            
            if (team2==t1)
            {
                nbVictories=nbLost;
            }
            else
            {
                nbVictories=nbVictory;
            }            
        }
        return nbVictories;
    }

    public int getLoss(Team t1) {
        Tournament tour = Tournament.getTournament();
        final Team team1 = (Team) t1;
        Team team2;

        int nbLoose = 0;

        if (t1 == mCompetitor1) {
            team2 = (Team) mCompetitor2;
        } else {
            if (t1 == mCompetitor2) {
                team2 = (Team) mCompetitor1;
            } else {
                team2 = null;
            }
        }

        if (team2 != null) {
            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().mCriterias.get(0);
            for (int j = 0; j < mMatchs.size(); j++) {
                CoachMatch m = mMatchs.get(j);
                if (m.mValues.get(td).mValue1 > m.mValues.get(td).mValue2) {
                    nbVictory++;
                } else {
                    if (m.mValues.get(td).mValue1 < m.mValues.get(td).mValue2) {
                        nbLost++;
                    }
                }
            }
            
            if (team2==t1)
            {
                nbLoose=nbVictory;
            }
            else
            {
                nbLoose=nbLost;
            }            
        }
        return nbLoose;
    }

    public int getDraw(Team t1) {
        Tournament tour = Tournament.getTournament();
        final Team team1 = (Team) t1;
        Team team2;

        int nbDraw = 0;

        if (t1 == mCompetitor1) {
            team2 = (Team) mCompetitor2;
        } else {
            if (t1 == mCompetitor2) {
                team2 = (Team) mCompetitor1;
            } else {
                team2 = null;
            }
        }

        if (team2 != null) {
            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().mCriterias.get(0);
            for (int j = 0; j < mMatchs.size(); j++) {
                CoachMatch m = mMatchs.get(j);
                if ((m.mValues.get(td).mValue1 == m.mValues.get(td).mValue2) &&(m.mValues.get(td).mValue2!=-1)){
                    nbDraw++;
                } 
            }                                    
        }
        return nbDraw;
    }
}
