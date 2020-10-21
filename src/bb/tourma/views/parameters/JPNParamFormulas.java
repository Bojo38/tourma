/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.views.parameters;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import bb.tourma.data.CoachMatch;
import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.ETeamPairing;
import bb.tourma.data.Round;
import bb.tourma.data.Tournament;
import bb.tourma.data.Value;
import bb.tourma.languages.Translate;
import bb.tourma.tableModel.MjtFormulas;


/**
 *
 * @author WFMJ7631
 */
public final class JPNParamFormulas extends javax.swing.JPanel {

    private Tournament mTournament;

    public JPNParamFormulas() {

        mTournament = Tournament.getTournament();
        initComponents();

    }

    private final static String CS_Formula = "FORMULE";
    private final static String CS_Possible_Variables="Variables possibles";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel16 = new javax.swing.JPanel();
        jbtAddFormula = new javax.swing.JButton();
        jbtRemoveFormula = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtbFormulas = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxpDescription = new javax.swing.JTextPane();

        setLayout(new java.awt.BorderLayout());

        jbtAddFormula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Add.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bb/tourma/languages/language"); // NOI18N
        jbtAddFormula.setText(bundle.getString("AJOUTER FORMULA")); // NOI18N
        jbtAddFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddFormulaActionPerformed(evt);
            }
        });
        jPanel16.add(jbtAddFormula);

        jbtRemoveFormula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Close.png"))); // NOI18N
        jbtRemoveFormula.setText(bundle.getString("RETIRER FORMULE")); // NOI18N
        jbtRemoveFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveFormulaActionPerformed(evt);
            }
        });
        jPanel16.add(jbtRemoveFormula);

        add(jPanel16, java.awt.BorderLayout.PAGE_START);

        jtbFormulas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jtbFormulas);

        add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jtxpDescription.setEditable(false);
        jScrollPane1.setViewportView(jtxpDescription);

        add(jScrollPane1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddFormulaActionPerformed
        final int nb = Tournament.getTournament().getParams().getFormulaCount();
        final Formula f = new Formula(
                Translate.translate(CS_Formula)
                + " " + Integer.toString(nb));
        f.setFormula(Tournament.getTournament().getParams().getCriteria(0).getAccronym()+"1");
        Tournament.getTournament().getParams().addFormula(f);
        
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            final Round r = mTournament.getRound(i);
            for (int j = 0; j < r.getCoachMatchs().size(); j++) {
                final CoachMatch m = r.getCoachMatchs().get(j);
                m.putValue(f,new Value(f));
            }
        }
        update();
    }//GEN-LAST:event_jbtAddFormulaActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveFormulaActionPerformed
        if (jtbFormulas.getSelectedRow() < mTournament.getParams().getFormulaCount()) {
            final Formula form = mTournament.getParams().getFormula(jtbFormulas.getSelectedRow());
            for (int i = 0; i < mTournament.getRoundsCount(); i++) {
                final Round r = mTournament.getRound(i);
                for (int j = 0; j < r.getCoachMatchs().size(); j++) {
                    final CoachMatch m = r.getCoachMatchs().get(j);
                    m.removeValue(form);
                }
            }
            mTournament.getParams().removeFormula(jtbFormulas.getSelectedRow());
        }
        repaint();
    }//GEN-LAST:event_jbtRemoveFormulaActionPerformed

    /**
     * Update Panel
     */
    public void update() {

        final boolean bTourStarted = false;//mTournament.getRoundsCount() > 0;
        jtbFormulas.setModel(new MjtFormulas(mTournament));
       
        if (Tournament.getTournament().isClient()) {
            jtbFormulas.setEnabled(false);
        }

        jbtAddFormula.setEnabled(!bTourStarted && !Tournament.getTournament().isClient());
        jbtRemoveFormula.setEnabled(!bTourStarted && !Tournament.getTournament().isClient());

        String description=Translate.translate(CS_Possible_Variables);
        description+="\n";
        for (int i=0; i<Tournament.getTournament().getParams().getCriteriaCount(); i++)
        {
            Criterion crit=Tournament.getTournament().getParams().getCriteria(i);
            description+=crit.getAccronym()+"1 / "+crit.getAccronym()+"2\n";
        }
        jtxpDescription.setText(description);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton jbtAddFormula;
    private javax.swing.JButton jbtRemoveFormula;
    private javax.swing.JTable jtbFormulas;
    private javax.swing.JTextPane jtxpDescription;
    // End of variables declaration//GEN-END:variables

}
