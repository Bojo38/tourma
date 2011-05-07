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
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import tourma.data.Coach;
import tourma.data.Match;
import tourma.data.Round;

/**
 *
 * @author Administrateur
 */
public class jdgChangePairing extends JDialog implements ActionListener {

    Round _round;
    Vector<JComboBox> _playersSelected;
    Vector<Coach> _players;

    /** Creates new form jdgChangePairing */
    public jdgChangePairing(java.awt.Frame parent, boolean modal, Round round) {
        super(parent, modal);
        initComponents();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();
        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        _round = round;
        _playersSelected = new Vector<JComboBox>();
        _players = new Vector<Coach>();

        for (int i = 0; i < _round.getMatchs().size(); i++) {
            Match m = _round.getMatchs().get(i);
            _players.add(m._coach1);
            _players.add(m._coach2);
        }
        update();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpnMatchs = new javax.swing.JPanel();

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        jbtCancel.setText("Annuler");
        jbtCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jbtCancel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jpnMatchs.setBorder(javax.swing.BorderFactory.createTitledBorder("Matchs"));

        javax.swing.GroupLayout jpnMatchsLayout = new javax.swing.GroupLayout(jpnMatchs);
        jpnMatchs.setLayout(jpnMatchsLayout);
        jpnMatchsLayout.setHorizontalGroup(
            jpnMatchsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );
        jpnMatchsLayout.setVerticalGroup(
            jpnMatchsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jpnMatchs);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtCancelActionPerformed

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed

        int result = JOptionPane.showConfirmDialog(this, "Voulez vous confirmeer le nouvel appariement ?", "Confirmer", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {

            for (int i=0; i<_round.getMatchs().size(); i++)
            {
                Match m=_round.getMatchs().get(i);
                m._coach1=_players.get(2*i);
                m._coach2=_players.get(2*i+1);
            }
            
            this.setVisible(false);
        }
    }//GEN-LAST:event_jbtOKActionPerformed

    public void actionPerformed(ActionEvent e) {
        JComboBox jcb = (JComboBox) e.getSource();
        int index = 0;
        Coach oldCoach = null;
        Coach newCoach = null;
        Coach tmpCoach = null;

        newCoach = _players.get(jcb.getSelectedIndex());
        for (int i = 0; i < _playersSelected.size(); i++) {
            if (_playersSelected.get(i) == jcb) {
                index = i;
                oldCoach = _players.get(i);
            }
        }

        _playersSelected.get(jcb.getSelectedIndex()).removeActionListener(this);
        _playersSelected.get(jcb.getSelectedIndex()).setSelectedIndex(index);
        _playersSelected.get(jcb.getSelectedIndex()).addActionListener(this);

        for (int i = 0; i < _players.size(); i++) {
            if (_players.get(i) == newCoach) {
                _players.set(i, oldCoach);
            } else {
                if (_players.get(i) == oldCoach) {
                    _players.set(i, newCoach);
                }
            }
        }


        update();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtOK;
    private javax.swing.JPanel jpnMatchs;
    // End of variables declaration//GEN-END:variables

    public void update() {
        jpnMatchs.removeAll();

        Vector<String> playersNames = new Vector<String>();
        for (int i = 0; i < _players.size(); i++) {
            playersNames.add(_players.get(i)._name + " - " + _players.get(i)._team + " (" + _players.get(i)._roster + ")");
        }

        for (int i = 0; i < _players.size(); i++) {
            JComboBox jcb = new JComboBox(playersNames);
            jcb.setSelectedIndex(i);
            jcb.addActionListener(this);
            _playersSelected.add(jcb);
        }

        jpnMatchs.setLayout(new GridLayout(_round.getMatchs().size(), 2));
        for (int i = 0; i < _playersSelected.size(); i++) {
            jpnMatchs.add(_playersSelected.get(i));
        }
    }
}
