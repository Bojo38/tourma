
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * JdgCoach.java
 *
 * Created on 10 mai 2010, 19:37:53
 */
package tourma;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import teamma.data.LRB;
import teamma.views.JdgRoster;
import tourma.data.Category;
import tourma.data.Coach;
import tourma.data.RosterType;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.utility.ExtensionFileFilter;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;
import tourma.utils.NAF;

/**
 *
 * @author Frederic Berger
 */
public final class JdgCoach extends javax.swing.JDialog {

    private boolean mTeamTournament;
    private Coach mCoach;
    private boolean mNew = false;

    /**
     * Creates new form jdgCoach
     *
     * @param parent
     * @param modal
     */
    @SuppressWarnings("unchecked")
    public JdgCoach(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        mCoach = new Coach();
        mNew = true;
        initComponents();

        mTeamTournament = Tournament.getTournament().getParams().isTeamTournament();

        jcbRoster.setModel(RosterType.getRostersNamesModel());
        mCoach.setRoster(new RosterType(jcbRoster.getSelectedIndex()));
        final DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            clanListModel.addElement(Tournament.getTournament().getClan(i).getName());
        }
        jcbClan.setModel(clanListModel);

        final DefaultListModel categoryListModel = new DefaultListModel();
        for (int i = 0; i < this.mCoach.getCategoryCount(); i++) {
            categoryListModel.addElement(mCoach.getCategory(i).getName());
        }
        jlsCategories.setModel(categoryListModel);

        /*final DefaultComboBoxModel categoryListModel = new DefaultComboBoxModel();
         for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
         categoryListModel.addElement(Tournament.getTournament().getCategory(i).getName());
         }
         jcbCategory.setModel(categoryListModel);*/
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();
        final int screenWidth = dmode.getWidth();
        final int screenHeight = dmode.getHeight();
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

        if ((mTeamTournament) && (Tournament.getTournament().getClansCount() > 1)) {
            jcbClan.setEnabled(false);
        } else {
            jcbClan.setEnabled(Tournament.getTournament().getParams().isEnableClans());
        }

        jbtAddCategory.setEnabled(Tournament.getTournament().getCategoriesCount() > 1);
        jbtDelCategory.setEnabled((Tournament.getTournament().getCategoriesCount() > 1) && (jlsCategories.getSelectedValuesList().size() > 0));

        jbtEditRoster.setEnabled(true);
        jbtAdd.setEnabled(true);
        jpnBtns.setEnabled(true);
        jlsCompositions.setEnabled(true);
        updatelist();

        jLabel4.setEnabled(true);
        jtfNAF.setEnabled(true);
        jlbNafRanking.setText(Double.toString(mCoach.getNafRank()) + " (" + Double.toString(mCoach.getNafRankAvg()) + ")");
        jcbRoster.setModel(RosterType.getRostersNamesModel());

        jtfPinCode.setText(Integer.toString(mCoach.getPinCode()));

        if (mCoach.getPicture() == null) {
            try {
                BufferedImage img = ImageIO.read(getClass().getResource("/tourma/images/avatar/60001.png"));
                mCoach.setPicture(new ImageIcon(img));
            } catch (IOException ex) {
                Logger.getLogger(JdgCoach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jbtAvatar.setIcon(ImageTreatment.resize(mCoach.getPicture(), 80, 80));

    }
    private Team mTeam = null;

    /**
     *
     * @param parent
     * @param modal
     * @param team
     */
    @SuppressWarnings("unchecked")
    public JdgCoach(final java.awt.Frame parent, final boolean modal, final Team team) {
        super(parent, modal);
        mCoach = new Coach();
        initComponents();
        mNew = true;
        mTeam = team;

        mTeamTournament = Tournament.getTournament().getParams().isTeamTournament();

        jtfPinCode.setText(Integer.toString(mCoach.getPinCode()));
        jcbRoster.setModel(RosterType.getRostersNamesModel());
        mCoach.setRoster(new RosterType(jcbRoster.getSelectedIndex()));
        final DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            clanListModel.addElement(Tournament.getTournament().getClan(i).getName());
        }
        jcbClan.setModel(clanListModel);

        final DefaultListModel categoryListModel = new DefaultListModel();
        for (int i = 0; i < this.mCoach.getCategoryCount(); i++) {
            categoryListModel.addElement(mCoach.getCategory(i).getName());
        }
        jlsCategories.setModel(categoryListModel);

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();
        final int screenWidth = dmode.getWidth();
        final int screenHeight = dmode.getHeight();
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

        if ((mTeamTournament) && (Tournament.getTournament().getClansCount() > 1)) {
            jcbClan.setEnabled(false);
        } else {
            jcbClan.setEnabled(Tournament.getTournament().getParams().isEnableClans());
        }

        jbtAddCategory.setEnabled(Tournament.getTournament().getCategoriesCount() > 1);
        jbtDelCategory.setEnabled((Tournament.getTournament().getCategoriesCount() > 1) && (jlsCategories.getSelectedValuesList().size() > 0));

        jbtEditRoster.setEnabled(true);
        jLabel4.setEnabled(true);
        jtfNAF.setEnabled(true);
        jlbNafRanking.setText(Double.toString(mCoach.getNafRank()) + " (" + Double.toString(mCoach.getNafRankAvg()) + ")");

        jbtAdd.setEnabled(true);
        jpnBtns.setEnabled(true);
        jlsCompositions.setEnabled(true);
        updatelist();

        jcbRoster.setModel(RosterType.getRostersNamesModel());
        if (mCoach.getPicture() == null) {
            try {
                java.net.URL url = getClass().getResource("/tourma/images/avatar/60001.png");
                if (url != null) {
                    BufferedImage buf = ImageIO.read(url);
                    if (buf != null) {
                        mCoach.setPicture(new ImageIcon(buf));
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(JdgCoach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jbtAvatar.setIcon(ImageTreatment.resize(mTeam.getPicture(), 80, 80));
    }

    /**
     * Creates new form jdgCoach form an existing coach
     *
     * @param parent
     * @param modal
     * @param coach
     */
    public JdgCoach(final java.awt.Frame parent, final boolean modal, final Coach coach) {
        super(parent, modal);
        mCoach = new Coach();
        initComponents();

        jcbRoster.setModel(RosterType.getRostersNamesModel());
        mCoach = coach;
        mCoach.setName(coach.getName());
        if (mCoach.getPicture() == null) {
            try {
                coach.setPicture(new ImageIcon(ImageIO.read(getClass().getResource("/tourma/images/avatar/60001.png"))));
            } catch (IOException ex) {
                Logger.getLogger(JdgCoach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        ImageIcon image;
        image = mCoach.getPicture();
        jbtAvatar.setIcon(ImageTreatment.resize(image, 80, 80));

        final DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            clanListModel.addElement(Tournament.getTournament().getClan(i).getName());
        }
        jcbClan.setModel(clanListModel);

        final DefaultListModel categoryListModel = new DefaultListModel();
        for (int i = 0; i < this.mCoach.getCategoryCount(); i++) {
            Category cat = mCoach.getCategory(i);
            if (cat != null) {
                categoryListModel.addElement(cat.getName());
            } else {
                mCoach.delCategory(cat);
            }
        }
        jlsCategories.setModel(categoryListModel);

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();

        final int screenWidth = dmode.getWidth();
        final int screenHeight = dmode.getHeight();
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

        jtfEquipe.setText(mCoach.getTeam());
        jtfNAF.setText(Integer.toString(mCoach.getNaf()));
        jtfNom.setText(mCoach.getName());
        jcbRoster.setSelectedItem(mCoach.getRoster().getName());

        jtfPinCode.setText(Integer.toString(mCoach.getPinCode()));

        jckActive.setSelected(mCoach.isActive());

        jtfHandicap.setText(Integer.toString(mCoach.getHandicap()));

        if ((Tournament.getTournament().getParams().isEnableClans()) && (Tournament.getTournament().getClansCount() > 1)) {
            jcbClan.setEnabled(true);
            
            if(mCoach.getClan()!=null)
            {
                jcbClan.setSelectedItem(mCoach.getClan().getName());
            }
        }

        jbtAddCategory.setEnabled(Tournament.getTournament().getCategoriesCount() > 1);
        jbtDelCategory.setEnabled(Tournament.getTournament().getCategoriesCount() > 1);

        jcbRoster.setSelectedItem(mCoach.getRoster().getName());
        if (mCoach.getClan() != null) {
            jcbClan.setSelectedItem(mCoach.getClan().getName());
        }

        jbtEditRoster.setEnabled(true);
        jLabel4.setEnabled(true);
        jtfNAF.setEnabled(true);
        jlbNafRanking.setText(Double.toString(mCoach.getNafRank()) + " (" + Double.toString(mCoach.getNafRankAvg()) + ")");

        jbtAdd.setEnabled(true);
        jpnBtns.setEnabled(true);
        jlsCompositions.setEnabled(true);
        updatelist();

        if (coach.getPicture() == null) {
            try {
                coach.setPicture(new ImageIcon(ImageIO.read(getClass().getResource("/tourma/images/avatar/60001.png"))));
            } catch (IOException ex) {
                Logger.getLogger(JdgCoach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jbtAvatar.setIcon(ImageTreatment.resize(mCoach.getPicture(), 80, 80));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    @SuppressFBWarnings({"SIC", "Generated Code"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jpnBtns = new javax.swing.JPanel();
        jbtAdd = new javax.swing.JButton();
        jbtDel = new javax.swing.JButton();
        jbtEditRoster = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlsCompositions = new javax.swing.JList();
        jPanel5 = new javax.swing.JPanel();
        jpnBtns1 = new javax.swing.JPanel();
        jbtAddCategory = new javax.swing.JButton();
        jbtDelCategory = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlsCategories = new javax.swing.JList();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfNom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfEquipe = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcbRoster = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jtfNAF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfRank = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jcbClan = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jckActive = new javax.swing.JCheckBox();
        jlbHandicap = new javax.swing.JLabel();
        jtfHandicap = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jbtDownloadFromNaf = new javax.swing.JButton();
        jbtUpdateFromDb = new javax.swing.JButton();
        jlbNafRanking = new javax.swing.JLabel();
        jbtPinCode = new javax.swing.JButton();
        jtfPinCode = new javax.swing.JTextField();
        jbtAvatar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel2.add(jbtOK);

        jbtCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Halt.png"))); // NOI18N
        jbtCancel.setText(bundle.getString("Cancel")); // NOI18N
        jbtCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelActionPerformed(evt);
            }
        });
        jPanel2.add(jbtCancel);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Rosters"))); // NOI18N
        jPanel4.setLayout(new java.awt.BorderLayout());

        jpnBtns.setLayout(new java.awt.GridLayout(3, 1, 1, 1));

        jbtAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddActionPerformed(evt);
            }
        });
        jpnBtns.add(jbtAdd);

        jbtDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDelActionPerformed(evt);
            }
        });
        jpnBtns.add(jbtDel);

        jbtEditRoster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Open.png"))); // NOI18N
        jbtEditRoster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditRosterActionPerformed(evt);
            }
        });
        jpnBtns.add(jbtEditRoster);

        jPanel4.add(jpnBtns, java.awt.BorderLayout.WEST);

        jlsCompositions.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlsCompositions);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Category"))); // NOI18N
        jPanel5.setLayout(new java.awt.BorderLayout());

        jpnBtns1.setLayout(new java.awt.GridLayout(3, 1, 1, 1));

        jbtAddCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddCategoryActionPerformed(evt);
            }
        });
        jpnBtns1.add(jbtAddCategory);

        jbtDelCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtDelCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDelCategoryActionPerformed(evt);
            }
        });
        jpnBtns1.add(jbtDelCategory);

        jPanel5.add(jpnBtns1, java.awt.BorderLayout.WEST);

        jlsCategories.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jlsCategories);

        jPanel5.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(10, 2));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText(bundle.getString("CoachNameKey")); // NOI18N
        jPanel1.add(jLabel1);

        jtfNom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jtfNomPropertyChange(evt);
            }
        });
        jtfNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfNomKeyPressed(evt);
            }
        });
        jPanel1.add(jtfNom);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText(bundle.getString("TeamNameKey")); // NOI18N
        jPanel1.add(jLabel2);
        jPanel1.add(jtfEquipe);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText(bundle.getString("RaceRosterKey")); // NOI18N
        jPanel1.add(jLabel3);

        jcbRoster.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Amazone", "Bas-Fonds", "Chaos", "Elfe", "Elfe sylvain", "Elfe noir", "Gobelin", "Halfling", "Haut Elfe", "Homme lézard", "Humain", "Khemri", "Mort-Vivant", "Nain", "Nain du chaos", "Necromantique", "Nordique", "Nurgle", "Ogre", "Orque", "Pacte Chaotique", "Skaven", "Slann", "Vampire" }));
        jcbRoster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRosterActionPerformed(evt);
            }
        });
        jcbRoster.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jcbRosterPropertyChange(evt);
            }
        });
        jPanel1.add(jcbRoster);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText(bundle.getString("NAFNumberKey")); // NOI18N
        jPanel1.add(jLabel4);
        jPanel1.add(jtfNAF);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText(bundle.getString("Ranking")); // NOI18N
        jPanel1.add(jLabel5);

        jtfRank.setText("110");
        jPanel1.add(jtfRank);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText(bundle.getString("ClanKey:")); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.getAccessibleContext().setAccessibleName(bundle.getString("ClanKey")); // NOI18N

        jPanel1.add(jcbClan);
        jPanel1.add(jLabel7);

        jckActive.setSelected(true);
        jckActive.setText(bundle.getString("Active")); // NOI18N
        jPanel1.add(jckActive);

        jlbHandicap.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbHandicap.setText(bundle.getString("HANDICAP")); // NOI18N
        jPanel1.add(jlbHandicap);

        jtfHandicap.setText(bundle.getString("110")); // NOI18N
        jPanel1.add(jtfHandicap);

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        jbtDownloadFromNaf.setText(bundle.getString("DOWNLOAD FROM NAF")); // NOI18N
        jbtDownloadFromNaf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDownloadFromNafActionPerformed(evt);
            }
        });
        jPanel7.add(jbtDownloadFromNaf);

        jbtUpdateFromDb.setText(bundle.getString("UPDATE FROM NAF DB")); // NOI18N
        jbtUpdateFromDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtUpdateFromDbActionPerformed(evt);
            }
        });
        jPanel7.add(jbtUpdateFromDb);

        jPanel1.add(jPanel7);

        jlbNafRanking.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbNafRanking.setText("150");
        jPanel1.add(jlbNafRanking);

        jbtPinCode.setText(bundle.getString("PinCode")); // NOI18N
        jbtPinCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPinCodeActionPerformed(evt);
            }
        });
        jPanel1.add(jbtPinCode);
        jPanel1.add(jtfPinCode);

        jPanel6.add(jPanel1, java.awt.BorderLayout.CENTER);

        jbtAvatar.setMnemonic('A');
        jbtAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAvatarActionPerformed(evt);
            }
        });
        jPanel6.add(jbtAvatar, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel6, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelActionPerformed
        //mCoach.setName("");
        this.setVisible(false);
    }//GEN-LAST:event_jbtCancelActionPerformed

    private final static String CS_AnotherCoachHasTheSameName = "UN AUTRE COACH A DÉJÀ LE MÊME NOM";
    private final static String CS_NameIsEmpty = "NameIsEmpty";
    private final static String CS_TeamIsEmpty = "TeamIsEmpty";
    private final static String CS_RosterIsEmpty = "RosterIsEmpty";
    private final static String CS_RostersChoice = "Roster's Choice";
    private final static String CS_ChooseRoster = "ChooseRoster";
    private final static String CS_RostersChoiceError0 = "RostersChoiceError0";
    private final static String CS_SelectPicture = "Select picture";
    private final static String CS_Picture = "Picture";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        Coach c;
        boolean error = false;
        if (mCoach == null) {
            c = new Coach();
        } else {
            c = mCoach;
        }

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            final Coach tmp = Tournament.getTournament().getCoach(i);
            if ((tmp.getName().equals(jtfNom.getText())) && (!tmp.equals(c))) {
                JOptionPane.showMessageDialog(this,
                        Translate.translate(CS_AnotherCoachHasTheSameName)
                );
                error = true;
                break;
            }
        }

        if (!error) {
            if (MainFrame.getMainFrame().jcxmiAsServer.isSelected()) {
                try {
                    int pinCode = Integer.parseInt(jtfPinCode.getText());
                    if ((pinCode > 10000) || (pinCode == 0)) {
                        error = true;
                        JOptionPane.showMessageDialog(this, "Invalid Pin Code", "Pin Code Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        c.setPinCode(pinCode);
                    }
                } catch (NumberFormatException e) {
                    c.setPinCode(0);
                }
            }

            if (!jtfNom.getText().equals(""))
            {
                c.setName(jtfNom.getText());
            }
            c.setRoster(new RosterType(jcbRoster.getSelectedIndex()));
            c.setTeam(jtfEquipe.getText());
            c.setActive(jckActive.isSelected());
            try {
                c.setNaf(Integer.parseInt(jtfNAF.getText()));
            } catch (NumberFormatException e) {
                c.setNaf(0);
            }
            try {
                c.setRank(Integer.parseInt(jtfRank.getText()));
            } catch (NumberFormatException e) {
                c.setRank(110);
            }

            try {
                c.setHandicap(Integer.parseInt(jtfHandicap.getText()));
            } catch (NumberFormatException e) {
                c.setHandicap(0);
            }
            c.setPicture(mCoach.getPicture());
        }

        if (!error) {

            if (c.getName().equals(StringConstants.CS_NULL)) {
                JOptionPane.showMessageDialog(this, Translate.translate(CS_NameIsEmpty));
                error = true;
            }
            if (c.getTeam().equals(StringConstants.CS_NULL)) {
                JOptionPane.showMessageDialog(this, Translate.translate(CS_TeamIsEmpty));
                error = true;
            }
            if (c.getRoster().getName().equals(StringConstants.CS_NULL)) {
                JOptionPane.showMessageDialog(this, Translate.translate(CS_RosterIsEmpty));
                error = true;
            }

            if (jcbClan.getSelectedIndex() > 0) {
                c.setClan(Tournament.getTournament().getClan(jcbClan.getSelectedIndex()));
            } else {
                c.setClan(Tournament.getTournament().getClan(0));
            }
        } else {
            c.setClan(Tournament.getTournament().getClan(0));

        }

        if (!error) {
            if (mNew) {
                if (mTeam != null) {
                    c.setTeamMates(mTeam);
                    mTeam.addCoach(c);
                }
                Tournament.getTournament().addCoach(c);
            }
        }

        if (!error) {
            this.setVisible(false);
        }

    }//GEN-LAST:event_jbtOKActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtEditRosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditRosterActionPerformed

        if (jlsCompositions.getSelectedIndex() >= 0) {

            final teamma.views.JdgRoster window = new JdgRoster(MainFrame.getMainFrame(), mCoach, mCoach.getComposition(jlsCompositions.getSelectedIndex()), true);
            window.setVisible(true);

            updatelist();
        }

    }//GEN-LAST:event_jbtEditRosterActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtfNomPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jtfNomPropertyChange
        //mCoach.mName = jtfNom.getText();
    }//GEN-LAST:event_jtfNomPropertyChange
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRosterPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jcbRosterPropertyChange
        //mCoach.mRoster = new RosterType(jcbRoster.getSelectedIndex());
    }//GEN-LAST:event_jcbRosterPropertyChange
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtfNomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNomKeyPressed
        //mCoach.mName = jtfNom.getText();
    }//GEN-LAST:event_jtfNomKeyPressed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRosterActionPerformed
        mCoach.setRoster(new RosterType(jcbRoster.getSelectedIndex()));
    }//GEN-LAST:event_jcbRosterActionPerformed

    private void jbtDownloadFromNafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDownloadFromNafActionPerformed
        
        double rank = NAF.getRanking(jtfNom.getText(), mCoach);
        jtfNAF.setText(Integer.toString(mCoach.getNaf()));
        jlbNafRanking.setText(Double.toString(mCoach.getNafRank()) + " (" + Double.toString(mCoach.getNafRankAvg()) + ")");
    }//GEN-LAST:event_jbtDownloadFromNafActionPerformed

    /**
     * Update list
     */
    @SuppressWarnings("unchecked")
    private void updatelist() {
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < mCoach.getCompositionCount(); i++) {
            teamma.data.Roster rost = mCoach.getComposition(i);
            if (rost != null) {
                model.addElement(rost.getRoster().getName());
                mCoach.setUpdated(true);
            }
        }
        jlsCompositions.setModel(model);

        if (Tournament.getTournament().getCategoriesCount() != 0) {
            final DefaultListModel categoryListModel = new DefaultListModel();
            for (int i = 0; i < this.mCoach.getCategoryCount(); i++) {
                Category cat = mCoach.getCategory(i);
                categoryListModel.addElement(cat.getName());
                mCoach.setUpdated(true);
            }
            jlsCategories.setModel(categoryListModel);
        }
        jlsCategories.setEnabled(Tournament.getTournament().getCategoriesCount() > 1);
        jbtAddCategory.setEnabled(Tournament.getTournament().getCategoriesCount() > 1);
        jbtDelCategory.setEnabled(Tournament.getTournament().getCategoriesCount() > 1);

    }

    private void jbtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddActionPerformed

        String input = (String) JOptionPane.showInputDialog(this,
                Translate.translate(CS_ChooseRoster),
                Translate.translate(CS_RostersChoice),
                JOptionPane.INFORMATION_MESSAGE,
                null, RosterType.getRostersNames(), RosterType.getRostersNames()[0]);

        LRB.E_Version version=LRB.E_Version.BB2016;
        teamma.data.RosterType rt = teamma.data.LRB.getLRB(version).getRosterType(input, false);

        if (rt != null) {
            teamma.data.Roster compo = new teamma.data.Roster();
            compo.setRoster(rt);
            mCoach.addComposition(compo);
            mCoach.setUpdated(true);
            updatelist();
        } else {
            JOptionPane.showMessageDialog(this,
                    Translate.translate(CS_RostersChoiceError0) + input);
        }
    }//GEN-LAST:event_jbtAddActionPerformed

    private void jbtDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDelActionPerformed
        // Get Selection
        if (jlsCompositions.getSelectedIndex() >= 0) {
            int index = jlsCompositions.getSelectedIndex();
            mCoach.removeComposition(index);
            updatelist();
        }
    }//GEN-LAST:event_jbtDelActionPerformed

    private void jbtAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAvatarActionPerformed
        try {
            List<ImageIcon> listOfFiles = getImagesResources("/tourma/images/avatar");

            Object[] objects = new Object[listOfFiles.size() + 1];
            for (int i = 0; i < listOfFiles.size(); i++) {
                if (listOfFiles.get(i) != null) {
                    objects[i] = resize(listOfFiles.get(i), 80, 80);
                }
            }

            ImageIcon empty = new ImageIcon();
            objects[listOfFiles.size()] = empty;

            JComboBox combo = new JComboBox(objects);
            JPanel panel = new JPanel(new BorderLayout());
            JLabel l = new JLabel(("Select picture"));
            panel.add(l, BorderLayout.NORTH);
            panel.add(combo, BorderLayout.CENTER);

            combo.setSelectedItem(empty);

            JOptionPane.showConfirmDialog(null, panel, null, JOptionPane.YES_OPTION);

            if (combo.getSelectedItem() == empty) {
                final JFileChooser jfc = new JFileChooser();
                final FileFilter filter1 = new ExtensionFileFilter(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Picture"), new String[]{"PNG", "png", "JPG", "jpg", "GIF", "gif"});
                jfc.setFileFilter(filter1);
                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ImageIcon icon = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
                    icon = ImageTreatment.resize(icon, 80, 80);
                    this.mCoach.setPicture(icon);
                }
            } else {
                ImageIcon icon = (ImageIcon) combo.getSelectedItem();
                this.mCoach.setPicture(icon);
            }

            jbtAvatar.setIcon(mCoach.getPicture());
        } catch (IOException ex) {
            LOG.log(Level.WARNING, ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_jbtAvatarActionPerformed

    public List<ImageIcon> getImagesResources(final String path) throws IOException {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ArrayList<ImageIcon> list = new ArrayList<>();
        final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        if (jarFile == null) {
            System.out.println("Jarfile is null: " + getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        } else {
            System.out.println("Open JAR File: " + getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        }

        if (jarFile.isFile()) {  // Run with JAR file
            final JarFile jar = new JarFile(jarFile);
            final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
            while (entries.hasMoreElements()) {
                final String name = entries.nextElement().getName();
                //System.out.println("Open JAR File: "+name);
                String pathToTest;
                if (path.startsWith("/")) {
                    pathToTest = path.subSequence(1, path.length()).toString();
                } else {
                    pathToTest = path;
                }
                // System.out.println("Tests "+name+" starts with "+pathToTest);
                if (name.startsWith(pathToTest + "/")) { //filter according to the path
                    BufferedImage bi = ImageIO.read(getClass().getResource("/tourma/images/flags/Country France.png"));
                    bi = ImageIO.read(getClass().getResource("/" + name));
                    if (bi == null) {
                        System.out.println("/" + name + " returns a null image");
                    } else {
                        ImageIcon ii = new ImageIcon(bi);
                        list.add(ii);
                    }
                }
            }
            jar.close();
        } else { // Run with IDE
            try {
                final URL url = getClass().getResource(path).toURI().toURL();
                if (url != null) {
                    try {
                        final File apps = new File(url.toURI());
                        for (File app : apps.listFiles()) {
                            ImageIcon icon = new ImageIcon(app.getAbsolutePath());
                            list.add(icon);
                        }
                    } catch (URISyntaxException ex) {
                        // never happens
                    }
                }
            } catch (URISyntaxException ex) {
                LOG.log(Level.WARNING, ex.getLocalizedMessage());
            }
        }
        return list;
    }

    private ImageIcon resize(ImageIcon image, int heigth, int width) {
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    private final static String CS_PleaseSelectCategory = "PleaseSelectCategory";

    private void jbtAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddCategoryActionPerformed
        ArrayList<String> cats = new ArrayList<>();
        cats.add(" ");
        for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
            Category cat = Tournament.getTournament().getCategory(i);
            if (!mCoach.containsCategory(cat)) {
                cats.add(cat.getName());

            }
        }
        @SuppressWarnings("unchecked")
        JComboBox jcb = new JComboBox(cats.toArray());
        jcb.setEditable(true);
        JOptionPane.showMessageDialog(this, jcb,
                Translate.translate(CS_PleaseSelectCategory),
                JOptionPane.QUESTION_MESSAGE);

        if (jcb.getSelectedIndex() > 0) {
            String name = cats.get(jcb.getSelectedIndex());
            Category cat = Category.getCategory(name);
            mCoach.addCategory(cat);
            mCoach.setUpdated(true);
        }
        updatelist();

    }//GEN-LAST:event_jbtAddCategoryActionPerformed

    private void jbtDelCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDelCategoryActionPerformed

        List<Object> selection = jlsCategories.getSelectedValuesList();
        for (Object o : selection) {
            if (o instanceof String) {
                String name = (String) o;
                Category cat = Category.getCategory(name);
                mCoach.delCategory(cat);
                mCoach.setUpdated(true);
            }
        }

        updatelist();
    }//GEN-LAST:event_jbtDelCategoryActionPerformed

    private void jbtPinCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPinCodeActionPerformed
        Random random = new Random();
        int ran = random.nextInt(10000);
        jtfPinCode.setText(Integer.toString(ran));
    }//GEN-LAST:event_jbtPinCodeActionPerformed

    private void jbtUpdateFromDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtUpdateFromDbActionPerformed
        NAF.updateCoachID(mCoach);
    }//GEN-LAST:event_jbtUpdateFromDbActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtAddCategory;
    private javax.swing.JButton jbtAvatar;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtDel;
    private javax.swing.JButton jbtDelCategory;
    private javax.swing.JButton jbtDownloadFromNaf;
    private javax.swing.JButton jbtEditRoster;
    private javax.swing.JButton jbtOK;
    private javax.swing.JButton jbtPinCode;
    private javax.swing.JButton jbtUpdateFromDb;
    private javax.swing.JComboBox jcbClan;
    private javax.swing.JComboBox jcbRoster;
    private javax.swing.JCheckBox jckActive;
    private javax.swing.JLabel jlbHandicap;
    private javax.swing.JLabel jlbNafRanking;
    private javax.swing.JList jlsCategories;
    private javax.swing.JList jlsCompositions;
    private javax.swing.JPanel jpnBtns;
    private javax.swing.JPanel jpnBtns1;
    private javax.swing.JTextField jtfEquipe;
    private javax.swing.JTextField jtfHandicap;
    private javax.swing.JTextField jtfNAF;
    private javax.swing.JTextField jtfNom;
    private javax.swing.JTextField jtfPinCode;
    private javax.swing.JTextField jtfRank;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(JdgCoach.class.getName());

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
