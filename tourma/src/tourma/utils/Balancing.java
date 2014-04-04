/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Match;
import tourma.data.Team;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class Balancing {

    public static void BalanceCoachMatchs(ArrayList<Match> matchs) {

        ArrayList<Team> teams = new ArrayList<>();
        // Build possible teams opponents
        for (int i = 0; i < matchs.size(); i++) {
            CoachMatch cm = (CoachMatch) matchs.get(i);
            Team t1 = ((Coach) cm.mCompetitor1).mTeamMates;
            Team t2 = ((Coach) cm.mCompetitor2).mTeamMates;

            if (!teams.contains(t1)) {
                teams.add(t1);
            }
            if (!teams.contains(t2)) {
                teams.add(t2);
            }
        }

        // First, build opposition map        
        // According to each competitor (Coach), evaluates rounds
        HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC = new HashMap<>();
        HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT = new HashMap<>();
        FillHashMap(matchs, evaluationPreviousC, evaluationPreviousT, teams);

        int i = 0;
        while ((!IsRoundValid(matchs, evaluationPreviousC, evaluationPreviousT))
                || (i < 10000)) {

            int minT = getMinimumFromHash(evaluationPreviousT);
            int maxT = getMaximumFromHash(evaluationPreviousT);

            int minC = getMinimumFromHash(evaluationPreviousC);
            int maxC = getMaximumFromHash(evaluationPreviousC);

            // First, find coach to swap
            Iterator it = evaluationPreviousC.keySet().iterator();
            while (it.hasNext()) {
                Coach coach = (Coach) it.next();

                HashMap<Team, Integer> map = evaluationPreviousC.get(coach);

                int min = 65535;
                int max = 0;
                Iterator it2 = map.keySet().iterator();
                while (it2.hasNext()) {
                    Team t = (Team) it2.next();
                    int nb = map.get(t);
                    if (nb < min) {
                        min = nb;
                    }
                    if (nb > max) {
                        max = nb;
                    }
                }
                if (max - min > 1) {
                    // Find Opposing team maximum
                    // Find Opposing team minimum
                    Team minTeam = null;
                    Team maxTeam = null;
                    it2 = map.keySet().iterator();
                    while (it2.hasNext()) {
                        Team t = (Team) it2.next();
                        int nb = map.get(t);
                        if (nb == min) {
                            minTeam = t;
                        }
                        if (nb == max) {
                            maxTeam = t;
                        }
                    }

                    if ((minTeam != null) && (maxTeam != null)) {
                        // find a possible coach from minTeam 
                        ArrayList<Competitor> comps = new ArrayList<>();
                        for (int j = 0; j < minTeam.getActivePlayers().size(); j++) {
                            Competitor cptt = minTeam.getActivePlayers().get(j);
                            boolean valid = false;
                            for (int k = 0; k < matchs.size(); k++) {
                                // If player is not present in matchs, remove it
                                CoachMatch cm = (CoachMatch) matchs.get(j);
                                if ((cm.mCompetitor1 == cptt) || (cm.mCompetitor2 == cptt)) {
                                    valid = true;
                                    break;
                                }
                            }
                            if (valid) {
                                comps.add(cptt);
                            }
                        }

                        comps = coach.getPossibleOpponents(comps, Tournament.getTournament().getRounds().get(Tournament.getTournament().getRounds().size() - 1));
                        if (comps.size() > 0) {
                            // swap opponent
                            Collections.shuffle(comps);
                            Coach opponent = null;
                            for (int j = 0; j < comps.size(); j++) {
                                // Test possible opponent count
                                opponent = (Coach) comps.get(j);

                                HashMap<Team, Integer> mapOpp = evaluationPreviousC.get(opponent);

                                int minOpp = 65535;
                                int maxOpp = 0;
                                Iterator itOpp = mapOpp.keySet().iterator();
                                while (itOpp.hasNext()) {
                                    Team t = (Team) itOpp.next();
                                    int nb = mapOpp.get(t);
                                    if (nb < minOpp) {
                                        minOpp = nb;
                                    }
                                    if (nb > maxOpp) {
                                        maxOpp = nb;
                                    }
                                }

                                ArrayList<Team> minTeams = new ArrayList<>();
                                ArrayList<Team> maxTeams = new ArrayList<>();
                                itOpp = mapOpp.keySet().iterator();
                                while (itOpp.hasNext()) {
                                    Team t = (Team) itOpp.next();
                                    int nb = mapOpp.get(t);
                                    if (nb == minOpp) {
                                        minTeams.add(t);
                                    }
                                    if (nb == maxOpp) {
                                        maxTeams.add(t);
                                    }
                                }

                                if (minTeams.contains(coach.mTeamMates)) {
                                    // Test if opponent adversary is not
                                    // from same team that the current coach
                                    Coach oppopp;
                                    if (opponent.mMatchs.size() > 0) {
                                        CoachMatch m = (CoachMatch) opponent.mMatchs.get(opponent.mMatchs.size() - 1);
                                        if (m.mCompetitor1 == opponent) {
                                            oppopp = (Coach) m.mCompetitor2;
                                        } else {
                                            oppopp = (Coach) m.mCompetitor1;
                                        }
                                        if (oppopp.mTeamMates != coach.mTeamMates) {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }

                            // Swap opponent and current opponent                            
                            if (opponent.mMatchs.size() > 0) {
                                Coach oppopp;
                                Coach curropp;
                                CoachMatch OpponentMatch = (CoachMatch) opponent.mMatchs.get(opponent.mMatchs.size() - 1);
                                CoachMatch CurrenttMatch = (CoachMatch) coach.mMatchs.get(coach.mMatchs.size() - 1);
                                
                                if (OpponentMatch.mCompetitor1 == opponent) {
                                    oppopp = (Coach) OpponentMatch.mCompetitor2;
                                } else {
                                    oppopp = (Coach) OpponentMatch.mCompetitor1;
                                }
                                
                                if (CurrenttMatch.mCompetitor1 == coach) {
                                    curropp = (Coach) CurrenttMatch.mCompetitor2;
                                } else {
                                    curropp = (Coach) CurrenttMatch.mCompetitor1;
                                }
                                
                                coach.mMatchs.remove(CurrenttMatch);
                                curropp.mMatchs.remove(CurrenttMatch);
                                
                                opponent.mMatchs.remove(OpponentMatch);
                                oppopp.mMatchs.remove(OpponentMatch);
                                
                                CurrenttMatch.mCompetitor1=coach;
                                CurrenttMatch.mCompetitor2=opponent;
                                
                                OpponentMatch.mCompetitor1=curropp;
                                OpponentMatch.mCompetitor2=oppopp;
                                
                                coach.mMatchs.add(CurrenttMatch);
                                opponent.mMatchs.add(CurrenttMatch);
                                
                                curropp.mMatchs.add(OpponentMatch);
                                oppopp.mMatchs.add(OpponentMatch);                                                                
                            }
                        }
                    }

                }

            }
            // Find possible opponents team for the coach

            FillHashMap(matchs, evaluationPreviousC, evaluationPreviousT, teams);
            i++;
        }
    }

    static protected void FillHashMap(ArrayList<Match> matchs, HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC,
            HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT, ArrayList<Team> teams) {


      /*  for (int i = 0; i < matchs.size(); i++) {
            Coach comp1 = (Coach) matchs.get(i).mCompetitor1;
            Coach comp2 = (Coach) matchs.get(i).mCompetitor2;
            evaluationPreviousC.put(comp1, comp1.getTeamOppositionCount(teams));
            evaluationPreviousC.put(comp2, comp2.getTeamOppositionCount(teams));
            if (!evaluationPreviousT.containsKey(comp1.mTeamMates)) {
                evaluationPreviousT.put(comp1.mTeamMates, comp1.getTeamOppositionCount(teams));
            }
            if (!evaluationPreviousT.containsKey(comp2.mTeamMates)) {
                evaluationPreviousT.put(comp2.mTeamMates, comp2.getTeamOppositionCount(teams));
            }
        }*/
    }

    static public boolean IsRoundValid(ArrayList<Match> matchs, HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC,
            HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT) {

        int minT = getMinimumFromHash(evaluationPreviousT);
        int maxT = getMaximumFromHash(evaluationPreviousT);

        int minC = getMinimumFromHash(evaluationPreviousC);
        int maxC = getMaximumFromHash(evaluationPreviousC);

        if ((maxT - minT > 1) || (maxC - minC > 1)) {
            return false;
        }
        return true;

    }

    static public int getMinimumFromHash(HashMap<Competitor, HashMap<Team, Integer>> hash) {

        Iterator it = hash.keySet().iterator();
        int minimum2 = 65535;
        while (it.hasNext()) {
            HashMap<Team, Integer> hash2 = hash.get((Competitor) it.next());
            Iterator it2 = hash2.keySet().iterator();
            while (it2.hasNext()) {
                Competitor en2 = (Competitor) it2.next();
                int nb2 = hash2.get(en2);
                if (nb2 < minimum2) {
                    minimum2 = nb2;
                }
            }
        }
        return minimum2;
    }

    static public int getMaximumFromHash(HashMap<Competitor, HashMap<Team, Integer>> hash) {

        Iterator it = hash.keySet().iterator();
        int maximum2 = 0;
        while (it.hasNext()) {
            HashMap<Team, Integer> hash2 = hash.get((Competitor) it.next());
            Iterator it2 = hash2.keySet().iterator();
            while (it2.hasNext()) {
                Competitor en2 = (Competitor) it2.next();
                int nb2 = hash2.get(en2);
                if (nb2 > maximum2) {
                    maximum2 = nb2;
                }
            }
        }
        return maximum2;
    }
}
