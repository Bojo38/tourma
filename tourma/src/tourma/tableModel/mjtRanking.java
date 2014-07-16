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
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Round;
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
    public static final int C_STARTING_RANK = 1000;
    public static final int C_ELO_K = 256;

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
        return mDatas.size();
    }

    public static int getOppPointsByCoach(final Coach c, final CoachMatch m) {

        int index = 0;
        CoachMatch tmp_m = (CoachMatch) c.mMatchs.get(index);
        while (tmp_m != m) {
            index++;
            tmp_m = (CoachMatch) c.mMatchs.get(index);
        }

        int value = 0;
        Competitor opponent;
        if (m.mCompetitor1 == c) {
            opponent = m.mCompetitor2;
        } else {
            opponent = m.mCompetitor1;
        }

        for (int i = 0; i < ((Coach) opponent).mMatchs.size(); i++) {
            value += getPointsByCoach((Coach) opponent, (CoachMatch) ((Coach) opponent).mMatchs.get(i));
        }

        return value;
    }

    public static int getCoachNbMatchs(final Coach c, final CoachMatch m) {
        int index = c.mMatchs.indexOf(m);
        return index + 1;
    }

    public static int getOppELOByCoach(final Coach c, final CoachMatch m) {
        int value = 0;
        Competitor opponent;
        if (m.mCompetitor1 == c) {
            opponent = m.mCompetitor2;
        } else {
            opponent = m.mCompetitor1;
        }
        value = getELOByCoach((Coach) opponent, m);

        return value;
    }

    public static int getVNDByCoach(final Coach c, final CoachMatch m) {
        int value = 0;
        final Value val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
        if (val.mValue1 >= 0) {
            if (m.mCompetitor1 == c) {
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
            if (m.mCompetitor2 == c) {
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

    public static int getELOByCoach(final Coach c, final CoachMatch m) {
        int value = 0;

        Value val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
        if (val.mValue1 >= 0) {
            // Get current Round index
            int indexRound = -1;
            for (int i = 0; i < Tournament.getTournament().getRounds().size(); i++) {
                Round r = Tournament.getTournament().getRounds().get(i);
                if (r.getCoachMatchs().contains(m)) {
                    indexRound = i;
                    break;
                }
            }

            int lastPlayerRank = C_STARTING_RANK;
            int lastOppRank = C_STARTING_RANK;
            Coach opp = null;
            int tdPlayer = 0;
            int tdOpp = 0;
            if (m.mCompetitor1 == c) {
                opp = (Coach) m.mCompetitor2;
                tdPlayer = val.mValue1;
                tdOpp = val.mValue2;
            }
            if (m.mCompetitor2 == c) {
                opp = (Coach) m.mCompetitor1;
                tdOpp = val.mValue1;
                tdPlayer = val.mValue2;
            }
            if (indexRound >= 0) {
                // Find Previous Match for current player
                int index = c.mMatchs.indexOf(m);
                if (index > 0) {
                    lastPlayerRank = getELOByCoach(c, (CoachMatch) c.mMatchs.get(index - 1));
                }

                // Find Previous Match for oponnent player

                index = opp.mMatchs.indexOf(m);
                if (index > 0) {
                    lastPlayerRank = getELOByCoach(opp, (CoachMatch) opp.mMatchs.get(index - 1));
                }
            }

            double EA = 1 / (1 + Math.pow(10.0, (lastOppRank - lastPlayerRank) / 200));
            double EB = 1 / (1 + Math.pow(10.0, (lastPlayerRank - lastOppRank) / 200));

            // Compute real score

            // Victory is 1000
            // All bonuses to 1
            double SA = 0;
            if (tdPlayer > tdOpp) {
                SA = 1000;
            }
            if (tdPlayer < tdOpp) {
                SA = 0;
            }
            if (tdPlayer == tdOpp) {
                SA = 500;
            }

            // Add/Remove Bonuses
            for (int i = 0; i < Tournament.getTournament().getParams().mCriterias.size(); i++) {
                Criteria crit = Tournament.getTournament().getParams().mCriterias.get(i);
                val = m.mValues.get(crit);
                if (m.mCompetitor1 == c) {
                    SA += val.mValue1 * crit.mPointsFor;
                    SA -= val.mValue2 * crit.mPointsFor;

                    SA -= val.mValue1 * crit.mPointsAgainst;
                    SA += val.mValue2 * crit.mPointsAgainst;
                }
                if (m.mCompetitor2 == c) {
                    SA -= val.mValue1 * crit.mPointsFor;
                    SA += val.mValue2 * crit.mPointsFor;

                    SA -= val.mValue2 * crit.mPointsAgainst;
                    SA += val.mValue1 * crit.mPointsAgainst;
                }
            }
            double diff = SA / 1000 - EA;
            value = Math.round((float) (lastPlayerRank + C_ELO_K * diff));
        }
        return value;
    }

    public static int getPointsByCoach(final Coach c, final CoachMatch m) {
        int value = 0;
        if (c.mMatchs.indexOf(m) == 0) {
            value += c.mHandicap;
        }
        final Criteria td = Tournament.getTournament().getParams().mCriterias.get(0);
        final Value val = m.mValues.get(td);
        if (m.mCompetitor1 == c) {
            if (m.concedeedBy1) {
                value += Tournament.getTournament().getParams().mPointsConcedeed;
            } else {
                if (m.refusedBy1) {
                    value += Tournament.getTournament().getParams().mPointsRefused;
                } else {
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
            }
        }
        if (m.mCompetitor2 == c) {
            if (m.concedeedBy2) {
                value += Tournament.getTournament().getParams().mPointsConcedeed;
            } else {
                if (m.refusedBy2) {
                    value += Tournament.getTournament().getParams().mPointsRefused;
                } else {
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
            }
        }
        return value;
    }

    public static int getValue(final Coach c, final CoachMatch m, final Criteria criteria, final int sub_type) {
        int value = 0;
        if (sub_type == Parameters.C_RANKING_SUBTYPE_POSITIVE) {
            if (c == m.mCompetitor1) {
                value += Math.max(m.mValues.get(criteria).mValue1, 0);
            } else {
                value += Math.max(m.mValues.get(criteria).mValue2, 0);
            }
        } else {
            if (sub_type == Parameters.C_RANKING_SUBTYPE_NEGATIVE) {
                if (c == m.mCompetitor1) {
                    value += Math.max(m.mValues.get(criteria).mValue2, 0);
                } else {
                    value += Math.max(m.mValues.get(criteria).mValue1, 0);
                }
            } else {
                if (c == m.mCompetitor1) {
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
    public static int getValue(final Coach c, final CoachMatch m, final int valueType, int lastValue) {
        int value = 0;

        switch (valueType) {
            case Parameters.C_RANKING_POINTS:
                value = lastValue + getPointsByCoach(c, m);
                break;
            case Parameters.C_RANKING_NONE:
                value = lastValue + 0;
                break;
            case Parameters.C_RANKING_OPP_POINTS:
                value = lastValue + getOppPointsByCoach(c, m);
                break;
            case Parameters.C_RANKING_VND:
                value = lastValue + getVNDByCoach(c, m);
                break;
            case Parameters.C_RANKING_ELO:
                value = getELOByCoach(c, m);
                break;
            case Parameters.C_RANKING_ELO_OPP:
                value = lastValue + getOppELOByCoach(c, m);
                break;
            case Parameters.C_RANKING_NB_MATCHS:
                value = getCoachNbMatchs(c, m);
                break;
            default:
                value = lastValue;
                break;
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
        boolean useColor = Tournament.getTournament().getParams().useColor;
        if (!useColor) {
            if (row % 2 == 1) {
                jlb.setBackground(new Color(220, 220, 220));
            }
        }

        if (value instanceof String) {
            jlb.setText((String) value);
        }
        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }

        if (row == 0) {
            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
            if (useColor) {
                jlb.setBackground(new Color(200, 50, 50));
                jlb.setForeground(new Color(255, 255, 255));
            }
        }

        if (row == 1) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            if (useColor) {
                jlb.setBackground(new Color(200, 100, 100));
                jlb.setForeground(new Color(0, 0, 0));
            }
        }

        if (row == 2) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            if (useColor) {
                jlb.setBackground(new Color(200, 150, 150));
                jlb.setForeground(new Color(0, 0, 0));
            }
        }

        if ((row == mObjects.size() - 1) && (mObjects.size() > 3)) {
            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
            if (useColor) {
                jlb.setBackground(new Color(50, 50, 200));
                jlb.setForeground(new Color(255, 255, 255));
            }
        }

        if ((row == mObjects.size() - 2) && (mObjects.size() > 4)) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            if (useColor) {
                jlb.setBackground(new Color(100, 100, 200));
                jlb.setForeground(new Color(0, 0, 0));
            }
        }

        if ((row == mObjects.size() - 3) && (mObjects.size() > 5)) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            if (useColor) {
                jlb.setBackground(new Color(150, 150, 200));
                jlb.setForeground(new Color(0, 0, 0));
            }
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
                case Parameters.C_RANKING_ELO:
                    result = "ELO";
                    break;
                case Parameters.C_RANKING_ELO_OPP:
                    result = "ELO Adversaires";
                    break;
                case Parameters.C_RANKING_NB_MATCHS:
                    result = "Nb Matchs";
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
