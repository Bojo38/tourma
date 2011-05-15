/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

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
import tourma.data.Criteria;
import tourma.data.ObjectAnnexRanking;

/**
 *
 * @author Frederic Berger
 */
abstract public class mjtAnnexRank extends mjtRanking {

    //int _ranking_type;
    /*public static final int C_MOST_TD_POS = 0;
    public static final int C_MOST_TD_NEG = 1;
    public static final int C_MOST_SOR_POS = 2;
    public static final int C_MOST_SOR_NEG = 3;
    public static final int C_MOST_FOUL_POS = 4;
    public static final int C_MOST_FOUL_NEG = 5;
    public static final int C_MOST_PAS_POS = 6;
    public static final int C_MOST_PAS_NEG = 7;
    public static final int C_MOST_INT_POS = 8;
    public static final int C_MOST_INT_NEG = 9;*/
    boolean _full_ranking;
    Criteria _criteria;
    int _subtype;

    public mjtAnnexRank(int round, Criteria criteria, int subtype, Vector objects, boolean full, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, objects);
        _criteria = criteria;
        _subtype = subtype;
        //_ranking_type = ranking_type;
        _full_ranking = full;
        sortDatas();
    }

    abstract protected void sortDatas();

    @Override
    abstract public int getColumnCount();

    @Override
    public int getRowCount() {
        if (_full_ranking) {
            return _objects.size();
        } else {
            return Math.min(3, _objects.size());
        }
    }

    @Override
    abstract public String getColumnName(int col);

    @Override
    abstract public Object getValueAt(int row, int col);

    @Override
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

        if (_subtype == 0) {
            if (row == 0) {
                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                jlb.setBackground(new Color(200, 50, 50));
                jlb.setForeground(new Color(255, 255, 255));
            }
        }

        if (_subtype == 2) {
            if (row == 0) {
                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                jlb.setBackground(new Color(50, 200, 50));
                jlb.setForeground(new Color(255, 255, 255));
            }
        }

        if (_subtype == 1) {
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
