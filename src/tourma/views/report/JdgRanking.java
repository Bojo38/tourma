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
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtRanking;
import tourma.utility.ExtensionFileFilter;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public final class JdgRanking extends javax.swing.JDialog {

    private int mRoundNumber;
    private Tournament mTour;
    //private boolean mResult;
    //private int mRankType;
    private int mType = 0;
    /**
     * 0: indiv 1: team 2: clan
     */
    private File mFilename = null;
    //Criteria _criteria;
    //boolean _positive;
    private String mTitle;
    private MjtRanking mRanking;

    private final static String CS_Round="Round";
    
    /**
     * Creates new form jdgRoundReport
     * @param parent
     * @param type
     * @param modal
     * @param title
     * @param tour
     * @param roundNumber
     * @param ranking
     */
    public JdgRanking(final java.awt.Frame parent, final boolean modal, final String title, final int roundNumber, final Tournament tour, final MjtRanking ranking, final int type) {
        super(parent, modal);
        initComponents();

        mRanking = ranking;
        //_criteria = criteria;
        //_positive = positive;
        //_round = round;
        mTitle = title;
        mRoundNumber = roundNumber;
        mTour = tour;
        //_rankType = RankType;
        mType = type;
        this.setTitle(
                tour.getParams().getTournamentName()
                + " - " + Translate.translate(CS_Round) + " " + roundNumber);
        try {
            jepHTML.setContentType("html");
            mFilename = createReport();
            jepHTML.setPage(mFilename.toURI().toURL());
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

        jbtExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Html.png"))); // NOI18N
        jbtExport.setText(bundle.getString("HTMLExport")); // NOI18N
        jbtExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExportActionPerformed(evt);
            }
        });
        jPanel1.add(jbtExport);

        jbtExportPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/pdf.jpg"))); // NOI18N
        jbtExportPDF.setText(bundle.getString("HTMLExport")); // NOI18N
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
                in = new InputStreamReader(new FileInputStream(mFilename),Charset.defaultCharset());
                {
                    out = new OutputStreamWriter(new FileOutputStream(export),Charset.defaultCharset());
                    int c= in.read();
                    while (c  != -1) {
                        out.write(c);
                        c= in.read();
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
                        LOG.log(Level.INFO,e.getLocalizedMessage());
                    }
                }

                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        LOG.log(Level.INFO,e.getLocalizedMessage());
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
    private javax.swing.JButton jbtExport;
    private javax.swing.JButton jbtExportPDF;
    private javax.swing.JButton jbtOK;
    private javax.swing.JButton jbtPrint;
    private javax.swing.JEditorPane jepHTML;
    // End of variables declaration//GEN-END:variables

    @SuppressWarnings({"PMD.UnusedFormalParameter"})
    private File createReport() {
        File address = null;

        Writer out=null;
        try {

            final Configuration cfg = new Configuration();
            final URI uri = getClass().getResource("/tourma/views/report").toURI();
            if (uri.toString().contains(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".JAR!"))) {
                cfg.setClassForTemplateLoading(getClass(), StringConstants.CS_NULL);
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            final Template temp = cfg.getTemplate("rank.html");

            final Map root = new HashMap();
            root.put(ReportKeys.CS_Nom,
                    StringEscapeUtils.escapeHtml4(mTour.getParams().getTournamentName() + " - " + 
                            Translate.translate(CS_Round) + " " + mRoundNumber));
            root.put(ReportKeys.CS_Title, mTitle);

            final ArrayList titles = new ArrayList();
            for (int i = 0; i < mRanking.getColumnCount(); i++) {
                titles.add(StringEscapeUtils.escapeHtml4(mRanking.getColumnName(i)));
            }
            root.put(ReportKeys.CS_Titles, titles);

            final ArrayList lines = new ArrayList();
            for (int i = 0; i < mRanking.getRowCount(); i++) {
                final HashMap line = new HashMap();
                final ArrayList cols = new ArrayList();
                for (int j = 0; j < mRanking.getColumnCount(); j++) {
                    Object obj=mRanking.getValueAt(i, j);
                    if (obj instanceof String)
                    {
                        cols.add(StringEscapeUtils.escapeHtml4((String)obj));
                    }
                    else
                    {
                        cols.add(obj);
                    }
                }
                line.put(ReportKeys.CS_Cols, cols);
                lines.add(line);
            }
            root.put(ReportKeys.CS_Lines, lines);

            final SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy", Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            root.put(ReportKeys.CS_DateGeneration, formatShort.format(new Date()));
            address = File.createTempFile(
                    StringConstants.CS_RESULT + " "
                    + format.format(new Date()), ".tmp");
            address.deleteOnExit();
            out = new OutputStreamWriter(new FileOutputStream(address),Charset.defaultCharset());
            temp.process(root, out);
            out.flush();

        } catch (URISyntaxException | IOException | TemplateException e) {
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
    private static final Logger LOG = Logger.getLogger(JdgRanking.class.getName());
    
}
