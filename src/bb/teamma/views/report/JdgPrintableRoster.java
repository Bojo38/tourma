/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgRoundReport.java
 *
 * Created on 28 juin 2010, 10:52:47
 */
package bb.teamma.views.report;

import com.itextpdf.text.PageSize;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.utility.HtmlEscape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
import bb.teamma.data.Inducement;
import bb.teamma.data.Player;
import bb.teamma.data.Roster;
import bb.teamma.data.RosterType;
import bb.teamma.data.Skill;
import bb.teamma.data.SkillType;
import bb.teamma.data.StarPlayer;
import bb.teamma.languages.Translate;
import bb.tourma.MainFrame;
import bb.tourma.data.Coach;
import bb.tourma.utility.ExtensionFileFilter;
import bb.tourma.utility.StringConstants;
import bb.tourma.views.report.HTMLtoPDF;
import bb.tourma.views.report.ReportKeys;

/**
 *
 * @author Frederic Berger
 */
public final class JdgPrintableRoster extends javax.swing.JDialog {

    private Roster mRoster;
    private Coach mCoach;
    private File mFilename = null;
    private boolean mWithSkill = false;

    /**
     * Creates new form jdgRoundReport
     *
     * @param parent
     * @param modal
     * @param roster
     * @param withSkill
     * @param coach
     */
    public JdgPrintableRoster(final java.awt.Frame parent, final boolean modal, final Roster roster, final Coach coach, boolean withSkill) {
        super(parent, modal);
        initComponents();
        mRoster = roster;
        mCoach = coach;
        mWithSkill = withSkill;
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
        setAlwaysOnTop(true);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Select.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bb/teamma/languages/language"); // NOI18N
        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        jbtPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Document.png"))); // NOI18N
        jbtPrint.setText(bundle.getString("Print")); // NOI18N
        jbtPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPrintActionPerformed(evt);
            }
        });
        jPanel1.add(jbtPrint);

        jbtExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/Html.png"))); // NOI18N
        jbtExport.setText(bundle.getString("HTMLExport")); // NOI18N
        jbtExport.setName("jbtExport"); // NOI18N
        jbtExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExportActionPerformed(evt);
            }
        });
        jPanel1.add(jbtExport);

        jbtExportPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bb/tourma/images/pdf.jpg"))); // NOI18N
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("bb/tourma/languages/language"); // NOI18N
        jbtExportPDF.setText(bundle1.getString("PDFExport")); // NOI18N
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
        jfc.setName("jfc");
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            final File export = jfc.getSelectedFile();
            OutputStreamWriter out = null;
            InputStreamReader in = null;
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(getmFilename());
                in = new InputStreamReader(fis, StandardCharsets.UTF_8);
                fos = new FileOutputStream(export);
                out = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                int c = in.read();
                while (c != -1) {
                    out.write(c);
                    c = in.read();
                }

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            } finally {

                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    LOG.log(Level.INFO, e.getLocalizedMessage());
                }

                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    LOG.log(Level.INFO, e.getLocalizedMessage());
                }

                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    LOG.log(Level.INFO, e.getLocalizedMessage());
                }

                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    LOG.log(Level.INFO, e.getLocalizedMessage());
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

                    HTMLtoPDF.exportToPDF(new FileOutputStream(export), s, this.getName(),PageSize.A4.rotate());
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

    private static final String CS_title = "title";
    private static final String CS_numero = "numero";
    private static final String CS_name = "name";
    private static final String CS_position = "position";
    private static final String CS_movement = "movement";
    private static final String CS_strength = "strength";
    private static final String CS_agility = "agility";
    private static final String CS_armor = "armor";
    private static final String CS_skills = "skills";
    private static final String CS_cost = "cost";
    private static final String CS_nb = "nb";
    private static final String CS_single = "single";
    private static final String CS_double = "double";
    private static final String CS_NBSP = "&nbsp;";
    private static final String CS_players = "players";
    private static final String CS_teamname = "teamname";
    private static final String CS_apothecary = "apothecary";
    private static final String CS_apo_price = "apo_price";
    private static final String CS_apo_cost = "apo_cost";
    private static final String CS_coachname = "coachname";
    private static final String CS_assists = "assists";
    private static final String CS_ass_price = "ass_price";
    private static final String CS_ass_cost = "ass_cost";
    private static final String CS_race = "race";

    private static final String CS_cheer = "cheer";
    private static final String CS_cheer_price = "cheer_price";
    private static final String CS_cheer_cost = "cheer_cost";
    private static final String CS_rank = "rank";
    private static final String CS_reroll = "reroll";
    private static final String CS_reroll_price = "reroll_price";
    private static final String CS_reroll_cost = "reroll_cost";
    private static final String CS_pop = "pop";
    private static final String CS_pop_price = "pop_price";
    private static final String CS_pop_cost = "pop_cost";
    private static final String CS_extra = "extra";
    private static final String CS_extra_price = "extra_price";
    private static final String CS_extra_cost = "extra_cost";
    private static final String CS_local = "local";
    private static final String CS_local_price = "local_price";
    private static final String CS_local_cost = "local_cost";
    private static final String CS_igor = "igor";
    private static final String CS_igor_price = "igor_price";
    private static final String CS_igor_cost = "igor_cost";
    private static final String CS_bribe = "bribe";
    private static final String CS_bribe_price = "bribe_price";
    private static final String CS_bribe_cost = "bribe_cost";
    private static final String CS_wizard = "wizard";
    private static final String CS_wizard_price = "wizard_price";
    private static final String CS_wizard_cost = "wizard_cost";
    private static final String CS_babes = "babes";
    private static final String CS_babes_price = "babes_price";
    private static final String CS_babes_cost = "babes_cost";
    private static final String CS_chef = "chef";
    private static final String CS_chef_price = "chef_price";
    private static final String CS_chef_cost = "chef_cost";
    private static final String CS_cards = "cards";
    private static final String CS_total = "total";
    private static final String CS_inducements="inducements";
   
    private File createReport() {
        File address = null;
        HtmlEscape html=new HtmlEscape();
        OutputStreamWriter out = null;
        FileOutputStream fos = null;
        try {
            final Configuration cfg = new Configuration();
            final URI uri = getClass().getResource("/bb/teamma/views/report").toURI();
            if (uri.toString().contains(".jar!")) {
                cfg.setClassForTemplateLoading(getClass(), "");
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            final Template temp = cfg.getTemplate("roster.html");

            final Map<String, Object> root = new HashMap<>();
            
            if (getmCoach() != null) {
                if (getmCoach().getTeam() != null) {
                    root.put(CS_title, StringEscapeUtils.escapeHtml4(getmCoach().getTeam()));
                } else {
                    root.put(CS_title, StringEscapeUtils.escapeHtml4(getmCoach().getName()));
                }
            } else {
                root.put(CS_title, "?");
            }

            ArrayList<HashMap<String, Object>> players = new ArrayList<>();

            for (int i = 0; i < getmRoster().getPlayerCount(); i++) {
                Player p = getmRoster().getPlayer(i);
                final HashMap<String, Object> player = new HashMap<>();
                player.put(CS_numero, i + 1);
                player.put(CS_name,  StringEscapeUtils.escapeHtml4(Translate.translate(p.getName())));
                player.put(CS_position, StringEscapeUtils.escapeHtml4(Translate.translate(p.getPlayertype().getPosition())));
                player.put(CS_movement, p.getPlayertype().getMovement());
                player.put(CS_strength, p.getPlayertype().getStrength());
                player.put(CS_agility, p.getPlayertype().getAgility());
                player.put(CS_armor, p.getPlayertype().getArmor());

                ArrayList<String> skills = new ArrayList<>();
                for (int cpt = 0; cpt < p.getPlayertype().getSkillCount(); cpt++) {
                    Skill _skill = p.getPlayertype().getSkill(cpt);
                    skills.add(StringEscapeUtils.escapeHtml4(Translate.translate(_skill.getmName())));
                }
                for (int cpt = 0; cpt < p.getSkillCount(); cpt++) {
                    Skill _skill = p.getSkill(cpt);

                    String ctmp = "rgb(";
                    Color c = _skill.getmColor();
                    ctmp = ctmp + c.getRed() + ",";
                    ctmp = ctmp + c.getGreen() + ",";
                    ctmp = ctmp + c.getBlue() + ")";
                    skills.add("<div style=\"color:" + ctmp + ";\">" + StringEscapeUtils.escapeHtml4(Translate.translate(_skill.getmName())) + "</div>");
                }
                player.put(CS_skills, skills);
                player.put(CS_cost, p.getValue(isWithSkill()));

                ArrayList<String> single = new ArrayList<>();
                for (int cpt = 0; cpt < p.getPlayertype().getSingleCount(); cpt++) {
                    SkillType _single = p.getPlayertype().getSingle(cpt);
                    single.add(Translate.translate(_single.getAccronym()));
                }
                player.put(CS_single, single);

                ArrayList<String> doubleT = new ArrayList<>();
                for (int cpt = 0; cpt < p.getPlayertype().getDoubleCount(); cpt++) {
                    SkillType _double = p.getPlayertype().getDouble(cpt);
                    doubleT.add(Translate.translate(_double.getAccronym()));
                }
                player.put(CS_double, doubleT);

                players.add(player);
            }

            for (int cpt = 0; cpt < getmRoster().getChampionCount(); cpt++) {
                StarPlayer p = getmRoster().getChampion(cpt);
                final HashMap<String, Object> player = new HashMap<>();
                player.put(CS_numero, players.size() + 1);
                player.put(CS_name,  StringEscapeUtils.escapeHtml4(Translate.translate(p.getName())));
                player.put(CS_position, StringEscapeUtils.escapeHtml4(Translate.translate( p.getPosition())));
                player.put(CS_movement, p.getMovement());
                player.put(CS_strength, p.getStrength());
                player.put(CS_agility, p.getAgility());
                player.put(CS_armor, p.getArmor());

                ArrayList<String> skills = new ArrayList<>();
                for (int cpt2 = 0; cpt2 < p.getSkillCount(); cpt2++) {
                    Skill _skill = p.getSkill(cpt2);
                    skills.add( StringEscapeUtils.escapeHtml4(Translate.translate(_skill.getmName())));
                }
                player.put(CS_skills, skills);
                player.put(CS_cost, p.getCost());

                ArrayList<String> single = new ArrayList<>();
                single.add(CS_NBSP);
                player.put(CS_single, single);

                ArrayList<String> doubleT = new ArrayList<>();
                doubleT.add(CS_NBSP);
                player.put(CS_double, doubleT);

                players.add(player);
            }

            root.put(CS_players, players);

            root.put(CS_teamname, CS_NBSP);
            if (getmRoster().isApothecary()) {
                root.put(CS_apothecary, 1);
            } else {
                root.put(CS_apothecary, 0);
            }
            root.put(CS_apo_price, RosterType.getApothecary_cost());
            if (getmRoster().isApothecary()) {
                root.put(CS_apo_cost, RosterType.getApothecary_cost());
            } else {
                root.put(CS_apo_cost, 0);
            }

            root.put(CS_coachname, CS_NBSP);
            root.put(CS_assists, getmRoster().getAssistants());
            root.put(CS_ass_price, RosterType.getAssistant_cost());
            root.put(CS_ass_cost, getmRoster().getAssistants() * RosterType.getAssistant_cost());

            if (getmRoster().getRoster() != null) {
                root.put(CS_race, getmRoster().getRoster().getName());
            } else {
                root.put(CS_race, CS_NBSP);
            }
            root.put(CS_cheer, getmRoster().getCheerleaders());

            root.put(CS_cheer_price, RosterType.getCheerleader_cost());
            root.put(CS_cheer_cost, getmRoster().getCheerleaders() * RosterType.getCheerleader_cost());

            root.put(CS_rank, getmRoster().getValue(isWithSkill()) / 10000);
            root.put(CS_reroll, getmRoster().getRerolls());
            if (getmRoster().getRoster() != null) {
                root.put(CS_reroll_price, getmRoster().getRoster().getReroll_cost());
            } else {
                root.put(CS_reroll_price, 0);
            }
            root.put(CS_reroll_cost, getmRoster().getRerolls() * RosterType.getCheerleader_cost());

            root.put(CS_pop, getmRoster().getRerolls());
            root.put(CS_pop_price, RosterType.getFan_factor_cost());
            root.put(CS_pop_cost, getmRoster().getFanfactor() * RosterType.getFan_factor_cost());

            ArrayList<HashMap<String, Object>> inducements = new ArrayList<>();
             for (int i = 0; i < this.mRoster.getInducementsSize(); i++) {
                 Inducement induc=mRoster.getInducement(i);
                 HashMap<String, Object> inducement=new HashMap<>();
                 
                 inducement.put(CS_name, StringEscapeUtils.escapeHtml4(Translate.translate(induc.getType().getName())));
                 inducement.put(CS_nb, induc.getNb());
                 inducement.put(CS_cost, induc.getType().getCost());
                 inducement.put(CS_total, induc.getType().getCost()*induc.getNb());
                 
                 inducements.add(inducement);
             }
             
            root.put(CS_inducements,inducements);

            root.put(CS_total, getmRoster().getValue(isWithSkill()));
            root.put(CS_rank, getmRoster().getValue(isWithSkill()) / 10000);

            // Pure translation
            root.put("RosterTitle",StringEscapeUtils.escapeHtml4(Translate.translate("RosterTitle")));
            root.put("NameTitle",StringEscapeUtils.escapeHtml4(Translate.translate("NameTitle")));
            root.put("PositionTitle",StringEscapeUtils.escapeHtml4(Translate.translate("PositionTitle")));
            root.put("MTitle",StringEscapeUtils.escapeHtml4(Translate.translate("MTitle")));
            root.put("STitle",StringEscapeUtils.escapeHtml4(Translate.translate("STitle")));
            root.put("AgTitle",StringEscapeUtils.escapeHtml4(Translate.translate("AgTitle")));
            root.put("ArTitle",StringEscapeUtils.escapeHtml4(Translate.translate("ArTitle")));
            root.put("SkillsTitle",StringEscapeUtils.escapeHtml4(Translate.translate("SkillsTitle")));
            root.put("CostTitle",StringEscapeUtils.escapeHtml4(Translate.translate("CostTitle")));
            root.put("SRTitle",StringEscapeUtils.escapeHtml4(Translate.translate("SRTitle")));
            root.put("DRTitle",StringEscapeUtils.escapeHtml4(Translate.translate("DRTitle")));
            root.put("TeamNameTitle",StringEscapeUtils.escapeHtml4(Translate.translate("TeamNameTitle")));
            root.put("ApothecaryTitle",StringEscapeUtils.escapeHtml4(Translate.translate("ApothecaryTitle")));
            root.put("CoachNameTitle",StringEscapeUtils.escapeHtml4(Translate.translate("CoachNameTitle")));
            root.put("AssistTitle",StringEscapeUtils.escapeHtml4(Translate.translate("AssistTitle")));
            root.put("RaceTitle",StringEscapeUtils.escapeHtml4(Translate.translate("RaceTitle")));
            root.put("CheerleadersTitle",StringEscapeUtils.escapeHtml4(Translate.translate("CheerleadersTitle")));
            root.put("FanFactorTitle",StringEscapeUtils.escapeHtml4(Translate.translate("FanFactorTitle")));
            root.put("ExtraRerollTitle",StringEscapeUtils.escapeHtml4(Translate.translate("ExtraRerollTitle")));
            root.put("LocalApothecaryTitle",StringEscapeUtils.escapeHtml4(Translate.translate("LocalApothecaryTitle")));
            root.put("IgorTitle",StringEscapeUtils.escapeHtml4(Translate.translate("IgorTitle")));
            root.put("BribeTitle",StringEscapeUtils.escapeHtml4(Translate.translate("BribeTitle")));
            root.put("WizardTitle",StringEscapeUtils.escapeHtml4(Translate.translate("WizardTitle")));
            root.put("BabesTitle",StringEscapeUtils.escapeHtml4(Translate.translate("BabesTitle")));
            root.put("ChefTitle",StringEscapeUtils.escapeHtml4(Translate.translate("ChefTitle")));
            root.put("CardsTitle",StringEscapeUtils.escapeHtml4(Translate.translate("CardsTitle")));
            root.put("TotalTitle",StringEscapeUtils.escapeHtml4(Translate.translate("TotalTitle")));
            root.put("RerollTitle",StringEscapeUtils.escapeHtml4(Translate.translate("RerollTitle")));
            root.put("RankTitle",StringEscapeUtils.escapeHtml4(Translate.translate("RankTitle")));
            
                       
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
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
                }
            }
        }
        return address;
    }

    private static final Logger LOG = Logger.getLogger(JdgPrintableRoster.class.getName());

    /**
     * @return the mRoster
     */
    private Roster getmRoster() {
        return mRoster;
    }

    /**
     * @return the mCoach
     */
    private Coach getmCoach() {
        return mCoach;
    }

        /**
     * @return the mFilename
     */
    private File getmFilename() {
        return mFilename;
    }

    /**
     * @return the mWithSkill
     */
    private boolean isWithSkill() {
        return mWithSkill;
    }

}
