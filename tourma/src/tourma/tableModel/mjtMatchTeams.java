/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.utility.StringConstants;

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

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return mTeams.size() / 2;
    }

    public String getColumnName(final int col) {
        String res = "";
        switch (col) {
            case 0:
                res = "#";
                break;
            case 1:
                res = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Clan1");
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
                res = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Clan2");
                break;
            default:
        }
        return res;
    }

    public Object getValueAt(final int row, final int col) {
        Object obj = "";
        if (mTeams.size() > 0) {
            final Team t = mTeams.get(row * 2);
            Team team1 = t;
            Team team2 = t;
            final ArrayList<Match> matchs = new ArrayList<Match>();
            for (int i = 0; i < mRound.getMatchs().size(); i++) {
                final Match m = mRound.getMatchs().get(i);
                if (m.mCoach1.mTeamMates == t) {
                    matchs.add(m);
                    team1 = t;
                    team2 = m.mCoach2.mTeamMates;

                }
                if (m.mCoach2.mTeamMates == t) {
                    matchs.add(m);
                    team1 = t;
                    team2 = m.mCoach1.mTeamMates;
                }
            }
            int nbVictory = 0;
            int nbLost = 0;
            int nbDraw = 0;

            for (int i = 0; i < matchs.size(); i++) {
                final Match m = matchs.get(i);
                if (m.mCoach1.mTeamMates == t) {
                    final Value val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
                    if (val.mValue1 > val.mValue2) {
                        nbVictory++;
                    } else {
                        if (val.mValue1 < val.mValue2) {
                            nbLost++;
                        } else {
                            nbDraw++;
                        }
                    }
                }
                if (m.mCoach2.mTeamMates == t) {
                    final Value val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
                    if (val.mValue1 < val.mValue2) {
                        nbVictory++;
                    } else {
                        if (val.mValue1 > val.mValue2) {
                            nbLost++;
                        } else {
                            nbDraw++;
                        }
                    }
                }
            }

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

    public Class getColumnClass(final int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(final int row,final  int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return false;
    }

    public Component getTableCellRendererComponent(
            final JTable table, final Object value,final  boolean isSelected,final  boolean hasFocus,final  int row, final int column) {
        final JTextField jlb = new JTextField();

        jlb.setEditable(false);
        if (value instanceof String) {
            jlb.setText((String) value);
        }

        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }

        Color bkg = new Color(255, 255, 255);
        Color frg = new Color(0, 0, 0);
        if (isSelected) {
            bkg = new Color(200, 200, 200);
        } else {
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
        jlb.setBackground(bkg);
        jlb.setForeground(frg);
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
