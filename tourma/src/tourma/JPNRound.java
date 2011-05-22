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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import tourma.data.Criteria;
import tourma.data.Group;
import tourma.data.Parameters;
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

    /** Creates new form JPNRound */
    public JPNRound(int roundNumber, Round r, Tournament t) {
        initComponents();
        _round = r;
        _tournament = t;
        _roundNumber = roundNumber;

        if (_tournament.getParams()._teamTournament) {
            _jpnTeamRound = new JPNTeamRound(r, t);
            jtpGlobal.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PAR ÉQUIPE"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Team.png")), _jpnTeamRound);
        } else {
            if (_tournament.getParams()._enableClans) {
                _jpnClanRound = new JPNClanRound(r, t);
                jtpGlobal.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ByClan"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Clan.png")), _jpnClanRound);
            }

            if (_tournament.getGroups().size() > 1) {
                for (int i = 0; i < _tournament.getGroups().size(); i++) {
                    Group g = _tournament.getGroups().get(i);
                    JPNGroup jpnGroup = new JPNGroup(t, g, _roundNumber);
                    jtpGlobal.addTab("Groupe: " + g._name, new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Group.png")), jpnGroup);
                }
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


            /*        jtbMatches.setDefaultEditor(Integer.class, model);*/
            setColumnSize(jtbMatches);
            Vector<Round> v = new Vector<Round>();
            for (int i = 0; i < _tournament.getRounds().size(); i++) {
                if (_tournament.getRounds().get(i).getHeure().before(_round.getHeure())) {
                    v.add(_tournament.getRounds().get(i));
                }
            }
            v.add(_round);

            mjtRankingIndiv mRanking = new mjtRankingIndiv(_roundNumber, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getCoachs(), _tournament.getParams()._teamTournament);
            jtbRankingIndiv.setModel(mRanking);
            jtbRankingIndiv.setDefaultRenderer(String.class, mRanking);
            jtbRankingIndiv.setDefaultRenderer(Integer.class, mRanking);

            for (int i = 0; i < jtpAnnexRankings.getComponentCount(); i++) {
                ((JPNAnnexRanking) jtpAnnexRankings.getComponent(i)).update();
            }
            setColumnSize(jtbRankingIndiv);
        }

        for (int i = 0; i < jtpGlobal.getTabCount(); i++) {
            Object panel = jtpGlobal.getTabComponentAt(i);
            if (panel instanceof JPNGroup) {
                ((JPNGroup) panel).update();
            } else {
                if (panel instanceof JPNTeamRound) {
                    ((JPNTeamRound) panel).update();
                } else {
                    if (panel instanceof JPNClanRound) {
                        ((JPNClanRound) panel).update();
                    }
                }
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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
        jbtShowMatches = new javax.swing.JButton();
        jbtShowResults = new javax.swing.JButton();
        jbtDeleteRound = new javax.swing.JButton();
        jbtChangeMatchs = new javax.swing.JButton();
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

        jbtNextRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Generate.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtNextRound.setText(bundle.getString("GenerateNextRoundKey")); // NOI18N
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

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

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

    private Round IndivAffectation(Vector<Match> matchs, Vector<ObjectRanking> datas, boolean team) {
        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r.setHeure(cal.getTime());
        // Résolution des doublons
        if (_tournament.getCoachs().size() - 1 <= _tournament.getRounds().size()) {
            // Affectation des matchs
            for (int i = 0; i < datas.size() / 2; i++) {
                Match m = new Match();
                m._coach1 = (Coach) datas.get(2 * i).getObject();
                m._coach2 = (Coach) datas.get(2 * i + 1).getObject();
                r.getMatchs().add(m);
            }
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PAS ASSEZ DE RONDE POUR ÉVITER DES DOUBLONS, GÉNÉRATION SANS GESTION DES DOUBLONS"));
        } else {
            Vector<ObjectRanking> datas2 = new Vector<ObjectRanking>(datas);
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
                        if (m._coach1._clan != _tournament.getDisplayClans().get(0)) {
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

            datas2 = new Vector<ObjectRanking>(datas);
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
                            if (r.getMatchs().get(i)._coach2._clan != _tournament.getDisplayClans().get(0)) {
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
                            if (r.getMatchs().get(i)._coach1._clan != _tournament.getDisplayClans().get(0)) {
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

    private Round TeamAffectation(Vector<Match> matchs, Vector<ObjectRanking> datas, boolean free_pairing, Vector<Round> rounds) {
        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r.setHeure(cal.getTime());
        // Résolution des doublons
        if (_tournament.getTeams().size() - 1 <= _tournament.getRounds().size()) {
            // Affectation des matchs
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PAS ASSEZ DE RONDE POUR ÉVITER DES DOUBLONS, GÉNÉRATION SANS GESTION DES DOUBLONS"));
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

        if (_tournament.getParams()._teamTournament) {
            mjtRankingTeam ranking = null;

            if (_tournament.getParams()._team_victory_only) {
                ranking = new mjtRankingTeam(true, _roundNumber,
                        _tournament.getParams()._ranking1_team,
                        _tournament.getParams()._ranking2_team,
                        _tournament.getParams()._ranking3_team,
                        _tournament.getParams()._ranking4_team,
                        _tournament.getParams()._ranking5_team,
                        _tournament.getTeams());
            } else {
                ranking = new mjtRankingTeam(false, _roundNumber,
                        _tournament.getParams()._ranking1,
                        _tournament.getParams()._ranking2,
                        _tournament.getParams()._ranking3,
                        _tournament.getParams()._ranking4,
                        _tournament.getParams()._ranking5,
                        _tournament.getTeams());
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
                    _tournament.getCoachs(), false);

            // Ranking class
            datas = ranking.getSortedDatas();
        }

        Round r;
        if (_tournament.getParams()._teamTournament) {
            if (_tournament.getParams()._teamPairing == 0) {
                r = IndivAffectation(matchs, datas, true);
            } else {
                r = TeamAffectation(matchs, datas, _tournament.getParams()._teamIndivPairing == 1, v);
            }
        } else {
            r = IndivAffectation(matchs, datas, false);
        }

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
        }

        for (int i = 0;
                i < _tournament.getRounds().size();
                i++) {
            JPNRound jpnr = new JPNRound(i, _tournament.getRounds().get(i), _tournament);
//            MainFrame.getMainFrame().jtpMain.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RONDE ") + (i + 1), jpnr);
            MainFrame.getMainFrame().jtpMain.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RONDE ") + (i + 1) ,new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Dice.png")),jpnr);
        }

        String filename = Tournament.getTournament().getParams()._tournament_name;
        filename = filename + "." + Tournament.getTournament().getRounds().size();
        Date date = new Date();
        filename = filename + "." + Long.toString(date.getTime()) + ".xml";

        File file = new File(filename);
        Tournament.getTournament().saveXML(file);

        update();
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
                mjtRankingIndiv model = new mjtRankingIndiv(_roundNumber, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getCoachs(), _tournament.getParams()._teamTournament);
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, "General par Coach", i + 1, _tournament, model, 0);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtGeneralIndivActionPerformed

    private void jbtDeleteRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDeleteRoundActionPerformed

        if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ETES VOUS CERTAIN DE VOULOIR EFFACER LA RONDE COURANTE ?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EFFACER UNE RONDE"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

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

            for (int i = 0; i
                    < _tournament.getRounds().size(); i++) {
                JPNRound jpnr = new JPNRound(i, _tournament.getRounds().get(i), _tournament);
                MainFrame.getMainFrame().jtpMain.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RONDE ") + (i + 1) ,new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Dice.png")),jpnr);
            }

            update();
        }
    }//GEN-LAST:event_jbtDeleteRoundActionPerformed

    private void jtpGlobalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtpGlobalStateChanged
        update();
    }//GEN-LAST:event_jtpGlobalStateChanged

    private void jbtGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGlobalActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                mjtRankingIndiv model = new mjtRankingIndiv(_roundNumber, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getCoachs(), _tournament.getParams()._teamTournament);
                HashMap<Criteria, mjtAnnexRank> annexForRankings = new HashMap<Criteria, mjtAnnexRank>();
                HashMap<Criteria, mjtAnnexRank> annexAgainstRankings = new HashMap<Criteria, mjtAnnexRank>();
                for (int j = 0; j < _tournament.getParams()._criterias.size(); j++) {
                    Criteria crit = _tournament.getParams()._criterias.get(j);
                    mjtAnnexRank annex = new mjtAnnexRankIndiv(i, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                            _tournament.getCoachs(), true,
                            _tournament.getParams()._ranking1, _tournament.getParams()._ranking2,
                            _tournament.getParams()._ranking3, _tournament.getParams()._ranking4,
                            _tournament.getParams()._ranking5, false);
                    annexForRankings.put(crit, annex);
                    annex = new mjtAnnexRankIndiv(i, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                            _tournament.getCoachs(), true,
                            _tournament.getParams()._ranking1, _tournament.getParams()._ranking2,
                            _tournament.getParams()._ranking3, _tournament.getParams()._ranking4,
                            _tournament.getParams()._ranking5, false);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbtChangeMatchs;
    private javax.swing.JButton jbtDeleteRound;
    private javax.swing.JButton jbtGeneralIndiv;
    private javax.swing.JButton jbtGlobal;
    private javax.swing.JButton jbtNextRound;
    private javax.swing.JButton jbtShowMatches;
    private javax.swing.JButton jbtShowResults;
    private javax.swing.JTable jtbMatches;
    private javax.swing.JTable jtbRankingIndiv;
    private javax.swing.JTabbedPane jtpAnnexRankings;
    private javax.swing.JTabbedPane jtpGlobal;
    // End of variables declaration//GEN-END:variables
}
