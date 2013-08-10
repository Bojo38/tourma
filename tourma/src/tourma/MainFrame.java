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
import tourma.tableModel.mjtTeams;
import tourma.views.system.jdgRevisions;
import tourma.views.system.jdgAbout;
import tourma.data.Tournament;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import tourma.views.system.jdgOnlineHelp;
import javax.swing.filechooser.FileFilter;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel;
import teamma.data.lrb;
import teamma.views.JdgRoster;
import tourma.data.Clan;
import tourma.data.Criteria;
import tourma.data.Group;
import tourma.data.Match;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Value;
import tourma.tableModel.mjtCriterias;
import tourma.utils.Generation;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class MainFrame extends javax.swing.JFrame {

    Tournament mTournament;
    File mFile = null;
    JPNParamGroup mJpnGroup;

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

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N

        mJpnGroup = new JPNParamGroup();
        jtpOptions.addTab(
                bundle.getString("Group"),
                new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Group.png")), 
                mJpnGroup);

        update();
    }

    protected void updateCriterias(final boolean bTourStarted) {

        jtbCriteria.setModel(new mjtCriterias(mTournament));
        jbtAddCriteria.setEnabled(!bTourStarted);
        jbtRemoveCriteria.setEnabled(!bTourStarted);
    }

    protected void updateRankIndivParameters() {

        jtffDraw.setValue(mTournament.getParams().mPointsIndivDraw);
        jtffLargeVictory.setValue(mTournament.getParams().mPointsIndivLargeVictory);
        jtffLittleLost.setValue(mTournament.getParams().mPointsIndivLittleLost);
        jtffLargeVictoryGap.setValue(mTournament.getParams().mGapLargeVictory);
        jtffLittleLostGap.setValue(mTournament.getParams().mGapLittleLost);
        jtffLost.setValue(mTournament.getParams().mPointsIndivLost);

        jtffVictory.setValue(mTournament.getParams().mPointsIndivVictory);

        final ArrayList<String> rankChoices = new ArrayList<String>();
        rankChoices.add(StringConstants.CS_NONE);
        rankChoices.add(StringConstants.CS_POINTS);
        rankChoices.add("Points Adversaires");
        rankChoices.add("V/N/D");
        for (int i = 0; i < Tournament.getTournament().getParams().mCriterias.size(); i++) {
            final Criteria criteria = Tournament.getTournament().getParams().mCriterias.get(i);
            rankChoices.add(criteria.mName + " Joueur");
            rankChoices.add(criteria.mName + " Adversaire");
            rankChoices.add(criteria.mName + " Différence");
        }

        jcbRank1.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank2.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank3.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank4.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank5.setModel(new DefaultComboBoxModel(rankChoices.toArray()));

        jcbRank1.removeActionListener(jcbRank1.getActionListeners()[0]);
        jcbRank2.removeActionListener(jcbRank2.getActionListeners()[0]);
        jcbRank3.removeActionListener(jcbRank3.getActionListeners()[0]);
        jcbRank4.removeActionListener(jcbRank4.getActionListeners()[0]);
        jcbRank5.removeActionListener(jcbRank5.getActionListeners()[0]);

        jcbRank1.setSelectedIndex(mTournament.getParams().mRankingIndiv1);
        jcbRank2.setSelectedIndex(mTournament.getParams().mRankingIndiv2);
        jcbRank3.setSelectedIndex(mTournament.getParams().mRankingIndiv3);
        jcbRank4.setSelectedIndex(mTournament.getParams().mRankingIndiv4);
        jcbRank5.setSelectedIndex(mTournament.getParams().mRankingIndiv5);



        jcbRank1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank1ActionPerformed(evt);
            }
        });

        jcbRank2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank2ActionPerformed(evt);
            }
        });

        jcbRank3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank3ActionPerformed(evt);
            }
        });

        jcbRank4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank4ActionPerformed(evt);
            }
        });

        jcbRank5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank5ActionPerformed(evt);
            }
        });

    }

    protected void updateRankTeamParameters() {

        final boolean teamMatches = mTournament.getParams().mTeamTournament && (mTournament.getParams().mTeamPairing == 1);

        jtffDrawTeam.setValue(mTournament.getParams().mPointsTeamDraw);
        jtffLostTeam.setValue(mTournament.getParams().mPointsTeamLost);

        jtffVictoryTeam.setValue(mTournament.getParams().mPointsTeamVictory);

        jrbCoachPoints.setEnabled(teamMatches);
        jrbTeamVictory.setEnabled(teamMatches);

        jtffTeamVictoryBonus.setEnabled(teamMatches && (!mTournament.getParams().mTeamVictoryOnly));

        jtffTeamDrawBonus.setEnabled(teamMatches && (!mTournament.getParams().mTeamVictoryOnly));
        jlbVictoryPoints.setEnabled(teamMatches && (!mTournament.getParams().mTeamVictoryOnly));
        jlbVictoryPoints1.setEnabled(teamMatches && (!mTournament.getParams().mTeamVictoryOnly));

        jtffDrawTeam.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jtffLostTeam.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));

        jtffVictoryTeam.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jtffDrawTeam.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));


        jLabel23.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jLabel24.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jLabel25.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jLabel26.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jLabel27.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jLabel28.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jLabel29.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jLabel30.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));

        jrbCoachPoints.setSelected(!mTournament.getParams().mTeamVictoryOnly);
        jrbTeamVictory.setSelected(mTournament.getParams().mTeamVictoryOnly);
        jtffTeamVictoryBonus.setValue(mTournament.getParams().mPointsTeamVictoryBonus);
        jtffTeamDrawBonus.setValue(mTournament.getParams().mPointsTeamDrawBonus);

        final ArrayList<String> rankChoices = new ArrayList<String>();
        rankChoices.add(StringConstants.CS_NONE);
        rankChoices.add(StringConstants.CS_POINTS);
        rankChoices.add("Points Adversaires");
        rankChoices.add("V/N/D");
        for (int i = 0; i < Tournament.getTournament().getParams().mCriterias.size(); i++) {
            final Criteria criteria = Tournament.getTournament().getParams().mCriterias.get(i);
            rankChoices.add(criteria.mName + " Joueur");
            rankChoices.add(criteria.mName + " Adversaire");
            rankChoices.add(criteria.mName + " Différence");
        }


        jcbRank1Team.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank2Team.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank3Team.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank4Team.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank5Team.setModel(new DefaultComboBoxModel(rankChoices.toArray()));

        jcbRank1Team.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jcbRank2Team.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jcbRank3Team.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jcbRank4Team.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));
        jcbRank5Team.setEnabled(teamMatches && (mTournament.getParams().mTeamVictoryOnly));

        jcbRank1Team.removeActionListener(jcbRank1Team.getActionListeners()[0]);
        jcbRank2Team.removeActionListener(jcbRank2Team.getActionListeners()[0]);
        jcbRank3Team.removeActionListener(jcbRank3Team.getActionListeners()[0]);
        jcbRank4Team.removeActionListener(jcbRank4Team.getActionListeners()[0]);
        jcbRank5Team.removeActionListener(jcbRank5Team.getActionListeners()[0]);

        jcbRank1Team.setSelectedIndex(mTournament.getParams().mRankingTeam1);
        jcbRank2Team.setSelectedIndex(mTournament.getParams().mRankingTeam2);
        jcbRank3Team.setSelectedIndex(mTournament.getParams().mRankingTeam3);
        jcbRank4Team.setSelectedIndex(mTournament.getParams().mRankingTeam4);
        jcbRank5Team.setSelectedIndex(mTournament.getParams().mRankingTeam5);

        jcbRank1Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank1TeamActionPerformed(evt);
            }
        });

        jcbRank2Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank2TeamActionPerformed(evt);
            }
        });

        jcbRank3Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank3TeamActionPerformed(evt);
            }
        });

        jcbRank4Team.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(final java.awt.event.ActionEvent evt) {
                        jcbRank4TeamActionPerformed(evt);
                    }
                });

        jcbRank5Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank5TeamActionPerformed(evt);
            }
        });
    }

    protected void updateClans(final boolean bTourStarted) {
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

    protected void updateTables(final boolean bTourStarted) {

        jbtAdd.setEnabled(!bTourStarted);
        jbtRemove.setEnabled(!bTourStarted);

        jbtAddTeam.setEnabled(!bTourStarted);
        jbtRemoveTeam.setEnabled(!bTourStarted);

        if (mTournament.getParams().mTeamTournament) {
            jpnTeamTour.setVisible(true);
            jpnCoachButtons.setVisible(false);
            String text = "";
            if (mTournament.getParams().mTeamPairing == 0) {
                text = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Single");
            } else {
                text = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ByTeam");
            }
            jlbDetails.setText(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("MembersNumber") + ": " + mTournament.getParams().mTeamMatesNumber + " " + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Pairing") + " " + text);
        } else {
            jpnTeamTour.setVisible(false);
            jpnCoachButtons.setVisible(true);
        }

        final mjtCoaches coachModel = new mjtCoaches(mTournament.getCoachs());
        jtbCoachs.setModel(coachModel);
        setColumnSize(jtbCoachs);

        jtbCoachs.setDefaultRenderer(String.class, coachModel);
        jtbCoachs.setDefaultRenderer(Integer.class, coachModel);

        final mjtTeams teamModel = new mjtTeams(mTournament.getTeams());
        jtbTeam.setModel(teamModel);
        setColumnSize(jtbTeam);
        jtbTeam.setDefaultRenderer(String.class, teamModel);
        jtbTeam.setDefaultRenderer(Integer.class, teamModel);
    }

    public void update() {

        final boolean bTourStarted = mTournament.getRounds().size() > 0;

        updateCriterias(bTourStarted);
        updateClans(bTourStarted);
        updateRankIndivParameters();
        updateTables(bTourStarted);

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
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtffLargeVictory = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jtffLargeVictoryGap = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jtffVictory = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jtffDraw = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jtffLittleLost = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jtffLittleLostGap = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jtffLost = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jcbRank1 = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jcbRank2 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jcbRank3 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jcbRank4 = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jcbRank5 = new javax.swing.JComboBox();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jbtAddCriteria = new javax.swing.JButton();
        jbtRemoveCriteria = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtbCriteria = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jrbTeamVictory = new javax.swing.JRadioButton();
        jrbCoachPoints = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        jtffVictoryTeam = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jtffDrawTeam = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        jtffLostTeam = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        jcbRank1Team = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        jcbRank2Team = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        jcbRank3Team = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jcbRank4Team = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jcbRank5Team = new javax.swing.JComboBox();
        jlbVictoryPoints = new javax.swing.JLabel();
        jtffTeamVictoryBonus = new javax.swing.JFormattedTextField();
        jlbVictoryPoints1 = new javax.swing.JLabel();
        jtffTeamDrawBonus = new javax.swing.JFormattedTextField();
        jPanel11 = new javax.swing.JPanel();
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
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jpnCoachButtons = new javax.swing.JPanel();
        jbtAdd = new javax.swing.JButton();
        jbtRemove = new javax.swing.JButton();
        jbtModify = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbCoachs = new javax.swing.JTable();
        jpnTeamTour = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jbtAddTeam = new javax.swing.JButton();
        jbtRemoveTeam = new javax.swing.JButton();
        jbtModifyTeam = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbTeam = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jlbDetails = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jbtFirstRound = new javax.swing.JButton();
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

        jPanel6.setLayout(new java.awt.GridLayout(12, 2));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText(bundle.getString("LargeVictoryKey")); // NOI18N
        jLabel3.setToolTipText(bundle.getString("LargeVictoryTipKey")); // NOI18N
        jPanel6.add(jLabel3);

        jtffLargeVictory.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLargeVictory.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLargeVictoryFocusLost(evt);
            }
        });
        jPanel6.add(jtffLargeVictory);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel19.setText(bundle.getString("MinimumGapForLargeVictoryKey")); // NOI18N
        jLabel19.setToolTipText(bundle.getString("GapForLargeVictoryTipKey")); // NOI18N
        jPanel6.add(jLabel19);

        jtffLargeVictoryGap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLargeVictoryGap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLargeVictoryGapFocusLost(evt);
            }
        });
        jPanel6.add(jtffLargeVictoryGap);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText(bundle.getString("VictoryKey")); // NOI18N
        jLabel4.setToolTipText(bundle.getString("VictoryNumberOfPointsKey")); // NOI18N
        jPanel6.add(jLabel4);

        jtffVictory.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffVictory.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffVictoryFocusLost(evt);
            }
        });
        jPanel6.add(jtffVictory);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText(bundle.getString("DrawKey")); // NOI18N
        jLabel5.setToolTipText(bundle.getString("DrawMatchTipKey")); // NOI18N
        jPanel6.add(jLabel5);

        jtffDraw.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffDraw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffDrawFocusLost(evt);
            }
        });
        jPanel6.add(jtffDraw);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText(bundle.getString("ShortLossKey")); // NOI18N
        jLabel6.setToolTipText(bundle.getString("ShortLossTipKey")); // NOI18N
        jPanel6.add(jLabel6);

        jtffLittleLost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLittleLost.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLittleLostFocusLost(evt);
            }
        });
        jPanel6.add(jtffLittleLost);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setText(bundle.getString("MaximumGapForShortLossKey")); // NOI18N
        jLabel20.setToolTipText(bundle.getString("MaximumGapForShortLossTipKey")); // NOI18N
        jPanel6.add(jLabel20);

        jtffLittleLostGap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLittleLostGap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLittleLostGapFocusLost(evt);
            }
        });
        jPanel6.add(jtffLittleLostGap);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText(bundle.getString("LossKey")); // NOI18N
        jLabel7.setToolTipText(bundle.getString("LossTipKey")); // NOI18N
        jPanel6.add(jLabel7);

        jtffLost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLost.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLostFocusLost(evt);
            }
        });
        jPanel6.add(jtffLost);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RankingCriteria")+" 1:");
        jPanel6.add(jLabel14);

        jcbRank1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite", "Passes", "Interceptions", "Différence de passes", "Différence d'interceptions" }));
        jcbRank1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank1ActionPerformed(evt);
            }
        });
        jPanel6.add(jcbRank1);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RankingCriteria")+" 2:");
        jPanel6.add(jLabel15);

        jcbRank2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite", "Passes", "Interceptions", "Différence de passes", "Différence d'interceptions" }));
        jcbRank2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank2ActionPerformed(evt);
            }
        });
        jPanel6.add(jcbRank2);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RankingCriteria")+" 3:");
        jPanel6.add(jLabel16);

        jcbRank3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite", "Passes", "Interceptions", "Différence de passes", "Différence d'interceptions" }));
        jcbRank3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank3ActionPerformed(evt);
            }
        });
        jPanel6.add(jcbRank3);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RankingCriteria")+" 4:");
        jPanel6.add(jLabel17);

        jcbRank4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite", "Passes", "Interceptions", "Différence de passes", "Différence d'interceptions" }));
        jcbRank4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank4ActionPerformed(evt);
            }
        });
        jPanel6.add(jcbRank4);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RankingCriteria")+" 5:");
        jPanel6.add(jLabel18);

        jcbRank5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite", "Passes", "Interceptions", "Différence de passes", "Différence d'interceptions" }));
        jcbRank5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank5ActionPerformed(evt);
            }
        });
        jPanel6.add(jcbRank5);

        jtpOptions.addTab(bundle.getString("IndividualKey"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/User.png")), jPanel6); // NOI18N

        jPanel15.setLayout(new java.awt.BorderLayout());

        jbtAddCriteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAddCriteria.setText("Ajouter Critère");
        jbtAddCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddCriteriaActionPerformed(evt);
            }
        });
        jPanel16.add(jbtAddCriteria);

        jbtRemoveCriteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemoveCriteria.setText("Retirer critère");
        jbtRemoveCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveCriteriaActionPerformed(evt);
            }
        });
        jPanel16.add(jbtRemoveCriteria);

        jPanel15.add(jPanel16, java.awt.BorderLayout.PAGE_START);

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

        jPanel15.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jtpOptions.addTab("Critères", new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Tools.png")), jPanel15); // NOI18N

        jPanel10.setLayout(new java.awt.GridLayout(11, 2));

        jrbTeamVictory.setText(bundle.getString("UseTeamVictory")); // NOI18N
        jrbTeamVictory.setHideActionText(true);
        jrbTeamVictory.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jrbTeamVictory.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jrbTeamVictory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTeamVictoryActionPerformed(evt);
            }
        });
        jPanel10.add(jrbTeamVictory);

        jrbCoachPoints.setText(bundle.getString("UseCoachsPointsSum")); // NOI18N
        jrbCoachPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCoachPointsActionPerformed(evt);
            }
        });
        jPanel10.add(jrbCoachPoints);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel23.setText(bundle.getString("Victory")); // NOI18N
        jLabel23.setToolTipText(bundle.getString("VictoryNumberOfPointsKey")); // NOI18N
        jPanel10.add(jLabel23);

        jtffVictoryTeam.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffVictoryTeam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffVictoryTeamFocusLost(evt);
            }
        });
        jPanel10.add(jtffVictoryTeam);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel24.setText(bundle.getString("Draw")); // NOI18N
        jLabel24.setToolTipText(bundle.getString("DrawMatchTipKey")); // NOI18N
        jPanel10.add(jLabel24);

        jtffDrawTeam.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffDrawTeam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffDrawTeamFocusLost(evt);
            }
        });
        jPanel10.add(jtffDrawTeam);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel25.setText(bundle.getString("Loss")); // NOI18N
        jLabel25.setToolTipText(bundle.getString("LossTipKey")); // NOI18N
        jPanel10.add(jLabel25);

        jtffLostTeam.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLostTeam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLostTeamFocusLost(evt);
            }
        });
        jPanel10.add(jtffLostTeam);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel26.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RankingCriteria")+" 1:");
        jLabel26.setToolTipText("");
        jPanel10.add(jLabel26);

        jcbRank1Team.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
        jcbRank1Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank1TeamActionPerformed(evt);
            }
        });
        jPanel10.add(jcbRank1Team);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel27.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RankingCriteria")+" 2:");
        jLabel27.setToolTipText("");
        jPanel10.add(jLabel27);

        jcbRank2Team.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
        jcbRank2Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank2TeamActionPerformed(evt);
            }
        });
        jPanel10.add(jcbRank2Team);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel28.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RankingCriteria")+" 3:");
        jLabel28.setToolTipText("");
        jPanel10.add(jLabel28);

        jcbRank3Team.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
        jcbRank3Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank3TeamActionPerformed(evt);
            }
        });
        jPanel10.add(jcbRank3Team);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel29.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RankingCriteria")+" 4:");
        jLabel29.setToolTipText("");
        jPanel10.add(jLabel29);

        jcbRank4Team.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
        jcbRank4Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank4TeamActionPerformed(evt);
            }
        });
        jPanel10.add(jcbRank4Team);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel30.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RankingCriteria")+" 5:");
        jLabel30.setToolTipText("");
        jPanel10.add(jLabel30);

        jcbRank5Team.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
        jcbRank5Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank5TeamActionPerformed(evt);
            }
        });
        jPanel10.add(jcbRank5Team);

        jlbVictoryPoints.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbVictoryPoints.setText(bundle.getString("TeamVictoryBonus")); // NOI18N
        jlbVictoryPoints.setToolTipText("");
        jPanel10.add(jlbVictoryPoints);

        jtffTeamVictoryBonus.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffTeamVictoryBonus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffTeamVictoryBonusFocusLost(evt);
            }
        });
        jPanel10.add(jtffTeamVictoryBonus);

        jlbVictoryPoints1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbVictoryPoints1.setText(bundle.getString("TeamDrawBonus")); // NOI18N
        jlbVictoryPoints1.setToolTipText("");
        jPanel10.add(jlbVictoryPoints1);

        jtffTeamDrawBonus.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffTeamDrawBonus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffTeamDrawBonusFocusLost(evt);
            }
        });
        jPanel10.add(jtffTeamDrawBonus);

        jtpOptions.addTab(bundle.getString("ByTeamKey"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Team.png")), jPanel10); // NOI18N

        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.GridLayout(4, 2));

        jlbActivateClans.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
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

        jPanel11.add(jPanel12, java.awt.BorderLayout.NORTH);

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

        jPanel11.add(jPanel13, java.awt.BorderLayout.SOUTH);

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

        jPanel11.add(jPanel14, java.awt.BorderLayout.CENTER);

        jtpOptions.addTab(bundle.getString("ClanKey"), new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Clan.png")), jPanel11); // NOI18N

        jPanel9.add(jtpOptions, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel9, java.awt.BorderLayout.CENTER);

        jpnParameters.add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

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

        jPanel2.add(jPanel7, java.awt.BorderLayout.CENTER);

        jpnTeamTour.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipes"));
        jpnTeamTour.setPreferredSize(new java.awt.Dimension(450, 240));
        jpnTeamTour.setLayout(new java.awt.BorderLayout());

        jbtAddTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAddTeam.setText(bundle.getString("Add")); // NOI18N
        jbtAddTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtAddTeam);

        jbtRemoveTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemoveTeam.setText(bundle.getString("Remove")); // NOI18N
        jbtRemoveTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtRemoveTeam);

        jbtModifyTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Swap.png"))); // NOI18N
        jbtModifyTeam.setText(bundle.getString("Modify")); // NOI18N
        jbtModifyTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtModifyTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtModifyTeam);

        jpnTeamTour.add(jPanel8, java.awt.BorderLayout.NORTH);

        jtbTeam.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbTeam.setCellSelectionEnabled(true);
        jtbTeam.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jtbTeam);

        jpnTeamTour.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.add(jlbDetails);

        jpnTeamTour.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jpnTeamTour, java.awt.BorderLayout.NORTH);

        jbtFirstRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Generate.png"))); // NOI18N
        jbtFirstRound.setText(bundle.getString("GenerateFirstRound")); // NOI18N
        jbtFirstRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFirstRoundActionPerformed(evt);
            }
        });
        jPanel4.add(jbtFirstRound);

        jPanel2.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jpnParameters.add(jPanel2);

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
    private void jbtFirstRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFirstRoundActionPerformed

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
}//GEN-LAST:event_jbtFirstRoundActionPerformed
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
    private void jcbRank5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank5ActionPerformed
        mTournament.getParams().mRankingIndiv5 = jcbRank5.getSelectedIndex();
        update();
}//GEN-LAST:event_jcbRank5ActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank4ActionPerformed
        mTournament.getParams().mRankingIndiv4 = jcbRank4.getSelectedIndex();
        update();
}//GEN-LAST:event_jcbRank4ActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank3ActionPerformed
        mTournament.getParams().mRankingIndiv3 = jcbRank3.getSelectedIndex();
        update();
}//GEN-LAST:event_jcbRank3ActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank2ActionPerformed
        mTournament.getParams().mRankingIndiv2 = jcbRank2.getSelectedIndex();
        update();
}//GEN-LAST:event_jcbRank2ActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank1ActionPerformed
        mTournament.getParams().mRankingIndiv1 = jcbRank1.getSelectedIndex();
        update();
}//GEN-LAST:event_jcbRank1ActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffLostFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLostFocusLost
        try {
            jtffLost.commitEdit();
            final int points = ((Long) jtffLost.getValue()).intValue();
            mTournament.getParams().mPointsIndivLost = points;
        } catch (ParseException e) {
            jtffLost.setValue(jtffLost.getValue());
        }
        update();
}//GEN-LAST:event_jtffLostFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffLittleLostFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLittleLostFocusLost
        try {
            jtffLittleLost.commitEdit();
            final int points = ((Long) jtffLittleLost.getValue()).intValue();
            mTournament.getParams().mPointsIndivLittleLost = points;
        } catch (ParseException e) {
            jtffLittleLost.setValue(jtffLittleLost.getValue());
        }
        update();
}//GEN-LAST:event_jtffLittleLostFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffDrawFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffDrawFocusLost
        try {
            jtffDraw.commitEdit();
            final int points = ((Long) jtffDraw.getValue()).intValue();
            mTournament.getParams().mPointsIndivDraw = points;
        } catch (ParseException e) {
            jtffDraw.setValue(jtffDraw.getValue());
        }
        update();
}//GEN-LAST:event_jtffDrawFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffVictoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffVictoryFocusLost
        try {
            jtffVictory.commitEdit();
            final int points = ((Long) jtffVictory.getValue()).intValue();
            mTournament.getParams().mPointsIndivVictory = points;
        } catch (ParseException e) {
            jtffVictory.setValue(jtffVictory.getValue());
        }
        update();
}//GEN-LAST:event_jtffVictoryFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffLargeVictoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLargeVictoryFocusLost

        try {
            jtffLargeVictory.commitEdit();
            final int points = ((Long) jtffLargeVictory.getValue()).intValue();
            mTournament.getParams().mPointsIndivLargeVictory = points;
        } catch (ParseException e) {
            jtffLargeVictory.setValue(jtffLargeVictory.getValue());
        }
        update();
}//GEN-LAST:event_jtffLargeVictoryFocusLost
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
    private void jtffLargeVictoryGapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLargeVictoryGapFocusLost
        try {
            jtffLargeVictoryGap.commitEdit();
            final int points = ((Long) jtffLargeVictoryGap.getValue()).intValue();
            mTournament.getParams().mGapLargeVictory = points;
        } catch (ParseException e) {
            jtffLargeVictoryGap.setValue(jtffLargeVictoryGap.getValue());
        }

        update();
    }//GEN-LAST:event_jtffLargeVictoryGapFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffLittleLostGapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLittleLostGapFocusLost
        try {
            jtffLittleLostGap.commitEdit();
            final int points = ((Long) jtffLittleLostGap.getValue()).intValue();
            mTournament.getParams().mGapLittleLost = points;
        } catch (ParseException e) {
            jtffLittleLostGap.setValue(jtffLittleLostGap.getValue());
        }

        update();
    }//GEN-LAST:event_jtffLittleLostGapFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddTeamActionPerformed

        final jdgTeam jdg = new jdgTeam(this, true);
        jdg.setVisible(true);

        update();
    }//GEN-LAST:event_jbtAddTeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveTeamActionPerformed
        final Team t = mTournament.getTeams().get(jtbTeam.getSelectedRow());
        for (int i = 0; i < t.mCoachs.size(); i++) {
            mTournament.getCoachs().remove(t.mCoachs.get(i));
        }
        t.mCoachs.clear();
        mTournament.getTeams().remove(t);
        update();
    }//GEN-LAST:event_jbtRemoveTeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtModifyTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModifyTeamActionPerformed
        if (mTournament.getTeams().size() > jtbTeam.getSelectedRow()) {
            final Team t = mTournament.getTeams().get(jtbTeam.getSelectedRow());
            if (jtbTeam.getSelectedColumn() == 1) {
                final String name = JOptionPane.showInputDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("EnterTeamName"), t.mName);
                t.mName = name;
            } else if (jtbTeam.getSelectedColumn() > 1) {
                final jdgCoach jdg = new jdgCoach(this, true, t.mCoachs.get(jtbTeam.getSelectedColumn() - 2));
                jdg.setVisible(true);
            }
            update();
        }
    }//GEN-LAST:event_jbtModifyTeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffTeamVictoryBonusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffTeamVictoryBonusFocusLost
        try {
            jtffTeamVictoryBonus.commitEdit();
            final int points = ((Long) jtffTeamVictoryBonus.getValue()).intValue();
            mTournament.getParams().mPointsTeamVictoryBonus = points;
        } catch (ParseException e) {
            jtffTeamVictoryBonus.setValue(jtffTeamVictoryBonus.getValue());
        }
        update();
    }//GEN-LAST:event_jtffTeamVictoryBonusFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jrbTeamVictoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbTeamVictoryActionPerformed
        mTournament.getParams().mTeamVictoryOnly = jrbTeamVictory.isSelected();
        update();
    }//GEN-LAST:event_jrbTeamVictoryActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jrbCoachPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCoachPointsActionPerformed
        mTournament.getParams().mTeamVictoryOnly = !jrbCoachPoints.isSelected();
        update();
    }//GEN-LAST:event_jrbCoachPointsActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffVictoryTeamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffVictoryTeamFocusLost
        try {
            jtffVictoryTeam.commitEdit();
            final int points = ((Long) jtffVictoryTeam.getValue()).intValue();
            mTournament.getParams().mPointsTeamVictory = points;
        } catch (ParseException e) {
            jtffVictoryTeam.setValue(jtffVictoryTeam.getValue());
        }
        update();
    }//GEN-LAST:event_jtffVictoryTeamFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffDrawTeamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffDrawTeamFocusLost
        try {
            jtffDrawTeam.commitEdit();
            final int points = ((Long) jtffDrawTeam.getValue()).intValue();
            mTournament.getParams().mPointsTeamDraw = points;
        } catch (ParseException e) {
            jtffDrawTeam.setValue(jtffDrawTeam.getValue());
        }
        update();
    }//GEN-LAST:event_jtffDrawTeamFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffLostTeamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLostTeamFocusLost
        try {
            jtffLostTeam.commitEdit();
            final int points = ((Long) jtffLostTeam.getValue()).intValue();
            mTournament.getParams().mPointsTeamLost = points;
        } catch (ParseException e) {
            jtffLostTeam.setValue(jtffLostTeam.getValue());
        }
        update();
    }//GEN-LAST:event_jtffLostTeamFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank1TeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank1TeamActionPerformed
        mTournament.getParams().mRankingTeam1 = jcbRank1Team.getSelectedIndex();
        update();
    }//GEN-LAST:event_jcbRank1TeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank2TeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank2TeamActionPerformed
        mTournament.getParams().mRankingTeam2 = jcbRank2Team.getSelectedIndex();
        update();
    }//GEN-LAST:event_jcbRank2TeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank3TeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank3TeamActionPerformed
        mTournament.getParams().mRankingTeam3 = jcbRank3Team.getSelectedIndex();
        update();
    }//GEN-LAST:event_jcbRank3TeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank4TeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank4TeamActionPerformed
        mTournament.getParams().mRankingTeam4 = jcbRank4Team.getSelectedIndex();
        update();
    }//GEN-LAST:event_jcbRank4TeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank5TeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank5TeamActionPerformed
        mTournament.getParams().mRankingTeam5 = jcbRank5Team.getSelectedIndex();
        update();
    }//GEN-LAST:event_jcbRank5TeamActionPerformed
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

    /**
     * Press Add CLan name callback
     *
     * @param evt not used
     */
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddClanActionPerformed

        final String enterClanName = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("EnterClanNameKey");
        final String clanName = JOptionPane.showInputDialog(this, enterClanName);
        if (clanName != null) {
            if (!clanName.equals("")) {
                mTournament.getClans().add(new Clan(clanName));
            }
        }
        update();
    }//GEN-LAST:event_jbtAddClanActionPerformed

    /**
     * Press Edit clan name callback
     *
     * @param evt not used
     */
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtEditClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditClanActionPerformed
        final String enterClanName = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("EnterClanNameKey");
        final String clanName = (String) jlsClans.getSelectedValue();
        final String newClanName = JOptionPane.showInputDialog(this, enterClanName, clanName);
        if (!clanName.equals("")) {
            mTournament.getClans().get(jlsClans.getSelectedIndex()).mName = newClanName;
        }
        update();
    }//GEN-LAST:event_jbtEditClanActionPerformed

    /**
     * Press Remove clan button callback
     *
     * @param evt Not used
     */
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
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveCriteriaActionPerformed
        if ((jtbCriteria.getSelectedRow() > 1) && (jtbCriteria.getSelectedRow() < mTournament.getParams().mCriterias.size())) {
            final Criteria crit = mTournament.getParams().mCriterias.get(jtbCriteria.getSelectedRow());
            for (int i = 0; i < mTournament.getRounds().size(); i++) {
                final Round r = mTournament.getRounds().get(i);
                for (int j = 0; j < r.getMatchs().size(); j++) {
                    final Match m = r.getMatchs().get(j);
                    m.mValues.remove(crit);
                }
            }
            mTournament.getParams().mCriterias.remove(jtbCriteria.getSelectedRow());
        }
        repaint();
    }//GEN-LAST:event_jbtRemoveCriteriaActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddCriteriaActionPerformed
        final int nb = Tournament.getTournament().getParams().mCriterias.size();
        final Criteria c = new Criteria("Critère " + Integer.toString(nb));
        Tournament.getTournament().getParams().mCriterias.add(c);
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            final Round r = mTournament.getRounds().get(i);
            for (int j = 0; j < r.getMatchs().size(); j++) {
                final Match m = r.getMatchs().get(j);
                m.mValues.put(c, new Value(c));
            }
        }

        update();
    }//GEN-LAST:event_jbtAddCriteriaActionPerformed
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
    private void jtffTeamDrawBonusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffTeamDrawBonusFocusLost
        try {
            jtffTeamDrawBonus.commitEdit();
            final int points = ((Long) jtffTeamDrawBonus.getValue()).intValue();
            mTournament.getParams().mPointsTeamDrawBonus = points;
        } catch (ParseException e) {
            jtffTeamDrawBonus.setValue(jtffTeamDrawBonus.getValue());
        }
        update();
    }//GEN-LAST:event_jtffTeamDrawBonusFocusLost
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

    public void setColumnSize(final JTable t) {
        final FontMetrics fm = t.getFontMetrics(t.getFont());
        for (int i = 0; i
                < t.getColumnCount(); i++) {
            int max = 0;
            for (int j = 0; j
                    < t.getRowCount(); j++) {
                final Object value = t.getValueAt(j, i);
                String tmp = "";
                if (value instanceof String) {
                    tmp = (String) value;
                }

                if (value instanceof Integer) {
                    tmp = ((Integer) value).toString();
                }

                final int taille = fm.stringWidth(tmp);
                if (taille > max) {
                    max = taille;
                }

            }
            final String nom = (String) t.getColumnModel().getColumn(i).getIdentifier();
            final int taille = fm.stringWidth(nom);
            if (taille > max) {
                max = taille;
            }

            t.getColumnModel().getColumn(i).setPreferredWidth(max + 10);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                JFrame.setDefaultLookAndFeelDecorated(true);
                try {
                    final SubstanceLookAndFeel lf = new SubstanceMistSilverLookAndFeel();
                    UIManager.setLookAndFeel(lf);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }

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
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtAddClan;
    private javax.swing.JButton jbtAddCriteria;
    private javax.swing.JButton jbtAddTeam;
    private javax.swing.JButton jbtEditClan;
    private javax.swing.JButton jbtFirstRound;
    private javax.swing.JButton jbtModify;
    private javax.swing.JButton jbtModifyTeam;
    private javax.swing.JButton jbtRemove;
    private javax.swing.JButton jbtRemoveClan;
    private javax.swing.JButton jbtRemoveCriteria;
    private javax.swing.JButton jbtRemoveTeam;
    private javax.swing.JComboBox jcbRank1;
    private javax.swing.JComboBox jcbRank1Team;
    private javax.swing.JComboBox jcbRank2;
    private javax.swing.JComboBox jcbRank2Team;
    private javax.swing.JComboBox jcbRank3;
    private javax.swing.JComboBox jcbRank3Team;
    private javax.swing.JComboBox jcbRank4;
    private javax.swing.JComboBox jcbRank4Team;
    private javax.swing.JComboBox jcbRank5;
    private javax.swing.JComboBox jcbRank5Team;
    private javax.swing.JCheckBox jcxActivatesClans;
    private javax.swing.JCheckBoxMenuItem jcxAllowSpecialSkill;
    private javax.swing.JCheckBox jcxAvoidFirstMatch;
    private javax.swing.JCheckBox jcxAvoidMatch;
    private javax.swing.JLabel jlbActivateClans;
    private javax.swing.JLabel jlbAvoidClansMembersMatch;
    private javax.swing.JLabel jlbClansMembersNUmbers;
    private javax.swing.JLabel jlbDetails;
    private javax.swing.JLabel jlbTeamMatesNumber;
    private javax.swing.JLabel jlbVictoryPoints;
    private javax.swing.JLabel jlbVictoryPoints1;
    private javax.swing.JList jlsClans;
    private javax.swing.JList jlsCoachList;
    private javax.swing.JMenuItem jmiAbout;
    private javax.swing.JMenuItem jmiAideEnLigne;
    private javax.swing.JMenuItem jmiCharger;
    private javax.swing.JMenuItem jmiEditTeam;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiExport;
    private javax.swing.JMenuItem jmiExportFbb;
    private javax.swing.JMenuItem jmiExportFbb1;
    private javax.swing.JMenuItem jmiNouveau;
    private javax.swing.JMenuItem jmiRevisions;
    private javax.swing.JMenuItem jmiSave;
    private javax.swing.JMenuItem jmiSaveAs;
    private javax.swing.JMenu jmnFile;
    private javax.swing.JMenu jmnHelp;
    private javax.swing.JMenu jmnParameters;
    private javax.swing.JPanel jpnCoachButtons;
    private javax.swing.JPanel jpnParameters;
    private javax.swing.JPanel jpnTeamTour;
    private javax.swing.JRadioButton jrbCoachPoints;
    private javax.swing.JRadioButton jrbTeamVictory;
    private javax.swing.JSpinner jspTeamMembers;
    private javax.swing.JTable jtbCoachs;
    private javax.swing.JTable jtbCriteria;
    private javax.swing.JTable jtbTeam;
    private javax.swing.JTextField jtfOrgas;
    private javax.swing.JTextField jtfPlace;
    private javax.swing.JTextField jtfTournamentName;
    private javax.swing.JFormattedTextField jtffDraw;
    private javax.swing.JFormattedTextField jtffDrawTeam;
    private javax.swing.JFormattedTextField jtffLargeVictory;
    private javax.swing.JFormattedTextField jtffLargeVictoryGap;
    private javax.swing.JFormattedTextField jtffLittleLost;
    private javax.swing.JFormattedTextField jtffLittleLostGap;
    private javax.swing.JFormattedTextField jtffLost;
    private javax.swing.JFormattedTextField jtffLostTeam;
    private javax.swing.JFormattedTextField jtffTeamDrawBonus;
    private javax.swing.JFormattedTextField jtffTeamVictoryBonus;
    private javax.swing.JFormattedTextField jtffVictory;
    private javax.swing.JFormattedTextField jtffVictoryTeam;
    public javax.swing.JTabbedPane jtpMain;
    private javax.swing.JTabbedPane jtpOptions;
    private org.jdesktop.swingx.JXDatePicker jxdDate;
    // End of variables declaration//GEN-END:variables
}
