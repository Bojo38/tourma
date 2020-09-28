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
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tourma.JdgCoach;
import tourma.MainFrame;
import tourma.data.Category;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.ETeamPairing;
import tourma.data.Formula;
import tourma.data.Group;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.tableModel.MjtAnnexRank;
import tourma.tableModel.MjtAnnexRankIndiv;
import tourma.tableModel.MjtMatches;
import tourma.tableModel.MjtRankingIndiv;
import tourma.utils.display.TableFormat;
import tourma.views.JPNCup;
import tourma.views.report.JdgGlobal;
import tourma.views.report.JdgRanking;
import tourma.views.report.JdgRound;

/**
 *
 * @author Frederic Berger
 */
public final class JPNRound extends javax.swing.JPanel {

    private static final long serialVersionUID = 31L;

    private final Round mRound;
    private final int mRoundNumber;
    private final Tournament mTournament;
    private JPNTeamRound mJpnTeamRound = null;
    private JPNClan mJpnClanRound = null;
    private boolean mRoundOnly = false;
    private boolean mNafOnly = false;

    private final static String CS_ByTeam = "ByTeam";
    private final static String CS_ByClan = "ByClan";
    private final static String CS_None = "None";
    private final static String CS_Group = "Group";
    private final static String CS_Category = "Category";
    private final static String CS_Pool = "Pool";
    private final static String CS_GeneralByCoach = "GENERAL PAR COACH";
    private final static String CS_ChooseARosterFor = "CHOISISSEZ UN ROSTER POUR ";
    private final static String CS_Roster = "Roster";

    /**
     * Creates new form JPNRound
     *
     * @param roundNumber
     * @param r
     * @param t
     */
    public JPNRound(final int roundNumber, final Round r, final Tournament t) {
        initComponents();
        mRound = r;
        mTournament = t;
        mRoundNumber = roundNumber;

        if (mTournament.getPoolCount() > 0) {
            jcxPoolOption.setSelected(true);
        }
        if (mTournament.getParams().isTeamTournament()) {
            mJpnTeamRound = new JPNTeamRound(r, t);
            jtpGlobal.addTab(
                    Translate.translate(CS_ByTeam),
                    new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Team.png"
                    )), mJpnTeamRound);
        }
        if (mTournament.getParams().isEnableClans()) {
            mJpnClanRound = new JPNClan(r, t);
            jtpGlobal.addTab(
                    Translate.translate(CS_ByClan),
                    new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Clan.png"
                    )), mJpnClanRound);
        }

        if (mTournament.getGroupsCount() > 1) {
            for (int i = 0; i < mTournament.getGroupsCount(); i++) {
                final Group g = mTournament.getGroup(i);
                if (!g.getName().equals(
                        Translate.translate(CS_None)
                )) {
                    final JPNGroup jpnGroup = new JPNGroup(t, g, mRoundNumber);
                    jtpGlobal.addTab(
                            Translate.translate(CS_Group) + " " + g.getName(),
                            new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Group.png")),
                            jpnGroup);
                }
            }
        }

        if (mTournament.getCategoriesCount() > 0) {
            for (int i = 0; i < mTournament.getCategoriesCount(); i++) {
                final Category c = mTournament.getCategory(i);
                if (!c.getName().equals(Translate.translate(CS_None))) {
                    final JPNCategory jpnCategory = new JPNCategory(t, c, mRoundNumber);
                    jtpGlobal.addTab(
                            Translate.translate(CS_Category) + " " + c.getName(),
                            new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Group2.png")),
                            jpnCategory);
                }
            }
        }

        if (mTournament.getPoolCount() >= 1) {
            for (int i = 0; i < mTournament.getPoolCount(); i++) {
                final Pool p = mTournament.getPool(i);
                final JPNPool jpnPool = new JPNPool(r, t, p);
                jtpGlobal.addTab(
                        Translate.translate(CS_Pool) + " " + p.getName(),
                        new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Pool.png")), jpnPool);
            }
        }

        ArrayList<Team> teams = new ArrayList<>();
        for (int cpt = 0; cpt < t.getTeamsCount(); cpt++) {
            teams.add(t.getTeam(cpt));
        }
        for (int i = 0; i < mTournament.getParams().getCriteriaCount(); i++) {
            final Criteria criteria = mTournament.getParams().getCriteria(i);
            final ArrayList<Coach> coaches = new ArrayList<>();
            for (int cpt = 0; cpt < t.getCoachsCount(); cpt++) {
                coaches.add(t.getCoach(cpt));
            }
            final JPNAnnexRanking jpn = new JPNAnnexRanking(criteria.getName(), criteria, t, coaches, teams, mRound, false, false);
            jtpAnnexRankings.add(criteria.getName(), jpn);
        }

        for (int i = 0; i < mTournament.getParams().getFormulaCount(); i++) {
            final Formula formula = mTournament.getParams().getFormula(i);
            final ArrayList<Coach> coaches = new ArrayList<>();
            for (int cpt = 0; cpt < t.getCoachsCount(); cpt++) {
                coaches.add(t.getCoach(cpt));
            }
            final JPNAnnexRanking jpn = new JPNAnnexRanking(formula.getName(), formula, t, coaches, teams, mRound, false, false);
            jtpAnnexRankings.add(formula.getName(), jpn);
        }
        
        mNafOnly = MainFrame.getMainFrame().isNafOnly();
        mRoundOnly = MainFrame.getMainFrame().isRoundOnly();
        update();
    }

    /**
     *
     * @return
     */
    public Round getRound() {
        return mRound;
    }

    /**
     * Update Panel
     */
    public void update() {

        if (mRound != null) {
            final Date d = mRound.getHour();
            boolean locked = false;
            for (int i = 0; i < mTournament.getRoundsCount(); i++) {
                if (mTournament.getRound(i).getHour().after(d)) {
                    locked = true;
                }
            }
            if (mTournament.isRoundRobin()) {
                locked = false;
            }

            if (mJpnTeamRound != null) {
                mJpnTeamRound.update();
            }
            if (mJpnClanRound != null) {
                mJpnClanRound.update();
            }

//            jbtDeleteRound.setEnabled(!locked);
            final MjtMatches model = new MjtMatches(mRound.getCoachMatchs(), locked, mTournament.getParams().isTeamTournament(), true, mNafOnly);
            jtbMatches.setModel(model);
            jtbMatches.setDefaultRenderer(String.class, model);
            jtbMatches.setDefaultRenderer(Integer.class, model);
            jtbMatches.setRowHeight(30);

            jtbRankingIndiv.setRowHeight(30);

            TableFormat.setColumnSize(jtbMatches);

            if (mRoundNumber < mTournament.getRoundsCount()) {
                boolean forPool = (mTournament.getPoolCount() > 0) && (!mRound.isCup());

                if (forPool) {
                    jcxPoolOption.setEnabled(true);
                    forPool = jcxPoolOption.isSelected();
                } else {
                    jcxPoolOption.setSelected(false);
                    jcxPoolOption.setEnabled(false);
                }

                boolean forCup = mRound.isCup();
                if (forCup) {
                    jcxCupOption.setEnabled(true);
                    forCup = jcxCupOption.isSelected();
                } else {
                    jcxCupOption.setSelected(false);
                    jcxCupOption.setEnabled(false);
                }

                final ArrayList<Coach> coaches = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount(); cpt++) {
                    coaches.add(Tournament.getTournament().getCoach(cpt));
                }
                final MjtRankingIndiv mRanking = new MjtRankingIndiv(mRoundNumber, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), coaches, mTournament.getParams().isTeamTournament(), mRoundOnly, forPool, forCup);
                jtbRankingIndiv.setModel(mRanking);
                jtbRankingIndiv.setDefaultRenderer(String.class, mRanking);
                jtbRankingIndiv.setDefaultRenderer(Integer.class, mRanking);

                for (int i = 0; i < jtpAnnexRankings.getComponentCount(); i++) {
                    ((JPNAnnexRanking) jtpAnnexRankings.getComponent(i)).setRoundOnly(mRoundOnly);
                    ((JPNAnnexRanking) jtpAnnexRankings.getComponent(i)).update();
                }
                TableFormat.setColumnSize(jtbRankingIndiv);
            }

            jmiChangeRosterForThisRound.setEnabled(Tournament.getTournament().getParams().isMultiRoster());
        }
        for (int i = 0; i < jtpGlobal.getTabCount(); i++) {
            Object panel;
            panel = jtpGlobal.getComponent(i);
            if (panel instanceof JPNGroup) {
                ((JPNGroup) panel).setRoundOnly(mRoundOnly);
                ((JPNGroup) panel).update();
            } else if (panel instanceof JPNTeamRound) {
                ((JPNTeamRound) panel).setRoundOnly(mRoundOnly);
                ((JPNTeamRound) panel).update();
            } else if (panel instanceof JPNClan) {
                ((JPNClan) panel).setRoundOnly(mRoundOnly);
                ((JPNClan) panel).update();
            } else if (panel instanceof JPNPool) {
                ((JPNPool) panel).setRoundOnly(mRoundOnly);
                ((JPNPool) panel).update();
            } else if (panel instanceof JPNCategory) {
                ((JPNCategory) panel).setRoundOnly(mRoundOnly);
                ((JPNCategory) panel).update();
            } else if (panel instanceof JPNCup) {
                ((JPNCup) panel).update();
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

        jpmCoach = new javax.swing.JPopupMenu();
        jmiEditCoach = new javax.swing.JMenuItem();
        jmiChangePairing = new javax.swing.JMenuItem();
        jmiChangeRosterForThisRound = new javax.swing.JMenuItem();
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
        jPanel2 = new javax.swing.JPanel();
        jcxPoolOption = new javax.swing.JCheckBox();
        jcxCupOption = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jtpAnnexRankings = new javax.swing.JTabbedPane();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jmiEditCoach.setText(bundle.getString("EditCoach")); // NOI18N
        jmiEditCoach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditCoachActionPerformed(evt);
            }
        });
        jpmCoach.add(jmiEditCoach);

        jmiChangePairing.setText(bundle.getString("ChangePairing")); // NOI18N
        jmiChangePairing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiChangePairingActionPerformed(evt);
            }
        });
        jpmCoach.add(jmiChangePairing);

        jmiChangeRosterForThisRound.setText(bundle.getString("ChangeRoster")); // NOI18N
        jmiChangeRosterForThisRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiChangeRosterForThisRoundActionPerformed(evt);
            }
        });
        jpmCoach.add(jmiChangeRosterForThisRound);

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
        jtbMatches.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbMatchesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtbMatches);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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

        jcxPoolOption.setText(bundle.getString("PoolOption")); // NOI18N
        jcxPoolOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxPoolOptionActionPerformed(evt);
            }
        });
        jPanel2.add(jcxPoolOption);

        jcxCupOption.setText(bundle.getString("CupOption")); // NOI18N
        jcxCupOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxCupOptionActionPerformed(evt);
            }
        });
        jPanel2.add(jcxCupOption);

        jPanel6.add(jPanel2, java.awt.BorderLayout.NORTH);

        jSplitPane1.setLeftComponent(jPanel6);

        jPanel8.setLayout(new java.awt.BorderLayout());
        jPanel8.add(jtpAnnexRankings, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel8);

        jtpGlobal.addTab(bundle.getString("IndividualRankingKey"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/User.png")), jSplitPane1); // NOI18N

        add(jtpGlobal, java.awt.BorderLayout.CENTER);
        jtpGlobal.getAccessibleContext().setAccessibleName(bundle.getString("Matchs")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtShowMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowMatchesActionPerformed

        for (int i = 0; i
                < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {

                final JdgRound jdg = new JdgRound(MainFrame.getMainFrame(), true, mRound, i + 1, mTournament, false, false);
                jdg.setVisible(true);
                break;

            }
        }

    }//GEN-LAST:event_jbtShowMatchesActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtShowResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowResultsActionPerformed

        for (int i = 0; i
                < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {
                final JdgRound jdg = new JdgRound(MainFrame.getMainFrame(), true, mRound, i + 1, mTournament, true, false);
                jdg.setVisible(true);
                break;

            }
        }

    }//GEN-LAST:event_jbtShowResultsActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralIndivActionPerformed

        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {
                final boolean forPool = (mTournament.getPoolCount() > 0) && (!mRound.isCup());
                final ArrayList<Coach> coaches = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount(); cpt++) {
                    coaches.add(Tournament.getTournament().getCoach(cpt));
                }
                boolean forCup = mRound.isCup();
                final MjtRankingIndiv model = new MjtRankingIndiv(mRoundNumber, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), coaches, mTournament.getParams().isTeamTournament(), mRoundOnly, forPool, forCup);
                final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true,
                        Translate.translate(CS_GeneralByCoach),
                        i + 1, mTournament, model, 0);
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

        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {
                final boolean forPool = (mTournament.getPoolCount() > 0) && (!mRound.isCup());
                final ArrayList<Coach> coaches = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount(); cpt++) {
                    coaches.add(Tournament.getTournament().getCoach(cpt));
                }
                boolean forCup = mRound.isCup();
                final MjtRankingIndiv model = new MjtRankingIndiv(mRoundNumber, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), coaches, mTournament.getParams().isTeamTournament(), mRoundOnly, forPool, forCup);
                final HashMap<Criteria, MjtAnnexRank> annexForRankings = new HashMap<>();
                final HashMap<Criteria, MjtAnnexRank> annexAgainstRankings = new HashMap<>();
                for (int j = 0; j < mTournament.getParams().getCriteriaCount(); j++) {
                    final Criteria crit = mTournament.getParams().getCriteria(j);
                    MjtAnnexRank annex = new MjtAnnexRankIndiv(i, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                            coaches, true, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), false, mRoundOnly);
                    annexForRankings.put(crit, annex);
                    annex = new MjtAnnexRankIndiv(i, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                            coaches, true, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), false, mRoundOnly);
                    annexAgainstRankings.put(crit, annex);
                }
                final JdgGlobal jdg = new JdgGlobal(MainFrame.getMainFrame(), true, i + 1, mTournament, model, annexForRankings, annexAgainstRankings, false, false);
                jdg.setVisible(true);
                break;

            }
        }

    }//GEN-LAST:event_jbtGlobalActionPerformed

    /**
     *
     * @return
     */
    public int getMatchTableSelectedRow() {
        return jtbMatches.getSelectedRow();
    }

    /**
     *
     * @param roundonly
     */
    public void setRoundOnly(boolean roundonly) {
        mRoundOnly = roundonly;
    }

    public void setNafOnly(boolean nafonly) {
        mNafOnly = nafonly;
    }

    private void jtbMatchesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbMatchesMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {

            if (jtbMatches.getSelectedRow() >= 0) {
                CoachMatch cm = mRound.getCoachMatchs().get(jtbMatches.getSelectedRow());
                Coach c = null;
                Coach opp = null;
                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    if (jtbMatches.getSelectedColumn() == 2) {
                        c = (Coach) cm.getCompetitor1();
                        opp = (Coach) cm.getCompetitor2();
                    }
                    if (jtbMatches.getSelectedColumn() == 5) {
                        c = (Coach) cm.getCompetitor2();
                        opp = (Coach) cm.getCompetitor1();
                    }
                } else {
                    if (jtbMatches.getSelectedColumn() == 1) {
                        c = (Coach) cm.getCompetitor1();
                        opp = (Coach) cm.getCompetitor2();
                    }
                    if (jtbMatches.getSelectedColumn() == 4) {
                        c = (Coach) cm.getCompetitor2();
                        opp = (Coach) cm.getCompetitor1();
                    }
                }
                if (c != null) {
                    jpmCoach.setLocation(evt.getXOnScreen(), evt.getYOnScreen());
                    jpmCoach.setVisible(true);
                }
            }

        }

        if (evt.getClickCount() == 2) {
            jmiChangeRosterForThisRoundActionPerformed(null);
        }
        MainFrame.getMainFrame().update();
    }//GEN-LAST:event_jtbMatchesMouseClicked

    private void jtbMatchesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbMatchesKeyPressed
        // First detect value to set
        // Get Match
        int matchIndex = jtbMatches.getSelectedRow();
        if (matchIndex >= 0) {

            CoachMatch match = this.mRound.getCoachMatchs().get(matchIndex);
            boolean c1 = false;
            int critIndex = -1;
            int col = jtbMatches.getSelectedColumn();
            switch (col) {
                case 2:
                    if (!Tournament.getTournament().getParams().isTeamTournament()) {
                        c1 = true;
                        critIndex = 0;
                        match.resetWL();
                        match.recomputeValues();
                    }

                    break;
                case 3:
                    if (Tournament.getTournament().getParams().isTeamTournament()) {
                        c1 = true;
                        critIndex = 0;
                    } else {
                        c1 = false;
                        critIndex = 0;
                    }
                    match.resetWL();
                    match.recomputeValues();
                    break;
                case 4:
                    if (Tournament.getTournament().getParams().isTeamTournament()) {
                        c1 = false;
                        critIndex = 0;
                        match.resetWL();
                        match.recomputeValues();
                    }
                    break;
                default:
                    if (((Tournament.getTournament().getParams().isTeamTournament()) && (col >= 7))
                            || (((!Tournament.getTournament().getParams().isTeamTournament()) && (col >= 5)))) {
                        c1 = (col % 2 == 1);
                        if (Tournament.getTournament().getParams().isTeamTournament()) {
                            critIndex = (col - 5) / 2;
                        } else {
                            critIndex = (col - 3) / 2;
                        }
                        match.resetWL();
                        match.recomputeValues();
                    }
            }
            if (critIndex >= 0) {
                if (critIndex < Tournament.getTournament().getParams().getCriteriaCount()) {
                    Value mv = match.getValues().get(Tournament.getTournament().getParams().getCriteria(critIndex));
                    if (c1) {
                        int v = mv.getValue1();
                        if (critIndex == 0) {
                            if (v == -1) {
                                mv.setValue1(0);
                                match.resetWL();
                                match.recomputeValues();
                            }
                        }
                    } else {
                        int v = mv.getValue2();
                        if (critIndex == 0) {
                            if (v == -1) {
                                mv.setValue2(0);
                                match.resetWL();
                                match.recomputeValues();
                            }
                        }
                    }
                }
            }

        }
    }//GEN-LAST:event_jtbMatchesKeyPressed

    private void jmiChangePairingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiChangePairingActionPerformed
        jpmCoach.setVisible(false);
        if (jtbMatches.getSelectedRow() >= 0) {

            TeamMatch tm = null;
            CoachMatch cm = mRound.getCoachMatchs().get(jtbMatches.getSelectedRow());
            Coach c = null;
            Coach opp = null;
            if (Tournament.getTournament().getParams().isTeamTournament()) {
                if (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
                    tm = (TeamMatch) mRound.getMatch(jtbMatches.getSelectedRow() / Tournament.getTournament().getParams().getTeamMatesNumber());
                }
                if (jtbMatches.getSelectedColumn() == 2) {
                    c = (Coach) cm.getCompetitor1();
                    opp = (Coach) cm.getCompetitor2();
                }
                if (jtbMatches.getSelectedColumn() == 5) {
                    c = (Coach) cm.getCompetitor2();
                    opp = (Coach) cm.getCompetitor1();
                }
            } else {
                if (jtbMatches.getSelectedColumn() == 1) {
                    c = (Coach) cm.getCompetitor1();
                    opp = (Coach) cm.getCompetitor2();
                }
                if (jtbMatches.getSelectedColumn() == 4) {
                    c = (Coach) cm.getCompetitor2();
                    opp = (Coach) cm.getCompetitor1();
                }
            }
            if (c != null) {
                int option = JOptionPane.showConfirmDialog(this, "Voulez vous échanger l'appariement de ce coach ?", "Appariement", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {

                    ArrayList<Coach> coachs = new ArrayList<>();
                    if (Tournament.getTournament().getParams().isTeamTournament() && (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
                        for (int i = 0; i < opp.getTeamMates().getCoachsCount(); i++) {
                            coachs.add(opp.getTeamMates().getCoach(i));
                        }
                    } else {
                        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                            if (Tournament.getTournament().getCoach(i) != c) {
                                coachs.add(Tournament.getTournament().getCoach(i));
                            }
                        }
                    }

                    ArrayList<String> coachNames = new ArrayList<>();
                    for (int i = 0; i < coachs.size(); i++) {
                        coachNames.add(coachs.get(i).getName());
                    }

                    JComboBox jcbCoaches = new JComboBox(coachNames.toArray());
                    jcbCoaches.setSelectedItem(opp.getName());
                    JLabel jlbMessage = new JLabel("Choisissez le nouvel adersaire de " + c.getName());
                    JPanel jpnMessage = new JPanel(new FlowLayout());
                    jpnMessage.add(jlbMessage);
                    jpnMessage.add(jcbCoaches);

                    int result = JOptionPane.showConfirmDialog(this, jpnMessage, "Pairing", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        Coach newOpp = coachs.get(jcbCoaches.getSelectedIndex());

                        opp.removeMatch(cm);

                        CoachMatch cm2 = null;
                        for (int i = 0; i < mRound.getCoachMatchs().size(); i++) {
                            cm2 = mRound.getCoachMatchs().get(i);
                            if (cm2.getCompetitor1() == newOpp) {
                                newOpp.removeMatch(cm2);
                                opp.addMatch(cm2);
                                newOpp.addMatch(cm);
                                cm2.setCompetitor1(opp);
                                if (cm.getCompetitor1() == c) {
                                    cm.setCompetitor2(newOpp);
                                }
                                if (cm.getCompetitor2() == c) {
                                    cm.setCompetitor1(newOpp);
                                }
                                cm.recomputeValues();
                                cm2.recomputeValues();
                                if (tm != null) {
                                    tm.recomputeValues();
                                }
                                break;
                            }
                            if (cm2.getCompetitor2() == newOpp) {
                                newOpp.removeMatch(cm2);
                                newOpp.addMatch(cm);
                                opp.addMatch(cm2);
                                cm2.setCompetitor2(opp);
                                if (cm.getCompetitor1() == c) {
                                    cm.setCompetitor2(newOpp);
                                }
                                if (cm.getCompetitor2() == c) {
                                    cm.setCompetitor1(newOpp);
                                }
                                cm.recomputeValues();
                                cm2.recomputeValues();
                                if (tm != null) {
                                    tm.recomputeValues();
                                }
                                break;
                            }
                        }

                    }
                }
            }

        }
    }//GEN-LAST:event_jmiChangePairingActionPerformed

    private void jmiEditCoachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditCoachActionPerformed
        jpmCoach.setVisible(false);
        if (jtbMatches.getSelectedRow() >= 0) {

            CoachMatch cm = mRound.getCoachMatchs().get(jtbMatches.getSelectedRow());
            Coach c = null;
            Coach opp = null;
            if (Tournament.getTournament().getParams().isTeamTournament()) {
                if (jtbMatches.getSelectedColumn() == 2) {
                    c = (Coach) cm.getCompetitor1();
                    opp = (Coach) cm.getCompetitor2();
                }
                if (jtbMatches.getSelectedColumn() == 5) {
                    c = (Coach) cm.getCompetitor2();
                    opp = (Coach) cm.getCompetitor1();
                }
            } else {
                if (jtbMatches.getSelectedColumn() == 1) {
                    c = (Coach) cm.getCompetitor1();
                    opp = (Coach) cm.getCompetitor2();
                }
                if (jtbMatches.getSelectedColumn() == 4) {
                    c = (Coach) cm.getCompetitor2();
                    opp = (Coach) cm.getCompetitor1();
                }
            }
            if (c != null) {
                JdgCoach jdg = new JdgCoach(MainFrame.getMainFrame(), true, c);
                jdg.setVisible(true);
            }

        }

    }//GEN-LAST:event_jmiEditCoachActionPerformed

    private void jmiChangeRosterForThisRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiChangeRosterForThisRoundActionPerformed
        jpmCoach.setVisible(false);

        if (mTournament.getParams().isMultiRoster()) {
            int col = jtbMatches.getSelectedColumn();
            if (mTournament.getParams().isTeamTournament()) {
                col--;
            }
            if ((col == 1) || (col == 4)) {
                CoachMatch match = mRound.getCoachMatchs().get(jtbMatches.getSelectedRow());
                Coach coach;
                if (col == 1) {
                    coach = (Coach) match.getCompetitor1();
                } else {
                    coach = (Coach) match.getCompetitor2();
                }
                JComboBox jcbRoster = new JComboBox();
                jcbRoster.setModel(RosterType.getRostersNamesModel());

                jcbRoster.setSelectedItem(coach.getRoster().getName());
                JPanel jpn = new JPanel();
                jpn.setLayout(new BorderLayout());

                JLabel jlb = new JLabel(
                        Translate.translate(CS_ChooseARosterFor) + " "
                        + coach.getName());

                jpn.add(jlb, BorderLayout.NORTH);
                jpn.add(jcbRoster, BorderLayout.CENTER);

                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jpn,
                        Translate.translate(CS_Roster),
                        JOptionPane.QUESTION_MESSAGE);

                int index = jcbRoster.getSelectedIndex();
                if (col == 1) {
                    match.setRoster1(RosterType.getRosterType(index));
                } else {
                    match.setRoster2(RosterType.getRosterType(index));
                }
                update();
            }
        }

    }//GEN-LAST:event_jmiChangeRosterForThisRoundActionPerformed

    private void jcxPoolOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxPoolOptionActionPerformed
        update();
    }//GEN-LAST:event_jcxPoolOptionActionPerformed

    private void jcxCupOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxCupOptionActionPerformed
        update();
    }//GEN-LAST:event_jcxCupOptionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JCheckBox jcxCupOption;
    private javax.swing.JCheckBox jcxPoolOption;
    private javax.swing.JMenuItem jmiChangePairing;
    private javax.swing.JMenuItem jmiChangeRosterForThisRound;
    private javax.swing.JMenuItem jmiEditCoach;
    private javax.swing.JPopupMenu jpmCoach;
    private javax.swing.JTable jtbMatches;
    private javax.swing.JTable jtbRankingIndiv;
    private javax.swing.JTabbedPane jtpAnnexRankings;
    private javax.swing.JTabbedPane jtpGlobal;
    // End of variables declaration//GEN-END:variables

}
