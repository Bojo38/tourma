/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * JPNTeamRound.java
 *
 * Created on 20 juil. 2010, 10:47:49
 */
package tourma.views.round;

import java.util.ArrayList;
import java.util.HashMap;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtAnnexRank;
import tourma.tableModel.MjtAnnexRankClan;
import tourma.tableModel.MjtRankingClan;
import tourma.utils.display.TableFormat;
import tourma.views.report.JdgGlobal;
import tourma.views.report.JdgRanking;

/**
 *
 * @author Frederic Berger
 */
public final class JPNClan extends javax.swing.JPanel {

    private final Round mRound;
    private final Tournament mTournament;
//    private final JTable mJtbTeamMatch = null;

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

        ArrayList<Team> teams = new ArrayList<>();
        for (int cpt = 0; cpt < t.getTeamsCount(); cpt++) {
            teams.add(t.getTeam(cpt));
        }

        ArrayList<Coach> coachs = new ArrayList<>();
        for (int cpt = 0; cpt < t.getCoachsCount(); cpt++) {
            coachs.add(t.getCoach(cpt));
        }
        for (int i = 0; i < mTournament.getParams().getCriteriaCount(); i++) {
            final Criteria criteria = mTournament.getParams().getCriteria(i);
            final JPNAnnexRanking jpn = new JPNAnnexRanking(criteria.getName(), criteria, t, coachs, teams, mRound, true, false);
            jtpAnnexRank.add(criteria.getName(), jpn);
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
        jPanel1 = new javax.swing.JPanel();
        jtpAnnexRank = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(600);

        jPanel2.setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
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

        jbtGeneralClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtGeneralClan.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneralClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralClanActionPerformed(evt);
            }
        });
        jPanel3.add(jbtGeneralClan);

        jbtGGlobalClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtGGlobalClan.setText(bundle.getString("GlobalRankingKey")); // NOI18N
        jbtGGlobalClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGGlobalClanActionPerformed(evt);
            }
        });
        jPanel3.add(jbtGGlobalClan);

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
                if ((mTournament.getParams().isTeamTournament()) && ((mTournament.getParams().isTeamVictoryOnly()))) {
                    model = new MjtRankingClan(i, mTournament.getParams().getRankingTeam1(), mTournament.getParams().gemRankingTeam2(), mTournament.getParams().getRankingTeam3(), mTournament.getParams().getRankingTeam4(), mTournament.getParams().getRankingTeam5(),
                            mTournament.getDisplayClans(), mRoundOnly);
                } else {
                    model = new MjtRankingClan(i, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), mTournament.getDisplayClans(), mRoundOnly);
                }
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
                final MjtRankingClan model = new MjtRankingClan(i, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), mTournament.getDisplayClans(), mRoundOnly);
                final HashMap<Criteria, MjtAnnexRank> annexForRankings = new HashMap<>();
                final HashMap<Criteria, MjtAnnexRank> annexAgainstRankings = new HashMap<>();
                for (int j = 0; j < mTournament.getParams().getCriteriaCount(); j++) {
                    final Criteria crit = mTournament.getParams().getCriteria(j);
                    MjtAnnexRank annex = new MjtAnnexRankClan(i, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                            mTournament.getDisplayClans(), true, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), mRoundOnly);
                    annexForRankings.put(crit, annex);
                    annex = new MjtAnnexRankClan(i, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                            mTournament.getDisplayClans(), true, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), mRoundOnly);
                    annexAgainstRankings.put(crit, annex);
                }
                final JdgGlobal jdg = new JdgGlobal(MainFrame.getMainFrame(), true, i + 1, mTournament, model, annexForRankings, annexAgainstRankings, false, false);
                jdg.setVisible(true);
                break;
            }
        }

    }//GEN-LAST:event_jbtGGlobalClanActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbtGGlobalClan;
    private javax.swing.JButton jbtGeneralClan;
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

        final ArrayList<Round> v = new ArrayList<>();
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mTournament.getRound(i).getHour().before(mRound.getHour())) {
                v.add(mTournament.getRound(i));
            }
        }
        v.add(mRound);

        MjtRankingClan mRankingClan;
        if ((mTournament.getParams().isTeamTournament()) && ((mTournament.getParams().isTeamVictoryOnly()))) {
            mRankingClan = new MjtRankingClan(v.size() - 1, mTournament.getParams().getRankingTeam1(), mTournament.getParams().gemRankingTeam2(), mTournament.getParams().getRankingTeam3(), mTournament.getParams().getRankingTeam4(), mTournament.getParams().getRankingTeam5(),
                    mTournament.getDisplayClans(), mRoundOnly);
        } else {
            mRankingClan = new MjtRankingClan(v.size() - 1, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(),
                    mTournament.getDisplayClans(), mRoundOnly);
        }
        jtbRankingClan.setModel(mRankingClan);
        jtbRankingClan.setDefaultRenderer(String.class, mRankingClan);
        jtbRankingClan.setDefaultRenderer(Integer.class, mRankingClan);
        TableFormat.setColumnSize(jtbRankingClan);

        for (int i = 0; i < jtpAnnexRank.getComponentCount(); i++) {
            ((JPNAnnexRanking) jtpAnnexRank.getComponent(i)).update();
        }
        jtbRankingClan.setRowHeight(25);

    }

}
