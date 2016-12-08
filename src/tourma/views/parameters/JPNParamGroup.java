/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.parameters;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.rmi.RemoteException;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import tourma.data.Group;
import tourma.data.GroupPoints;
import tourma.data.ITournament;
import tourma.data.RosterType;
import tourma.data.Tournament;
import tourma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public final class JPNParamGroup extends javax.swing.JPanel {

    private ITournament mTournament;

    /**
     * Creates new form JPNParamGroup
     */
    public JPNParamGroup() {

        try {
            mTournament = Tournament.getTournament();
        } catch (RemoteException re) {
            re.printStackTrace();
        }
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

        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jlsLeft = new javax.swing.JList();
        jcbGroupLeft = new javax.swing.JComboBox();
        jPanel22 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jbtGroupToRight = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jbtGrouToLeft = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jlsRight = new javax.swing.JList();
        jcbGroupRight = new javax.swing.JComboBox();
        jpnPointsModifiers = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcbPointsSelectedGroup = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jcbPointsOpponentGroup = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jftfGroupVictory = new javax.swing.JFormattedTextField();
        jftfGroupDraw = new javax.swing.JFormattedTextField();
        jftfGroupLoss = new javax.swing.JFormattedTextField();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jlsGroups = new javax.swing.JList();
        jPanel19 = new javax.swing.JPanel();
        jbtAddGroup = new javax.swing.JButton();
        jbtRemoveGroup = new javax.swing.JButton();
        jbtRenameGroup = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GroupEdition"))); // NOI18N
        jPanel20.setLayout(new java.awt.GridLayout(1, 3, 2, 0));

        jPanel21.setLayout(new java.awt.BorderLayout(2, 2));

        jlsLeft.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(jlsLeft);

        jPanel21.add(jScrollPane7, java.awt.BorderLayout.CENTER);

        jcbGroupLeft.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbGroupLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbGroupLeftActionPerformed(evt);
            }
        });
        jPanel21.add(jcbGroupLeft, java.awt.BorderLayout.PAGE_START);

        jPanel20.add(jPanel21);

        jPanel22.setLayout(new java.awt.GridLayout(5, 1));
        jPanel22.add(jPanel24);

        jbtGroupToRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Forward.png"))); // NOI18N
        jbtGroupToRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGroupToRightActionPerformed(evt);
            }
        });
        jPanel22.add(jbtGroupToRight);
        jPanel22.add(jLabel8);

        jbtGrouToLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Backward.png"))); // NOI18N
        jbtGrouToLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGrouToLeftActionPerformed(evt);
            }
        });
        jPanel22.add(jbtGrouToLeft);
        jPanel22.add(jLabel9);

        jPanel20.add(jPanel22);

        jPanel23.setLayout(new java.awt.BorderLayout(2, 2));

        jlsRight.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(jlsRight);

        jPanel23.add(jScrollPane8, java.awt.BorderLayout.CENTER);

        jcbGroupRight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbGroupRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbGroupRightActionPerformed(evt);
            }
        });
        jPanel23.add(jcbGroupRight, java.awt.BorderLayout.PAGE_START);

        jPanel20.add(jPanel23);

        add(jPanel20, java.awt.BorderLayout.CENTER);

        jpnPointsModifiers.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("PointsModifers"))); // NOI18N
        jpnPointsModifiers.setLayout(new java.awt.GridLayout(4, 3, 5, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(bundle.getString("SelectedGroup")); // NOI18N
        jpnPointsModifiers.add(jLabel1);
        jpnPointsModifiers.add(jLabel2);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(bundle.getString("OpponentGroup")); // NOI18N
        jpnPointsModifiers.add(jLabel3);

        jcbPointsSelectedGroup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbPointsSelectedGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPointsSelectedGroupActionPerformed(evt);
            }
        });
        jpnPointsModifiers.add(jcbPointsSelectedGroup);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(bundle.getString("vs")); // NOI18N
        jpnPointsModifiers.add(jLabel4);

        jcbPointsOpponentGroup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbPointsOpponentGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPointsOpponentGroupActionPerformed(evt);
            }
        });
        jpnPointsModifiers.add(jcbPointsOpponentGroup);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(bundle.getString("Victory")); // NOI18N
        jpnPointsModifiers.add(jLabel5);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(bundle.getString("Draw")); // NOI18N
        jpnPointsModifiers.add(jLabel6);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(bundle.getString("Loss")); // NOI18N
        jpnPointsModifiers.add(jLabel7);

        jftfGroupVictory.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jftfGroupVictory.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jftfGroupVictory.setText("0");
        jftfGroupVictory.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jftfGroupVictoryFocusLost(evt);
            }
        });
        jpnPointsModifiers.add(jftfGroupVictory);

        jftfGroupDraw.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jftfGroupDraw.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jftfGroupDraw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jftfGroupDrawFocusLost(evt);
            }
        });
        jpnPointsModifiers.add(jftfGroupDraw);

        jftfGroupLoss.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jftfGroupLoss.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jftfGroupLoss.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jftfGroupLossFocusLost(evt);
            }
        });
        jpnPointsModifiers.add(jftfGroupLoss);

        add(jpnPointsModifiers, java.awt.BorderLayout.SOUTH);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GroupList"))); // NOI18N
        jPanel18.setLayout(new java.awt.BorderLayout(1, 1));

        jlsGroups.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlsGroups.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(jlsGroups);

        jPanel18.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jPanel19.setLayout(new java.awt.GridLayout(3, 1, 1, 1));

        jbtAddGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAddGroup.setText(bundle.getString("Add")); // NOI18N
        jbtAddGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddGroupActionPerformed(evt);
            }
        });
        jPanel19.add(jbtAddGroup);

        jbtRemoveGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemoveGroup.setText(bundle.getString("Remove")); // NOI18N
        jbtRemoveGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveGroupActionPerformed(evt);
            }
        });
        jPanel19.add(jbtRemoveGroup);

        jbtRenameGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Swap.png"))); // NOI18N
        jbtRenameGroup.setText(bundle.getString("Rename")); // NOI18N
        jbtRenameGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRenameGroupActionPerformed(evt);
            }
        });
        jPanel19.add(jbtRenameGroup);

        jPanel18.add(jPanel19, java.awt.BorderLayout.LINE_END);

        add(jPanel18, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private static final String CS_EnterNewGroupName = "ENTREZ LE NOM DU NOUVEAU GROUPE";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddGroupActionPerformed
        final String newGroup = JOptionPane.showInputDialog(
                Translate.translate(CS_EnterNewGroupName));
        try {
            if (newGroup != null) {
                Group ng = new Group(newGroup);
                mTournament.addGroup(ng);

                for (int i = 0; i < mTournament.getGroupsCount(); i++) {
                    Group g = mTournament.getGroup(i);
                    GroupPoints gp = new GroupPoints();
                    g.setOpponentModificationPoints(ng, gp);
                }

                update();
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }//GEN-LAST:event_jbtAddGroupActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveGroupActionPerformed
        if (jlsGroups.getSelectedIndex() > 0) {
            try {
                //final ArrayList<RosterType> rosters = mTournament.getGroup(jlsGroups.getSelectedIndex()).getRosters();
                Group g = mTournament.getGroup(jlsGroups.getSelectedIndex());
                for (int i = 0; i < g.getRosterCount(); i++) {
                    mTournament.getGroup(0).addRoster(g.getRoster(i));
                }
                mTournament.removeGroup(jlsGroups.getSelectedIndex());

                for (int i = 0; i < mTournament.getGroupsCount(); i++) {
                    Group g2 = mTournament.getGroup(i);
                    g2.delOpponentModificationPoints(g);
                }
            } catch (RemoteException re) {
                re.printStackTrace();
            }
            update();
        }
    }//GEN-LAST:event_jbtRemoveGroupActionPerformed

    private final static String CS_EnterGroupNewName = "ENTREZ LE NOUVEAU NOM DU GROUPE";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRenameGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRenameGroupActionPerformed

        if (jlsGroups.getSelectedIndex() >= 0) {
            try {
                final String currentName = mTournament.getGroup(jlsGroups.getSelectedIndex()).getName();

                final String newGroup = JOptionPane.showInputDialog(
                        Translate.translate(CS_EnterGroupNewName),
                        currentName);
                if (newGroup != null) {
                    mTournament.getGroup(jlsGroups.getSelectedIndex()).setName(newGroup);
                    update();
                }
            } catch (RemoteException re) {
                re.printStackTrace();
            }
        }
    }//GEN-LAST:event_jbtRenameGroupActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbGroupLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbGroupLeftActionPerformed
        final DefaultListModel listModel = new DefaultListModel();
        //final ArrayList<RosterType> rosters = mTournament.getGroup(jcbGroupLeft.getSelectedIndex()).getRosters();
        try {
            Group g = mTournament.getGroup(jcbGroupLeft.getSelectedIndex());
            for (int i = 0; i < g.getRosterCount(); i++) {
                RosterType r = g.getRoster(i);
                if (r != null) {
                    listModel.addElement(r.getName());
                }
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        jlsLeft.setModel(listModel);
    }//GEN-LAST:event_jcbGroupLeftActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGroupToRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGroupToRightActionPerformed

        if (jlsLeft.getSelectedIndex() > -1) {
            try {
                final int index = jlsLeft.getSelectedIndex();
                Group g = mTournament.getGroup(jcbGroupLeft.getSelectedIndex());
                final RosterType roster = g.getRoster(index);
                g.removeRoster(roster);

                final DefaultListModel listModelLeft = new DefaultListModel();
                for (int i = 0; i < g.getRosterCount(); i++) {
                    listModelLeft.addElement(g.getRoster(i).getName());
                }
                jlsLeft.setModel(listModelLeft);

                final DefaultListModel listModelRight = new DefaultListModel();
                g = mTournament.getGroup(jcbGroupRight.getSelectedIndex());
                g.addRoster(roster);

                for (int i = 0; i < g.getRosterCount(); i++) {
                    listModelRight.addElement(g.getRoster(i).getName());
                }
                jlsRight.setModel(listModelRight);
            } catch (RemoteException re) {
                re.printStackTrace();
            }
        }
    }//GEN-LAST:event_jbtGroupToRightActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGrouToLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGrouToLeftActionPerformed
        if (jlsRight.getSelectedIndex() > -1) {
            try {
                final int index = jlsRight.getSelectedIndex();
                Group g = mTournament.getGroup(jcbGroupRight.getSelectedIndex());
                final RosterType roster = g.getRoster(index);
                g.removeRoster(roster);
                g.addRoster(roster);

                final DefaultListModel listModelRight = new DefaultListModel();
                for (int i = 0; i < g.getRosterCount(); i++) {
                    listModelRight.addElement(g.getRoster(i).getName());
                }
                jlsRight.setModel(listModelRight);

                final DefaultListModel listModelLeft = new DefaultListModel();

                //rosters = mTournament.getGroup(jcbGroupLeft.getSelectedIndex()).getRosters();
                g = mTournament.getGroup(jcbGroupLeft.getSelectedIndex());
                for (int i = 0; i < g.getRosterCount(); i++) {
                    listModelLeft.addElement(g.getRoster(i).getName());
                }
                jlsLeft.setModel(listModelLeft);
            } catch (RemoteException re) {
                re.printStackTrace();
            }
        }
    }//GEN-LAST:event_jbtGrouToLeftActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcbGroupRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbGroupRightActionPerformed
        final DefaultListModel listModel = new DefaultListModel();

        try {
            Group g = mTournament.getGroup(jcbGroupRight.getSelectedIndex());
            for (int i = 0; i < g.getRosterCount(); i++) {
                RosterType r = g.getRoster(i);
                if (r != null) {
                    listModel.addElement(r.getName());
                }
            }
            jlsRight.setModel(listModel);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }//GEN-LAST:event_jcbGroupRightActionPerformed

    private void jcbPointsSelectedGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPointsSelectedGroupActionPerformed
        updatePoints();


    }//GEN-LAST:event_jcbPointsSelectedGroupActionPerformed

    private void jcbPointsOpponentGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPointsOpponentGroupActionPerformed
        updatePoints();
    }//GEN-LAST:event_jcbPointsOpponentGroupActionPerformed

    private void jftfGroupVictoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jftfGroupVictoryFocusLost
        try {
            Group g = mTournament.getGroup(jcbPointsSelectedGroup.getSelectedIndex());
            Group go = mTournament.getGroup(jcbPointsOpponentGroup.getSelectedIndex());

            GroupPoints gp = g.getOpponentModificationPoints(go);
            try {
                jftfGroupVictory.commitEdit();
                final int points = ((Number) jftfGroupVictory.getValue()).intValue();
                gp.setVictoryPoints(points);
            } catch (ParseException e) {
                jftfGroupVictory.setValue(jftfGroupVictory.getValue());
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }

    }//GEN-LAST:event_jftfGroupVictoryFocusLost

    private void jftfGroupDrawFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jftfGroupDrawFocusLost
        try {
            Group g = mTournament.getGroup(jcbPointsSelectedGroup.getSelectedIndex());
            Group go = mTournament.getGroup(jcbPointsOpponentGroup.getSelectedIndex());

            GroupPoints gp = g.getOpponentModificationPoints(go);
            try {
                jftfGroupDraw.commitEdit();
                final int points = ((Number) jftfGroupDraw.getValue()).intValue();
                gp.setDrawPoints(points);
            } catch (ParseException e) {
                jftfGroupDraw.setValue(jftfGroupDraw.getValue());
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }//GEN-LAST:event_jftfGroupDrawFocusLost

    private void jftfGroupLossFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jftfGroupLossFocusLost
        try {
            Group g = mTournament.getGroup(jcbPointsSelectedGroup.getSelectedIndex());
            Group go = mTournament.getGroup(jcbPointsOpponentGroup.getSelectedIndex());

            GroupPoints gp = g.getOpponentModificationPoints(go);
            try {
                jftfGroupLoss.commitEdit();
                final int points = ((Number) jftfGroupLoss.getValue()).intValue();
                gp.setLossPoints(points);
            } catch (ParseException e) {
                jftfGroupLoss.setValue(jftfGroupLoss.getValue());
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }//GEN-LAST:event_jftfGroupLossFocusLost

    private void updatePoints() {
        try {
            int index = jcbPointsSelectedGroup.getSelectedIndex();
            int indexO = jcbPointsOpponentGroup.getSelectedIndex();
            if ((index >= 0) && (indexO >= 0)) {
                Group g = mTournament.getGroup(index);
                Group go = mTournament.getGroup(indexO);

                GroupPoints gp = g.getOpponentModificationPoints(go);
                if (gp == null) {
                    gp = new GroupPoints();
                    g.setOpponentModificationPoints(go, gp);
                }

                jftfGroupVictory.setValue(gp.getVictoryPoints());
                jftfGroupDraw.setValue(gp.getDrawPoints());
                jftfGroupLoss.setValue(gp.getLossPoints());
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    /**
     * Update Panel
     */
    public void update() {

        try {
            final DefaultListModel groupModel = new DefaultListModel();
            final DefaultComboBoxModel groupsLeftModel = new DefaultComboBoxModel();
            final DefaultComboBoxModel groupsRightModel = new DefaultComboBoxModel();
            final DefaultComboBoxModel groupsSelectedModel = new DefaultComboBoxModel();
            final DefaultComboBoxModel groupsOpponentModel = new DefaultComboBoxModel();
            for (int i = 0; i < mTournament.getGroupsCount(); i++) {
                groupModel.addElement(mTournament.getGroup(i).getName());
                groupsLeftModel.addElement(mTournament.getGroup(i).getName());
                groupsRightModel.addElement(mTournament.getGroup(i).getName());
                groupsSelectedModel.addElement(mTournament.getGroup(i).getName());
                groupsOpponentModel.addElement(mTournament.getGroup(i).getName());
            }
            jlsGroups.setModel(groupModel);
            jcbGroupLeft.setModel(groupsLeftModel);
            jcbGroupRight.setModel(groupsRightModel);
            jcbPointsSelectedGroup.setModel(groupsSelectedModel);
            jcbPointsOpponentGroup.setModel(groupsOpponentModel);

            if (mTournament.getGroupsCount() > 0) {
                jcbGroupLeft.setSelectedIndex(0);
                if (mTournament.getGroupsCount() > 1) {
                    jcbGroupRight.setSelectedIndex(1);
                } else {
                    jcbGroupRight.setSelectedIndex(0);
                }
                jcbPointsSelectedGroup.setSelectedIndex(0);
                if (mTournament.getGroupsCount() > 1) {
                    jcbPointsOpponentGroup.setSelectedIndex(1);
                } else {
                    jcbPointsOpponentGroup.setSelectedIndex(0);
                }
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        updatePoints();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JButton jbtAddGroup;
    private javax.swing.JButton jbtGrouToLeft;
    private javax.swing.JButton jbtGroupToRight;
    private javax.swing.JButton jbtRemoveGroup;
    private javax.swing.JButton jbtRenameGroup;
    private javax.swing.JComboBox jcbGroupLeft;
    private javax.swing.JComboBox jcbGroupRight;
    private javax.swing.JComboBox jcbPointsOpponentGroup;
    private javax.swing.JComboBox jcbPointsSelectedGroup;
    private javax.swing.JFormattedTextField jftfGroupDraw;
    private javax.swing.JFormattedTextField jftfGroupLoss;
    private javax.swing.JFormattedTextField jftfGroupVictory;
    private javax.swing.JList jlsGroups;
    private javax.swing.JList jlsLeft;
    private javax.swing.JList jlsRight;
    private javax.swing.JPanel jpnPointsModifiers;
    // End of variables declaration//GEN-END:variables

}
