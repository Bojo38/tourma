/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Criterion;
import tourma.data.Formula;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Tournament;
import tourma.data.ranking.Ranking;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.display.IRanked;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
abstract public class MjtRanking extends AbstractTableModel implements TableCellRenderer {

    Ranking mRanking;

    protected int mMin;
    protected int mMax;

    /**
     *
     * @param round
     * @param ranking_type1
     * @param ranking_type2
     * @param ranking_type3
     * @param ranking_type4
     * @param ranking_type5
     * @param objects
     * @param roundOnly
     */
    public MjtRanking(Ranking ranking, int min, int max) {
        mRanking = ranking;

        mMin = min;
        mMax = max;
        //sortDatas();
    }

    @Override
    public abstract int getColumnCount();

    @Override
    public abstract String getColumnName(int col);

    @Override
    public abstract Object getValueAt(int row, int col);

    @Override
    public int getRowCount() {
        return mMax - mMin;
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
        final JLabel jlb = new JLabel();
        //jlb.setEditable(false);
        jlb.setOpaque(true);
        jlb.setBackground(new Color(255, 255, 255));
        jlb.setForeground(new Color(0, 0, 0));
        boolean useColor = false;

        useColor = Tournament.getTournament().getParams().isUseColor();

        if (!useColor) {
            if (row % 2 != 0) {
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

        int size = mRanking.getRowCount();
        if ((row == size - 1) && (size > 3)) {
            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
            if (useColor) {
                jlb.setBackground(new Color(50, 50, 200));
                jlb.setForeground(new Color(255, 255, 255));
            }
        }

        if ((row == size - 2) && (size > 4)) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            if (useColor) {
                jlb.setBackground(new Color(100, 100, 200));
                jlb.setForeground(new Color(0, 0, 0));
            }
        }

        if ((row == size - 3) && (size > 5)) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            if (useColor) {
                jlb.setBackground(new Color(150, 150, 200));
                jlb.setForeground(new Color(0, 0, 0));
            }
        }

        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }

}
