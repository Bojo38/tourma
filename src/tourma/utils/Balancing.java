/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;
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
public final class Balancing {

    private static final Logger LOG = Logger.getLogger(Balancing.class.getName());

    /**
     *
     * @param matchs
     */
    public static void balanceCoachMatchs(ArrayList<Match> matchs) throws RemoteException{

        ArrayList<Team> teams = new ArrayList<>();
        // Build possible teams opponents
        for (int i = 0; i < matchs.size(); i++) {
            CoachMatch cm = (CoachMatch) matchs.get(i);
            Team t1 = ((Coach) cm.getCompetitor1()).getTeamMates();
            Team t2 = ((Coach) cm.getCompetitor2()).getTeamMates();

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
        fillHashMap(matchs, evaluationPreviousC, evaluationPreviousT, teams);

        int i = 0;
        while ((!isRoundValid(matchs, evaluationPreviousC, evaluationPreviousT))
                || (i < 10000)) {

            /*int minT = getMinimumFromHash(evaluationPreviousT);
             int maxT = getMaximumFromHash(evaluationPreviousT);

             int minC = getMinimumFromHash(evaluationPreviousC);
             int maxC = getMaximumFromHash(evaluationPreviousC);*/
            // First, find coach to swap
            Iterator<Competitor> it = evaluationPreviousC.keySet().iterator();
            while (it.hasNext()) {
                Competitor c = it.next();
                if (c instanceof Coach) {
                    Coach coach = (Coach) c;
                    HashMap<Team, Integer> map = evaluationPreviousC.get(coach);

                    int min = 65535;
                    int max = 0;
                    Iterator<Team> it2 = map.keySet().iterator();
                    while (it2.hasNext()) {
                        Team t = it2.next();
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
                            Team t = it2.next();
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
                                    if ((cm.getCompetitor1() == cptt) || (cm.getCompetitor2() == cptt)) {
                                        valid = true;
                                        break;
                                    }
                                }
                                if (valid) {
                                    comps.add(cptt);
                                }
                            }

                            comps = coach.getPossibleOpponents(comps, Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1));
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
                                    Iterator<Team> itOpp = mapOpp.keySet().iterator();
                                    while (itOpp.hasNext()) {
                                        Team t = itOpp.next();
                                        int nb = mapOpp.get(t);
                                        if (nb < minOpp) {
                                            minOpp = nb;
                                        }
                                        if (nb > maxOpp) {
                                            maxOpp = nb;
                                        }
                                    }

                                    ArrayList<Team> minTeams = new ArrayList<>();
                                    //ArrayList<Team> maxTeams = new ArrayList<Team>();
                                    itOpp = mapOpp.keySet().iterator();
                                    while (itOpp.hasNext()) {
                                        Team t = itOpp.next();
                                        int nb = mapOpp.get(t);
                                        if (nb == minOpp) {
                                            minTeams.add(t);
                                        }
                                        /*if (nb == maxOpp) {
                                         maxTeams.add(t);
                                         }*/
                                    }

                                    if (minTeams.contains(coach.getTeamMates())) {
                                        // Test if opponent adversary is not
                                        // from same team that the current coach
                                        Coach oppopp;
                                        if (opponent.getMatchCount() > 0) {
                                            CoachMatch m = (CoachMatch) opponent.getMatch(opponent.getMatchCount() - 1);
                                            if (m.getCompetitor1() == opponent) {
                                                oppopp = (Coach) m.getCompetitor2();
                                            } else {
                                                oppopp = (Coach) m.getCompetitor1();
                                            }
                                            if (oppopp.getTeamMates() != coach.getTeamMates()) {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                }

                                if (opponent != null) {
                                    if (opponent.isMatchsNotNull()) {
                                        // Swap opponent and current opponent                            
                                        if (opponent.getMatchCount() > 0) {
                                            Coach oppopp;
                                            Coach curropp;
                                            CoachMatch OpponentMatch = (CoachMatch) opponent.getMatch(opponent.getMatchCount() - 1);
                                            CoachMatch CurrenttMatch = (CoachMatch) coach.getMatch(coach.getMatchCount() - 1);

                                            if (OpponentMatch.getCompetitor1() == opponent) {
                                                oppopp = (Coach) OpponentMatch.getCompetitor2();
                                            } else {
                                                oppopp = (Coach) OpponentMatch.getCompetitor1();
                                            }

                                            if (CurrenttMatch.getCompetitor1() == coach) {
                                                curropp = (Coach) CurrenttMatch.getCompetitor2();
                                            } else {
                                                curropp = (Coach) CurrenttMatch.getCompetitor1();
                                            }

                                            coach.removeMatch(CurrenttMatch);
                                            curropp.removeMatch(CurrenttMatch);

                                            opponent.removeMatch(OpponentMatch);
                                            oppopp.removeMatch(OpponentMatch);

                                            CurrenttMatch.setCompetitor1(coach);
                                            CurrenttMatch.setCompetitor2(opponent);

                                            OpponentMatch.setCompetitor1(curropp);
                                            OpponentMatch.setCompetitor2(oppopp);

                                            coach.addMatch(CurrenttMatch);
                                            opponent.addMatch(CurrenttMatch);

                                            curropp.addMatch(OpponentMatch);
                                            oppopp.addMatch(OpponentMatch);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }

            }
            // Find possible opponents team for the coach

            fillHashMap(matchs, evaluationPreviousC, evaluationPreviousT, teams);
            i++;
        }
    }

    /**
     *
     * @param matchs
     * @param evaluationPreviousC
     * @param evaluationPreviousT
     * @param teams
     */
    static private void fillHashMap(ArrayList<Match> matchs, HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC,
            HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT, ArrayList<Team> teams) throws RemoteException{
        // @TODO
        if (Tournament.getTournament().getTeamsCount() > 0) {
            for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                Coach c=Tournament.getTournament().getCoach(i);
                for (int k=0; k<c.getMatchCount(); k++)
                {
                    CoachMatch cm=(CoachMatch)c.getMatch(k);
                    Coach opp=null;
                    if (cm.getCompetitor1()==c)
                    {
                        opp=(Coach)cm.getCompetitor2();
                    }
                    else
                    {
                        opp=(Coach)cm.getCompetitor1();
                    }
                    
                    HashMap mapC=evaluationPreviousC.get(c);
                    if (mapC==null)
                    {
                        mapC=new HashMap<>();
                    }
                    int nb=(Integer)mapC.get(opp.getTeamMates());
                    nb++;
                    mapC.put(opp.getTeamMates(), nb);
                    
                    HashMap mapT=evaluationPreviousC.get(c.getTeamMates());
                    if (mapT==null)
                    {
                        mapT=new HashMap<>();
                    }
                    nb=(Integer)mapT.get(opp.getTeamMates());
                    nb++;
                    mapT.put(opp.getTeamMates(), nb);
                }
            }
        }
    }

    /**
     *
     * @param matchs
     * @param evaluationPreviousC
     * @param evaluationPreviousT
     * @return
     */
    static public boolean isRoundValid(ArrayList<Match> matchs, HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC,
            HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT) {

        int minT = getMinimumFromHash(evaluationPreviousT);
        int maxT = getMaximumFromHash(evaluationPreviousT);

        int minC = getMinimumFromHash(evaluationPreviousC);
        int maxC = getMaximumFromHash(evaluationPreviousC);

        return !((maxT - minT > 1) || (maxC - minC > 1));

    }

    /**
     *
     * @param hash
     * @return
     */
    static public int getMinimumFromHash(HashMap<Competitor, HashMap<Team, Integer>> hash) {

        Iterator<Competitor> it = hash.keySet().iterator();
        int minimum2 = 65535;
        while (it.hasNext()) {
            HashMap<Team, Integer> hash2;
            hash2 = hash.get(it.next());
            Iterator<Team> it2 = hash2.keySet().iterator();
            while (it2.hasNext()) {
                Competitor en2 = it2.next();
                if (en2 instanceof Team) {
                    Team t2 = (Team) en2;
                    int nb2 = hash2.get(t2);
                    if (nb2 < minimum2) {
                        minimum2 = nb2;
                    }
                }
            }
        }
        return minimum2;
    }

    /**
     *
     * @param hash
     * @return
     */
    static public int getMaximumFromHash(HashMap<Competitor, HashMap<Team, Integer>> hash) {

        Iterator<Competitor> it = hash.keySet().iterator();
        int maximum2 = 0;
        while (it.hasNext()) {
            HashMap<Team, Integer> hash2;
            hash2 = hash.get(it.next());
            Iterator<Team> it2 = hash2.keySet().iterator();
            while (it2.hasNext()) {
                Competitor en2 = it2.next();
                if (en2 instanceof Team) {
                    Team t2 = (Team) en2;
                    int nb2 = hash2.get(t2);
                    if (nb2 > maximum2) {
                        maximum2 = nb2;
                    }
                }
            }
        }
        return maximum2;
    }

    private Balancing() {
    }
}
