package bb.tourma.views.round;

import java.util.ArrayList;
import bb.tourma.MainFrame;
import bb.tourma.data.Coach;
import bb.tourma.data.Group;
import bb.tourma.data.Round;
import bb.tourma.data.Tournament;
import bb.tourma.data.ranking.IndivRanking;
import bb.tourma.languages.Translate;
import bb.tourma.tableModel.MjtRanking;
import bb.tourma.tableModel.MjtRankingIndiv;
import bb.tourma.utils.display.TableFormat;
import bb.tourma.views.report.JdgRanking;

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
public final class JPNGroup extends javax.swing.JPanel {

    private final Tournament mTournament;
    private final Group mGroup;
    private final int mRoundNumber;
    private final Round mRound;
    private int mPageCount = 0;
    private int mPageIndex = 0;

    /**
     *
     */
    private boolean mRoundOnly = false;

    /**
     * Creates new form JPNGroup
     *
     * @param t
     * @param g
     * @param roundNumber
     */
    public JPNGroup(final Tournament t, final Group g, final int roundNumber) {
        initComponents();
        mTournament = t;
        mGroup = g;
        mRoundNumber = roundNumber;
        mRound = t.getRound(roundNumber);
        update();
    }

    /**
     *
     * @param r
     */
    public void setRoundOnly(boolean r) {
        mRoundOnly = r;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbGroup = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jbtGeneral = new javax.swing.JButton();
        jbtBack = new javax.swing.JButton();
        jlbPage = new javax.swing.JLabel();
        jbtNext = new javax.swing.JButton();

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

        jbtGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Document.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bb/tourma/languages/language"); // NOI18N
        jbtGeneral.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralActionPerformed(evt);
            }
        });
        jPanel1.add(jbtGeneral);

        jbtBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Backward.png"))); // NOI18N
        jbtBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBackActionPerformed(evt);
            }
        });
        jPanel1.add(jbtBack);

        jlbPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPage.setText("jLabel1");
        jPanel1.add(jlbPage);

        jbtNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Forward.png"))); // NOI18N
        jbtNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextActionPerformed(evt);
            }
        });
        jPanel1.add(jbtNext);

        add(jPanel1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Update panel
     */
    public void update() {

        final ArrayList<Coach> list = new ArrayList<>();

        for (int i = 0; i < mTournament.getCoachsCount(); i++) {
            final Coach c = mTournament.getCoach(i);
            for (int j = 0; j < mGroup.getRosterCount(); j++) {
                if (mGroup.getRoster(j) != null) {
                    if (mGroup.getRoster(j).getName() != null) {
                        if (c != null) {
                            if (c.getRoster() != null) {
                                if (mGroup.getRoster(j).getName().equals(c.getRoster().getName())) {
                                    list.add(c);
                                    break;
                                }
                            } else {
                                System.err.println("Null roster detected");
                            }
                        } else {
                            System.err.println("Null coach");
                        }
                    } else {
                        System.err.println("Null group name detected");
                    }
                } else {
                    System.err.println("Null group roster detected");
                }
            }
        }

        jlbPage.setText(Integer.toString(mPageIndex) + " / " + Integer.toString(mPageCount));

        mPageCount = list.size() / this.mTournament.getParams().getPageSize();
        if (mPageCount * this.mTournament.getParams().getPageSize() < list.size()) {
            mPageCount = mPageCount + 1;
        }
        mPageIndex = 1;

        IndivRanking ranking = mRound.getRankings(mRoundOnly).getGroupRanking().get(mGroup);
       
        MjtRankingIndiv tableModel;
        if (this.mTournament.getParams().isDisplayByPages()) {
            int min = (mPageIndex - 1) * mTournament.getParams().getPageSize();
            int max = mPageIndex * mTournament.getParams().getPageSize();
            if (max > list.size()) {
                max = list.size();
            }
            tableModel = new MjtRankingIndiv(ranking, min, max);
        } else {
            tableModel = new MjtRankingIndiv(ranking);
        }

        jtbGroup.setModel(tableModel);
        jtbGroup.setDefaultRenderer(String.class, tableModel);
        jtbGroup.setDefaultRenderer(Integer.class, tableModel);

        jtbGroup.setRowHeight(25);
        //TableFormat.setColumnSize(jtbGroup);
    }

    private static final String CS_Group = "GENERAL PAR GROUPE";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralActionPerformed

        final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true,
                Translate.translate(CS_Group) + ": " + mGroup.getName(), mRoundNumber, mTournament, (MjtRanking) jtbGroup.getModel(), 0);
        jdg.setVisible(true);

}//GEN-LAST:event_jbtGeneralActionPerformed

    private void jbtBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBackActionPerformed
        mPageIndex = mPageIndex - 1;
        if (mPageIndex <= 1) {
            mPageIndex = 1;
        }
        update();
    }//GEN-LAST:event_jbtBackActionPerformed

    private void jbtNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextActionPerformed
        mPageIndex = mPageIndex + 1;
        if (mPageIndex >= mPageCount) {
            mPageIndex = mPageCount;
        }
        update();
    }//GEN-LAST:event_jbtNextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtBack;
    private javax.swing.JButton jbtGeneral;
    private javax.swing.JButton jbtNext;
    private javax.swing.JLabel jlbPage;
    private javax.swing.JTable jtbGroup;
    // End of variables declaration//GEN-END:variables

}