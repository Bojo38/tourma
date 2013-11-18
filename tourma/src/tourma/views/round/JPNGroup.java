package tourma.views.round;

import java.util.ArrayList;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.Group;
import tourma.data.Tournament;
import tourma.tableModel.mjtRankingIndiv;
import tourma.utility.StringConstants;
import tourma.utils.TableFormat;
import tourma.views.report.jdgRanking;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPNGroup.java
 *
 * Created on 21 mai 2011, 23:18:17
 */
/**
 *
 * @author Administrateur
 */
public class JPNGroup extends javax.swing.JPanel {

    Tournament mTournament;
    Group mGroup;
    int mRoundNumber;

    public boolean mRoundOnly=false;
    
    /** Creates new form JPNGroup */
    public JPNGroup(final Tournament t,final  Group g,final int roundNumber) {
        initComponents();
        mTournament = t;
        mGroup = g;
        mRoundNumber = roundNumber;
        update();
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbGroup = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jbtGeneralClan = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jtbGroup.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbGroup);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jbtGeneralClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtGeneralClan.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneralClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralClanActionPerformed(evt);
            }
        });
        jPanel1.add(jbtGeneralClan);

        add(jPanel1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    public void update() {
        final ArrayList<Coach> ArrayList = new ArrayList<>();

        for (int i = 0; i < mTournament.getCoachs().size(); i++) {
            final Coach c = mTournament.getCoachs().get(i);
            for (int j = 0; j < mGroup.mRosters.size(); j++) {
                if (mGroup.mRosters.get(j).mName.equals(c.mRoster.mName)) {
                    ArrayList.add(c);
                    break;
                }
            }
        }

        final mjtRankingIndiv tableModel = new mjtRankingIndiv(mRoundNumber,
                mTournament.getParams().mRankingIndiv1,
                mTournament.getParams().mRankingIndiv2,
                mTournament.getParams().mRankingIndiv3,
                mTournament.getParams().mRankingIndiv4,
                mTournament.getParams().mRankingIndiv5,
                ArrayList, mTournament.getParams().mTeamTournament,mRoundOnly,false);
        jtbGroup.setModel(tableModel);
        jtbGroup.setDefaultRenderer(String.class, tableModel);
        jtbGroup.setDefaultRenderer(Integer.class, tableModel);

        jtbGroup.setRowHeight(25);
        TableFormat.setColumnSize(jtbGroup);
    }

    

     @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralClanActionPerformed
        final jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GENERAL PAR GROUPE"), mRoundNumber, mTournament, (mjtRankingIndiv) jtbGroup.getModel(), 0);
        jdg.setVisible(true);
}//GEN-LAST:event_jbtGeneralClanActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtGeneralClan;
    private javax.swing.JTable jtbGroup;
    // End of variables declaration//GEN-END:variables
}