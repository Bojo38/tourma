/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Logger;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.views.round.JPNMatch;

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
        final ArrayList<Round> rounds_with_cup = new ArrayList<>();
        //final ArrayList<Round> rounds = Tournament.getTournament().getRounds();
        boolean bLooserCup = false;
        try {
            for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
                Round round = Tournament.getTournament().getRound(i);
                if (round.isCup()) {
                    rounds_with_cup.add(round);
                    if (round.isLooserCup()) {
                        bLooserCup = true;
                    }
                }
            }

            jpnCup.removeAll();
            final int max_width = rounds_with_cup.size() * 200;
            int max_heigth = (int) Math.pow(2, rounds_with_cup.size()) * 150;
            if (bLooserCup) {
                max_heigth += max_heigth / 2;
            }
            jpnCup.setSize(max_width, max_heigth);

            final int max_nb_match = (int) Math.pow(2, rounds_with_cup.get(0).getCupMaxTour() - 1);
            final int base_high = 60;
            final int total_high = 60 * max_nb_match;

            int gap = 0;
            int nb_looseMatch = 0;
            int last_offset;
            int offset = 0;
            for (int i = 0; i < rounds_with_cup.size(); i++) {
                final Round r = rounds_with_cup.get(i);
                final int remaining_tour = r.getCupMaxTour() - r.getCupTour() + 1;
                int nb_match = (int) Math.pow(2, remaining_tour - 1) / 2;
                if (nb_match == 0) {
                    nb_match = 1;
                }
                final int last_gap = gap;
                gap = total_high / nb_match;
                last_offset = offset;
                offset = (gap - base_high) / 2 + 5;
                final int w = 175;
                final int h = 50;
                int x = i * 200 + 5;

                //final Tournament tour = Tournament.getTournament();
                for (int j = 0; j < nb_match; j++) {
                    Match m;
                    m = r.getMatch(j);

                    final JPNMatch match = new JPNMatch(m, true);
                    match.setSize(175, 50);
                    //int y = j * gap + offset * 75 / 2 + 5;                
                    final int y = j * gap + offset;
                    jpnCup.add(match, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, w, h));

                }

                if (r.isThirdPlace()) {
                    Match m;
                    m = r.getMatch(nb_match);

                    final JPNMatch match = new JPNMatch(m, true);
                    match.setSize(175, 50);
                    //int y = j * gap + offset * 75 / 2 + 5;                
                    final int y = (nb_match - 1) * gap + offset + 55;
                    jpnCup.add(match, new org.netbeans.lib.awtextra.AbsoluteConstraints(x - 50, y, w, h));
                    nb_match++;
                }

                if (r.isLooserCup()) {
                    if (r.getCupTour() > 0) {
                        nb_looseMatch = nb_looseMatch / 2 + nb_match;

                        /* Check the maximum round for looser cup */
                        if (Math.round(Math.pow(2, i - 1) / 2) == rounds_with_cup.get(0).getMatchsCount()) {
                            /* We are at maximum looser cup round */
                            nb_looseMatch = 1;
                        }
                        if (Math.pow(2, i - 1) / 2 > rounds_with_cup.get(0).getMatchsCount()) {
                            /* We are at maximum looser cup round */
                            nb_looseMatch = 0;
                        }

                        int factor = 1;
                        for (int j = nb_match; (j < nb_match + nb_looseMatch) && (j < r.getMatchsCount()); j++) {
                            Match m;

                            m = r.getMatch(j);

                            final JPNMatch match = new JPNMatch(m, false);
                            match.setSize(175, 50);
                            final int y = (j - nb_match) * last_gap + last_offset + 30 + total_high;
                            x = (i - 1) * 200 + 5;
                            jpnCup.add(match, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, w, h));

                            // Draw line between the previous matches end this one
                        }
                    }
                }
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }
    private static final Logger LOG = Logger.getLogger(JPNCup.class.getName());
}
