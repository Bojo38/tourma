/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * jdgCoach.java
 *
 * Created on 10 mai 2010, 19:37:53
 */
package bb.tourma;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import javax.swing.SpinnerNumberModel;
import bb.tourma.data.Coach;
import bb.tourma.data.Cup;
import bb.tourma.data.Round;
import bb.tourma.data.Team;
import bb.tourma.data.TeamMatch;
import bb.tourma.data.Tournament;
import bb.tourma.tableModel.MjtPairs;
import bb.tourma.views.report.JdgRound;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public class JdgCupOptions extends javax.swing.JDialog {

    Cup mCup;

    /**
     * Creates new form jdgCoach
     *
     * @param parent
     * @param modal
     *
     */
    public JdgCupOptions(final java.awt.Frame parent, final boolean modal, int MaxTour) {
        super(parent, modal);
        initComponents();

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();

        final int screenWidth = dmode.getWidth();
        final int screenHeight = dmode.getHeight();

        this.setSize(768, 300);

        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

        mCup = Tournament.getTournament().getCup();
        if (mCup == null) {
            mCup = new Cup(Cup.ROUND_TYPE.CLASSIC, 1, true, true, Cup.INITIAL_DRAW.RANDOM);
            Tournament.getTournament().setCup(mCup);
        }

        final SpinnerNumberModel model = new SpinnerNumberModel(1, 1, MaxTour, 1);
        jspNumberOfCupRounds.setModel(model);
        update();
    }

    private void update() {

        jcxShuffle.setSelected(mCup.isShuffle());
        jcxSwissForOthers.setSelected(mCup.isSwissForLoosers());

        jspNumberOfCupRounds.setValue(mCup.getRoundsCount());

        jrbClassic3rd.setSelected(mCup.getType() == Cup.ROUND_TYPE.CLASSIC_THIRD);
        jrbTypeClassic.setSelected(mCup.getType() == Cup.ROUND_TYPE.CLASSIC);
        jrbRankingMatchs.setSelected(mCup.getType() == Cup.ROUND_TYPE.RANKING_MATCHES);
        jrbLooserCup.setSelected(mCup.getType() == Cup.ROUND_TYPE.LOOSER);

        jrbDrawRandom.setSelected(mCup.getInitialDraw() == Cup.INITIAL_DRAW.RANDOM);
        jrbRankingOrder.setSelected(mCup.getInitialDraw() == Cup.INITIAL_DRAW.RANKING);
        jrbManualChoice.setSelected(mCup.getInitialDraw() == Cup.INITIAL_DRAW.MANUAL);

        if (Tournament.getTournament().getCategoriesCount()>= 2) {
            jrbCategoriesCrossed.setSelected(mCup.getInitialDraw() == Cup.INITIAL_DRAW.CATEGORIES_CROSSED);
            jrbCategoriesMixed.setSelected(mCup.getInitialDraw() == Cup.INITIAL_DRAW.CATEGORIES_MIXED);
            jrbCategoriesAbsoluteRanking.setSelected(mCup.getInitialDraw() == Cup.INITIAL_DRAW.CATEGORIES_ABSOLUTE_RANKING);
            jrbCategoriesNotMixed.setSelected(mCup.getInitialDraw() == Cup.INITIAL_DRAW.CATEGORIES_NOT_MIXED);
        }
        else
        {
            jrbCategoriesCrossed.setEnabled(false);
            jrbCategoriesMixed.setEnabled(false);
            jrbCategoriesAbsoluteRanking.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    @SuppressFBWarnings(
            value = "UI_INHERITANCE_UNSAFE_GETRESOURCE",
            justification = "GeneratedCode")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgCupType = new javax.swing.ButtonGroup();
        bgInitialDraw = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jspNumberOfCupRounds = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jcxSwissForOthers = new javax.swing.JCheckBox();
        jcxShuffle = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jrbTypeClassic = new javax.swing.JRadioButton();
        jrbClassic3rd = new javax.swing.JRadioButton();
        jrbRankingMatchs = new javax.swing.JRadioButton();
        jrbLooserCup = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jrbDrawRandom = new javax.swing.JRadioButton();
        jrbRankingOrder = new javax.swing.JRadioButton();
        jrbManualChoice = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jrbCategoriesCrossed = new javax.swing.JRadioButton();
        jrbCategoriesMixed = new javax.swing.JRadioButton();
        jrbCategoriesNotMixed = new javax.swing.JRadioButton();
        jrbCategoriesAbsoluteRanking = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Select.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bb/tourma/languages/language"); // NOI18N
        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel2.add(jbtOK);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setLayout(new java.awt.GridLayout(4, 1));

        jPanel7.setLayout(new java.awt.BorderLayout());

        jspNumberOfCupRounds.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspNumberOfCupRoundsStateChanged(evt);
            }
        });
        jPanel7.add(jspNumberOfCupRounds, java.awt.BorderLayout.WEST);

        jLabel1.setText(bundle.getString("CupRoundNumber")); // NOI18N
        jPanel7.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel7);

        jcxSwissForOthers.setText(bundle.getString("CupSwissForLoosers")); // NOI18N
        jcxSwissForOthers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxSwissForOthersActionPerformed(evt);
            }
        });
        jPanel3.add(jcxSwissForOthers);

        jcxShuffle.setText(bundle.getString("CupDrawShuffle")); // NOI18N
        jcxShuffle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxShuffleActionPerformed(evt);
            }
        });
        jPanel3.add(jcxShuffle);

        jPanel1.add(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("CupType"))); // NOI18N
        jPanel4.setLayout(new java.awt.GridLayout(4, 1));

        bgCupType.add(jrbTypeClassic);
        jrbTypeClassic.setText(bundle.getString("CupTypeClassic")); // NOI18N
        jrbTypeClassic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTypeClassicActionPerformed(evt);
            }
        });
        jPanel4.add(jrbTypeClassic);

        bgCupType.add(jrbClassic3rd);
        jrbClassic3rd.setText(bundle.getString("CupTypeClassicThirdPlace")); // NOI18N
        jrbClassic3rd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbClassic3rdActionPerformed(evt);
            }
        });
        jPanel4.add(jrbClassic3rd);

        bgCupType.add(jrbRankingMatchs);
        jrbRankingMatchs.setText(bundle.getString("CupTypeRankingMatches")); // NOI18N
        jrbRankingMatchs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbRankingMatchsActionPerformed(evt);
            }
        });
        jPanel4.add(jrbRankingMatchs);

        bgCupType.add(jrbLooserCup);
        jrbLooserCup.setText(bundle.getString("CupTypeLooserCup")); // NOI18N
        jrbLooserCup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbLooserCupActionPerformed(evt);
            }
        });
        jPanel4.add(jrbLooserCup);

        jPanel1.add(jPanel4);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("CupInitialDraw"))); // NOI18N
        jPanel6.setLayout(new java.awt.GridLayout(8, 1));

        bgInitialDraw.add(jrbDrawRandom);
        jrbDrawRandom.setText(bundle.getString("CupDrawRandom")); // NOI18N
        jrbDrawRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbDrawRandomActionPerformed(evt);
            }
        });
        jPanel6.add(jrbDrawRandom);

        bgInitialDraw.add(jrbRankingOrder);
        jrbRankingOrder.setText(bundle.getString("CupDrawRanking")); // NOI18N
        jrbRankingOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbRankingOrderActionPerformed(evt);
            }
        });
        jPanel6.add(jrbRankingOrder);

        bgInitialDraw.add(jrbManualChoice);
        jrbManualChoice.setText(bundle.getString("CupDrawManual")); // NOI18N
        jrbManualChoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbManualChoiceActionPerformed(evt);
            }
        });
        jPanel6.add(jrbManualChoice);

        jLabel3.setText(bundle.getString("CategoriesAsConferences")); // NOI18N
        jPanel6.add(jLabel3);

        bgInitialDraw.add(jrbCategoriesCrossed);
        jrbCategoriesCrossed.setText(bundle.getString("CupDrawCategoriesCrossed")); // NOI18N
        jrbCategoriesCrossed.setToolTipText("");
        jrbCategoriesCrossed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCategoriesCrossedActionPerformed(evt);
            }
        });
        jPanel6.add(jrbCategoriesCrossed);

        bgInitialDraw.add(jrbCategoriesMixed);
        jrbCategoriesMixed.setText(bundle.getString("CupCategoriesMixed")); // NOI18N
        jrbCategoriesMixed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCategoriesMixedActionPerformed(evt);
            }
        });
        jPanel6.add(jrbCategoriesMixed);

        bgInitialDraw.add(jrbCategoriesNotMixed);
        jrbCategoriesNotMixed.setText(bundle.getString("CupCategoriesNotMixed")); // NOI18N
        jrbCategoriesNotMixed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCategoriesNotMixedActionPerformed(evt);
            }
        });
        jPanel6.add(jrbCategoriesNotMixed);

        bgInitialDraw.add(jrbCategoriesAbsoluteRanking);
        jrbCategoriesAbsoluteRanking.setText(bundle.getString("CupCategoriesMixed")); // NOI18N
        jrbCategoriesAbsoluteRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCategoriesAbsoluteRankingActionPerformed(evt);
            }
        });
        jPanel6.add(jrbCategoriesAbsoluteRanking);

        jPanel1.add(jPanel6);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jLabel2.setText(bundle.getString("CupOptions")); // NOI18N
        jPanel5.add(jLabel2);

        getContentPane().add(jPanel5, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed

        Cup newCup=new Cup(mCup);
        Tournament.getTournament().setCup(newCup);
        this.setVisible(false);

    }//GEN-LAST:event_jbtOKActionPerformed

    private void jrbClassic3rdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbClassic3rdActionPerformed
        if (jrbClassic3rd.isSelected()) {
            mCup.setType(Cup.ROUND_TYPE.CLASSIC_THIRD);
        }
        update();
    }//GEN-LAST:event_jrbClassic3rdActionPerformed

    private void jrbDrawRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbDrawRandomActionPerformed
        if (jrbDrawRandom.isSelected()) {
            mCup.setInitialDraw(Cup.INITIAL_DRAW.RANDOM);
        }
        update();
    }//GEN-LAST:event_jrbDrawRandomActionPerformed

    private void jrbTypeClassicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbTypeClassicActionPerformed
        if (jrbTypeClassic.isSelected()) {
            mCup.setType(Cup.ROUND_TYPE.CLASSIC);
        }
        update();
    }//GEN-LAST:event_jrbTypeClassicActionPerformed

    private void jrbRankingMatchsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbRankingMatchsActionPerformed
        if (jrbRankingMatchs.isSelected()) {
            mCup.setType(Cup.ROUND_TYPE.RANKING_MATCHES);
        }
        update();
    }//GEN-LAST:event_jrbRankingMatchsActionPerformed

    private void jrbLooserCupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbLooserCupActionPerformed
        if (jrbLooserCup.isSelected()) {
            mCup.setType(Cup.ROUND_TYPE.LOOSER);
        }
        update();
    }//GEN-LAST:event_jrbLooserCupActionPerformed

    private void jrbRankingOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbRankingOrderActionPerformed
        if (jrbRankingOrder.isSelected()) {
            mCup.setInitialDraw(Cup.INITIAL_DRAW.RANKING);
        }
        update();
    }//GEN-LAST:event_jrbRankingOrderActionPerformed

    private void jrbManualChoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbManualChoiceActionPerformed
        if (jrbManualChoice.isSelected()) {
            mCup.setInitialDraw(Cup.INITIAL_DRAW.MANUAL);
        }
        update();
    }//GEN-LAST:event_jrbManualChoiceActionPerformed

    private void jcxShuffleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxShuffleActionPerformed
        if (jcxShuffle.isSelected()) {
            mCup.setShuffle(true);
        } else {
            mCup.setShuffle(false);
        }
        update();
    }//GEN-LAST:event_jcxShuffleActionPerformed

    private void jcxSwissForOthersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxSwissForOthersActionPerformed
        if (jcxSwissForOthers.isSelected()) {
            mCup.setSwissForLoosers(true);
        } else {
            mCup.setSwissForLoosers(false);
        }
        update();
    }//GEN-LAST:event_jcxSwissForOthersActionPerformed

    private void jspNumberOfCupRoundsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspNumberOfCupRoundsStateChanged
        mCup.setRoundsCount((Integer) jspNumberOfCupRounds.getValue());
        update();
    }//GEN-LAST:event_jspNumberOfCupRoundsStateChanged

    private void jrbCategoriesCrossedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCategoriesCrossedActionPerformed
        if (jrbCategoriesCrossed.isSelected()) {
            mCup.setInitialDraw(Cup.INITIAL_DRAW.CATEGORIES_CROSSED);
        }
    }//GEN-LAST:event_jrbCategoriesCrossedActionPerformed

    private void jrbCategoriesMixedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCategoriesMixedActionPerformed
        if (jrbCategoriesMixed.isSelected()) {
            mCup.setInitialDraw(Cup.INITIAL_DRAW.CATEGORIES_MIXED);
        }
    }//GEN-LAST:event_jrbCategoriesMixedActionPerformed

    private void jrbCategoriesAbsoluteRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCategoriesAbsoluteRankingActionPerformed
        if (jrbCategoriesMixed.isSelected()) {
            mCup.setInitialDraw(Cup.INITIAL_DRAW.CATEGORIES_ABSOLUTE_RANKING);
        }
    }//GEN-LAST:event_jrbCategoriesAbsoluteRankingActionPerformed

    private void jrbCategoriesNotMixedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCategoriesNotMixedActionPerformed
         if (jrbCategoriesNotMixed.isSelected()) {
            mCup.setInitialDraw(Cup.INITIAL_DRAW.CATEGORIES_NOT_MIXED);
        }
    }//GEN-LAST:event_jrbCategoriesNotMixedActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgCupType;
    private javax.swing.ButtonGroup bgInitialDraw;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton jbtOK;
    private javax.swing.JCheckBox jcxShuffle;
    private javax.swing.JCheckBox jcxSwissForOthers;
    private javax.swing.JRadioButton jrbCategoriesAbsoluteRanking;
    private javax.swing.JRadioButton jrbCategoriesCrossed;
    private javax.swing.JRadioButton jrbCategoriesMixed;
    private javax.swing.JRadioButton jrbCategoriesNotMixed;
    private javax.swing.JRadioButton jrbClassic3rd;
    private javax.swing.JRadioButton jrbDrawRandom;
    private javax.swing.JRadioButton jrbLooserCup;
    private javax.swing.JRadioButton jrbManualChoice;
    private javax.swing.JRadioButton jrbRankingMatchs;
    private javax.swing.JRadioButton jrbRankingOrder;
    private javax.swing.JRadioButton jrbTypeClassic;
    private javax.swing.JSpinner jspNumberOfCupRounds;
    // End of variables declaration//GEN-END:variables

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
