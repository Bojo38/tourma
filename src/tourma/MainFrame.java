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

import com.hexidec.ekit.EkitCore;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
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
import org.apache.commons.net.ftp.FTPClient;
import org.jfree.ui.tabbedui.VerticalLayout;
import teamma.data.LRB;
import teamma.views.JdgRoster;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.ETeamPairing;
import static tourma.data.ETeamPairing.TEAM_PAIRING;
import tourma.data.Group;
import tourma.data.ITournament;
import tourma.data.Match;
import tourma.data.Parameters;
import tourma.rmi.RMITournament;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.Substitute;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.rmi.RMIThread;
import tourma.utility.ExtensionFileFilter;
import tourma.utility.StringConstants;
import tourma.utils.Generation;
import tourma.utils.NAF;
import tourma.utils.display.TMultiServer;
import tourma.utils.web.WebServer;
import tourma.views.JPNCup;
import tourma.views.JPNStatistics;
import tourma.views.report.JdgPrintLabel;
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
import tourma.views.report.JdgReport;
import tourma.views.round.JPNRound;
import tourma.views.system.JdgAbout;
import tourma.views.system.JdgOnlineHelp;
import tourma.views.system.JdgRevisions;
import tourma.utils.NafTask;

/**
 *
 * @author Frederic Berger
 */
//@com.yworks.util.annotation.Obfuscation ( exclude = true, applyToMembers = true )
public final class MainFrame extends javax.swing.JFrame implements PropertyChangeListener {

    private Tournament mTournament;
    private File mFile = null;

    private final static String CS_TourMaXMLFile = "TourMaXMLFile";
    private String currentPath;

    public String getCurrentPath() {
        return currentPath;
    }

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

        currentPath = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        if (res == 0) {
            jmiNouveauActionPerformed(null);
        } else {
            if (res == 1) {
                final JFileChooser jfc = new JFileChooser();
                jfc.setCurrentDirectory(new File(currentPath));
                final FileFilter filter1 = new ExtensionFileFilter(
                        Translate.translate(CS_TourMaXMLFile),
                        new String[]{StringConstants.CS_XML, StringConstants.CS_MINXML});
                jfc.setFileFilter(filter1);
                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    if (mTournament instanceof Tournament) {
                        ((Tournament) mTournament).loadXML(jfc.getSelectedFile());
                    }
                }
                File f = jfc.getSelectedFile();
                if (f != null) {
                    currentPath = f.getAbsolutePath();
                }
            }
        }

        update();
    }

    public void updateMenus() {
        boolean isClient = Tournament.getTournament().isClient();
        if (jpnContent instanceof JPNRound) {

            Round r = ((JPNRound) jpnContent).getRound();
            if (mTournament.getRoundIndex(r) == mTournament.getRoundsCount() - 1) {

                jmiIndivReport.setEnabled(true);
                jmiClanReport.setEnabled(true);
                jmiTeamReport.setEnabled(true);

                jmiDelRound.setEnabled(!isClient);

                boolean generateNextRoundEnabled = (r.allMatchesEntered()) && (!isClient);
                if (r.isCup()) {
                    if (r.getCupMaxTour() - 1 == r.getCupTour()) {
                        generateNextRoundEnabled = false;
                    }
                }

                jmiGenerateNextRound.setEnabled(generateNextRoundEnabled);

                jmiChangePairing.setEnabled(!isClient);
                jmiAddFreeMatch.setEnabled(!isClient);
                jmiDelFreeMatch.setEnabled(!isClient);
                jmiFullScreenMatchs.setEnabled(!isClient);
                jmiFullScreenMatchsClash.setEnabled(!isClient);
                jmiFullScreenRankGeneral.setEnabled(!isClient);
                jmiFullScreenRankAnnexIndiv.setEnabled(!isClient);
                jmiFullScreenRankAnnexIndiv1.setEnabled(!isClient);

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

                jmiPrintLabels.setEnabled(true);

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
                jmiPrintLabels.setEnabled(false);
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
            jckmiHideNonNaf.setEnabled(true);
            jmiFullScreenRankTeam.setEnabled(mTournament.getParams().isTeamTournament());
            jmiFullScreenRankClan.setEnabled(mTournament.getClansCount() > 1);
            jmiFullScreenRankAnnexClan.setEnabled(mTournament.getClansCount() > 1);
            jmiFullScreenRankAnnexClan1.setEnabled(mTournament.getClansCount() > 1);
            jmiFullScreenRankAnnexTeam.setEnabled(mTournament.getParams().isTeamTournament());
            jmiFullScreenRankAnnexTeam1.setEnabled(mTournament.getParams().isTeamTournament());
            jmiEditCoef.setEnabled(mTournament.getParams().isTableBonusPerRound());
        } else {
            jmiIndivReport.setEnabled(false);
            jmiClanReport.setEnabled(false);
            jmiTeamReport.setEnabled(false);
            jmiPrintLabels.setEnabled(false);
            jckmiRoundOnly.setEnabled(false);
            jckmiHideNonNaf.setEnabled(false);
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
        jcxmiRemoteEdit.setSelected((Tournament.getTournament().getParams().isWebEdit()) && (!isClient));

        jmiGenerateFirstRound.setEnabled(!isClient);
        jcxmiAsServer.setEnabled(!isClient);
        jcxPatchPortugal.setEnabled(!isClient);
        jcxDisplayRosters.setEnabled(true);
        jcxDisplayRosters.setSelected(Tournament.getTournament().getParams().isDisplayRoster());
        jmiEditColors.setEnabled(!isClient);
        jmiEditWebPort.setEnabled(!isClient);
        jmiEditDescription.setEnabled(!isClient);
        jcxmiRemoteEdit.setEnabled(!isClient);
        jmiNafLoad.setEnabled(!isClient);
    }

    /**
     * Update
     */
    public void update() {

        jmiEditTeam.setEnabled(true);
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
        jcxIgnoreCaps = new javax.swing.JCheckBoxMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jcxPatchPortugal = new javax.swing.JCheckBoxMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jcxUseColor = new javax.swing.JCheckBoxMenuItem();
        jcxUseImage = new javax.swing.JCheckBoxMenuItem();
        jcxDisplayRosters = new javax.swing.JCheckBoxMenuItem();
        jmnParameters = new javax.swing.JMenu();
        jmiGenerateFirstRound = new javax.swing.JMenuItem();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        jmiEditRosterList = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        jmiMassAdd = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jmiSubstitutePlayer = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jcxmiAsServer = new javax.swing.JCheckBoxMenuItem();
        jmiEditColors = new javax.swing.JMenuItem();
        jmiEditWebPort = new javax.swing.JMenuItem();
        jmiEditDescription = new javax.swing.JMenuItem();
        jcxmiRemoteEdit = new javax.swing.JCheckBoxMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        jmiExportWebServerAsZIP = new javax.swing.JMenuItem();
        jmiExportWebServerToSite = new javax.swing.JMenuItem();
        jmnRound = new javax.swing.JMenu();
        jmiGenerateNextRound = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jmiDelRound = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jmiChangePairing = new javax.swing.JMenuItem();
        jmiEditCoef = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jmiPrintLabels = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        jckmiRoundOnly = new javax.swing.JCheckBoxMenuItem();
        jckmiHideNonNaf = new javax.swing.JCheckBoxMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jmiAddFreeMatch = new javax.swing.JMenuItem();
        jmiDelFreeMatch = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
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
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        jmiIndivReport = new javax.swing.JMenuItem();
        jmiClanReport = new javax.swing.JMenuItem();
        jmiTeamReport = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
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

        jmiExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/naf.png"))); // NOI18N
        jmiExport.setText(bundle.getString("ExportNafResultKey")); // NOI18N
        jmiExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExportActionPerformed(evt);
            }
        });
        jmnFile.add(jmiExport);

        jmiExportFbb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Html.png"))); // NOI18N
        jmiExportFbb.setText(bundle.getString("ExportFBBResultXML")); // NOI18N
        jmiExportFbb.setActionCommand(bundle.getString("FBBExport")); // NOI18N
        jmiExportFbb.setEnabled(false);
        jmiExportFbb.setLabel(bundle.getString("FBBExport")); // NOI18N
        jmiExportFbb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExportFbbActionPerformed(evt);
            }
        });
        jmnFile.add(jmiExportFbb);

        jmiExportFbb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Html.png"))); // NOI18N
        jmiExportFbb1.setEnabled(false);
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

        jcxIgnoreCaps.setSelected(true);
        jcxIgnoreCaps.setText(bundle.getString("IgnoreCapitalLetter")); // NOI18N
        jcxIgnoreCaps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxIgnoreCapsActionPerformed(evt);
            }
        });
        jmnTools.add(jcxIgnoreCaps);
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

        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("tourma/languages/language_en"); // NOI18N
        jcxDisplayRosters.setText(bundle1.getString("DisplayRostersInFullScreen")); // NOI18N
        jcxDisplayRosters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxDisplayRostersActionPerformed(evt);
            }
        });
        jmnTools.add(jcxDisplayRosters);

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
        jmnParameters.add(jSeparator21);

        jmiEditRosterList.setText(bundle.getString("EditRosterList")); // NOI18N
        jmiEditRosterList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditRosterListActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiEditRosterList);
        jmnParameters.add(jSeparator20);

        jmiMassAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jmiMassAdd.setText(bundle.getString("MassAdd")); // NOI18N
        jmiMassAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMassAddActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiMassAdd);
        jmnParameters.add(jSeparator6);

        jmiSubstitutePlayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/User2.png"))); // NOI18N
        jmiSubstitutePlayer.setText(bundle.getString("MakeSubstitution")); // NOI18N
        jmiSubstitutePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSubstitutePlayerActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiSubstitutePlayer);
        jmnParameters.add(jSeparator15);

        jcxmiAsServer.setText(bundle.getString("AsServer")); // NOI18N
        jcxmiAsServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxmiAsServerActionPerformed(evt);
            }
        });
        jmnParameters.add(jcxmiAsServer);

        jmiEditColors.setText(bundle.getString("WebColors")); // NOI18N
        jmiEditColors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditColorsActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiEditColors);

        jmiEditWebPort.setText(bundle.getString("EditWebPort")); // NOI18N
        jmiEditWebPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditWebPortActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiEditWebPort);

        jmiEditDescription.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Zoom.png"))); // NOI18N
        jmiEditDescription.setText(bundle.getString("EditDescription")); // NOI18N
        jmiEditDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditDescriptionActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiEditDescription);

        jcxmiRemoteEdit.setText(bundle.getString("RemoteMatchEdit")); // NOI18N
        jcxmiRemoteEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxmiRemoteEditActionPerformed(evt);
            }
        });
        jmnParameters.add(jcxmiRemoteEdit);
        jmnParameters.add(jSeparator19);

        jmiExportWebServerAsZIP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/zip_32.png"))); // NOI18N
        jmiExportWebServerAsZIP.setText(bundle.getString("ExportToZip")); // NOI18N
        jmiExportWebServerAsZIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExportWebServerAsZIPActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiExportWebServerAsZIP);

        jmiExportWebServerToSite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Html.png"))); // NOI18N
        jmiExportWebServerToSite.setText(bundle.getString("ExportWebToSite")); // NOI18N
        jmiExportWebServerToSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExportWebServerToSiteActionPerformed(evt);
            }
        });
        jmnParameters.add(jmiExportWebServerToSite);

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

        jmiPrintLabels.setText(bundle.getString("PrintRoundLabels")); // NOI18N
        jmiPrintLabels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPrintLabelsActionPerformed(evt);
            }
        });
        jmnRound.add(jmiPrintLabels);
        jmnRound.add(jSeparator16);

        jckmiRoundOnly.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jckmiRoundOnly.setText(bundle.getString("RoundOnlyRanking")); // NOI18N
        jckmiRoundOnly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckmiRoundOnlyActionPerformed(evt);
            }
        });
        jmnRound.add(jckmiRoundOnly);

        jckmiHideNonNaf.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jckmiHideNonNaf.setText(bundle.getString("HideNonNaf")); // NOI18N
        jckmiHideNonNaf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckmiHideNonNafActionPerformed(evt);
            }
        });
        jmnRound.add(jckmiHideNonNaf);
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

        jMenu1.setText(bundle.getString("FullScreen")); // NOI18N

        jmiFullScreenMatchs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenMatchs.setText(bundle.getString("FullScreenMatchIndiv")); // NOI18N
        jmiFullScreenMatchs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenMatchsActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenMatchs);

        jmiFullScreenMatchsClash.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmiFullScreenMatchsClash.setText(bundle.getString("FullScreenMatchIndivClash")); // NOI18N
        jmiFullScreenMatchsClash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenMatchsClashActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenMatchsClash);

        jmiFullScreenRankGeneral.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankGeneral.setText(bundle.getString("FullScreenIndivRank")); // NOI18N
        jmiFullScreenRankGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankGeneralActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankGeneral);

        jmiFullScreenRankAnnexIndiv.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexIndiv.setText(bundle.getString("FullScreenIndivAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexIndivActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexIndiv);

        jmiFullScreenRankAnnexIndiv1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexIndiv1.setText(bundle.getString("FullScreenIndivAnnexShort")); // NOI18N
        jmiFullScreenRankAnnexIndiv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexIndiv1ActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexIndiv1);

        jmiFullScreenRankTeam.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankTeam.setText(bundle.getString("FullScreenTeamRank")); // NOI18N
        jmiFullScreenRankTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankTeamActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankTeam);

        jmiFullScreenRankAnnexTeam.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexTeam.setText(bundle.getString("FullScreenTeamAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexTeamActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexTeam);

        jmiFullScreenRankAnnexTeam1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexTeam1.setText(bundle.getString("FullScreenTeamAnnexRankShort")); // NOI18N
        jmiFullScreenRankAnnexTeam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexTeam1ActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexTeam1);

        jmiFullScreenRankClan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankClan.setText(bundle.getString("FullScreenClanRank")); // NOI18N
        jmiFullScreenRankClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankClanActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankClan);

        jmiFullScreenRankAnnexClan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexClan.setText(bundle.getString("FullScreenClanAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexClanActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexClan);

        jmiFullScreenRankAnnexClan1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jmiFullScreenRankAnnexClan1.setText(bundle.getString("FullScreenClanAnnexRankShort")); // NOI18N
        jmiFullScreenRankAnnexClan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexClan1ActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexClan1);

        jmiFullScreenRankGroups.setText(bundle.getString("FullScreenGroupRank")); // NOI18N
        jmiFullScreenRankGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankGroupsActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankGroups);

        jmiFullScreenRankAnnexGroups.setText(bundle.getString("FullScreenGroupAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexGroupsActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexGroups);

        jmiFullScreenRankAnnexGroups1.setText(bundle.getString("FullScreenGroupAnnexRankShort")); // NOI18N
        jmiFullScreenRankAnnexGroups1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexGroups1ActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexGroups1);

        jmiFullScreenRankCategory.setText(bundle.getString("FullScreenCategoryRank")); // NOI18N
        jmiFullScreenRankCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankCategoryActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankCategory);

        jmiFullScreenRankAnnexCategory.setText(bundle.getString("FullScreenCategoryAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexCategoryActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexCategory);

        jmiFullScreenRankAnnexCategory1.setText(bundle.getString("FullScreenCategoryAnnexRankShort")); // NOI18N
        jmiFullScreenRankAnnexCategory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexCategory1ActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexCategory1);

        jmiFullScreenPool.setText(bundle.getString("FullScreenPoolRank")); // NOI18N
        jmiFullScreenPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenPoolActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenPool);

        jmiFullScreenRankAnnexPool.setText(bundle.getString("FullScreenPoolAnnexRank")); // NOI18N
        jmiFullScreenRankAnnexPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexPoolActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexPool);

        jmiFullScreenRankAnnexPool1.setText(bundle.getString("FullScreenPoolAnnexRankShort")); // NOI18N
        jmiFullScreenRankAnnexPool1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFullScreenRankAnnexPool1ActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFullScreenRankAnnexPool1);

        jmnRound.add(jMenu1);
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
        jmnRound.add(jSeparator17);

        jMenu2.setText(bundle.getString("Reports")); // NOI18N

        jmiIndivReport.setText(bundle.getString("IndividualReport")); // NOI18N
        jmiIndivReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiIndivReportActionPerformed(evt);
            }
        });
        jMenu2.add(jmiIndivReport);

        jmiClanReport.setText(bundle.getString("ClanReport")); // NOI18N
        jmiClanReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiClanReportActionPerformed(evt);
            }
        });
        jMenu2.add(jmiClanReport);

        jmiTeamReport.setText(bundle.getString("TeamReport")); // NOI18N
        jmiTeamReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTeamReportActionPerformed(evt);
            }
        });
        jMenu2.add(jmiTeamReport);

        jmnRound.add(jMenu2);
        jmnRound.add(jSeparator18);

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
        jfc.setCurrentDirectory(new File(currentPath));
        final FileFilter filter1 = new ExtensionFileFilter(
                Translate.translate(CS_TourMaXMLFile),
                new String[]{StringConstants.CS_XML, StringConstants.CS_MINXML});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            StringBuffer url2 = new StringBuffer(jfc.getSelectedFile().getAbsolutePath());
            String ext = StringConstants.CS_NULL;
            final int i = url2.toString().lastIndexOf('.');
            if (i > 0 && i < url2.length() - 1) {
                ext = url2.substring(i + 1).toLowerCase(Locale.getDefault());
            }

            if (!ext.equals(StringConstants.CS_MINXML)) {
                url2 = url2.append(".XML");
            }
            mFile = new File(url2.toString());

            if (mTournament instanceof Tournament) {
                ((Tournament) mTournament).saveXML(mFile);
            }

            currentPath = jfc.getSelectedFile().getAbsolutePath();

        }
    }//GEN-LAST:event_jmiSaveAsActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSaveActionPerformed
        if (mFile != null) {
            if (mTournament instanceof Tournament) {

                ((Tournament) mTournament).saveXML(mFile);

            }
        } else {
            jmiSaveAsActionPerformed(evt);
        }
    }//GEN-LAST:event_jmiSaveActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiChargerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiChargerActionPerformed
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(currentPath));
        final FileFilter filter1 = new ExtensionFileFilter(
                Translate.translate(CS_TourMaXMLFile),
                new String[]{StringConstants.CS_XML, StringConstants.CS_MINXML});
        jfc.setFileFilter(filter1);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (mTournament instanceof Tournament) {
                ((Tournament) mTournament).loadXML(jfc.getSelectedFile());
                mFile = jfc.getSelectedFile();
                updateTree();
                update();
                currentPath = jfc.getSelectedFile().getAbsolutePath();
            }

        }
    }//GEN-LAST:event_jmiChargerActionPerformed

    private static final String CS_None = "None";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiNouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNouveauActionPerformed

        mTournament = Tournament.resetTournament();

        JdgParameters jdgParams = new JdgParameters(this, true);
        jdgParams.setVisible(true);

        mTournament = Tournament.getTournament();

        RosterType.initCollection();

        mTournament.clearGroups();
        final Group group = new Group(Translate.translate(CS_None));
        mTournament.addGroup(group);

        for (int i = 0; i < RosterType.getRostersNamesCount(); i++) {
            String mRostersName = RosterType.getRostersName(i);
            group.addRoster(RosterType.getRosterType(mRostersName));
        }

        update();
    }//GEN-LAST:event_jmiNouveauActionPerformed

    private final static String CS_NAFXMLFile = "NAF XML FILE";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportActionPerformed
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(currentPath));
        final FileFilter filter1 = new ExtensionFileFilter(
                Translate.translate(CS_NAFXMLFile),
                new String[]{StringConstants.CS_XML, StringConstants.CS_MINXML});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (mTournament instanceof Tournament) {
                ((Tournament) mTournament).exportNAF(jfc.getSelectedFile());
            }

            currentPath = jfc.getSelectedFile().getAbsolutePath();

        }
    }//GEN-LAST:event_jmiExportActionPerformed

    private final static String CS_DoYouWantToSave = "VOULEZ VOUS SAUVGARDER ?";
    private final static String CS_Exit = "EXIT";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExitActionPerformed
        int res = JOptionPane.showConfirmDialog(this,
                Translate.translate(CS_DoYouWantToSave),
                Translate.translate(CS_Exit),
                JOptionPane.YES_NO_CANCEL_OPTION);
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
        RMIThread.stop();
        if (JOptionPane.showConfirmDialog(this,
                Translate.translate(CS_DoYouWantToSave),
                Translate.translate(CS_Exit),
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
        if (JOptionPane.showConfirmDialog(this,
                Translate.translate(CS_DoYouWantToSave),
                Translate.translate(CS_Exit),
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (mFile == null) {
                jmiSaveAsActionPerformed(null);
            } else if (mFile.getName().isEmpty()) {
                jmiSaveAsActionPerformed(null);
            } else {
                jmiSaveActionPerformed(null);
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
        LRB.setAllowSpecialSkills(jcxAllowSpecialSkill.getState());
    }//GEN-LAST:event_jcxAllowSpecialSkillActionPerformed

    private final static String CS_FBBCSVFile = "FBB CSV FILE";
    private final static String CS_FBBXMLFile = "FBB XML FILE";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiExportFbbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportFbbActionPerformed
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(currentPath));
        final FileFilter filter1 = new ExtensionFileFilter(
                Translate.translate(CS_FBBCSVFile),
                new String[]{"CSV",
                    "CSV"});
        jfc.setFileFilter(filter1);

        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (mTournament instanceof Tournament) {
                ((Tournament) mTournament).exportFBB(jfc.getSelectedFile());
            }
            currentPath = jfc.getSelectedFile().getAbsolutePath();
        }

    }//GEN-LAST:event_jmiExportFbbActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiExportFbb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportFbb1ActionPerformed
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(currentPath));
        final FileFilter filter1 = new ExtensionFileFilter(
                Translate.translate(CS_FBBXMLFile),
                new String[]{"FBB_XML",
                    "FBB_XML"});
        jfc.setFileFilter(filter1);

        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            final File f = jfc.getSelectedFile();
            if (f.getName().endsWith(".fbb_xml")) {
                if (mTournament instanceof Tournament) {
                    ((Tournament) mTournament).exportFullFBB(f);
                }
            } else {
                if (mTournament instanceof Tournament) {
                    ((Tournament) mTournament).exportFullFBB(new File(f.getAbsolutePath() + ".fbb_xml"));
                }
            }
        }

    }//GEN-LAST:event_jmiExportFbb1ActionPerformed

    private final static String CS_Check = "Check";
    private final static String CS_LargeVictoryPointsAreNotSuperiorToVictoryPoints = "Large victory points are not superior to victory points";
    private final static String CS_LittleLossPointsAreNotSuperiorToLossPoints = "Little loss points are not superior to loss points";
    private final static String CS_LargeVictoryGapIsNotMoreThanOneTouchdown = "Large victory gap is not more than 1 touchdown";
    private final static String CS_LittleLostGapIsNotSuperiorTo0 = "Little lost gap is not superior to 0";
    private final static String CS_2DrawsAreMoreThan1Victory = "2 draws are more than one victory";
    private final static String CS_2LostAreMoreThan1Draw = "2 lost are more than one draw";
    private final static String CS_2LittleLostAreMoreThanOneDraw = "2 little loss are more than one draw";
    private final static String CS_2TeamDrawsAreMoreThan1TeamVictory = "2 team draws are more than one team victory";
    private final static String CS_2TeamLostAreMoreThan1TeamVictory = "2 team lost are more than one team draw";
    private final static String CS_2TeamDrawBonusAreMoreThan1TeamVictoryBonus = "2 team draw bonus are more than one team victory bonus";

    private boolean areRulesValid() {

        boolean valid = true;

        // Check that large victory is more than victory
        if (mTournament.getParams().isUseLargeVictory()) {
            valid = (mTournament.getParams().getPointsIndivLargeVictory() > mTournament.getParams().getPointsIndivVictory());
            if (!valid) {
                JOptionPane.showMessageDialog(this,
                        Translate.translate(CS_LargeVictoryPointsAreNotSuperiorToVictoryPoints),
                        Translate.translate(CS_Check), JOptionPane.ERROR_MESSAGE);
            }
        }

        if (valid) {
            // Check that little loss is more than loss
            if (mTournament.getParams().isUseLittleLoss()) {
                valid = (mTournament.getParams().getPointsIndivLittleLost() > mTournament.getParams().getPointsIndivLost());
                if (!valid) {
                    JOptionPane.showMessageDialog(this,
                            Translate.translate(CS_LittleLossPointsAreNotSuperiorToLossPoints),
                            Translate.translate(CS_Check), JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (valid) {
            // Check that touchdown gap is positive
            if (mTournament.getParams().isUseLargeVictory()) {
                valid = (mTournament.getParams().getGapLargeVictory() > 1);
                if (!valid) {

                    JOptionPane.showMessageDialog(this,
                            Translate.translate(CS_LargeVictoryGapIsNotMoreThanOneTouchdown),
                            Translate.translate(CS_Check), JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (valid) {
            // Check that little loss is positive
            if (mTournament.getParams().isUseLittleLoss()) {
                valid = (mTournament.getParams().getGapLittleLost() > 0);
                if (!valid) {
                    JOptionPane.showMessageDialog(this,
                            Translate.translate(CS_LittleLostGapIsNotSuperiorTo0),
                            Translate.translate(CS_Check), JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (valid) {
            // Check that vicotry is more than 2 draw
            valid = (mTournament.getParams().getPointsIndivVictory() >= 2 * mTournament.getParams().getPointsIndivDraw());
            if (!valid) {
                JOptionPane.showMessageDialog(this,
                        Translate.translate(CS_2DrawsAreMoreThan1Victory),
                        Translate.translate(CS_Check), JOptionPane.ERROR_MESSAGE);
            }

        }

        if (valid) {
            // Check that draw is more than 2 lost
            valid = (mTournament.getParams().getPointsIndivDraw() >= 2 * mTournament.getParams().getPointsIndivLost());
            if (!valid) {
                JOptionPane.showMessageDialog(this, Translate.translate(CS_2LostAreMoreThan1Draw),
                        Translate.translate(CS_Check), JOptionPane.ERROR_MESSAGE);
            }
        }

        if (valid) {
            // Check that little loss is positive
            if (mTournament.getParams().isUseLittleLoss()) {
                valid = (mTournament.getParams().getPointsIndivDraw() >= 2 * mTournament.getParams().getPointsIndivLittleLost());
                if (!valid) {
                    JOptionPane.showMessageDialog(this, Translate.translate(CS_2LittleLostAreMoreThanOneDraw),
                            Translate.translate(CS_Check), JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (mTournament.getParams().isTeamTournament()) {
            if (mTournament.getParams().getTeamPairing() == TEAM_PAIRING) {
                if (mTournament.getParams().isTeamVictoryOnly()) {
                    if (valid) {
                        // Check that vicotry is more than 2 draw
                        valid = (mTournament.getParams().getPointsTeamVictory() >= 2 * mTournament.getParams().getPointsTeamDraw());
                        if (!valid) {
                            JOptionPane.showMessageDialog(this, Translate.translate(CS_2TeamDrawsAreMoreThan1TeamVictory),
                                    Translate.translate(CS_Check), JOptionPane.ERROR_MESSAGE);
                        }

                    }

                    if (valid) {
                        // Check that vicotry is more than 2 draw
                        valid = (mTournament.getParams().getPointsTeamDraw() >= 2 * mTournament.getParams().getPointsTeamLost());
                        if (!valid) {
                            JOptionPane.showMessageDialog(this, Translate.translate(CS_2TeamLostAreMoreThan1TeamVictory),
                                    Translate.translate(CS_Check), JOptionPane.ERROR_MESSAGE);
                        }

                    }
                } else if (valid) {
                    // Check that vicotry is more than 2 draw
                    valid = (mTournament.getParams().getPointsTeamVictoryBonus() >= 2 * mTournament.getParams().getPointsTeamDrawBonus());
                    if (!valid) {
                        JOptionPane.showMessageDialog(this, Translate.translate(CS_2TeamDrawBonusAreMoreThan1TeamVictoryBonus),
                                Translate.translate(CS_Check), JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        }

        return valid;
    }

    private static final String CS_Random = "Random";
    private static final String CS_Cup = "Cup";
    private static final String CS_RegisteringOrder = "Registering order";
    private static final String CS_RoundRobin = "ROUND ROBIN";
    private static final String CS_OddTeamNumber = "OddTeamNumber";
    private static final String CS_ManualChoice = "CHOIX MANUEL";
    private static final String CS_Pools = "POULES";
    private static final String CS_FreeRound = "FREE_ROUND";
    private static final String CS_NafRanking = "NAF_RANK";
    private static final String CS_NafRankingAvg = "NAF_RANK_AVG";
    private static final String CS_RandomAndBalancing = "RandomAndBalancing";
    private static final String CS_Generation = "GÉNÉRATION";
    private static final String CS_ChooseGenerationMethod = "CHOISISSEZ LA MÉTHODE DE GÉNÉRATION: ";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jmiGenerateFirstRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGenerateFirstRoundActionPerformed
        if (areRulesValid()) {

            if (JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("AreYouSure?ItWillEraseAllRounds"), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("FirstRound"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (mTournament.getParams().isTeamTournament() && (mTournament.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) && mTournament.getTeamsCount() % 2 > 0) {
                    JOptionPane.showMessageDialog(this,
                            Translate.translate(CS_OddTeamNumber));
                } else {

                    final ArrayList<String> labels = new ArrayList<>();
                    final ArrayList<Integer> Options = new ArrayList<>();

                    /**
                     * GenRandom possible ?
                     */
                    labels.add(Translate.translate(CS_Random));
                    Options.add(Generation.GEN_RANDOM);

                    /**
                     * Coupe
                     */
                    labels.add(Translate.translate(CS_Cup));
                    Options.add(Generation.GEN_CUP);

                    /**
                     * Ordre
                     */
                    labels.add(Translate.translate(CS_RegisteringOrder));
                    Options.add(Generation.GEN_ORDER);

                    /**
                     * Round Robin
                     */
                    labels.add(Translate.translate(CS_RoundRobin));
                    Options.add(Generation.GEN_RROBIN);

                    /**
                     * manuel
                     */
                    labels.add(Translate.translate(CS_ManualChoice));
                    Options.add(Generation.GEN_MANUAL);

                    /**
                     * Poules
                     */
                    labels.add(Translate.translate(CS_Pools));
                    Options.add(Generation.GEN_POOL);

                    /**
                     * Naf Ranking
                     */
                    labels.add(Translate.translate(CS_NafRanking));
                    Options.add(Generation.GEN_NAF);

                    /**
                     * Naf Ranking
                     */
                    labels.add(Translate.translate(CS_FreeRound));
                    Options.add(Generation.GEN_FREE);

                    /**
                     * Naf Ranking
                     */
                    labels.add(Translate.translate(CS_NafRankingAvg));
                    Options.add(Generation.GEN_NAF_AVG);

                    /*
                    OPTION NON CONCLUANTE
                    
                    if (Tournament.getTournament().getParams().isTeamTournament()) {
                        if (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                      // Balanced Options
                            labels.add(
                                    Translate.translate(CS_RandomAndBalancing));
                            Options.add(Generation.GEN_BALANCED);
                        }
                    }*/
                    final JPanel jpn = new JPanel(new BorderLayout());
                    final JComboBox jcb = new JComboBox(labels.toArray());
                    jpn.add(jcb, BorderLayout.CENTER);
                    final JLabel jlb = new JLabel(
                            Translate.translate(CS_ChooseGenerationMethod));
                    jpn.add(jlb, BorderLayout.NORTH);

                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jpn,
                            Translate.translate(CS_Generation),
                            JOptionPane.QUESTION_MESSAGE);
                    final int index = jcb.getSelectedIndex();

                    Generation.generateFirstRound(Options.get(index));

                    updateTree();
                    update();

                }

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

                if (object.equals(Translate.translate(MainTreeModel.CS_Cup))) {
                    jspSplit.remove(jpnContent);
                    JPNCup jpn = new JPNCup();
                    jspSplit.add(jpn, JSplitPane.RIGHT);
                    jpn.update();
                    jspSplit.setDividerLocation(200);
                    jpnContent = jpn;
                    //System.gc();
                    this.revalidate();
                }

                if (object.equals(Translate.translate(MainTreeModel.CS_Statistics))) {
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

    private ProgressMonitor progressMonitor;

    private static final String CS_DownloadFromNAF = "DownloadFromNAF";
    private static final String CS_Downloading = "Downloading";

    private void jmiNafLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNafLoadActionPerformed

        progressMonitor = new ProgressMonitor(this,
                Translate.translate(CS_DownloadFromNAF),
                Translate.translate(CS_Downloading), 0,
                Tournament.getTournament().getCoachsCount());
        progressMonitor.setProgress(0);

        task = new NafTask(progressMonitor);
        task.addPropertyChangeListener(this);
        task.execute();

        update();
    }//GEN-LAST:event_jmiNafLoadActionPerformed

    private final static String CS_WhichCoachIsSubstituted = "WhichCoachIsSubstituted";
    private final static String CS_Substitution = "Substitution";
    private final static String CS_Round = "Round";
    private final static String CS_ACCR_Versus = "vs";
    private final static String CS_WhichMatch = "WhichMatch";
    private final static String CS_ChooseASubstitute = "ChooseASubstitute";
    private final static String CS_NewDOTDOTDOT = "New...";


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
        JLabel jlb = new JLabel(
                Translate.translate(CS_WhichCoachIsSubstituted)
        );
        JPanel jpn = new JPanel(new BorderLayout());
        jpn.add(jlb, BorderLayout.NORTH);
        jpn.add(jcb, BorderLayout.CENTER);
        int ret = JOptionPane.showConfirmDialog(this, jpn,
                Translate.translate(CS_Substitution),
                JOptionPane.OK_CANCEL_OPTION);
        if (ret == JOptionPane.OK_OPTION) {
            Coach c = (Coach) jcb.getSelectedItem();
            ArrayList<String> matchs_descr = new ArrayList<>();

            // Select Match
            for (int i = 0; i < c.getMatchCount(); i++) {
                Match mMatch = c.getMatch(i);
                CoachMatch m = (CoachMatch) mMatch;
                String tmp
                        = Translate.translate(CS_Round)
                        + " " + (Tournament.getTournament().getRoundIndex(m.getRound()) + 1);
                tmp = tmp + " / " + m.getCompetitor1().getDecoratedName() + " " + Translate.translate(CS_ACCR_Versus) + " "
                        + m.getCompetitor2().getDecoratedName();
                matchs_descr.add(tmp);
            }
            jpn.remove(jcb);
            jcb = new JComboBox(matchs_descr.toArray());
            jpn.add(jcb, BorderLayout.CENTER);
            jlb.setText(Translate.translate(CS_WhichMatch));
            JOptionPane.showConfirmDialog(this, jpn,
                    Translate.translate(CS_Substitution),
                    JOptionPane.OK_OPTION);
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

            availableCoachsName.add(
                    Translate.translate(CS_NewDOTDOTDOT)
            );
            jpn.remove(jcb);
            jcb = new JComboBox(availableCoachsName.toArray());
            jpn.add(jcb, BorderLayout.CENTER);
            jlb.setText(
                    Translate.translate(CS_ChooseASubstitute)
            );
            JOptionPane.showConfirmDialog(this, jpn,
                    Translate.translate(CS_Substitution),
                    JOptionPane.OK_OPTION);

            boolean cancel = false;
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
                    sub.setActive(false);
                    sub.setRoster(RosterType.getRosterType(0));
                    JdgCoach jdg = new JdgCoach(this, true, sub);
                    jdg.setVisible(true);
                    if (!sub.getName().equals("")) {
                        Tournament.getTournament().addCoach(sub);
                        sub.setActive(false);
                        //cancel = true;
                    }
                } else {
                    sub = availableCoachs.get(jcb.getSelectedIndex());
                }

                if (!cancel) {
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
        }

    }//GEN-LAST:event_jmiSubstitutePlayerActionPerformed

    private void jmiEditDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditDescriptionActionPerformed
        /*JTextArea jta = new JTextArea(40, 80);
         jta.setText(Tournament.getTournament().getDescription());
         */

        EkitCore editor = new EkitCore();

        editor.setSize(320, 320);
        editor.setMinimumSize(new Dimension(320, 320));
        editor.setPreferredSize(new Dimension(320, 300));

        JPanel jsp
                = new JPanel(new VerticalLayout());
        //jsp.add(editor.getToolBarMain(true));
        jsp.add(editor.getMenuBar());
        jsp.add(editor.getToolBarFormat(true));
        jsp.add(editor.getToolBarStyles(true));
        jsp.add(editor.getToolBarMain(true));

        jsp.add(editor);

        String text = "<html><header></header><body>" + Tournament.getTournament().getDescription() + "</body>";
        editor.setDocumentText(text);

        //JScrollPane jsp = new JScrollPane(html);
        JOptionPane.showInputDialog(this, jsp);
        String body = editor.getDocumentBody();

        Tournament.getTournament().setDescription(body);

    }//GEN-LAST:event_jmiEditDescriptionActionPerformed

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

                // Clean Cup
                if (round.isCup()) {
                    if (round.getCupTour() == 0) {
                        mTournament.setCup(null);
                    } else {
                        mTournament.getCup().cleanRound(round);
                    }
                }

                mTournament.removeRound(round);

                update();
                updateTree();
            }
        }
    }//GEN-LAST:event_jmiDelRoundActionPerformed

    private final static String CS_FirstTableCoef = "FirstTableCoef";
    private final static String CS_LastTableCoef = "LastTableCoef";
    private final static String CS_RoundCoefficient = "Round coefficient";

    private void editRoundCoef(Round r) {

        JPanel jpn = new JPanel();
        jpn.setLayout(new GridLayout(2, 2));
        JLabel jlb1 = new JLabel(Translate.translate(CS_FirstTableCoef));
        jlb1.setHorizontalAlignment(JLabel.TRAILING);
        JLabel jlb2 = new JLabel(Translate.translate(CS_LastTableCoef));
        jlb2.setHorizontalAlignment(JLabel.TRAILING);

        JFormattedTextField jftf1 = new JFormattedTextField(new DecimalFormat("####.##"));
        jftf1.setValue(new Double(r.getMaxBonus()));

        JFormattedTextField jftf2 = new JFormattedTextField(new DecimalFormat("####.##"));
        jftf2.setValue(new Double(r.getMinBonus()));

        jpn.add(jlb1);
        jpn.add(jftf1);
        jpn.add(jlb2);
        jpn.add(jftf2);

        int res = JOptionPane.showConfirmDialog(this, jpn, Translate.translate(CS_RoundCoefficient), JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                Double val1 = (Double) jftf1.getValue();
                r.setMaxBonus(val1);
            } catch (ClassCastException ce) {
                if (jftf1.getValue() instanceof Long) {
                    String txt = ((Long) jftf1.getValue()).toString() + ".0";
                    r.setMaxBonus(Double.valueOf(txt));
                }
            }
            try {
                Double val2 = (Double) jftf2.getValue();
                r.setMinBonus(val2);
            } catch (ClassCastException cc) {
                if (jftf2.getValue() instanceof Long) {
                    String txt = ((Long) jftf2.getValue()).toString() + ".0";
                    r.setMinBonus(Double.valueOf(txt));
                }
            }
            update();
        }

    }

    private final static String CS_SwissRound = "RONDE SUISSE";
    private final static String CS_SwissRound_TopDown = "RONDE SUISSE ALTERNEE";

    private final static String CS_AcceleratedSwissRound = "RONDE SUISSE ACCELERÉE";
    private final static String CS_Animation = "Animation";

    private final static String CS_Confirm = "CONFIRM";
    private final static String CS_WebMacthNotChecked = "WEB_MATCHES_NOT_CONFIRMED";

    private void jmiGenerateNextRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGenerateNextRoundActionPerformed

        if (areRulesValid()) {
            final ArrayList<String> labels = new ArrayList<>();
            final ArrayList<Integer> Options = new ArrayList<>();

            if (jpnContent instanceof JPNRound) {

                JPNRound jpnr = (JPNRound) jpnContent;
                Round round = jpnr.getRound();

                int remote = 0;
                for (CoachMatch cm : round.getCoachMatchs()) {
                    if (cm.isRemotely()) {
                        remote++;
                    }
                }
                if (remote > 0) {
                    int res = JOptionPane.showConfirmDialog(this, Translate.translate(CS_WebMacthNotChecked), Translate.translate(CS_Confirm), JOptionPane.OK_CANCEL_OPTION);
                    if (res == JOptionPane.CANCEL_OPTION) {
                        return;
                    }
                }

                int round_number = mTournament.getRoundIndex(round);

                /**
                 * Swiss possible ?
                 */
                if ((!round.isCup()) && (!mTournament.isRoundRobin())) {
                    labels.add(Translate.translate(CS_SwissRound));
                    Options.add(Generation.GEN_SWISS);
                    labels.add(Translate.translate(CS_SwissRound_TopDown));
                    Options.add(Generation.GEN_SWISS_TOP_AND_DOWN);
                }

                /**
                 * QSwiss possible ?
                 */
                if ((!round.isCup()) && (!mTournament.isRoundRobin())) {
                    labels.add(Translate.translate(CS_AcceleratedSwissRound));
                    Options.add(Generation.GEN_QSWISS);
                }

                /**
                 * GenRandom possible ?
                 */
                if ((!round.isCup()) && (!mTournament.isRoundRobin())) {
                    labels.add(Translate.translate(CS_Random));
                    Options.add(Generation.GEN_RANDOM);
                }

                /**
                 * Coupe
                 */
                labels.add(Translate.translate(CS_Cup));
                Options.add(Generation.GEN_CUP);

                /**
                 *
                 * Libre
                 */
                if ((!round.isCup()) && (!mTournament.isRoundRobin())) {
                    labels.add(Translate.translate(CS_FreeRound));
                    Options.add(Generation.GEN_FREE);
                }

                final JPanel jpn = new JPanel(new BorderLayout());
                final JComboBox jcb = new JComboBox(labels.toArray());
                jpn.add(jcb, BorderLayout.CENTER);
                final JLabel jlb = new JLabel(Translate.translate(CS_ChooseGenerationMethod));
                jpn.add(jlb, BorderLayout.NORTH);

                final JCheckBox jcxClash = new JCheckBox(Translate.translate(CS_Animation));
                jpn.add(jcxClash, BorderLayout.SOUTH);

                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jpn,
                        Translate.translate(CS_Generation), JOptionPane.QUESTION_MESSAGE);

                final int index = jcb.getSelectedIndex();

                Generation.nextRound(round, Options.get(index), round_number);

                if (mTournament.getParams().isTableBonusPerRound()) {
                    editRoundCoef(mTournament.getRound(mTournament.getRoundsCount() - 1));
                }

                if (jpnContent instanceof JPNRound) {
                    ((JPNRound) jpnContent).update();
                    update();
                }

                updateTree();

                if (jcxClash.isSelected()) {
                    try {
                        JFullScreen fs = new JFullScreenMatchs(Tournament.getTournament().getRound(round_number + 1), true);
                        fs.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

    private static final String CS_FreeMatch = "MATCH LIBRE";
    private static final String CS_Error = "ERROR";
    private static final String CS_ImpossibleMatch = "MATCH IMPOSSIBLE";

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
                    final JLabel jlb = new JLabel(
                            " " + Translate.translate(CS_ACCR_Versus) + " "
                    );
                    jpnQuestion.add(jlb, BorderLayout.CENTER);

                    final int ret = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), jpnQuestion,
                            Translate.translate(CS_FreeMatch),
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
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
                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                                    Translate.translate(CS_ImpossibleMatch),
                                    Translate.translate(CS_Error),
                                    JOptionPane.ERROR_MESSAGE);
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
                    final JLabel jlb = new JLabel(
                            " " + Translate.translate(CS_ACCR_Versus) + " ");
                    jpnQuestion.add(jlb, BorderLayout.CENTER);

                    final int ret = JOptionPane.showOptionDialog(MainFrame.getMainFrame(), jpnQuestion,
                            Translate.translate(CS_FreeMatch),
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

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
                            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                                    Translate.translate(CS_ImpossibleMatch),
                                    Translate.translate(CS_Error), JOptionPane.ERROR_MESSAGE);
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

    private static final String CS_MatchAlreadyConceededOrRefused = "MatchAlreadyConceededOrRefused";
    private static final String CS_Cancel = "Cancel";
    private static final String CS_ConceedAMatch = "ConceedAMatch";
    private static final String CS_WhoConceedTheMatch = "WhoConceedTheMatch";
    private static final String CS_RefuseAMatch = "RefuseAMatch";
    private static final String CS_WhoRefuseMatch = "WhoRefuseMatch";


    private void jmiConceedMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConceedMatchActionPerformed
        try {
            if (jpnContent instanceof JPNRound) {

                JPNRound jpnr = (JPNRound) jpnContent;
                int nbMatch = jpnr.getMatchTableSelectedRow();
                CoachMatch m = jpnr.getRound().getCoachMatchs().get(nbMatch);
                if (m.isConcedeedBy1() || m.isConcedeedBy2() || m.isRefusedBy1() || m.isRefusedBy2()) {
                    JOptionPane.showMessageDialog(null,
                            Translate.translate(CS_Error),
                            Translate.translate(CS_MatchAlreadyConceededOrRefused),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Object[] options = new Object[3];
                    options[0] = m.getCompetitor1();
                    options[1] = m.getCompetitor2();
                    options[2] = Translate.translate(CS_Cancel);

                    Object option = JOptionPane.showInputDialog(null,
                            Translate.translate(CS_ConceedAMatch),
                            Translate.translate(CS_WhoConceedTheMatch),
                            JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

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
                    JOptionPane.showMessageDialog(null,
                            Translate.translate(CS_Error),
                            Translate.translate(CS_MatchAlreadyConceededOrRefused),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Object[] options = new Object[3];
                    options[0] = m.getCompetitor1();
                    options[1] = m.getCompetitor2();
                    options[2] = Translate.translate(CS_Cancel);

                    Object option = JOptionPane.showInputDialog(null,
                            Translate.translate(CS_RefuseAMatch),
                            Translate.translate(CS_WhoRefuseMatch),
                            JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

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
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jmiFullScreenMatchsActionPerformed

    private void jmiFullScreenRankGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankGeneralActionPerformed
        JFullScreenIndivRank fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivRank(Tournament.getTournament().getRoundIndex(jpnr.getRound()));
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jmiFullScreenRankGeneralActionPerformed

    private void jmiFullScreenRankTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankTeamActionPerformed
        JFullScreenTeamRank fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenTeamRank(Tournament.getTournament().getRoundIndex(jpnr.getRound()));
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jmiFullScreenRankTeamActionPerformed

    private void jmiFullScreenRankClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankClanActionPerformed
        JFullScreenClanRank fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenClanRank(Tournament.getTournament().getRoundIndex(jpnr.getRound()));
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankClanActionPerformed

    private void jmiFullScreenRankAnnexIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexIndivActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), true);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexIndivActionPerformed

    private void jmiFullScreenRankAnnexIndiv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexIndiv1ActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), false);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexIndiv1ActionPerformed

    private void jmiFullScreenRankAnnexTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexTeamActionPerformed
        JFullScreenClanTeamAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), true, true);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexTeamActionPerformed

    private void jmiFullScreenRankAnnexTeam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexTeam1ActionPerformed
        JFullScreenClanTeamAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), false, true);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexTeam1ActionPerformed

    private void jmiFullScreenRankAnnexClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexClanActionPerformed
        JFullScreenClanTeamAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), true, false);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexClanActionPerformed

    private void jmiFullScreenRankAnnexClan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexClan1ActionPerformed
        JFullScreenClanTeamAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), false, false);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexClan1ActionPerformed

    private TMultiServer server = null;
    private WebServer web = null;

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
                        Logger.getLogger(MainFrame.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                server.start();
            }

            if (web == null) {
                try {
                    try {
                        web = new WebServer();

                    } catch (IOException ex) {
                        Logger.getLogger(MainFrame.class
                                .getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                    web.start();

                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            } else {
                synchronized (this) {
                    web.stop();
                    try {
                        web.wait();

                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainFrame.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    web.start();
                } catch (IOException ex) {
                    //Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            synchronized (this) {
                server.stopServer();
                try {
                    server.wait();
                } catch (InterruptedException | IllegalMonitorStateException ex) {
                    //Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            server = null;

            synchronized (this) {
                web.stop();
                try {
                    web.wait();
                } catch (InterruptedException | IllegalMonitorStateException ex) {
                    //Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            web = null;

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
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenMatchsClashActionPerformed

    private void jmiFullScreenRankGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankGroupsActionPerformed
        JFullScreenIndivRank fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivRank(Tournament.getTournament().getRoundIndex(jpnr.getRound()), JFullScreenIndivRank.C_GROUP);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankGroupsActionPerformed

    private void jmiFullScreenRankAnnexGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexGroupsActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), true, C_GROUP);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexGroupsActionPerformed

    private void jmiFullScreenRankAnnexGroups1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexGroups1ActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), false, C_GROUP);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexGroups1ActionPerformed

    private void jmiFullScreenRankCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankCategoryActionPerformed
        JFullScreenIndivRank fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivRank(Tournament.getTournament().getRoundIndex(jpnr.getRound()), JFullScreenIndivRank.C_CATEGORY);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankCategoryActionPerformed

    private void jmiFullScreenRankAnnexCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexCategoryActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), true, JFullScreenIndivRank.C_CATEGORY);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexCategoryActionPerformed

    private void jmiFullScreenRankAnnexCategory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenRankAnnexCategory1ActionPerformed
        JFullScreenIndivAnnex fs;
        try {
            if (jpnContent instanceof JPNRound) {
                JPNRound jpnr = ((JPNRound) jpnContent);
                fs = new JFullScreenIndivAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), false, JFullScreenIndivRank.C_CATEGORY);
                fs.setVisible(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmiFullScreenRankAnnexCategory1ActionPerformed

    private void jmiFullScreenPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFullScreenPoolActionPerformed

        if (Tournament.getTournament().getPoolCount() > 0) {
            if (Tournament.getTournament().getParams().isTeamTournament()) {
                JFullScreenTeamRank fs;
                try {
                    if (jpnContent instanceof JPNRound) {
                        JPNRound jpnr = ((JPNRound) jpnContent);
                        fs = new JFullScreenTeamRank(Tournament.getTournament().getRoundIndex(jpnr.getRound()),
                                true);
                        fs.setVisible(true);

                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JFullScreenIndivRank fs;
                try {
                    if (jpnContent instanceof JPNRound) {
                        JPNRound jpnr = ((JPNRound) jpnContent);
                        fs = new JFullScreenIndivRank(Tournament.getTournament().getRoundIndex(jpnr.getRound()),
                                C_POOL);
                        fs.setVisible(true);

                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class
                            .getName()).log(Level.SEVERE, null, ex);
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
                        fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), true,
                                true, true);
                        fs.setVisible(true);

                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JFullScreenIndivAnnex fs;
                try {
                    if (jpnContent instanceof JPNRound) {
                        JPNRound jpnr = ((JPNRound) jpnContent);
                        fs = new JFullScreenIndivAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), true,
                                C_POOL);
                        fs.setVisible(true);

                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class
                            .getName()).log(Level.SEVERE, null, ex);
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
                        fs = new JFullScreenClanTeamAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), false,
                                true, true);
                        fs.setVisible(true);

                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JFullScreenIndivAnnex fs;
                try {
                    if (jpnContent instanceof JPNRound) {
                        JPNRound jpnr = ((JPNRound) jpnContent);
                        fs = new JFullScreenIndivAnnex(Tournament.getTournament().getRoundIndex(jpnr.getRound()), false,
                                C_POOL);
                        fs.setVisible(true);

                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class
                            .getName()).log(Level.SEVERE, null, ex);
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

    private void jckmiHideNonNafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckmiHideNonNafActionPerformed
        if (jpnContent instanceof JPNRound) {

            JPNRound jpnr = (JPNRound) jpnContent;
            jpnr.setNafOnly(jckmiHideNonNaf.isSelected());
            jpnr.update();
        }
    }//GEN-LAST:event_jckmiHideNonNafActionPerformed

    public static String CS_EditWebPort = "EditWebPort";

    private void jmiEditWebPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditWebPortActionPerformed

        int port = Tournament.getTournament().getParams().getWebServerPort();

        Object obj = JOptionPane.showInputDialog(this, Translate.translate(CS_EditWebPort), port);

        if (obj instanceof String) {
            Tournament.getTournament().getParams().setWebServerPort(Integer.parseInt((String) obj));
        }

        if (obj instanceof Integer) {
            Tournament.getTournament().getParams().setWebServerPort((Integer) obj);
        }

    }//GEN-LAST:event_jmiEditWebPortActionPerformed

    private void jcxmiRemoteEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxmiRemoteEditActionPerformed

        Tournament.getTournament().getParams().setWebEdit(jcxmiRemoteEdit.isSelected());

    }//GEN-LAST:event_jcxmiRemoteEditActionPerformed

    private static final String COLOR_1 = "Couleur 1";
    private static final String COLOR_2 = "Couleur 2";
    private static final String COLOR_WRITING = "Couleur Ecriture";
    private static final String COLOR_BORDER = "Couleur Bordure";

    private void jmiEditColorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditColorsActionPerformed

        JButton jbtColor1 = new JButton(" ");
        jbtColor1.setBackground(Tournament.getTournament().getParams().getColor1());
        JButton jbtColor2 = new JButton(" ");
        jbtColor2.setBackground(Tournament.getTournament().getParams().getColor2());
        JButton jbtBorderColor = new JButton(" ");
        jbtBorderColor.setBackground(Tournament.getTournament().getParams().getBorderColor());
        JButton jbtForeColor = new JButton(" ");
        jbtForeColor.setBackground(Tournament.getTournament().getParams().getForeColor());

        jbtColor1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(jpnContent, CS_None, Tournament.getTournament().getParams().getColor1());
                Tournament.getTournament().getParams().setColor1(c);
                jbtColor1.setBackground(c);
            }
        });

        jbtColor2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(jpnContent, CS_None, Tournament.getTournament().getParams().getColor2());
                Tournament.getTournament().getParams().setColor2(c);
                jbtColor2.setBackground(c);
            }
        });

        jbtBorderColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(jpnContent, CS_None, Tournament.getTournament().getParams().getBorderColor());
                Tournament.getTournament().getParams().setBorderColor(c);
                jbtBorderColor.setBackground(c);
            }
        });

        jbtForeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(jpnContent, CS_None, Tournament.getTournament().getParams().getForeColor());
                Tournament.getTournament().getParams().setForeColor(c);
                jbtForeColor.setBackground(c);
            }
        });

        JLabel jlbColor1 = new JLabel(Translate.translate(COLOR_1));
        JLabel jlbColor2 = new JLabel(Translate.translate(COLOR_2));
        JLabel jlbForeColor = new JLabel(Translate.translate(COLOR_WRITING));
        JLabel jlbBorderColor = new JLabel(Translate.translate(COLOR_BORDER));

        JPanel jpn = new JPanel(new GridLayout(4, 2));

        jpn.add(jlbColor1);
        jpn.add(jbtColor1);
        jpn.add(jlbColor2);
        jpn.add(jbtColor2);
        jpn.add(jlbBorderColor);
        jpn.add(jbtBorderColor);
        jpn.add(jlbForeColor);
        jpn.add(jbtForeColor);

        JOptionPane.showConfirmDialog(this, jpn, "Couleurs", JOptionPane.OK_OPTION);

    }//GEN-LAST:event_jmiEditColorsActionPerformed

    private void jmiPrintLabelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPrintLabelsActionPerformed
        // Set Choice 
        ArrayList<String> choices = new ArrayList<>();
        // Individual/Team
        // With Name/Without Names

        if (Tournament.getTournament().getParams().isTeamTournament()
                && (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
            choices.add("Individuelles pré-remplies");
            choices.add("Individuelles vides");
            choices.add("Par équipe pré-remplies");
            choices.add("Par équipe vides");
        } else {
            choices.add("pré-remplies");
            choices.add("vides");
        }

        Object obj = JOptionPane.showOptionDialog(this, "Quel type d'étiquettes voulez vous ?", "Etiquettes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices.toArray(), null);
        if (obj != null) {
            JdgPrintLabel jdg = null;
            if ((jpnContent != null) && (obj instanceof Integer)) {
                if (jpnContent instanceof JPNRound) {
                    int choice = (int) obj;
                    Round round = ((JPNRound) jpnContent).getRound();
                    if (choice == 0) {
                        jdg = new JdgPrintLabel(this, true, round, true, false);
                    }
                    if (choice == 1) {
                        jdg = new JdgPrintLabel(this, true, round, false, false);
                    }
                    if (choice == 2) {
                        jdg = new JdgPrintLabel(this, true, round, true, true);
                    }
                    if (choice == 3) {
                        jdg = new JdgPrintLabel(this, true, round, false, true);
                    }
                }
            }
            // Display PDF template with print button
            if (jdg != null) {
                jdg.setVisible(true);
            }
        }


    }//GEN-LAST:event_jmiPrintLabelsActionPerformed

    private void jmiIndivReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIndivReportActionPerformed
        JPNRound jpnr = ((JPNRound) jpnContent);
        JdgReport report = new JdgReport(this, true, Tournament.getTournament().getRoundIndex(jpnr.getRound()),
                this.mTournament, JdgReport.C_INDIVIDUAL);
        report.setVisible(true);
    }//GEN-LAST:event_jmiIndivReportActionPerformed

    private void jmiClanReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiClanReportActionPerformed
        JPNRound jpnr = ((JPNRound) jpnContent);
        JdgReport report = new JdgReport(this, true, Tournament.getTournament().getRoundIndex(jpnr.getRound()),
                this.mTournament, JdgReport.C_CLAN);
        report.setVisible(true);
    }//GEN-LAST:event_jmiClanReportActionPerformed

    private void jmiTeamReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTeamReportActionPerformed
        JPNRound jpnr = ((JPNRound) jpnContent);
        JdgReport report = new JdgReport(this, true, Tournament.getTournament().getRoundIndex(jpnr.getRound()),
                this.mTournament, JdgReport.C_TEAM);
        report.setVisible(true);
    }//GEN-LAST:event_jmiTeamReportActionPerformed

    private void jmiExportWebServerAsZIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportWebServerAsZIPActionPerformed

        JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter(
                "Zip",
                new String[]{"ZIP",
                    "zip"});
        jfc.setFileFilter(filter1);
        int result = jfc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {

            File zipFile = jfc.getSelectedFile();

            // First this function will generate in a temporary directory
            // all the possible web pages
            ArrayList<File> files = WebServer.getWebSiteFiles();

            if (files.size() > 0) {
                FileOutputStream fout = null;
                try {
                    // Then it will zip this directory and export it
                    fout = new FileOutputStream(zipFile);
                    ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(fout));

                    for (int i = 0; i < files.size(); i++) {
                        File f = files.get(i);
                        byte[] b = new byte[(int) f.length()];
                        FileInputStream fin = new FileInputStream(f);
                        zout.putNextEntry(new ZipEntry(f.getName()));
                        int length;
                        while ((length = fin.read(b)) > 0) {
                            zout.write(b, 0, length);
                        }
                        zout.closeEntry();
                        fin.close();
                    }
                    zout.close();

                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fout.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            WebServer.cleanFiles(files);
        }
    }//GEN-LAST:event_jmiExportWebServerAsZIPActionPerformed

    private void jmiExportWebServerToSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportWebServerToSiteActionPerformed
        // First this function will generate in a temporary directory
        // all the possible web pages
        FTPClient client = new FTPClient();
        try {
            client.connect("ftp.ainpacte.org");
            System.out.println("Connected to " + server + ".");
            System.out.println(client.getReplyString());
            System.out.println(client.getReplyCode());
            client.login("ainpacte", "Lancie69");
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }

        // Then it will connect to remote site (FTP or SFTP)
        // and copy the files.
    }//GEN-LAST:event_jmiExportWebServerToSiteActionPerformed

    private void jmiMassAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMassAddActionPerformed
        JdgMassAdd jdg = new JdgMassAdd(this, true);
        jdg.setVisible(true);
        update();
    }//GEN-LAST:event_jmiMassAddActionPerformed

    private void jcxDisplayRostersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxDisplayRostersActionPerformed
        Tournament.getTournament().getParams().setDisplayRoster(jcxDisplayRosters.getState());

    }//GEN-LAST:event_jcxDisplayRostersActionPerformed

    private void jmiEditRosterListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditRosterListActionPerformed
        JdgRosters jdg = new JdgRosters(this, true);
        jdg.setVisible(true);
    }//GEN-LAST:event_jmiEditRosterListActionPerformed

    private void jcxIgnoreCapsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxIgnoreCapsActionPerformed

        NAF.setIgnoreCaps(jcxIgnoreCaps.isSelected());
    }//GEN-LAST:event_jcxIgnoreCapsActionPerformed

    public boolean isRoundOnly() {
        return jckmiRoundOnly.isSelected();
    }

    public boolean isNafOnly() {
        return jckmiHideNonNaf.isSelected();
    }

    private NafTask task;

    private static final String CS_Download = "Download";

    private static final String CS_NewGame = "NewGame";
    private static final String CS_Open = "Open";
    private static final String CS_UseRosterEditor = "UseRosterEditor";
    private static final String CS_ClientViewer = "ClientViewer";
    private static final String CS_NewGameOrOpen = "NewGameOrOpen";
    private static final String CS_EnterRemoteTourmaServer = "Enter remote server IP address";
    private static final String CS_Exiting = "Exiting";
    private static final String CS_ConnectionImpossibleTo = "Connection impossible to";
    private static final String CS_ChooseFullScreen = "ChooseFullScreen";
    /**
     * @param args the command line arguments
     */

    private static final String CS_IndividualRanking = "Individual ranking";
    private static final String CS_IndividualAnnexRanking = "Individual Annex rankings";
    private static final String CS_TeamRanking = "Team ranking";
    private static final String CS_TeamAnnexRanking = "Team Annex Ranking";
    private static final String CS_ClanRanking = "Clan ranking";
    private static final String CS_ClanAnnexRanking = "Clan Annex Ranking";
    private static final String CS_MatchsClash = "Matchs Clash";
    private static final String CS_CategoryRanking = "Categories Ranking";
    private static final String CS_CategoryAnnexRanking = "Categories Annex Ranking";
    private static final String CS_GroupeRanking = "Group Ranking";
    private static final String CS_GroupeAnnexRanking = "Group Annex Ranking";
    private static final String CS_IndividualPoolRanking = "Individual Pool Ranking";
    private static final String CS_IndividualPoolAnnexRanking = "Individual Pool Annex Ranking";
    private static final String CS_TeamPoolRanking = "Team Pool Ranking";
    private static final String CS_TeamPoolAnnexRanking = "Team Pool Annex Ranking";
    private static final String CS_Matchs = "Matchs";

    /**
     * Display a (very) basic progress bar
     *
     * @param pct how much of the progress bar to display 0-100
     */
    public static void splashProgress(int pct) {
        if (mySplash != null && mySplash.isVisible()) {
            splashGraphics.setPaint(Color.LIGHT_GRAY);
            splashGraphics.fill(splashProgressArea);

            // draw an outline
            splashGraphics.setPaint(Color.BLUE);
            splashGraphics.draw(splashProgressArea);

            // Calculate the width corresponding to the correct percentage
            int x = (int) splashProgressArea.getMinX();
            int y = (int) splashProgressArea.getMinY();
            int wid = (int) splashProgressArea.getWidth();
            int hgt = (int) splashProgressArea.getHeight();

            int doneWidth = Math.round(pct * wid / 100.f);
            doneWidth = Math.max(0, Math.min(doneWidth, wid - 1));  // limit 0-width

            // fill the done part one pixel smaller than the outline
            splashGraphics.setPaint(Color.CYAN);
            splashGraphics.fillRect(x, y + 1, doneWidth, hgt - 1);

            x = (int) splashThanksTo.getMinX();
            y = (int) splashThanksTo.getMinY();
            wid = (int) splashThanksTo.getWidth();
            hgt = (int) splashThanksTo.getHeight();

            doneWidth = Math.round(pct * wid / 100.f);
            doneWidth = Math.max(0, Math.min(doneWidth, wid - 1));  // limit 0-width

            // fill the done part one pixel smaller than the outline
            splashGraphics.setPaint(Color.WHITE);
            splashGraphics.fillRect(x, y + 1, doneWidth, hgt - 1);

            // erase the last status text
            splashGraphics.setPaint(Color.LIGHT_GRAY);
            splashGraphics.fill(splashTextArea);

            // draw the text
            splashGraphics.setPaint(Color.BLACK);
            splashGraphics.drawString(Translate.translate("GREETINGS0"), (int) (splashThanksTo.getX() + 10), (int) (splashThanksTo.getY() + 15));
            splashGraphics.drawString(Translate.translate("GREETINGS1"), (int) (splashThanksTo.getX() + 10), (int) (splashThanksTo.getY() + 15 + splashGraphics.getFontMetrics().getHeight()));
            splashGraphics.drawString(Translate.translate("GREETINGS2"), (int) (splashThanksTo.getX() + 10), (int) (splashThanksTo.getY() + 15 + splashGraphics.getFontMetrics().getHeight() * 2));
            splashGraphics.drawString(Translate.translate("GREETINGS3"), (int) (splashThanksTo.getX() + 10), (int) (splashThanksTo.getY() + 15 + splashGraphics.getFontMetrics().getHeight() * 3));
            splashGraphics.drawString(Translate.translate("GREETINGS4"), (int) (splashThanksTo.getX() + 10), (int) (splashThanksTo.getY() + 15 + splashGraphics.getFontMetrics().getHeight() * 4));
            splashGraphics.drawString(Translate.translate("GREETINGS5"), (int) (splashThanksTo.getX() + 10), (int) (splashThanksTo.getY() + 15 + splashGraphics.getFontMetrics().getHeight() * 5));

            // make sure it's displayed
            mySplash.update();
        }
    }

    /**
     * just a stub to simulate a long initialization task that updates the text
     * and progress parts of the status in the Splash
     */
    private static void appInit() {

        splashText("List NAF id Database");
        // Get Number of naf_id text
        ArrayList<File> naf_list = NAF.getFileList();
        int nb_steps = naf_list.size() + 6;
        int pct = 100 / nb_steps;
        splashProgress(1 * pct);

        try {
            splashText("Initization of RMi Registry");
            splashProgress(2 * pct);

            RMITournament tour = RMITournament.getInstance();
            ITournament stub = (ITournament) UnicastRemoteObject.exportObject(tour, 0);

            splashText("Binding Tournament");
            splashProgress(3 * pct);
            Registry registry = LocateRegistry.createRegistry(1099);// getRegistry();
            registry.bind("TourMa", stub);

        } catch (RemoteException | AlreadyBoundException e) {
            System.out.println(e.getLocalizedMessage());
        }

        splashText("Loading NAF coach XML base");
        for (int i = 0; i < naf_list.size(); i++) {
            splashProgress((4 + i) * pct);
            NAF.initCoachs(naf_list.get(i));
        }
        for (int i = 1; i <= 2; i++) {
            int pctDone = i * 2;
            splashText("Initialization");
            splashProgress((5 + naf_list.size()) * pct);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                // ignore it
            }
        }
    }

    /**
     * Display text in status area of Splash. Note: no validation it will fit.
     *
     * @param str - text to be displayed
     */
    public static void splashText(String str) {
        if (mySplash != null && mySplash.isVisible()) {   // important to check here so no other methods need to know if there
            // really is a Splash being displayed

            // erase the last status text
            splashGraphics.setPaint(Color.LIGHT_GRAY);
            splashGraphics.fill(splashTextArea);

            // draw the text
            splashGraphics.setPaint(Color.BLACK);
            splashGraphics.drawString(str, (int) (splashTextArea.getX() + 10), (int) (splashTextArea.getY() + 15));

            // make sure it's displayed
            mySplash.update();
        }
    }

    /**
     * Prepare the global variables for the other splash functions
     */
    private static void splashInit() {
        mySplash = SplashScreen.getSplashScreen();

        if (mySplash != null) {   // if there are any problems displaying the splash this will be null
            Dimension ssDim = mySplash.getSize();
            int height = ssDim.height;
            int width = ssDim.width;
            // stake out some area for our status information
            splashTextArea = new Rectangle2D.Double(15., height * 0.88, width * .45, 32.);
            splashProgressArea = new Rectangle2D.Double(width * .55, height * .92, width * .4, 12);
            splashThanksTo = new Rectangle2D.Double(width * .05, height * .45, width * .9, 200);

            // create the Graphics environment for drawing status info
            splashGraphics = mySplash.createGraphics();
            font = new Font("Dialog", Font.PLAIN, 14);
            splashGraphics.setFont(font);

            splashGraphics.fill(splashThanksTo);

            // initialize the status info
            splashText("Start");
            splashProgress(0);
        }
    }

    private static SplashScreen mySplash = null;
    private static Rectangle2D splashTextArea = null;
    private static Rectangle2D splashProgressArea = null;
    private static Rectangle2D splashThanksTo = null;
    private static Graphics2D splashGraphics = null;
    private static Font font = null;

    public static void main(final String args[]) {

        splashInit();

        appInit();

        if (mySplash != null) // check if we really had a spash screen
        {
            mySplash.close();   // if so we're now done with it
        }
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    final ArrayList<String> StartOptions = new ArrayList<>();
                    StartOptions.add(
                            Translate.translate(CS_NewGame));
                    StartOptions.add(
                            Translate.translate(CS_Open));
                    StartOptions.add(
                            Translate.translate(CS_UseRosterEditor));
                    StartOptions.add(Translate.translate(CS_ClientViewer));
                    final int res = JOptionPane.showOptionDialog(null,
                            Translate.translate(CS_NewGameOrOpen),
                            StringConstants.CS_NULL,
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, StartOptions.toArray(),
                            Translate.translate(CS_Open));

                    if ((res == 0) || (res == 1)) {
                        MainFrame window = MainFrame.getMainFrame(res);
                        window.setVisible(true);
                    }

                    if (res == 2) {
                        teamma.views.JdgRoster jdg = new JdgRoster(null, true);
                        jdg.setVisible(true);
                        System.exit(0);
                    }

                    if (res == 3) {

                        String address = (String) JOptionPane.showInputDialog(null,
                                Translate.translate(CS_EnterRemoteTourmaServer), "127.0.0.1");

                        try {
                            UnicastRemoteObject.unexportObject(RMITournament.getInstance(), true);
                        } catch (NoSuchObjectException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        RMIThread rmi = new RMIThread(address);
                        Thread thread = new Thread(rmi);
                        thread.start();

                        Tournament.getTournament().setIsClient(true);

                        MainFrame window = MainFrame.getMainFrame(res);
                        window.setVisible(true);


                        /*try {
                            
                            Socket socket = null;
                            try {
                                socket = new Socket(address, 2017);
                            } catch (ConnectException e) {
                                JOptionPane.showMessageDialog(null, Translate.translate(CS_ConnectionImpossibleTo)
                                        + " " + address + ". "
                                        + Translate.translate(CS_Exiting));
                                System.exit(1);
                            }

                            ArrayList<String> labels = new ArrayList<>();

                            // Index 0: Individual Ranking
                            labels.add(Translate.translate(CS_IndividualRanking));
                            // Index 1: Individual Annex ranks
                            labels.add(Translate.translate(CS_IndividualAnnexRanking));
                            // Index 2: Team Ranking
                            labels.add(Translate.translate(CS_TeamRanking));
                            // Index 3: Team Annex
                            labels.add(Translate.translate(CS_TeamAnnexRanking));
                            // Index 4: Clan Ranking
                            labels.add(Translate.translate(CS_ClanRanking));
                            // Index 5: Clan Annex
                            labels.add(Translate.translate(CS_ClanAnnexRanking));
                            // Index 6:Matchs
                            labels.add(Translate.translate(CS_Matchs));
                            // Index 7: Clash Match
                            labels.add(Translate.translate(CS_MatchsClash));
                            // Index 8: Categories
                            labels.add(Translate.translate(CS_CategoryRanking));
                            // Index 9: Categories
                            labels.add(Translate.translate(CS_CategoryAnnexRanking));
                            // Index 10: Group
                            labels.add(Translate.translate(CS_GroupeRanking));
                            // Index 11: Categories
                            labels.add(Translate.translate(CS_GroupeAnnexRanking));
                            // Index 12: Indiv Pool
                            labels.add(Translate.translate(CS_IndividualPoolRanking));
                            // Index 13: Indiv Pool Annex
                            labels.add(Translate.translate(CS_IndividualPoolAnnexRanking));
                            // Index 14: Team Pool
                            labels.add(Translate.translate(CS_TeamPoolRanking));
                            // Index 15: Categories
                            labels.add(Translate.translate(CS_TeamPoolAnnexRanking));

                            final JPanel jpn = new JPanel(new BorderLayout());
                            final JComboBox jcb = new JComboBox(labels.toArray());
                            jpn.add(jcb, BorderLayout.CENTER);
                            final JLabel jlb = new JLabel(
                                    Translate.translate(CS_ChooseFullScreen)
                            );
                            jpn.add(jlb, BorderLayout.NORTH);

                            JOptionPane.showMessageDialog(null, jpn,
                                    Translate.translate(CS_Generation),
                                    JOptionPane.QUESTION_MESSAGE);

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
                        }*/
                    }
                }
            }
            );

        } catch (InterruptedException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (InvocationTargetException ex) {
            Logger.getLogger(MainFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator20;
    private javax.swing.JPopupMenu.Separator jSeparator21;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JCheckBoxMenuItem jckmiHideNonNaf;
    private javax.swing.JCheckBoxMenuItem jckmiRoundOnly;
    private javax.swing.JCheckBoxMenuItem jcxAllowSpecialSkill;
    private javax.swing.JCheckBoxMenuItem jcxDisplayRosters;
    private javax.swing.JCheckBoxMenuItem jcxIgnoreCaps;
    private javax.swing.JCheckBoxMenuItem jcxPatchPortugal;
    private javax.swing.JCheckBoxMenuItem jcxUseColor;
    private javax.swing.JCheckBoxMenuItem jcxUseImage;
    public javax.swing.JCheckBoxMenuItem jcxmiAsServer;
    private javax.swing.JCheckBoxMenuItem jcxmiRemoteEdit;
    private javax.swing.JMenuItem jmiAbout;
    private javax.swing.JMenuItem jmiAddFreeMatch;
    private javax.swing.JMenuItem jmiAideEnLigne;
    private javax.swing.JMenuItem jmiCancelConceedMatch;
    private javax.swing.JMenuItem jmiCancelMatchRefuse;
    private javax.swing.JMenuItem jmiChangePairing;
    private javax.swing.JMenuItem jmiCharger;
    private javax.swing.JMenuItem jmiClanReport;
    private javax.swing.JMenuItem jmiConceedMatch;
    private javax.swing.JMenuItem jmiDelFreeMatch;
    private javax.swing.JMenuItem jmiDelRound;
    private javax.swing.JMenuItem jmiEditCoef;
    private javax.swing.JMenuItem jmiEditColors;
    private javax.swing.JMenuItem jmiEditDescription;
    private javax.swing.JMenuItem jmiEditRosterList;
    private javax.swing.JMenuItem jmiEditTeam;
    private javax.swing.JMenuItem jmiEditWebPort;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiExport;
    private javax.swing.JMenuItem jmiExportFbb;
    private javax.swing.JMenuItem jmiExportFbb1;
    private javax.swing.JMenuItem jmiExportWebServerAsZIP;
    private javax.swing.JMenuItem jmiExportWebServerToSite;
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
    private javax.swing.JMenuItem jmiIndivReport;
    private javax.swing.JMenuItem jmiMassAdd;
    private javax.swing.JMenuItem jmiNafLoad;
    private javax.swing.JMenuItem jmiNouveau;
    private javax.swing.JMenuItem jmiPrintLabels;
    private javax.swing.JMenuItem jmiRefuseMatch;
    private javax.swing.JMenuItem jmiRevisions;
    private javax.swing.JMenuItem jmiSave;
    private javax.swing.JMenuItem jmiSaveAs;
    private javax.swing.JMenuItem jmiSubstitutePlayer;
    private javax.swing.JMenuItem jmiTeamReport;
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
    private static final Logger LOG = Logger.getLogger(MainFrame.class
            .getName());

    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressMonitor.setProgress(progress);
            String message = String.format("%d%%.\n", progress);
            progressMonitor.setNote(message);
            //taskOutput.append(message);
            if (progressMonitor.isCanceled() || task.isDone()) {
                Toolkit.getDefaultToolkit().beep();
                if (progressMonitor.isCanceled()) {
                    task.cancel(true);
                    //taskOutput.append("Task canceled.\n");
                } else {
                    //taskOutput.append("Task completed.\n");
                }
            }
        }
    }
}
