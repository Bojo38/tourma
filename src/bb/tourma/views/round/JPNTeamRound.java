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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import bb.tourma.MainFrame;
import bb.tourma.data.Coach;
import bb.tourma.data.Criterion;
import bb.tourma.data.ETeamPairing;
import bb.tourma.data.Formula;
import bb.tourma.data.Match;
import bb.tourma.data.Parameters;
import bb.tourma.data.Round;
import bb.tourma.data.Team;
import bb.tourma.data.Tournament;
import bb.tourma.data.ranking.AnnexTeamRanking;
import bb.tourma.data.ranking.TeamRanking;
import bb.tourma.languages.Translate;
import bb.tourma.tableModel.MjtAnnexRank;
import bb.tourma.tableModel.MjtAnnexRankTeam;
import bb.tourma.tableModel.MjtMatchTeams;
import bb.tourma.tableModel.MjtRankingIndiv;
import bb.tourma.tableModel.MjtRankingTeam;
import bb.tourma.utils.display.TableFormat;
import bb.tourma.views.report.JdgGlobal;
import bb.tourma.views.report.JdgRanking;
import bb.tourma.views.report.JdgRound;

/**
 *
 * @author Frederic Berger
 */
public final class JPNTeamRound extends javax.swing.JPanel {

    private final Round mRound;
    private final Tournament mTournament;
    private JTable mJtbTeamMatch = null;

    private int mPageCount = 0;
    private int mPageIndex = 0;

    private int mPageMatchCount = 0;
    private int mPageMatchIndex = 0;

    JButton jbtBackTeam = new JButton(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Backward.png")));
    JButton jbtNextTeam = new JButton(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Forward.png")));
    JLabel jlbPageTeam = new JLabel();
    /**
     *
     */
    private boolean mRoundOnly = false;

    private static final String CS_Matchs = "MATCHS";

    /**
     * Creates new form JPNTeamRound
     *
     * @param r
     * @param t
     */
    public JPNTeamRound(final Round r, final Tournament t) {
        initComponents();
        mRound = r;
        mTournament = t;

        mPageCount = t.getTeamsCount() / t.getParams().getPageSize();
        if (mPageCount * t.getParams().getPageSize() < t.getTeamsCount()) {
            mPageCount = mPageCount + 1;
        }
        mPageIndex = 1;

        mPageMatchCount = r.getMatchsCount() / t.getParams().getPageSize();
        if (mPageMatchCount * t.getParams().getPageSize() < r.getMatchsCount()) {
            mPageMatchCount = mPageMatchCount + 1;
        }
        mPageMatchIndex = 1;

        if (mTournament.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
            final JScrollPane jsp = new JScrollPane();
            mJtbTeamMatch = new JTable();
            jsp.setViewportView(mJtbTeamMatch);

            JPanel jpnMatchs = new JPanel(new BorderLayout());
            jpnMatchs.add(jsp, BorderLayout.CENTER);

            JPanel jpnButtons = new JPanel(new FlowLayout());
            JButton jbtMatchs = new JButton(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Document.png")));
            jbtMatchs.addActionListener((ActionEvent e) -> {
                for (int i = 0; i < mTournament.getRoundsCount(); i++) {
                    if (mRound == mTournament.getRound(i)) {
                        final JdgRound jdg = new JdgRound(MainFrame.getMainFrame(), true, mRound, i + 1, mTournament, true, true);
                        jdg.setVisible(true);
                        break;
                    }
                }
            });

            jbtBackTeam.addActionListener((ActionEvent e) -> {
                mPageMatchIndex = mPageMatchIndex - 1;
                if (mPageMatchIndex <= 1) {
                    mPageMatchIndex = 1;
                }
                updateMatchs();
            });

            jbtNextTeam.addActionListener((ActionEvent e) -> {
                mPageMatchIndex = mPageMatchIndex + 1;
                if (mPageMatchIndex >= mPageMatchCount) {
                    mPageMatchIndex = mPageMatchCount;
                }
                updateMatchs();
            });

            jpnButtons.add(jbtMatchs);
            jpnButtons.add(jbtBackTeam);
            jpnButtons.add(jlbPageTeam);
            jpnButtons.add(jbtNextTeam);

            jpnMatchs.add(jpnButtons, BorderLayout.SOUTH);

            jpnMatchs.add(jsp, BorderLayout.CENTER);
            jtpTeams.add(Translate.translate(CS_Matchs), jpnMatchs);
            jpnMatchs.setVisible(true);
        } else {
            //jpnMatchs.setVisible(false);
        }

        for (int i = 0; i < mTournament.getParams().getCriteriaCount(); i++) {
            final Criterion criteria = mTournament.getParams().getCriterion(i);
            ArrayList<Team> teams = new ArrayList<>();
            for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                teams.add(Tournament.getTournament().getTeam(cpt));
            }
            ArrayList<Coach> coachs = new ArrayList<>();
            for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount(); cpt++) {
                coachs.add(Tournament.getTournament().getCoach(cpt));
            }
            final JPNAnnexRanking jpn = new JPNAnnexRanking(criteria.getName(),
                    criteria, Tournament.getTournament(), coachs, teams, mRound, false, true);
            jtpAnnexRank.add(criteria.getName(), jpn);
        }

        for (int i = 0; i < mTournament.getParams().getFormulaCount(); i++) {
            final Formula formula = mTournament.getParams().getFormula(i);
            ArrayList<Team> teams = new ArrayList<>();
            for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                teams.add(Tournament.getTournament().getTeam(cpt));
            }
            ArrayList<Coach> coachs = new ArrayList<>();
            for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount(); cpt++) {
                coachs.add(Tournament.getTournament().getCoach(cpt));
            }
            final JPNAnnexRanking jpn = new JPNAnnexRanking(formula.getName(),
                    formula, Tournament.getTournament(), coachs, teams, mRound, false, true);
            jtpAnnexRank.add(formula.getName(), jpn);
        }

        update();

    }

    /**
     *
     * @param r
     */
    public void setRoundOnly(boolean r) {
        mRoundOnly = r;
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
        jpnTeam = new javax.swing.JPanel();
        jtpTeams = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtbRankingTeam = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jbtGeneralTeam = new javax.swing.JButton();
        jbtGlobal = new javax.swing.JButton();
        jbtBack = new javax.swing.JButton();
        jlbPage = new javax.swing.JLabel();
        jbtNext = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jtpAnnexRank = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(600);

        jpnTeam.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bb/tourma/languages/language"); // NOI18N
        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GeneralRankingKey"))); // NOI18N
        jScrollPane3.setPreferredSize(new java.awt.Dimension(466, 300));

        jtbRankingTeam.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbRankingTeam.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jtbRankingTeam);

        jPanel8.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jbtGeneralTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Document.png"))); // NOI18N
        jbtGeneralTeam.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneralTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralTeamActionPerformed(evt);
            }
        });
        jPanel2.add(jbtGeneralTeam);

        jbtGlobal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Document.png"))); // NOI18N
        jbtGlobal.setText(bundle.getString("GlobalRankingKey")); // NOI18N
        jbtGlobal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGlobalActionPerformed(evt);
            }
        });
        jPanel2.add(jbtGlobal);

        jbtBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Backward.png"))); // NOI18N
        jbtBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBackActionPerformed(evt);
            }
        });
        jPanel2.add(jbtBack);

        jlbPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPage.setText("jLabel1");
        jPanel2.add(jlbPage);

        jbtNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Forward.png"))); // NOI18N
        jbtNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextActionPerformed(evt);
            }
        });
        jPanel2.add(jbtNext);

        jPanel8.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jtpTeams.addTab(bundle.getString("Ranking"), jPanel8); // NOI18N

        jpnTeam.add(jtpTeams, java.awt.BorderLayout.CENTER);
        jtpTeams.getAccessibleContext().setAccessibleName(bundle.getString("Ranking")); // NOI18N

        jSplitPane1.setLeftComponent(jpnTeam);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jtpAnnexRank, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private static final String CS_generalByTeam = "GENERAL PAR EQUIPE";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralTeamActionPerformed

        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {

                TeamRanking ranking = mRound.getRankings(mRoundOnly).getTeamRankingSet().getRanking();

                final MjtRankingTeam model = new MjtRankingTeam(ranking);
                final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true,
                        Translate.translate(CS_generalByTeam),
                        i + 1, mTournament, model, 0);
                jdg.setVisible(true);
                break;
            }
        }

}//GEN-LAST:event_jbtGeneralTeamActionPerformed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGlobalActionPerformed

        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {
                
                TeamRanking ranking = mRound.getRankings(mRoundOnly).getTeamRankingSet().getRanking();
                final MjtRankingTeam model = new MjtRankingTeam(ranking);
                final HashMap<Criterion, MjtAnnexRank> annexForRankings = new HashMap<>();
                final HashMap<Criterion, MjtAnnexRank> annexAgainstRankings = new HashMap<>();

                for (int j = 0; j < mTournament.getParams().getCriteriaCount(); j++) {
                    final Criterion crit = mTournament.getParams().getCriterion(j);

                    AnnexTeamRanking aranking = mRound.getRankings(mRoundOnly).getTeamRankingSet().getAnnexPosRanking().get(crit);
                    MjtAnnexRank annex = new MjtAnnexRankTeam(aranking, true);
                    annexForRankings.put(crit, annex);

                    aranking = mRound.getRankings(mRoundOnly).getTeamRankingSet().getAnnexNegRanking().get(crit);
                    annex = new MjtAnnexRankTeam(aranking, true);
                    annexAgainstRankings.put(crit, annex);
                }
                final JdgGlobal jdg = new JdgGlobal(MainFrame.getMainFrame(), true, i + 1, mTournament, model, annexForRankings, annexAgainstRankings, false, false);
                jdg.setVisible(true);
                break;
            }
        }

    }//GEN-LAST:event_jbtGlobalActionPerformed

    private void jbtBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBackActionPerformed
        mPageIndex = mPageIndex - 1;
        if (mPageIndex <= 1) {
            mPageIndex = 1;
        }
        updateRank();
    }//GEN-LAST:event_jbtBackActionPerformed

    private void jbtNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextActionPerformed
        mPageIndex = mPageIndex + 1;
        if (mPageIndex >= mPageCount) {
            mPageIndex = mPageCount;
        }
        updateRank();
    }//GEN-LAST:event_jbtNextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbtBack;
    private javax.swing.JButton jbtGeneralTeam;
    private javax.swing.JButton jbtGlobal;
    private javax.swing.JButton jbtNext;
    private javax.swing.JLabel jlbPage;
    private javax.swing.JPanel jpnTeam;
    private javax.swing.JTable jtbRankingTeam;
    private javax.swing.JTabbedPane jtpAnnexRank;
    private javax.swing.JTabbedPane jtpTeams;
    // End of variables declaration//GEN-END:variables

    /**
     * Upadtae Panel
     */
    public void update() {

        for (int i = 0; i < jtpAnnexRank.getComponentCount(); i++) {
            final JPNAnnexRanking jpn = (JPNAnnexRanking) jtpAnnexRank.getComponent(i);
            jpn.setRoundOnly(mRoundOnly);
            jpn.update();
        }

        updateRank();
        updateMatchs();

    }

    private void updateRank() {

        jbtBack.setEnabled(mTournament.getParams().isDisplayByPages());
        jbtNext.setEnabled(mTournament.getParams().isDisplayByPages());
        jlbPage.setEnabled(mTournament.getParams().isDisplayByPages());

        jlbPage.setText(Integer.toString(mPageIndex) + " / " + Integer.toString(mPageCount));

        final ArrayList<Round> v = new ArrayList<>();

        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mTournament.getRound(i).getHour().before(mRound.getHour())) {
                v.add(mTournament.getRound(i));
            }
        }
        v.add(mRound);
        MjtRankingTeam mRankingTeam;

        TeamRanking ranking = mRound.getRankings(mRoundOnly).getTeamRankingSet().getRanking();
        
        if (this.mTournament.getParams().isDisplayByPages()) {
            int min = (mPageIndex - 1) * mTournament.getParams().getPageSize();
            int max = mPageIndex * mTournament.getParams().getPageSize();
            if (max > mTournament.getTeamsCount()) {
                max = mTournament.getTeamsCount();
            }
            mRankingTeam = new MjtRankingTeam(ranking, min, max);
        } else {
            mRankingTeam = new MjtRankingTeam(ranking);
        }

        jtbRankingTeam.setModel(mRankingTeam);
        jtbRankingTeam.setDefaultRenderer(String.class, mRankingTeam);
        jtbRankingTeam.setDefaultRenderer(Integer.class, mRankingTeam);

        //TableFormat.setColumnSize(jtbRankingTeam);
        jtbRankingTeam.setRowHeight(30);
    }

    private void updateMatchs() {

        jbtBackTeam.setEnabled(mTournament.getParams().isDisplayByPages());
        jbtBackTeam.setEnabled(mTournament.getParams().isDisplayByPages());
        jlbPageTeam.setEnabled(mTournament.getParams().isDisplayByPages());

        jlbPageTeam.setText(Integer.toString(mPageMatchIndex) + " / " + Integer.toString(mPageMatchCount));

        if (mJtbTeamMatch != null) {

            final ArrayList<Round> v = new ArrayList<>();

            for (int i = 0; i < mTournament.getRoundsCount(); i++) {
                if (mTournament.getRound(i).getHour().before(mRound.getHour())) {
                    v.add(mTournament.getRound(i));
                }
            }
            v.add(mRound);

            ArrayList<Team> teams = new ArrayList<>();
            for (int i = 0; i < mTournament.getTeamsCount(); i++) {
                teams.add(mTournament.getTeam(i));
            }
            for (int i = 0; i < mRound.getMatchsCount(); i++) {
                final Match m = mRound.getMatch(i);
                final Team team1 = (Team) m.getCompetitor1();
                final Team team2 = (Team) m.getCompetitor2();
                if (!teams.contains(team1)) {
                    teams.add(team1);
                }
                if (!teams.contains(team2)) {
                    teams.add(team2);
                }
            }

            MjtMatchTeams model;

            if (this.mTournament.getParams().isDisplayByPages()) {
                int min = (mPageMatchIndex - 1) * mTournament.getParams().getPageSize();
                int max = mPageMatchIndex * mTournament.getParams().getPageSize();
                if (max > mTournament.getTeamsCount() / 2) {
                    max = mTournament.getTeamsCount() / 2;
                }
                model = new MjtMatchTeams(teams, mRound, min, max);
            } else {
                model = new MjtMatchTeams(teams, mRound);
            }

            mJtbTeamMatch.setModel(model);
            mJtbTeamMatch.setDefaultRenderer(String.class, model);
            mJtbTeamMatch.setDefaultRenderer(Integer.class, model);
            //TableFormat.setColumnSize(mJtbTeamMatch);
            mJtbTeamMatch.setRowHeight(30);

        }
    }

}