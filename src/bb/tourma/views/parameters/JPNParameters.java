package bb.tourma.views.parameters;

import java.awt.BorderLayout;
import java.util.Date;
import bb.tourma.JdgCoach;
import bb.tourma.MainFrame;
import bb.tourma.data.Coach;
import bb.tourma.data.Tournament;
import bb.tourma.languages.Translate;
import bb.tourma.tableModel.MjtCoaches;
import bb.tourma.utils.display.TableFormat;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author WFMJ7631
 */
public final class JPNParameters extends javax.swing.JPanel {

    private Tournament mTournament;
    private final JPNParamGroup mJpnGroup;
    private final JPNParamCategories mJpnCategories;
    private final JPNParamClan mJpnClan;
    private final JPNParamTeam mJpnTeam;
    private final JPNParamCriterias mJpnCriterias;
    private final JPNParamFormulas mJpnFormulas;
    private final JPNParamIndiv mJpnIndiv;
    private final JPNTeams jpnTeamTour;

    private final static String CS_Individual = "Individual";
    private final static String CS_Criterias = "Criterias";
    private final static String CS_Formulas = "Formulas";
    private final static String CS_ByTeam = "ByTeam";
    private final static String CS_Clan = "Clan";
    private final static String CS_Group = "Group";
    private final static String CS_Category = "Category";

    /**
     * Creates new form JPNParameters
     */
    public JPNParameters() {
        mTournament = null;

        mTournament = Tournament.getTournament();

        initComponents();

        mJpnIndiv = new JPNParamIndiv();
        mJpnCriterias = new JPNParamCriterias();
        mJpnFormulas = new JPNParamFormulas();
        mJpnTeam = new JPNParamTeam();
        mJpnGroup = new JPNParamGroup();
        mJpnClan = new JPNParamClan();
        mJpnCategories = new JPNParamCategories();

        jtpOptions.addTab(
                Translate.translate(CS_Individual),
                new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/User.png")),
                mJpnIndiv);
        jtpOptions.addTab(
                Translate.translate(CS_Criterias),
                new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Tools.png")),
                mJpnCriterias);
        jtpOptions.addTab(
                Translate.translate(CS_Formulas),
                new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Formula.png")),
                mJpnFormulas);
        jtpOptions.addTab(
                Translate.translate(CS_ByTeam),
                new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Team.png")),
                mJpnTeam);
        jtpOptions.addTab(
                Translate.translate(CS_Clan),
                new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Clan.png")),
                mJpnClan);
        jtpOptions.addTab(
                Translate.translate(CS_Group),
                new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Group.png")),
                mJpnGroup);
        jtpOptions.addTab(
                Translate.translate(CS_Category),
                new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Group2.png")),
                mJpnCategories);

        jpnTeamTour = new JPNTeams();
        jpnParticipant.add(jpnTeamTour, BorderLayout.NORTH);
    }

    /**
     *
     * @param bTourStarted
     */
    private void updateTables(final boolean bTourStarted) {

        jbtAdd.setEnabled(!bTourStarted);
        jbtRemove.setEnabled(!bTourStarted);

        final MjtCoaches coachModel = new MjtCoaches(mTournament);
        jtbCoachs.setModel(coachModel);
        TableFormat.setColumnSize(jtbCoachs);

        jtbCoachs.setDefaultRenderer(String.class, coachModel);
        jtbCoachs.setDefaultRenderer(Integer.class, coachModel);
        jtbCoachs.setAutoCreateRowSorter(true);

    }

    /**
     * Update Panel
     */
    public void update() {

        final boolean bTourStarted = mTournament.getRoundsCount() > 0;

        updateTables(bTourStarted);

        if (mTournament.getParams().isTeamTournament()) {
            jpnTeamTour.update();
            jpnTeamTour.setVisible(true);
            jpnCoachButtons.setVisible(false);
        } else {
            jpnTeamTour.setVisible(false);
            jpnCoachButtons.setVisible(true);
        }

        mJpnIndiv.update();
        mJpnCriterias.update();
        mJpnFormulas.update();
        mJpnTeam.update();
        mJpnClan.update();
        mJpnGroup.update();
        mJpnCategories.update();

        jtpOptions.setEnabledAt(5, !mTournament.getParams().isMultiRoster());
        jtpOptions.setEnabledAt(3, mTournament.getParams().isTeamTournament());

        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < mTournament.getParams().getTournamentOrga().size(); i++) {
            String orga = mTournament.getParams().getTournamentOrga().get(i);
            model.add(i, orga);
        }

        jlsOrgas.setModel(model);
        jtfTournamentName.setText(mTournament.getParams().getTournamentName());

        Date d = new Date();
        d.setTime(mTournament.getParams().getDateTime());
        jDate.setDate(d);

        jtfPlace.setText(mTournament.getParams().getPlace());

        MainFrame.getMainFrame().update();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfTournamentName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jtfPlace = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jDate = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlsOrgas = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        jbtAddOrga = new javax.swing.JButton();
        jbtRemoveOrga = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jtpOptions = new javax.swing.JTabbedPane();
        jpnParticipant = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jpnCoachButtons = new javax.swing.JPanel();
        jbtAdd = new javax.swing.JButton();
        jbtRemove = new javax.swing.JButton();
        jbtModify = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbCoachs = new javax.swing.JTable();

        setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bb/tourma/languages/language"); // NOI18N
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("TournamentKey"))); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.GridLayout(3, 2));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText(bundle.getString("TournamentNameKey")); // NOI18N
        jPanel5.add(jLabel1);

        jtfTournamentName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfTournamentNameKeyPressed(evt);
            }
        });
        jPanel5.add(jtfTournamentName);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel21.setText(bundle.getString("PlaceKey")); // NOI18N
        jPanel5.add(jLabel21);

        jtfPlace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfPlaceKeyPressed(evt);
            }
        });
        jPanel5.add(jtfPlace);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel22.setText(bundle.getString("DateKey")); // NOI18N
        jPanel5.add(jLabel22);
        jPanel5.add(jDate);

        jPanel3.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText(bundle.getString("OrganizerKey")); // NOI18N
        jPanel4.add(jLabel2);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jlsOrgas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlsOrgas.setMaximumSize(new java.awt.Dimension(20, 20));
        jlsOrgas.setMinimumSize(new java.awt.Dimension(20, 20));
        jlsOrgas.setPreferredSize(new java.awt.Dimension(20, 20));
        jlsOrgas.setVisibleRowCount(4);
        jScrollPane2.setViewportView(jlsOrgas);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jbtAddOrga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Add.png"))); // NOI18N
        jbtAddOrga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddOrgaActionPerformed(evt);
            }
        });
        jPanel6.add(jbtAddOrga);

        jbtRemoveOrga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Close.png"))); // NOI18N
        jbtRemoveOrga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveOrgaActionPerformed(evt);
            }
        });
        jPanel6.add(jbtRemoveOrga);

        jPanel2.add(jPanel6, java.awt.BorderLayout.SOUTH);

        jPanel4.add(jPanel2);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("RankingParametersKey"))); // NOI18N
        jPanel9.setLayout(new java.awt.BorderLayout());

        jtpOptions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtpOptionsMouseClicked(evt);
            }
        });
        jPanel9.add(jtpOptions, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel9, java.awt.BorderLayout.CENTER);

        add(jPanel1);

        jpnParticipant.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Coachs"))); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(450, 240));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jbtAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Add.png"))); // NOI18N
        jbtAdd.setText(bundle.getString("Add")); // NOI18N
        jbtAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddActionPerformed(evt);
            }
        });
        jpnCoachButtons.add(jbtAdd);

        jbtRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Close.png"))); // NOI18N
        jbtRemove.setText(bundle.getString("Remove")); // NOI18N
        jbtRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveActionPerformed(evt);
            }
        });
        jpnCoachButtons.add(jbtRemove);

        jbtModify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Swap.png"))); // NOI18N
        jbtModify.setText(bundle.getString("Modify")); // NOI18N
        jbtModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtModifyActionPerformed(evt);
            }
        });
        jpnCoachButtons.add(jbtModify);

        jPanel7.add(jpnCoachButtons, java.awt.BorderLayout.NORTH);

        jtbCoachs.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbCoachs);

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jpnParticipant.add(jPanel7, java.awt.BorderLayout.CENTER);

        add(jpnParticipant);
    }// </editor-fold>//GEN-END:initComponents
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtfTournamentNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTournamentNameKeyPressed
        mTournament.getParams().setTournamentName(jtfTournamentName.getText() + evt.getKeyChar());

    }//GEN-LAST:event_jtfTournamentNameKeyPressed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddActionPerformed

       final JdgCoach w = new JdgCoach(MainFrame.getMainFrame(), true);
       w.setVisible(true);
       update();
    }//GEN-LAST:event_jbtAddActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveActionPerformed

        Object oname = jtbCoachs.getValueAt(jtbCoachs.getSelectedRow(), 1);
        if (oname != null) {
            if (oname instanceof String) {
                Coach c = mTournament.getCoach((String) oname);
                mTournament.removeCoach(c);
            }
        }
        update();

    }//GEN-LAST:event_jbtRemoveActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModifyActionPerformed

        if (jtbCoachs.getSelectedRow() >= 0) {
            Object obj = jtbCoachs.getValueAt(jtbCoachs.getSelectedRow(), 0);
            if (obj instanceof Integer) {
                int index = (Integer) obj;
                final JdgCoach w = new JdgCoach(MainFrame.getMainFrame(), true, mTournament.getCoach(index - 1));
                w.setVisible(true);
                update();
            }
        }

    }//GEN-LAST:event_jbtModifyActionPerformed

    private void jtfPlaceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPlaceKeyPressed

        mTournament.getParams().setPlace(jtfPlace.getText() + evt.getKeyChar());

    }//GEN-LAST:event_jtfPlaceKeyPressed

    private void jtpOptionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtpOptionsMouseClicked
        mJpnFormulas.update();
    }//GEN-LAST:event_jtpOptionsMouseClicked

    private void jbtRemoveOrgaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveOrgaActionPerformed
        mTournament.getParams().delTournamentOrga(jlsOrgas.getSelectedIndex());
        update();
    }//GEN-LAST:event_jbtRemoveOrgaActionPerformed

    private void jbtAddOrgaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddOrgaActionPerformed
        String value = JOptionPane.showInputDialog("Enter orga name:");
        if (value != null) {
            mTournament.getParams().addTournamentOrga(value);
            update();
        }
    }//GEN-LAST:event_jbtAddOrgaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtAddOrga;
    private javax.swing.JButton jbtModify;
    private javax.swing.JButton jbtRemove;
    private javax.swing.JButton jbtRemoveOrga;
    private javax.swing.JList<String> jlsOrgas;
    private javax.swing.JPanel jpnCoachButtons;
    private javax.swing.JPanel jpnParticipant;
    private javax.swing.JTable jtbCoachs;
    private javax.swing.JTextField jtfPlace;
    private javax.swing.JTextField jtfTournamentName;
    private javax.swing.JTabbedPane jtpOptions;
    // End of variables declaration//GEN-END:variables

}
