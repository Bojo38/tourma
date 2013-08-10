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
import tourma.data.Criteria;

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
    boolean mFullRanking;
    Criteria mCriteria;
    int mSubtype;

    public mjtAnnexRank(final int round, final Criteria criteria, final int subtype, final ArrayList objects, final boolean full, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, objects);
        mCriteria = criteria;
        mSubtype = subtype;
        //_ranking_type = ranking_type;
        mFullRanking = full;
        sortDatas();
    }

    abstract protected void sortDatas();

    @Override
    abstract public int getColumnCount();

    @Override
    public int getRowCount() {
        int result = Math.min(3, mObjects.size());
        if (mFullRanking) {
            result = mObjects.size();
        }
        return result;
    }

    @Override
    abstract public String getColumnName(int col);

    @Override
    abstract public Object getValueAt(int row, int col);

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value,  final boolean  isSelected,final  boolean  hasFocus, final int row, final int column) {
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

        if (mSubtype == 0) {
            if (row == 0) {
                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                jlb.setBackground(new Color(200, 50, 50));
                jlb.setForeground(new Color(255, 255, 255));
            }
        }

        if (mSubtype == 2) {
            if (row == 0) {
                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                jlb.setBackground(new Color(50, 200, 50));
                jlb.setForeground(new Color(255, 255, 255));
            }
        }

        if (mSubtype == 1) {
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
