/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * JdgCoach.java
 *
 * Created on 10 mai 2010, 19:37:53
 */
package tourma;

import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import tourma.data.Category;
import tourma.data.Coach;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtCoaches;
import tourma.utility.ExtensionFileFilter;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public final class JdgTeam extends javax.swing.JDialog {

    private boolean mTeamTournament;
    private Team mTeam;
    private Tournament mTour;

    boolean newTeam=false;
    
    /**
     *
     * @param parent
     * @param modal
     */
    @SuppressWarnings("unchecked")
    public JdgTeam(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();

        newTeam=true;
        mTour = Tournament.getTournament();
        mTeam = new Team();

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();

        final int screenWidth = dmode.getWidth();
        final int screenHeight = dmode.getHeight();
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

        if (mTeam.getPicture() == null) {
            try {
                java.net.URL url = getClass().getResource("/tourma/images/flags/Country France.png");
                if (url != null) {
                    BufferedImage buf = ImageIO.read(url);
                    if (buf != null) {
                        mTeam.setPicture(new ImageIcon(buf));
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(JdgCoach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jbtAvatar.setIcon(resize(mTeam.getPicture(), 80, 80));

        final DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            clanListModel.addElement(Tournament.getTournament().getClan(i).getName());
        }
        jcbClan.setModel(clanListModel);

        if ((Tournament.getTournament().getParams().isEnableClans()) && (Tournament.getTournament().getClansCount() > 1)) {
            jcbClan.setEnabled(true);
            jLabel2.setEnabled(true);
        } else {
            jcbClan.setEnabled(false);
            jLabel2.setEnabled(false);
        }
        update();
    }

    /**
     *
     * @param parent
     * @param modal
     * @param team
     */
    @SuppressWarnings("unchecked")
    public JdgTeam(final java.awt.Frame parent, final boolean modal, final Team team) {
        super(parent, modal);
        initComponents();

        mTour = Tournament.getTournament();
        mTeam = team;

        newTeam=false;
        jtbCoachs.setModel(new MjtCoaches(mTeam));

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();

        final int screenWidth = dmode.getWidth();
        final int screenHeight = dmode.getHeight();
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

        if (mTeam.getPicture() == null) {
            mTeam.setPicture(new ImageIcon(new BufferedImage(96, 96, BufferedImage.TYPE_4BYTE_ABGR)));
        }

        final DefaultComboBoxModel clanListModel = new DefaultComboBoxModel();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            clanListModel.addElement(Tournament.getTournament().getClan(i).getName());
        }
        jcbClan.setModel(clanListModel);

        if ((Tournament.getTournament().getParams().isEnableClans()) && (Tournament.getTournament().getClansCount() > 1)) {
            jcbClan.setEnabled(true);
            if (team.getClan() != null) {
                jcbClan.setSelectedItem(team.getClan().getName());
            }
        }

        jbtAvatar.setIcon(resize(mTeam.getPicture(), 80, 80));
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

        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jpnCoachButtons = new javax.swing.JPanel();
        jbtAdd = new javax.swing.JButton();
        jbtRemove = new javax.swing.JButton();
        jbtModify = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbCoachs = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jbtAvatar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfNom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jcbClan = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jpnBtns1 = new javax.swing.JPanel();
        jbtAddCategory = new javax.swing.JButton();
        jbtDelCategory = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlsCategories = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel2.add(jbtOK);

        jbtCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Halt.png"))); // NOI18N
        jbtCancel.setText(bundle.getString("Cancel")); // NOI18N
        jbtCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelActionPerformed(evt);
            }
        });
        jPanel2.add(jbtCancel);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Coachs"))); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(450, 240));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jbtAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAdd.setText(bundle.getString("Add")); // NOI18N
        jbtAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddActionPerformed(evt);
            }
        });
        jpnCoachButtons.add(jbtAdd);

        jbtRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemove.setText(bundle.getString("Remove")); // NOI18N
        jbtRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveActionPerformed(evt);
            }
        });
        jpnCoachButtons.add(jbtRemove);

        jbtModify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Swap.png"))); // NOI18N
        jbtModify.setText(bundle.getString("Modify")); // NOI18N
        jbtModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtModifyActionPerformed(evt);
            }
        });
        jpnCoachButtons.add(jbtModify);

        jPanel7.add(jpnCoachButtons, java.awt.BorderLayout.PAGE_START);

        jtbCoachs.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbCoachs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jtbCoachs);

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jbtAvatar.setMnemonic('A');
        jbtAvatar.setPreferredSize(new java.awt.Dimension(80, 80));
        jbtAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAvatarActionPerformed(evt);
            }
        });
        jPanel3.add(jbtAvatar, java.awt.BorderLayout.WEST);

        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText(bundle.getString("TeamNameKey")); // NOI18N
        jPanel1.add(jLabel1);
        jPanel1.add(jtfNom);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText(bundle.getString("ClanNameKey")); // NOI18N
        jPanel1.add(jLabel2);

        jPanel1.add(jcbClan);

        jPanel3.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Category"))); // NOI18N
        jPanel5.setLayout(new java.awt.BorderLayout());

        jpnBtns1.setLayout(new java.awt.GridLayout(3, 1, 1, 1));

        jbtAddCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddCategoryActionPerformed(evt);
            }
        });
        jpnBtns1.add(jbtAddCategory);

        jbtDelCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtDelCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDelCategoryActionPerformed(evt);
            }
        });
        jpnBtns1.add(jbtDelCategory);

        jPanel5.add(jpnBtns1, java.awt.BorderLayout.WEST);

        jlsCategories.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jlsCategories);

        jPanel5.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel3, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelActionPerformed

        /*
        If new team remove added coaches
        */
        if (newTeam)
        {
            for (int i=0; i<mTeam.getCoachsCount(); i++)
            {
                Coach C=mTeam.getCoach(i);
                mTour.removeCoach(C);
                mTeam.removeCoach(i);
            }            
        }
        this.setVisible(false);
    }//GEN-LAST:event_jbtCancelActionPerformed

    private final static String CS_EmptyTeamName = "NOM DE L'ÉQUIPE VIDE";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed

        if (jtfNom.getText().equals(StringConstants.CS_NULL)) {
            JOptionPane.showMessageDialog(this,
                    Translate.translate(CS_EmptyTeamName)
            );
        } else {

            mTeam.setName(jtfNom.getText());

            if (mTour.getParams().isEnableClans()) {
                if (jcbClan.getSelectedIndex() > 0) {
                    mTeam.setClan(Tournament.getTournament().getClan(jcbClan.getSelectedIndex()));
                } else {
                    mTeam.setClan(Tournament.getTournament().getClan(0));
                }
            }

            if (!mTour.containsTeam(mTeam)) {
                mTour.addTeam(mTeam);
            }
            this.setVisible(false);
        }

    }//GEN-LAST:event_jbtOKActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddActionPerformed

        final JdgCoach w = new JdgCoach(MainFrame.getMainFrame(), true, mTeam);
        w.setVisible(true);

        update();
}//GEN-LAST:event_jbtAddActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveActionPerformed
        mTour.removeCoach(mTeam.getCoach(jtbCoachs.getSelectedRow()));
        mTeam.removeCoach(jtbCoachs.getSelectedRow());

        update();
}//GEN-LAST:event_jbtRemoveActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModifyActionPerformed

        if (jtbCoachs.getSelectedRow() >= 0) {
            final JdgCoach w = new JdgCoach(MainFrame.getMainFrame(), true, mTeam.getCoach(jtbCoachs.getSelectedRow()));
            w.setVisible(true);
            update();
        }
}//GEN-LAST:event_jbtModifyActionPerformed

    private ImageIcon resize(ImageIcon image, int heigth, int width) {
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    private static final String CS_SelectPicture = "Select picture";
    private static final String CS_Picture = "Picture";

    public List<ImageIcon> getImagesResources(final String path) throws IOException {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ArrayList<ImageIcon> list = new ArrayList<>();
        final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        if (jarFile == null) {
            System.out.println("Jarfile is null: " + getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        } else {
            System.out.println("Open JAR File: " + getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        }

        if (jarFile.isFile()) {  // Run with JAR file
            final JarFile jar = new JarFile(jarFile);
            final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
            while (entries.hasMoreElements()) {
                final String name = entries.nextElement().getName();
                //System.out.println("Open JAR File: "+name);
                String pathToTest;
                if (path.startsWith("/")) {
                    pathToTest = path.subSequence(1, path.length()).toString();
                } else {
                    pathToTest = path;
                }
                // System.out.println("Tests "+name+" starts with "+pathToTest);
                if (name.startsWith(pathToTest + "/")) { //filter according to the path
                    BufferedImage bi = ImageIO.read(getClass().getResource("/tourma/images/flags/Country France.png"));
                    bi = ImageIO.read(getClass().getResource("/" + name));
                    if (bi == null) {
                        System.out.println("/" + name + " returns a null image");
                    } else {
                        ImageIcon ii = new ImageIcon(bi);
                        list.add(ii);
                    }
                }
            }
            jar.close();
        } else { // Run with IDE
            try {
                final URL url = getClass().getResource(path).toURI().toURL();
                if (url != null) {
                    try {
                        final File apps = new File(url.toURI());
                        for (File app : apps.listFiles()) {
                            ImageIcon icon = new ImageIcon(app.getAbsolutePath());
                            list.add(icon);
                        }
                    } catch (URISyntaxException ex) {
                        // never happens
                    }
                }
            } catch (URISyntaxException ex) {
                LOG.log(Level.WARNING, ex.getLocalizedMessage());
            }
        }
        return list;
    }


    private void jbtAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAvatarActionPerformed
        try {
            List<ImageIcon> listOfFiles = getImagesResources("/tourma/images/flags");

            Object[] objects = new Object[listOfFiles.size() + 1];
            for (int i = 0; i < listOfFiles.size(); i++) {
                if (listOfFiles.get(i) != null) {
                    objects[i] = resize(listOfFiles.get(i), 80, 80);
                }
            }
            ImageIcon empty = new ImageIcon();
            objects[listOfFiles.size()] = empty;

            JComboBox<Object> combo = new JComboBox<>(objects);
            JPanel panel = new JPanel(new BorderLayout());
            JLabel l = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SelectPicture"));
            panel.add(l, BorderLayout.NORTH);
            panel.add(combo, BorderLayout.CENTER);

            combo.setSelectedItem(empty);

            JOptionPane.showConfirmDialog(null, panel, null, JOptionPane.YES_OPTION);

            if (combo.getSelectedItem() == empty) {
                final JFileChooser jfc = new JFileChooser();
                final FileFilter filter1 = new ExtensionFileFilter(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Picture"), new String[]{"PNG", "png", "JPG", "jpg", "GIF", "gif"});
                jfc.setFileFilter(filter1);
                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ImageIcon icon = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
                    icon = resize(icon, 80, 80);
                    this.mTeam.setPicture(icon);
                    mTeam.setUpdated(true);
                }
            } else {
                ImageIcon icon = (ImageIcon) combo.getSelectedItem();
                this.mTeam.setPicture(icon);
                mTeam.setUpdated(true);
            }

            jbtAvatar.setIcon(mTeam.getPicture());
        } catch (IOException use) {
            LOG.log(Level.WARNING, use.getLocalizedMessage());
        }
    }//GEN-LAST:event_jbtAvatarActionPerformed

    private static final String CS_PleaseSelectCategory = "PleaseSelectCategory";

    private void jbtAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddCategoryActionPerformed
        ArrayList<String> cats = new ArrayList<>();
        cats.add(" ");

        for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
            Category cat = Tournament.getTournament().getCategory(i);
            if (!mTeam.containsCategory(cat)) {
                cats.add(cat.getName());
            }
        }
        JComboBox jcb = new JComboBox(cats.toArray());
        jcb.setEditable(true);
        JOptionPane.showMessageDialog(this, jcb, Translate.translate(CS_PleaseSelectCategory), JOptionPane.QUESTION_MESSAGE);

        if (jcb.getSelectedIndex() > 0) {
            String name = cats.get(jcb.getSelectedIndex());
            Category cat = Category.getCategory(name);
            mTeam.addCategory(cat);
            mTeam.setUpdated(true);
        }

        update();

    }//GEN-LAST:event_jbtAddCategoryActionPerformed

    private void jbtDelCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDelCategoryActionPerformed

        List selection = jlsCategories.getSelectedValuesList();
        for (Object o : selection) {
            if (o instanceof String) {

                String name = (String) o;
                Category cat = Category.getCategory(name);
                mTeam.delCategory(cat);
                mTeam.setUpdated(true);
            }
        }

        update();
    }//GEN-LAST:event_jbtDelCategoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtAddCategory;
    private javax.swing.JButton jbtAvatar;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtDelCategory;
    private javax.swing.JButton jbtModify;
    private javax.swing.JButton jbtOK;
    private javax.swing.JButton jbtRemove;
    private javax.swing.JComboBox jcbClan;
    private javax.swing.JList jlsCategories;
    private javax.swing.JPanel jpnBtns1;
    private javax.swing.JPanel jpnCoachButtons;
    private javax.swing.JTable jtbCoachs;
    private javax.swing.JTextField jtfNom;
    // End of variables declaration//GEN-END:variables

    /**
     * Update hmi
     */
    @SuppressWarnings("unchecked")
    private void update() {
        jtfNom.setText(mTeam.getName());
        jtbCoachs.setModel(new MjtCoaches(mTeam));

        if (mTeam.getCoachsCount() < mTour.getParams().getTeamMatesNumber()) {
            jbtOK.setEnabled(false);
        }

        if (mTeam.getCoachsCount() >= mTour.getParams().getTeamMatesNumber()) {
            jbtOK.setEnabled(true);
        }

        if ((mTeam.getCoachsCount() == mTour.getParams().getTeamMatesNumber()) && (!mTour.getParams().isSubstitutes())) {
            jbtAdd.setEnabled(false);
        } else {
            jbtAdd.setEnabled(true);
        }

        final DefaultListModel categoryListModel = new DefaultListModel();
        for (int i = 0; i < this.mTeam.getCategoryCount(); i++) {
            categoryListModel.addElement(mTeam.getCategory(i).getName());
        }
        jlsCategories.setModel(categoryListModel);

        jbtAddCategory.setEnabled(Tournament.getTournament().getCategoriesCount() > 0);
        jbtDelCategory.setEnabled(mTeam.getCategoryCount() > 0);

    }
    private static final Logger LOG = Logger.getLogger(JdgTeam.class.getName());

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
