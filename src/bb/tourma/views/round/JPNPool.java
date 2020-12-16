/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * JPNPool.java
 *
 * Created on 20 juil. 2010, 10:47:49
 */
package bb.tourma.views.round;

import java.util.ArrayList;
import java.util.HashMap;
import bb.tourma.MainFrame;
import bb.tourma.data.Coach;
import bb.tourma.data.Competitor;
import bb.tourma.data.Criterion;
import bb.tourma.data.ETeamPairing;
import bb.tourma.data.IContainCoachs;
import bb.tourma.data.Parameters;
import bb.tourma.data.Pool;
import bb.tourma.data.Round;
import bb.tourma.data.Team;
import bb.tourma.data.Tournament;
import bb.tourma.data.ranking.AnnexIndivRanking;
import bb.tourma.data.ranking.AnnexRanking;
import bb.tourma.data.ranking.AnnexTeamRanking;
import bb.tourma.data.ranking.IndivRanking;
import bb.tourma.data.ranking.TeamRanking;
import bb.tourma.languages.Translate;
import bb.tourma.tableModel.MjtAnnexRank;
import bb.tourma.tableModel.MjtAnnexRankIndiv;
import bb.tourma.tableModel.MjtAnnexRankTeam;
import bb.tourma.tableModel.MjtRanking;
import bb.tourma.tableModel.MjtRankingIndiv;
import bb.tourma.tableModel.MjtRankingTeam;
import bb.tourma.utils.display.TableFormat;
import bb.tourma.views.report.JdgGlobal;
import bb.tourma.views.report.JdgRanking;

/**
 *
 * @author Frederic Berger
 */
public final class JPNPool extends javax.swing.JPanel {

    private static final long serialVersionUID = 20L;

    private final Round mRound;
    private final Tournament mTournament;
    private final Pool mPool;

    private int mPageCount = 0;
    private int mPageIndex = 0;

    /**
     *
     */
    private boolean mRoundOnly = false;

    /**
     * Creates new form JPNTeamRound
     *
     * @param r
     * @param t
     * @param p
     */
    public JPNPool(final Round r, final Tournament t, final Pool p) {
        mPool = p;
        initComponents();
        mRound = r;
        mTournament = t;

        mPageCount = p.getCompetitorCount() / t.getParams().getPageSize();
        if (mPageCount * t.getParams().getPageSize() < p.getCompetitorCount()) {
            mPageCount = mPageCount + 1;
        }
        mPageIndex = 1;

        for (int i = 0; i < mTournament.getParams().getCriteriaCount(); i++) {
            final Criterion criteria = mTournament.getParams().getCriterion(i);
            if (!mTournament.getParams().isTeamTournament()) {
                final JPNAnnexRanking jpn = new JPNAnnexRanking(criteria.getName(), criteria, mTournament, mRound, false, false, p.getCompetitors(), new ArrayList<Team>());
                jtpAnnexRank.add(criteria.getName(), jpn);
            } else {

                final ArrayList<Coach> v = new ArrayList<>();
                for (int j = 0; j < p.getCompetitorCount(); j++) {
                    for (int k = 0; k < ((IContainCoachs) p.getCompetitor(j)).getCoachsCount(); k++) {
                        v.add(((IContainCoachs) p.getCompetitor(j)).getCoach(k));
                    }
                }
                JPNAnnexRanking jpn = new JPNAnnexRanking(criteria.getName(), criteria, mTournament, mRound, false, true, v, p.getCompetitors());
                jtpAnnexRank.add(criteria.getName(), jpn);

                jpn = new JPNAnnexRanking(criteria.getName(), criteria, mTournament, mRound, false, false, v, p.getCompetitors());
                jtpAnnexRank.add(criteria.getName(), jpn);
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

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtbRankingPool = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jbtGeneralPool = new javax.swing.JButton();
        jbtGlobalPool = new javax.swing.JButton();
        jbtBack = new javax.swing.JButton();
        jlbPage = new javax.swing.JLabel();
        jbtNext = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jtpAnnexRank = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(600);

        jPanel2.setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bb/tourma/languages/language"); // NOI18N
        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GeneralRankingKey"))); // NOI18N
        jScrollPane3.setPreferredSize(new java.awt.Dimension(466, 300));

        jtbRankingPool.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbRankingPool.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jtbRankingPool);

        jPanel2.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jbtGeneralPool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Document.png"))); // NOI18N
        jbtGeneralPool.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneralPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralPoolActionPerformed(evt);
            }
        });
        jPanel3.add(jbtGeneralPool);

        jbtGlobalPool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Document.png"))); // NOI18N
        jbtGlobalPool.setText(bundle.getString("GlobalRankingKey")); // NOI18N
        jbtGlobalPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGlobalPoolActionPerformed(evt);
            }
        });
        jPanel3.add(jbtGlobalPool);

        jbtBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Backward.png"))); // NOI18N
        jbtBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBackActionPerformed(evt);
            }
        });
        jPanel3.add(jbtBack);

        jlbPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPage.setText("jLabel1");
        jPanel3.add(jlbPage);

        jbtNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Forward.png"))); // NOI18N
        jbtNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextActionPerformed(evt);
            }
        });
        jPanel3.add(jbtNext);

        jPanel2.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setLeftComponent(jPanel2);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jtpAnnexRank, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
 @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralPoolActionPerformed

     final ArrayList<Round> v = new ArrayList<>();
     for (int i = 0; i < mTournament.getRoundsCount(); i++) {
         if (mTournament.getRound(i).getHour().before(mRound.getHour())) {
             v.add(mTournament.getRound(i));
         }
     }
     v.add(mRound);

     for (int i = 0; i < mTournament.getRoundsCount(); i++) {
         if (mRound == mTournament.getRound(i)) {

             MjtRanking model;
             if ((mTournament.getParams().isTeamTournament())
                     && (mTournament.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {

                 TeamRanking ranking = mRound.getRankings(mRoundOnly).getPoolTeamRankings().get(mPool).getRanking();
                 model = new MjtRankingTeam(ranking);
             } else {
                 IndivRanking ranking = mRound.getRankings(mRoundOnly).getPoolIndivRankings().get(mPool).getRanking();
                 model = new MjtRankingIndiv(ranking);
             }
             final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true,
                     Translate.translate(CS_GeneralByPool),
                     i + 1, mTournament, model, 0);
             jdg.setVisible(true);
         }
     }

}//GEN-LAST:event_jbtGeneralPoolActionPerformed
    private static final String CS_GeneralByPool = "GENERAL PAR POOL";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGlobalPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGlobalPoolActionPerformed
        final ArrayList<Round> v = new ArrayList<>();
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mTournament.getRound(i).getHour().before(mRound.getHour())) {
                v.add(mTournament.getRound(i));
            }
        }
        v.add(mRound);

        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {

                MjtRanking model;
                if ((mTournament.getParams().isTeamTournament())
                        && (mTournament.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {

                    TeamRanking ranking = mRound.getRankings(mRoundOnly).getPoolTeamRankings().get(mPool).getRanking();
                    model = new MjtRankingTeam(ranking);
                } else {
                    IndivRanking ranking = mRound.getRankings(mRoundOnly).getPoolIndivRankings().get(mPool).getRanking();
                    model = new MjtRankingIndiv(ranking);
                }

                ArrayList<Team> teams = new ArrayList<>();
                ArrayList<Coach> coachs = new ArrayList<>();

                for (Competitor c : mPool.getCompetitors()) {
                    if (c instanceof Team) {
                        teams.add((Team) c);
                    }
                    if (c instanceof Coach) {
                        coachs.add((Coach) c);
                    }
                }

                final HashMap<Criterion, MjtAnnexRank> annexForRankings = new HashMap<>();
                final HashMap<Criterion, MjtAnnexRank> annexAgainstRankings = new HashMap<>();
                for (int j = 0; j < mTournament.getParams().getCriteriaCount(); j++) {
                    final Criterion crit = mTournament.getParams().getCriterion(j);
                    MjtAnnexRank annexPos;
                    MjtAnnexRank annexNeg;
                    if ((mTournament.getParams().isTeamTournament())
                            && (mTournament.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
                        AnnexTeamRanking rankingPos = mRound.getRankings(mRoundOnly).getPoolTeamRankings().get(mPool).getAnnexPosRanking().get(crit);
                        AnnexTeamRanking rankingNeg = mRound.getRankings(mRoundOnly).getPoolTeamRankings().get(mPool).getAnnexNegRanking().get(crit);
                        annexPos= new MjtAnnexRankTeam(rankingPos, true);
                        annexNeg= new MjtAnnexRankTeam(rankingNeg, true);
                    } else {
                        AnnexIndivRanking rankingPos = mRound.getRankings(mRoundOnly).getPoolIndivRankings().get(mPool).getAnnexPosRanking().get(crit);
                        AnnexIndivRanking rankingNeg = mRound.getRankings(mRoundOnly).getPoolIndivRankings().get(mPool).getAnnexNegRanking().get(crit);
                        
                        annexPos= new MjtAnnexRankIndiv(rankingPos, true);
                        annexNeg= new MjtAnnexRankIndiv(rankingNeg, true);
                    }
                    annexForRankings.put(crit, annexPos);
                    annexAgainstRankings.put(crit, annexNeg);
                }

                final JdgGlobal jdg = new JdgGlobal(MainFrame.getMainFrame(), true, i + 1, mTournament, model, annexForRankings, annexAgainstRankings, false, false);
                jdg.setVisible(true);
                break;
            }
        }

    }//GEN-LAST:event_jbtGlobalPoolActionPerformed

    private void jbtBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBackActionPerformed
        mPageIndex = mPageIndex - 1;
        if (mPageIndex <= 1) {
            mPageIndex = 1;
        }
        updatePool();
    }//GEN-LAST:event_jbtBackActionPerformed

    private void jbtNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextActionPerformed
        mPageIndex = mPageIndex + 1;
        if (mPageIndex >= mPageCount) {
            mPageIndex = mPageCount;
        }
        updatePool();
    }//GEN-LAST:event_jbtNextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbtBack;
    private javax.swing.JButton jbtGeneralPool;
    private javax.swing.JButton jbtGlobalPool;
    private javax.swing.JButton jbtNext;
    private javax.swing.JLabel jlbPage;
    private javax.swing.JTable jtbRankingPool;
    private javax.swing.JTabbedPane jtpAnnexRank;
    // End of variables declaration//GEN-END:variables

    /**
     * Update Panel
     */
    public void update() {

        updatePool();

        for (int i = 0; i < jtpAnnexRank.getComponentCount(); i++) {
            ((JPNAnnexRanking) jtpAnnexRank.getComponent(i)).update();
        }

        //jtbRankingPool.setRowHeight(25);
    }

    private void updatePool() {
        int r_index = 0;
        final ArrayList<Round> v = new ArrayList<>();
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mTournament.getRound(i).getHour().before(mRound.getHour())) {
                v.add(mTournament.getRound(i));
            }
            if (mTournament.getRound(i) == mRound) {
                r_index = i;
            }
        }
        v.add(mRound);

        jlbPage.setText(Integer.toString(mPageIndex) + " / " + Integer.toString(mPageCount));

        if ((mTournament.getParams().isTeamTournament())
                && (mTournament.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
            MjtRankingTeam mRankingTeam;

            TeamRanking ranking = mRound.getRankings(mRoundOnly).getPoolTeamRankings().get(mPool).getRanking();
            if (this.mTournament.getParams().isDisplayByPages()) {
                int min = (mPageIndex - 1) * mTournament.getParams().getPageSize();
                int max = mPageIndex * mTournament.getParams().getPageSize();
                if (max > this.mPool.getCompetitorCount()) {
                    max = this.mPool.getCompetitorCount();
                }
                mRankingTeam = new MjtRankingTeam(ranking, min, max);
            } else {
                mRankingTeam = new MjtRankingTeam(ranking);
            }

            jtbRankingPool.setModel(mRankingTeam);
            jtbRankingPool.setDefaultRenderer(String.class, mRankingTeam);
            jtbRankingPool.setDefaultRenderer(Integer.class, mRankingTeam);

            // TableFormat.setColumnSize(jtbRankingPool);
        } else {
            MjtRankingIndiv mRankingIndiv;

            IndivRanking ranking = mRound.getRankings(mRoundOnly).getPoolIndivRankings().get(mPool).getRanking();

            if (this.mTournament.getParams().isDisplayByPages()) {
                int min = (mPageIndex - 1) * mTournament.getParams().getPageSize();
                int max = mPageIndex * mTournament.getParams().getPageSize();
                if (max > this.mPool.getCompetitorCount()) {
                    max = this.mPool.getCompetitorCount();
                }
                mRankingIndiv = new MjtRankingIndiv(ranking, min, max);
            } else {
                mRankingIndiv = new MjtRankingIndiv(ranking);
            }

            jtbRankingPool.setModel(mRankingIndiv);
            jtbRankingPool.setDefaultRenderer(String.class, mRankingIndiv);
            jtbRankingPool.setDefaultRenderer(Integer.class, mRankingIndiv);
        }
    }

    /**
     *
     * @param r
     */
    public void setRoundOnly(boolean r) {
        mRoundOnly = r;
    }

}