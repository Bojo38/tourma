/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * JPNAnnexRanking.java
 *
 * Created on 13 mai 2011, 21:52:44
 */
package tourma.views.round;

import java.util.ArrayList;
import java.util.logging.Logger;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.Formula;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtAnnexRank;
import tourma.tableModel.MjtAnnexRankClan;
import tourma.tableModel.MjtAnnexRankIndiv;
import tourma.tableModel.MjtAnnexRankTeam;
import tourma.utils.display.TableFormat;
import tourma.views.report.JdgRanking;

/**
 *
 * @author Administrateur
 */
public final class JPNAnnexRanking extends javax.swing.JPanel {

    private String mName = "";
    private Criteria mCriteria = null;
    private Formula mFormula = null;
    private Tournament mTour = null;
    private Round mRound = null;
    private boolean mClan;
    private boolean mTeam;

    /**
     *
     */
    private boolean mRoundOnly = false;
    private ArrayList<Coach> mCoachs = null;
    private ArrayList<Team> mTeams = null;

    /**
     * Creates new form JPNAnnexRanking
     *
     * @param name
     * @param criteria
     * @param tour
     * @param coachs
     * @param teams
     * @param team
     * @param round
     * @param clan
     */
    public JPNAnnexRanking(final String name, final Criteria criteria, final Tournament tour, final ArrayList<Coach> coachs, final ArrayList<Team> teams, final Round round, final boolean clan, final boolean team) {
        this(name, criteria, tour, round, clan, team, coachs, teams);
    }

    public JPNAnnexRanking(final String name, final Formula formula, final Tournament tour, final ArrayList<Coach> coachs, final ArrayList<Team> teams, final Round round, final boolean clan, final boolean team) {
        this(name, formula, tour, round, clan, team, coachs, teams);
    }

    /**
     *
     * @param r
     */
    public void setRoundOnly(boolean r) {
        mRoundOnly = r;
    }

    private final static String CS_Clan = "Clan";
    private final static String CS_Opponents = "ADVERSAIRES";
    private final static String CS_Team = "Team";
    private final static String CS_Coach = "Coach";

    /**
     *
     * @param name
     * @param criteria
     * @param tour
     * @param round
     * @param clan
     * @param team
     * @param v
     * @param t
     */
    public JPNAnnexRanking(final String name, final Criteria criteria, final Tournament tour, final Round round, final boolean clan, final boolean team, final ArrayList v, final ArrayList t) {
        initComponents();
        mName = name;
        mCriteria = criteria;
        mTour = tour;
        mRound = round;
        mClan = clan;
        mTeam = team;

        mCoachs = v;
        mTeams = t;

        int roundnumber = 0;
        while (!round.equals(tour.getRound(roundnumber))) {
            roundnumber++;
        }

        if (clan) {
            jbtPositive.setText(name + "(" + Translate.translate(CS_Clan) + ")");
            jbtNegative.setText(name + "(" + Translate.translate(CS_Opponents) + ")");
        } else {
            if (team) {
                jbtPositive.setText(name + "(" + Translate.translate(CS_Team) + ")");
                jbtNegative.setText(name + "(" + Translate.translate(CS_Opponents) + ")");
            } else {
                jbtPositive.setText(name + "(" + Translate.translate(CS_Coach) + ")");
                jbtNegative.setText(name + "(" + Translate.translate(CS_Opponents) + ")");
            }
        }

        update();
    }

    public JPNAnnexRanking(final String name, final Formula formula, final Tournament tour, final Round round, final boolean clan, final boolean team, final ArrayList v, final ArrayList t) {
        initComponents();
        mName = name;
        mCriteria = null;
        mFormula = formula;

        mTour = tour;
        mRound = round;
        mClan = clan;
        mTeam = team;

        mCoachs = v;
        mTeams = t;

        int roundnumber = 0;
        while (!round.equals(tour.getRound(roundnumber))) {
            roundnumber++;
        }

        if (clan) {
            jbtPositive.setText(name + "(" + Translate.translate(CS_Clan) + ")");
            jbtNegative.setText(name + "(" + Translate.translate(CS_Opponents) + ")");
        } else {
            if (team) {
                jbtPositive.setText(name + "(" + Translate.translate(CS_Team) + ")");
                jbtNegative.setText(name + "(" + Translate.translate(CS_Opponents) + ")");
            } else {
                jbtPositive.setText(name + "(" + Translate.translate(CS_Coach) + ")");
                jbtNegative.setText(name + "(" + Translate.translate(CS_Opponents) + ")");
            }
        }

        update();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbPositive = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jbtPositive = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbNegative = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jbtNegative = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Annex Ranking"));
        setLayout(new java.awt.GridLayout(2, 1, 1, 1));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jtbPositive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbPositive);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jbtPositive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtPositive.setText(bundle.getString("Positive")); // NOI18N
        jbtPositive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPositiveActionPerformed(evt);
            }
        });
        jPanel3.add(jbtPositive);

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jtbNegative.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jtbNegative);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jbtNegative.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtNegative.setText(bundle.getString("Negative")); // NOI18N
        jbtNegative.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNegativeActionPerformed(evt);
            }
        });
        jPanel4.add(jbtNegative);

        jPanel2.add(jPanel4, java.awt.BorderLayout.SOUTH);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private final static String CS_ByTheClan = "ByTheClan";
    private final static String CS_ByTheCoach = "ByTheCoach";
    private final static String CS_ByTheTeam = "ByTheTeam";
    private final static String CS_AgainstTheClan = "AgainstTheClan";
    private final static String CS_AgainstTheCoach = "AgainstTheCoach";
    private final static String CS_AgainstTheTeam = "AgainstTheTeam";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtPositiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPositiveActionPerformed

        for (int i = 0; i < mTour.getRoundsCount(); i++) {
            if (mRound == mTour.getRound(i)) {
                MjtAnnexRank model;
                if (mClan) {
                    if (mCriteria != null) {
                        model = new MjtAnnexRankClan(i, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                    } else {
                        model = new MjtAnnexRankClan(i, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);

                    }
                } else {
                    if (mTeam) {
                        if (mCriteria != null) {
                            model = new MjtAnnexRankTeam(i, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                    mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                        } else {
                            model = new MjtAnnexRankTeam(i, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                    mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);

                        }
                    } else {
                        if (mCriteria != null) {
                            model = new MjtAnnexRankIndiv(i, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, mCoachs, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                        } else {
                            model = new MjtAnnexRankIndiv(i, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE, mCoachs, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                        }
                    }
                }
                final StringBuffer a = new StringBuffer(mCriteria.getName());
                if (mClan) {
                    a.append(" " + Translate.translate(CS_ByTheClan));
                } else {
                    if (mTeam) {
                        a.append(" " + Translate.translate(CS_ByTheTeam));
                    } else {
                        a.append(" " + Translate.translate(CS_ByTheCoach));
                    }
                }
                final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true, a.toString(), i + 1, mTour, model, 0);
                jdg.setVisible(true);
                break;
            }
        }

    }//GEN-LAST:event_jbtPositiveActionPerformed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtNegativeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNegativeActionPerformed
        for (int i = 0; i < mTour.getRoundsCount(); i++) {
            if (mRound == mTour.getRound(i)) {
                MjtAnnexRank model;
                if (mClan) {
                    if (mCriteria != null) {
                        model = new MjtAnnexRankClan(i, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                    } else {
                        model = new MjtAnnexRankClan(i, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);

                    }
                } else {
                    if (mTeam) {
                        if (mCriteria != null) {
                            model = new MjtAnnexRankTeam(i, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                    mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                        } else {
                            model = new MjtAnnexRankTeam(i, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                    mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                        }
                    } else {
                        if (mCriteria != null) {
                            model = new MjtAnnexRankIndiv(i, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                    mCoachs, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                        } else {
                            model = new MjtAnnexRankIndiv(i, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                    mCoachs, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                        }
                    }
                }

                final StringBuffer a = new StringBuffer(mCriteria.getName());
                if (mClan) {
                    a.append(" " + Translate.translate(CS_AgainstTheClan));
                } else {
                    if (mTeam) {
                        a.append(" " + Translate.translate(CS_AgainstTheTeam));
                    } else {
                        a.append(" " + Translate.translate(CS_AgainstTheCoach));
                    }
                }
                final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true, a.toString(), i + 1, mTour, model, 0);
                jdg.setVisible(true);
                break;
            }
        }

    }//GEN-LAST:event_jbtNegativeActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtNegative;
    private javax.swing.JButton jbtPositive;
    private javax.swing.JTable jtbNegative;
    private javax.swing.JTable jtbPositive;
    // End of variables declaration//GEN-END:variables

    /**
     * Update panel
     */
    public void update() {

        if (mRound != null) {
            final ArrayList<Round> v = new ArrayList<>();
            for (int i = 0; i < mTour.getRoundsCount(); i++) {
                if (mTour.getRound(i).getHour().before(mRound.getHour())) {
                    v.add(mTour.getRound(i));
                }
            }
            v.add(mRound);

            MjtAnnexRank modelPos;
            MjtAnnexRank modelNeg;

            if (mClan) {
                if (mCriteria != null) {
                    modelPos
                            = new MjtAnnexRankClan(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                    mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                    modelNeg
                            = new MjtAnnexRankClan(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                    mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                } else {
                    modelPos
                            = new MjtAnnexRankClan(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                    mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                    modelNeg
                            = new MjtAnnexRankClan(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                    mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);

                }
            } else {
                if (mCriteria != null) {
                    if (mTeam) {
                        modelPos
                                = new MjtAnnexRankTeam(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                        mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                        modelNeg
                                = new MjtAnnexRankTeam(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                        mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                    } else {
                        modelPos
                                = new MjtAnnexRankIndiv(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, this.mCoachs, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                        modelNeg
                                = new MjtAnnexRankIndiv(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, this.mCoachs, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                    }
                } else {
                    if (mTeam) {
                        modelPos
                                = new MjtAnnexRankTeam(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                        mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                        modelNeg
                                = new MjtAnnexRankTeam(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                        mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                    } else {
                        modelPos
                                = new MjtAnnexRankIndiv(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE, this.mCoachs, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                        modelNeg
                                = new MjtAnnexRankIndiv(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE, this.mCoachs, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                    }
                }
            }
            jtbNegative.setModel(modelNeg);
            jtbPositive.setModel(modelPos);

            jtbNegative.setDefaultRenderer(String.class, modelNeg);
            jtbNegative.setDefaultRenderer(Integer.class, modelNeg);

            jtbPositive.setDefaultRenderer(String.class, modelPos);
            jtbPositive.setDefaultRenderer(Integer.class, modelPos);

            TableFormat.setColumnSize(jtbPositive);
            TableFormat.setColumnSize(jtbNegative);

            jtbPositive.setRowHeight(25);
            jtbNegative.setRowHeight(25);

            jtbPositive.setAutoCreateRowSorter(true);
            jtbNegative.setAutoCreateRowSorter(true);
        }

    }
    private static final Logger LOG = Logger.getLogger(JPNAnnexRanking.class.getName());

    /*     private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
     throw new java.io.NotSerializableException(getClass().getName());
     }

     private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
     throw new java.io.NotSerializableException(getClass().getName());
     }*/
}
