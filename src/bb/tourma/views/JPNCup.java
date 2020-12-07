/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.views;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import bb.tourma.data.CupRound;
import bb.tourma.data.CupTable;
import bb.tourma.data.Match;
import bb.tourma.data.Round;
import bb.tourma.data.Tournament;
import bb.tourma.views.round.JPNMatch;

/**
 *
 * @author WFMJ7631
 */
public final class JPNCup extends javax.swing.JPanel {

    /**
     * Creates new form JPNCup
     */
    public JPNCup() {
        initComponents();

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

        jsp1 = new javax.swing.JScrollPane();
        jpnCup = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jpnCup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpnCup.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jsp1.setViewportView(jpnCup);

        add(jsp1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpnCup;
    private javax.swing.JScrollPane jsp1;
    // End of variables declaration//GEN-END:variables

    /**
     * Update panel
     */
    public void update() {

        jpnCup.removeAll();

        /*int nbTables = Tournament.getTournament().getCup().getTables().size();
        int nbRounds=Tournament.getTournament().getCup().getRoundsCount();*/
        
        jpnCup.setLayout(new BoxLayout(jpnCup,BoxLayout.Y_AXIS));

        for (CupTable table : Tournament.getTournament().getCup().getTables()) {
            // Compute High of this table Part
            JPanel jpnTab = new JPanel(new FlowLayout(FlowLayout.CENTER));
            // @TODO compute W starting offset & gap for this round
            for (int i = table.getCupRounds().size() - 1; i >= 0; i--) {
                CupRound round = table.getCupRounds().get(i);
                JPanel jpn = new JPanel(new GridLayout(round.getNbMatchs(), 1));
              
                for (int j = 0; j < round.getNbMatchs(); j++) {
                    Match match = round.getMatchs().get(j);

                    // If the match is empty, draw it empty
                    if ((match.getCompetitor1() != null) && (match.getCompetitor2() != null)) {
                        if ((!match.getCompetitor1().getName().equals("")) && (!match.getCompetitor2().getName().equals(""))) {
                            // Find real match in the rounds
                            // Build Graphical panel
                            // Draw it
                            for (int cpt_r = 0; cpt_r < Tournament.getTournament().getRoundsCount(); cpt_r++) {
                                Round r = Tournament.getTournament().getRound(cpt_r);

                                for (int cpt_m = 0; cpt_m < r.getMatchsCount(); cpt_m++) {
                                    Match m = r.getMatch(cpt_m);
                                    if ((m.getCompetitor1() == match.getCompetitor1()) && (m.getCompetitor2() == match.getCompetitor2())) {
                                        JPNMatch g_match = new JPNMatch(m, true);
                                        jpn.add(g_match);
                                    }
                                }
                            }                            
                        }
                    }
                }               
                jpnTab.add(jpn);
            }
 
            jpnCup.add(jpnTab);
            jpnCup.add(new JSeparator(JSeparator.HORIZONTAL));
        }
    }
    private static final Logger LOG = Logger.getLogger(JPNCup.class.getName());
}
