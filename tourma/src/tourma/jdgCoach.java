/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgCoach.java
 *
 * Created on 10 mai 2010, 19:37:53
 */
package tourma;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import teamma.views.JdgRoster;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.RosterType;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.utility.StringConstants;
import tourma.utils.NAF;

/**
 *
 * @author Frederic Berger
 */
public class jdgCoach extends javax.swing.JDialog {

    boolean mTeamTournament;
    Coach mCoach;
    boolean mNew = false;

    /**
     * Creates new form jdgCoach
     */
    public jdgCoach(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        mCoach = new Coach();
        mNew = true;
        initComponents();

        mTeamTournament = Tournament.getTournament().getParams().mTeamTournament;

        jcbRoster.setModel(new DefaultComboBoxModel(RosterType.mRostersNames.toArray()));

        if (!mTeamTournament) {
            final DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
            for (int i = 0; i < Tournament.getTournament().getClans().size(); i++) {
                clanListModel.addElement(Tournament.getTournament().getClans().get(i).mName);
            }
            jcbClan.setModel(clanListModel);
        }

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();
        if (dmode != null) {
            final int screenWidth = dmode.getWidth();
            final int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        if (mTeamTournament) {
            jcbClan.setEnabled(false);
        } else {
            jcbClan.setEnabled(Tournament.getTournament().getParams().mEnableClans);
        }
        jbtEditRoster.setEnabled(Tournament.getTournament().getParams().mGame == RosterType.C_BLOOD_BOWL);
        jLabel4.setEnabled(Tournament.getTournament().getParams().mGame == RosterType.C_BLOOD_BOWL);
        jtfNAF.setEnabled(Tournament.getTournament().getParams().mGame == RosterType.C_BLOOD_BOWL);
        jlbNafRanking.setText(Double.toString(mCoach.mNafRank));

        jcbRoster.setModel(new javax.swing.DefaultComboBoxModel(RosterType.mRostersNames.toArray()));
    }
    Team mTeam = null;

    public jdgCoach(final java.awt.Frame parent, final boolean modal, final Team team) {
        super(parent, modal);
        mCoach = new Coach();
        initComponents();
        mNew = true;
        mTeam = team;
        mTeamTournament = Tournament.getTournament().getParams().mTeamTournament;

        jcbRoster.setModel(new DefaultComboBoxModel(RosterType.mRostersNames.toArray()));

        if (!mTeamTournament) {
            final DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
            for (int i = 0; i < Tournament.getTournament().getClans().size(); i++) {
                clanListModel.addElement(Tournament.getTournament().getClans().get(i).mName);
            }
            jcbClan.setModel(clanListModel);
        }

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();
        if (dmode != null) {
            final int screenWidth = dmode.getWidth();
            final int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        if (mTeamTournament) {
            jcbClan.setEnabled(false);
        } else {
            jcbClan.setEnabled(Tournament.getTournament().getParams().mEnableClans);
        }
        jbtEditRoster.setEnabled(Tournament.getTournament().getParams().mGame == RosterType.C_BLOOD_BOWL);
        jLabel4.setEnabled(Tournament.getTournament().getParams().mGame == RosterType.C_BLOOD_BOWL);
        jtfNAF.setEnabled(Tournament.getTournament().getParams().mGame == RosterType.C_BLOOD_BOWL);
        jlbNafRanking.setText(Double.toString(mCoach.mNafRank));

        
        jcbRoster.setModel(new javax.swing.DefaultComboBoxModel(RosterType.mRostersNames.toArray()));
    }

    /**
     * Creates new form jdgCoach form an existing coach
     */
    public jdgCoach(final java.awt.Frame parent, final boolean modal, final Coach coach) {
        super(parent, modal);
        mCoach = new Coach();
        initComponents();

        jcbRoster.setModel(new DefaultComboBoxModel(RosterType.mRostersNames.toArray()));
        mCoach = coach;
        if (!mTeamTournament) {
            final DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
            for (int i = 0; i < Tournament.getTournament().getClans().size(); i++) {
                clanListModel.addElement(Tournament.getTournament().getClans().get(i).mName);
            }
            jcbClan.setModel(clanListModel);
        }

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();
        if (dmode != null) {
            final int screenWidth = dmode.getWidth();
            final int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }


        jtfEquipe.setText(mCoach.mTeam);
        jtfNAF.setText(Integer.toString(mCoach.mNaf));
        jtfNom.setText(mCoach.mName);
        jcbRoster.setSelectedItem(mCoach.mRoster.mName);

        jckActive.setSelected(mCoach.mActive);

        jtfHandicap.setText(Integer.toString(mCoach.mHandicap));

        if (!mTeamTournament) {
            if (mTeamTournament) {
                jcbClan.setEnabled(false);
            } else {
                if (Tournament.getTournament().getParams().mEnableClans) {
                    jcbClan.setEnabled(true);
                    jcbClan.setSelectedItem(mCoach.mClan.mName);
                }
            }
        }
        jcbRoster.setSelectedItem(mCoach.mRoster.mName);
        if (mCoach.mClan != null) {
            jcbClan.setSelectedItem(mCoach.mClan.mName);
        }

        jbtEditRoster.setEnabled(Tournament.getTournament().getParams().mGame == RosterType.C_BLOOD_BOWL);
        jLabel4.setEnabled(Tournament.getTournament().getParams().mGame == RosterType.C_BLOOD_BOWL);
        jtfNAF.setEnabled(Tournament.getTournament().getParams().mGame == RosterType.C_BLOOD_BOWL);
        jlbNafRanking.setText(Double.toString(mCoach.mNafRank));
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
        jbtEditRoster = new javax.swing.JButton();
        jckActive = new javax.swing.JCheckBox();
        jlbHandicap = new javax.swing.JLabel();
        jtfHandicap = new javax.swing.JTextField();
        jbtDownloadFromNaf = new javax.swing.JButton();
        jlbNafRanking = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(9, 2));

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

        jcbRoster.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Amazone", "Bas-Fonds", "Chaos", "Elfe", "Elfe Sylvain", "Elfe Noir", "Gobelin", "Halfling", "Haut Elfe", "Homme lézard", "Humain", "Khemri", "Mort-Vivant", "Nain", "Nain du chaos", "Necromantique", "Nordique", "Nurgle", "Ogre", "Orque", "Pacte Chaotique", "Skaven", "Slann", "Vampire" }));
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

        jbtEditRoster.setText(bundle.getString("EDITER ROSTER")); // NOI18N
        jbtEditRoster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditRosterActionPerformed(evt);
            }
        });
        jPanel1.add(jbtEditRoster);

        jckActive.setSelected(true);
        jckActive.setText(bundle.getString("Active")); // NOI18N
        jPanel1.add(jckActive);

        jlbHandicap.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbHandicap.setText(bundle.getString("HANDICAP")); // NOI18N
        jPanel1.add(jlbHandicap);

        jtfHandicap.setText(bundle.getString("0")); // NOI18N
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

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

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

        for (int i = 0; i < Tournament.getTournament().getCoachs().size(); i++) {
            final Coach tmp = Tournament.getTournament().getCoachs().get(i);
            if ((tmp.mName.equals(jtfNom.getText())) && (!tmp.equals(c))) {
                JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UN AUTRE COACH A DÉJÀ LE MÊME NOM"));
                error = true;
                break;
            }
        }


        c.mName = jtfNom.getText();
        c.mRoster = new RosterType(jcbRoster.getSelectedIndex());
        c.mTeam = jtfEquipe.getText();
        c.mActive = jckActive.isSelected();
        try {
            c.mNaf = Integer.parseInt(jtfNAF.getText());
        } catch (NumberFormatException e) {
            c.mNaf = 0;
        }
        try {
            c.mRank = Integer.parseInt(jtfRank.getText());
        } catch (NumberFormatException e) {
            c.mRank = 110;
        }

        try {
            c.mHandicap = Integer.parseInt(jtfHandicap.getText());
        } catch (NumberFormatException e) {
            c.mHandicap = 0;
        }

        if (c.mName.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""))) {
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NameIsEmpty"));
            error = true;
        }
        if (c.mTeam.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""))) {
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("TeamIsEmpty"));
            error = true;
        }
        if (c.mRoster.mName.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""))) {
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("RosterIsEmpty"));
            error = true;
        }

        if (!mTeamTournament) {
            if (jcbClan.getSelectedIndex() > 0) {
                c.mClan = (Clan) Tournament.getTournament().getClans().get(jcbClan.getSelectedIndex());
            } else {
                c.mClan = (Clan) Tournament.getTournament().getClans().get(0);
            }
        } else {
            c.mClan = Tournament.getTournament().getClans().get(0);
        }

        if (!error) {
            if (mNew) {
                if (mTeam != null) {
                    c.mTeamMates = mTeam;
                    mTeam.mCoachs.add(c);
                }
                Tournament.getTournament().getCoachs().add(c);
            }
        }


        this.setVisible(false);

    }//GEN-LAST:event_jbtOKActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtEditRosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditRosterActionPerformed

        final teamma.views.JdgRoster window = new JdgRoster(MainFrame.getMainFrame(), mCoach, true);
        window.setVisible(true);

        if (mCoach.mComposition != null) {
            mCoach.mRoster = new RosterType(mCoach.mComposition._roster._name);
            mCoach.mRank = mCoach.mComposition.getValue(false) / 10000;
            jtfRank.setText(Integer.toString(mCoach.mRank));
        }
        jcbRoster.setSelectedItem(mCoach.mRoster.mName);

    }//GEN-LAST:event_jbtEditRosterActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtfNomPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jtfNomPropertyChange
        mCoach.mName = jtfNom.getText();
    }//GEN-LAST:event_jtfNomPropertyChange
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRosterPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jcbRosterPropertyChange
        mCoach.mRoster = new RosterType(jcbRoster.getSelectedIndex());
    }//GEN-LAST:event_jcbRosterPropertyChange
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtfNomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNomKeyPressed
        mCoach.mName = jtfNom.getText();
    }//GEN-LAST:event_jtfNomKeyPressed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRosterActionPerformed
        mCoach.mRoster = new RosterType(jcbRoster.getSelectedIndex());
    }//GEN-LAST:event_jcbRosterActionPerformed

    private void jbtDownloadFromNafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDownloadFromNafActionPerformed

        double rank = NAF.GetRanking(jtfNom.getText(), mCoach);
        jtfNAF.setText(Integer.toString(mCoach.mNaf));
        jlbNafRanking.setText(Double.toString(rank));
    }//GEN-LAST:event_jbtDownloadFromNafActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtDownloadFromNaf;
    private javax.swing.JButton jbtEditRoster;
    private javax.swing.JButton jbtOK;
    private javax.swing.JComboBox jcbClan;
    private javax.swing.JComboBox jcbRoster;
    private javax.swing.JCheckBox jckActive;
    private javax.swing.JLabel jlbHandicap;
    private javax.swing.JLabel jlbNafRanking;
    private javax.swing.JTextField jtfEquipe;
    private javax.swing.JTextField jtfHandicap;
    private javax.swing.JTextField jtfNAF;
    private javax.swing.JTextField jtfNom;
    private javax.swing.JTextField jtfRank;
    // End of variables declaration//GEN-END:variables
}
