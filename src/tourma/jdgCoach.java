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

import tourma.data.Tournament;
import tourma.data.Coach;
import javax.swing.JOptionPane;

/**
 *
 * @author Frederic Berger
 */
public class jdgCoach extends javax.swing.JDialog {

    /** Creates new form jdgCoach */
    public jdgCoach(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        _coach=null;
    }

    Coach _coach;
    /** Creates new form jdgCoach */
    public jdgCoach(java.awt.Frame parent, boolean modal,Coach coach) {
        super(parent, modal);
        initComponents();
        _coach=coach;

        jtfEquipe.setText(_coach._team);
        jtfNAF.setText(Integer.toString(_coach._naf));
        jtfNom.setText(_coach._name);
        jcbRoster.setSelectedItem(_coach._roster);
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
        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(5, 2));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Nom du Coach:");
        jPanel1.add(jLabel1);
        jPanel1.add(jtfNom);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Nom de l'équipe");
        jPanel1.add(jLabel2);
        jPanel1.add(jtfEquipe);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Race du Roster");
        jPanel1.add(jLabel3);

        jcbRoster.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Amazone", "Bas Fonds", "Chaos", "Elfe", "Elfe sylvain", "Elfe noir", "Gobelin", "Halfling", "Haut Elfe", "Homme lézard", "Humain", "Khemri", "Mort-Vivant", "Nain", "Nain du chaos", "Necromantique", "Nordique", "Nurgle", "Ogre", "Orque", "Pacte Chaotique", "Skaven", "Slann", "Vampire" }));
        jPanel1.add(jcbRoster);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Numéro NAF");
        jPanel1.add(jLabel4);
        jPanel1.add(jtfNAF);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Classement");
        jPanel1.add(jLabel5);

        jtfRank.setText("110");
        jPanel1.add(jtfRank);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel2.add(jbtOK);

        jbtCancel.setText("Annuler");
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
        if (_coach==null)
        {
            c=new Coach();
            Tournament.getTournament().getCoachs().add(c);
        }
        else
        {
            c=_coach;
        }
        c._name=jtfNom.getText();
        c._roster=jcbRoster.getSelectedItem().toString();
        c._team=jtfEquipe.getText();
        try
        {
            c._naf=Integer.parseInt(jtfNAF.getText());
        }
        catch(NumberFormatException e)
        {
            c._naf=0;
        }

        try
        {
            c._rank=Integer.parseInt(jtfRank.getText());
        }
        catch(NumberFormatException e)
        {
            c._rank=110;
        }

        if (c._name.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Le nom est vide");
            return;
        }
        if (c._team.equals(""))
        {
            JOptionPane.showMessageDialog(this, "L'équipe est vide");
            return;
        }
        if (c._roster.equals(""))
        {
            JOptionPane.showMessageDialog(this, "le roster est vide");
            return;
        }
        
        this.setVisible(false);

    }//GEN-LAST:event_jbtOKActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdgCoach dialog = new jdgCoach(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtOK;
    private javax.swing.JComboBox jcbRoster;
    private javax.swing.JTextField jtfEquipe;
    private javax.swing.JTextField jtfNAF;
    private javax.swing.JTextField jtfNom;
    private javax.swing.JTextField jtfRank;
    // End of variables declaration//GEN-END:variables

}
