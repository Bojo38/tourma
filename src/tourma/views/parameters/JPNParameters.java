package tourma.views.parameters;


import java.awt.BorderLayout;
import java.util.Date;
import java.util.logging.Logger;
import tourma.JdgCoach;
import tourma.MainFrame;
import tourma.data.Tournament;
import tourma.tableModel.MjtCoaches;
import tourma.utils.TableFormat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author WFMJ7631
 */
public final class JPNParameters extends javax.swing.JPanel {

    private final  Tournament mTournament;
    private final JPNParamGroup mJpnGroup;
    private final JPNParamCategories mJpnCategories;
    private final JPNParamClan mJpnClan;
    private final JPNParamTeam mJpnTeam;
    private final JPNParamCriterias mJpnCriterias;
    private final JPNParamIndiv mJpnIndiv;
    private final JPNTeams jpnTeamTour;

    /**
     * Creates new form JPNParameters
     */
    public JPNParameters() {
        
        mTournament = Tournament.getTournament();
        initComponents();

        mJpnIndiv = new JPNParamIndiv();
        mJpnCriterias = new JPNParamCriterias();
        mJpnTeam = new JPNParamTeam();
        mJpnGroup = new JPNParamGroup();
        mJpnClan = new JPNParamClan();
        mJpnCategories = new JPNParamCategories();

        final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N

        jtpOptions.addTab(
                bundle.getString("Individual"),
                new javax.swing.ImageIcon(getClass().getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/USER.PNG"))),
                mJpnIndiv);
        jtpOptions.addTab(
                bundle.getString("Criterias"),
                new javax.swing.ImageIcon(getClass().getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/TOOLS.PNG"))),
                mJpnCriterias);
        jtpOptions.addTab(
                bundle.getString("ByTeamKey"),
                new javax.swing.ImageIcon(getClass().getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/TEAM.PNG"))),
                mJpnTeam);
        jtpOptions.addTab(
                bundle.getString("Clan"),
                new javax.swing.ImageIcon(getClass().getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/CLAN.PNG"))),
                mJpnClan);
        jtpOptions.addTab(
                bundle.getString("Group"),
                new javax.swing.ImageIcon(getClass().getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/GROUP.PNG"))),
                mJpnGroup);
                jtpOptions.addTab(
                bundle.getString("Category"),
                new javax.swing.ImageIcon(getClass().getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("/TOURMA/IMAGES/GROUP2.PNG"))),
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
        mJpnTeam.update();
        mJpnClan.update();
        mJpnGroup.update();
        mJpnCategories.update();

        jtpOptions.setEnabledAt(4,!mTournament.getParams().isMultiRoster());
        jtpOptions.setEnabledAt(2, mTournament.getParams().isTeamTournament());
//        jtpOptions.setEnabledAt(3, !mTournament.getParams().mTeamTournament);

        jtfOrgas.setText(mTournament.getParams().getTournamentOrga());
        jtfTournamentName.setText(mTournament.getParams().getTournamentName());

        Date d=new Date();
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
    @SuppressWarnings({"unchecked","PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfTournamentName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfOrgas = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jtfPlace = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jDate = new com.toedter.calendar.JDateChooser();
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

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("TournamentKey"))); // NOI18N
        jPanel5.setLayout(new java.awt.GridLayout(4, 2));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText(bundle.getString("TournamentNameKey")); // NOI18N
        jPanel5.add(jLabel1);

        jtfTournamentName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfTournamentNameKeyPressed(evt);
            }
        });
        jPanel5.add(jtfTournamentName);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText(bundle.getString("OrganizerKey")); // NOI18N
        jPanel5.add(jLabel2);

        jtfOrgas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfOrgasActionPerformed(evt);
            }
        });
        jtfOrgas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfOrgasKeyPressed(evt);
            }
        });
        jPanel5.add(jtfOrgas);

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

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("RankingParametersKey"))); // NOI18N
        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel9.add(jtpOptions, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel9, java.awt.BorderLayout.CENTER);

        add(jPanel1);

        jpnParticipant.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Coachs"));
        jPanel7.setPreferredSize(new java.awt.Dimension(450, 240));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jbtAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAdd.setText(bundle.getString("Add")); // NOI18N
        jbtAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddActionPerformed(evt);
            }
        });
        jpnCoachButtons.add(jbtAdd);

        jbtRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemove.setText(bundle.getString("Remove")); // NOI18N
        jbtRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveActionPerformed(evt);
            }
        });
        jpnCoachButtons.add(jbtRemove);

        jbtModify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Swap.png"))); // NOI18N
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
        mTournament.getParams().setTournamentName(jtfTournamentName.getText()+evt.getKeyChar());
    }//GEN-LAST:event_jtfTournamentNameKeyPressed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtfOrgasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfOrgasKeyPressed
        mTournament.getParams().semTournamentOrga(jtfOrgas.getText()+evt.getKeyChar());
    }//GEN-LAST:event_jtfOrgasKeyPressed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddActionPerformed
        final JdgCoach w = new JdgCoach(MainFrame.getMainFrame(), true);
        w.setVisible(true);
        update();
    }//GEN-LAST:event_jbtAddActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveActionPerformed
        mTournament.removeCoach(jtbCoachs.getSelectedRow());
        update();
    }//GEN-LAST:event_jbtRemoveActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModifyActionPerformed

        if (jtbCoachs.getSelectedRow() >= 0) {
            final JdgCoach w = new JdgCoach(MainFrame.getMainFrame(), true, mTournament.getCoach(jtbCoachs.getSelectedRow()));
            w.setVisible(true);
            update();
        }
    }//GEN-LAST:event_jbtModifyActionPerformed

    private void jtfOrgasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfOrgasActionPerformed
        mTournament.getParams().semTournamentOrga(jtfOrgas.getText());
    }//GEN-LAST:event_jtfOrgasActionPerformed

    private void jtfPlaceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPlaceKeyPressed
        mTournament.getParams().setPlace(jtfPlace.getText()+evt.getKeyChar());
    }//GEN-LAST:event_jtfPlaceKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtModify;
    private javax.swing.JButton jbtRemove;
    private javax.swing.JPanel jpnCoachButtons;
    private javax.swing.JPanel jpnParticipant;
    private javax.swing.JTable jtbCoachs;
    private javax.swing.JTextField jtfOrgas;
    private javax.swing.JTextField jtfPlace;
    private javax.swing.JTextField jtfTournamentName;
    private javax.swing.JTabbedPane jtpOptions;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(JPNParameters.class.getName());
    
/*     private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }*/
}
