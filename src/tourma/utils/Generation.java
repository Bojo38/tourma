/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import tourma.JdgCupOptions;
import tourma.JdgManualCupDraw;
import tourma.JdgPairing;
import tourma.MainFrame;
import tourma.data.Category;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.Cup;
import tourma.data.ETeamPairing;
import tourma.data.Match;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.languages.Translate;
import static tourma.languages.Translate.CS_Pool;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingManual;
import tourma.tableModel.MjtRankingTeam;

/**
 *
 * @author WFMJ7631
 */
public final class Generation {

    /**
     *
     */
    public static final int GEN_ORDER = 0;

    /**
     *
     */
    public static final int GEN_RANDOM = 1;

    /**
     *
     */
    public static final int GEN_SWISS = 2;

    /**
     *
     */
    public static final int GEN_QSWISS = 3;

    /**
     *
     */
    public static final int GEN_CUP = 4;

    /**
     *
     */
    public static final int GEN_RROBIN = 5;

    /**
     *
     */
    public static final int GEN_MANUAL = 6;

    /**
     *
     */
    public static final int GEN_POOL = 7;

    /**
     *
     */
    public static final int GEN_NAF = 8;

    /**
     *
     */
    public static final int GEN_FREE = 9;

    /**
     *
     */
    public static final int GEN_BALANCED = 10;
    public static final int GEN_NAF_AVG = 11;
    public static final int GEN_SWISS_TOP_AND_DOWN = 12;
    /**
     *
     */
    private final static String[] mPortugueses = {"yanno",
        "obelix", "oon", "fredriech", "pershum",
        "buldogr", "simon_mlf", "haktar", "tristelune", "fredao",
        "haktar", "jc"};

    private static final String CS_GenerationError = "ERREUR DE GÉNÉRATION";
    private final static String CS_BadNumberOfActivePlayersForTeam = "MAUVAIS NOMBRE DE JOUEURS ACTIF POUR L'ÉQUIPE";
    private final static String CS_OddNumberOfActiveCoachs = "NOMBRE IMPAIR DE COACH ACTIFS";
    private final static String CS_DoYouWantATeamFirstPairing = "DoYouWantATeamFirstPairing";
    private final static String CS_FirstRound = "FirstRound";
    private final static String CS_NumberByPool = "NumberByPool";
    private final static String CS_TheNumberOfTeamsIsNotAMultipleOfYourChoice = "LE NOMBRE D'ÉQUIPE N'EST PAS UN MULTIPLE DE VOTRE CHOIX";
    private final static String CS_OddInPool = "Impair in pool";
    private final static String CS_DoYouWantToChooseRankTeam = "VOULEZ VOUS CHOISIR L'ÉQUIPE DU RANG {0} ?";
    private final static String CS_ChooseCompetitor = "CHOISISSEZ UN COACH";
    private final static String CS_ChooseOpponentFor = "ChooseOpponentFor";
    private final static String CS_ChoosOpponent = "ChoosOpponent";
    private final static String CS_IsItDoubleKickTournament = "SAGIT-IL D'UN TOURNOI À DOUBLE ÉLIMINATION ?";
    private final static String CS_NotEnoughRoundToAvoidSameMatch = "NotEnoughRoundToAvoidSameMatch";
    private final static String CS_AccleratedSwissMethodIsNotApplicable = "LA MÉTHODE SUISSE ACCELÉRÉE N'EST PAS APPLICABLE";
    private final static String CS_DoYouWantToMixDraw = "VOULEZ VOUS MÉLANGER LE(S) TABLEAU(X) ?";
    private final static String CS_FinalRoundReached = "TOUR FINAL ATTEINT";
    private final static String CS_DoYouWantToGenerateThirdPlaceMatch = "VOULEZ VOUS GÉNÉRER LE MATCH POUR LA 3E PLACE ?";
    private final static String CS_NumberOfTurns = "NOMBRE DE TOURS: ";
    private final static String CS_DoYouWantToUseCategoriesForTheCup = "DoYouWantToUseCategoriesForTheCup";
    private final static String CS_PleaseSelectCategoriesToUseForTheCup = "PleaseSelectCategoriesToUseForTheCup";
    private final static String CS_MixAll = "MixAll";
    private final static String CS_MixByAbsoluteRanking = "MixByAbsoluteRanking";
    private final static String CS_MixByCategoryRanking = "MixByCategoryRanking";
    private final static String CS_KeepGroup = "KeepGroup";
    private final static String CS_AffectRoundsToRemainingCoachs = "AFFECTER UNE RONDE AUX JOUEURS RESTANTS ?";
    private final static String CS_UseSwissRoundOrRandom = "UTILISER UNE RONDE SUISSE (ALÉATOIRE SINON)? ";

    /**
     *
     * @param round
     */
    private static void applyPortugal(final Round round) {
        Parameters params = Tournament.getTournament().getParams();
        if (params.isPortugal()) {
            Criteria td = params.getCriteria(0);
            ArrayList<CoachMatch> acm = round.getCoachMatchs();
            for (int i = 0; i < acm.size(); i++) {
                CoachMatch cm = acm.get(i);
                for (int j = 0; j < mPortugueses.length; j++) {
                    if (mPortugueses[j].toLowerCase(Locale.getDefault()).equals(cm.getCompetitor1().getName().toLowerCase(Locale.getDefault()))) {
                        cm.getValue(td).setValue1(-2);
                    }
                    if (mPortugueses[j].toLowerCase(Locale.getDefault()).equals(cm.getCompetitor2().getName().toLowerCase(Locale.getDefault()))) {
                        cm.getValue(td).setValue2(-2);
                    }
                }

            }
        }
    }

    /**
     *
     * @param round
     * @param choice
     * @param roundnumber
     */
    public static void nextRound(final Round round, final int choice, final int roundnumber) {

        Round r = null;
        Tournament.getTournament().recomputeAll();

        switch (choice) {
            case GEN_SWISS:
                r = nextRoundSwiss(round, roundnumber, false);
                break;
            case GEN_SWISS_TOP_AND_DOWN:
                r = nextRoundSwiss(round, roundnumber, true);
                break;
            case GEN_CUP:
                r = nextRoundCup(round, roundnumber);
                break;
            case GEN_QSWISS:
                r = nextRoundQSwiss(round, roundnumber);
                break;
            case GEN_RANDOM:
                r = nextRoundRandom(round);
                break;
            case GEN_FREE:
                r = nextRoundFree();
                break;
            case GEN_BALANCED:
                r = nextRoundBalanced();
                break;
            default:
        }

        if (r != null) {
            finalGenerationStep(round, r);
        }
    }

    /**
     * Generate the first round with free pairing
     */
    public static void generateFirstRoundFree() {
        final Round r = new Round();
        //final Calendar cal = Calendar.getInstance();
        r.setCurrentHour();
        final Tournament tour = Tournament.getTournament();
        tour.clearRounds();
        tour.addRound(r);
    }

    /**
     *
     * @param choice
     */
    public static void generateFirstRound(final int choice) {
        final Tournament tour = Tournament.getTournament();
        final Parameters params = tour.getParams();

        final ArrayList<Coach> coachs = new ArrayList<>();
        for (int i = 0; i < tour.getCoachsCount(); i++) {
            Coach c = tour.getCoach(i);
            coachs.add(c);
            c.clearMatchs();
        }
        //final ArrayList<Round> rounds = tour.getRounds();
        ArrayList<Team> vteams = new ArrayList<>();
        for (int cpt = 0; cpt < tour.getTeamsCount(); cpt++) {
            Team t = tour.getTeam(cpt);
            vteams.add(t);
            t.clearMatchs();
        }

        tour.clearRounds();

        tour.setRoundRobin(false);

        if (params.isTeamTournament()) {
            /**
             * First check the number of active players
             */
            for (int i = 0; i < vteams.size(); i++) {
                if (vteams.get(i).getActivePlayerNumber() != params.getTeamMatesNumber()) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                            Translate.translate(CS_BadNumberOfActivePlayersForTeam) + " " + vteams.get(i).getName());
                    return;
                }
            }
        } else {
            final int nb_coach = tour.getActiveCoachNumber();
            if (nb_coach % 2 > 0) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                        Translate.translate(CS_OddNumberOfActiveCoachs),
                        Translate.translate(CS_GenerationError),
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        for (int i = 0; i < coachs.size(); i++) {
            coachs.get(i).newMatchs();
        }

        ArrayList competitors;
        if (!params.isTeamTournament()) {
            competitors = tour.getActiveCoaches();
        } else {
            if (params.getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                if (tour.getTeamsCount() % 2 > 0) {
                    competitors = tour.getActiveCoaches();
                } else {
                    int res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),
                            Translate.translate(CS_DoYouWantATeamFirstPairing),
                            Translate.translate(CS_FirstRound),
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (res == JOptionPane.YES_OPTION) {
                        ArrayList<Team> teams = new ArrayList<>();
                        for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                            teams.add(Tournament.getTournament().getTeam(cpt));
                        }
                        competitors = teams;
                    } else {
                        competitors = tour.getActiveCoaches();
                    }
                }
            } else {
                ArrayList<Team> teams = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                    teams.add(Tournament.getTournament().getTeam(cpt));
                }
                competitors = teams;
            }
        }

        if (choice == GEN_NAF_AVG) {
            for (Object comp : competitors) {

                ((Competitor) comp).enableNafAvg(true);
            }
        }

        switch (choice) {
            // Manual Choice
            case GEN_MANUAL:
                generateFirstRoundManual(competitors);
                break;
            // Toute Ronde
            case GEN_RROBIN:
                generateFirstRoundRobin(competitors);
                break;
            case GEN_RANDOM:
                generateFirstRoundOrder(competitors, true, false);
                break;
            case GEN_ORDER:
                generateFirstRoundOrder(competitors, false, false);
                break;
            case GEN_POOL:
                generateFirstRoundPool(competitors);
                break;
            case GEN_CUP:
                generateFirstRoundCup(competitors);
                break;
            case GEN_NAF:
                generateFirstRoundOrder(competitors, false, true);
                break;
            case GEN_NAF_AVG:
                generateFirstRoundOrder(competitors, false, true);
                break;
            case GEN_FREE:
                generateFirstRoundFree();
                break;
            case GEN_BALANCED:
                tour.addRound(nextRoundBalanced());
                break;

            default:
                break;
        }

        if (tour.getRoundsCount() > 0) {
            for (int k = 0; k < tour.getRoundsCount(); k++) {

                Round r = tour.getRound(k);
                for (int i = 0; i < r.getMatchsCount(); i++) {
                    final Match m = r.getMatch(i);

                    Competitor c1 = m.getCompetitor1();
                    if (!c1.containsMatch(m)) {
                        c1.addMatch(m);

                    }
                    Competitor c2 = m.getCompetitor2();
                    if (!c2.containsMatch(m)) {
                        c2.addMatch(m);

                    }
                    if (m instanceof TeamMatch) {
                        for (int j = 0; j < ((TeamMatch) m).getMatchCount(); j++) {
                            CoachMatch cm = ((TeamMatch) m).getMatch(j);
                            cm.getCompetitor1().addMatch(cm);
                            cm.getCompetitor2().addMatch(cm);
                        }
                    }
                }
                applyPortugal(r);
            }
        }

        saveTemporaryTournament();
    }

    private static void saveTemporaryTournament() {
        Parameters params = Tournament.getTournament().getParams();
        final StringBuffer filename = new StringBuffer(params.getTournamentName());
        filename.append(".");
        filename.append(Tournament.getTournament().getRoundsCount());
        final Date date = new Date();
        filename.append(".");
        filename.append(Long.toString(date.getTime()));
        filename.append(".xml");
        final File file = new File(filename.toString());

        final Tournament tour = Tournament.getTournament();
        if (tour instanceof Tournament) {
            ((Tournament) tour).saveXML(file);
        }
    }

    /**
     *
     * @param competitors
     */
    private static void generateFirstRoundPool(ArrayList competitors) {

        final Tournament tour = Tournament.getTournament();

        tour.clearPools();

        @SuppressWarnings("unchecked")
        ArrayList<Competitor> comps = new ArrayList<>(competitors);

        final JPanel message = new JPanel();
        message.setLayout(new BorderLayout());

        final JLabel jlb = new JLabel(Translate.translate(CS_NumberByPool));

        final JSpinner jspNb = new JSpinner();

        final SpinnerNumberModel model = new SpinnerNumberModel(2, 2, comps.size() / 2, 1);
        jspNb.setModel(model);

        message.add(jlb, BorderLayout.NORTH);
        message.add(jspNb, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), message, Translate.translate(CS_Pool), JOptionPane.QUESTION_MESSAGE);

        final int nb = (Integer) model.getValue();

        boolean cross_matches = false;
        if (nb % 2 != 0) {
            cross_matches = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),
                    Translate.translate(CS_OddInPool),
                    Translate.translate(CS_GenerationError),
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        }

        tour.getParams().setCrossPoolMatch(cross_matches);

        if (comps.size() % nb != 0) {

            boolean complete = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),
                    Translate.translate(CS_TheNumberOfTeamsIsNotAMultipleOfYourChoice),
                    Translate.translate(CS_GenerationError),
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            if (complete) {
                Competitor nc;
                if (comps.get(0) instanceof Coach) {
                    nc = Coach.getNullCoach();
                } else {
                    nc = Team.getNullTeam();
                }
                while (comps.size() % nb != 0) {
                    comps.add(nc);
                }
            } else {
                return;
            }
        }

        final int nbPools = comps.size() / nb;
        for (int i = 0; i < nbPools; i++) {
            final Pool p = new Pool();
            tour.addPool(p);
            p.setName(Integer.toString(i + 1));
        }

        int nbCounter = nb;

        int response = JOptionPane.YES_OPTION;

        while (nbCounter > 0) {

            if (response == JOptionPane.YES_OPTION) {
                String question = Translate.translate(CS_DoYouWantToChooseRankTeam);

                String formattedQuestion = question.replace("{0}", Integer.toString(nb - nbCounter + 1));
                response = JOptionPane.showConfirmDialog(
                        MainFrame.getMainFrame(),
                        formattedQuestion,
                        Translate.translate(Translate.CS_Pool), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            }
            if (response == JOptionPane.YES_OPTION) {
                for (int i = 0; i < nbPools; i++) {
                    final ArrayList<String> names = new ArrayList<>();
                    for (int j = 0; j < comps.size(); j++) {
                        names.add(comps.get(j).getName());
                    }
                    String question = Translate.translate(CS_ChooseCompetitor);
                    question = Integer.toString(i + 1) + ": " + question;
                    final int index = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), question, Translate.translate(Translate.CS_Pool), JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, names.toArray(), 0);
                    tour.getPool(i).addCompetitor(comps.get(index));
                    comps.remove(index);
                }
            } else {
                Random ran = new Random();
                for (int i = 0; i < nbPools; i++) {
                    final int index;
                    index = Math.abs(ran.nextInt(comps.size()));
                    if (index < comps.size()) {
                        Competitor comp = comps.get(index);
                        tour.getPool(i).addCompetitor(comp);
                        comps.remove(index);
                    }
                }
            }
            nbCounter--;
        }

        Round r = nextRoundRandom(null);

        tour.addRound(r);
    }

    /**
     *
     * @param competitors ArrayList including competitors (Coachs or Teams)
     */
    private static void generateFirstRoundManual(ArrayList competitors) {

        final Tournament tour = Tournament.getTournament();

        tour.setRoundRobin(false);
        @SuppressWarnings("unchecked")
        final ArrayList<Competitor> comps = new ArrayList<>(competitors);
        final Round r = new Round();
        //final Calendar cal = Calendar.getInstance();
        r.setCurrentHour();
        while (comps.size() > 0) {

            if (comps.size() == 3) {
                if (Tournament.getTournament().getParams().isTeamTournament()
                        && (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
                    Team t1 = (Team) comps.get(0);
                    Team t2 = (Team) comps.get(1);
                    Team t3 = (Team) comps.get(2);

                    int nbMates = Tournament.getTournament().getParams().getTeamMatesNumber();
                    TeamMatch tm1 = new TeamMatch(r);
                    TeamMatch tm2 = new TeamMatch(r);
                    TeamMatch tm3 = new TeamMatch(r);

                    ArrayList<Coach> tc1 = new ArrayList<>();
                    ArrayList<Coach> tc2 = new ArrayList<>();
                    ArrayList<Coach> tc3 = new ArrayList<>();
                    for (int i = 0; i < nbMates; i++) {
                        tc1.add(t1.getCoach(i));
                        tc2.add(t2.getCoach(i));
                        tc3.add(t3.getCoach(i));
                    }

                    setManualHalfTeamMatch(nbMates, t1, t2, tc1, tc2, r);
                    setManualHalfTeamMatch(nbMates, t1, t3, tc1, tc3, r);
                    setManualHalfTeamMatch(nbMates, t3, t2, tc3, tc2, r);

                    comps.clear();

                }
            } else {

                Competitor c = comps.get(0);
                comps.remove(c);
                ArrayList<Competitor> possible = c.getPossibleOpponents(comps, r);

                Object[] possibilities = new Object[possible.size()];
                for (int i = 0; i < possible.size(); i++) {
                    possibilities[i] = possible.get(i).getDecoratedName();
                }

                final String name = c.getDecoratedName();

                String opp = null;

                if (possibilities.length > 0) {
                    while (opp == null) {
                        opp = (String) JOptionPane.showInputDialog(
                                MainFrame.getMainFrame(),
                                Translate.translate(CS_ChooseOpponentFor)
                                + " " + name,
                                Translate.translate(CS_ChoosOpponent),
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                possibilities,
                                possibilities[0]);
                    }

                    for (int i = 0; i < comps.size(); i++) {
                        Competitor tmpOpp = possible.get(i);
                        final String tmpString = tmpOpp.getDecoratedName();

                        if (opp.equals(tmpString)) {
                            if (tmpOpp instanceof Team) {
                                TeamMatch tm = new TeamMatch(r);
                                tm.setCompetitor1(c);
                                tm.setCompetitor2(tmpOpp);
                                r.addMatch(tm);
                                JdgPairing jdg = new JdgPairing(null, true, (Team) c, (Team) tmpOpp, r, tm);
                                jdg.setVisible(true);

                                tmpOpp.addMatch(tm);
                                c.addMatch(tm);
                            } else {
                                c.addMatch(tmpOpp, r);
                            }

                            comps.remove(tmpOpp);
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }

        tour.addRound(r);
    }

    private static void setManualHalfTeamMatch(int nbMates, Team t1, Team t2, ArrayList<Coach> tc1, ArrayList<Coach> tc2, Round r) {
        TeamMatch tm = new TeamMatch(r);
        for (int i = 0; i < nbMates / 2; i++) {
            // tc1.get(i) coach.
            Object[] possibilities = new Object[tc2.size()];
            for (int j = 0; j < tc2.size(); j++) {
                possibilities[j] = tc2.get(i).getDecoratedName();
            }

            final String name = tc1.get(i).getDecoratedName();

            String opp = null;

            opp = (String) JOptionPane.showInputDialog(
                    MainFrame.getMainFrame(),
                    Translate.translate(CS_ChooseOpponentFor)
                    + " " + name,
                    Translate.translate(CS_ChoosOpponent),
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    possibilities,
                    possibilities[0]);

            for (int j = 0; j < tc2.size(); j++) {
                if (opp.equals(possibilities[j])) {
                    CoachMatch mc = new CoachMatch(r);
                    mc.setCompetitor1(tc2.get(j));
                    tc2.get(j).addMatch(mc);
                    tc2.remove(j);
                    mc.setCompetitor2(tc1.get(i));
                    tc1.get(i).addMatch(mc);
                    tc1.remove(i);
                    tm.addMatch(mc);
                }
            }
        }

        tm.setCompetitor1(t1);
        tm.setCompetitor2(t2);
        t1.addMatch(tm);
        t2.addMatch(tm);

        r.addMatch(tm);
    }

    /**
     *
     * @param competitors
     * @param random
     * @param naf
     */
    private static void generateFirstRoundOrder(ArrayList competitors, final boolean random, final boolean naf) {

        final Tournament tour = Tournament.getTournament();

        tour.setRoundRobin(false);
        final Round r = new Round();
        //final Calendar cal = Calendar.getInstance();
        r.setCurrentHour();

        @SuppressWarnings("unchecked")
        final ArrayList<Competitor> shuffle = new ArrayList<>(competitors);
        if (naf) {
            Collections.sort(shuffle);
        } else {
            if (random) /* Aléatoire */ {
                Collections.shuffle(shuffle);
            }
        }
        r.clearMatchs();

        while (shuffle.size() > 0) {
            if (shuffle.size() == 3) {
                // In this specific case of odd number of teams with even number of player, the teams must be splitted in 2
                if (Tournament.getTournament().getParams().isTeamTournament()
                        && (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
                    Team t1 = (Team) shuffle.get(0);
                    Team t2 = (Team) shuffle.get(1);
                    Team t3 = (Team) shuffle.get(2);

                    int nbMates = Tournament.getTournament().getParams().getTeamMatesNumber();

                    ArrayList<Coach> tc1 = new ArrayList<>();
                    ArrayList<Coach> tc2 = new ArrayList<>();
                    ArrayList<Coach> tc3 = new ArrayList<>();
                    for (int i = 0; i < nbMates; i++) {
                        tc1.add(t1.getCoach(i));
                        tc2.add(t2.getCoach(i));
                        tc3.add(t3.getCoach(i));
                    }

                    if (random) {
                        Collections.shuffle(tc1);
                        Collections.shuffle(tc2);
                        Collections.shuffle(tc3);
                    }
                    // T1.1 vs T2.1
                    // T1.1 vs T3.1
                    // T2.2 vs T3.2

                    setHalfTeamMatch(nbMates, t1, t2, tc1, tc2, r);
                    setHalfTeamMatch(nbMates, t1, t3, tc1, tc3, r);
                    setHalfTeamMatch(nbMates, t3, t2, tc3, tc2, r);

                    shuffle.clear();
                }
            } else {
                Competitor c = shuffle.get(0);
                shuffle.remove(c);
                ArrayList<Competitor> possible = c.getPossibleOpponents(shuffle, r);
                Competitor opp = possible.get(0);
                c.addMatch(possible.get(0), r);
                shuffle.remove(possible.get(0));

                c.roundCheck(r);
                opp.roundCheck(r);
            }
        }

        tour.addRound(r);
    }

    private static void set3TeamsMatches(Team t1, Team t2, Team t3, Round r) {
        int nbMates = Tournament.getTournament().getParams().getTeamMatesNumber();
        Tournament tour = Tournament.getTournament();

        ArrayList<Coach> tc1 = new ArrayList<>();
        ArrayList<Coach> tc2 = new ArrayList<>();
        ArrayList<Coach> tc3 = new ArrayList<>();

        for (int j = 0; j < nbMates; j++) {
            tc1.add(t1.getCoach(j));
            tc2.add(t2.getCoach(j));
            tc3.add(t3.getCoach(j));
        }

        // Ordering coaches
        final ArrayList<Round> vs = new ArrayList<>();
        for (int j = 0; j < tour.getRoundsCount(); j++) {
            if (tour.getRound(j).getHour().before(r.getHour())) {
                vs.add(tour.getRound(j));
            }
        }
        vs.add(r);

        switch (tour.getParams().getTeamIndivPairing()) {
            // Ranking
            case RANKING:
                if (vs.size() == 1) {
                    Collections.shuffle(tc1);
                    Collections.shuffle(tc2);
                    Collections.shuffle(tc3);
                } else {
                    final ArrayList<ObjectRanking> coachs1 = Generation.subRanking(tc1, vs, true);
                    final ArrayList<ObjectRanking> coachs2 = Generation.subRanking(tc2, vs, true);
                    final ArrayList<ObjectRanking> coachs3 = Generation.subRanking(tc3, vs, true);
                    tc1.clear();
                    tc2.clear();
                    tc3.clear();
                    for (int k = 0; k < coachs1.size(); k++) {
                        tc1.add((Coach) coachs1.get(k).getObject());
                        tc2.add((Coach) coachs2.get(k).getObject());
                        tc3.add((Coach) coachs3.get(k).getObject());
                    }
                }
                break;
            // Manual
            case FREE:
                // Keep Registration order. Organizers can arrange the match later.
                break;
            // genRandom
            case RANDOM:
                Collections.shuffle(tc1);
                Collections.shuffle(tc2);
                Collections.shuffle(tc3);
                break;
            // NAF
            case NAF:
                Collections.sort(tc1);
                Collections.sort(tc2);
                Collections.sort(tc3);
                break;
        }

        // T1.1 vs T2.1
        // T1.1 vs T3.1
        // T2.2 vs T3.2
        setHalfTeamMatch(nbMates, t1, t2, tc1, tc2, r);
        setHalfTeamMatch(nbMates, t1, t3, tc1, tc3, r);
        setHalfTeamMatch(nbMates, t3, t2, tc3, tc2, r);
    }

    private static void setHalfTeamMatch(int nbMates, Team t1, Team t2, ArrayList<Coach> tc1, ArrayList<Coach> tc2, Round r) {
        TeamMatch tm = new TeamMatch(r);

        for (int i = 0; i < nbMates / 2; i++) {
            CoachMatch mc = new CoachMatch(r);
            mc.setCompetitor1(tc1.get(0));
            mc.setCompetitor2(tc2.get(0));
            tc1.get(0).addMatch(mc);
            tc2.get(0).addMatch(mc);
            tc1.remove(0);
            tc2.remove(0);
            tm.addMatch(mc);
        }

        tm.setCompetitor1(t1);
        tm.setCompetitor2(t2);
        t1.addMatch(tm);
        t2.addMatch(tm);
        r.addMatch(tm);
    }

    /**
     *
     * @param n
     * @return
     */
    private static int factorielle(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorielle(n - 1);
        }
    }

    /**
     *
     * @param competitors
     * @param r
     */
    private static void genBalanced(ArrayList competitors, Round r) {

        /*        final Tournament tour = Tournament.getTournament();
         final ArrayList<Round> rounds = tour.getRounds();
        
         tour.mRoundRobin = false;
         final Calendar cal = Calendar.getInstance();
         r.setCurrentHour();
        
         final ArrayList<Competitor> competitors2 = new ArrayList<>(competitors);
         Collections.shuffle(competitors2);
        
         ArrayList RotationList = new ArrayList();
        
        
         // According to each competitor (Coach), evaluates previous rounds
         HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC = new HashMap<>();
         HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT = new HashMap<>();
         for (int i = 0; i < competitors.size(); i++) {
         Coach comp = (Coach) competitors.get(i);
         evaluationPreviousC.put(comp, comp.getTeamOppositionCount(Tournament.getTournament().getTeams()));
         if (!evaluationPreviousT.containsKey(comp.mTeamMates)) {
         evaluationPreviousT.put(comp.mTeamMates, comp.getTeamOppositionCount(Tournament.getTournament().getTeams()));
         }
         }
        
         rotateListForBalanced(competitors2, RotationList, evaluationPreviousC, evaluationPreviousT);
        
         if (RotationList.size() > 0) {
         ArrayList<Competitor> l = (ArrayList<Competitor>) RotationList.get(0);
         for (int i = 0; i < l.size() / 2; i++) {
         Competitor c1 = l.get(2 * i);
         Competitor c2 = l.get(2 * i + 1);
         CoachMatch cm = new CoachMatch(r);
         cm.mCompetitor1 = c1;
         cm.mCompetitor2 = c2;
         r.getMatchs().add(cm);
        
         System.out.println(i + ". " + c1.mName + " vs " + c2.mName);
         }
         } else {
         genRandom(competitors, r);
         }*/
    }

    /**
     *
     * @param competitors
     */
    private static void generateFirstRoundRobin(ArrayList competitors) {

        final Tournament tour = Tournament.getTournament();

        tour.setRoundRobin(false);
        //final Calendar cal = Calendar.getInstance();
        final boolean home_away = (JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH ALLER-RETOUR ?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ALLER-RETOUR"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        final ArrayList<Competitor> comps = new ArrayList<>(competitors);

        if (comps.size() % 2 == 1) {
            if (tour.getParams().isTeamTournament()) {
                comps.add(Team.getNullTeam());
            } else {
                comps.add(Coach.getNullCoach());
            }
        }

        Collections.shuffle(comps);

        final ArrayList<Competitor> c1part = new ArrayList<>();
        final ArrayList<Competitor> c2part = new ArrayList<>();

        for (int i = 0; i < comps.size() / 2; i++) {
            c1part.add(comps.get(2 * i));
            c2part.add(comps.get(2 * i + 1));
        }

        int nbrounds = comps.size() - 1;
        if (home_away) {
            nbrounds = 2 * nbrounds;
        }

        boolean complete = false;
        if (c2part.get(0) instanceof Team) {
            complete = (JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND ROBIN INTEGRAL (INCLUANT TOUS LES JOUEURS)?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND ROBIN INTEGRAL"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        }
        for (int i = 0; i < nbrounds; i++) {
            final Round r = new Round();
            r.setCurrentHour();

            for (int j = 0; j < c2part.size(); j++) {

                c1part.get(j).addMatchRoundRobin(c2part.get(j), r, complete);
            }

            // Move coaches for round robin / ribbon method
            Competitor c_tmp = c2part.get(0);
            c2part.remove(c_tmp);
            c1part.add(1, c_tmp);
            c_tmp = c1part.get(c1part.size() - 1);
            c1part.remove(c1part.size() - 1);
            c2part.add(c_tmp);

            tour.addRound(r);
        }
    }

    /**
     *
     * @param competitors
     */
    private static void generateFirstRoundCup(ArrayList competitors) {

        final Tournament tour = Tournament.getTournament();

        // Analyze number of players
        final int nb_comps = competitors.size();
        final ArrayList<Competitor> comps;
        comps = new ArrayList<>(competitors);
        Collections.shuffle(comps);
        int nb_tmp = 1;
        int nbrounds = 0;
        while (nb_tmp < nb_comps) {
            nb_tmp *= 2;
            nbrounds++;
        }
        while (comps.size() < nb_tmp) {
            if (comps.get(0) instanceof Coach) {
                comps.add(Coach.getNullCoach());
            } else {
                comps.add(Team.getNullTeam());
            }
        }
        int nb_matchs = nb_tmp / 2;

        final Round r = new Round();
        r.setCurrentHour();

        /*r.setLooserCup(
                JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),
                        Translate.translate(CS_IsItDoubleKickTournament),
                        Translate.translate(Translate.CS_Cup), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);*/
        // there is nb_tmp/matchs
        for (int i = 0; i < nb_matchs / 2; i++) {
            comps.get(2 * i).addMatch(comps.get(nb_tmp - 2 * i - 1), r);
        }
        for (int i = 0; i < nb_matchs / 2; i++) {
            comps.get(2 * i + 1).addMatch(comps.get(nb_tmp - 2 * i - 2), r);
        }

        r.setCup(true);
        r.setCupMaxTour(nbrounds);
        r.setCupTour(0);
        tour.addRound(r);
    }

    /**
     *
     * @param competitors
     * @param datas
     * @param r
     * @return
     */
    private static Round genSwiss(final ArrayList competitors, final MjtRanking datas, final Round r, boolean topdown) {

        final ArrayList<ObjectRanking> datas_tmp = new ArrayList<>();

        for (int i = 0; i < datas.getRowCount(); i++) {
            ObjectRanking data = datas.getSortedObject(i);
            datas_tmp.add(data);
        }

        return genSwiss(competitors, datas_tmp, r, topdown);
    }

    /**
     *
     * Generates a swiss pairing for this round
     *
     * @param competitors list of competitors
     * @param datas ranking data
     * @param r current round
     * @return new round
     */
    @SuppressWarnings("unchecked")
    private static Round genSwiss(final ArrayList competitors, final ArrayList<ObjectRanking> datas, final Round r, boolean topdown) {

        final Tournament tour = Tournament.getTournament();
        //final Calendar cal = Calendar.getInstance();
        r.setCurrentHour();
        final ArrayList<ObjectRanking> datas_tmp = new ArrayList<>(datas);

        ArrayList comps = new ArrayList(competitors);

        // Remove inactives coaches
        if (competitors.get(0) instanceof Coach) {
            for (int i = 0; i < competitors.size(); i++) {
                final Coach c = (Coach) competitors.get(i);
                if (!c.isActive()) {
                    for (int j = 0; j < datas_tmp.size(); j++) {
                        if (datas_tmp.get(j).getObject().equals(c)) {
                            datas_tmp.remove(j);
                        }
                    }
                    comps.remove(c);
                }
            }
        }

        ArrayList<ObjectRanking> toRemove=new ArrayList<>();
        // Remove Competitors from data if not in datas
        for (ObjectRanking or:datas_tmp)
        {
            Object o=or.getObject();
            if (o instanceof Competitor)
            {
                if (!competitors.contains(o))
                {
                    toRemove.add(or);
                }
            }
        }
        for(ObjectRanking or:toRemove)
        {
            datas_tmp.remove(or);
        }
        
        // If number of players is odd; add one null a the end
        if (comps.size() % 2 == 1) {
            if (comps.get(0) instanceof Coach) {
                comps.add(Coach.getNullCoach());
            }

            if (comps.get(0) instanceof Team) {
                if (Tournament.getTournament().getParams().getTeamMatesNumber() % 2 == 1) {
                    comps.add(Team.getNullTeam());
                }
            }
        }

        // If the number of round is more than the number of players, no switch
        if ((comps.size() - 1 <= tour.getRoundsCount())
                || ((comps.get(0) instanceof Team)
                && (comps.size() % 2 == 1)
                && (((comps.size() - 1) / 2) - 1 <= tour.getRoundsCount()))) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    Translate.translate(CS_NotEnoughRoundToAvoidSameMatch));

            while (datas_tmp.size() > 0) {
                if (datas_tmp.size() == 3) {
                    if (Tournament.getTournament().getParams().isTeamTournament()
                            && Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {

                        // Allocates the 3 last teams
                        Team t1 = (Team) datas_tmp.get(0).getObject();
                        Team t2 = (Team) datas_tmp.get(1).getObject();
                        Team t3 = (Team) datas_tmp.get(2).getObject();

                        // Distribute the 3 last teams. If they have already played,it will be treated during roundcheck
                        set3TeamsMatches(t1, t2, t3, r);
                        datas_tmp.clear();

                    } else {
                        Competitor c1 = (Competitor) datas_tmp.get(0).getObject();
                        if (c1 instanceof Coach) {
                            c1.addMatch(Coach.getNullCoach(), r);
                        }
                        if (c1 instanceof Team) {
                            c1.addMatch(Team.getNullTeam(), r);
                        }
                    }
                } else {
                    Competitor c1 = (Competitor) datas_tmp.get(0).getObject();
                    Competitor c2 = (Competitor) datas_tmp.get(1).getObject();
                    c1.addMatch(c2, r);
                    datas_tmp.remove(0);
                    datas_tmp.remove(0);
                }
            }
        } else {
            ArrayList<ObjectRanking> datas2 = new ArrayList<>(datas_tmp);

            while (datas2.size() > 0) {

                if ((datas2.size() == 3)
                        && Tournament.getTournament().getParams().isTeamTournament()
                        && Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {

                    // Allocates the 3 last teams
                    // Find the 2 last teams which have not played against the last one
                    Team opp1 = (Team) datas2.get(0).getObject();
                    Team opp2 = (Team) datas2.get(1).getObject();
                    Team t3 = (Team) datas2.get(2).getObject();

                    ArrayList<Team> opps = t3.getPossibleOpponents(competitors, r);

                    Team t1 = null;
                    Team t2 = null;

                    if (opps.size() < 2) {
                        t1 = opp1;
                        t2 = opp2;
                    } else {
                        for (int cpt = opps.size() - 1; (cpt >= 0); cpt--) {
                            Team t = (Team) opps.get(cpt);
                            if (t3.canPlay(t, r)) {
                                if (t1 == null) {
                                    t1 = t;
                                } else {
                                    if (t2 == null) {
                                        t2 = t;
                                        break;
                                    }
                                }
                            }
                        }

                        if ((t1 == null) || (t2 == null)) {
                            t1 = opp1;
                            t2 = opp2;
                        }

                        // if needed swap t1 and t2 in the previously allocated matches.
                        TeamMatch tm1 = (TeamMatch) t1.getMatch(t1.getMatchCount() - 1);
                        if (tm1.getMatchCount() == Tournament.getTournament().getParams().getTeamMatesNumber()) {
                            if (r.containsMatch(tm1)) {

                                t1.removeMatch(tm1);
                                opp1.addMatch(tm1);

                                // Swap t1 with opp1
                                if (tm1.getCompetitor1() == t1) {
                                    tm1.setCompetitor1(opp1);
                                    for (int cpt2 = 0; cpt2 < tm1.getMatchCount(); cpt2++) {
                                        CoachMatch tc1 = tm1.getMatch(cpt2);
                                        if (((Coach) tc1.getCompetitor1()).getTeamMates() == t1) {
                                            tc1.getCompetitor1().removeMatch(tc1);
                                            tc1.setCompetitor1(opp1.getCoach(cpt2));

                                        }

                                        if (((Coach) tc1.getCompetitor2()).getTeamMates() == t1) {
                                            tc1.getCompetitor2().removeMatch(tc1);
                                            tc1.setCompetitor2(opp1.getCoach(cpt2));
                                        }
                                        opp1.getCoach(cpt2).addMatch(tc1);
                                    }
                                }

                                if (tm1.getCompetitor2() == t1) {
                                    tm1.setCompetitor2(opp1);
                                    for (int cpt2 = 0; cpt2 < tm1.getMatchCount(); cpt2++) {
                                        CoachMatch tc1 = tm1.getMatch(cpt2);

                                        if (((Coach) tc1.getCompetitor1()).getTeamMates() == t1) {
                                            tc1.getCompetitor1().removeMatch(tc1);
                                            tc1.setCompetitor1(opp1.getCoach(cpt2));

                                        }

                                        if (((Coach) tc1.getCompetitor2()).getTeamMates() == t1) {
                                            tc1.getCompetitor2().removeMatch(tc1);
                                            tc1.setCompetitor2(opp1.getCoach(cpt2));
                                        }
                                        opp1.getCoach(cpt2).addMatch(tc1);
                                    }
                                }
                            }
                        }

                        TeamMatch tm2 = (TeamMatch) t2.getMatch(t2.getMatchCount() - 1);
                        if (tm2.getMatchCount() == Tournament.getTournament().getParams().getTeamMatesNumber()) {
                            if (r.containsMatch(tm2)) {
                                t2.removeMatch(tm2);
                                opp2.addMatch(tm2);

                                // Swap t1 with opp1
                                if (tm2.getCompetitor1() == t2) {
                                    tm2.setCompetitor1(opp2);
                                    for (int cpt2 = 0; cpt2 < tm2.getMatchCount(); cpt2++) {
                                        CoachMatch tc2 = tm2.getMatch(cpt2);

                                        if (((Coach) tc2.getCompetitor1()).getTeamMates() == t2) {
                                            tc2.getCompetitor1().removeMatch(tc2);
                                            tc2.setCompetitor1(opp2.getCoach(cpt2));

                                        }

                                        if (((Coach) tc2.getCompetitor2()).getTeamMates() == t2) {
                                            tc2.getCompetitor2().removeMatch(tc2);
                                            tc2.setCompetitor2(opp2.getCoach(cpt2));
                                        }
                                        opp2.getCoach(cpt2).addMatch(tc2);
                                    }
                                }

                                if (tm2.getCompetitor2() == t2) {
                                    tm2.setCompetitor2(opp2);
                                    for (int cpt2 = 0; cpt2 < tm2.getMatchCount(); cpt2++) {
                                        CoachMatch tc2 = tm2.getMatch(cpt2);
                                        if (((Coach) tc2.getCompetitor1()).getTeamMates() == t2) {
                                            tc2.getCompetitor1().removeMatch(tc2);
                                            tc2.setCompetitor1(opp2.getCoach(cpt2));

                                        }

                                        if (((Coach) tc2.getCompetitor2()).getTeamMates() == t2) {
                                            tc2.getCompetitor2().removeMatch(tc2);
                                            tc2.setCompetitor2(opp2.getCoach(cpt2));
                                        }
                                        opp2.getCoach(cpt2).addMatch(tc2);
                                    }
                                }
                            }
                        }

                    }
                    set3TeamsMatches(t1, t2, t3, r);
                    datas2.clear();

                } else {
                    int index = 0;
                    boolean byEnd = false;
                    // Alternate affectation top then bottom
                    if (topdown) {
                        if ((datas2.size() / 2) % 2 == 1) {
                            index = datas2.size() - 1;
                            byEnd = true;
                        }
                    }
                    Competitor c1 = (Competitor) datas2.get(index).getObject();
                    datas2.remove(index);

                    ArrayList<Competitor> opponents = new ArrayList<>();
                    for (int i = 0; i < datas2.size(); i++) {
                        if (tour.getParams().isTeamTournament() && (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {
                            Coach opp = (Coach) datas2.get(i).getObject();
                            Coach self = (Coach) c1;
                            if (self.getTeamMates() != opp.getTeamMates()) {
                                opponents.add((Competitor) datas2.get(i).getObject());
                            }
                        } else {
                            opponents.add((Competitor) datas2.get(i).getObject());
                        }
                    }

                    ArrayList<Competitor> possible = c1.getPossibleOpponents(opponents, r);
                    Competitor c2 = null;
                    if (!possible.isEmpty()) {
                        if (!byEnd) {
                            c2 = possible.get(0);
                        } else {
                            c2 = possible.get(possible.size() - 1);
                        }
                        //int index = 0;
                        for (int i = 0; i < datas2.size(); i++) {
                            if (datas2.get(i).getObject().equals(c2)) {
                                datas2.remove(i);
                                break;
                            }
                        }
                        //datas2.remove(index);
                    } else {

                        if (((tour.getParams().isTeamTournament() && (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)))
                                && (opponents.size() % 2 == 0)) {
                            int nbMatchs = c1.getMatchCount();
                            // Remove Find possible opponent in previous allocated Match                        
                            for (int i = r.getMatchsCount() - 1; i >= 0; i--) {
                                Match m;
                                if (!topdown) {
                                    m = r.getMatch(i);
                                } else {
                                    //inverted index
                                    int j = i - r.getMatchsCount() + 1;

                                    if (!byEnd) {
                                        if (j % 2 == 0) {
                                            m = r.getMatch(r.getMatchsCount() / 2 - j / 2);
                                        } else {
                                            m = r.getMatch(r.getMatchsCount() / 2 + j / 2);
                                        }
                                    } else {
                                        if (j % 2 == 0) {
                                            m = r.getMatch(r.getMatchsCount() / 2 + 1 + j / 2);
                                        } else {
                                            m = r.getMatch(r.getMatchsCount() / 2 + 1 - j / 2);
                                        }
                                    }
                                }
                                Competitor cm1 = m.getCompetitor1();
                                Competitor cm2 = m.getCompetitor2();
                                Coach copp1 = (Coach) cm1;
                                Coach copp2 = (Coach) cm2;
                                if (copp2.getTeamMates() != ((Coach) c1).getTeamMates()) {
                                    if (copp1.getTeamMates() != ((Coach) c1).getTeamMates()) {
                                        opponents.add(copp2);
                                    }
                                }
                                if (copp1.getTeamMates() != ((Coach) c1).getTeamMates()) {
                                    if (copp2.getTeamMates() != ((Coach) c1).getTeamMates()) {
                                        opponents.add(copp1);
                                    }
                                }
                                possible = c1.getPossibleOpponents(opponents, r);
                                if (!possible.isEmpty()) {
                                    cm1.removeMatch(m);
                                    cm2.removeMatch(m);
                                    r.removeMatch(m);
                                    c2 = possible.get(possible.size() - 1);
                                    // Re-Add removed data to data2
                                    Competitor other = null;
                                    if (c2 == cm1) {
                                        other = cm2;
                                    }
                                    if (c2 == cm2) {
                                        other = cm1;
                                    }
                                    // Find cm2 data and re-add it at beginning
                                    for (int j = 0; j < datas_tmp.size(); j++) {
                                        if (datas_tmp.get(j).getObject().equals(other)) {
                                            datas2.add(0, datas_tmp.get(j));
                                        }
                                    }

                                    break;
                                }
                            }
                        } else {
                            if (c1 instanceof Team) {
                                c2 = Team.getNullTeam();
                            }
                            if (c1 instanceof Coach) {
                                c2 = Coach.getNullCoach();
                            }
                        }
                    }
                    if (c2 != null) {
                        c1.addMatch(c2, r);
                        // reorder matchset
                        if (topdown) {
                            int middle = r.getMatchsCount() / 2;
                            Match m = c1.getMatch(c1.getMatchCount() - 1);
                            r.setMatchPosition(m, middle + (r.getMatchsCount() - 1) % 2);
                        }
                    }
                }

            }
            ((Competitor) competitors.get(0)).roundCheck(r);
        }
        return r;
    }

    /**
     *
     * @param competitors
     * @param datas
     * @param r
     * @return
     */
    @SuppressWarnings("unchecked")
    private static Round genQSwiss(final ArrayList competitors, final MjtRanking datas, Round r2
    ) {

        final Tournament tour = Tournament.getTournament();

        Round r = new Round();
        r.setCurrentHour();

        final ArrayList<ObjectRanking> datas_tmp;
        datas_tmp = new ArrayList<>();

        for (int i = 0; i < datas.getRowCount(); i++) {
            ObjectRanking data = datas.getSortedObject(i);
            datas_tmp.add(data);
        }

        if (competitors.get(0) instanceof Coach) {
            for (int i = 0; i < competitors.size(); i++) {
                final Coach c = (Coach) competitors.get(i);
                if (!c.isActive()) {
                    for (int j = 0; j < datas_tmp.size(); j++) {
                        if (datas_tmp.get(j).getObject().equals(c)) {
                            datas_tmp.remove(j);
                        }
                    }
                }
            }
        }
        // part Ranking in 4 groups
        int idx1;
        int idx2;
        int idx3;
        int idx4;
        final int size = datas.getRowCount();
        if (size < 4) {
            JOptionPane.showMessageDialog(
                    MainFrame.getMainFrame(),
                    Translate.translate(CS_AccleratedSwissMethodIsNotApplicable));
            r = null;
        } else {
            int half_size = size / 2;
            if (half_size % 2 != 0) {
                half_size -= 1;
            }
            idx1 = 0;
            idx2 = half_size / 2;
            idx3 = half_size;
            idx4 = (size - half_size) / 2 + half_size;
            final ArrayList<ObjectRanking> datas_1 = new ArrayList<>();
            final ArrayList<ObjectRanking> datas_2 = new ArrayList<>();
            final ArrayList<ObjectRanking> datas_3 = new ArrayList<>();
            final ArrayList<ObjectRanking> datas_4 = new ArrayList<>();
            for (int i = idx1; i < idx2; i++) {
                datas_1.add(datas.getSortedObject(i));
            }
            for (int i = idx2; i < idx3; i++) {
                datas_2.add(datas.getSortedObject(i));
            }
            for (int i = idx3; i < idx4; i++) {
                datas_3.add(datas.getSortedObject(i));
            }
            for (int i = idx4; i < size; i++) {
                datas_4.add(datas.getSortedObject(i));
            }
            if (competitors.size() - 1 <= tour.getRoundsCount()) {
                for (int i = 0; i < datas_1.size(); i++) {
                    Competitor c1 = (Competitor) datas_1.get(i).getObject();
                    Competitor c2 = (Competitor) datas_2.get(i).getObject();
                    c1.addMatch(c2, r);
                }
                for (int i = 0; i < datas_3.size(); i++) {
                    Competitor c1 = (Competitor) datas_3.get(i).getObject();
                    Competitor c2 = (Competitor) datas_4.get(i).getObject();
                    c1.addMatch(c2, r);
                }
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                        Translate.translate(CS_NotEnoughRoundToAvoidSameMatch));
            } else {
                final ArrayList<ObjectRanking> datas_tmp1 = new ArrayList<>(datas_1);
                final ArrayList<ObjectRanking> datas_tmp2 = new ArrayList<>(datas_2);
                while ((datas_tmp1.size() > 0) && (datas_tmp2.size() > 0)) {
                    Competitor c1 = (Competitor) datas_1.get(0).getObject();
                    datas_tmp1.remove(0);

                    ArrayList opponents = new ArrayList();
                    for (int i = 0; i < datas_tmp2.size(); i++) {
                        opponents.add(datas_tmp2.get(i));
                    }
                    ArrayList<Competitor> possible = c1.getPossibleOpponents(opponents, r);
                    Competitor c2 = possible.get(0);
                    int index = opponents.indexOf(c2);
                    datas_tmp2.remove(index);
                    c1.addMatch(c2, r);
                }

                final ArrayList<ObjectRanking> datas_tmp3 = new ArrayList<>(datas_3);
                final ArrayList<ObjectRanking> datas_tmp4 = new ArrayList<>(datas_4);

                while ((datas_tmp3.size() > 0) && (datas_tmp4.size() > 0)) {
                    // Odd number of teams management
                    if ((datas_tmp3.size() + datas_tmp4.size() == 3)) {
                        if (tour.getParams().isTeamTournament() && (tour.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
                            Competitor c1 = (Competitor) datas_3.get(0).getObject();
                            Team t1 = (Team) c1;
                            Competitor c2 = (Competitor) datas_4.get(0).getObject();
                            Team t2 = (Team) c2;
                            Competitor c3 = (Competitor) datas_4.get(1).getObject();
                            Team t3 = (Team) c3;

                            set3TeamsMatches(t1, t2, t3, r);
                        }
                    } else {

                        Competitor c1 = (Competitor) datas_3.get(0).getObject();
                        datas_tmp3.remove(0);

                        ArrayList opponents = new ArrayList();
                        for (int i = 0; i < datas_tmp4.size(); i++) {
                            opponents.add(datas_tmp4.get(i));
                        }
                        ArrayList<Competitor> possible = c1.getPossibleOpponents(opponents, r);
                        Competitor c2 = possible.get(0);

                        int index = opponents.indexOf(c2);
                        datas_tmp4.remove(index);
                        c1.addMatch(c2, r);
                    }
                }
            }
        }

        ((Competitor) competitors.get(0)).roundCheck(r);
        return r;
    }

    /**
     *
     * @param competitors
     * @param r
     * @return
     */
    private static Round genRandom(final ArrayList competitors, final Round r) {

        //final Calendar cal = Calendar.getInstance();
        r.setCurrentHour();

        @SuppressWarnings("unchecked")
        final ArrayList<Competitor> shuffle = new ArrayList<>(competitors);
        Collections.shuffle(shuffle);
        while (shuffle.size() > 1) {
            // Manage odd number of team with even number of coachs
            if (shuffle.size() == 3) {
                if (Tournament.getTournament().getParams().isTeamTournament() && (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
                    Team t1 = (Team) shuffle.get(0);
                    Team t2 = (Team) shuffle.get(1);
                    Team t3 = (Team) shuffle.get(2);

                    set3TeamsMatches(t1, t2, t3, r);
                } else {
                    Competitor c = shuffle.get(0);
                    shuffle.remove(c);
                    ArrayList<Competitor> opp = c.getPossibleOpponents(shuffle, r);
                    c.addMatch(opp.get(0), r);
                    shuffle.remove(opp.get(0));
                }
            } else {
                Competitor c = shuffle.get(0);
                shuffle.remove(c);
                ArrayList<Competitor> opp = c.getPossibleOpponents(shuffle, r);
                c.addMatch(opp.get(0), r);
                shuffle.remove(opp.get(0));
            }
        }

        ((Competitor) competitors.get(0)).roundCheck(r);

        return r;
    }

    /**
     *
     * @param previous_round
     * @param r
     * @param third_place
     */
    private static void genCup(final Round previous_round, final Round r) {

        Parameters params = Tournament.getTournament().getParams();
        Cup cup = Tournament.getTournament().getCup();
        int roundIndex = previous_round.getCupTour();

        ArrayList<Match> previousMatchs = new ArrayList<>();
        for (int i = 0; i < previous_round.getMatchsCount(); i++) {
            previousMatchs.add(previous_round.getMatch(i));
        }

        ArrayList<Competitor> comps = new ArrayList<Competitor>();

        if (params.isTeamTournament() && params.getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
            for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
                comps.add(Tournament.getTournament().getTeam(i));
            }
        } else {
            for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                comps.add(Tournament.getTournament().getCoach(i));
            }
        }

        ArrayList<Match> nextMatchs = cup.generateMatches(roundIndex + 1, r, previousMatchs, comps);

        /*int nb_match = (int) Math.pow(2, previous_round.getCupMaxTour() - previous_round.getCupTour() - 1);

        final ArrayList<Competitor> _winners = new ArrayList<>();
        final ArrayList<Competitor> _loosers = new ArrayList<>();

        Competitor nullElt;
        if ((params.isTeamTournament()) && (params.getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
            nullElt = Team.getNullTeam();
        } else {
            nullElt = Coach.getNullCoach();
        }

        if (nb_match == 0) {
            if (previous_round.getCupTour() == previous_round.getCupMaxTour() + 1) {
                _loosers.add(previous_round.getMatch(0).getWinner());
            } else {
                _winners.add(previous_round.getMatch(0).getWinner());
                _loosers.add(previous_round.getMatch(1).getWinner());
            }
        }
        for (int i = 0; i < nb_match; i++) {
            _winners.add(previous_round.getMatch(i).getWinner());
            _loosers.add(previous_round.getMatch(i).getLooser());
        }
        if (nb_match > 0) {
            final int option = JOptionPane.showConfirmDialog(
                    MainFrame.getMainFrame(),
                    Translate.translate(CS_DoYouWantToMixDraw),
                    Translate.translate(Translate.CS_Cup), JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                Collections.shuffle(_winners);
                Collections.shuffle(_loosers);
            }
        }
        for (int i = 0; i < (_winners.size() + 1) / 2; i++) {
            Competitor c1 = _winners.get(2 * i);
            Competitor c2;
            if ((2 * i + 1) >= _winners.size()) {
                c2 = nullElt;
            } else {
                c2 = _winners.get(2 * i + 1);
            }
            c1.addMatch(c2, r);
        }

        if (third_place) {
            if (_loosers.size() == 2) {
                for (int i = 0; i < (_loosers.size() + 1) / 2; i++) {
                    Competitor c1 = _loosers.get(2 * i);
                    Competitor c2;
                    if ((2 * i + 1) >= _loosers.size()) {
                        c2 = nullElt;
                    } else {
                        c2 = _loosers.get(2 * i + 1);
                    }
                    c1.addMatch(c2, r);
                }
            }
        }
        if (Tournament.getTournament().getCup().getType() == Cup.ROUND_TYPE.LOOSER) {
            int nb_remaining_match = 0;
            if (previous_round.getCupTour() > 0) {
                nb_remaining_match = nb_match;
            }
            if (previous_round.getCupTour() > 1) {
                nb_remaining_match += nb_match;
            }

            if (nb_match == 1) {
                nb_remaining_match = 3;
            }
            if (nb_match == 0) {
                nb_remaining_match = 1;
                if (previous_round.getCupTour() == previous_round.getCupMaxTour() + 1) {
                    nb_match = 1;
                } else {
                    nb_match = 2;
                }
            }
            for (int i = nb_match; (i < nb_match + nb_remaining_match) && (i < previous_round.getMatchsCount()); i++) {
                _loosers.add(previous_round.getMatch(i).getWinner());
            }

            /**
             * Patch For ensure that the winner of the last looser cup match is
             * pushed into the winners'cup All the coachs presents in winner cup
             * are removed from the looser cup
             
            for (int i = 0; i < r.getMatchsCount(); i++) {
                Competitor c1 = r.getMatch(i).getCompetitor1();
                _loosers.remove(c1);
                Competitor c2 = r.getMatch(i).getCompetitor2();
                _loosers.remove(c2);
            }

            if (_loosers.size() % 2 == 1) {
                if (_loosers.get(0) instanceof Coach) {
                    _loosers.add(Coach.getNullCoach());
                }
                if (_loosers.get(0) instanceof Team) {
                    _loosers.add(Team.getNullTeam());
                }
            }
            for (int i = 0; i < _loosers.size() / 2; i++) {
                _loosers.get(i).addMatch(_loosers.get(_loosers.size() / 2 + i), r);
            }
        }*/
        nextMatchs.forEach((m) -> {
            m.getCompetitor1().addMatch(m.getCompetitor2(), r);
        });
    }

    /**
     *
     * @param r
     * @param datas
     * @param nbPlayers
     */
    private static void genCupFirst(final Round r, final MjtRanking datas, final int nbPlayers) {
        final int nb_match = nbPlayers / 2;
        final ArrayList<Competitor> comps = new ArrayList<>();
        Competitor nullElt;
        if ((datas.getSortedObject(0).getObject()) instanceof Coach) {
            nullElt = Coach.getNullCoach();
        } else {
            nullElt = Team.getNullTeam();
        }
        for (int i = 0; i < nbPlayers; i++) {
            Competitor c;
            if (i < datas.getRowCount()) {
                c = ((Competitor) datas.getSortedObject(i).getObject());
            } else {
                c = nullElt;
            }
            comps.add(c);
        }

        // Reorder to create pairing 1st against last
        ArrayList<Competitor> comps2 = new ArrayList<>();

        for (int i = 0; i < nb_match / 2; i++) {
            comps2.add(comps.get(2 * i));
            comps2.add(comps.get(nbPlayers - 2 * i - 1));
        }
        for (int i = 0; i < nb_match / 2; i++) {
            comps2.add(comps.get(2 * i + 1));
            comps2.add(comps.get(nbPlayers - 2 * i - 2));
        }

        // If Order is a category one, job already done.
        // If order is ranking, already done
        // If order is random
        if (Tournament.getTournament().getCup().getInitialDraw() == Cup.INITIAL_DRAW.RANDOM) {
            Collections.shuffle(comps2);
        }

        // If draw is manual
        if (Tournament.getTournament().getCup().getInitialDraw() == Cup.INITIAL_DRAW.MANUAL) {
            /**
             * Double selection list panel
             */
            JdgManualCupDraw jdg = new JdgManualCupDraw(MainFrame.getMainFrame(), true, comps2);
            jdg.setVisible(true);
        }

        ArrayList<Match> matches = Tournament.getTournament().getCup().generateMatches(0, r, null, comps2);

        matches.forEach((m) -> {
            m.getCompetitor1().addMatch(m.getCompetitor2(), r);
        });
    }

    /**
     *
     * @param round
     * @param r
     */
    private static void finalGenerationStep(final Round round, final Round r) {
        final Tournament tour = Tournament.getTournament();
        if (round != null) {
            int i = 0;
            while (i < tour.getRoundsCount()) {
                if (tour.getRound(i).getHour().after(round.getHour())) {
                    tour.removeRound(i);
                } else {
                    i++;
                }
            }
        }
        tour.addRound(r);

        for (int i = 0; i < r.getCoachMatchs().size(); i++) {
            final Match m = r.getCoachMatchs().get(i);
            m.getCompetitor1().addMatch(m);
            m.getCompetitor2().addMatch(m);
        }

        /**
         * Application of a provate joke
         */
        applyPortugal(r);

        saveTemporaryTournament();
    }

    /**
     *
     * @param team
     * @param rounds
     * @return
     */
    public static ArrayList<ObjectRanking> subRanking(final Team team, final ArrayList<Round> rounds, boolean sum) {
        ArrayList<Coach> array = new ArrayList<>();
        for (int i = 0; i < team.getCoachsCount(); i++) {
            array.add(team.getCoach(i));
        }
        return subRanking(array, rounds, sum);
    }

    /**
     *
     * @param coachs
     * @param rounds
     * @param sum
     * @return
     */
    public static ArrayList<ObjectRanking> subRanking(final ArrayList<Coach> coachs, final ArrayList<Round> rounds, boolean sum) {

        final Parameters params = Tournament.getTournament().getParams();

        final ArrayList<ObjectRanking> result = new ArrayList<>();
        for (int j = 0; j < coachs.size(); j++) {
            final Coach c = coachs.get(j);
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int k = 0; k < c.getMatchCount(); k++) {
                final CoachMatch m = (CoachMatch) c.getMatch(k);
                if (rounds.contains(m.getRound())) {
                    if (!sum) {
                        value1 = m.getValue(1, c);
                        value2 = m.getValue(2, c);
                        value3 = m.getValue(3, c);
                        value4 = m.getValue(4, c);
                        value5 = m.getValue(5, c);
                    } else {
                        value1 += m.getValue(1, c);
                        value2 += m.getValue(2, c);
                        value3 += m.getValue(3, c);
                        value4 += m.getValue(4, c);
                        value5 += m.getValue(5, c);
                    }
                }
            }
            final ObjectRanking obj = new ObjectRanking(c, value1, value2, value3, value4, value5);
            result.add(obj);
        }
        Collections.sort(result);
        return result;
    }

    /**
     *
     * @param round
     * @param roundnumber
     * @return
     */
    private static Round nextRoundQSwiss(final Round round, final int roundnumber) {

        final Tournament tour = Tournament.getTournament();

        MjtRanking datas;
        // First: Create ranking
        // previous rounds

        Round r = new Round();
        if (tour.getPoolCount() == 0) {

            datas = getSortedRankingData(roundnumber);
            if (tour.getParams().isTeamTournament()) {
                if (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                    final ArrayList<Coach> coaches = new ArrayList<>();
                    for (int i = 0; i < tour.getCoachsCount(); i++) {
                        coaches.add(tour.getCoach(i));
                    }
                    genQSwiss(coaches, datas, r);
                } else {
                    ArrayList<Team> teams = new ArrayList<>();
                    for (int cpt = 0; cpt < tour.getTeamsCount(); cpt++) {
                        teams.add(tour.getTeam(cpt));
                    }
                    Generation.genQSwiss(teams, datas, r);
                }
            } else {
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                            Translate.translate(CS_OddNumberOfActiveCoachs),
                            Translate.translate(CS_GenerationError), JOptionPane.WARNING_MESSAGE);
                    r = null;
                } else {
                    final ArrayList<Coach> coaches = new ArrayList<>();
                    for (int i = 0; i < tour.getCoachsCount(); i++) {
                        coaches.add(tour.getCoach(i));
                    }
                    Generation.genQSwiss(coaches, datas, r);
                }
            }
        } else {
            // If Cross match accepted, Take 1 competitor per pool which has not
            // already played cross match
            ArrayList<Competitor> comps = new ArrayList<>();
            if (tour.getParams().isCrossPoolMatch()) {
                // Collec coachs without matches                
                for (int i = 0; i < tour.getPoolCount(); i++) {
                    Pool p=tour.getPool(i);
                    ArrayList<Competitor> pComps = p.getCompetitors();

                    // Find the one without cross match match
                    for (Competitor c : pComps) {
                        boolean cross = false;
                        for (int j=0; j<c.getMatchCount(); j++)
                        {
                            Match m=c.getMatch(j);
                            if (m.getCompetitor1()==c)
                            {
                                if (!pComps.contains(m.getCompetitor2()))
                                {
                                    cross=true;
                                }
                            }
                            if (m.getCompetitor2()==c)
                            {
                                if (!pComps.contains(m.getCompetitor1()))
                                {
                                    cross=true;
                                }
                            }
                        }

                        if (!cross) {
                            comps.add(c);
                            break;
                        }
                    }
                }
                datas = getSortedRankingData(roundnumber);
                Generation.genQSwiss(comps,datas, r);
                
            }
                                    
            for (int i = 0; i < tour.getPoolCount(); i++) {
                datas = getSortedRankingData(tour.getPool(i), roundnumber);
                
                Pool p=tour.getPool(i);
                ArrayList<Competitor> pComps=new ArrayList<>(p.getCompetitors());
                // Remove the ones used for cross matches
                for (Competitor c:comps)
                {
                    pComps.remove(c);
                    
                }
                
                genQSwiss(pComps, datas, r);
            }
        }
        return r;
    }

    /**
     *
     * @param round
     * @param roundnumber
     * @return
     */
    @SuppressWarnings("unchecked")
    private static Round nextRoundCup(final Round round, final int roundnumber) {

        final Tournament tour = Tournament.getTournament();
        Round r = new Round();
        //final Calendar cal = Calendar.getInstance();
        r.setCurrentHour();

        if ((round != null) && (round.isCup())) {
            if (((round.getCupTour() == round.getCupMaxTour() - 1) && (Tournament.getTournament().getCup().getType() != Cup.ROUND_TYPE.LOOSER))
                    || (round.getCupTour() == round.getCupMaxTour() + 2)) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                        Translate.translate(CS_FinalRoundReached),
                        Translate.translate(Translate.CS_Cup), JOptionPane.ERROR_MESSAGE);
                r = null;
            } else {

                /*                boolean _third_place = false;
                if ((round.getCupTour() == round.getCupMaxTour() - 2) && (Tournament.getTournament().getCup().getType() != Cup.ROUND_TYPE.LOOSER)) {
                    _third_place = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),
                            Translate.translate(CS_DoYouWantToGenerateThirdPlaceMatch),
                            Translate.translate(Translate.CS_Cup), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                }*/
                genCup(round, r);
                //r.setThirdPlace(_third_place);

                r.setCup(true);
                r.setCupMaxTour(round.getCupMaxTour());
                r.setCupTour(round.getCupTour() + 1);
                //r.setLooserCup(round.isLooserCup());

            }
        } else /**
         * First Round The purpose of this funcion block is to order the
         * competitors for first pairing
         */
        {
            int cup_max_tour = 0;
            int nb_tmp = 1;

            /* r.setLooserCup(JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),
                    Translate.translate(CS_IsItDoubleKickTournament),
                    Translate.translate(Translate.CS_Cup),
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
             */
            if (tour.getParams().isTeamTournament()) {
                if (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                    while (nb_tmp < tour.getActiveCoachNumber()) {
                        nb_tmp *= 2;
                        cup_max_tour++;
                    }
                } else {
                    while (nb_tmp < tour.getTeamsCount()) {
                        nb_tmp *= 2;
                        cup_max_tour++;
                    }
                }
            } else {
                if (tour.getActiveCompetitorsCount() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                            Translate.translate(CS_OddNumberOfActiveCoachs),
                            Translate.translate(CS_GenerationError), JOptionPane.WARNING_MESSAGE);
                    int option = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Complete with ghost coach/team ?", "Odd number", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.NO_OPTION) {
                        r = null;
                    } else {

                        while (nb_tmp < tour.getActiveCoachNumber()) {
                            nb_tmp *= 2;
                            cup_max_tour++;
                        }
                    }
                } else {
                    while (nb_tmp < tour.getActiveCoachNumber()) {
                        nb_tmp *= 2;
                        cup_max_tour++;
                    }
                }
            }

            if (r != null) {

                if (!round.isCup()) {
                    JdgCupOptions jdg = new JdgCupOptions(MainFrame.getMainFrame(), true, cup_max_tour);
                    jdg.setVisible(true);
                }

                /*final JPanel message = new JPanel();
                message.setLayout(new BorderLayout());

                final JLabel jlb = new JLabel(
                        Translate.translate(CS_NumberOfTurns));
                // generate cup for the first round
                final JSpinner jspNbTeams = new JSpinner();
                final SpinnerNumberModel model = new SpinnerNumberModel(1, 1, cup_max_tour, 1);
                jspNbTeams.setModel(model);

                message.add(jlb, BorderLayout.NORTH);
                message.add(jspNbTeams, BorderLayout.CENTER);

                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), message, Translate.translate(Translate.CS_Cup), JOptionPane.QUESTION_MESSAGE);

                cup_max_tour = (Integer) model.getValue();*/
                final int nb = (int) Math.pow(2, Tournament.getTournament().getCup().getRoundsCount());

                /* boolean useCategories = false;
                if (tour.getCategoriesCount() > 1) {
                    useCategories = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),
                            Translate.translate(CS_DoYouWantToUseCategoriesForTheCup),
                            Translate.translate(Translate.CS_Cup),
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                }*/
                MjtRanking datas;
                if ((Tournament.getTournament().getCup().getInitialDraw() == Cup.INITIAL_DRAW.MANUAL)
                        || (Tournament.getTournament().getCup().getInitialDraw() == Cup.INITIAL_DRAW.RANDOM)
                        || (Tournament.getTournament().getCup().getInitialDraw() == Cup.INITIAL_DRAW.RANKING)) {
                    datas = getSortedRankingData(roundnumber);
                } else {
                    ArrayList<Category> cats = new ArrayList<>();
                    JPanel jpn = new JPanel(new BorderLayout());
                    DefaultListModel<Category> model2 = new DefaultListModel<>();
                    for (int i = 0; i < tour.getCategoriesCount(); i++) {
                        Category cat = tour.getCategory(i);
                        cats.add(cat);
                        model2.addElement(cat);
                    }

                    @SuppressWarnings("unchecked")
                    JList jls = new JList(model2);
                    jls.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    JLabel jlb2 = new JLabel(Translate.translate(CS_PleaseSelectCategoriesToUseForTheCup));
                    jpn.add(jlb2, BorderLayout.NORTH);
                    jpn.add(jls, BorderLayout.CENTER);

                    /*JPanel jpnsouth = new JPanel(new GridLayout(2, 2));
                    JRadioButton jrbMixAll = new JRadioButton(Translate.translate(CS_MixAll));
                    jrbMixAll.setSelected(true);
                    JRadioButton jrbAbsoluteOrder = new JRadioButton(Translate.translate(CS_MixByAbsoluteRanking));
                    JRadioButton jrbMixGroups = new JRadioButton(Translate.translate(CS_MixByCategoryRanking));
                    JRadioButton jrbKeepGroup = new JRadioButton(Translate.translate(CS_KeepGroup));

                    ButtonGroup bg = new ButtonGroup();
                    bg.add(jrbMixAll);
                    bg.add(jrbAbsoluteOrder);
                    bg.add(jrbMixGroups);
                    bg.add(jrbKeepGroup);

                    jpnsouth.add(jrbMixAll);
                    jpnsouth.add(jrbAbsoluteOrder);
                    jpnsouth.add(jrbMixGroups);
                    jpnsouth.add(jrbKeepGroup);

                    jpn.add(jpnsouth, BorderLayout.SOUTH);*/
                    while (jls.getSelectedValuesList().isEmpty()) {
                        JOptionPane.showMessageDialog(null, jpn, Translate.translate(Translate.CS_Cup), JOptionPane.QUESTION_MESSAGE);
                    }

                    CATEGORY_DRAW mix = CATEGORY_DRAW.MIX_ALL;

                    // 0: Mix all
                    // 1: Mix by absolute order
                    // 2: Mix by category ranking
                    // 3: No mix
                    switch (Tournament.getTournament().getCup().getInitialDraw()) {
                        case CATEGORIES_MIXED:
                            mix = CATEGORY_DRAW.MIX_ALL;
                            break;
                        case CATEGORIES_ABSOLUTE_RANKING:
                            mix = CATEGORY_DRAW.ABSOLUTE_ORDER;
                            break;
                        case CATEGORIES_CROSSED:
                            mix = CATEGORY_DRAW.CATEGORY_RANKING;
                            break;
                        case CATEGORIES_NOT_MIXED:
                            mix = CATEGORY_DRAW.NO_MIX;
                            break;
                        default:
                            mix = CATEGORY_DRAW.MIX_ALL;
                    }
                    /*if (jrbMixAll.isSelected()) {
                        mix = 0;
                    }
                    if (jrbAbsoluteOrder.isSelected()) {
                        mix = 1;
                    }
                    if (jrbMixGroups.isSelected()) {
                        mix = 2;
                    }
                    if (jrbKeepGroup.isSelected()) {
                        mix = 3;
                    }*/

                    datas = getSortedRankingDataCategories(roundnumber, jls.getSelectedValuesList(), mix, nb);
                }

                genCupFirst(r, datas, nb);

                r.setCup(true);
                r.setCupMaxTour(Tournament.getTournament().getCup().getRoundsCount());
                r.setCupTour(0);

            }
        }

        if (r != null) {
            if (r.getMatchsCount() < tour.getActiveCoachNumber() / 2) {
                int res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),
                        Translate.translate(CS_AffectRoundsToRemainingCoachs),
                        Translate.translate(Translate.CS_Cup), JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {

                    MjtRanking d = getSortedRankingData(roundnumber);
                    final ArrayList<ObjectRanking> datas = new ArrayList<>();

                    for (int i = 0; i < d.getRowCount(); i++) {
                        ObjectRanking data = d.getSortedObject(i);
                        datas.add(data);
                    }

                    ArrayList<Coach> coachs;
                    ArrayList<Team> teams;
                    final Round r2 = new Round();

                    if (tour.getParams().isTeamTournament()) {
                        if (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {

                            coachs = new ArrayList<>(tour.getActiveCoaches());
                            // First, remove coachs
                            for (int i = 0; i < r.getMatchsCount(); i++) {
                                final CoachMatch m = r.getCoachMatchs().get(i);
                                if (m.getCompetitor1() instanceof Coach) {
                                    Coach c1 = (Coach) m.getCompetitor1();
                                    if (coachs.contains(c1)) {
                                        coachs.remove(c1);
                                    }
                                }
                                if (m.getCompetitor2() instanceof Coach) {
                                    Coach c2 = (Coach) m.getCompetitor2();
                                    if (coachs.contains(c2)) {
                                        coachs.remove(c2);
                                    }
                                }
                            }
                            // Second remove bad datas
                            int i = 0;
                            while (i < datas.size()) {
                                final Coach c = (Coach) datas.get(i).getObject();
                                for (int j = 0; j < r.getMatchsCount(); j++) {
                                    final CoachMatch m = r.getCoachMatchs().get(j);
                                    if ((c == m.getCompetitor1()) || (c == m.getCompetitor2())) {
                                        datas.remove(i);
                                        i--;
                                    }
                                }
                                i++;
                            }

                        } else {
                            ArrayList<Team> tmp_teams = new ArrayList<>();
                            for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                                tmp_teams.add(Tournament.getTournament().getTeam(cpt));
                            }
                            teams = new ArrayList<>(tmp_teams);
                            // First, remove coachs
                            for (int i = 0; i < r.getCoachMatchs().size(); i++) {
                                final CoachMatch m = r.getCoachMatchs().get(i);
                                if (teams.contains(((Coach) m.getCompetitor1()).getTeamMates())) {
                                    teams.remove(((Coach) m.getCompetitor1()).getTeamMates());
                                }
                                if (teams.contains(((Coach) m.getCompetitor2()).getTeamMates())) {
                                    teams.remove(((Coach) m.getCompetitor2()).getTeamMates());
                                }
                            }
                            // Second remove bad datas
                            int i = 0;
                            while (i < datas.size()) {
                                final Team t = (Team) datas.get(i).getObject();
                                for (int j = 0; j < r.getCoachMatchs().size(); j++) {
                                    final CoachMatch m = r.getCoachMatchs().get(j);
                                    if ((t == ((Coach) m.getCompetitor1()).getTeamMates()) || (t == ((Coach) m.getCompetitor2()).getTeamMates())) {
                                        datas.remove(i);
                                        i--;
                                        break;
                                    }
                                }
                                i++;
                            }
                        }
                    } else {
                        if (tour.getActiveCoachNumber() % 2 > 0) {
                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                                    Translate.translate(CS_OddNumberOfActiveCoachs),
                                    Translate.translate(CS_GenerationError), JOptionPane.WARNING_MESSAGE);
                            r = null;
                        } else {
                            coachs = new ArrayList<>(tour.getActiveCoaches());
                            // First, remove coachs
                            for (int i = 0; i < r.getMatchsCount(); i++) {
                                final CoachMatch m = r.getCoachMatchs().get(i);
                                if (m.getCompetitor1() instanceof Coach) {
                                    Coach c1 = (Coach) m.getCompetitor1();
                                    if (coachs.contains(c1)) {
                                        coachs.remove(c1);
                                    }
                                }
                                if (m.getCompetitor2() instanceof Coach) {
                                    Coach c2 = (Coach) m.getCompetitor2();
                                    if (coachs.contains(c2)) {
                                        coachs.remove(c2);
                                    }
                                }
                            }
                            // Second remove bad datas
                            int i = 0;
                            while (i < datas.size()) {
                                final Coach c = (Coach) datas.get(i).getObject();
                                for (int j = 0; j < r.getMatchsCount(); j++) {
                                    final CoachMatch m = r.getCoachMatchs().get(j);
                                    if ((c == m.getCompetitor1()) || (c == m.getCompetitor2())) {
                                        datas.remove(i);
                                        i--;
                                    }
                                }
                                i++;
                            }
                        }
                    }

                    if (r != null) {
                        final ArrayList<Round> v = new ArrayList<>();
                        final ArrayList<CoachMatch> matchs = new ArrayList<>();
                        buildUntilRound(round, v, matchs);

                        res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),
                                Translate.translate(CS_UseSwissRoundOrRandom),
                                Translate.translate(Translate.CS_Cup), JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION) {

                            if (tour.getParams().isTeamTournament()) {
                                if (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                                    final ArrayList<Coach> coaches = new ArrayList<>();
                                    for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                                        coaches.add(Tournament.getTournament().getCoach(i));
                                    }
                                    genSwiss(coaches, datas, r2, false);
                                } else {
                                    ArrayList<Team> tmp_teams = new ArrayList<>();
                                    for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                                        tmp_teams.add(Tournament.getTournament().getTeam(cpt));
                                    }
                                    genSwiss(tmp_teams, datas, r2, false);
                                }
                            } else {
                                if (tour.getActiveCoachNumber() % 2 > 0) {
                                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                                            Translate.translate(CS_OddNumberOfActiveCoachs),
                                            Translate.translate(CS_GenerationError), JOptionPane.WARNING_MESSAGE);
                                    r = null;
                                } else {
                                    final ArrayList<Coach> coaches = new ArrayList<>();
                                    for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                                        coaches.add(Tournament.getTournament().getCoach(i));
                                    }
                                    genSwiss(coaches, datas, r2, false);
                                }
                            }
                        } else {
                            if (tour.getParams().isTeamTournament()) {
                                if (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                                    final ArrayList<Coach> coaches = new ArrayList<>();
                                    for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                                        coaches.add(Tournament.getTournament().getCoach(i));
                                    }
                                    Generation.genRandom(coaches, r2);
                                } else {
                                    ArrayList<Team> tmp_teams = new ArrayList<>();
                                    for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                                        tmp_teams.add(Tournament.getTournament().getTeam(cpt));
                                    }
                                    Generation.genRandom(tmp_teams, r2);
                                }
                            } else {
                                if (tour.getActiveCoachNumber() % 2 > 0) {
                                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                                            Translate.translate(CS_OddNumberOfActiveCoachs),
                                            Translate.translate(CS_GenerationError), JOptionPane.WARNING_MESSAGE);
                                    r = null;
                                } else {
                                    final ArrayList<Coach> coaches = new ArrayList<>();
                                    for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                                        coaches.add(Tournament.getTournament().getCoach(i));
                                    }
                                    Generation.genRandom(coaches, r);
                                }
                            }
                        }

                        if (r != null) {
                            // Merge the generated rounds
                            for (int i = 0; i < r2.getMatchsCount(); i++) {
                                r.addMatch(r2.getMatch(i));
                            }
                        }
                    }
                }
            }
        }
        return r;
    }

    /**
     *
     * @param round
     * @param v
     * @param matchs
     */
    private static void buildUntilRound(final Round round, final ArrayList<Round> v, final ArrayList<CoachMatch> matchs) {
        final Tournament tour = Tournament.getTournament();
        if (round != null) {
            for (int i = 0; i < tour.getRoundsCount(); i++) {
                if (tour.getRound(i).getHour().before(round.getHour())) {
                    v.add(tour.getRound(i));
                }
            }
            v.add(round);

            for (int j = 0; j < v.size(); j++) {
                for (int k = 0; k < v.get(j).getMatchsCount(); k++) {
                    matchs.add(v.get(j).getCoachMatchs().get(k));
                }
            }
        }
    }

    /**
     *
     * @param round
     * @param roundnumber
     * @return
     */
    private static Round nextRoundSwiss(final Round round, final int roundnumber, boolean topdown) {

        final Tournament tour = Tournament.getTournament();

        MjtRanking datas;
        // First: Create ranking
        // previous rounds       
        final ArrayList<Round> v = new ArrayList<>();
        final ArrayList<CoachMatch> matchs = new ArrayList<>();
        buildUntilRound(round, v, matchs);

        Round r = new Round();
        if (tour.getPoolCount() == 0) {
            datas = getSortedRankingData(roundnumber);
            if (tour.getParams().isTeamTournament()) {
                if (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                    final ArrayList<Coach> coaches = new ArrayList<>();
                    for (int i = 0; i < tour.getCoachsCount(); i++) {
                        coaches.add(tour.getCoach(i));
                    }
                    genSwiss(coaches, datas, r, topdown);
                } else {
                    ArrayList<Team> tmp_teams = new ArrayList<>();
                    for (int cpt = 0; cpt < tour.getTeamsCount(); cpt++) {
                        tmp_teams.add(tour.getTeam(cpt));
                    }
                    genSwiss(tmp_teams, datas, r, topdown);
                }
            } else {
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                            Translate.translate(CS_OddNumberOfActiveCoachs),
                            Translate.translate(CS_GenerationError), JOptionPane.WARNING_MESSAGE);
                    r = null;
                } else {
                    final ArrayList<Coach> coaches = new ArrayList<>();
                    for (int i = 0; i < tour.getCoachsCount(); i++) {
                        coaches.add(tour.getCoach(i));
                    }
                    genSwiss(coaches, datas, r, topdown);
                }
            }
        } else {
            
            // If Cross match accepted, Take 1 competitor per pool which has not
            // already played cross match
            ArrayList<Competitor> comps = new ArrayList<>();
            if (tour.getParams().isCrossPoolMatch()) {
                // Collec coachs without matches                
                for (int i = 0; i < tour.getPoolCount(); i++) {
                    Pool p=tour.getPool(i);
                    ArrayList<Competitor> pComps = p.getCompetitors();

                    // Find the one without cross match match
                    for (Competitor c : pComps) {
                        boolean cross = false;
                        for (int j=0; j<c.getMatchCount(); j++)
                        {
                            Match m=c.getMatch(j);
                            if (m.getCompetitor1()==c)
                            {
                                if (!pComps.contains(m.getCompetitor2()))
                                {
                                    cross=true;
                                }
                            }
                            if (m.getCompetitor2()==c)
                            {
                                if (!pComps.contains(m.getCompetitor1()))
                                {
                                    cross=true;
                                }
                            }
                        }

                        if (!cross) {
                            comps.add(c);
                            break;
                        }
                    }
                }
                datas = getSortedRankingData(roundnumber);
                Generation.genSwiss(comps,datas, r,topdown);
                
            }
                                    
            for (int i = 0; i < tour.getPoolCount(); i++) {
                datas = getSortedRankingData(tour.getPool(i), roundnumber);
                
                Pool p=tour.getPool(i);
                ArrayList<Competitor> pComps=new ArrayList<>(p.getCompetitors());
                // Remove the ones used for cross matches
                for (Competitor c:comps)
                {
                    pComps.remove(c);
                    
                }
                
                genSwiss(pComps, datas, r, topdown);
            }
        }
        return r;
    }

    enum CATEGORY_DRAW {
        MIX_ALL,
        ABSOLUTE_ORDER,
        CATEGORY_RANKING,
        NO_MIX
    };

    private static MjtRanking getSortedRankingDataCategories(int roundNumber, List<Category> categories, CATEGORY_DRAW mix, int nbPlayers) {
        // First, collect coachs by categories  
        final Tournament tour = Tournament.getTournament();
        ArrayList<ArrayList<Competitor>> acomps = new ArrayList<>();
        for (Object obj : categories) {
            if (obj instanceof Category) {
                Category cat = (Category) obj;
                ArrayList<Competitor> comps = new ArrayList<>();

                if (tour.getParams().isTeamTournament()
                        && tour.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
                    for (int i = 0; i < tour.getTeamsCount(); i++) {
                        Team t = tour.getTeam(i);
                        if (t.containsCategory(cat)) {
                            comps.add(t);
                        }
                    }
                } else {
                    for (int i = 0; i < tour.getCoachsCount(); i++) {
                        Coach c = tour.getCoach(i);
                        if (c.containsCategory(cat)) {
                            comps.add(c);
                        }
                    }
                }
                acomps.add(comps);

            }
        }

        ArrayList<MjtRanking> rankings = new ArrayList<>();

        // Order competitors by ranking
        for (ArrayList<Competitor> acomp : acomps) {
            if (Tournament.getTournament().getParams().isTeamTournament()
                    && Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
                MjtRankingTeam t = new MjtRankingTeam(Tournament.getTournament().getParams().isTeamVictoryOnly(),
                        roundNumber, acomp, false);
                rankings.add(t);
            } else {
                MjtRankingIndiv t = new MjtRankingIndiv(roundNumber, acomp, Tournament.getTournament().getParams().isTeamTournament(), false);
                rankings.add(t);
            }
        }

        MjtRanking mjt = null;
        int nb_players;
        int more = 0;
        do {
            nb_players = (nbPlayers + more) / rankings.size();
        } while ((nbPlayers + more) % rankings.size() != 0);

        // Build MjtRanking according to category mix
        // 0: Mix all
        // 1: Mix by absolute order
        // 2: Mix by category ranking
        // 3: No mix
        // The generated cup will create matchs as this
        // first vs last
        // second vs last-1 etc.
        // In order to respect the draw, the newly created ranking has to take account the match order.
        // If mix by absolute order
        if ((mix == CATEGORY_DRAW.ABSOLUTE_ORDER) || (mix == CATEGORY_DRAW.MIX_ALL)) {
            // Create the list of all elements and add them to one ranking.

            ArrayList<Competitor> c = new ArrayList<>();

            if (mix == CATEGORY_DRAW.ABSOLUTE_ORDER) {
                for (MjtRanking r : rankings) {
                    for (int i = 0; i < nb_players; i++) {
                        c.add((Competitor) r.getSortedObject(i).getObject());
                    }
                }
            } else {
                for (MjtRanking r : rankings) {
                    for (int i = 0; (i < r.getRowCount()) && (c.size() < nbPlayers); i++) {
                        c.add((Competitor) r.getSortedObject(i).getObject());
                    }
                }
            }

            if (Tournament.getTournament().getParams().isTeamTournament()
                    && Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
                mjt = new MjtRankingTeam(Tournament.getTournament().getParams().isTeamVictoryOnly(),
                        roundNumber, c, false);
            } else {
                mjt = new MjtRankingIndiv(roundNumber, c, Tournament.getTournament().getParams().isTeamTournament(), false);
            }

            // The return this ranking
            // If mix==0, keep only the first players, and randomize them.
        } else {
            if (mix == CATEGORY_DRAW.CATEGORY_RANKING) {
                // According to the number of players, determine the first and the last of each
                // category, then make the pairing to make the first vs the last of another cat, etc ...
                ArrayList<Competitor> c = new ArrayList<>();

                for (int i = 0; i < nb_players; i++) {
                    ArrayList<Competitor> sub = new ArrayList<>();
                    MjtRanking m;
                    for (MjtRanking r : rankings) {
                        sub.add((Competitor) r.getSortedObject(i).getObject());
                    }
                    if (Tournament.getTournament().getParams().isTeamTournament()
                            && Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
                        m = new MjtRankingTeam(Tournament.getTournament().getParams().isTeamVictoryOnly(),
                                roundNumber, sub, false);
                    } else {
                        m = new MjtRankingIndiv(roundNumber, sub, Tournament.getTournament().getParams().isTeamTournament(), false);
                    }
                    for (int j = 0; j < m.getRowCount(); j++) {
                        c.add((Competitor) m.getSortedObject(j).getObject());
                    }
                }
                mjt = new MjtRankingManual(roundNumber, 0, 0, 0, 0, 0, c, false);

            } else {
                if (mix == CATEGORY_DRAW.NO_MIX) {
                    // Order the match to force the category to meet themselves before
                    // meeting the others categories. (Conference ?)

                    ArrayList<Competitor> c = new ArrayList<>();
                    for (MjtRanking r : rankings) {

                        int first_pos = c.size() / 2;

                        int nb_fake = 0;
                        while (((nb_players + nb_fake) & (nb_players + nb_fake - 1)) != 0) {
                            nb_fake++;
                        }

                        for (int i = 0; i < nb_players; i++) {
                            c.add(first_pos + i, (Competitor) r.getSortedObject(i).getObject());
                        }
                        for (int i = 0; i < nb_fake; i++) {
                            if (Tournament.getTournament().getParams().isTeamTournament()
                                    && Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
                                c.add(first_pos + i + nb_players, Team.getNullTeam());
                            } else {
                                c.add(first_pos + i + nb_players, Coach.getNullCoach());
                            }
                        }
                    }
                    mjt = new MjtRankingManual(roundNumber, 0, 0, 0, 0, 0, c, false);
                }
            }
        }
        return mjt;
    }

    private static MjtRanking getSortedRankingData(final int roundnumber) {
        final Tournament tour = Tournament.getTournament();
        MjtRanking ranking;
        if ((tour.getParams().isTeamTournament()) && (tour.getParams().getTeamPairing() != ETeamPairing.INDIVIDUAL_PAIRING)) {
            ArrayList<Team> tmp_teams = new ArrayList<>();
            for (int cpt = 0; cpt < tour.getTeamsCount(); cpt++) {
                tmp_teams.add(tour.getTeam(cpt));
            }
            ranking = new MjtRankingTeam(tour.getParams().isTeamVictoryOnly(), roundnumber,
                    tmp_teams, false);

        } else {
            final boolean forPool = (tour.getPoolCount() > 0) && (!tour.getRound(roundnumber).isCup());
            final ArrayList<Coach> coaches = new ArrayList<>();
            for (int i = 0; i < tour.getCoachsCount(); i++) {
                Coach coach = tour.getCoach(i);
                coaches.add(coach);
            }
            ranking = new MjtRankingIndiv(roundnumber,
                    tour.getParams().getRankingIndiv1(),
                    tour.getParams().getRankingIndiv2(),
                    tour.getParams().getRankingIndiv3(),
                    tour.getParams().getRankingIndiv4(),
                    tour.getParams().getRankingIndiv5(),
                    coaches, false, false, forPool, tour.getRound(roundnumber).isCup());

        }
        return ranking;
    }

    private static MjtRanking getSortedRankingData(final Pool p, final int roundnumber) {
        MjtRanking ranking;
        final Tournament tour = Tournament.getTournament();
        if ((tour.getParams().isTeamTournament()) && (tour.getParams().getTeamPairing() != ETeamPairing.INDIVIDUAL_PAIRING)) {
            ranking = new MjtRankingTeam(tour.getParams().isTeamVictoryOnly(), roundnumber, p.getCompetitors(), false);
        } else {
            final boolean forPool = (tour.getPoolCount() > 0) && (!tour.getRound(roundnumber).isCup());
            ranking = new MjtRankingIndiv(roundnumber,
                    tour.getParams().getRankingIndiv1(),
                    tour.getParams().getRankingIndiv2(),
                    tour.getParams().getRankingIndiv3(),
                    tour.getParams().getRankingIndiv4(),
                    tour.getParams().getRankingIndiv5(),
                    p.getCompetitors(), false, false, forPool, false);

        }
        return ranking;
    }

    /**
     *
     * @return
     */
    private static Round nextRoundFree() {
        final Round r = new Round();
        //final Calendar cal = Calendar.getInstance();
        r.setCurrentHour();
        return r;
    }

    /**
     *
     * @param round
     * @return
     */
    private static Round nextRoundRandom(final Round round) {
        final Tournament tour = Tournament.getTournament();

        Round r = new Round();
        r.clearMatchs();

        if (tour.getPoolCount() == 0) {
            if (tour.getParams().isTeamTournament()) {
                if (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                    Generation.genRandom(tour.getActiveCoaches(), r);
                } else {
                    ArrayList<Team> tmp_teams = new ArrayList<>();
                    for (int cpt = 0; cpt < tour.getTeamsCount(); cpt++) {
                        tmp_teams.add(tour.getTeam(cpt));
                    }
                    Generation.genRandom(tmp_teams, r);
                }
            } else {
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                            Translate.translate(CS_OddNumberOfActiveCoachs),
                            Translate.translate(CS_GenerationError), JOptionPane.WARNING_MESSAGE);
                    r = null;
                } else {
                    Generation.genRandom(tour.getActiveCoaches(), r);
                }
            }
        } else {
            
            // If Cross match accepted, Take 1 competitor per pool which has not
            // already played cross match
            ArrayList<Competitor> comps = new ArrayList<>();
            if (tour.getParams().isCrossPoolMatch()) {
                // Collec coachs without matches                
                for (int i = 0; i < tour.getPoolCount(); i++) {
                    Pool p=tour.getPool(i);
                    ArrayList<Competitor> pComps = p.getCompetitors();

                    // Find the one without cross match match
                    for (Competitor c : pComps) {
                        boolean cross = false;
                        for (int j=0; j<c.getMatchCount(); j++)
                        {
                            Match m=c.getMatch(j);
                            if (m.getCompetitor1()==c)
                            {
                                if (!pComps.contains(m.getCompetitor2()))
                                {
                                    cross=true;
                                }
                            }
                            if (m.getCompetitor2()==c)
                            {
                                if (!pComps.contains(m.getCompetitor1()))
                                {
                                    cross=true;
                                }
                            }
                        }

                        if (!cross) {
                            comps.add(c);
                            break;
                        }
                    }
                }
                Generation.genRandom(comps, r);
                
            }
            
            for (int i = 0; i < tour.getPoolCount(); i++) {
                Pool p=tour.getPool(i);
                ArrayList<Competitor> pComps=new ArrayList<>(p.getCompetitors());
                // Remove the ones used for cross matches
                for (Competitor c:comps)
                {
                    pComps.remove(c);
                }
                Generation.genRandom(pComps, r);
            }

            
        }
        return r;
    }

    /**
     *
     * @return
     */
    private static Round nextRoundBalanced() {
        final Tournament tour = Tournament.getTournament();

        Round r = new Round();
        if (tour.getPoolCount() == 0) {
            if (tour.getParams().isTeamTournament()) {
                if (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                    Generation.genBalanced(tour.getActiveCoaches(), r);
                }
            } else {
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                            Translate.translate(CS_OddNumberOfActiveCoachs),
                            Translate.translate(CS_GenerationError), JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            for (int i = 0; i < tour.getPoolCount(); i++) {
                Generation.genBalanced(tour.getPool(i).getCompetitors(), r);
            }
        }
        return r;
    }

    private Generation() {
    }
}
