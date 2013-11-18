/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.parameters;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import tourma.data.Clan;
import tourma.data.Tournament;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class JPNParamClan extends javax.swing.JPanel {

    Tournament mTournament;

    /**
     * Creates new form JPNParamClan
     */
    public JPNParamClan() {

        mTournament = Tournament.getTournament();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked","PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jlbActivateClans = new javax.swing.JLabel();
        jcxActivatesClans = new javax.swing.JCheckBox();
        jlbTeamMatesNumber = new javax.swing.JLabel();
        jspTeamMembers = new javax.swing.JSpinner();
        jlbClansMembersNUmbers = new javax.swing.JLabel();
        jcxAvoidFirstMatch = new javax.swing.JCheckBox();
        jlbAvoidClansMembersMatch = new javax.swing.JLabel();
        jcxAvoidMatch = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jbtAddClan = new javax.swing.JButton();
        jbtEditClan = new javax.swing.JButton();
        jbtRemoveClan = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlsClans = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jlsCoachList = new javax.swing.JList();

        setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.GridLayout(4, 2));

        jlbActivateClans.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jlbActivateClans.setText(bundle.getString("EnableClansKey")); // NOI18N
        jPanel12.add(jlbActivateClans);

        jcxActivatesClans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxActivatesClansActionPerformed(evt);
            }
        });
        jPanel12.add(jcxActivatesClans);

        jlbTeamMatesNumber.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbTeamMatesNumber.setText(bundle.getString("TeamMatesNumber")); // NOI18N
        jlbTeamMatesNumber.setEnabled(false);
        jPanel12.add(jlbTeamMatesNumber);

        jspTeamMembers.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(3), Integer.valueOf(1), null, Integer.valueOf(1)));
        jspTeamMembers.setEnabled(false);
        jspTeamMembers.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspTeamMembersStateChanged(evt);
            }
        });
        jPanel12.add(jspTeamMembers);

        jlbClansMembersNUmbers.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbClansMembersNUmbers.setText(bundle.getString("AvoidMembersMatchsFirstRoundKey")); // NOI18N
        jlbClansMembersNUmbers.setEnabled(false);
        jPanel12.add(jlbClansMembersNUmbers);

        jcxAvoidFirstMatch.setEnabled(false);
        jcxAvoidFirstMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxAvoidFirstMatchActionPerformed(evt);
            }
        });
        jPanel12.add(jcxAvoidFirstMatch);

        jlbAvoidClansMembersMatch.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbAvoidClansMembersMatch.setText(bundle.getString("AvoidCLansMemberMatchKey")); // NOI18N
        jlbAvoidClansMembersMatch.setEnabled(false);
        jPanel12.add(jlbAvoidClansMembersMatch);

        jcxAvoidMatch.setEnabled(false);
        jcxAvoidMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxAvoidMatchActionPerformed(evt);
            }
        });
        jPanel12.add(jcxAvoidMatch);

        add(jPanel12, java.awt.BorderLayout.NORTH);

        jbtAddClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAddClan.setText(bundle.getString("AddKey")); // NOI18N
        jbtAddClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddClanActionPerformed(evt);
            }
        });
        jPanel13.add(jbtAddClan);

        jbtEditClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Swap.png"))); // NOI18N
        jbtEditClan.setText(bundle.getString("EditKey")); // NOI18N
        jbtEditClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditClanActionPerformed(evt);
            }
        });
        jPanel13.add(jbtEditClan);

        jbtRemoveClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemoveClan.setText(bundle.getString("RemoveKey")); // NOI18N
        jbtRemoveClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveClanActionPerformed(evt);
            }
        });
        jPanel13.add(jbtRemoveClan);

        add(jPanel13, java.awt.BorderLayout.SOUTH);

        jPanel14.setLayout(new java.awt.GridLayout(1, 2));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ClansKey"))); // NOI18N

        jlsClans.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlsClans.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlsClans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlsClansMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jlsClans);

        jPanel14.add(jScrollPane3);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("CLansCoachsKey"))); // NOI18N

        jlsCoachList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlsCoachList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jlsCoachList);

        jPanel14.add(jScrollPane4);

        add(jPanel14, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcxActivatesClansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxActivatesClansActionPerformed
    final boolean clansEnable = jcxActivatesClans.isSelected();

    jlbAvoidClansMembersMatch.setEnabled(clansEnable);
    jlbClansMembersNUmbers.setEnabled(clansEnable);
    jlbTeamMatesNumber.setEnabled(clansEnable);

    jspTeamMembers.setEnabled(clansEnable);
    jcxAvoidFirstMatch.setEnabled(clansEnable);
    jcxAvoidMatch.setEnabled(clansEnable);

    jbtAddClan.setEnabled(clansEnable);
    jbtRemoveClan.setEnabled(clansEnable);
    jbtEditClan.setEnabled(clansEnable);
    jlsClans.setEnabled(clansEnable);

    mTournament.getParams().mEnableClans = clansEnable;

    update();
    }//GEN-LAST:event_jcxActivatesClansActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jspTeamMembersStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspTeamMembersStateChanged
    mTournament.getParams().mTeamMatesNumber = (Integer) jspTeamMembers.getValue();
    }//GEN-LAST:event_jspTeamMembersStateChanged
 @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcxAvoidFirstMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxAvoidFirstMatchActionPerformed
        mTournament.getParams().mAvoidClansFirstMatch = jcxAvoidFirstMatch.isSelected();
    }//GEN-LAST:event_jcxAvoidFirstMatchActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcxAvoidMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxAvoidMatchActionPerformed
    mTournament.getParams().mAvoidClansMatch = jcxAvoidMatch.isSelected();
    }//GEN-LAST:event_jcxAvoidMatchActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddClanActionPerformed

    final String enterClanName = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("EnterClanNameKey");
    final String clanName = JOptionPane.showInputDialog(this, enterClanName);
    if (clanName != null) {
        if (!clanName.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""))) {
            mTournament.getClans().add(new Clan(clanName));
        }
    }
    update();
    }//GEN-LAST:event_jbtAddClanActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtEditClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditClanActionPerformed
    final String enterClanName = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("EnterClanNameKey");
    final String clanName = (String) jlsClans.getSelectedValue();
    final String newClanName = JOptionPane.showInputDialog(this, enterClanName, clanName);
    if (!clanName.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""))) {
        mTournament.getClans().get(jlsClans.getSelectedIndex()).mName = newClanName;
    }
    update();
    }//GEN-LAST:event_jbtEditClanActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveClanActionPerformed
    final int index = jlsClans.getSelectedIndex();

    if (index > 0) {
        mTournament.getClans().remove(index);
    }
    update();
    }//GEN-LAST:event_jbtRemoveClanActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jlsClansMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlsClansMouseClicked
    update();
    }//GEN-LAST:event_jlsClansMouseClicked

    public void update() {

        final boolean bTourStarted = mTournament.getRounds().size() > 0;
        jcxActivatesClans.setSelected(!bTourStarted && !mTournament.getParams().mTeamTournament);
        jcxActivatesClans.setEnabled(!bTourStarted && !mTournament.getParams().mTeamTournament);

        final boolean clansEnable = (mTournament.getParams().mEnableClans) && (!mTournament.getParams().mTeamTournament);
        jlbActivateClans.setEnabled(!mTournament.getParams().mTeamTournament);
        jlbAvoidClansMembersMatch.setEnabled(clansEnable);
        jlbClansMembersNUmbers.setEnabled(clansEnable);
        jlbTeamMatesNumber.setEnabled(clansEnable);

        jspTeamMembers.setEnabled(clansEnable);
        jcxAvoidFirstMatch.setEnabled(clansEnable);
        jcxAvoidMatch.setEnabled(clansEnable);

        jbtAddClan.setEnabled(clansEnable && !bTourStarted);
        jbtRemoveClan.setEnabled(clansEnable && !bTourStarted);
        jbtEditClan.setEnabled(clansEnable && !bTourStarted);
        jlsClans.setEnabled(clansEnable && !bTourStarted);

        jcxActivatesClans.setSelected(clansEnable);
        jcxAvoidFirstMatch.setSelected(mTournament.getParams().mAvoidClansFirstMatch);
        jcxAvoidMatch.setSelected(mTournament.getParams().mAvoidClansMatch);
        jspTeamMembers.setValue(mTournament.getParams().mTeamMatesNumber);

        final int selectedClan = jlsClans.getSelectedIndex();
        final DefaultListModel coachListModel = new DefaultListModel();
        if (selectedClan >= 0) {

            final String clanName = mTournament.getClans().get(selectedClan).mName;
            for (int i = 0; i < mTournament.getCoachs().size(); i++) {
                if (clanName.equals(mTournament.getCoachs().get(i).mClan.mName)) {
                    coachListModel.addElement(mTournament.getCoachs().get(i).mName);
                }
            }
        }
        jlsCoachList.setModel(coachListModel);

        final DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < mTournament.getClans().size(); i++) {
            listModel.addElement(mTournament.getClans().get(i).mName);
        }

        jlsClans.setModel(listModel);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jbtAddClan;
    private javax.swing.JButton jbtEditClan;
    private javax.swing.JButton jbtRemoveClan;
    private javax.swing.JCheckBox jcxActivatesClans;
    private javax.swing.JCheckBox jcxAvoidFirstMatch;
    private javax.swing.JCheckBox jcxAvoidMatch;
    private javax.swing.JLabel jlbActivateClans;
    private javax.swing.JLabel jlbAvoidClansMembersMatch;
    private javax.swing.JLabel jlbClansMembersNUmbers;
    private javax.swing.JLabel jlbTeamMatesNumber;
    private javax.swing.JList jlsClans;
    private javax.swing.JList jlsCoachList;
    private javax.swing.JSpinner jspTeamMembers;
    // End of variables declaration//GEN-END:variables
}