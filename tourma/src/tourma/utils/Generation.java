/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import tourma.MainFrame;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.Match;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.jdgPairing;
import tourma.jdgTeamPairing;
import tourma.tableModel.mjtRankingIndiv;
import tourma.tableModel.mjtRankingTeam;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Generation {

    public static final int GEN_ORDER = 0;
    public static final int GEN_RANDOM = 1;
    public static final int GEN_SWISS = 2;
    public static final int GEN_QSWISS = 3;
    public static final int GEN_CUP = 4;
    public static final int GEN_RROBIN = 5;
    public static final int GEN_MANUAL = 6;
    public static final int GEN_POOL = 7;

    public static void NextRound(final Round round, final int choice, final int roundnumber) {

        Round r = null;

        switch (choice) {
            case GEN_SWISS:
                r = NextRoundSwiss(round, roundnumber);
                break;
            case GEN_CUP:
                r = NextRoundCup(round, roundnumber);
                break;
            case GEN_QSWISS:
                r = NextRoundQSwiss(round, roundnumber);
                break;
            case GEN_RANDOM:
                r = NextRoundRandom(round);
                break;
            default:
        }

        if (r != null) {
            finalGenerationStep(round, r);
        }
    }

    public static void generateFirstRound(final int choice) {
        final Tournament tour = Tournament.getTournament();
        final Parameters params = tour.getParams();
        final ArrayList<Coach> coachs = tour.getCoachs();
        final ArrayList<Round> rounds = tour.getRounds();
        rounds.clear();

        final ArrayList<Team> vteams = tour.getTeams();
        tour.mRoundRobin = false;

        if (params.mTeamTournament) {
            /**
             * First check the number of active players
             */
            for (int i = 0; i < vteams.size(); i++) {
                if (vteams.get(i).getActivePlayerNumber() != params.mTeamMatesNumber) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Mauvais nombre de joueurs actif pour l'équipe " + vteams.get(i).mName);
                    return;
                }
            }
        } else {
            final int nb_coach = tour.GetActiveCoachNumber();
            if (nb_coach % 2 > 0) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre impair de coach actifs", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
            }
        }

        for (int i = 0; i < coachs.size(); i++) {
            coachs.get(i).mMatchs = new ArrayList<Match>();
        }


        switch (choice) {
            // Manual Choice
            case GEN_MANUAL:
                if (!params.mTeamTournament) {
                    generateIndividualFirstRoundManual();
                } else {
                    generateTeamFirstRoundManual();
                }
                break;
            // Toute Ronde
            case GEN_RROBIN:
                if (!params.mTeamTournament) {
                    generateIndividualFirstRoundRobin();
                } else {
                    generateTeamFirstRoundRobin();
                }
                break;
            case GEN_RANDOM:
                if (!params.mTeamTournament) {
                    generateIndividualFirstRoundOrder(true);
                } else {
                    generateTeamFirstRoundOrder(true);
                }
                break;
            case GEN_ORDER:
                if (!params.mTeamTournament) {
                    generateIndividualFirstRoundOrder(false);
                } else {
                    generateTeamFirstRoundOrder(false);
                }
                break;
            case GEN_POOL:
                generateFirstRoundPool(params.mTeamTournament);
                break;
            case GEN_CUP:
                if (!params.mTeamTournament) {
                    generateIndividualFirstRoundCup();
                } else {
                    generateTeamFirstRoundCup();
                }
                break;
            default:
                break;
        }


        if (rounds.size() > 0) {
            for (int k = 0; k < rounds.size(); k++) {
                for (int i = 0; i < rounds.get(k).getMatchs().size(); i++) {
                    final Match m = rounds.get(k).getMatchs().get(i);
                    m.mCoach1.mMatchs.add(m);
                    m.mCoach2.mMatchs.add(m);

                    for (int j = 0; j < params.mCriterias.size(); j++) {
                        final Criteria criteria = params.mCriterias.get(j);
                        final Value val = new Value(criteria);
                        if (j == 0) {
                            val.mValue1 = -1;
                            val.mValue2 = -1;
                        }
                        m.mValues.put(criteria, val);
                    }
                }
            }
        }

        StringBuffer filename = new StringBuffer(Tournament.getTournament().getParams().mTournamentName);
        filename.append(".");
        filename.append(Tournament.getTournament().getRounds().size());
        final Date date = new Date();
        filename.append(".");
        filename.append(Long.toString(date.getTime()));
        filename.append(".xml");
        final File file = new File(filename.toString());

        Tournament.getTournament().saveXML(file, false);
    }

    protected static void generateFirstRoundPool(final boolean byTeam) {

        final Tournament tour = Tournament.getTournament();
        final ArrayList<Round> rounds = tour.getRounds();

        if (byTeam) {
            TeamCreatePools();
        } else {
            IndivCreatePools();
        }

        Round r;
        final boolean random = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Generation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        if (random) {
            r = NextRoundRandom(null);
        } else {
            r = NextRoundSwiss(null, -1);
        }

        rounds.add(r);
    }

    protected static void IndivCreatePools() {

        final JPanel message = new JPanel();
        message.setLayout(new BorderLayout());

        final Tournament tour = Tournament.getTournament();
        final ArrayList<Pool> pools = tour.getPools();
        final ArrayList<Coach> vcoachs = tour.getCoachs();


        final JLabel jlb = new JLabel("Nombre de joueur par Poule: ");

        final JSpinner jspNbPlayers = new JSpinner();
        final SpinnerNumberModel model = new SpinnerNumberModel(4, 2, tour.GetActiveCoachNumber() / 2, 2);
        jspNbPlayers.setModel(model);

        message.add(jlb, BorderLayout.NORTH);
        message.add(jspNbPlayers, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), message, StringConstants.CS_POOL, JOptionPane.QUESTION_MESSAGE);

        final int nbPlayers = (Integer) model.getValue();

        if (tour.GetActiveCoachNumber() % nbPlayers != 0) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Le nombre de joueur n'est pas un multiple de votre choix", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }


        final int nbPools = tour.GetActiveCoachNumber() / nbPlayers;
        for (int i = 0; i < nbPools; i++) {
            final Pool p = new Pool();
            pools.add(p);
            p.mName = Integer.toString(i + 1);
        }

        int nbCounter = nbPlayers;

        int response = JOptionPane.YES_OPTION;
        final ArrayList<Coach> coachs = new ArrayList<Coach>(tour.GetActiveCoaches());
        while (nbCounter > 0) {

            if (response == JOptionPane.YES_OPTION) {
                response = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Voulez vous choisir le coach du rang " + (nbPlayers - nbCounter + 1) + " ?", StringConstants.CS_POOL, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            }
            if (response == JOptionPane.YES_OPTION) {
                for (int i = 0; i < nbPools; i++) {
                    final ArrayList<String> names = new ArrayList<String>();
                    for (int j = 0; j < coachs.size(); j++) {
                        names.add(coachs.get(j).mName);
                    }
                    final int index = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), "Choisissez un coach", StringConstants.CS_POOL, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, names.toArray(), 0);
                    pools.get(i).mCoachs.add(coachs.get(index));
                    coachs.remove(index);
                }
            } else {
                for (int i = 0; i < nbPools; i++) {
                    final int index = ((int) Math.random()) % vcoachs.size();
                    pools.get(i).mCoachs.add(coachs.get(index));
                    coachs.remove(index);
                }
            }
            nbCounter--;
        }
    }

    protected static void generateIndividualFirstRoundManual() {

        final Tournament tour = Tournament.getTournament();

        final Parameters params = tour.getParams();
        final ArrayList<Clan> clans = tour.getClans();
        final ArrayList<Round> rounds = tour.getRounds();

        tour.mRoundRobin = false;
        final ArrayList<Coach> coachs = new ArrayList<Coach>(tour.GetActiveCoaches());
        final Round r = new Round();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        while (coachs.size() > 0) {
            /*
             * Choisir un coach pour l'adversaire.
             */
            final Match m = new Match();
            m.mCoach1 = coachs.get(0);
            coachs.remove(m.mCoach1);
            ArrayList<Coach> possibleCoachs = new ArrayList(coachs);

            if (m.mCoach1.mClan != clans.get(0)) {
                if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
                    for (int i = 0; i
                            < possibleCoachs.size(); i++) {
                        if (possibleCoachs.get(i).mClan.mName.equals(m.mCoach1.mClan.mName)) {
                            possibleCoachs.remove(i);
                            i--;
                        }
                    }
                }
                if (possibleCoachs.isEmpty()) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OnlyOneClanCoachKey"));
                    possibleCoachs = coachs;
                }
            }

            Object[] possibilities = new Object[possibleCoachs.size()];
            for (int i = 0; i
                    < possibleCoachs.size(); i++) {
                String tmpString = possibleCoachs.get(i).mName + " (" + possibleCoachs.get(i).mRoster.mName + ")";
                if (params.mEnableClans) {
                    tmpString = tmpString + " (" + possibleCoachs.get(i).mClan.mName + ")";
                }
                possibilities[i] = tmpString;
            }
            final ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);
            String coachString = m.mCoach1.mName + " (" + m.mCoach1.mRoster.mName + ")";
            if (params.mEnableClans) {
                coachString = coachString + " (" + m.mCoach1.mClan.mName + ")";
            }

            String opp = null;

            while (opp == null) {
                opp = (String) JOptionPane.showInputDialog(
                        MainFrame.getMainFrame(),
                        bundle.getString("ChooseOpponentFor")
                        + coachString,
                        bundle.getString("ChoosOpponent"),
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]);
            }

            for (int i = 0; i
                    < coachs.size(); i++) {
                String tmpString = possibleCoachs.get(i).mName + " (" + possibleCoachs.get(i).mRoster.mName + ")";
                if (params.mEnableClans) {
                    tmpString = tmpString + " (" + possibleCoachs.get(i).mClan.mName + ")";
                }

                if (opp.equals(tmpString)) {
                    m.mCoach2 = possibleCoachs.get(i);
                    coachs.remove(m.mCoach2);
                    break;
                }
            }
            r.getMatchs().add(m);
        }
        rounds.add(r);
    }

    protected static void generateIndividualFirstRoundOrder(final boolean random) {

        final Tournament tour = Tournament.getTournament();

        final Parameters params = tour.getParams();
        final ArrayList<Clan> clans = tour.getClans();
        final ArrayList<Round> rounds = tour.getRounds();

        tour.mRoundRobin = false;
        final Round r = new Round();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());

        boolean NotOK = true;
        int counter = 0;

        while ((NotOK) && (counter < 500)) {

            NotOK = false;
            final ArrayList<Coach> shuffle = new ArrayList<Coach>(tour.GetActiveCoaches());
            if (random) /* Aléatoire */ {
                Collections.shuffle(shuffle);
            }

            r.getMatchs().clear();

            while (shuffle.size() > 0) {
                final Match m = new Match();
                m.mCoach1 = shuffle.get(0);
                shuffle.remove(m.mCoach1);
                if (m.mCoach1.mClan != clans.get(0)) {
                    if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
                        int j = 0;
                        while (j < shuffle.size() && (m.mCoach1.mClan == shuffle.get(j).mClan)) {
                            j++;
                        }
                        if (j == shuffle.size()) {
                            NotOK = true;
                            j = 0;
                        }
                        m.mCoach2 = shuffle.get(j);
                        shuffle.remove(j);
                    } else {
                        m.mCoach2 = shuffle.get(0);
                        shuffle.remove(0);
                    }
                } else {
                    m.mCoach2 = shuffle.get(0);
                    shuffle.remove(0);
                }
                r.getMatchs().add(m);
            }

            counter++;
        }

        /*
         * Check the clans
         *
         */
        boolean bSameClan = false;
        for (int i = 0; i < r.getMatchs().size(); i++) {
            final Match m = r.getMatchs().get(i);
            if (m.mCoach1.mClan != clans.get(0)) {
                if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
                    if (m.mCoach1.mClan == m.mCoach2.mClan) {
                        bSameClan = true;
                        break;
                    }
                }
            }
        }

        if (bSameClan) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OnlyOneClanCoachKey"));
        }


        if (counter == 500) {
            if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
                final ArrayList<Coach> shuffle = new ArrayList<Coach>(tour.GetActiveCoaches());
                if (random) /* Aléatoire */ {
                    Collections.shuffle(shuffle);
                }

                r.getMatchs().clear();

                while (shuffle.size() > 0) {
                    final Match m = new Match();
                    m.mCoach1 = shuffle.get(0);
                    shuffle.remove(m.mCoach1);
                    if (m.mCoach1.mClan != clans.get(0)) {
                        if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
                            int j = 0;
                            while (j < shuffle.size() && (m.mCoach1.mClan == shuffle.get(j).mClan)) {
                                j++;
                            }
                            if (j == shuffle.size()) {

                                j = 0;
                            }
                            m.mCoach2 = shuffle.get(j);
                            shuffle.remove(j);
                        } else {
                            m.mCoach2 = shuffle.get(0);
                            shuffle.remove(0);
                        }
                    } else {
                        m.mCoach2 = shuffle.get(0);
                        shuffle.remove(0);
                    }
                    r.getMatchs().add(m);
                }
            } else {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("CanMatchKey"));
                final ArrayList<Coach> shuffle = new ArrayList<Coach>(tour.GetActiveCoaches());
                if (random) /* Aléatoire */ {
                    Collections.shuffle(shuffle);
                }
                for (int i = 0; i
                        < shuffle.size() / 2; i++) {
                    final Match m = new Match();
                    m.mCoach1 = shuffle.get(2 * i);
                    m.mCoach2 = shuffle.get(2 * i + 1);
                    r.getMatchs().add(m);
                }
            }
        }
        rounds.add(r);
    }

    protected static void generateIndividualFirstRoundRobin() {


        final Tournament tour = Tournament.getTournament();

        final ArrayList<Round> rounds = tour.getRounds();

        tour.mRoundRobin = false;
        final Calendar cal = Calendar.getInstance();

        final boolean home_away = (JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Match Aller-Retour ?", "Aller-Retour", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        final ArrayList<Coach> coachs = tour.GetActiveCoaches();
        Collections.shuffle(coachs);

        final ArrayList<Coach> c1part = new ArrayList<Coach>();
        final ArrayList<Coach> c2part = new ArrayList<Coach>();

        for (int i = 0; i < coachs.size() / 2; i++) {
            c1part.add(coachs.get(2 * i));
            c2part.add(coachs.get(2 * i + 1));
        }

        int nbrounds = coachs.size() - 1;
        if (home_away) {
            nbrounds = 2 * nbrounds;
        }

        for (int i = 0; i < nbrounds; i++) {
            final Round r = new Round();
            r.setHour(cal.getTime());

            for (int j = 0; j < c2part.size(); j++) {
                final Match m = new Match();
                m.mCoach1 = c1part.get(j);
                m.mCoach2 = c2part.get(j);
                r.getMatchs().add(m);
            }

            // Move coaches for round robin / ribbon method

            Coach c_tmp = c2part.get(0);
            c2part.remove(c_tmp);
            c1part.add(1, c_tmp);
            c_tmp = c1part.get(c1part.size() - 1);
            c1part.remove(c1part.size() - 1);
            c2part.add(c_tmp);

            rounds.add(r);
        }
    }

    protected static void generateIndividualFirstRoundCup() {

        final Tournament tour = Tournament.getTournament();

        final ArrayList<Round> rounds = tour.getRounds();

        // Analyze number of players
        final int nb_coachs = tour.GetActiveCoachNumber();
        final ArrayList<Coach> coachs = new ArrayList<Coach>(tour.GetActiveCoaches());
        Collections.shuffle(coachs);
        int nb_tmp = 1;
        int nbrounds = 0;
        while (nb_tmp < nb_coachs) {
            nb_tmp *= 2;
            nbrounds++;
        }

        final Round r = new Round();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());

        r.mLooserCup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Sagit-il d'un tournoi à double élimination ?", StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        // there is nb_tmp/matchs
        for (int i = 0; i < nb_tmp / 2; i++) {
            final Match m = new Match();
            m.mCoach1 = coachs.get(i);
            m.mCoach2 = Coach.sNullCoach;
            if (nb_tmp / 2 + i < nb_coachs) {
                m.mCoach2 = coachs.get(i + nb_tmp / 2);
            }
            r.getMatchs().add(m);
        }

        // Comme les matchs sans adversaires sont à la fin, on réordonne pour les faire glisser
        for (int i = 0; i < r.getMatchs().size() / 2; i++) {
            if (i % 2 == 0) {
                final Match m1 = r.getMatchs().get(i);
                final Match m2 = r.getMatchs().get(r.getMatchs().size() - 1 - i);
                r.getMatchs().set(i, m2);
                r.getMatchs().set(r.getMatchs().size() - 1 - i, m1);
            }
        }


        r.mCup = true;
        r.mCupMaxTour = nbrounds;
        r.mCupTour = 0;
        //r.mLooserCup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Sagit-il d'un tournoi à double élimination ?", StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        rounds.add(r);
    }

    /*protected static void generateIndividualFirstRound(int choice) {

     Tournament tour = Tournament.getTournament();
     tour.mRoundRobin = false;

     int nb_coach = tour.GetActiveCoachNumber();
     if (nb_coach % 2 > 0) {
     JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre impair de coach actifs", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
     } else {
     switch (choice) {
     // Manual Choice
     case GEN_MANUAL:
     generateIndividualFirstRoundManual();
     break;
     // Toute Ronde
     case GEN_RROBIN:
     generateIndividualFirstRoundRobin();
     break;
     case GEN_RANDOM:
     generateIndividualFirstRoundOrder(true);
     break;
     case GEN_ORDER:
     generateIndividualFirstRoundOrder(false);
     break;
     case GEN_POOL:
     IndivCreatePools();
     break;
     case GEN_CUP:
     generateIndividualFirstRoundCup();
     //NextRoundCup(null, -1);
     break;
     default:
     break;
     }
     }
     }*/
    protected static void generateTeamFirstRoundOrder(final boolean random) {

        final Tournament tour = Tournament.getTournament();

        final ArrayList<Team> teams = tour.getTeams();
        final ArrayList<Round> rounds = tour.getRounds();

        tour.mRoundRobin = false;
        final Calendar cal = Calendar.getInstance();

        final Round r = new Round();
        r.setHour(cal.getTime());

        final ArrayList<Team> teams1 = new ArrayList<Team>();
        final ArrayList<Team> teams2 = new ArrayList<Team>();
        /*
         * Le premier appariement est aléatoire ByTeam
         */
        final ArrayList<Team> shuffle = new ArrayList<Team>(teams);
        if (random) /* Aléatoire */ {
            Collections.shuffle(shuffle);
        }
        for (int i = 0; i
                < shuffle.size() / 2; i++) {
            final Team team1 = shuffle.get(2 * i);
            final Team team2 = shuffle.get(2 * i + 1);
            teams1.add(team1);
            teams2.add(team2);
        }

        TeamIndivPairing(teams1, teams2, r, JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation des joueurs aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Affecation des joueurs", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        rounds.add(r);
    }

    protected static void TeamIndivPairing(final ArrayList<Team> teams1, final ArrayList<Team> teams2, final Round r, boolean random) {

        final Tournament tour = Tournament.getTournament();
        final Parameters params = tour.getParams();

        if (params.mTeamIndivPairing == 1) {
            final jdgTeamPairing jdg = new jdgTeamPairing(MainFrame.getMainFrame(), true, teams1, teams2, r);
            jdg.setVisible(true);
        } else {
            if (params.mTeamIndivPairing == 2) {
                random = true;
            }
            for (int i = 0; i
                    < teams1.size(); i++) {
                final Team team1 = teams1.get(i);
                final Team team2 = teams2.get(i);
                final ArrayList<Coach> coachs1 = team1.getActivePlayers();
                final ArrayList<Coach> shuffle2 = new ArrayList<Coach>(team2.getActivePlayers());

                if (random) /* Aléatoire */ {
                    Collections.shuffle(shuffle2);
                }
                for (int j = 0; j < shuffle2.size(); j++) {
                    final Match m = new Match();
                    m.mCoach1 = coachs1.get(j);
                    m.mCoach2 = shuffle2.get(j);
                    r.getMatchs().add(m);
                }
            }
        }
    }

    protected static void generateTeamFirstRoundRobin() {


        final Tournament tour = Tournament.getTournament();

        final Parameters params = tour.getParams();
        final ArrayList<Team> teams = tour.getTeams();
        final ArrayList<Round> rounds = tour.getRounds();

        final boolean complete = (JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Round Robin integral (incluant tous les joueurs)?", "Round Robin integral", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        final boolean home_away = (JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Match Aller-Retour ?", "Aller-Retour", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        final boolean random = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation des joueurs aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Affecation des joueurs", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        if (random) {
            Collections.shuffle(teams);
        }
        tour.mRoundRobin = true;


        final ArrayList<Team> t1part = new ArrayList<Team>();
        final ArrayList<Team> t2part = new ArrayList<Team>();

        for (int i = 0; i < teams.size() / 2; i++) {
            t1part.add(teams.get(2 * i));
            t2part.add(teams.get(2 * i + 1));
        }


        int nb_teamrounds = teams.size() - 1;
        if (home_away) {
            nb_teamrounds = 2 * nb_teamrounds;
        }

        for (int i = 0; i < nb_teamrounds; i++) {

            final ArrayList<Team> teams1 = t1part;
            final ArrayList<Team> teams2 = t2part;


            if (!complete) {
                final Round r = new Round();
                final Calendar cal = Calendar.getInstance();
                r.setHour(cal.getTime());

                TeamIndivPairing(teams1, teams2, r, random);

                rounds.add(r);
            } else {

                for (int n = 0; n < params.mTeamMatesNumber; n++) {

                    final Round r = new Round();
                    final Calendar cal = Calendar.getInstance();
                    r.setHour(cal.getTime());

                    for (int k = 0; k < teams1.size(); k++) {
                        final Team team1 = teams1.get(k);
                        final Team team2 = teams2.get(k);

                        final ArrayList<Coach> t1players = team1.getActivePlayers();
                        final ArrayList<Coach> t2players = team2.getActivePlayers();

                        // Arrange player list using ribbon method
                        for (int p = 0; p < n; p++) {
                            final Coach c_tmp = t2players.get(0);
                            t2players.remove(0);
                            t2players.add(c_tmp);
                        }

                        for (int l = 0; l < t1players.size(); l++) {

                            final Coach c1 = t1players.get(l);
                            final Coach c2 = t2players.get(l);

                            final Match m = new Match();
                            m.mCoach1 = c1;
                            m.mCoach2 = c2;

                            r.getMatchs().add(m);
                        }
                    }
                    rounds.add(r);

                }

            }

            // Rolls teams with ribbon method
            Team t_tmp = t2part.get(0);
            t2part.remove(0);
            t1part.add(1, t_tmp);
            t_tmp = t1part.get(t1part.size() - 1);
            t1part.remove(t1part.size() - 1);
            t2part.add(t_tmp);
        }

    }

    protected static void generateTeamFirstRoundCup() {

        final Tournament tour = Tournament.getTournament();

        ArrayList<Team> teams = tour.getTeams();
        final ArrayList<Round> rounds = tour.getRounds();

        // Analyze number of players
        final int nb_teams = teams.size();
        teams = new ArrayList<Team>(teams);
        Collections.shuffle(teams);
        int nb_tmp = 1;
        int nbrounds = 0;
        while (nb_tmp < nb_teams) {
            nb_tmp *= 2;
            nbrounds++;
        }

        final Round r = new Round();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());

        r.mLooserCup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Sagit-il d'un tournoi à double élimination ?", StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        final ArrayList<Team> teams1 = new ArrayList<Team>();
        final ArrayList<Team> teams2 = new ArrayList<Team>();

        for (int i = 0; i < nb_tmp / 2; i++) {
            teams1.add(teams.get(i));
            if (nb_tmp / 2 + i < nb_teams) {
                teams2.add(teams.get(i + nb_tmp / 2));
            } else {
                teams2.add(Team.sNullTeam);
            }
        }

        // Comme les matchs sans adversaires sont à la fin, on réordonne pour les faire glisser
        for (int i = 0; i < teams1.size() / 2; i++) {
            if (i % 2 == 0) {
                final Team t1 = teams1.get(i);
                final Team t2 = teams2.get(teams1.size() - 1 - i);
                teams1.set(i, t2);
                teams2.set(teams1.size() - 1 - i, t1);
            }
        }

        TeamIndivPairing(teams1, teams2, r, JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation des joueurs aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Affecation des joueurs", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        r.mCup = true;
        r.mCupMaxTour = nbrounds - 1;
        r.mCupTour = 0;

        rounds.add(r);
    }

    protected static void generateTeamFirstRoundManual() {

        final Tournament tour = Tournament.getTournament();

        final ArrayList<Team> vteams = tour.getTeams();
        final ArrayList<Round> rounds = tour.getRounds();

        tour.mRoundRobin = false;

        final Round r = new Round();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());


        final ArrayList<Team> teams1 = new ArrayList<Team>();
        final ArrayList<Team> teams2 = new ArrayList<Team>();

        final ArrayList<Team> teams = new ArrayList<Team>(vteams);
        while (teams.size() > 0) {

            final Team team1 = teams.get(0);
            teams1.add(team1);
            teams.remove(team1);

            Object[] possibilities = new Object[teams.size()];
            for (int i = 0; i
                    < teams.size(); i++) {
                possibilities[i] = teams.get(i).mName;
            }

            final String opp = (String) JOptionPane.showInputDialog(
                    MainFrame.getMainFrame(),
                    java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChooseOpponentFor") + ": "
                    + team1.mName,
                    java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChooseOpponent"),
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    possibilities[0]);
            for (int i = 0; i < teams.size(); i++) {
                if (opp.equals(teams.get(i).mName)) {
                    final Team team2 = teams.get(i);
                    teams2.add(team2);
                    teams.remove(team2);
                    break;
                }
            }
        }

        TeamIndivPairing(teams1, teams2, r, JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation des joueurs aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Affecation des joueurs", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        rounds.add(r);
    }

    protected static void TeamCreatePools() {

        final Tournament tour = Tournament.getTournament();

        final ArrayList<Coach> vcoachs = tour.getCoachs();
        final ArrayList<Team> vteams = tour.getTeams();
        final ArrayList<Round> rounds = tour.getRounds();
        final ArrayList<Pool> pools = tour.getPools();

        final JPanel message = new JPanel();
        message.setLayout(new BorderLayout());

        final JLabel jlb = new JLabel("Nombre d'équipes par Poule: ");

        final JSpinner jspNbTeams = new JSpinner();
        final SpinnerNumberModel model = new SpinnerNumberModel(4, 2, vteams.size() / 2, 2);
        jspNbTeams.setModel(model);

        message.add(jlb, BorderLayout.NORTH);
        message.add(jspNbTeams, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), message, StringConstants.CS_POOL, JOptionPane.QUESTION_MESSAGE);

        final int nbTeams = (Integer) model.getValue();

        if (vteams.size() % nbTeams != 0) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Le nombre dd'équipe n'est pas un multiple de votre choix", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }


        final int nbPools = vteams.size() / nbTeams;
        for (int i = 0; i < nbPools; i++) {
            final Pool p = new Pool();
            pools.add(p);
            p.mName = Integer.toString(i + 1);
        }

        int nbCounter = nbTeams;

        int response = JOptionPane.YES_OPTION;
        final ArrayList<Team> teams = new ArrayList<Team>(vteams);
        while (nbCounter > 0) {

            if (response == JOptionPane.YES_OPTION) {
                response = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Voulez vous choisir l'équipe du rang " + (nbTeams - nbCounter + 1) + " ?", StringConstants.CS_POOL, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            }
            if (response == JOptionPane.YES_OPTION) {
                for (int i = 0; i < nbPools; i++) {
                    final ArrayList<String> names = new ArrayList<String>();
                    for (int j = 0; j < vteams.size(); j++) {
                        names.add(vteams.get(j).mName);
                    }
                    final int index = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), "Choisissez un coach", StringConstants.CS_POOL, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, names.toArray(), 0);
                    pools.get(i).mTeams.add(teams.get(index));
                    teams.remove(index);
                }
            } else {
                for (int i = 0; i < nbPools; i++) {
                    final int index = ((int) Math.random()) % vcoachs.size();
                    pools.get(i).mTeams.add(teams.get(index));
                    teams.remove(index);
                }
            }
            nbCounter--;
        }

        Round r;
        final boolean random = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Generation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        if (random) {
            r = NextRoundRandom(null);
        } else {
            r = NextRoundSwiss(null, -1);
        }

        rounds.add(r);
    }

    /*protected static void generateTeamFirstRound(int choice) {

     Tournament tour = Tournament.getTournament();

     Parameters params = tour.getParams();
     ArrayList<Team> vteams = tour.getTeams();

     tour.mRoundRobin = false;
     for (int i = 0; i < vteams.size(); i++) {
     if (vteams.get(i).getActivePlayerNumber() != params.mTeamMatesNumber) {
     JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Mauvais nombre de joueurs actif pour l'équipe " + vteams.get(i).mName);
     return;
     }
     }

     switch (choice) {
     // Manual
     case GEN_MANUAL:
     generateTeamFirstRoundManual();
     break;
     case GEN_RANDOM:
     generateTeamFirstRoundOrder(true);
     break;
     case GEN_ORDER:
     generateTeamFirstRoundOrder(false);
     break;
     case GEN_RROBIN:
     generateTeamFirstRoundRobin();
     case GEN_POOL:
     TeamCreatePools();
     break;
     case GEN_CUP:
     generateTeamFirstRoundCup();
     break;
     default:
     break;
     }
     }*/
    protected static void TeamCupAffectation(final Round r, final ArrayList<ObjectRanking> datas, final int nbTeams) {
        final int nb_match = nbTeams / 2;
        final ArrayList<Team> teams = new ArrayList<Team>();
        final ArrayList<Team> teams1 = new ArrayList<Team>();
        final ArrayList<Team> teams2 = new ArrayList<Team>();
        for (int i = 0; i < nbTeams; i++) {
            Team t;
            if (i < datas.size()) {
                t = ((Team) ((ObjectRanking) datas.get(i)).getObject());
            } else {
                t = Team.sNullTeam;
            }
            teams.add(t);
        }
        for (int i = 0; i < nb_match / 2; i++) {
            teams1.add(teams.get(2 * i));
            teams2.add(teams.get(nbTeams - 2 * i - 1));
        }
        for (int i = 0; i < nb_match / 2; i++) {
            teams1.add(teams.get(2 * i + 1));
            teams2.add(teams.get(nbTeams - 2 * i - 2));
        }
        TeamIndivPairing(teams1, teams2, r, JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation des joueurs al\u00e9atoite (sinon, l'order d'inscription sera utilis\u00e9e) ?", "Affecation des joueurs", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }

    protected static void TeamCup(final Round round, final Round r, final boolean third_place) {
        final Tournament tour = Tournament.getTournament();
        final ArrayList<Match> matchs = new ArrayList<Match>(round.getMatchs());
        int nb_match = (int) Math.pow(2, round.mCupMaxTour - round.mCupTour - 1);
        final ArrayList<Team> _winners = new ArrayList<Team>();
        final ArrayList<Team> _loosers = new ArrayList<Team>();
        for (int i = 0; i < nb_match; i++) {
            final Team t = Match.getTeamMatchWinner(tour.getParams().mTeamMatesNumber, i, matchs);
            final Match m = matchs.get(i * tour.getParams().mTeamMatesNumber);
            final Team team1 = m.mCoach1.mTeamMates;
            final Team team2 = m.mCoach2.mTeamMates;
            if (team1 == t) {
                _winners.add(team1);
                _loosers.add(team2);
            } else {
                _winners.add(team2);
                _loosers.add(team1);
            }
        }
        if (nb_match == 0) {
            if (round.mCupTour == round.mCupMaxTour + 1) {
                _loosers.add(Match.getTeamMatchWinner(tour.getParams().mTeamMatesNumber, 0, matchs));
            } else {
                _winners.add(Match.getTeamMatchWinner(tour.getParams().mTeamMatesNumber, 0, matchs));
                _loosers.add(Match.getTeamMatchWinner(tour.getParams().mTeamMatesNumber, 1, matchs));
            }
        }
        final int option = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Voulez vous m\u00e9langer le(s) tableau(x) ?", StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            Collections.shuffle(_winners);
            Collections.shuffle(_loosers);
        }
        for (int i = 0; i < (_winners.size() + 1) / 2; i++) {
            final Team team1 = _winners.get(2 * i);
            Team team2;
            if ((2 * i + 1) >= _winners.size()) {
                team2 = Team.sNullTeam;
            } else {
                team2 = _winners.get(2 * i + 1);
            }
            affectTeamCoachs(round, team1, team2, r);
        }
        if (third_place) {
            if (_loosers.size() == 2) {
                for (int i = 0; i < (_loosers.size() + 1) / 2; i++) {
                    for (int j = 0; j < tour.getParams().mTeamMatesNumber; j++) {
                        if ((2 * i + 1) >= _loosers.size()) {
                            affectTeamCoachs(round, _loosers.get(2 * i), Team.sNullTeam, r);
                        } else {
                            affectTeamCoachs(round, _loosers.get(2 * i), _loosers.get(2 * i + 1), r);
                        }
                    }
                }
            }
        }
        if (round.mLooserCup) {
            int nb_remaining_match = 0;
            if (round.mCupTour > 0) {
                nb_remaining_match = nb_match;
            }
            if (nb_match == 1) {
                final Team t = _loosers.get(0);
                affectTeamCoachs(round, t, Team.sNullTeam, r);
                _loosers.remove(0);
                nb_remaining_match = 2;
            }
            if (nb_match == 0) {
                nb_remaining_match = 1;
                if (round.mCupTour == round.mCupMaxTour + 1) {
                    nb_match = 1;
                } else {
                    nb_match = 2;
                }
            }
            for (int i = nb_match; (i < nb_match + nb_remaining_match) && (i < matchs.size()); i++) {
                _loosers.add(Match.getTeamMatchWinner(tour.getParams().mTeamMatesNumber, i, matchs));
            }
            for (int i = 0; i < _loosers.size() / 2; i++) {
                if ((2 * i + 1) >= _loosers.size()) {
                    affectTeamCoachs(round, _loosers.get(i), Team.sNullTeam, r);
                } else {
                    affectTeamCoachs(round, _loosers.get(i + _loosers.size() / 2), Team.sNullTeam, r);
                }
            }
        }
    }

    protected static Round IndivSwiss(final ArrayList<Coach> coachs, final ArrayList<Match> matchs, final ArrayList<ObjectRanking> datas, final boolean team, final Round r) {

        final Tournament tour = Tournament.getTournament();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        final ArrayList<ObjectRanking> datas_tmp = new ArrayList<ObjectRanking>(datas);
        for (int i = 0; i < coachs.size(); i++) {
            final Coach c = coachs.get(i);
            if (!c.mActive) {
                for (int j = 0; j < datas_tmp.size(); j++) {
                    if (datas_tmp.get(j).getObject().equals(c)) {
                        datas_tmp.remove(j);
                    }
                }
            }
        }
        if (tour.GetActiveCoaches().size() - 1 <= tour.getRounds().size()) {
            for (int i = 0; i < datas_tmp.size() / 2; i++) {
                final Match m = new Match();
                m.mCoach1 = (Coach) datas_tmp.get(2 * i).getObject();
                m.mCoach2 = (Coach) datas_tmp.get(2 * i + 1).getObject();
                r.getMatchs().add(m);
            }
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NotEnoughRoundToAvoidSameMatch"));
        } else {
            ArrayList<ObjectRanking> datas2 = new ArrayList<ObjectRanking>(datas_tmp);
            while (datas2.size() > 0) {
                final Match m = new Match();
                m.mCoach1 = (Coach) datas2.get(0).getObject();
                datas2.remove(0);
                for (int i = 0; i < datas2.size(); i++) {
                    boolean have_played = false;
                    for (int j = 0; j < matchs.size(); j++) {
                        if (((matchs.get(j).mCoach1 == m.mCoach1) && (matchs.get(j).mCoach2 == datas2.get(i).getObject())) || ((matchs.get(j).mCoach2 == m.mCoach1) && (matchs.get(j).mCoach1 == datas2.get(i).getObject()))) {
                            have_played = true;
                        }
                    }
                    if ((team) && (!have_played)) {
                        for (int j = 0; j < m.mCoach1.mTeamMates.mCoachs.size(); j++) {
                            if (m.mCoach1.mTeamMates.mCoachs.get(j) == datas2.get(i).getObject()) {
                                have_played = true;
                            }
                        }
                    }
                    boolean canMatch = !have_played;
                    if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
                        if (!m.mCoach1.mClan.mName.equals("Aucun")) {
                            if (m.mCoach1.mClan == ((Coach) datas2.get(i).getObject()).mClan) {
                                canMatch = false;
                            }
                        }
                    }
                    if ((canMatch) || (i == datas2.size() - 1)) {
                        m.mCoach2 = (Coach) datas2.get(i).getObject();
                        datas2.remove(i);
                        break;
                    }
                }
                r.getMatchs().add(m);
            }
            datas2 = new ArrayList<ObjectRanking>(datas_tmp);
            for (int i = r.getMatchs().size() - 1; i > 0; i--) {
                boolean have_played = false;
                final Coach c1 = r.getMatchs().get(i).mCoach1;
                final Coach c2 = r.getMatchs().get(i).mCoach2;
                for (int j = 0; j < matchs.size(); j++) {
                    if (((matchs.get(j).mCoach1 == c1) && (matchs.get(j).mCoach2 == c2)) || ((matchs.get(j).mCoach1 == c2) && (matchs.get(j).mCoach2 == c1))) {
                        have_played = true;
                    }
                }
                if (have_played) {
                    for (int k = i - 1; k >= 0; k--) {
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;
                            if (((matchs.get(j).mCoach1 == c2) && (matchs.get(j).mCoach2 == r.getMatchs().get(k).mCoach2)) || ((matchs.get(j).mCoach2 == c2) && (matchs.get(j).mCoach1 == r.getMatchs().get(k).mCoach2))) {
                                have_played = true;
                                break;
                            }
                        }
                        boolean canMatch = !have_played;
                        if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
                            if (!r.getMatchs().get(i).mCoach2.mClan.mName.equals("Aucun")) {
                                if (r.getMatchs().get(i).mCoach2.mClan == r.getMatchs().get(k).mCoach2.mClan) {
                                    canMatch = false;
                                }
                            }
                        }
                        if (canMatch) {
                            r.getMatchs().get(i).mCoach1 = r.getMatchs().get(k).mCoach2;
                            r.getMatchs().get(k).mCoach2 = c1;
                            break;
                        }
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;
                            if (((matchs.get(j).mCoach1 == c2) && (matchs.get(j).mCoach2 == r.getMatchs().get(k).mCoach1)) || ((matchs.get(j).mCoach2 == c2) && (matchs.get(j).mCoach1 == r.getMatchs().get(k).mCoach1))) {
                                have_played = true;
                                break;
                            }
                        }
                        canMatch = !have_played;
                        if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
                            if (!r.getMatchs().get(i).mCoach1.mClan.mName.equals("Aucun")) {
                                if (r.getMatchs().get(i).mCoach2.mClan == r.getMatchs().get(k).mCoach1.mClan) {
                                    canMatch = false;
                                }
                            }
                        }
                        if (canMatch) {
                            r.getMatchs().get(i).mCoach1 = r.getMatchs().get(k).mCoach1;
                            r.getMatchs().get(k).mCoach1 = c1;
                            break;
                        }
                    }
                    for (int k = 0; k < datas2.size(); k++) {
                        if ((datas2.get(k).getObject() == c1) || (datas2.get(k).getObject() == c2)) {
                            datas2.remove(k);
                            k--;
                        }
                    }
                } else {
                    for (int k = 0; k < datas2.size(); k++) {
                        if ((datas2.get(k).getObject() == c1) || (datas2.get(k).getObject() == c2)) {
                            datas2.remove(k);
                            k--;
                        }
                    }
                }
            }
        }
        return r;
    }

    protected static void affectTeamCoachs(final Round round, final Team team1, final Team team2, final Round r) {
        final Tournament tour = Tournament.getTournament();
        final ArrayList<Round> vs = new ArrayList<Round>();
        for (int i = 0; i < tour.getRounds().size(); i++) {
            if (tour.getRounds().get(i).getHour().before(round.getHour())) {
                vs.add(tour.getRounds().get(i));
            }
        }
        vs.add(round);
        /*final ArrayList<Match> ms = new ArrayList<Match>();
         for (int j = 0; j < vs.size(); j++) {
         for (int k = 0; k < vs.get(j).getMatchs().size(); k++) {
         ms.add(vs.get(j).getMatchs().get(k));
         }
         }*/
        for (int j = 0; j < tour.getParams().mTeamMatesNumber; j++) {
            if (tour.getParams().mTeamIndivPairing == 0) {
                final ArrayList<ObjectRanking> coachs1 = subRanking(team1.mCoachs, vs);
                final ArrayList<ObjectRanking> coachs2 = subRanking(team2.mCoachs, vs);
                for (int k = 0; k < coachs1.size(); k++) {
                    final Match m = new Match();
                    m.mCoach1 = (Coach) coachs1.get(k).getObject();
                    m.mCoach2 = (Coach) coachs2.get(k).getObject();
                    r.getMatchs().add(m);
                }
            } else {
                if (tour.getParams().mTeamIndivPairing == 1) {
                    final jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                    jdg.setVisible(true);
                } else {
                    for (int k = 0; k < tour.getParams().mTeamMatesNumber; k++) {
                        final ArrayList<Coach> shuffle2 = new ArrayList<Coach>(team2.getActivePlayers());
                        Collections.shuffle(shuffle2);
                        final Match m = new Match();
                        m.mCoach1 = team1.getActivePlayers().get(k);
                        m.mCoach2 = shuffle2.get(k);
                        r.getMatchs().add(m);
                    }
                }
            }
        }
    }

    protected static Round IndivQSwiss(final ArrayList<Coach> coachs, final ArrayList<Match> matchs, final ArrayList<ObjectRanking> datas, final boolean team, Round r) {

        final Tournament tour = Tournament.getTournament();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        final ArrayList<ObjectRanking> datas_tmp = new ArrayList<ObjectRanking>(datas);
        for (int i = 0; i < coachs.size(); i++) {
            final Coach c = coachs.get(i);
            if (!c.mActive) {
                for (int j = 0; j < datas_tmp.size(); j++) {
                    if (datas_tmp.get(j).getObject().equals(c)) {
                        datas_tmp.remove(j);
                    }
                }
            }
        }
        // part Ranking in 4 groups
        int idx1;
        int idx2;
        int idx3;
        int idx4;
        final int size = datas.size();
        if (size < 4) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "La m\u00e9thode Suisse accel\u00e9r\u00e9e n'est pas applicable");
            r = null;
        } else {
            int half_size = size / 2;
            if (half_size % 2 == 1) {
                half_size -= 1;
            }
            idx1 = 0;
            idx2 = half_size / 2;
            idx3 = half_size;
            idx4 = (size - half_size) / 2 + half_size;
            final ArrayList<ObjectRanking> datas_1 = new ArrayList<ObjectRanking>();
            final ArrayList<ObjectRanking> datas_2 = new ArrayList<ObjectRanking>();
            final ArrayList<ObjectRanking> datas_3 = new ArrayList<ObjectRanking>();
            final ArrayList<ObjectRanking> datas_4 = new ArrayList<ObjectRanking>();
            for (int i = idx1; i < idx2; i++) {
                datas_1.add(datas.get(i));
            }
            for (int i = idx2; i < idx3; i++) {
                datas_2.add(datas.get(i));
            }
            for (int i = idx3; i < idx4; i++) {
                datas_3.add(datas.get(i));
            }
            for (int i = idx4; i < size; i++) {
                datas_4.add(datas.get(i));
            }
            if (Tournament.getTournament().GetActiveCoaches().size() - 1 <= tour.getRounds().size()) {
                for (int i = 0; i < datas_1.size(); i++) {
                    final Match m = new Match();
                    m.mCoach1 = (Coach) datas_1.get(i).getObject();
                    m.mCoach2 = (Coach) datas_2.get(i).getObject();
                    r.getMatchs().add(m);
                }
                for (int i = 0; i < datas_3.size(); i++) {
                    final Match m = new Match();
                    m.mCoach1 = (Coach) datas_3.get(i).getObject();
                    m.mCoach2 = (Coach) datas_4.get(i).getObject();
                    r.getMatchs().add(m);
                }
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NotEnoughRoundToAvoidSameMatch"));
            } else {
                final ArrayList<ObjectRanking> datas_tmp1 = new ArrayList<ObjectRanking>(datas_1);
                final ArrayList<ObjectRanking> datas_tmp2 = new ArrayList<ObjectRanking>(datas_2);
                while ((datas_tmp1.size() > 0) && (datas_tmp2.size() > 0)) {
                    final Match m = new Match();
                    m.mCoach1 = (Coach) datas_tmp1.get(0).getObject();
                    datas_tmp1.remove(0);
                    for (int i = 0; i < datas_tmp2.size(); i++) {
                        boolean have_played = false;
                        for (int j = 0; j < matchs.size(); j++) {
                            if (((matchs.get(j).mCoach1 == m.mCoach1) && (matchs.get(j).mCoach2 == datas_tmp2.get(i).getObject())) || ((matchs.get(j).mCoach2 == m.mCoach1) && (matchs.get(j).mCoach1 == datas_tmp2.get(i).getObject()))) {
                                have_played = true;
                            }
                        }
                        if ((team) && (!have_played)) {
                            for (int j = 0; j < m.mCoach1.mTeamMates.mCoachs.size(); j++) {
                                if (m.mCoach1.mTeamMates.mCoachs.get(j) == datas_tmp2.get(i).getObject()) {
                                    have_played = true;
                                }
                            }
                        }
                        boolean canMatch = !have_played;
                        if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
                            if (!m.mCoach1.mClan.mName.equals("Aucun")) {
                                if (m.mCoach1.mClan == ((Coach) datas_tmp2.get(i).getObject()).mClan) {
                                    canMatch = false;
                                }
                            }
                        }
                        if ((canMatch) || (i == datas_tmp2.size() - 1)) {
                            m.mCoach2 = (Coach) datas_tmp2.get(i).getObject();
                            datas_tmp2.remove(i);
                            break;
                        }
                    }
                    r.getMatchs().add(m);
                }
                final ArrayList<ObjectRanking> datas_tmp3 = new ArrayList<ObjectRanking>(datas_3);
                final ArrayList<ObjectRanking> datas_tmp4 = new ArrayList<ObjectRanking>(datas_4);
                while ((datas_tmp3.size() > 0) && (datas_tmp4.size() > 0)) {
                    final Match m = new Match();
                    m.mCoach1 = (Coach) datas_tmp3.get(0).getObject();
                    datas_tmp3.remove(0);
                    for (int i = 0; i < datas_tmp4.size(); i++) {
                        boolean have_played = false;
                        for (int j = 0; j < matchs.size(); j++) {
                            if (((matchs.get(j).mCoach1 == m.mCoach1) && (matchs.get(j).mCoach2 == datas_tmp4.get(i).getObject())) || ((matchs.get(j).mCoach2 == m.mCoach1) && (matchs.get(j).mCoach1 == datas_tmp4.get(i).getObject()))) {
                                have_played = true;
                            }
                        }
                        if ((team) && (!have_played)) {
                            for (int j = 0; j < m.mCoach1.mTeamMates.mCoachs.size(); j++) {
                                if (m.mCoach1.mTeamMates.mCoachs.get(j) == datas_tmp4.get(i).getObject()) {
                                    have_played = true;
                                }
                            }
                        }
                        boolean canMatch = !have_played;
                        if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
                            if (!m.mCoach1.mClan.mName.equals("Aucun")) {
                                if (m.mCoach1.mClan == ((Coach) datas_tmp4.get(i).getObject()).mClan) {
                                    canMatch = false;
                                }
                            }
                        }
                        if ((canMatch) || (i == datas_tmp4.size() - 1)) {
                            m.mCoach2 = (Coach) datas_tmp4.get(i).getObject();
                            datas_tmp4.remove(i);
                            break;
                        }
                    }
                    r.getMatchs().add(m);
                }
                final ArrayList<ObjectRanking> datas2 = new ArrayList<ObjectRanking>(datas_tmp);
                for (int i = r.getMatchs().size() - 1; i > 0; i--) {
                    boolean have_played = false;
                    final Coach c1 = r.getMatchs().get(i).mCoach1;
                    final Coach c2 = r.getMatchs().get(i).mCoach2;
                    for (int j = 0; j < matchs.size(); j++) {
                        if (((matchs.get(j).mCoach1 == c1) && (matchs.get(j).mCoach2 == c2)) || ((matchs.get(j).mCoach1 == c2) && (matchs.get(j).mCoach2 == c1))) {
                            have_played = true;
                        }
                    }
                    if (have_played) {
                        for (int k = i - 1; k >= 0; k--) {
                            for (int j = 0; j < matchs.size(); j++) {
                                have_played = false;
                                if (((matchs.get(j).mCoach1 == c2) && (matchs.get(j).mCoach2 == r.getMatchs().get(k).mCoach2)) || ((matchs.get(j).mCoach2 == c2) && (matchs.get(j).mCoach1 == r.getMatchs().get(k).mCoach2))) {
                                    have_played = true;
                                    break;
                                }
                            }
                            boolean canMatch = !have_played;
                            if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
                                if (!r.getMatchs().get(i).mCoach2.mClan.mName.equals("Aucun")) {
                                    if (r.getMatchs().get(i).mCoach2.mClan == r.getMatchs().get(k).mCoach2.mClan) {
                                        canMatch = false;
                                    }
                                }
                            }
                            if (canMatch) {
                                r.getMatchs().get(i).mCoach1 = r.getMatchs().get(k).mCoach2;
                                r.getMatchs().get(k).mCoach2 = c1;
                                break;
                            }
                            for (int j = 0; j < matchs.size(); j++) {
                                have_played = false;
                                if (((matchs.get(j).mCoach1 == c2) && (matchs.get(j).mCoach2 == r.getMatchs().get(k).mCoach1)) || ((matchs.get(j).mCoach2 == c2) && (matchs.get(j).mCoach1 == r.getMatchs().get(k).mCoach1))) {
                                    have_played = true;
                                    break;
                                }
                            }
                            canMatch = !have_played;
                            if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
                                if (!r.getMatchs().get(i).mCoach1.mClan.mName.equals("Aucun")) {
                                    if (r.getMatchs().get(i).mCoach2.mClan == r.getMatchs().get(k).mCoach1.mClan) {
                                        canMatch = false;
                                    }
                                }
                            }
                            if (canMatch) {
                                r.getMatchs().get(i).mCoach1 = r.getMatchs().get(k).mCoach1;
                                r.getMatchs().get(k).mCoach1 = c1;
                                break;
                            }
                        }
                        for (int k = 0; k < datas2.size(); k++) {
                            if ((datas2.get(k).getObject() == c1) || (datas2.get(k).getObject() == c2)) {
                                datas2.remove(k);
                                k--;
                            }
                        }
                    } else {
                        for (int k = 0; k < datas2.size(); k++) {
                            if ((datas2.get(k).getObject() == c1) || (datas2.get(k).getObject() == c2)) {
                                datas2.remove(k);
                                k--;
                            }
                        }
                    }
                }
            }

        }
        return r;

    }

    protected static Round TeamRandom(final ArrayList<Team> teams, final ArrayList<Match> matchs, final boolean random, final boolean free_pairing, final ArrayList<Round> rounds, final Round r) {

        final Tournament tour = Tournament.getTournament();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        final ArrayList<Team> datas = new ArrayList<Team>(teams);
        boolean bOK = false;
        int count = 0;
        while (!bOK) {
            count++;
            Collections.shuffle(datas);
            if (count > 100000) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Le moteur al\u00e9atoire n'est pas parvenu \u00e0 \u00e9viter les doubles rencontres");
                for (int i = 0; i < datas.size() / 2; i++) {
                    final Team team1 = datas.get(2 * i);
                    final Team team2 = datas.get(2 * i + 1);
                    if (!free_pairing) {
                        if (!random) {
                            final ArrayList<ObjectRanking> coachs1 = subRanking(team1.mCoachs, rounds);
                            final ArrayList<ObjectRanking> coachs2 = subRanking(team2.mCoachs, rounds);
                            for (int j = 0; j < coachs1.size(); j++) {
                                final Match m = new Match();
                                m.mCoach1 = (Coach) coachs1.get(j).getObject();
                                m.mCoach2 = (Coach) coachs2.get(j).getObject();
                                r.getMatchs().add(m);
                            }
                        } else {
                            final ArrayList<Coach> coachs1 = new ArrayList<Coach>(team1.mCoachs);
                            Collections.shuffle(coachs1);
                            final ArrayList<Coach> coachs2 = new ArrayList<Coach>(team2.mCoachs);
                            Collections.shuffle(coachs2);
                            for (int j = 0; j < coachs1.size(); j++) {
                                final Match m = new Match();
                                m.mCoach1 = (Coach) coachs1.get(j);
                                m.mCoach2 = (Coach) coachs2.get(j);
                                r.getMatchs().add(m);
                            }
                        }
                    } else {
                        final jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                        jdg.setVisible(true);
                    }
                }
                break;
            }
            if (tour.getTeams().size() - 1 <= tour.getRounds().size()) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NotEnoughRoundToAvoidSameMatch"));
                for (int i = 0; i < datas.size() / 2; i++) {
                    final Team team1 = datas.get(2 * i);
                    final Team team2 = datas.get(2 * i + 1);
                    if (!free_pairing) {
                        if (!random) {
                            final ArrayList<ObjectRanking> coachs1 = subRanking(team1.mCoachs, rounds);
                            final ArrayList<ObjectRanking> coachs2 = subRanking(team2.mCoachs, rounds);
                            for (int j = 0; j < coachs1.size(); j++) {
                                final Match m = new Match();
                                m.mCoach1 = (Coach) coachs1.get(j).getObject();
                                m.mCoach2 = (Coach) coachs2.get(j).getObject();
                                r.getMatchs().add(m);
                            }
                        } else {
                            final ArrayList<Coach> coachs1 = new ArrayList<Coach>(team1.mCoachs);
                            Collections.shuffle(coachs1);
                            final ArrayList<Coach> coachs2 = new ArrayList<Coach>(team2.mCoachs);
                            Collections.shuffle(coachs2);
                            for (int j = 0; j < coachs1.size(); j++) {
                                final Match m = new Match();
                                m.mCoach1 = (Coach) coachs1.get(j);
                                m.mCoach2 = (Coach) coachs2.get(j);
                                r.getMatchs().add(m);
                            }
                        }
                    } else {
                        final jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                        jdg.setVisible(true);
                    }
                }
                bOK = true;
            } else {
                bOK = true;
                final ArrayList<Team> data_tmp = new ArrayList<Team>(datas);
                while (data_tmp.size() > 0) {
                    final Team team1 = data_tmp.get(0);
                    data_tmp.remove(team1);
                    Team team2 = null;
                    for (int i = 0; i < data_tmp.size(); i++) {
                        boolean have_played = false;
                        for (int j = 0; j < matchs.size(); j++) {
                            if (((matchs.get(j).mCoach1.mTeamMates == team1) && (matchs.get(j).mCoach2.mTeamMates == data_tmp.get(i))) || ((matchs.get(j).mCoach2.mTeamMates == team1) && (matchs.get(j).mCoach1.mTeamMates == data_tmp.get(i)))) {
                                have_played = true;
                            }
                        }
                        if ((!have_played) || (i == data_tmp.size() - 1)) {
                            team2 = data_tmp.get(i);
                            data_tmp.remove(i);
                            break;
                        }
                    }
                    if (team2 != null) {
                        if (!free_pairing) {
                            if (!random) {
                                final ArrayList<ObjectRanking> coachs1 = subRanking(team1.mCoachs, rounds);
                                final ArrayList<ObjectRanking> coachs2 = subRanking(team2.mCoachs, rounds);
                                for (int j = 0; j < coachs1.size(); j++) {
                                    final Match m = new Match();
                                    m.mCoach1 = (Coach) coachs1.get(j).getObject();
                                    m.mCoach2 = (Coach) coachs2.get(j).getObject();
                                    r.getMatchs().add(m);
                                }
                            } else {
                                final ArrayList<Coach> coachs1 = new ArrayList<Coach>(team1.mCoachs);
                                Collections.shuffle(coachs1);
                                final ArrayList<Coach> coachs2 = new ArrayList<Coach>(team2.mCoachs);
                                Collections.shuffle(coachs2);
                                for (int j = 0; j < coachs1.size(); j++) {
                                    final Match m = new Match();
                                    m.mCoach1 = (Coach) coachs1.get(j);
                                    m.mCoach2 = (Coach) coachs2.get(j);
                                    r.getMatchs().add(m);
                                }
                            }
                        } else {
                            final jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                            jdg.setVisible(true);
                        }
                    } else {
                        bOK = false;
                        break;
                    }
                }
            }
        }
        return r;
    }

    protected static Round IndivRandom(final ArrayList<Coach> coachs, final ArrayList<Match> matchs, final boolean team, final Round r) {
        final Tournament tour = Tournament.getTournament();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        final ArrayList<Coach> shuffle = new ArrayList<Coach>(coachs);
        boolean bOK = false;
        int count = 0;
        while (!bOK) {
            Collections.shuffle(shuffle);
            count++;
            for (int i = 0; i < coachs.size(); i++) {
                if (!coachs.get(i).mActive) {
                    shuffle.remove(coachs.get(i));
                }
            }
            if (count > 100000) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Le moteur al\u00e9atoire n'est pas parvenu \u00e0 \u00e9viter les doubles rencontres");
                for (int i = 0; i < shuffle.size() / 2; i++) {
                    final Match m = new Match();
                    m.mCoach1 = shuffle.get(2 * i);
                    m.mCoach2 = shuffle.get(2 * i + 1);
                    r.getMatchs().add(m);
                }
            } else {
                if (Tournament.getTournament().GetActiveCoaches().size() - 1 <= tour.getRounds().size()) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NotEnoughRoundToAvoidSameMatch"));
                    for (int i = 0; i < shuffle.size() / 2; i++) {
                        final Match m = new Match();
                        m.mCoach1 = shuffle.get(2 * i);
                        m.mCoach2 = shuffle.get(2 * i + 1);
                        r.getMatchs().add(m);
                    }
                } else {
                    final ArrayList<Coach> data_tmp = new ArrayList<Coach>(shuffle);
                    bOK = true;
                    while (data_tmp.size() > 0) {
                        final Match m = new Match();
                        m.mCoach1 = data_tmp.get(0);
                        m.mCoach2 = null;
                        data_tmp.remove(m.mCoach1);
                        for (int i = 0; i < data_tmp.size(); i++) {
                            final Coach candidate = data_tmp.get(i);
                            boolean have_played = false;
                            for (int j = 0; j < candidate.mMatchs.size(); j++) {
                                if (((candidate.mMatchs.get(j).mCoach1 == m.mCoach1) && (candidate.mMatchs.get(j).mCoach2 == candidate)) || ((candidate.mMatchs.get(j).mCoach2 == m.mCoach1) && (candidate.mMatchs.get(j).mCoach1 == candidate))) {
                                    have_played = true;
                                    break;
                                }
                            }
                            if (!have_played) {
                                m.mCoach2 = candidate;
                                data_tmp.remove(candidate);
                                r.getMatchs().add(m);
                                break;
                            }
                        }
                        if (m.mCoach2 == null) {
                            bOK = false;
                            break;
                        }
                    }
                }
            }
        }
        return r;
    }

    protected static Round TeamSwiss(final ArrayList<Team> teams, final ArrayList<Match> matchs, final ArrayList<ObjectRanking> datas, final boolean free_pairing, final ArrayList<Round> rounds, final Round r) {

        final Tournament tour = Tournament.getTournament();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        if (teams.size() - 1 <= tour.getRounds().size()) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NotEnoughRoundToAvoidSameMatch"));
            for (int i = 0; i < datas.size() / 2; i++) {
                final Team team1 = (Team) datas.get(2 * i).getObject();
                final Team team2 = (Team) datas.get(2 * i + 1).getObject();
                if (!free_pairing) {
                    final ArrayList<ObjectRanking> coachs1 = subRanking(team1.mCoachs, rounds);
                    final ArrayList<ObjectRanking> coachs2 = subRanking(team2.mCoachs, rounds);
                    for (int j = 0; j < coachs1.size(); j++) {
                        final Match m = new Match();
                        m.mCoach1 = (Coach) coachs1.get(j).getObject();
                        m.mCoach2 = (Coach) coachs2.get(j).getObject();
                        r.getMatchs().add(m);
                    }
                } else {
                    final jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                    jdg.setVisible(true);
                }
            }
        } else {
            ArrayList<ObjectRanking> datas2 = new ArrayList<ObjectRanking>(datas);
            final ArrayList<Team> teams1 = new ArrayList<Team>();
            final ArrayList<Team> teams2 = new ArrayList<Team>();
            while (datas2.size() > 0) {
                final Team team1 = (Team) datas2.get(0).getObject();
                Team team2 = null;
                datas2.remove(0);
                for (int i = 0; i < datas2.size(); i++) {
                    boolean have_played = false;
                    for (int j = 0; j < matchs.size(); j++) {
                        if (((matchs.get(j).mCoach1.mTeamMates == team1) && (matchs.get(j).mCoach2.mTeamMates == datas2.get(i).getObject())) || ((matchs.get(j).mCoach2.mTeamMates == team1) && (matchs.get(j).mCoach1.mTeamMates == datas2.get(i).getObject()))) {
                            have_played = true;
                        }
                    }
                    if ((!have_played) || (i == datas2.size() - 1)) {
                        team2 = (Team) datas2.get(i).getObject();
                        datas2.remove(i);
                        break;
                    }
                }
                teams1.add(team1);
                teams2.add(team2);
            }
            datas2 = new ArrayList<ObjectRanking>(datas);
            for (int i = teams1.size() - 1; i > 0; i--) {
                boolean have_played = false;
                final Team team1 = teams1.get(i);
                final Team team2 = teams2.get(i);
                for (int j = 0; j < matchs.size(); j++) {
                    if (((matchs.get(j).mCoach1.mTeamMates == team1) && (matchs.get(j).mCoach2.mTeamMates == team2)) || ((matchs.get(j).mCoach1.mTeamMates == team2) && (matchs.get(j).mCoach2.mTeamMates == team1))) {
                        have_played = true;
                    }
                }
                if (have_played) {
                    for (int k = i - 1; k >= 0; k--) {
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;
                            if (((matchs.get(j).mCoach1.mTeamMates == team2) && (matchs.get(j).mCoach2.mTeamMates == teams2.get(k))) || ((matchs.get(j).mCoach2.mTeamMates == team2) && (matchs.get(j).mCoach1.mTeamMates == teams2.get(k)))) {
                                have_played = true;
                                break;
                            }
                        }
                        if (!have_played) {
                            teams1.remove(i);
                            teams1.add(i, teams2.get(k));
                            teams2.remove(k);
                            teams2.add(k, team1);
                            break;
                        }
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;
                            if (((matchs.get(j).mCoach1.mTeamMates == team2) && (matchs.get(j).mCoach2.mTeamMates == teams1.get(k))) || ((matchs.get(j).mCoach2.mTeamMates == team2) && (matchs.get(j).mCoach1.mTeamMates == teams1.get(k)))) {
                                have_played = true;
                                break;
                            }
                        }
                        if (!have_played) {
                            teams1.remove(i);
                            teams1.add(i, teams1.get(k));
                            teams1.remove(k);
                            teams1.add(k, team1);
                            break;
                        }
                    }
                    for (int k = 0; k < datas2.size(); k++) {
                        if ((datas2.get(k).getObject() == team1) || (datas2.get(k).getObject() == team2)) {
                            datas2.remove(k);
                            k--;
                        }
                    }
                } else {
                    for (int k = 0; k < datas2.size(); k++) {
                        if ((datas2.get(k).getObject() == team1) || (datas2.get(k).getObject() == team2)) {
                            datas2.remove(k);
                            k--;
                        }
                    }
                }
            }
            if (free_pairing) {
                final jdgTeamPairing jdg = new jdgTeamPairing(MainFrame.getMainFrame(), true, teams1, teams2, r);
                jdg.setVisible(true);
            } else {
                for (int i = 0; i < teams1.size(); i++) {
                    final Team team1 = teams1.get(i);
                    final Team team2 = teams2.get(i);
                    final ArrayList<ObjectRanking> coachs1 = subRanking(team1.mCoachs, rounds);
                    final ArrayList<ObjectRanking> coachs2 = subRanking(team2.mCoachs, rounds);
                    for (int j = 0; j < coachs1.size(); j++) {
                        final Match m = new Match();
                        m.mCoach1 = (Coach) coachs1.get(j).getObject();
                        m.mCoach2 = (Coach) coachs2.get(j).getObject();
                        r.getMatchs().add(m);
                    }
                }
            }
        }
        return r;
    }

    protected static void IndivCup(final Round round, final Round r, final boolean third_place) {
        final ArrayList<Match> matchs = new ArrayList<Match>(round.getMatchs());
        int nb_match = (int) Math.pow(2, round.mCupMaxTour - round.mCupTour - 1);
        final ArrayList<Coach> _winners = new ArrayList<Coach>();
        final ArrayList<Coach> _loosers = new ArrayList<Coach>();
        if (nb_match == 0) {
            if (round.mCupTour == round.mCupMaxTour + 1) {
                _loosers.add(matchs.get(0).getWinner());
            } else {
                _winners.add(matchs.get(0).getWinner());
                _loosers.add(matchs.get(1).getWinner());
            }
        }
        for (int i = 0; i < nb_match; i++) {
            _winners.add(matchs.get(i).getWinner());
            _loosers.add(matchs.get(i).getLooser());
        }
        if (nb_match > 0) {
            final int option = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Voulez vous m\u00e9langer le(s) tableau(x) ?", StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                Collections.shuffle(_winners);
                Collections.shuffle(_loosers);
            }
        }
        for (int i = 0; i < (_winners.size() + 1) / 2; i++) {
            final Match m = new Match();
            m.mCoach1 = _winners.get(2 * i);
            if ((2 * i + 1) >= _winners.size()) {
                m.mCoach2 = Coach.sNullCoach;
            } else {
                m.mCoach2 = _winners.get(2 * i + 1);
            }
            r.getMatchs().add(m);
        }
        if (third_place) {
            if (_loosers.size() == 2) {
                for (int i = 0; i < (_loosers.size() + 1) / 2; i++) {
                    final Match m = new Match();
                    m.mCoach1 = _loosers.get(2 * i);
                    if ((2 * i + 1) >= _loosers.size()) {
                        m.mCoach2 = Coach.sNullCoach;
                    } else {
                        m.mCoach2 = _loosers.get(2 * i + 1);
                    }
                    r.getMatchs().add(m);
                }
            }
        }
        if (round.mLooserCup) {
            int nb_remaining_match = 0;
            if (round.mCupTour > 0) {
                nb_remaining_match = nb_match;
            }
            if (round.mCupTour > 1) {
                nb_remaining_match += nb_match;
            }

            if (nb_match == 1) {
                /*Match m = new Match();
                 m.mCoach1 = _loosers.get(0);
                 _loosers.remove(0);
                 m.mCoach2 = Coach.sNullCoach;
                 r.getMatchs().add(m);*/
                nb_remaining_match = 3;
            }
            if (nb_match == 0) {
                nb_remaining_match = 1;
                if (round.mCupTour == round.mCupMaxTour + 1) {
                    nb_match = 1;
                } else {
                    nb_match = 2;
                }
            }
            for (int i = nb_match; (i < nb_match + nb_remaining_match) && (i < matchs.size()); i++) {
                _loosers.add(matchs.get(i).getWinner());
            }
            for (int i = 0; i < _loosers.size() / 2; i++) {
                final Match m = new Match();
                m.mCoach1 = _loosers.get(i);
                m.mCoach2 = _loosers.get(_loosers.size() / 2 + i);
                r.getMatchs().add(m);
            }
        }
    }

    protected static void IndivCupAffectation(final Round r, final ArrayList<ObjectRanking> datas, final boolean teamTour, final int nbPlayers) {
        final int nb_match = nbPlayers / 2;
        final ArrayList<Coach> coachs = new ArrayList<Coach>();
        for (int i = 0; i < nbPlayers; i++) {
            Coach c;
            if (i < datas.size()) {
                c = ((Coach) ((ObjectRanking) datas.get(i)).getObject());
            } else {
                c = Coach.sNullCoach;
            }
            coachs.add(c);
        }
        for (int i = 0; i < nb_match / 2; i++) {
            final Match m = new Match();
            m.mCoach1 = coachs.get(2 * i);
            m.mCoach2 = coachs.get(nbPlayers - 2 * i - 1);
            r.getMatchs().add(m);
        }
        for (int i = 0; i < nb_match / 2; i++) {
            final Match m = new Match();
            m.mCoach1 = coachs.get(2 * i + 1);
            m.mCoach2 = coachs.get(nbPlayers - 2 * i - 2);
            r.getMatchs().add(m);
        }
    }

    protected static Round TeamQSwiss(final ArrayList<Team> teams, final ArrayList<Match> matchs, final ArrayList<ObjectRanking> datas, final boolean free_pairing, final ArrayList<Round> rounds, Round r) {
        final Tournament tour = Tournament.getTournament();

        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        // part Ranking in 4 groups
        int idx1;
        int idx2;
        int idx3;
        int idx4;
        final int size = datas.size();
        if (size < 4) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "La m\u00e9thode Suisse accel\u00e9r\u00e9e n'est pas applicable");
            r = null;
        } else {
            int half_size = size / 2;
            if (half_size % 2 == 1) {
                half_size -= 1;
            }
            idx1 = 0;
            idx2 = half_size / 2;
            idx3 = half_size;
            idx4 = (size - half_size) / 2 + half_size;
            final ArrayList<ObjectRanking> datas_1 = new ArrayList<ObjectRanking>();
            final ArrayList<ObjectRanking> datas_2 = new ArrayList<ObjectRanking>();
            final ArrayList<ObjectRanking> datas_3 = new ArrayList<ObjectRanking>();
            final ArrayList<ObjectRanking> datas_4 = new ArrayList<ObjectRanking>();
            for (int i = idx1; i < idx2; i++) {
                datas_1.add(datas.get(i));
            }
            for (int i = idx2; i < idx3; i++) {
                datas_2.add(datas.get(i));
            }
            for (int i = idx3; i < idx4; i++) {
                datas_3.add(datas.get(i));
            }
            for (int i = idx4; i < size; i++) {
                datas_4.add(datas.get(i));
            }
            if (teams.size() - 1 <= tour.getRounds().size()) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NotEnoughRoundToAvoidSameMatch"));
                for (int i = 0; i < datas_3.size(); i++) {
                    final Team team1 = (Team) datas_3.get(i).getObject();
                    final Team team2 = (Team) datas_4.get(i).getObject();
                    if (!free_pairing) {
                        final ArrayList<ObjectRanking> coachs1 = subRanking(team1.mCoachs, rounds);
                        final ArrayList<ObjectRanking> coachs2 = subRanking(team2.mCoachs, rounds);
                        for (int j = 0; j < coachs1.size(); j++) {
                            final Match m = new Match();
                            m.mCoach1 = (Coach) coachs1.get(j).getObject();
                            m.mCoach2 = (Coach) coachs2.get(j).getObject();
                            r.getMatchs().add(m);
                        }
                    } else {
                        final jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                        jdg.setVisible(true);
                    }
                }
                for (int i = 0; i < datas_1.size(); i++) {
                    final Team team1 = (Team) datas_1.get(i).getObject();
                    final Team team2 = (Team) datas_2.get(i).getObject();
                    if (!free_pairing) {
                        final ArrayList<ObjectRanking> coachs1 = subRanking(team1.mCoachs, rounds);
                        final ArrayList<ObjectRanking> coachs2 = subRanking(team2.mCoachs, rounds);
                        for (int j = 0; j < coachs1.size(); j++) {
                            final Match m = new Match();
                            m.mCoach1 = (Coach) coachs1.get(j).getObject();
                            m.mCoach2 = (Coach) coachs2.get(j).getObject();
                            r.getMatchs().add(m);
                        }
                    } else {
                        final jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                        jdg.setVisible(true);
                    }
                }
            } else {
                final ArrayList<ObjectRanking> datas_tmp1 = new ArrayList<ObjectRanking>(datas_1);
                final ArrayList<ObjectRanking> datas_tmp2 = new ArrayList<ObjectRanking>(datas_2);
                final ArrayList<Team> teams1 = new ArrayList<Team>();
                final ArrayList<Team> teams2 = new ArrayList<Team>();
                while ((datas_tmp2.size() > 0) && (datas_tmp1.size() > 0)) {
                    final Team team1 = (Team) datas_tmp1.get(0).getObject();
                    Team team2 = null;
                    datas_tmp1.remove(0);
                    for (int i = 0; i < datas_tmp2.size(); i++) {
                        boolean have_played = false;
                        for (int j = 0; j < matchs.size(); j++) {
                            if (((matchs.get(j).mCoach1.mTeamMates == team1) && (matchs.get(j).mCoach2.mTeamMates == datas_tmp2.get(i).getObject())) || ((matchs.get(j).mCoach2.mTeamMates == team1) && (matchs.get(j).mCoach1.mTeamMates == datas_tmp2.get(i).getObject()))) {
                                have_played = true;
                            }
                        }
                        if ((!have_played) || (i == datas_tmp2.size() - 1)) {
                            team2 = (Team) datas_tmp2.get(i).getObject();
                            datas_tmp2.remove(i);
                            break;
                        }
                    }
                    teams1.add(team1);
                    teams2.add(team2);
                }
                final ArrayList<ObjectRanking> datas_tmp3 = new ArrayList<ObjectRanking>(datas_3);
                final ArrayList<ObjectRanking> datas_tmp4 = new ArrayList<ObjectRanking>(datas_4);
                while ((datas_tmp4.size() > 0) && (datas_tmp3.size() > 0)) {
                    final Team team1 = (Team) datas_tmp3.get(0).getObject();
                    Team team2 = null;
                    datas_tmp3.remove(0);
                    for (int i = 0; i < datas_tmp4.size(); i++) {
                        boolean have_played = false;
                        for (int j = 0; j < matchs.size(); j++) {
                            if (((matchs.get(j).mCoach1.mTeamMates == team1) && (matchs.get(j).mCoach2.mTeamMates == datas_tmp4.get(i).getObject())) || ((matchs.get(j).mCoach2.mTeamMates == team1) && (matchs.get(j).mCoach1.mTeamMates == datas_tmp4.get(i).getObject()))) {
                                have_played = true;
                            }
                        }
                        if ((!have_played) || (i == datas_tmp4.size() - 1)) {
                            team2 = (Team) datas_tmp4.get(i).getObject();
                            datas_tmp4.remove(i);
                            break;
                        }
                    }
                    teams1.add(team1);
                    teams2.add(team2);
                }
                final ArrayList<ObjectRanking> datas2 = new ArrayList<ObjectRanking>(datas);
                for (int i = teams1.size() - 1; i > 0; i--) {
                    boolean have_played = false;
                    final Team team1 = teams1.get(i);
                    final Team team2 = teams2.get(i);
                    for (int j = 0; j < matchs.size(); j++) {
                        if (((matchs.get(j).mCoach1.mTeamMates == team1) && (matchs.get(j).mCoach2.mTeamMates == team2)) || ((matchs.get(j).mCoach1.mTeamMates == team2) && (matchs.get(j).mCoach2.mTeamMates == team1))) {
                            have_played = true;
                        }
                    }
                    if (have_played) {
                        for (int k = i - 1; k >= 0; k--) {
                            for (int j = 0; j < matchs.size(); j++) {
                                have_played = false;
                                if (((matchs.get(j).mCoach1.mTeamMates == team2) && (matchs.get(j).mCoach2.mTeamMates == teams2.get(k))) || ((matchs.get(j).mCoach2.mTeamMates == team2) && (matchs.get(j).mCoach1.mTeamMates == teams2.get(k)))) {
                                    have_played = true;
                                    break;
                                }
                            }
                            if (!have_played) {
                                teams1.remove(i);
                                teams1.add(i, teams2.get(k));
                                teams2.remove(k);
                                teams2.add(k, team1);
                                break;
                            }
                            for (int j = 0; j < matchs.size(); j++) {
                                have_played = false;
                                if (((matchs.get(j).mCoach1.mTeamMates == team2) && (matchs.get(j).mCoach2.mTeamMates == teams1.get(k))) || ((matchs.get(j).mCoach2.mTeamMates == team2) && (matchs.get(j).mCoach1.mTeamMates == teams1.get(k)))) {
                                    have_played = true;
                                    break;
                                }
                            }
                            if (!have_played) {
                                teams1.remove(i);
                                teams1.add(i, teams1.get(k));
                                teams1.remove(k);
                                teams1.add(k, team1);
                                break;
                            }
                        }
                        for (int k = 0; k < datas2.size(); k++) {
                            if ((datas2.get(k).getObject() == team1) || (datas2.get(k).getObject() == team2)) {
                                datas2.remove(k);
                                k--;
                            }
                        }
                    } else {
                        for (int k = 0; k < datas2.size(); k++) {
                            if ((datas2.get(k).getObject() == team1) || (datas2.get(k).getObject() == team2)) {
                                datas2.remove(k);
                                k--;
                            }
                        }
                    }
                }
                if (free_pairing) {
                    final jdgTeamPairing jdg = new jdgTeamPairing(MainFrame.getMainFrame(), true, teams1, teams2, r);
                    jdg.setVisible(true);
                } else {
                    for (int i = 0; i < teams1.size(); i++) {
                        final Team team1 = teams1.get(i);
                        final Team team2 = teams2.get(i);
                        final ArrayList<ObjectRanking> coachs1 = subRanking(team1.mCoachs, rounds);
                        final ArrayList<ObjectRanking> coachs2 = subRanking(team2.mCoachs, rounds);
                        for (int j = 0; j < coachs1.size(); j++) {
                            final Match m = new Match();
                            m.mCoach1 = (Coach) coachs1.get(j).getObject();
                            m.mCoach2 = (Coach) coachs2.get(j).getObject();
                            r.getMatchs().add(m);
                        }
                    }
                }
            }
        }
        return r;
    }

    protected static void finalGenerationStep(final Round round, final Round r) {
        final Tournament tour = Tournament.getTournament();
        if (round != null) {
            for (int i = 0; i < tour.getRounds().size(); i++) {
                if (tour.getRounds().get(i).getHour().after(round.getHour())) {
                    tour.getRounds().remove(i);
                    i--;
                }
            }
        }
        tour.getRounds().add(r);
        for (int i = 0; i < r.getMatchs().size(); i++) {
            final Match m = r.getMatchs().get(i);
            m.mCoach1.mMatchs.add(m);
            m.mCoach2.mMatchs.add(m);
        }
        /*for (int i = MainFrame.getMainFrame().jtpMain.getTabCount() - 1; i >= 0; i--) {
         Component obj = MainFrame.getMainFrame().jtpMain.getComponentAt(i);
         if (obj instanceof JPNRound) {
         MainFrame.getMainFrame().jtpMain.remove(obj);
         }
         if (obj instanceof JPNCup) {
         MainFrame.getMainFrame().jtpMain.remove(obj);
         }
         }
         boolean cup = false;
         for (int i = 0; i < tour.getRounds().size(); i++) {
         JPNRound jpnr = new JPNRound(i, tour.getRounds().get(i), tour);
         if (tour.getRounds().get(i).mCup) {
         cup = true;
         }
         MainFrame.getMainFrame().jtpMain.addTab(ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Round") + " " + (i + 1), new ImageIcon(MainFrame.getMainFrame().getClass().getResource("/tourma/images/Dice.png")), jpnr);
         }
         if (cup) {
         JPNCup jpncup = new JPNCup();
         MainFrame.getMainFrame().jtpMain.addTab(ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Cup"), new ImageIcon(MainFrame.getMainFrame().getClass().getResource("/tourma/images/Star.png")), jpncup);
         }

         MainFrame.getMainFrame().jtpMain.setSelectedIndex(tour.getRounds().size());*/
        MainFrame.getMainFrame().update();
        String filename = Tournament.getTournament().getParams().mTournamentName;
        filename = filename + "." + Tournament.getTournament().getRounds().size();
        final Date date = new Date();
        filename = filename + "." + Long.toString(date.getTime()) + ".xml";
        final File file = new File(filename);
        Tournament.getTournament().saveXML(file);
    }

    private static ArrayList<ObjectRanking> subRanking(final ArrayList<Coach> coachs, final ArrayList<Round> rounds) {

        final Parameters params = Tournament.getTournament().getParams();

        final ArrayList<ObjectRanking> result = new ArrayList<ObjectRanking>();
        for (int j = 0; j < coachs.size(); j++) {
            final Coach c = coachs.get(j);
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int k = 0; k < rounds.size(); k++) {
                final Match m = c.mMatchs.get(k);
                value1 += mjtRankingIndiv.getValue(c, m, params.mRankingIndiv1);
                value2 += mjtRankingIndiv.getValue(c, m, params.mRankingIndiv2);
                value3 += mjtRankingIndiv.getValue(c, m, params.mRankingIndiv3);
                value4 += mjtRankingIndiv.getValue(c, m, params.mRankingIndiv4);
                value5 += mjtRankingIndiv.getValue(c, m, params.mRankingIndiv5);
            }
            final ObjectRanking obj = new ObjectRanking(c, value1, value2, value3, value4, value5);
            result.add(obj);
        }
        Collections.sort(result);
        return result;
    }

    protected static Round NextRoundQSwiss(final Round round, final int roundnumber) {

        final Tournament tour = Tournament.getTournament();

        ArrayList<ObjectRanking> datas;
        // First: Create ranking
        // previous rounds
        final ArrayList<Round> v = new ArrayList<Round>();
        final ArrayList<Match> matchs = new ArrayList<Match>();
        buildUntilRound(round, v, matchs);

        Round r = new Round();
        if (tour.getPools().isEmpty()) {

            datas = getSortedRankingData(roundnumber);
            if (tour.getParams().mTeamTournament) {
                if (tour.getParams().mTeamPairing == 0) {
                    Generation.IndivQSwiss(tour.getCoachs(), matchs, datas, true, r);
                } else {
                    Generation.TeamQSwiss(tour.getTeams(), matchs, datas, tour.getParams().mTeamIndivPairing == 1, v, r);
                }
            } else {
                if (tour.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    r = null;
                } else {
                    Generation.IndivQSwiss(tour.getCoachs(), matchs, datas, false, r);
                }
            }
        } else {
            for (int i = 0; i < tour.getPools().size(); i++) {
                datas = getSortedRankingData(tour.getPools().get(i), roundnumber);
                if (tour.getParams().mTeamTournament) {
                    if (tour.getParams().mTeamPairing == 0) {
                        Generation.IndivQSwiss(tour.getPools().get(i).mCoachs, matchs, datas, true, r);
                    } else {
                        Generation.TeamQSwiss(tour.getPools().get(i).mTeams, matchs, datas, tour.getParams().mTeamIndivPairing == 1, v, r);
                    }
                } else {
                    if (tour.GetActiveCoachNumber() % 2 > 0) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                        r= null;
                    } else {
                        Generation.IndivQSwiss(tour.getPools().get(i).mCoachs, matchs, datas, false, r);
                    }
                }
            }
        }
        return r;
    }

    protected static Round NextRoundCup(final Round round, final int roundnumber) {

        final Tournament tour = Tournament.getTournament();
        final Round r = new Round();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        if ((round != null) && (round.mCup)) {
            if (((round.mCupTour == round.mCupMaxTour - 1) && (!round.mLooserCup))
                    || (round.mCupTour == round.mCupMaxTour + 2)) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Tour final atteint", StringConstants.CS_CUP, JOptionPane.ERROR_MESSAGE);
                return null;
            }

            boolean _third_place = false;
            if ((round.mCupTour == round.mCupMaxTour - 2) && (!round.mLooserCup)) {
                _third_place = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Voulez vous générer le match pour la 3e place ?", StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            }

            if (tour.getParams().mTeamTournament) {
                if (tour.getParams().mTeamPairing == 0) {
                    Generation.IndivCup(round, r, _third_place);
                } else {
                    Generation.TeamCup(round, r, _third_place);
                }
            } else {
                if (tour.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    return null;
                } else {
                    Generation.IndivCup(round, r, _third_place);
                }
            }

            r.mCup = true;
            r.mCupMaxTour = round.mCupMaxTour;
            r.mCupTour = round.mCupTour + 1;
            r.mLooserCup = round.mLooserCup;
        } else {
            int cup_max_tour = 0;
            int nb_tmp = 1;
            r.mLooserCup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Sagit-il d'un tournoi à double élimination ?", StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            if (tour.getParams().mTeamTournament) {
                if (tour.getParams().mTeamPairing == 0) {
                    while (nb_tmp < tour.GetActiveCoachNumber()) {
                        nb_tmp *= 2;
                        cup_max_tour++;
                    }
                } else {
                    while (nb_tmp < tour.getTeams().size()) {
                        nb_tmp *= 2;
                        cup_max_tour++;
                    }
                }
            } else {
                if (tour.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    return null;
                } else {
                    while (nb_tmp < tour.GetActiveCoachNumber()) {
                        nb_tmp *= 2;
                        cup_max_tour++;
                    }
                }
            }

            final JPanel message = new JPanel();
            message.setLayout(new BorderLayout());

            final JLabel jlb = new JLabel("Nombre de tours: ");
            // generate cup for the first round
            final JSpinner jspNbTeams = new JSpinner();
            final SpinnerNumberModel model = new SpinnerNumberModel(1, 1, cup_max_tour, 1);
            jspNbTeams.setModel(model);

            message.add(jlb, BorderLayout.NORTH);
            message.add(jspNbTeams, BorderLayout.CENTER);

            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), message, StringConstants.CS_CUP, JOptionPane.QUESTION_MESSAGE);

            cup_max_tour = (Integer) model.getValue();
            final int nb = (int) Math.pow(2, cup_max_tour);

            ArrayList<ObjectRanking> datas;
            datas = getSortedRankingData(roundnumber);
            if (tour.getParams().mTeamTournament) {
                if (tour.getParams().mTeamPairing == 0) {
                    Generation.IndivCupAffectation(r, datas, true, nb);
                } else {
                    Generation.TeamCupAffectation(r, datas, nb);
                }
            } else {
                if (tour.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    return null;
                } else {
                    Generation.IndivCupAffectation(r, datas, false, nb);
                }
            }
            r.mCup = true;
            r.mCupMaxTour = cup_max_tour;
            r.mCupTour = 0;
        }

        if (r.getMatchs().size() < tour.GetActiveCoachNumber() / 2) {
            int res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affecter une ronde aux joueurs restants ?", StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {

                final ArrayList<ObjectRanking> datas = new ArrayList<ObjectRanking>(getSortedRankingData(roundnumber));
                ArrayList<Coach> coachs;
                ArrayList<Team> teams;
                final Round r2 = new Round();

                if (tour.getParams().mTeamTournament) {
                    if (tour.getParams().mTeamPairing == 0) {

                        coachs = new ArrayList<Coach>(tour.GetActiveCoaches());
                        // First, remove coachs
                        for (int i = 0; i < r.getMatchs().size(); i++) {
                            final Match m = r.getMatchs().get(i);
                            if (coachs.contains(m.mCoach1)) {
                                coachs.remove(m.mCoach1);
                            }
                            if (coachs.contains(m.mCoach2)) {
                                coachs.remove(m.mCoach2);
                            }
                        }
                        // Second remove bad datas
                        for (int i = 0; i < datas.size(); i++) {
                            final Coach c = (Coach) ((ObjectRanking) datas.get(i)).getObject();
                            for (int j = 0; j < r.getMatchs().size(); j++) {
                                final Match m = r.getMatchs().get(j);
                                if ((c == m.mCoach1) || (c == m.mCoach2)) {
                                    datas.remove(i);
                                    i--;
                                }
                            }
                        }

                    } else {
                        teams = new ArrayList<Team>(tour.getTeams());
                        // First, remove coachs
                        for (int i = 0; i < r.getMatchs().size(); i++) {
                            final Match m = r.getMatchs().get(i);
                            if (teams.contains(m.mCoach1.mTeamMates)) {
                                teams.remove(m.mCoach1.mTeamMates);
                            }
                            if (teams.contains(m.mCoach2.mTeamMates)) {
                                teams.remove(m.mCoach2.mTeamMates);
                            }
                        }
                        // Second remove bad datas
                        for (int i = 0; i < datas.size(); i++) {
                            final Team t = (Team) ((ObjectRanking) datas.get(i)).getObject();
                            for (int j = 0; j < r.getMatchs().size(); j++) {
                                final Match m = r.getMatchs().get(j);
                                if ((t == m.mCoach1.mTeamMates) || (t == m.mCoach2.mTeamMates)) {
                                    datas.remove(i);
                                    i--;
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    if (tour.GetActiveCoachNumber() % 2 > 0) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                        return null;
                    } else {
                        coachs = new ArrayList<Coach>(tour.GetActiveCoaches());
                        // First, remove coachs
                        for (int i = 0; i < r.getMatchs().size(); i++) {
                            final Match m = r.getMatchs().get(i);
                            if (coachs.contains(m.mCoach1)) {
                                coachs.remove(m.mCoach1);
                            }
                            if (coachs.contains(m.mCoach2)) {
                                coachs.remove(m.mCoach2);
                            }
                        }
                        // Second remove bad datas
                        for (int i = 0; i < datas.size(); i++) {
                            final Coach c = (Coach) ((ObjectRanking) datas.get(i)).getObject();
                            for (int j = 0; j < r.getMatchs().size(); j++) {
                                final Match m = r.getMatchs().get(j);
                                if ((c == m.mCoach1) || (c == m.mCoach2)) {
                                    datas.remove(i);
                                    i--;
                                }
                            }
                        }
                    }
                }

                final ArrayList<Round> v = new ArrayList<Round>();
                final ArrayList<Match> matchs = new ArrayList<Match>();
                buildUntilRound(round, v, matchs);

                res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Utiliser une ronde suisse (aléatoire sinon)? ", StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {

                    if (tour.getParams().mTeamTournament) {
                        if (tour.getParams().mTeamPairing == 0) {
                            Generation.IndivSwiss(tour.getCoachs(), matchs, datas, true, r2);
                        } else {
                            Generation.TeamSwiss(tour.getTeams(), matchs, datas, tour.getParams().mTeamIndivPairing == 1, v, r2);
                        }
                    } else {
                        if (tour.GetActiveCoachNumber() % 2 > 0) {
                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                            return null;
                        } else {
                            Generation.IndivSwiss(tour.getCoachs(), matchs, datas, false, r2);
                        }
                    }
                } else {
                    if (tour.getParams().mTeamTournament) {
                        if (tour.getParams().mTeamPairing == 0) {
                            Generation.IndivRandom(tour.getCoachs(), matchs, true, r2);
                        } else {
                            Generation.TeamRandom(tour.getTeams(), matchs, true, tour.getParams().mTeamIndivPairing == 1, v, r2);
                        }
                    } else {
                        if (tour.GetActiveCoachNumber() % 2 > 0) {
                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                            return null;
                        } else {
                            Generation.IndivRandom(tour.getCoachs(), matchs, false, r);
                        }
                    }
                }

                // Merge the generated rounds
                for (int i = 0; i < r2.getMatchs().size(); i++) {
                    r.getMatchs().add(r2.getMatchs().get(i));
                }
            }
        }
        return r;
    }

    protected static void buildUntilRound(final Round round, final ArrayList<Round> v, final ArrayList<Match> matchs) {
        final Tournament tour = Tournament.getTournament();
        if (round != null) {
            for (int i = 0; i < tour.getRounds().size(); i++) {
                if (tour.getRounds().get(i).getHour().before(round.getHour())) {
                    v.add(tour.getRounds().get(i));
                }
            }
            v.add(round);


            for (int j = 0; j < v.size(); j++) {
                for (int k = 0; k < v.get(j).getMatchs().size(); k++) {
                    matchs.add(v.get(j).getMatchs().get(k));
                }
            }
        }
    }

    protected static Round NextRoundSwiss(final Round round, final int roundnumber) {

        final Tournament tour = Tournament.getTournament();

        ArrayList<ObjectRanking> datas;
        // First: Create ranking
        // previous rounds       
        final ArrayList<Round> v = new ArrayList<Round>();
        final ArrayList<Match> matchs = new ArrayList<Match>();
        buildUntilRound(round, v, matchs);

        final Round r = new Round();
        if (tour.getPools().isEmpty()) {
            datas = getSortedRankingData(roundnumber);
            if (tour.getParams().mTeamTournament) {
                if (tour.getParams().mTeamPairing == 0) {
                    Generation.IndivSwiss(tour.getCoachs(), matchs, datas, true, r);
                } else {
                    Generation.TeamSwiss(tour.getTeams(), matchs, datas, tour.getParams().mTeamIndivPairing == 1, v, r);
                }
            } else {
                if (tour.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    return null;
                } else {
                    Generation.IndivSwiss(tour.getCoachs(), matchs, datas, false, r);
                }
            }
        } else {
            for (int i = 0; i < tour.getPools().size(); i++) {
                datas = getSortedRankingData(tour.getPools().get(i), roundnumber);
                if (tour.getParams().mTeamTournament) {
                    if (tour.getParams().mTeamPairing == 0) {
                        Generation.IndivSwiss(tour.getPools().get(i).mCoachs, matchs, datas, true, r);
                    } else {
                        Generation.TeamSwiss(tour.getPools().get(i).mTeams, matchs, datas, tour.getParams().mTeamIndivPairing == 1, v, r);
                    }
                } else {
                    if (tour.GetActiveCoachNumber() % 2 > 0) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                        return null;
                    } else {
                        Generation.IndivSwiss(tour.getPools().get(i).mCoachs, matchs, datas, false, r);
                    }
                }
            }
        }
        return r;
    }

    private static ArrayList<ObjectRanking> getSortedRankingData(final int roundnumber) {
        final Tournament tour = Tournament.getTournament();
        ArrayList<ObjectRanking> datas;
        if ((tour.getParams().mTeamTournament) && (tour.getParams().mTeamPairing != 0)) {
            mjtRankingTeam ranking;

            if (tour.getParams().mTeamVictoryOnly) {
                ranking = new mjtRankingTeam(true, roundnumber,
                        tour.getParams().mRankingTeam1,
                        tour.getParams().mRankingTeam2,
                        tour.getParams().mRankingTeam3,
                        tour.getParams().mRankingTeam4,
                        tour.getParams().mRankingTeam5,
                        tour.getTeams(), false);
            } else {
                ranking = new mjtRankingTeam(false, roundnumber,
                        tour.getParams().mRankingIndiv1,
                        tour.getParams().mRankingIndiv2,
                        tour.getParams().mRankingIndiv3,
                        tour.getParams().mRankingIndiv4,
                        tour.getParams().mRankingIndiv5,
                        tour.getTeams(), false);
            }
            // Ranking class
            datas = ranking.getSortedDatas();
        } else {
            final boolean forPool = (tour.getPools().size() > 0) && (!tour.getRounds().get(roundnumber).mCup);
            final mjtRankingIndiv ranking = new mjtRankingIndiv(roundnumber,
                    tour.getParams().mRankingIndiv1,
                    tour.getParams().mRankingIndiv2,
                    tour.getParams().mRankingIndiv3,
                    tour.getParams().mRankingIndiv4,
                    tour.getParams().mRankingIndiv5,
                    tour.getCoachs(), false, false, forPool);

            // Ranking class
            datas = ranking.getSortedDatas();
        }
        return datas;
    }

    private static ArrayList<ObjectRanking> getSortedRankingData(final Pool p, final int roundnumber) {
        ArrayList<ObjectRanking> datas;
        final Tournament tour = Tournament.getTournament();
        if ((tour.getParams().mTeamTournament) && (tour.getParams().mTeamPairing != 0)) {
            mjtRankingTeam ranking;

            if (tour.getParams().mTeamVictoryOnly) {
                ranking = new mjtRankingTeam(true, roundnumber,
                        tour.getParams().mRankingTeam1,
                        tour.getParams().mRankingTeam2,
                        tour.getParams().mRankingTeam3,
                        tour.getParams().mRankingTeam4,
                        tour.getParams().mRankingTeam5,
                        p.mTeams,
                        false);
            } else {
                ranking = new mjtRankingTeam(false, roundnumber,
                        tour.getParams().mRankingIndiv1,
                        tour.getParams().mRankingIndiv2,
                        tour.getParams().mRankingIndiv3,
                        tour.getParams().mRankingIndiv4,
                        tour.getParams().mRankingIndiv5,
                        p.mTeams, false);
            }
            // Ranking class
            datas = ranking.getSortedDatas();
        } else {
            final boolean forPool = (tour.getPools().size() > 0) && (!tour.getRounds().get(roundnumber).mCup);
            final mjtRankingIndiv ranking = new mjtRankingIndiv(roundnumber,
                    tour.getParams().mRankingIndiv1,
                    tour.getParams().mRankingIndiv2,
                    tour.getParams().mRankingIndiv3,
                    tour.getParams().mRankingIndiv4,
                    tour.getParams().mRankingIndiv5,
                    p.mCoachs, false, false, forPool);

            // Ranking class
            datas = ranking.getSortedDatas();
        }
        return datas;
    }

    protected static Round NextRoundRandom(final Round round) {
        final Tournament tour = Tournament.getTournament();

        final ArrayList<Round> v = new ArrayList<Round>();
        final ArrayList<Match> matchs = new ArrayList<Match>();
        buildUntilRound(round, v, matchs);

        final Round r = new Round();
        r.getMatchs().clear();
        if (tour.getPools().isEmpty()) {
            if (tour.getParams().mTeamTournament) {
                if (tour.getParams().mTeamPairing == 0) {
                    Generation.IndivRandom(tour.GetActiveCoaches(), matchs, true, r);
                } else {
                    Generation.TeamRandom(tour.getTeams(), matchs, true, tour.getParams().mTeamIndivPairing == 1, v, r);
                }
            } else {
                if (tour.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    return null;
                } else {
                    Generation.IndivRandom(tour.GetActiveCoaches(), matchs, false, r);
                }
            }
        } else {
            for (int i = 0; i < tour.getPools().size(); i++) {
                if (tour.getParams().mTeamTournament) {
                    if (tour.getParams().mTeamPairing == 0) {
                        Generation.IndivRandom(tour.getPools().get(i).mCoachs, matchs, true, r);
                    } else {
                        Generation.TeamRandom(tour.getPools().get(i).mTeams, matchs, true, tour.getParams().mTeamIndivPairing == 1, v, r);
                    }
                } else {
                    if (tour.GetActiveCoachNumber() % 2 > 0) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Nombre de coachs actif impair", StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                        return null;
                    } else {
                        Generation.IndivRandom(tour.getPools().get(i).mCoachs, matchs, false, r);
                    }
                }
            }
        }
        return r;
    }
}
