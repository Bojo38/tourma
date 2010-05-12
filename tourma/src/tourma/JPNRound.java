/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPNRound.java
 *
 * Created on 11 mai 2010, 14:13:53
 */

package tourma;

import java.util.Date;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Frederic Berger
 */
public class JPNRound extends javax.swing.JPanel {

    Round _round;
    Tournament _tournament;
    
    /** Creates new form JPNRound */
    public JPNRound(Round r, Tournament t) {
        initComponents();
        _round=r;
        _tournament=t;
        update();
    }

    public void update()
    {
        Date d=_round._heure;
        boolean locked=false;
        for (int i=0; i<_tournament._rounds.size();i++)
        {
            if (_tournament._rounds.get(i)._heure.after(d))
            {
                locked=true;
            }
        }
        mjtMatches model=new mjtMatches(_round._matchs, locked);
        jtbMatches.setModel(model);
        jtbMatches.setDefaultRenderer(String.class, model);
        jtbMatches.setDefaultRenderer(Integer.class, model);
        jtbMatches.setDefaultEditor(Integer.class, model);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtNextRound = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbMatches = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jbtNextRound.setLabel("Générer la prochaine ronde");
        jPanel1.add(jbtNextRound);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jtbMatches.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbMatches.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jtbMatches);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtNextRound;
    private javax.swing.JTable jtbMatches;
    // End of variables declaration//GEN-END:variables

}
