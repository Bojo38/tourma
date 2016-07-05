/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgRoundReport.java
 *
 * Created on 28 juin 2010, 10:52:47
 */
package tourma.views.report;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import org.apache.commons.lang3.StringEscapeUtils;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtMatchTeams;
import tourma.utility.ExtensionFileFilter;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public final class JdgRound extends javax.swing.JDialog {

    private Round mRound;
    private int mRoundNumber;
    private Tournament mTour;
    private boolean mResult;
    private boolean mTeam = false;
    private ArrayList<Team> mTeams1;
    private ArrayList<Team> mTeams2;
    private File mFilename = null;

    private final static String CS_Round = "Round";

    /**
     * Creates new form jdgRoundReport
     *
     * @param parent
     * @param modal
     * @param round
     * @param roundNumber
     * @param tour
     * @param result
     * @param team
     */
    public JdgRound(final java.awt.Frame parent, final boolean modal, final Round round, final int roundNumber, final Tournament tour, final boolean result, final boolean team) {
        super(parent, modal);
        initComponents();
        mRound = round;
        mRoundNumber = roundNumber;
        mTour = tour;
        mResult = result;
        mTeam = team;

        this.setTitle(tour.getParams().getTournamentName()
                + " -" + StringConstants.CS_THICK
                + Translate.translate(CS_Round)
                + " "
                + roundNumber);
        try {
            jepHTML.setContentType("html");

            if (mTeam) {
                mFilename = createTeamReport();
            } else {
                mFilename = createReport();
            }
            jepHTML.setPage(mFilename.toURI().toURL());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, e.getLocalizedMessage());
        }
        this.setPreferredSize(new Dimension(800, 600));
        pack();
    }

    /**
     *
     * @param parent
     * @param modal
     * @param teams1
     * @param teams2
     * @param roundNumber
     * @param tour
     */
    public JdgRound(final java.awt.Frame parent, final boolean modal, final ArrayList<Team> teams1, final ArrayList<Team> teams2, final int roundNumber, final Tournament tour) {
        super(parent, modal);
        initComponents();

        mTeams1 = teams1;
        mTeams2 = teams2;
        mRoundNumber = roundNumber;
        mTour = tour;

        this.setTitle(tour.getParams().getTournamentName() + StringConstants.CS_THICK + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(StringConstants.CS_ROUND) + " " + roundNumber);
        try {
            jepHTML.setContentType("HTML");
            File f;
            f = createEmptyTeamReport();

            jepHTML.setPage(f.toURI().toURL());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, e.getLocalizedMessage());
        }
        this.setPreferredSize(new Dimension(800, 600));
        pack();
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
        jbtOK = new javax.swing.JButton();
        jbtPrint = new javax.swing.JButton();
        jbtExport = new javax.swing.JButton();
        jbtExportPDF = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jepHTML = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        jbtPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtPrint.setText(bundle.getString("Print")); // NOI18N
        jbtPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPrintActionPerformed(evt);
            }
        });
        jPanel1.add(jbtPrint);

        jbtExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Html.png"))); // NOI18N
        jbtExport.setText(bundle.getString("HTMLExport")); // NOI18N
        jbtExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExportActionPerformed(evt);
            }
        });
        jPanel1.add(jbtExport);

        jbtExportPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/pdf.jpg"))); // NOI18N
        jbtExportPDF.setText(bundle.getString("PDFExport")); // NOI18N
        jbtExportPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExportPDFActionPerformed(evt);
            }
        });
        jPanel1.add(jbtExportPDF);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setViewportView(jepHTML);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPrintActionPerformed
        try {
            jepHTML.print();

        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getLocalizedMessage());
        }
    }//GEN-LAST:event_jbtPrintActionPerformed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExportActionPerformed
        final JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter("HTML", new String[]{"HTML", "html"});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            StringBuffer url2 = new StringBuffer(jfc.getSelectedFile().getAbsolutePath());
            String ext = StringConstants.CS_NULL;
            final int i = url2.toString().lastIndexOf('.');
            if (i > 0 && i < url2.length() - 1) {
                ext = url2.substring(i + 1).toLowerCase(Locale.getDefault());
            }
            if (!ext.equals("html")) {
                url2 = url2.append(".html");
            }

            final File export = new File(url2.toString());

            OutputStreamWriter out = null;
            InputStreamReader in = null;
            try {
                in = new InputStreamReader(new FileInputStream(mFilename), Charset.defaultCharset());
                {
                    out = new OutputStreamWriter(new FileOutputStream(export), Charset.defaultCharset());
                    int c = in.read();
                    while (c != -1) {
                        out.write(c);
                        c = in.read();
                    }
                }

            } catch (FileNotFoundException fnf) {
                JOptionPane.showMessageDialog(this, fnf.getLocalizedMessage());
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(this, ioe.getLocalizedMessage());
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        LOG.log(Level.INFO, e.getLocalizedMessage());
                    }
                }

                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        LOG.log(Level.INFO, e.getLocalizedMessage());
                    }
                }
            }
        }
    }//GEN-LAST:event_jbtExportActionPerformed

    private void jbtExportPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExportPDFActionPerformed

        final JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter("PDF", new String[]{"PDF", "pdf"});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            StringBuffer url2 = new StringBuffer(jfc.getSelectedFile().getAbsolutePath());
            String ext = StringConstants.CS_NULL;
            final int i = url2.toString().lastIndexOf('.');
            if (i > 0 && i < url2.length() - 1) {
                ext = url2.substring(i + 1).toLowerCase(Locale.getDefault());
            }
            if (!ext.equals("pdf")) {
                url2 = url2.append(".pdf");
            }

            final File export = new File(url2.toString());

            OutputStreamWriter out = null;
            InputStreamReader in = null;
            try {
                in = new InputStreamReader(new FileInputStream(mFilename), Charset.defaultCharset());
                {
                    //out = new OutputStreamWriter(new FileOutputStream(export), Charset.defaultCharset());
                    StringBuilder sb=new StringBuilder();
                    int c = in.read();
                    while (c != -1) {
                        sb.append((char)c);
                        c = in.read();
                    }

                    String s=sb.toString();

                    s=s.substring(s.indexOf("<html>"));

                    HTMLtoPDF.exportToPDF(new FileOutputStream(export), s, "Round Report");
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        LOG.log(Level.INFO, e.getLocalizedMessage());
                    }
                }

                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        LOG.log(Level.INFO, e.getLocalizedMessage());
                    }
                }
            }
        }

        //        http://www.rgagnon.com/javadetails/java-html-to-pdf-using-itext.html

    }//GEN-LAST:event_jbtExportPDFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtExport;
    private javax.swing.JButton jbtExportPDF;
    private javax.swing.JButton jbtOK;
    private javax.swing.JButton jbtPrint;
    private javax.swing.JEditorPane jepHTML;
    // End of variables declaration//GEN-END:variables

    private File createReport() {
        File address = null;

        Writer out = null;
        try {
            final Configuration cfg = new Configuration();
            final URI uri = getClass().getResource("/tourma/views/report").toURI();
            if (uri.toString().contains(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".JAR!"))) {
                cfg.setClassForTemplateLoading(getClass(), StringConstants.CS_NULL);
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            final Template temp = cfg.getTemplate("round.html");

            final Map<String, Object> root = new HashMap<>();
            root.put(ReportKeys.CS_Nom,
                    StringEscapeUtils.escapeHtml4(mTour.getParams().getTournamentName() + StringConstants.CS_THICK + Translate.translate(CS_Round) + " " + mRoundNumber));
            root.put(ReportKeys.CS_Table, mRound.getMatchsCount());

            final ArrayList<CoachMatch> matches = mRound.getCoachMatchs();
            final ArrayList<Object> parMatches = new ArrayList<>();
            if (mResult) {
                root.put(ReportKeys.CS_Result, 1);
            } else {
                root.put(ReportKeys.CS_Result, 0);
            }

            final ArrayList<Object> crits = new ArrayList<>();
            for (int i = 1; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                crits.add(Tournament.getTournament().getParams().getCriteria(i).getName());
            }
            root.put(ReportKeys.CS_Criterias, crits);

            for (int i = 0; i < matches.size(); i++) {
                final CoachMatch match = matches.get(i);

                final HashMap<String, Object> m = new HashMap<>();
                m.put(ReportKeys.CS_Numero, i + 1);

                if (match.getRoster1() == null) {
                    m.put(ReportKeys.CS_Roster1, StringEscapeUtils.escapeHtml4(((Coach) match.getCompetitor1()).getRoster().getName()));
                } else {
                    m.put(ReportKeys.CS_Roster1, StringEscapeUtils.escapeHtml4(match.getRoster1().getName()));
                }

                if (match.getRoster2() == null) {
                    m.put(ReportKeys.CS_Roster2, StringEscapeUtils.escapeHtml4(((Coach) match.getCompetitor2()).getRoster().getName()));
                } else {
                    m.put(ReportKeys.CS_Roster2, StringEscapeUtils.escapeHtml4(match.getRoster2().getName()));
                }

                if (!mTour.getParams().isTeamTournament()) {
                    m.put(ReportKeys.CS_Coach1, StringEscapeUtils.escapeHtml4(match.getCompetitor1().getName()));
                } else {
                    m.put(ReportKeys.CS_Coach1, StringEscapeUtils.escapeHtml4(((Coach) match.getCompetitor1()).getTeamMates().getName() + StringConstants.CS_THICK + match.getCompetitor1().getName()));
                }
                if (mResult) {
                    m.put(ReportKeys.CS_Score1, match.getValue(Tournament.getTournament().getParams().getCriteria(0)).getValue1());
                    m.put(ReportKeys.CS_Score2, match.getValue(Tournament.getTournament().getParams().getCriteria(0)).getValue2());

                    final ArrayList<Object> values = new ArrayList<>();
                    for (int j = 1; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                        final HashMap<String, Object> value = new HashMap<>();
                        value.put(ReportKeys.CS_Value1, match.getValue(Tournament.getTournament().getParams().getCriteria(j)).getValue1());
                        value.put(ReportKeys.CS_Value2, match.getValue(Tournament.getTournament().getParams().getCriteria(j)).getValue2());
                        values.add(value);
                    }
                    m.put(ReportKeys.CS_Values, values);

                } else {
                    m.put(ReportKeys.CS_Score1, StringConstants.CS_HTML_EMPTY);
                    m.put(ReportKeys.CS_Score2, StringConstants.CS_HTML_EMPTY);
                    final ArrayList<Object> values = new ArrayList<>();
                    for (int j = 1; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                        final HashMap<String, Object> value = new HashMap<>();
                        value.put(ReportKeys.CS_Value1, StringConstants.CS_HTML_EMPTY);
                        value.put(ReportKeys.CS_Value2, StringConstants.CS_HTML_EMPTY);
                        values.add(value);
                    }
                    m.put(ReportKeys.CS_Values, values);
                }
                if (!mTour.getParams().isTeamTournament()) {
                    m.put(ReportKeys.CS_Coach2, match.getCompetitor2().getName());
                } else {
                    m.put(ReportKeys.CS_Coach2, ((Coach) match.getCompetitor2()).getTeamMates().getName() + StringConstants.CS_THICK + match.getCompetitor2().getName());
                }

                parMatches.add(m);
            }

            root.put(ReportKeys.CS_Matches, parMatches);

            root.put("TitleCoach", StringEscapeUtils.escapeHtml4(Translate.translate("TitleCoach")));
            root.put("TitleScore", StringEscapeUtils.escapeHtml4(Translate.translate("TitleScore")));

            final SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy", Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            root.put(ReportKeys.CS_DateGeneration, formatShort.format(new Date()));
            address = File.createTempFile(StringConstants.CS_RESULT + format.format(new Date()), ".tmp");
            address.deleteOnExit();
            out = new OutputStreamWriter(new FileOutputStream(address), Charset.defaultCharset());
            temp.process(root, out);
            out.flush();

        } catch (IOException | TemplateException | URISyntaxException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
                }

            }
        }
        return address;
    }

    private File createTeamReport() {
        File address = null;
        Writer out = null;
        try {
            final Configuration cfg = new Configuration();
            final URI uri = getClass().getResource("/tourma/views/report").toURI();
            if (uri.toString().contains(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".JAR!"))) {
                cfg.setClassForTemplateLoading(getClass(), StringConstants.CS_NULL);
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            final Template temp = cfg.getTemplate("team_round.html");

            final Map<String, Object> root = new HashMap<>();
            root.put(ReportKeys.CS_Nom, StringEscapeUtils.escapeHtml4(mTour.getParams().getTournamentName() + StringConstants.CS_THICK + Translate.translate(CS_Round) + " " + mRoundNumber));
            root.put(ReportKeys.CS_Tables, mRound.getMatchsCount());

            if (mResult) {
                root.put(ReportKeys.CS_Result, 1);
            } else {
                root.put(ReportKeys.CS_Result, 0);
            }

            final ArrayList<Team> teams = new ArrayList<>();
            for (int i = 0; i < mRound.getMatchsCount(); i++) {
                final Match m = mRound.getMatch(i);
                final Team team1 = (Team) m.getCompetitor1();
                final Team team2 = (Team) m.getCompetitor2();
                if (!teams.contains(team1)) {
                    teams.add(team1);
                }
                if (!teams.contains(team2)) {
                    teams.add(team2);
                }
            }
            final MjtMatchTeams model = new MjtMatchTeams(teams, mRound);
            final ArrayList<Object> parMatches = new ArrayList<>();
            for (int i = 0; i < model.getRowCount(); i++) {

                final HashMap<String, Object> m = new HashMap<>();
                m.put(ReportKeys.CS_Numero, model.getValueAt(i, 0));
                m.put(ReportKeys.CS_Team1, StringEscapeUtils.escapeHtml4((String) model.getValueAt(i, 1)));
                if (mResult) {
                    m.put(ReportKeys.CS_V1, model.getValueAt(i, 2));
                    m.put(ReportKeys.CS_N, model.getValueAt(i, 3));
                    m.put(ReportKeys.CS_V2, model.getValueAt(i, 4));
                } else {
                    m.put(ReportKeys.CS_V1, StringConstants.CS_HTML_EMPTY);
                    m.put(ReportKeys.CS_N, StringConstants.CS_HTML_EMPTY);
                    m.put(ReportKeys.CS_V2, StringConstants.CS_HTML_EMPTY);
                }
                m.put(ReportKeys.CS_Team2, StringEscapeUtils.escapeHtml4((String) model.getValueAt(i, 5)));
                parMatches.add(m);
            }

            root.put("TitleTeam", StringEscapeUtils.escapeHtml4(Translate.translate("TitleTeam")));
            root.put("TitleV", StringEscapeUtils.escapeHtml4(Translate.translate("TitleV")));
            root.put("TitleD", StringEscapeUtils.escapeHtml4(Translate.translate("TitleD")));
            root.put(ReportKeys.CS_Matches, parMatches);

            final SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy", Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            root.put(ReportKeys.CS_DateGeneration, formatShort.format(new Date()));
            address = File.createTempFile(StringConstants.CS_RESULT + format.format(new Date()), ".tmp");
            address.deleteOnExit();
            out = new OutputStreamWriter(new FileOutputStream(address), Charset.defaultCharset());
            temp.process(root, out);
            out.flush();

        } catch (IOException | TemplateException | URISyntaxException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
                }

            }
        }
        return address;
    }

    private File createEmptyTeamReport() {
        File address = null;
        Writer out = null;
        try {
            final Configuration cfg = new Configuration();
            final URI uri = getClass().getResource("tourma/views/report").toURI();
            if (uri.toString().contains(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".JAR!"))) {

                cfg.setClassForTemplateLoading(getClass(), StringConstants.CS_NULL);
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            final Template temp = cfg.getTemplate("team_round.html");

            final Map<String, Object> root = new HashMap<>();
            root.put(ReportKeys.CS_Nom,
                    mTour.getParams().getTournamentName() + " - "
                    + Translate.translate(CS_Round) + " " + mRoundNumber);
            root.put(ReportKeys.CS_Tables, mTour.getTeamsCount() / 2);
            root.put(ReportKeys.CS_Result, 0);

            final ArrayList<Object> parMatches = new ArrayList<>();
            for (int i = 0; i < mTeams1.size(); i++) {
                final HashMap<String, Object> m = new HashMap<>();
                m.put(ReportKeys.CS_Numero, i + 1);
                m.put(ReportKeys.CS_Team1, mTeams1.get(i).getName());
                m.put(ReportKeys.CS_V1, StringConstants.CS_HTML_EMPTY);
                m.put(ReportKeys.CS_N, StringConstants.CS_HTML_EMPTY);
                m.put(ReportKeys.CS_V2, StringConstants.CS_HTML_EMPTY);

                m.put(ReportKeys.CS_Team2, mTeams2.get(i).getName());
                parMatches.add(m);
            }

            root.put(ReportKeys.CS_Matches, parMatches);

            final SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy", Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            root.put(ReportKeys.CS_DateGeneration, formatShort.format(new Date()));
            address = File.createTempFile(StringConstants.CS_RESULT + format.format(new Date()),
                    ".tmp");
            address.deleteOnExit();
            out = new OutputStreamWriter(new FileOutputStream(address), Charset.defaultCharset());
            temp.process(root, out);
            out.flush();

        } catch (IOException | TemplateException | URISyntaxException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
                }

            }
        }
        return address;
    }
    private static final Logger LOG = Logger.getLogger(JdgRound.class.getName());

}