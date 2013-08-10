/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on 10 mai 2010, 16:38:37
 */
package tourma;

import java.awt.BorderLayout;
import tourma.utility.ExtensionFileFilter;
import tourma.tableModel.mjtCoaches;
import tourma.views.system.jdgRevisions;
import tourma.views.system.jdgAbout;
import tourma.data.Tournament;
import java.awt.Component;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tourma.views.system.jdgOnlineHelp;
import javax.swing.filechooser.FileFilter;
import teamma.data.lrb;
import teamma.views.JdgRoster;
import tourma.data.Group;
import tourma.data.RosterType;
import tourma.utils.Generation;
import tourma.utility.StringConstants;
import tourma.utils.TableFormat;

/**
 *
 * @author Frederic Berger
 */
public class MainFrame extends javax.swing.JFrame {

    Tournament mTournament;
    File mFile = null;
    JPNParamGroup mJpnGroup;
     JPNParamClan mJpnClan;
     JPNParamTeam mJpnTeam;
     JPNParamCriterias mJpnCriterias;
     JPNParamIndiv mJpnIndiv;
     JPNTeams jpnTeamTour;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {

        mTournament = Tournament.getTournament();
        this.setSize(800, 600);
        initComponents();

        final ArrayList<String> StartOptions = new ArrayList<String>();
        StartOptions.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NewGame"));
        StartOptions.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Open"));
        final int res = JOptionPane.showOptionDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NewGameOrOpen"), "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, StartOptions.toArray(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Open"));

        if (res == 0) {
            jmiNouveauActionPerformed(null);
        } else {
            final JFileChooser jfc = new JFileChooser();
            final FileFilter filter1 = new ExtensionFileFilter(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("TourMaXMLFile"), new String[]{StringConstants.CS_XML, StringConstants.CS_xml});
            jfc.setFileFilter(filter1);
            if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                Tournament.getTournament().loadXML(jfc.getSelectedFile());
                mFile =
                        jfc.getSelectedFile();
                for (int i = jtpMain.getTabCount() - 1; i
                        >= 0; i--) {
                    final Component obj = jtpMain.getComponentAt(i);
                    if (obj instanceof JPNRound) {
                        jtpMain.remove(obj);
                    }

                }
                boolean cup = false;
                for (int i = 0; i
                        < mTournament.getRounds().size(); i++) {
                    final JPNRound jpnr = new JPNRound(i, mTournament.getRounds().get(i), mTournament);
                    jtpMain.addTab(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Round") + " " + (i + 1), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Dice.png")), jpnr);
                    if (mTournament.getRounds().get(i).mCup) {
                        cup = true;
                    }
                }
                if (cup) {
                    final JPNCup jpncup = new JPNCup();
                    jtpMain.addTab("Coupe", new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Star.png")), jpncup);
                }
            }
        }

        final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N

        mJpnIndiv=new JPNParamIndiv();
        mJpnCriterias=new JPNParamCriterias();
        mJpnTeam=new JPNParamTeam();
        mJpnGroup = new JPNParamGroup();
        mJpnClan = new JPNParamClan();
        jtpOptions.addTab(
                bundle.getString("Individual"),
                new javax.swing.ImageIcon(getClass().getResource("/tourma/images/User.png")), 
                mJpnIndiv);
        jtpOptions.addTab(
                bundle.getString("Criterias"),
                new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Tools.png")), 
                mJpnCriterias);
        jtpOptions.addTab(
                bundle.getString("ByTeamKey"),
                new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Team.png")), 
                mJpnTeam);
        jtpOptions.addTab(
                bundle.getString("Clan"),
                new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Clan.png")), 
                mJpnClan);
        jtpOptions.addTab(
                bundle.getString("Group"),
                new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Group.png")), 
                mJpnGroup);
        
        
        jpnTeamTour=new JPNTeams();
        jpnParticipant.add(jpnTeamTour,BorderLayout.NORTH);

        update();
    }



   

     

    protected void updateTables(final boolean bTourStarted) {

        jbtAdd.setEnabled(!bTourStarted);
        jbtRemove.setEnabled(!bTourStarted);

       

        final mjtCoaches coachModel = new mjtCoaches(mTournament.getCoachs());
        jtbCoachs.setModel(coachModel);
        TableFormat.setColumnSize(jtbCoachs);

        jtbCoachs.setDefaultRenderer(String.class, coachModel);
        jtbCoachs.setDefaultRenderer(Integer.class, coachModel);

    }

    public void update() {

        final boolean bTourStarted = mTournament.getRounds().size() > 0;

        updateTables(bTourStarted);

         if (mTournament.getParams().mTeamTournament) {
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

        jmiEditTeam.setEnabled(mTournament.getParams().mGame == RosterType.C_BLOOD_BOWL);

        jtpOptions.setEnabledAt(2, mTournament.getParams().mTeamTournament);
        jtpOptions.setEnabledAt(3, !mTournament.getParams().mTeamTournament);

        jtfOrgas.setText(mTournament.getParams().mTournamentOrga);
        jtfTournamentName.setText(mTournament.getParams().mTournamentName);

        jxdDate.setDate(mTournament.getParams().mDate);
        jtfPlace.setText(mTournament.getParams().mPlace);

        for (int i = 0; i < jtpMain.getComponentCount(); i++) {
            final Object o = jtpMain.getComponentAt(i);
            if (o instanceof JPNRound) {
                ((JPNRound) o).update();
            }
            if (o instanceof JPNCup) {
                ((JPNCup) o).update();
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtpMain = new javax.swing.JTabbedPane();
        jpnParameters = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfTournamentName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfOrgas = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jtfPlace = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jxdDate = new org.jdesktop.swingx.JXDatePicker();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jmnFile = new javax.swing.JMenu();
        jmiNouveau = new javax.swing.JMenuItem();
        jmiCharger = new javax.swing.JMenuItem();
        jmiSave = new javax.swing.JMenuItem();
        jmiSaveAs = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jmiExport = new javax.swing.JMenuItem();
        jmiExportFbb = new javax.swing.JMenuItem();
        jmiExportFbb1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jmiExit = new javax.swing.JMenuItem();
        jmnParameters = new javax.swing.JMenu();
        jmiEditTeam = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jcxAllowSpecialSkill = new javax.swing.JCheckBoxMenuItem();
        jmnRounds = new javax.swing.JMenu();
        jmiGenerateFirstRound = new javax.swing.JMenuItem();
        jmnHelp = new javax.swing.JMenu();
        jmiAbout = new javax.swing.JMenuItem();
        jmiRevisions = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jmiAideEnLigne = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        setTitle(bundle.getString("SoftwareTitle")); // NOI18N
        setIconImage((Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("images/icone.png"))));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jtpMain.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jtpMainStateChanged(evt);
            }
        });

        jpnParameters.setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setLayout(new java.awt.BorderLayout());

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

        jtfOrgas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfOrgasKeyPressed(evt);
            }
        });
        jPanel5.add(jtfOrgas);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel21.setText(bundle.getString("PlaceKey")); // NOI18N
        jPanel5.add(jLabel21);
        jPanel5.add(jtfPlace);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel22.setText(bundle.getString("DateKey")); // NOI18N
        jPanel5.add(jLabel22);
        jPanel5.add(jxdDate);

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("RankingParametersKey"))); // NOI18N
        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel9.add(jtpOptions, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel9, java.awt.BorderLayout.CENTER);

        jpnParameters.add(jPanel1);

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

        jPanel7.add(jpnCoachButtons, java.awt.BorderLayout.PAGE_START);

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
        jtbCoachs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jtbCoachs);

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jpnParticipant.add(jPanel7, java.awt.BorderLayout.CENTER);

        jpnParameters.add(jpnParticipant);

        jtpMain.addTab(bundle.getString("TournamentParameterKey"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Tools.png")), jpnParameters); // NOI18N

        getContentPane().add(jtpMain, java.awt.BorderLayout.CENTER);

        jmnFile.setText(bundle.getString("FileKey")); // NOI18N

        jmiNouveau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/New.png"))); // NOI18N
        jmiNouveau.setText(bundle.getString("NewTourKey")); // NOI18N
        jmiNouveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNouveauActionPerformed(evt);
            }
        });
        jmnFile.add(jmiNouveau);

        jmiCharger.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Open.png"))); // NOI18N
        jmiCharger.setText(bundle.getString("LoadTourKey")); // NOI18N
        jmiCharger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiChargerActionPerformed(evt);
            }
        });
        jmnFile.add(jmiCharger);

        jmiSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Save.png"))); // NOI18N
        jmiSave.setText(bundle.getString("SaveTourKey")); // NOI18N
        jmiSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSaveActionPerformed(evt);
            }
        });
        jmnFile.add(jmiSave);

        jmiSaveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Save.png"))); // NOI18N
        jmiSaveAs.setText(bundle.getString("SaveTourAsKey")); // NOI18N
        jmiSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSaveAsActionPerformed(evt);
            }
        });
        jmnFile.add(jmiSaveAs);
        jmnFile.add(jSeparator1);

        jmiExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Html.png"))); // NOI18N
        jmiExport.setText(bundle.getString("ExportNafResultKey")); // NOI18N
        jmiExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExportActionPerformed(evt);
            }
        });
        jmnFile.add(jmiExport);

        jmiExportFbb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Html.png"))); // NOI18N
        jmiExportFbb.setText(bundle.getString("ExportFBBResultKey")); // NOI18N
        jmiExportFbb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExportFbbActionPerformed(evt);
            }
        });
        jmnFile.add(jmiExportFbb);

        jmiExportFbb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Html.png"))); // NOI18N
        jmiExportFbb1.setText(bundle.getString("ExportFBBResultKey")); // NOI18N
        jmiExportFbb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExportFbb1ActionPerformed(evt);
            }
        });
        jmnFile.add(jmiExportFbb1);
        jmnFile.add(jSeparator2);

        jmiExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Halt.png"))); // NOI18N
        jmiExit.setText(bundle.getString("QuitKey")); // NOI18N
        jmiExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExitActionPerformed(evt);
            }
        });
        jmnFile.add(jmiExit);

        jMenuBar1.add(jmnFile);

        jmnParameters.setText(bundle.getString("Parametres")); // NOI18N

        jmiEditTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/New.png"))); // NOI18N
        jmiEditTeam.setText(bundle.getString("ChangeRosters")); // NOI18N
        jmiEditTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditTeamActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiEditTeam);
        jmnParameters.add(jSeparator5);

        jcxAllowSpecialSkill.setText("Autoriser les compétences spéciales");
        jcxAllowSpecialSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxAllowSpecialSkillActionPerformed(evt);
            }
        });
        jmnParameters.add(jcxAllowSpecialSkill);

        jMenuBar1.add(jmnParameters);

        jmnRounds.setText(bundle.getString("Rounds")); // NOI18N

        jmiGenerateFirstRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Generate.png"))); // NOI18N
        jmiGenerateFirstRound.setText(bundle.getString("GenerateFirstRound")); // NOI18N
        jmiGenerateFirstRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGenerateFirstRoundActionPerformed(evt);
            }
        });
        jmnRounds.add(jmiGenerateFirstRound);

        jMenuBar1.add(jmnRounds);

        jmnHelp.setText("?");

        jmiAbout.setText(bundle.getString("AboutKey")); // NOI18N
        jmiAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAboutActionPerformed(evt);
            }
        });
        jmnHelp.add(jmiAbout);

        jmiRevisions.setText(bundle.getString("SoftwareRevisionsKey")); // NOI18N
        jmiRevisions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRevisionsActionPerformed(evt);
            }
        });
        jmnHelp.add(jmiRevisions);
        jmnHelp.add(jSeparator3);

        jmiAideEnLigne.setText(bundle.getString("Help")); // NOI18N
        jmiAideEnLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAideEnLigneActionPerformed(evt);
            }
        });
        jmnHelp.add(jmiAideEnLigne);

        jMenuBar1.add(jmnHelp);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModifyActionPerformed

        if (jtbCoachs.getSelectedRow() >= 0) {
            final jdgCoach w = new jdgCoach(this, true, mTournament.getCoachs().get(jtbCoachs.getSelectedRow()));
            w.setVisible(true);
            update();
        }
}//GEN-LAST:event_jbtModifyActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveActionPerformed
        mTournament.getCoachs().remove(jtbCoachs.getSelectedRow());
        update();
}//GEN-LAST:event_jbtRemoveActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddActionPerformed
        final jdgCoach w = new jdgCoach(this, true);
        w.setVisible(true);
        update();
}//GEN-LAST:event_jbtAddActionPerformed

   @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtfOrgasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfOrgasKeyPressed
        mTournament.getParams().mTournamentOrga = jtfOrgas.getText();
}//GEN-LAST:event_jtfOrgasKeyPressed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtfTournamentNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTournamentNameKeyPressed
        mTournament.getParams().mTournamentName = jtfTournamentName.getText();
}//GEN-LAST:event_jtfTournamentNameKeyPressed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSaveAsActionPerformed
        final JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("TourMaXMLFile"), new String[]{StringConstants.CS_XML, StringConstants.CS_xml});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            StringBuffer url2 = new StringBuffer(jfc.getSelectedFile().getAbsolutePath());
            String ext = "";
            final int i = url2.toString().lastIndexOf('.');
            if (i > 0 && i < url2.length() - 1) {
                ext = url2.substring(i + 1).toLowerCase();
            }

            if (!ext.equals(StringConstants.CS_xml)) {
                url2 = url2.append(".xml");
            }
            mFile = new File(url2.toString());
            Tournament.getTournament().saveXML(mFile);
        }
    }//GEN-LAST:event_jmiSaveAsActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSaveActionPerformed
        if (mFile != null) {
            Tournament.getTournament().saveXML(mFile);
        } else {
            jmiSaveAsActionPerformed(evt);
        }
    }//GEN-LAST:event_jmiSaveActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiChargerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiChargerActionPerformed
        final JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("TourMaXMLFile"), new String[]{StringConstants.CS_XML, StringConstants.CS_xml});
        jfc.setFileFilter(filter1);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            Tournament.getTournament().loadXML(jfc.getSelectedFile());
            mFile =
                    jfc.getSelectedFile();
            for (int i = jtpMain.getTabCount() - 1; i
                    >= 0; i--) {
                final Component obj = jtpMain.getComponentAt(i);
                if (obj instanceof JPNRound) {
                    jtpMain.remove(obj);
                }

            }
            for (int i = 0; i
                    < mTournament.getRounds().size(); i++) {
                final JPNRound jpnr = new JPNRound(i, mTournament.getRounds().get(i), mTournament);
//                jtpMain.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("RONDE ") + (i + 1), jpnr);
                jtpMain.addTab(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Round") + " " + (i + 1), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Dice.png")), jpnr);
            }

            update();

        }
    }//GEN-LAST:event_jmiChargerActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiNouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNouveauActionPerformed


        for (int i = jtpMain.getTabCount() - 1; i
                >= 0; i--) {
            final Component obj = jtpMain.getComponentAt(i);
            if (obj instanceof JPNRound) {
                jtpMain.remove(obj);
            }
        }
        mTournament = Tournament.resetTournament();

        final ArrayList<String> Games = new ArrayList<String>();
        Games.add("Blood Bowl");
        Games.add("DreadBall");
        final int res2 = JOptionPane.showOptionDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChooseGame"), "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Games.toArray(), "Blood Bowl");
        if (res2 == 0) {
            RosterType.initCollection(RosterType.C_BLOOD_BOWL);
            lrb.getLRB();
            Tournament.getTournament().getParams().mGame = RosterType.C_BLOOD_BOWL;

        } else {
            RosterType.initCollection(RosterType.C_DREAD_BALL);
            Tournament.getTournament().getParams().mGame = RosterType.C_DREAD_BALL;
            jmiExport.setEnabled(false);
            jmiExportFbb.setEnabled(false);
            jcxAllowSpecialSkill.setEnabled(false);
        }

        mTournament = Tournament.getTournament();
        mTournament.getGroups().clear();
        final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);
        final Group group = new Group(bundle.getString("NoneKey"));
        mTournament.getGroups().add(group);

        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {
            group.mRosters.add(new RosterType(RosterType.mRostersNames.get(i)));
        }

        final Object options[] = {java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Single"), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ByTeam")};
        int res = JOptionPane.showOptionDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("TournamentType"), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NewTournament"),
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        Tournament.getTournament().getParams().mTeamTournament = (res == 1);
        if (res == 1) {
            final jdgSelectNumber jdg = new jdgSelectNumber(this, true, mTournament);
            jdg.setVisible(true);
            res = JOptionPane.showOptionDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("PairingType"), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Pairing"),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            Tournament.getTournament().getParams().mTeamPairing = res;
            if (res == 1) {
                final Object options2[] = {java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("AccordingToRanking"), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Free")};
                res = JOptionPane.showOptionDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("IndividualPairingType"), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("IndividualPairing"),
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options2, options2[0]);
                Tournament.getTournament().getParams().mTeamIndivPairing = res;
            }
        }


        update();
    }//GEN-LAST:event_jmiNouveauActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportActionPerformed
        final JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter("NAF XML file", new String[]{StringConstants.CS_XML, StringConstants.CS_xml});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            Tournament.getTournament().exportResults(jfc.getSelectedFile());
        }
    }//GEN-LAST:event_jmiExportActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jmiExitActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiRevisionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRevisionsActionPerformed
        jdgRevisions jdg = new jdgRevisions(this, true);
        jdg.setVisible(true);
        jdg =
                null;
}//GEN-LAST:event_jmiRevisionsActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiAideEnLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAideEnLigneActionPerformed
        jdgOnlineHelp jdg = new jdgOnlineHelp(this, false);
        jdg.setVisible(true);
        jdg =
                null;
    }//GEN-LAST:event_jmiAideEnLigneActionPerformed

   @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        if (JOptionPane.showConfirmDialog(this, "Voulez vous sauvgarder ?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (mFile.equals("")) {
                jmiSaveAsActionPerformed(null);
            } else {
                jmiSaveActionPerformed(null);
            }
        }
    }//GEN-LAST:event_formWindowClosed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAboutActionPerformed
        jdgAbout jdg = new jdgAbout(this, true);
        jdg.setVisible(true);
        jdg =
                null;
}//GEN-LAST:event_jmiAboutActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(this, "Voulez vous sauvgarder ?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (mFile == null) {
                jmiSaveAsActionPerformed(null);
            } else {

                if (mFile.equals("")) {
                    jmiSaveAsActionPerformed(null);
                } else {
                    jmiSaveActionPerformed(null);
                }
            }
        }
    }//GEN-LAST:event_formWindowClosing
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiEditTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditTeamActionPerformed
        final JdgRoster jdg = new JdgRoster(this, true);
        jdg.setVisible(true);
    }//GEN-LAST:event_jmiEditTeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcxAllowSpecialSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxAllowSpecialSkillActionPerformed
        lrb.getLRB()._allowSpecialSkills = jcxAllowSpecialSkill.getState();
    }//GEN-LAST:event_jcxAllowSpecialSkillActionPerformed

   @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiExportFbbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportFbbActionPerformed
        final JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter("FBB csv file", new String[]{"CSV", "csv"});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            Tournament.getTournament().exportFBB(jfc.getSelectedFile());
        }
    }//GEN-LAST:event_jmiExportFbbActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiExportFbb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportFbb1ActionPerformed
        final JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter("FBB xml file", new String[]{"FBB_XML", "fbb_xml"});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            final File f = jfc.getSelectedFile();
            if (f.getName().endsWith(".fbb_xml")) {
                Tournament.getTournament().exportFullFBB(f);
            } else {
                Tournament.getTournament().exportFullFBB(new File(f.getAbsolutePath() + ".fbb_xml"));
            }
        }
    }//GEN-LAST:event_jmiExportFbb1ActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtpMainStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtpMainStateChanged
        final Object obj = jtpMain.getSelectedComponent();
        if (obj instanceof JPNCup) {
            ((JPNCup) obj).update();
        }
    }//GEN-LAST:event_jtpMainStateChanged
 @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiGenerateFirstRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGenerateFirstRoundActionPerformed
       if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("AreYouSure?ItWillEraseAllRounds"), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("FirstRound"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (mTournament.getParams().mTeamTournament && (mTournament.getParams().mTeamPairing == 1) && mTournament.getTeams().size() % 2 > 0) {
                JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OddTeamNumber"));
            } else {

                final ArrayList<String> labels = new ArrayList<String>();
                final ArrayList<Integer> Options = new ArrayList<Integer>();

                /**
                 * Random possible ?
                 */
                labels.add("Aléatoire");
                Options.add(Generation.GEN_RANDOM);

                /**
                 * Coupe
                 */
                labels.add("Coupe");
                Options.add(Generation.GEN_CUP);

                /**
                 * Ordre
                 */
                labels.add("Order d'inscription");
                Options.add(Generation.GEN_ORDER);

                /**
                 * Round Robin
                 */
                labels.add("Round Robin");
                Options.add(Generation.GEN_RROBIN);

                /**
                 * manuel
                 */
                labels.add("Choix Manuel");
                Options.add(Generation.GEN_MANUAL);

                /**
                 * Poules
                 */
                labels.add("Poules");
                Options.add(Generation.GEN_POOL);


                final JPanel jpn = new JPanel(new BorderLayout());
                final JComboBox jcb = new JComboBox(labels.toArray());
                jpn.add(jcb, BorderLayout.CENTER);
                final JLabel jlb = new JLabel("Choisissez la méthode de génération: ");
                jpn.add(jlb, BorderLayout.NORTH);

                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jpn, "Génération", JOptionPane.QUESTION_MESSAGE);
                final int index = jcb.getSelectedIndex();

                Generation.generateFirstRound(Options.get(index));

                for (int i = jtpMain.getTabCount() - 1; i >= 0; i--) {
                    final Component obj = jtpMain.getComponentAt(i);
                    if (obj instanceof JPNRound) {
                        jtpMain.remove(obj);
                    }
                }
                boolean cup = false;
                for (int i = 0; i < mTournament.getRounds().size(); i++) {
                    final JPNRound jpnr = new JPNRound(i, mTournament.getRounds().get(i), mTournament);
                    if (mTournament.getRounds().get(i).mCup) {
                        cup = true;
                    }
                    jtpMain.addTab(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Round") + " " + (i + 1), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Dice.png")), jpnr);
                }

                if (cup) {
                    final JPNCup jpncup = new JPNCup();
                    jtpMain.addTab("Coupe", new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Star.png")), jpncup);
                }

                jtpMain.setSelectedIndex(mTournament.getRounds().size());
                update();

            }

        }
    }//GEN-LAST:event_jmiGenerateFirstRoundActionPerformed

   

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame.getMainFrame().setVisible(true);
            }
        });
    }
    protected static MainFrame mSingleton;

    public static MainFrame getMainFrame() {
        if (mSingleton == null) {
            mSingleton = new MainFrame();
        }

        return mSingleton;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtModify;
    private javax.swing.JButton jbtRemove;
    private javax.swing.JCheckBoxMenuItem jcxAllowSpecialSkill;
    private javax.swing.JMenuItem jmiAbout;
    private javax.swing.JMenuItem jmiAideEnLigne;
    private javax.swing.JMenuItem jmiCharger;
    private javax.swing.JMenuItem jmiEditTeam;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiExport;
    private javax.swing.JMenuItem jmiExportFbb;
    private javax.swing.JMenuItem jmiExportFbb1;
    private javax.swing.JMenuItem jmiGenerateFirstRound;
    private javax.swing.JMenuItem jmiNouveau;
    private javax.swing.JMenuItem jmiRevisions;
    private javax.swing.JMenuItem jmiSave;
    private javax.swing.JMenuItem jmiSaveAs;
    private javax.swing.JMenu jmnFile;
    private javax.swing.JMenu jmnHelp;
    private javax.swing.JMenu jmnParameters;
    private javax.swing.JMenu jmnRounds;
    private javax.swing.JPanel jpnCoachButtons;
    private javax.swing.JPanel jpnParameters;
    private javax.swing.JPanel jpnParticipant;
    private javax.swing.JTable jtbCoachs;
    private javax.swing.JTextField jtfOrgas;
    private javax.swing.JTextField jtfPlace;
    private javax.swing.JTextField jtfTournamentName;
    public javax.swing.JTabbedPane jtpMain;
    private javax.swing.JTabbedPane jtpOptions;
    private org.jdesktop.swingx.JXDatePicker jxdDate;
    // End of variables declaration//GEN-END:variables
}
