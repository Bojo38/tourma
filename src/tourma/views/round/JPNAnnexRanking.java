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
import tourma.tableModel.MjtRankingIndiv;
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

    private int mPagePosCount = 0;
    private int mPagePosIndex = 0;

    private int mPageNegCount = 0;
    private int mPageNegIndex = 0;

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

        int nb = 0;

        if (clan) {
            nb = tour.getClansCount();
        } else {
            if (team) {
                nb = tour.getTeamsCount();
            } else {
                nb = tour.getCoachsCount();
            }
        }

        mPagePosCount = nb / tour.getParams().getPageSize();
        if (mPagePosCount * tour.getParams().getPageSize() < nb) {
            mPagePosCount = mPagePosCount + 1;
        }
        mPagePosIndex = 1;

        mPageNegCount = nb / tour.getParams().getPageSize();
        if (mPageNegCount * tour.getParams().getPageSize() < nb) {
            mPageNegCount = mPageNegCount + 1;
        }
        mPageNegIndex = 1;

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

        int nb = 0;

        if (clan) {
            nb = tour.getClansCount();
        } else {
            if (team) {
                nb = tour.getTeamsCount();
            } else {
                nb = tour.getCoachsCount();
            }
        }

        mPagePosCount = nb / tour.getParams().getPageSize();
        if (mPagePosCount * tour.getParams().getPageSize() < nb) {
            mPagePosCount = mPagePosCount + 1;
        }
        mPagePosIndex = 1;

        mPageNegCount = nb / tour.getParams().getPageSize();
        if (mPageNegCount * tour.getParams().getPageSize() < nb) {
            mPageNegCount = mPageNegCount + 1;
        }
        mPageNegIndex = 1;

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
        jbtBackPos = new javax.swing.JButton();
        jlbPagePos = new javax.swing.JLabel();
        jbtNextPos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbNegative = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jbtNegative = new javax.swing.JButton();
        jbtBackNeg = new javax.swing.JButton();
        jlbPageNeg = new javax.swing.JLabel();
        jbtNextNeg = new javax.swing.JButton();

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

        jbtBackPos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Backward.png"))); // NOI18N
        jbtBackPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBackPosActionPerformed(evt);
            }
        });
        jPanel3.add(jbtBackPos);

        jlbPagePos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPagePos.setText("jLabel1");
        jPanel3.add(jlbPagePos);

        jbtNextPos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Forward.png"))); // NOI18N
        jbtNextPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextPosActionPerformed(evt);
            }
        });
        jPanel3.add(jbtNextPos);

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

        jbtBackNeg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Backward.png"))); // NOI18N
        jbtBackNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBackNegActionPerformed(evt);
            }
        });
        jPanel4.add(jbtBackNeg);

        jlbPageNeg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPageNeg.setText("jLabel1");
        jPanel4.add(jlbPageNeg);

        jbtNextNeg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Forward.png"))); // NOI18N
        jbtNextNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextNegActionPerformed(evt);
            }
        });
        jPanel4.add(jbtNextNeg);

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

    private void jbtBackPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBackPosActionPerformed
        mPagePosIndex = mPagePosIndex - 1;
        if (mPagePosIndex <= 1) {
            mPagePosIndex = 1;
        }
        updatePositive();
    }//GEN-LAST:event_jbtBackPosActionPerformed

    private void jbtNextPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextPosActionPerformed
        mPagePosIndex = mPagePosIndex + 1;
        if (mPagePosIndex >= mPagePosCount) {
            mPagePosIndex = mPagePosCount;
        }
        updatePositive();
    }//GEN-LAST:event_jbtNextPosActionPerformed

    private void jbtBackNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBackNegActionPerformed
        mPageNegIndex = mPageNegIndex - 1;
        if (mPageNegIndex <= 1) {
            mPageNegIndex = 1;
        }
        updateNegative();
    }//GEN-LAST:event_jbtBackNegActionPerformed

    private void jbtNextNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextNegActionPerformed
        mPageNegIndex = mPageNegIndex + 1;
        if (mPageNegIndex >= mPageNegCount) {
            mPageNegIndex = mPageNegCount;
        }
        updateNegative();
    }//GEN-LAST:event_jbtNextNegActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtBackNeg;
    private javax.swing.JButton jbtBackPos;
    private javax.swing.JButton jbtNegative;
    private javax.swing.JButton jbtNextNeg;
    private javax.swing.JButton jbtNextPos;
    private javax.swing.JButton jbtPositive;
    private javax.swing.JLabel jlbPageNeg;
    private javax.swing.JLabel jlbPagePos;
    private javax.swing.JTable jtbNegative;
    private javax.swing.JTable jtbPositive;
    // End of variables declaration//GEN-END:variables

    /**
     * Update panel
     */
    public void update() {

        if (mRound != null) {

            updatePositive();
            updateNegative();

            
        }

    }
    private static final Logger LOG = Logger.getLogger(JPNAnnexRanking.class.getName());

    private void updatePositive() {
        if (mRound != null) {

            final ArrayList<Round> v = new ArrayList<>();
            for (int i = 0; i < mTour.getRoundsCount(); i++) {
                if (mTour.getRound(i).getHour().before(mRound.getHour())) {
                    v.add(mTour.getRound(i));
                }
            }
            v.add(mRound);

            jlbPagePos.setText(Integer.toString(mPagePosIndex) + " / " + Integer.toString(mPagePosCount));
            MjtAnnexRank modelPos;
            if (mClan) {
                if (mCriteria != null) {
                    if (this.mTour.getParams().isDisplayByPages()) {
                        int min = (mPagePosIndex - 1) * mTour.getParams().getPageSize();
                        int max = mPagePosIndex * mTour.getParams().getPageSize();
                        if (max > mTour.getDisplayClans().size()) {
                            max = mTour.getDisplayClans().size();
                        }
                        modelPos = new MjtAnnexRankClan(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), 
                                mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly, min, max);
                    } else {
                        modelPos = new MjtAnnexRankClan(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), 
                                mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                    }

                } else {
                    if (this.mTour.getParams().isDisplayByPages()) {
                        int min = (mPagePosIndex - 1) * mTour.getParams().getPageSize();
                        int max = mPagePosIndex * mTour.getParams().getPageSize();
                        if (max > mTour.getDisplayClans().size()) {
                            max = mTour.getDisplayClans().size();
                        }
                        modelPos
                                = new MjtAnnexRankClan(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                        mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), 
                                        mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly, min, max);
                    } else {
                        modelPos
                                = new MjtAnnexRankClan(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                        mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(),
                                        mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                    }
                }
            } else {
                if (mCriteria != null) {
                    if (mTeam) {
                        if (this.mTour.getParams().isDisplayByPages()) {
                            int min = (mPagePosIndex - 1) * mTour.getParams().getPageSize();
                            int max = mPagePosIndex * mTour.getParams().getPageSize();
                            if (max > mTeams.size()) {
                                max = mTeams.size();
                            }
                            modelPos
                                    = new MjtAnnexRankTeam(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                            mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly, min, max);
                        } else {
                            modelPos
                                    = new MjtAnnexRankTeam(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                            mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(),
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                        }
                    } else {
                        if (this.mTour.getParams().isDisplayByPages()) {
                            int min = (mPagePosIndex - 1) * mTour.getParams().getPageSize();
                            int max = mPagePosIndex * mTour.getParams().getPageSize();
                            if (max > mCoachs.size()) {
                                max = mCoachs.size();
                            }
                            modelPos
                                    = new MjtAnnexRankIndiv(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, this.mCoachs, true, 
                                            mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly, min, max);
                        } else {
                            modelPos
                                    = new MjtAnnexRankIndiv(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, this.mCoachs, true, 
                                            mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(),
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                        }

                    }
                } else {
                    if (mTeam) {
                        if (this.mTour.getParams().isDisplayByPages()) {
                            int min = (mPagePosIndex - 1) * mTour.getParams().getPageSize();
                            int max = mPagePosIndex * mTour.getParams().getPageSize();
                            if (max > mTeams.size()) {
                                max = mTeams.size();
                            }
                            modelPos
                                    = new MjtAnnexRankTeam(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                            mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly, min, max);
                        } else {
                            modelPos
                                    = new MjtAnnexRankTeam(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                            mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                        }

                    } else {
                        if (this.mTour.getParams().isDisplayByPages()) {
                            int min = (mPagePosIndex - 1) * mTour.getParams().getPageSize();
                            int max = mPagePosIndex * mTour.getParams().getPageSize();
                            if (max > mCoachs.size()) {
                                max = mCoachs.size();
                            }
                            modelPos
                                    = new MjtAnnexRankIndiv(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE, this.mCoachs, true, 
                                            mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly, min, max);
                        } else {
                            modelPos
                                    = new MjtAnnexRankIndiv(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_POSITIVE, this.mCoachs, true, 
                                            mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                        }

                    }
                }
            }
            jtbPositive.setModel(modelPos);

            jtbPositive.setDefaultRenderer(String.class, modelPos);
            jtbPositive.setDefaultRenderer(Integer.class, modelPos);

            //TableFormat.setColumnSize(jtbPositive);

            jtbPositive.setRowHeight(25);

            jtbPositive.setAutoCreateRowSorter(true);
        }
    }
    
    private void updateNegative() {
        if (mRound != null) {

            final ArrayList<Round> v = new ArrayList<>();
            for (int i = 0; i < mTour.getRoundsCount(); i++) {
                if (mTour.getRound(i).getHour().before(mRound.getHour())) {
                    v.add(mTour.getRound(i));
                }
            }
            v.add(mRound);

            jlbPageNeg.setText(Integer.toString(mPageNegIndex) + " / " + Integer.toString(mPageNegCount));
             
            MjtAnnexRank modelNeg;
            if (mClan) {
                if (mCriteria != null) {
                    if (this.mTour.getParams().isDisplayByPages()) {
                        int min = (mPageNegIndex - 1) * mTour.getParams().getPageSize();
                        int max = mPageNegIndex * mTour.getParams().getPageSize();
                        if (max > mTour.getDisplayClans().size()) {
                            max = mTour.getDisplayClans().size();
                        }
                        modelNeg = new MjtAnnexRankClan(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), 
                                mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly, min, max);
                    } else {
                        modelNeg = new MjtAnnexRankClan(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), 
                                mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                    }

                } else {
                    if (this.mTour.getParams().isDisplayByPages()) {
                        int min = (mPageNegIndex - 1) * mTour.getParams().getPageSize();
                        int max = mPageNegIndex * mTour.getParams().getPageSize();
                        if (max > mTour.getDisplayClans().size()) {
                            max = mTour.getDisplayClans().size();
                        }
                        modelNeg
                                = new MjtAnnexRankClan(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                        mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), 
                                        mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly, min, max);
                    } else {
                        modelNeg
                                = new MjtAnnexRankClan(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                        mTour.getDisplayClans(), true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(),
                                        mTour.getParams().getRankingIndiv3(), mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                    }
                }
            } else {
                if (mCriteria != null) {
                    if (mTeam) {
                        if (this.mTour.getParams().isDisplayByPages()) {
                            int min = (mPageNegIndex - 1) * mTour.getParams().getPageSize();
                            int max = mPageNegIndex * mTour.getParams().getPageSize();
                            if (max > mTeams.size()) {
                                max = mTeams.size();
                            }
                            modelNeg
                                    = new MjtAnnexRankTeam(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                            mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly, min, max);
                        } else {
                            modelNeg
                                    = new MjtAnnexRankTeam(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                            mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(),
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                        }
                    } else {
                        if (this.mTour.getParams().isDisplayByPages()) {
                            int min = (mPageNegIndex - 1) * mTour.getParams().getPageSize();
                            int max = mPageNegIndex * mTour.getParams().getPageSize();
                            if (max > mCoachs.size()) {
                                max = mCoachs.size();
                            }
                            modelNeg
                                    = new MjtAnnexRankIndiv(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, this.mCoachs, true, 
                                            mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly, min, max);
                        } else {
                            modelNeg
                                    = new MjtAnnexRankIndiv(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, this.mCoachs, true, 
                                            mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(),
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                        }

                    }
                } else {
                    if (mTeam) {
                        if (this.mTour.getParams().isDisplayByPages()) {
                            int min = (mPageNegIndex - 1) * mTour.getParams().getPageSize();
                            int max = mPageNegIndex * mTour.getParams().getPageSize();
                            if (max > mTeams.size()) {
                                max = mTeams.size();
                            }
                            modelNeg
                                    = new MjtAnnexRankTeam(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                            mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly, min, max);
                        } else {
                            modelNeg
                                    = new MjtAnnexRankTeam(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                            mTeams, true, mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mRoundOnly);
                        }

                    } else {
                        if (this.mTour.getParams().isDisplayByPages()) {
                            int min = (mPageNegIndex - 1) * mTour.getParams().getPageSize();
                            int max = mPageNegIndex * mTour.getParams().getPageSize();
                            if (max > mCoachs.size()) {
                                max = mCoachs.size();
                            }
                            modelNeg
                                    = new MjtAnnexRankIndiv(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE, this.mCoachs, true, 
                                            mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly, min, max);
                        } else {
                            modelNeg
                                    = new MjtAnnexRankIndiv(v.size() - 1, mFormula, Parameters.C_RANKING_SUBTYPE_NEGATIVE, this.mCoachs, true, 
                                            mTour.getParams().getRankingIndiv1(), mTour.getParams().getRankingIndiv2(), mTour.getParams().getRankingIndiv3(), 
                                            mTour.getParams().getRankingIndiv4(), mTour.getParams().getRankingIndiv5(), mTour.getParams().isTeamTournament(), mRoundOnly);
                        }

                    }
                }
            }
            jtbNegative.setModel(modelNeg);

            jtbNegative.setDefaultRenderer(String.class, modelNeg);
            jtbNegative.setDefaultRenderer(Integer.class, modelNeg);

            //TableFormat.setColumnSize(jtbNegative);

            jtbNegative.setRowHeight(25);

            jtbNegative.setAutoCreateRowSorter(true);
        }
    }
}
