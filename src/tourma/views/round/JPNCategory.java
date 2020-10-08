package tourma.views.round;

import java.util.ArrayList;
import tourma.MainFrame;
import tourma.data.Category;
import tourma.data.Coach;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.data.ranking.IndivRanking;
import tourma.data.ranking.TeamRanking;
import tourma.languages.Translate;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingTeam;
import tourma.utils.display.TableFormat;
import tourma.views.report.JdgRanking;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * JPNCategory.java
 *
 * Created on 21 mai 2011, 23:18:17
 */
/**
 *
 * @author Administrateur
 */
public final class JPNCategory extends javax.swing.JPanel {

    private final Tournament mTournament;
    private final Category mCategory;
    private final int mRoundNumber;

    /**
     *
     */
    private boolean mRoundOnly = false;
    private boolean mEnableTeam = false;
    private boolean mEnableCoach = false;

    private int mPageIndivCount = 0;
    private int mPageIndivIndex = 0;

    private int mPageTeamCount = 0;
    private int mPageTeamIndex = 0;

    private int nbTeam = 0;
    private int nbCoach = 0;

    /**
     * Creates new form JPNCategory
     *
     * @param t
     * @param g
     * @param roundNumber
     */
    public JPNCategory(final Tournament t, final Category g, final int roundNumber) {
        initComponents();
        mTournament = t;
        mCategory = g;
        mRoundNumber = roundNumber;

        if (t.getParams().isTeamTournament()) {

            nbTeam = 0;
            for (int i = 0; i < t.getTeamsCount(); i++) {

                Team team = t.getTeam(i);
                if (team.containsCategory(g)) {
                    mEnableTeam = true;
                    nbTeam++;
                }
            }

            mPageTeamCount = nbTeam / t.getParams().getPageSize();
            if (mPageTeamCount * t.getParams().getPageSize() < nbTeam) {
                mPageTeamCount = mPageTeamCount + 1;
            }
            mPageTeamIndex = 1;

        }

        nbCoach = 0;
        for (int i = 0; i < t.getCoachsCount(); i++) {
            Coach coach = t.getCoach(i);
            if (coach.containsCategory(g)) {
                mEnableCoach = true;
                nbCoach++;
            }
        }

        mPageIndivCount = nbCoach / t.getParams().getPageSize();
        if (mPageIndivCount * t.getParams().getPageSize() < nbCoach) {
            mPageIndivCount = mPageIndivCount + 1;
        }
        mPageIndivIndex = 1;

        if (!mEnableCoach) {
            jPanel2.remove(jspIndiv);
            jPanel1.remove(jbtBackIndiv);
            jPanel1.remove(jbtNextIndiv);
            jPanel1.remove(jlbPageIndiv);
        }

        if (!mEnableTeam) {
            jPanel2.remove(jspTeam);
            jPanel1.remove(jbtBackTeam);
            jPanel1.remove(jbtNextTeam);
            jPanel1.remove(jlbPageTeam);
        }

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

        jPanel1 = new javax.swing.JPanel();
        jbtBackIndiv = new javax.swing.JButton();
        jlbPageIndiv = new javax.swing.JLabel();
        jbtNextIndiv = new javax.swing.JButton();
        jbtGeneral = new javax.swing.JButton();
        jbtBackTeam = new javax.swing.JButton();
        jlbPageTeam = new javax.swing.JLabel();
        jbtNextTeam = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jspIndiv = new javax.swing.JScrollPane();
        jtbCategory = new javax.swing.JTable();
        jspTeam = new javax.swing.JScrollPane();
        jtbTeam = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jbtBackIndiv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Backward.png"))); // NOI18N
        jbtBackIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBackIndivActionPerformed(evt);
            }
        });
        jPanel1.add(jbtBackIndiv);

        jlbPageIndiv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPageIndiv.setText("jLabel1");
        jPanel1.add(jlbPageIndiv);

        jbtNextIndiv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Forward.png"))); // NOI18N
        jbtNextIndiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextIndivActionPerformed(evt);
            }
        });
        jPanel1.add(jbtNextIndiv);

        jbtGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtGeneral.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralActionPerformed(evt);
            }
        });
        jPanel1.add(jbtGeneral);

        jbtBackTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Backward.png"))); // NOI18N
        jbtBackTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBackTeamActionPerformed(evt);
            }
        });
        jPanel1.add(jbtBackTeam);

        jlbPageTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPageTeam.setText("jLabel1");
        jPanel1.add(jlbPageTeam);

        jbtNextTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Forward.png"))); // NOI18N
        jbtNextTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNextTeamActionPerformed(evt);
            }
        });
        jPanel1.add(jbtNextTeam);

        add(jPanel1, java.awt.BorderLayout.SOUTH);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jtbCategory.setModel(new javax.swing.table.DefaultTableModel(
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
        jspIndiv.setViewportView(jtbCategory);

        jPanel2.add(jspIndiv);

        jtbTeam.setModel(new javax.swing.table.DefaultTableModel(
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
        jspTeam.setViewportView(jtbTeam);

        jPanel2.add(jspTeam);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Update Panel
     */
    public void update() {

        if (mEnableCoach) {
            updateIndiv();

        }
        if (mEnableTeam) {
            updateTeam();

        }
    }

    private void updateIndiv() {
        final ArrayList<Coach> al = new ArrayList<>();

        for (int i = 0; i < mTournament.getCoachsCount(); i++) {
            final Coach c = mTournament.getCoach(i);
            if (c.containsCategory(mCategory)) {
                al.add(c);
            }
        }

        jlbPageIndiv.setText(Integer.toString(mPageIndivIndex) + " / " + Integer.toString(mPageIndivCount));
        IndivRanking ranking=new IndivRanking(mRoundNumber, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(),
                al, mTournament.getParams().isTeamTournament(), mRoundOnly, false, false);
        final MjtRankingIndiv tableModel = new MjtRankingIndiv(ranking);
        jtbCategory.setModel(tableModel);
        jtbCategory.setDefaultRenderer(String.class, tableModel);
        jtbCategory.setDefaultRenderer(Integer.class, tableModel);

        jtbCategory.setRowHeight(25);
        TableFormat.setColumnSize(jtbCategory);
    }

    private void updateTeam() {
        final ArrayList<Team> al = new ArrayList<>();
        jlbPageTeam.setText(Integer.toString(mPageTeamIndex) + " / " + Integer.toString(mPageTeamCount));
        for (int i = 0; i < mTournament.getTeamsCount(); i++) {
            final Team t = mTournament.getTeam(i);
            if (t.containsCategory(mCategory)) {
                al.add(t);
            }
        }

        TeamRanking ranking=new TeamRanking(mTournament.getParams().isTeamTournament(),
                mRoundNumber,mTournament.getParams(),
                al,
                mRoundOnly);
        
        final MjtRankingTeam tableModel = new MjtRankingTeam(ranking);
        jtbTeam.setModel(tableModel);
        jtbTeam.setDefaultRenderer(String.class, tableModel);
        jtbTeam.setDefaultRenderer(Integer.class, tableModel);

        jtbTeam.setRowHeight(25);
        //TableFormat.setColumnSize(jtbTeam);
    }

    private final static String CS_GeneralByCategory = "GENERAL PAR CATEGORIE";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralActionPerformed
        final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true,
                Translate.translate(CS_GeneralByCategory) + ": "
                + mCategory.getName(), mRoundNumber, mTournament, (MjtRanking) jtbCategory.getModel(), 0);
        jdg.setVisible(true);
}//GEN-LAST:event_jbtGeneralActionPerformed

    private void jbtBackIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBackIndivActionPerformed
        mPageIndivIndex = mPageIndivIndex - 1;
        if (mPageIndivIndex <= 1) {
            mPageIndivIndex = 1;
        }
        updateIndiv();
    }//GEN-LAST:event_jbtBackIndivActionPerformed

    private void jbtNextIndivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextIndivActionPerformed
        mPageIndivIndex = mPageIndivIndex + 1;
        if (mPageIndivIndex >= mPageIndivCount) {
            mPageIndivIndex = mPageIndivCount;
        }
        updateIndiv();
    }//GEN-LAST:event_jbtNextIndivActionPerformed

    private void jbtBackTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBackTeamActionPerformed
        mPageTeamIndex = mPageTeamIndex - 1;
        if (mPageTeamIndex <= 1) {
            mPageTeamIndex = 1;
        }
        updateTeam();
    }//GEN-LAST:event_jbtBackTeamActionPerformed

    private void jbtNextTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNextTeamActionPerformed
        mPageTeamIndex = mPageTeamIndex + 1;
        if (mPageTeamIndex >= mPageTeamCount) {
            mPageTeamIndex = mPageTeamCount;
        }
        updateTeam();
    }//GEN-LAST:event_jbtNextTeamActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtBackIndiv;
    private javax.swing.JButton jbtBackTeam;
    private javax.swing.JButton jbtGeneral;
    private javax.swing.JButton jbtNextIndiv;
    private javax.swing.JButton jbtNextTeam;
    private javax.swing.JLabel jlbPageIndiv;
    private javax.swing.JLabel jlbPageTeam;
    private javax.swing.JScrollPane jspIndiv;
    private javax.swing.JScrollPane jspTeam;
    private javax.swing.JTable jtbCategory;
    private javax.swing.JTable jtbTeam;
    // End of variables declaration//GEN-END:variables

}
