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
//        this.addCellEditorListener(this);
    }

    public int getColumnCount() {
        if (_teamTournament) {
            if (_full) {
                return 15;
            } else {
                return 7;
            }
        } else {
            return 13;
        }
    }

    public int getRowCount() {
        return _matchs.size();
    }

    public String getColumnName(int col) {

        if (_teamTournament) {
            switch (col) {
                case 0:
                    return "Table";
                case 1:
                    return "Equipe 1";
                case 2:
                    return "Coach 1";
                case 3:
                    return "Score 1";
                case 4:
                    return "Score 2";
                case 5:
                    return "Coach 2";
                case 6:
                    return "Equipe 2";
                case 7:
                    return "Sorties 1";
                case 8:
                    return "Sorties 2";
                case 9:
                    return "Foul 1";
                case 10:
                    return "Foul 2";
                case 11:
                    return "Passes 1";
                case 12:
                    return "Passes 2";
                case 13:
                    return "Inter. 1";
                case 14:
                    return "Inter. 2";
            }
        } else {

            switch (col) {
                case 0:
                    return "Table";
                case 1:
                    return "Coach 1";
                case 2:
                    return "Score 1";
                case 3:
                    return "Score 2";
                case 4:
                    return "Coach 2";
                case 5:
                    return "Sorties 1";
                case 6:
                    return "Sorties 2";
                case 7:
                    return "Foul 1";
                case 8:
                    return "Foul 2";
                case 9:
                    return "Passes 1";
                case 10:
                    return "Passes 2";
                case 11:
                    return "Inter. 1";
                case 12:
                    return "Inter. 2";
            }
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        Match m = _matchs.get(row);

        if (_teamTournament) {
            switch (col) {
                case 0:
                    return row + 1;
                case 1:
                    return m._coach1._teamMates._name;
                case 2:
                    return m._coach1._team + " - " + m._coach1._name;
                case 3:
                    if (m._td1 >= 0) {
                        return m._td1;
                    } else {
                        return "";
                    }
                case 4:
                    if (m._td2 >= 0) {
                        return m._td2;
                    } else {
                        return "";
                    }
                case 5:
                    return m._coach2._team + " - " + m._coach2._name;
                case 6:
                    return m._coach2._teamMates._name;
                case 7:
                    return m._sor1;
                case 8:
                    return m._sor2;
                case 9:
                    return m._foul1;
                case 10:
                    return m._foul2;
                case 11:
                    return m._pas1;
                case 12:
                    return m._pas2;
                case 13:
                    return m._int1;
                case 14:
                    return m._int2;
            }
        } else {
            switch (col) {
                case 0:
                    return row + 1;
                case 1:
                    return m._coach1._team + " - " + m._coach1._name;
                case 2:
                    if (m._td1 >= 0) {
                        return m._td1;
                    } else {
                        return "";
                    }
                case 3:
                    if (m._td2 >= 0) {
                        return m._td2;
                    } else {
                        return "";
                    }
                case 4:
                    return m._coach2._team + " (" + m._coach2._name + ")";
                case 5:
                    return m._sor1;
                case 6:
                    return m._sor2;
                case 7:
                    return m._foul1;
                case 8:
                    return m._foul2;
                case 9:
                    return m._pas1;
                case 10:
                    return m._pas2;
                case 11:
                    return m._int1;
                case 12:
                    return m._int2;
            }
        }
        return "";
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (value != null) {
            Match m = _matchs.get(row);
            if (_teamTournament) {
                switch (col) {
                    case 3:
                        m._td1 = (Integer.valueOf(value.toString()));
                        break;
                    case 4:
                        m._td2 = (Integer.valueOf(value.toString()));
                        break;
                    case 7:
                        m._sor1 = (Integer.valueOf(value.toString()));
                        break;
                    case 8:
                        m._sor2 = (Integer.valueOf(value.toString()));
                        break;
                    case 9:
                        m._foul1 = (Integer.valueOf(value.toString()));
                        break;
                    case 10:
                        m._foul2 = (Integer.valueOf(value.toString()));
                        break;
                    case 11:
                        m._pas1 = (Integer.valueOf(value.toString()));
                        break;
                    case 12:
                        m._pas2 = (Integer.valueOf(value.toString()));
                        break;
                    case 13:
                        m._int1 = (Integer.valueOf(value.toString()));
                        break;
                    case 14:
                        m._int2 = (Integer.valueOf(value.toString()));
                        break;
                }
            } else {
                switch (col) {
                    case 2:
                        m._td1 = (Integer.valueOf(value.toString()));
                        break;
                    case 3:
                        m._td2 = (Integer.valueOf(value.toString()));
                        break;
                    case 5:
                        m._sor1 = (Integer.valueOf(value.toString()));
                        break;
                    case 6:
                        m._sor2 = (Integer.valueOf(value.toString()));
                        break;
                    case 7:
                        m._foul1 = (Integer.valueOf(value.toString()));
                        break;
                    case 8:
                        m._foul2 = (Integer.valueOf(value.toString()));
                        break;
                    case 9:
                        m._pas1 = (Integer.valueOf(value.toString()));
                        break;
                    case 10:
                        m._pas2 = (Integer.valueOf(value.toString()));
                        break;
                    case 11:
                        m._int1 = (Integer.valueOf(value.toString()));
                        break;
                    case 12:
                        m._int2 = (Integer.valueOf(value.toString()));
                        break;
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
            if (_teamTournament) {
                switch (column) {
                    case 1:
                        bkg = new Color(200, 50, 50);
                        frg =
                                new Color(255, 255, 255);
                        if (_matchs.get(row)._td1 > _matchs.get(row)._td2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (_matchs.get(row)._td1 == _matchs.get(row)._td2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }

                        break;
                    case 2:
                        bkg = new Color(200, 50, 50);
                        frg =
                                new Color(255, 255, 255);
                        if (_matchs.get(row)._td1 > _matchs.get(row)._td2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (_matchs.get(row)._td1 == _matchs.get(row)._td2) {
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
                        frg =
                                new Color(255, 255, 255);
                        if (_matchs.get(row)._td1 < _matchs.get(row)._td2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (_matchs.get(row)._td1 == _matchs.get(row)._td2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }
                        break;
                    case 6:
                        bkg = new Color(50, 50, 250);
                        frg =
                                new Color(255, 255, 255);
                        if (_matchs.get(row)._td1 < _matchs.get(row)._td2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (_matchs.get(row)._td1 == _matchs.get(row)._td2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }
                        break;
                    case 7:
                        frg = new Color(200, 50, 50);
                        break;

                    case 8:
                        frg = new Color(50, 50, 200);
                        break;

                    case 9:
                        frg = new Color(200, 50, 50);
                        break;

                    case 10:
                        frg = new Color(50, 50, 200);
                        break;

                    default:

                        break;
                }
            } else {
                switch (column) {
                    case 1:
                        bkg = new Color(200, 50, 50);
                        frg =
                                new Color(255, 255, 255);
                        if (_matchs.get(row)._td1 > _matchs.get(row)._td2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (_matchs.get(row)._td1 == _matchs.get(row)._td2) {
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
                        frg =
                                new Color(255, 255, 255);
                        if (_matchs.get(row)._td1 < _matchs.get(row)._td2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (_matchs.get(row)._td1 == _matchs.get(row)._td2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }

                        break;
                    case 5:
                        frg = new Color(200, 50, 50);
                        break;

                    case 6:
                        frg = new Color(50, 50, 200);
                        break;

                    case 7:
                        frg = new Color(200, 50, 50);
                        break;

                    case 8:
                        frg = new Color(50, 50, 200);
                        break;
                    case 9:
                        frg = new Color(200, 50, 50);
                        break;

                    case 10:
                        frg = new Color(50, 50, 200);
                        break;
                    case 11:
                        frg = new Color(200, 50, 50);
                        break;

                    case 12:
                        frg = new Color(50, 50, 200);
                        break;
                    case 13:
                        frg = new Color(200, 50, 50);
                        break;

                    case 14:
                        frg = new Color(50, 50, 200);
                        break;

                    default:

                        break;
                }
            }

        }
        jlb.setBackground(bkg);
        jlb.setForeground(frg);
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
