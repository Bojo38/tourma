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

import tourma.views.report.jdgRound;
import tourma.views.report.jdgRanking;
import tourma.data.CoachRanking;
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

/**
 *
 * @author Frederic Berger
 */
public class JPNRound extends javax.swing.JPanel {

    Round _round;
    Tournament _tournament;

    /** Creates new form JPNRound */
    public JPNRound(Round r, Tournament t) {
        initComponents();
        _round = r;
        _tournament = t;
        update();
    }

    public void update() {
        Date d = _round.getHeure();
        boolean locked = false;
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_tournament.getRounds().get(i).getHeure().after(d)) {
                locked = true;
            }
        }

        jbtDeleteRound.setEnabled(!locked);

        mjtMatches model = new mjtMatches(_round.getMatchs(), locked);
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
        mjtAnnexRank mTdPos = new mjtAnnexRank(v, mjtAnnexRank.C_MOST_TD_POS, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);
        mjtAnnexRank mTdNeg = new mjtAnnexRank(v, mjtAnnexRank.C_MOST_TD_NEG, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);
        mjtAnnexRank mSorPos = new mjtAnnexRank(v, mjtAnnexRank.C_MOST_SOR_POS, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);
        mjtAnnexRank mSorNeg = new mjtAnnexRank(v, mjtAnnexRank.C_MOST_SOR_NEG, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);
        mjtAnnexRank mFoulPos = new mjtAnnexRank(v, mjtAnnexRank.C_MOST_FOUL_POS, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);
        mjtAnnexRank mFoulNeg = new mjtAnnexRank(v, mjtAnnexRank.C_MOST_FOUL_NEG, _tournament.getCoachs(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);

        jtbMostTd.setModel(mTdPos);
        jtbMostTd.setDefaultRenderer(String.class, mTdPos);
        jtbMostTd.setDefaultRenderer(Integer.class, mTdPos);
        jtbMostTdNeg.setModel(mTdNeg);
        jtbMostTdNeg.setDefaultRenderer(String.class, mTdNeg);
        jtbMostTdNeg.setDefaultRenderer(Integer.class, mTdNeg);
        jtbMostSor.setModel(mSorPos);
        jtbMostSor.setDefaultRenderer(String.class, mSorPos);
        jtbMostSor.setDefaultRenderer(Integer.class, mSorPos);
        jtbMostSorNeg.setModel(mSorNeg);
        jtbMostSorNeg.setDefaultRenderer(String.class, mSorNeg);
        jtbMostSorNeg.setDefaultRenderer(Integer.class, mSorNeg);
        jtbMostFoul.setModel(mFoulPos);
        jtbMostFoul.setDefaultRenderer(String.class, mFoulPos);
        jtbMostFoul.setDefaultRenderer(Integer.class, mFoulPos);
        jtbMostFoulNeg.setModel(mFoulNeg);
        jtbMostFoulNeg.setDefaultRenderer(String.class, mFoulNeg);
        jtbMostFoulNeg.setDefaultRenderer(Integer.class, mFoulNeg);

        mjtRanking mRanking = new mjtRanking(v, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getCoachs());
        jtbRanking.setModel(mRanking);
        jtbRanking.setDefaultRenderer(String.class, mRanking);
        jtbRanking.setDefaultRenderer(Integer.class, mRanking);

        setColumnSize(jtbRanking);
        setColumnSize(jtbMostFoulNeg);
        setColumnSize(jtbMostFoul);
        setColumnSize(jtbMostSorNeg);
        setColumnSize(jtbMostSor);
        setColumnSize(jtbMostTdNeg);
        setColumnSize(jtbMostTd);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        jtbRanking = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtbMostTd = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtbMostSor = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtbMostFoul = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtbMostTdNeg = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtbMostSorNeg = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtbMostFoulNeg = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jbtGeneral = new javax.swing.JButton();
        jbtScorePos = new javax.swing.JButton();
        jbtScoreNeg = new javax.swing.JButton();
        jbtSorPos = new javax.swing.JButton();
        jbtSorNeg = new javax.swing.JButton();
        jbtFoulPos = new javax.swing.JButton();
        jbtFoulNeg = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

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

        jTabbedPane1.addTab("Matchs", jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Classement général"));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(466, 300));

        jtbRanking.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jtbRanking);

        jSplitPane1.setBottomComponent(jScrollPane2);

        jPanel4.setLayout(new java.awt.GridLayout(2, 3));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Scoreur"));

        jtbMostTd.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jtbMostTd);

        jPanel4.add(jScrollPane4);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Desctructeur"));

        jtbMostSor.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jtbMostSor);

        jPanel4.add(jScrollPane5);

        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("Crampon"));

        jtbMostFoul.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jtbMostFoul);

        jPanel4.add(jScrollPane6);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder("Passoire"));

        jtbMostTdNeg.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jtbMostTdNeg);

        jPanel4.add(jScrollPane8);

        jScrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder("Punching-Ball"));

        jtbMostSorNeg.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(jtbMostSorNeg);

        jPanel4.add(jScrollPane9);

        jScrollPane10.setBorder(javax.swing.BorderFactory.createTitledBorder("Paillasson"));

        jtbMostFoulNeg.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(jtbMostFoulNeg);

        jPanel4.add(jScrollPane10);

        jSplitPane1.setTopComponent(jPanel4);

        jPanel2.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jbtGeneral.setText("Général");
        jbtGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralActionPerformed(evt);
            }
        });
        jPanel5.add(jbtGeneral);

        jbtScorePos.setText("Scoreur");
        jbtScorePos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtScorePosActionPerformed(evt);
            }
        });
        jPanel5.add(jbtScorePos);

        jbtScoreNeg.setText("Passoire");
        jbtScoreNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtScoreNegActionPerformed(evt);
            }
        });
        jPanel5.add(jbtScoreNeg);

        jbtSorPos.setText("Destructeur");
        jbtSorPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSorPosActionPerformed(evt);
            }
        });
        jPanel5.add(jbtSorPos);

        jbtSorNeg.setText("Punching Ball");
        jbtSorNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSorNegActionPerformed(evt);
            }
        });
        jPanel5.add(jbtSorNeg);

        jbtFoulPos.setText("Crampon");
        jbtFoulPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFoulPosActionPerformed(evt);
            }
        });
        jPanel5.add(jbtFoulPos);

        jbtFoulNeg.setText("Paillasson");
        jbtFoulNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFoulNegActionPerformed(evt);
            }
        });
        jPanel5.add(jbtFoulNeg);

        jPanel2.add(jPanel5, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("Classement", jPanel2);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
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

    private void jbtNextRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextRoundActionPerformed


        // First: Create ranking
        // previous rounds
        Vector<Round> v = new Vector<Round>();
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_tournament.getRounds().get(i).getHeure().before(_round.getHeure())) {
                v.add(_tournament.getRounds().get(i));
            }
        }
        v.add(_round);
        // Match list
        Vector<Match> matchs = new Vector<Match>();
        for (int i = 0; i < v.size(); i++) {
            for (int j = 0; j < v.get(i).getMatchs().size(); j++) {
                matchs.add(v.get(i).getMatchs().get(j));
            }
        }

        // Ranking class
        Vector<CoachRanking> datas = new Vector<CoachRanking>();

        // Build ranking
        for (int i = 0; i < _tournament.getCoachs().size(); i++) {
            Coach c = _tournament.getCoachs().get(i);
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int j = 0; j < matchs.size(); j++) {
                Match m = matchs.get(j);
                value1 += mjtRanking.getValue(c, m, _tournament.getParams()._ranking1);
                value2 += mjtRanking.getValue(c, m, _tournament.getParams()._ranking2);
                value3 += mjtRanking.getValue(c, m, _tournament.getParams()._ranking3);
                value4 += mjtRanking.getValue(c, m, _tournament.getParams()._ranking4);
                value5 += mjtRanking.getValue(c, m, _tournament.getParams()._ranking5);
            }
            datas.add(new CoachRanking(c, value1, value2, value3, value4, value5));
        }

        // Tri
        Collections.sort(datas);

        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r.setHeure(cal.getTime());

        // Résolution des doublons
        if (_tournament.getCoachs().size() - 1 <= _tournament.getRounds().size()) {
            // Affectation des matchs
            for (int i = 0; i < datas.size() / 2; i++) {
                Match m = new Match();
                m._coach1 = datas.get(2 * i).getCoach();
                m._coach2 = datas.get(2 * i + 1).getCoach();
                r.getMatchs().add(m);
            }
            JOptionPane.showMessageDialog(this, "Pas assez de ronde pour éviter des doublons, génération sans gestion des doublons");
        } else {
            Vector<CoachRanking> datas2 = new Vector<CoachRanking>(datas);
            // Première passe de haut en bas
            while (datas2.size() > 0) {
                Match m = new Match();
                m._coach1 = datas2.get(0).getCoach();
                datas2.remove(0);
                for (int i = 0; i < datas2.size(); i++) {
                    boolean have_played = false;
                    for (int j = 0; j < matchs.size(); j++) {
                        if (((matchs.get(j)._coach1 == m._coach1) && (matchs.get(j)._coach2 == datas2.get(i).getCoach())) ||
                                ((matchs.get(j)._coach2 == m._coach1) && (matchs.get(j)._coach1 == datas2.get(i).getCoach()))) {
                            have_played = true;
                        }
                    }
                    if ((!have_played) || (i == datas2.size() - 1)) {
                        m._coach2 = datas2.get(i).getCoach();
                        datas2.remove(i);
                        break;
                    }
                }
                r.getMatchs().add(m);
            }

            datas2 = new Vector<CoachRanking>(datas);
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
                        if ((datas2.get(k).getCoach() == c1) || (datas2.get(k).getCoach() == c2)) {
                            datas2.remove(k);
                            k--;
                        }
                    }

                } else {
                    for (int k = 0; k < datas2.size(); k++) {
                        if ((datas2.get(k).getCoach() == c1) || (datas2.get(k).getCoach() == c2)) {
                            datas2.remove(k);
                            k--;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_tournament.getRounds().get(i).getHeure().after(_round.getHeure())) {
                _tournament.getRounds().remove(i);
                i--;
            }
        }

        _tournament.getRounds().add(r);
        for (int i = MainFrame.getMainFrame().jtpMain.getTabCount() - 1; i >= 0; i--) {
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
    }//GEN-LAST:event_jbtNextRoundActionPerformed

    private void jbtShowMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowMatchesActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRound jdg = new jdgRound(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, false);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtShowMatchesActionPerformed

    private void jbtShowResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowResultsActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRound jdg = new jdgRound(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, true);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtShowResultsActionPerformed

    private void jbtGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_GENERAL);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtGeneralActionPerformed

    private void jbtScoreNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtScoreNegActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_SCORED);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtScoreNegActionPerformed

    private void jbtScorePosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtScorePosActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_SCORER);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtScorePosActionPerformed

    private void jbtSorPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSorPosActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_DESTROYER);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtSorPosActionPerformed

    private void jbtSorNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSorNegActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_DESTROYED);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtSorNegActionPerformed

    private void jbtFoulPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFoulPosActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_FOULER);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtFoulPosActionPerformed

    private void jbtFoulNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFoulNegActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, _round, i + 1, _tournament, jdgRanking.RANKING_FOULED);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtFoulNegActionPerformed

    private void jbtDeleteRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDeleteRoundActionPerformed

        if (JOptionPane.showConfirmDialog(this, "Etes vous certain de vouloir effacer la ronde courante ?", "Effacer une ronde", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            _tournament.getRounds().remove(_round);
            for (int i = MainFrame.getMainFrame().jtpMain.getTabCount() - 1; i >= 0; i--) {
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtDeleteRound;
    private javax.swing.JButton jbtFoulNeg;
    private javax.swing.JButton jbtFoulPos;
    private javax.swing.JButton jbtGeneral;
    private javax.swing.JButton jbtNextRound;
    private javax.swing.JButton jbtScoreNeg;
    private javax.swing.JButton jbtScorePos;
    private javax.swing.JButton jbtShowMatches;
    private javax.swing.JButton jbtShowResults;
    private javax.swing.JButton jbtSorNeg;
    private javax.swing.JButton jbtSorPos;
    private javax.swing.JTable jtbMatches;
    private javax.swing.JTable jtbMostFoul;
    private javax.swing.JTable jtbMostFoulNeg;
    private javax.swing.JTable jtbMostSor;
    private javax.swing.JTable jtbMostSorNeg;
    private javax.swing.JTable jtbMostTd;
    private javax.swing.JTable jtbMostTdNeg;
    private javax.swing.JTable jtbRanking;
    // End of variables declaration//GEN-END:variables
}
