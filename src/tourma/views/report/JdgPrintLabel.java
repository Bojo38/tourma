/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * jdgCoach.java
 *
 * Created on 10 mai 2010, 19:37:53
 */
package tourma.views.report;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.awt.Dimension;
import tourma.*;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
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
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.languages.Translate;
import static tourma.languages.Translate.CS_Round;
import tourma.utility.ExtensionFileFilter;
import tourma.utility.StringConstants;
import tourma.views.report.HTMLtoPDF;
import tourma.views.report.ReportKeys;

/**
 *
 * @author Frederic Berger
 */
public final class JdgPrintLabel extends javax.swing.JDialog {

    private File mFilename = null;
    private final Round mRound;
    private final boolean mByTeam;
    private final boolean mPreFilled;
    private Tournament mTour;
    private int mRoundNumber = 0;

    private final static String CS_ACCR_Versus = "VS";

    /**
     * Creates new form jdgCoach
     *
     * @param parent
     * @param modal
     * @param team1
     * @param round
     * @param team2
     * @param teammatch
     */
    public JdgPrintLabel(final java.awt.Frame parent, final boolean modal, final Round round, final boolean preFilled, final boolean byTeam) {
        super(parent, modal);
        initComponents();

        mTour = Tournament.getTournament();

        this.setPreferredSize(new Dimension(800, 600));

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();

        final int screenWidth = dmode.getWidth();
        final int screenHeight = dmode.getHeight();
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

        mRound = round;
        mByTeam = byTeam;
        mPreFilled = preFilled;

        mRoundNumber = mTour.getRoundIndex(round) + 1;
        update();
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);

    }

    private void update() {
        if (!mByTeam) {
            try {
                mFilename = createIndivLabels(mPreFilled);
                jepHTML.setPage(mFilename.toURI().toURL());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getLocalizedMessage());
            }
        } else {
            try {
                mFilename = createTeamLabels(mPreFilled);
                jepHTML.setPage(mFilename.toURI().toURL());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getLocalizedMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private File createIndivLabels(boolean prefilled) {

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
            final Template temp = cfg.getTemplate("indivLabels.html");

            final ArrayList<Round> rounds;
            rounds = new ArrayList<>();
            for (int i = 0; i < mTour.getRoundsCount() && i < mRoundNumber; i++) {
                rounds.add(mTour.getRound(i));
            }

            final Map root = new HashMap();
            root.put(
                    ReportKeys.CS_Nom,
                    StringEscapeUtils.escapeHtml3(mTour.getParams().getTournamentName()
                            + " - " + Translate.translate(CS_Round) + " " + mRoundNumber));

            root.put("TitleCoach", StringEscapeUtils.escapeHtml3(Translate.translate(Translate.CS_Coach)));

            int nb_rows = 0;
            root.put("nb_rows", Tournament.getTournament().getParams().getCriteriaCount() * 2);
            final ArrayList matches = new ArrayList();
            for (int i = 0; i < mRound.getCoachMatchs().size(); i++) {
                CoachMatch cm = mRound.getCoachMatchs().get(i);

                final Map match = new HashMap();
                match.put("numero", i + 1);

                if (prefilled) {
                    match.put("coach1", cm.getCompetitor1().getName());
                    match.put("coach2", cm.getCompetitor2().getName());
                    match.put("roster1", ((Coach) cm.getCompetitor1()).getRoster().getName());
                    match.put("roster2", ((Coach) cm.getCompetitor2()).getRoster().getName());
                } else {
                    match.put("coach1", "&nbsp;");
                    match.put("coach2", "&nbsp;");
                    match.put("roster1", "&nbsp;");
                    match.put("roster2", "&nbsp;");
                }
                ArrayList crits = new ArrayList();
                for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                    final Map crit = new HashMap();
                    Criteria criteria = Tournament.getTournament().getParams().getCriteria(j);
                    if (j == 0) {
                        match.put("firstcriterianame", criteria.getName());
                    } else {
                        crit.put("nom", criteria.getName());
                    }
                    if (j == 0) {
                        match.put("firstcriteriavalue1", "&nbsp;");
                        match.put("firstcriteriavalue2", "&nbsp;");
                    } else {
                        crit.put("value1", "&nbsp;");
                        crit.put("value2", "&nbsp;");
                        crits.add(crit);
                    }
                }
                match.put("criterias", crits);
                matches.add(match);
            }
            root.put(ReportKeys.CS_Matches, matches);

            final SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy", Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            root.put(ReportKeys.CS_DateGeneration, formatShort.format(new Date()));
            address = File.createTempFile(
                    StringConstants.CS_RESULT + " " + format.format(new Date()), ".tmp");
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

    @SuppressWarnings("unchecked")
    private File createTeamLabels(boolean prefilled) {

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
            final Template temp = cfg.getTemplate("teamLabels.html");

            final ArrayList<Round> rounds;
            rounds = new ArrayList<>();
            for (int i = 0; i < mTour.getRoundsCount() && i < mRoundNumber; i++) {
                rounds.add(mTour.getRound(i));
            }

            final Map root = new HashMap();
            root.put(
                    ReportKeys.CS_Nom,
                    StringEscapeUtils.escapeHtml3(mTour.getParams().getTournamentName()
                            + " - " + Translate.translate(CS_Round) + " " + mRoundNumber));

            root.put("TitleCoach", StringEscapeUtils.escapeHtml3(Translate.translate(Translate.CS_Coach)));
            root.put("TitleTeam", StringEscapeUtils.escapeHtml3(Translate.translate(Translate.CS_Team)));

            int nb_rows = Tournament.getTournament().getParams().getCriteriaCount() * 2;

            root.put("nb_rows", nb_rows);
            int nb_rows_team = Tournament.getTournament().getParams().getCriteriaCount() * 2 * Tournament.getTournament().getParams().getTeamMatesNumber();
            root.put("nb_rows_team", nb_rows_team);
            final ArrayList team_matches = new ArrayList();
            for (int i = 0; i < mRound.getMatchsCount(); i++) {
                Match m = mRound.getMatch(i);
                if (m instanceof TeamMatch) {
                    TeamMatch tm = (TeamMatch) m;
                    Map team_match = new HashMap();
                    ArrayList matches = new ArrayList();
                    for (int j = 0; j < tm.getMatchCount(); j++) {
                        CoachMatch cm = tm.getMatch(j);

                        final Map match = new HashMap();
                        team_match.put("numero", i + 1);

                        match.put("coachindex", j);
                        if (prefilled) {
                            match.put("coach1", cm.getCompetitor1().getName());
                            match.put("coach2", cm.getCompetitor2().getName());
                            match.put("roster1", ((Coach) cm.getCompetitor1()).getRoster().getName());
                            match.put("roster2", ((Coach) cm.getCompetitor2()).getRoster().getName());

                            team_match.put("team1", tm.getCompetitor1().getName());
                            team_match.put("team2", tm.getCompetitor2().getName());

                        } else {
                            team_match.put("team1", "&nbsp;");
                            team_match.put("team2", "&nbsp;");
                            match.put("coach1", "&nbsp;");
                            match.put("coach2", "&nbsp;");
                            match.put("roster1", "&nbsp;");
                            match.put("roster2", "&nbsp;");
                        }
                        ArrayList crits = new ArrayList();
                        for (int k = 0; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
                            final Map crit = new HashMap();
                            Criteria criteria = Tournament.getTournament().getParams().getCriteria(k);
                            if (k == 0) {
                                match.put("firstcriterianame", criteria.getName());
                            } else {
                                crit.put("nom", criteria.getName());
                            }
                            if (k == 0) {
                                match.put("firstcriteriavalue1", "&nbsp;");
                                match.put("firstcriteriavalue2", "&nbsp;");
                            } else {
                                crit.put("value1", "&nbsp;");
                                crit.put("value2", "&nbsp;");
                                crits.add(crit);
                            }
                        }
                        match.put("criterias", crits);
                        matches.add(match);
                    }
                    team_match.put("matches", matches);
                    team_matches.add(team_match);
                }

            }
            root.put(ReportKeys.CS_TeamMatches, team_matches);

            final SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy", Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            root.put(ReportKeys.CS_DateGeneration, formatShort.format(new Date()));
            address = File.createTempFile(
                    StringConstants.CS_RESULT + " " + format.format(new Date()), ".tmp");
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
        jbtExportPDF = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jepHTML = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        jbtExportPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/pdf.jpg"))); // NOI18N
        jbtExportPDF.setText(bundle.getString("PDFExport")); // NOI18N
        jbtExportPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExportPDFActionPerformed(evt);
            }
        });
        jPanel1.add(jbtExportPDF);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jepHTML.setEditable(false);
        jScrollPane1.setViewportView(jepHTML);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed

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
                    StringBuilder sb = new StringBuilder();
                    int c = in.read();
                    while (c != -1) {
                        sb.append((char) c);
                        c = in.read();
                    }

                    String s = sb.toString();

                    s = s.substring(s.indexOf("<html>"));

                    HTMLtoPDF.exportToPDF(new FileOutputStream(export), s, "Global Report");
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
    private javax.swing.JButton jbtExportPDF;
    private javax.swing.JButton jbtOK;
    private javax.swing.JEditorPane jepHTML;
    // End of variables declaration//GEN-END:variables

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private static final Logger LOG = Logger.getLogger(JdgPrintLabel.class
            .getName());
}
