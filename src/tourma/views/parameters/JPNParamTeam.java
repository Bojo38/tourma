/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.parameters;

import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import tourma.data.Criteria;
import tourma.data.ETeamPairing;
import tourma.data.ITournament;
import tourma.data.Tournament;
import tourma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public final class JPNParamTeam extends javax.swing.JPanel {

    private final ITournament mTournament;

    /**
     * Creates new form JPNParamTeam
     */
    public JPNParamTeam() {
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

        jPanel1 = new javax.swing.JPanel();
        jrbTeamVictory = new javax.swing.JRadioButton();
        jrbCoachPoints = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        jtffVictoryTeam = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jtffDrawTeam = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        jtffLostTeam = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        jcbRank1Team = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        jcbRank2Team = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        jcbRank3Team = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jcbRank4Team = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jcbRank5Team = new javax.swing.JComboBox();
        jlbVictoryPoints = new javax.swing.JLabel();
        jtffTeamVictoryBonus = new javax.swing.JFormattedTextField();
        jlbVictoryPoints1 = new javax.swing.JLabel();
        jtffTeamDrawBonus = new javax.swing.JFormattedTextField();
        jcxBestResult = new javax.swing.JCheckBox();
        jspBestResults = new javax.swing.JSpinner();
        jcxExceptBestAndWorst = new javax.swing.JCheckBox();
        jcxForAnnexRankingToo = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jcxTeamBalance = new javax.swing.JCheckBox();
        jcxIndividualBalance = new javax.swing.JCheckBox();

        setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Team Ranking"))); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(13, 2));

        jrbTeamVictory.setText(bundle.getString("UseTeamVictory")); // NOI18N
        jrbTeamVictory.setHideActionText(true);
        jrbTeamVictory.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jrbTeamVictory.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jrbTeamVictory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTeamVictoryActionPerformed(evt);
            }
        });
        jPanel1.add(jrbTeamVictory);

        jrbCoachPoints.setText(bundle.getString("UseCoachsPointsSum")); // NOI18N
        jrbCoachPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCoachPointsActionPerformed(evt);
            }
        });
        jPanel1.add(jrbCoachPoints);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel23.setText(bundle.getString("VictoryKey")); // NOI18N
        jLabel23.setToolTipText(bundle.getString("VictoryNumberOfPointsKey")); // NOI18N
        jPanel1.add(jLabel23);

        jtffVictoryTeam.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffVictoryTeam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffVictoryTeamFocusLost(evt);
            }
        });
        jPanel1.add(jtffVictoryTeam);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel24.setText(bundle.getString("DrawKey")); // NOI18N
        jLabel24.setToolTipText(bundle.getString("DrawMatchTipKey")); // NOI18N
        jPanel1.add(jLabel24);

        jtffDrawTeam.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffDrawTeam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffDrawTeamFocusLost(evt);
            }
        });
        jPanel1.add(jtffDrawTeam);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel25.setText(bundle.getString("LossKey")); // NOI18N
        jLabel25.setToolTipText(bundle.getString("LossTipKey")); // NOI18N
        jPanel1.add(jLabel25);

        jtffLostTeam.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffLostTeam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffLostTeamFocusLost(evt);
            }
        });
        jPanel1.add(jtffLostTeam);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel26.setText(tourma.languages.Translate.translate("RankingCriteria")+" 1:");
        jLabel26.setToolTipText("null");
        jPanel1.add(jLabel26);

        jcbRank1Team.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
        jcbRank1Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank1TeamActionPerformed(evt);
            }
        });
        jPanel1.add(jcbRank1Team);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel27.setText(tourma.languages.Translate.translate("RankingCriteria")+" 2:");
        jLabel27.setToolTipText("null");
        jPanel1.add(jLabel27);

        jcbRank2Team.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
        jcbRank2Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank2TeamActionPerformed(evt);
            }
        });
        jPanel1.add(jcbRank2Team);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel28.setText(tourma.languages.Translate.translate("RankingCriteria")+" 3:");
        jLabel28.setToolTipText("null");
        jPanel1.add(jLabel28);

        jcbRank3Team.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
        jcbRank3Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank3TeamActionPerformed(evt);
            }
        });
        jPanel1.add(jcbRank3Team);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel29.setText(tourma.languages.Translate.translate("RankingCriteria")+" 4:");
        jLabel29.setToolTipText("null");
        jPanel1.add(jLabel29);

        jcbRank4Team.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
        jcbRank4Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank4TeamActionPerformed(evt);
            }
        });
        jPanel1.add(jcbRank4Team);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel30.setText(tourma.languages.Translate.translate("RankingCriteria")+" 5:");
        jLabel30.setToolTipText("null");
        jPanel1.add(jLabel30);

        jcbRank5Team.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aucun", "Points", "Points adversaires", "Touchdowns", "Sorties", "Aggressions", "Différence de touchdowns", "Différence de sorties", "Différence d'aggression", "Victoire-Nul-Défaite" }));
        jcbRank5Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRank5TeamActionPerformed(evt);
            }
        });
        jPanel1.add(jcbRank5Team);

        jlbVictoryPoints.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbVictoryPoints.setText(bundle.getString("TeamVictoryBonus")); // NOI18N
        jlbVictoryPoints.setToolTipText("null");
        jPanel1.add(jlbVictoryPoints);

        jtffTeamVictoryBonus.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffTeamVictoryBonus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffTeamVictoryBonusFocusLost(evt);
            }
        });
        jPanel1.add(jtffTeamVictoryBonus);

        jlbVictoryPoints1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbVictoryPoints1.setText(bundle.getString("TeamDrawBonus")); // NOI18N
        jlbVictoryPoints1.setToolTipText("null");
        jPanel1.add(jlbVictoryPoints1);

        jtffTeamDrawBonus.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtffTeamDrawBonus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtffTeamDrawBonusFocusLost(evt);
            }
        });
        jPanel1.add(jtffTeamDrawBonus);

        jcxBestResult.setText(bundle.getString("KeepBestResults")); // NOI18N
        jcxBestResult.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcxBestResult.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcxBestResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxBestResultActionPerformed(evt);
            }
        });
        jPanel1.add(jcxBestResult);

        jspBestResults.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspBestResultsStateChanged(evt);
            }
        });
        jPanel1.add(jspBestResults);

        jcxExceptBestAndWorst.setText(bundle.getString("ExceptBestAndWorst")); // NOI18N
        jcxExceptBestAndWorst.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcxExceptBestAndWorst.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcxExceptBestAndWorst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxExceptBestAndWorstActionPerformed(evt);
            }
        });
        jPanel1.add(jcxExceptBestAndWorst);

        jcxForAnnexRankingToo.setText(bundle.getString("ExceptBestAndWorstAnnex")); // NOI18N
        jcxForAnnexRankingToo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxForAnnexRankingTooActionPerformed(evt);
            }
        });
        jPanel1.add(jcxForAnnexRankingToo);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Pairing"))); // NOI18N
        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        jcxTeamBalance.setText(bundle.getString("TeamNumberBalance")); // NOI18N
        jcxTeamBalance.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcxTeamBalance.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcxTeamBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxTeamBalanceActionPerformed(evt);
            }
        });
        jPanel2.add(jcxTeamBalance);

        jcxIndividualBalance.setText(bundle.getString("IndividualBalance")); // NOI18N
        jcxIndividualBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxIndividualBalanceActionPerformed(evt);
            }
        });
        jPanel2.add(jcxIndividualBalance);

        add(jPanel2, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jrbTeamVictoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbTeamVictoryActionPerformed
        mTournament.getParams().setTeamVictoryOnly(jrbTeamVictory.isSelected());
        update();
    }//GEN-LAST:event_jrbTeamVictoryActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jrbCoachPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCoachPointsActionPerformed
        mTournament.getParams().setTeamVictoryOnly(!jrbCoachPoints.isSelected());
        update();
    }//GEN-LAST:event_jrbCoachPointsActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffVictoryTeamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffVictoryTeamFocusLost
        try {
            jtffVictoryTeam.commitEdit();
            final int points = ((Number) jtffVictoryTeam.getValue()).intValue();
            mTournament.getParams().setPointsTeamVictory(points);
        } catch (ParseException e) {
            jtffVictoryTeam.setValue(jtffVictoryTeam.getValue());
        }
        update();
    }//GEN-LAST:event_jtffVictoryTeamFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffDrawTeamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffDrawTeamFocusLost
        try {
            jtffDrawTeam.commitEdit();
            final int points = ((Number) jtffDrawTeam.getValue()).intValue();
            mTournament.getParams().setPointsTeamDraw(points);
        } catch (ParseException e) {
            jtffDrawTeam.setValue(jtffDrawTeam.getValue());
        }
        update();
    }//GEN-LAST:event_jtffDrawTeamFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffLostTeamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffLostTeamFocusLost
        try {
            jtffLostTeam.commitEdit();
            final int points = ((Number) jtffLostTeam.getValue()).intValue();
            mTournament.getParams().setPointsTeamLost(points);
        } catch (ParseException e) {
            jtffLostTeam.setValue(jtffLostTeam.getValue());
        }
        update();
    }//GEN-LAST:event_jtffLostTeamFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank1TeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank1TeamActionPerformed
        mTournament.getParams().setRankingTeam1(jcbRank1Team.getSelectedIndex());
        update();
    }//GEN-LAST:event_jcbRank1TeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank2TeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank2TeamActionPerformed
        mTournament.getParams().setRankingTeam2(jcbRank2Team.getSelectedIndex());
        update();
    }//GEN-LAST:event_jcbRank2TeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank3TeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank3TeamActionPerformed
        mTournament.getParams().setRankingTeam3(jcbRank3Team.getSelectedIndex());
        update();
    }//GEN-LAST:event_jcbRank3TeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank4TeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank4TeamActionPerformed
        mTournament.getParams().setRankingTeam4(jcbRank4Team.getSelectedIndex());
        update();
    }//GEN-LAST:event_jcbRank4TeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbRank5TeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRank5TeamActionPerformed
        mTournament.getParams().setRankingTeam5(jcbRank5Team.getSelectedIndex());
        update();
    }//GEN-LAST:event_jcbRank5TeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffTeamVictoryBonusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffTeamVictoryBonusFocusLost
        try {
            jtffTeamVictoryBonus.commitEdit();
            final int points = ((Number) jtffTeamVictoryBonus.getValue()).intValue();
            mTournament.getParams().setPointsTeamVictoryBonus(points);
        } catch (ParseException e) {
            jtffTeamVictoryBonus.setValue(jtffTeamVictoryBonus.getValue());
        }
        update();
    }//GEN-LAST:event_jtffTeamVictoryBonusFocusLost
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jtffTeamDrawBonusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffTeamDrawBonusFocusLost
        try {
            jtffTeamDrawBonus.commitEdit();
            final int points = ((Number) jtffTeamDrawBonus.getValue()).intValue();
            mTournament.getParams().setPointsTeamDrawBonus(points);
        } catch (ParseException e) {
            jtffTeamDrawBonus.setValue(jtffTeamDrawBonus.getValue());
        }
        update();
    }//GEN-LAST:event_jtffTeamDrawBonusFocusLost

    private void jcxTeamBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxTeamBalanceActionPerformed

        if ((mTournament.getParams().isTeamTournament()) && (mTournament.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {
            mTournament.getParams().setIndivPairingTeamBalanced(jcxTeamBalance.isSelected());
        }
        update();
    }//GEN-LAST:event_jcxTeamBalanceActionPerformed

    private void jcxIndividualBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxIndividualBalanceActionPerformed
        if ((mTournament.getParams().isTeamTournament()) && (mTournament.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING)) {
            mTournament.getParams().setIndivPairingIndivBalanced(jcxIndividualBalance.isSelected());
        }
        update();
    }//GEN-LAST:event_jcxIndividualBalanceActionPerformed

    private void jcxBestResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxBestResultActionPerformed
        mTournament.getParams().setUseBestResultTeam(jcxBestResult.isSelected());
        update();
    }//GEN-LAST:event_jcxBestResultActionPerformed

    private void jspBestResultsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspBestResultsStateChanged
        mTournament.getParams().setBestResultTeam((Integer) jspBestResults.getValue());
        update();
    }//GEN-LAST:event_jspBestResultsStateChanged

    private void jcxExceptBestAndWorstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxExceptBestAndWorstActionPerformed
        mTournament.getParams().setExceptBestAndWorstTeam(jcxExceptBestAndWorst.isSelected());
        update();
    }//GEN-LAST:event_jcxExceptBestAndWorstActionPerformed

    private void jcxForAnnexRankingTooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxForAnnexRankingTooActionPerformed
        mTournament.getParams().setApplyToAnnexTeam(jcxForAnnexRankingToo.isSelected());
        update();
    }//GEN-LAST:event_jcxForAnnexRankingTooActionPerformed

    private final static String CS_None = "None";
    private final static String CS_Points = "Points";
    private final static String CS_OpponentPoints = "POINTS ADVERSAIRES";
    private final static String CS_ACCR_VictoryDrawLost = "V/N/D";
    private final static String CS_ELO = "ELO";
    private final static String CS_OpponentsElo = "OpponentsElo";
    private final static String CS_MatchCount = "MatchCount";
    private final static String CS_OpponentsPointsExceptOwnMatch = "POINTS ADVERSAIRES EXCEPT OWN MATCH";
    private final static String CS_TablesPoints = "TablesPoints";
    private final static String CS_PointsWithoutBonus = "Points sans bonus";
    private final static String CS_BonusPoints = "Bonus Points";
    private final static String CS_Player = "JOUEUR";
    private final static String CS_Opponent = "ADVERSAIRE";
    private final static String CS_Difference = "DIFFÉRENCE";

    /**
     * Update panel
     */
    public void update() {

        final boolean teamMatches = mTournament.getParams().isTeamTournament() && (mTournament.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING);
        final boolean IndivMatches = mTournament.getParams().isTeamTournament() && (mTournament.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING);

        jtffDrawTeam.setValue(mTournament.getParams().getPointsTeamDraw());
        jtffLostTeam.setValue(mTournament.getParams().getPointsTeamLost());

        jtffVictoryTeam.setValue(mTournament.getParams().getPointsTeamVictory());

        jrbCoachPoints.setEnabled(teamMatches);
        jrbTeamVictory.setEnabled(teamMatches);

        jtffTeamVictoryBonus.setEnabled(teamMatches && (!mTournament.getParams().isTeamVictoryOnly()));

        jtffTeamDrawBonus.setEnabled(teamMatches && (!mTournament.getParams().isTeamVictoryOnly()));
        jlbVictoryPoints.setEnabled(teamMatches && (!mTournament.getParams().isTeamVictoryOnly()));
        jlbVictoryPoints1.setEnabled(teamMatches && (!mTournament.getParams().isTeamVictoryOnly()));

        jtffDrawTeam.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jtffLostTeam.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));

        jtffVictoryTeam.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jtffDrawTeam.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));

        jcxIndividualBalance.setEnabled(IndivMatches);
        jcxTeamBalance.setEnabled(IndivMatches);

        jspBestResults.setEnabled(mTournament.getParams().isUseBestResultTeam());
        jspBestResults.setValue(mTournament.getParams().getBestResultTeam());

        jcxBestResult.setSelected(mTournament.getParams().isUseBestResultTeam());
        jcxExceptBestAndWorst.setEnabled(!mTournament.getParams().isUseBestResultTeam());

        jcxExceptBestAndWorst.setSelected(mTournament.getParams().isExceptBestAndWorstTeam());
        jcxForAnnexRankingToo.setSelected(mTournament.getParams().isApplyToAnnexTeam());

        jLabel23.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jLabel24.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jLabel25.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jLabel26.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jLabel27.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jLabel28.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jLabel29.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jLabel30.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));

        jrbCoachPoints.setSelected(!mTournament.getParams().isTeamVictoryOnly());
        jrbTeamVictory.setSelected(mTournament.getParams().isTeamVictoryOnly());
        jtffTeamVictoryBonus.setValue(mTournament.getParams().getPointsTeamVictoryBonus());
        jtffTeamDrawBonus.setValue(mTournament.getParams().getPointsTeamDrawBonus());

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
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            final Criteria criteria = Tournament.getTournament().getParams().getCriteria(i);
            rankChoices.add(criteria.getName() + " "+Translate.translate(CS_Player));
            rankChoices.add(criteria.getName() + " "+Translate.translate(CS_Opponent));
            rankChoices.add(criteria.getName() + " "+Translate.translate(CS_Difference));
        }

        jcxIndividualBalance.setSelected(mTournament.getParams().isIndivPairingIndivBalanced());
        jcxTeamBalance.setSelected(mTournament.getParams().isIndivPairingTeamBalanced());

        jcbRank1Team.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank2Team.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank3Team.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank4Team.setModel(new DefaultComboBoxModel(rankChoices.toArray()));
        jcbRank5Team.setModel(new DefaultComboBoxModel(rankChoices.toArray()));

        jcbRank1Team.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jcbRank2Team.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jcbRank3Team.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jcbRank4Team.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));
        jcbRank5Team.setEnabled(teamMatches && (mTournament.getParams().isTeamVictoryOnly()));

        jcbRank1Team.removeActionListener(jcbRank1Team.getActionListeners()[0]);
        jcbRank2Team.removeActionListener(jcbRank2Team.getActionListeners()[0]);
        jcbRank3Team.removeActionListener(jcbRank3Team.getActionListeners()[0]);
        jcbRank4Team.removeActionListener(jcbRank4Team.getActionListeners()[0]);
        jcbRank5Team.removeActionListener(jcbRank5Team.getActionListeners()[0]);

        jcbRank1Team.setSelectedIndex(mTournament.getParams().getRankingTeam1());
        jcbRank2Team.setSelectedIndex(mTournament.getParams().gemRankingTeam2());
        jcbRank3Team.setSelectedIndex(mTournament.getParams().getRankingTeam3());
        jcbRank4Team.setSelectedIndex(mTournament.getParams().getRankingTeam4());
        jcbRank5Team.setSelectedIndex(mTournament.getParams().getRankingTeam5());

        jcbRank1Team.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank1TeamActionPerformed(evt);
            }
        });

        jcbRank2Team.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank2TeamActionPerformed(evt);
            }
        });

        jcbRank3Team.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank3TeamActionPerformed(evt);
            }
        });

        jcbRank4Team.addActionListener(
                new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(final java.awt.event.ActionEvent evt) {
                        jcbRank4TeamActionPerformed(evt);
                    }
                });

        jcbRank5Team.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                jcbRank5TeamActionPerformed(evt);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox jcbRank1Team;
    private javax.swing.JComboBox jcbRank2Team;
    private javax.swing.JComboBox jcbRank3Team;
    private javax.swing.JComboBox jcbRank4Team;
    private javax.swing.JComboBox jcbRank5Team;
    private javax.swing.JCheckBox jcxBestResult;
    private javax.swing.JCheckBox jcxExceptBestAndWorst;
    private javax.swing.JCheckBox jcxForAnnexRankingToo;
    private javax.swing.JCheckBox jcxIndividualBalance;
    private javax.swing.JCheckBox jcxTeamBalance;
    private javax.swing.JLabel jlbVictoryPoints;
    private javax.swing.JLabel jlbVictoryPoints1;
    private javax.swing.JRadioButton jrbCoachPoints;
    private javax.swing.JRadioButton jrbTeamVictory;
    private javax.swing.JSpinner jspBestResults;
    private javax.swing.JFormattedTextField jtffDrawTeam;
    private javax.swing.JFormattedTextField jtffLostTeam;
    private javax.swing.JFormattedTextField jtffTeamDrawBonus;
    private javax.swing.JFormattedTextField jtffTeamVictoryBonus;
    private javax.swing.JFormattedTextField jtffVictoryTeam;
    // End of variables declaration//GEN-END:variables

}
