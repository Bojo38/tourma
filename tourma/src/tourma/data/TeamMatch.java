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
}
