/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Match;
import tourma.data.Coach;
import tourma.data.Value;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Criteria;

/**
 *
 * @author Frederic Berger
 */
abstract public class mjtRanking extends AbstractTableModel implements TableCellRenderer {

    int _round;
    Vector _objects;
    int _ranking_type1;
    int _ranking_type2;
    int _ranking_type3;
    int _ranking_type4;
    int _ranking_type5;
    Vector _datas = new Vector<ObjectRanking>();

    public Vector<ObjectRanking> getSortedDatas() {
        return _datas;
    }

    public mjtRanking(int round, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5, Vector objects) {
        _round = round;
        _ranking_type1 = ranking_type1;
        _ranking_type2 = ranking_type2;
        _ranking_type3 = ranking_type3;
        _ranking_type4 = ranking_type4;
        _ranking_type5 = ranking_type5;
        _objects = objects;
        //sortDatas();
    }

    abstract protected void sortDatas();

    abstract public int getColumnCount();

    @Override
    abstract public String getColumnName(int col);

    abstract public Object getValueAt(int row, int col);

    public int getRowCount() {
        return _objects.size();
    }

    public static int getOppPointsByCoach(Coach c, Match m) {

        int index = 0;
        Match tmp_m = c._matchs.get(index);
        while (tmp_m != m) {
            index++;
            tmp_m = c._matchs.get(index);
        }

        int value = 0;
        Coach opponent;
        if (m._coach1 == c) {
            opponent = m._coach2;
        } else {
            opponent = m._coach1;
        }

        for (int i = 0; i <= index; i++) {
            value += getPointsByCoach(opponent, opponent._matchs.get(i));
        }

        return value;
    }

    public static int getVNDByCoach(Coach c, Match m) {
        int value = 0;
        Parameters params = Tournament.getTournament().getParams();
        Value val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
        if (val._value1 >= 0) {
            if (m._coach1 == c) {
                if (val._value1 > val._value2) {
                    value += 1000000;
                } else {
                    if (val._value1 >= 0) {
                        if (val._value1 == val._value2) {
                            value += 1000;
                        } else {
                            value += 1;
                        }
                    }
                }
            }
            if (m._coach2 == c) {
                if (val._value2 > val._value1) {
                    value += 1000000;
                } else {
                    if (val._value1 >= 0) {
                        if (val._value1 == val._value2) {
                            value += 1000;
                        } else {
                            value += 1;
                        }
                    }
                }
            }
        }
        return value;
    }

    public static int getPointsByCoach(Coach c, Match m) {
        int value = 0;
        Value val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
        if (m._coach1 == c) {
            if (val._value1 >= 0) {
                if (val._value1 >= val._value2 + Tournament.getTournament().getParams()._large_victory_gap) {
                    value += Tournament.getTournament().getParams()._large_victory_points;
                } else {
                    if (val._value1 > val._value2) {
                        value += Tournament.getTournament().getParams()._victory_points;
                    } else {
                        if (val._value1 == val._value2) {
                            value += Tournament.getTournament().getParams()._draw_points;
                        } else {
                            if (val._value1 + Tournament.getTournament().getParams()._little_lost_gap >= val._value2) {
                                value += Tournament.getTournament().getParams()._little_lost_points;
                            } else {
                                value += Tournament.getTournament().getParams()._lost_points;
                            }
                        }
                    }
                }

                for (int j = 0; j < Tournament.getTournament().getParams()._criterias.size(); j++) {
                    Criteria cri = Tournament.getTournament().getParams()._criterias.get(j);
                    Value va = m._values.get(cri);
                    value += va._value1 * cri._pointsFor;
                    value += va._value2 * cri._pointsAgainst;
                }

            }
        }
        if (m._coach2 == c) {
            if (val._value1 >= 0) {
                if (val._value2 >= val._value1 + Tournament.getTournament().getParams()._large_victory_gap) {
                    value += Tournament.getTournament().getParams()._large_victory_points;
                } else {
                    if (val._value2 > val._value1) {
                        value += Tournament.getTournament().getParams()._victory_points;
                    } else {
                        if (val._value2 == val._value1) {
                            value += Tournament.getTournament().getParams()._draw_points;
                        } else {
                            if (val._value2 + Tournament.getTournament().getParams()._little_lost_gap >= val._value1) {
                                value += Tournament.getTournament().getParams()._little_lost_points;
                            } else {
                                value += Tournament.getTournament().getParams()._lost_points;
                            }
                        }
                    }
                }
                for (int j = 0; j < Tournament.getTournament().getParams()._criterias.size(); j++) {

                    Criteria cri = Tournament.getTournament().getParams()._criterias.get(j);
                    Value va = m._values.get(cri);
                    value += va._value2 * cri._pointsFor;
                    value += va._value1 * cri._pointsAgainst;
                }
            }
        }
        return value;
    }

    public static int getValue(Coach c, Match m, Criteria criteria, int sub_type) {
        int value = 0;

        int i = 0;
        if (sub_type == Parameters.C_RANKING_SUBTYPE_POSITIVE) {
            if (c == m._coach1) {
                value += Math.max(m._values.get(criteria)._value1, 0);
            } else {
                value += Math.max(m._values.get(criteria)._value2, 0);
            }
        } else {
            if (sub_type == Parameters.C_RANKING_SUBTYPE_NEGATIVE) {
                if (c == m._coach1) {
                    value += Math.max(m._values.get(criteria)._value2, 0);
                } else {
                    value += Math.max(m._values.get(criteria)._value1, 0);
                }
            } else {
                if (c == m._coach1) {
                    value += m._values.get(criteria)._value1 - m._values.get(criteria)._value2;
                } else {
                    value += m._values.get(criteria)._value2 - m._values.get(criteria)._value1;
                }
            }
        }

        return value;
    }

    public static int getSubtypeByValue(int valueType) {
        int subType = 0;
        if (valueType > Parameters.C_MAX_RANKING) {
            int value = valueType - Parameters.C_MAX_RANKING - 1;
            subType = value % 3;
        }
        return subType;
    }

    public static Criteria getCriteriaByValue(int valueType) {
        Criteria criteria = null;

        if (valueType > Parameters.C_MAX_RANKING) {
            int value = valueType - Parameters.C_MAX_RANKING - 1;
            criteria = Tournament.getTournament().getParams()._criterias.get(value / 3);
        }
        return criteria;
    }

    /**
     * Find value for criteria and the current match
     */
    public static int getValue(Coach c, Match m, int valueType) {
        int value = 0;

        switch (valueType) {
            case Parameters.C_RANKING_POINTS:
                value = getPointsByCoach(c, m);
                break;
            case Parameters.C_RANKING_NONE:
                value = 0;
                break;
            case Parameters.C_RANKING_OPP_POINTS:
                value = getOppPointsByCoach(c, m);
                break;
            case Parameters.C_RANKING_VND:
                value = getVNDByCoach(c, m);
                break;
        }

        return value;
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

        if (row == _objects.size() - 1) {
            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
            jlb.setBackground(new Color(50, 50, 200));
            jlb.setForeground(new Color(255, 255, 255));




        }

        if (row == _objects.size() - 2) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            jlb.setBackground(new Color(100, 100, 200));
            jlb.setForeground(new Color(0, 0, 0));




        }

        if (row == _objects.size() - 3) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            jlb.setBackground(new Color(150, 150, 200));
            jlb.setForeground(new Color(0, 0, 0));




        }

        jlb.setHorizontalAlignment(JTextField.CENTER);




        return jlb;


    }

    public static String getRankingString(int rankingType) {

        Criteria c = mjtRanking.getCriteriaByValue(rankingType);
        if (c == null) {
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS");
                case Parameters.C_RANKING_NONE:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RIEN");
                case Parameters.C_RANKING_OPP_POINTS:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PTS ADV");
                case Parameters.C_RANKING_VND:
                    return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V/N/D");
            }
        } else {
            int subRanking = mjtRanking.getSubtypeByValue(rankingType);
            switch (subRanking) {
                case Parameters.C_RANKING_SUBTYPE_POSITIVE:
                    return c._name + " Coach";
                case Parameters.C_RANKING_SUBTYPE_NEGATIVE:
                    return c._name + " Adversaire";
                case Parameters.C_RANKING_SUBTYPE_DIFFERENCE:
                    return c._name + " Difference";
            }
        }
        return "";
    }
}
