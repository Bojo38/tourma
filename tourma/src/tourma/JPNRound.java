/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPNRound.java
 *
 * Created on 11 mai 2010, 14:13:53
 */
package tourma;

import java.awt.BorderLayout;
import tourma.tableModel.mjtAnnexRank;
import tourma.tableModel.mjtRankingIndiv;
import tourma.tableModel.mjtMatches;
import tourma.views.jdgRound;
import tourma.views.jdgRanking;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Match;
import tourma.data.Coach;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tourma.data.Criteria;
import tourma.data.Group;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.RosterType;
import tourma.tableModel.mjtAnnexRankIndiv;
import tourma.utility.StringConstants;
import tourma.utils.Generation;
import tourma.utils.TableFormat;
import tourma.views.jdgGlobal;

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
        } else {
            if (mTournament.getParams().mEnableClans) {
                mJpnClanRound = new JPNClanRound(r, t);
                jtpGlobal.addTab(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ByClan"), new javax.swing.ImageIcon(getClass().getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/CLAN.PNG"))), mJpnClanRound);
            }
        }
        if (mTournament.getGroups().size() > 1) {
            for (int i = 0; i < mTournament.getGroups().size(); i++) {
                final Group g = mTournament.getGroups().get(i);
                if (!g.mName.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AUCUN"))) {
                    final JPNGroup jpnGroup = new JPNGroup(t, g, mRoundNumber);
                    jtpGlobal.addTab(
                            java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GROUPE: {0}"), g.mName),
                            new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Group.png")),
                            jpnGroup);
                }
            }
        }

        if (mTournament.getPools().size() >= 1) {
            for (int i = 0; i < mTournament.getPools().size(); i++) {
                final Pool p = mTournament.getPools().get(i);
                final JPNPoolRound jpnPool = new JPNPoolRound(r, t, p);
                jtpGlobal.addTab(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POULE: {0}"), p.mName),
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

            jbtDeleteRound.setEnabled(!locked);

            final mjtMatches model = new mjtMatches(mRound.getMatchs(), locked, mTournament.getParams().mTeamTournament, true);
            jtbMatches.setModel(model);
            jtbMatches.setDefaultRenderer(String.class, model);
            jtbMatches.setDefaultRenderer(Integer.class, model);
            jtbMatches.setRowHeight(25);

            jtbRankingIndiv.setRowHeight(25);

            jbtNextRound.setEnabled((!mTournament.mRoundRobin) || (mTournament.mRoundRobin && (mRoundNumber == mTournament.getRounds().size() - 1)));

            /*        jtbMatches.setDefaultEditor(Integer.class, model);*/
            TableFormat.setColumnSize(jtbMatches);
            final ArrayList<Round> v = new ArrayList<Round>();
            for (int i = 0; i < mTournament.getRounds().size(); i++) {
                if (mTournament.getRounds().get(i).getHour().before(mRound.getHour())) {
                    v.add(mTournament.getRounds().get(i));
                }
            }
            v.add(mRound);

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
            Object panel = jtpGlobal.getTabComponentAt(i);
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
                            if (panel instanceof JPNCup) {
                                ((JPNCup) panel).update();
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
        jbtNextRound = new javax.swing.JButton();
        jbtShowMatches = new javax.swing.JButton();
        jbtShowResults = new javax.swing.JButton();
        jbtDeleteRound = new javax.swing.JButton();
        jbtChangeMatchs = new javax.swing.JButton();
        jbtAddMatch = new javax.swing.JButton();
        jbtDelMatch = new javax.swing.JButton();
        jtbRoundSum = new javax.swing.JToggleButton();
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

        jbtNextRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Generate.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtNextRound.setText(bundle.getString("GenerateNextRoundKey")); // NOI18N
        jbtNextRound.setActionCommand(bundle.getString("GÉNÉRER RONDE SUISSE")); // NOI18N
        jbtNextRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextRoundActionPerformed(evt);
            }
        });
        jPanel3.add(jbtNextRound);

        jbtShowMatches.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
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

        jbtDeleteRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Halt.png"))); // NOI18N
        jbtDeleteRound.setText(bundle.getString("DeleteCurrentRoundKey")); // NOI18N
        jbtDeleteRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDeleteRoundActionPerformed(evt);
            }
        });
        jPanel3.add(jbtDeleteRound);

        jbtChangeMatchs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Swap.png"))); // NOI18N
        jbtChangeMatchs.setText(bundle.getString("ChangePairing")); // NOI18N
        jbtChangeMatchs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtChangeMatchsActionPerformed(evt);
            }
        });
        jPanel3.add(jbtChangeMatchs);

        jbtAddMatch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAddMatch.setText(bundle.getString("AddMatch")); // NOI18N
        jbtAddMatch.setActionCommand(bundle.getString("AJOUTER UN MATCH")); // NOI18N
        jbtAddMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddMatchActionPerformed(evt);
            }
        });
        jPanel3.add(jbtAddMatch);

        jbtDelMatch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtDelMatch.setText(bundle.getString("DelMatch")); // NOI18N
        jbtDelMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDelMatchActionPerformed(evt);
            }
        });
        jPanel3.add(jbtDelMatch);

        jtbRoundSum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Zoom.png"))); // NOI18N
        jtbRoundSum.setText(bundle.getString("SUR LA RONDE / CUMUL  DES RONDES")); // NOI18N
        jtbRoundSum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbRoundSumActionPerformed(evt);
            }
        });
        jPanel3.add(jtbRoundSum);

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
    private void jbtNextRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextRoundActionPerformed

        final ArrayList<String> labels = new ArrayList<String>();
        final ArrayList<Integer> Options = new ArrayList<Integer>();

        /**
         * Swiss possible ?
         */
        if ((!mRound.mCup) && (!mTournament.mRoundRobin)) {
            labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RONDE SUISSE"));
            Options.add(Generation.GEN_SWISS);
        }

        /**
         * QSwiss possible ?
         */
        if ((!mRound.mCup) && (!mTournament.mRoundRobin)) {
            labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RONDE SUISSE ACCELERÉE"));
            Options.add(Generation.GEN_QSWISS);
        }

        /**
         * Random possible ?
         */
        if ((!mRound.mCup) && (!mTournament.mRoundRobin)) {
            labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ALÉATOIRE"));
            Options.add(Generation.GEN_RANDOM);
        }

        /**
         * Coupe
         */
        labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COUPE"));
        Options.add(Generation.GEN_CUP);

        final JPanel jpn = new JPanel(new BorderLayout());
        final JComboBox jcb = new JComboBox(labels.toArray());
        jpn.add(jcb, BorderLayout.CENTER);
        final JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHOISISSEZ LA MÉTHODE DE GÉNÉRATION: "));
        jpn.add(jlb, BorderLayout.NORTH);

        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jpn, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GÉNÉRATION"), JOptionPane.QUESTION_MESSAGE);

        final int index = jcb.getSelectedIndex();


        Generation.NextRound(mRound, Options.get(index), mRoundNumber);
        update();
        MainFrame.getMainFrame().update();
        MainFrame.getMainFrame().updateTree();
    }//GEN-LAST:event_jbtNextRoundActionPerformed

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
    private void jbtDeleteRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDeleteRoundActionPerformed

        if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ConfirmEraseCurrentRound"), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("EraseRound"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {


            // Remove mRound
       /* mTournament.getRounds().remove(mRound);
             for (int i = MainFrame.getMainFrame().jtpMain.getTabCount() - 1; i
             >= 0; i--) {
             final Component obj = MainFrame.getMainFrame().jtpMain.getComponentAt(i);
             if (obj instanceof JPNRound) {
             MainFrame.getMainFrame().jtpMain.remove(obj);
             }
             }*/

            // Remove matchs from coach reference list
            for (int i = 0; i < mRound.getMatchs().size(); i++) {
                final Match m = mRound.getMatchs().get(i);
                m.mCoach1.mMatchs.remove(m);
                m.mCoach2.mMatchs.remove(m);
            }

            mTournament.getRounds().remove(mRound);

            /* for (int i = 0; i
             < mTournament.getRounds().size(); i++) {
             final JPNRound jpnr = new JPNRound(i, mTournament.getRounds().get(i), mTournament);
             MainFrame.getMainFrame().jtpMain.addTab(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Round") + " " + (i + 1), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Dice.png")), jpnr);
             }*/

            MainFrame.getMainFrame().update();
            MainFrame.getMainFrame().updateTree();
        }
    }//GEN-LAST:event_jbtDeleteRoundActionPerformed
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
                final HashMap<Criteria, mjtAnnexRank> annexForRankings = new HashMap<Criteria, mjtAnnexRank>();
                final HashMap<Criteria, mjtAnnexRank> annexAgainstRankings = new HashMap<Criteria, mjtAnnexRank>();
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
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtChangeMatchsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtChangeMatchsActionPerformed
        final jdgChangePairing jdg = new jdgChangePairing(MainFrame.getMainFrame(), true, mRound);
        jdg.setVisible(true);
        update();
    }//GEN-LAST:event_jbtChangeMatchsActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddMatchActionPerformed

        final ArrayList<Coach> Coachs1 = new ArrayList<Coach>();
        final ArrayList<Coach> Coachs2 = new ArrayList<Coach>();

        final JComboBox<String> jcb1 = new JComboBox<String>();
        final JComboBox<String> jcb2 = new JComboBox<String>();

        for (int i = 0; i < mTournament.GetActiveCoaches().size(); i++) {
            final Coach c = mTournament.GetActiveCoaches().get(i);
            Coachs1.add(c);
            Coachs2.add(c);
            jcb1.addItem(c.mName);
            jcb2.addItem(c.mName);
        }

        boolean ValidMatch = false;

        while (!ValidMatch) {
            jcb1.setSelectedIndex(0);
            jcb2.setSelectedIndex(1);

            final JPanel jpnQuestion = new JPanel(new BorderLayout(0, 0));
            jpnQuestion.add(jcb1, BorderLayout.WEST);
            jpnQuestion.add(jcb2, BorderLayout.EAST);
            final JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" VS "));
            jpnQuestion.add(jlb, BorderLayout.CENTER);

            final int ret = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), jpnQuestion, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH LIBRE"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (ret == JOptionPane.OK_OPTION) {
                if (jcb1.getSelectedIndex() != jcb2.getSelectedIndex()) {
                    final Match m = new Match(mRound);
                    m.mCoach1 = Coachs1.get(jcb1.getSelectedIndex());
                    m.mCoach2 = Coachs2.get(jcb2.getSelectedIndex());

                    mRound.getMatchs().add(m);
                    m.mCoach1.mMatchs.add(m);
                    m.mCoach2.mMatchs.add(m);
                    ValidMatch = true;
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH IMPOSSIBLE"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ERREUR"), JOptionPane.ERROR_MESSAGE);
                }
            } else {
                ValidMatch = true;
            }
        }


        update();
    }//GEN-LAST:event_jbtAddMatchActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtDelMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDelMatchActionPerformed

        if (jtbMatches.getSelectedRow() >= 0) {
            mRound.getMatchs().remove(jtbMatches.getSelectedRow());
            update();
        }
    }//GEN-LAST:event_jbtDelMatchActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtbRoundSumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbRoundSumActionPerformed
        mRoundOnly = jtbRoundSum.isSelected();

        update();
    }//GEN-LAST:event_jtbRoundSumActionPerformed

    private void jtbMatchesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbMatchesMouseClicked
        if (evt.getClickCount() == 2) {
            if (mTournament.getParams().mMultiRoster) {
                int col = jtbMatches.getSelectedColumn();
                if (mTournament.getParams().mTeamTournament) {
                    col--;
                }
                if ((col == 1) || (col == 4)) {
                    Match match = mRound.getMatchs().get(jtbMatches.getSelectedRow());
                    Coach coach;
                    if (col == 1) {
                        coach = match.mCoach1;
                    } else {
                        coach = match.mCoach2;
                    }
                    JComboBox jcbRoster = new JComboBox();
                    jcbRoster.setModel(new DefaultComboBoxModel(RosterType.mRostersNames.toArray()));

                    jcbRoster.setSelectedItem(coach.mRoster.mName);
                    JPanel jpn = new JPanel();
                    jpn.setLayout(new BorderLayout());

                    JLabel jlb = new JLabel("Choisissez un roster pour " + coach.mName);

                    jpn.add(jlb, BorderLayout.NORTH);
                    jpn.add(jcbRoster, BorderLayout.CENTER);

                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jpn, "Roster", JOptionPane.QUESTION_MESSAGE);

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
    private javax.swing.JButton jbtAddMatch;
    private javax.swing.JButton jbtChangeMatchs;
    private javax.swing.JButton jbtDelMatch;
    private javax.swing.JButton jbtDeleteRound;
    private javax.swing.JButton jbtGeneralIndiv;
    private javax.swing.JButton jbtGlobal;
    private javax.swing.JButton jbtNextRound;
    private javax.swing.JButton jbtShowMatches;
    private javax.swing.JButton jbtShowResults;
    private javax.swing.JTable jtbMatches;
    private javax.swing.JTable jtbRankingIndiv;
    private javax.swing.JToggleButton jtbRoundSum;
    private javax.swing.JTabbedPane jtpAnnexRankings;
    private javax.swing.JTabbedPane jtpGlobal;
    // End of variables declaration//GEN-END:variables
}
