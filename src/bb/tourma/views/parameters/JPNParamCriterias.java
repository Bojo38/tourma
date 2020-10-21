/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.views.parameters;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import bb.tourma.data.CoachMatch;
import bb.tourma.data.Criterion;
import bb.tourma.data.ETeamPairing;
import bb.tourma.data.Round;
import bb.tourma.data.Tournament;
import bb.tourma.data.Value;
import bb.tourma.languages.Translate;
import bb.tourma.tableModel.MjtCriterias;
import bb.tourma.tableModel.MjtCriteriasIndivBonus;
import bb.tourma.tableModel.MjtCriteriasTeamBonus;

/**
 *
 * @author WFMJ7631
 */
public final class JPNParamCriterias extends javax.swing.JPanel {

    private Tournament mTournament;

    /**
     * Creates new form JPNParamCriterias
     */
    public JPNParamCriterias() {

        mTournament = Tournament.getTournament();
        initComponents();

        jspCoef.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspCoefStateChanged(evt);
            }
        });

        if ((!mTournament.getParams().isTeamTournament())
                || ((mTournament.getParams().isTeamTournament())
                && (mTournament.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING))) {
            jtpCriterias.remove(jspTeamBonuses);
        }
    }

    private final static String CS_Criteria = "CRITÈRE";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel16 = new javax.swing.JPanel();
        jbtAddCriteria = new javax.swing.JButton();
        jbtRemoveCriteria = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jcxTableBonus = new javax.swing.JCheckBox();
        jcxTableCoefPerRound = new javax.swing.JCheckBox();
        jlbCoef = new javax.swing.JLabel();
        jspCoef = new javax.swing.JSpinner();
        jtpCriterias = new javax.swing.JTabbedPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtbCriteria = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtbPlayerBonuses = new javax.swing.JTable();
        jspTeamBonuses = new javax.swing.JScrollPane();
        jtbTeamBonus = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jbtAddCriteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Add.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bb/tourma/languages/language"); // NOI18N
        jbtAddCriteria.setText(bundle.getString("AJOUTER CRITÈRE")); // NOI18N
        jbtAddCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddCriteriaActionPerformed(evt);
            }
        });
        jPanel16.add(jbtAddCriteria);

        jbtRemoveCriteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Close.png"))); // NOI18N
        jbtRemoveCriteria.setText(bundle.getString("RETIRER CRITÈRE")); // NOI18N
        jbtRemoveCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveCriteriaActionPerformed(evt);
            }
        });
        jPanel16.add(jbtRemoveCriteria);

        add(jPanel16, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("TableBonus"))); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        jcxTableBonus.setText(bundle.getString("TablePoints")); // NOI18N
        jcxTableBonus.setToolTipText(bundle.getString("PointsTableTooltip")); // NOI18N
        jcxTableBonus.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcxTableBonus.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcxTableBonus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxTableBonusActionPerformed(evt);
            }
        });
        jPanel1.add(jcxTableBonus);

        jcxTableCoefPerRound.setText(bundle.getString("TableBonusPonderation")); // NOI18N
        jcxTableCoefPerRound.setToolTipText(bundle.getString("BonusPointPonderationTooltip")); // NOI18N
        jcxTableCoefPerRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxTableCoefPerRoundActionPerformed(evt);
            }
        });
        jPanel1.add(jcxTableCoefPerRound);

        jlbCoef.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbCoef.setText(bundle.getString("TablePonderation")); // NOI18N
        jPanel1.add(jlbCoef);

        jspCoef.setModel(new javax.swing.SpinnerNumberModel(1.0,0.0 ,1000.0,0.01));
        jspCoef.setEditor(new JSpinner.NumberEditor(jspCoef));
        jspCoef.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspCoefStateChanged(evt);
            }
        });
        jPanel1.add(jspCoef);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jtbCriteria.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jtbCriteria);

        jtpCriterias.addTab(bundle.getString("Points"), jScrollPane5); // NOI18N

        jtbPlayerBonuses.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jtbPlayerBonuses);

        jtpCriterias.addTab(bundle.getString("BonusIndiv"), jScrollPane6); // NOI18N

        jtbTeamBonus.setModel(new javax.swing.table.DefaultTableModel(
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
        jspTeamBonuses.setViewportView(jtbTeamBonus);

        jtpCriterias.addTab(bundle.getString("TeamBonus"), jspTeamBonuses); // NOI18N

        add(jtpCriterias, java.awt.BorderLayout.CENTER);
        jtpCriterias.getAccessibleContext().setAccessibleName(bundle.getString("IndivBonus")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddCriteriaActionPerformed
        final int nb = Tournament.getTournament().getParams().getCriteriaCount();
        final Criterion c = new Criterion(
                Translate.translate(CS_Criteria)
                + " " + Integer.toString(nb));
        Tournament.getTournament().getParams().addCriteria(c);
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            final Round r = mTournament.getRound(i);
            for (int j = 0; j < r.getCoachMatchs().size(); j++) {
                final CoachMatch m = r.getCoachMatchs().get(j);
                m.putValue(c, new Value(c));
            }
        }
        update();
    }//GEN-LAST:event_jbtAddCriteriaActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveCriteriaActionPerformed
        if ((jtbCriteria.getSelectedRow() > 1) && (jtbCriteria.getSelectedRow() < mTournament.getParams().getCriteriaCount())) {
            final Criterion crit = mTournament.getParams().getCriteria(jtbCriteria.getSelectedRow());
            for (int i = 0; i < mTournament.getRoundsCount(); i++) {
                final Round r = mTournament.getRound(i);
                for (int j = 0; j < r.getCoachMatchs().size(); j++) {
                    final CoachMatch m = r.getCoachMatchs().get(j);
                    m.removeValue(crit);
                }
            }
            mTournament.getParams().removeCriteria(jtbCriteria.getSelectedRow());
        }
        repaint();
    }//GEN-LAST:event_jbtRemoveCriteriaActionPerformed

    private void jcxTableCoefPerRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxTableCoefPerRoundActionPerformed
        Tournament.getTournament().getParams().setTableBonusPerRound(jcxTableCoefPerRound.isSelected());
        update();
    }//GEN-LAST:event_jcxTableCoefPerRoundActionPerformed

    private void jcxTableBonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxTableBonusActionPerformed
        Tournament.getTournament().getParams().setTableBonus(jcxTableBonus.isSelected());
        update();
    }//GEN-LAST:event_jcxTableBonusActionPerformed

    private void jspCoefStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspCoefStateChanged
        Tournament.getTournament().getParams().setTableBonusCoef((Double) jspCoef.getValue());
        update();
    }//GEN-LAST:event_jspCoefStateChanged

    /**
     * Update Panel
     */
    public void update() {

        final boolean bTourStarted = false;//mTournament.getRoundsCount() > 0;
        jtbCriteria.setModel(new MjtCriterias(mTournament));
        jtbPlayerBonuses.setModel(new MjtCriteriasIndivBonus(mTournament));

        if (!Tournament.getTournament().getParams().isTeamTournament()) {
            jtbTeamBonus.setEnabled(false);
        } else {
            jtbTeamBonus.setModel(new MjtCriteriasTeamBonus(mTournament));
        }

        if (Tournament.getTournament().isClient()) {
            jtbCriteria.setEnabled(false);
            jcxTableBonus.setEnabled(false);
            jcxTableCoefPerRound.setEnabled(false);
            jspCoef.setEnabled(false);
        }

        jbtAddCriteria.setEnabled(!bTourStarted && !Tournament.getTournament().isClient());
        jbtRemoveCriteria.setEnabled(!bTourStarted && !Tournament.getTournament().isClient());

        jcxTableBonus.setSelected(mTournament.getParams().isTableBonus());
        jcxTableCoefPerRound.setSelected(mTournament.getParams().isTableBonusPerRound());

        jspCoef.setValue(Tournament.getTournament().getParams().getTableBonusCoef());
        jspCoef.setEnabled(mTournament.getParams().isTableBonus());
        jlbCoef.setEnabled(mTournament.getParams().isTableBonus());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JButton jbtAddCriteria;
    private javax.swing.JButton jbtRemoveCriteria;
    private javax.swing.JCheckBox jcxTableBonus;
    private javax.swing.JCheckBox jcxTableCoefPerRound;
    private javax.swing.JLabel jlbCoef;
    private javax.swing.JSpinner jspCoef;
    private javax.swing.JScrollPane jspTeamBonuses;
    private javax.swing.JTable jtbCriteria;
    private javax.swing.JTable jtbPlayerBonuses;
    private javax.swing.JTable jtbTeamBonus;
    private javax.swing.JTabbedPane jtpCriterias;
    // End of variables declaration//GEN-END:variables

}
