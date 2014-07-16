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

import tourma.views.JPNCup;
import tourma.views.parameters.JPNParameters;
import tourma.views.JPNStatistics;
import tourma.views.round.JPNRound;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ProgressMonitor;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import teamma.data.lrb;
import teamma.views.JdgRoster;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Group;
import tourma.data.Match;
import tourma.data.Parameters;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.Substitute;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.utility.ExtensionFileFilter;
import tourma.utility.StringConstants;
import tourma.utils.Generation;
import tourma.utils.NAF;
import tourma.views.system.jdgAbout;
import tourma.views.system.jdgOnlineHelp;
import tourma.views.system.jdgRevisions;

/**
 *
 * @author Frederic Berger
 */
public class MainFrame extends javax.swing.JFrame {

    Tournament mTournament;
    File mFile = null;

    /**
     * Creates new form MainFrame
     */
    public MainFrame(int res) {

        mTournament = Tournament.getTournament();
        this.setSize(800, 600);
        initComponents();

        if (res == 0) {
            jmiNouveauActionPerformed(null);
        } else {
            final JFileChooser jfc = new JFileChooser();
            final FileFilter filter1 = new ExtensionFileFilter(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("TourMaXMLFile"), new String[]{StringConstants.CS_XML, StringConstants.CS_MINXML});
            jfc.setFileFilter(filter1);
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                Tournament.getTournament().loadXML(jfc.getSelectedFile());
            }
        }

        update();
    }

    public void update() {
        //final boolean bTourStarted = mTournament.getRounds().size() > 0;
        jmiEditTeam.setEnabled(mTournament.getParams().mGame == RosterType.C_BLOOD_BOWL);
        jmiSubstitutePlayer.setEnabled(mTournament.getRounds().size() > 0);
        final mainTreeModel dtm = new mainTreeModel();
        jtrPanels.setCellRenderer(dtm);
        jtrPanels.setModel(dtm);
        jtrPanels.setSize(100, this.getHeight());

        if (jpnContent instanceof JPNRound) {
            Round r = ((JPNRound) jpnContent).getRound();
            if (mTournament.getRounds().indexOf(r) == mTournament.getRounds().size() - 1) {
                jmiDelRound.setEnabled(true);
                jmiGenerateNextRound.setEnabled(true);
                jmiChangePairing.setEnabled(true);
                jmiAddFreeMatch.setEnabled(true);
                jmiDelFreeMatch.setEnabled(true);
                if (((JPNRound) jpnContent).getMatchTableSelectedRow() >= 0) {
                    jmiCancelConceedMatch.setEnabled(true);
                    jmiCancelMatchRefuse.setEnabled(true);
                    jmiConceedMatch.setEnabled(true);
                    jmiRefuseMatch.setEnabled(true);
                } else {
                    jmiCancelConceedMatch.setEnabled(false);
                    jmiCancelMatchRefuse.setEnabled(false);
                    jmiConceedMatch.setEnabled(false);
                    jmiRefuseMatch.setEnabled(false);
                }
            } else {
                jmiDelRound.setEnabled(false);
                jmiGenerateNextRound.setEnabled(false);
                jmiChangePairing.setEnabled(false);
                jmiAddFreeMatch.setEnabled(false);
                jmiDelFreeMatch.setEnabled(false);
                jmiCancelConceedMatch.setEnabled(false);
                jmiCancelMatchRefuse.setEnabled(false);
                jmiConceedMatch.setEnabled(false);
                jmiRefuseMatch.setEnabled(false);
            }
            jckmiRoundOnly.setEnabled(true);
        } else {
            jckmiRoundOnly.setEnabled(false);
            jmiDelRound.setEnabled(false);
            jmiGenerateNextRound.setEnabled(false);
            jmiChangePairing.setEnabled(false);
            jmiAddFreeMatch.setEnabled(false);
            jmiDelFreeMatch.setEnabled(false);
            jmiCancelConceedMatch.setEnabled(false);
            jmiCancelMatchRefuse.setEnabled(false);
            jmiConceedMatch.setEnabled(false);
            jmiRefuseMatch.setEnabled(false);
        }
        jcxPatchPortugal.setSelected(mTournament.getParams().mPortugal);
        this.revalidate();
        this.repaint();
    }

    public void updateTree() {
        jtrPanels.setSelectionPath(new TreePath(((mainTreeModel) jtrPanels.getModel()).mParams));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspSplit = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtrPanels = new javax.swing.JTree();
        jpnContent = new javax.swing.JPanel();
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
        jmnTools = new javax.swing.JMenu();
        jmiEditTeam = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jcxAllowSpecialSkill = new javax.swing.JCheckBoxMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jmiNafLoad = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jcxPatchPortugal = new javax.swing.JCheckBoxMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jcxUseColor = new javax.swing.JCheckBoxMenuItem();
        jcxUseImage = new javax.swing.JCheckBoxMenuItem();
        jmnParameters = new javax.swing.JMenu();
        jmiGenerateFirstRound = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jmiSubstitutePlayer = new javax.swing.JMenuItem();
        jmiAddCoach = new javax.swing.JMenuItem();
        jmnRound = new javax.swing.JMenu();
        jmiGenerateNextRound = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jmiDelRound = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jmiChangePairing = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jckmiRoundOnly = new javax.swing.JCheckBoxMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jmiAddFreeMatch = new javax.swing.JMenuItem();
        jmiDelFreeMatch = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jmiConceedMatch = new javax.swing.JMenuItem();
        jmiCancelConceedMatch = new javax.swing.JMenuItem();
        jmiRefuseMatch = new javax.swing.JMenuItem();
        jmiCancelMatchRefuse = new javax.swing.JMenuItem();
        jmnHelp = new javax.swing.JMenu();
        jmiAbout = new javax.swing.JMenuItem();
        jmiRevisions = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jmiAideEnLigne = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        setTitle(bundle.getString("SoftwareTitle")); // NOI18N
        setIconImage((Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("IMAGES/ICONE.PNG")))));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jtrPanels.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jtrPanelsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jtrPanels);

        jspSplit.setLeftComponent(jScrollPane1);
        jspSplit.setRightComponent(jpnContent);

        getContentPane().add(jspSplit, java.awt.BorderLayout.CENTER);

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
        jmiExportFbb.setActionCommand(bundle.getString("FBBExport")); // NOI18N
        jmiExportFbb.setLabel(bundle.getString("FBBExport")); // NOI18N
        jmiExportFbb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExportFbbActionPerformed(evt);
            }
        });
        jmnFile.add(jmiExportFbb);

        jmiExportFbb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Html.png"))); // NOI18N
        jmiExportFbb1.setText(bundle.getString("ExportFBBResultKey")); // NOI18N
        jmiExportFbb1.setLabel(bundle.getString("FBBFullExport")); // NOI18N
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

        jmnTools.setText(bundle.getString("Tools")); // NOI18N

        jmiEditTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/New.png"))); // NOI18N
        jmiEditTeam.setText(bundle.getString("ChangeRosters")); // NOI18N
        jmiEditTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditTeamActionPerformed(evt);
            }
        });
        jmnTools.add(jmiEditTeam);
        jmnTools.add(jSeparator5);

        jcxAllowSpecialSkill.setText(bundle.getString("AUTORISER LES COMPÉTENCES SPÉCIALES")); // NOI18N
        jcxAllowSpecialSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxAllowSpecialSkillActionPerformed(evt);
            }
        });
        jmnTools.add(jcxAllowSpecialSkill);
        jmnTools.add(jSeparator4);

        jmiNafLoad.setText("Mettre à jour les informations NAF des coachs");
        jmiNafLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNafLoadActionPerformed(evt);
            }
        });
        jmnTools.add(jmiNafLoad);
        jmnTools.add(jSeparator11);

        jcxPatchPortugal.setText(bundle.getString("PortugalPatch")); // NOI18N
        jcxPatchPortugal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxPatchPortugalActionPerformed(evt);
            }
        });
        jmnTools.add(jcxPatchPortugal);
        jmnTools.add(jSeparator13);

        jcxUseColor.setSelected(true);
        jcxUseColor.setText(bundle.getString("UseColor")); // NOI18N
        jcxUseColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxUseColorActionPerformed(evt);
            }
        });
        jmnTools.add(jcxUseColor);

        jcxUseImage.setSelected(true);
        jcxUseImage.setText(bundle.getString("UseImage")); // NOI18N
        jcxUseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxUseImageActionPerformed(evt);
            }
        });
        jmnTools.add(jcxUseImage);

        jMenuBar1.add(jmnTools);

        jmnParameters.setText(bundle.getString("Parameters")); // NOI18N

        jmiGenerateFirstRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Generate.png"))); // NOI18N
        jmiGenerateFirstRound.setText(bundle.getString("GenerateFirstRound")); // NOI18N
        jmiGenerateFirstRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGenerateFirstRoundActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiGenerateFirstRound);
        jmnParameters.add(jSeparator6);

        jmiSubstitutePlayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/User2.png"))); // NOI18N
        jmiSubstitutePlayer.setText(bundle.getString("MakeSubstitution")); // NOI18N
        jmiSubstitutePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSubstitutePlayerActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiSubstitutePlayer);

        jmiAddCoach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/User2.png"))); // NOI18N
        jmiAddCoach.setText(bundle.getString("AddCoach")); // NOI18N
        jmiAddCoach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAddCoachActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiAddCoach);

        jMenuBar1.add(jmnParameters);

        jmnRound.setText(bundle.getString("CurrentRound")); // NOI18N

        jmiGenerateNextRound.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jmiGenerateNextRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Generate.png"))); // NOI18N
        jmiGenerateNextRound.setText(bundle.getString("GenerateNextRoundKey")); // NOI18N
        jmiGenerateNextRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGenerateNextRoundActionPerformed(evt);
            }
        });
        jmnRound.add(jmiGenerateNextRound);
        jmnRound.add(jSeparator7);

        jmiDelRound.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jmiDelRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Halt.png"))); // NOI18N
        jmiDelRound.setText(bundle.getString("DeleteCurrentRoundKey")); // NOI18N
        jmiDelRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDelRoundActionPerformed(evt);
            }
        });
        jmnRound.add(jmiDelRound);
        jmnRound.add(jSeparator8);

        jmiChangePairing.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jmiChangePairing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Swap.png"))); // NOI18N
        jmiChangePairing.setText(bundle.getString("ChangePairing")); // NOI18N
        jmiChangePairing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiChangePairingActionPerformed(evt);
            }
        });
        jmnRound.add(jmiChangePairing);
        jmnRound.add(jSeparator9);

        jckmiRoundOnly.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jckmiRoundOnly.setText(bundle.getString("RoundOnlyRanking")); // NOI18N
        jckmiRoundOnly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckmiRoundOnlyActionPerformed(evt);
            }
        });
        jmnRound.add(jckmiRoundOnly);
        jmnRound.add(jSeparator10);

        jmiAddFreeMatch.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ADD, java.awt.event.InputEvent.CTRL_MASK));
        jmiAddFreeMatch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jmiAddFreeMatch.setText(bundle.getString("AddMatch")); // NOI18N
        jmiAddFreeMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAddFreeMatchActionPerformed(evt);
            }
        });
        jmnRound.add(jmiAddFreeMatch);

        jmiDelFreeMatch.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SUBTRACT, java.awt.event.InputEvent.CTRL_MASK));
        jmiDelFreeMatch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jmiDelFreeMatch.setText(bundle.getString("DelMatch")); // NOI18N
        jmiDelFreeMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDelFreeMatchActionPerformed(evt);
            }
        });
        jmnRound.add(jmiDelFreeMatch);
        jmnRound.add(jSeparator12);

        jmiConceedMatch.setText(bundle.getString("MatchConceed")); // NOI18N
        jmiConceedMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConceedMatchActionPerformed(evt);
            }
        });
        jmnRound.add(jmiConceedMatch);

        jmiCancelConceedMatch.setText(bundle.getString("AnnulerMatchConceed")); // NOI18N
        jmiCancelConceedMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCancelConceedMatchActionPerformed(evt);
            }
        });
        jmnRound.add(jmiCancelConceedMatch);

        jmiRefuseMatch.setText(bundle.getString("MatchRefused")); // NOI18N
        jmiRefuseMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRefuseMatchActionPerformed(evt);
            }
        });
        jmnRound.add(jmiRefuseMatch);

        jmiCancelMatchRefuse.setText(bundle.getString("CancelMatchRefuse")); // NOI18N
        jmiCancelMatchRefuse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCancelMatchRefuseActionPerformed(evt);
            }
        });
        jmnRound.add(jmiCancelMatchRefuse);

        jMenuBar1.add(jmnRound);

        jmnHelp.setText(bundle.getString("?")); // NOI18N

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
    private void jmiSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSaveAsActionPerformed
        final JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("TourMaXMLFile"), new String[]{StringConstants.CS_XML, StringConstants.CS_MINXML});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            StringBuffer url2 = new StringBuffer(jfc.getSelectedFile().getAbsolutePath());
            String ext = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
            final int i = url2.toString().lastIndexOf('.');
            if (i > 0 && i < url2.length() - 1) {
                ext = url2.substring(i + 1).toLowerCase();
            }

            if (!ext.equals(StringConstants.CS_MINXML)) {
                url2 = url2.append(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".XML"));
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
        final FileFilter filter1 = new ExtensionFileFilter(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("TourMaXMLFile"), new String[]{StringConstants.CS_XML, StringConstants.CS_MINXML});
        jfc.setFileFilter(filter1);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            Tournament.getTournament().loadXML(jfc.getSelectedFile());
            mFile =
                    jfc.getSelectedFile();
            /*            for (int i = jtpMain.getTabCount() - 1; i
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
             }*/
            updateTree();
            update();

        }
    }//GEN-LAST:event_jmiChargerActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiNouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNouveauActionPerformed


        /*for (int i = jtpMain.getTabCount() - 1; i
         >= 0; i--) {
         final Component obj = jtpMain.getComponentAt(i);
         if (obj instanceof JPNRound) {
         jtpMain.remove(obj);
         }
         }*/
        mTournament = Tournament.resetTournament();

        JdgParameters jdgParams = new JdgParameters(this, true);
        jdgParams.setVisible(true);

        /*final ArrayList<String> Games = new ArrayList<String>();
         Games.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BLOOD BOWL"));
         Games.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DREADBALL"));
         final int res2 = JOptionPane.showOptionDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChooseGame"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Games.toArray(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BLOOD BOWL"));
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
         }*/

        mTournament = Tournament.getTournament();
        if (mTournament.getParams().mGame == RosterType.C_DREAD_BALL) {
            RosterType.initCollection(RosterType.C_DREAD_BALL);
            jmiExport.setEnabled(false);
            jmiExportFbb.setEnabled(false);
            jcxAllowSpecialSkill.setEnabled(false);
        } else {
            RosterType.initCollection(RosterType.C_BLOOD_BOWL);
            lrb.getLRB();
        }

        mTournament.getGroups().clear();
        final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);
        final Group group = new Group(bundle.getString("NoneKey"));
        mTournament.getGroups().add(group);

        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {
            group.mRosters.add(RosterType.mRosterTypes.get(RosterType.mRostersNames.get(i)));
        }

        /*int multi = JOptionPane.showConfirmDialog(this, "S'agit-il d'un tournoi multi roster ?", "MultiRoster", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

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
         }*/


        update();
    }//GEN-LAST:event_jmiNouveauActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportActionPerformed
        final JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF XML FILE"), new String[]{StringConstants.CS_XML, StringConstants.CS_MINXML});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            Tournament.getTournament().exportNAF(jfc.getSelectedFile());
        }
    }//GEN-LAST:event_jmiExportActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExitActionPerformed
        int res = JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VOULEZ VOUS SAUVGARDER ?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EXIT"), JOptionPane.YES_NO_CANCEL_OPTION);
        if (res == JOptionPane.CANCEL_OPTION) {
        } else {
            if (res == JOptionPane.YES_OPTION) {
                if (mFile.getName().equals("")) {
                    jmiSaveAsActionPerformed(null);
                } else {
                    jmiSaveActionPerformed(null);
                }
            }
            System.exit(0);
        }
    }//GEN-LAST:event_jmiExitActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiRevisionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRevisionsActionPerformed
        jdgRevisions jdg = new jdgRevisions(this, true);
        jdg.setVisible(true);
}//GEN-LAST:event_jmiRevisionsActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiAideEnLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAideEnLigneActionPerformed
        jdgOnlineHelp jdg = new jdgOnlineHelp(this, false);
        jdg.setVisible(true);
    }//GEN-LAST:event_jmiAideEnLigneActionPerformed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VOULEZ VOUS SAUVGARDER ?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EXIT"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (mFile.getName().equals("")) {
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
}//GEN-LAST:event_jmiAboutActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VOULEZ VOUS SAUVGARDER ?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EXIT"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (mFile == null) {
                jmiSaveAsActionPerformed(null);
            } else {

                if (mFile.getName().equals("")) {
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
        final FileFilter filter1 = new ExtensionFileFilter(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FBB CSV FILE"), new String[]{java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CSV"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CSV")});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            Tournament.getTournament().exportFBB(jfc.getSelectedFile());
        }
    }//GEN-LAST:event_jmiExportFbbActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiExportFbb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportFbb1ActionPerformed
        final JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FBB XML FILE"), new String[]{java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FBB_XML"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FBB_XML")});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            final File f = jfc.getSelectedFile();
            if (f.getName().endsWith(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".FBB_XML"))) {
                Tournament.getTournament().exportFullFBB(f);
            } else {
                Tournament.getTournament().exportFullFBB(new File(f.getAbsolutePath() + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".FBB_XML")));
            }
        }
    }//GEN-LAST:event_jmiExportFbb1ActionPerformed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiGenerateFirstRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGenerateFirstRoundActionPerformed
        if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("AreYouSure?ItWillEraseAllRounds"), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("FirstRound"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (mTournament.getParams().mTeamTournament && (mTournament.getParams().mTeamPairing == 1) && mTournament.getTeams().size() % 2 > 0) {
                JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OddTeamNumber"));
            } else {

                final ArrayList<String> labels = new ArrayList<>();
                final ArrayList<Integer> Options = new ArrayList<>();

                /**
                 * GenRandom possible ?
                 */
                labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ALÉATOIRE"));
                Options.add(Generation.GEN_RANDOM);

                /**
                 * Coupe
                 */
                labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COUPE"));
                Options.add(Generation.GEN_CUP);

                /**
                 * Ordre
                 */
                labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ORDER D'INSCRIPTION"));
                Options.add(Generation.GEN_ORDER);

                /**
                 * Round Robin
                 */
                labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND ROBIN"));
                Options.add(Generation.GEN_RROBIN);

                /**
                 * manuel
                 */
                labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHOIX MANUEL"));
                Options.add(Generation.GEN_MANUAL);

                /**
                 * Poules
                 */
                labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POULES"));
                Options.add(Generation.GEN_POOL);

                /**
                 * Naf Ranking
                 */
                labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF_RANK"));
                Options.add(Generation.GEN_NAF);

                /**
                 * Naf Ranking
                 */
                labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FREE_ROUND"));
                Options.add(Generation.GEN_FREE);

                if (Tournament.getTournament().getParams().mTeamTournament) {
                    if (Tournament.getTournament().getParams().mTeamPairing == 0) {
                        /**
                         * Balanced Options
                         */
                        labels.add("Aléatoire et équilibrage");
                        Options.add(Generation.GEN_BALANCED);
                    }
                }


                final JPanel jpn = new JPanel(new BorderLayout());
                final JComboBox jcb = new JComboBox(labels.toArray());
                jpn.add(jcb, BorderLayout.CENTER);
                final JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHOISISSEZ LA MÉTHODE DE GÉNÉRATION: "));
                jpn.add(jlb, BorderLayout.NORTH);

                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jpn, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GÉNÉRATION"), JOptionPane.QUESTION_MESSAGE);
                final int index = jcb.getSelectedIndex();

                Generation.generateFirstRound(Options.get(index));

                /*                for (int i = jtpMain.getTabCount() - 1; i >= 0; i--) {
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

                 jtpMain.setSelectedIndex(mTournament.getRounds().size());*/
                updateTree();
                update();

            }

        }
    }//GEN-LAST:event_jmiGenerateFirstRoundActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtrPanelsValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jtrPanelsValueChanged
        final TreePath path = evt.getNewLeadSelectionPath();
        if (path != null) {

            final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            if (node != null) {
                final Object object = node.getUserObject();
                if (object instanceof Parameters) {
                    jspSplit.remove(jpnContent);
                    jpnContent = new JPNParameters();
                    jspSplit.add(jpnContent, JSplitPane.RIGHT);
                    ((JPNParameters) jpnContent).update();
                    jspSplit.setDividerLocation(200);
                    //System.gc();
                    this.revalidate();
                }
                if (object instanceof Round) {
                    for (int i = 0; i < mTournament.getRounds().size(); i++) {
                        if (mTournament.getRounds().get(i).equals(object)) {
                            jspSplit.remove(jpnContent);
                            jpnContent = new JPNRound(i, (Round) object, mTournament);
                            jspSplit.add(jpnContent, JSplitPane.RIGHT);
                            ((JPNRound) jpnContent).update();
                            jspSplit.setDividerLocation(200);
                            ((JPNRound) jpnContent).setRoundOnly(jckmiRoundOnly.isSelected());
                            //System.gc();
                            this.revalidate();
                            break;
                        }
                    }
                }

                if (object.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CUP"))) {
                    jspSplit.remove(jpnContent);
                    jpnContent = new JPNCup();
                    jspSplit.add(jpnContent, JSplitPane.RIGHT);
                    ((JPNCup) jpnContent).update();
                    jspSplit.setDividerLocation(200);
                    //System.gc();
                    this.revalidate();
                }

                if (object.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("STATISTICS"))) {
                    jspSplit.remove(jpnContent);
                    jpnContent = new JPNStatistics();
                    jspSplit.add(jpnContent, JSplitPane.RIGHT);
                    ((JPNStatistics) jpnContent).update();
                    jspSplit.setDividerLocation(200);
                    //System.gc();
                    this.revalidate();
                }

            }

            if (jpnContent instanceof JPNRound) {
                Round r = ((JPNRound) jpnContent).getRound();
                if (mTournament.getRounds().indexOf(r) == mTournament.getRounds().size() - 1) {
                    jmiDelRound.setEnabled(true);
                    jmiGenerateNextRound.setEnabled(true);
                    jmiChangePairing.setEnabled(true);
                    jmiAddFreeMatch.setEnabled(true);
                    jmiDelFreeMatch.setEnabled(true);
                } else {
                    jmiDelRound.setEnabled(false);
                    jmiGenerateNextRound.setEnabled(false);
                    jmiChangePairing.setEnabled(false);
                    jmiAddFreeMatch.setEnabled(false);
                    jmiDelFreeMatch.setEnabled(false);
                }
                jckmiRoundOnly.setEnabled(true);
            } else {
                jckmiRoundOnly.setEnabled(false);
                jmiDelRound.setEnabled(false);
                jmiGenerateNextRound.setEnabled(false);
                jmiChangePairing.setEnabled(false);
                jmiAddFreeMatch.setEnabled(false);
                jmiDelFreeMatch.setEnabled(false);
            }

            repaint();
        }

    }//GEN-LAST:event_jtrPanelsValueChanged

    private void jmiNafLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNafLoadActionPerformed

        ArrayList<Coach> alc = Tournament.getTournament().getCoachs();

        ProgressMonitor progressMonitor = new ProgressMonitor(this,
                "Download from NAF",
                "Downloading", 0, alc.size());
        progressMonitor.setProgress(0);
        for (int i = 0; i < alc.size(); i++) {
            Coach c = alc.get(i);
            progressMonitor.setNote("Download: " + c.mName);
            c.mNafRank = NAF.GetRanking(c.mName, c);
            progressMonitor.setProgress(i + 1);
        }
        progressMonitor.close();
        update();
    }//GEN-LAST:event_jmiNafLoadActionPerformed

    private void jmiSubstitutePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSubstitutePlayerActionPerformed
        // Select Player to subtitute
        ArrayList<Coach> list = new ArrayList<>(Tournament.getTournament().getCoachs());
        for (int i = 0; i < Tournament.getTournament().getCoachs().size(); i++) {
            if (!Tournament.getTournament().getCoachs().get(i).mActive) {
                list.remove(i);
            }
        }
        JComboBox jcb = new JComboBox(list.toArray());
        JLabel jlb = new JLabel("Quel coach se fait remplacer ?");
        JPanel jpn = new JPanel(new BorderLayout());
        jpn.add(jlb, BorderLayout.NORTH);
        jpn.add(jcb, BorderLayout.CENTER);
        int ret = JOptionPane.showConfirmDialog(this, jpn, "Remplacement", JOptionPane.OK_CANCEL_OPTION);
        if (ret == JOptionPane.OK_OPTION) {
            Coach c = (Coach) jcb.getSelectedItem();
            ArrayList<String> matchs_descr = new ArrayList<>();

            // Select Match
            for (int i = 0; i < c.mMatchs.size(); i++) {
                CoachMatch m = (CoachMatch) c.mMatchs.get(i);
                String tmp = "Ronde " + ((Tournament.getTournament().getRounds().indexOf(m.mRound)) + 1);
                tmp = tmp + " / " + m.mCompetitor1.getDecoratedName() + " VS " + m.mCompetitor2.getDecoratedName();
                matchs_descr.add(tmp);
            }
            jpn.remove(jcb);
            jcb = new JComboBox(matchs_descr.toArray());
            jpn.add(jcb, BorderLayout.CENTER);
            jlb.setText("Quel match ?");
            JOptionPane.showConfirmDialog(this, jpn, "Remplacement", JOptionPane.OK_OPTION);
            CoachMatch m = (CoachMatch) c.mMatchs.get(jcb.getSelectedIndex());

            // Select subtitute
            ArrayList<Coach> availableCoachs = new ArrayList<>();
            ArrayList<String> availableCoachsName = new ArrayList<>();
            for (int i = 0; i < Tournament.getTournament().getCoachs().size(); i++) {
                Coach sub = Tournament.getTournament().getCoachs().get(i);
                if (!sub.mActive) {
                    availableCoachs.add(sub);
                }
            }
            availableCoachs.add(Coach.getNullCoach());
            for (int i = 0; i < availableCoachs.size(); i++) {
                availableCoachsName.add(availableCoachs.get(i).getDecoratedName());
            }

            availableCoachsName.add("Nouveau ...");
            jpn.remove(jcb);
            jcb = new JComboBox(availableCoachsName.toArray());
            jpn.add(jcb, BorderLayout.CENTER);
            jlb.setText("Choisissez un remplaçant");
            JOptionPane.showConfirmDialog(this, jpn, "Remplacement", JOptionPane.OK_OPTION);

            // Create Substitution

            // If None
            if (jcb.getSelectedIndex() == availableCoachs.size() - 1) {
                if (m.mCompetitor1 == c) {
                    m.mSubstitute1 = null;
                }
                if (m.mCompetitor2 == c) {
                    m.mSubstitute2 = null;
                }
            } else {
                Coach sub;
                // New
                if (jcb.getSelectedIndex() == availableCoachs.size()) {
                    sub = new Coach();
                    sub.mRoster = RosterType.mRosterTypes.get(0);
                    jdgCoach jdg = new jdgCoach(this, true, sub);
                    jdg.setVisible(true);
                    Tournament.getTournament().getCoachs().add(sub);
                    sub.mActive = false;
                } else {
                    sub = availableCoachs.get(jcb.getSelectedIndex());
                }

                Substitute s = new Substitute();
                s.mMatch = m;
                s.mSubstitute = sub;
                s.mTitular = c;

                if (m.mCompetitor1 == c) {
                    m.mSubstitute1 = s;
                }
                if (m.mCompetitor2 == c) {
                    m.mSubstitute2 = s;
                }
            }
        }
    }//GEN-LAST:event_jmiSubstitutePlayerActionPerformed

    private void jmiAddCoachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAddCoachActionPerformed
        final jdgCoach w = new jdgCoach(MainFrame.getMainFrame(), true);
        w.setVisible(true);
        update();
    }//GEN-LAST:event_jmiAddCoachActionPerformed

    private void jmiDelRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDelRoundActionPerformed
        if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ConfirmEraseCurrentRound"), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("EraseRound"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {


            // Remove mRound
            Round round = ((JPNRound) jpnContent).getRound();

            // Remove matchs from coach reference list
            ArrayList<CoachMatch> cms = round.getCoachMatchs();
            for (int i = 0; i < cms.size(); i++) {
                final CoachMatch m = cms.get(i);
                ((Coach) m.mCompetitor1).mMatchs.remove(m);
                ((Coach) m.mCompetitor2).mMatchs.remove(m);
            }

            // remove matchs from competitors
            for (int i = 0; i < round.getMatchs().size(); i++) {
                final Match m = round.getMatchs().get(i);
                m.mCompetitor1.mMatchs.remove(m);
                m.mCompetitor2.mMatchs.remove(m);
            }

            mTournament.getRounds().remove(round);

            update();
            updateTree();
        }
    }//GEN-LAST:event_jmiDelRoundActionPerformed

    private void jmiGenerateNextRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGenerateNextRoundActionPerformed

        final ArrayList<String> labels = new ArrayList<>();
        final ArrayList<Integer> Options = new ArrayList<>();

        Round round = ((JPNRound) jpnContent).getRound();
        int round_number = mTournament.getRounds().indexOf(round);
        /**
         * Swiss possible ?
         */
        if ((!round.mCup) && (!mTournament.mRoundRobin)) {
            labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RONDE SUISSE"));
            Options.add(Generation.GEN_SWISS);
        }

        /**
         * QSwiss possible ?
         */
        if ((!round.mCup) && (!mTournament.mRoundRobin)) {
            labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RONDE SUISSE ACCELERÉE"));
            Options.add(Generation.GEN_QSWISS);
        }

        /**
         * GenRandom possible ?
         */
        if ((!round.mCup) && (!mTournament.mRoundRobin)) {
            labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ALÉATOIRE"));
            Options.add(Generation.GEN_RANDOM);
        }

        /**
         * Coupe
         */
        labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COUPE"));
        Options.add(Generation.GEN_CUP);

        /**
         *
         * Libre
         */
        if ((!round.mCup) && (!mTournament.mRoundRobin)) {
            labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FREE_ROUND"));
            Options.add(Generation.GEN_FREE);
        }


        final JPanel jpn = new JPanel(new BorderLayout());
        final JComboBox jcb = new JComboBox(labels.toArray());
        jpn.add(jcb, BorderLayout.CENTER);
        final JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHOISISSEZ LA MÉTHODE DE GÉNÉRATION: "));
        jpn.add(jlb, BorderLayout.NORTH);

        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jpn, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GÉNÉRATION"), JOptionPane.QUESTION_MESSAGE);

        final int index = jcb.getSelectedIndex();


        Generation.NextRound(round, Options.get(index), round_number);
        ((JPNRound) jpnContent).update();
        update();
        updateTree();
    }//GEN-LAST:event_jmiGenerateNextRoundActionPerformed

    private void jmiChangePairingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiChangePairingActionPerformed

        final jdgChangePairing jdg = new jdgChangePairing(MainFrame.getMainFrame(), true, ((JPNRound) jpnContent).getRound());
        jdg.setVisible(true);
        ((JPNRound) jpnContent).update();
        update();
    }//GEN-LAST:event_jmiChangePairingActionPerformed

    private void jckmiRoundOnlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckmiRoundOnlyActionPerformed
        ((JPNRound) jpnContent).setRoundOnly(jckmiRoundOnly.isSelected());
        ((JPNRound) jpnContent).update();
    }//GEN-LAST:event_jckmiRoundOnlyActionPerformed

    private void jmiAddFreeMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAddFreeMatchActionPerformed

        Round round = ((JPNRound) jpnContent).getRound();
        if (Tournament.getTournament().getParams().mTeamTournament) {

            final ArrayList<Team> teams1 = new ArrayList<>();
            final ArrayList<Team> teams2 = new ArrayList<>();

            final JComboBox<String> jcb1 = new JComboBox<>();
            final JComboBox<String> jcb2 = new JComboBox<>();

            for (int i = 0; i < mTournament.getTeams().size(); i++) {
                final Team c = mTournament.getTeams().get(i);
                teams1.add(c);
                teams2.add(c);
                jcb1.addItem(c.mName);
                jcb2.addItem(c.mName);
            }

            boolean ValidMatch = false;

            while (!ValidMatch) {
                jcb1.setSelectedIndex(0);
                jcb2.setSelectedIndex(1);


                final JPanel jpnQuestion = new JPanel(new BorderLayout(0, 0));
                jpnQuestion.add(jcb1, BorderLayout.WEST);
                jpnQuestion.add(jcb2, BorderLayout.EAST);
                final JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" VS "));
                jpnQuestion.add(jlb, BorderLayout.CENTER);

                final int ret = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), jpnQuestion, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH LIBRE"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                if (ret == JOptionPane.OK_OPTION) {
                    if (jcb1.getSelectedIndex() != jcb2.getSelectedIndex()) {
                        TeamMatch m = new TeamMatch(round);
                        m.mCompetitor1 = teams1.get(jcb1.getSelectedIndex());
                        m.mCompetitor2 = teams2.get(jcb2.getSelectedIndex());
                        jdgPairing jdg = new jdgPairing(MainFrame.getMainFrame(), true, teams1.get(jcb1.getSelectedIndex()), teams2.get(jcb2.getSelectedIndex()), round, m.mMatchs);
                        jdg.setVisible(true);
                        round.getMatchs().add(m);

                        for (int i = 0; i < m.mMatchs.size(); i++) {
                            CoachMatch c = m.mMatchs.get(i);
                            c.mCompetitor1.mMatchs.add(c);
                            c.mCompetitor2.mMatchs.add(c);
                        }

                        ValidMatch = true;
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH IMPOSSIBLE"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ERREUR"), JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    ValidMatch = true;
                }
            }
        } else {
            final ArrayList<Coach> Coachs1 = new ArrayList<>();
            final ArrayList<Coach> Coachs2 = new ArrayList<>();

            final JComboBox<String> jcb1 = new JComboBox<>();
            final JComboBox<String> jcb2 = new JComboBox<>();

            for (int i = 0; i < mTournament.GetActiveCoaches().size(); i++) {
                final Coach c = mTournament.GetActiveCoaches().get(i);
                Coachs1.add(c);
                Coachs2.add(c);
                jcb1.addItem(c.mName);
                jcb2.addItem(c.mName);
            }

            boolean ValidMatch = false;

            while (!ValidMatch) {
                jcb1.setSelectedIndex(0);
                jcb2.setSelectedIndex(1);

                final JPanel jpnQuestion = new JPanel(new BorderLayout(0, 0));
                jpnQuestion.add(jcb1, BorderLayout.WEST);
                jpnQuestion.add(jcb2, BorderLayout.EAST);
                final JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" VS "));
                jpnQuestion.add(jlb, BorderLayout.CENTER);

                final int ret = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), jpnQuestion, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH LIBRE"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                if (ret == JOptionPane.OK_OPTION) {
                    if (jcb1.getSelectedIndex() != jcb2.getSelectedIndex()) {
                        final CoachMatch m = new CoachMatch(round);
                        m.mCompetitor1 = Coachs1.get(jcb1.getSelectedIndex());
                        m.mCompetitor2 = Coachs2.get(jcb2.getSelectedIndex());

                        round.getMatchs().add(m);
                        ((Coach) m.mCompetitor1).mMatchs.add(m);
                        ((Coach) m.mCompetitor2).mMatchs.add(m);
                        ValidMatch = true;
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH IMPOSSIBLE"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ERREUR"), JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    ValidMatch = true;
                }
            }
        }


        ((JPNRound) jpnContent).update();
    }//GEN-LAST:event_jmiAddFreeMatchActionPerformed

    private void jmiDelFreeMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDelFreeMatchActionPerformed

        int row = ((JPNRound) jpnContent).getMatchTableSelectedRow();
        if (row >= 0) {
            Round round = ((JPNRound) jpnContent).getRound();
            if (Tournament.getTournament().getParams().mTeamTournament) {
                CoachMatch c = round.getCoachMatchs().get(row);
                // Fint TeamMatch corresponding to CoachMatch
                for (int j = 0; j < round.getMatchs().size(); j++) {
                    TeamMatch t = ((TeamMatch) round.getMatchs().get(j));
                    for (int i = 0; i < t.mMatchs.size(); i++) {
                        if (t.mMatchs.contains(c)) {
                            for (int k = 0; k < t.mMatchs.size(); k++) {
                                CoachMatch cm = t.mMatchs.get(k);
                                cm.mCompetitor1.mMatchs.remove(cm);
                                cm.mCompetitor2.mMatchs.remove(cm);
                            }
                            t.mCompetitor1.mMatchs.remove(t);
                            t.mCompetitor2.mMatchs.remove(t);
                            round.getMatchs().remove(t);
                            j = round.getMatchs().size();
                            i = t.mMatchs.size();
                        }
                    }
                }
            } else {
                CoachMatch c = (CoachMatch) round.getMatchs().get(row);
                c.mCompetitor1.mMatchs.remove(c);
                c.mCompetitor2.mMatchs.remove(c);
                round.getMatchs().remove(c);
            }
            ((JPNRound) jpnContent).update();
        }
    }//GEN-LAST:event_jmiDelFreeMatchActionPerformed

    private void jcxPatchPortugalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxPatchPortugalActionPerformed
        mTournament.getParams().mPortugal = jcxPatchPortugal.isSelected();
    }//GEN-LAST:event_jcxPatchPortugalActionPerformed

    private void jmiConceedMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConceedMatchActionPerformed
        try {
            int nbMatch = ((JPNRound) jpnContent).getMatchTableSelectedRow();
            CoachMatch m = ((JPNRound) jpnContent).getRound().getCoachMatchs().get(nbMatch);
            if (m.concedeedBy1 || m.concedeedBy2 || m.refusedBy1 || m.refusedBy2) {
                JOptionPane.showMessageDialog(null, "Error", "Match déjà concedé ou refusé", JOptionPane.ERROR_MESSAGE);
            } else {
                Object[] options = new Object[3];
                options[0] = m.mCompetitor1;
                options[1] = m.mCompetitor2;
                options[2] = "Annuler";

                Object option = JOptionPane.showInputDialog(null, "Concéder un match", "Qui concède le match ?", JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

                if (option.equals(m.mCompetitor1)) {
                    m.concedeedBy1 = true;
                    m.concedeedBy2 = false;
                }
                if (option.equals(m.mCompetitor2)) {
                    m.concedeedBy2 = true;
                    m.concedeedBy1 = false;
                }
                update();
                ((JPNRound) jpnContent).update();
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jmiConceedMatchActionPerformed

    private void jmiCancelConceedMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCancelConceedMatchActionPerformed
        try {
            int nbMatch = ((JPNRound) jpnContent).getMatchTableSelectedRow();
            CoachMatch m = ((JPNRound) jpnContent).getRound().getCoachMatchs().get(nbMatch);
            m.concedeedBy1 = false;
            m.concedeedBy2 = false;
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jmiCancelConceedMatchActionPerformed

    private void jmiRefuseMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRefuseMatchActionPerformed
        try {
            int nbMatch = ((JPNRound) jpnContent).getMatchTableSelectedRow();
            CoachMatch m = ((JPNRound) jpnContent).getRound().getCoachMatchs().get(nbMatch);
            if (m.concedeedBy1 || m.concedeedBy2 || m.refusedBy1 || m.refusedBy2) {
                JOptionPane.showMessageDialog(null, "Error", "Match déjà concedé ou refusé", JOptionPane.ERROR_MESSAGE);
            } else {
                Object[] options = new Object[3];
                options[0] = m.mCompetitor1;
                options[1] = m.mCompetitor2;
                options[2] = "Annuler";

                Object option = JOptionPane.showInputDialog(null, "Refuser un match", "Qui refuse le match ?", JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

                if (option.equals(m.mCompetitor1)) {
                    m.refusedBy1 = true;
                    m.refusedBy2 = false;
                }
                if (option.equals(m.mCompetitor2)) {
                    m.refusedBy2 = true;
                    m.refusedBy1 = false;
                }
                update();
                ((JPNRound) jpnContent).update();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jmiRefuseMatchActionPerformed

    private void jmiCancelMatchRefuseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCancelMatchRefuseActionPerformed
        try {
            int nbMatch = ((JPNRound) jpnContent).getMatchTableSelectedRow();
            CoachMatch m = ((JPNRound) jpnContent).getRound().getCoachMatchs().get(nbMatch);
            m.refusedBy1 = false;
            m.refusedBy2 = false;
        } catch (Exception e) {
        }// TODO add your handling code here:
    }//GEN-LAST:event_jmiCancelMatchRefuseActionPerformed

    private void jcxUseColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxUseColorActionPerformed
        Tournament.getTournament().getParams().useColor=jcxUseColor.isSelected();
        if (jpnContent instanceof JPNRound) {
            ((JPNRound) jpnContent).update();
        }
    }//GEN-LAST:event_jcxUseColorActionPerformed

    private void jcxUseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxUseImageActionPerformed
        Tournament.getTournament().getParams().useImage=jcxUseImage.isSelected();
        if (jpnContent instanceof JPNRound) {
            ((JPNRound) jpnContent).update();
        }
    }//GEN-LAST:event_jcxUseImageActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                final ArrayList<String> StartOptions = new ArrayList<>();
                StartOptions.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NewGame"));
                StartOptions.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Open"));
                StartOptions.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UseRosterEditor"));
                final int res = JOptionPane.showOptionDialog(null, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NewGameOrOpen"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, StartOptions.toArray(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Open"));

                if ((res == 0) || (res == 1)) {
                    MainFrame window = MainFrame.getMainFrame(res);
                    window.setVisible(true);
                }

                if (res == 2) {
                    teamma.views.JdgRoster jdg = new JdgRoster(null, true);
                    jdg.setVisible(true);
                    System.exit(0);
                }
            }
        });
    }
    protected static MainFrame mSingleton;

    public static MainFrame getMainFrame() {
        if (mSingleton == null) {
            mSingleton = new MainFrame(0);
        }

        return mSingleton;
    }

    public static MainFrame getMainFrame(int res) {
        if (mSingleton == null) {
            mSingleton = new MainFrame(res);
        }

        return mSingleton;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JCheckBoxMenuItem jckmiRoundOnly;
    private javax.swing.JCheckBoxMenuItem jcxAllowSpecialSkill;
    private javax.swing.JCheckBoxMenuItem jcxPatchPortugal;
    private javax.swing.JCheckBoxMenuItem jcxUseColor;
    private javax.swing.JCheckBoxMenuItem jcxUseImage;
    private javax.swing.JMenuItem jmiAbout;
    private javax.swing.JMenuItem jmiAddCoach;
    private javax.swing.JMenuItem jmiAddFreeMatch;
    private javax.swing.JMenuItem jmiAideEnLigne;
    private javax.swing.JMenuItem jmiCancelConceedMatch;
    private javax.swing.JMenuItem jmiCancelMatchRefuse;
    private javax.swing.JMenuItem jmiChangePairing;
    private javax.swing.JMenuItem jmiCharger;
    private javax.swing.JMenuItem jmiConceedMatch;
    private javax.swing.JMenuItem jmiDelFreeMatch;
    private javax.swing.JMenuItem jmiDelRound;
    private javax.swing.JMenuItem jmiEditTeam;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiExport;
    private javax.swing.JMenuItem jmiExportFbb;
    private javax.swing.JMenuItem jmiExportFbb1;
    private javax.swing.JMenuItem jmiGenerateFirstRound;
    private javax.swing.JMenuItem jmiGenerateNextRound;
    private javax.swing.JMenuItem jmiNafLoad;
    private javax.swing.JMenuItem jmiNouveau;
    private javax.swing.JMenuItem jmiRefuseMatch;
    private javax.swing.JMenuItem jmiRevisions;
    private javax.swing.JMenuItem jmiSave;
    private javax.swing.JMenuItem jmiSaveAs;
    private javax.swing.JMenuItem jmiSubstitutePlayer;
    private javax.swing.JMenu jmnFile;
    private javax.swing.JMenu jmnHelp;
    private javax.swing.JMenu jmnParameters;
    private javax.swing.JMenu jmnRound;
    private javax.swing.JMenu jmnTools;
    private javax.swing.JPanel jpnContent;
    private javax.swing.JSplitPane jspSplit;
    private javax.swing.JTree jtrPanels;
    // End of variables declaration//GEN-END:variables
}
