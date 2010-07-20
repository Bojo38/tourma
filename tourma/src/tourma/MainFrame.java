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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel;
import tourma.views.system.jdgOnlineHelp;
import javax.swing.filechooser.FileFilter;
import tourma.data.Coach;
import tourma.data.Team;

/**
 *
 * @author Frederic Berger
 */
public class MainFrame extends javax.swing.JFrame {

    Tournament _tournament;
    File file = null;

    /** Creates new form MainFrame */
    public MainFrame() {
        _tournament = Tournament.getTournament();
        this.setSize(800, 600);
        initComponents();

        update();

    }

    public void update() {

        if (_tournament.getParams()._teamTournament) {
            jpnTeamTour.setVisible(true);
            jpnCoachButtons.setVisible(false);
            String text = "";
            if (_tournament.getParams()._teamPairing == 0) {
                text = "individuel";
            } else {
                text = "par équipe";
            }
            jlbDetails.setText("Nombre de membres: " + _tournament.getParams()._teamMatesNumber + " Appariement " + text);
        } else {
            jpnTeamTour.setVisible(false);
            jpnCoachButtons.setVisible(true);
        }

        boolean teamMatches = _tournament.getParams()._teamTournament && (_tournament.getParams()._teamPairing == 1);
        jrbCoachPoints.setEnabled(teamMatches);
        jrbTeamVictory.setEnabled(teamMatches);

        jtffTeamVictory.setEnabled(teamMatches && (!_tournament.getParams()._team_victory_only));
        jlbVictoryPoints.setEnabled(teamMatches && (!_tournament.getParams()._team_victory_only));

        jrbCoachPoints.setSelected(!_tournament.getParams()._team_victory_only);
        jrbTeamVictory.setSelected(_tournament.getParams()._team_victory_only);
        jtffTeamVictory.setValue(_tournament.getParams()._team_victory_points);

        jtfOrgas.setText(_tournament.getParams()._tournament_orga);
        jtfTournamentName.setText(_tournament.getParams()._tournament_name);
        jtffDraw.setValue(new Integer(_tournament.getParams()._draw_points));
        jtffFoulNeg.setValue(new Integer(_tournament.getParams()._bonus_neg_foul_points));
        jtffFoulPos.setValue(new Integer(_tournament.getParams()._bonus_foul_points));
        jtffLargeVictory.setValue(new Integer(_tournament.getParams()._large_victory_points));
        jtffLittleLost.setValue(new Integer(_tournament.getParams()._little_lost_points));
        jtffLargeVictoryGap.setValue(new Integer(_tournament.getParams()._large_victory_gap));
        jtffLittleLostGap.setValue(new Integer(_tournament.getParams()._little_lost_gap));
        jtffLost.setValue(new Integer(_tournament.getParams()._lost_points));
        jtffSorNeg.setValue(new Integer(_tournament.getParams()._bonus_neg_sor_points));
        jtffSorPos.setValue(new Integer(_tournament.getParams()._bonus_sor_points));
        jtffTdNeg.setValue(new Integer(_tournament.getParams()._bonus_neg_td_points));
        jtffTdPos.setValue(new Integer(_tournament.getParams()._bonus_td_points));
        jtffVictory.setValue(new Integer(_tournament.getParams()._victory_points));

        jcbRank1.removeActionListener(jcbRank1.getActionListeners()[0]);
        jcbRank2.removeActionListener(jcbRank2.getActionListeners()[0]);
        jcbRank3.removeActionListener(jcbRank3.getActionListeners()[0]);
        jcbRank4.removeActionListener(jcbRank4.getActionListeners()[0]);
        jcbRank5.removeActionListener(jcbRank5.getActionListeners()[0]);

        jcbRank1.setSelectedIndex(_tournament.getParams()._ranking1);
        jcbRank2.setSelectedIndex(_tournament.getParams()._ranking2);
        jcbRank3.setSelectedIndex(_tournament.getParams()._ranking3);
        jcbRank4.setSelectedIndex(_tournament.getParams()._ranking4);
        jcbRank5.setSelectedIndex(_tournament.getParams()._ranking5);

        jcbRank1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank1ActionPerformed(evt);
            }
        });


        jcbRank2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank2ActionPerformed(evt);
            }
        });



        jcbRank3.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank3ActionPerformed(evt);
            }
        });

        jcbRank4.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank4ActionPerformed(evt);
            }
        });

        jcbRank5.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank5ActionPerformed(evt);
            }
        });
        jxdDate.setDate(_tournament.getParams()._date);
        jtfPlace.setText(_tournament.getParams()._place);

        mjtCoaches coachModel = new mjtCoaches(_tournament.getCoachs());
        jtbCoachs.setModel(coachModel);
        setColumnSize(jtbCoachs);

        mjtTeams teamModel = new mjtTeams(_tournament.getTeams());
        jtbTeam.setModel(teamModel);
        setColumnSize(jtbTeam);

        for (int i = 0; i < jtpMain.getComponentCount(); i++) {
            Object o = jtpMain.getComponentAt(i);
            if (o instanceof JPNRound) {
                ((JPNRound) o).update();
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jLabel8 = new javax.swing.JLabel();
        jtffTdPos = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jtffSorPos = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jtffFoulPos = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jtffTdNeg = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jtffSorNeg = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jtffFoulNeg = new javax.swing.JFormattedTextField();
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
        jlbVictoryPoints = new javax.swing.JLabel();
        jtffTeamVictory = new javax.swing.JFormattedTextField();
        jrbTeamVictory = new javax.swing.JRadioButton();
        jrbCoachPoints = new javax.swing.JRadioButton();
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
        jSeparator2 = new javax.swing.JSeparator();
        jmiExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmnHelp = new javax.swing.JMenu();
        jmiAbout = new javax.swing.JMenuItem();
        jmiRevisions = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jmiAideEnLigne = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TOURnoi MAnager");
        setIconImage((Toolkit.getDefaultToolkit().getImage(
            MainFrame.class.getResource("images/icone.png"))));

jpnParameters.setLayout(new java.awt.GridLayout(1, 2));

jPanel1.setLayout(new java.awt.BorderLayout());

jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tournoi"));
jPanel5.setLayout(new java.awt.GridLayout(4, 2));

jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
jLabel1.setText("Nom du tournoi:");
jPanel5.add(jLabel1);

jtfTournamentName.setText("Ain Pacte");
jtfTournamentName.addKeyListener(new java.awt.event.KeyAdapter() {
    public void keyPressed(java.awt.event.KeyEvent evt) {
        jtfTournamentNameKeyPressed(evt);
    }
    });
    jPanel5.add(jtfTournamentName);

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel2.setText("Organisateurs:");
    jPanel5.add(jLabel2);

    jtfOrgas.setText("Ain Pacte Team");
    jtfOrgas.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            jtfOrgasKeyPressed(evt);
        }
    });
    jPanel5.add(jtfOrgas);

    jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel21.setText("Lieu:");
    jPanel5.add(jLabel21);

    jtfPlace.setText("Ain Pacte");
    jtfPlace.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            jtfPlaceKeyPressed(evt);
        }
    });
    jPanel5.add(jtfPlace);

    jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel22.setText("Date:");
    jPanel5.add(jLabel22);
    jPanel5.add(jxdDate);

    jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_START);

    jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Classement"));
    jPanel6.setLayout(new java.awt.GridLayout(20, 2));

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel3.setText("Large victoire:");
    jLabel3.setToolTipText("Nombre points pour unelarge victoire (2 Touchdowns d'écarts):");
    jPanel6.add(jLabel3);

    jtffLargeVictory.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffLargeVictory.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffLargeVictoryFocusLost(evt);
        }
    });
    jPanel6.add(jtffLargeVictory);

    jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel19.setText("Ecart minimum pour une large victoire:");
    jLabel19.setToolTipText("Nombre points pour unelarge victoire (2 Touchdowns d'écarts):");
    jPanel6.add(jLabel19);

    jtffLargeVictoryGap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffLargeVictoryGap.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffLargeVictoryGapFocusLost(evt);
        }
    });
    jPanel6.add(jtffLargeVictoryGap);

    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel4.setText("Victoire:");
    jLabel4.setToolTipText("Nombre points pour une victoire");
    jPanel6.add(jLabel4);

    jtffVictory.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffVictory.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffVictoryFocusLost(evt);
        }
    });
    jPanel6.add(jtffVictory);

    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel5.setText("Match nul:");
    jLabel5.setToolTipText("Nombre de points pour un match nul");
    jPanel6.add(jLabel5);

    jtffDraw.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffDraw.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffDrawFocusLost(evt);
        }
    });
    jPanel6.add(jtffDraw);

    jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel6.setText("Petite défaite:");
    jLabel6.setToolTipText("Nombre de points pour une petite défaite (par un Touchdown d'écart)");
    jPanel6.add(jLabel6);

    jtffLittleLost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffLittleLost.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffLittleLostFocusLost(evt);
        }
    });
    jPanel6.add(jtffLittleLost);

    jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel20.setText("Ecart maximum pour une petite défaite:");
    jLabel20.setToolTipText("Nombre de points pour une petite défaite (par un Touchdown d'écart)");
    jPanel6.add(jLabel20);

    jtffLittleLostGap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffLittleLostGap.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffLittleLostGapFocusLost(evt);
        }
    });
    jPanel6.add(jtffLittleLostGap);

    jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel7.setText("Défaite:");
    jLabel7.setToolTipText("Nombre de points pour une petite défaite:");
    jPanel6.add(jLabel7);

    jtffLost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffLost.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffLostFocusLost(evt);
        }
    });
    jPanel6.add(jtffLost);

    jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel8.setText("Touchdowns marqués:");
    jLabel8.setToolTipText("Nombre de points pour un touchdown");
    jPanel6.add(jLabel8);

    jtffTdPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffTdPos.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffTdPosFocusLost(evt);
        }
    });
    jPanel6.add(jtffTdPos);

    jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel9.setText("Sorties réalisées:");
    jLabel9.setToolTipText("Nombre de points pour une sortie réalisée");
    jPanel6.add(jLabel9);

    jtffSorPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffSorPos.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffSorPosFocusLost(evt);
        }
    });
    jPanel6.add(jtffSorPos);

    jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel10.setText("Aggressions réussies:");
    jLabel10.setToolTipText("Nombre de points pour une sortie réussie sur aggression");
    jPanel6.add(jLabel10);

    jtffFoulPos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffFoulPos.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffFoulPosFocusLost(evt);
        }
    });
    jPanel6.add(jtffFoulPos);

    jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel11.setText("Touchdowns encaissés:");
    jLabel11.setToolTipText("Nombre de points (éventuellement négatifs) pour les touchdowns encaissés");
    jPanel6.add(jLabel11);

    jtffTdNeg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffTdNeg.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffTdNegFocusLost(evt);
        }
    });
    jPanel6.add(jtffTdNeg);

    jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel12.setText("Sorties subies:");
    jLabel12.setToolTipText("Nombre de points (éventuellement négatif) pour les sorties subies");
    jPanel6.add(jLabel12);

    jtffSorNeg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffSorNeg.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffSorNegFocusLost(evt);
        }
    });
    jPanel6.add(jtffSorNeg);

    jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel13.setText("Aggressions subies:");
    jLabel13.setToolTipText("Nombre de points (éventuellement négatif) pour les sorties subies sur aggression");
    jPanel6.add(jLabel13);

    jtffFoulNeg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffFoulNeg.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffFoulNegFocusLost(evt);
        }
    });
    jPanel6.add(jtffFoulNeg);

    jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel14.setText("Critère de classement n°1:");
    jPanel6.add(jLabel14);

    jcbRank1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
    jcbRank1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jcbRank1ActionPerformed(evt);
        }
    });
    jPanel6.add(jcbRank1);

    jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel15.setText("Critère de classement n°2:");
    jPanel6.add(jLabel15);

    jcbRank2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
    jcbRank2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jcbRank2ActionPerformed(evt);
        }
    });
    jPanel6.add(jcbRank2);

    jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel16.setText("Critère de classement n°3:");
    jPanel6.add(jLabel16);

    jcbRank3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
    jcbRank3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jcbRank3ActionPerformed(evt);
        }
    });
    jPanel6.add(jcbRank3);

    jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel17.setText("Critère de classement n°4:");
    jPanel6.add(jLabel17);

    jcbRank4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
    jcbRank4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jcbRank4ActionPerformed(evt);
        }
    });
    jPanel6.add(jcbRank4);

    jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel18.setText("Critère de classement n°5:");
    jPanel6.add(jLabel18);

    jcbRank5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
    jcbRank5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jcbRank5ActionPerformed(evt);
        }
    });
    jPanel6.add(jcbRank5);

    jlbVictoryPoints.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jlbVictoryPoints.setText("Prime à la victoire d'équipe");
    jPanel6.add(jlbVictoryPoints);

    jtffTeamVictory.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
    jtffTeamVictory.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jtffTeamVictoryFocusLost(evt);
        }
    });
    jPanel6.add(jtffTeamVictory);

    buttonGroup1.add(jrbTeamVictory);
    jrbTeamVictory.setText("Utiliser la victoire d'équipe");
    jrbTeamVictory.setHideActionText(true);
    jrbTeamVictory.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jrbTeamVictory.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
    jrbTeamVictory.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jrbTeamVictoryActionPerformed(evt);
        }
    });
    jPanel6.add(jrbTeamVictory);

    buttonGroup1.add(jrbCoachPoints);
    jrbCoachPoints.setText("Utiliser le cumul des points des coachs");
    jrbCoachPoints.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jrbCoachPointsActionPerformed(evt);
        }
    });
    jPanel6.add(jrbCoachPoints);

    jPanel1.add(jPanel6, java.awt.BorderLayout.CENTER);

    jpnParameters.add(jPanel1);

    jPanel2.setLayout(new java.awt.BorderLayout());

    jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Coachs"));
    jPanel7.setPreferredSize(new java.awt.Dimension(450, 240));
    jPanel7.setLayout(new java.awt.BorderLayout());

    jbtAdd.setText("Ajouter");
    jbtAdd.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbtAddActionPerformed(evt);
        }
    });
    jpnCoachButtons.add(jbtAdd);

    jbtRemove.setText("Retirer");
    jbtRemove.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbtRemoveActionPerformed(evt);
        }
    });
    jpnCoachButtons.add(jbtRemove);

    jbtModify.setText("Modifier");
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

    jbtAddTeam.setText("Ajouter");
    jbtAddTeam.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbtAddTeamActionPerformed(evt);
        }
    });
    jPanel8.add(jbtAddTeam);

    jbtRemoveTeam.setText("Retirer");
    jbtRemoveTeam.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbtRemoveTeamActionPerformed(evt);
        }
    });
    jPanel8.add(jbtRemoveTeam);

    jbtModifyTeam.setText("Modifier");
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

    jbtFirstRound.setText("Générer la première ronde");
    jbtFirstRound.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbtFirstRoundActionPerformed(evt);
        }
    });
    jPanel4.add(jbtFirstRound);

    jPanel2.add(jPanel4, java.awt.BorderLayout.SOUTH);

    jpnParameters.add(jPanel2);

    jtpMain.addTab("Paramètres du tournoi", jpnParameters);

    getContentPane().add(jtpMain, java.awt.BorderLayout.CENTER);

    jmnFile.setText("Fichier");

    jmiNouveau.setText("Nouveau tournoi");
    jmiNouveau.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiNouveauActionPerformed(evt);
        }
    });
    jmnFile.add(jmiNouveau);

    jmiCharger.setText("Charger un tounoi");
    jmiCharger.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiChargerActionPerformed(evt);
        }
    });
    jmnFile.add(jmiCharger);

    jmiSave.setText("Enregistrer le tournoi");
    jmiSave.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiSaveActionPerformed(evt);
        }
    });
    jmnFile.add(jmiSave);

    jmiSaveAs.setText("Enregistrer le tournoi sous ");
    jmiSaveAs.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiSaveAsActionPerformed(evt);
        }
    });
    jmnFile.add(jmiSaveAs);
    jmnFile.add(jSeparator1);

    jmiExport.setLabel("Exporter les résultats (NAF)");
    jmiExport.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiExportActionPerformed(evt);
        }
    });
    jmnFile.add(jmiExport);
    jmnFile.add(jSeparator2);

    jmiExit.setText("Quitter");
    jmiExit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiExitActionPerformed(evt);
        }
    });
    jmnFile.add(jmiExit);

    jMenuBar1.add(jmnFile);

    jMenu2.setText("Edit");
    jMenuBar1.add(jMenu2);

    jmnHelp.setText("?");

    jmiAbout.setText("A propos de");
    jmiAbout.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiAboutActionPerformed(evt);
        }
    });
    jmnHelp.add(jmiAbout);

    jmiRevisions.setText("Révisions logicielles");
    jmiRevisions.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiRevisionsActionPerformed(evt);
        }
    });
    jmnHelp.add(jmiRevisions);
    jmnHelp.add(jSeparator3);

    jmiAideEnLigne.setText("Aide");
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

    private void jbtFirstRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFirstRoundActionPerformed

        if (JOptionPane.showConfirmDialog(this, "Etes vous sûr ? Cela va effacer toutes les rondes", "Première ronde", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (_tournament.getParams()._teamTournament && _tournament.getTeams().size() % 2 > 0) {
                JOptionPane.showMessageDialog(this, "Nombre impair d'équipes");
            } else {
                if (_tournament.getCoachs().size() % 2 > 0) {
                    JOptionPane.showMessageDialog(this, "Nombre impair de joueurs");
                } else {
                    String[] options = {"Aléatoire", "Ordre d'inscription"};
                    int choice = JOptionPane.showOptionDialog(this, "Choisissez le tirage initial", "Tirage", JOptionPane.YES_NO_OPTION, WIDTH, null, options, 0);
                    _tournament.generateFirstRound(choice);
                    for (int i = jtpMain.getTabCount() - 1; i >= 0; i--) {
                        Component obj = jtpMain.getComponentAt(i);
                        if (obj instanceof JPNRound) {
                            jtpMain.remove(obj);
                        }
                    }
                    for (int i = 0; i < _tournament.getRounds().size(); i++) {
                        JPNRound jpnr = new JPNRound(_tournament.getRounds().get(i), _tournament);
                        jtpMain.add("Ronde " + (i + 1), jpnr);
                    }
                    update();
                }
            }
        }
}//GEN-LAST:event_jbtFirstRoundActionPerformed

    private void jbtModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModifyActionPerformed

        if (jtbCoachs.getSelectedRow() >= 0) {
            jdgCoach w = new jdgCoach(this, true, _tournament.getCoachs().get(jtbCoachs.getSelectedRow()));
            w.setVisible(true);
            update();
        }
}//GEN-LAST:event_jbtModifyActionPerformed

    private void jbtRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveActionPerformed
        _tournament.getCoachs().remove(jtbCoachs.getSelectedRow());
        update();
}//GEN-LAST:event_jbtRemoveActionPerformed

    private void jbtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddActionPerformed
        jdgCoach w = new jdgCoach(this, true);
        w.setVisible(true);
        update();
}//GEN-LAST:event_jbtAddActionPerformed

    private void jcbRank5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank5ActionPerformed
        _tournament.getParams()._ranking5 = jcbRank5.getSelectedIndex();
        update();
}//GEN-LAST:event_jcbRank5ActionPerformed

    private void jcbRank4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank4ActionPerformed
        _tournament.getParams()._ranking4 = jcbRank4.getSelectedIndex();
        update();
}//GEN-LAST:event_jcbRank4ActionPerformed

    private void jcbRank3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank3ActionPerformed
        _tournament.getParams()._ranking3 = jcbRank3.getSelectedIndex();
        update();
}//GEN-LAST:event_jcbRank3ActionPerformed

    private void jcbRank2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank2ActionPerformed
        _tournament.getParams()._ranking2 = jcbRank2.getSelectedIndex();
        update();
}//GEN-LAST:event_jcbRank2ActionPerformed

    private void jcbRank1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank1ActionPerformed
        _tournament.getParams()._ranking1 = jcbRank1.getSelectedIndex();
        update();
}//GEN-LAST:event_jcbRank1ActionPerformed

    private void jtffFoulNegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffFoulNegFocusLost
        try {
            jtffFoulNeg.commitEdit();
            int points = ((Long) jtffFoulNeg.getValue()).intValue();
            _tournament.getParams()._bonus_neg_foul_points = points;
        } catch (ParseException e) {
            jtffFoulNeg.setValue(jtffFoulNeg.getValue());
        }
        update();
}//GEN-LAST:event_jtffFoulNegFocusLost

    private void jtffSorNegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffSorNegFocusLost
        try {
            jtffSorNeg.commitEdit();
            int points = ((Long) jtffSorNeg.getValue()).intValue();
            _tournament.getParams()._bonus_neg_sor_points = points;
        } catch (ParseException e) {
            jtffSorNeg.setValue(jtffSorNeg.getValue());
        }
        update();
}//GEN-LAST:event_jtffSorNegFocusLost

    private void jtffTdNegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffTdNegFocusLost
        try {
            jtffTdNeg.commitEdit();
            int points = ((Long) jtffTdNeg.getValue()).intValue();
            _tournament.getParams()._bonus_neg_td_points = points;
        } catch (ParseException e) {
            jtffTdNeg.setValue(jtffTdNeg.getValue());
        }
        update();
}//GEN-LAST:event_jtffTdNegFocusLost

    private void jtffFoulPosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffFoulPosFocusLost
        try {
            jtffFoulPos.commitEdit();
            int points = ((Long) jtffFoulPos.getValue()).intValue();
            _tournament.getParams()._bonus_foul_points = points;
        } catch (ParseException e) {
            jtffFoulPos.setValue(jtffFoulPos.getValue());
        }
        update();
}//GEN-LAST:event_jtffFoulPosFocusLost

    private void jtffSorPosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffSorPosFocusLost
        try {
            jtffSorPos.commitEdit();
            int points = ((Long) jtffSorPos.getValue()).intValue();
            _tournament.getParams()._bonus_sor_points = points;
        } catch (ParseException e) {
            jtffSorPos.setValue(jtffSorPos.getValue());
        }
        update();
}//GEN-LAST:event_jtffSorPosFocusLost

    private void jtffTdPosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffTdPosFocusLost
        try {
            jtffTdPos.commitEdit();
            int points = ((Long) jtffTdPos.getValue()).intValue();
            _tournament.getParams()._bonus_td_points = points;
        } catch (ParseException e) {
            jtffTdPos.setValue(jtffTdPos.getValue());
        }
        update();
}//GEN-LAST:event_jtffTdPosFocusLost

    private void jtffLostFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLostFocusLost
        try {
            jtffLost.commitEdit();
            int points = ((Long) jtffLost.getValue()).intValue();
            _tournament.getParams()._lost_points = points;
        } catch (ParseException e) {
            jtffLost.setValue(jtffLost.getValue());
        }
        update();
}//GEN-LAST:event_jtffLostFocusLost

    private void jtffLittleLostFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLittleLostFocusLost
        try {
            jtffLittleLost.commitEdit();
            int points = ((Long) jtffLittleLost.getValue()).intValue();
            _tournament.getParams()._little_lost_points = points;
        } catch (ParseException e) {
            jtffLittleLost.setValue(jtffLittleLost.getValue());
        }
        update();
}//GEN-LAST:event_jtffLittleLostFocusLost

    private void jtffDrawFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffDrawFocusLost
        try {
            jtffDraw.commitEdit();
            int points = ((Long) jtffDraw.getValue()).intValue();
            _tournament.getParams()._draw_points = points;
        } catch (ParseException e) {
            jtffDraw.setValue(jtffDraw.getValue());
        }
        update();
}//GEN-LAST:event_jtffDrawFocusLost

    private void jtffVictoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffVictoryFocusLost
        try {
            jtffVictory.commitEdit();
            int points = ((Long) jtffVictory.getValue()).intValue();
            _tournament.getParams()._victory_points = points;
        } catch (ParseException e) {
            jtffVictory.setValue(jtffVictory.getValue());
        }
        update();
}//GEN-LAST:event_jtffVictoryFocusLost

    private void jtffLargeVictoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLargeVictoryFocusLost

        try {
            jtffLargeVictory.commitEdit();
            int points = ((Long) jtffLargeVictory.getValue()).intValue();
            _tournament.getParams()._large_victory_points = points;
        } catch (ParseException e) {
            jtffLargeVictory.setValue(jtffLargeVictory.getValue());
        }
        update();
}//GEN-LAST:event_jtffLargeVictoryFocusLost

    private void jtfOrgasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfOrgasKeyPressed
        _tournament.getParams()._tournament_orga = jtfOrgas.getText();
}//GEN-LAST:event_jtfOrgasKeyPressed

    private void jtfTournamentNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTournamentNameKeyPressed
        _tournament.getParams()._tournament_name = jtfTournamentName.getText();
}//GEN-LAST:event_jtfTournamentNameKeyPressed

    private void jmiSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSaveAsActionPerformed
        JFileChooser jfc = new JFileChooser();
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String url2 = jfc.getSelectedFile().getAbsolutePath();
            String ext = "";
            int i = url2.lastIndexOf('.');
            if (i > 0 && i < url2.length() - 1) {
                ext = url2.substring(i + 1).toLowerCase();
            }

            if (!ext.equals("xml")) {
                url2 = url2 + ".xml";
            }
            file = new File(url2);
            Tournament.getTournament().saveXML(file);
        }
    }//GEN-LAST:event_jmiSaveAsActionPerformed

    private void jmiSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSaveActionPerformed
        if (file != null) {
            Tournament.getTournament().saveXML(file);
        } else {
            jmiSaveAsActionPerformed(evt);
        }
    }//GEN-LAST:event_jmiSaveActionPerformed

    private void jmiChargerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiChargerActionPerformed
        JFileChooser jfc = new JFileChooser();
        FileFilter filter1 = new ExtensionFileFilter("TourMa XML file", new String[]{"XML", "xml"});
        jfc.setFileFilter(filter1);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            Tournament.getTournament().loadXML(jfc.getSelectedFile());
            file =
                    jfc.getSelectedFile();
            for (int i = jtpMain.getTabCount() - 1; i >=
                    0; i--) {
                Component obj = jtpMain.getComponentAt(i);
                if (obj instanceof JPNRound) {
                    jtpMain.remove(obj);
                }

            }
            for (int i = 0; i <
                    _tournament.getRounds().size(); i++) {
                JPNRound jpnr = new JPNRound(_tournament.getRounds().get(i), _tournament);
                jtpMain.add("Ronde " + (i + 1), jpnr);
            }

            update();

        }
    }//GEN-LAST:event_jmiChargerActionPerformed

    private void jmiNouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNouveauActionPerformed


        for (int i = jtpMain.getTabCount() - 1; i >=
                0; i--) {
            Component obj = jtpMain.getComponentAt(i);
            if (obj instanceof JPNRound) {
                jtpMain.remove(obj);
            }
        }
        _tournament = Tournament.resetTournament();

        Object options[] = {"Individuel", "Par équipe"};
        int res = JOptionPane.showOptionDialog(this, "Type de tournoi", "Nouveau tournoi",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        Tournament.getTournament().getParams()._teamTournament = (res == 1);
        if (res == 1) {
            res = JOptionPane.showOptionDialog(this, "Type d'appariement", "Appariement",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            Tournament.getTournament().getParams()._teamPairing = res;
            if (res == 1) {
                Object options2[] = {"Selon classement", "Libre"};
                res = JOptionPane.showOptionDialog(this, "Type d'appariement individuel", "Appariement individuel",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options2, options2[0]);
                Tournament.getTournament().getParams()._teamIndivPairing = res;
                jdgSelectNumber jdg = new jdgSelectNumber(this, true, _tournament);
                jdg.setVisible(true);
            }
        }


        update();
    }//GEN-LAST:event_jmiNouveauActionPerformed

    private void jmiExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportActionPerformed
        JFileChooser jfc = new JFileChooser();
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            Tournament.getTournament().exportResults(jfc.getSelectedFile());
        }
    }//GEN-LAST:event_jmiExportActionPerformed

    private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jmiExitActionPerformed

    private void jmiAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAboutActionPerformed
        jdgAbout jdg = new jdgAbout(this, true);
        jdg.setVisible(true);
        jdg =
                null;
}//GEN-LAST:event_jmiAboutActionPerformed

    private void jmiRevisionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRevisionsActionPerformed
        jdgRevisions jdg = new jdgRevisions(this, true);
        jdg.setVisible(true);
        jdg =
                null;
}//GEN-LAST:event_jmiRevisionsActionPerformed

    private void jmiAideEnLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAideEnLigneActionPerformed
        jdgOnlineHelp jdg = new jdgOnlineHelp(this, false);
        jdg.setVisible(true);
        jdg =
                null;
    }//GEN-LAST:event_jmiAideEnLigneActionPerformed

    private void jtffLargeVictoryGapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLargeVictoryGapFocusLost
        try {
            jtffLargeVictoryGap.commitEdit();
            int points = ((Long) jtffLargeVictoryGap.getValue()).intValue();
            _tournament.getParams()._large_victory_gap = points;
        } catch (ParseException e) {
            jtffLargeVictoryGap.setValue(jtffLargeVictoryGap.getValue());
        }

        update();
    }//GEN-LAST:event_jtffLargeVictoryGapFocusLost

    private void jtffLittleLostGapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLittleLostGapFocusLost
        try {
            jtffLittleLostGap.commitEdit();
            int points = ((Long) jtffLittleLostGap.getValue()).intValue();
            _tournament.getParams()._little_lost_gap = points;
        } catch (ParseException e) {
            jtffLittleLostGap.setValue(jtffLittleLostGap.getValue());
        }

        update();
    }//GEN-LAST:event_jtffLittleLostGapFocusLost

    private void jtfPlaceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPlaceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfPlaceKeyPressed

    private void jbtAddTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddTeamActionPerformed
        String name = JOptionPane.showInputDialog(this, "Entrez le nome de l'aquipe", "Nom de l'équipe");
        Team team = new Team();
        team._name = name;
        Coach lastCoach = null;
        while (team._coachs.size() < _tournament.getParams()._teamMatesNumber) {
            jdgCoach jdg = new jdgCoach(this, true);
            jdg.setVisible(true);
            Coach c = _tournament.getCoachs().lastElement();
            if (c != lastCoach) {
                c._teamMates = team;
                team._coachs.add(c);
                lastCoach = c;
            }
        }
        _tournament.getTeams().add(team);
        update();
    }//GEN-LAST:event_jbtAddTeamActionPerformed

    private void jbtRemoveTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveTeamActionPerformed
        Team t = _tournament.getTeams().get(jtbTeam.getSelectedRow());
        for (int i = 0; i < t._coachs.size(); i++) {
            _tournament.getCoachs().remove(t._coachs.get(i));
        }
        t._coachs.clear();
        _tournament.getTeams().remove(t);
        update();
    }//GEN-LAST:event_jbtRemoveTeamActionPerformed

    private void jbtModifyTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModifyTeamActionPerformed
        Team t = _tournament.getTeams().get(jtbTeam.getSelectedRow());
        if (jtbTeam.getSelectedColumn() == 1) {
            String name = JOptionPane.showInputDialog(this, "Entrez le nome de l'aquipe", t._name);
            t._name = name;
        } else if (jtbTeam.getSelectedColumn() > 1) {
            jdgCoach jdg = new jdgCoach(this, true, t._coachs.get(jtbTeam.getSelectedColumn() - 2));
            jdg.setVisible(true);
        }
        update();
    }//GEN-LAST:event_jbtModifyTeamActionPerformed

    private void jtffTeamVictoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffTeamVictoryFocusLost
        try {
            jtffTeamVictory.commitEdit();
            int points = ((Long) jtffTeamVictory.getValue()).intValue();
            _tournament.getParams()._team_victory_points = points;
        } catch (ParseException e) {
            jtffTeamVictory.setValue(jtffTeamVictory.getValue());
        }
        update();
    }//GEN-LAST:event_jtffTeamVictoryFocusLost

    private void jrbTeamVictoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbTeamVictoryActionPerformed
        _tournament.getParams()._team_victory_only = jrbTeamVictory.isSelected();
        update();
    }//GEN-LAST:event_jrbTeamVictoryActionPerformed

    private void jrbCoachPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCoachPointsActionPerformed
        _tournament.getParams()._team_victory_only = !jrbCoachPoints.isSelected();
        update();
    }//GEN-LAST:event_jrbCoachPointsActionPerformed

    public void setColumnSize(JTable t) {
        FontMetrics fm = t.getFontMetrics(t.getFont());
        for (int i = 0; i <
                t.getColumnCount(); i++) {
            int max = 0;
            for (int j = 0; j <
                    t.getRowCount(); j++) {
                Object value = t.getValueAt(j, i);
                String tmp = "";
                if (value instanceof String) {
                    tmp = (String) value;
                }

                if (value instanceof Integer) {
                    tmp = "" + (Integer) value;
                }

                int taille = fm.stringWidth(tmp);
                if (taille > max) {
                    max = taille;
                }

            }
            String nom = (String) t.getColumnModel().getColumn(i).getIdentifier();
            int taille = fm.stringWidth(nom);
            if (taille > max) {
                max = taille;
            }

            t.getColumnModel().getColumn(i).setPreferredWidth(max + 10);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                JFrame.setDefaultLookAndFeelDecorated(true);
                try {
                    SubstanceLookAndFeel lf = new SubstanceMistSilverLookAndFeel();
                    UIManager.setLookAndFeel(lf);
                } catch (Exception e) {
                    System.out.println("Substance Creme Coffee failed to initialize");
                }

                MainFrame.getMainFrame().setVisible(true);
            }
        });
    }
    protected static MainFrame _singleton;

    public static MainFrame getMainFrame() {
        if (_singleton == null) {
            _singleton = new MainFrame();
        }

        return _singleton;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtAddTeam;
    private javax.swing.JButton jbtFirstRound;
    private javax.swing.JButton jbtModify;
    private javax.swing.JButton jbtModifyTeam;
    private javax.swing.JButton jbtRemove;
    private javax.swing.JButton jbtRemoveTeam;
    private javax.swing.JComboBox jcbRank1;
    private javax.swing.JComboBox jcbRank2;
    private javax.swing.JComboBox jcbRank3;
    private javax.swing.JComboBox jcbRank4;
    private javax.swing.JComboBox jcbRank5;
    private javax.swing.JLabel jlbDetails;
    private javax.swing.JLabel jlbVictoryPoints;
    private javax.swing.JMenuItem jmiAbout;
    private javax.swing.JMenuItem jmiAideEnLigne;
    private javax.swing.JMenuItem jmiCharger;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiExport;
    private javax.swing.JMenuItem jmiNouveau;
    private javax.swing.JMenuItem jmiRevisions;
    private javax.swing.JMenuItem jmiSave;
    private javax.swing.JMenuItem jmiSaveAs;
    private javax.swing.JMenu jmnFile;
    private javax.swing.JMenu jmnHelp;
    private javax.swing.JPanel jpnCoachButtons;
    private javax.swing.JPanel jpnParameters;
    private javax.swing.JPanel jpnTeamTour;
    private javax.swing.JRadioButton jrbCoachPoints;
    private javax.swing.JRadioButton jrbTeamVictory;
    private javax.swing.JTable jtbCoachs;
    private javax.swing.JTable jtbTeam;
    private javax.swing.JTextField jtfOrgas;
    private javax.swing.JTextField jtfPlace;
    private javax.swing.JTextField jtfTournamentName;
    private javax.swing.JFormattedTextField jtffDraw;
    private javax.swing.JFormattedTextField jtffFoulNeg;
    private javax.swing.JFormattedTextField jtffFoulPos;
    private javax.swing.JFormattedTextField jtffLargeVictory;
    private javax.swing.JFormattedTextField jtffLargeVictoryGap;
    private javax.swing.JFormattedTextField jtffLittleLost;
    private javax.swing.JFormattedTextField jtffLittleLostGap;
    private javax.swing.JFormattedTextField jtffLost;
    private javax.swing.JFormattedTextField jtffSorNeg;
    private javax.swing.JFormattedTextField jtffSorPos;
    private javax.swing.JFormattedTextField jtffTdNeg;
    private javax.swing.JFormattedTextField jtffTdPos;
    private javax.swing.JFormattedTextField jtffTeamVictory;
    private javax.swing.JFormattedTextField jtffVictory;
    public javax.swing.JTabbedPane jtpMain;
    private org.jdesktop.swingx.JXDatePicker jxdDate;
    // End of variables declaration//GEN-END:variables
}
