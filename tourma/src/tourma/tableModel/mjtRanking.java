/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.Match;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
abstract public class mjtRanking extends AbstractTableModel implements TableCellRenderer {

    int mRound;
    ArrayList mObjects;
    int mRankingType1;
    int mRankingType2;
    int mRankingType3;
    int mRankingType4;
    int mRankingType5;
    ArrayList mDatas = new ArrayList<>();

    public ArrayList<ObjectRanking> getSortedDatas() {
        return mDatas;
    }

    public mjtRanking(final int round, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final ArrayList objects) {
        mRound = round;
        mRankingType1 = ranking_type1;
        mRankingType2 = ranking_type2;
        mRankingType3 = ranking_type3;
        mRankingType4 = ranking_type4;
        mRankingType5 = ranking_type5;
        mObjects = objects;
        //sortDatas();
    }

    abstract protected void sortDatas();

    @Override
    abstract public int getColumnCount();

    @Override
    abstract public String getColumnName(int col);

    @Override
    abstract public Object getValueAt(int row, int col);

    @Override
    public int getRowCount() {
        return mObjects.size();
    }

    public static int getOppPointsByCoach(final Coach c, final Match m) {

        int index = 0;
        Match tmp_m = c.mMatchs.get(index);
        while (tmp_m != m) {
            index++;
            tmp_m = c.mMatchs.get(index);
        }

        int value = 0;
        Coach opponent;
        if (m.mCoach1 == c) {
            opponent = m.mCoach2;
        } else {
            opponent = m.mCoach1;
        }

        for (int i = 0; i < opponent.mMatchs.size(); i++) {
            value += getPointsByCoach(opponent, opponent.mMatchs.get(i));
        }

        return value;
    }

    public static int getVNDByCoach(final Coach c, final Match m) {
        int value = 0;
        final Value val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
        if (val.mValue1 >= 0) {
            if (m.mCoach1 == c) {
                if (val.mValue1 > val.mValue2) {
                    value += 1000000;
                } else {
                    if (val.mValue1 >= 0) {
                        if (val.mValue1 == val.mValue2) {
                            value += 1000;
                        } else {
                            value += 1;
                        }
                    }
                }
            }
            if (m.mCoach2 == c) {
                if (val.mValue2 > val.mValue1) {
                    value += 1000000;
                } else {
                    if (val.mValue1 >= 0) {
                        if (val.mValue1 == val.mValue2) {
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

    public static int getPointsByCoach(final Coach c, final Match m) {
        int value = c.mHandicap;
        final Criteria td=Tournament.getTournament().getParams().mCriterias.get(0);
        final Value val = m.mValues.get(td);
        if (m.mCoach1 == c) {
            if (val.mValue1 >= 0) {
                if (val.mValue1 >= val.mValue2 + Tournament.getTournament().getParams().mGapLargeVictory) {
                    value += Tournament.getTournament().getParams().mPointsIndivLargeVictory;
                } else {
                    if (val.mValue1 > val.mValue2) {
                        value += Tournament.getTournament().getParams().mPointsIndivVictory;
                    } else {
                        if (val.mValue1 == val.mValue2) {
                            value += Tournament.getTournament().getParams().mPointsIndivDraw;
                        } else {
                            if (val.mValue1 + Tournament.getTournament().getParams().mGapLittleLost >= val.mValue2) {
                                value += Tournament.getTournament().getParams().mPointsIndivLittleLost;
                            } else {
                                value += Tournament.getTournament().getParams().mPointsIndivLost;
                            }
                        }
                    }
                }

                for (int j = 0; j < Tournament.getTournament().getParams().mCriterias.size(); j++) {
                    final Criteria cri = Tournament.getTournament().getParams().mCriterias.get(j);
                    final Value va = m.mValues.get(cri);
                    value += va.mValue1 * cri.mPointsFor;
                    value += va.mValue2 * cri.mPointsAgainst;
                }

            }
        }
        if (m.mCoach2 == c) {
            if (val.mValue1 >= 0) {
                if (val.mValue2 >= val.mValue1 + Tournament.getTournament().getParams().mGapLargeVictory) {
                    value += Tournament.getTournament().getParams().mPointsIndivLargeVictory;
                } else {
                    if (val.mValue2 > val.mValue1) {
                        value += Tournament.getTournament().getParams().mPointsIndivVictory;
                    } else {
                        if (val.mValue2 == val.mValue1) {
                            value += Tournament.getTournament().getParams().mPointsIndivDraw;
                        } else {
                            if (val.mValue2 + Tournament.getTournament().getParams().mGapLittleLost >= val.mValue1) {
                                value += Tournament.getTournament().getParams().mPointsIndivLittleLost;
                            } else {
                                value += Tournament.getTournament().getParams().mPointsIndivLost;
                            }
                        }
                    }
                }
                for (int j = 0; j < Tournament.getTournament().getParams().mCriterias.size(); j++) {

                    final Criteria cri = Tournament.getTournament().getParams().mCriterias.get(j);
                    final Value va = m.mValues.get(cri);
                    value += va.mValue2 * cri.mPointsFor;
                    value += va.mValue1 * cri.mPointsAgainst;
                }
            }
        }
        return value;
    }

    public static int getValue(final Coach c, final Match m, final Criteria criteria, final int sub_type) {
        int value = 0;
        if (sub_type == Parameters.C_RANKING_SUBTYPE_POSITIVE) {
            if (c == m.mCoach1) {
                value += Math.max(m.mValues.get(criteria).mValue1, 0);
            } else {
                value += Math.max(m.mValues.get(criteria).mValue2, 0);
            }
        } else {
            if (sub_type == Parameters.C_RANKING_SUBTYPE_NEGATIVE) {
                if (c == m.mCoach1) {
                    value += Math.max(m.mValues.get(criteria).mValue2, 0);
                } else {
                    value += Math.max(m.mValues.get(criteria).mValue1, 0);
                }
            } else {
                if (c == m.mCoach1) {
                    value += m.mValues.get(criteria).mValue1 - m.mValues.get(criteria).mValue2;
                } else {
                    value += m.mValues.get(criteria).mValue2 - m.mValues.get(criteria).mValue1;
                }
            }
        }

        return value;
    }

    public static int getSubtypeByValue(final int valueType) {
        int subType = 0;
        if (valueType > Parameters.C_MAX_RANKING) {
            final int value = valueType - Parameters.C_MAX_RANKING - 1;
            subType = value % 3;
        }
        return subType;
    }

    public static Criteria getCriteriaByValue(final int valueType) {
        Criteria criteria = null;

        if (valueType > Parameters.C_MAX_RANKING) {
            final int value = valueType - Parameters.C_MAX_RANKING - 1;
            criteria = Tournament.getTournament().getParams().mCriterias.get(value / 3);
        }
        return criteria;
    }

    /**
     * Find value for criteria and the current match
     */
    public static int getValue(final Coach c, final Match m, final int valueType) {
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
            default:
        }


        return value;
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
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final JTextField jlb = new JTextField();
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

        if (row == mObjects.size() - 1) {
            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
            jlb.setBackground(new Color(50, 50, 200));
            jlb.setForeground(new Color(255, 255, 255));




        }

        if (row == mObjects.size() - 2) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            jlb.setBackground(new Color(100, 100, 200));
            jlb.setForeground(new Color(0, 0, 0));




        }

        if (row == mObjects.size() - 3) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            jlb.setBackground(new Color(150, 150, 200));
            jlb.setForeground(new Color(0, 0, 0));




        }

        jlb.setHorizontalAlignment(JTextField.CENTER);




        return jlb;


    }

    public static String getRankingString(final int rankingType) {
        String result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        final Criteria c = mjtRanking.getCriteriaByValue(rankingType);
        if (c == null) {
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Points");
                    break;
                case Parameters.C_RANKING_NONE:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Nothing");
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Opp. Pts");
                    break;
                case Parameters.C_RANKING_VND:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("V/D/L");
                    break;
                default:
            }
        } else {
            final int subRanking = mjtRanking.getSubtypeByValue(rankingType);
            switch (subRanking) {
                case Parameters.C_RANKING_SUBTYPE_POSITIVE:
                    result = c.mName + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" COACH");
                    break;
                case Parameters.C_RANKING_SUBTYPE_NEGATIVE:
                    result = c.mName + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" ADVERSAIRE");
                    break;
                case Parameters.C_RANKING_SUBTYPE_DIFFERENCE:
                    result = c.mName + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" DIFFERENCE");
                    break;
                default:
            }
        }
        return result;
    }
}
