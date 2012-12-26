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
import tourma.data.Tournament;
import tourma.data.Coach;
import javax.swing.JOptionPane;
import teamma.views.JdgRoster;
import tourma.data.Clan;
import tourma.data.RosterType;
import tourma.data.Team;

/**
 *
 * @author Frederic Berger
 */
public class jdgCoach extends javax.swing.JDialog {

    boolean _teamTournament;
    Coach _coach;
    boolean _new=false;

    /** Creates new form jdgCoach */
    public jdgCoach(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        _coach = new Coach();
        _new=true;
        initComponents();

        _teamTournament = Tournament.getTournament().getParams()._teamTournament;

        jcbRoster.setModel(new DefaultComboBoxModel(RosterType.RostersNames.toArray()));

        if (!_teamTournament) {
            DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
            for (int i = 0; i < Tournament.getTournament().getClans().size(); i++) {
                clanListModel.addElement(Tournament.getTournament().getClans().get(i)._name);
            }
            jcbClan.setModel(clanListModel);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();
        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        if (_teamTournament) {
            jcbClan.setEnabled(false);
        } else {
            jcbClan.setEnabled(Tournament.getTournament().getParams()._enableClans);
        }
        
    }
    Team _team = null;

    public jdgCoach(java.awt.Frame parent, boolean modal, Team team) {
        super(parent, modal);
        _coach = new Coach();
        initComponents();
        _new=true;
        _team = team;
        _teamTournament = Tournament.getTournament().getParams()._teamTournament;

        jcbRoster.setModel(new DefaultComboBoxModel(RosterType.RostersNames.toArray()));

        if (!_teamTournament) {
            DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
            for (int i = 0; i < Tournament.getTournament().getClans().size(); i++) {
                clanListModel.addElement(Tournament.getTournament().getClans().get(i)._name);
            }
            jcbClan.setModel(clanListModel);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();
        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        if (_teamTournament) {
            jcbClan.setEnabled(false);
        } else {
            jcbClan.setEnabled(Tournament.getTournament().getParams()._enableClans);
        }

    }

    /** Creates new form jdgCoach form an existing coach */
    public jdgCoach(java.awt.Frame parent, boolean modal, Coach coach) {
        super(parent, modal);
        _coach = coach;
        initComponents();

        jcbRoster.setModel(new DefaultComboBoxModel(RosterType.RostersNames.toArray()));

        if (!_teamTournament) {
            DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
            for (int i = 0; i < Tournament.getTournament().getClans().size(); i++) {
                clanListModel.addElement(Tournament.getTournament().getClans().get(i)._name);
            }
            jcbClan.setModel(clanListModel);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();
        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }


        jtfEquipe.setText(_coach._team);
        jtfNAF.setText(Integer.toString(_coach._naf));
        jtfNom.setText(_coach._name);
        jcbRoster.setSelectedItem(_coach._roster._name);

        jckActive.setSelected(_coach._active);

        if (!_teamTournament) {
            if (_teamTournament) {
                jcbClan.setEnabled(false);
            } else {
                if (Tournament.getTournament().getParams()._enableClans) {
                    jcbClan.setEnabled(true);
                    jcbClan.setSelectedItem(_coach._clan._name);
                }
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
        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(7, 2));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jLabel1.setText(bundle.getString("CoachNameKey")); // NOI18N
        jPanel1.add(jLabel1);

        jtfNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNomActionPerformed(evt);
            }
        });
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

        jcbRoster.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Amazone", "Bas Fonds", "Chaos", "Elfe", "Elfe sylvain", "Elfe noir", "Gobelin", "Halfling", "Haut Elfe", "Homme lézard", "Humain", "Khemri", "Mort-Vivant", "Nain", "Nain du chaos", "Necromantique", "Nordique", "Nurgle", "Ogre", "Orque", "Pacte Chaotique", "Skaven", "Slann", "Vampire" }));
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

        jbtEditRoster.setText("Editer Roster");
        jbtEditRoster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditRosterActionPerformed(evt);
            }
        });
        jPanel1.add(jbtEditRoster);

        jckActive.setSelected(true);
        jckActive.setText(bundle.getString("Active")); // NOI18N
        jPanel1.add(jckActive);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        jbtOK.setText("OK");
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

    private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtCancelActionPerformed

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        Coach c;
        if (_coach == null) {
            c = new Coach();
        } else {
            c = _coach;
        }

        for (int i = 0; i < Tournament.getTournament().getCoachs().size(); i++) {
            Coach tmp = Tournament.getTournament().getCoachs().get(i);
            if ((tmp._name.equals(jtfNom.getText())) && (!tmp.equals(c))) {
                JOptionPane.showMessageDialog(this, "Un autre coach a déjà le même nom");
                return;
            }
        }

        c._name = jtfNom.getText();
        c._roster = new RosterType(jcbRoster.getSelectedIndex());
        c._team = jtfEquipe.getText();
        c._active = jckActive.isSelected();
        try {
            c._naf = Integer.parseInt(jtfNAF.getText());
        } catch (NumberFormatException e) {
            c._naf = 0;
        }
        try {
            c._rank = Integer.parseInt(jtfRank.getText());
        } catch (NumberFormatException e) {
            c._rank = 110;
        }

        if (c._name.equals("")) {
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NameIsEmpty"));
            return;
        }
        if (c._team.equals("")) {
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TeamIsEmpty"));
            return;
        }
        if (c._roster._name.equals("")) {
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RosterIsEmpty"));
            return;
        }

        if (!_teamTournament) {
            if (jcbClan.getSelectedIndex() > 0) {
                c._clan = (Clan) Tournament.getTournament().getClans().get(jcbClan.getSelectedIndex());
            } else {
                c._clan = (Clan) Tournament.getTournament().getClans().get(0);
            }
        } else {
            c._clan = Tournament.getTournament().getClans().get(0);
        }

        if (_new) {
            if (_team != null) {
                c._teamMates = _team;
                _team._coachs.add(c);
            }
            Tournament.getTournament().getCoachs().add(c);
        }

        this.setVisible(false);

    }//GEN-LAST:event_jbtOKActionPerformed

    private void jbtEditRosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditRosterActionPerformed
        
        teamma.views.JdgRoster window=new JdgRoster(MainFrame.getMainFrame(),_coach,true);
        window.setVisible(true);
        
        if (_coach._composition!=null)
        {
            _coach._roster=new RosterType(_coach._composition._roster._name);
        }
        jcbRoster.setSelectedItem(_coach._roster._name);
    }//GEN-LAST:event_jbtEditRosterActionPerformed

    private void jtfNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNomActionPerformed

    private void jtfNomPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jtfNomPropertyChange
       _coach._name=jtfNom.getText();
    }//GEN-LAST:event_jtfNomPropertyChange

    private void jcbRosterPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jcbRosterPropertyChange
        _coach._roster= new RosterType(jcbRoster.getSelectedIndex());
    }//GEN-LAST:event_jcbRosterPropertyChange

    private void jtfNomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNomKeyPressed
        _coach._name=jtfNom.getText();
    }//GEN-LAST:event_jtfNomKeyPressed

    private void jcbRosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRosterActionPerformed
         _coach._roster= new RosterType(jcbRoster.getSelectedIndex());
    }//GEN-LAST:event_jcbRosterActionPerformed

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
    private javax.swing.JButton jbtEditRoster;
    private javax.swing.JButton jbtOK;
    private javax.swing.JComboBox jcbClan;
    private javax.swing.JComboBox jcbRoster;
    private javax.swing.JCheckBox jckActive;
    private javax.swing.JTextField jtfEquipe;
    private javax.swing.JTextField jtfNAF;
    private javax.swing.JTextField jtfNom;
    private javax.swing.JTextField jtfRank;
    // End of variables declaration//GEN-END:variables
}
