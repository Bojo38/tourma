/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import tourma.data.Round;
import tourma.data.Match;
import tourma.data.Coach;
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
import tourma.data.CoachAnnexRanking;

/**
 *
 * @author Frederic Berger
 */
public class mjtAnnexRank extends mjtRanking {

    int _ranking_type;
    public static final int C_MOST_TD_POS = 0;
    public static final int C_MOST_TD_NEG = 1;
    public static final int C_MOST_SOR_POS = 2;
    public static final int C_MOST_SOR_NEG = 3;
    public static final int C_MOST_FOUL_POS = 4;
    public static final int C_MOST_FOUL_NEG = 5;
    boolean _full_ranking;
    Vector<CoachAnnexRanking> _datas;
    public mjtAnnexRank(Vector<Round> rounds, int ranking_type, Vector<Coach> coachs, boolean full, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5) {
        super(rounds, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, coachs);
        _rounds = rounds;
        _ranking_type = ranking_type;
        _coachs = coachs;
        _full_ranking = full;
        _ranking_type1 = ranking_type1;
        _ranking_type2 = ranking_type2;
        _ranking_type3 = ranking_type3;
        _ranking_type4 = ranking_type4;
        _ranking_type5 = ranking_type5;

        Vector<Match> matchs = new Vector<Match>();
        for (int i = 0; i < _rounds.size(); i++) {
            for (int j = 0; j < _rounds.get(i).getMatchs().size(); j++) {
                matchs.add(_rounds.get(i).getMatchs().get(j));
            }
        }

        _datas = new Vector<CoachAnnexRanking>();

        for (int i = 0; i < _coachs.size(); i++) {
            Coach c = _coachs.get(i);
            int value = 0;
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int j = 0; j < matchs.size(); j++) {
                Match m = matchs.get(j);
                if (m._coach1 == c) {
                    switch (_ranking_type) {
                        case C_MOST_TD_POS:
                            value += m._td1;
                            break;
                        case C_MOST_TD_NEG:
                            value += m._td2;
                            break;
                        case C_MOST_SOR_POS:
                            value += m._sor1;
                            break;
                        case C_MOST_SOR_NEG:
                            value += m._sor2;
                            break;
                        case C_MOST_FOUL_POS:
                            value += m._foul1;
                            break;
                        case C_MOST_FOUL_NEG:
                            value += m._foul2;
                            break;
                    }
                }
                if (m._coach2 == c) {
                    switch (_ranking_type) {
                        case C_MOST_TD_POS:
                            value += m._td2;
                            break;
                        case C_MOST_TD_NEG:
                            value += m._td1;
                            ;
                            break;
                        case C_MOST_SOR_POS:
                            value += m._sor2;
                            break;
                        case C_MOST_SOR_NEG:
                            value += m._sor1;
                            break;
                        case C_MOST_FOUL_POS:
                            value += m._foul2;
                            break;
                        case C_MOST_FOUL_NEG:
                            value += m._foul1;
                            break;
                    }
                }
                value1 += getValue(c, m, _ranking_type1);
                value2 += getValue(c, m, _ranking_type2);
                value3 += getValue(c, m, _ranking_type3);
                value4 += getValue(c, m, _ranking_type4);
                value5 += getValue(c, m, _ranking_type5);
            }
            _datas.add(new CoachAnnexRanking(c, value,value1,value2,value3,value4,value5));
        }

        Collections.sort(_datas);
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public int getRowCount() {
        if (_full_ranking) {
            return _coachs.size();
        } else {
            return 3;
        }
    }

    @Override
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
                switch (_ranking_type) {
                    case C_MOST_TD_POS:
                        return "Tds";
                    case C_MOST_TD_NEG:
                        return "Tds";
                    case C_MOST_SOR_POS:
                        return "Sor";
                    case C_MOST_SOR_NEG:
                        return "Sor";
                    case C_MOST_FOUL_POS:
                        return "Foul";
                    case C_MOST_FOUL_NEG:
                        return "Foul";
                }
        }
        return "";
    }

    @Override
    public Object getValueAt(int row, int col) {
        

        switch (col) {
            case 0:
                return row + 1;
            case 1:
                return _datas.get(row)._coach._team;
            case 2:
                return _datas.get(row)._coach._name;
            case 3:
                return _datas.get(row)._coach._roster;
            case 4:
                return _datas.get(row)._value;
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

        if ((_ranking_type == C_MOST_FOUL_POS) ||
                (_ranking_type == C_MOST_SOR_POS) ||
                (_ranking_type == C_MOST_TD_POS)) {
            if (row == 0) {
                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                jlb.setBackground(new Color(200, 50, 50));
                jlb.setForeground(new Color(255, 255, 255));
            }
        }

        if ((_ranking_type == C_MOST_FOUL_NEG) ||
                (_ranking_type == C_MOST_SOR_NEG) ||
                (_ranking_type == C_MOST_TD_NEG)) {
            if (row == 0) {
                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                jlb.setBackground(new Color(50, 50, 200));
                jlb.setForeground(new Color(255, 255, 255));
            }
        }

        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
