/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.system;

import java.awt.Component;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author root
 */
public class mtRevisions extends AbstractTableModel implements TableCellRenderer {

    Vector _versions;
    Vector _descriptions;

    public mtRevisions(Vector versions, Vector descriptions) {
        _versions=versions;
        _descriptions=descriptions;
    }

    public int getColumnCount() {
        return 2;
    }

    public int getRowCount() {
        return _versions.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Version";
            case 1:
                return "Description";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        String tmp="";
        switch (col) {
            case 0:
                tmp=(String)(_versions.get(row));
                break;
            case 1:
                tmp=(String)(_descriptions.get(row));
                break;
        }
            return tmp;
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
        return new JLabel((String) value);
    }
}
