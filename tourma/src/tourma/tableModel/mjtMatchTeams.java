/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
public class mjtMatchTeams extends AbstractTableModel implements TableCellRenderer {

    ArrayList<Team> mTeams;
    Round mRound;

    public mjtMatchTeams(final ArrayList<Team> teams, final Round round) {
        mTeams = teams;
        mRound = round;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return mRound.getMatchs().size();
    }

    @Override
    public String getColumnName(final int col) {
        String res = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        switch (col) {
            case 0:
                res = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("#");
                break;
            case 1:
                res = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team1");
                break;
            case 2:
                res = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("V1");
                break;
            case 3:
                res = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("N");
                break;
            case 4:
                res = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("V2");
                break;
            case 5:
                res = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team2");
                break;
            default:
        }
        return res;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object obj = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        if (mTeams.size() > 0) {

            final TeamMatch m = (TeamMatch) mRound.getMatchs().get(row);
            Team team1 = (Team) m.mCompetitor1;
            Team team2 = (Team) m.mCompetitor2;

            int nbVictory = m.getVictories(team1);
            int nbLost = m.getLoss(team1);
            int nbDraw = m.getDraw(team1);

            switch (col) {
                case 0:
                    obj = row + 1;
                    break;
                case 1:
                    obj = team1.mName;
                    break;
                case 2:
                    obj = nbVictory;
                    break;
                case 3:
                    obj = nbDraw;
                    break;
                case 4:
                    obj = nbLost;
                    break;
                case 5:
                    obj = team2.mName;
                    break;
                default:
            }
        }
        return obj;
    }

    @Override
    public Class getColumnClass(final int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(final int row, final int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return false;
    }

    @Override
    public Component getTableCellRendererComponent(
            final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final JTextField jlb = new JTextField();

        jlb.setEditable(false);
        if (value instanceof String) {
            jlb.setText((String) value);
        }

        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }
        boolean useColor = Tournament.getTournament().getParams().useColor;
        Color bkg = new Color(255, 255, 255);
        Color frg = new Color(0, 0, 0);
        if (!useColor) {
            if (row % 2 == 1) {
                bkg = new Color(220, 220, 220);
            }
        }
        if (isSelected) {
            bkg = new Color(200, 200, 200);
        } else {
            if (useColor) {
                switch (column) {
                    case 1:

                        bkg = new Color(200, 50, 50);
                        frg = new Color(255, 255, 255);


                        break;
                    case 2:
                        bkg = new Color(250, 200, 200);
                        break;

                    case 4:
                        bkg = new Color(200, 200, 250);
                        break;
                    case 3:
                        bkg = new Color(200, 200, 200);
                        break;

                    case 5:
                        bkg = new Color(50, 50, 250);
                        frg = new Color(255, 255, 255);
                        break;
                    case 6:
                        frg = new Color(200, 50, 50);
                        break;



                    case 7:
                        frg = new Color(200, 50, 50);
                        break;

                    case 8:
                        frg = new Color(50, 50, 200);
                        break;

                    default:

                        break;
                }
            }
        }

        if (Tournament.getTournament().getParams().useImage) {
            if (column == 1) {
                TeamMatch tm = (TeamMatch) mRound.getMatchs().get(row);
                if (tm.mCompetitor1.picture != null) {
                    JLabel obj = new JLabel();
                    ImageIcon icon = ImageTreatment.resize(new ImageIcon(tm.mCompetitor1.picture), 30, 30);
                    obj.setIcon(icon);
                    obj.setText((String) value);
                    obj.setOpaque(true);
                    obj.setBackground(bkg);
                    return obj;
                }
            }
            if (column == 5) {
                TeamMatch tm = (TeamMatch) mRound.getMatchs().get(row);
                if (tm.mCompetitor2.picture != null) {
                    JLabel obj = new JLabel();
                    ImageIcon icon = ImageTreatment.resize(new ImageIcon(tm.mCompetitor2.picture), 30, 30);
                    obj.setIcon(icon);
                    obj.setText((String) value);
                    obj.setOpaque(true);
                    obj.setBackground(bkg);
                    //obj.setBorder(new LineBorder(frg,1));
                    return obj;
                }
            }
        }

        jlb.setBackground(bkg);
        jlb.setForeground(frg);
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
