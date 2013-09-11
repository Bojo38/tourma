/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgCoach.java
 *
 * Created on 10 mai 2010, 19:37:53
 */
package tourma;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import tourma.data.Tournament;
import tourma.data.Round;
import tourma.data.Team;
import tourma.tableModel.mjtPairs;
import tourma.views.jdgRound;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class jdgTeamPairing extends javax.swing.JDialog {

    ArrayList<Team> mTeams1;
    ArrayList<Team> mTeams2;
    Round mRound;
    ArrayList<Boolean> mPairsDone;

    /**
     * Creates new form jdgCoach
     */
    public jdgTeamPairing(final java.awt.Frame parent, final boolean modal, final ArrayList<Team> teams1, final ArrayList<Team> teams2, final Round round) {
        super(parent, modal);
        initComponents();

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();
        if (dmode != null) {
            final int screenWidth = dmode.getWidth();
            final int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        mTeams1 = teams1;
        mTeams2 = teams2;
        mRound = round;

        mPairsDone = new ArrayList<Boolean>();
        for (int i = 0; i < mTeams1.size(); i++) {
            mPairsDone.add(false);
        }

        update();

    }

    private void update() {

        final mjtPairs model = new mjtPairs(mTeams1, mTeams2, mPairsDone);
        jtPairs.setModel(model);
        jtPairs.setDefaultRenderer(String.class, model);
        jtPairs.setDefaultRenderer(Integer.class, model);

        boolean allOK = true;
        for (int i = 0; i < mPairsDone.size(); i++) {
            allOK = allOK && mPairsDone.get(i).booleanValue();
        }
        jbtOK.setEnabled(allOK);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtPairs = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtSeeMatches = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jbtSelect = new javax.swing.JButton();
        jbtRemove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jtPairs.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtPairs);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE); // NOI18N
        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel2.add(jbtOK);

        jbtSeeMatches.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Zoom.png"))); // NOI18N
        jbtSeeMatches.setText(bundle.getString("SeeMatchsKey")); // NOI18N
        jbtSeeMatches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSeeMatchesActionPerformed(evt);
            }
        });
        jPanel2.add(jbtSeeMatches);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jbtSelect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Team.png"))); // NOI18N
        jbtSelect.setText(bundle.getString("SelectMatchsKeys")); // NOI18N
        jbtSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSelectActionPerformed(evt);
            }
        });
        jPanel4.add(jbtSelect);

        jbtRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemove.setText(bundle.getString("CancelSelectionKey")); // NOI18N
        jbtRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveActionPerformed(evt);
            }
        });
        jPanel4.add(jbtRemove);

        jPanel3.add(jPanel4, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel3, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed

        this.setVisible(false);

    }//GEN-LAST:event_jbtOKActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSelectActionPerformed
        if (jtPairs.getSelectedRow() >= 0) {
            final jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, mTeams1.get(jtPairs.getSelectedRow()), mTeams2.get(jtPairs.getSelectedRow()), mRound);
            jdg.setVisible(true);
            mPairsDone.add(jtPairs.getSelectedRow(),true);
        }
        update();
}//GEN-LAST:event_jbtSelectActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveActionPerformed

        if (jtPairs.getSelectedRow() >= 0) {
            mPairsDone.add(jtPairs.getSelectedRow(),false);

            final Team t1 = mTeams1.get(jtPairs.getSelectedRow());
            int i = 0;
            while (i < mRound.getMatchs().size()) {
                if ((mRound.getMatchs().get(i).mCoach1.mTeamMates == t1)
                        || (mRound.getMatchs().get(i).mCoach2.mTeamMates == t1)) {
                    mRound.getMatchs().remove(i);
                    i = 0;
                } else {
                    i++;
                }
            }
        }
        update();
}//GEN-LAST:event_jbtRemoveActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtSeeMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSeeMatchesActionPerformed

        int nb = 1;
        for (int i = 0; i < Tournament.getTournament().getRounds().size(); i++) {
            if (Tournament.getTournament().getRounds().get(i).getHour().before(mRound.getHour())) {
                nb++;
            }
        }
        final jdgRound jdg = new jdgRound(MainFrame.getMainFrame(), true, mTeams1, mTeams2, nb, Tournament.getTournament());
        jdg.setVisible(true);
    }//GEN-LAST:event_jbtSeeMatchesActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtOK;
    private javax.swing.JButton jbtRemove;
    private javax.swing.JButton jbtSeeMatches;
    private javax.swing.JButton jbtSelect;
    private javax.swing.JTable jtPairs;
    // End of variables declaration//GEN-END:variables
}
