/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.Match;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Criteria;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class mjtMatches extends AbstractTableModel implements TableCellRenderer {

    ArrayList<Match> mMatchs;
    boolean mLocked;
    boolean mTeamTournament;
    boolean mFull;

    public mjtMatches(final ArrayList<Match> matchs, final boolean locked, final boolean teamTournament, final boolean full) {
        mMatchs = matchs;
        mLocked = locked;
        mTeamTournament = teamTournament;
        mFull = full;
    }

    public int getColumnCount() {
        int res;
        if (mTeamTournament) {
            res = Tournament.getTournament().getParams().mCriterias.size() * 2 + 5;
        } else {
            res = Tournament.getTournament().getParams().mCriterias.size() * 2 + 3;
        }
        return res;
    }

    public int getRowCount() {
        return mMatchs.size();
    }

    public String getColumnName(final int col) {

        String result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        if (mTeamTournament) {
            switch (col) {
                case 0:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Table");
                    break;
                case 1:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team1");
                    break;
                case 2:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Coach1");
                    break;
                case 3:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Score1");
                    break;
                case 4:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Score2");
                    break;
                case 5:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Coach2");
                    break;
                case 6:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team2");
                    break;
                default:
                    final Criteria crit = Tournament.getTournament().getParams().mCriterias.get((col - 5) / 2);
                    final int ind = (col - 5) % 2;
                    if (ind == 0) {
                        result = crit.mName + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" 1");
                    } else {
                        result = crit.mName + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" 2");
                    }
            }
        } else {
            switch (col) {
                case 0:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Table");
                    break;
                case 1:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Coach1");
                    break;
                case 2:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Score1");
                    break;
                case 3:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Score2");
                    break;
                case 4:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Coach2");
                    break;
                default:
                    final Criteria crit = Tournament.getTournament().getParams().mCriterias.get((col - 3) / 2);
                    if ((col - 3) % 2 > 0) {
                        result = crit.mName + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" 2");
                    } else {
                        result = crit.mName + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" 1");
                    }
            }
        }
        return result;
    }

    public Object getValueAt(final int row, final int col) {
        Object obj = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        final Match m = mMatchs.get(row);
        Value val;
        int index;
        String rosterName;
        if (mTeamTournament) {
            switch (col) {
                case 0:
                    obj = row + 1;
                    break;
                case 1:
                    obj = m.mCoach1.mTeamMates.mName;
                    break;
                case 2:
                    if (m.mRoster1 == null) {
                        rosterName = m.mCoach1.mRoster.mName;
                    } else {
                        rosterName = m.mRoster1.mName;
                    }
                    obj = m.mCoach1.mName + StringConstants.CS_THICK + rosterName;
                    break;
                case 3:
                    val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
                    if (val.mValue1 >= 0) {
                        obj = val.mValue1;
                    } else {
                        obj = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
                    }
                    break;
                case 4:
                    val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
                    if (val.mValue2 >= 0) {
                        obj = val.mValue2;
                    } else {
                        obj = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
                    }
                    break;
                case 5:
                    if (m.mRoster2 == null) {
                        rosterName = m.mCoach2.mRoster.mName;
                    } else {
                        rosterName = m.mRoster2.mName;
                    }
                    obj = m.mCoach2.mName + StringConstants.CS_THICK + rosterName;
                    break;
                case 6:
                    obj = m.mCoach2.mTeamMates.mName;
                    break;
                default:
                    index = (col - 5) / 2;
                    final Criteria crit = Tournament.getTournament().getParams().mCriterias.get(index);
                    val = m.mValues.get(crit);
                    if ((col - 5) % 2 == 0) {
                        obj = val.mValue1;
                    } else {
                        obj = val.mValue2;
                    }
            }
        } else {
            switch (col) {
                case 0:
                    obj = row + 1;
                    break;
                case 1:
                    if (m.mRoster1 == null) {
                        rosterName = m.mCoach1.mRoster.mName;
                    } else {
                        rosterName = m.mRoster1.mName;
                    }
                    obj = m.mCoach1.mName + StringConstants.CS_THICK + rosterName;
                    break;
                case 2:
                    val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
                    if (val.mValue1 >= 0) {
                        obj = val.mValue1;
                    } else {
                        obj = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
                    }
                    break;
                case 3:
                    val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
                    if (val.mValue2 >= 0) {
                        obj = val.mValue2;
                    } else {
                        obj = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
                    }
                    break;
                case 4:
                    if (m.mRoster2 == null) {
                        rosterName = m.mCoach2.mRoster.mName;
                    } else {
                        rosterName = m.mRoster2.mName;
                    }
                    obj = m.mCoach2.mName + StringConstants.CS_THICK + rosterName;
                    break;
                default:
                    index = (col - 3) / 2;
                    final Criteria crit = Tournament.getTournament().getParams().mCriterias.get(index);
                    val = m.mValues.get(crit);
                    if ((col - 3) % 2 == 0) {
                        obj = val.mValue1;
                    } else {
                        obj = val.mValue2;
                    }
            }
        }
        return obj;
    }

    @Override
    public void setValueAt(final Object value, final int row, final int col) {
        if (value != null) {
            Value val;
            final Match m = mMatchs.get(row);
            if (mTeamTournament) {
                switch (col) {
                    case 3:
                        val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
                        val.mValue1 = Integer.valueOf(value.toString());
                        break;
                    case 4:
                        val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
                        val.mValue2 = Integer.valueOf(value.toString());
                        break;
                    default:
                        final int index = (col - 5) / 2;
                        val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(index));
                        if ((col - 5) % 2 == 0) {
                            val.mValue1 = Integer.valueOf(value.toString());
                        } else {
                            val.mValue2 = Integer.valueOf(value.toString());
                        }
                }
            } else {
                switch (col) {
                    case 2:
                        val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
                        val.mValue1 = Integer.valueOf(value.toString());
                        break;
                    case 3:
                        val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
                        val.mValue2 = Integer.valueOf(value.toString());
                        break;
                    default:
                        final int index = (col - 3) / 2;
                        val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(index));
                        if ((col - 3) % 2 == 0) {
                            val.mValue1 = Integer.valueOf(value.toString());
                        } else {
                            val.mValue2 = Integer.valueOf(value.toString());
                        }
                }
            }
            m.resetWL();
        }
        fireTableDataChanged();
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

        boolean result = true;
        if (mLocked) {
            result = false;
        } else {
            if (mTeamTournament) {
                if ((col == 1) || (col == 2) || (col == 5) || (col == 6)) {
                    result = false;
                }
            } else {
                if ((col == 1) || (col == 4)) {
                    result = false;
                }
            }
        }
        return result;
    }

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

        Color bkg = new Color(255, 255, 255);
        Color frg = new Color(0, 0, 0);


        if (isSelected) {
            bkg = new Color(200, 200, 200);
        } else {
            Value val;
            val = mMatchs.get(row).mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
            if (mTeamTournament) {

                switch (column) {
                    case 1:
                        bkg = new Color(200, 50, 50);
                        frg = new Color(255, 255, 255);
                        if (val.mValue1 > val.mValue2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (val.mValue1 == val.mValue2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }

                        break;
                    case 2:
                        bkg = new Color(200, 50, 50);
                        frg = new Color(255, 255, 255);
                        if (val.mValue1 > val.mValue2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (val.mValue1 == val.mValue2) {
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
                        if (val.mValue1 < val.mValue2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }
                        if (val.mValue1 == val.mValue2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }
                        break;
                    case 6:
                        bkg = new Color(50, 50, 250);
                        frg = new Color(255, 255, 255);

                        if (val.mValue1 < val.mValue2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (val.mValue1 == val.mValue2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }
                        break;
                    default:
                        if (column % 2 > 0) {
                            frg = new Color(150, 50, 50);
                        } else {
                            frg = new Color(50, 50, 150);
                        }
                }
            } else {
                switch (column) {
                    case 1:
                        bkg = new Color(200, 50, 50);
                        frg = new Color(255, 255, 255);
                        if (val.mValue1 > val.mValue2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        if (val.mValue1 == val.mValue2) {
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
                        if (val.mValue1 < val.mValue2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }
                        if (val.mValue1 == val.mValue2) {
                            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                        }

                        break;

                    default:
                        if (column % 2 > 0) {
                            frg = new Color(150, 50, 50);
                        } else {
                            frg = new Color(50, 50, 150);
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
