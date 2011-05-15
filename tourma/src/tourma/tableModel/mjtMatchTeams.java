/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.data.Value;

/**
 *
 * @author Frederic Berger
 */
public class mjtMatchTeams extends AbstractTableModel implements TableCellRenderer {

    Vector<Team> _teams;
    Round _round;

    public mjtMatchTeams(Vector<Team> teams, Round round) {
        _teams = teams;
        _round = round;
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return _teams.size() / 2;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB");
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN 1");
            case 2:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V1");
            case 3:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("N");
            case 4:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V2");
            case 5:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN 2");
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        if (_teams.size() > 0) {
            Team t = _teams.get(row * 2);
            Team team1 = t;
            Team team2 = t;
            Vector<Match> matchs = new Vector<Match>();
            for (int i = 0; i < _round.getMatchs().size(); i++) {
                Match m = _round.getMatchs().get(i);
                if (m._coach1._teamMates == t) {
                    matchs.add(m);
                    team1 = t;
                    team2 = m._coach2._teamMates;

                }
                if (m._coach2._teamMates == t) {
                    matchs.add(m);
                    team1 = t;
                    team2 = m._coach1._teamMates;
                }
            }
            int nbVictory = 0;
            int nbLost = 0;
            int nbDraw = 0;

            for (int i = 0; i < matchs.size(); i++) {
                Match m = matchs.get(i);
                if (m._coach1._teamMates == t) {
                    Value val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
                    if (val._value1 > val._value2) {
                        nbVictory++;
                    } else {
                        if (val._value1 < val._value2) {
                            nbLost++;
                        } else {
                            nbDraw++;
                        }
                    }
                }
                if (m._coach2._teamMates == t) {
                     Value val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
                    if (val._value1 < val._value2) {
                        nbVictory++;
                    } else {
                        if (val._value1 > val._value2) {
                            nbLost++;
                        } else {
                            nbDraw++;
                        }
                    }
                }
            }

            switch (col) {
                case 0:
                    return row + 1;
                case 1:
                    return team1._name;
                case 2:
                    return nbVictory;
                case 3:
                    return nbDraw;
                case 4:
                    return nbLost;
                case 5:
                    return team2._name;
            }
        }
        return "";
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return false;
    }

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JTextField jlb = new JTextField();

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
