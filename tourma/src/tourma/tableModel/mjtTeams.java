/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.awt.Font;
import tourma.data.Coach;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Team;

/**
 *
 * @author Frederic Berger
 */
public class mjtTeams extends AbstractTableModel implements TableCellRenderer {

    Vector<Team> _teams;

    public mjtTeams(Vector<Team> teams) {
        _teams = teams;
    }

    public int getColumnCount() {

        int nbCol = 2;
        for (int i = 0; i < _teams.size(); i++) {
            nbCol = Math.max(nbCol, _teams.get(i)._coachs.size() + 2);
        }
        return nbCol;
    }

    public int getRowCount() {
        return _teams.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "#";
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Name");
        }
        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Coach") + (col - 1);
    }

    public Object getValueAt(int row, int col) {
        if (_teams.size() > 0) {
            Team t = _teams.get(row);
            switch (col) {
                case 0:
                    return row + 1;
                case 1:
                    return t._name;
            }
            if (t._coachs.size() > (col - 2)) {
                return t._coachs.get(col - 2)._name;
            } else {
                return "";
            }
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

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JTextField jlb = new JTextField();

        jlb.setEditable(false);
        if (value instanceof String) {
            jlb.setText((String) value);
        }

        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }

        Team t = _teams.get(row);
        if (t._coachs.size() > column - 2) {
            if (column >= 2) {
                Coach c = t._coachs.get(column - 2);
                if (!c._active) {
                    jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                }
            }
        }
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
