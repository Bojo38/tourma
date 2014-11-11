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
import java.util.HashMap;
import java.util.Iterator;
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
import tourma.data.Match;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingTeam;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Generation {

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
    protected static String[] mPortugueses = {"yanno",
        "obelix", "oon", "fredriech", "pershum",
        "buldogr", "simon_mlf", "haktar"};
    private static final Logger LOG = Logger.getLogger(Generation.class.getName());

    /**
     *
     * @param round
     */
    protected static void applyPortugal(final Round round) {
        if (Tournament.getTournament().getParams().mPortugal) {
            Criteria td = Tournament.getTournament().getParams().mCriterias.get(0);
            ArrayList<CoachMatch> acm = round.getCoachMatchs();
            for (int i = 0; i < acm.size(); i++) {
                CoachMatch cm = acm.get(i);
                for (int j = 0; j < mPortugueses.length; j++) {
                    if (mPortugueses[j].toLowerCase().equals(((Coach) cm.mCompetitor1).mName.toLowerCase())) {
                        cm.mValues.get(td).mValue1 = -2;
                    }
                    if (mPortugueses[j].toLowerCase().equals(((Coach) cm.mCompetitor2).mName.toLowerCase())) {
                        cm.mValues.get(td).mValue2 = -2;
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
     *
     */
    public static void generateFirstRoundFree() {
        final Round r = new Round();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        final Tournament tour = Tournament.getTournament();
        final ArrayList<Round> rounds = tour.getRounds();
        rounds.clear();
        rounds.add(r);
    }

    /**
     *
     * @param choice
     */
    public static void generateFirstRound(final int choice) {
        final Tournament tour = Tournament.getTournament();
        final Parameters params = tour.getParams();
        final ArrayList<Coach> coachs = tour.getCoachs();
        final ArrayList<Round> rounds = tour.getRounds();
        final ArrayList<Team> vteams = tour.getTeams();

        rounds.clear();
        tour.mRoundRobin = false;

        if (params.mTeamTournament) {
            /**
             * First check the number of active players
             */
            for (int i = 0; i < vteams.size(); i++) {
                if (vteams.get(i).getActivePlayerNumber() != params.mTeamMatesNumber) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MAUVAIS NOMBRE DE JOUEURS ACTIF POUR L'ÉQUIPE") + " " + vteams.get(i).mName);
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
            coachs.get(i).mMatchs = new ArrayList<>();
        }

        ArrayList competitors;
        if (!params.mTeamTournament) {
            competitors = tour.getActiveCoaches();
        } else {
            if (params.mTeamPairing == 0) {
                if (tour.getTeams().size() % 2 > 0) {
                    competitors = tour.getActiveCoaches();
                } else {
                    int res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DoYouWantATeamFirstPairing"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FirstRound"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (res == JOptionPane.YES_OPTION) {
                        competitors = tour.getTeams();
                    } else {
                        competitors = tour.getActiveCoaches();
                    }
                }
            } else {
                competitors = tour.getTeams();
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
                rounds.add(nextRoundBalanced());
                break;

            default:
                break;
        }

        if (rounds.size() > 0) {
            for (int k = 0; k < rounds.size(); k++) {

                Round r = rounds.get(k);
                for (int i = 0; i < r.getMatchs().size(); i++) {
                    final Match m = r.getMatchs().get(i);
                    m.mCompetitor1.mMatchs.add(m);
                    m.mCompetitor2.mMatchs.add(m);

                    if (m instanceof TeamMatch) {
                        for (int j = 0; j < ((TeamMatch) m).mMatchs.size(); j++) {
                            CoachMatch cm = ((TeamMatch) m).mMatchs.get(j);
                            cm.mCompetitor1.mMatchs.add(cm);
                            cm.mCompetitor2.mMatchs.add(cm);
                        }
                    }
                }
                applyPortugal(r);
                /*                
                 for (int i = 0; i < rounds.get(k).getCoachMatchs().size(); i++) {
                 final CoachMatch m = rounds.get(k).getCoachMatchs().get(i);
                 m.mCompetitor1.mMatchs.add(m);
                 m.mCompetitor2.mMatchs.add(m);

                 for (int j = 0; j < params.mCriterias.size(); j++) {
                 final Criteria criteria = params.mCriterias.get(j);
                 final Value val = new Value(criteria);
                 if (j == 0) {
                 val.mValue1 = -1;
                 val.mValue2 = -1;
                 }
                 m.mValues.put(criteria, val);
                 }
                 }*/
            }
        }

        final StringBuffer filename = new StringBuffer(Tournament.getTournament().getParams().mTournamentName);
        filename.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("."));
        filename.append(Tournament.getTournament().getRounds().size());
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
    protected static void generateFirstRoundPool(ArrayList competitors) {

        final Tournament tour = Tournament.getTournament();
        final ArrayList<Round> rounds = tour.getRounds();

        //final ArrayList<Coach> vcoachs = tour.getCoachs();
        //final ArrayList<Team> vteams = tour.getTeams();
        final ArrayList<Pool> pools = tour.getPools();
        pools.clear();

        ArrayList<Competitor> comps = new ArrayList<>(competitors);

        final JPanel message = new JPanel();
        message.setLayout(new BorderLayout());

        final JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE PAR POULE: "));

        final JSpinner jspNb = new JSpinner();
        int max = comps.size() / 2;
        if (max % 2 != 0) {
            max++;
        }

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
            pools.add(p);
            p.mName = Integer.toString(i + 1);
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
                        names.add(comps.get(j).mName);
                    }
                    String question = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHOISISSEZ UN COACH");
                    question = Integer.toString(i + 1) + ": " + question;
                    final int index = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), question, StringConstants.CS_POOL, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, names.toArray(), 0);
                    pools.get(i).mCompetitors.add(comps.get(index));
                    comps.remove(index);
                }
            } else {
                Random ran = new Random();
                for (int i = 0; i < nbPools; i++) {
                    final int index = ran.nextInt() % comps.size();
                    pools.get(i).mCompetitors.add(comps.get(index));
                    comps.remove(index);
                }
            }
            nbCounter--;
        }
        // ++--
        /*if (byTeam) {
         TeamCreatePools();
         } else {
         IndivCreatePools();
         }*/

        Round r = nextRoundRandom(null);
        /*final boolean random = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),
         java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AFFECTATION ALÉATOITE (SINON, L'ORDER D'INSCRIPTION SERA UTILISÉE) ?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GENERATION"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
         if (random) {
         r = nextRoundRandom(null);
         } else {
         r = nextRoundSwiss(null, -1);
         }*/

        rounds.add(r);
    }

    /**
     *
     * @param competitors ArrayList including competitors (Coachs or Teams)
     */
    protected static void generateFirstRoundManual(ArrayList competitors) {

        final Tournament tour = Tournament.getTournament();
        final ArrayList<Round> rounds = tour.getRounds();

        tour.mRoundRobin = false;
        final ArrayList<Competitor> comps = new ArrayList<>(competitors);
        final Round r = new Round();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
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
        rounds.add(r);
    }

    /*    protected static void generateIndividualFirstRoundManual() {

     final Tournament tour = Tournament.getTournament();

     final Parameters params = tour.getParams();
     final ArrayList<Clan> clans = tour.getClans();
     final ArrayList<Round> rounds = tour.getRounds();

     tour.mRoundRobin = false;
     final ArrayList<Coach> coachs = new ArrayList<>(tour.getActiveCoaches());
     final Round r = new Round();
     final Calendar cal = Calendar.getInstance();
     r.setHour(cal.getTime());
     while (coachs.size() > 0) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = coachs.get(0);
     coachs.remove(m.mCompetitor1);
     ArrayList<Coach> possibleCoachs = new ArrayList(coachs);

     if (m.mCompetitor1.mClan != clans.get(0)) {
     if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
     for (int i = 0; i
     < possibleCoachs.size(); i++) {
     if (possibleCoachs.get(i).mClan.mName.equals(m.mCompetitor1.mClan.mName)) {
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
     final StringBuffer tmpString = new StringBuffer(possibleCoachs.get(i).mName);
     tmpString.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" ("));
     tmpString.append(possibleCoachs.get(i).mRoster.mName);

     //String tmpString = possibleCoachs.get(i).mName + " (" + possibleCoachs.get(i).mRoster.mName + ")";
     if (params.mEnableClans) {
     tmpString.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/"));
     tmpString.append(possibleCoachs.get(i).mClan.mName);
     }
     tmpString.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(")"));
     possibilities[i] = tmpString.toString();
     }
     final ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);
     final StringBuffer coachString = new StringBuffer(m.mCompetitor1.mName);
     coachString.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" ("));
     coachString.append(m.mCompetitor1.mRoster.mName);
     //String coachString = m.mCompetitor1.mName + " (" + m.mCompetitor1.mRoster.mName + ")";
     if (params.mEnableClans) {
     coachString.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" / "));
     coachString.append(m.mCompetitor1.mClan.mName);
     //coachString = coachString + " (" + m.mCompetitor1.mClan.mName + ")";
     }
     coachString.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(")"));

     String opp = null;

     if (possibilities.length > 0) {
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

     final StringBuffer tmpString = new StringBuffer(possibleCoachs.get(i).mName);
     tmpString.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" ("));
     tmpString.append(possibleCoachs.get(i).mRoster.mName);

     //String tmpString = possibleCoachs.get(i).mName + " (" + possibleCoachs.get(i).mRoster.mName + ")";
     if (params.mEnableClans) {
     tmpString.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/"));
     tmpString.append(possibleCoachs.get(i).mClan.mName);
     }
     tmpString.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(")"));

     if (opp.equals(tmpString.toString())) {
     m.mCompetitor2 = possibleCoachs.get(i);
     coachs.remove(m.mCompetitor2);
     break;
     }
     }
     r.getMatchs().add(m);
     } else {
     break;
     }
     }
     rounds.add(r);
     }*/

    /**
     *
     * @param competitors
     * @param random
     * @param naf
     */
    
    protected static void generateFirstRoundOrder(ArrayList competitors, final boolean random, final boolean naf) {

        final Tournament tour = Tournament.getTournament();
        final ArrayList<Round> rounds = tour.getRounds();

        tour.mRoundRobin = false;
        final Round r = new Round();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());

        final ArrayList<Competitor> shuffle = new ArrayList<>(competitors);
        if (naf) {
            Collections.sort(shuffle);
        } else {
            if (random) /* Aléatoire */ {
                Collections.shuffle(shuffle);
            }
        }
        r.getMatchs().clear();

        while (shuffle.size() > 0) {
            Competitor c = shuffle.get(0);
            shuffle.remove(c);
            ArrayList<Competitor> possible = c.getPossibleOpponents(shuffle, r);
            c.addMatch(possible.get(0), r);
            shuffle.remove(possible.get(0));
        }

        rounds.add(r);
    }

    /**
     *
     * @param k
     * @param r
     * @param RotateList
     * @param evaluationPreviousC
     * @param evaluationPreviousT
     */
    protected static void permutation(int k,
            ArrayList<Competitor> r,
            ArrayList<ArrayList<Competitor>> RotateList,
            HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC,
            HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT) {
        int fact = 1;
        int compteur = 0; //Le compteur sert à déterminer s'il y a eu une permutation

        for (int i = 2; i < r.size() + 1; i++) {
            fact = fact * (i - 1);
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
    }

    /**
     *
     * @param i
     * @param j
     * @param A
     */
    protected static void swap(int i, int j, ArrayList<Competitor> A) {
        Competitor oi = A.get(i);
        Competitor oj = A.get(j);
        A.set(j, oi);
        A.set(i, oj);
    }

    /**
     *
     * @param n
     * @return
     */
    protected static int factorielle(int n) {
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
    protected static boolean isDrawValidForBalanced(ArrayList<Competitor> l, HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC,
            HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousT) {
        boolean valid = true;

        Round r = new Round();

        /*
         * Optimize algorithm for avoid same team players
         */
        ArrayList<Competitor> l2 = new ArrayList<>(l);

        for (int i = 0; i < l.size() - 1; i++) {
            Competitor c1 = l.get(i);
            Competitor c2 = l.get(i + 1);
            if ((c1 instanceof Coach) && (c2 instanceof Coach)) {
                Coach co1 = (Coach) c1;
                Coach co2 = (Coach) c2;
                if (co1.getTeamMates().mCoachs.contains(co2)) {
                    if (i < l.size() - 2) {
                        for (int j = i + 2; j < l.size(); j++) {
                            if (!((Coach) l.get(j)).getTeamMates().mCoachs.contains(co1)) {
                                swap(i + 1, j, l);
                                break;
                            }
                        }
                    } else {
                        for (int j = i - 1; j > 0; j--) {
                            if (!((Coach) l.get(j)).getTeamMates().mCoachs.contains(co1)) {
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
                if (co1.getTeamMates().mCoachs.contains(co2)) {
                    valid = false;
                    break;
                }
            }
            CoachMatch cm = new CoachMatch(r);
            cm.mCompetitor1 = c1;
            cm.mCompetitor2 = c2;
            r.getMatchs().add(cm);
        }

        if (valid) {

            HashMap<Competitor, HashMap<Team, Integer>> evaluationC = new HashMap<>(evaluationPreviousC);
            HashMap<Competitor, HashMap<Team, Integer>> evaluationT = new HashMap<>(evaluationPreviousT);

            Iterator it = evaluationPreviousC.keySet().iterator();
            while (it.hasNext()) {
                Competitor c = (Competitor) it.next();
                HashMap<Team, Integer> hm = new HashMap<>(evaluationPreviousC.get(c));
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
                Coach c1 = (Coach) m.mCompetitor1;
                Coach c2 = (Coach) m.mCompetitor2;

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
                System.out.println(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FOUND "));

                for (int i = 0; i < l.size() / 2; i++) {
                    Competitor c1 = l.get(2 * i);
                    Competitor c2 = l.get(2 * i + 1);
                    System.out.println(i + ". " + c1.mName + " vs " + c2.mName);
                }
            }
        }

        if (!valid) {
            l.clear();
            l.addAll(l2);
        }

        return valid;
    }

    /**
     *
     * @param l
     * @param RotateList
     * @param evaluationPreviousC
     * @param evaluationPreviousT
     */
    protected static void rotateListForBalanced(ArrayList<Competitor> l, ArrayList<ArrayList<Competitor>> RotateList, HashMap<Competitor, HashMap<Team, Integer>> evaluationPreviousC,
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
                if (co1.getTeamMates().mCoachs.contains(co2)) {
                    if (i < l.size() - 2) {
                        for (int j = i + 2; j < l.size(); j++) {
                            if (!((Coach) l.get(j)).getTeamMates().mCoachs.contains(co1)) {
                                swap(i + 1, j, l);
                                break;
                            }
                        }
                    } else {
                        for (int j = i - 1; j > 0; j--) {
                            if (!((Coach) l.get(j)).getTeamMates().mCoachs.contains(co1)) {
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
    }

    /**
     *
     * @param competitors
     * @param r
     */
    protected static void genBalanced(ArrayList competitors, Round r) {

        /*        final Tournament tour = Tournament.getTournament();
         final ArrayList<Round> rounds = tour.getRounds();

         tour.mRoundRobin = false;
         final Calendar cal = Calendar.getInstance();
         r.setHour(cal.getTime());

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

    /*protected static void generateIndividualFirstRoundOrder(final boolean random, final boolean naf) {

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
     final ArrayList<Coach> shuffle = new ArrayList<>(tour.getActiveCoaches());
     if (naf) {
     Collections.sort(shuffle);
     } else {
     if (random) {
     Collections.shuffle(shuffle);
     }
     }
     r.getMatchs().clear();

     while (shuffle.size() > 0) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = shuffle.get(0);
     shuffle.remove(m.mCompetitor1);
     if (m.mCompetitor1.mClan != clans.get(0)) {
     if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
     int j = 0;
     while (j < shuffle.size() && (m.mCompetitor1.mClan == shuffle.get(j).mClan)) {
     j++;
     }
     if (j == shuffle.size()) {
     NotOK = true;
     j = 0;
     }
     m.mCompetitor2 = shuffle.get(j);
     shuffle.remove(j);
     } else {
     m.mCompetitor2 = shuffle.get(0);
     shuffle.remove(0);
     }
     } else {
     m.mCompetitor2 = shuffle.get(0);
     shuffle.remove(0);
     }
     r.getMatchs().add(m);
     }

     counter++;
     }

     boolean bSameClan = false;
     for (int i = 0; i < r.getMatchs().size(); i++) {
     final CoachMatch m = r.getMatchs().get(i);
     if (m.mCompetitor1.mClan != clans.get(0)) {
     if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
     if (m.mCompetitor1.mClan == m.mCompetitor2.mClan) {
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
     final ArrayList<Coach> shuffle = new ArrayList<>(tour.getActiveCoaches());
     if (random){
     Collections.shuffle(shuffle);
     }

     r.getMatchs().clear();

     while (shuffle.size() > 0) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = shuffle.get(0);
     shuffle.remove(m.mCompetitor1);
     if (m.mCompetitor1.mClan != clans.get(0)) {
     if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
     int j = 0;
     while (j < shuffle.size() && (m.mCompetitor1.mClan == shuffle.get(j).mClan)) {
     j++;
     }
     if (j == shuffle.size()) {

     j = 0;
     }
     m.mCompetitor2 = shuffle.get(j);
     shuffle.remove(j);
     } else {
     m.mCompetitor2 = shuffle.get(0);
     shuffle.remove(0);
     }
     } else {
     m.mCompetitor2 = shuffle.get(0);
     shuffle.remove(0);
     }
     r.getMatchs().add(m);
     }
     } else {
     JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("CanMatchKey"));
     final ArrayList<Coach> shuffle;
     shuffle = new ArrayList<>(tour.getActiveCoaches());
     if (random)  {
     Collections.shuffle(shuffle);
     }
     for (int i = 0; i
     < shuffle.size() / 2; i++) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = shuffle.get(2 * i);
     m.mCompetitor2 = shuffle.get(2 * i + 1);
     r.getMatchs().add(m);
     }
     }
     }
     rounds.add(r);
     }*/

    /**
     *
     * @param competitors
     */
    
    protected static void generateFirstRoundRobin(ArrayList competitors) {

        final Tournament tour = Tournament.getTournament();
        final ArrayList<Round> rounds = tour.getRounds();
        tour.mRoundRobin = false;
        final Calendar cal = Calendar.getInstance();
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
            r.setHour(cal.getTime());

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

            rounds.add(r);
        }
    }

    /*protected static void generateIndividualFirstRoundRobin() {

     final Tournament tour = Tournament.getTournament();
     final ArrayList<Round> rounds = tour.getRounds();
     tour.mRoundRobin = false;
     final Calendar cal = Calendar.getInstance();
     final boolean home_away = (JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH ALLER-RETOUR ?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ALLER-RETOUR"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

     final ArrayList<Coach> coachs = tour.getActiveCoaches();
     Collections.shuffle(coachs);

     final ArrayList<Coach> c1part = new ArrayList<>();
     final ArrayList<Coach> c2part = new ArrayList<>();

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
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = c1part.get(j);
     m.mCompetitor2 = c2part.get(j);
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
     }*/

    /**
     *
     * @param competitors
     */
    
    protected static void generateFirstRoundCup(ArrayList competitors) {

        final Tournament tour = Tournament.getTournament();

        final ArrayList<Round> rounds = tour.getRounds();

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
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());

        r.mLooserCup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SAGIT-IL D'UN TOURNOI À DOUBLE ÉLIMINATION ?"), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        // there is nb_tmp/matchs

        for (int i = 0; i < nb_matchs / 2; i++) {
            comps.get(2 * i).addMatch(comps.get(nb_tmp - 2 * i - 1), r);
        }
        for (int i = 0; i < nb_matchs / 2; i++) {
            comps.get(2 * i + 1).addMatch(comps.get(nb_tmp - 2 * i - 2), r);
        }

        r.mCup = true;
        r.mCupMaxTour = nbrounds;
        r.mCupTour = 0;
        //r.mLooserCup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Sagit-il d'un tournoi à double élimination ?", StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        rounds.add(r);
    }

    /**
     *
     * @param competitors
     * @param datas
     * @param r
     * @return
     */
    protected static Round genSwiss(final ArrayList competitors, final ArrayList<ObjectRanking> datas, final Round r) {

        final Tournament tour = Tournament.getTournament();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        final ArrayList<ObjectRanking> datas_tmp = new ArrayList<>(datas);

        ArrayList comps = new ArrayList(competitors);

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

        if (comps.size() % 2 == 1) {
            if (comps.get(0) instanceof Coach) {
                comps.add(Coach.getNullCoach());
            }
            if (comps.get(0) instanceof Team) {
                comps.add(Team.getNullTeam());
            }
        }

        if (comps.size() - 1 <= tour.getRounds().size()) {
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

    /*protected static void affectTeamCoachs(final Round round, final Team team1, final Team team2, final Round r) {
     final Tournament tour = Tournament.getTournament();
     final ArrayList<Round> vs = new ArrayList<>();
     for (int i = 0; i < tour.getRounds().size(); i++) {
     if (tour.getRounds().get(i).getHour().before(round.getHour())) {
     vs.add(tour.getRounds().get(i));
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
    
    protected static Round genQSwiss(final ArrayList competitors, final ArrayList<ObjectRanking> datas, Round r) {

        final Tournament tour = Tournament.getTournament();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());

        final ArrayList<ObjectRanking> datas_tmp;
        datas_tmp = new ArrayList(datas);

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
        final int size = datas.size();
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
            if (competitors.size() - 1 <= tour.getRounds().size()) {
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

    /*protected static Round IndivQSwiss(final ArrayList coachs, final ArrayList<Match> matchs, final ArrayList<ObjectRanking> datas, final boolean team, Round r) {

     final Tournament tour = Tournament.getTournament();
     final Calendar cal = Calendar.getInstance();
     r.setHour(cal.getTime());
     final ArrayList<ObjectRanking> datas_tmp;
     datas_tmp = new ArrayList<>(datas);
     for (int i = 0; i < coachs.size(); i++) {
     final Coach c = (Coach) coachs.get(i);
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
     JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LA MÉTHODE SUISSE ACCELÉRÉE N'EST PAS APPLICABLE"));
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
     final ArrayList<ObjectRanking> datas_1 = new ArrayList<>();
     final ArrayList<ObjectRanking> datas_2 = new ArrayList<>();
     final ArrayList<ObjectRanking> datas_3 = new ArrayList<>();
     final ArrayList<ObjectRanking> datas_4 = new ArrayList<>();
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
     if (Tournament.getTournament().getActiveCoaches().size() - 1 <= tour.getRounds().size()) {
     for (int i = 0; i < datas_1.size(); i++) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) datas_1.get(i).getObject();
     m.mCompetitor2 = (Coach) datas_2.get(i).getObject();
     r.getMatchs().add(m);
     }
     for (int i = 0; i < datas_3.size(); i++) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) datas_3.get(i).getObject();
     m.mCompetitor2 = (Coach) datas_4.get(i).getObject();
     r.getMatchs().add(m);
     }
     JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NotEnoughRoundToAvoidSameMatch"));
     } else {
     final ArrayList<ObjectRanking> datas_tmp1 = new ArrayList<>(datas_1);
     final ArrayList<ObjectRanking> datas_tmp2 = new ArrayList<>(datas_2);
     while ((datas_tmp1.size() > 0) && (datas_tmp2.size() > 0)) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) datas_tmp1.get(0).getObject();
     datas_tmp1.remove(0);
     for (int i = 0; i < datas_tmp2.size(); i++) {
     boolean have_played = false;
     for (int j = 0; j < matchs.size(); j++) {
     if (((matchs.get(j).mCompetitor1 == m.mCompetitor1) && (matchs.get(j).mCompetitor2 == datas_tmp2.get(i).getObject())) || ((matchs.get(j).mCompetitor2 == m.mCompetitor1) && (matchs.get(j).mCompetitor1 == datas_tmp2.get(i).getObject()))) {
     have_played = true;
     }
     }
     if ((team) && (!have_played)) {
     for (int j = 0; j < m.mCompetitor1.mTeamMates.mCoachs.size(); j++) {
     if (m.mCompetitor1.mTeamMates.mCoachs.get(j) == datas_tmp2.get(i).getObject()) {
     have_played = true;
     }
     }
     }
     boolean canMatch = !have_played;
     if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
     if (!m.mCompetitor1.mClan.mName.equals(StringConstants.CS_NONE)) {
     if (m.mCompetitor1.mClan == ((Coach) datas_tmp2.get(i).getObject()).mClan) {
     canMatch = false;
     }
     }
     }
     if ((canMatch) || (i == datas_tmp2.size() - 1)) {
     m.mCompetitor2 = (Coach) datas_tmp2.get(i).getObject();
     datas_tmp2.remove(i);
     break;
     }
     }
     r.getMatchs().add(m);
     }
     final ArrayList<ObjectRanking> datas_tmp3 = new ArrayList<>(datas_3);
     final ArrayList<ObjectRanking> datas_tmp4 = new ArrayList<>(datas_4);
     while ((datas_tmp3.size() > 0) && (datas_tmp4.size() > 0)) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) datas_tmp3.get(0).getObject();
     datas_tmp3.remove(0);
     for (int i = 0; i < datas_tmp4.size(); i++) {
     boolean have_played = false;
     for (int j = 0; j < matchs.size(); j++) {
     if (((matchs.get(j).mCompetitor1 == m.mCompetitor1) && (matchs.get(j).mCompetitor2 == datas_tmp4.get(i).getObject())) || ((matchs.get(j).mCompetitor2 == m.mCompetitor1) && (matchs.get(j).mCompetitor1 == datas_tmp4.get(i).getObject()))) {
     have_played = true;
     }
     }
     if ((team) && (!have_played)) {
     for (int j = 0; j < m.mCompetitor1.mTeamMates.mCoachs.size(); j++) {
     if (m.mCompetitor1.mTeamMates.mCoachs.get(j) == datas_tmp4.get(i).getObject()) {
     have_played = true;
     }
     }
     }
     boolean canMatch = !have_played;
     if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
     if (!m.mCompetitor1.mClan.mName.equals(StringConstants.CS_NONE)) {
     if (m.mCompetitor1.mClan == ((Coach) datas_tmp4.get(i).getObject()).mClan) {
     canMatch = false;
     }
     }
     }
     if ((canMatch) || (i == datas_tmp4.size() - 1)) {
     m.mCompetitor2 = (Coach) datas_tmp4.get(i).getObject();
     datas_tmp4.remove(i);
     break;
     }
     }
     r.getMatchs().add(m);
     }
     final ArrayList<ObjectRanking> datas2 = new ArrayList<>(datas_tmp);
     for (int i = r.getMatchs().size() - 1; i > 0; i--) {
     boolean have_played = false;
     final Coach c1 = r.getMatchs().get(i).mCompetitor1;
     final Coach c2 = r.getMatchs().get(i).mCompetitor2;
     for (int j = 0; j < matchs.size(); j++) {
     if (((matchs.get(j).mCompetitor1 == c1) && (matchs.get(j).mCompetitor2 == c2)) || ((matchs.get(j).mCompetitor1 == c2) && (matchs.get(j).mCompetitor2 == c1))) {
     have_played = true;
     }
     }
     if (have_played) {
     for (int k = i - 1; k >= 0; k--) {
     for (int j = 0; j < matchs.size(); j++) {
     have_played = false;
     if (((matchs.get(j).mCompetitor1 == c2) && (matchs.get(j).mCompetitor2 == r.getMatchs().get(k).mCompetitor2)) || ((matchs.get(j).mCompetitor2 == c2) && (matchs.get(j).mCompetitor1 == r.getMatchs().get(k).mCompetitor2))) {
     have_played = true;
     break;
     }
     }
     boolean canMatch = !have_played;
     if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
     if (!r.getMatchs().get(i).mCompetitor2.mClan.mName.equals(StringConstants.CS_NONE)) {
     if (r.getMatchs().get(i).mCompetitor2.mClan == r.getMatchs().get(k).mCompetitor2.mClan) {
     canMatch = false;
     }
     }
     }
     if (canMatch) {
     r.getMatchs().get(i).mCompetitor1 = r.getMatchs().get(k).mCompetitor2;
     r.getMatchs().get(k).mCompetitor2 = c1;
     break;
     }
     for (int j = 0; j < matchs.size(); j++) {
     have_played = false;
     if (((matchs.get(j).mCompetitor1 == c2) && (matchs.get(j).mCompetitor2 == r.getMatchs().get(k).mCompetitor1)) || ((matchs.get(j).mCompetitor2 == c2) && (matchs.get(j).mCompetitor1 == r.getMatchs().get(k).mCompetitor1))) {
     have_played = true;
     break;
     }
     }
     canMatch = !have_played;
     if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
     if (!r.getMatchs().get(i).mCompetitor1.mClan.mName.equals(StringConstants.CS_NONE)) {
     if (r.getMatchs().get(i).mCompetitor2.mClan == r.getMatchs().get(k).mCompetitor1.mClan) {
     canMatch = false;
     }
     }
     }
     if (canMatch) {
     r.getMatchs().get(i).mCompetitor1 = r.getMatchs().get(k).mCompetitor1;
     r.getMatchs().get(k).mCompetitor1 = c1;
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

     }*/

    /*protected static Round TeamRandom(final ArrayList teams, final ArrayList<Match> matchs, final boolean random, final boolean free_pairing, final ArrayList<Round> rounds, final Round r) {

     final Tournament tour = Tournament.getTournament();
     final Calendar cal = Calendar.getInstance();
     r.setHour(cal.getTime());
     final ArrayList<Team> datas = new ArrayList<>(teams);
     boolean bOK = false;
     int count = 0;
     while (!bOK) {
     count++;
     Collections.shuffle(datas);
     if (count > 100000) {
     JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LE MOTEUR ALÉATOIRE N'EST PAS PARVENU À ÉVITER LES DOUBLES RENCONTRES"));
     for (int i = 0; i < datas.size() / 2; i++) {
     final Team team1 = datas.get(2 * i);
     final Team team2 = datas.get(2 * i + 1);
     if (!free_pairing) {
     if (!random) {
     final ArrayList<ObjectRanking> coachs1 = subRanking(team1.mCoachs, rounds);
     final ArrayList<ObjectRanking> coachs2 = subRanking(team2.mCoachs, rounds);
     for (int j = 0; j < coachs1.size(); j++) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) coachs1.get(j).getObject();
     m.mCompetitor2 = (Coach) coachs2.get(j).getObject();
     r.getMatchs().add(m);
     }
     } else {
     final ArrayList<Coach> coachs1 = new ArrayList<>(team1.mCoachs);
     Collections.shuffle(coachs1);
     final ArrayList<Coach> coachs2 = new ArrayList<>(team2.mCoachs);
     Collections.shuffle(coachs2);
     for (int j = 0; j < coachs1.size(); j++) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) coachs1.get(j);
     m.mCompetitor2 = (Coach) coachs2.get(j);
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
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) coachs1.get(j).getObject();
     m.mCompetitor2 = (Coach) coachs2.get(j).getObject();
     r.getMatchs().add(m);
     }
     } else {
     final ArrayList<Coach> coachs1 = new ArrayList<>(team1.mCoachs);
     Collections.shuffle(coachs1);
     final ArrayList<Coach> coachs2 = new ArrayList<>(team2.mCoachs);
     Collections.shuffle(coachs2);
     for (int j = 0; j < coachs1.size(); j++) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) coachs1.get(j);
     m.mCompetitor2 = (Coach) coachs2.get(j);
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
     final ArrayList<Team> data_tmp = new ArrayList<>(datas);
     while (data_tmp.size() > 0) {
     final Team team1 = data_tmp.get(0);
     data_tmp.remove(team1);
     Team team2 = null;
     for (int i = 0; i < data_tmp.size(); i++) {
     boolean have_played = false;
     for (int j = 0; j < matchs.size(); j++) {
     if (((matchs.get(j).mCompetitor1.mTeamMates == team1) && (matchs.get(j).mCompetitor2.mTeamMates == data_tmp.get(i))) || ((matchs.get(j).mCompetitor2.mTeamMates == team1) && (matchs.get(j).mCompetitor1.mTeamMates == data_tmp.get(i)))) {
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
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) coachs1.get(j).getObject();
     m.mCompetitor2 = (Coach) coachs2.get(j).getObject();
     r.getMatchs().add(m);
     }
     } else {
     final ArrayList<Coach> coachs1 = new ArrayList<>(team1.mCoachs);
     Collections.shuffle(coachs1);
     final ArrayList<Coach> coachs2 = new ArrayList<>(team2.mCoachs);
     Collections.shuffle(coachs2);
     for (int j = 0; j < coachs1.size(); j++) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) coachs1.get(j);
     m.mCompetitor2 = (Coach) coachs2.get(j);
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
     }*/

    /**
     *
     * @param competitors
     * @param r
     * @return
     */
    
    protected static Round genRandom(final ArrayList competitors, final Round r) {

        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());

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
             for (int i = 0; i < Tournament.getTournament().getTeams().size(); i++) {
             Team t = Tournament.getTournament().getTeams().get(i);
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

    /*protected static Round TeamSwiss(final ArrayList teams, final ArrayList<Match> matchs, final ArrayList<ObjectRanking> datas, final boolean free_pairing, final ArrayList<Round> rounds, final Round r) {

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
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) coachs1.get(j).getObject();
     m.mCompetitor2 = (Coach) coachs2.get(j).getObject();
     r.getMatchs().add(m);
     }
     } else {
     final jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
     jdg.setVisible(true);
     }
     }
     } else {
     ArrayList<ObjectRanking> datas2 = new ArrayList<>(datas);
     final ArrayList<Team> teams1 = new ArrayList<>();
     final ArrayList<Team> teams2 = new ArrayList<>();
     while (datas2.size() > 0) {
     final Team team1 = (Team) datas2.get(0).getObject();
     Team team2 = null;
     datas2.remove(0);
     for (int i = 0; i < datas2.size(); i++) {
     boolean have_played = false;
     for (int j = 0; j < matchs.size(); j++) {
     if (((matchs.get(j).mCompetitor1.mTeamMates == team1) && (matchs.get(j).mCompetitor2.mTeamMates == datas2.get(i).getObject())) || ((matchs.get(j).mCompetitor2.mTeamMates == team1) && (matchs.get(j).mCompetitor1.mTeamMates == datas2.get(i).getObject()))) {
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
     datas2 = new ArrayList<>(datas);
     for (int i = teams1.size() - 1; i > 0; i--) {
     boolean have_played = false;
     final Team team1 = teams1.get(i);
     final Team team2 = teams2.get(i);
     for (int j = 0; j < matchs.size(); j++) {
     if (((matchs.get(j).mCompetitor1.mTeamMates == team1) && (matchs.get(j).mCompetitor2.mTeamMates == team2)) || ((matchs.get(j).mCompetitor1.mTeamMates == team2) && (matchs.get(j).mCompetitor2.mTeamMates == team1))) {
     have_played = true;
     }
     }
     if (have_played) {
     for (int k = i - 1; k >= 0; k--) {
     for (int j = 0; j < matchs.size(); j++) {
     have_played = false;
     if (((matchs.get(j).mCompetitor1.mTeamMates == team2) && (matchs.get(j).mCompetitor2.mTeamMates == teams2.get(k))) || ((matchs.get(j).mCompetitor2.mTeamMates == team2) && (matchs.get(j).mCompetitor1.mTeamMates == teams2.get(k)))) {
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
     if (((matchs.get(j).mCompetitor1.mTeamMates == team2) && (matchs.get(j).mCompetitor2.mTeamMates == teams1.get(k))) || ((matchs.get(j).mCompetitor2.mTeamMates == team2) && (matchs.get(j).mCompetitor1.mTeamMates == teams1.get(k)))) {
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
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = (Coach) coachs1.get(j).getObject();
     m.mCompetitor2 = (Coach) coachs2.get(j).getObject();
     r.getMatchs().add(m);
     }
     }
     }
     }
     return r;
     }*/

    /**
     *
     * @param round
     * @param r
     * @param third_place
     */
    
    protected static void genCup(final Round round, final Round r, final boolean third_place) {
        final ArrayList<Match> matchs = new ArrayList<>(round.getMatchs());
        int nb_match = (int) Math.pow(2, round.mCupMaxTour - round.mCupTour - 1);

        final ArrayList<Competitor> _winners = new ArrayList<>();
        final ArrayList<Competitor> _loosers = new ArrayList<>();

        Competitor nullElt;
        if ((Tournament.getTournament().getParams().mTeamTournament) && (Tournament.getTournament().getParams().mTeamPairing == 1)) {
            nullElt = Team.getNullTeam();
        } else {
            nullElt = Coach.getNullCoach();
        }

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
        if (round.mLooserCup) {
            int nb_remaining_match = 0;
            if (round.mCupTour > 0) {
                nb_remaining_match = nb_match;
            }
            if (round.mCupTour > 1) {
                nb_remaining_match += nb_match;
            }

            if (nb_match == 1) {
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

            /**
             * Patch For ensure that the winner of the last looser cup match is
             * pushed into the winners'cup All the coachs presents in winner cup
             * are removed from the looser cup
             */
            for (int i = 0; i < r.getMatchs().size(); i++) {
                Competitor c1 = r.getMatchs().get(i).mCompetitor1;
                _loosers.remove(c1);
                Competitor c2 = r.getMatchs().get(i).mCompetitor2;
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

    /*protected static void IndivCup(final Round round, final Round r, final boolean third_place) {
     final ArrayList<CoachMatch> matchs = new ArrayList<>(round.getMatchs());
     int nb_match = (int) Math.pow(2, round.mCupMaxTour - round.mCupTour - 1);

     final ArrayList<Coach> _winners = new ArrayList<>();
     final ArrayList<Coach> _loosers = new ArrayList<>();

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
     final int option = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VOULEZ VOUS MÉLANGER LE(S) TABLEAU(X) ?"), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION);
     if (option == JOptionPane.YES_OPTION) {
     Collections.shuffle(_winners);
     Collections.shuffle(_loosers);
     }
     }
     for (int i = 0; i < (_winners.size() + 1) / 2; i++) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = _winners.get(2 * i);
     if ((2 * i + 1) >= _winners.size()) {
     m.mCompetitor2 = Coach.getNullCoach();
     } else {
     m.mCompetitor2 = _winners.get(2 * i + 1);
     }
     r.getMatchs().add(m);
     }
     if (third_place) {
     if (_loosers.size() == 2) {
     for (int i = 0; i < (_loosers.size() + 1) / 2; i++) {
     final CoachMatch m = new CoachMatch(r);
     m.mCompetitor1 = _loosers.get(2 * i);
     if ((2 * i + 1) >= _loosers.size()) {
     m.mCompetitor2 = Coach.getNullCoach();
     } else {
     m.mCompetitor2 = _loosers.get(2 * i + 1);
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
     _loosers.get(i).addMatch(_loosers.get(_loosers.size() / 2 + i), r);
     }
     }
     }*/

    /*protected static void IndivCupAffectation(final Round r, final ArrayList<ObjectRanking> datas, final boolean teamTour, final int nbPlayers) {
     final int nb_match = nbPlayers / 2;
     final ArrayList<Coach> coachs = new ArrayList<>();
     for (int i = 0; i < nbPlayers; i++) {
     Coach c;
     if (i < datas.size()) {
     c = ((Coach) ((ObjectRanking) datas.get(i)).getObject());
     } else {
     c = Coach.getNullCoach();
     }
     coachs.add(c);
     }
     for (int i = 0; i < nb_match / 2; i++) {
     coachs.get(2 * i).addMatch(coachs.get(nbPlayers - 2 * i - 1), r);
     }
     for (int i = 0; i < nb_match / 2; i++) {
     coachs.get(2 * i + 1).addMatch(coachs.get(nbPlayers - 2 * i - 2), r);
     }
     }*/

    /**
     *
     * @param r
     * @param datas
     * @param nbPlayers
     */
    
    protected static void genCupFirst(final Round r, final ArrayList<ObjectRanking> datas, final int nbPlayers) {
        final int nb_match = nbPlayers / 2;
        final ArrayList<Competitor> comps = new ArrayList<>();
        Competitor nullElt;
        if ((((ObjectRanking) datas.get(0)).getObject()) instanceof Coach) {
            nullElt = Coach.getNullCoach();
        } else {
            nullElt = Team.getNullTeam();
        }
        for (int i = 0; i < nbPlayers; i++) {
            Competitor c;
            if (i < datas.size()) {
                c = ((Competitor) ((ObjectRanking) datas.get(i)).getObject());
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

    /*    protected static Round TeamQSwiss(final ArrayList teams, final ArrayList<Match> matchs, final ArrayList<ObjectRanking> datas, final ArrayList<Round> rounds, Round r) {
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
     JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LA MÉTHODE SUISSE ACCELÉRÉE N'EST PAS APPLICABLE"));
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
     final ArrayList<ObjectRanking> datas_1 = new ArrayList<>();
     final ArrayList<ObjectRanking> datas_2 = new ArrayList<>();
     final ArrayList<ObjectRanking> datas_3 = new ArrayList<>();
     final ArrayList<ObjectRanking> datas_4 = new ArrayList<>();
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
     team1.addMatch(team2, r);
     }
     for (int i = 0; i < datas_1.size(); i++) {
     final Team team1 = (Team) datas_1.get(i).getObject();
     final Team team2 = (Team) datas_2.get(i).getObject();
     team1.addMatch(team2, r);
     }
     } else {
     final ArrayList<ObjectRanking> datas_tmp1 = new ArrayList<>(datas_1);
     final ArrayList<ObjectRanking> datas_tmp2 = new ArrayList<>(datas_2);
     final ArrayList<Team> teams1 = new ArrayList<>();
     final ArrayList<Team> teams2 = new ArrayList<>();
     while ((datas_tmp2.size() > 0) && (datas_tmp1.size() > 0)) {
     final Team team1 = (Team) datas_tmp1.get(0).getObject();
     Team team2 = null;
     datas_tmp1.remove(0);
     for (int i = 0; i < datas_tmp2.size(); i++) {
     boolean have_played = false;
     for (int j = 0; j < matchs.size(); j++) {
     if (((matchs.get(j).mCompetitor1.mTeamMates == team1) && (matchs.get(j).mCompetitor2.mTeamMates == datas_tmp2.get(i).getObject())) || ((matchs.get(j).mCompetitor2.mTeamMates == team1) && (matchs.get(j).mCompetitor1.mTeamMates == datas_tmp2.get(i).getObject()))) {
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
     final ArrayList<ObjectRanking> datas_tmp3 = new ArrayList<>(datas_3);
     final ArrayList<ObjectRanking> datas_tmp4 = new ArrayList<>(datas_4);
     while ((datas_tmp4.size() > 0) && (datas_tmp3.size() > 0)) {
     final Team team1 = (Team) datas_tmp3.get(0).getObject();
     Team team2 = null;
     datas_tmp3.remove(0);
     for (int i = 0; i < datas_tmp4.size(); i++) {
     boolean have_played = false;
     for (int j = 0; j < matchs.size(); j++) {
     if (((matchs.get(j).mCompetitor1.mTeamMates == team1) && (matchs.get(j).mCompetitor2.mTeamMates == datas_tmp4.get(i).getObject())) || ((matchs.get(j).mCompetitor2.mTeamMates == team1) && (matchs.get(j).mCompetitor1.mTeamMates == datas_tmp4.get(i).getObject()))) {
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
     final ArrayList<ObjectRanking> datas2 = new ArrayList<>(datas);
     for (int i = teams1.size() - 1; i > 0; i--) {
     boolean have_played = false;
     final Team team1 = teams1.get(i);
     final Team team2 = teams2.get(i);
     for (int j = 0; j < matchs.size(); j++) {
     if (((matchs.get(j).mCompetitor1.mTeamMates == team1) && (matchs.get(j).mCompetitor2.mTeamMates == team2)) || ((matchs.get(j).mCompetitor1.mTeamMates == team2) && (matchs.get(j).mCompetitor2.mTeamMates == team1))) {
     have_played = true;
     }
     }
     if (have_played) {
     for (int k = i - 1; k >= 0; k--) {
     for (int j = 0; j < matchs.size(); j++) {
     have_played = false;
     if (((matchs.get(j).mCompetitor1.mTeamMates == team2) && (matchs.get(j).mCompetitor2.mTeamMates == teams2.get(k))) || ((matchs.get(j).mCompetitor2.mTeamMates == team2) && (matchs.get(j).mCompetitor1.mTeamMates == teams2.get(k)))) {
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
     if (((matchs.get(j).mCompetitor1.mTeamMates == team2) && (matchs.get(j).mCompetitor2.mTeamMates == teams1.get(k))) || ((matchs.get(j).mCompetitor2.mTeamMates == team2) && (matchs.get(j).mCompetitor1.mTeamMates == teams1.get(k)))) {
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

     for (int i = 0; i < teams1.size(); i++) {
     final Team team1 = teams1.get(i);
     final Team team2 = teams2.get(i);
     team1.addMatch(team2, r);
     }
                
     }
     }
     return r;
     }*/

    /**
     *
     * @param round
     * @param r
     */
    
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

        /*for (int i = 0; i < r.getMatchs().size(); i++) {
         final Match m = r.getMatchs().get(i);
         m.mCompetitor1.mMatchs.add(m);
         m.mCompetitor2.mMatchs.add(m);
         }*/
        for (int i = 0; i < r.getCoachMatchs().size(); i++) {
            final Match m = r.getCoachMatchs().get(i);
            m.mCompetitor1.mMatchs.add(m);
            m.mCompetitor2.mMatchs.add(m);
        }

        applyPortugal(r);

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
        final StringBuffer filename = new StringBuffer(Tournament.getTournament().getParams().mTournamentName);
        filename.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("."));
        filename.append(Tournament.getTournament().getRounds().size());
        final Date date = new Date();
        filename.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("."));
        filename.append(Long.toString(date.getTime()));
        filename.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".XML"));
        final File file = new File(filename.toString());

        Tournament.getTournament().saveXML(file);
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
            for (int k = 0; k < c.mMatchs.size(); k++) {
                final CoachMatch m = (CoachMatch) c.mMatchs.get(k);
                if (rounds.contains(m.mRound)) {
                    value1 = MjtRankingIndiv.getValue(c, m, params.mRankingIndiv1, value1);
                    value2 = MjtRankingIndiv.getValue(c, m, params.mRankingIndiv2, value2);
                    value3 = MjtRankingIndiv.getValue(c, m, params.mRankingIndiv3, value3);
                    value4 = MjtRankingIndiv.getValue(c, m, params.mRankingIndiv4, value4);
                    value5 = MjtRankingIndiv.getValue(c, m, params.mRankingIndiv5, value5);
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
    protected static Round nextRoundQSwiss(final Round round, final int roundnumber) {

        final Tournament tour = Tournament.getTournament();

        ArrayList<ObjectRanking> datas;
        // First: Create ranking
        // previous rounds
        //final ArrayList<Round> v = new ArrayList<>();
        /*final ArrayList<Match> matchs = new ArrayList<>();
         buildUntilRound(round, v, matchs);*/

        Round r = new Round();
        if (tour.getPools().isEmpty()) {

            datas = getSortedRankingData(roundnumber);
            if (tour.getParams().mTeamTournament) {
                if (tour.getParams().mTeamPairing == 0) {
                    //Generation.IndivQSwiss(tour.getCoachs(), matchs, datas, true, r);
                    genQSwiss(tour.getCoachs(), datas, r);
                } else {
                    Generation.genQSwiss(tour.getTeams(), datas, r);
                }
            } else {
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    r = null;
                } else {
                    Generation.genQSwiss(tour.getCoachs(), datas, r);
                }
            }
        } else {
            for (int i = 0; i < tour.getPools().size(); i++) {
                datas = getSortedRankingData(tour.getPools().get(i), roundnumber);
                Generation.genQSwiss(tour.getPools().get(i).mCompetitors, datas, r);
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
    protected static Round nextRoundCup(final Round round, final int roundnumber) {

        final Tournament tour = Tournament.getTournament();
        Round r = new Round();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        if ((round != null) && (round.mCup)) {
            if (((round.mCupTour == round.mCupMaxTour - 1) && (!round.mLooserCup))
                    || (round.mCupTour == round.mCupMaxTour + 2)) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TOUR FINAL ATTEINT"), StringConstants.CS_CUP, JOptionPane.ERROR_MESSAGE);
                r = null;
            } else {

                boolean _third_place = false;
                if ((round.mCupTour == round.mCupMaxTour - 2) && (!round.mLooserCup)) {
                    _third_place = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VOULEZ VOUS GÉNÉRER LE MATCH POUR LA 3E PLACE ?"), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                }

                genCup(round, r, _third_place);

                r.mCup = true;
                r.mCupMaxTour = round.mCupMaxTour;
                r.mCupTour = round.mCupTour + 1;
                r.mLooserCup = round.mLooserCup;

            }
        } else {
            int cup_max_tour = 0;
            int nb_tmp = 1;
            r.mLooserCup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SAGIT-IL D'UN TOURNOI À DOUBLE ÉLIMINATION ?"), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            if (tour.getParams().mTeamTournament) {
                if (tour.getParams().mTeamPairing == 0) {
                    while (nb_tmp < tour.getActiveCoachNumber()) {
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

                ArrayList<ObjectRanking> datas;
                datas = getSortedRankingData(roundnumber);

                genCupFirst(r, datas, nb);

                r.mCup = true;
                r.mCupMaxTour = cup_max_tour;
                r.mCupTour = 0;

            }
        }

        if (r != null) {
            if (r.getMatchs().size() < tour.getActiveCoachNumber() / 2) {
                int res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AFFECTER UNE RONDE AUX JOUEURS RESTANTS ?"), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {

                    final ArrayList<ObjectRanking> datas = new ArrayList<>(getSortedRankingData(roundnumber));
                    ArrayList<Coach> coachs;
                    ArrayList<Team> teams;
                    final Round r2 = new Round();

                    if (tour.getParams().mTeamTournament) {
                        if (tour.getParams().mTeamPairing == 0) {

                            coachs = new ArrayList<>(tour.getActiveCoaches());
                            // First, remove coachs
                            for (int i = 0; i < r.getMatchs().size(); i++) {
                                final CoachMatch m = r.getCoachMatchs().get(i);
                                if (coachs.contains((Coach)m.mCompetitor1)) {
                                    coachs.remove((Coach)m.mCompetitor1);
                                }
                                if (coachs.contains((Coach)m.mCompetitor2)) {
                                    coachs.remove((Coach)m.mCompetitor2);
                                }
                            }
                            // Second remove bad datas
                            for (int i = 0; i < datas.size(); i++) {
                                final Coach c = (Coach) ((ObjectRanking) datas.get(i)).getObject();
                                for (int j = 0; j < r.getMatchs().size(); j++) {
                                    final CoachMatch m = r.getCoachMatchs().get(j);
                                    if ((c == m.mCompetitor1) || (c == m.mCompetitor2)) {
                                        datas.remove(i);
                                        i--;
                                    }
                                }
                            }

                        } else {
                            teams = new ArrayList<>(tour.getTeams());
                            // First, remove coachs
                            for (int i = 0; i < r.getCoachMatchs().size(); i++) {
                                final CoachMatch m = r.getCoachMatchs().get(i);
                                if (teams.contains(((Coach) m.mCompetitor1).getTeamMates())) {
                                    teams.remove(((Coach) m.mCompetitor1).getTeamMates());
                                }
                                if (teams.contains(((Coach) m.mCompetitor2).getTeamMates())) {
                                    teams.remove(((Coach) m.mCompetitor2).getTeamMates());
                                }
                            }
                            // Second remove bad datas
                            for (int i = 0; i < datas.size(); i++) {
                                final Team t = (Team) ((ObjectRanking) datas.get(i)).getObject();
                                for (int j = 0; j < r.getCoachMatchs().size(); j++) {
                                    final CoachMatch m = r.getCoachMatchs().get(j);
                                    if ((t == ((Coach) m.mCompetitor1).getTeamMates()) || (t == ((Coach) m.mCompetitor2).getTeamMates())) {
                                        datas.remove(i);
                                        i--;
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        if (tour.getActiveCoachNumber() % 2 > 0) {
                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                            r = null;
                        } else {
                            coachs = new ArrayList<>(tour.getActiveCoaches());
                            // First, remove coachs
                            for (int i = 0; i < r.getMatchs().size(); i++) {
                                final CoachMatch m = r.getCoachMatchs().get(i);
                                if (coachs.contains((Coach)m.mCompetitor1)) {
                                    coachs.remove((Coach)m.mCompetitor1);
                                }
                                if (coachs.contains((Coach)m.mCompetitor2)) {
                                    coachs.remove((Coach)m.mCompetitor2);
                                }
                            }
                            // Second remove bad datas
                            for (int i = 0; i < datas.size(); i++) {
                                final Coach c = (Coach) ((ObjectRanking) datas.get(i)).getObject();
                                for (int j = 0; j < r.getMatchs().size(); j++) {
                                    final CoachMatch m = r.getCoachMatchs().get(j);
                                    if ((c == m.mCompetitor1) || (c == m.mCompetitor2)) {
                                        datas.remove(i);
                                        i--;
                                    }
                                }
                            }
                        }
                    }

                    if (r != null) {
                        final ArrayList<Round> v = new ArrayList<>();
                        final ArrayList<CoachMatch> matchs = new ArrayList<>();
                        buildUntilRound(round, v, matchs);

                        res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UTILISER UNE RONDE SUISSE (ALÉATOIRE SINON)? "), StringConstants.CS_CUP, JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION) {

                            if (tour.getParams().mTeamTournament) {
                                if (tour.getParams().mTeamPairing == 0) {
                                    genSwiss(tour.getCoachs(), datas, r2);
                                } else {
                                    genSwiss(tour.getTeams(), datas, r2);
                                }
                            } else {
                                if (tour.getActiveCoachNumber() % 2 > 0) {
                                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                                    r = null;
                                } else {
                                    genSwiss(tour.getCoachs(), datas, r2);
                                }
                            }
                        } else {
                            if (tour.getParams().mTeamTournament) {
                                if (tour.getParams().mTeamPairing == 0) {
                                    Generation.genRandom(tour.getCoachs(), r2);
                                } else {
                                    Generation.genRandom(tour.getTeams(), r2);
                                }
                            } else {
                                if (tour.getActiveCoachNumber() % 2 > 0) {
                                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                                    r = null;
                                } else {
                                    Generation.genRandom(tour.getCoachs(), r);
                                }
                            }
                        }

                        if (r != null) {
                            // Merge the generated rounds
                            for (int i = 0; i < r2.getMatchs().size(); i++) {
                                r.getMatchs().add(r2.getMatchs().get(i));
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
    protected static void buildUntilRound(final Round round, final ArrayList<Round> v, final ArrayList<CoachMatch> matchs) {
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
    protected static Round nextRoundSwiss(final Round round, final int roundnumber) {

        final Tournament tour = Tournament.getTournament();

        ArrayList<ObjectRanking> datas;
        // First: Create ranking
        // previous rounds       
        final ArrayList<Round> v = new ArrayList<>();
        final ArrayList<CoachMatch> matchs = new ArrayList<>();
        buildUntilRound(round, v, matchs);

        Round r = new Round();
        if (tour.getPools().isEmpty()) {
            datas = getSortedRankingData(roundnumber);
            if (tour.getParams().mTeamTournament) {
                if (tour.getParams().mTeamPairing == 0) {
                    genSwiss(tour.getCoachs(), datas, r);
                } else {
                    genSwiss(tour.getTeams(), datas, r);
                }
            } else {
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                    r = null;
                } else {
                    genSwiss(tour.getCoachs(), datas, r);
                }
            }
        } else {
            for (int i = 0; i < tour.getPools().size(); i++) {
                datas = getSortedRankingData(tour.getPools().get(i), roundnumber);
                genSwiss(tour.getPools().get(i).mCompetitors, datas, r);
            }
        }
        return r;
    }

    private static ArrayList<ObjectRanking> getSortedRankingData(final int roundnumber) {
        final Tournament tour = Tournament.getTournament();
        ArrayList<ObjectRanking> datas;
        if ((tour.getParams().mTeamTournament) && (tour.getParams().mTeamPairing != 0)) {
            MjtRankingTeam ranking;

            ranking = new MjtRankingTeam(tour.getParams().mTeamVictoryOnly, roundnumber,
                    tour.getTeams(), false);
            // Ranking class
            datas = ranking.getSortedDatas();
        } else {
            final boolean forPool = (tour.getPools().size() > 0) && (!tour.getRounds().get(roundnumber).mCup);
            final MjtRankingIndiv ranking = new MjtRankingIndiv(roundnumber,
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
            MjtRankingTeam ranking;
            ranking = new MjtRankingTeam(tour.getParams().mTeamVictoryOnly, roundnumber,
                    p.mCompetitors,
                    false);
            // Ranking class
            datas = ranking.getSortedDatas();
        } else {
            final boolean forPool = (tour.getPools().size() > 0) && (!tour.getRounds().get(roundnumber).mCup);
            final MjtRankingIndiv ranking = new MjtRankingIndiv(roundnumber,
                    tour.getParams().mRankingIndiv1,
                    tour.getParams().mRankingIndiv2,
                    tour.getParams().mRankingIndiv3,
                    tour.getParams().mRankingIndiv4,
                    tour.getParams().mRankingIndiv5,
                    p.mCompetitors, false, false, forPool);

            // Ranking class
            datas = ranking.getSortedDatas();
        }
        return datas;
    }

    /**
     *
     * @return
     */
    protected static Round nextRoundFree() {
        final Round r = new Round();
        final Calendar cal = Calendar.getInstance();
        r.setHour(cal.getTime());
        return r;
    }

    /**
     *
     * @param round
     * @return
     */
    protected static Round nextRoundRandom(final Round round) {
        final Tournament tour = Tournament.getTournament();

        /*  final ArrayList<Round> v = new ArrayList<>();
         final ArrayList<Match> matchs = new ArrayList<>();
         buildUntilRound(round, v, matchs);*/
        Round r = new Round();
        r.getMatchs().clear();
        if (tour.getPools().isEmpty()) {
            if (tour.getParams().mTeamTournament) {
                if (tour.getParams().mTeamPairing == 0) {
                    Generation.genRandom(tour.getActiveCoaches(), r);
                } else {
                    Generation.genRandom(tour.getTeams(), r);
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
            for (int i = 0; i < tour.getPools().size(); i++) {
                Generation.genRandom(tour.getPools().get(i).mCompetitors, r);
            }
        }
        return r;
    }

    /**
     *
     * @return
     */
    protected static Round nextRoundBalanced() {
        final Tournament tour = Tournament.getTournament();

        /*  final ArrayList<Round> v = new ArrayList<>();
         final ArrayList<Match> matchs = new ArrayList<>();
         buildUntilRound(round, v, matchs);*/
        Round r = new Round();
        if (tour.getPools().isEmpty()) {
            if (tour.getParams().mTeamTournament) {
                if (tour.getParams().mTeamPairing == 0) {
                    Generation.genBalanced(tour.getActiveCoaches(), r);
                }
            } else {
                if (tour.getActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE COACHS ACTIF IMPAIR"), StringConstants.CS_GEN_ERROR, JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            for (int i = 0; i < tour.getPools().size(); i++) {
                Generation.genBalanced(tour.getPools().get(i).mCompetitors, r);
            }
        }
        return r;
    }
}
