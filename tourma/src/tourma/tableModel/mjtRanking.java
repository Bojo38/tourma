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
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Frederic Berger
 */
abstract public class mjtRanking extends AbstractTableModel implements TableCellRenderer {

    Vector<Round> _rounds;
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

    public mjtRanking(Vector<Round> rounds, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5, Vector objects) {
        _rounds = rounds;
        _ranking_type1 = ranking_type1;
        _ranking_type2 = ranking_type2;
        _ranking_type3 = ranking_type3;
        _ranking_type4 = ranking_type4;
        _ranking_type5 = ranking_type5;
        _objects = objects;
        sortDatas();
    }

    abstract protected void sortDatas();

    abstract public int getColumnCount();

    @Override
    abstract public String getColumnName(int col);

    abstract public Object getValueAt(int row, int col);

    public int getRowCount() {
        return _objects.size();
    }

    public static int getSorByCoach(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._sor1;
        }
        if (m._coach2 == c) {
            value += m._sor2;
        }
        return value;
    }

    public static int getPasByCoach(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._pas1;
        }
        if (m._coach2 == c) {
            value += m._pas2;
        }
        return value;
    }

    public static int getIntByCoach(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._int1;
        }
        if (m._coach2 == c) {
            value += m._int2;
        }
        return value;
    }

    public static int getTdByCoach(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._td1;
        }
        if (m._coach2 == c) {
            value += m._td2;
        }
        return value;
    }

    public static int getFoulByCoach(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._foul1;
        }
        if (m._coach2 == c) {
            value += m._foul2;
        }
        return value;
    }

    public static int getDiffTdByCoach(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._td1 - m._td2;
        }
        if (m._coach2 == c) {
            value += m._td2 - m._td1;
        }
        return value;
    }

    public static int getDiffSorByCoach(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._sor1 - m._sor2;
        }
        if (m._coach2 == c) {
            value += m._sor2 - m._sor1;
        }
        return value;
    }

     public static int getDiffPasByCoach(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._pas1 - m._pas2;
        }
        if (m._coach2 == c) {
            value += m._pas2 - m._pas1;
        }
        return value;
    }

      public static int getDiffIntByCoach(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._int1 - m._int2;
        }
        if (m._coach2 == c) {
            value += m._int2 - m._int1;
        }
        return value;
    }


    public static int getDiffFoulByCoach(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            value += m._foul1 - m._foul2;
        }
        if (m._coach2 == c) {
            value += m._foul2 - m._foul1;
        }
        return value;
    }

    public static int getOppPointsByCoach(Coach c, Vector<Round> v) {
        

        Vector<Coach> Opponents=new Vector<Coach>();
        
        for (int i=0; i<v.size(); i++)
        {
            Round r=v.get(i);
            for (int j=0; j<r.getMatchs().size(); j++)
            {
                Match m=r.getMatchs().get(j);
                if (m._coach1==c)
                {
                    Opponents.add(m._coach2);
                }
                else
                {
                    if (m._coach2==c)
                    {
                        Opponents.add(m._coach1);
                    }
                }
            }
        }


        int value = 0;

        for (int i = 0; i < v.size(); i++) {
            Round r = v.get(i);
            for (int j = 0; j < r.getMatchs().size(); j++)
            {
                Match m=r.getMatchs().get(j);
                for (int k=0; k<Opponents.size(); k++)
                {
                    Coach opp=Opponents.get(k);
                    if ((m._coach1==opp)||
                            (m._coach2==opp))
                    {
                        value += getPointsByCoach(opp, m);
                    }
                }
            }
        }
        return value;
    }

    public static int getVNDByCoach(Coach c, Match m) {
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

    public static int getPointsByCoach(Coach c, Match m) {
        int value = 0;
        if (m._coach1 == c) {
            if (m._td1 >= m._td2 + Tournament.getTournament().getParams()._large_victory_gap) {
                value += Tournament.getTournament().getParams()._large_victory_points;
            } else {
                if (m._td1 > m._td2) {
                    value += Tournament.getTournament().getParams()._victory_points;
                } else {
                    if (m._td1 == m._td2) {
                        value += Tournament.getTournament().getParams()._draw_points;
                    } else {
                        if (m._td1 + Tournament.getTournament().getParams()._little_lost_gap >= m._td2) {
                            value += Tournament.getTournament().getParams()._little_lost_points;
                        } else {
                            value += Tournament.getTournament().getParams()._lost_points;
                        }
                    }
                }
            }
            value += m._td1 * Tournament.getTournament().getParams()._bonus_td_points + m._td2 * Tournament.getTournament().getParams()._bonus_neg_td_points;
            value += m._sor1 * Tournament.getTournament().getParams()._bonus_sor_points + m._sor2 * Tournament.getTournament().getParams()._bonus_neg_sor_points;
            value += m._foul1 * Tournament.getTournament().getParams()._bonus_foul_points + m._foul2 * Tournament.getTournament().getParams()._bonus_neg_foul_points;
        }
        if (m._coach2 == c) {
            if (m._td2 >= m._td1 + Tournament.getTournament().getParams()._large_victory_gap) {
                value += Tournament.getTournament().getParams()._large_victory_points;
            } else {
                if (m._td2 > m._td1) {
                    value += Tournament.getTournament().getParams()._victory_points;
                } else {
                    if (m._td2 == m._td1) {
                        value += Tournament.getTournament().getParams()._draw_points;
                    } else {
                        if (m._td2 + Tournament.getTournament().getParams()._little_lost_gap >= m._td1) {
                            value += Tournament.getTournament().getParams()._little_lost_points;
                        } else {
                            value += Tournament.getTournament().getParams()._lost_points;
                        }
                    }
                }
            }
            value += m._td2 * Tournament.getTournament().getParams()._bonus_td_points + m._td1 * Tournament.getTournament().getParams()._bonus_neg_td_points;
            value += m._sor2 * Tournament.getTournament().getParams()._bonus_sor_points + m._sor1 * Tournament.getTournament().getParams()._bonus_neg_sor_points;
            value += m._foul2 * Tournament.getTournament().getParams()._bonus_foul_points + m._foul1 * Tournament.getTournament().getParams()._bonus_neg_foul_points;
        }
        return value;
    }

    public static int getValue(Coach c, Match m, int valueType, Vector<Round> v) {
        int value = 0;
        switch (valueType) {
            case Parameters.C_RANKING_POINTS:
                value = getPointsByCoach(c, m);
                break;
            case Parameters.C_RANKING_DIFF_FOUL:
                value = getDiffFoulByCoach(c, m);
                break;
            case Parameters.C_RANKING_DIFF_SOR:
                value = getDiffSorByCoach(c, m);
                break;
            case Parameters.C_RANKING_DIFF_TD:
                value = getDiffTdByCoach(c, m);
                break;
            case Parameters.C_RANKING_FOUL:
                value = getFoulByCoach(c, m);
                break;
            case Parameters.C_RANKING_NONE:
                value = 0;
                break;
            case Parameters.C_RANKING_OPP_POINTS:
                value = getOppPointsByCoach(c, v);
                break;
            case Parameters.C_RANKING_SOR:
                value = getSorByCoach(c, m);
                break;
            case Parameters.C_RANKING_TD:
                value = getTdByCoach(c, m);
                break;
            case Parameters.C_RANKING_VND:
                value = getVNDByCoach(c, m);
                break;

            case Parameters.C_RANKING_DIFF_PAS:
                value = getDiffPasByCoach(c, m);
                break;
            case Parameters.C_RANKING_DIFF_INT:
                value = getDiffIntByCoach(c, m);
                break;

                case Parameters.C_RANKING_PAS:
                value = getPasByCoach(c, m);
                break;
            case Parameters.C_RANKING_INT:
                value = getIntByCoach(c, m);
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
}
