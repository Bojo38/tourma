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
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;

/**
 *
 * @author Administrateur
 */
public class JdgChangePairing extends JDialog implements ActionListener {

    Round mRound;
    ArrayList<JComboBox> mPlayersSelected;
    ArrayList<Competitor> mPlayers;
    ArrayList<Competitor> mPlayersTmp;

    /**
     * Creates new form jdgChangePairing
     */
    public JdgChangePairing(final java.awt.Frame parent, final boolean modal, final Round round) {
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
            final Match m = mRound.getMatchs().get(i);
            mPlayers.add(m.mCompetitor1);
            mPlayers.add(m.mCompetitor2);
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
                Match m = mRound.getMatchs().get(i);                

                if (m instanceof TeamMatch)
                {
                    Team t1=(Team)mPlayersTmp.get(2 * i);
                    Team t2=(Team)mPlayersTmp.get(2 * i+1);
                    if ((m.mCompetitor1!=t1)||
                            (m.mCompetitor2!=t2))
                    {                        
                        // Remove matchs from coachs
                        for (int j=0; j<((TeamMatch)m).mMatchs.size(); j++)
                        {
                            CoachMatch cm=((TeamMatch)m).mMatchs.get(j);
                            for (int k=0; k<((Team)m.mCompetitor1).mCoachs.size(); k++)
                            {
                                Coach c=((Team)m.mCompetitor1).mCoachs.get(k);
                                c.mMatchs.remove(cm);
                            }
                            
                            for (int k=0; k<((Team)m.mCompetitor2).mCoachs.size(); k++)
                            {
                                Coach c=((Team)m.mCompetitor2).mCoachs.get(k);
                                c.mMatchs.remove(cm);
                            }
                        }
                        
                        ((TeamMatch)m).mMatchs.clear();
                        JdgPairing jdg=new JdgPairing(MainFrame.getMainFrame(), true, 
                                t1,t2, mRound, ((TeamMatch)m).mMatchs);
                        jdg.setVisible(true);
                        
                        for (int j=0; j<((TeamMatch)m).mMatchs.size(); j++)
                        {
                            CoachMatch cm=((TeamMatch)m).mMatchs.get(j);
                            cm.mCompetitor1.mMatchs.add(cm);
                            cm.mCompetitor2.mMatchs.add(cm);                            
                        }
                    }
                }
                                
                m.mCompetitor1 = mPlayersTmp.get(2 * i);
                m.mCompetitor2 = mPlayersTmp.get(2 * i + 1);
                
                m.mCompetitor1.mMatchs.remove(m.mCompetitor1.mMatchs.get( m.mCompetitor1.mMatchs.size() - 1));
                m.mCompetitor2.mMatchs.remove(m.mCompetitor2.mMatchs.get(m.mCompetitor2.mMatchs.size() - 1));
                
                m.mCompetitor1.mMatchs.add(m);
                m.mCompetitor2.mMatchs.add(m);
            
        }

        this.setVisible(false);
    }
    }//GEN-LAST:event_jbtOKActionPerformed
@Override
        public void actionPerformed(final ActionEvent e) {
        final JComboBox jcb = (JComboBox) e.getSource();

        Competitor oldCoach = null;
        Competitor newCoach;

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

    /**
     *
     */
    public void update() {

        final ArrayList<String> playersNames = new ArrayList<>();
        for (int i = 0; i < mPlayersTmp.size(); i++) {

            String name = mPlayersTmp.get(i).getDecoratedName();
            playersNames.add(name);
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
    private static final Logger LOG = Logger.getLogger(JdgChangePairing.class.getName());
}
