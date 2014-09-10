/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPNRound.java
 *
 * Created on 11 mai 2010, 14:13:53
 */
package tourma.views.round;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tourma.views.JPNCup;
import tourma.MainFrame;
import tourma.data.Category;
import tourma.data.Coach;
import tourma.data.Team;
import tourma.data.Criteria;
import tourma.data.Group;
import tourma.data.CoachMatch;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.jdgChangePairing;
import tourma.jdgPairing;
import tourma.tableModel.mjtAnnexRank;
import tourma.tableModel.mjtAnnexRankIndiv;
import tourma.tableModel.mjtMatches;
import tourma.tableModel.mjtRankingIndiv;
import tourma.utility.StringConstants;
import tourma.utils.Generation;
import tourma.utils.TableFormat;
import tourma.views.report.jdgGlobal;
import tourma.views.report.jdgRanking;
import tourma.views.report.jdgRound;

/**
 *
 * @author Frederic Berger
 */
public class JPNRound extends javax.swing.JPanel {

    Round mRound;
    int mRoundNumber;
    Tournament mTournament;
    JPNTeamRound mJpnTeamRound = null;
    JPNClanRound mJpnClanRound = null;
    boolean mRoundOnly = false;

    /**
     * Creates new form JPNRound
     */
    public JPNRound(final int roundNumber, final Round r, final Tournament t) {
        initComponents();
        mRound = r;
        mTournament = t;
        mRoundNumber = roundNumber;

        if (mTournament.getParams().mTeamTournament) {
            mJpnTeamRound = new JPNTeamRound(r, t);
            jtpGlobal.addTab(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ByTeam"), new javax.swing.ImageIcon(getClass().getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/TEAM.PNG"))), mJpnTeamRound);
        }
        if (mTournament.getParams().mEnableClans) {
            mJpnClanRound = new JPNClanRound(r, t);
            jtpGlobal.addTab(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ByClan"), new javax.swing.ImageIcon(getClass().getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/CLAN.PNG"))), mJpnClanRound);
        }

        if (mTournament.getGroups().size() > 1) {
            for (int i = 0; i < mTournament.getGroups().size(); i++) {
                final Group g = mTournament.getGroups().get(i);
                if (!g.mName.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AUCUN"))) {
                    final JPNGroup jpnGroup = new JPNGroup(t, g, mRoundNumber);
                    jtpGlobal.addTab(
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Group")+" "+ g.mName,
                            new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Group.png")),
                            jpnGroup);
                }
            }
        }

        if (mTournament.getCategories().size() > 1) {
            for (int i = 0; i < mTournament.getCategories().size(); i++) {
                final Category c = mTournament.getCategories().get(i);
                if (!c.mName.equals(StringConstants.CS_NONE)) {
                    final JPNCategory jpnCategory = new JPNCategory(t, c, mRoundNumber);
                    jtpGlobal.addTab(
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Category")+" "+ c.mName,
                            new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Group2.png")),
                            jpnCategory);
                }
            }
        }

        if (mTournament.getPools().size() >= 1) {
            for (int i = 0; i < mTournament.getPools().size(); i++) {
                final Pool p = mTournament.getPools().get(i);
                final JPNPoolRound jpnPool = new JPNPoolRound(r, t, p);
                jtpGlobal.addTab(
                        java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Pool")+" "+ p.mName,
                        new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Pool.png")), jpnPool);
            }
        }

        for (int i = 0; i < mTournament.getParams().mCriterias.size(); i++) {
            final Criteria criteria = mTournament.getParams().mCriterias.get(i);
            final JPNAnnexRanking jpn = new JPNAnnexRanking(criteria.mName, criteria, mTournament, mRound, false, false);
            jtpAnnexRankings.add(criteria.mName, jpn);
        }

        update();
    }

    public Round getRound() {
        return mRound;
    }

    public void update() {

        if (mRound != null) {
            final Date d = mRound.getHour();
            boolean locked = false;
            for (int i = 0; i < mTournament.getRounds().size(); i++) {
                if (mTournament.getRounds().get(i).getHour().after(d)) {
                    locked = true;
                }
            }
            if (mTournament.mRoundRobin) {
                locked = false;
            }

            if (mJpnTeamRound != null) {
                mJpnTeamRound.update();
            }
            if (mJpnClanRound != null) {
                mJpnClanRound.update();
            }

//            jbtDeleteRound.setEnabled(!locked);

            final mjtMatches model = new mjtMatches(mRound.getCoachMatchs(), locked, mTournament.getParams().mTeamTournament, true);
            jtbMatches.setModel(model);
            jtbMatches.setDefaultRenderer(String.class, model);
            jtbMatches.setDefaultRenderer(Integer.class, model);
            jtbMatches.setRowHeight(30);

            jtbRankingIndiv.setRowHeight(30);

            //jbtNextRound.setEnabled((!mTournament.mRoundRobin) || (mTournament.mRoundRobin && (mRoundNumber == mTournament.getRounds().size() - 1)));

            /*        jtbMatches.setDefaultEditor(Integer.class, model);*/
            TableFormat.setColumnSize(jtbMatches);
            /*final ArrayList<Round> v = new ArrayList<>();
             for (int i = 0; i < mTournament.getRounds().size(); i++) {
             if (mTournament.getRounds().get(i).getHour().before(mRound.getHour())) {
             v.add(mTournament.getRounds().get(i));
             }
             }
             v.add(mRound);*/

            if (mRoundNumber < mTournament.getRounds().size()) {
                final boolean forPool = (mTournament.getPools().size() > 0) && (!mRound.mCup);
                final mjtRankingIndiv mRanking = new mjtRankingIndiv(mRoundNumber, mTournament.getParams().mRankingIndiv1, mTournament.getParams().mRankingIndiv2, mTournament.getParams().mRankingIndiv3, mTournament.getParams().mRankingIndiv4, mTournament.getParams().mRankingIndiv5, mTournament.getCoachs(), mTournament.getParams().mTeamTournament, mRoundOnly, forPool);
                jtbRankingIndiv.setModel(mRanking);
                jtbRankingIndiv.setDefaultRenderer(String.class, mRanking);
                jtbRankingIndiv.setDefaultRenderer(Integer.class, mRanking);

                for (int i = 0; i < jtpAnnexRankings.getComponentCount(); i++) {
                    ((JPNAnnexRanking) jtpAnnexRankings.getComponent(i)).mRoundOnly = mRoundOnly;
                    ((JPNAnnexRanking) jtpAnnexRankings.getComponent(i)).update();
                }
                TableFormat.setColumnSize(jtbRankingIndiv);
            }
        }

        for (int i = 0; i < jtpGlobal.getTabCount(); i++) {
            Object panel;
            panel = jtpGlobal.getComponent(i);
            if (panel instanceof JPNGroup) {
                ((JPNGroup) panel).mRoundOnly = mRoundOnly;
                ((JPNGroup) panel).update();
            } else {
                if (panel instanceof JPNTeamRound) {
                    ((JPNTeamRound) panel).mRoundOnly = mRoundOnly;
                    ((JPNTeamRound) panel).update();
                } else {
                    if (panel instanceof JPNClanRound) {
                        ((JPNClanRound) panel).mRoundOnly = mRoundOnly;
                        ((JPNClanRound) panel).update();
                    } else {
                        if (panel instanceof JPNPoolRound) {
                            ((JPNPoolRound) panel).mRoundOnly = mRoundOnly;
                            ((JPNPoolRound) panel).update();
                        } else {
                            if (panel instanceof JPNCategory) {
                                ((JPNCategory) panel).mRoundOnly = mRoundOnly;
                                ((JPNCategory) panel).update();
                            } else {
                                if (panel instanceof JPNCup) {
                                    ((JPNCup) panel).update();
                                }
                            }
                        }
                    }
                }
            }
        }

        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtpGlobal = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbMatches = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jbtShowMatches = new javax.swing.JButton();
        jbtShowResults = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbRankingIndiv = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jbtGeneralIndiv = new javax.swing.JButton();
        jbtGlobal = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jtpAnnexRankings = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());

        jtpGlobal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jtpGlobalStateChanged(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jtbMatches.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbMatches.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtbMatches.setColumnSelectionAllowed(true);
        jtbMatches.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbMatches.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbMatchesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbMatches);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jbtShowMatches.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtShowMatches.setText(bundle.getString("ShowMatchsKey")); // NOI18N
        jbtShowMatches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShowMatchesActionPerformed(evt);
            }
        });
        jPanel3.add(jbtShowMatches);

        jbtShowResults.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtShowResults.setText(bundle.getString("ShowResultsKey")); // NOI18N
        jbtShowResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShowResultsActionPerformed(evt);
            }
        });
        jPanel3.add(jbtShowResults);

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jtpGlobal.addTab(bundle.getString("MatchsKey"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/User2.png")), jPanel1); // NOI18N

        jSplitPane1.setDividerLocation(640);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GeneralRankingKey"))); // NOI18N
        jScrollPane2.setPreferredSize(new java.awt.Dimension(466, 300));

        jtbRankingIndiv.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jtbRankingIndiv);

        jPanel6.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jbtGeneralIndiv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtGeneralIndiv.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneralIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralIndivActionPerformed(evt);
            }
        });
        jPanel7.add(jbtGeneralIndiv);

        jbtGlobal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtGlobal.setText(bundle.getString("GlobalRankingKey")); // NOI18N
        jbtGlobal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGlobalActionPerformed(evt);
            }
        });
        jPanel7.add(jbtGlobal);

        jPanel6.add(jPanel7, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setLeftComponent(jPanel6);

        jPanel8.setLayout(new java.awt.BorderLayout());
        jPanel8.add(jtpAnnexRankings, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel8);

        jtpGlobal.addTab(bundle.getString("IndividualRankingKey"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/User.png")), jSplitPane1); // NOI18N

        add(jtpGlobal, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtShowMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowMatchesActionPerformed
        for (int i = 0; i
                < mTournament.getRounds().size(); i++) {
            if (mRound == mTournament.getRounds().get(i)) {

                final jdgRound jdg = new jdgRound(MainFrame.getMainFrame(), true, mRound, i + 1, mTournament, false, false);
                jdg.setVisible(true);
                break;

            }
        }
    }//GEN-LAST:event_jbtShowMatchesActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtShowResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowResultsActionPerformed
        for (int i = 0; i
                < mTournament.getRounds().size(); i++) {
            if (mRound == mTournament.getRounds().get(i)) {
                final jdgRound jdg = new jdgRound(MainFrame.getMainFrame(), true, mRound, i + 1, mTournament, true, false);
                jdg.setVisible(true);
                break;

            }
        }
    }//GEN-LAST:event_jbtShowResultsActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralIndivActionPerformed
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            if (mRound == mTournament.getRounds().get(i)) {
                final boolean forPool = (mTournament.getPools().size() > 0) && (!mRound.mCup);
                final mjtRankingIndiv model = new mjtRankingIndiv(mRoundNumber, mTournament.getParams().mRankingIndiv1, mTournament.getParams().mRankingIndiv2, mTournament.getParams().mRankingIndiv3, mTournament.getParams().mRankingIndiv4, mTournament.getParams().mRankingIndiv5, mTournament.getCoachs(), mTournament.getParams().mTeamTournament, mRoundOnly, forPool);
                final jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GENERAL PAR COACH"), i + 1, mTournament, model, 0);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtGeneralIndivActionPerformed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtpGlobalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtpGlobalStateChanged
        update();
    }//GEN-LAST:event_jtpGlobalStateChanged
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGlobalActionPerformed
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            if (mRound == mTournament.getRounds().get(i)) {
                final boolean forPool = (mTournament.getPools().size() > 0) && (!mRound.mCup);
                final mjtRankingIndiv model = new mjtRankingIndiv(mRoundNumber, mTournament.getParams().mRankingIndiv1, mTournament.getParams().mRankingIndiv2, mTournament.getParams().mRankingIndiv3, mTournament.getParams().mRankingIndiv4, mTournament.getParams().mRankingIndiv5, mTournament.getCoachs(), mTournament.getParams().mTeamTournament, mRoundOnly, forPool);
                final HashMap<Criteria, mjtAnnexRank> annexForRankings = new HashMap<>();
                final HashMap<Criteria, mjtAnnexRank> annexAgainstRankings = new HashMap<>();
                for (int j = 0; j < mTournament.getParams().mCriterias.size(); j++) {
                    final Criteria crit = mTournament.getParams().mCriterias.get(j);
                    mjtAnnexRank annex = new mjtAnnexRankIndiv(i, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                            mTournament.getCoachs(), true,
                            mTournament.getParams().mRankingIndiv1, mTournament.getParams().mRankingIndiv2,
                            mTournament.getParams().mRankingIndiv3, mTournament.getParams().mRankingIndiv4,
                            mTournament.getParams().mRankingIndiv5, false, mRoundOnly);
                    annexForRankings.put(crit, annex);
                    annex = new mjtAnnexRankIndiv(i, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                            mTournament.getCoachs(), true,
                            mTournament.getParams().mRankingIndiv1, mTournament.getParams().mRankingIndiv2,
                            mTournament.getParams().mRankingIndiv3, mTournament.getParams().mRankingIndiv4,
                            mTournament.getParams().mRankingIndiv5, false, mRoundOnly);
                    annexAgainstRankings.put(crit, annex);
                }
                final jdgGlobal jdg = new jdgGlobal(MainFrame.getMainFrame(), true, i + 1, mTournament, model, annexForRankings, annexAgainstRankings, false, false);
                jdg.setVisible(true);
                break;

            }
        }
    }//GEN-LAST:event_jbtGlobalActionPerformed

    public int getMatchTableSelectedRow() {
        return jtbMatches.getSelectedRow();
    }

    public void setRoundOnly(boolean roundonly) {
        mRoundOnly = roundonly;
    }

    private void jtbMatchesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbMatchesMouseClicked
        if (evt.getClickCount() == 2) {
            if (mTournament.getParams().mMultiRoster) {
                int col = jtbMatches.getSelectedColumn();
                if (mTournament.getParams().mTeamTournament) {
                    col--;
                }
                if ((col == 1) || (col == 4)) {
                    CoachMatch match = mRound.getCoachMatchs().get(jtbMatches.getSelectedRow());
                    Coach coach;
                    if (col == 1) {
                        coach = (Coach) match.mCompetitor1;
                    } else {
                        coach = (Coach) match.mCompetitor2;
                    }
                    JComboBox jcbRoster = new JComboBox();
                    jcbRoster.setModel(new DefaultComboBoxModel(RosterType.mRostersNames.toArray()));

                    jcbRoster.setSelectedItem(coach.mRoster.mName);
                    JPanel jpn = new JPanel();
                    jpn.setLayout(new BorderLayout());

                    JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHOISISSEZ UN ROSTER POUR ")+coach.mName);

                    jpn.add(jlb, BorderLayout.NORTH);
                    jpn.add(jcbRoster, BorderLayout.CENTER);

                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jpn, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER"), JOptionPane.QUESTION_MESSAGE);

                    int index = jcbRoster.getSelectedIndex();
                    if (col == 1) {
                        match.mRoster1 = RosterType.mRosterTypes.get(index);
                    } else {
                        match.mRoster2 = RosterType.mRosterTypes.get(index);
                    }

                    update();
                }
            }
        }
        MainFrame.getMainFrame().update();
    }//GEN-LAST:event_jtbMatchesMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbtGeneralIndiv;
    private javax.swing.JButton jbtGlobal;
    private javax.swing.JButton jbtShowMatches;
    private javax.swing.JButton jbtShowResults;
    private javax.swing.JTable jtbMatches;
    private javax.swing.JTable jtbRankingIndiv;
    private javax.swing.JTabbedPane jtpAnnexRankings;
    private javax.swing.JTabbedPane jtpGlobal;
    // End of variables declaration//GEN-END:variables
}
