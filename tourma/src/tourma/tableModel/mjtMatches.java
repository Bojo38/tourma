/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.Match;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Criteria;
import tourma.data.Tournament;
import tourma.data.Value;

/**
 *
 * @author Frederic Berger
 */
public class mjtMatches extends AbstractTableModel implements TableCellRenderer {

    Vector<Match> _matchs;
    boolean _locked;
    boolean _teamTournament;
    boolean _full;

    public mjtMatches(Vector<Match> matchs, boolean locked, boolean teamTournament, boolean full) {
        _matchs = matchs;
        _locked = locked;
        _teamTournament = teamTournament;
        _full = full;
    }

    public int getColumnCount() {
        if (_teamTournament) {
            return Tournament.getTournament().getParams()._criterias.size() * 2 + 5;
        } else {
            return Tournament.getTournament().getParams()._criterias.size() * 2 + 3;
        }
    }

    public int getRowCount() {
        return _matchs.size();
    }

    public String getColumnName(int col) {

        if (_teamTournament) {
            switch (col) {
                case 0:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Table");
                case 1:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team1");
                case 2:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Coach1");
                case 3:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Score1");
                case 4:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Score2");
                case 5:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Coach2");
                case 6:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team2");
                default:
                    Criteria crit = Tournament.getTournament().getParams()._criterias.get((col - 5) / 2);
                    int ind = (col - 5) % 2;
                    if (ind == 0) {
                        return crit._name + " 1";
                    } else {
                        return crit._name + " 2";
                    }
            }
        } else {
            switch (col) {
                case 0:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Table");
                case 1:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Coach1");
                case 2:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Score1");
                case 3:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Score2");
                case 4:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Coach2");
                default:
                    Criteria crit = Tournament.getTournament().getParams()._criterias.get((col - 3) / 2);
                    if ((col - 3) % 2 > 0) {
                        return crit._name + " 2";
                    } else {
                        return crit._name + " 1";
                    }
            }
        }
    }

    public Object getValueAt(int row, int col) {
        Match m = _matchs.get(row);
        Value val;
        int index;
        if (_teamTournament) {
            switch (col) {
                case 0:
                    return row + 1;
                case 1:
                    return m._coach1._teamMates._name;
                case 2:
                    return m._coach1._name + " - " + m._coach1._team + " (" + m._coach1._roster._name + ")";
                case 3:
                    val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
                    if (val._value1 >= 0) {
                        return val._value1;
                    } else {
                        return "";
                    }
                case 4:
                    val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
                    if (val._value2 >= 0) {
                        return val._value2;
                    } else {
                        return "";
                    }
                case 5:
                    return m._coach2._name + " - " + m._coach2._team + " (" + m._coach2._roster._name + ")";
                case 6:
                    return m._coach2._teamMates._name;
                default:
                    index=(col - 5) / 2;
                    Criteria crit=Tournament.getTournament().getParams()._criterias.get(index);
                    val = m._values.get(crit);
                    if ((col - 5) % 2 == 0) {
                        return val._value1;
                    } else {
                        return val._value2;
                    }
            }
        } else {
            switch (col) {
                case 0:
                    return row + 1;
                case 1:
                    return m._coach1._name + " - " + m._coach1._team + " (" + m._coach1._roster._name + ") ";
                case 2:
                    val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
                    if (val._value1 >= 0) {
                        return val._value1;
                    } else {
                        return "";
                    }
                case 3:
                    val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
                    if (val._value2 >= 0) {
                        return val._value2;
                    } else {
                        return "";
                    }
                case 4:
                    return m._coach2._name + " - " + m._coach2._team + " (" + m._coach2._roster._name + ") ";
                default:
                    index=(col - 3) / 2;
                    Criteria crit=Tournament.getTournament().getParams()._criterias.get(index);
                    val = m._values.get(crit);
                    if ((col - 3) % 2 == 0) {
                        return val._value1;
                    } else {
                        return val._value2;
                    }
            }
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (value != null) {
            Value val;
            Match m = _matchs.get(row);
            if (_teamTournament) {
                switch (col) {
                    case 3:
                        val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
                        val._value1 = (Integer.valueOf(value.toString()));
                        break;
                    case 4:
                        val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
                        val._value2 = (Integer.valueOf(value.toString()));
                        break;
                    default:
                        int index = (col - 5) / 2;
                        val = m._values.get(Tournament.getTournament().getParams()._criterias.get(index));
                        if ((col-5) % 2 == 0) {
                            val._value1 = (Integer.valueOf(value.toString()));
                        } else {
                            val._value2 = (Integer.valueOf(value.toString()));
                        }
                }
            } else {
                switch (col) {
                    case 2:
                        val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
                        val._value1 = (Integer.valueOf(value.toString()));
                        break;
                    case 3:
                        val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
                        val._value2 = (Integer.valueOf(value.toString()));
                        break;
                    default:
                        int index = (col - 3) / 2;
                        val = m._values.get(Tournament.getTournament().getParams()._criterias.get(index));
                        if ((col -3) % 2 == 0) {
                            val._value1 = (Integer.valueOf(value.toString()));
                        } else {
                            val._value2 = (Integer.valueOf(value.toString()));
                        }
                }
            }
        }
        fireTableDataChanged();
    }

    @Override
    public Class getColumnClass(
            int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (_locked) {
            return false;
        } else {
            if (_teamTournament) {
                if ((col == 1) || (col == 2) || (col == 5) || (col == 6)) {
                    return false;
                }
            } else {
                if ((col == 1) || (col == 4)) {
                    return false;
                }
            }
        }
        return true;
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
            Value val;
            val = _matchs.get(row)._values.get(Tournament.getTournament().getParams()._criterias.get(0));
            if (_teamTournament) {

                switch (column) {
                    case 1:
                        bkg = new Color(200, 50, 50);
                        frg = new Color(255, 255, 255);
                        if (val._value1 > val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (val._value1 == val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }

                        break;
                    case 2:
                        bkg = new Color(200, 50, 50);
                        frg = new Color(255, 255, 255);
                        if (val._value1 > val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (val._value1 == val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }
                        break;
                    case 3:
                        bkg = new Color(250, 200, 200);
                        break;
                    case 4:
                        bkg = new Color(200, 200, 250);
                        break;
                    case 5:
                        bkg = new Color(50, 50, 250);
                        frg = new Color(255, 255, 255);
                        if (val._value1 < val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }
                        if (val._value1 == val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }
                        break;
                    case 6:
                        bkg = new Color(50, 50, 250);
                        frg = new Color(255, 255, 255);

                        if (val._value1 < val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (val._value1 == val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }
                        break;
                    default:
                        if (column % 2 > 0) {
                            frg = new Color(200, 50, 50);
                        } else {
                            frg = new Color(50, 50, 200);
                        }
                }
            } else {
                switch (column) {
                    case 1:
                        bkg = new Color(200, 50, 50);
                        frg = new Color(255, 255, 255);
                        if (val._value1 > val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (val._value1 == val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }
                        break;
                    case 2:
                        bkg = new Color(250, 200, 200);
                        break;
                    case 3:
                        bkg = new Color(200, 200, 250);
                        break;
                    case 4:
                        bkg = new Color(50, 50, 250);
                        frg = new Color(255, 255, 255);
                        if (val._value1 < val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }
                        if (val._value1 == val._value2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }

                        break;

                    default:
                        if (column % 2 > 0) {
                            frg = new Color(200, 50, 50);
                        } else {
                            frg = new Color(50, 50, 200);
                        }
                }
            }
        }
        jlb.setBackground(bkg);
        jlb.setForeground(frg);
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
