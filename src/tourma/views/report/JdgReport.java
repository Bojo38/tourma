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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import org.apache.commons.lang3.StringEscapeUtils;
import tourma.data.Criteria;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.ETeamPairing;
import tourma.data.Team;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectRanking;
import tourma.data.Round;
import tourma.data.TeamMatch;
import tourma.data.Match;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingClan;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingTeam;
import tourma.utility.ExtensionFileFilter;
import tourma.utility.StringConstants;
import tourma.utils.web.WebPicture;
import java.io.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author Frederic Berger
 */
public final class JdgReport extends javax.swing.JDialog {

    private static final long serialVersionUID = 12L;

    private int mRoundNumber;
    private Tournament mTour;
//    private boolean mResult;
    private File mFilename = null;
    private MjtRanking mRanking;

    private static final String CS_IncludeMembersReports = "Include the reports of the members";

    private final JCheckBox jcxWithMembers = new JCheckBox(Translate.translate(CS_IncludeMembersReports));

    public final static int C_INDIVIDUAL = 0;
    public final static int C_CLAN = 1;
    public final static int C_TEAM = 2;

    SwingWebView webView;
    int mType = 0;
    ArrayList<IWithNameAndPicture> objects;

    /**
     * Creates new form jdgRoundReport
     *
     * @param parent
     * @param modal
     * @param roundNumber
     * @param tour
     */
    public JdgReport(final java.awt.Frame parent, final boolean modal, final int roundNumber, final Tournament tour, int type) {
        super(parent, modal);
        initComponents();
        webView = new SwingWebView();
        //webView.setPreferredSize(new Dimension(640,480));
        this.add(webView, BorderLayout.CENTER);

        //_round = round;
        mRoundNumber = roundNumber;
        mTour = tour;
        mType = type;

        objects = new ArrayList<>();
        DefaultListModel model = new DefaultListModel();

        switch (mType) {
            case C_INDIVIDUAL:
                for (int i = 0; i < tour.getCoachsCount(); i++) {
                    objects.add(tour.getCoach(i));
                    model.addElement(tour.getCoach(i).getName());
                }
                break;
            case C_TEAM:
                for (int i = 0; i < tour.getTeamsCount(); i++) {
                    objects.add(tour.getTeam(i));
                    model.addElement(tour.getTeam(i).getName());
                }

                break;
            case C_CLAN:
                for (int i = 0; i < tour.getClansCount(); i++) {
                    objects.add(tour.getClan(i));
                    model.addElement(tour.getClan(i).getName());
                }

                break;
        }

        if ((mType == C_TEAM) || (mType == C_CLAN)) {
            JPanel north = new JPanel(new FlowLayout());
            north.add(jcxWithMembers);
            jcxWithMembers.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jcxWithMembersActionPerformed(evt);
                }
            });
            this.add(north, BorderLayout.NORTH);
        }

        jlsObject.setModel(model);
        if (objects.size() > 0) {
            jlsObject.setSelectedIndex(0);
            try {
                this.setTitle(
                        tour.getParams().getTournamentName()
                        + " - "
                        + Translate.translate(Translate.CS_Round) + " " + roundNumber + "<br>"
                        + objects.get(0).getName());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, e.getLocalizedMessage());
            }
            try {

                mFilename = createReport(jlsObject.getSelectedIndex());
                webView.setURL(mFilename.toURI().toURL().toString());
            } catch (IOException | NullPointerException e) {
                JOptionPane.showMessageDialog(parent, e.getLocalizedMessage());
            }
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
        jbtPrintAll = new javax.swing.JButton();
        jbtExport = new javax.swing.JButton();
        jbtExportPDF = new javax.swing.JButton();
        jbtExportPDFAll = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlsObject = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Ranking"); // NOI18N

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

        jbtPrintAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtPrintAll.setText(bundle.getString("PrintAll")); // NOI18N
        jbtPrintAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPrintAllActionPerformed(evt);
            }
        });
        jPanel1.add(jbtPrintAll);

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

        jbtExportPDFAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/pdf.jpg"))); // NOI18N
        jbtExportPDFAll.setText(bundle.getString("PDFExportAll")); // NOI18N
        jbtExportPDFAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExportPDFAllActionPerformed(evt);
            }
        });
        jPanel1.add(jbtExportPDFAll);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jlsObject.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlsObject.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlsObject.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlsObjectValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jlsObject);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcxWithMembersActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            mFilename = createReport(jlsObject.getSelectedIndex());
            webView.setURL(mFilename.toURI().toURL().toString());
        } catch (IOException | NullPointerException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }
    }

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPrintActionPerformed
        /*try {
            //jepHTML.print();

        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getLocalizedMessage());
        }*/

        File file = createReport(jlsObject.getSelectedIndex());
        File file2 = createPDFfromHTML(file);
        printPDFFile(file2);
    }//GEN-LAST:event_jbtPrintActionPerformed

    private void printPDFFile(File file2) {
        FileInputStream psStream = null;
        if (file2 != null) {
            try {
                psStream = new FileInputStream(file2);
            } catch (FileNotFoundException ffne) {
                ffne.printStackTrace();
            }
            if (psStream == null) {
                return;
            }
            DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc myDoc = new SimpleDoc(psStream, psInFormat, null);
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);

            // this step is necessary because I have several printers configured
            PrintService myPrinter = null;
            if (services.length == 0) {
                JOptionPane.showMessageDialog(this, "No printer found", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ArrayList<String> choices = new ArrayList<>();
                for (int i = 0; i < services.length; i++) {
                    choices.add(services[i].getName());
                }
                String input = (String) JOptionPane.showInputDialog(null, "Choose printer.",
                        "printer", JOptionPane.QUESTION_MESSAGE, null, // Use
                        // default
                        // icon
                        choices.toArray(), // Array of choices
                        choices.get(0)); // Initial choice

                int index = choices.indexOf(input);

                if (index >= 0) {
                    myPrinter = services[index];
                }
                if (myPrinter != null) {
                    DocPrintJob job = myPrinter.createPrintJob();
                    try {
                        job.print(myDoc, aset);

                    } catch (Exception pe) {
                        pe.printStackTrace();
                    }
                } else {
                    System.out.println("no printer services found");
                }
            }
        }
    }

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

    private File createPDFfromHTML(File html) {
        String property = "java.io.tmpdir";
        OutputStreamWriter out = null;
        InputStreamReader in = null;
        File export = null;
        String tempDir = System.getProperty(property);
        try {
            export = File.createTempFile("pdf_report", null, new File(tempDir));

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
        return export;
    }

    private File createAllPDFfromHTML() {
        String property = "java.io.tmpdir";
        OutputStreamWriter out = null;
        InputStreamReader in = null;
        File export = null;
        String tempDir = System.getProperty(property);
        try {
            export = File.createTempFile("pdf_report", null, new File(tempDir));
            String content = "";
            for (int j = 0; j < jlsObject.getModel().getSize(); j++) {
                File file = createReport(j);
                in = new InputStreamReader(new FileInputStream(file), Charset.defaultCharset());
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
                    content += s;
                    if (j > 0) {
                        content += "<p style=\"page-break-after: always;\">&nbsp;</p>";
                    }
                }
            }
            HTMLtoPDF.exportToPDF(new FileOutputStream(export), content, "Total Report");

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
        return export;
    }

    private void jlsObjectValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlsObjectValueChanged
        if (jlsObject.getSelectedIndex() >= 0) {
            try {
                this.setTitle(
                        mTour.getParams().getTournamentName()
                        + " - "
                        + Translate.translate(Translate.CS_Round) + " " + mRoundNumber + "<br>"
                        + objects.get(jlsObject.getSelectedIndex()).getName());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this.getParent(), e.getLocalizedMessage());
            }
            try {
                //jepHTML.setContentType("html");
                mFilename = createReport(jlsObject.getSelectedIndex());
                //jepHTML.setPage(mFilename.toURI().toURL());
                webView.setURL(mFilename.toURI().toURL().toString());

            } catch (IOException | NullPointerException e) {
                JOptionPane.showMessageDialog(this.getParent(), e.getLocalizedMessage());
            }

        }
    }//GEN-LAST:event_jlsObjectValueChanged

    private void jbtPrintAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPrintAllActionPerformed

        File file2 = createAllPDFfromHTML();
        printPDFFile(file2);
    }//GEN-LAST:event_jbtPrintAllActionPerformed

    private void jbtExportPDFAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExportPDFAllActionPerformed
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
                String content = "";
                for (int j = 0; j < jlsObject.getModel().getSize(); j++) {
                    File file = createReport(j);
                    in = new InputStreamReader(new FileInputStream(file), Charset.defaultCharset());
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
                        content += s;
                        if (j > 0) {
                            content += "<p style=\"page-break-after: always;\">&nbsp;</p>";
                        }
                    }
                }
                HTMLtoPDF.exportToPDF(new FileOutputStream(export), content, "Total Report");

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
    }//GEN-LAST:event_jbtExportPDFAllActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtExport;
    private javax.swing.JButton jbtExportPDF;
    private javax.swing.JButton jbtExportPDFAll;
    private javax.swing.JButton jbtOK;
    private javax.swing.JButton jbtPrint;
    private javax.swing.JButton jbtPrintAll;
    private javax.swing.JList<String> jlsObject;
    // End of variables declaration//GEN-END:variables

    private static final String CS_TheMostFor = "LE PLUS R&EACUTE;ALIS&EACUTE;";
    private static final String CS_TheLessFor = "LE MOINS R&EACUTE;ALIS&EACUTE;";
    private static final String CS_TheMoreAgainst = "LE PLUS SUBI";
    private static final String CS_TheLessAgainst = "LE MOINS SUBI";

    private File createIndivReport(Coach c) {
        File address = null;
        Writer out = null;
        Coach coach = null;
        ArrayList<Coach> coaches = new ArrayList<>();

        if (c != null) {
            coach = (Coach) c;
            coaches.add(coach);
            mRanking = new MjtRankingIndiv(mRoundNumber, coaches, true, false);
        } else {
            return null;
        }
        try {
            final Configuration cfg = new Configuration();
            final URI uri = getClass().getResource("/tourma/views/report").toURI();
            if (uri.toString().contains(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".JAR!"))) {
                cfg.setClassForTemplateLoading(getClass(), StringConstants.CS_NULL);
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            final Template temp = cfg.getTemplate("indiv_report.html");

            final ArrayList<Round> rounds;
            rounds = new ArrayList<>();
            for (int i = 0; i < mTour.getRoundsCount() && i < mRoundNumber; i++) {
                rounds.add(mTour.getRound(i));
            }

            final Map root = new HashMap();
            root.put(
                    ReportKeys.CS_Nom,
                    StringEscapeUtils.escapeHtml3(mTour.getParams().getTournamentName()));

            // Team Name
            root.put(ReportKeys.CS_Title, StringEscapeUtils.escapeHtml3(coach.getName()));

            // Picture
            String picture = "";
            if (coach.getPicture() != null) {
                picture = WebPicture.getPictureAsHTML(coach.getPicture(), 50, 50);

            }
            root.put("picture", picture);

            int nb_criterias = Tournament.getTournament().getParams().getCriteriaCount();
            root.put("nb_criterias", nb_criterias);

            root.put("coach", Translate.translate(Translate.CS_Coach));
            root.put("round", Translate.translate(Translate.CS_Round));
            root.put("points", Translate.translate(Translate.CS_Points));
            root.put("total_points", Translate.translate(Translate.CS_TotalPoints));

            root.put("roster", Translate.translate(Translate.CS_Roster));
            root.put("nb_criterias", nb_criterias);
            root.put("nb_criteriasx2", 2 * nb_criterias);

            int ranking = 0;
            MjtRankingIndiv mjt = new MjtRankingIndiv(
                    mRoundNumber, Tournament.getTournament().getCoachs(), Tournament.getTournament().getParams().isTeamTournament(), false);

            int colindex = 1;
            if (Tournament.getTournament().getParams().isTeamTournament()) {
                colindex++;
            }
            for (int i = 0; i < mjt.getRowCount(); i++) {
                if (mjt.getValueAt(i, colindex).equals(coach.getName())) {
                    ranking = i + 1;
                }
            }
            root.put("ranking", ranking);
            root.put("total_ranking", Tournament.getTournament().getCoachsCount());

            final ArrayList criterias = new ArrayList();
            for (int i = 0; i < nb_criterias; i++) {
                criterias.add(StringEscapeUtils.escapeHtml3(mTour.getParams().getCriteria(i).getAccronym()));
            }
            root.put(ReportKeys.CS_Criterias, criterias);

            final ArrayList rcriterias = new ArrayList();
            for (int i = nb_criterias - 1; i >= 0; i--) {
                rcriterias.add(StringEscapeUtils.escapeHtml3(mTour.getParams().getCriteria(i).getAccronym()));
            }
            root.put("rcriterias", rcriterias);

            /**
             ********************
             **** First table *** *******************
             */
            final ArrayList coaches_summary = new ArrayList();

            HashMap<String, Object> hCoach = new HashMap<>();
            hCoach.put("name", coach.getName());
            hCoach.put("roster", coach.getRoster().getName());
            for (int j = 0; j < mRanking.getRowCount(); j++) {
                ObjectRanking or = mRanking.getSortedObject(j);
                if (or.getObject() == coach) {
                    hCoach.put("points", or.getValue1());
                }
            }

            ArrayList<Integer> values = new ArrayList<>();

            Criteria td = Tournament.getTournament().getParams().getCriteria(0);

            // Find value + for each matches
            for (int j = 0; j < nb_criterias; j++) {
                Criteria crit = mTour.getParams().getCriteria(j);
                int val = 0;
                for (int k = 0; k < coach.getMatchCount(); k++) {
                    CoachMatch cm = (CoachMatch) coach.getMatch(k);
                    Value value = cm.getValue(crit);
                    if (cm.getCompetitor1() == coach) {
                        if (crit == td) {
                            if (value.getValue1() < 0) {
                                val += 0;
                            } else {
                                val += value.getValue1();
                            }

                        } else {
                            val += value.getValue1();
                        }
                    }
                    if (cm.getCompetitor2() == coach) {
                        if (crit == td) {
                            if (value.getValue2() < 0) {
                                val += 0;
                            } else {
                                val += value.getValue2();
                            }

                        } else {
                            val += value.getValue2();
                        }
                    }
                }
                values.add(val);
            }
            // Find value - for each matches
            for (int j = 0; j < nb_criterias; j++) {
                Criteria crit = mTour.getParams().getCriteria(j);
                int val = 0;
                for (int k = 0; k < coach.getMatchCount(); k++) {
                    CoachMatch cm = (CoachMatch) coach.getMatch(k);
                    Value value = cm.getValue(crit);
                    if (cm.getCompetitor2() == coach) {
                        if (crit == td) {
                            if (value.getValue1() < 0) {
                                val += 0;
                            } else {
                                val += value.getValue1();
                            }

                        } else {
                            val += value.getValue1();
                        }
                    }
                    if (cm.getCompetitor1() == coach) {
                        if (crit == td) {
                            if (value.getValue2() < 0) {
                                val += 0;
                            } else {
                                val += value.getValue2();
                            }

                        } else {
                            val += value.getValue2();
                        }
                    }
                }
                values.add(val);
            }

            hCoach.put("values", values);
            coaches_summary.add(hCoach);
            //StringEscapeUtils.escapeHtml3(mTour.getParams().getCriteria(i).getName()));

            root.put("coaches_summary", coaches_summary);

            root.put("nb_rounds", mRoundNumber + 1);
            /**
             * *****************
             **** Second table *** *****************
             */
            ArrayList coaches_rounds = new ArrayList();

            HashMap<String, Object> hCoach2 = new HashMap<>();
            hCoach2.put("name", coach.getName());

            int round_index = 1;
            hCoach2.put("round", round_index);

            ArrayList values2 = new ArrayList();
            Round r = Tournament.getTournament().getRound(round_index - 1);
            for (int j = 0; j < nb_criterias; j++) {
                Criteria crit = mTour.getParams().getCriteria(j);
                int val = 0;
                for (int k = 0; k < coach.getMatchCount(); k++) {
                    CoachMatch cm = (CoachMatch) coach.getMatch(k);
                    if (r.containsCoachMatch(cm)) {
                        Value value = cm.getValue(crit);
                        if (cm.getCompetitor1() == coach) {
                            if (crit == td) {
                                if (value.getValue1() < 0) {
                                    val += 0;
                                } else {
                                    val += value.getValue1();
                                }

                            } else {
                                val += value.getValue1();
                            }
                        }
                        if (cm.getCompetitor2() == coach) {
                            if (crit == td) {
                                if (value.getValue2() < 0) {
                                    val += 0;
                                } else {
                                    val += value.getValue2();
                                }

                            } else {
                                val += value.getValue2();
                            }
                        }
                    }
                }
                values2.add(val);
            }
            hCoach2.put("values", values2);

            MjtRanking rank = new MjtRankingIndiv(round_index - 1, coaches, true, true);
            for (int j = 0; j < rank.getRowCount(); j++) {
                ObjectRanking or = rank.getSortedObject(j);
                if (or.getObject() == coach) {
                    hCoach2.put("points", or.getValue1());
                }
            }

            for (int j = 0; j < mRanking.getRowCount(); j++) {
                ObjectRanking or = mRanking.getSortedObject(j);
                if (or.getObject() == coach) {
                    hCoach2.put("total_points", or.getValue1());
                }
            }

            ArrayList coach_rounds = new ArrayList();
            for (int j = 1; j <= mRoundNumber; j++) {
                HashMap<String, Object> hhCoach = new HashMap<>();
                round_index++;
                hhCoach.put("name", coach.getName());
                hhCoach.put("round_index", j + 1);

                values = new ArrayList();
                r = Tournament.getTournament().getRound(j);
                for (int k = 0; k < nb_criterias; k++) {
                    Criteria crit = mTour.getParams().getCriteria(k);
                    int val = 0;
                    for (int l = 0; l < coach.getMatchCount(); l++) {
                        CoachMatch cm = (CoachMatch) coach.getMatch(l);
                        if (r.containsCoachMatch(cm)) {
                            Value value = cm.getValue(crit);
                            if (cm.getCompetitor1() == coach) {
                                if (crit == td) {
                                    if (value.getValue1() < 0) {
                                        val += 0;
                                    } else {
                                        val += value.getValue1();
                                    }

                                } else {
                                    val += value.getValue1();
                                }
                            }
                            if (cm.getCompetitor2() == coach) {
                                if (crit == td) {
                                    if (value.getValue2() < 0) {
                                        val += 0;
                                    } else {
                                        val += value.getValue2();
                                    }

                                } else {
                                    val += value.getValue2();
                                }
                            }
                        }
                    }
                    values.add(val);
                }

                hhCoach.put("values", values);

                rank = new MjtRankingIndiv(round_index - 1, coaches, true, true);
                for (int k = 0; k < rank.getRowCount(); k++) {
                    ObjectRanking or = rank.getSortedObject(k);
                    if (or.getObject() == coach) {
                        hhCoach.put("points", or.getValue1());
                    }
                }

                coach_rounds.add(hhCoach);
            }

            hCoach2.put("rounds", coach_rounds);

            coaches_rounds.add(hCoach2);
            root.put("coaches_rounds", coaches_rounds);

            /**
             * *****************
             **** third table *** ******************
             */
            ArrayList aRounds = new ArrayList();

            for (int i = 0; i <= mRoundNumber; i++) {
                HashMap aRound = new HashMap();
                ArrayList matchs = new ArrayList();

                aRound.put("index", i + 1);

                Round round = Tournament.getTournament().getRound(i);
                int matchindex = 0;
                CoachMatch cm = null;
                for (int j = 0; j < round.getMatchsCount(); j++) {
                    Match m = round.getMatch(j);
                    if (m instanceof CoachMatch) {
                        if ((((CoachMatch) m).getCompetitor1() == coach) || (((CoachMatch) m).getCompetitor2() == coach)) {
                            matchindex = j + 1;
                            cm = (CoachMatch) m;
                            break;
                        }
                    }
                    if (m instanceof TeamMatch) {
                        for (int k = 0; k < ((TeamMatch) m).getMatchCount(); k++) {
                            CoachMatch m2 = ((TeamMatch) m).getMatch(k);
                            if ((m2.getCompetitor1() == coach) || (m2.getCompetitor2() == coach)) {
                                matchindex = j * ((TeamMatch) m).getMatchCount() + k + 1;
                                cm = m2;
                                break;
                            }
                        }
                    }

                }
                aRound.put("tableindex", matchindex);

                HashMap hCm = new HashMap();
                hCm.put("c1", cm.getCompetitor1().getName());
                hCm.put("c2", cm.getCompetitor2().getName());
                if (cm.getRoster1() != null) {
                    hCm.put("roster1", cm.getRoster1().getName());
                } else {
                    hCm.put("roster1", ((Coach) cm.getCompetitor1()).getRoster().getName());
                }
                if (cm.getRoster2() != null) {
                    hCm.put("roster2", cm.getRoster2().getName());
                } else {
                    hCm.put("roster2", ((Coach) cm.getCompetitor2()).getRoster().getName());
                }
                hCm.put("points1", cm.getValue(1, (Coach) cm.getCompetitor1()));
                hCm.put("points2", cm.getValue(1, (Coach) cm.getCompetitor2()));

                ArrayList val1 = new ArrayList();
                ArrayList val2 = new ArrayList();
                for (int k = 0; k < nb_criterias; k++) {
                    Criteria crit = mTour.getParams().getCriteria(k);
                    Value value = cm.getValue(crit);

                    if (crit == td) {
                        if (value.getValue1() < 0) {
                            val1.add(0, 0);
                        } else {
                            val1.add(0, value.getValue1());
                        }

                    } else {
                        val1.add(0, value.getValue1());
                    }
                    if (crit == td) {
                        if (value.getValue2() < 0) {
                            val2.add(0);
                        } else {
                            val2.add(value.getValue2());
                        }

                    } else {
                        val2.add(value.getValue2());
                    }
                    //val1.add(0, value.getValue1());
                    //val2.add(value.getValue2());
                }
                hCm.put("values1", val1);
                hCm.put("values2", val2);

                matchs.add(hCm);

                aRound.put("matchs", matchs);
                aRounds.add(aRound);
            }
            root.put("rounds", aRounds);

            final SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy", Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            root.put(ReportKeys.CS_DateGeneration, formatShort.format(new Date()));
            address = File.createTempFile(
                    StringConstants.CS_RESULT + " " + format.format(new Date()), ".html");
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

    private File createTeamReport(Team team) {
        ArrayList<Coach> coaches = new ArrayList<>();

        for (int i = 0; i < team.getCoachsCount(); i++) {
            Coach coach = team.getCoach(i);
            coaches.add(coach);
        }

        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            teams.add(Tournament.getTournament().getTeam(i));
        }

        MjtRankingTeam ranking = new MjtRankingTeam(Tournament.getTournament().getParams().isTeamVictoryOnly(), mRoundNumber, teams, false);
        int rank = 0;
        for (int i = 0; i < ranking.getRowCount(); i++) {
            Object obj = ranking.getSortedObject(i).getObject();
            if (obj instanceof Team) {
                if (obj == team) {
                    rank = i + 1;
                    break;
                }
            }
        }

        return createCoachGroupReport(team, "coach_group_report.html", coaches, rank, Tournament.getTournament().getTeamsCount());

    }

    /*private File createTeamReport(Team c) {
        File address = null;
        Writer out = null;
        Team team = null;
        ArrayList<Coach> coaches = new ArrayList<>();

        if (c != null) {
            team = (Team) c;
            for (int i = 0; i < team.getCoachsCount(); i++) {
                coaches.add(team.getCoach(i));
            }
            mRanking = new MjtRankingIndiv(mRoundNumber, coaches, true, false);
        } else {
            return null;
        }
        try {
            final Configuration cfg = new Configuration();
            final URI uri = getClass().getResource("/tourma/views/report").toURI();
            if (uri.toString().contains(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".JAR!"))) {
                cfg.setClassForTemplateLoading(getClass(), StringConstants.CS_NULL);
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            final Template temp = cfg.getTemplate("team_report.html");

            final ArrayList<Round> rounds;
            rounds = new ArrayList<>();
            for (int i = 0; i < mTour.getRoundsCount() && i < mRoundNumber; i++) {
                rounds.add(mTour.getRound(i));
            }

            final Map root = new HashMap();
            root.put(
                    ReportKeys.CS_Nom,
                    StringEscapeUtils.escapeHtml3(mTour.getParams().getTournamentName()));

            // Team Name
            root.put(ReportKeys.CS_Title, StringEscapeUtils.escapeHtml3(team.getName()));

            // Picture
            String picture = "";
            if (team.getPicture() != null) {
                picture = WebPicture.getPictureAsHTML(team.getPicture(), 50, 50);

            }
            root.put("picture", picture);

            int nb_criterias = Tournament.getTournament().getParams().getCriteriaCount();
            root.put("nb_criterias", nb_criterias);

            root.put("coach", Translate.translate(Translate.CS_Coach));
            root.put("round", Translate.translate(Translate.CS_Round));
            root.put("points", Translate.translate(Translate.CS_Points));
            root.put("total_points", Translate.translate(Translate.CS_TotalPoints));

            root.put("roster", Translate.translate(Translate.CS_Roster));
            root.put("nb_criterias", nb_criterias);
            root.put("nb_criteriasx2", 2 * nb_criterias);

            final ArrayList criterias = new ArrayList();
            for (int i = 0; i < nb_criterias; i++) {
                criterias.add(StringEscapeUtils.escapeHtml3(mTour.getParams().getCriteria(i).getAccronym()));
            }
            root.put(ReportKeys.CS_Criterias, criterias);

            final ArrayList rcriterias = new ArrayList();
            for (int i = nb_criterias - 1; i >= 0; i--) {
                rcriterias.add(StringEscapeUtils.escapeHtml3(mTour.getParams().getCriteria(i).getAccronym()));
            }
            root.put("rcriterias", rcriterias);

            int ranking = 0;
            MjtRankingTeam mjt = new MjtRankingTeam(
                    Tournament.getTournament().getParams().isTeamVictoryOnly(),
                    mRoundNumber, Tournament.getTournament().getTeams(),
                    false);
            for (int i = 0; i < mjt.getRowCount(); i++) {
                if (mjt.getValueAt(i, 1).equals(team.getName())) {
                    ranking = i + 1;
                }
            }
            root.put("ranking", ranking);
            root.put("total_ranking", Tournament.getTournament().getTeamsCount());

            /**
             * *****************
             **** First table *** ******************
     */
 /*      final ArrayList coaches_summary = new ArrayList();
            for (int i = 0; i < team.getCoachsCount(); i++) {
                Coach coach = team.getCoach(i);
                HashMap<String, Object> hCoach = new HashMap<>();
                hCoach.put("name", coach.getName());
                hCoach.put("roster", coach.getRoster().getName());
                for (int j = 0; j < mRanking.getRowCount(); j++) {
                    ObjectRanking or = mRanking.getSortedObject(j);
                    if (or.getObject() == coach) {
                        hCoach.put("points", or.getValue1());
                    }
                }

                ArrayList<Integer> values = new ArrayList<>();

                // Find value + for each matches
                for (int j = 0; j < nb_criterias; j++) {
                    Criteria crit = mTour.getParams().getCriteria(j);
                    int val = 0;
                    for (int k = 0; k < coach.getMatchCount(); k++) {
                        CoachMatch cm = (CoachMatch) coach.getMatch(k);
                        Value value = cm.getValue(crit);
                        if (cm.getCompetitor1() == coach) {
                            val += value.getValue1();
                        }
                        if (cm.getCompetitor2() == coach) {
                            val += value.getValue2();
                        }
                    }
                    values.add(val);
                }
                // Find value - for each matches
                for (int j = 0; j < nb_criterias; j++) {
                    Criteria crit = mTour.getParams().getCriteria(j);
                    int val = 0;
                    for (int k = 0; k < coach.getMatchCount(); k++) {
                        CoachMatch cm = (CoachMatch) coach.getMatch(k);
                        Value value = cm.getValue(crit);
                        if (cm.getCompetitor2() == coach) {
                            val += value.getValue1();
                        }
                        if (cm.getCompetitor1() == coach) {
                            val += value.getValue2();
                        }
                    }
                    values.add(val);
                }

                hCoach.put("values", values);
                coaches_summary.add(hCoach);
                //StringEscapeUtils.escapeHtml3(mTour.getParams().getCriteria(i).getName()));
            }
            root.put("coaches_summary", coaches_summary);

            root.put("nb_rounds", mRoundNumber + 1);
            /**
             * *****************
             **** Second table *** ******************
     */
 /*    ArrayList coaches_rounds = new ArrayList();
            for (int i = 0; i < team.getCoachsCount(); i++) {
                Coach coach = team.getCoach(i);
                HashMap<String, Object> hCoach = new HashMap<>();
                hCoach.put("name", coach.getName());

                int round_index = 1;
                hCoach.put("round", round_index);

                ArrayList values = new ArrayList();
                Round r = Tournament.getTournament().getRound(round_index - 1);
                for (int j = 0; j < nb_criterias; j++) {
                    Criteria crit = mTour.getParams().getCriteria(j);
                    int val = 0;
                    for (int k = 0; k < coach.getMatchCount(); k++) {
                        CoachMatch cm = (CoachMatch) coach.getMatch(k);
                        if (r.containsCoachMatch(cm)) {
                            Value value = cm.getValue(crit);
                            if (cm.getCompetitor1() == coach) {
                                val += value.getValue1();
                            }
                            if (cm.getCompetitor2() == coach) {
                                val += value.getValue2();
                            }
                        }
                    }
                    values.add(val);
                }
                hCoach.put("values", values);

                MjtRanking rank = new MjtRankingIndiv(round_index - 1, coaches, true, true);
                for (int j = 0; j < rank.getRowCount(); j++) {
                    ObjectRanking or = rank.getSortedObject(j);
                    if (or.getObject() == coach) {
                        hCoach.put("points", or.getValue1());
                    }
                }

                for (int j = 0; j < mRanking.getRowCount(); j++) {
                    ObjectRanking or = mRanking.getSortedObject(j);
                    if (or.getObject() == coach) {
                        hCoach.put("total_points", or.getValue1());
                    }
                }

                ArrayList coach_rounds = new ArrayList();
                for (int j = 1; j <= mRoundNumber; j++) {
                    HashMap<String, Object> hhCoach = new HashMap<>();

                    hhCoach.put("round_index", j + 1);

                    values = new ArrayList();
                    r = Tournament.getTournament().getRound(j);
                    for (int k = 0; k < nb_criterias; k++) {
                        Criteria crit = mTour.getParams().getCriteria(k);
                        int val = 0;
                        for (int l = 0; l < coach.getMatchCount(); l++) {
                            CoachMatch cm = (CoachMatch) coach.getMatch(l);
                            if (r.containsCoachMatch(cm)) {
                                Value value = cm.getValue(crit);
                                if (cm.getCompetitor1() == coach) {
                                    val += value.getValue1();
                                }
                                if (cm.getCompetitor2() == coach) {
                                    val += value.getValue2();
                                }
                            }
                        }
                        values.add(val);
                    }

                    hhCoach.put("values", values);

                    rank = new MjtRankingIndiv(j, coaches, true, true);
                    for (int k = 0; k < rank.getRowCount(); k++) {
                        ObjectRanking or = rank.getSortedObject(k);
                        if (or.getObject() == coach) {
                            hhCoach.put("points", or.getValue1());
                        }
                    }

                    coach_rounds.add(hhCoach);
                }

                hCoach.put("rounds", coach_rounds);

                coaches_rounds.add(hCoach);
            }

            root.put("coaches_rounds", coaches_rounds);

            /**
             * *****************
             **** third table *** ******************
     */
 /*  ArrayList aRounds = new ArrayList();
            for (int i = 0; i < team.getMatchCount(); i++) {
                HashMap aRound = new HashMap();
                aRound.put("index", i + 1);
                TeamMatch tm = (TeamMatch) team.getMatch(i);

                aRound.put("opp1", WebPicture.getPictureAsHTML(((Team) tm.getCompetitor1()).getPicture(), 10, 10) + tm.getCompetitor1().getName());
                aRound.put("opp2", WebPicture.getPictureAsHTML(((Team) tm.getCompetitor2()).getPicture(), 10, 10) + tm.getCompetitor2().getName());

                ArrayList matchs = new ArrayList();
                for (int j = 0; j < tm.getMatchCount(); j++) {
                    CoachMatch cm = tm.getMatch(j);
                    HashMap hCm = new HashMap();
                    hCm.put("c1", cm.getCompetitor1().getName());
                    hCm.put("c2", cm.getCompetitor2().getName());
                    if (cm.getRoster1() != null) {
                        hCm.put("roster1", cm.getRoster1().getName());
                    } else {
                        hCm.put("roster1", ((Coach) cm.getCompetitor1()).getRoster().getName());
                    }
                    if (cm.getRoster2() != null) {
                        hCm.put("roster2", cm.getRoster2().getName());
                    } else {
                        hCm.put("roster2", ((Coach) cm.getCompetitor2()).getRoster().getName());
                    }
                    hCm.put("points1", cm.getValue(1, (Coach) cm.getCompetitor1()));
                    hCm.put("points2", cm.getValue(1, (Coach) cm.getCompetitor2()));

                    ArrayList values1 = new ArrayList();
                    ArrayList values2 = new ArrayList();
                    for (int k = 0; k < nb_criterias; k++) {
                        Criteria crit = mTour.getParams().getCriteria(k);
                        Value value = cm.getValue(crit);
                        values1.add(0, value.getValue1());
                        values2.add(value.getValue2());
                    }
                    hCm.put("values1", values1);
                    hCm.put("values2", values2);

                    matchs.add(hCm);
                }
                aRound.put("matchs", matchs);
                aRounds.add(aRound);
            }
            root.put("rounds", aRounds);

            final SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy", Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            root.put(ReportKeys.CS_DateGeneration, formatShort.format(new Date()));
            address = File.createTempFile(
                    StringConstants.CS_RESULT + " " + format.format(new Date()), ".html");
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
    }*/
    private File createClanReport(Clan clan) {
        ArrayList<Coach> coaches = new ArrayList<>();

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            Coach coach = Tournament.getTournament().getCoach(i);
            if (coach.getClan() == clan) {
                coaches.add(coach);
            }
        }

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team team = Tournament.getTournament().getTeam(i);
            if (team.getClan() == clan) {
                for (int j = 0; j < team.getCoachsCount(); j++) {
                    Coach coach = team.getCoach(j);
                    if (!coaches.contains(coach)) {
                        coaches.add(coach);
                    }
                }
            }
        }
        ArrayList<Clan> clans = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            clans.add(Tournament.getTournament().getClan(i));
        }

        try {
            MjtRankingClan ranking = new MjtRankingClan(mRoundNumber, clans, false);
            int rank = 0;
            for (int i = 0; i < ranking.getRowCount(); i++) {
                Object obj = ranking.getSortedObject(i).getObject();
                if (obj instanceof Clan) {
                    if (obj == clan) {
                        rank = i + 1;
                        break;
                    }
                }
            }

            return createCoachGroupReport(clan, "coach_group_report.html", coaches, rank, Tournament.getTournament().getClansCount());

        } catch (RemoteException re) {
            System.out.println(re.getLocalizedMessage());
            return null;
        }

    }

    private File createCoachGroupReport(IWithNameAndPicture object, String template, ArrayList<Coach> coaches, int ranking, int total_ranking) {
        File address = null;
        Writer out = null;

        mRanking = new MjtRankingIndiv(mRoundNumber, coaches, true, false);
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        try {
            final Configuration cfg = new Configuration();
            final URI uri = getClass().getResource("/tourma/views/report").toURI();
            if (uri.toString().contains(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".JAR!"))) {
                cfg.setClassForTemplateLoading(getClass(), StringConstants.CS_NULL);
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            final Template temp = cfg.getTemplate(template);

            final ArrayList<Round> rounds;
            rounds = new ArrayList<>();
            for (int i = 0; i < mTour.getRoundsCount() && i < mRoundNumber; i++) {
                rounds.add(mTour.getRound(i));
            }

            final Map root = new HashMap();
            root.put(
                    ReportKeys.CS_Nom,
                    StringEscapeUtils.escapeHtml3(mTour.getParams().getTournamentName()));

            // Team Name
            root.put(ReportKeys.CS_Title, StringEscapeUtils.escapeHtml3(object.getName()));

            // Picture
            String picture = "";
            if (object.getPicture() != null) {
                picture = WebPicture.getPictureAsHTML(object.getPicture(), 50, 50);

            }
            root.put("picture", picture);

            int nb_criterias = Tournament.getTournament().getParams().getCriteriaCount();
            root.put("nb_criterias", nb_criterias);

            root.put("coach", Translate.translate(Translate.CS_Coach));
            root.put("round", Translate.translate(Translate.CS_Round));
            root.put("points", Translate.translate(Translate.CS_Points));
            root.put("total_points", Translate.translate(Translate.CS_TotalPoints));

            root.put("roster", Translate.translate(Translate.CS_Roster));
            root.put("nb_criterias", nb_criterias);
            root.put("nb_criteriasx2", 2 * nb_criterias);

            final ArrayList criterias = new ArrayList();
            for (int i = 0; i < nb_criterias; i++) {
                criterias.add(StringEscapeUtils.escapeHtml3(mTour.getParams().getCriteria(i).getAccronym()));
            }
            root.put(ReportKeys.CS_Criterias, criterias);

            final ArrayList rcriterias = new ArrayList();
            for (int i = nb_criterias - 1; i >= 0; i--) {
                rcriterias.add(StringEscapeUtils.escapeHtml3(mTour.getParams().getCriteria(i).getAccronym()));
            }
            root.put("rcriterias", rcriterias);

            root.put("ranking", ranking);
            root.put("total_ranking", total_ranking);

            /**
             * *****************
             **** First table *** ******************
             */
            final ArrayList coaches_summary = new ArrayList();
            for (int i = 0; i < coaches.size(); i++) {
                Coach coach = coaches.get(i);
                HashMap<String, Object> hCoach = new HashMap<>();
                hCoach.put("name", coach.getName());
                hCoach.put("roster", coach.getRoster().getName());
                for (int j = 0; j < mRanking.getRowCount(); j++) {
                    ObjectRanking or = mRanking.getSortedObject(j);
                    if (or.getObject() == coach) {
                        hCoach.put("points", or.getValue1());
                    }
                }

                ArrayList<Integer> values = new ArrayList<>();

                // Find value + for each matches
                for (int j = 0; j < nb_criterias; j++) {
                    Criteria crit = mTour.getParams().getCriteria(j);
                    int val = 0;
                    for (int k = 0; k < coach.getMatchCount(); k++) {
                        CoachMatch cm = (CoachMatch) coach.getMatch(k);
                        Value value = cm.getValue(crit);
                        if (cm.getCompetitor1() == coach) {
                            if (crit == td) {
                                if (value.getValue1() < 0) {
                                    val += 0;
                                } else {
                                    val += value.getValue1();
                                }

                            } else {
                                val += value.getValue1();
                            }
                        }
                        if (cm.getCompetitor2() == coach) {
                            if (crit == td) {
                                if (value.getValue2() < 0) {
                                    val += 0;
                                } else {
                                    val += value.getValue2();
                                }

                            } else {
                                val += value.getValue2();
                            }
                        }
                    }
                    values.add(val);
                }
                // Find value - for each matches
                for (int j = 0; j < nb_criterias; j++) {
                    Criteria crit = mTour.getParams().getCriteria(j);
                    int val = 0;
                    for (int k = 0; k < coach.getMatchCount(); k++) {
                        CoachMatch cm = (CoachMatch) coach.getMatch(k);
                        Value value = cm.getValue(crit);
                        if (cm.getCompetitor2() == coach) {
                            if (crit == td) {
                                if (value.getValue1() < 0) {
                                    val += 0;
                                } else {
                                    val += value.getValue1();
                                }

                            } else {
                                val += value.getValue1();
                            }
                        }
                        if (cm.getCompetitor1() == coach) {
                            if (crit == td) {
                                if (value.getValue2() < 0) {
                                    val += 0;
                                } else {
                                    val += value.getValue2();
                                }

                            } else {
                                val += value.getValue2();
                            }
                        }
                    }
                    values.add(val);
                }

                hCoach.put("values", values);
                coaches_summary.add(hCoach);
                //StringEscapeUtils.escapeHtml3(mTour.getParams().getCriteria(i).getName()));
            }
            root.put("coaches_summary", coaches_summary);

            root.put("nb_rounds", mRoundNumber + 1);
            /**
             * *****************
             **** Second table *** ******************
             */
            ArrayList coaches_rounds = new ArrayList();
            for (int i = 0; i < coaches.size(); i++) {
                Coach coach = coaches.get(i);
                HashMap<String, Object> hCoach = new HashMap<>();
                hCoach.put("name", coach.getName());

                int round_index = 1;
                hCoach.put("round", round_index);

                ArrayList values = new ArrayList();
                Round r = Tournament.getTournament().getRound(round_index - 1);
                for (int j = 0; j < nb_criterias; j++) {
                    Criteria crit = mTour.getParams().getCriteria(j);
                    int val = 0;
                    for (int k = 0; k < coach.getMatchCount(); k++) {
                        CoachMatch cm = (CoachMatch) coach.getMatch(k);
                        if (r.containsCoachMatch(cm)) {
                            Value value = cm.getValue(crit);
                            if (cm.getCompetitor1() == coach) {
                                if (crit == td) {
                                    if (value.getValue1() < 0) {
                                        val += 0;
                                    } else {
                                        val += value.getValue1();
                                    }

                                } else {
                                    val += value.getValue1();
                                }
                            }
                            if (cm.getCompetitor2() == coach) {
                                if (crit == td) {
                                    if (value.getValue2() < 0) {
                                        val += 0;
                                    } else {
                                        val += value.getValue2();
                                    }

                                } else {
                                    val += value.getValue2();
                                }
                            }
                        }
                    }
                    values.add(val);
                }
                hCoach.put("values", values);

                MjtRanking rank = new MjtRankingIndiv(round_index - 1, coaches, true, true);
                for (int j = 0; j < rank.getRowCount(); j++) {
                    ObjectRanking or = rank.getSortedObject(j);
                    if (or.getObject() == coach) {
                        hCoach.put("points", or.getValue1());
                    }
                }

                for (int j = 0; j < mRanking.getRowCount(); j++) {
                    ObjectRanking or = mRanking.getSortedObject(j);
                    if (or.getObject() == coach) {
                        hCoach.put("total_points", or.getValue1());
                    }
                }

                ArrayList coach_rounds = new ArrayList();
                for (int j = 1; j <= mRoundNumber; j++) {
                    HashMap<String, Object> hhCoach = new HashMap<>();

                    hhCoach.put("round_index", j + 1);

                    values = new ArrayList();
                    r = Tournament.getTournament().getRound(j);
                    for (int k = 0; k < nb_criterias; k++) {
                        Criteria crit = mTour.getParams().getCriteria(k);
                        int val = 0;
                        for (int l = 0; l < coach.getMatchCount(); l++) {
                            CoachMatch cm = (CoachMatch) coach.getMatch(l);
                            if (r.containsCoachMatch(cm)) {
                                Value value = cm.getValue(crit);
                                if (cm.getCompetitor1() == coach) {
                                    if (crit == td) {
                                        if (value.getValue1() < 0) {
                                            val += 0;
                                        } else {
                                            val += value.getValue1();
                                        }

                                    } else {
                                        val += value.getValue1();
                                    }
                                }
                                if (cm.getCompetitor2() == coach) {
                                    if (crit == td) {
                                        if (value.getValue2() < 0) {
                                            val += 0;
                                        } else {
                                            val += value.getValue2();
                                        }

                                    } else {
                                        val += value.getValue2();
                                    }
                                }
                            }
                        }
                        values.add(val);
                    }

                    hhCoach.put("values", values);

                    rank = new MjtRankingIndiv(j, coaches, true, true);
                    for (int k = 0; k < rank.getRowCount(); k++) {
                        ObjectRanking or = rank.getSortedObject(k);
                        if (or.getObject() == coach) {
                            hhCoach.put("points", or.getValue1());
                        }
                    }

                    coach_rounds.add(hhCoach);
                }

                hCoach.put("rounds", coach_rounds);

                coaches_rounds.add(hCoach);
            }

            root.put("coaches_rounds", coaches_rounds);

            /**
             * *****************
             **** third table *** ******************
             */
            ArrayList aRounds = new ArrayList();
            for (int i = 0; i <= mRoundNumber; i++) {
                HashMap aRound = new HashMap();
                aRound.put("index", i + 1);
                ArrayList matchs = new ArrayList();
                ArrayList<CoachMatch> coach_matchs = new ArrayList<>();
                int tableIndex = 0;
                if (Tournament.getTournament().getParams().isTeamTournament()
                        && (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
                    Coach c = coaches.get(0);
                    Team team = c.getTeamMates();
                    if (team != null) {
                        TeamMatch tm = (TeamMatch) team.getMatch(i);
                        aRound.put("opp1", WebPicture.getPictureAsHTML(((Team) tm.getCompetitor1()).getPicture(), 10, 10) + tm.getCompetitor1().getName());
                        aRound.put("opp2", WebPicture.getPictureAsHTML(((Team) tm.getCompetitor2()).getPicture(), 10, 10) + tm.getCompetitor2().getName());
                        Round round = Tournament.getTournament().getRound(i);
                        aRound.put("tableindex", "&nbsp;");

                        for (int j = 0; j < round.getMatchsCount(); j++) {
                            if (round.getMatch(j) == tm) {
                                aRound.put("tableindex", j + 1);
                                break;
                            }
                        }

                        for (int j = 0; j < tm.getMatchCount(); j++) {
                            coach_matchs.add(tm.getMatch(j));
                        }
                    }
                } else {
                    aRound.put("tableindex", "&nbsp;");

                    aRound.put("opp1", "&nbsp;");
                    aRound.put("opp2", "&nbsp;");
                    Round round = Tournament.getTournament().getRound(i);
                    for (int j = 0; j < coaches.size(); j++) {
                        Coach c = coaches.get(j);
                        for (int k = 0; k < c.getMatchCount(); k++) {
                            CoachMatch cm = (CoachMatch) c.getMatch(k);
                            if (cm.getRound() == round) {
                                coach_matchs.add(cm);
                            }
                        }
                    }
                }

                for (int j = 0; j < coach_matchs.size(); j++) {
                    CoachMatch cm = coach_matchs.get(j);
                    HashMap hCm = new HashMap();
                    Round round = Tournament.getTournament().getRound(i);
                    ArrayList<CoachMatch> list = round.getCoachMatchs();
                    tableIndex = list.lastIndexOf(cm) + 1;
                    if (tableIndex >= list.size()) {
                        tableIndex = 0;
                    }
                    hCm.put("table", tableIndex);
                    hCm.put("c1", cm.getCompetitor1().getName());
                    hCm.put("c2", cm.getCompetitor2().getName());
                    if (cm.getRoster1() != null) {
                        hCm.put("roster1", cm.getRoster1().getName());
                    } else {
                        hCm.put("roster1", ((Coach) cm.getCompetitor1()).getRoster().getName());
                    }
                    if (cm.getRoster2() != null) {
                        hCm.put("roster2", cm.getRoster2().getName());
                    } else {
                        hCm.put("roster2", ((Coach) cm.getCompetitor2()).getRoster().getName());
                    }
                    hCm.put("points1", cm.getValue(1, (Coach) cm.getCompetitor1()));
                    hCm.put("points2", cm.getValue(1, (Coach) cm.getCompetitor2()));

                    ArrayList values1 = new ArrayList();
                    ArrayList values2 = new ArrayList();
                    for (int k = 0; k < nb_criterias; k++) {
                        Criteria crit = mTour.getParams().getCriteria(k);
                        Value value = cm.getValue(crit);

                        if (crit == td) {
                            if (value.getValue1() < 0) {
                                values1.add(0, 0);
                            } else {
                                values1.add(0, value.getValue1());
                            }

                        } else {
                            values1.add(0, value.getValue1());
                        }
                        if (crit == td) {
                            if (value.getValue2() < 0) {
                                values2.add(0);
                            } else {
                                values2.add(value.getValue2());
                            }

                        } else {
                            values2.add(value.getValue2());
                        }
                        // values1.add(0, value.getValue1());
                        // values2.add(value.getValue2());
                    }
                    hCm.put("values1", values1);
                    hCm.put("values2", values2);

                    matchs.add(hCm);
                }

                aRound.put("matchs", matchs);
                aRounds.add(aRound);
            }
            root.put("rounds", aRounds);

            final SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy", Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            root.put(ReportKeys.CS_DateGeneration, formatShort.format(new Date()));
            address = File.createTempFile(
                    StringConstants.CS_RESULT + " " + format.format(new Date()), ".html");
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
    private File createReport(int index) {

        File f = null;
        if (index >= 0) {
            IWithNameAndPicture object = objects.get(index);
            switch (mType) {
                case C_CLAN:
                    f = createClanReport((Clan) object);
                    break;
                case C_INDIVIDUAL:
                    f = createIndivReport((Coach) object);
                    break;
                case C_TEAM:
                    f = createTeamReport((Team) object);
                    break;
            }

            if (jcxWithMembers.isSelected()) {
                ArrayList<File> files = new ArrayList<>();
                if (object instanceof Team) {
                    Team t = (Team) object;
                    for (int i = 0; i < t.getCoachsCount(); i++) {
                        File f_tmp = createIndivReport(t.getCoach(i));
                        files.add(f_tmp);
                    }
                }
                if (object instanceof Clan) {
                    Clan c = (Clan) object;
                    for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
                        Team t = Tournament.getTournament().getTeam(i);
                        if (t.getClan() == c) {
                            File f_tmp = createTeamReport(t);
                            files.add(f_tmp);
                        }
                    }
                    for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                        Coach co = Tournament.getTournament().getCoach(i);
                        if (co.getClan() == c) {
                            File f_tmp = createIndivReport(co);
                            files.add(f_tmp);
                        }
                    }
                }

                f = mergeReports(f, files);
            }
        }
        return f;
    }

    protected File mergeReports(File file, ArrayList<File> files) {

        File full_file = null;
        StringBuilder sb = new StringBuilder();

        try {
            // Load first file
            String baseFile = readFile(file);

            // Find </body>
            int end_body_index = baseFile.indexOf("</body>");
            sb.append(baseFile.substring(0, end_body_index));

            // Loop on other files
            for (File f : files) {
                // Find <body> and </body>
                String tmp_f = readFile(f);
                int end_index = tmp_f.indexOf("</body>");
                int start_index = tmp_f.indexOf("<body>");

                if ((end_index != -1) && (start_index != -1)) {
                    // insert the content in sb
                    sb.append("<p style=\"page-break-before: always\">\n");
                    sb.append(tmp_f.substring(start_index + 6, end_index));
                    sb.append("</p>\n");
                }
            }

            sb.append(baseFile.substring(end_body_index));

            // Open temporary file
            full_file = File.createTempFile("full_report_file", ".html");
            // Write it
            BufferedWriter writer = new BufferedWriter(new FileWriter(full_file));
            writer.write(sb.toString());

            //Close writer
            writer.close();

            // Delete others
            file.delete();
            for (File f : files) {
                f.delete();
            }
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, exception.getLocalizedMessage());
        }

        return full_file;
    }

    private String readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    private static final Logger LOG = Logger.getLogger(JdgReport.class
            .getName());

}
