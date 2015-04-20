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
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import teamma.views.JdgRoster;
import tourma.data.Category;
import tourma.data.Coach;
import tourma.data.RosterType;
import tourma.data.Team;
import tourma.data.Tournament;
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
    public JdgCoach(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        mCoach = new Coach();
        mNew = true;
        initComponents();

        mTeamTournament = Tournament.getTournament().getParams().isTeamTournament();

        jcbRoster.setModel(RosterType.getRostersNamesModel());

        final DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            clanListModel.addElement(Tournament.getTournament().getClan(i).getName());
        }
        jcbClan.setModel(clanListModel);

        final DefaultComboBoxModel categoryListModel = new DefaultComboBoxModel();
        for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
            categoryListModel.addElement(Tournament.getTournament().getCategory(i).getName());
        }
        jcbCategory.setModel(categoryListModel);

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

        jcbCategory.setEnabled(Tournament.getTournament().getCategoriesCount() > 1);

        jbtEditRoster.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jbtAdd.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jpnBtns.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jlsCompositions.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        updatelist();

        jLabel4.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jtfNAF.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jlbNafRanking.setText(Double.toString(mCoach.getNafRank()));

        jcbRoster.setModel(RosterType.getRostersNamesModel());

        if (mCoach.getPicture() == null) {
            try {
                BufferedImage img = ImageIO.read(getClass().getResource("/tourma/images/avatar/60001.png"));
                mCoach.setPicture(img);
            } catch (IOException ex) {
                Logger.getLogger(JdgCoach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jbtAvatar.setIcon(ImageTreatment.resize(new ImageIcon(mCoach.getPicture()), 80, 80));

    }
    private Team mTeam = null;

    /**
     *
     * @param parent
     * @param modal
     * @param team
     */
    public JdgCoach(final java.awt.Frame parent, final boolean modal, final Team team) {
        super(parent, modal);
        mCoach = new Coach();
        initComponents();
        mNew = true;
        mTeam = team;
        mTeamTournament = Tournament.getTournament().getParams().isTeamTournament();

        jcbRoster.setModel(RosterType.getRostersNamesModel());

        final DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            clanListModel.addElement(Tournament.getTournament().getClan(i).getName());
        }
        jcbClan.setModel(clanListModel);

        final DefaultComboBoxModel categoryListModel = new DefaultComboBoxModel();
        for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
            categoryListModel.addElement(Tournament.getTournament().getCategory(i).getName());
        }
        jcbCategory.setModel(categoryListModel);

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

        jcbCategory.setEnabled(Tournament.getTournament().getCategoriesCount() > 1);

        jbtEditRoster.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jLabel4.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jtfNAF.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jlbNafRanking.setText(Double.toString(mCoach.getNafRank()));

        jbtAdd.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jpnBtns.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jlsCompositions.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        updatelist();

        jcbRoster.setModel(RosterType.getRostersNamesModel());
        if (mCoach.getPicture() == null) {
            try {
                mCoach.setPicture(ImageIO.read(getClass().getResource("/tourma/images/avatar/60001.gif")));
            } catch (IOException ex) {
                Logger.getLogger(JdgCoach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jbtAvatar.setIcon(ImageTreatment.resize(new ImageIcon(mTeam.getPicture()), 80, 80));
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
        if (mCoach.getPicture() == null) {
            try {
                coach.setPicture(ImageIO.read(getClass().getResource("/tourma/images/avatar/60001.png")));
            } catch (IOException ex) {
                Logger.getLogger(JdgCoach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        ImageIcon image;
        image = new ImageIcon(mCoach.getPicture());
        jbtAvatar.setIcon(ImageTreatment.resize(image, 80, 80));

        final DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            clanListModel.addElement(Tournament.getTournament().getClan(i).getName());
        }
        jcbClan.setModel(clanListModel);

        final DefaultComboBoxModel categoryListModel = new DefaultComboBoxModel();
        for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
            categoryListModel.addElement(Tournament.getTournament().getCategory(i).getName());
        }
        jcbCategory.setModel(categoryListModel);

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

        jckActive.setSelected(mCoach.isActive());

        jtfHandicap.setText(Integer.toString(mCoach.getHandicap()));

        if ((Tournament.getTournament().getParams().isEnableClans()) && (Tournament.getTournament().getClansCount() > 1)) {
            jcbClan.setEnabled(true);
            jcbClan.setSelectedItem(mCoach.getClan().getName());
        }

        if (Tournament.getTournament().getCategoriesCount() > 1) {
            jcbCategory.setEnabled(true);
            if (mCoach.getCategory() != null) {
                jcbCategory.setSelectedItem(mCoach.getCategory().getName());
            } else {
                jcbCategory.setSelectedIndex(0);
            }
        }

        jcbRoster.setSelectedItem(mCoach.getRoster().getName());
        if (mCoach.getClan() != null) {
            jcbClan.setSelectedItem(mCoach.getClan().getName());
        }

        jbtEditRoster.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jLabel4.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jtfNAF.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jlbNafRanking.setText(Double.toString(mCoach.getNafRank()));

        jbtAdd.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jpnBtns.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        jlsCompositions.setEnabled(Tournament.getTournament().getParams().getGame() == RosterType.C_BLOOD_BOWL);
        updatelist();

        if (coach.getPicture() == null) {
            try {
                coach.setPicture(ImageIO.read(getClass().getResource("/tourma/images/avatar/60001.gif")));
            } catch (IOException ex) {
                Logger.getLogger(JdgCoach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jbtAvatar.setIcon(ImageTreatment.resize(new ImageIcon(mCoach.getPicture()), 80, 80));

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
        jLabel8 = new javax.swing.JLabel();
        jcbCategory = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jckActive = new javax.swing.JCheckBox();
        jlbHandicap = new javax.swing.JLabel();
        jtfHandicap = new javax.swing.JTextField();
        jbtDownloadFromNaf = new javax.swing.JButton();
        jlbNafRanking = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jbtAvatar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jpnBtns = new javax.swing.JPanel();
        jbtAdd = new javax.swing.JButton();
        jbtDel = new javax.swing.JButton();
        jbtEditRoster = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlsCompositions = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(10, 2));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
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

        jtfRank.setText(bundle.getString("110")); // NOI18N
        jPanel1.add(jtfRank);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText(bundle.getString("ClanKey:")); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.getAccessibleContext().setAccessibleName(bundle.getString("ClanKey")); // NOI18N

        jPanel1.add(jcbClan);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText(bundle.getString("CategoryKey:")); // NOI18N
        jPanel1.add(jLabel8);

        jPanel1.add(jcbCategory);
        jPanel1.add(jLabel7);

        jckActive.setSelected(true);
        jckActive.setText(bundle.getString("Active")); // NOI18N
        jPanel1.add(jckActive);

        jlbHandicap.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbHandicap.setText(bundle.getString("HANDICAP")); // NOI18N
        jPanel1.add(jlbHandicap);

        jtfHandicap.setText(bundle.getString("110")); // NOI18N
        jPanel1.add(jtfHandicap);

        jbtDownloadFromNaf.setText(bundle.getString("DOWNLOAD FROM NAF")); // NOI18N
        jbtDownloadFromNaf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDownloadFromNafActionPerformed(evt);
            }
        });
        jPanel1.add(jbtDownloadFromNaf);

        jlbNafRanking.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbNafRanking.setText("150");
        jPanel1.add(jlbNafRanking);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
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

        jPanel3.setLayout(new java.awt.BorderLayout());

        jbtAvatar.setMnemonic('A');
        jbtAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAvatarActionPerformed(evt);
            }
        });
        jPanel3.add(jbtAvatar, java.awt.BorderLayout.NORTH);

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

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtCancelActionPerformed
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
                JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UN AUTRE COACH A DÉJÀ LE MÊME NOM"));
                error = true;
                break;
            }
        }

        if (!error) {

            c.setName(jtfNom.getText());
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

        try {
            Category cat = null;
            String tmp = (String) jcbCategory.getSelectedItem();
            for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
                if (Tournament.getTournament().getCategory(i).getName().equals(tmp)) {
                    cat = Tournament.getTournament().getCategory(i);
                    c.setCategory(cat);
                }
            }
        } catch (NumberFormatException e) {
            c.setCategory(Tournament.getTournament().getCategory(0));
        }

        if (!error) {
            if (c.getName().equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""))) {
                JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NameIsEmpty"));
                error = true;
            }
            if (c.getTeam().equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""))) {
                JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("TeamIsEmpty"));
                error = true;
            }
            if (c.getRoster().getName().equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""))) {
                JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("RosterIsEmpty"));
                error = true;
            }

            if (jcbClan.getSelectedIndex() > 0) {
                c.setClan(Tournament.getTournament().getClan(jcbClan.getSelectedIndex()));
            } else {
                c.setClan(Tournament.getTournament().getClan(0));
            }
        } else {
            c.setClan(Tournament.getTournament().getClan(0));

            if (jcbCategory.getSelectedIndex() > 0) {
                c.setCategory(Tournament.getTournament().getCategory(jcbCategory.getSelectedIndex()));
            } else {
                if (Tournament.getTournament().getCategoriesCount() > 0) {
                    c.setCategory(Tournament.getTournament().getCategory(0));
                }
            }
        }

        if (!error) {
            if (mNew) {
                if (mTeam != null) {
                    c.setTeamMates(mTeam);
                    mTeam.addCoach(c);
                }
                Tournament.getTournament().addCoach(c);
            }
            //c.setPicture(mCoach.getPicture());
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

            /*if (mCoach.mComposition != null) {
             mCoach.mRoster = new RosterType(mCoach.mComposition._roster._name);
             mCoach.mRank = mCoach.mComposition.getValue(false) / 10000;
             jtfRank.setText(Integer.toString(mCoach.mRank));
             }
             jcbRoster.setSelectedItem(mCoach.mRoster.mName);*/
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
        //mCoach.mRoster = new RosterType(jcbRoster.getSelectedIndex());
    }//GEN-LAST:event_jcbRosterActionPerformed

    private void jbtDownloadFromNafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDownloadFromNafActionPerformed

        double rank = NAF.getRanking(jtfNom.getText(), mCoach);
        jtfNAF.setText(Integer.toString(mCoach.getNaf()));
        jlbNafRanking.setText(Double.toString(rank));
    }//GEN-LAST:event_jbtDownloadFromNafActionPerformed

    /**
     * Update list
     */
    private void updatelist() {
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < mCoach.getCompositionCount(); i++) {
            teamma.data.Roster rost = mCoach.getComposition(i);
            if (rost != null) {
                model.addElement(rost.getRoster().getName());
            }
        }
        jlsCompositions.setModel(model);
    }

    private void jbtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddActionPerformed

            String input = (String) JOptionPane.showInputDialog(this,
                java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChooseRoster"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Roster's Choice"), JOptionPane.INFORMATION_MESSAGE,
                null, RosterType.getRostersNames(), "Amazons");
        teamma.data.RosterType rt = teamma.data.LRB.getLRB().getRosterType(input);
        if (rt != null) {
            teamma.data.Roster compo = new teamma.data.Roster();
            compo.setRoster(rt);
            mCoach.addComposition(compo);
            updatelist();
        } else {
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RostersChoiceError0") + input);
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
        File folder;
        folder = new File(getClass().getResource("/tourma/images/avatar").getFile());
        File[] listOfFiles = folder.listFiles();

        Object[] objects = new Object[listOfFiles.length + 1];

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String path = listOfFiles[i].getAbsolutePath();
                ImageIcon icon = new ImageIcon(path);
                objects[i] = ImageTreatment.resize(icon, 80, 80);
            } else if (listOfFiles[i].isDirectory()) {
                LOG.log(Level.INFO, "{0} {1}", new Object[]{java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIRECTORY"), listOfFiles[i].getName()});
            }
        }

        ImageIcon empty = new ImageIcon();
        objects[listOfFiles.length] = empty;

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
                this.mCoach.setPicture(new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB));
                Graphics g = this.mCoach.getPicture().createGraphics();
                // paint the Icon to the BufferedImage.
                icon.paintIcon(null, g, 0, 0);
                g.dispose();
            }
        } else {
            ImageIcon icon = (ImageIcon) combo.getSelectedItem();
            this.mCoach.setPicture(new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB));
            Graphics g = this.mCoach.getPicture().createGraphics();
            // paint the Icon to the BufferedImage.
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
        }

        jbtAvatar.setIcon(new ImageIcon(mCoach.getPicture()));
    }//GEN-LAST:event_jbtAvatarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtAvatar;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtDel;
    private javax.swing.JButton jbtDownloadFromNaf;
    private javax.swing.JButton jbtEditRoster;
    private javax.swing.JButton jbtOK;
    private javax.swing.JComboBox jcbCategory;
    private javax.swing.JComboBox jcbClan;
    private javax.swing.JComboBox jcbRoster;
    private javax.swing.JCheckBox jckActive;
    private javax.swing.JLabel jlbHandicap;
    private javax.swing.JLabel jlbNafRanking;
    private javax.swing.JList jlsCompositions;
    private javax.swing.JPanel jpnBtns;
    private javax.swing.JTextField jtfEquipe;
    private javax.swing.JTextField jtfHandicap;
    private javax.swing.JTextField jtfNAF;
    private javax.swing.JTextField jtfNom;
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
