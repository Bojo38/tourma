/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.parameters;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.rmi.RemoteException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import tourma.data.Category;
import tourma.data.Tournament;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public final class JPNParamCategories extends javax.swing.JPanel {

    private Tournament mTournament;

    /**
     * Creates new form JPNParamCategory
     */
    public JPNParamCategories() {
        mTournament = null;

            mTournament = Tournament.getTournament();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    @SuppressFBWarnings({"SIC"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jbtAddCategory = new javax.swing.JButton();
        jbtEditCategory = new javax.swing.JButton();
        jbtRemoveCategory = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlsCategories = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jlsCoachList = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlsTeamList = new javax.swing.JList();

        setLayout(new java.awt.BorderLayout());

        jbtAddCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtAddCategory.setText(bundle.getString("Add")); // NOI18N
        jbtAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddCategoryActionPerformed(evt);
            }
        });
        jPanel13.add(jbtAddCategory);

        jbtEditCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Swap.png"))); // NOI18N
        jbtEditCategory.setText(bundle.getString("Edit")); // NOI18N
        jbtEditCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditCategoryActionPerformed(evt);
            }
        });
        jPanel13.add(jbtEditCategory);

        jbtRemoveCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemoveCategory.setText(bundle.getString("Remove")); // NOI18N
        jbtRemoveCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveCategoryActionPerformed(evt);
            }
        });
        jPanel13.add(jbtRemoveCategory);

        add(jPanel13, java.awt.BorderLayout.SOUTH);

        jPanel14.setLayout(new java.awt.GridLayout(1, 2));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Categories"))); // NOI18N

        jlsCategories.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlsCategories.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlsCategories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlsCategoriesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jlsCategories);

        jPanel14.add(jScrollPane3);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("CategoriesCoachsKey"))); // NOI18N

        jlsCoachList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlsCoachList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jlsCoachList);

        jPanel14.add(jScrollPane4);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("TeamofCategory"))); // NOI18N

        jlsTeamList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlsTeamList);

        jPanel14.add(jScrollPane1);

        add(jPanel14, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddCategoryActionPerformed

        final String enterCategoryName = Translate.translate(CS_EnterCategoryNameKey);
        final String categoryName = JOptionPane.showInputDialog(this, enterCategoryName);
        if (categoryName != null) {
            if (!categoryName.equals(StringConstants.CS_NULL)) {
                Category cat = new Category(categoryName);
                    mTournament.addCategory(cat);
                Category.putCategory(categoryName, cat);
            }
        }
        update();
    }//GEN-LAST:event_jbtAddCategoryActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtEditCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditCategoryActionPerformed
        final String enterCategoryName
                = Translate.translate(CS_EnterCategoryNameKey);
        final String categoryName = (String) jlsCategories.getSelectedValue();
        final String newCategoryName = JOptionPane.showInputDialog(this, enterCategoryName, categoryName);
        if (!newCategoryName.equals(StringConstants.CS_NULL)) {
                Category cat = mTournament.getCategory(jlsCategories.getSelectedIndex());
                cat.setmName(newCategoryName);
                Category.delCategory(categoryName);
                Category.putCategory(newCategoryName, cat);
        }
        update();
    }//GEN-LAST:event_jbtEditCategoryActionPerformed

    private final static String CS_EnterCategoryNameKey = "EnterCategoryNameKey";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveCategoryActionPerformed
        final int index = jlsCategories.getSelectedIndex();

        if (index >= 0) {
                Category cat = mTournament.getCategory(index);
                Category.delCategory(cat.getName());
                mTournament.removeCategory(index);
                jlsCategories.setSelectedIndex(index - 1);
        }
        update();
    }//GEN-LAST:event_jbtRemoveCategoryActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jlsCategoriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlsCategoriesMouseClicked
        int index = jlsCategories.getSelectedIndex();
        update();
        jlsCategories.setSelectedIndex(index);
    }//GEN-LAST:event_jlsCategoriesMouseClicked

    /**
     * Update panel contant
     */
    public void update() {

        final int selectedCategory = jlsCategories.getSelectedIndex();
        final DefaultListModel coachListModel = new DefaultListModel();
            if ((selectedCategory >= 0) && (mTournament.getCategoriesCount() > selectedCategory)) {
                final String categName = mTournament.getCategory(selectedCategory).getName();
                Category cat = Category.getCategory(categName);
                for (int i = 0; i < mTournament.getCoachsCount(); i++) {
                    if (mTournament.getCoach(i).containsCategory(cat)) {
                        coachListModel.addElement(mTournament.getCoach(i).getName());
                    }
                }
            }

        jlsCoachList.setModel(coachListModel);

            if (Tournament.getTournament().getParams().isTeamTournament()) {
                jlsTeamList.setEnabled(true);
                final DefaultListModel teamListModel = new DefaultListModel();
                if ((selectedCategory >= 0) && (mTournament.getCategoriesCount() > selectedCategory)) {
                    final String categName = mTournament.getCategory(selectedCategory).getName();
                    Category cat = Category.getCategory(categName);
                    for (int i = 0; i < mTournament.getTeamsCount(); i++) {
                        if (mTournament.getTeam(i).containsCategory(cat)) {
                            teamListModel.addElement(mTournament.getTeam(i).getName());
                        }
                    }
                }
                jlsTeamList.setModel(teamListModel);
            } else {
                jlsTeamList.setEnabled(false);
                final DefaultListModel teamListModel = new DefaultListModel();
                jlsTeamList.setModel(teamListModel);
            }
        
        final DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < mTournament.getCategoriesCount(); i++) {
            listModel.addElement(mTournament.getCategory(i).getName());
        }

        jlsCategories.setModel(listModel);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jbtAddCategory;
    private javax.swing.JButton jbtEditCategory;
    private javax.swing.JButton jbtRemoveCategory;
    private javax.swing.JList jlsCategories;
    private javax.swing.JList jlsCoachList;
    private javax.swing.JList jlsTeamList;
    // End of variables declaration//GEN-END:variables

}
