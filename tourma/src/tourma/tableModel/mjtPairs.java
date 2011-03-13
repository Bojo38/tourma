/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.Match;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
public class mjtPairs extends AbstractTableModel implements TableCellRenderer {

    Vector<Team> _teams1;
    Vector<Team> _teams2;
    Vector<Boolean> _done;

    public mjtPairs(Vector<Team> teams1, Vector<Team> teams2, Vector<Boolean> done) {
        _teams1 = teams1;
        _teams2 = teams2;
        _done = done;
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return _teams1.size();
    }

    public String getColumnName(int col) {

        switch (col) {
            case 0:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TABLE");
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN 1");
            case 2:
                return "";
            case 3:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN 2");
        }
        return "";
    }

    public Object getValueAt(int row, int col) {

        switch (col) {
            case 0:
                return row + 1;
            case 1:
                return _teams1.get(row)._name;
            case 2:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VS");
            case 3:
                return _teams2.get(row)._name;
        }
        return "";
    }

    @Override
    public Class getColumnClass(
            int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
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
            jlb.setText(""+((Integer) value));
        }

        Color bkg = new Color(255, 255, 255);
        Color frg = new Color(0, 0, 0);
        if (_done.get(row).booleanValue()) {
            bkg = new Color(190, 190, 190);
        }

        jlb.setBackground(bkg);
        jlb.setForeground(frg);
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
