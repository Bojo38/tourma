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
import tourma.views.report.jdgRound;
import tourma.views.report.jdgRanking;
import tourma.data.ObjectRanking;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Match;
import tourma.data.Coach;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import tourma.data.Criteria;
import tourma.data.Group;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Team;
import tourma.tableModel.mjtAnnexRankIndiv;
import tourma.tableModel.mjtRankingTeam;
import tourma.views.report.jdgGlobal;

/**
 *
 * @author Frederic Berger
 */
public class JPNRound extends javax.swing.JPanel {

    Round _round;
    int _roundNumber;
    Tournament _tournament;
    JPNTeamRound _jpnTeamRound = null;
    JPNClanRound _jpnClanRound = null;
    boolean _round_only = false;

    /**
     * Creates new form JPNRound
     */
    public JPNRound(int roundNumber, Round r, Tournament t) {
        initComponents();
        _round = r;
        _tournament = t;
        _roundNumber = roundNumber;

        if (_tournament.getParams()._teamTournament) {
            _jpnTeamRound = new JPNTeamRound(r, t);
            jtpGlobal.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ByTeam"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Team.png")), _jpnTeamRound);
        } else {
            if (_tournament.getParams()._enableClans) {
                _jpnClanRound = new JPNClanRound(r, t);
                jtpGlobal.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ByClan"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Clan.png")), _jpnClanRound);
            }
        }
        if (_tournament.getGroups().size() > 1) {
            for (int i = 0; i < _tournament.getGroups().size(); i++) {
                Group g = _tournament.getGroups().get(i);
                if (!g._name.equals("Aucun")) {
                    JPNGroup jpnGroup = new JPNGroup(t, g, _roundNumber);
                    jtpGlobal.addTab("Groupe: " + g._name, new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Group.png")), jpnGroup);
                }
            }
        }

        if (_tournament.getPools().size() >= 1) {
            for (int i = 0; i < _tournament.getPools().size(); i++) {
                Pool p = _tournament.getPools().get(i);
                JPNPoolRound jpnPool = new JPNPoolRound(r, t, p);
                jtpGlobal.addTab("Poule: " + p._name, new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Pool.png")), jpnPool);
            }
        }

        for (int i = 0; i < _tournament.getParams()._criterias.size(); i++) {
            Criteria criteria = _tournament.getParams()._criterias.get(i);
            JPNAnnexRanking jpn = new JPNAnnexRanking(criteria._name, criteria, _tournament, _round, false, false);
            jtpAnnexRankings.add(criteria._name, jpn);
        }

        update();
    }

    public void update() {

        if (_round != null) {
            Date d = _round.getHeure();
            boolean locked = false;
            for (int i = 0; i < _tournament.getRounds().size(); i++) {
                if (_tournament.getRounds().get(i).getHeure().after(d)) {
                    locked = true;
                }
            }
            if (_tournament._roundrobin) {
                locked = false;
            }

            if (_jpnTeamRound != null) {
                _jpnTeamRound.update();
            }
            if (_jpnClanRound != null) {
                _jpnClanRound.update();
            }

            jbtDeleteRound.setEnabled(!locked);

            mjtMatches model = new mjtMatches(_round.getMatchs(), locked, _tournament.getParams()._teamTournament, true);
            jtbMatches.setModel(model);
            jtbMatches.setDefaultRenderer(String.class, model);
            jtbMatches.setDefaultRenderer(Integer.class, model);
            jtbMatches.setRowHeight(25);

            jtbRankingIndiv.setRowHeight(25);

            jbtNextRound.setEnabled(!_round._cup);
            jbtNextRoundQuickSwiss.setEnabled(!_round._cup);
            jbtNextRoundRandom.setEnabled(!_round._cup);

            /*        jtbMatches.setDefaultEditor(Integer.class, model);*/
            setColumnSize(jtbMatches);
            Vector<Round> v = new Vector<Round>();
            for (int i = 0; i < _tournament.getRounds().size(); i++) {
                if (_tournament.getRounds().get(i).getHeure().before(_round.getHeure())) {
                    v.add(_tournament.getRounds().get(i));
                }
            }
            v.add(_round);

            if (_roundNumber < _tournament.getRounds().size()) {
                mjtRankingIndiv mRanking = new mjtRankingIndiv(_roundNumber, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getCoachs(), _tournament.getParams()._teamTournament, _round_only);
                jtbRankingIndiv.setModel(mRanking);
                jtbRankingIndiv.setDefaultRenderer(String.class, mRanking);
                jtbRankingIndiv.setDefaultRenderer(Integer.class, mRanking);

                for (int i = 0; i < jtpAnnexRankings.getComponentCount(); i++) {
                    ((JPNAnnexRanking) jtpAnnexRankings.getComponent(i))._round_only = _round_only;
                    ((JPNAnnexRanking) jtpAnnexRankings.getComponent(i)).update();
                }
                setColumnSize(jtbRankingIndiv);
            }
        }


        for (int i = 0; i < jtpGlobal.getTabCount(); i++) {
            Object panel = jtpGlobal.getTabComponentAt(i);
            panel = jtpGlobal.getComponent(i);
            if (panel instanceof JPNGroup) {
                ((JPNGroup) panel)._round_only = _round_only;
                ((JPNGroup) panel).update();
            } else {
                if (panel instanceof JPNTeamRound) {
                    ((JPNTeamRound) panel)._round_only = _round_only;
                    ((JPNTeamRound) panel).update();
                } else {
                    if (panel instanceof JPNClanRound) {
                        ((JPNClanRound) panel)._round_only = _round_only;
                        ((JPNClanRound) panel).update();
                    } else {
                        if (panel instanceof JPNPoolRound) {
                            ((JPNPoolRound) panel)._round_only = _round_only;
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtpGlobal = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbMatches = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jbtNextRound = new javax.swing.JButton();
        jbtNextRoundRandom = new javax.swing.JButton();
        jbtNextRoundQuickSwiss = new javax.swing.JButton();
        jbtNextRoundFinalTab = new javax.swing.JButton();
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
        jScrollPane1.setViewportView(jtbMatches);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new org.jdesktop.swingx.VerticalLayout());

        jbtNextRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Generate.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtNextRound.setText(bundle.getString("GenerateNextRoundKeySwiss")); // NOI18N
        jbtNextRound.setActionCommand("Générer ronde Suisse");
        jbtNextRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextRoundActionPerformed(evt);
            }
        });
        jPanel3.add(jbtNextRound);

        jbtNextRoundRandom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Generate.png"))); // NOI18N
        jbtNextRoundRandom.setText(bundle.getString("GenerateNextRoundKeyRandom")); // NOI18N
        jbtNextRoundRandom.setToolTipText("Cette méthode permet de générer une ronde totalement aléatoire. Cependant, au plus le nombre de ronde se rapprocher de la limite (nombre de joueurs-1) au plus le logiciel aura du mal à déterminer un tirage aléatoire satisfaisant.\n"); // NOI18N
        jbtNextRoundRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextRoundRandomActionPerformed(evt);
            }
        });
        jPanel3.add(jbtNextRoundRandom);

        jbtNextRoundQuickSwiss.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Generate.png"))); // NOI18N
        jbtNextRoundQuickSwiss.setText(bundle.getString("GenerateNextRoundKeyQuick")); // NOI18N
        jbtNextRoundQuickSwiss.setToolTipText("Une ronde suisse accelerée fait se partage le classement en 4: Q1, Q2 Q3 et Q4. Q1 rencontre Q2 et Q3 rencontre Q4.\nCette méthode permet de créer plus rapidement un groupe de vainqueurs possible. Sur un tournoi en 5 ou  6 ronde avec plus de 32 joueurs, cette méthode peut être utilisée lors des rondes 2 (et 3 sur plus de 64 joueurs).\n"); // NOI18N
        jbtNextRoundQuickSwiss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextRoundQuickSwissActionPerformed(evt);
            }
        });
        jPanel3.add(jbtNextRoundQuickSwiss);

        jbtNextRoundFinalTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Generate.png"))); // NOI18N
        jbtNextRoundFinalTab.setText(bundle.getString("GenerateCup")); // NOI18N
        jbtNextRoundFinalTab.setToolTipText("Le tableau final se déroule comme une coupe."); // NOI18N
        jbtNextRoundFinalTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextRoundFinalTabActionPerformed(evt);
            }
        });
        jPanel3.add(jbtNextRoundFinalTab);

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
        jbtAddMatch.setActionCommand("Ajouter un match");
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
        jtbRoundSum.setText("Sur la ronde / Cumul  des rondes");
        jtbRoundSum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbRoundSumActionPerformed(evt);
            }
        });
        jPanel3.add(jtbRoundSum);

        jPanel1.add(jPanel3, java.awt.BorderLayout.WEST);

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

    public void setColumnSize(JTable t) {
        FontMetrics fm = t.getFontMetrics(t.getFont());
        for (int i = 0; i < t.getColumnCount(); i++) {
            int max = 0;
            for (int j = 0; j < t.getRowCount(); j++) {
                Object value = t.getValueAt(j, i);
                String tmp = "";
                if (value instanceof String) {
                    tmp = (String) value;
                }
                if (value instanceof Integer) {
                    tmp = "" + (Integer) value;
                }
                int taille = fm.stringWidth(tmp);
                if (taille > max) {
                    max = taille;
                }
            }
            String nom = (String) t.getColumnModel().getColumn(i).getIdentifier();
            int taille = fm.stringWidth(nom);
            if (taille > max) {
                max = taille;
            }
            t.getColumnModel().getColumn(i).setPreferredWidth(max + 10);
        }
    }

    private Round IndivAffectation(Vector<Coach> coachs, Vector<Match> matchs, Vector<ObjectRanking> datas, boolean team, Round r) {
        Calendar cal = Calendar.getInstance();
        r.setHeure(cal.getTime());
        // Résolution des doublons

        // Build ranking without inactive coachs
        Vector<ObjectRanking> datas_tmp = new Vector<ObjectRanking>(datas);
        for (int i = 0; i < coachs.size(); i++) {
            Coach c = coachs.get(i);
            if (!c._active) {
                for (int j = 0; j < datas_tmp.size(); j++) {
                    if (datas_tmp.get(j).getObject().equals(c)) {
                        datas_tmp.remove(j);
                    }
                }
            }
        }

        if (Tournament.getTournament().GetActiveCoaches().size() - 1 <= _tournament.getRounds().size()) {
            // Affectation des matchs
            for (int i = 0; i < datas_tmp.size() / 2; i++) {
                Match m = new Match();
                m._coach1 = (Coach) datas_tmp.get(2 * i).getObject();
                m._coach2 = (Coach) datas_tmp.get(2 * i + 1).getObject();
                r.getMatchs().add(m);
            }
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NotEnoughRoundToAvoidSameMatch"));
        } else {
            Vector<ObjectRanking> datas2 = new Vector<ObjectRanking>(datas_tmp);
            // Première passe de haut en bas
            while (datas2.size() > 0) {
                Match m = new Match();
                m._coach1 = (Coach) datas2.get(0).getObject();
                datas2.remove(0);
                for (int i = 0; i < datas2.size(); i++) {
                    boolean have_played = false;
                    for (int j = 0; j < matchs.size(); j++) {
                        if (((matchs.get(j)._coach1 == m._coach1) && (matchs.get(j)._coach2 == datas2.get(i).getObject()))
                                || ((matchs.get(j)._coach2 == m._coach1) && (matchs.get(j)._coach1 == datas2.get(i).getObject()))) {
                            have_played = true;
                        }
                    }

                    if ((team) && (!have_played)) {
                        for (int j = 0; j < m._coach1._teamMates._coachs.size(); j++) {
                            if (m._coach1._teamMates._coachs.get(j) == datas2.get(i).getObject()) {
                                have_played = true;
                            }
                        }
                    }

                    boolean canMatch = !have_played;

                    if ((_tournament.getParams()._enableClans) && (_tournament.getParams()._avoidClansMatch)) {
                        if (!m._coach1._clan._name.equals("Aucun")) {
                            if (m._coach1._clan == ((Coach) datas2.get(i).getObject())._clan) {
                                canMatch = false;
                            }
                        }
                    }

                    if ((canMatch) || (i == datas2.size() - 1)) {
                        m._coach2 = (Coach) datas2.get(i).getObject();
                        datas2.remove(i);
                        break;
                    }
                }
                r.getMatchs().add(m);
            }

            datas2 = new Vector<ObjectRanking>(datas_tmp);
            // Seconde passe de bas en haut
            for (int i = r.getMatchs().size() - 1; i > 0; i--) {
                boolean have_played = false;
                Coach c1 = r.getMatchs().get(i)._coach1;
                Coach c2 = r.getMatchs().get(i)._coach2;
                for (int j = 0; j < matchs.size(); j++) {
                    if (((matchs.get(j)._coach1 == c1) && (matchs.get(j)._coach2 == c2))
                            || ((matchs.get(j)._coach1 == c2) && (matchs.get(j)._coach2 == c1))) {
                        have_played = true;
                    }
                }

                if (have_played) {
                    // Le but est déchanger C1 avec le premier joueur non encore rencontré
                    for (int k = i - 1; k >= 0; k--) {

                        // Test du c2 des matchs pécédents
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;

                            if (((matchs.get(j)._coach1 == c2) && (matchs.get(j)._coach2 == r.getMatchs().get(k)._coach2))
                                    || ((matchs.get(j)._coach2 == c2) && (matchs.get(j)._coach1 == r.getMatchs().get(k)._coach2))) {
                                have_played = true;
                                break;
                            }
                        }

                        boolean canMatch = !have_played;
                        if ((_tournament.getParams()._enableClans) && (_tournament.getParams()._avoidClansMatch)) {
                            if (!r.getMatchs().get(i)._coach2._clan._name.equals("Aucun")) {
                                if (r.getMatchs().get(i)._coach2._clan == r.getMatchs().get(k)._coach2._clan) {
                                    canMatch = false;
                                }
                            }
                        }

                        if (canMatch) {
                            r.getMatchs().get(i)._coach1 = r.getMatchs().get(k)._coach2;
                            r.getMatchs().get(k)._coach2 = c1;
                            break;
                        }

                        // Test du c1 des matchs pécédents
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;

                            if (((matchs.get(j)._coach1 == c2) && (matchs.get(j)._coach2 == r.getMatchs().get(k)._coach1))
                                    || ((matchs.get(j)._coach2 == c2) && (matchs.get(j)._coach1 == r.getMatchs().get(k)._coach1))) {
                                have_played = true;
                                break;
                            }
                        }

                        canMatch = !have_played;

                        if ((_tournament.getParams()._enableClans) && (_tournament.getParams()._avoidClansMatch)) {
                            if (!r.getMatchs().get(i)._coach1._clan._name.equals("Aucun")) {
                                if (r.getMatchs().get(i)._coach2._clan == r.getMatchs().get(k)._coach1._clan) {
                                    canMatch = false;
                                }
                            }
                        }

                        if (canMatch) {
                            r.getMatchs().get(i)._coach1 = r.getMatchs().get(k)._coach1;
                            r.getMatchs().get(k)._coach1 = c1;
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
        return r;
    }

    private Round IndivQuickSwissAffectation(Vector<Coach> coachs, Vector<Match> matchs, Vector<ObjectRanking> datas, boolean team, Round r) {

        Calendar cal = Calendar.getInstance();
        r.setHeure(cal.getTime());
        // Résolution des doublons

        // Build ranking without inactive coachs
        Vector<ObjectRanking> datas_tmp = new Vector<ObjectRanking>(datas);
        for (int i = 0; i < coachs.size(); i++) {
            Coach c = coachs.get(i);
            if (!c._active) {
                for (int j = 0; j < datas_tmp.size(); j++) {
                    if (datas_tmp.get(j).getObject().equals(c)) {
                        datas_tmp.remove(j);
                    }
                }
            }
        }

        // part Ranking in 4 groups
        int idx1, idx2, idx3, idx4;
        int size = datas.size();
        if (size < 4) {
            JOptionPane.showMessageDialog(this, "La méthode Suisse accelérée n'est pas applicable");
            return null;
        }
        int half_size = size / 2;
        if (half_size % 2 == 1) {
            half_size = half_size - 1;
        }

        idx1 = 0;
        idx2 = half_size / 2;
        idx3 = half_size;
        idx4 = (size - half_size) / 2 + half_size;

        Vector<ObjectRanking> datas_1 = new Vector<ObjectRanking>();
        Vector<ObjectRanking> datas_2 = new Vector<ObjectRanking>();
        Vector<ObjectRanking> datas_3 = new Vector<ObjectRanking>();
        Vector<ObjectRanking> datas_4 = new Vector<ObjectRanking>();

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


        if (Tournament.getTournament().GetActiveCoaches().size() - 1 <= _tournament.getRounds().size()) {
            // Affectation des matchs
            for (int i = 0; i < datas_1.size(); i++) {
                Match m = new Match();
                m._coach1 = (Coach) datas_1.get(i).getObject();
                m._coach2 = (Coach) datas_2.get(i).getObject();
                r.getMatchs().add(m);
            }
            for (int i = 0; i < datas_3.size(); i++) {
                Match m = new Match();
                m._coach1 = (Coach) datas_3.get(i).getObject();
                m._coach2 = (Coach) datas_4.get(i).getObject();
                r.getMatchs().add(m);
            }
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NotEnoughRoundToAvoidSameMatch"));
        } else {
            Vector<ObjectRanking> datas_tmp1 = new Vector<ObjectRanking>(datas_1);
            Vector<ObjectRanking> datas_tmp2 = new Vector<ObjectRanking>(datas_2);
            // Première passe de haut en bas
            while ((datas_tmp1.size() > 0) && (datas_tmp2.size() > 0)) {
                Match m = new Match();
                m._coach1 = (Coach) datas_tmp1.get(0).getObject();
                datas_tmp1.remove(0);
                for (int i = 0; i < datas_tmp2.size(); i++) {
                    boolean have_played = false;
                    for (int j = 0; j < matchs.size(); j++) {
                        if (((matchs.get(j)._coach1 == m._coach1) && (matchs.get(j)._coach2 == datas_tmp2.get(i).getObject()))
                                || ((matchs.get(j)._coach2 == m._coach1) && (matchs.get(j)._coach1 == datas_tmp2.get(i).getObject()))) {
                            have_played = true;
                        }
                    }

                    if ((team) && (!have_played)) {
                        for (int j = 0; j < m._coach1._teamMates._coachs.size(); j++) {
                            if (m._coach1._teamMates._coachs.get(j) == datas_tmp2.get(i).getObject()) {
                                have_played = true;
                            }
                        }
                    }

                    boolean canMatch = !have_played;

                    if ((_tournament.getParams()._enableClans) && (_tournament.getParams()._avoidClansMatch)) {
                        if (!m._coach1._clan._name.equals("Aucun")) {
                            if (m._coach1._clan == ((Coach) datas_tmp2.get(i).getObject())._clan) {
                                canMatch = false;
                            }
                        }
                    }

                    if ((canMatch) || (i == datas_tmp2.size() - 1)) {
                        m._coach2 = (Coach) datas_tmp2.get(i).getObject();
                        datas_tmp2.remove(i);
                        break;
                    }
                }
                r.getMatchs().add(m);
            }

            Vector<ObjectRanking> datas_tmp3 = new Vector<ObjectRanking>(datas_3);
            Vector<ObjectRanking> datas_tmp4 = new Vector<ObjectRanking>(datas_4);
            // Première passe de haut en bas
            while ((datas_tmp3.size() > 0) && (datas_tmp4.size() > 0)) {
                Match m = new Match();
                m._coach1 = (Coach) datas_tmp3.get(0).getObject();
                datas_tmp3.remove(0);
                for (int i = 0; i < datas_tmp4.size(); i++) {
                    boolean have_played = false;
                    for (int j = 0; j < matchs.size(); j++) {
                        if (((matchs.get(j)._coach1 == m._coach1) && (matchs.get(j)._coach2 == datas_tmp4.get(i).getObject()))
                                || ((matchs.get(j)._coach2 == m._coach1) && (matchs.get(j)._coach1 == datas_tmp4.get(i).getObject()))) {
                            have_played = true;
                        }
                    }

                    if ((team) && (!have_played)) {
                        for (int j = 0; j < m._coach1._teamMates._coachs.size(); j++) {
                            if (m._coach1._teamMates._coachs.get(j) == datas_tmp4.get(i).getObject()) {
                                have_played = true;
                            }
                        }
                    }

                    boolean canMatch = !have_played;

                    if ((_tournament.getParams()._enableClans) && (_tournament.getParams()._avoidClansMatch)) {
                        if (!m._coach1._clan._name.equals("Aucun")) {
                            if (m._coach1._clan == ((Coach) datas_tmp4.get(i).getObject())._clan) {
                                canMatch = false;
                            }
                        }
                    }

                    if ((canMatch) || (i == datas_tmp4.size() - 1)) {
                        m._coach2 = (Coach) datas_tmp4.get(i).getObject();
                        datas_tmp4.remove(i);
                        break;
                    }
                }
                r.getMatchs().add(m);
            }

            Vector<ObjectRanking> datas2 = new Vector<ObjectRanking>(datas_tmp);
            // Seconde passe de bas en haut
            for (int i = r.getMatchs().size() - 1; i > 0; i--) {
                boolean have_played = false;
                Coach c1 = r.getMatchs().get(i)._coach1;
                Coach c2 = r.getMatchs().get(i)._coach2;
                for (int j = 0; j < matchs.size(); j++) {
                    if (((matchs.get(j)._coach1 == c1) && (matchs.get(j)._coach2 == c2))
                            || ((matchs.get(j)._coach1 == c2) && (matchs.get(j)._coach2 == c1))) {
                        have_played = true;
                    }
                }

                if (have_played) {
                    // Le but est déchanger C1 avec le premier joueur non encore rencontré
                    for (int k = i - 1; k >= 0; k--) {

                        // Test du c2 des matchs pécédents
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;

                            if (((matchs.get(j)._coach1 == c2) && (matchs.get(j)._coach2 == r.getMatchs().get(k)._coach2))
                                    || ((matchs.get(j)._coach2 == c2) && (matchs.get(j)._coach1 == r.getMatchs().get(k)._coach2))) {
                                have_played = true;
                                break;
                            }
                        }

                        boolean canMatch = !have_played;
                        if ((_tournament.getParams()._enableClans) && (_tournament.getParams()._avoidClansMatch)) {
                            if (!r.getMatchs().get(i)._coach2._clan._name.equals("Aucun")) {
                                if (r.getMatchs().get(i)._coach2._clan == r.getMatchs().get(k)._coach2._clan) {
                                    canMatch = false;
                                }
                            }
                        }

                        if (canMatch) {
                            r.getMatchs().get(i)._coach1 = r.getMatchs().get(k)._coach2;
                            r.getMatchs().get(k)._coach2 = c1;
                            break;
                        }

                        // Test du c1 des matchs pécédents
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;

                            if (((matchs.get(j)._coach1 == c2) && (matchs.get(j)._coach2 == r.getMatchs().get(k)._coach1))
                                    || ((matchs.get(j)._coach2 == c2) && (matchs.get(j)._coach1 == r.getMatchs().get(k)._coach1))) {
                                have_played = true;
                                break;
                            }
                        }

                        canMatch = !have_played;

                        if ((_tournament.getParams()._enableClans) && (_tournament.getParams()._avoidClansMatch)) {
                            if (!r.getMatchs().get(i)._coach1._clan._name.equals("Aucun")) {
                                if (r.getMatchs().get(i)._coach2._clan == r.getMatchs().get(k)._coach1._clan) {
                                    canMatch = false;
                                }
                            }
                        }

                        if (canMatch) {
                            r.getMatchs().get(i)._coach1 = r.getMatchs().get(k)._coach1;
                            r.getMatchs().get(k)._coach1 = c1;
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
        return r;

    }

    private Round IndivRandomAffectation(Vector<Coach> coachs, Vector<Match> matchs, boolean team, Round r) {
        Calendar cal = Calendar.getInstance();
        r.setHeure(cal.getTime());
        // Résolution des doublons
        Vector<Coach> shuffle = new Vector<Coach>(coachs);

        boolean bOK = false;
        int count = 0;
        while (!bOK) {
            r.getMatchs().clear();
            Collections.shuffle(shuffle);
            count++;
            int i = 0;
            for (i = 0; i < coachs.size(); i++) {
                if (!coachs.get(i)._active) {
                    shuffle.remove(coachs.get(i));
                }
            }

            if (count > 100000) {
                // Affectation des matchs           
                JOptionPane.showMessageDialog(this, "Le moteur aléatoire n'est pas parvenu à éviter les doubles rencontres");

                for (i = 0; i < shuffle.size() / 2; i++) {
                    Match m = new Match();
                    m._coach1 = shuffle.get(2 * i);
                    m._coach2 = shuffle.get(2 * i + 1);
                    r.getMatchs().add(m);
                }

                bOK = true;
                return r;
            }


            if (Tournament.getTournament().GetActiveCoaches().size() - 1 <= _tournament.getRounds().size()) {
                // Affectation des matchs           
                JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NotEnoughRoundToAvoidSameMatch"));

                for (i = 0; i < shuffle.size() / 2; i++) {
                    Match m = new Match();
                    m._coach1 = shuffle.get(2 * i);
                    m._coach2 = shuffle.get(2 * i + 1);
                    r.getMatchs().add(m);
                }

                bOK = true;
                return r;
            } else {

                Vector<Coach> data_tmp = new Vector<Coach>(shuffle);
                bOK = true;
                while (data_tmp.size() > 0) {
                    Match m = new Match();
                    m._coach1 = data_tmp.get(0);
                    m._coach2 = null;
                    data_tmp.remove(m._coach1);
                    for (i = 0; i < data_tmp.size(); i++) {
                        Coach candidate = data_tmp.get(i);

                        boolean have_played = false;
                        for (int j = 0; j < candidate._matchs.size(); j++) {
                            if (((candidate._matchs.get(j)._coach1 == m._coach1) && (candidate._matchs.get(j)._coach2 == candidate))
                                    || ((candidate._matchs.get(j)._coach2 == m._coach1) && (candidate._matchs.get(j)._coach1 == candidate))) {
                                have_played = true;
                                break;
                            }
                        }
                        if (!have_played) {
                            m._coach2 = candidate;
                            data_tmp.remove(candidate);
                            r.getMatchs().add(m);
                            break;
                        }
                    }
                    if (m._coach2 == null) {
                        bOK = false;
                        break;
                    }
                }

            }
        }



        return r;

    }

    private Vector<ObjectRanking> subRanking(Vector<Coach> coachs, Vector<Match> matchs, Vector<Round> rounds) {
        Vector<ObjectRanking> result = new Vector<ObjectRanking>();
        for (int j = 0; j < coachs.size(); j++) {
            Coach c = coachs.get(j);
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int k = 0; k < c._matchs.size(); k++) {
                Match m = c._matchs.get(k);
                value1 += mjtRankingIndiv.getValue(c, m, _tournament.getParams()._ranking1);
                value2 += mjtRankingIndiv.getValue(c, m, _tournament.getParams()._ranking2);
                value3 += mjtRankingIndiv.getValue(c, m, _tournament.getParams()._ranking3);
                value4 += mjtRankingIndiv.getValue(c, m, _tournament.getParams()._ranking4);
                value5 += mjtRankingIndiv.getValue(c, m, _tournament.getParams()._ranking5);
            }
            ObjectRanking obj = new ObjectRanking(c, value1, value2, value3, value4, value5);
            result.add(obj);
        }
        Collections.sort(result);
        return result;
    }

    private Round TeamAffectation(Vector<Team> teams, Vector<Match> matchs, Vector<ObjectRanking> datas, boolean free_pairing, Vector<Round> rounds, Round r) {
        Calendar cal = Calendar.getInstance();
        r.setHeure(cal.getTime());
        // Résolution des doublons
        if (teams.size() - 1 <= _tournament.getRounds().size()) {
            // Affectation des matchs
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NotEnoughRoundToAvoidSameMatch"));
            for (int i = 0; i < datas.size() / 2; i++) {
                Team team1 = (Team) datas.get(2 * i).getObject();
                Team team2 = (Team) datas.get(2 * i + 1).getObject();
                if (!free_pairing) {
                    Vector<ObjectRanking> coachs1 = subRanking(team1._coachs, matchs, rounds);
                    Vector<ObjectRanking> coachs2 = subRanking(team2._coachs, matchs, rounds);
                    for (int j = 0; j < coachs1.size(); j++) {
                        Match m = new Match();
                        m._coach1 = (Coach) coachs1.get(j).getObject();
                        m._coach2 = (Coach) coachs2.get(j).getObject();
                        r.getMatchs().add(m);
                    }
                } else {
                    jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                    jdg.setVisible(true);
                }
            }
        } else {
            Vector<ObjectRanking> datas2 = new Vector<ObjectRanking>(datas);
            // Première passe de haut en bas
            Vector<Team> teams1 = new Vector<Team>();
            Vector<Team> teams2 = new Vector<Team>();
            while (datas2.size() > 0) {
                Team team1 = (Team) datas2.get(0).getObject();
                Team team2 = null;
                datas2.remove(0);

                for (int i = 0; i < datas2.size(); i++) {
                    boolean have_played = false;
                    for (int j = 0; j < matchs.size(); j++) {
                        if (((matchs.get(j)._coach1._teamMates == team1) && (matchs.get(j)._coach2._teamMates == datas2.get(i).getObject()))
                                || ((matchs.get(j)._coach2._teamMates == team1) && (matchs.get(j)._coach1._teamMates == datas2.get(i).getObject()))) {
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

            datas2 = new Vector<ObjectRanking>(datas);

            // Seconde passe de bas en haut

            for (int i = teams1.size() - 1; i > 0; i--) {
                boolean have_played = false;
                Team team1 = teams1.get(i);
                Team team2 = teams2.get(i);

                for (int j = 0; j < matchs.size(); j++) {
                    if (((matchs.get(j)._coach1._teamMates == team1) && (matchs.get(j)._coach2._teamMates == team2))
                            || ((matchs.get(j)._coach1._teamMates == team2) && (matchs.get(j)._coach2._teamMates == team1))) {
                        have_played = true;
                    }
                }
                if (have_played) {
                    // Le but est déchanger C1 avec la première équipe non encore rencontrée
                    for (int k = i - 1; k >= 0; k--) {

                        // Test du c2 des matchs pécédents
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;

                            if (((matchs.get(j)._coach1._teamMates == team2) && (matchs.get(j)._coach2._teamMates == teams2.get(k)))
                                    || ((matchs.get(j)._coach2._teamMates == team2) && (matchs.get(j)._coach1._teamMates == teams2.get(k)))) {
                                have_played = true;
                                break;
                            }
                        }

                        if (!have_played) {
                            teams1.remove(i);
                            teams1.insertElementAt(teams2.get(k), i);
                            teams2.remove(k);
                            teams2.insertElementAt(team1, k);
                            break;
                        }

                        // Test du c1 des matchs pécédents
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;

                            if (((matchs.get(j)._coach1._teamMates == team2) && (matchs.get(j)._coach2._teamMates == teams1.get(k)))
                                    || ((matchs.get(j)._coach2._teamMates == team2) && (matchs.get(j)._coach1._teamMates == teams1.get(k)))) {
                                have_played = true;
                                break;
                            }
                        }

                        if (!have_played) {
                            teams1.remove(i);
                            teams1.insertElementAt(teams1.get(k), i);
                            teams1.remove(k);
                            teams1.insertElementAt(team1, k);
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
                jdgTeamPairing jdg = new jdgTeamPairing(MainFrame.getMainFrame(), true, teams1, teams2, r);
                jdg.setVisible(true);
            } else {
                for (int i = 0; i < teams1.size(); i++) {
                    Team team1 = teams1.get(i);
                    Team team2 = teams2.get(i);

                    Vector<ObjectRanking> coachs1 = subRanking(team1._coachs, matchs, rounds);
                    Vector<ObjectRanking> coachs2 = subRanking(team2._coachs, matchs, rounds);
                    for (int j = 0; j < coachs1.size(); j++) {
                        Match m = new Match();
                        m._coach1 = (Coach) coachs1.get(j).getObject();
                        m._coach2 = (Coach) coachs2.get(j).getObject();
                        r.getMatchs().add(m);
                    }
                }
            }
        }
        return r;
    }

    private Round TeamQuickSwissAffectation(Vector<Team> teams, Vector<Match> matchs, Vector<ObjectRanking> datas, boolean free_pairing, Vector<Round> rounds, Round r) {
        Calendar cal = Calendar.getInstance();
        r.setHeure(cal.getTime());

        // part Ranking in 4 groups
        int idx1, idx2, idx3, idx4;
        int size = datas.size();
        if (size < 4) {
            JOptionPane.showMessageDialog(this, "La méthode Suisse accelérée n'est pas applicable");
            return null;
        }
        int half_size = size / 2;
        if (half_size % 2 == 1) {
            half_size = half_size - 1;
        }

        idx1 = 0;
        idx2 = half_size / 2;
        idx3 = half_size;
        idx4 = (size - half_size) / 2 + half_size;

        Vector<ObjectRanking> datas_1 = new Vector<ObjectRanking>();
        Vector<ObjectRanking> datas_2 = new Vector<ObjectRanking>();
        Vector<ObjectRanking> datas_3 = new Vector<ObjectRanking>();
        Vector<ObjectRanking> datas_4 = new Vector<ObjectRanking>();

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

        // Résolution des doublons
        if (teams.size() - 1 <= _tournament.getRounds().size()) {

            // Affectation des matchs
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NotEnoughRoundToAvoidSameMatch"));

            for (int i = 0; i < datas_3.size(); i++) {
                Team team1 = (Team) datas_3.get(i).getObject();
                Team team2 = (Team) datas_4.get(i).getObject();
                if (!free_pairing) {
                    Vector<ObjectRanking> coachs1 = subRanking(team1._coachs, matchs, rounds);
                    Vector<ObjectRanking> coachs2 = subRanking(team2._coachs, matchs, rounds);
                    for (int j = 0; j < coachs1.size(); j++) {
                        Match m = new Match();
                        m._coach1 = (Coach) coachs1.get(j).getObject();
                        m._coach2 = (Coach) coachs2.get(j).getObject();
                        r.getMatchs().add(m);
                    }
                } else {
                    jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                    jdg.setVisible(true);
                }
            }

            for (int i = 0; i < datas_1.size(); i++) {
                Team team1 = (Team) datas_1.get(i).getObject();
                Team team2 = (Team) datas_2.get(i).getObject();
                if (!free_pairing) {
                    Vector<ObjectRanking> coachs1 = subRanking(team1._coachs, matchs, rounds);
                    Vector<ObjectRanking> coachs2 = subRanking(team2._coachs, matchs, rounds);
                    for (int j = 0; j < coachs1.size(); j++) {
                        Match m = new Match();
                        m._coach1 = (Coach) coachs1.get(j).getObject();
                        m._coach2 = (Coach) coachs2.get(j).getObject();
                        r.getMatchs().add(m);
                    }
                } else {
                    jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                    jdg.setVisible(true);
                }
            }

        } else {
            Vector<ObjectRanking> datas_tmp1 = new Vector<ObjectRanking>(datas_1);
            Vector<ObjectRanking> datas_tmp2 = new Vector<ObjectRanking>(datas_2);
            // Première passe de haut en bas
            Vector<Team> teams1 = new Vector<Team>();
            Vector<Team> teams2 = new Vector<Team>();
            while ((datas_tmp2.size() > 0) && (datas_tmp1.size() > 0)) {
                Team team1 = (Team) datas_tmp1.get(0).getObject();
                Team team2 = null;
                datas_tmp1.remove(0);

                for (int i = 0; i < datas_tmp2.size(); i++) {
                    boolean have_played = false;
                    for (int j = 0; j < matchs.size(); j++) {
                        if (((matchs.get(j)._coach1._teamMates == team1) && (matchs.get(j)._coach2._teamMates == datas_tmp2.get(i).getObject()))
                                || ((matchs.get(j)._coach2._teamMates == team1) && (matchs.get(j)._coach1._teamMates == datas_tmp2.get(i).getObject()))) {
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

            Vector<ObjectRanking> datas_tmp3 = new Vector<ObjectRanking>(datas_3);
            Vector<ObjectRanking> datas_tmp4 = new Vector<ObjectRanking>(datas_4);
            while ((datas_tmp4.size() > 0) && (datas_tmp3.size() > 0)) {
                Team team1 = (Team) datas_tmp3.get(0).getObject();
                Team team2 = null;
                datas_tmp3.remove(0);

                for (int i = 0; i < datas_tmp4.size(); i++) {
                    boolean have_played = false;
                    for (int j = 0; j < matchs.size(); j++) {
                        if (((matchs.get(j)._coach1._teamMates == team1) && (matchs.get(j)._coach2._teamMates == datas_tmp4.get(i).getObject()))
                                || ((matchs.get(j)._coach2._teamMates == team1) && (matchs.get(j)._coach1._teamMates == datas_tmp4.get(i).getObject()))) {
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

            Vector<ObjectRanking> datas2 = new Vector<ObjectRanking>(datas);

            // Seconde passe de bas en haut

            for (int i = teams1.size() - 1; i > 0; i--) {
                boolean have_played = false;
                Team team1 = teams1.get(i);
                Team team2 = teams2.get(i);

                for (int j = 0; j < matchs.size(); j++) {
                    if (((matchs.get(j)._coach1._teamMates == team1) && (matchs.get(j)._coach2._teamMates == team2))
                            || ((matchs.get(j)._coach1._teamMates == team2) && (matchs.get(j)._coach2._teamMates == team1))) {
                        have_played = true;
                    }
                }
                if (have_played) {
                    // Le but est déchanger C1 avec la première équipe non encore rencontrée
                    for (int k = i - 1; k >= 0; k--) {

                        // Test du c2 des matchs pécédents
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;

                            if (((matchs.get(j)._coach1._teamMates == team2) && (matchs.get(j)._coach2._teamMates == teams2.get(k)))
                                    || ((matchs.get(j)._coach2._teamMates == team2) && (matchs.get(j)._coach1._teamMates == teams2.get(k)))) {
                                have_played = true;
                                break;
                            }
                        }

                        if (!have_played) {
                            teams1.remove(i);
                            teams1.insertElementAt(teams2.get(k), i);
                            teams2.remove(k);
                            teams2.insertElementAt(team1, k);
                            break;
                        }

                        // Test du c1 des matchs pécédents
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;

                            if (((matchs.get(j)._coach1._teamMates == team2) && (matchs.get(j)._coach2._teamMates == teams1.get(k)))
                                    || ((matchs.get(j)._coach2._teamMates == team2) && (matchs.get(j)._coach1._teamMates == teams1.get(k)))) {
                                have_played = true;
                                break;
                            }
                        }

                        if (!have_played) {
                            teams1.remove(i);
                            teams1.insertElementAt(teams1.get(k), i);
                            teams1.remove(k);
                            teams1.insertElementAt(team1, k);
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
                jdgTeamPairing jdg = new jdgTeamPairing(MainFrame.getMainFrame(), true, teams1, teams2, r);
                jdg.setVisible(true);
            } else {
                for (int i = 0; i < teams1.size(); i++) {
                    Team team1 = teams1.get(i);
                    Team team2 = teams2.get(i);

                    Vector<ObjectRanking> coachs1 = subRanking(team1._coachs, matchs, rounds);
                    Vector<ObjectRanking> coachs2 = subRanking(team2._coachs, matchs, rounds);
                    for (int j = 0; j < coachs1.size(); j++) {
                        Match m = new Match();
                        m._coach1 = (Coach) coachs1.get(j).getObject();
                        m._coach2 = (Coach) coachs2.get(j).getObject();
                        r.getMatchs().add(m);
                    }
                }
            }
        }
        return r;
    }

    private Round TeamRandomAffectation(Vector<Team> teams, Vector<Match> matchs, boolean random, boolean free_pairing, Vector<Round> rounds, Round r) {
        Calendar cal = Calendar.getInstance();
        r.setHeure(cal.getTime());
        Vector<Team> datas = new Vector<Team>(teams);

        boolean bOK = false;
        int count = 0;
        while (!bOK) {
            count++;
            Collections.shuffle(datas);

            // Résolution des doublons
            if (count > 100000) {
                // Affectation des matchs
                JOptionPane.showMessageDialog(this, "Le moteur aléatoire n'est pas parvenu à éviter les doubles rencontres");
                for (int i = 0; i < datas.size() / 2; i++) {
                    Team team1 = datas.get(2 * i);
                    Team team2 = datas.get(2 * i + 1);
                    if (!free_pairing) {
                        if (!random) {
                            Vector<ObjectRanking> coachs1 = subRanking(team1._coachs, matchs, rounds);
                            Vector<ObjectRanking> coachs2 = subRanking(team2._coachs, matchs, rounds);
                            for (int j = 0; j < coachs1.size(); j++) {
                                Match m = new Match();
                                m._coach1 = (Coach) coachs1.get(j).getObject();
                                m._coach2 = (Coach) coachs2.get(j).getObject();
                                r.getMatchs().add(m);
                            }
                        } else {
                            Vector<Coach> coachs1 = new Vector<Coach>(team1._coachs);
                            Collections.shuffle(coachs1);
                            Vector<Coach> coachs2 = new Vector<Coach>(team2._coachs);
                            Collections.shuffle(coachs2);
                            for (int j = 0; j < coachs1.size(); j++) {
                                Match m = new Match();
                                m._coach1 = (Coach) coachs1.get(j);
                                m._coach2 = (Coach) coachs2.get(j);
                                r.getMatchs().add(m);
                            }
                        }
                    } else {
                        jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                        jdg.setVisible(true);
                    }
                }

                bOK = true;
            }

            // Résolution des doublons
            if (_tournament.getTeams().size() - 1 <= _tournament.getRounds().size()) {
                // Affectation des matchs
                JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NotEnoughRoundToAvoidSameMatch"));
                for (int i = 0; i < datas.size() / 2; i++) {
                    Team team1 = datas.get(2 * i);
                    Team team2 = datas.get(2 * i + 1);
                    if (!free_pairing) {
                        if (!random) {
                            Vector<ObjectRanking> coachs1 = subRanking(team1._coachs, matchs, rounds);
                            Vector<ObjectRanking> coachs2 = subRanking(team2._coachs, matchs, rounds);
                            for (int j = 0; j < coachs1.size(); j++) {
                                Match m = new Match();
                                m._coach1 = (Coach) coachs1.get(j).getObject();
                                m._coach2 = (Coach) coachs2.get(j).getObject();
                                r.getMatchs().add(m);
                            }
                        } else {
                            Vector<Coach> coachs1 = new Vector<Coach>(team1._coachs);
                            Collections.shuffle(coachs1);
                            Vector<Coach> coachs2 = new Vector<Coach>(team2._coachs);
                            Collections.shuffle(coachs2);
                            for (int j = 0; j < coachs1.size(); j++) {
                                Match m = new Match();
                                m._coach1 = (Coach) coachs1.get(j);
                                m._coach2 = (Coach) coachs2.get(j);
                                r.getMatchs().add(m);
                            }
                        }
                    } else {
                        jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                        jdg.setVisible(true);
                    }
                }

                bOK = true;
            } else {
                bOK = true;

                Vector<Team> data_tmp = new Vector<Team>(datas);
                while (data_tmp.size() > 0) {
                    Team team1 = data_tmp.get(0);
                    data_tmp.remove(team1);
                    Team team2 = null;

                    for (int i = 0; i < data_tmp.size(); i++) {
                        boolean have_played = false;
                        for (int j = 0; j < matchs.size(); j++) {
                            if (((matchs.get(j)._coach1._teamMates == team1) && (matchs.get(j)._coach2._teamMates == data_tmp.get(i)))
                                    || ((matchs.get(j)._coach2._teamMates == team1) && (matchs.get(j)._coach1._teamMates == data_tmp.get(i)))) {
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
                                Vector<ObjectRanking> coachs1 = subRanking(team1._coachs, matchs, rounds);
                                Vector<ObjectRanking> coachs2 = subRanking(team2._coachs, matchs, rounds);
                                for (int j = 0; j < coachs1.size(); j++) {
                                    Match m = new Match();
                                    m._coach1 = (Coach) coachs1.get(j).getObject();
                                    m._coach2 = (Coach) coachs2.get(j).getObject();
                                    r.getMatchs().add(m);
                                }
                            } else {
                                Vector<Coach> coachs1 = new Vector<Coach>(team1._coachs);
                                Collections.shuffle(coachs1);
                                Vector<Coach> coachs2 = new Vector<Coach>(team2._coachs);
                                Collections.shuffle(coachs2);
                                for (int j = 0; j < coachs1.size(); j++) {
                                    Match m = new Match();
                                    m._coach1 = (Coach) coachs1.get(j);
                                    m._coach2 = (Coach) coachs2.get(j);
                                    r.getMatchs().add(m);
                                }
                            }
                        } else {
                            jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
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
    }

    private void jbtNextRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextRoundActionPerformed

        Vector<ObjectRanking> datas;
        // First: Create ranking
        // previous rounds
        Vector<Round> v = new Vector<Round>();
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_tournament.getRounds().get(i).getHeure().before(_round.getHeure())) {
                v.add(_tournament.getRounds().get(i));
            }
        }
        v.add(_round);

        Vector<Match> matchs = new Vector<Match>();
        for (int j = 0; j < v.size(); j++) {
            for (int k = 0; k < v.get(j).getMatchs().size(); k++) {
                matchs.add(v.get(j).getMatchs().get(k));
            }
        }



        Round r = new Round();
        if (_tournament.getPools().size() == 0) {
            datas = getSortedRankingData();
            if (_tournament.getParams()._teamTournament) {
                if (_tournament.getParams()._teamPairing == 0) {
                    IndivAffectation(_tournament.getCoachs(), matchs, datas, true, r);
                } else {
                    TeamAffectation(_tournament.getTeams(), matchs, datas, _tournament.getParams()._teamIndivPairing == 1, v, r);
                }
            } else {
                if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    IndivAffectation(_tournament.getCoachs(), matchs, datas, false, r);
                }
            }
        } else {
            for (int i = 0; i < _tournament.getPools().size(); i++) {
                datas = getSortedRankingData(_tournament.getPools().get(i));
                if (_tournament.getParams()._teamTournament) {
                    if (_tournament.getParams()._teamPairing == 0) {
                        IndivAffectation(_tournament.getPools().get(i)._coachs, matchs, datas, true, r);
                    } else {
                        TeamAffectation(_tournament.getPools().get(i)._teams, matchs, datas, _tournament.getParams()._teamIndivPairing == 1, v, r);
                    }
                } else {
                    if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                        JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else {
                        IndivAffectation(_tournament.getPools().get(i)._coachs, matchs, datas, false, r);
                    }
                }
            }
        }

        finalGenerationStep(r);
    }//GEN-LAST:event_jbtNextRoundActionPerformed

    private void jbtShowMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowMatchesActionPerformed
        for (int i = 0; i
                < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {

                jdgRound jdg = new jdgRound(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, false, false);
                jdg.setVisible(true);
                break;

            }
        }
    }//GEN-LAST:event_jbtShowMatchesActionPerformed

    private void jbtShowResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowResultsActionPerformed
        for (int i = 0; i
                < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRound jdg = new jdgRound(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, true, false);
                jdg.setVisible(true);
                break;

            }
        }
    }//GEN-LAST:event_jbtShowResultsActionPerformed

    private void jbtGeneralIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralIndivActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                mjtRankingIndiv model = new mjtRankingIndiv(_roundNumber, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getCoachs(), _tournament.getParams()._teamTournament, _round_only);
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, "General par Coach", i + 1, _tournament, model, 0);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtGeneralIndivActionPerformed

    private void jbtDeleteRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDeleteRoundActionPerformed

        if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ConfirmEraseCurrentRound"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EraseRound"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            // Remove _round
            _tournament.getRounds().remove(_round);
            for (int i = MainFrame.getMainFrame().jtpMain.getTabCount() - 1; i
                    >= 0; i--) {
                Component obj = MainFrame.getMainFrame().jtpMain.getComponentAt(i);
                if (obj instanceof JPNRound) {
                    MainFrame.getMainFrame().jtpMain.remove(obj);
                }
            }

            // Remove matchs from coach reference list
            for (int i = 0; i < _round.getMatchs().size(); i++) {
                Match m = _round.getMatchs().get(i);
                m._coach1._matchs.remove(m);
                m._coach2._matchs.remove(m);
            }

            _tournament.getRounds().remove(_round);

            for (int i = 0; i
                    < _tournament.getRounds().size(); i++) {
                JPNRound jpnr = new JPNRound(i, _tournament.getRounds().get(i), _tournament);
                MainFrame.getMainFrame().jtpMain.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Round") + " " + (i + 1), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Dice.png")), jpnr);
            }

            MainFrame.getMainFrame().update();
        }
    }//GEN-LAST:event_jbtDeleteRoundActionPerformed

    private void jtpGlobalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtpGlobalStateChanged
        update();
    }//GEN-LAST:event_jtpGlobalStateChanged

    private void jbtGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGlobalActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                mjtRankingIndiv model = new mjtRankingIndiv(_roundNumber, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getCoachs(), _tournament.getParams()._teamTournament, _round_only);
                HashMap<Criteria, mjtAnnexRank> annexForRankings = new HashMap<Criteria, mjtAnnexRank>();
                HashMap<Criteria, mjtAnnexRank> annexAgainstRankings = new HashMap<Criteria, mjtAnnexRank>();
                for (int j = 0; j < _tournament.getParams()._criterias.size(); j++) {
                    Criteria crit = _tournament.getParams()._criterias.get(j);
                    mjtAnnexRank annex = new mjtAnnexRankIndiv(i, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                            _tournament.getCoachs(), true,
                            _tournament.getParams()._ranking1, _tournament.getParams()._ranking2,
                            _tournament.getParams()._ranking3, _tournament.getParams()._ranking4,
                            _tournament.getParams()._ranking5, false, _round_only);
                    annexForRankings.put(crit, annex);
                    annex = new mjtAnnexRankIndiv(i, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                            _tournament.getCoachs(), true,
                            _tournament.getParams()._ranking1, _tournament.getParams()._ranking2,
                            _tournament.getParams()._ranking3, _tournament.getParams()._ranking4,
                            _tournament.getParams()._ranking5, false, _round_only);
                    annexAgainstRankings.put(crit, annex);
                }
                jdgGlobal jdg = new jdgGlobal(MainFrame.getMainFrame(), true, i + 1, _tournament, model, annexForRankings, annexAgainstRankings, false, false);
                jdg.setVisible(true);
                break;

            }
        }
    }//GEN-LAST:event_jbtGlobalActionPerformed

    private void jbtChangeMatchsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtChangeMatchsActionPerformed
        jdgChangePairing jdg = new jdgChangePairing(MainFrame.getMainFrame(), true, _round);
        jdg.setVisible(true);
        update();
    }//GEN-LAST:event_jbtChangeMatchsActionPerformed

    private void jbtAddMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddMatchActionPerformed

        Vector<Coach> Coachs1 = new Vector<Coach>();
        Vector<Coach> Coachs2 = new Vector<Coach>();

        JComboBox<String> jcb1 = new JComboBox<String>();
        JComboBox<String> jcb2 = new JComboBox<String>();

        for (int i = 0; i < _tournament.GetActiveCoaches().size(); i++) {
            Coach c = _tournament.GetActiveCoaches().get(i);
            Coachs1.add(c);
            Coachs2.add(c);
            jcb1.addItem(c._name);
            jcb2.addItem(c._name);
        }



        boolean ValidMatch = false;

        while (!ValidMatch) {
            jcb1.setSelectedIndex(0);
            jcb2.setSelectedIndex(1);

            JPanel jpnQuestion = new JPanel(new BorderLayout(0, 0));
            jpnQuestion.add(jcb1, BorderLayout.WEST);
            jpnQuestion.add(jcb2, BorderLayout.EAST);
            JLabel jlb = new JLabel(" vs ");
            jpnQuestion.add(jlb, BorderLayout.CENTER);

            int ret = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), jpnQuestion, "Match libre", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (ret == JOptionPane.OK_OPTION) {
                if (jcb1.getSelectedIndex() != jcb2.getSelectedIndex()) {
                    Match m = new Match();
                    m._coach1 = Coachs1.get(jcb1.getSelectedIndex());
                    m._coach2 = Coachs2.get(jcb2.getSelectedIndex());

                    _round.getMatchs().add(m);
                    ValidMatch = true;
                } else {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Match impossible", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                ValidMatch = true;
            }
        }


        update();
    }//GEN-LAST:event_jbtAddMatchActionPerformed

    private void jbtDelMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDelMatchActionPerformed

        if (jtbMatches.getSelectedRow() >= 0) {
            _round.getMatchs().remove(jtbMatches.getSelectedRow());
            update();
        }
    }//GEN-LAST:event_jbtDelMatchActionPerformed

    private void jtbRoundSumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbRoundSumActionPerformed
        _round_only = jtbRoundSum.isSelected();

        update();
    }//GEN-LAST:event_jtbRoundSumActionPerformed

    private void jbtNextRoundRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextRoundRandomActionPerformed

        Vector<Round> v = new Vector<Round>();
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_tournament.getRounds().get(i).getHeure().before(_round.getHeure())) {
                v.add(_tournament.getRounds().get(i));
            }
        }
        v.add(_round);

        Vector<Match> matchs = new Vector<Match>();
        for (int j = 0; j < v.size(); j++) {
            for (int k = 0; k < v.get(j).getMatchs().size(); k++) {
                matchs.add(v.get(j).getMatchs().get(k));
            }
        }

        Round r = new Round();
        r.getMatchs().removeAllElements();
        if (_tournament.getPools().size() == 0) {
            if (_tournament.getParams()._teamTournament) {
                if (_tournament.getParams()._teamPairing == 0) {
                    IndivRandomAffectation(_tournament.getCoachs(), matchs, true, r);
                } else {
                    TeamRandomAffectation(_tournament.getTeams(), matchs, true, _tournament.getParams()._teamIndivPairing == 1, v, r);
                }
            } else {
                if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    IndivRandomAffectation(_tournament.getCoachs(), matchs, false, r);
                }
            }
        } else {
            for (int i = 0; i < _tournament.getPools().size(); i++) {
                if (_tournament.getParams()._teamTournament) {
                    if (_tournament.getParams()._teamPairing == 0) {
                        IndivRandomAffectation(_tournament.getPools().get(i)._coachs, matchs, true, r);
                    } else {
                        TeamRandomAffectation(_tournament.getPools().get(i)._teams, matchs, true, _tournament.getParams()._teamIndivPairing == 1, v, r);
                    }
                } else {
                    if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                        JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else {
                        IndivRandomAffectation(_tournament.getPools().get(i)._coachs, matchs, false, r);
                    }
                }
            }
        }


        finalGenerationStep(r);
    }//GEN-LAST:event_jbtNextRoundRandomActionPerformed

    private void jbtNextRoundQuickSwissActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextRoundQuickSwissActionPerformed

        Vector<ObjectRanking> datas;
        // First: Create ranking
        // previous rounds
        Vector<Round> v = new Vector<Round>();
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_tournament.getRounds().get(i).getHeure().before(_round.getHeure())) {
                v.add(_tournament.getRounds().get(i));
            }
        }
        v.add(_round);

        Vector<Match> matchs = new Vector<Match>();
        for (int j = 0; j < v.size(); j++) {
            for (int k = 0; k < v.get(j).getMatchs().size(); k++) {
                matchs.add(v.get(j).getMatchs().get(k));
            }
        }

        Round r = new Round();
        if (_tournament.getPools().size() == 0) {

            datas = getSortedRankingData();
            if (_tournament.getParams()._teamTournament) {
                if (_tournament.getParams()._teamPairing == 0) {
                    IndivQuickSwissAffectation(_tournament.getCoachs(), matchs, datas, true, r);
                } else {
                    TeamQuickSwissAffectation(_tournament.getTeams(), matchs, datas, _tournament.getParams()._teamIndivPairing == 1, v, r);
                }
            } else {
                if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    IndivQuickSwissAffectation(_tournament.getCoachs(), matchs, datas, false, r);
                }
            }
        } else {
            for (int i = 0; i < _tournament.getPools().size(); i++) {
                datas = getSortedRankingData(_tournament.getPools().get(i));
                if (_tournament.getParams()._teamTournament) {
                    if (_tournament.getParams()._teamPairing == 0) {
                        IndivQuickSwissAffectation(_tournament.getPools().get(i)._coachs, matchs, datas, true, r);
                    } else {
                        TeamQuickSwissAffectation(_tournament.getPools().get(i)._teams, matchs, datas, _tournament.getParams()._teamIndivPairing == 1, v, r);
                    }
                } else {
                    if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                        JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else {
                        IndivQuickSwissAffectation(_tournament.getPools().get(i)._coachs, matchs, datas, false, r);
                    }
                }
            }
        }

        finalGenerationStep(r);

    }//GEN-LAST:event_jbtNextRoundQuickSwissActionPerformed

    private Vector<ObjectRanking> getSortedRankingData() {
        Vector<ObjectRanking> datas = null;
        if ((_tournament.getParams()._teamTournament) && (_tournament.getParams()._teamPairing != 0)) {
            mjtRankingTeam ranking = null;

            if (_tournament.getParams()._team_victory_only) {
                ranking = new mjtRankingTeam(true, _roundNumber,
                        _tournament.getParams()._ranking1_team,
                        _tournament.getParams()._ranking2_team,
                        _tournament.getParams()._ranking3_team,
                        _tournament.getParams()._ranking4_team,
                        _tournament.getParams()._ranking5_team,
                        _tournament.getTeams(), _round_only);
            } else {
                ranking = new mjtRankingTeam(false, _roundNumber,
                        _tournament.getParams()._ranking1,
                        _tournament.getParams()._ranking2,
                        _tournament.getParams()._ranking3,
                        _tournament.getParams()._ranking4,
                        _tournament.getParams()._ranking5,
                        _tournament.getTeams(), _round_only);
            }
            // Ranking class
            datas = ranking.getSortedDatas();
        } else {

            mjtRankingIndiv ranking = new mjtRankingIndiv(_roundNumber,
                    _tournament.getParams()._ranking1,
                    _tournament.getParams()._ranking2,
                    _tournament.getParams()._ranking3,
                    _tournament.getParams()._ranking4,
                    _tournament.getParams()._ranking5,
                    _tournament.getCoachs(), false, _round_only);

            // Ranking class
            datas = ranking.getSortedDatas();
        }
        return datas;
    }

    private Vector<ObjectRanking> getSortedRankingData(Pool p) {
        Vector<ObjectRanking> datas = null;

        if ((_tournament.getParams()._teamTournament) && (_tournament.getParams()._teamPairing != 0)) {
            mjtRankingTeam ranking = null;

            if (_tournament.getParams()._team_victory_only) {
                ranking = new mjtRankingTeam(true, _roundNumber,
                        _tournament.getParams()._ranking1_team,
                        _tournament.getParams()._ranking2_team,
                        _tournament.getParams()._ranking3_team,
                        _tournament.getParams()._ranking4_team,
                        _tournament.getParams()._ranking5_team,
                        p._teams,
                        _round_only);
            } else {
                ranking = new mjtRankingTeam(false, _roundNumber,
                        _tournament.getParams()._ranking1,
                        _tournament.getParams()._ranking2,
                        _tournament.getParams()._ranking3,
                        _tournament.getParams()._ranking4,
                        _tournament.getParams()._ranking5,
                        p._teams, _round_only);
            }
            // Ranking class
            datas = ranking.getSortedDatas();
        } else {

            mjtRankingIndiv ranking = new mjtRankingIndiv(_roundNumber,
                    _tournament.getParams()._ranking1,
                    _tournament.getParams()._ranking2,
                    _tournament.getParams()._ranking3,
                    _tournament.getParams()._ranking4,
                    _tournament.getParams()._ranking5,
                    p._coachs, false, _round_only);

            // Ranking class
            datas = ranking.getSortedDatas();
        }
        return datas;
    }

    protected void finalGenerationStep(Round r) {
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_tournament.getRounds().get(i).getHeure().after(_round.getHeure())) {
                _tournament.getRounds().remove(i);
                i--;
            }
        }

        _tournament.getRounds().add(r);

        // Add Matchs to Coach internal reference list
        for (int i = 0; i < r.getMatchs().size(); i++) {
            Match m = r.getMatchs().get(i);
            m._coach1._matchs.add(m);
            m._coach2._matchs.add(m);
        }

        for (int i = MainFrame.getMainFrame().jtpMain.getTabCount() - 1;
                i >= 0; i--) {
            Component obj = MainFrame.getMainFrame().jtpMain.getComponentAt(i);
            if (obj instanceof JPNRound) {
                MainFrame.getMainFrame().jtpMain.remove(obj);
            }
            if (obj instanceof JPNCup) {
                MainFrame.getMainFrame().jtpMain.remove(obj);
            }
        }

        boolean cup = false;

        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            JPNRound jpnr = new JPNRound(i, _tournament.getRounds().get(i), _tournament);
            if (_tournament.getRounds().get(i)._cup) {
                cup = true;
            }
//            MainFrame.getMainFrame().jtpMain.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RONDE ") + (i + 1), jpnr);
            MainFrame.getMainFrame().jtpMain.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Round") + " " + (i + 1), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Dice.png")), jpnr);
        }

        if (cup) {
            JPNCup jpncup = new JPNCup();
            MainFrame.getMainFrame().jtpMain.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Cup"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Star.png")), jpncup);
        }
        MainFrame.getMainFrame().jtpMain.setSelectedIndex(_tournament.getRounds().size());

        String filename = Tournament.getTournament().getParams()._tournament_name;
        filename = filename + "." + Tournament.getTournament().getRounds().size();
        Date date = new Date();
        filename = filename + "." + Long.toString(date.getTime()) + ".xml";

        File file = new File(filename);
        Tournament.getTournament().saveXML(file);

        update();
    }

    protected void IndivCupAffectation(Round r, boolean third_place) {
        int nb_match = 0;

        Vector<Match> matchs = new Vector<Match>(_round.getMatchs());

        nb_match = (int) Math.pow(2, _round._cup_max_tour - _round._cup_tour - 1);

        /* if ((_round._cup_max_tour == _round._cup_tour) && (_round._looser_cup)) {
         nb_match = 2;
         }*/

        // Récupérer les gagnants/perdants
        Vector<Coach> _winners = new Vector<Coach>();
        Vector<Coach> _loosers = new Vector<Coach>();

        if (nb_match == 0) {
            if (_round._cup_tour == _round._cup_max_tour + 1) {
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
            int option = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Voulez vous mélanger le(s) tableau(x) ?", "Coupe", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                Collections.shuffle(_winners);
                Collections.shuffle(_loosers);
            }
        }

        for (int i = 0; i < (_winners.size() + 1) / 2; i++) {
            Match m = new Match();
            m._coach1 = _winners.get(2 * i);
            if ((2 * i + 1) >= _winners.size()) {
                m._coach2 = Coach.NullCoach;
            } else {
                m._coach2 = _winners.get(2 * i + 1);
            }
            r.getMatchs().add(m);
        }


        if (third_place) {
            if (_loosers.size() == 2) {
                for (int i = 0; i < (_loosers.size() + 1) / 2; i++) {
                    Match m = new Match();
                    m._coach1 = _loosers.get(2 * i);
                    if ((2 * i + 1) >= _loosers.size()) {
                        m._coach2 = Coach.NullCoach;
                    } else {
                        m._coach2 = _loosers.get(2 * i + 1);
                    }
                    r.getMatchs().add(m);
                }
            }
        }

        if (_round._looser_cup) {

            int nb_remaining_match = 0;
            if (_round._cup_tour > 0) {
                nb_remaining_match = nb_match;
            }

            if (nb_match == 1) {
                Match m = new Match();
                m._coach1 = _loosers.get(0);
                _loosers.remove(0);
                m._coach2 = Coach.NullCoach;
                r.getMatchs().add(m);
                nb_remaining_match = 2;
            }
            if (nb_match == 0) {
                nb_remaining_match = 1;
                if (_round._cup_tour == _round._cup_max_tour + 1) {
                    nb_match = 1;
                } else {
                    nb_match = 2;
                }
            }

            for (int i = nb_match; (i < nb_match + nb_remaining_match) && (i < matchs.size()); i++) {
                _loosers.add(matchs.get(i).getWinner());
            }

            for (int i = 0; i < _loosers.size() / 2; i++) {
                Match m = new Match();
                m._coach1 = _loosers.get(i);
                m._coach2 = _loosers.get(_loosers.size() / 2 + i);
                r.getMatchs().add(m);
            }
        }
    }

    protected void IndivCupAffectation(Round r, Vector<ObjectRanking> datas, boolean teamTour, int nbPlayers) {

        int nb_match = nbPlayers / 2;

        Vector<Coach> coachs = new Vector<Coach>();
        for (int i = 0; i < nbPlayers; i++) {
            Coach c;
            if (i < datas.size()) {
                c = ((Coach) ((ObjectRanking) datas.get(i)).getObject());
            } else {
                c = Coach.NullCoach;
            }
            coachs.add(c);
        }


        for (int i = 0; i < nb_match / 2; i++) {
            Match m = new Match();

            m._coach1 = coachs.get(2 * i);
            m._coach2 = coachs.get(nbPlayers - 2 * i - 1);
            r.getMatchs().add(m);
        }
        for (int i = 0; i < nb_match / 2; i++) {
            Match m = new Match();

            m._coach1 = coachs.get(2 * i + 1);
            m._coach2 = coachs.get(nbPlayers - 2 * i - 2);
            r.getMatchs().add(m);
        }
    }

    protected void TeamCupAffectation(Round r, Vector<ObjectRanking> datas, int nbTeams) {

        int nb_match = nbTeams / 2;

        Vector<Team> teams = new Vector<Team>();
        Vector<Team> teams1 = new Vector<Team>();
        Vector<Team> teams2 = new Vector<Team>();

        for (int i = 0; i < nbTeams; i++) {
            Team t;
            if (i < datas.size()) {
                t = ((Team) ((ObjectRanking) datas.get(i)).getObject());
            } else {
                t = Team.NullTeam;
            }
            teams.add(t);
        }


        for (int i = 0; i < nb_match / 2; i++) {
            teams1.add(teams.get(2 * i));
            teams2.add(teams.get(nbTeams - 2 * i - 1));
        }
        for (int i = 0; i < nb_match / 2; i++) {
            teams1.add(teams.get(2 * i + 1));
            teams2.add(teams.get(nbTeams - 2 * i - 2));
        }

        Tournament.getTournament().TeamIndivPairing(teams1, teams2, r, JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affectation des joueurs aléatoite (sinon, l'order d'inscription sera utilisée) ?", "Affecation des joueurs", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }

    protected void affectTeamCoachs(Team team1, Team team2, Round r) {
        Vector<Round> vs = new Vector<Round>();
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_tournament.getRounds().get(i).getHeure().before(_round.getHeure())) {
                vs.add(_tournament.getRounds().get(i));
            }
        }
        vs.add(_round);

        Vector<Match> ms = new Vector<Match>();
        for (int j = 0; j < vs.size(); j++) {
            for (int k = 0; k < vs.get(j).getMatchs().size(); k++) {
                ms.add(vs.get(j).getMatchs().get(k));
            }
        }

        for (int j = 0; j < _tournament.getParams()._teamMatesNumber; j++) {

            if (_tournament.getParams()._teamIndivPairing == 0) {
                Vector<ObjectRanking> coachs1 = subRanking(team1._coachs, ms, vs);
                Vector<ObjectRanking> coachs2 = subRanking(team2._coachs, ms, vs);
                for (int k = 0; k < coachs1.size(); k++) {
                    Match m = new Match();
                    m._coach1 = (Coach) coachs1.get(k).getObject();
                    m._coach2 = (Coach) coachs2.get(k).getObject();
                    r.getMatchs().add(m);
                }
            } else {
                if (_tournament.getParams()._teamIndivPairing == 1) {
                    jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, team1, team2, r);
                    jdg.setVisible(true);
                } else {
                    for (int k = 0; k < _tournament.getParams()._teamMatesNumber; k++) {
                        Vector<Coach> shuffle2 = new Vector<Coach>(team2.getActivePlayers());
                        Collections.shuffle(shuffle2);
                        Match m = new Match();
                        m._coach1 = team1.getActivePlayers().get(k);
                        m._coach2 = shuffle2.get(k);
                        r.getMatchs().add(m);
                    }
                }
            }
        }
    }

    protected void TeamCupAffectation(Round r, boolean third_place) {
        int nb_match = 0;


        Vector<Match> matchs = new Vector<Match>(_round.getMatchs());
        nb_match = (int) Math.pow(2, _round._cup_max_tour - _round._cup_tour - 1);
        //nb_match = nb_match;

        // Récupérer les gagnants/perdants
        Vector<Team> _winners = new Vector<Team>();
        Vector<Team> _loosers = new Vector<Team>();


        Criteria td = _tournament.getParams()._criterias.get(0);
        for (int i = 0; i < nb_match; i++) {

            Team t = Match.getTeamMatchWinner(_tournament.getParams()._teamMatesNumber, i, matchs);

            Match m = matchs.get(i * _tournament.getParams()._teamMatesNumber);
            Team team1 = m._coach1._teamMates;
            Team team2 = m._coach2._teamMates;

            if (team1 == t) {
                _winners.add(team1);
                _loosers.add(team2);
            } else {
                _winners.add(team2);
                _loosers.add(team1);
            }
        }

        if (nb_match == 0) {
            if (_round._cup_tour == _round._cup_max_tour + 1) {
                _loosers.add(Match.getTeamMatchWinner(_tournament.getParams()._teamMatesNumber, 0, matchs));
            } else {
                _winners.add(Match.getTeamMatchWinner(_tournament.getParams()._teamMatesNumber, 0, matchs));
                _loosers.add(Match.getTeamMatchWinner(_tournament.getParams()._teamMatesNumber, 1, matchs));
            }
        }

        int option = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Voulez vous mélanger le(s) tableau(x) ?", "Coupe", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            Collections.shuffle(_winners);
            Collections.shuffle(_loosers);
        }



        for (int i = 0; i < (_winners.size() + 1) / 2; i++) {
            Team team1 = _winners.get(2 * i);
            Team team2;
            if ((2 * i + 1) >= _winners.size()) {
                team2 = Team.NullTeam;
            } else {
                team2 = _winners.get(2 * i + 1);
            }
            affectTeamCoachs(team1, team2, r);
        }

        if (third_place) {
            if (_loosers.size() == 2) {
                for (int i = 0; i < (_loosers.size() + 1) / 2; i++) {


                    for (int j = 0; j < _tournament.getParams()._teamMatesNumber; j++) {
                        if ((2 * i + 1) >= _loosers.size()) {
                            affectTeamCoachs(_loosers.get(2 * i), Team.NullTeam, r);
                        } else {
                            affectTeamCoachs(_loosers.get(2 * i), _loosers.get(2 * i + 1), r);
                        }
                    }
                }
            }
        }

        if (_round._looser_cup) {

            int nb_remaining_match = 0;
            if (_round._cup_tour > 0) {
                nb_remaining_match = nb_match;
            }

            if (nb_match == 1) {
                Team t = _loosers.get(0);
                affectTeamCoachs(t, Team.NullTeam, r);
                _loosers.remove(0);
                nb_remaining_match = 2;
            }
            if (nb_match == 0) {
                nb_remaining_match = 1;
                if (_round._cup_tour == _round._cup_max_tour + 1) {
                    nb_match = 1;
                } else {
                    nb_match = 2;
                }
            }

            for (int i = nb_match; (i < nb_match + nb_remaining_match) && (i < matchs.size()); i++) {
                _loosers.add(Match.getTeamMatchWinner(_tournament.getParams()._teamMatesNumber, i, matchs));
            }

            for (int i = 0; i < _loosers.size() / 2; i++) {
                if ((2 * i + 1) >= _loosers.size()) {
                    affectTeamCoachs(_loosers.get(i), Team.NullTeam, r);
                } else {
                    affectTeamCoachs(_loosers.get(i + _loosers.size() / 2), Team.NullTeam, r);
                }
            }
        }
    }

    private void jbtNextRoundFinalTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextRoundFinalTabActionPerformed
        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r.setHeure(cal.getTime());
        if (_round._cup) {
            if (((_round._cup_tour == _round._cup_max_tour - 1) && (!_round._looser_cup))
                    || (_round._cup_tour == _round._cup_max_tour + 2)) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Tour final atteint", "Coupe", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean _third_place = false;
            if ((_round._cup_tour == _round._cup_max_tour - 2) && (!_round._looser_cup)) {
                _third_place = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Voulez vous générer le match pour la 3e place ?", "Coupe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            }

            if (_tournament.getParams()._teamTournament) {
                if (_tournament.getParams()._teamPairing == 0) {
                    IndivCupAffectation(r, _third_place);
                } else {
                    TeamCupAffectation(r, _third_place);
                }
            } else {
                if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    IndivCupAffectation(r, _third_place);
                }
            }

            r._cup = true;
            r._cup_max_tour = _round._cup_max_tour;
            r._cup_tour = _round._cup_tour + 1;
            r._looser_cup = _round._looser_cup;
        } else {
            int cup_max_tour = 0;
            int nb_tmp = 1;
            r._looser_cup = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Sagit-il d'un tournoi à double élimination ?", "Coupe", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            if (_tournament.getParams()._teamTournament) {
                if (_tournament.getParams()._teamPairing == 0) {
                    while (nb_tmp < _tournament.GetActiveCoachNumber()) {
                        nb_tmp = nb_tmp * 2;
                        cup_max_tour++;
                    }
                } else {
                    while (nb_tmp < _tournament.getTeams().size()) {
                        nb_tmp = nb_tmp * 2;
                        cup_max_tour++;
                    }
                }
            } else {
                if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    while (nb_tmp < _tournament.GetActiveCoachNumber()) {
                        nb_tmp = nb_tmp * 2;
                        cup_max_tour++;
                    }
                }
            }

            JPanel message = new JPanel();
            message.setLayout(new BorderLayout());

            JLabel jlb = new JLabel("Nombre de tours: ");
            // generate cup for the first round
            JSpinner jspNbTeams = new JSpinner();
            SpinnerNumberModel model = new SpinnerNumberModel(1, 1, cup_max_tour, 1);
            jspNbTeams.setModel(model);

            message.add(jlb, BorderLayout.NORTH);
            message.add(jspNbTeams, BorderLayout.CENTER);

            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), message, "Coupe", JOptionPane.QUESTION_MESSAGE);

            cup_max_tour = (Integer) model.getValue();
            int nb = (int) Math.pow(2, cup_max_tour);

            Vector<ObjectRanking> datas;
            datas = getSortedRankingData();
            if (_tournament.getParams()._teamTournament) {
                if (_tournament.getParams()._teamPairing == 0) {
                    IndivCupAffectation(r, datas, true, nb);
                } else {
                    TeamCupAffectation(r, datas, nb);
                }
            } else {
                if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                    JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    IndivCupAffectation(r, datas, false, nb);
                }
            }
            r._cup = true;
            r._cup_max_tour = cup_max_tour;
            r._cup_tour = 0;
        }

        if (r.getMatchs().size() < _tournament.GetActiveCoachNumber() / 2) {
            int res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Affecter une ronde aux joueurs restants ?", "Coupe", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {

                Vector<ObjectRanking> datas = new Vector<ObjectRanking>(getSortedRankingData());
                Vector<Coach> coachs;
                Vector<Team> teams;
                Round r2 = new Round();

                if (_tournament.getParams()._teamTournament) {
                    if (_tournament.getParams()._teamPairing == 0) {

                        coachs = new Vector<Coach>(_tournament.GetActiveCoaches());
                        // First, remove coachs
                        for (int i = 0; i < r.getMatchs().size(); i++) {
                            Match m = r.getMatchs().get(i);
                            if (coachs.contains(m._coach1)) {
                                coachs.remove(m._coach1);
                            }
                            if (coachs.contains(m._coach2)) {
                                coachs.remove(m._coach2);
                            }
                        }
                        // Second remove bad datas
                        for (int i = 0; i < datas.size(); i++) {
                            Coach c = (Coach) ((ObjectRanking) datas.get(i)).getObject();
                            for (int j = 0; j < r.getMatchs().size(); j++) {
                                Match m = r.getMatchs().get(j);
                                if ((c == m._coach1) || (c == m._coach2)) {
                                    datas.remove(i);
                                    i--;
                                }
                            }
                        }

                    } else {
                        teams = new Vector<Team>(_tournament.getTeams());
                        // First, remove coachs
                        for (int i = 0; i < r.getMatchs().size(); i++) {
                            Match m = r.getMatchs().get(i);
                            if (teams.contains(m._coach1._teamMates)) {
                                teams.remove(m._coach1._teamMates);
                            }
                            if (teams.contains(m._coach2._teamMates)) {
                                teams.remove(m._coach2._teamMates);
                            }
                        }
                        // Second remove bad datas
                        for (int i = 0; i < datas.size(); i++) {
                            Team t = (Team) ((ObjectRanking) datas.get(i)).getObject();
                            for (int j = 0; j < r.getMatchs().size(); j++) {
                                Match m = r.getMatchs().get(j);
                                if ((t == m._coach1._teamMates) || (t == m._coach2._teamMates)) {
                                    datas.remove(i);
                                    i--;
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                        JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else {
                        coachs = new Vector<Coach>(_tournament.GetActiveCoaches());
                        // First, remove coachs
                        for (int i = 0; i < r.getMatchs().size(); i++) {
                            Match m = r.getMatchs().get(i);
                            if (coachs.contains(m._coach1)) {
                                coachs.remove(m._coach1);
                            }
                            if (coachs.contains(m._coach2)) {
                                coachs.remove(m._coach2);
                            }
                        }
                        // Second remove bad datas
                        for (int i = 0; i < datas.size(); i++) {
                            Coach c = (Coach) ((ObjectRanking) datas.get(i)).getObject();
                            for (int j = 0; j < r.getMatchs().size(); j++) {
                                Match m = r.getMatchs().get(j);
                                if ((c == m._coach1) || (c == m._coach2)) {
                                    datas.remove(i);
                                    i--;
                                }
                            }
                        }
                    }
                }

                Vector<Round> v = new Vector<Round>();
                for (int i = 0; i < _tournament.getRounds().size(); i++) {
                    if (_tournament.getRounds().get(i).getHeure().before(_round.getHeure())) {
                        v.add(_tournament.getRounds().get(i));
                    }
                }
                v.add(_round);

                Vector<Match> matchs = new Vector<Match>();
                for (int j = 0; j < v.size(); j++) {
                    for (int k = 0; k < v.get(j).getMatchs().size(); k++) {
                        matchs.add(v.get(j).getMatchs().get(k));
                    }
                }

                res = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Utiliser une ronde suisse (aléatoire sinon)? ", "Coupe", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {

                    if (_tournament.getParams()._teamTournament) {
                        if (_tournament.getParams()._teamPairing == 0) {
                            IndivAffectation(_tournament.getCoachs(), matchs, datas, true, r2);
                        } else {
                            TeamAffectation(_tournament.getTeams(), matchs, datas, _tournament.getParams()._teamIndivPairing == 1, v, r2);
                        }
                    } else {
                        if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                            JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                            return;
                        } else {
                            IndivAffectation(_tournament.getCoachs(), matchs, datas, false, r2);
                        }
                    }
                } else {
                    if (_tournament.getParams()._teamTournament) {
                        if (_tournament.getParams()._teamPairing == 0) {
                            IndivRandomAffectation(_tournament.getCoachs(), matchs, true, r2);
                        } else {
                            TeamRandomAffectation(_tournament.getTeams(), matchs, true, _tournament.getParams()._teamIndivPairing == 1, v, r2);
                        }
                    } else {
                        if (_tournament.GetActiveCoachNumber() % 2 > 0) {
                            JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs actif impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
                            return;
                        } else {
                            IndivRandomAffectation(_tournament.getCoachs(), matchs, false, r);
                        }
                    }
                }

                // Merge the generated rounds
                for (int i = 0; i < r2.getMatchs().size(); i++) {
                    r.getMatchs().add(r2.getMatchs().get(i));
                }
            }
        }

        finalGenerationStep(r);


        /*    Vector<Integer> Options = new Vector<Integer>();
         Round r;
         int nbElements = 0;
         int nbMax = 0;
         int maxnumber = 0;
         int result = 0;

         Vector<ObjectRanking> datas_swiss;

         if (_round._final_tab) {
         if (_roundNumber == 1) {
         return;
         } else {
         r = new Round();
         Calendar cal = Calendar.getInstance();
         r.setHeure(cal.getTime());
         r._max_tab = _round._max_tab;

         int nbMatchs = (int) Math.pow(2, _round._max_tab);


         if ((_tournament.getParams()._teamTournament) && (_tournament.getParams()._teamPairing != 0)) {
         nbElements = _tournament.getTeams().size();
         nbMax = _tournament.getTeams().size();
         while (nbElements > 1) {
         maxnumber++;
         nbElements = nbElements / 2;
         Options.add(maxnumber);
         }
         int index = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), "Nombre de tours", "Tableau final", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, Options.toArray(), Options.get(0));
         result = Options.get(index);
         double puiss = (double) result;
         nbElements = (int) (Math.round(Math.pow(2.0, puiss)));
         } else {
         // Winner against winners
         for (int i = 0; i < nbMatchs / 2; i++) {
         Coach c1 = _round.getMatchs().get(2 * i).getWinner();
         Coach c2 = _round.getMatchs().get(2 * i + 1).getWinner();
         Match m = new Match();
         m._coach1 = c1;
         m._coach2 = c2;
         r.getMatchs().add(m);

         }
         // Looser against loosers
         for (int i = 0; i < nbMatchs / 2; i++) {
         Coach c1 = _round.getMatchs().get(2 * i).getLooser();
         Coach c2 = _round.getMatchs().get(2 * i + 1).getLooser();
         Match m = new Match();
         m._coach1 = c1;
         m._coach2 = c2;
         r.getMatchs().add(m);
         }

         Vector<ObjectRanking> datas=getSortedRankingData();
         Vector<ObjectRanking> data_swiss=getSortedRankingData();
         for (int i=0; i<datas.size(); i++)
         {
         ObjectRanking obj=datas.get(i);
         Object o=obj.getObject();
         if (o instanceof Coach )
         {
         for (int j=0; j<nbMatchs; j++)
         {
         if ((_round.getMatchs().get(j)._coach1 == (Coach)o)||
         (_round.getMatchs().get(j)._coach1 == (Coach)o))
         {
         data_swiss.remove(obj);
         }
         }
         }
         }
         }

         // Récupérer la liste des coachs restants et construire une ronde suisse
         }
         } else {
         if ((_tournament.getParams()._teamTournament) && (_tournament.getParams()._teamPairing != 0)) {
         nbElements = _tournament.getTeams().size();
         nbMax = _tournament.getTeams().size();
         while (nbElements > 1) {
         maxnumber++;
         nbElements = nbElements / 2;
         Options.add(maxnumber);
         }
         int index = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), "Nombre de tours", "Tableau final", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, Options.toArray(), Options.get(0));
         result = Options.get(index);
         double puiss = (double) result;
         nbElements = (int) (Math.round(Math.pow(2.0, puiss)));
         } else {
         nbElements = _tournament.GetActiveCoachNumber();
         nbMax = _tournament.GetActiveCoachNumber();
         while (nbElements > 1) {
         maxnumber++;
         nbElements = nbElements / 2;
         Options.add(maxnumber);
         }
         int index = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), "Nombre de tours", "Tableau final", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, Options.toArray(), Options.get(0));
         result = Options.get(index);
         double puiss = result;
         nbElements = (int) (Math.round(Math.pow(2.0, puiss)));
         }

         Vector<ObjectRanking> datas=getSortedRankingData();            
         datas_swiss = new Vector<ObjectRanking>();

         if (nbElements < nbMax) {
         if (JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Compléter les autres en ronde suisse ?", "Tableau Final", JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
         while (datas.size() > nbElements) {
         datas_swiss.add(datas.get(nbElements));
         datas.remove(nbElements);
         }
         }

         }
         }

         // finish with the swiss
         if (datas_swiss.size() > 0) {

         Vector<Round> v = new Vector<Round>();
         for (int i = 0; i < _tournament.getRounds().size(); i++) {
         if (_tournament.getRounds().get(i).getHeure().before(_round.getHeure())) {
         v.add(_tournament.getRounds().get(i));
         }
         }
         v.add(_round);

         Vector<Match> matchs = new Vector<Match>();
         for (int j = 0; j < v.size(); j++) {
         for (int k = 0; k < v.get(j).getMatchs().size(); k++) {
         matchs.add(v.get(j).getMatchs().get(k));
         }
         }

         if (_tournament.getParams()._teamTournament) {
         if (_tournament.getParams()._teamPairing == 0) {
         r = IndivAffectation(matchs, datas_swiss, true);
         } else {
         r = TeamAffectation(matchs, datas_swiss, _tournament.getParams()._teamIndivPairing == 1, v);
         }
         } else {
         if (_tournament.GetActiveCoachNumber() % 2 > 0) {
         JOptionPane.showMessageDialog(_jpnTeamRound, "Nombre de coachs restants impair", "Erreur de génération", JOptionPane.WARNING_MESSAGE);
         return;
         } else {
         r = IndivAffectation(matchs, datas_swiss, false);
         }
         }
         }
         r._final_tab = true;
         r._tour_tab = result;
         r._max_tab = result;

         _tournament.getRounds().add(r);*/



    }//GEN-LAST:event_jbtNextRoundFinalTabActionPerformed
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
    private javax.swing.JButton jbtNextRoundFinalTab;
    private javax.swing.JButton jbtNextRoundQuickSwiss;
    private javax.swing.JButton jbtNextRoundRandom;
    private javax.swing.JButton jbtShowMatches;
    private javax.swing.JButton jbtShowResults;
    private javax.swing.JTable jtbMatches;
    private javax.swing.JTable jtbRankingIndiv;
    private javax.swing.JToggleButton jtbRoundSum;
    private javax.swing.JTabbedPane jtpAnnexRankings;
    private javax.swing.JTabbedPane jtpGlobal;
    // End of variables declaration//GEN-END:variables
}
