/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import teamma.data.Player;
import teamma.data.SkillType;
import teamma.data.Skill;
import teamma.data.lrb;
import tourma.MainFrame;

/**
 *
 * @author Perso
 */
public class JdgSelectSkill extends javax.swing.JDialog {

    Player _player;
    Vector<JComboBox> _jcbs;
    Color _color=Color.BLACK;

    /**
     * Creates new form JdgSelectSkill
     */
    public JdgSelectSkill(java.awt.Frame parent, boolean modal, Player player) {
        super(parent, modal);
        initComponents();

        _jcbs = new Vector<JComboBox>();
        int nbcats = lrb.getLRB()._skillTypes.size();
        _player = player;

        GridLayout layout = new GridLayout(nbcats, 3);
        jpnSkills.setLayout(layout);
        int i;
        int j;
        for (i = 0; i < nbcats; i++) {
            SkillType st = lrb.getLRB()._skillTypes.get(i);
            JLabel jlb = new JLabel(st._name);
            jlb.setHorizontalAlignment(JLabel.TRAILING);
            Vector<String> sa = new Vector<String>();
            sa.add("");
            for (j = 0; j < st._skills.size(); j++) {
                sa.add(st._skills.get(j)._name);
            }
            JComboBox jcb = new JComboBox(sa.toArray());
            _jcbs.add(jcb);
            jcb.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int i;
                    for (i = 0; i < _jcbs.size(); i++) {
                        JComboBox jcb= _jcbs.get(i);
                        if (!e.getSource().equals(jcb))
                        {
                            jcb.setSelectedIndex(0);
                        }
                    }
                }
            });

            jpnSkills.add(jlb);
            jpnSkills.add(jcb);

            JLabel jlb2 = new JLabel("");
            boolean enabled = st._special && lrb.getLRB()._allowSpecialSkills;

            /* Get if Single Roll or doubl roll */
            if (_player._playertype._single.contains(st)) {
                jlb2.setText("Single Roll");
                enabled = true;
            }
            if (_player._playertype._double.contains(st)) {
                jlb2.setText("Double Roll");
                enabled = true;
            }

            jpnSkills.add(jlb2);
            jlb.setEnabled(enabled);
            jcb.setEnabled(enabled);
            jlb2.setEnabled(enabled);
        }

        this.setPreferredSize(new Dimension(400, 260));
        pack();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        this.setSize(400, 260);

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnSkills = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ccColor = new net.java.dev.colorchooser.ColorChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout jpnSkillsLayout = new javax.swing.GroupLayout(jpnSkills);
        jpnSkills.setLayout(jpnSkillsLayout);
        jpnSkillsLayout.setHorizontalGroup(
            jpnSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jpnSkillsLayout.setVerticalGroup(
            jpnSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );

        getContentPane().add(jpnSkills, java.awt.BorderLayout.CENTER);

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel2.add(jbtOK);

        jbtCancel.setText("Annuler");
        jbtCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelActionPerformed(evt);
            }
        });
        jPanel2.add(jbtCancel);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jLabel1.setText("Select Color :");
        jPanel1.add(jLabel1);

        ccColor.setColor(new java.awt.Color(0, 0, 0));
        ccColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ccColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ccColorLayout = new javax.swing.GroupLayout(ccColor);
        ccColor.setLayout(ccColorLayout);
        ccColorLayout.setHorizontalGroup(
            ccColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );
        ccColorLayout.setVerticalGroup(
            ccColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        jPanel1.add(ccColor);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtCancelActionPerformed

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        
        Skill s=null;
        int i;
        String stringName;
        for (i=0; i<_jcbs.size(); i++)
        {
            JComboBox<String> jcb=_jcbs.get(i);
            if (jcb.getSelectedIndex()>0)
            {
                stringName=(String)jcb.getSelectedItem();
                s=lrb.getLRB().getSkill(stringName);
                break;
            }
        }
        
        if (s!=null)
        {
            Skill s2=new Skill(s._name,s._category);
            s2._color=_color;
            _player._skills.add(s2);
            this.setVisible(false);
        }
        else
        {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),"Error","No skill selected",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtOKActionPerformed

    private void ccColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ccColorActionPerformed
        _color=ccColor.getColor();
    }//GEN-LAST:event_ccColorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private net.java.dev.colorchooser.ColorChooser ccColor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtOK;
    private javax.swing.JPanel jpnSkills;
    // End of variables declaration//GEN-END:variables
}