package tourma.views.round;

import java.util.ArrayList;
import tourma.MainFrame;
import tourma.data.Category;
import tourma.data.Coach;
import tourma.data.Team;
import tourma.data.Tournament;
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

        if (Tournament.getTournament().getParams().isTeamTournament()) {
            for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
                Team team = Tournament.getTournament().getTeam(i);
                if (team.containsCategory(g)) {
                    mEnableTeam = true;
                    break;
                }
            }
        }

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            Coach coach = Tournament.getTournament().getCoach(i);
            if (coach.containsCategory(g)) {
                mEnableCoach = true;
                break;
            }
        }

        if (!mEnableCoach) {
            jPanel2.remove(jspIndiv);
        }

        if (!mEnableTeam) {
            jPanel2.remove(jspTeam);
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
        jbtGeneral = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jspIndiv = new javax.swing.JScrollPane();
        jtbCategory = new javax.swing.JTable();
        jspTeam = new javax.swing.JScrollPane();
        jtbTeam = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jbtGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtGeneral.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralActionPerformed(evt);
            }
        });
        jPanel1.add(jbtGeneral);

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
            final ArrayList<Coach> al = new ArrayList<>();

            for (int i = 0; i < mTournament.getCoachsCount(); i++) {
                final Coach c = mTournament.getCoach(i);
                if (c.containsCategory(mCategory)) {
                    al.add(c);
                }
            }

            final MjtRankingIndiv tableModel = new MjtRankingIndiv(mRoundNumber, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(),
                    al, mTournament.getParams().isTeamTournament(), mRoundOnly, false);
            jtbCategory.setModel(tableModel);
            jtbCategory.setDefaultRenderer(String.class, tableModel);
            jtbCategory.setDefaultRenderer(Integer.class, tableModel);

            jtbCategory.setRowHeight(25);
            TableFormat.setColumnSize(jtbCategory);
        }
        if (mEnableTeam) {
            final ArrayList<Team> al = new ArrayList<>();

            for (int i = 0; i < mTournament.getTeamsCount(); i++) {
                final Team t = mTournament.getTeam(i);
                if (t.containsCategory(mCategory)) {
                    al.add(t);
                }
            }

            final MjtRankingTeam tableModel = new MjtRankingTeam(
                    mTournament.getParams().isTeamTournament(),
                    mRoundNumber,
                    al,
                    mRoundOnly);
            jtbTeam.setModel(tableModel);
            jtbTeam.setDefaultRenderer(String.class, tableModel);
            jtbTeam.setDefaultRenderer(Integer.class, tableModel);

            jtbTeam.setRowHeight(25);
            TableFormat.setColumnSize(jtbTeam);
        }
    }

    private final static String CS_GeneralByCategory="GENERAL PAR CATEGORIE";
    
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralActionPerformed
        final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true,
                Translate.translate(CS_GeneralByCategory) + ": " 
                        + mCategory.getName(), mRoundNumber, mTournament, (MjtRanking) jtbCategory.getModel(), 0);
        jdg.setVisible(true);
}//GEN-LAST:event_jbtGeneralActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtGeneral;
    private javax.swing.JScrollPane jspIndiv;
    private javax.swing.JScrollPane jspTeam;
    private javax.swing.JTable jtbCategory;
    private javax.swing.JTable jtbTeam;
    // End of variables declaration//GEN-END:variables

}