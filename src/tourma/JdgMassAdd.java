/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * JdgChangePairing.java
 *
 * Created on 7 mai 2011, 10:51:43
 */
package tourma;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.table.TableColumn;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.IContainCoachs;
import tourma.data.Match;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.mjtTeamsAndCoaches;
import tourma.utils.NafTask;

/**
 *
 * @author Administrateur
 */
public final class JdgMassAdd extends JDialog implements PropertyChangeListener{
    
    private static final long serialVersionUID = 1L;
    ArrayList<Clan> _clans = new ArrayList<>();

    /**
     * Creates new form jdgChangePairing
     *
     * @param parent
     * @param modal
     * @param round
     */
    public JdgMassAdd(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
        
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();
        
        this.setSize(800, 600);
        
        final int screenWidth = dmode.getWidth();
        final int screenHeight = dmode.getHeight();
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            Clan c = Tournament.getTournament().getClan(i);
            _clans.add(c);
        }
        
        update();
    }
    
    @SuppressWarnings({"PMD.MethodArgumentCouldBeFinal", "PMD.LocalVariableCouldBeFinal"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();
        jsp = new javax.swing.JScrollPane();
        jpnMatchs = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTeamsAndCoaches = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jbtAdd = new javax.swing.JButton();
        jbtMinus = new javax.swing.JButton();
        jbtNAF = new javax.swing.JButton();
        jbtImportExcel = new javax.swing.JButton();

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.setName("ok"); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        jbtCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Halt.png"))); // NOI18N
        jbtCancel.setText(bundle.getString("ANNULER")); // NOI18N
        jbtCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jbtCancel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jsp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jpnMatchs.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Team&Coaches"))); // NOI18N
        jpnMatchs.setName("jpnMatchs"); // NOI18N
        jpnMatchs.setLayout(new java.awt.BorderLayout());

        jtTeamsAndCoaches.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtTeamsAndCoaches);

        jpnMatchs.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jsp.setViewportView(jpnMatchs);

        getContentPane().add(jsp, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jbtAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddActionPerformed(evt);
            }
        });
        jPanel2.add(jbtAdd);

        jbtMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMinusActionPerformed(evt);
            }
        });
        jPanel2.add(jbtMinus);

        jbtNAF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/naf.png"))); // NOI18N
        jbtNAF.setToolTipText("Update NAF number");
        jbtNAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNAFActionPerformed(evt);
            }
        });
        jPanel2.add(jbtNAF);

        jbtImportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/excel.png"))); // NOI18N
        jbtImportExcel.setToolTipText("Import Excel File");
        jbtImportExcel.setEnabled(false);
        jbtImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtImportExcelActionPerformed(evt);
            }
        });
        jPanel2.add(jbtImportExcel);

        getContentPane().add(jPanel2, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtCancelActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        
        this.setVisible(false);

    }//GEN-LAST:event_jbtOKActionPerformed

    private void jbtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddActionPerformed
        if (Tournament.getTournament().getParams().isTeamTournament()) {
            Team t = new Team("New Team " + Tournament.getTournament().getTeamsCount());
            for (int i = 0; i < Tournament.getTournament().getParams().getTeamMatesNumber(); i++) {
                Coach c = new Coach("New Coach " + (Tournament.getTournament().getCoachsCount() + i));
                c.setTeam(c.getName() + " team");
                c.setRoster(RosterType.getRosterType(0));
                c.setActive(true);
                c.setClan(Tournament.getTournament().getClan(0));
                t.setClan(Tournament.getTournament().getClan(0));
                t.addCoach(c);
                Tournament.getTournament().addCoach(c);
            }
            Tournament.getTournament().addTeam(t);
        } else {
            Coach c = new Coach("New Coach " + Tournament.getTournament().getCoachsCount());
            c.setTeam(c.getName() + " team");
            c.setRoster(RosterType.getRosterType(0));
            c.setClan(Tournament.getTournament().getClan(0));
            c.setActive(true);
            Tournament.getTournament().addCoach(c);
        }
        update();
    }//GEN-LAST:event_jbtAddActionPerformed

    private void jbtMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMinusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtMinusActionPerformed

     private ProgressMonitor progressMonitor;
     private static final String CS_DownloadFromNAF = "DownloadFromNAF";
    private static final String CS_Downloading = "Downloading";
    
    private NafTask task;

    private void jbtNAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNAFActionPerformed
                progressMonitor = new ProgressMonitor(this,
                Translate.translate(CS_DownloadFromNAF),
                Translate.translate(CS_Downloading), 0,
                Tournament.getTournament().getCoachsCount());
        progressMonitor.setProgress(0);

        task = new NafTask(progressMonitor);
        task.addPropertyChangeListener(this);
        task.execute();

        update();
    }//GEN-LAST:event_jbtNAFActionPerformed

    private void jbtImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtImportExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtImportExcelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtImportExcel;
    private javax.swing.JButton jbtMinus;
    private javax.swing.JButton jbtNAF;
    private javax.swing.JButton jbtOK;
    private javax.swing.JPanel jpnMatchs;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JTable jtTeamsAndCoaches;
    // End of variables declaration//GEN-END:variables

    /**
     * Update Panel
     */
    protected void update() {
        mjtTeamsAndCoaches model = new mjtTeamsAndCoaches(Tournament.getTournament().getParams().isTeamTournament());
        jtTeamsAndCoaches.setModel(model);
        jtTeamsAndCoaches.setDefaultRenderer(Integer.class, model);
        jtTeamsAndCoaches.setDefaultRenderer(String.class, model);
        jtTeamsAndCoaches.setDefaultRenderer(RosterType.class, model);
        jtTeamsAndCoaches.setDefaultRenderer(Team.class, model);
        jtTeamsAndCoaches.setDefaultRenderer(RosterType.class, model);
        jtTeamsAndCoaches.setDefaultRenderer(Coach.class, model);
        jtTeamsAndCoaches.setDefaultRenderer(Clan.class, model);
        
        TableColumn rosterColumn;
        if (Tournament.getTournament().getParams().isTeamTournament()) {
            rosterColumn = jtTeamsAndCoaches.getColumnModel().getColumn(4);
        } else {
            rosterColumn = jtTeamsAndCoaches.getColumnModel().getColumn(2);
        }        
        
        JComboBox jcbRoster = new JComboBox(RosterType.getRostersNames());
        rosterColumn.setCellEditor(new DefaultCellEditor(jcbRoster));
        
        TableColumn clanColumn;
        if (Tournament.getTournament().getParams().isTeamTournament()) {
            clanColumn = jtTeamsAndCoaches.getColumnModel().getColumn(5);
        } else {
            clanColumn = jtTeamsAndCoaches.getColumnModel().getColumn(3);
        }        
        
        JComboBox jcbClans = new JComboBox(_clans.toArray());
        clanColumn.setCellEditor(new DefaultCellEditor(jcbClans));
        
        if (Tournament.getTournament().getParams().isTeamTournament()) {
            jtTeamsAndCoaches.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jcbClans));
        }
        
        repaint();
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressMonitor.setProgress(progress);
            String message = String.format("%d%%.\n", progress);
            progressMonitor.setNote(message);
            //taskOutput.append(message);
            if (progressMonitor.isCanceled() || task.isDone()) {
                Toolkit.getDefaultToolkit().beep();
                if (progressMonitor.isCanceled()) {
                    task.cancel(true);
                    //taskOutput.append("Task canceled.\n");
                } else {
                    //taskOutput.append("Task completed.\n");
                }
            }
        }
        update();
    }    
}
