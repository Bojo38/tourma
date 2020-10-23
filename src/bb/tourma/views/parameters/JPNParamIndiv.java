/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.views.parameters;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.Tournament;
import bb.tourma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public class JPNParamIndiv extends javax.swing.JPanel {

    private Tournament mTournament;

    /**
     * Creates new form JPNParamIndiv
     */
    public JPNParamIndiv() {

        mTournament = Tournament.getTournament();

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcxLargeVictory = new javax.swing.JCheckBox();
        jtffLargeVictory = new javax.swing.JFormattedTextField();
        jlbLargeVictoryGap = new javax.swing.JLabel();
        jtffLargeVictoryGap = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jtffVictory = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jtffDraw = new javax.swing.JFormattedTextField();
        jcxLittleLoss = new javax.swing.JCheckBox();
        jtffLittleLost = new javax.swing.JFormattedTextField();
        jlbLittleLossGap = new javax.swing.JLabel();
        jtffLittleLostGap = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jtffLost = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jtffRefused = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jtffConcedeed = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jcbRank1 = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jcbRank2 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jcbRank3 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jcbRank4 = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jcbRank5 = new javax.swing.JComboBox();
        jcxBestResult = new javax.swing.JCheckBox();
        jspBestResults = new javax.swing.JSpinner();
        jcxExceptBestAndWorst = new javax.swing.JCheckBox();
        jcxForAnnexRankingToo = new javax.swing.JCheckBox();

        setLayout(new java.awt.GridLayout(16, 2));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bb/tourma/languages/language"); // NOI18N
        jcxLargeVictory.setText(bundle.getString("LargeVictoryKey")); // NOI18N
        jcxLargeVictory.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcxLargeVictory.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcxLargeVictory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxLargeVictoryActionPerformed(evt);
            }
        });
        add(jcxLargeVictory);

        jtffLargeVictory.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLargeVictory.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLargeVictoryFocusLost(evt);
            }
        });
        add(jtffLargeVictory);

        jlbLargeVictoryGap.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbLargeVictoryGap.setText(bundle.getString("MinimumGapForLargeVictoryKey")); // NOI18N
        jlbLargeVictoryGap.setToolTipText(bundle.getString("GapForLargeVictoryTipKey")); // NOI18N
        add(jlbLargeVictoryGap);

        jtffLargeVictoryGap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLargeVictoryGap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLargeVictoryGapFocusLost(evt);
            }
        });
        add(jtffLargeVictoryGap);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText(bundle.getString("VictoryKey")); // NOI18N
        jLabel4.setToolTipText(bundle.getString("VictoryNumberOfPointsKey")); // NOI18N
        add(jLabel4);

        jtffVictory.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffVictory.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffVictoryFocusLost(evt);
            }
        });
        add(jtffVictory);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText(bundle.getString("DrawKey")); // NOI18N
        jLabel5.setToolTipText(bundle.getString("DrawMatchTipKey")); // NOI18N
        add(jLabel5);

        jtffDraw.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffDraw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffDrawFocusLost(evt);
            }
        });
        add(jtffDraw);

        jcxLittleLoss.setText(bundle.getString("ShortLossKey")); // NOI18N
        jcxLittleLoss.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcxLittleLoss.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcxLittleLoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxLittleLossActionPerformed(evt);
            }
        });
        add(jcxLittleLoss);

        jtffLittleLost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLittleLost.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLittleLostFocusLost(evt);
            }
        });
        add(jtffLittleLost);

        jlbLittleLossGap.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbLittleLossGap.setText(bundle.getString("MaximumGapForShortLossKey")); // NOI18N
        jlbLittleLossGap.setToolTipText(bundle.getString("MaximumGapForShortLossTipKey")); // NOI18N
        add(jlbLittleLossGap);

        jtffLittleLostGap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLittleLostGap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLittleLostGapFocusLost(evt);
            }
        });
        add(jtffLittleLostGap);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText(bundle.getString("LossKey")); // NOI18N
        jLabel7.setToolTipText(bundle.getString("LossTipKey")); // NOI18N
        add(jLabel7);

        jtffLost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLost.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLostFocusLost(evt);
            }
        });
        add(jtffLost);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText(bundle.getString("Match Refused")); // NOI18N
        jLabel8.setToolTipText(bundle.getString("LossTipKey")); // NOI18N
        add(jLabel8);

        jtffRefused.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffRefused.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffRefusedFocusLost(evt);
            }
        });
        add(jtffRefused);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText(bundle.getString("MatchConceeded")); // NOI18N
        jLabel9.setToolTipText(bundle.getString("LossTipKey")); // NOI18N
        add(jLabel9);

        jtffConcedeed.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffConcedeed.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffConcedeedFocusLost(evt);
            }
        });
        add(jtffConcedeed);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText(Translate.translate("RankingCriteria")+" 1:");
        add(jLabel14);

        jcbRank1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite", "Passes", "Interceptions", "Différence de passes", "Différence d'interceptions" }));
        jcbRank1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank1ActionPerformed(evt);
            }
        });
        add(jcbRank1);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText(Translate.translate("RankingCriteria")+" 2:");
        add(jLabel15);

        jcbRank2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite", "Passes", "Interceptions", "Différence de passes", "Différence d'interceptions" }));
        jcbRank2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank2ActionPerformed(evt);
            }
        });
        add(jcbRank2);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText(Translate.translate("RankingCriteria")+" 3:");
        add(jLabel16);

        jcbRank3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite", "Passes", "Interceptions", "Différence de passes", "Différence d'interceptions" }));
        jcbRank3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank3ActionPerformed(evt);
            }
        });
        add(jcbRank3);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText(Translate.translate("RankingCriteria")+" 4:");
        add(jLabel17);

        jcbRank4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite", "Passes", "Interceptions", "Différence de passes", "Différence d'interceptions" }));
        jcbRank4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank4ActionPerformed(evt);
            }
        });
        add(jcbRank4);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText(Translate.translate("RankingCriteria")+" 5:");
        add(jLabel18);

        jcbRank5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite", "Passes", "Interceptions", "Différence de passes", "Différence d'interceptions" }));
        jcbRank5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank5ActionPerformed(evt);
            }
        });
        add(jcbRank5);

        jcxBestResult.setText(bundle.getString("KeepBestResults")); // NOI18N
        jcxBestResult.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcxBestResult.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcxBestResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxBestResultActionPerformed(evt);
            }
        });
        add(jcxBestResult);

        jspBestResults.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspBestResultsStateChanged(evt);
            }
        });
        add(jspBestResults);

        jcxExceptBestAndWorst.setText(bundle.getString("ExceptBestAndWorst")); // NOI18N
        jcxExceptBestAndWorst.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcxExceptBestAndWorst.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcxExceptBestAndWorst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxExceptBestAndWorstActionPerformed(evt);
            }
        });
        add(jcxExceptBestAndWorst);

        jcxForAnnexRankingToo.setText(bundle.getString("ExceptBestAndWorstAnnex")); // NOI18N
        jcxForAnnexRankingToo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxForAnnexRankingTooActionPerformed(evt);
            }
        });
        add(jcxForAnnexRankingToo);
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffLargeVictoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLargeVictoryFocusLost

        try {
            jtffLargeVictory.commitEdit();
            final int points = ((Number) jtffLargeVictory.getValue()).intValue();
            mTournament.getParams().setPointsIndivLargeVictory(points);
        } catch (ParseException e) {
            jtffLargeVictory.setValue(jtffLargeVictory.getValue());
        }

        update();
    }//GEN-LAST:event_jtffLargeVictoryFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffLargeVictoryGapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLargeVictoryGapFocusLost
        try {
            jtffLargeVictoryGap.commitEdit();
            final int points = ((Number) jtffLargeVictoryGap.getValue()).intValue();
            mTournament.getParams().setGapLargeVictory(points);
        } catch (ParseException e) {
            jtffLargeVictoryGap.setValue(jtffLargeVictoryGap.getValue());
        }

        update();
    }//GEN-LAST:event_jtffLargeVictoryGapFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffVictoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffVictoryFocusLost
        try {
            jtffVictory.commitEdit();
            final int points = ((Number) jtffVictory.getValue()).intValue();
            mTournament.getParams().setPointsIndivVictory(points);
        } catch (ParseException e) {
            jtffVictory.setValue(jtffVictory.getValue());
        }
        update();
    }//GEN-LAST:event_jtffVictoryFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffDrawFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffDrawFocusLost
        try {
            jtffDraw.commitEdit();
            final int points = ((Number) jtffDraw.getValue()).intValue();
            mTournament.getParams().setPointsIndivDraw(points);
        } catch (ParseException e) {
            jtffDraw.setValue(jtffDraw.getValue());
        }
        update();
    }//GEN-LAST:event_jtffDrawFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffLittleLostFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLittleLostFocusLost
        try {
            jtffLittleLost.commitEdit();
            final int points = ((Number) jtffLittleLost.getValue()).intValue();
            mTournament.getParams().setPointsIndivLittleLost(points);
        } catch (ParseException e) {
            jtffLittleLost.setValue(jtffLittleLost.getValue());
        }
        update();
    }//GEN-LAST:event_jtffLittleLostFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffLittleLostGapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLittleLostGapFocusLost
        try {
            jtffLittleLostGap.commitEdit();
            final int points = ((Number) jtffLittleLostGap.getValue()).intValue();
            mTournament.getParams().setGapLittleLost(points);
        } catch (ParseException e) {
            jtffLittleLostGap.setValue(jtffLittleLostGap.getValue());
        }

        update();
    }//GEN-LAST:event_jtffLittleLostGapFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffLostFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLostFocusLost
        try {
            jtffLost.commitEdit();
            final int points = ((Number) jtffLost.getValue()).intValue();
            mTournament.getParams().setPointsIndivLost(points);
        } catch (ParseException e) {
            jtffLost.setValue(jtffLost.getValue());
        }
        update();
    }//GEN-LAST:event_jtffLostFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank1ActionPerformed

        mTournament.getParams().setRankingIndiv1(jcbRank1.getSelectedIndex());
        update();
        mTournament.recomputeAll();

    }//GEN-LAST:event_jcbRank1ActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank2ActionPerformed

        mTournament.getParams().setRankingIndiv2(jcbRank2.getSelectedIndex());
        update();
        mTournament.recomputeAll();

    }//GEN-LAST:event_jcbRank2ActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank3ActionPerformed

        mTournament.getParams().setRankingIndiv3(jcbRank3.getSelectedIndex());
        update();
        mTournament.recomputeAll();
    }//GEN-LAST:event_jcbRank3ActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank4ActionPerformed
        mTournament.getParams().setRankingIndiv4(jcbRank4.getSelectedIndex());
        update();
        mTournament.recomputeAll();
    }//GEN-LAST:event_jcbRank4ActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank5ActionPerformed
        mTournament.getParams().setRankingIndiv5(jcbRank5.getSelectedIndex());
        update();
        mTournament.recomputeAll();
    }//GEN-LAST:event_jcbRank5ActionPerformed

    private void jtffRefusedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffRefusedFocusLost
        try {
            jtffRefused.commitEdit();
            final int points = ((Number) jtffRefused.getValue()).intValue();
            mTournament.getParams().setPointsRefused(points);
        } catch (ParseException e) {
            jtffRefused.setValue(jtffRefused.getValue());
        }
        update();
    }//GEN-LAST:event_jtffRefusedFocusLost

    private void jtffConcedeedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffConcedeedFocusLost
        try {
            jtffConcedeed.commitEdit();
            final int points = ((Number) jtffConcedeed.getValue()).intValue();
            mTournament.getParams().setPointsConcedeed(points);
        } catch (ParseException e) {
            jtffConcedeed.setValue(jtffConcedeed.getValue());
        }
        update();// TODO add your handling code here:
    }//GEN-LAST:event_jtffConcedeedFocusLost

    private void jcxLittleLossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxLittleLossActionPerformed

        mTournament.getParams().setUseLittleLoss(jcxLittleLoss.isSelected());

        update();
        mTournament.recomputeAll();

    }//GEN-LAST:event_jcxLittleLossActionPerformed

    private void jcxLargeVictoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxLargeVictoryActionPerformed

        mTournament.getParams().setUseLargeVictory(jcxLargeVictory.isSelected());
        update();
        mTournament.recomputeAll();

    }//GEN-LAST:event_jcxLargeVictoryActionPerformed

    private void jcxBestResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxBestResultActionPerformed

        mTournament.getParams().setUseBestResultIndiv(jcxBestResult.isSelected());
        update();
        mTournament.recomputeAll();

    }//GEN-LAST:event_jcxBestResultActionPerformed

    private void jspBestResultsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspBestResultsStateChanged
        mTournament.getParams().setBestResultIndiv((Integer) jspBestResults.getValue());
        update();
        mTournament.recomputeAll();
    }//GEN-LAST:event_jspBestResultsStateChanged

    private void jcxExceptBestAndWorstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxExceptBestAndWorstActionPerformed

        mTournament.getParams().setExceptBestAndWorstIndiv(jcxExceptBestAndWorst.isSelected());
        update();
        mTournament.recomputeAll();
    }//GEN-LAST:event_jcxExceptBestAndWorstActionPerformed

    private void jcxForAnnexRankingTooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxForAnnexRankingTooActionPerformed

        mTournament.getParams().setApplyToAnnexIndiv(jcxForAnnexRankingToo.isSelected());
        update();
        mTournament.recomputeAll();
    }//GEN-LAST:event_jcxForAnnexRankingTooActionPerformed

    public final static String CS_None = "None";
    public final static String CS_Points = "Points";
    public final static String CS_OpponentPoints = "POINTS ADVERSAIRES";
    public final static String CS_ACCR_VictoryDrawLost = "V/N/D";
    public final static String CS_ELO = "ELO";
    public final static String CS_OpponentsElo = "OpponentsElo";
    public final static String CS_MatchCount = "MatchCount";
    public final static String CS_OpponentsPointsExceptOwnMatch = "POINTS ADVERSAIRES EXCEPT OWN MATCH";
    public final static String CS_TablesPoints = "TablesPoints";
    public final static String CS_PointsWithoutBonus = "Points sans bonus";
    public final static String CS_BonusPoints = "Bonus Points";
    public final static String CS_Player = "JOUEUR";
    public final static String CS_Opponent = "ADVERSAIRE";
    public final static String CS_Difference = "DIFFÉRENCE";

    /**
     * Update Panel
     */
    public void update() {

        

        jcxLargeVictory.setSelected(mTournament.getParams().isUseLargeVictory());
        jcxLittleLoss.setSelected(mTournament.getParams().isUseLittleLoss());

        jtffLargeVictory.setEnabled(mTournament.getParams().isUseLargeVictory());
        jtffLargeVictoryGap.setEnabled(mTournament.getParams().isUseLargeVictory());
        jlbLargeVictoryGap.setEnabled(mTournament.getParams().isUseLargeVictory());

        jtffLittleLost.setEnabled(mTournament.getParams().isUseLittleLoss());
        jtffLittleLostGap.setEnabled(mTournament.getParams().isUseLittleLoss());
        jlbLittleLossGap.setEnabled(mTournament.getParams().isUseLittleLoss());

        jtffDraw.setValue(mTournament.getParams().getPointsIndivDraw());
        jtffLargeVictory.setValue(mTournament.getParams().getPointsIndivLargeVictory());
        jtffLittleLost.setValue(mTournament.getParams().getPointsIndivLittleLost());
        jtffLargeVictoryGap.setValue(mTournament.getParams().getGapLargeVictory());
        jtffLittleLostGap.setValue(mTournament.getParams().getGapLittleLost());
        jtffLost.setValue(mTournament.getParams().getPointsIndivLost());

        jtffRefused.setValue(mTournament.getParams().getPointsRefused());
        jtffConcedeed.setValue(mTournament.getParams().getPointsConcedeed());

        jtffVictory.setValue(mTournament.getParams().getPointsIndivVictory());

        jspBestResults.setEnabled(mTournament.getParams().isUseBestResultIndiv());
        jspBestResults.setValue(mTournament.getParams().getBestResultIndiv());

        jcxExceptBestAndWorst.setEnabled(!mTournament.getParams().isUseBestResultIndiv());

        jcxExceptBestAndWorst.setSelected(mTournament.getParams().isExceptBestAndWorstIndiv());
        jcxForAnnexRankingToo.setSelected(mTournament.getParams().isApplyToAnnexIndiv());

        final ArrayList<String> rankChoices = new ArrayList<>();
        rankChoices.add(Translate.translate(CS_None));
        rankChoices.add(Translate.translate(CS_Points));
        rankChoices.add(Translate.translate(CS_OpponentPoints));
        rankChoices.add(Translate.translate(CS_ACCR_VictoryDrawLost));
        rankChoices.add(Translate.translate(CS_ELO));
        rankChoices.add(Translate.translate(CS_OpponentsElo));
        rankChoices.add(Translate.translate(CS_MatchCount));
        rankChoices.add(Translate.translate(CS_OpponentsPointsExceptOwnMatch));
        rankChoices.add(Translate.translate(CS_TablesPoints));
        rankChoices.add(Translate.translate(CS_PointsWithoutBonus));
        rankChoices.add(Translate.translate(CS_BonusPoints));
        rankChoices.add(Translate.translate(Translate.CS_HeadByHead));
        rankChoices.add(Translate.translate(Translate.CS_Tier));
        rankChoices.add(Translate.translate(Translate.CS_Teammates_Points));
        rankChoices.add(Translate.translate(Translate.CS_Teammates_VND));
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            final Criterion criteria = Tournament.getTournament().getParams().getCriterion(i);
            rankChoices.add(criteria.getName() + " " + Translate.translate(CS_Player));
            rankChoices.add(criteria.getName() + " " + Translate.translate(CS_Opponent));
            rankChoices.add(criteria.getName() + " " + Translate.translate(CS_Difference));
        }
        
        for (int i = 0; i < Tournament.getTournament().getParams().getFormulaCount(); i++) {
            final Formula formula = Tournament.getTournament().getParams().getFormula(i);
            rankChoices.add(formula.getName());
        }

        jcbRank1.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank2.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank3.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank4.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank5.setModel(new DefaultComboBoxModel(rankChoices.toArray()));

        jcbRank1.removeActionListener(jcbRank1.getActionListeners()[0]);
        jcbRank2.removeActionListener(jcbRank2.getActionListeners()[0]);
        jcbRank3.removeActionListener(jcbRank3.getActionListeners()[0]);
        jcbRank4.removeActionListener(jcbRank4.getActionListeners()[0]);
        jcbRank5.removeActionListener(jcbRank5.getActionListeners()[0]);

        jcbRank1.setSelectedIndex(mTournament.getParams().getRankingIndiv1());
        jcbRank2.setSelectedIndex(mTournament.getParams().getRankingIndiv2());
        jcbRank3.setSelectedIndex(mTournament.getParams().getRankingIndiv3());
        jcbRank4.setSelectedIndex(mTournament.getParams().getRankingIndiv4());
        jcbRank5.setSelectedIndex(mTournament.getParams().getRankingIndiv5());

        jcbRank1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank1ActionPerformed(evt);
            }
        });

        jcbRank2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank2ActionPerformed(evt);
            }
        });

        jcbRank3.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank3ActionPerformed(evt);
            }
        });

        jcbRank4.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank4ActionPerformed(evt);
            }
        });

        jcbRank5.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank5ActionPerformed(evt);
            }
        });
        
        if (Tournament.getTournament().isClient()) {
            jcbRank1.setEnabled(false);
            jcbRank2.setEnabled(false);
            jcbRank3.setEnabled(false);
            jcbRank4.setEnabled(false);
            jcbRank5.setEnabled(false);
            jcxBestResult.setEnabled(false);
            jcxExceptBestAndWorst.setEnabled(false);
            jcxExceptBestAndWorst.setEnabled(false);
            jcxForAnnexRankingToo.setEnabled(false);
            jcxLargeVictory.setEnabled(false);
            jcxLittleLoss.setEnabled(false);
            jlbLargeVictoryGap.setEnabled(false);
            jlbLittleLossGap.setEnabled(false);
            jspBestResults.setEnabled(false);
            jtffConcedeed.setEnabled(false);
            jtffDraw.setEnabled(false);
            jtffLargeVictory.setEnabled(false);
            jtffLargeVictoryGap.setEnabled(false);
            jtffLittleLost.setEnabled(false);
            jtffLittleLostGap.setEnabled(false);
            jtffLost.setEnabled(false);
            jtffRefused.setEnabled(false);
            jtffVictory.setEnabled(false);
            
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox jcbRank1;
    private javax.swing.JComboBox jcbRank2;
    private javax.swing.JComboBox jcbRank3;
    private javax.swing.JComboBox jcbRank4;
    private javax.swing.JComboBox jcbRank5;
    private javax.swing.JCheckBox jcxBestResult;
    private javax.swing.JCheckBox jcxExceptBestAndWorst;
    private javax.swing.JCheckBox jcxForAnnexRankingToo;
    private javax.swing.JCheckBox jcxLargeVictory;
    private javax.swing.JCheckBox jcxLittleLoss;
    private javax.swing.JLabel jlbLargeVictoryGap;
    private javax.swing.JLabel jlbLittleLossGap;
    private javax.swing.JSpinner jspBestResults;
    private javax.swing.JFormattedTextField jtffConcedeed;
    private javax.swing.JFormattedTextField jtffDraw;
    private javax.swing.JFormattedTextField jtffLargeVictory;
    private javax.swing.JFormattedTextField jtffLargeVictoryGap;
    private javax.swing.JFormattedTextField jtffLittleLost;
    private javax.swing.JFormattedTextField jtffLittleLostGap;
    private javax.swing.JFormattedTextField jtffLost;
    private javax.swing.JFormattedTextField jtffRefused;
    private javax.swing.JFormattedTextField jtffVictory;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(JPNParamIndiv.class.getName());

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

}
