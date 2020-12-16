/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * JPNTeamRound.java
 *
 * Created on 20 juil. 2010, 10:47:49
 */
package bb.tourma.views.round;

import java.util.ArrayList;
import java.util.HashMap;
import bb.tourma.MainFrame;
import bb.tourma.data.Coach;
import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.Parameters;
import bb.tourma.data.Round;
import bb.tourma.data.Team;
import bb.tourma.data.Tournament;
import bb.tourma.data.ranking.AnnexClanRanking;
import bb.tourma.data.ranking.ClanRanking;
import bb.tourma.languages.Translate;
import bb.tourma.tableModel.MjtAnnexRank;
import bb.tourma.tableModel.MjtAnnexRankClan;
import bb.tourma.tableModel.MjtRankingClan;
import bb.tourma.tableModel.MjtRankingIndiv;
import bb.tourma.utils.display.TableFormat;
import bb.tourma.views.report.JdgGlobal;
import bb.tourma.views.report.JdgRanking;

/**
 *
 * @author Frederic Berger
 */
public final class JPNClan extends javax.swing.JPanel {

    private final Round mRound;
    private final Tournament mTournament;
//    private final JTable mJtbTeamMatch = null;

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
     */
    public JPNClan(final Round r, final Tournament t) {
        initComponents();
        mRound = r;
        mTournament = t;

        mPageCount = t.getClansCount() / t.getParams().getPageSize();
        if (mPageCount * t.getParams().getPageSize() < t.getClansCount()) {
            mPageCount = mPageCount + 1;
        }
        mPageIndex = 1;

        ArrayList<Team> teams = new ArrayList<>();
        for (int cpt = 0; cpt < t.getTeamsCount(); cpt++) {
            teams.add(t.getTeam(cpt));
        }

        ArrayList<Coach> coachs = new ArrayList<>();
        for (int cpt = 0; cpt < t.getCoachsCount(); cpt++) {
            coachs.add(t.getCoach(cpt));
        }

        for (int i = 0; i < mTournament.getParams().getCriteriaCount(); i++) {
            final Criterion criteria = mTournament.getParams().getCriterion(i);
            final JPNAnnexRanking jpn = new JPNAnnexRanking(criteria.getName(), criteria, t, coachs, teams, mRound, true, false);
            jtpAnnexRank.add(criteria.getName(), jpn);
        }

        for (int i = 0; i < mTournament.getParams().getFormulaCount(); i++) {
            final Formula formula = mTournament.getParams().getFormula(i);
            final JPNAnnexRanking jpn = new JPNAnnexRanking(formula.getName(), formula, t, coachs, teams, mRound, true, false);
            jtpAnnexRank.add(formula.getName(), jpn);
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
        jtbRankingClan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jbtGeneralClan = new javax.swing.JButton();
        jbtGGlobalClan = new javax.swing.JButton();
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

        jtbRankingClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbRankingClan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jtbRankingClan);

        jPanel2.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jbtGeneralClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Document.png"))); // NOI18N
        jbtGeneralClan.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneralClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralClanActionPerformed(evt);
            }
        });
        jPanel3.add(jbtGeneralClan);

        jbtGGlobalClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Document.png"))); // NOI18N
        jbtGGlobalClan.setText(bundle.getString("GlobalRankingKey")); // NOI18N
        jbtGGlobalClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGGlobalClanActionPerformed(evt);
            }
        });
        jPanel3.add(jbtGGlobalClan);

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

    private final static String CS_GeneralByClan = "GENERAL PAR CLAN";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralClanActionPerformed
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {
                MjtRankingClan model;

                ClanRanking ranking = mRound.getRankings(mRoundOnly).getClanRankingSet().getRanking();
                

                model = new MjtRankingClan(ranking);

                final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true,
                        Translate.translate(CS_GeneralByClan),
                        i + 1, mTournament, model, 0);
                jdg.setVisible(true);
                break;
            }
        }

}//GEN-LAST:event_jbtGeneralClanActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGGlobalClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGGlobalClanActionPerformed

        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {
                ClanRanking ranking = mRound.getRankings(mRoundOnly).getClanRankingSet().getRanking();
                final MjtRankingClan model = new MjtRankingClan(ranking);
                final HashMap<Criterion, MjtAnnexRank> annexForRankings = new HashMap<>();
                final HashMap<Criterion, MjtAnnexRank> annexAgainstRankings = new HashMap<>();

                for (int j = 0; j < mTournament.getParams().getCriteriaCount(); j++) {
                    final Criterion crit = mTournament.getParams().getCriterion(j);
                    
                    AnnexClanRanking aranking=mRound.getRankings(mRoundOnly).getClanRankingSet().getAnnexPosRanking().get(crit);
                    MjtAnnexRank annex = new MjtAnnexRankClan(aranking);
                    annexForRankings.put(crit, annex);

                    aranking=mRound.getRankings(mRoundOnly).getClanRankingSet().getAnnexNegRanking().get(crit);
                    
                    annex = new MjtAnnexRankClan(aranking);
                    annexAgainstRankings.put(crit, annex);
                }

                final JdgGlobal jdg = new JdgGlobal(MainFrame.getMainFrame(), true, i + 1, mTournament, model, annexForRankings, annexAgainstRankings, false, false);
                jdg.setVisible(true);
                break;
            }
        }

    }//GEN-LAST:event_jbtGGlobalClanActionPerformed

    private void jbtBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBackActionPerformed
        mPageIndex = mPageIndex - 1;
        if (mPageIndex <= 1) {
            mPageIndex = 1;
        }
        updateMain();
    }//GEN-LAST:event_jbtBackActionPerformed

    private void jbtNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextActionPerformed
        mPageIndex = mPageIndex + 1;
        if (mPageIndex >= mPageCount) {
            mPageIndex = mPageCount;
        }
        updateMain();
    }//GEN-LAST:event_jbtNextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbtBack;
    private javax.swing.JButton jbtGGlobalClan;
    private javax.swing.JButton jbtGeneralClan;
    private javax.swing.JButton jbtNext;
    private javax.swing.JLabel jlbPage;
    private javax.swing.JTable jtbRankingClan;
    private javax.swing.JTabbedPane jtpAnnexRank;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @param r
     */
    public void setRoundOnly(boolean r) {
        mRoundOnly = r;
    }

    /**
     * Update panel
     */
    public void update() {

        updateMain();

        for (int i = 0; i < jtpAnnexRank.getComponentCount(); i++) {
            ((JPNAnnexRanking) jtpAnnexRank.getComponent(i)).update();
        }
        jtbRankingClan.setRowHeight(25);
    }

    private void updateMain() {
        final ArrayList<Round> v = new ArrayList<>();
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mTournament.getRound(i).getHour().before(mRound.getHour())) {
                v.add(mTournament.getRound(i));
            }
        }
        v.add(mRound);

        MjtRankingClan mRankingClan;

        jbtBack.setEnabled(mTournament.getParams().isDisplayByPages());
        jbtNext.setEnabled(mTournament.getParams().isDisplayByPages());
        jlbPage.setEnabled(mTournament.getParams().isDisplayByPages());

        jlbPage.setText(Integer.toString(mPageIndex) + " / " + Integer.toString(mPageCount));

        ClanRanking ranking = mRound.getRankings(mRoundOnly).getClanRankingSet().getRanking();

        if (this.mTournament.getParams().isDisplayByPages()) {
            int min = (mPageIndex - 1) * mTournament.getParams().getPageSize();
            int max = mPageIndex * mTournament.getParams().getPageSize();
            if (max > mTournament.getDisplayClans().size()) {
                max = mTournament.getDisplayClans().size();
            }
            mRankingClan = new MjtRankingClan(ranking, min, max);
        } else {
            mRankingClan = new MjtRankingClan(ranking);
        }

        jtbRankingClan.setModel(mRankingClan);
        jtbRankingClan.setDefaultRenderer(String.class, mRankingClan);
        jtbRankingClan.setDefaultRenderer(Integer.class, mRankingClan);
        //TableFormat.setColumnSize(jtbRankingClan);
    }

}