/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.Formula;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public class MjtMatches extends AbstractTableModel implements TableCellRenderer {

    private final ArrayList<CoachMatch> mMatchs;
    private final boolean mLocked;
    private final boolean mTeamTournament;
    private final boolean mFull;
    private final boolean mNafOnly;

    /**
     *
     * @param matchs
     * @param locked
     * @param teamTournament
     * @param full
     */
    public MjtMatches(final ArrayList<CoachMatch> matchs, final boolean locked, final boolean teamTournament, final boolean full, final boolean nafOnly) {
        mMatchs = new ArrayList<>();
        mLocked = locked;
        mTeamTournament = teamTournament;
        mFull = full;
        mNafOnly = nafOnly;

        if (mNafOnly) {
            for (int i = 0; i < matchs.size(); i++) {
                CoachMatch m = matchs.get(i);
                if (m.isFullNaf()) {
                    mMatchs.add(m);
                }

            }
        } else {
            mMatchs.addAll(matchs);
        }
    }

    @Override
    public int getColumnCount() {
        int res = 0;
        if (mTeamTournament) {
            res = Tournament.getTournament().getParams().getCriteriaCount() * 2 + Tournament.getTournament().getParams().getFormulaCount() * 2 + 5;
        } else {
            res = Tournament.getTournament().getParams().getCriteriaCount() * 2 + Tournament.getTournament().getParams().getFormulaCount() * 2 + 3;
        }

        return res;
    }

    @Override
    public int getRowCount() {
        int count = mMatchs.size();
        return count;
    }

    @Override
    public String getColumnName(final int col) {

        String result = "";
        if (mTeamTournament) {
            switch (col) {
                case 0:
                    result = Translate.translate(Translate.CS_Table);
                    break;
                case 1:
                    result = Translate.translate(Translate.CS_Team) + " 1";
                    break;
                case 2:
                    result = Translate.translate(Translate.CS_Coach) + " 1";
                    break;
                case 3:
                    result = Translate.translate(Translate.CS_Score) + " 1";
                    break;
                case 4:
                    result = Translate.translate(Translate.CS_Score) + " 2";
                    break;
                case 5:
                    result = Translate.translate(Translate.CS_Coach) + " 2";
                    break;
                case 6:
                    result = Translate.translate(Translate.CS_Team) + " 2";
                    break;
                default:
                    if (col < Tournament.getTournament().getParams().getCriteriaCount() * 2 + 5) {
                        final Criteria crit = Tournament.getTournament().getParams().getCriteria((col - 5) / 2);
                        final int ind = (col - 5) % 2;
                        if (ind == 0) {
                            result = crit.getName() + " 1";
                        } else {
                            result = crit.getName() + " 2";
                        }
                    } else {
                        final Formula form = Tournament.getTournament().getParams().getFormula((col - 5 - Tournament.getTournament().getParams().getCriteriaCount() * 2) / 2);
                        final int ind = (col - 5 - Tournament.getTournament().getParams().getCriteriaCount() * 2) % 2;
                        if (ind == 0) {
                            result = form.getName() + " 1";
                        } else {
                            result = form.getName() + " 2";
                        }
                    }
            }
        } else {
            switch (col) {
                case 0:
                    result = Translate.translate(Translate.CS_Table);
                    break;
                case 1:
                    result = Translate.translate(Translate.CS_Coach) + " 1";
                    break;
                case 2:
                    result = Translate.translate(Translate.CS_Score) + " 1";
                    break;
                case 3:
                    result = Translate.translate(Translate.CS_Score) + " 2";
                    break;
                case 4:
                    result = Translate.translate(Translate.CS_Coach) + " 2";
                    break;
                default:
                    if (col < Tournament.getTournament().getParams().getCriteriaCount() * 2 + 3) {
                        final Criteria crit = Tournament.getTournament().getParams().getCriteria((col - 3) / 2);
                        final int ind = (col - 3) % 2;
                        if (ind == 0) {
                            result = crit.getName() + " 1";
                        } else {
                            result = crit.getName() + " 2";
                        }
                    } else {
                        final Formula form = Tournament.getTournament().getParams().getFormula((col - 3 - Tournament.getTournament().getParams().getCriteriaCount() * 2) / 2);
                        final int ind = (col - 3 - Tournament.getTournament().getParams().getCriteriaCount() * 2) % 2;
                        if (ind == 0) {
                            result = form.getName() + " 1";
                        } else {
                            result = form.getName() + " 2";
                        }
                    }
            }
        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object obj = (int) 0;
        final CoachMatch m = mMatchs.get(row);
        Value val;
        int index;
        String rosterName;
        if (mTeamTournament) {
            switch (col) {
                case 0:
                    obj = row + 1;
                    break;
                case 1:
                    obj = ((Coach) m.getCompetitor1()).getTeamMates().getName();
                    break;
                case 2: {
                    String tmp = "";
                    if (m.isConcedeedBy1()) {
                        tmp = "(" + Translate.translate(Translate.CS_Conceeded) + ") ";
                    }
                    if (m.isRefusedBy1()) {
                        tmp = "(" + Translate.translate(Translate.CS_Refused) + ") ";
                    }

                    if (m.getRoster1() == null) {
                        rosterName = ((Coach) m.getCompetitor1()).getRoster().getName();
                    } else {
                        rosterName = m.getRoster1().getName();
                    }
                    if (m.getSubstitute1() != null) {
                        tmp += m.getCompetitor1().getName() + "/" + m.getSubstitute1().getSubstitute().getName() + StringConstants.CS_THICK + rosterName;
                    } else {
                        tmp += m.getCompetitor1().getName() + StringConstants.CS_THICK + rosterName;
                    }
                    obj = tmp;
                }
                break;
                case 3:
                    val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
                    if (val.getValue1() != -1) {
                        obj = val.getValue1();
                    } else {
                        obj = StringConstants.CS_NULL;
                    }
                    break;
                case 4:
                    val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
                    if (val.getValue2() != -1) {
                        obj = val.getValue2();
                    } else {
                        obj = StringConstants.CS_NULL;
                    }
                    break;
                case 5: {
                    String tmp = "";
                    if (m.isConcedeedBy2()) {
                        tmp = "(" + Translate.translate(Translate.CS_Conceeded) + ") ";
                    }
                    if (m.isRefusedBy2()) {
                        tmp = "(" + Translate.translate(Translate.CS_Refused) + ") ";
                    }
                    if (m.getRoster2() == null) {
                        rosterName = ((Coach) m.getCompetitor2()).getRoster().getName();
                    } else {
                        rosterName = m.getRoster2().getName();
                    }
                    if (m.getSubstitute2() != null) {
                        tmp += m.getCompetitor2().getName() + "/" + m.getSubstitute2().getSubstitute().getName() + StringConstants.CS_THICK + rosterName;
                    } else {
                        tmp += m.getCompetitor2().getName() + StringConstants.CS_THICK + rosterName;
                    }
                    obj = tmp;
                }
                break;
                case 6:
                    obj = ((Coach) m.getCompetitor2()).getTeamMates().getName();
                    break;
                default:
                    if (col < Tournament.getTournament().getParams().getCriteriaCount() * 2 + 5) {
                        final Criteria crit = Tournament.getTournament().getParams().getCriteria((col - 5) / 2);
                        val = m.getValue(crit);
                        if ((col - 5) % 2 == 0) {
                            obj = val.getValue1();
                        } else {
                            obj = val.getValue2();
                        }
                    } else {
                        final Formula form = Tournament.getTournament().getParams().getFormula((col - 5 - Tournament.getTournament().getParams().getCriteriaCount() * 2) / 2);
                        val = m.getValue(form);
                        if ((col - 5) % 2 == 0) {
                            obj = val.getValue1();
                        } else {
                            obj = val.getValue2();
                        }
                    }
            }
        } else {
            switch (col) {
                case 0:
                    obj = row + 1;
                    break;
                case 1: {
                    String tmp = "";
                    if (m.isConcedeedBy1()) {
                        tmp = "(" + Translate.translate(Translate.CS_Conceeded) + ") ";
                    }
                    if (m.isRefusedBy1()) {
                        tmp = "(" + Translate.translate(Translate.CS_Refused) + ") ";
                    }
                    if (m.getRoster1() == null) {
                        rosterName = ((Coach) m.getCompetitor1()).getRoster().getName();
                    } else {
                        rosterName = m.getRoster1().getName();
                    }
                    if (m.getSubstitute1() != null) {
                        tmp += m.getCompetitor1().getName() + "/" + m.getSubstitute1().getSubstitute().getName() + StringConstants.CS_THICK + rosterName;
                    } else {
                        tmp += m.getCompetitor1().getName() + StringConstants.CS_THICK + rosterName;
                    }
                    obj = tmp;
                }
                break;
                case 2:
                    val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
                    if (val.getValue1() != -1) {
                        obj = val.getValue1();
                    } else {
                        obj = StringConstants.CS_NULL;
                    }
                    break;
                case 3:
                    val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
                    if (val.getValue2() != -1) {
                        obj = val.getValue2();
                    } else {
                        obj = StringConstants.CS_NULL;
                    }
                    break;
                case 4: {
                    String tmp = "";
                    if (m.isConcedeedBy2()) {
                        tmp = "(" + Translate.translate(Translate.CS_Conceeded) + ") ";;
                    }
                    if (m.isRefusedBy2()) {
                        tmp = "(" + Translate.translate(Translate.CS_Refused) + ") ";;
                    }
                    if (m.getRoster2() == null) {
                        rosterName = ((Coach) m.getCompetitor2()).getRoster().getName();
                    } else {
                        rosterName = m.getRoster2().getName();
                    }
                    if (m.getSubstitute2() != null) {
                        tmp += m.getCompetitor2().getName() + "/" + m.getSubstitute2().getSubstitute().getName() + StringConstants.CS_THICK + rosterName;
                    } else {
                        tmp += m.getCompetitor2().getName() + StringConstants.CS_THICK + rosterName;
                    }
                    obj = tmp;
                }
                break;
                default:
                    if (col < Tournament.getTournament().getParams().getCriteriaCount() * 2 + 3) {
                        final Criteria crit = Tournament.getTournament().getParams().getCriteria((col - 3) / 2);
                        val = m.getValue(crit);
                        if ((col - 3) % 2 == 0) {
                            obj = val.getValue1();
                        } else {
                            obj = val.getValue2();
                        }
                    } else {
                        final Formula form = Tournament.getTournament().getParams().getFormula((col - 3 - Tournament.getTournament().getParams().getCriteriaCount() * 2) / 2);
                        val = m.getValue(form);
                        if ((col - 3) % 2 == 0) {
                            obj = val.getValue1();
                        } else {
                            obj = val.getValue2();
                        }
                    }
            }
        }
        return obj;
    }

    @Override
    public void setValueAt(final Object value, final int row, final int col) {
        final CoachMatch m = mMatchs.get(row);
        if (value != null) {
            Value val;
            String tmp = value.toString();

            if (mTeamTournament) {
                switch (col) {
                    case 3:
                        val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
                        if (tmp.equals("")) {
                            val.setValue1(-1);
                        } else {
                            val.setValue1(Integer.parseInt(tmp));
                            updateFormulasValues(row, 1);
                        }
                        break;
                    case 4:
                        val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
                        if (tmp.equals("")) {
                            val.setValue2(-1);
                        } else {
                            val.setValue2(Integer.parseInt(tmp));
                            updateFormulasValues(row, 2);
                            /**
                             * Update Formulas
                             */
                        }
                        break;
                    default:
                        final int index = (col - 5) / 2;
                        val = m.getValue(Tournament.getTournament().getParams().getCriteria(index));
                        if ((col - 5) % 2 == 0) {
                            val.setValue1(Integer.parseInt(tmp));
                            updateFormulasValues(row, 1);
                        } else {
                            val.setValue2(Integer.parseInt(tmp));
                            updateFormulasValues(row, 2);
                        }
                }
            } else {
                switch (col) {
                    case 2:
                        val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
                        if (tmp.equals("")) {
                            val.setValue1(-1);
                        } else {
                            val.setValue1(Integer.parseInt(tmp));
                            updateFormulasValues(row, 1);
                        }
                        break;
                    case 3:
                        val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
                        if (tmp.equals("")) {
                            val.setValue2(-1);
                        } else {
                            val.setValue2(Integer.parseInt(tmp));
                            updateFormulasValues(row, 2);
                        }
                        break;
                    default:
                        final int index = (col - 3) / 2;
                        val = m.getValue(Tournament.getTournament().getParams().getCriteria(index));
                        if ((col - 3) % 2 == 0) {
                            val.setValue1(Integer.parseInt(tmp));
                            updateFormulasValues(row, 1);
                        } else {
                            val.setValue2(Integer.parseInt(tmp));
                            updateFormulasValues(row, 2);
                        }
                }
            }

        }
        m.setRemotely(false);
        m.resetWL();
        m.recomputeValues();

        if (mTeamTournament) {
            for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
                Round r = Tournament.getTournament().getRound(i);
                for (int j = 0; j < r.getMatchsCount(); j++) {
                    Match match = r.getMatch(j);
                    if (match instanceof TeamMatch) {
                        for (int k = 0; k < ((TeamMatch) match).getMatchCount(); k++) {
                            if (((TeamMatch) match).containsMatch(m)) {
                                ((TeamMatch) match).resetWL();
                                ((TeamMatch) match).recomputeValues();
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        fireTableDataChanged();
        MainFrame.getMainFrame().updateMenus();

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
                if ((col == 1) || (col == 2) || (col == 5) || (col == 6) || (col == 0) || (col > 5 + Tournament.getTournament().getParams().getCriteriaCount()*2)) {
                    result = false;
                }
            } else {
                if ((col == 1) || (col == 4) || (col == 0) || (col > 3 + Tournament.getTournament().getParams().getCriteriaCount()*2)) {
                    result = false;
                }
            }
        }
        return result;
    }

    @Override
    public Component getTableCellRendererComponent(
            final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final JTextField jlb = new JTextField();

        jlb.setEditable(false);

        boolean useColor = false;

        useColor = Tournament.getTournament().getParams().isUseColor();

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
            Value val = null;
            CoachMatch m = mMatchs.get(row);
            val = mMatchs.get(row).getValue(Tournament.getTournament().getParams().getCriteria(0));
            if (mTeamTournament) {

                switch (column) {
                    case 0:
                        if (!useColor) {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }
                        break;
                    case 1:
                        if (useColor) {
                            bkg = new Color(200, 150, 150);
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }
                        if (val != null) {
                            //frg = new Color(255, 255, 255);
                            if (val.getValue1() > val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                            }

                            if (val.getValue1() == val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                            }

                        }
                        break;
                    case 2:
                        if (useColor) {
                            bkg = new Color(200, 150, 150);
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }
                        if (val != null) {
                            //frg = new Color(255, 255, 255);
                            if (val.getValue1() > val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                            }

                            if (val.getValue1() == val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                            }
                        }
                        if (m.isConcedeedBy1()) {
                            frg = new Color(50, 50, 50);
                        }
                        if (m.isRefusedBy1()) {
                            frg = new Color(150, 50, 50);
                        }

                        break;
                    case 3:
                        if (useColor) {
                            bkg = new Color(250, 200, 200);
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }

                        if (val.getValue1() > Tournament.getTournament().getParams().getCriteria(0).getCriticalThreshold()) {
                            if (useColor) {
                                bkg = Color.RED;
                            } else {
                                bkg = Color.DARK_GRAY;
                            }
                            frg = Color.WHITE;
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }
                        break;
                    case 4:
                        if (useColor) {
                            bkg = new Color(200, 200, 250);
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }
                        if (val.getValue2() > Tournament.getTournament().getParams().getCriteria(0).getCriticalThreshold()) {
                            if (useColor) {
                                bkg = Color.BLUE;
                            } else {
                                bkg = Color.DARK_GRAY;
                            }
                            frg = Color.WHITE;
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }

                        break;

                    case 5:
                        if (useColor) {
                            bkg = new Color(150, 150, 220);
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }

                        if (val != null) {
                            //frg = new Color(255, 255, 255);
                            if (val.getValue1() < val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                            }
                            if (val.getValue1() == val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                            }
                        }
                        if (m.isConcedeedBy2()) {
                            frg = new Color(50, 50, 50);
                        }
                        if (m.isRefusedBy2()) {
                            frg = new Color(150, 50, 50);
                        }
                        break;
                    case 6:
                        if (useColor) {
                            bkg = new Color(150, 150, 220);
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }

                        if (val != null) {
                            // frg = new Color(255, 255, 255);
                            if (val.getValue1() < val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                            }

                            if (val.getValue1() == val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                            }
                        }
                        break;
                    default:
                        if (useColor) {
                            if (column % 2 > 0) {
                                frg = new Color(150, 50, 50);
                            } else {
                                frg = new Color(50, 50, 150);
                            }
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }
                        int CritIndex = (column - 5) / 2;
                        int ValIndex = (column - 5) % 2;

                        if (CritIndex < Tournament.getTournament().getParams().getCriteriaCount()) {
                            Criteria crit = Tournament.getTournament().getParams().getCriteria(CritIndex);
                            Value v = m.getValue(crit);
                            if (ValIndex == 0) {
                                if (v.getValue1() > crit.getCriticalThreshold()) {
                                    if (useColor) {
                                        bkg = Color.RED;
                                    } else {
                                        bkg = Color.DARK_GRAY;
                                    }
                                    frg = Color.WHITE;
                                    jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                                }
                            }
                            if (ValIndex == 1) {
                                if (v.getValue2() > crit.getCriticalThreshold()) {
                                    if (useColor) {
                                        bkg = Color.BLUE;
                                    } else {
                                        bkg = Color.DARK_GRAY;
                                    }
                                    frg = Color.WHITE;
                                    jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                                }
                            }
                        } 
                }
            } else {
                switch (column) {
                    case 0:
                        if (!useColor) {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }
                        break;
                    case 1:
                        if (useColor) {
                            bkg = new Color(200, 150, 150);
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }

                        if (val != null) {
                            //frg = new Color(255, 255, 255);
                            if (val.getValue1() > val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                            }

                            if (val.getValue1() == val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                            }
                        }
                        if (m.isConcedeedBy1()) {
                            frg = new Color(50, 50, 50);
                        }
                        if (m.isRefusedBy1()) {
                            frg = new Color(150, 50, 50);
                        }
                        if (val.getValue1() > Tournament.getTournament().getParams().getCriteria(0).getCriticalThreshold()) {
                            if (useColor) {
                                bkg = Color.RED;
                            } else {
                                bkg = Color.DARK_GRAY;
                            }
                            frg = Color.WHITE;
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }
                        break;
                    case 2:
                        if (useColor) {
                            bkg = new Color(250, 200, 200);
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }
                        if (val.getValue2() > Tournament.getTournament().getParams().getCriteria(0).getCriticalThreshold()) {
                            if (useColor) {
                                bkg = Color.BLUE;
                            } else {
                                bkg = Color.DARK_GRAY;
                            }
                            frg = Color.WHITE;
                            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                        }
                        break;
                    case 3:
                        if (useColor) {
                            bkg = new Color(200, 200, 250);
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }
                        break;
                    case 4:
                        if (useColor) {
                            bkg = new Color(150, 150, 200);
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }

                        if (val != null) {
                            //frg = new Color(255, 255, 255);
                            if (val.getValue1() < val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                            }
                            if (val.getValue1() == val.getValue2()) {
                                jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                            }
                        }
                        if (m.isConcedeedBy2()) {
                            frg = new Color(50, 50, 50);
                        }
                        if (m.isRefusedBy2()) {
                            frg = new Color(150, 50, 50);
                        }
                        break;

                    default:
                        if (useColor) {
                            if (column % 2 > 0) {
                                frg = new Color(150, 50, 50);
                            } else {
                                frg = new Color(50, 50, 150);
                            }
                        } else {
                            if (row % 2 != 0) {
                                bkg = new Color(220, 220, 220);
                            }
                        }
                        int CritIndex = (column - 3) / 2;
                        int ValIndex = (column - 3) % 2;
                        Criteria crit = Tournament.getTournament().getParams().getCriteria(CritIndex);
                        Value v = m.getValue(crit);
                        if (ValIndex == 0) {
                            if (v.getValue1() > crit.getCriticalThreshold()) {
                                if (useColor) {
                                    bkg = Color.RED;
                                } else {
                                    bkg = Color.DARK_GRAY;
                                }
                                frg = Color.WHITE;
                                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                            }
                        }
                        if (ValIndex == 1) {
                            if (v.getValue2() > crit.getCriticalThreshold()) {
                                if (useColor) {
                                    bkg = Color.BLUE;
                                } else {
                                    bkg = Color.DARK_GRAY;
                                }
                                frg = Color.WHITE;
                                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                            }
                        }
                }
            }
            if (m.isRemotely()) {
                frg = Color.BLUE;
            }
        }

        jlb.setBackground(bkg);

        jlb.setForeground(frg);

        jlb.setHorizontalAlignment(JTextField.CENTER);

        if (Tournament.getTournament()
                .getParams().isUseImage()) {
            if ((column == 1) && (Tournament.getTournament().getParams().isTeamTournament())) {

                CoachMatch m = mMatchs.get(row);
                if (((Coach) m.getCompetitor1()).getTeamMates().getPicture() != null) {
                    JLabel obj = new JLabel();
                    ImageIcon icon = ImageTreatment.resize(((Coach) m.getCompetitor1()).getTeamMates().getPicture(), 30, 30);
                    obj.setIcon(icon);
                    obj.setText((String) value);
                    obj.setOpaque(true);
                    obj.setBackground(bkg);
                    return obj;
                }
            }

            if ((column == 6) && (Tournament.getTournament().getParams().isTeamTournament())) {

                CoachMatch m = mMatchs.get(row);
                if (((Coach) m.getCompetitor2()).getTeamMates().getPicture() != null) {
                    JLabel obj = new JLabel();
                    ImageIcon icon = ImageTreatment.resize(((Coach) m.getCompetitor2()).getTeamMates().getPicture(), 30, 30);
                    obj.setIcon(icon);
                    obj.setText((String) value);
                    obj.setOpaque(true);
                    obj.setBackground(bkg);
                    return obj;
                }
            }
        }

        return jlb;
    }

    private void updateFormulasValues(int row, int side) {
        for (int i = 0; i < Tournament.getTournament().getParams().getFormulaCount(); i++) {
            Formula f = Tournament.getTournament().getParams().getFormula(i);
            CoachMatch cm = this.mMatchs.get(row);
            Value val = cm.getValue(f);

            
            int ivalue=f.evaluate(cm.getValues(),side);
            
            if (side==1)
            {
                val.setValue1(ivalue);
            }
            else
            {
                val.setValue2(ivalue);
            }
            
        }
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
