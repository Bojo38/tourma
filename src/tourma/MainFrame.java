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
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ProgressMonitor;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import teamma.data.LRB;
import teamma.views.JdgRoster;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.ETeamPairing;
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
import tourma.utils.TMultiServer;
import tourma.views.JPNCup;
import tourma.views.JPNStatistics;
import tourma.views.fullscreen.JFullScreen;
import tourma.views.fullscreen.JFullScreenClanRank;
import tourma.views.fullscreen.JFullScreenClanTeamAnnex;
import tourma.views.fullscreen.JFullScreenIndivAnnex;
import tourma.views.fullscreen.JFullScreenIndivRank;
import static tourma.views.fullscreen.JFullScreenIndivRank.C_GROUP;
import static tourma.views.fullscreen.JFullScreenIndivRank.C_POOL;
import tourma.views.fullscreen.JFullScreenMatchs;
import tourma.views.fullscreen.JFullScreenTeamRank;
import tourma.views.parameters.JPNParameters;
import tourma.views.round.JPNRound;
import tourma.views.system.JdgAbout;
import tourma.views.system.JdgOnlineHelp;
import tourma.views.system.JdgRevisions;

/**
 *
 * @author Frederic Berger
 */
//@com.yworks.util.annotation.Obfuscation ( exclude = true, applyToMembers = true )
public final class MainFrame extends javax.swing.JFrame {

    private Tournament mTournament;
    private File mFile = null;

    /**
     * Creates new form MainFrame
     *
     * @param res Choice selected 0=> new tournament, open an existing one if
     * other choice
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

    private void updateMenus() {
        if (jpnContent instanceof JPNRound) {
            Round r = ((JPNRound) jpnContent).getRound();
            if (mTournament.indexOfRound(r) == mTournament.getRoundsCount() - 1) {
                jmiDelRound.setEnabled(true);
                jmiGenerateNextRound.setEnabled(true);
                jmiChangePairing.setEnabled(true);
                jmiAddFreeMatch.setEnabled(true);
                jmiDelFreeMatch.setEnabled(true);
                jmiFullScreenMatchs.setEnabled(true);
                jmiFullScreenMatchsClash.setEnabled(true);
                jmiFullScreenRankGeneral.setEnabled(true);
                jmiFullScreenRankAnnexIndiv.setEnabled(true);
                jmiFullScreenRankAnnexIndiv1.setEnabled(true);

                jmiFullScreenRankAnnexCategory.setEnabled(Tournament.getTournament().getCategoriesCount() > 0);
                jmiFullScreenRankAnnexCategory1.setEnabled(Tournament.getTournament().getCategoriesCount() > 0);
                jmiFullScreenRankCategory.setEnabled(Tournament.getTournament().getCategoriesCount() > 0);

                jmiFullScreenRankAnnexGroups.setEnabled(Tournament.getTournament().getGroupsCount() > 0);
                jmiFullScreenRankAnnexGroups1.setEnabled(Tournament.getTournament().getGroupsCount() > 0);
                jmiFullScreenRankGroups.setEnabled(Tournament.getTournament().getGroupsCount() > 0);

                jmiFullScreenRankAnnexPool.setEnabled(Tournament.getTournament().getPoolCount() > 0);
                jmiFullScreenRankAnnexPool1.setEnabled(Tournament.getTournament().getPoolCount() > 0);
                jmiFullScreenPool.setEnabled(Tournament.getTournament().getPoolCount() > 0);

                jmiFullScreenRankTeam.setEnabled(mTournament.getParams().isTeamTournament());
                jmiFullScreenRankClan.setEnabled(mTournament.getClansCount() > 1);
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
            jmiFullScreenMatchs.setEnabled(true);
            jmiFullScreenMatchsClash.setEnabled(true);
            jckmiRoundOnly.setEnabled(true);
            jmiFullScreenRankTeam.setEnabled(mTournament.getParams().isTeamTournament());
            jmiFullScreenRankClan.setEnabled(mTournament.getClansCount() > 1);
            jmiFullScreenRankAnnexClan.setEnabled(mTournament.getClansCount() > 1);
            jmiFullScreenRankAnnexClan1.setEnabled(mTournament.getClansCount() > 1);
            jmiFullScreenRankAnnexTeam.setEnabled(mTournament.getParams().isTeamTournament());
            jmiFullScreenRankAnnexTeam1.setEnabled(mTournament.getParams().isTeamTournament());
            jmiEditCoef.setEnabled(mTournament.getParams().isTableBonusPerRound());
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
            jmiFullScreenMatchs.setEnabled(false);
            jmiFullScreenMatchsClash.setEnabled(false);
            jmiFullScreenRankTeam.setEnabled(false);
            jmiFullScreenRankClan.setEnabled(false);
            jmiFullScreenRankGeneral.setEnabled(false);
            jmiFullScreenRankAnnexIndiv.setEnabled(false);
            jmiFullScreenRankAnnexIndiv1.setEnabled(false);
            jmiFullScreenRankAnnexClan.setEnabled(false);
            jmiFullScreenRankAnnexClan1.setEnabled(false);
            jmiFullScreenRankAnnexTeam.setEnabled(false);
            jmiFullScreenRankAnnexTeam1.setEnabled(false);
            jmiFullScreenRankAnnexCategory.setEnabled(false);
            jmiFullScreenRankAnnexCategory1.setEnabled(false);
            jmiFullScreenRankCategory.setEnabled(false);
            jmiFullScreenRankAnnexGroups.setEnabled(false);
            jmiFullScreenRankAnnexGroups1.setEnabled(false);
            jmiFullScreenRankGroups.setEnabled(false);
            jmiFullScreenRankAnnexPool.setEnabled(false);
            jmiFullScreenRankAnnexPool1.setEnabled(false);
            jmiFullScreenPool.setEnabled(false);
            jmiEditCoef.setEnabled(false);
        }
    }

    /**
     * Update
     */
    public void update() {
        //final boolean bTourStarted = mTournament.getRoundsCount() > 0;
        jmiEditTeam.setEnabled(mTournament.getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jmiSubstitutePlayer.setEnabled(mTournament.getRoundsCount() > 0);
        final MainTreeModel dtm = new MainTreeModel();
        jtrPanels.setCellRenderer(dtm);
        jtrPanels.setModel(dtm);
        jtrPanels.setSize(100, this.getHeight());
        jtrPanels.repaint();
        updateMenus();

        jcxPatchPortugal.setSelected(mTournament.getParams().isPortugal());
        this.revalidate();
        this.repaint();
    }

    /**
     * Update only tree
     */
    public void updateTree() {
        jtrPanels.setSelectionPath(new TreePath(((MainTreeModel) jtrPanels.getModel()).getParams()));
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
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jcxmiAsServer = new javax.swing.JCheckBoxMenuItem();
        jmnRound = new javax.swing.JMenu();
        jmiGenerateNextRound = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jmiDelRound = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jmiChangePairing = new javax.swing.JMenuItem();
        jmiEditCoef = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jckmiRoundOnly = new javax.swing.JCheckBoxMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jmiAddFreeMatch = new javax.swing.JMenuItem();
        jmiDelFreeMatch = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jmiFullScreenMatchs = new javax.swing.JMenuItem();
        jmiFullScreenMatchsClash = new javax.swing.JMenuItem();
        jmiFullScreenRankGeneral = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexIndiv = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexIndiv1 = new javax.swing.JMenuItem();
        jmiFullScreenRankTeam = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexTeam = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexTeam1 = new javax.swing.JMenuItem();
        jmiFullScreenRankClan = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexClan = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexClan1 = new javax.swing.JMenuItem();
        jmiFullScreenRankGroups = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexGroups = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexGroups1 = new javax.swing.JMenuItem();
        jmiFullScreenRankCategory = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexCategory = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexCategory1 = new javax.swing.JMenuItem();
        jmiFullScreenPool = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexPool = new javax.swing.JMenuItem();
        jmiFullScreenRankAnnexPool1 = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
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
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
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

        jmiNafLoad.setText(bundle.getString("UpdateCoachNAFDatas")); // NOI18N
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
        jmnParameters.add(jSeparator15);

        jcxmiAsServer.setText(bundle.getString("AsServer")); // NOI18N
        jcxmiAsServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxmiAsServerActionPerformed(evt);
            }
        });
        jmnParameters.add(jcxmiAsServer);

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
        jmiDelRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/trashcan_empty.png"))); // NOI18N
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

        jmiEditCoef.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        jmiEditCoef.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/reload.png"))); // NOI18N
        jmiEditCoef.setText(bundle.getString("ChangeCoef")); // NOI18N
        jmiEditCoef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditCoefActionPerformed(evt);
            }
        });
        jmnRound.add(jmiEditCoef);
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

        jmiFullScreenMatchs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenMatchs.setText(bundle.getString("FullScreenMatchIndiv")); // NOI18N
        jmiFullScreenMatchs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenMatchsActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenMatchs);

        jmiFullScreenMatchsClash.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmiFullScreenMatchsClash.setText(bundle.getString("FullScreenMatchIndivClash")); // NOI18N
        jmiFullScreenMatchsClash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenMatchsClashActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenMatchsClash);

        jmiFullScreenRankGeneral.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankGeneral.setText(bundle.getString("FullScreenIndivRank")); // NOI18N
        jmiFullScreenRankGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankGeneralActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankGeneral);

        jmiFullScreenRankAnnexIndiv.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexIndiv.setText(bundle.getString("FullScreenIndivAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexIndivActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexIndiv);

        jmiFullScreenRankAnnexIndiv1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexIndiv1.setText(bundle.getString("FullScreenIndivAnnexShort")); // NOI18N
        jmiFullScreenRankAnnexIndiv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexIndiv1ActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexIndiv1);

        jmiFullScreenRankTeam.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankTeam.setText(bundle.getString("FullScreenTeamRank")); // NOI18N
        jmiFullScreenRankTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankTeamActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankTeam);

        jmiFullScreenRankAnnexTeam.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexTeam.setText(bundle.getString("FullScreenTeamAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexTeamActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexTeam);

        jmiFullScreenRankAnnexTeam1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexTeam1.setText(bundle.getString("FullScreenTeamAnnexRankShort")); // NOI18N
        jmiFullScreenRankAnnexTeam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexTeam1ActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexTeam1);

        jmiFullScreenRankClan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankClan.setText(bundle.getString("FullScreenClanRank")); // NOI18N
        jmiFullScreenRankClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankClanActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankClan);

        jmiFullScreenRankAnnexClan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexClan.setText(bundle.getString("FullScreenClanAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexClanActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexClan);

        jmiFullScreenRankAnnexClan1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexClan1.setText(bundle.getString("FullScreenClanAnnexRankShort")); // NOI18N
        jmiFullScreenRankAnnexClan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexClan1ActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexClan1);

        jmiFullScreenRankGroups.setText(bundle.getString("FullScreenGroupRank")); // NOI18N
        jmiFullScreenRankGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankGroupsActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankGroups);

        jmiFullScreenRankAnnexGroups.setText(bundle.getString("FullScreenGroupAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexGroupsActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexGroups);

        jmiFullScreenRankAnnexGroups1.setText(bundle.getString("FullScreenGroupAnnexRankShort")); // NOI18N
        jmiFullScreenRankAnnexGroups1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexGroups1ActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexGroups1);

        jmiFullScreenRankCategory.setText(bundle.getString("FullScreenCategoryRank")); // NOI18N
        jmiFullScreenRankCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankCategoryActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankCategory);

        jmiFullScreenRankAnnexCategory.setText(bundle.getString("FullScreenCategoryAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexCategoryActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexCategory);

        jmiFullScreenRankAnnexCategory1.setText(bundle.getString("FullScreenCategoryAnnexRankShort")); // NOI18N
        jmiFullScreenRankAnnexCategory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexCategory1ActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexCategory1);

        jmiFullScreenPool.setText(bundle.getString("FullScreenPoolRank")); // NOI18N
        jmiFullScreenPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenPoolActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenPool);

        jmiFullScreenRankAnnexPool.setText(bundle.getString("FullScreenPoolAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexPoolActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexPool);

        jmiFullScreenRankAnnexPool1.setText(bundle.getString("FullScreenPoolAnnexRankShort")); // NOI18N
        jmiFullScreenRankAnnexPool1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexPool1ActionPerformed(evt);
            }
        });
        jmnRound.add(jmiFullScreenRankAnnexPool1);
        jmnRound.add(jSeparator14);

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
                ext = url2.substring(i + 1).toLowerCase(Locale.getDefault());
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
            mFile
                    = jfc.getSelectedFile();
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

        mTournament = Tournament.getTournament();
        if (mTournament.getParams().getGame() == RosterType.C_DREAD_BALL) {
            RosterType.initCollection(RosterType.C_DREAD_BALL);
            jmiExport.setEnabled(false);
            jmiExportFbb.setEnabled(false);
            jcxAllowSpecialSkill.setEnabled(false);
        } else {
            RosterType.initCollection(RosterType.C_BLOOD_BOWL);
            LRB.getLRB();
        }

        mTournament.clearGroups();
        final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);
        final Group group = new Group(bundle.getString("NoneKey"));
        mTournament.addGroup(group);

        for (int i = 0; i < RosterType.getRostersNamesCount(); i++) {
            String mRostersName = RosterType.getRostersName(i);
            group.addRoster(RosterType.getRosterType(mRostersName));
        }
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
                if (mFile.getName().isEmpty()) {
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
        JdgRevisions jdg = new JdgRevisions(this, true);
        jdg.setVisible(true);
}//GEN-LAST:event_jmiRevisionsActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiAideEnLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAideEnLigneActionPerformed
        JdgOnlineHelp jdg = new JdgOnlineHelp(this, false);
        jdg.setVisible(true);
    }//GEN-LAST:event_jmiAideEnLigneActionPerformed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VOULEZ VOUS SAUVGARDER ?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EXIT"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (mFile.getName().isEmpty()) {
                jmiSaveAsActionPerformed(null);
            } else {
                jmiSaveActionPerformed(null);
            }
        }
    }//GEN-LAST:event_formWindowClosed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAboutActionPerformed
        JdgAbout jdg = new JdgAbout(this, true);
        jdg.setVisible(true);
}//GEN-LAST:event_jmiAboutActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VOULEZ VOUS SAUVGARDER ?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EXIT"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (mFile == null) {
                jmiSaveAsActionPerformed(null);
            } else {

                if (mFile.getName().isEmpty()) {
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
        LRB.getLRB().setAllowSpecialSkills(jcxAllowSpecialSkill.getState());
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
            if (mTournament.getParams().isTeamTournament() && (mTournament.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) && mTournament.getTeamsCount() % 2 > 0) {
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

                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    if (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                        /**
                         * Balanced Options
                         */
                        labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RandomAndBalancing"));
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

                updateTree();
                update();

            }

        }
    }//GEN-LAST:event_jmiGenerateFirstRoundActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtrPanelsValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jtrPanelsValueChanged

        TreePath path;
        if (evt != null) {
            path = evt.getNewLeadSelectionPath();
        } else {
            path = jtrPanels.getPathForRow(1);
        }
        if (path != null) {

            final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            if (node != null) {
                final Object object = node.getUserObject();
                if (object instanceof Parameters) {
                    jspSplit.remove(jpnContent);
                    JPNParameters jpn = new JPNParameters();
                    jspSplit.add(jpn, JSplitPane.RIGHT);
                    jpn.update();
                    jspSplit.setDividerLocation(200);
                    jpnContent = jpn;
                    //System.gc();
                    this.revalidate();
                }
                if (object instanceof Round) {
                    for (int i = 0; i < mTournament.getRoundsCount(); i++) {
                        if (mTournament.getRound(i).equals(object)) {
                            jspSplit.remove(jpnContent);
                            JPNRound jpn = new JPNRound(i, (Round) object, mTournament);
                            jspSplit.add(jpn, JSplitPane.RIGHT);
                            jpn.update();
                            jspSplit.setDividerLocation(200);
                            jpn.setRoundOnly(jckmiRoundOnly.isSelected());
                            jpnContent = jpn;
                            //System.gc();
                            this.revalidate();
                            break;
                        }
                    }
                }

                if (object.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CUP"))) {
                    jspSplit.remove(jpnContent);
                    JPNCup jpn = new JPNCup();
                    jspSplit.add(jpn, JSplitPane.RIGHT);
                    jpn.update();
                    jspSplit.setDividerLocation(200);
                    jpnContent = jpn;
                    //System.gc();
                    this.revalidate();
                }

                if (object.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("STATISTICS"))) {
                    jspSplit.remove(jpnContent);
                    JPNStatistics jpn = new JPNStatistics();
                    jspSplit.add(jpn, JSplitPane.RIGHT);
                    jpn.update();
                    jspSplit.setDividerLocation(200);
                    jpnContent = jpn;
                    //System.gc();
                    this.revalidate();
                }

            }

            updateMenus();
            repaint();
        }

    }//GEN-LAST:event_jtrPanelsValueChanged

    private void jmiNafLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNafLoadActionPerformed

        ProgressMonitor progressMonitor = new ProgressMonitor(this,
                java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DownloadFromNAF"),
                java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Downloading"), 0, Tournament.getTournament().getCoachsCount());
        progressMonitor.setProgress(0);
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            Coach c = Tournament.getTournament().getCoach(i);
            progressMonitor.setNote(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Download0") + " " + c.getName());
            c.setNafRank(NAF.getRanking(c.getName(), c));
            progressMonitor.setProgress(i + 1);
        }
        progressMonitor.close();
        update();
    }//GEN-LAST:event_jmiNafLoadActionPerformed

    private void jmiSubstitutePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSubstitutePlayerActionPerformed
        // Select Player to subtitute
        ArrayList<Coach> list = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            list.add(Tournament.getTournament().getCoach(i));
        }
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            if (!Tournament.getTournament().getCoach(i).isActive()) {
                list.remove(i);
            }
        }
        JComboBox<Object> jcb = new JComboBox<>(list.toArray());
        JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WhichCoachIsSubstituted"));
        JPanel jpn = new JPanel(new BorderLayout());
        jpn.add(jlb, BorderLayout.NORTH);
        jpn.add(jcb, BorderLayout.CENTER);
        int ret = JOptionPane.showConfirmDialog(this, jpn, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Substitution"), JOptionPane.OK_CANCEL_OPTION);
        if (ret == JOptionPane.OK_OPTION) {
            Coach c = (Coach) jcb.getSelectedItem();
            ArrayList<String> matchs_descr = new ArrayList<>();

            // Select Match
            for (int i = 0; i < c.getMatchCount(); i++) {
                Match mMatch = c.getMatch(i);
                CoachMatch m = (CoachMatch) mMatch;
                String tmp = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Round01") + " " + Tournament.getTournament().indexOfRound(m.getRound()) + "1";
                tmp = tmp + " / " + m.getCompetitor1().getDecoratedName() + " VS " + m.getCompetitor2().getDecoratedName();
                matchs_descr.add(tmp);
            }
            jpn.remove(jcb);
            jcb = new JComboBox(matchs_descr.toArray());
            jpn.add(jcb, BorderLayout.CENTER);
            jlb.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WhichMatch"));
            JOptionPane.showConfirmDialog(this, jpn, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Substitution"), JOptionPane.OK_OPTION);
            CoachMatch m = (CoachMatch) c.getMatch(jcb.getSelectedIndex());

            // Select subtitute
            ArrayList<Coach> availableCoachs = new ArrayList<>();
            ArrayList<String> availableCoachsName = new ArrayList<>();
            for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                Coach sub = Tournament.getTournament().getCoach(i);
                if (!sub.isActive()) {
                    availableCoachs.add(sub);
                }
            }
            availableCoachs.add(Coach.getNullCoach());
            for (Coach availableCoach : availableCoachs) {
                availableCoachsName.add(availableCoach.getDecoratedName());
            }

            availableCoachsName.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("New..."));
            jpn.remove(jcb);
            jcb = new JComboBox(availableCoachsName.toArray());
            jpn.add(jcb, BorderLayout.CENTER);
            jlb.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChooseASubstitute"));
            JOptionPane.showConfirmDialog(this, jpn, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Substitution"), JOptionPane.OK_OPTION);

            // Create Substitution
            // If None
            if (jcb.getSelectedIndex() == availableCoachs.size() - 1) {
                if (m.getCompetitor1() == c) {
                    m.setSubstitute1(null);
                }
                if (m.getCompetitor2() == c) {
                    m.setSubstitute2(null);
                }
            } else {
                Coach sub;
                // New
                if (jcb.getSelectedIndex() == availableCoachs.size()) {
                    sub = new Coach();
                    sub.setRoster(RosterType.getRosterType(0));
                    JdgCoach jdg = new JdgCoach(this, true, sub);
                    jdg.setVisible(true);
                    Tournament.getTournament().addCoach(sub);
                    sub.setActive(false);
                } else {
                    sub = availableCoachs.get(jcb.getSelectedIndex());
                }

                Substitute s = new Substitute();
                s.setMatch(m);
                s.setSubstitute(sub);
                s.setTitular(c);

                if (m.getCompetitor1() == c) {
                    m.setSubstitute1(s);
                }
                if (m.getCompetitor2() == c) {
                    m.setSubstitute2(s);
                }
            }
        }
    }//GEN-LAST:event_jmiSubstitutePlayerActionPerformed

    private void jmiAddCoachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAddCoachActionPerformed
        final JdgCoach w = new JdgCoach(MainFrame.getMainFrame(), true);
        w.setVisible(true);
        update();
    }//GEN-LAST:event_jmiAddCoachActionPerformed

    private void jmiDelRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDelRoundActionPerformed
        if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ConfirmEraseCurrentRound"), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("EraseRound"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            if (jpnContent instanceof JPNRound) {

                JPNRound jpnr = (JPNRound) jpnContent;
                // Remove mRound
                Round round = jpnr.getRound();

                // Remove matchs from coach reference list
                ArrayList<CoachMatch> cms = round.getCoachMatchs();
                for (CoachMatch m : cms) {
                    m.getCompetitor1().removeMatch(m);
                    m.getCompetitor2().removeMatch(m);
                }

                // remove matchs from competitors
                for (int i = 0; i < round.getMatchsCount(); i++) {
                    final Match m = round.getMatch(i);
                    m.getCompetitor1().removeMatch(m);
                    m.getCompetitor2().removeMatch(m);
                }

                mTournament.removeRound(round);

                update();
                updateTree();
            }
        }
    }//GEN-LAST:event_jmiDelRoundActionPerformed

    private void editRoundCoef(Round r) {
        JPanel jpn = new JPanel();
        jpn.setLayout(new GridLayout(2, 2));
        JLabel jlb1 = new JLabel("Coefficient pour la première table :");
        jlb1.setHorizontalAlignment(JLabel.TRAILING);
        JLabel jlb2 = new JLabel("Coefficient pour la dernière table :");
        jlb2.setHorizontalAlignment(JLabel.TRAILING);

        JFormattedTextField jftf1 = new JFormattedTextField(new DecimalFormat("####.##"));
        jftf1.setValue(new Double(r.getMaxBonus()));

        JFormattedTextField jftf2 = new JFormattedTextField(new DecimalFormat("####.##"));
        jftf2.setValue(new Double(r.getMinBonus()));

        jpn.add(jlb1);
        jpn.add(jftf1);
        jpn.add(jlb2);
        jpn.add(jftf2);

        int res = JOptionPane.showConfirmDialog(this, jpn, "Round coefficient", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            Double val1 = (Double) jftf1.getValue();
            r.setMaxBonus(val1);
            Double val2 = (Double) jftf2.getValue();
            r.setMinBonus(val2);
            update();
        }
    }

    private void jmiGenerateNextRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGenerateNextRoundActionPerformed

        final ArrayList<String> labels = new ArrayList<>();
        final ArrayList<Integer> Options = new ArrayList<>();

        if (jpnContent instanceof JPNRound) {

            JPNRound jpnr = (JPNRound) jpnContent;
            Round round = jpnr.getRound();
            int round_number = mTournament.indexOfRound(round);
            

            /**
             * Swiss possible ?
             */
            if ((!round.isCup()) && (!mTournament.isRoundRobin())) {
                labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RONDE SUISSE"));
                Options.add(Generation.GEN_SWISS);
            }

            /**
             * QSwiss possible ?
             */
            if ((!round.isCup()) && (!mTournament.isRoundRobin())) {
                labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RONDE SUISSE ACCELERÉE"));
                Options.add(Generation.GEN_QSWISS);
            }

            /**
             * GenRandom possible ?
             */
            if ((!round.isCup()) && (!mTournament.isRoundRobin())) {
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
            if ((!round.isCup()) && (!mTournament.isRoundRobin())) {
                labels.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FREE_ROUND"));
                Options.add(Generation.GEN_FREE);
            }

            final JPanel jpn = new JPanel(new BorderLayout());
            final JComboBox jcb = new JComboBox(labels.toArray());
            jpn.add(jcb, BorderLayout.CENTER);
            final JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHOISISSEZ LA MÉTHODE DE GÉNÉRATION: "));
            jpn.add(jlb, BorderLayout.NORTH);

            final JCheckBox jcxClash = new JCheckBox("Animation");
            jpn.add(jcxClash, BorderLayout.SOUTH);

            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jpn, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GÉNÉRATION"), JOptionPane.QUESTION_MESSAGE);

            final int index = jcb.getSelectedIndex();

            Generation.nextRound(round, Options.get(index), round_number);
            
            
            if (mTournament.getParams().isTableBonusPerRound()) {
                editRoundCoef(mTournament.getRound(mTournament.getRoundsCount()-1));
            }
            
            if (jpnContent instanceof JPNRound) {
                ((JPNRound) jpnContent).update();
                update();
            }
            updateTree();

            if (jcxClash.isSelected()) {
                try {
                    JFullScreen fs = new JFullScreenMatchs(Tournament.getTournament().getRound(round_number + 1), true);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jmiGenerateNextRoundActionPerformed

    private void jmiChangePairingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiChangePairingActionPerformed

        if (jpnContent instanceof JPNRound) {

            JPNRound jpnr = (JPNRound) jpnContent;
            Round round = jpnr.getRound();
            final JdgChangePairing jdg = new JdgChangePairing(MainFrame.getMainFrame(), true, round);
            jdg.setVisible(true);
            jpnr.update();
        }
        update();
    }//GEN-LAST:event_jmiChangePairingActionPerformed

    private void jckmiRoundOnlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckmiRoundOnlyActionPerformed

        if (jpnContent instanceof JPNRound) {

            JPNRound jpnr = (JPNRound) jpnContent;
            jpnr.setRoundOnly(jckmiRoundOnly.isSelected());
            jpnr.update();
        }
    }//GEN-LAST:event_jckmiRoundOnlyActionPerformed

    private void jmiAddFreeMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAddFreeMatchActionPerformed

        if (jpnContent instanceof JPNRound) {

            JPNRound jpnr = (JPNRound) jpnContent;
            Round round = jpnr.getRound();
            if (Tournament.getTournament().getParams().isTeamTournament()) {

                final ArrayList<Team> teams1 = new ArrayList<>();
                final ArrayList<Team> teams2 = new ArrayList<>();

                final JComboBox<String> jcb1 = new JComboBox<>();
                final JComboBox<String> jcb2 = new JComboBox<>();

                for (int i = 0; i < mTournament.getTeamsCount(); i++) {
                    final Team c = mTournament.getTeam(i);
                    teams1.add(c);
                    teams2.add(c);
                    jcb1.addItem(c.getName());
                    jcb2.addItem(c.getName());
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
                            m.setCompetitor1(teams1.get(jcb1.getSelectedIndex()));
                            m.setCompetitor2(teams2.get(jcb2.getSelectedIndex()));
                            JdgPairing jdg = new JdgPairing(MainFrame.getMainFrame(), true, teams1.get(jcb1.getSelectedIndex()), teams2.get(jcb2.getSelectedIndex()), round, m);
                            jdg.setVisible(true);
                            round.addMatch(m);

                            for (int cpt = 0; cpt < m.getMatchCount(); cpt++) {
                                CoachMatch c = m.getMatch(cpt);
                                c.getCompetitor1().addMatch(c);
                                c.getCompetitor2().addMatch(c);
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

                for (int i = 0; i < mTournament.getActiveCoaches().size(); i++) {
                    final Coach c = mTournament.getActiveCoaches().get(i);
                    Coachs1.add(c);
                    Coachs2.add(c);
                    jcb1.addItem(c.getName());
                    jcb2.addItem(c.getName());
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
                            m.setCompetitor1(Coachs1.get(jcb1.getSelectedIndex()));
                            m.setCompetitor2(Coachs2.get(jcb2.getSelectedIndex()));

                            round.addMatch(m);
                            m.getCompetitor1().addMatch(m);
                            m.getCompetitor2().addMatch(m);
                            ValidMatch = true;
                        } else {
                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH IMPOSSIBLE"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ERREUR"), JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        ValidMatch = true;
                    }
                }
            }

            jpnr.update();
        }
    }//GEN-LAST:event_jmiAddFreeMatchActionPerformed

    private void jmiDelFreeMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDelFreeMatchActionPerformed

        if (jpnContent instanceof JPNRound) {
            JPNRound jpnr = (JPNRound) jpnContent;
            int row = jpnr.getMatchTableSelectedRow();
            if (row >= 0) {
                Round round = jpnr.getRound();
                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    CoachMatch c = round.getCoachMatchs().get(row);
                    // Fint TeamMatch corresponding to CoachMatch
                    int j = 0;
                    while (j < round.getMatchsCount()) {
                        TeamMatch t = ((TeamMatch) round.getMatch(j));
                        int i = 0;
                        while (i < t.getMatchCount()) {
                            if (t.containsMatch(c)) {
                                for (int cpt = 0; cpt < t.getMatchCount(); cpt++) {
                                    CoachMatch cm = t.getMatch(cpt);
                                    cm.getCompetitor1().removeMatch(cm);
                                    cm.getCompetitor2().removeMatch(cm);
                                }
                                t.getCompetitor1().removeMatch(t);
                                t.getCompetitor2().removeMatch(t);
                                round.removeMatch(t);
                                j = round.getMatchsCount();
                                i = t.getMatchCount();
                            }
                            i++;
                        }
                        j++;
                    }
                } else {
                    CoachMatch c = (CoachMatch) round.getMatch(row);
                    c.getCompetitor1().removeMatch(c);
                    c.getCompetitor2().removeMatch(c);
                    round.removeMatch(c);
                }
                jpnr.update();
            }
        }
    }//GEN-LAST:event_jmiDelFreeMatchActionPerformed

    private void jcxPatchPortugalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxPatchPortugalActionPerformed
        mTournament.getParams().setPortugal(jcxPatchPortugal.isSelected());
    }//GEN-LAST:event_jcxPatchPortugalActionPerformed

    private void jmiConceedMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConceedMatchActionPerformed
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = (JPNRound) jpnContent;
                int nbMatch = jpnr.getMatchTableSelectedRow();
                CoachMatch m = jpnr.getRound().getCoachMatchs().get(nbMatch);
                if (m.isConcedeedBy1() || m.isConcedeedBy2() || m.isRefusedBy1() || m.isRefusedBy2()) {
                    JOptionPane.showMessageDialog(null, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Error"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MatchAlreadyConceededOrRefused"), JOptionPane.ERROR_MESSAGE);
                } else {
                    Object[] options = new Object[3];
                    options[0] = m.getCompetitor1();
                    options[1] = m.getCompetitor2();
                    options[2] = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Cancel");

                    Object option = JOptionPane.showInputDialog(null, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ConceedAMatch"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WhoConceedTheMatch"), JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

                    if (option.equals(m.getCompetitor1())) {
                        m.setConcedeedBy1(true);
                        m.setConcedeedBy2(false);
                    }
                    if (option.equals(m.getCompetitor2())) {
                        m.setConcedeedBy2(true);
                        m.setConcedeedBy1(false);
                    }
                    update();
                    jpnr.update();
                }
            }
        } catch (HeadlessException e) {
            LOG.log(Level.INFO, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_jmiConceedMatchActionPerformed

    private void jmiCancelConceedMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCancelConceedMatchActionPerformed
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);

                int nbMatch = jpnr.getMatchTableSelectedRow();
                CoachMatch m = jpnr.getRound().getCoachMatchs().get(nbMatch);
                m.setConcedeedBy1(false);
                m.setConcedeedBy2(false);
            }
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_jmiCancelConceedMatchActionPerformed

    private void jmiRefuseMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRefuseMatchActionPerformed
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                int nbMatch = jpnr.getMatchTableSelectedRow();
                CoachMatch m = jpnr.getRound().getCoachMatchs().get(nbMatch);
                if (m.isConcedeedBy1() || m.isConcedeedBy2() || m.isRefusedBy1() || m.isRefusedBy2()) {
                    JOptionPane.showMessageDialog(null, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Error"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MatchAlreadyConceededOrRefused"), JOptionPane.ERROR_MESSAGE);
                } else {
                    Object[] options = new Object[3];
                    options[0] = m.getCompetitor1();
                    options[1] = m.getCompetitor2();
                    options[2] = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Cancel");

                    Object option = JOptionPane.showInputDialog(null, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RefuseAMatch"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WhoRefuseMatch"), JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

                    if (option.equals(m.getCompetitor1())) {
                        m.setRefusedBy1(true);
                        m.setRefusedBy2(false);
                    }
                    if (option.equals(m.getCompetitor2())) {
                        m.setRefusedBy2(true);
                        m.setRefusedBy1(false);
                    }
                    update();
                    jpnr.update();
                }
            }
        } catch (HeadlessException e) {
            LOG.log(Level.INFO, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_jmiRefuseMatchActionPerformed

    private void jmiCancelMatchRefuseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCancelMatchRefuseActionPerformed
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                int nbMatch = jpnr.getMatchTableSelectedRow();
                CoachMatch m = jpnr.getRound().getCoachMatchs().get(nbMatch);
                m.setRefusedBy1(false);
                m.setRefusedBy2(false);
            }
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_jmiCancelMatchRefuseActionPerformed

    private void jcxUseColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxUseColorActionPerformed
        Tournament.getTournament().getParams().setUseColor(jcxUseColor.isSelected());
        if (jpnContent instanceof JPNRound) {
            ((JPNRound) jpnContent).update();
        }
    }//GEN-LAST:event_jcxUseColorActionPerformed

    private void jcxUseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxUseImageActionPerformed
        Tournament.getTournament().getParams().setUseImage(jcxUseImage.isSelected());
        if (jpnContent instanceof JPNRound) {
            ((JPNRound) jpnContent).update();
        }
        if (jpnContent instanceof JPNParameters) {
            ((JPNParameters) jpnContent).update();
        }
    }//GEN-LAST:event_jcxUseImageActionPerformed

    private void jmiFullScreenMatchsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenMatchsActionPerformed
        JFullScreenMatchs fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenMatchs(jpnr.getRound());
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jmiFullScreenMatchsActionPerformed

    private void jmiFullScreenRankGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankGeneralActionPerformed
        JFullScreenIndivRank fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivRank(Tournament.getTournament().indexOfRound(jpnr.getRound()));
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jmiFullScreenRankGeneralActionPerformed

    private void jmiFullScreenRankTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankTeamActionPerformed
        JFullScreenTeamRank fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenTeamRank(Tournament.getTournament().indexOfRound(jpnr.getRound()));
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jmiFullScreenRankTeamActionPerformed

    private void jmiFullScreenRankClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankClanActionPerformed
        JFullScreenClanRank fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenClanRank(Tournament.getTournament().indexOfRound(jpnr.getRound()));
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankClanActionPerformed

    private void jmiFullScreenRankAnnexIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexIndivActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), true);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexIndivActionPerformed

    private void jmiFullScreenRankAnnexIndiv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexIndiv1ActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), false);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexIndiv1ActionPerformed

    private void jmiFullScreenRankAnnexTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexTeamActionPerformed
        JFullScreenClanTeamAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), true, true);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexTeamActionPerformed

    private void jmiFullScreenRankAnnexTeam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexTeam1ActionPerformed
        JFullScreenClanTeamAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), false, true);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexTeam1ActionPerformed

    private void jmiFullScreenRankAnnexClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexClanActionPerformed
        JFullScreenClanTeamAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), true, false);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexClanActionPerformed

    private void jmiFullScreenRankAnnexClan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexClan1ActionPerformed
        JFullScreenClanTeamAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), false, false);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexClan1ActionPerformed

    TMultiServer server = null;

    private void jcxmiAsServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxmiAsServerActionPerformed
        if (jcxmiAsServer.isSelected()) {
            if (server == null) {
                server = new TMultiServer();
                server.start();
            } else {
                synchronized (this) {
                    server.stopServer();
                    try {
                        server.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                server.start();
            }
        } else {
            synchronized (this) {
                server.stopServer();
                try {
                    server.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            server = null;

        }
    }//GEN-LAST:event_jcxmiAsServerActionPerformed

    private void jmiFullScreenMatchsClashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenMatchsClashActionPerformed
        JFullScreenMatchs fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenMatchs(jpnr.getRound(), true);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenMatchsClashActionPerformed

    private void jmiFullScreenRankGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankGroupsActionPerformed
        JFullScreenIndivRank fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivRank(Tournament.getTournament().indexOfRound(jpnr.getRound()), JFullScreenIndivRank.C_GROUP);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankGroupsActionPerformed

    private void jmiFullScreenRankAnnexGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexGroupsActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), true, C_GROUP);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexGroupsActionPerformed

    private void jmiFullScreenRankAnnexGroups1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexGroups1ActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), false, C_GROUP);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexGroups1ActionPerformed

    private void jmiFullScreenRankCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankCategoryActionPerformed
        JFullScreenIndivRank fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivRank(Tournament.getTournament().indexOfRound(jpnr.getRound()), JFullScreenIndivRank.C_CATEGORY);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankCategoryActionPerformed

    private void jmiFullScreenRankAnnexCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexCategoryActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), true, JFullScreenIndivRank.C_CATEGORY);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexCategoryActionPerformed

    private void jmiFullScreenRankAnnexCategory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexCategory1ActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), false, JFullScreenIndivRank.C_CATEGORY);
                fs.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexCategory1ActionPerformed

    private void jmiFullScreenPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenPoolActionPerformed
        if (Tournament.getTournament().getPoolCount() > 0) {
            if (Tournament.getTournament().getParams().isTeamTournament()) {
                JFullScreenTeamRank fs;
                try {
                    if (jpnContent instanceof JPNRound) {
                        JPNRound jpnr = ((JPNRound) jpnContent);
                        fs = new JFullScreenTeamRank(Tournament.getTournament().indexOfRound(jpnr.getRound()),
                                true);
                        fs.setVisible(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JFullScreenIndivRank fs;
                try {
                    if (jpnContent instanceof JPNRound) {
                        JPNRound jpnr = ((JPNRound) jpnContent);
                        fs = new JFullScreenIndivRank(Tournament.getTournament().indexOfRound(jpnr.getRound()),
                                C_POOL);
                        fs.setVisible(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jmiFullScreenPoolActionPerformed

    private void jmiFullScreenRankAnnexPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexPoolActionPerformed
        if (Tournament.getTournament().getPoolCount() > 0) {
            if (Tournament.getTournament().getParams().isTeamTournament()) {
                JFullScreenClanTeamAnnex fs;
                try {
                    if (jpnContent instanceof JPNRound) {
                        JPNRound jpnr = ((JPNRound) jpnContent);
                        fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), true,
                                true, true);
                        fs.setVisible(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JFullScreenIndivAnnex fs;
                try {
                    if (jpnContent instanceof JPNRound) {
                        JPNRound jpnr = ((JPNRound) jpnContent);
                        fs = new JFullScreenIndivAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), true,
                                C_POOL);
                        fs.setVisible(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexPoolActionPerformed

    private void jmiFullScreenRankAnnexPool1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexPool1ActionPerformed
        if (Tournament.getTournament().getPoolCount() > 0) {
            if (Tournament.getTournament().getParams().isTeamTournament()) {
                JFullScreenClanTeamAnnex fs;
                try {
                    if (jpnContent instanceof JPNRound) {
                        JPNRound jpnr = ((JPNRound) jpnContent);
                        fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), false,
                                true, true);
                        fs.setVisible(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JFullScreenIndivAnnex fs;
                try {
                    if (jpnContent instanceof JPNRound) {
                        JPNRound jpnr = ((JPNRound) jpnContent);
                        fs = new JFullScreenIndivAnnex(Tournament.getTournament().indexOfRound(jpnr.getRound()), false,
                                C_POOL);
                        fs.setVisible(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexPool1ActionPerformed

    private void jmiEditCoefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditCoefActionPerformed

        if (jpnContent instanceof JPNRound) {
            JPNRound jpnr = ((JPNRound) jpnContent);

            editRoundCoef(jpnr.getRound());
        }

    }//GEN-LAST:event_jmiEditCoefActionPerformed

    /**
     * @param args the command line arguments
     */
    //@com.yworks.util.annotation.Obfuscation ( exclude = false)
    public static void main(final String args[]) {

        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {

                    final ArrayList<String> StartOptions = new ArrayList<>();
                    StartOptions.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NewGame"));
                    StartOptions.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Open"));
                    StartOptions.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UseRosterEditor"));
                    StartOptions.add("Client Viewer");
                    final int res = JOptionPane.showOptionDialog(null, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NewGameOrOpen"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, StartOptions.toArray(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Open"));

                    if ((res == 0) || (res == 1)) {
                        MainFrame window = MainFrame.getMainFrame(res);
                        window.setVisible(true);
                    }

                    if (res == 2) {
                        teamma.views.JdgRoster jdg = new JdgRoster(null, true);
                        jdg.setVisible(true);
                        //System.exit(0);
                    }

                    if (res == 3) {

                        String address = (String) JOptionPane.showInputDialog(null, "Enter remote server IP address", "127.0.0.1");

                        try {
                            Socket socket = null;
                            try {
                                socket = new Socket(address, 2017);
                            } catch (ConnectException e) {
                                JOptionPane.showMessageDialog(null, "Connection to " + address + " impossible, exiting");
                                System.exit(1);
                            }

                            ArrayList<String> labels = new ArrayList<>();

                            // Index 0: Individual Ranking
                            labels.add("Individual ranking");
                            // Index 1: Individual Annex ranks
                            labels.add("Individual Annex rankings");
                            // Index 2: Team Ranking
                            labels.add("Team ranking");
                            // Index 3: Team Annex
                            labels.add("Team Annex Ranking");
                            // Index 4: Clan Ranking
                            labels.add("Clan ranking");
                            // Index 5: Clan Annex
                            labels.add("Clan Annex Ranking");
                            // Index 6:Matchs
                            labels.add("Matchs");
                            // Index 7: Clash Match
                            labels.add("Matchs Clash");
                            // Index 8: Categories
                            labels.add("Categories Ranking");
                            // Index 9: Categories
                            labels.add("Categories Annex Ranking");
                            // Index 10: Group
                            labels.add("Group Ranking");
                            // Index 11: Categories
                            labels.add("Group Annex Ranking");
                            // Index 12: Indiv Pool
                            labels.add("Individual Pool Ranking");
                            // Index 13: Indiv Pool Annex
                            labels.add("Individual Pool Annex Ranking");
                            // Index 14: Team Pool
                            labels.add("Team Pool Ranking");
                            // Index 15: Categories
                            labels.add("Team Pool Annex Ranking");

                            final JPanel jpn = new JPanel(new BorderLayout());
                            final JComboBox jcb = new JComboBox(labels.toArray());
                            jpn.add(jcb, BorderLayout.CENTER);
                            final JLabel jlb = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHOISISSEZ LA MÉTHODE DE GÉNÉRATION: "));
                            jpn.add(jlb, BorderLayout.NORTH);

                            JOptionPane.showMessageDialog(null, jpn, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GÉNÉRATION"), JOptionPane.QUESTION_MESSAGE);

                            final int index = jcb.getSelectedIndex();

                            switch (index) {
                                case 0:
                                    JFullScreenIndivRank indiv = new JFullScreenIndivRank(socket);
                                    indiv.setVisible(true);
                                    break;
                                case 1:
                                    JFullScreenIndivAnnex indivAnnex = new JFullScreenIndivAnnex(socket);
                                    indivAnnex.setVisible(true);
                                    break;
                                case 2:
                                    JFullScreenTeamRank team = new JFullScreenTeamRank(socket);
                                    team.setVisible(true);
                                    break;
                                case 3:
                                    JFullScreenClanTeamAnnex teamAnnex = new JFullScreenClanTeamAnnex(socket, true);
                                    teamAnnex.setVisible(true);
                                    break;
                                case 4:
                                    JFullScreenClanRank clan = new JFullScreenClanRank(socket);
                                    clan.setVisible(true);
                                    break;
                                case 5:
                                    JFullScreenClanTeamAnnex clanAnnex = new JFullScreenClanTeamAnnex(socket, false);
                                    clanAnnex.setVisible(true);
                                    break;
                                case 6:
                                    JFullScreenMatchs matchs = new JFullScreenMatchs(socket);
                                    matchs.setVisible(true);
                                    break;
                                case 7:
                                    JFullScreenMatchs matchsC = new JFullScreenMatchs(socket, true);
                                    matchsC.setVisible(true);
                                    break;
                                case 8:
                                    JFullScreenIndivRank category = new JFullScreenIndivRank(socket, JFullScreenIndivRank.C_CATEGORY);
                                    category.setVisible(true);
                                    break;
                                case 9:
                                    JFullScreenIndivAnnex categoryAnnex = new JFullScreenIndivAnnex(socket, JFullScreenIndivRank.C_CATEGORY);
                                    categoryAnnex.setVisible(true);
                                    break;
                                case 10:
                                    JFullScreenIndivRank group = new JFullScreenIndivRank(socket, JFullScreenIndivRank.C_GROUP);
                                    group.setVisible(true);
                                    break;
                                case 11:
                                    JFullScreenIndivAnnex groupAnnex = new JFullScreenIndivAnnex(socket, JFullScreenIndivRank.C_GROUP);
                                    groupAnnex.setVisible(true);
                                    break;
                                case 12:
                                    JFullScreenIndivRank indivPool = new JFullScreenIndivRank(socket, JFullScreenIndivRank.C_POOL);
                                    indivPool.setVisible(true);
                                    break;
                                case 13:
                                    JFullScreenIndivAnnex indivPoolAnnex = new JFullScreenIndivAnnex(socket, JFullScreenIndivRank.C_POOL);
                                    indivPoolAnnex.setVisible(true);
                                    break;
                                case 14:
                                    JFullScreenTeamRank teamPool = new JFullScreenTeamRank(socket, true);
                                    teamPool.setVisible(true);
                                    break;
                                case 15:
                                    JFullScreenClanTeamAnnex teamPoolAnnex = new JFullScreenClanTeamAnnex(socket, false, true);
                                    teamPoolAnnex.setVisible(true);
                                    break;

                            }
                        } catch (IOException e) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                }
            }
            );
        } catch (InterruptedException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static MainFrame mSingleton;

    /**
     *
     * @return
     */
    public static MainFrame getMainFrame() {
        if (mSingleton == null) {
            mSingleton = new MainFrame(0);
        }

        return mSingleton;
    }

    /**
     *
     * @param res
     * @return
     */
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
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
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
    private javax.swing.JCheckBoxMenuItem jcxmiAsServer;
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
    private javax.swing.JMenuItem jmiEditCoef;
    private javax.swing.JMenuItem jmiEditTeam;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiExport;
    private javax.swing.JMenuItem jmiExportFbb;
    private javax.swing.JMenuItem jmiExportFbb1;
    private javax.swing.JMenuItem jmiFullScreenMatchs;
    private javax.swing.JMenuItem jmiFullScreenMatchsClash;
    private javax.swing.JMenuItem jmiFullScreenPool;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexCategory;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexCategory1;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexClan;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexClan1;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexGroups;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexGroups1;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexIndiv;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexIndiv1;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexPool;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexPool1;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexTeam;
    private javax.swing.JMenuItem jmiFullScreenRankAnnexTeam1;
    private javax.swing.JMenuItem jmiFullScreenRankCategory;
    private javax.swing.JMenuItem jmiFullScreenRankClan;
    private javax.swing.JMenuItem jmiFullScreenRankGeneral;
    private javax.swing.JMenuItem jmiFullScreenRankGroups;
    private javax.swing.JMenuItem jmiFullScreenRankTeam;
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

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
    private static final Logger LOG = Logger.getLogger(MainFrame.class.getName());
}
