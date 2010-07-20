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

import tourma.tableModel.mjtAnnexRankIndiv;
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
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import tourma.data.Team;
import tourma.tableModel.mjtRankingTeam;

/**
 *
 * @author Frederic Berger
 */
public class JPNRound extends javax.swing.JPanel {

    Round _round;
    Tournament _tournament;
    JPNTeamRound _jpnTeamRound = null;

    /** Creates new form JPNRound */
    public JPNRound(Round r, Tournament t) {
        initComponents();
        _round = r;
        _tournament = t;

        if (_tournament.getParams()._teamTournament) {
            _jpnTeamRound = new JPNTeamRound(r, t);
            jtpGlobal.add(_jpnTeamRound, "Par équipe");
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

            mjtAnnexRankIndiv mTdPos = new mjtAnnexRankIndiv(v, mjtAnnexRankIndiv.C_MOST_TD_POS, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getParams()._teamTournament);
            mjtAnnexRankIndiv mTdNeg = new mjtAnnexRankIndiv(v, mjtAnnexRankIndiv.C_MOST_TD_NEG, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getParams()._teamTournament);
            mjtAnnexRankIndiv mSorPos = new mjtAnnexRankIndiv(v, mjtAnnexRankIndiv.C_MOST_SOR_POS, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getParams()._teamTournament);
            mjtAnnexRankIndiv mSorNeg = new mjtAnnexRankIndiv(v, mjtAnnexRankIndiv.C_MOST_SOR_NEG, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getParams()._teamTournament);
            mjtAnnexRankIndiv mFoulPos = new mjtAnnexRankIndiv(v, mjtAnnexRankIndiv.C_MOST_FOUL_POS, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getParams()._teamTournament);
            mjtAnnexRankIndiv mFoulNeg = new mjtAnnexRankIndiv(v, mjtAnnexRankIndiv.C_MOST_FOUL_NEG, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getParams()._teamTournament);

            jtbMostTdIndiv.setModel(mTdPos);
            jtbMostTdIndiv.setDefaultRenderer(String.class, mTdPos);
            jtbMostTdIndiv.setDefaultRenderer(Integer.class, mTdPos);
            jtbMostTdNegIndiv.setModel(mTdNeg);
            jtbMostTdNegIndiv.setDefaultRenderer(String.class, mTdNeg);
            jtbMostTdNegIndiv.setDefaultRenderer(Integer.class, mTdNeg);
            jtbMostSorIndiv.setModel(mSorPos);
            jtbMostSorIndiv.setDefaultRenderer(String.class, mSorPos);
            jtbMostSorIndiv.setDefaultRenderer(Integer.class, mSorPos);
            jtbMostSorNegIndiv.setModel(mSorNeg);
            jtbMostSorNegIndiv.setDefaultRenderer(String.class, mSorNeg);
            jtbMostSorNegIndiv.setDefaultRenderer(Integer.class, mSorNeg);
            jtbMostFoulIndiv.setModel(mFoulPos);
            jtbMostFoulIndiv.setDefaultRenderer(String.class, mFoulPos);
            jtbMostFoulIndiv.setDefaultRenderer(Integer.class, mFoulPos);
            jtbMostFoulNegIndiv.setModel(mFoulNeg);
            jtbMostFoulNegIndiv.setDefaultRenderer(String.class, mFoulNeg);
            jtbMostFoulNegIndiv.setDefaultRenderer(Integer.class, mFoulNeg);

            mjtRankingIndiv mRanking = new mjtRankingIndiv(v, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getCoachs(), _tournament.getParams()._teamTournament);
            jtbRankingIndiv.setModel(mRanking);
            jtbRankingIndiv.setDefaultRenderer(String.class, mRanking);
            jtbRankingIndiv.setDefaultRenderer(Integer.class, mRanking);


            setColumnSize(jtbRankingIndiv);
            setColumnSize(jtbMostFoulNegIndiv);
            setColumnSize(jtbMostFoulIndiv);
            setColumnSize(jtbMostSorNegIndiv);
            setColumnSize(jtbMostSorIndiv);
            setColumnSize(jtbMostTdNegIndiv);
            setColumnSize(jtbMostTdIndiv);


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
        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbRankingIndiv = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtbMostTdIndiv = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtbMostSorIndiv = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtbMostFoulIndiv = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtbMostTdNegIndiv = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtbMostSorNegIndiv = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtbMostFoulNegIndiv = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jbtGeneralIndiv = new javax.swing.JButton();
        jbtScorePosIndiv = new javax.swing.JButton();
        jbtScoreNegIndiv = new javax.swing.JButton();
        jbtSorPosIndiv = new javax.swing.JButton();
        jbtSorNegIndiv = new javax.swing.JButton();
        jbtFoulPosIndiv = new javax.swing.JButton();
        jbtFoulNegIndiv = new javax.swing.JButton();

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

        jbtNextRound.setLabel("Générer la prochaine ronde");
        jbtNextRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextRoundActionPerformed(evt);
            }
        });
        jPanel3.add(jbtNextRound);

        jbtShowMatches.setText("Vue des matchs");
        jbtShowMatches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShowMatchesActionPerformed(evt);
            }
        });
        jPanel3.add(jbtShowMatches);

        jbtShowResults.setText("Vue des résultats");
        jbtShowResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShowResultsActionPerformed(evt);
            }
        });
        jPanel3.add(jbtShowResults);

        jbtDeleteRound.setText("Effacer la ronde courante");
        jbtDeleteRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDeleteRoundActionPerformed(evt);
            }
        });
        jPanel3.add(jbtDeleteRound);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jtpGlobal.addTab("Matchs", jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Classement général"));
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

        jSplitPane1.setBottomComponent(jScrollPane2);

        jPanel4.setLayout(new java.awt.GridLayout(2, 3));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Scoreur"));

        jtbMostTdIndiv.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jtbMostTdIndiv);

        jPanel4.add(jScrollPane4);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Desctructeur"));

        jtbMostSorIndiv.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jtbMostSorIndiv);

        jPanel4.add(jScrollPane5);

        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("Crampon"));

        jtbMostFoulIndiv.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jtbMostFoulIndiv);

        jPanel4.add(jScrollPane6);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder("Passoire"));

        jtbMostTdNegIndiv.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jtbMostTdNegIndiv);

        jPanel4.add(jScrollPane8);

        jScrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder("Punching-Ball"));

        jtbMostSorNegIndiv.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(jtbMostSorNegIndiv);

        jPanel4.add(jScrollPane9);

        jScrollPane10.setBorder(javax.swing.BorderFactory.createTitledBorder("Paillasson"));

        jtbMostFoulNegIndiv.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(jtbMostFoulNegIndiv);

        jPanel4.add(jScrollPane10);

        jSplitPane1.setTopComponent(jPanel4);

        jPanel2.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jbtGeneralIndiv.setText("Général");
        jbtGeneralIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralIndivActionPerformed(evt);
            }
        });
        jPanel5.add(jbtGeneralIndiv);

        jbtScorePosIndiv.setText("Scoreur");
        jbtScorePosIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtScorePosIndivActionPerformed(evt);
            }
        });
        jPanel5.add(jbtScorePosIndiv);

        jbtScoreNegIndiv.setText("Passoire");
        jbtScoreNegIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtScoreNegIndivActionPerformed(evt);
            }
        });
        jPanel5.add(jbtScoreNegIndiv);

        jbtSorPosIndiv.setText("Destructeur");
        jbtSorPosIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSorPosIndivActionPerformed(evt);
            }
        });
        jPanel5.add(jbtSorPosIndiv);

        jbtSorNegIndiv.setText("Punching Ball");
        jbtSorNegIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSorNegIndivActionPerformed(evt);
            }
        });
        jPanel5.add(jbtSorNegIndiv);

        jbtFoulPosIndiv.setText("Crampon");
        jbtFoulPosIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFoulPosIndivActionPerformed(evt);
            }
        });
        jPanel5.add(jbtFoulPosIndiv);

        jbtFoulNegIndiv.setText("Paillasson");
        jbtFoulNegIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFoulNegIndivActionPerformed(evt);
            }
        });
        jPanel5.add(jbtFoulNegIndiv);

        jPanel2.add(jPanel5, java.awt.BorderLayout.SOUTH);

        jtpGlobal.addTab("Classements individuels", jPanel2);

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
            JOptionPane.showMessageDialog(this, "Pas assez de ronde pour éviter des doublons, génération sans gestion des doublons");
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
                        if (((matchs.get(j)._coach1 == m._coach1) && (matchs.get(j)._coach2 == datas2.get(i).getObject())) ||
                                ((matchs.get(j)._coach2 == m._coach1) && (matchs.get(j)._coach1 == datas2.get(i).getObject()))) {
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

                    if ((!have_played) || (i == datas2.size() - 1)) {
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
                    if (((matchs.get(j)._coach1 == c1) && (matchs.get(j)._coach2 == c2)) ||
                            ((matchs.get(j)._coach1 == c2) && (matchs.get(j)._coach2 == c1))) {
                        have_played = true;
                    }
                }

                if (have_played) {
                    // Le but est déchanger C1 avec le premier joueur non encore rencontré
                    for (int k = i - 1; k >= 0; k--) {

                        // Test du c2 des matchs pécédents
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;

                            if (((matchs.get(j)._coach1 == c2) && (matchs.get(j)._coach2 == r.getMatchs().get(k)._coach2)) ||
                                    ((matchs.get(j)._coach2 == c2) && (matchs.get(j)._coach1 == r.getMatchs().get(k)._coach2))) {
                                have_played = true;
                                break;
                            }
                        }

                        if (!have_played) {
                            r.getMatchs().get(i)._coach1 = r.getMatchs().get(k)._coach2;
                            r.getMatchs().get(k)._coach2 = c1;
                            break;
                        }

                        // Test du c1 des matchs pécédents
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;

                            if (((matchs.get(j)._coach1 == c2) && (matchs.get(j)._coach2 == r.getMatchs().get(k)._coach1)) ||
                                    ((matchs.get(j)._coach2 == c2) && (matchs.get(j)._coach1 == r.getMatchs().get(k)._coach1))) {
                                have_played = true;
                                break;
                            }
                        }

                        if (!have_played) {
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
            for (int k = 0; k < matchs.size(); k++) {
                Match m = matchs.get(k);
                value1 += mjtRankingIndiv.getValue(c, m, _tournament.getParams()._ranking1, rounds);
                value2 += mjtRankingIndiv.getValue(c, m, _tournament.getParams()._ranking2, rounds);
                value3 += mjtRankingIndiv.getValue(c, m, _tournament.getParams()._ranking3, rounds);
                value4 += mjtRankingIndiv.getValue(c, m, _tournament.getParams()._ranking4, rounds);
                value5 += mjtRankingIndiv.getValue(c, m, _tournament.getParams()._ranking5, rounds);
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
            JOptionPane.showMessageDialog(this, "Pas assez de ronde pour éviter des doublons, génération sans gestion des doublons");
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
                        if (((matchs.get(j)._coach1._teamMates == team1) && (matchs.get(j)._coach2._teamMates == datas2.get(i).getObject())) ||
                                ((matchs.get(j)._coach2._teamMates == team1) && (matchs.get(j)._coach1._teamMates == datas2.get(i).getObject()))) {
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
                    if (((matchs.get(j)._coach1._teamMates == team1) && (matchs.get(j)._coach2._teamMates == team2)) ||
                            ((matchs.get(j)._coach1._teamMates == team2) && (matchs.get(j)._coach2._teamMates == team1))) {
                        have_played = true;
                    }
                }
                if (have_played) {
                    // Le but est déchanger C1 avec la première équipe non encore rencontrée
                    for (int k = i - 1; k >= 0; k--) {

                        // Test du c2 des matchs pécédents
                        for (int j = 0; j < matchs.size(); j++) {
                            have_played = false;

                            if (((matchs.get(j)._coach1._teamMates == team2) && (matchs.get(j)._coach2._teamMates == teams2.get(k))) ||
                                    ((matchs.get(j)._coach2._teamMates == team2) && (matchs.get(j)._coach1._teamMates == teams2.get(k)))) {
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

                            if (((matchs.get(j)._coach1._teamMates == team2) && (matchs.get(j)._coach2._teamMates == teams1.get(k))) ||
                                    ((matchs.get(j)._coach2._teamMates == team2) && (matchs.get(j)._coach1._teamMates == teams1.get(k)))) {
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
                ranking = new mjtRankingTeam(v,
                        _tournament.getParams()._ranking1_team,
                        _tournament.getParams()._ranking2_team,
                        _tournament.getParams()._ranking3_team,
                        _tournament.getParams()._ranking4_team,
                        _tournament.getParams()._ranking5_team,
                        _tournament.getTeams());
            } else {
                ranking = new mjtRankingTeam(v,
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

            mjtRankingIndiv ranking = new mjtRankingIndiv(v,
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
            JPNRound jpnr = new JPNRound(_tournament.getRounds().get(i), _tournament);
            MainFrame.getMainFrame().jtpMain.add("Ronde " + (i + 1), jpnr);
        }

        update();
    }//GEN-LAST:event_jbtNextRoundActionPerformed

    private void jbtShowMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowMatchesActionPerformed
        for (int i = 0; i <
                _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRound jdg = new jdgRound(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, false, false);
                jdg.setVisible(true);
                break;

            }




        }
    }//GEN-LAST:event_jbtShowMatchesActionPerformed

    private void jbtShowResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowResultsActionPerformed
        for (int i = 0; i <
                _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRound jdg = new jdgRound(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, true, false);
                jdg.setVisible(true);
                break;

            }




        }
    }//GEN-LAST:event_jbtShowResultsActionPerformed

    private void jbtGeneralIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralIndivActionPerformed
        for (int i = 0; i <
                _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_GENERAL, false);
                jdg.setVisible(true);
                break;

            }




        }
    }//GEN-LAST:event_jbtGeneralIndivActionPerformed

    private void jbtScoreNegIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtScoreNegIndivActionPerformed
        for (int i = 0; i <
                _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_SCORED, false);
                jdg.setVisible(true);
                break;

            }




        }
    }//GEN-LAST:event_jbtScoreNegIndivActionPerformed

    private void jbtScorePosIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtScorePosIndivActionPerformed
        for (int i = 0; i <
                _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_SCORER, false);
                jdg.setVisible(true);
                break;

            }




        }
    }//GEN-LAST:event_jbtScorePosIndivActionPerformed

    private void jbtSorPosIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSorPosIndivActionPerformed
        for (int i = 0; i <
                _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_DESTROYER, false);
                jdg.setVisible(true);
                break;

            }




        }
    }//GEN-LAST:event_jbtSorPosIndivActionPerformed

    private void jbtSorNegIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSorNegIndivActionPerformed
        for (int i = 0; i <
                _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_DESTROYED, false);
                jdg.setVisible(true);
                break;

            }




        }
    }//GEN-LAST:event_jbtSorNegIndivActionPerformed

    private void jbtFoulPosIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFoulPosIndivActionPerformed
        for (int i = 0; i <
                _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_FOULER, false);
                jdg.setVisible(true);
                break;

            }




        }
    }//GEN-LAST:event_jbtFoulPosIndivActionPerformed

    private void jbtFoulNegIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFoulNegIndivActionPerformed
        for (int i = 0; i <
                _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_FOULED, false);
                jdg.setVisible(true);
                break;

            }




        }
    }//GEN-LAST:event_jbtFoulNegIndivActionPerformed

    private void jbtDeleteRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDeleteRoundActionPerformed

        if (JOptionPane.showConfirmDialog(this, "Etes vous certain de vouloir effacer la ronde courante ?", "Effacer une ronde", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            _tournament.getRounds().remove(_round);
            for (int i = MainFrame.getMainFrame().jtpMain.getTabCount() - 1; i >=
                    0; i--) {
                Component obj = MainFrame.getMainFrame().jtpMain.getComponentAt(i);
                if (obj instanceof JPNRound) {
                    MainFrame.getMainFrame().jtpMain.remove(obj);
                }

            }
            for (int i = 0; i <
                    _tournament.getRounds().size(); i++) {
                JPNRound jpnr = new JPNRound(_tournament.getRounds().get(i), _tournament);
                MainFrame.getMainFrame().jtpMain.add("Ronde " + (i + 1), jpnr);
            }

            update();
        }
    }//GEN-LAST:event_jbtDeleteRoundActionPerformed

    private void jtpGlobalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtpGlobalStateChanged
        update();
    }//GEN-LAST:event_jtpGlobalStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbtDeleteRound;
    private javax.swing.JButton jbtFoulNegIndiv;
    private javax.swing.JButton jbtFoulPosIndiv;
    private javax.swing.JButton jbtGeneralIndiv;
    private javax.swing.JButton jbtNextRound;
    private javax.swing.JButton jbtScoreNegIndiv;
    private javax.swing.JButton jbtScorePosIndiv;
    private javax.swing.JButton jbtShowMatches;
    private javax.swing.JButton jbtShowResults;
    private javax.swing.JButton jbtSorNegIndiv;
    private javax.swing.JButton jbtSorPosIndiv;
    private javax.swing.JTable jtbMatches;
    private javax.swing.JTable jtbMostFoulIndiv;
    private javax.swing.JTable jtbMostFoulNegIndiv;
    private javax.swing.JTable jtbMostSorIndiv;
    private javax.swing.JTable jtbMostSorNegIndiv;
    private javax.swing.JTable jtbMostTdIndiv;
    private javax.swing.JTable jtbMostTdNegIndiv;
    private javax.swing.JTable jtbRankingIndiv;
    private javax.swing.JTabbedPane jtpGlobal;
    // End of variables declaration//GEN-END:variables
}
