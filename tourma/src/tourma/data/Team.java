/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom2.Element;
import tourma.MainFrame;
import tourma.jdgPairing;
import tourma.utility.StringConstants;
import tourma.utils.Generation;

/**
 *
 * @author Frederic Berger
 */
public class Team extends Competitor implements XMLExport {

    protected static Team sNullTeam;
    public ArrayList<Coach> mCoachs;
    public static HashMap<String, Team> sTeamMap = new HashMap<>();

    public Team() {
        super();
        mCoachs = new ArrayList<>();
    }

    public Team(final String name) {
        super(name);
        mCoachs = new ArrayList<>();

    }

    public static Team getNullTeam() {
        if (sNullTeam == null) {
            sNullTeam = new Team(StringConstants.CS_NONE);
            int nbTeamMates = Tournament.getTournament().getParams().mTeamMatesNumber;

            for (int i = 0; i < nbTeamMates; i++) {
                sNullTeam.mCoachs.add(Coach.getNullCoach());
                if (Coach.getNullCoach().mTeamMates == null) {
                    Coach.getNullCoach().mTeamMates = sNullTeam;
                }
            }
        }
        return sNullTeam;
    }

    @Override
    public int compareTo(final Object obj) {
        int result = -1;
        if (obj instanceof Team) {

            double rank = 0;
            for (int i = 0; i < this.mCoachs.size(); i++) {
                rank += mCoachs.get(i).mNafRank;
            }
            rank = rank / mCoachs.size();

            double rankobj = 0;
            for (int i = 0; i < ((Team) obj).mCoachs.size(); i++) {
                rankobj += ((Team) obj).mCoachs.get(i).mNafRank;
            }
            rankobj = rankobj / ((Team) obj).mCoachs.size();

            result = ((Double) rank).compareTo(rankobj);
        }
        return result;
    }

    public int getActivePlayerNumber() {
        int nb = 0;

        for (int i = 0; i < mCoachs.size(); i++) {
            if (mCoachs.get(i).mActive) {
                nb++;
            }
        }
        return nb;
    }

    public ArrayList<Coach> getActivePlayers() {
        final ArrayList<Coach> v = new ArrayList<>();
        if (this == sNullTeam) {
            for (int i = 0; i < Tournament.getTournament().getParams().mTeamMatesNumber; i++) {
                v.add(Coach.sNullCoach);
            }
        } else {
            for (int i = 0; i < mCoachs.size(); i++) {
                if (mCoachs.get(i).mActive) {
                    v.add(mCoachs.get(i));
                }
            }
        }
        return v;
    }

    @Override
    public Element getXMLElement() {
        final Element team = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"));
        team.setAttribute(StringConstants.CS_NAME, this.mName);
        for (int j = 0; j < this.mCoachs.size(); j++) {
            final Element coach = new Element(StringConstants.CS_COACH);
            coach.setAttribute(StringConstants.CS_NAME, this.mCoachs.get(j).mName);
            team.addContent(coach);
        }
        return team;
    }

    @Override
    public void setXMLElement(final Element team) {
        this.mName = team.getAttributeValue(StringConstants.CS_NAME);
        final List coachs2 = team.getChildren(StringConstants.CS_COACH);
        final Iterator m = coachs2.iterator();
        this.mCoachs.clear();

        Team.sTeamMap.put(mName, this);
        while (m.hasNext()) {
            final Element coach = (Element) m.next();
            final Coach c = Coach.sCoachMap.get(coach.getAttribute(StringConstants.CS_NAME).getValue());
            c.mTeamMates = this;
            this.mCoachs.add(c);
        }
    }

    @Override
    public void AddMatch(Competitor opponent, Round r) {
        Tournament tour = Tournament.getTournament();

        final ArrayList<Round> vs = new ArrayList<>();
        for (int i = 0; i < tour.getRounds().size(); i++) {
            if (tour.getRounds().get(i).getHour().before(r.getHour())) {
                vs.add(tour.getRounds().get(i));
            }
        }
        vs.add(r);

        if (vs.size() == 1) {
            // First Round
        }

        Team team1 = this;
        Team team2 = (Team) opponent;

        TeamMatch m = new TeamMatch(r);
        m.mCompetitor1 = team1;
        m.mCompetitor2 = team2;
        r.mMatchs.add(m);

        switch (tour.getParams().mTeamIndivPairing) {
            // Ranking
            case 0:
                if (vs.size() == 1) {
                    //final boolean random = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AFFECTATION ALÉATOITE (SINON, L'ORDER D'INSCRIPTION SERA UTILISÉE) ?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GENERATION"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    final ArrayList<Coach> shuffle2 = new ArrayList<>(team2.getActivePlayers());
                    //if (random) {
                    Collections.shuffle(shuffle2);
                    //}
                    for (int k = 0; k < tour.getParams().mTeamMatesNumber; k++) {
                        m.mMatchs.add(team1.getActivePlayers().get(k).CreateMatch(shuffle2.get(k), r));
                    }
                } else {
                    final ArrayList<ObjectRanking> coachs1 = Generation.subRanking(team1.mCoachs, vs);
                    final ArrayList<ObjectRanking> coachs2 = Generation.subRanking(team2.mCoachs, vs);
                    for (int k = 0; k < coachs1.size(); k++) {
                        m.mMatchs.add(((Coach) coachs1.get(k).getObject()).CreateMatch((Coach) coachs2.get(k).getObject(), r));
                    }
                }
                break;
            // Manual
            case 1:
                final jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r, m.mMatchs);
                jdg.setVisible(true);
                break;
            // GenRandom
            case 2:
                final ArrayList<Coach> shuffle2 = new ArrayList<>(team2.getActivePlayers());
                Collections.shuffle(shuffle2);
                for (int k = 0; k < tour.getParams().mTeamMatesNumber; k++) {
                    m.mMatchs.add(team1.getActivePlayers().get(k).CreateMatch(shuffle2.get(k), r));
                }
                break;
            // NAF
            case 3:
                final ArrayList<Coach> sort1 = new ArrayList<>(team1.getActivePlayers());
                final ArrayList<Coach> sort2 = new ArrayList<>(team2.getActivePlayers());
                Collections.sort(sort1);
                Collections.sort(sort2);

                for (int k = 0; k < tour.getParams().mTeamMatesNumber; k++) {
                    m.mMatchs.add(sort1.get(k).CreateMatch(sort2.get(k), r));
                }
                break;
        }
    }

    @Override
    public boolean havePlayed(Competitor opponent) {
        boolean have_played = false;
        for (int j = 0; j < mCoachs.size(); j++) {
            ArrayList<Match> matchs = mCoachs.get(j).mMatchs;
            for (int k = 0; k < ((Team) opponent).mCoachs.size(); k++) {
                for (int i = 0; i < matchs.size(); i++) {
                    Coach c = ((Team) opponent).mCoachs.get(k);
                    if ((matchs.get(i).mCompetitor1 == c) || (matchs.get(i).mCompetitor2 == c)) {
                        have_played = true;
                        break;
                    }
                }
            }
        }
        return have_played;
    }

    @Override
    public ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents, Round r) {
        return new ArrayList<>(opponents);
    }

    @Override
    public String getDecoratedName() {
        return mName;
    }

    public void AddMatchRoundRobin(Competitor c, Round r) {
        final boolean complete = (JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND ROBIN INTEGRAL (INCLUANT TOUS LES JOUEURS)?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND ROBIN INTEGRAL"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        if (!complete) {
            AddMatch(c, r);
        } else {
            Tournament tour = Tournament.getTournament();
            Parameters params = tour.getParams();
            for (int n = 0; n < params.mTeamMatesNumber; n++) {

                final ArrayList<Coach> t1players = this.getActivePlayers();
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

                    c1.AddMatch(c2, r);
                }
            }
        }
    }

    public void RoundCheck(Round round) {

        Tournament tour = Tournament.getTournament();
        ArrayList<Match> matchs = round.getMatchs();
        /*ArrayList<Team> teams1 = new ArrayList<>();
         ArrayList<Team> teams2 = new ArrayList<>();
        
         // Build Teams
         for (int i = 0; i < matchs.size(); i+=tour.getParams().mTeamMatesNumber) {
         teams1.add(matchs.get(i).mCompetitor1.mTeamMates);
         teams2.add(matchs.get(i).mCompetitor2.mTeamMates);
         }*/

        for (int i = matchs.size() - 1; i > 0; i--) {

            final Team t1 = (Team) matchs.get(i).mCompetitor1;
            final Team t2 = (Team) matchs.get(i).mCompetitor2;
            boolean have_played = t1.havePlayed(t2);

            if (have_played) {
                for (int k = i - 1; k >= 0; k--) {

                    Team t1_tmp = (Team) matchs.get(k).mCompetitor1;
                    Team t2_tmp = (Team) matchs.get(k).mCompetitor2;

                    have_played = t1.havePlayed(t2_tmp);

                    boolean canMatch = !have_played;

                    if (canMatch) {
                        //Switch Team
                        matchs.get(i).mCompetitor2 = t2_tmp;
                        matchs.get(k).mCompetitor2 = t2;

                        // Switch coachs into matchs 
                        for (int j = 0; j < tour.getParams().mTeamMatesNumber; j++) {
                            Match m = ((TeamMatch) matchs.get(i)).mMatchs.get(j);
                            Match m_tmp = ((TeamMatch) matchs.get(k)).mMatchs.get(j);
                            Coach c2_tmp = (Coach) m_tmp.mCompetitor2;
                            Coach c2 = (Coach) m.mCompetitor2;
                            m.mCompetitor2 = c2_tmp;
                            m_tmp.mCompetitor2 = c2;
                        }
                        break;
                    } else {
                        have_played = t1.havePlayed(t1_tmp);

                        canMatch = !have_played;
                        if (canMatch) {
                            //Switch Team
                            matchs.get(i).mCompetitor2 = t1_tmp;
                            matchs.get(k).mCompetitor2 = t2;

                            // Switch coachs into matchs 
                            for (int j = 0; j < tour.getParams().mTeamMatesNumber; j++) {
                                Match m = ((TeamMatch) matchs.get(i)).mMatchs.get(j);
                                Match m_tmp = ((TeamMatch) matchs.get(k)).mMatchs.get(j);
                                Coach c1_tmp = (Coach) m_tmp.mCompetitor2;
                                Coach c2 = (Coach) m.mCompetitor2;
                                m.mCompetitor2 = c1_tmp;
                                m_tmp.mCompetitor1 = c2;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public HashMap<Team, Integer> getTeamOppositionCount(ArrayList<Team> teams, Round current) {

        HashMap<Team, Integer> map = new HashMap<>();

        // Build opponents map
        for (int i = 0; i < teams.size(); i++) {
            Team t = teams.get(i);
            if (!t.mName.equals(this.mName)) {
                map.put(t, 0);
            }
        }

        // compute the number of matches between the coach team and the other teams
        // Compute the number of match per opponent
        for (int i = 0; i < mCoachs.size(); i++) {
            Coach coach = mCoachs.get(i);
            for (int j = 0; j < coach.mMatchs.size(); j++) {
                CoachMatch m = (CoachMatch) coach.mMatchs.get(j);
                Coach opp;
                if (coach.mName.equals(m.mCompetitor1.mName)) {
                    opp = (Coach) m.mCompetitor2;
                } else {
                    opp = (Coach) m.mCompetitor1;
                }

                try {
                    int nb = map.get(opp.mTeamMates);
                    nb = nb + 1;
                    map.put(opp.mTeamMates, nb);
                } catch (NullPointerException npe) {
                    System.out.println("Impossible to manage " + mName + " vs " + opp.mTeamMates.mName);
                }
            }
        }


        /// Add current Round
        if (current != null) {
            for (int i = 0; i < mCoachs.size(); i++) {
                Coach coach = mCoachs.get(i);
                for (int j = 0; j < current.mMatchs.size(); j++) {
                    CoachMatch m = (CoachMatch) current.mMatchs.get(j);
                    Coach opp = null;
                    if (coach.mName.equals(m.mCompetitor1.mName)) {
                        opp = (Coach) m.mCompetitor2;
                    }
                    if (coach.mName.equals(m.mCompetitor2.mName)) {
                        opp = (Coach) m.mCompetitor1;
                    }

                    if (opp != null) {
                        try {
                            int nb = map.get(opp.mTeamMates);
                            nb = nb + 1;
                            map.put(opp.mTeamMates, nb);
                        } catch (NullPointerException npe) {
                            System.out.println("Impossible to manage " + mName + " vs " + opp.mTeamMates.mName);
                        }
                    }
                }
            }
        }

        return map;
    }
}
