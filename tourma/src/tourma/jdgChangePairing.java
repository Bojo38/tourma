/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgChangePairing.java
 *
 * Created on 7 mai 2011, 10:51:43
 */
package tourma;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Round;

/**
 *
 * @author Administrateur
 */
public class jdgChangePairing extends JDialog implements ActionListener {

    Round mRound;
    ArrayList<JComboBox> mPlayersSelected;
    ArrayList<Coach> mPlayers;
    ArrayList<Coach> mPlayersTmp;

    /**
     * Creates new form jdgChangePairing
     */
    public jdgChangePairing(final java.awt.Frame parent, final boolean modal, final Round round) {
        super(parent, modal);
        initComponents();

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();

        this.setSize(640, 480);

        if (dmode != null) {
            final int screenWidth = dmode.getWidth();
            final int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        mRound = round;
        mPlayersSelected = new ArrayList<>();
        mPlayers = new ArrayList<>();

        for (int i = 0; i < mRound.getMatchs().size(); i++) {
            final CoachMatch m = mRound.getCoachMatchs().get(i);
            mPlayers.add((Coach)m.mCompetitor1);
            mPlayers.add((Coach)m.mCompetitor2);
        }

        mPlayersTmp = mPlayers;

        final GridLayout lay = new GridLayout(mRound.getMatchs().size(), 2);
        jpnMatchs.setLayout(lay);

        update();
    }

    @SuppressWarnings({"PMD.MethodArgumentCouldBeFinal", "PMD.LocalVariableCouldBeFinal"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();
        jsp = new javax.swing.JScrollPane();
        jpnMatchs = new javax.swing.JPanel();

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        jbtCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Halt.png"))); // NOI18N
        jbtCancel.setText(bundle.getString("ANNULER")); // NOI18N
        jbtCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jbtCancel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jsp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jpnMatchs.setBorder(javax.swing.BorderFactory.createTitledBorder("Matchs"));

        javax.swing.GroupLayout jpnMatchsLayout = new javax.swing.GroupLayout(jpnMatchs);
        jpnMatchs.setLayout(jpnMatchsLayout);
        jpnMatchsLayout.setHorizontalGroup(
            jpnMatchsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );
        jpnMatchsLayout.setVerticalGroup(
            jpnMatchsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );

        jsp.setViewportView(jpnMatchs);

        getContentPane().add(jsp, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelActionPerformed
    this.setVisible(false);
    }//GEN-LAST:event_jbtCancelActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed

    final int result = JOptionPane.showConfirmDialog(this, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VOULEZ VOUS CONFIRMEER LE NOUVEL APPARIEMENT ?"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CONFIRMER"), JOptionPane.YES_NO_OPTION);
    if (result == JOptionPane.YES_OPTION) {

        for (int i = 0; i < mRound.getMatchs().size(); i++) {
            final CoachMatch m = mRound.getCoachMatchs().get(i);
            m.mCompetitor1 = mPlayersTmp.get(2 * i);
            m.mCompetitor2 = mPlayersTmp.get(2 * i + 1);

            ((Coach)m.mCompetitor1).mMatchs.remove(((Coach)m.mCompetitor1).mMatchs.get(((Coach)m.mCompetitor1).mMatchs.size() - 1));
            ((Coach)m.mCompetitor2).mMatchs.remove(((Coach)m.mCompetitor2).mMatchs.get(((Coach)m.mCompetitor2).mMatchs.size() - 1));
            ((Coach)m.mCompetitor1).mMatchs.add(m);
            ((Coach)m.mCompetitor2).mMatchs.add(m);
        }

        this.setVisible(false);
    }
    }//GEN-LAST:event_jbtOKActionPerformed

    @Override
    public void actionPerformed(final ActionEvent e) {
       final  JComboBox jcb = (JComboBox) e.getSource();
        
        Coach oldCoach = null;
        Coach newCoach;

        newCoach = mPlayersTmp.get(jcb.getSelectedIndex());
        for (int i = 0; i < mPlayersSelected.size(); i++) {
            if (mPlayersSelected.get(i) == jcb) {
                oldCoach = mPlayersTmp.get(i);
            }
        }

        for (int i = 0; i < mPlayersTmp.size(); i++) {
            if (mPlayersTmp.get(i) == newCoach) {
                mPlayersTmp.set(i, oldCoach);
            } else {
                if (mPlayersTmp.get(i) == oldCoach) {
                    mPlayersTmp.set(i, newCoach);
                }
            }
        }


        update();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtOK;
    private javax.swing.JPanel jpnMatchs;
    private javax.swing.JScrollPane jsp;
    // End of variables declaration//GEN-END:variables

    public void update() {

        final ArrayList<String> playersNames = new ArrayList<>();
        for (int i = 0; i < mPlayersTmp.size(); i++) {
            playersNames.add(mPlayersTmp.get(i).mName + java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" - {0} ({1})"), new Object[] {mPlayersTmp.get(i).mTeam, mPlayersTmp.get(i).mRoster.mName}));
        }

        mPlayersSelected.clear();

        for (int i = 0; i < mPlayers.size(); i++) {
            final JComboBox jcb = new JComboBox(playersNames.toArray());
            jcb.setSelectedIndex(i);
            jcb.addActionListener(this);
            mPlayersSelected.add(jcb);
        }

        jpnMatchs.setBorder(javax.swing.BorderFactory.createTitledBorder(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCHS")));

        jpnMatchs.removeAll();
        for (int i = 0; i < mPlayersSelected.size(); i++) {
            jpnMatchs.add(mPlayersSelected.get(i));
        }

        jsp.setViewportView(jpnMatchs);
        repaint();
    }
}
