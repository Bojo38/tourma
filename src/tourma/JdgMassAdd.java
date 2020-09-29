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
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableColumn;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import tourma.data.Category;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.RosterType;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.mjtTeamsAndCoaches;
import tourma.utility.ExtensionFileFilter;
import tourma.utils.NafTask;

/**
 *
 * @author Administrateur
 */
public final class JdgMassAdd extends JDialog implements PropertyChangeListener {

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
                Coach c = new Coach("New Coach " + (Tournament.getTournament().getCoachsCount()));
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

        // Select Excel file
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(MainFrame.getMainFrame().getCurrentPath()));
        final FileFilter filter1 = new ExtensionFileFilter(
                "Excel file",
                new String[]{"XLS", "xls"});
        jfc.setFileFilter(filter1);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            loadExcelFile(jfc.getSelectedFile());
        }
        update();

    }//GEN-LAST:event_jbtImportExcelActionPerformed

    private void loadExcelFile(File file) {
        try {
            // Open it
            Workbook workbook = WorkbookFactory.create(file);

            // Navigate in sheets
            int nbSheet = workbook.getNumberOfSheets();
            if (nbSheet < 2) {
                JOptionPane.showMessageDialog(this, "Error, invalid number of shhets");
                return;
            }

            Sheet sheet = workbook.getSheet("Coachs");
            if (sheet == null) {
                JOptionPane.showMessageDialog(this, "Error, sheet \"Coachs\" not found");
                return;
            }

            Sheet calcul = workbook.getSheet("Calculs");
            if (calcul == null) {
                JOptionPane.showMessageDialog(this, "Error, sheet \"Calculs\" not found");
                return;
            }

            // Load Rosters
            for (int i = 0; i <= 40; i++) {
                Row row = calcul.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(1);
                    if (cell != null) {
                        String value = cell.getStringCellValue();
                        if (!value.equals("")) {
                            boolean found = false;
                            // Check if roster exists
                            for (int j = 0; j < RosterType.getRostersNamesCount(); j++) {
                                String name = RosterType.getRostersName(j);
                                if (name.equals("None")) {
                                    found = true;
                                    break;
                                }
                                if (name.equals(value)) {
                                    found = true;
                                    break;
                                }
                                if (RosterType.getRosterTranslation(name).equals(value)) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                RosterType.addRosterName(value);
                            }
                        }
                    }
                }
            }

            // Test if sheet has correct format for team or coach
            // Load sheet content.
            // Load Categories
            String cat = "";
            int i = 1;
            do {
                Row row = sheet.getRow(i);
                if (row == null) {
                    cat = "";
                } else {
                    Cell cell = row.getCell(14);
                    if (cell == null) {
                        cat = "";
                    } else {
                        cat = cell.getStringCellValue();
                        if (!cat.equals("")) {
                            boolean found = false;
                            for (int j = 0; j < Tournament.getTournament().getCategoriesCount(); j++) {
                                Category category = Tournament.getTournament().getCategory(j);
                                if (category.getName().equals(cat)) {
                                    JOptionPane.showMessageDialog(this, "Category " + cat + " already exists");
                                    found = true;
                                }
                            }
                            if (!found) {
                                Category category = new Category(cat);
                                Tournament.getTournament().addCategory(category);
                            }
                        }
                    }
                }
                i++;
            } while (!cat.equals(""));

            // Load Clan
            String cl = "";
            i = 1;
            do {
                Row row = sheet.getRow(i);
                if (row == null) {
                    cl = "";
                } else {
                    Cell cell = row.getCell(15);
                    if (cell == null) {
                        cl = "";
                    } else {
                        cl = cell.getStringCellValue();
                        if (!cl.equals("")) {
                            boolean found = false;
                            for (int j = 0; j < Tournament.getTournament().getClansCount(); j++) {
                                Clan clan = Tournament.getTournament().getClan(j);
                                if (clan.getName().equals(cl)) {
                                    JOptionPane.showMessageDialog(this, "Clan " + cl + " already exists");
                                    found = true;
                                }
                            }
                            if (!found) {
                                Clan clan = new Clan(cl);
                                Tournament.getTournament().addClan(clan);
                            }
                        }
                    }
                }
                i++;
            } while (!cl.equals(""));

            if (Tournament.getTournament().getClansCount() > 1) {
                Tournament.getTournament().getParams().setEnableClans(true);
            }

            if (Tournament.getTournament().getParams().isTeamTournament()) {
                // Load Team            
                String t = "";
                i = 1;
                do {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        t = "";
                    } else {
                        Cell cell = row.getCell(13);
                        if (cell == null) {
                            t = "";
                        } else {
                            t = cell.getStringCellValue();
                            if (!t.equals("")) {
                                boolean found = false;
                                for (int j = 0; j < Tournament.getTournament().getTeamsCount(); j++) {
                                    Team team = Tournament.getTournament().getTeam(j);
                                    if (team.getName().equals(t)) {
                                        //JOptionPane.showMessageDialog(this, "Team " + t + " already exists");
                                        found = true;
                                    }
                                }
                                if (!found) {
                                    Team team = new Team(t);
                                    Tournament.getTournament().addTeam(team);
                                }
                            }
                        }
                    }
                    i++;
                } while (!t.equals(""));
            }

            // Load Coachs
            String c = "";
            i = 1;
            do {
                Row row = sheet.getRow(i);
                if (row == null) {
                    c = "";
                } else {
                    Cell cell = row.getCell(1);
                    if (cell == null) {
                        c = "";
                    } else {
                        c = cell.getStringCellValue();
                        if (!c.equals("")) {
                            boolean found = false;
                            for (int j = 0; j < Tournament.getTournament().getCoachsCount(); j++) {
                                Coach coach = Tournament.getTournament().getCoach(j);
                                if (coach.getName().equals(c)) {
                                    JOptionPane.showMessageDialog(this, "Coach " + c + " already exists");
                                    found = true;
                                }
                            }
                            if (!found) {
                                Coach coach = new Coach(c);
                                coach.setActive(true);
                                Tournament.getTournament().addCoach(coach);

                                // Roster Name
                                Cell cell_tmp = row.getCell(2);
                                String tmp = "";
                                if (cell_tmp != null) {
                                    tmp = cell_tmp.getStringCellValue();
                                }
                                if (!tmp.equals("")) {
                                    coach.setTeam(tmp);
                                } else {
                                    String suffixes[] = {"Team", "Fighters", "Marauders", "Dancers", "Miners", "Steelers",
                                        "Warriors", "Politicians", "Rebels", "Patriots", "Blacksmiths", "Knights"
                                    };
                                    Random random = new Random();
                                    int index = random.nextInt(suffixes.length);
                                    coach.setTeam(c + "'s " + suffixes[index]);
                                }

                                // Roster
                                cell_tmp = row.getCell(3);
                                tmp = "";
                                if (cell_tmp != null) {
                                    tmp = cell_tmp.getStringCellValue();

                                }
                                if (!tmp.equals("")) {
                                    RosterType rt = RosterType.getRosterType(tmp);
                                    if (rt == null) {
                                        String tr = RosterType.translate(tmp);
                                        rt = RosterType.getRosterType(tr);
                                    }

                                    coach.setRoster(rt);
                                }
                                if (coach.getRoster() == null) {
                                    String rosters[] = RosterType.getRostersNames();
                                    String s = (String) JOptionPane.showInputDialog(
                                            this,
                                            "Coach " + coach.getName() + " has no roster chosen. Please, choose one",
                                            "Choose a roster",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            rosters,
                                            rosters[0]);

                                    coach.setRoster(RosterType.getRosterType(s));
                                }

                                if (Tournament.getTournament().getParams().isTeamTournament()) {
                                    //Team
                                    cell_tmp = row.getCell(4);
                                    tmp = "";
                                    Team t = null;
                                    if (cell_tmp != null) {
                                        tmp = cell_tmp.getStringCellValue();
                                    }
                                    if (!tmp.equals("")) {
                                        t = Tournament.getTournament().getTeam(tmp);
                                        coach.setTeamMates(t);
                                        t.addCoach(coach);
                                    }
                                    if (coach.getTeamMates() == null) {
                                        String teams[] = Tournament.getTournament().getTeamsNames();
                                        String s = (String) JOptionPane.showInputDialog(
                                                this,
                                                "Coach " + coach.getName() + " has no team chosen. Please, choose one",
                                                "Choose a team",
                                                JOptionPane.PLAIN_MESSAGE,
                                                null,
                                                teams,
                                                teams[0]);

                                        t = Tournament.getTournament().getTeam(s);
                                        if (t.getActivePlayerNumber() >= Tournament.getTournament().getParams().getTeamMatesNumber()) {
                                            coach.setActive(false);
                                        }
                                        coach.setTeamMates(t);
                                        t.addCoach(coach);

                                    }
                                }

                                // Clan
                                cell_tmp = row.getCell(5);
                                tmp = "";
                                Clan clan = Tournament.getTournament().getClan(0);
                                if (cell_tmp != null) {
                                    tmp = cell_tmp.getStringCellValue();
                                }
                                if (!tmp.equals("")) {
                                    clan = Tournament.getTournament().getClan(tmp);

                                }
                                if (clan != null) {
                                    coach.setClan(clan);
                                }

                                // Category (3 columns)
                                for (int k = 6; k < 9; k++) {
                                    cell_tmp = row.getCell(k);
                                    tmp = "";
                                    Category category = null;
                                    if (cell_tmp != null) {
                                        tmp = cell_tmp.getStringCellValue();
                                    }
                                    if (!tmp.equals("")) {
                                        category = Tournament.getTournament().getCategory(tmp);
                                        if (category != null) {
                                            coach.addCategory(category);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                i++;
            } while (!c.equals(""));

            // Check number of Coachs
            for (int j = 0; j < Tournament.getTournament().getTeamsCount(); j++) {
                Team t = Tournament.getTournament().getTeam(j);
                int nbCoachs = t.getCoachsCount();
                if (nbCoachs < Tournament.getTournament().getParams().getTeamMatesNumber()) {
                    JOptionPane.showMessageDialog(this, "Team " + t.getName() + " is incomplete, completing by \"John Doe\"");
                    for (int k = nbCoachs; k < Tournament.getTournament().getParams().getTeamMatesNumber(); k++) {
                        Coach john = new Coach("John Doe " + Tournament.getTournament().getCoachsCount());
                        john.setRoster(RosterType.getRosterType(0));
                        john.setTeam("Forgotten players");
                        john.setTeamMates(t);
                        john.setClan(Tournament.getTournament().getClan(0));
                        t.addCoach(john);
                        Tournament.getTournament().addCoach(john);
                    }
                }
            }

            // Associate Clans to teams if all coachs are in clan
            for (int j = 0; j < Tournament.getTournament().getTeamsCount(); j++) {
                Team t = Tournament.getTournament().getTeam(j);
                Clan clan = t.getCoach(0).getClan();
                boolean associate = true;
                for (int k = 1; k < t.getCoachsCount(); k++) {
                    if (t.getCoach(k).getClan() != clan) {
                        associate = false;
                        break;
                    }
                }
                if (associate) {
                    t.setClan(clan);
                } else {
                    t.setClan(Tournament.getTournament().getClan(0));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

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
