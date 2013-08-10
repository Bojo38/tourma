/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.awt.Font;
import tourma.data.Coach;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Team;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class mjtTeams extends AbstractTableModel implements TableCellRenderer {

    ArrayList<Team> mTeams;

    public mjtTeams(final ArrayList<Team> teams) {
        mTeams = teams;
    }

    public int getColumnCount() {

        int nbCol = 2;
        for (int i = 0; i < mTeams.size(); i++) {
            nbCol = Math.max(nbCol, mTeams.get(i).mCoachs.size() + 2);
        }
        return nbCol;
    }

    public int getRowCount() {
        return mTeams.size();
    }

    public String getColumnName(final int col) {
        String val;
        switch (col) {
            case 0:
                val = "#";
                break;
            case 1:
                val = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Name");
                break;
            default:
                val = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(StringConstants.CS_COACH) + (col - 1);
        }
        return val;
    }

    public Object getValueAt(final int row, final int col) {
        Object object = "";
        if (mTeams.size() > 0) {
            final Team t = mTeams.get(row);
            switch (col) {
                case 0:
                    object = row + 1;
                    break;
                case 1:
                    object = t.mName;
                    break;
                default:
            }
            if (t.mCoachs.size() > 0) {
                if (t.mCoachs.size() > (col - 2)) {
                    if (col >= 2) {
                        object = t.mCoachs.get(col - 2).mName;
                    }
                }
            }
        }
        return object;
    }

    public Class getColumnClass(final int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(final int row, final int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return false;
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

        final Team t = mTeams.get(row);
        if (t.mCoachs.size() > column - 2) {
            if (column >= 2) {
                final Coach c = t.mCoachs.get(column - 2);
                if (!c.mActive) {
                    jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                }
            }
        }
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
