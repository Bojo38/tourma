/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.teamma.views;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import bb.teamma.data.PlayerType;
import bb.teamma.data.StarPlayer;
import bb.teamma.tableModel.MjtPlayerTypes;
import bb.teamma.tableModel.MjtStarPlayers;

/**
 *
 * @author Perso
 */
public class JdgSelectPosition extends javax.swing.JDialog {

    private PlayerType mPlayerType;
    private ArrayList<PlayerType> mPositions;
    private StarPlayer mStarPlayer;
    private ArrayList<StarPlayer> mStarPlayers;

    /**
     * Creates new form JdgSelectSkill
     *
     * @param parent
     * @param modal
     * @param player
     */
    public JdgSelectPosition(java.awt.Frame parent, boolean modal, ArrayList<PlayerType> positions, PlayerType pt) {
        super(parent, modal);
        initComponents();

        mPlayerType = pt;
        mPositions = positions;

        MjtPlayerTypes model = new MjtPlayerTypes(mPositions);
        jtPositions.setModel(model);

        jtPositions.setDefaultRenderer(Integer.class, model);
        jtPositions.setDefaultRenderer(String.class, model);

        int index = positions.indexOf(pt);

        jtPositions.getSelectionModel().setSelectionInterval(index, index);

        jtPositions.getColumnModel().getColumn(6).setMinWidth(200);

        this.setPreferredSize(new Dimension(400, 260));
        pack();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        this.setSize(800, 400);

        int screenWidth = dmode.getWidth();
        int screenHeight = dmode.getHeight();
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

    }

    public JdgSelectPosition(java.awt.Frame parent, boolean modal, ArrayList<StarPlayer> starplayers, StarPlayer sp) {
        super(parent, modal);
        initComponents();

        mStarPlayer = sp;
        mStarPlayers = starplayers;

        MjtStarPlayers model = new MjtStarPlayers(mStarPlayers);
        jtPositions.setModel(model);

        jtPositions.setDefaultRenderer(Integer.class, model);
        jtPositions.setDefaultRenderer(String.class, model);

        int index = starplayers.indexOf(sp);

        jtPositions.getSelectionModel().setSelectionInterval(index, index);

        jtPositions.getColumnModel().getColumn(6).setMinWidth(200);

        this.setPreferredSize(new Dimension(400, 260));
        pack();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        this.setSize(800, 400);

        int screenWidth = dmode.getWidth();
        int screenHeight = dmode.getHeight();
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPositions = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bb/tourma/languages/language"); // NOI18N
        jLabel1.setText(bundle.getString("Choisissez le type de joueur")); // NOI18N
        jpn.add(jLabel1);

        getContentPane().add(jpn, java.awt.BorderLayout.NORTH);

        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("bb/teamma/languages/language"); // NOI18N
        jbtOK.setText(bundle1.getString("OK")); // NOI18N
        jbtOK.setName("ok"); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel2.add(jbtOK);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jtPositions.setModel(new javax.swing.table.DefaultTableModel(
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
        jtPositions.setName("jtPositions"); // NOI18N
        jtPositions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPositionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtPositions);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private final static String CS_Error = "Error";
    private final static String CS_NoSkillSelected = "No skill selected";

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed

        if (mPlayerType!=null)
        {
            mPlayerType = mPositions.get(jtPositions.getSelectedRow());
        }
        if (mStarPlayer!=null)
        {
            mStarPlayer=mStarPlayers.get(jtPositions.getSelectedRow());
        }

        this.setVisible(false);
        /*else {
            JOptionPane.showMessageDialog(this, CS_Error, CS_NoSkillSelected, JOptionPane.ERROR_MESSAGE);
        }*/
    }//GEN-LAST:event_jbtOKActionPerformed

    public PlayerType getPosition() {
        return mPlayerType;
    }

    public StarPlayer getStarPlayer() {
        return mStarPlayer;
    }


    private void jtPositionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPositionsMouseClicked

    }//GEN-LAST:event_jtPositionsMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtOK;
    private javax.swing.JPanel jpn;
    private javax.swing.JTable jtPositions;
    // End of variables declaration//GEN-END:variables

}
