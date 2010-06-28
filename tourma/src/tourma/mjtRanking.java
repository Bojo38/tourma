/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;



/**
 *
 * @author Frederic Berger
 */
public class mjtRanking extends AbstractTableModel implements TableCellRenderer {

    Vector<Round> _rounds;
    Vector<Coach> _coachs;
    int _ranking_type1;
    int _ranking_type2;
    int _ranking_type3;
    int _ranking_type4;
    int _ranking_type5;

    public mjtRanking(Vector<Round> rounds, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5, Vector<Coach> coachs) {
        _rounds = rounds;
        _ranking_type1 = ranking_type1;
        _ranking_type2 = ranking_type2;
        _ranking_type3 = ranking_type3;
        _ranking_type4 = ranking_type4;
        _ranking_type5 = ranking_type5;
        _coachs = coachs;
    }

    public int getColumnCount() {
        return 9;
    }

    public int getRowCount() {
        return _coachs.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "N";
            case 1:
                return "Equipe";
            case 2:
                return "Coach";
            case 3:
                return "Roster";
            case 4:
                switch (_ranking_type1) {
                    case Parameters.C_RANKING_POINTS:
                        return "Points";
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return "Diff aggressions";
                    case Parameters.C_RANKING_DIFF_SOR:
                        return "Diff sorties";
                    case Parameters.C_RANKING_DIFF_TD:
                        return "Diff touchdowns";
                    case Parameters.C_RANKING_FOUL:
                        return "Nb aggressions";
                    case Parameters.C_RANKING_NONE:
                        return "Rien";
                    case Parameters.C_RANKING_OPP_POINTS:
                        return "Pts adv";
                    case Parameters.C_RANKING_SOR:
                        return "Nb sorties";
                    case Parameters.C_RANKING_TD:
                        return "Nb touchdowns";
                    case Parameters.C_RANKING_VND:
                        return "V/N/D";
                }
                break;
            case 5:
                switch (_ranking_type2) {
                    case Parameters.C_RANKING_POINTS:
                        return "Points";
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return "Différences d'aggressions";
                    case Parameters.C_RANKING_DIFF_SOR:
                        return "Différences de sorties";
                    case Parameters.C_RANKING_DIFF_TD:
                        return "Différences de touchdowns";
                    case Parameters.C_RANKING_FOUL:
                        return "Nombre d'aggressions";
                    case Parameters.C_RANKING_NONE:
                        return "Rien";
                    case Parameters.C_RANKING_OPP_POINTS:
                        return "Points adversaires";
                    case Parameters.C_RANKING_SOR:
                        return "Nombre de sorties";
                    case Parameters.C_RANKING_TD:
                        return "Nombre de touchdowns";
                    case Parameters.C_RANKING_VND:
                        return "Victoires/Nuls/Défaites";
                }
                break;
            case 6:
                switch (_ranking_type3) {
                    case Parameters.C_RANKING_POINTS:
                        return "Points";
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return "Différences d'aggressions";
                    case Parameters.C_RANKING_DIFF_SOR:
                        return "Différences de sorties";
                    case Parameters.C_RANKING_DIFF_TD:
                        return "Différences de touchdowns";
                    case Parameters.C_RANKING_FOUL:
                        return "Nombre d'aggressions";
                    case Parameters.C_RANKING_NONE:
                        return "Rien";
                    case Parameters.C_RANKING_OPP_POINTS:
                        return "Points adversaires";
                    case Parameters.C_RANKING_SOR:
                        return "Nombre de sorties";
                    case Parameters.C_RANKING_TD:
                        return "Nombre de touchdowns";
                    case Parameters.C_RANKING_VND:
                        return "Victoires/Nuls/Défaites";
                }
                break;
            case 7:
                switch (_ranking_type4) {
                    case Parameters.C_RANKING_POINTS:
                        return "Points";
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return "Différences d'aggressions";
                    case Parameters.C_RANKING_DIFF_SOR:
                        return "Différences de sorties";
                    case Parameters.C_RANKING_DIFF_TD:
                        return "Différences de touchdowns";
                    case Parameters.C_RANKING_FOUL:
                        return "Nombre d'aggressions";
                    case Parameters.C_RANKING_NONE:
                        return "Rien";
                    case Parameters.C_RANKING_OPP_POINTS:
                        return "Points adversaires";
                    case Parameters.C_RANKING_SOR:
                        return "Nombre de sorties";
                    case Parameters.C_RANKING_TD:
                        return "Nombre de touchdowns";
                    case Parameters.C_RANKING_VND:
                        return "Victoires/Nuls/Défaites";
                }
                break;
            case 8:
                switch (_ranking_type5) {
                    case Parameters.C_RANKING_POINTS:
                        return "Points";
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return "Différences d'aggressions";
                    case Parameters.C_RANKING_DIFF_SOR:
                        return "Différences de sorties";
                    case Parameters.C_RANKING_DIFF_TD:
                        return "Différences de touchdowns";
                    case Parameters.C_RANKING_FOUL:
                        return "Nombre d'aggressions";
                    case Parameters.C_RANKING_NONE:
                        return "Rien";
                    case Parameters.C_RANKING_OPP_POINTS:
                        return "Points adversaires";
                    case Parameters.C_RANKING_SOR:
                        return "Nombre de sorties";
                    case Parameters.C_RANKING_TD:
                        return "Nombre de touchdowns";
                    case Parameters.C_RANKING_VND:
                        return "Victoires/Nuls/Défaites";
                }
                break;
        }
        return "";
    }

    public static int getSor(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._sor1;
        }
        if (m._coach2 == c) {
            value += m._sor2;
        }
        return value;
    }

    public static int getTd(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._td1;
        }
        if (m._coach2 == c) {
            value += m._td2;
        }
        return value;
    }

    public static int getFoul(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._foul1;
        }
        if (m._coach2 == c) {
            value += m._foul2;
        }
        return value;
    }

    public static int getDiffTd(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._td1 - m._td2;
        }
        if (m._coach2 == c) {
            value += m._td2 - m._td1;
        }
        return value;
    }

    public static int getDiffSor(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._sor1 - m._sor2;
        }
        if (m._coach2 == c) {
            value += m._sor2 - m._sor1;
        }
        return value;
    }

    public static int getDiffFoul(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._foul1 - m._foul2;
        }
        if (m._coach2 == c) {
            value += m._foul2 - m._foul1;
        }
        return value;
    }

    public static int getOppPoints(Coach c, Match m) {
        int value = 0;
        if (m._coach2 == c) {
            if (m._td1 > m._td2 + 1) {
                value += Tournament.getTournament()._params._large_victory_points;
            } else {
                if (m._td1 > m._td2) {
                    value += Tournament.getTournament()._params._victory_points;
                } else {
                    if (m._td1 == m._td2) {
                        value += Tournament.getTournament()._params._draw_points;
                    } else {
                        if (m._td1 <= m._td2 + 1) {
                            value += Tournament.getTournament()._params._little_lost_points;
                        } else {
                            value += Tournament.getTournament()._params._lost_points;
                        }
                    }
                }
            }
            value += m._td1 * Tournament.getTournament()._params._bonus_td_points + m._td2 * Tournament.getTournament()._params._bonus_neg_td_points;
            value += m._sor1 * Tournament.getTournament()._params._bonus_sor_points + m._sor2 * Tournament.getTournament()._params._bonus_neg_sor_points;
            value += m._foul1 * Tournament.getTournament()._params._bonus_foul_points + m._foul2 * Tournament.getTournament()._params._bonus_neg_foul_points;
        }
        if (m._coach1 == c) {
            if (m._td2 > m._td1 + 1) {
                value += Tournament.getTournament()._params._large_victory_points;
            } else {
                if (m._td2 > m._td1) {
                    value += Tournament.getTournament()._params._victory_points;
                } else {
                    if (m._td2 == m._td1) {
                        value += Tournament.getTournament()._params._draw_points;
                    } else {
                        if (m._td2 <= m._td1 + 1) {
                            value += Tournament.getTournament()._params._little_lost_points;
                        } else {
                            value += Tournament.getTournament()._params._lost_points;
                        }
                    }
                }
            }
            value += m._td2 * Tournament.getTournament()._params._bonus_td_points + m._td1 * Tournament.getTournament()._params._bonus_neg_td_points;
            value += m._sor2 * Tournament.getTournament()._params._bonus_sor_points + m._sor1 * Tournament.getTournament()._params._bonus_neg_sor_points;
            value += m._foul2 * Tournament.getTournament()._params._bonus_foul_points + m._foul1 * Tournament.getTournament()._params._bonus_neg_foul_points;
        }
        return value;
    }

    public static int getVND(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            if (m._td1 > m._td2) {
                value += 1000000;
            } else {
                if (m._td1 == m._td2) {
                    value += 1000;
                } else {
                    value += 1;
                }
            }
        }
        if (m._coach2 == c) {
            if (m._td2 > m._td1) {
                value += 1000000;
            } else {
                if (m._td1 == m._td2) {
                    value += 1000;
                } else {
                    value += 1;
                }
            }
        }
        return value;
    }

    public static int getPoints(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            if (m._td1 > m._td2 + 1) {
                value += Tournament.getTournament()._params._large_victory_points;
            } else {
                if (m._td1 > m._td2) {
                    value += Tournament.getTournament()._params._victory_points;
                } else {
                    if (m._td1 == m._td2) {
                        value += Tournament.getTournament()._params._draw_points;
                    } else {
                        if (m._td1 <= m._td2 + 1) {
                            value += Tournament.getTournament()._params._little_lost_points;
                        } else {
                            value += Tournament.getTournament()._params._lost_points;
                        }
                    }
                }
            }
            value += m._td1 * Tournament.getTournament()._params._bonus_td_points + m._td2 * Tournament.getTournament()._params._bonus_neg_td_points;
            value += m._sor1 * Tournament.getTournament()._params._bonus_sor_points + m._sor2 * Tournament.getTournament()._params._bonus_neg_sor_points;
            value += m._foul1 * Tournament.getTournament()._params._bonus_foul_points + m._foul2 * Tournament.getTournament()._params._bonus_neg_foul_points;
        }
        if (m._coach2 == c) {
            if (m._td2 > m._td1 + 1) {
                value += Tournament.getTournament()._params._large_victory_points;
            } else {
                if (m._td2 > m._td1) {
                    value += Tournament.getTournament()._params._victory_points;
                } else {
                    if (m._td2 == m._td1) {
                        value += Tournament.getTournament()._params._draw_points;
                    } else {
                        if (m._td2 <= m._td1 + 1) {
                            value += Tournament.getTournament()._params._little_lost_points;
                        } else {
                            value += Tournament.getTournament()._params._lost_points;
                        }
                    }
                }
            }
            value += m._td2 * Tournament.getTournament()._params._bonus_td_points + m._td1 * Tournament.getTournament()._params._bonus_neg_td_points;
            value += m._sor2 * Tournament.getTournament()._params._bonus_sor_points + m._sor1 * Tournament.getTournament()._params._bonus_neg_sor_points;
            value += m._foul2 * Tournament.getTournament()._params._bonus_foul_points + m._foul1 * Tournament.getTournament()._params._bonus_neg_foul_points;
        }
        return value;
    }

    public static int getValue(Coach c, Match m, int valueType) {
        int value = 0;
        switch (valueType) {
            case Parameters.C_RANKING_POINTS:
                value = getPoints(c, m);
                break;
            case Parameters.C_RANKING_DIFF_FOUL:
                value = getDiffFoul(c, m);
                break;
            case Parameters.C_RANKING_DIFF_SOR:
                value = getDiffSor(c, m);
                break;
            case Parameters.C_RANKING_DIFF_TD:
                value = getDiffTd(c, m);
                break;
            case Parameters.C_RANKING_FOUL:
                value = getFoul(c, m);
                break;
            case Parameters.C_RANKING_NONE:
                value = 0;
                break;
            case Parameters.C_RANKING_OPP_POINTS:
                value = getOppPoints(c, m);
                break;
            case Parameters.C_RANKING_SOR:
                value = getSor(c, m);
                break;
            case Parameters.C_RANKING_TD:
                value = getTd(c, m);
                break;
            case Parameters.C_RANKING_VND:
                value = getVND(c, m);
                break;
        }
        return value;
    }

    public Object getValueAt(int row, int col) {
        Vector<Match> matchs = new Vector<Match>();
        for (int i = 0; i < _rounds.size(); i++) {
            for (int j = 0; j < _rounds.get(i)._matchs.size(); j++) {
                matchs.add(_rounds.get(i)._matchs.get(j));
            }
        }

        Vector<CoachRanking> datas = new Vector<CoachRanking>();

        for (int i = 0; i < _coachs.size(); i++) {
            Coach c = _coachs.get(i);
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int j = 0; j < matchs.size(); j++) {
                Match m = matchs.get(j);
                value1 += getValue(c, m, _ranking_type1);
                value2 += getValue(c, m, _ranking_type2);
                value3 += getValue(c, m, _ranking_type3);
                value4 += getValue(c, m, _ranking_type4);
                value5 += getValue(c, m, _ranking_type5);
            }

            datas.add(new CoachRanking(c, value1, value2, value3, value4, value5));
        }

        Collections.sort(datas);
        switch (col) {
            case 0:
                return row + 1;
            case 1:
                return datas.get(row)._coach._team;
            case 2:
                return datas.get(row)._coach._name;
            case 3:
                return datas.get(row)._coach._roster;
            case 4:
                return datas.get(row)._value1;
            case 5:
                return datas.get(row)._value2;
            case 6:
                return datas.get(row)._value3;
            case 7:
                return datas.get(row)._value4;
            case 8:
                return datas.get(row)._value5;
        }



        return "";
    }

    public Class getColumnClass(
            int c) {
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

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JTextField jlb = new JTextField();
        jlb.setEditable(false);
        jlb.setBackground(new Color(255, 255, 255));
        jlb.setForeground(new Color(0, 0, 0));
        if (value instanceof String) {
            jlb.setText((String) value);
        }
        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }

        if (row == 0) {
            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
            jlb.setBackground(new Color(200, 50, 50));
            jlb.setForeground(new Color(255, 255, 255));
        }

        if (row == 1) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            jlb.setBackground(new Color(200, 100, 100));
            jlb.setForeground(new Color(0, 0, 0));
        }

        if (row == 2) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            jlb.setBackground(new Color(200, 150, 150));
            jlb.setForeground(new Color(0, 0, 0));
        }

        if (row == _coachs.size() - 1) {
            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
            jlb.setBackground(new Color(50, 50, 200));
            jlb.setForeground(new Color(255, 255, 255));
        }

        if (row == _coachs.size() - 2) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            jlb.setBackground(new Color(100, 100, 200));
            jlb.setForeground(new Color(0, 0, 0));
        }

        if (row == _coachs.size() - 3) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            jlb.setBackground(new Color(150, 150, 200));
            jlb.setForeground(new Color(0, 0, 0));
        }

        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
