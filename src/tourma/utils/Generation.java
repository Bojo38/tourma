/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.ETeamPairing;
import tourma.data.Match;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingTeam;
import tourma.utility.StringConstants;

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

    /**
     *
     */
    private final static String[] mPortugueses = {"yanno",
        "obelix", "oon", "fredriech", "pershum",
        "buldogr", "simon_mlf", "haktar", "tristelune", "fredao",
        "haktar"};
    private static final Logger LOG = Logger.getLogger(Generation.class.getName());

    /**
     *
     * @param round
     */
    private static void applyPortugal(final Round round) {
        if (Tournament.getTournament().getParams().isPortugal()) {
            Criteria td = Tournament.getTournament().getParams().getCriteria(0);
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

        switch (choice) {
            case GEN_SWISS:
                r = nextRoundSwiss(round, roundnumber);
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
        //final ArrayList<Round> rounds = tour.getRounds();
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
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        //final ArrayList<Round> rounds = tour.getRounds();
        ArrayList<Team> vteams = new ArrayList<>();
        for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
            vteams.add(Tournament.getTournament().getTeam(cpt));
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
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MAUVAIS NOMBRE DE JOUEURS ACTIF POUR L'ÉQUIPE") + " " + vteams.get(i).getName());
                    return;
                }
            }
        } else {
            final int nb_coach = tour.getActiveCoachNumber();
            if (nb_coach % 2 > 0) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE IMPAIR DE COACH ACTIFS"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
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
                    int res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DoYouWantATeamFirstPairing"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FirstRound"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
                /*if (!params.mTeamTournament) {
                 generateIndividualFirstRoundCup();
                 } else {
                 generateTeamFirstRoundCup();
                 }*/
                break;
            case GEN_NAF:
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
                    m.getCompetitor1().addMatch(m);
                    m.getCompetitor2().addMatch(m);

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

        final StringBuffer filename = new StringBuffer(Tournament.getTournament().getParams().getTournamentName());
        filename.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("."));
        filename.append(Tournament.getTournament().getRoundsCount());
        final Date date = new Date();
        filename.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("."));
        filename.append(Long.toString(date.getTime()));
        filename.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".XML"));
        final File file = new File(filename.toString());

        Tournament.getTournament().saveXML(file, false);
    }

    /**
     *
     * @param competitors
     */
    private static void generateFirstRoundPool(ArrayList competitors) {

        final Tournament tour = Tournament.getTournament();

        tour.clearPools();

        ArrayList<Competitor> comps = new ArrayList<>(competitors);

        final JPanel message = new JPanel();
        message.setLayout(new BorderLayout());

        final JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE PAR POULE: "));

        final JSpinner jspNb = new JSpinner();
        /*int max = comps.size() / 2;
         if (max % 2 != 0) {
         max++;
         }*/

        final SpinnerNumberModel model = new SpinnerNumberModel(2, 2, comps.size() / 2, 2);
        jspNb.setModel(model);

        message.add(jlb, BorderLayout.NORTH);
        message.add(jspNb, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), message, StringConstants.CS_POOL, JOptionPane.QUESTION_MESSAGE);

        final int nb = (Integer) model.getValue();

        if (comps.size() % nb != 0) {
            //JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LE NOMBRE DD'ÉQUIPE N'EST PAS UN MULTIPLE DE VOTRE CHOIX"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ERREUR"), JOptionPane.ERROR_MESSAGE);

            boolean complete = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),
                    java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LE NOMBRE D'ÉQUIPE N'EST PAS UN MULTIPLE DE VOTRE CHOIX"),
                    java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ERREUR"),
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
                String question = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VOULEZ VOUS CHOISIR L'ÉQUIPE DU RANG {0} ?");
                String formattedQuestion = question.replace("{0}", Integer.toString(nb - nbCounter + 1));
                response = JOptionPane.showConfirmDialog(
                        MainFrame.getMainFrame(),
                        formattedQuestion,
                        StringConstants.CS_POOL, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            }
            if (response == JOptionPane.YES_OPTION) {
                for (int i = 0; i < nbPools; i++) {
                    final ArrayList<String> names = new ArrayList<>();
                    for (int j = 0; j < comps.size(); j++) {
                        names.add(comps.get(j).getName());
                    }
                    String question = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHOISISSEZ UN COACH");
                    question = Integer.toString(i + 1) + ": " + question;
                    final int index = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), question, StringConstants.CS_POOL, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, names.toArray(), 0);
                    tour.getPool(i).addCompetitor(comps.get(index));
                    comps.remove(index);
                }
            } else {
                Random ran = new Random();
                for (int i = 0; i < nbPools; i++) {
                    final int index;
                    index = Math.abs(ran.nextInt(comps.size()));
                    if (index < comps.size()) {
                        Competitor comp=comps.get(index);
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
        final ArrayList<Competitor> comps = new ArrayList<>(competitors);
        final Round r = new Round();
        //final Calendar cal = Calendar.getInstance();
        r.setCurrentHour();
        while (comps.size() > 0) {
            Competitor c = comps.get(0);
            comps.remove(c);
            ArrayList<Competitor> possible = c.getPossibleOpponents(comps, r);

            Object[] possibilities = new Object[possible.size()];
            for (int i = 0; i < possible.size(); i++) {
                possibilities[i] = possible.get(i).getDecoratedName();
            }

            final ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);
            final String name = c.getDecoratedName();

            String opp = null;

            if (possibilities.length > 0) {
                while (opp == null) {
                    opp = (String) JOptionPane.showInputDialog(
                            MainFrame.getMainFrame(),
                            bundle.getString("ChooseOpponentFor")
                            + " " + name,
                            bundle.getString("ChoosOpponent"),
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            possibilities,
                            possibilities[0]);
                }

                for (int i = 0; i < comps.size(); i++) {
                    Competitor tmpOpp = possible.get(i);
                    final String tmpString = tmpOpp.getDecoratedName();

                    if (opp.equals(tmpString)) {
                        c.addMatch(tmpOpp, r);
                        comps.remove(tmpOpp);
                        break;
                    }
                }
            } else {
                break;
            }
        }
        tour.addRound(r);
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
            Competitor c = shuffle.get(0);
            shuffle.remove(c);
            ArrayList<Competitor> possible = c.getPossibleOpponents(shuffle, r);
            c.addMatch(possible.get(0), r);
            shuffle.remove(possible.get(0));
        }

        tour.addRound(r);
    }

    /**
     *
     * @param k
     * @param r
     * @param RotateList
     * @param evaluationPreviousC
     * @param evaluationPreviousT
     */
    /*    private static void permutation(int k,
     ArrayList<Competitor> r,
     ArrayList<ArrayList<Competitor>> RotateList,
     HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC,
     HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT) {
     int fact = 1;
     int compteur = 0; //Le compteur sert à déterminer s'il y a eu une permutation

     for (int i = 2; i < r.size() + 1; i++) {
     fact *= (i - 1);
     int pos = i - (k / fact % i) - 1;

     Competitor s1 = r.get(pos);
     Competitor s2 = r.get(i - 1);

     if (!(s1.equals(s2))) {
     swap(pos, i - 1, r);
     compteur++;
     }
     }
     if (compteur != 0) {
     if (isDrawValidForBalanced(r, evaluationPreviousC, evaluationPreviousT)) {
     RotateList.add(r);
     }
     }
     }*/
    /**
     *
     * @param i
     * @param j
     * @param A
     */
/*    private static void swap(int i, int j, ArrayList<Competitor> A) {
        Competitor oi = A.get(i);
        Competitor oj = A.get(j);
        A.set(j, oi);
        A.set(i, oj);
    }*/

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
     * @param l
     * @param evaluationPreviousC
     * @param evaluationPreviousT
     * @return
     */
    /*private static boolean isDrawValidForBalanced(ArrayList<Competitor> l, HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC,
            HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT) {
        boolean valid = true;

        Round r = new Round();

        // Optimize algorithm for avoid same team players
         
        ArrayList<Competitor> l2 = new ArrayList<>(l);

        for (int i = 0; i < l.size() - 1; i++) {
            Competitor c1 = l.get(i);
            Competitor c2 = l.get(i + 1);
            if ((c1 instanceof Coach) && (c2 instanceof Coach)) {
                Coach co1 = (Coach) c1;
                Coach co2 = (Coach) c2;
                if (co1.getTeamMates().containsCoach(co2)) {
                    if (i < l.size() - 2) {
                        for (int j = i + 2; j < l.size(); j++) {
                            if (!((Coach) l.get(j)).getTeamMates().containsCoach(co1)) {
                                swap(i + 1, j, l);
                                break;
                            }
                        }
                    } else {
                        for (int j = i - 1; j > 0; j--) {
                            if (!((Coach) l.get(j)).getTeamMates().containsCoach(co1)) {
                                swap(i, j, l);
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < l.size() / 2; i++) {
            Competitor c1 = l.get(2 * i);
            Competitor c2 = l.get(2 * i + 1);
            if (c1.havePlayed(c2)) {
                valid = false;
                break;
            }
            if ((c1 instanceof Coach) && (c2 instanceof Coach)) {
                Coach co1 = (Coach) c1;
                Coach co2 = (Coach) c2;
                if (co1.getTeamMates().containsCoach(co2)) {
                    valid = false;
                    break;
                }
            }
            CoachMatch cm = new CoachMatch(r);
            cm.setCompetitor1(c1);
            cm.setCompetitor2(c2);
            r.addMatch(cm);
        }

        if (valid) {

            HashMap<Competitor, HashMap<Team, Integer>> evaluationC = new HashMap<>(evaluationPreviousC);
            HashMap<Competitor, HashMap<Team, Integer>> evaluationT = new HashMap<>(evaluationPreviousT);

            Iterator<Competitor> it = evaluationPreviousC.keySet().iterator();
            while (it.hasNext()) {
                Competitor c = it.next();
                HashMap<Team, Integer> hm_tmp = evaluationPreviousC.get(c);
                HashMap<Team, Integer> hm = new HashMap<>(hm_tmp);
                evaluationC.put(c, hm);
            }

            it = evaluationPreviousT.keySet().iterator();
            while (it.hasNext()) {
                Competitor c = (Competitor) it.next();
                HashMap<Team, Integer> hm = new HashMap<>(evaluationPreviousT.get(c));
                evaluationT.put(c, hm);
            }

            // Evaluates Map
            ArrayList<CoachMatch> matchs = r.getCoachMatchs();
            for (int i = 0; i < matchs.size(); i++) {
                CoachMatch m = matchs.get(i);
                Coach c1 = (Coach) m.getCompetitor1();
                Coach c2 = (Coach) m.getCompetitor2();

                int nb1C = evaluationC.get(c1).get(c2.getTeamMates());
                int nb2C = evaluationC.get(c2).get(c1.getTeamMates());

                int nb1T = evaluationT.get(c1.getTeamMates()).get(c2.getTeamMates());
                int nb2T = evaluationT.get(c2.getTeamMates()).get(c1.getTeamMates());

                nb1C++;
                nb2C++;
                nb1T++;
                nb2T++;

                evaluationC.get(c1).put(c2.getTeamMates(), nb1C);
                evaluationC.get(c2).put(c1.getTeamMates(), nb2C);
                evaluationT.get(c1.getTeamMates()).put(c2.getTeamMates(), nb1T);
                evaluationT.get(c2.getTeamMates()).put(c1.getTeamMates(), nb2T);
            }

            int minC = Balancing.getMinimumFromHash(evaluationC);
            int minT = Balancing.getMinimumFromHash(evaluationT);

            int maxC = Balancing.getMaximumFromHash(evaluationC);
            int maxT = Balancing.getMaximumFromHash(evaluationT);

            if ((maxT - minT > 1) || (maxC - minC > 1)) {
                valid = false;
            } else {
                LOG.log(Level.INFO, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FOUND "));

                for (int i = 0; i < l.size() / 2; i++) {
                    Competitor c1 = l.get(2 * i);
                    Competitor c2 = l.get(2 * i + 1);
                    LOG.log(Level.INFO, "{0}. {1} vs {2}", new Object[]{i, c1.getName(), c2.getName()});
                }
            }
        }

        if (!valid) {
            l.clear();
            l.addAll(l2);
        }

        return valid;
    }*/

    /**
     *
     * @param l
     * @param RotateList
     * @param evaluationPreviousC
     * @param evaluationPreviousT
     */
    /*    private static void rotateListForBalanced(ArrayList<Competitor> l, ArrayList<ArrayList<Competitor>> RotateList, HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC,
     HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT) {

     // Pour optimiser le tirage on ordonne de telle façon à ce que les éléments
     // de la même équipe de soient pas cote à cote sur le premier tirage
     Collections.shuffle(l);
     for (int i = 0; i < l.size() - 1; i++) {
     Competitor c1 = l.get(i);
     Competitor c2 = l.get(i + 1);
     if ((c1 instanceof Coach) && (c2 instanceof Coach)) {
     Coach co1 = (Coach) c1;
     Coach co2 = (Coach) c2;
     if (co1.getTeamMates().containsCoach(co2)) {
     if (i < l.size() - 2) {
     for (int j = i + 2; j < l.size(); j++) {
     if (!((Coach) l.get(j)).getTeamMates().containsCoach(co1)) {
     swap(i + 1, j, l);
     break;
     }
     }
     } else {
     for (int j = i - 1; j > 0; j--) {
     if (!((Coach) l.get(j)).getTeamMates().containsCoach(co1)) {
     swap(i, j, l);
     break;
     }
     }
     }
     }
     }
     }

     int resFact = factorielle(l.size());

     if (isDrawValidForBalanced(l, evaluationPreviousC, evaluationPreviousT)) {
     RotateList.add(l); //j'ajoute à l'arrayliste Jour le tableau de base, et je
     // commence la boucle qui suit à 1 au lieu de 0
     // pour éviter des calculs..
     }

     if (RotateList.isEmpty()) {
     for (int i = 1; i < resFact; i++) {
     ArrayList<Competitor> r = new ArrayList<>(l);
     permutation(i, r, RotateList, evaluationPreviousC, evaluationPreviousT);
     if (!RotateList.isEmpty()) {
     break;
     }
     }
     }
     }*/
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

        for (int i = 0; i < nbrounds; i++) {
            final Round r = new Round();
            r.setCurrentHour();

            for (int j = 0; j < c2part.size(); j++) {
                c1part.get(j).addMatchRoundRobin(c2part.get(j), r);
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

        //final ArrayList<Round> rounds = tour.getRounds();
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
        //final Calendar cal = Calendar.getInstance();
        r.setCurrentHour();

        r.setLooserCup(JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SAGIT-IL D'UN TOURNOI À DOUBLE ÉLIMINATION ?"), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
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
        //r.mLooserCup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Sagit-il d'un tournoi à double élimination ?", StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        tour.addRound(r);
    }

    /**
     *
     * @param competitors
     * @param datas
     * @param r
     * @return
     */
    private static Round genSwiss(final ArrayList competitors, final MjtRanking datas, final Round r) {

        final ArrayList<ObjectRanking> datas_tmp = new ArrayList<>();

        for (int i = 0; i < datas.getRowCount(); i++) {
            ObjectRanking data = datas.getSortedObject(i);
            datas_tmp.add(data);
        }

        return genSwiss(competitors, datas_tmp, r);
    }

    /**
     *
     * Generates a swiss pairing for this round
     * @param competitors list of competitors
     * @param datas ranking data
     * @param r current round
     * @return new round
     */
    private static Round genSwiss(final ArrayList competitors, final ArrayList<ObjectRanking> datas, final Round r) {

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

        // If number of players is odd; add one null a the end
        if (comps.size() % 2 == 1) {
            if (comps.get(0) instanceof Coach) {
                comps.add(Coach.getNullCoach());
            }
            if (comps.get(0) instanceof Team) {
                comps.add(Team.getNullTeam());
            }
        }

        // If the number of round is more than the number of players, no switch
        if (comps.size() - 1 <= tour.getRoundsCount()) {
            for (int i = 0; i < datas_tmp.size() / 2; i++) {
                Competitor c1 = (Competitor) datas_tmp.get(2 * i).getObject();
                Competitor c2 = (Competitor) datas_tmp.get(2 * i + 1).getObject();
                c1.addMatch(c2, r);
            }
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NotEnoughRoundToAvoidSameMatch"));
        } else {
            ArrayList<ObjectRanking> datas2 = new ArrayList<>(datas_tmp);
            while (datas2.size() > 0) {
                Competitor c1 = (Competitor) datas2.get(0).getObject();
                datas2.remove(0);

                ArrayList<Competitor> opponents = new ArrayList<>();
                for (int i = 0; i < datas2.size(); i++) {
                    opponents.add((Competitor) datas2.get(i).getObject());
                }

                ArrayList<Competitor> possible = c1.getPossibleOpponents(opponents, r);
                Competitor c2 = null;
                if (!possible.isEmpty()) {
                    c2 = possible.get(0);
                    int index = opponents.indexOf(c2);
                    datas2.remove(index);
                } else {
                    if (c1 instanceof Team) {
                        c2 = Team.getNullTeam();
                    }
                    if (c1 instanceof Coach) {
                        c2 = Coach.getNullCoach();
                    }

                }
                if (c2 != null) {
                    c1.addMatch(c2, r);
                }

            }
            ((Competitor) competitors.get(0)).roundCheck(r);
        }
        return r;
    }

    /*private static void affectTeamCoachs(final Round round, final Team team1, final Team team2, final Round r) {
     final Tournament tour = Tournament.getTournament();
     final ArrayList<Round> vs = new ArrayList<>();
     for (int i = 0; i < tour.getRoundsCount(); i++) {
     if (tour.getRound(i).getHour().before(round.getHour())) {
     vs.add(tour.getRound(i));
     }
     }
     vs.add(round);
      
     switch (tour.getParams().mTeamIndivPairing) {
     // Ranking
     case 0:
     final ArrayList<ObjectRanking> coachs1 = subRanking(team1.mCoachs, vs);
     final ArrayList<ObjectRanking> coachs2 = subRanking(team2.mCoachs, vs);
     for (int k = 0; k < coachs1.size(); k++) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) coachs1.get(k).getObject();
     m.mCompetitor2 = (Coach) coachs2.get(k).getObject();
     r.getMatchs().add(m);
     }
     break;
     // Manual
     case 1:
     final jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
     jdg.setVisible(true);
     break;
     // genRandom
     case 2:
     for (int k = 0; k < tour.getParams().mTeamMatesNumber; k++) {
     final ArrayList<Coach> shuffle2 = new ArrayList<>(team2.getActivePlayers());
     Collections.shuffle(shuffle2);
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = team1.getActivePlayers().get(k);
     m.mCompetitor2 = shuffle2.get(k);
     r.getMatchs().add(m);
     }
     break;
     // NAF
     case 3:
     for (int k = 0; k < tour.getParams().mTeamMatesNumber; k++) {
     final ArrayList<Coach> sort1 = new ArrayList<>(team1.getActivePlayers());
     final ArrayList<Coach> sort2 = new ArrayList<>(team2.getActivePlayers());
     Collections.sort(sort1);
     Collections.sort(sort2);
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = sort1.get(k);
     m.mCompetitor2 = sort2.get(k);
     r.getMatchs().add(m);
     }
     break;
     }
     }*/
    /**
     *
     * @param competitors
     * @param datas
     * @param r
     * @return
     */
    private static Round genQSwiss(final ArrayList competitors, final MjtRanking datas, Round r2) {

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
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LA MÉTHODE SUISSE ACCELÉRÉE N'EST PAS APPLICABLE"));
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
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NotEnoughRoundToAvoidSameMatch"));
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

        final ArrayList<Competitor> shuffle = new ArrayList<>(competitors);
        Collections.shuffle(shuffle);
        while (shuffle.size() > 0) {
            Competitor c = shuffle.get(0);
            /*            if ((Tournament.getTournament().getParams().mTeamTournament
             && (Tournament.getTournament().getParams().mTeamPairing == 0))
             && (Tournament.getTournament().getParams().mIndivPairingIndivBalanced
             || Tournament.getTournament().getParams().mIndivPairingTeamBalanced)) {

             // allocates matchs to still not allocated team layers
             HashMap<Team, Integer> map = new HashMap<>();
             for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
             Team t = Tournament.getTournament().getTeam(i);
             map.put(t, 0);
             }

             // Count the number of times each team has been aoolcated during this round
             for (int i = 0; i < r.getCoachMatchs().size(); i++) {
             CoachMatch m = r.getCoachMatchs().get(i);
             Team t1 = ((Coach) m.mCompetitor1).mTeamMates;
             Team t2 = ((Coach) m.mCompetitor2).mTeamMates;

             int nb1 = map.get(t1);
             int nb2 = map.get(t2);
             nb1 = nb1 + 1;
             nb2 = nb2 + 1;
             map.put(t2, nb2);
             map.put(t1, nb1);
             }

             // Find the minimum
             Iterator it = map.keySet().iterator();
             int minimum = 65535;
             while (it.hasNext()) {
             Team en = (Team) it.next();
             int nb = map.get(en);
             if (nb < minimum) {
             minimum = nb;
             }
             }

             // Select a team with the Minimum of use
             it = map.keySet().iterator();
             Team t = null;
             while (it.hasNext() && (t == null)) {
             Team en = (Team) it.next();
             int nb = map.get(en);
             if (nb == minimum) {
             t = en;
             }
             }

             // Select a player from this team
             for (int i = 0; i < t.mCoachs.size(); i++) {
             Coach co = t.mCoachs.get(i);
             if (co.mActive) {
             if (shuffle.contains(co)) {
             c = co;
             break;
             }
             }
             }
             }*/

            shuffle.remove(c);
            ArrayList<Competitor> opp = c.getPossibleOpponents(shuffle, r);
            c.addMatch(opp.get(0), r);
            shuffle.remove(opp.get(0));
        }

        ((Competitor) competitors.get(0)).roundCheck(r);

        return r;
    }

    /**
     *
     * @param round
     * @param r
     * @param third_place
     */
    private static void genCup(final Round round, final Round r, final boolean third_place) {
        //final ArrayList<Match> matchs = new ArrayList<>(round.getMatchs());
        int nb_match = (int) Math.pow(2, round.getCupMaxTour() - round.getCupTour() - 1);

        final ArrayList<Competitor> _winners = new ArrayList<>();
        final ArrayList<Competitor> _loosers = new ArrayList<>();

        Competitor nullElt;
        if ((Tournament.getTournament().getParams().isTeamTournament()) && (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
            nullElt = Team.getNullTeam();
        } else {
            nullElt = Coach.getNullCoach();
        }

        if (nb_match == 0) {
            if (round.getCupTour() == round.getCupMaxTour() + 1) {
                _loosers.add(round.getMatch(0).getWinner());
            } else {
                _winners.add(round.getMatch(0).getWinner());
                _loosers.add(round.getMatch(1).getWinner());
            }
        }
        for (int i = 0; i < nb_match; i++) {
            _winners.add(round.getMatch(i).getWinner());
            _loosers.add(round.getMatch(i).getLooser());
        }
        if (nb_match > 0) {
            final int option = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VOULEZ VOUS MÉLANGER LE(S) TABLEAU(X) ?"), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION);
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
        if (round.isLooserCup()) {
            int nb_remaining_match = 0;
            if (round.getCupTour() > 0) {
                nb_remaining_match = nb_match;
            }
            if (round.getCupTour() > 1) {
                nb_remaining_match += nb_match;
            }

            if (nb_match == 1) {
                nb_remaining_match = 3;
            }
            if (nb_match == 0) {
                nb_remaining_match = 1;
                if (round.getCupTour() == round.getCupMaxTour() + 1) {
                    nb_match = 1;
                } else {
                    nb_match = 2;
                }
            }
            for (int i = nb_match; (i < nb_match + nb_remaining_match) && (i < round.getMatchsCount()); i++) {
                _loosers.add(round.getMatch(i).getWinner());
            }

            /**
             * Patch For ensure that the winner of the last looser cup match is
             * pushed into the winners'cup All the coachs presents in winner cup
             * are removed from the looser cup
             */
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
        }
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
        for (int i = 0; i < nb_match / 2; i++) {
            comps.get(2 * i).addMatch(comps.get(nbPlayers - 2 * i - 1), r);
        }
        for (int i = 0; i < nb_match / 2; i++) {
            comps.get(2 * i + 1).addMatch(comps.get(nbPlayers - 2 * i - 2), r);
        }
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

        applyPortugal(r);

        final StringBuffer filename = new StringBuffer(Tournament.getTournament().getParams().getTournamentName());
        filename.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("."));
        filename.append(Tournament.getTournament().getRoundsCount());
        final Date date = new Date();
        filename.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("."));
        filename.append(Long.toString(date.getTime()));
        filename.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".XML"));
        final File file = new File(filename.toString());

        Tournament.getTournament().saveXML(file);
    }

    /**
     *
     * @param team
     * @param rounds
     * @return
     */
    public static ArrayList<ObjectRanking> subRanking(final Team team, final ArrayList<Round> rounds) {
        ArrayList<Coach> array = new ArrayList<>();
        for (int i = 0; i < team.getCoachCount(); i++) {
            array.add(team.getCoach(i));
        }
        return subRanking(array, rounds);
    }

    /**
     *
     * @param coachs
     * @param rounds
     * @return
     */
    public static ArrayList<ObjectRanking> subRanking(final ArrayList<Coach> coachs, final ArrayList<Round> rounds) {

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
                    value1 = MjtRankingIndiv.getValue(c, m, params.getRankingIndiv1(), value1);
                    value2 = MjtRankingIndiv.getValue(c, m, params.getRankingIndiv2(), value2);
                    value3 = MjtRankingIndiv.getValue(c, m, params.getRankingIndiv3(), value3);
                    value4 = MjtRankingIndiv.getValue(c, m, params.getRankingIndiv4(), value4);
                    value5 = MjtRankingIndiv.getValue(c, m, params.getRankingIndiv5(), value5);
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
                    for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                        coaches.add(Tournament.getTournament().getCoach(i));
                    }
                    genQSwiss(coaches, datas, r);
                } else {
                    ArrayList<Team> teams = new ArrayList<>();
                    for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                        teams.add(Tournament.getTournament().getTeam(cpt));
                    }
                    Generation.genQSwiss(teams, datas, r);
                }
            } else {
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    r = null;
                } else {
                    final ArrayList<Coach> coaches = new ArrayList<>();
                    for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                        coaches.add(Tournament.getTournament().getCoach(i));
                    }
                    Generation.genQSwiss(coaches, datas, r);
                }
            }
        } else {
            for (int i = 0; i < tour.getPoolCount(); i++) {
                datas = getSortedRankingData(tour.getPool(i), roundnumber);
                Generation.genQSwiss(tour.getPool(i).getCompetitors(), datas, r);
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
    private static Round nextRoundCup(final Round round, final int roundnumber) {

        final Tournament tour = Tournament.getTournament();
        Round r = new Round();
        //final Calendar cal = Calendar.getInstance();
        r.setCurrentHour();
        if ((round != null) && (round.isCup())) {
            if (((round.getCupTour() == round.getCupMaxTour() - 1) && (!round.isLooserCup()))
                    || (round.getCupTour() == round.getCupMaxTour() + 2)) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TOUR FINAL ATTEINT"), StringConstants.CS_CUP, JOptionPane.ERROR_MESSAGE);
                r = null;
            } else {

                boolean _third_place = false;
                if ((round.getCupTour() == round.getCupMaxTour() - 2) && (!round.isLooserCup())) {
                    _third_place = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VOULEZ VOUS GÉNÉRER LE MATCH POUR LA 3E PLACE ?"), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                }

                genCup(round, r, _third_place);

                r.setCup(true);
                r.setCupMaxTour(round.getCupMaxTour());
                r.setCupTour(round.getCupTour() + 1);
                r.setLooserCup(round.isLooserCup());

            }
        } else {
            int cup_max_tour = 0;
            int nb_tmp = 1;
            r.setLooserCup(JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SAGIT-IL D'UN TOURNOI À DOUBLE ÉLIMINATION ?"), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
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
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    r = null;
                } else {
                    while (nb_tmp < tour.getActiveCoachNumber()) {
                        nb_tmp *= 2;
                        cup_max_tour++;
                    }
                }
            }

            if (r != null) {
                final JPanel message = new JPanel();
                message.setLayout(new BorderLayout());

                final JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE TOURS: "));
                // generate cup for the first round
                final JSpinner jspNbTeams = new JSpinner();
                final SpinnerNumberModel model = new SpinnerNumberModel(1, 1, cup_max_tour, 1);
                jspNbTeams.setModel(model);

                message.add(jlb, BorderLayout.NORTH);
                message.add(jspNbTeams, BorderLayout.CENTER);

                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), message, StringConstants.CS_CUP, JOptionPane.QUESTION_MESSAGE);

                cup_max_tour = (Integer) model.getValue();
                final int nb = (int) Math.pow(2, cup_max_tour);

                MjtRanking datas;
                datas = getSortedRankingData(roundnumber);

                genCupFirst(r, datas, nb);

                r.setCup(true);
                r.setCupMaxTour(cup_max_tour);
                r.setCupTour(0);

            }
        }

        if (r != null) {
            if (r.getMatchsCount() < tour.getActiveCoachNumber() / 2) {
                int res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AFFECTER UNE RONDE AUX JOUEURS RESTANTS ?"), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION);
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
                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
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

                        res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UTILISER UNE RONDE SUISSE (ALÉATOIRE SINON)? "), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION) {

                            if (tour.getParams().isTeamTournament()) {
                                if (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                                    final ArrayList<Coach> coaches = new ArrayList<>();
                                    for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                                        coaches.add(Tournament.getTournament().getCoach(i));
                                    }
                                    genSwiss(coaches, datas, r2);
                                } else {
                                    ArrayList<Team> tmp_teams = new ArrayList<>();
                                    for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                                        tmp_teams.add(Tournament.getTournament().getTeam(cpt));
                                    }
                                    genSwiss(tmp_teams, datas, r2);
                                }
                            } else {
                                if (tour.getActiveCoachNumber() % 2 > 0) {
                                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                                    r = null;
                                } else {
                                    final ArrayList<Coach> coaches = new ArrayList<>();
                                    for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                                        coaches.add(Tournament.getTournament().getCoach(i));
                                    }
                                    genSwiss(coaches, datas, r2);
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
                                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
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
    private static Round nextRoundSwiss(final Round round, final int roundnumber) {

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
                    for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                        coaches.add(Tournament.getTournament().getCoach(i));
                    }
                    genSwiss(coaches, datas, r);
                } else {
                    ArrayList<Team> tmp_teams = new ArrayList<>();
                    for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                        tmp_teams.add(Tournament.getTournament().getTeam(cpt));
                    }
                    genSwiss(tmp_teams, datas, r);
                }
            } else {
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    r = null;
                } else {
                    final ArrayList<Coach> coaches = new ArrayList<>();
                    for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                        coaches.add(Tournament.getTournament().getCoach(i));
                    }
                    genSwiss(coaches, datas, r);
                }
            }
        } else {
            for (int i = 0; i < tour.getPoolCount(); i++) {
                datas = getSortedRankingData(tour.getPool(i), roundnumber);
                genSwiss(tour.getPool(i).getCompetitors(), datas, r);
            }
        }
        return r;
    }

    private static MjtRanking getSortedRankingData(final int roundnumber) {
        final Tournament tour = Tournament.getTournament();
        MjtRanking ranking;
        if ((tour.getParams().isTeamTournament()) && (tour.getParams().getTeamPairing() != ETeamPairing.INDIVIDUAL_PAIRING)) {
            ArrayList<Team> tmp_teams = new ArrayList<>();
            for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                tmp_teams.add(Tournament.getTournament().getTeam(cpt));
            }
            ranking = new MjtRankingTeam(tour.getParams().isTeamVictoryOnly(), roundnumber,
                    tmp_teams, false);

        } else {
            final boolean forPool = (tour.getPoolCount() > 0) && (!tour.getRound(roundnumber).isCup());
            final ArrayList<Coach> coaches = new ArrayList<>();
            for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                Coach coach=Tournament.getTournament().getCoach(i);                
                coaches.add(coach);
            }
            ranking = new MjtRankingIndiv(roundnumber, tour.getParams().getRankingIndiv1(), tour.getParams().getRankingIndiv2(), tour.getParams().getRankingIndiv3(), tour.getParams().getRankingIndiv4(), tour.getParams().getRankingIndiv5(),
                    coaches, false, false, forPool);

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
            ranking = new MjtRankingIndiv(roundnumber, tour.getParams().getRankingIndiv1(), tour.getParams().getRankingIndiv2(), tour.getParams().getRankingIndiv3(), tour.getParams().getRankingIndiv4(), tour.getParams().getRankingIndiv5(), p.getCompetitors(), false, false, forPool);

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

        /*  final ArrayList<Round> v = new ArrayList<>();
         final ArrayList<Match> matchs = new ArrayList<>();
         buildUntilRound(round, v, matchs);*/
        Round r = new Round();
        r.clearMatchs();

        if (tour.getPoolCount() == 0) {
            if (tour.getParams().isTeamTournament()) {
                if (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                    Generation.genRandom(tour.getActiveCoaches(), r);
                } else {
                    ArrayList<Team> tmp_teams = new ArrayList<>();
                    for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                        tmp_teams.add(Tournament.getTournament().getTeam(cpt));
                    }
                    Generation.genRandom(tmp_teams, r);
                }
            } else {
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    r = null;
                } else {
                    Generation.genRandom(tour.getActiveCoaches(), r);
                }
            }
        } else {
            for (int i = 0; i < tour.getPoolCount(); i++) {
                Generation.genRandom(tour.getPool(i).getCompetitors(), r);
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

        /*  final ArrayList<Round> v = new ArrayList<>();
         final ArrayList<Match> matchs = new ArrayList<>();
         buildUntilRound(round, v, matchs);*/
        Round r = new Round();
        if (tour.getPoolCount() == 0) {
            if (tour.getParams().isTeamTournament()) {
                if (tour.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                    Generation.genBalanced(tour.getActiveCoaches(), r);
                }
            } else {
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
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
