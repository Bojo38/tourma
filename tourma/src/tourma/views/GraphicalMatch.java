/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Match;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.utils.ImageTreatment;

/**
 *
 * @author WFMJ7631
 */
public class GraphicalMatch extends javax.swing.JPanel {

    Match match;

    /**
     *
     */
    public JLabel clanIcon1=null;

    /**
     *
     */
    public JLabel clanIcon2=null;
    
    
    /**
     * Creates new form GraphicalMatch
     */
    public GraphicalMatch(Match m, boolean odd,int width ) {
        initComponents();
        match = m;
        this.setSize(getWidth(), 60);
        
        int computed_height=60;
        int computed_width=180;
        
        Color bkg = new Color(255, 255, 255);
        if (odd) {
            bkg = new Color(220, 220, 220);
        }
        
        this.setBorder(new LineBorder(new Color(200,200,200),1,false));

        int xOffset=0;
        GridBagLayout gbl=new GridBagLayout();
        this.setLayout(gbl);

        if (match instanceof CoachMatch) {
            //this.setSize(60, 400);
            CoachMatch cm = (CoachMatch) match;

            if (Tournament.getTournament().getClans().size() > 1) {
                JLabel ClanIcon1 = new JLabel();
                ClanIcon1.setSize(computed_width, computed_height);
                xOffset=computed_width;
                ClanIcon1.setLocation(1, 1);
                JLabel ClanIcon2 = new JLabel();
                ClanIcon2.setSize(computed_width, computed_height);
                ClanIcon2.setLocation(width-computed_width-1, 1);
                Clan clan1 = ((Coach) (cm.mCompetitor1)).mClan;
                if (clan1.getPicture() != null) {
                    ClanIcon1.setIcon(ImageTreatment.resize(new ImageIcon(clan1.getPicture()), computed_height,computed_height));
                }
                ClanIcon1.setText(clan1.getName());
                Clan clan2 = ((Coach) (cm.mCompetitor2)).mClan;
                if (clan2.getPicture() != null) {
                    ClanIcon2.setIcon(ImageTreatment.resize(new ImageIcon(clan2.getPicture()), computed_height,computed_height));
                }
                ClanIcon2.setText(clan2.getName());
                ClanIcon1.setOpaque(true);
                ClanIcon1.setBackground(bkg);
                ClanIcon2.setOpaque(true);
                ClanIcon2.setBackground(bkg);
            }

            JLabel CoachIcon1 = new JLabel();
            CoachIcon1.setSize(computed_width, computed_height);
            CoachIcon1.setLocation(xOffset+1,1);
            JLabel CoachIcon2 = new JLabel();
            CoachIcon2.setSize(computed_width, computed_height);
            CoachIcon2.setLocation(width-xOffset-computed_width-1, 1);
            if (cm.mCompetitor1.picture != null) {
                CoachIcon1.setIcon(ImageTreatment.resize(new ImageIcon(cm.mCompetitor1.picture), computed_height,computed_height));
            }
            CoachIcon1.setText(cm.mCompetitor1.mName);
            if (cm.mCompetitor2.picture != null) {
                CoachIcon2.setIcon(ImageTreatment.resize(new ImageIcon(cm.mCompetitor2.picture), computed_height,computed_height));
            }
            CoachIcon2.setText(cm.mCompetitor2.mName);
            CoachIcon1.setBackground(bkg);
            CoachIcon1.setOpaque(true);
            CoachIcon2.setBackground(bkg);
            CoachIcon2.setOpaque(true);
            this.add(CoachIcon1);
            this.add(CoachIcon2);

        }
        if (match instanceof TeamMatch) {
            TeamMatch tm = (TeamMatch) match;
            this.setSize(80 * tm.mMatchs.size() + 80, 400);
        }
        
        this.setPreferredSize(new Dimension(width,computed_height));
        this.setSize(new Dimension(width,computed_height));
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(400, 150));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(GraphicalMatch.class.getName());
}
