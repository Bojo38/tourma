/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPNTeamRound.java
 *
 * Created on 20 juil. 2010, 10:47:49
 */
package tourma;

import tourma.tableModel.mjtAnnexRank;
import java.awt.FontMetrics;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTable;
import tourma.data.Criteria;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.tableModel.mjtAnnexRankClan;
import tourma.tableModel.mjtRankingClan;
import tourma.views.report.jdgGlobal;
import tourma.views.report.jdgRanking;

/**
 *
 * @author Frederic Berger
 */
public class JPNClanRound extends javax.swing.JPanel {

    Round _round;
    Tournament _tournament;
    JTable _jtbTeamMatch = null;

    /** Creates new form JPNTeamRound */
    public JPNClanRound(Round r, Tournament t) {
        initComponents();
        _round = r;
        _tournament = t;

        for (int i = 0; i < _tournament.getParams()._criterias.size(); i++) {
            Criteria criteria = _tournament.getParams()._criterias.get(i);
            JPNAnnexRanking jpn = new JPNAnnexRanking(criteria._name, criteria, _tournament, _round, true, false);
            jtpAnnexRank.add(criteria._name, jpn);
        }

        update();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnClan = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtbMostTdClan = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        jtbMostSorClan = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        jtbMostFoulClan = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        jtbMostPasClan = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        jtbMostIntClan = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        jtbMostTdNegClan = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        jtbMostSorNegClan = new javax.swing.JTable();
        jScrollPane15 = new javax.swing.JScrollPane();
        jtbMostFoulNegClan = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        jtbMostPasNegClan = new javax.swing.JTable();
        jScrollPane19 = new javax.swing.JScrollPane();
        jtbMostIntNegClan = new javax.swing.JTable();
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

        jpnClan.setLayout(new java.awt.BorderLayout());

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel7.setLayout(new java.awt.GridLayout(2, 5));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("TouchdownForKey"))); // NOI18N

        jtbMostTdClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(jtbMostTdClan);

        jPanel7.add(jScrollPane7);

        jScrollPane11.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("CasualtiesKey"))); // NOI18N

        jtbMostSorClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane11.setViewportView(jtbMostSorClan);

        jPanel7.add(jScrollPane11);

        jScrollPane12.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("FoulsForKey"))); // NOI18N

        jtbMostFoulClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane12.setViewportView(jtbMostFoulClan);

        jPanel7.add(jScrollPane12);

        jScrollPane16.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("PassesForKey"))); // NOI18N

        jtbMostPasClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane16.setViewportView(jtbMostPasClan);

        jPanel7.add(jScrollPane16);

        jScrollPane18.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("BestInterceptorKey"))); // NOI18N

        jtbMostIntClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane18.setViewportView(jtbMostIntClan);

        jPanel7.add(jScrollPane18);

        jScrollPane13.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("TouchdownAgainstKey"))); // NOI18N

        jtbMostTdNegClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane13.setViewportView(jtbMostTdNegClan);

        jPanel7.add(jScrollPane13);

        jScrollPane14.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("CasualtiesAgainstKey"))); // NOI18N

        jtbMostSorNegClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane14.setViewportView(jtbMostSorNegClan);

        jPanel7.add(jScrollPane14);

        jScrollPane15.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("FoulsAgainstKey"))); // NOI18N

        jtbMostFoulNegClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane15.setViewportView(jtbMostFoulNegClan);

        jPanel7.add(jScrollPane15);

        jScrollPane17.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("PassesAgainstKey"))); // NOI18N

        jtbMostPasNegClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane17.setViewportView(jtbMostPasNegClan);

        jPanel7.add(jScrollPane17);

        jScrollPane19.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("InterceptionsAgainstKey"))); // NOI18N

        jtbMostIntNegClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane19.setViewportView(jtbMostIntNegClan);

        jPanel7.add(jScrollPane19);

        jSplitPane2.setTopComponent(jPanel7);

        jpnClan.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        add(jpnClan, java.awt.BorderLayout.CENTER);

        jSplitPane1.setDividerLocation(600);

        jPanel2.setLayout(new java.awt.BorderLayout());

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

        jbtGeneralClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/printer.png"))); // NOI18N
        jbtGeneralClan.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneralClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralClanActionPerformed(evt);
            }
        });
        jPanel3.add(jbtGeneralClan);

        jbtGGlobalClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/printer.png"))); // NOI18N
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

    private void jbtGeneralClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralClanActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                mjtRankingClan model = new mjtRankingClan(i, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getDisplayClans());
                jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, "General par Clan", i + 1, _tournament, model, 0);
                jdg.setVisible(true);
                break;
            }
        }
}//GEN-LAST:event_jbtGeneralClanActionPerformed

    private void jbtGGlobalClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGGlobalClanActionPerformed
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_round == _tournament.getRounds().get(i)) {
                mjtRankingClan model = new mjtRankingClan(i, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getDisplayClans());
                HashMap<Criteria, mjtAnnexRank> annexForRankings = new HashMap<Criteria, mjtAnnexRank>();
                HashMap<Criteria, mjtAnnexRank> annexAgainstRankings = new  HashMap<Criteria, mjtAnnexRank>();
                for (int j = 0; j < _tournament.getParams()._criterias.size(); j++) {
                    Criteria crit=_tournament.getParams()._criterias.get(j);
                    mjtAnnexRank annex=new mjtAnnexRankClan(i, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                            _tournament.getDisplayClans(), true,
                            _tournament.getParams()._ranking1, _tournament.getParams()._ranking2,
                            _tournament.getParams()._ranking3, _tournament.getParams()._ranking4,
                            _tournament.getParams()._ranking5);
                    annexForRankings.put(crit,annex);
                    annex=new mjtAnnexRankClan(i, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                            _tournament.getDisplayClans(), true,
                            _tournament.getParams()._ranking1, _tournament.getParams()._ranking2,
                            _tournament.getParams()._ranking3, _tournament.getParams()._ranking4,
                            _tournament.getParams()._ranking5);
                    annexAgainstRankings.put(crit,annex);
                }
                jdgGlobal jdg = new jdgGlobal(MainFrame.getMainFrame(), true, i + 1, _tournament, model, annexForRankings, annexAgainstRankings, false, false);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtGGlobalClanActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JButton jbtGGlobalClan;
    private javax.swing.JButton jbtGeneralClan;
    private javax.swing.JPanel jpnClan;
    private javax.swing.JTable jtbMostFoulClan;
    private javax.swing.JTable jtbMostFoulNegClan;
    private javax.swing.JTable jtbMostIntClan;
    private javax.swing.JTable jtbMostIntNegClan;
    private javax.swing.JTable jtbMostPasClan;
    private javax.swing.JTable jtbMostPasNegClan;
    private javax.swing.JTable jtbMostSorClan;
    private javax.swing.JTable jtbMostSorNegClan;
    private javax.swing.JTable jtbMostTdClan;
    private javax.swing.JTable jtbMostTdNegClan;
    private javax.swing.JTable jtbRankingClan;
    private javax.swing.JTabbedPane jtpAnnexRank;
    // End of variables declaration//GEN-END:variables

    public void update() {

        Vector<Round> v = new Vector<Round>();
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_tournament.getRounds().get(i).getHeure().before(_round.getHeure())) {
                v.add(_tournament.getRounds().get(i));
            }
        }
        v.add(_round);

        mjtRankingClan mRankingClan = null;
        mRankingClan = new mjtRankingClan(v.size() - 1, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getDisplayClans());
        jtbRankingClan.setModel(mRankingClan);
        jtbRankingClan.setDefaultRenderer(String.class, mRankingClan);
        jtbRankingClan.setDefaultRenderer(Integer.class, mRankingClan);
        setColumnSize(jtbRankingClan);

        for (int i = 0; i < jtpAnnexRank.getComponentCount(); i++) {
            ((JPNAnnexRanking) jtpAnnexRank.getComponent(i)).update();
        }
    }

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
}
