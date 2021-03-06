/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JdgAbout.java
 *
 * Created on 27 mai 2009, 11:08:24
 */
package tourma.views.system;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ResourceBundle;
import tourma.languages.Translate;
import tourma.utility.Version;

/**
 *
 * @author root.106572700130
 */
public final class JdgAbout extends javax.swing.JDialog {

    private final static String CS_Name="Name";
    private final static String CS_Date="Date";
    private final static String CS_Version="Version";
    
    /**
     * Creates new form jdgAbout
     *
     * @param parent
     * @param modal
     */
    public JdgAbout(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();

        this.setSize(580, 350);

        final int screenWidth = dmode.getWidth();
        final int screenHeight = dmode.getHeight();
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

        ResourceBundle copyright = java.util.ResourceBundle.getBundle("tourma/languages/copyrights");

        jlbNom.setText(Translate.translate(CS_Name)
                + ": "
                + Version.getSingleton().getProperty(copyright.getString("LONG_NAME")));
        jlbDate.setText(Translate.translate(CS_Date)
                + ": "
                + Version.getSingleton().getProperty(copyright.getString("DATE")));
        jlbVersion.setText(Translate.translate(CS_Version)
                + ": "
                + Version.getSingleton().getProperty(copyright.getString("VERSION")));

        jlbFreeMarker.setText(Version.getSingleton().getProperty(copyright.getString("FREEMARKER")));
        jlbFreeMarkerC.setText(Version.getSingleton().getProperty(copyright.getString("FREEMARKER_COPYRIGHT")));
        jlbJCommon.setText(Version.getSingleton().getProperty(copyright.getString("JCOMMON")));
        jlbJCommonC.setText(Version.getSingleton().getProperty(copyright.getString("JCOMMON_COPYRIGHT")));
        jlbJDom.setText(Version.getSingleton().getProperty(copyright.getString("JDOM")));
        jlbJDomC.setText(Version.getSingleton().getProperty(copyright.getString("JDOM_COPYRIGHT")));
        jlbJFreeChart.setText(Version.getSingleton().getProperty(copyright.getString("JFREECHART")));
        jlbJFreeChartC.setText(Version.getSingleton().getProperty(copyright.getString("JFREECHART_COPYRIGHT")));
                jlbEkit.setText(Version.getSingleton().getProperty(copyright.getString("EKIT")));
        jlbEkit1.setText(Version.getSingleton().getProperty(copyright.getString("EKIT_COPYRIGHT")));

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
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jlbFreeMarker = new javax.swing.JLabel();
        jlbFreeMarkerC = new javax.swing.JLabel();
        jlbJDom = new javax.swing.JLabel();
        jlbJDomC = new javax.swing.JLabel();
        jlbJCommon = new javax.swing.JLabel();
        jlbJCommonC = new javax.swing.JLabel();
        jlbJFreeChart = new javax.swing.JLabel();
        jlbJFreeChartC = new javax.swing.JLabel();
        jlbEkit = new javax.swing.JLabel();
        jlbEkit1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jlbNom = new javax.swing.JLabel();
        jlbVersion = new javax.swing.JLabel();
        jlbDate = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        setTitle(bundle.getString("A PROPOS DE")); // NOI18N
        setUndecorated(true);
        setResizable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        jButton1.setText(bundle.getString("OK")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setLayout(new java.awt.GridLayout(8, 1, 1, 1));

        jlbFreeMarker.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlbFreeMarker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbFreeMarker.setText("FreeMarker"); // NOI18N
        jPanel2.add(jlbFreeMarker);

        jlbFreeMarkerC.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlbFreeMarkerC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbFreeMarkerC.setText(bundle.getString("FREEMARKER COPYRIGHT")); // NOI18N
        jPanel2.add(jlbFreeMarkerC);

        jlbJDom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlbJDom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbJDom.setText("JDOM"); // NOI18N
        jPanel2.add(jlbJDom);

        jlbJDomC.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlbJDomC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbJDomC.setText("JDOM"); // NOI18N
        jPanel2.add(jlbJDomC);

        jlbJCommon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlbJCommon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbJCommon.setText("JCommon"); // NOI18N
        jPanel2.add(jlbJCommon);

        jlbJCommonC.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlbJCommonC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbJCommonC.setText("JCOMMON"); // NOI18N
        jPanel2.add(jlbJCommonC);

        jlbJFreeChart.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlbJFreeChart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbJFreeChart.setText("JFreeChart"); // NOI18N
        jPanel2.add(jlbJFreeChart);

        jlbJFreeChartC.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlbJFreeChartC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbJFreeChartC.setText("JFreeChart"); // NOI18N
        jPanel2.add(jlbJFreeChartC);

        jlbEkit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlbEkit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbEkit.setText("Ekit"); // NOI18N
        jPanel2.add(jlbEkit);

        jlbEkit1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlbEkit1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbEkit1.setText("Ekit\n"); // NOI18N
        jPanel2.add(jlbEkit1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(4, 0));

        jlbNom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbNom.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbNom.setText(bundle.getString("Name")); // NOI18N
        jPanel4.add(jlbNom);

        jlbVersion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbVersion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbVersion.setText(bundle.getString("Version")); // NOI18N
        jPanel4.add(jlbVersion);

        jlbDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbDate.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbDate.setText(bundle.getString("Date")); // NOI18N
        jPanel4.add(jlbDate);

        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText(bundle.getString("License")); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel jlbDate;
    private javax.swing.JLabel jlbEkit;
    private javax.swing.JLabel jlbEkit1;
    private javax.swing.JLabel jlbFreeMarker;
    private javax.swing.JLabel jlbFreeMarkerC;
    private javax.swing.JLabel jlbJCommon;
    private javax.swing.JLabel jlbJCommonC;
    private javax.swing.JLabel jlbJDom;
    private javax.swing.JLabel jlbJDomC;
    private javax.swing.JLabel jlbJFreeChart;
    private javax.swing.JLabel jlbJFreeChartC;
    private javax.swing.JLabel jlbNom;
    private javax.swing.JLabel jlbVersion;
    // End of variables declaration//GEN-END:variables

}
