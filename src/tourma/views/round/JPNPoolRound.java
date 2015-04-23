/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPNPoolRound.java
 *
 * Created on 20 juil. 2010, 10:47:49
 */
package tourma.views.round;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.JTable;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.IContainCoachs;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.tableModel.MjtAnnexRank;
import tourma.tableModel.MjtAnnexRankClan;
import tourma.tableModel.MjtRankingClan;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingTeam;
import tourma.utils.TableFormat;
import tourma.views.report.JdgGlobal;
import tourma.views.report.JdgRanking;

/**
 *
 * @author Frederic Berger
 */
public final class JPNPoolRound extends javax.swing.JPanel {
    private static final long serialVersionUID = 20L;

    private final Round mRound;
    private final Tournament mTournament;
    private final Pool mPool;
    private final JTable mJtbTeamMatch = null;

    /**
     *
     */
    private boolean mRoundOnly = false;

    /**
     * Creates new form JPNTeamRound
     * @param r
     * @param t
     * @param p
     */
    public JPNPoolRound(final Round r,final  Tournament t, final Pool p) {
        mPool = p;
        initComponents();
        mRound = r;
        mTournament = t;

        for (int i = 0; i < mTournament.getParams().getCriteriaCount(); i++) {
            final Criteria criteria = mTournament.getParams().getCriteria(i);
            if (!mTournament.getParams().isTeamTournament()) {
                final JPNAnnexRanking jpn = new JPNAnnexRanking(criteria.getName(), criteria, mTournament, mRound, false, false, p.getCompetitors(), new ArrayList<Team>());
                jtpAnnexRank.add(criteria.getName(), jpn);
            } else {

                final ArrayList<Coach> v = new ArrayList<>();
                for (int j = 0; j < p.getCompetitorCount(); j++) {
                    for (int k = 0; k < ((IContainCoachs)p.getCompetitor(j)).getCoachCount(); k++) {
                        v.add(((IContainCoachs)p.getCompetitor(j)).getCoach(k));
                    }
                }
                JPNAnnexRanking jpn = new JPNAnnexRanking(criteria.getName(), criteria, mTournament, mRound, false, true, v, p.getCompetitors());
                jtpAnnexRank.add(criteria.getName(), jpn);
                
                jpn = new JPNAnnexRanking(criteria.getName(), criteria, mTournament, mRound, false, false, v, p.getCompetitors());
                jtpAnnexRank.add(criteria.getName(), jpn);
            }
        }
        update();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtbRankingPool = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jbtGeneralPool = new javax.swing.JButton();
        jbtGlobalPool = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jtpAnnexRank = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(600);

        jPanel2.setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GeneralRankingKey"))); // NOI18N
        jScrollPane3.setPreferredSize(new java.awt.Dimension(466, 300));

        jtbRankingPool.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbRankingPool.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jtbRankingPool);

        jPanel2.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jbtGeneralPool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtGeneralPool.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneralPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralPoolActionPerformed(evt);
            }
        });
        jPanel3.add(jbtGeneralPool);

        jbtGlobalPool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtGlobalPool.setText(bundle.getString("GlobalRankingKey")); // NOI18N
        jbtGlobalPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGlobalPoolActionPerformed(evt);
            }
        });
        jPanel3.add(jbtGlobalPool);

        jPanel2.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setLeftComponent(jPanel2);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jtpAnnexRank, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
 @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralPoolActionPerformed
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {
                final MjtRankingClan model = new MjtRankingClan(i, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), mTournament.getDisplayClans(), mRoundOnly);
                final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GENERAL PAR CLAN"), i + 1, mTournament, model, 0);
                jdg.setVisible(true);
                break;
            }
        }
}//GEN-LAST:event_jbtGeneralPoolActionPerformed
 @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGlobalPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGlobalPoolActionPerformed
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {
               final  MjtRankingClan model = new MjtRankingClan(i, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), mTournament.getDisplayClans(), mRoundOnly);
                final HashMap<Criteria, MjtAnnexRank> annexForRankings = new HashMap<>();
                final HashMap<Criteria, MjtAnnexRank> annexAgainstRankings = new HashMap<>();
                for (int j = 0; j < mTournament.getParams().getCriteriaCount(); j++) {
                    final Criteria crit = mTournament.getParams().getCriteria(j);
                    MjtAnnexRank annex = new MjtAnnexRankClan(i, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                            mTournament.getDisplayClans(), true, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), mRoundOnly);
                    annexForRankings.put(crit, annex);
                    annex = new MjtAnnexRankClan(i, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                            mTournament.getDisplayClans(), true, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), mRoundOnly);
                    annexAgainstRankings.put(crit, annex);
                }
                final JdgGlobal jdg = new JdgGlobal(MainFrame.getMainFrame(), true, i + 1, mTournament, model, annexForRankings, annexAgainstRankings, false, false);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtGlobalPoolActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbtGeneralPool;
    private javax.swing.JButton jbtGlobalPool;
    private javax.swing.JTable jtbRankingPool;
    private javax.swing.JTabbedPane jtpAnnexRank;
    // End of variables declaration//GEN-END:variables

    /**
     * Update Panel
     */
    public void update() {

        int r_index = 0;
        final ArrayList<Round> v = new ArrayList<>();
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mTournament.getRound(i).getHour().before(mRound.getHour())) {
                v.add(mTournament.getRound(i));
            }
            if (mTournament.getRound(i) == mRound) {
                r_index = i;
            }
        }
        v.add(mRound);

        if (mTournament.getParams().isTeamTournament()) {
            MjtRankingTeam mRankingTeam;
            mRankingTeam = new MjtRankingTeam(mTournament.getParams().isTeamVictoryOnly(), v.size() - 1, mPool.getCompetitors(), mRoundOnly);
            jtbRankingPool.setModel(mRankingTeam);
            jtbRankingPool.setDefaultRenderer(String.class, mRankingTeam);
            jtbRankingPool.setDefaultRenderer(Integer.class, mRankingTeam);

            TableFormat.setColumnSize(jtbRankingPool);
        } else {
            MjtRankingIndiv mRankingIndiv;
            mRankingIndiv = new MjtRankingIndiv(r_index, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), mPool.getCompetitors(), mTournament.getParams().isTeamTournament(), mRoundOnly,false);
            jtbRankingPool.setModel(mRankingIndiv);
            jtbRankingPool.setDefaultRenderer(String.class, mRankingIndiv);
            jtbRankingPool.setDefaultRenderer(Integer.class, mRankingIndiv);
            TableFormat.setColumnSize(jtbRankingPool);
        }

        for (int i = 0; i < jtpAnnexRank.getComponentCount(); i++) {
            ((JPNAnnexRanking) jtpAnnexRank.getComponent(i)).update();
        }
        
        jtbRankingPool.setRowHeight(25);
    }
    private static final Logger LOG = Logger.getLogger(JPNPoolRound.class.getName());

   /**
     * 
     * @param r 
     */
    public void setRoundOnly(boolean r)
    {
        mRoundOnly=r;
    }
     private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}