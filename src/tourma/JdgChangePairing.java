/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * JdgChangePairing.java
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
import tourma.data.Competitor;
import tourma.data.IContainCoachs;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.languages.Translate;

/**
 *
 * @author Administrateur
 */
public final class JdgChangePairing extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    private final Round mRound;
    private ArrayList<JComboBox> mPlayersSelected;
    private ArrayList<Competitor> mPlayers;
    private ArrayList<Competitor> mPlayersTmp;

    /**
     * Creates new form jdgChangePairing
     *
     * @param parent
     * @param modal
     * @param round
     */
    public JdgChangePairing(final java.awt.Frame parent, final boolean modal, final Round round) {
        super(parent, modal);
        initComponents();

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();

        this.setSize(640, 480);

        final int screenWidth = dmode.getWidth();
        final int screenHeight = dmode.getHeight();
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

        mRound = round;
        mPlayersSelected = new ArrayList<>();
        mPlayers = new ArrayList<>();

        for (int i = 0; i < mRound.getMatchsCount(); i++) {
            final Match m = mRound.getMatch(i);
            mPlayers.add(m.getCompetitor1());
            mPlayers.add(m.getCompetitor2());
        }

        mPlayersTmp = mPlayers;

        final GridLayout lay = new GridLayout(mRound.getMatchsCount(), 2);
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
        jbtOK.setName("ok"); // NOI18N
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

        jpnMatchs.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Matchs"))); // NOI18N
        jpnMatchs.setName("jpnMatchs"); // NOI18N

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

        final int result = JOptionPane.showConfirmDialog(this,
                Translate.translate(CS_DoYouConfirmNewpairing),
                Translate.translate(CS_Confirm),
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {

            for (int i = 0; i < mRound.getMatchsCount(); i++) {
                Match m = mRound.getMatch(i);

                if (m instanceof TeamMatch) {
                    Team t1 = (Team) mPlayersTmp.get(2 * i);
                    Team t2 = (Team) mPlayersTmp.get(2 * i + 1);
                 
                    ArrayList<Coach> list1=new ArrayList<>();
                    ArrayList<Coach> list2=new ArrayList<>();
                    
                    if ((m.getCompetitor1() != t1)
                            || (m.getCompetitor2() != t2)) {
                        // Remove matchs from coachs
                        for (int j = 0; j < ((TeamMatch) m).getMatchCount(); j++) {
                            CoachMatch cm = ((TeamMatch) m).getMatch(j);
                            for (int k = 0; k < ((IContainCoachs) m.getCompetitor1()).getCoachsCount(); k++) {
                                Coach c = ((IContainCoachs) m.getCompetitor1()).getCoach(k);                                
                                c.removeMatch(cm);
                            }

                            for (int k = 0; k < ((IContainCoachs) m.getCompetitor2()).getCoachsCount(); k++) {
                                Coach c = ((IContainCoachs) m.getCompetitor2()).getCoach(k);                                
                                c.removeMatch(cm);
                            }
                        }

                        ((TeamMatch) m).clearMatchs();
                        JdgPairing jdg = new JdgPairing(MainFrame.getMainFrame(), true,
                                t1, t2, mRound, (TeamMatch) m);
                        jdg.setVisible(true);

                        for (int j = 0; j < ((TeamMatch) m).getMatchCount(); j++) {
                            CoachMatch cm = ((TeamMatch) m).getMatch(j);
                            cm.getCompetitor1().addMatch(cm);
                            cm.getCompetitor2().addMatch(cm);
                        }
                    }
                }

                m.setCompetitor1(mPlayersTmp.get(2 * i));
                m.setCompetitor2(mPlayersTmp.get(2 * i + 1));

                Match tmp1 = m.getCompetitor1().getMatch(m.getCompetitor1().getMatchCount() - 1);
                m.getCompetitor1().removeMatch(tmp1);
                Competitor c2 = m.getCompetitor2();
                if (c2 == null) {
                    System.err.println("Null Coach detected");
                }
                Match tmp2 = m.getCompetitor2().getMatch(m.getCompetitor2().getMatchCount() - 1);
                m.getCompetitor2().removeMatch(tmp2);

                m.getCompetitor1().addMatch(m);
                m.getCompetitor2().addMatch(m);

            }

            this.setVisible(false);
        }
    }//GEN-LAST:event_jbtOKActionPerformed
    @Override
    public void actionPerformed(final ActionEvent e) {
        final JComboBox jcb = (JComboBox) e.getSource();

        Competitor oldCoach = null;
        Competitor newCoach;

        int newCoachIndex = jcb.getSelectedIndex();
        int oldCoachIndex = mPlayersSelected.indexOf(jcb);
        if (oldCoachIndex >= 0) {
            newCoach = mPlayersTmp.get(newCoachIndex);
            oldCoach = mPlayersTmp.get(oldCoachIndex);
            /*for (int i = 0; i < mPlayersSelected.size(); i++) {
            if (mPlayersSelected.get(i) == jcb) {
                oldCoach = mPlayersTmp.get(i);
                oldCoachIndex=i;
                break;
            }
        }*/

            mPlayersTmp.set(oldCoachIndex, newCoach);

            mPlayersTmp.set(newCoachIndex, oldCoach);
        }
        /*for (int i = 0; i < mPlayersTmp.size(); i++) {
            if (mPlayersTmp.get(i) == newCoach) {
                mPlayersTmp.set(i, oldCoach);
            } else {
                if (mPlayersTmp.get(i) == oldCoach) {
                    mPlayersTmp.set(i, newCoach);
                }
            }
        }*/

        update();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtOK;
    private javax.swing.JPanel jpnMatchs;
    private javax.swing.JScrollPane jsp;
    // End of variables declaration//GEN-END:variables

    /**
     * Update Panel
     */
    protected void update() {

        final ArrayList<String> playersNames = new ArrayList<>();
        for (Competitor mPlayersTmp1 : mPlayersTmp) {
            if (mPlayersTmp1 == null) {
                System.err.println("Null coach detected");
            } else {

                String name = mPlayersTmp1.getDecoratedName();
                playersNames.add(name);

            }
        }

        jpnMatchs.setBorder(javax.swing.BorderFactory.createTitledBorder(
                Translate.translate(CS_Matchs)
        ));

        jpnMatchs.removeAll();

        mPlayersSelected.clear();

        for (int i = 0; i < mPlayers.size(); i++) {
            if (mPlayers.get(i) != null) {
                final JComboBox<Object> jcb = new JComboBox<>(playersNames.toArray());
                jcb.setSelectedIndex(i);
                jcb.addActionListener(this);
                jcb.setName("jcb" + i);
                mPlayersSelected.add(jcb);
            }
        }

        for (int i = 0; i < mPlayersSelected.size(); i++) {
            jpnMatchs.add(mPlayersSelected.get(i));
        }

        jsp.setViewportView(jpnMatchs);
        repaint();
    }

    private static final String CS_Matchs = "MATCHS";
    private static final String CS_Confirm = "CONFIRMER";
    private static final String CS_DoYouConfirmNewpairing = "VOULEZ VOUS CONFIRMER LE NOUVEL APPARIEMENT ?";

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
