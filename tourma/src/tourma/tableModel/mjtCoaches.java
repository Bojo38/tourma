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
import tourma.data.Tournament;

/**
 *
 * @author Frederic Berger
 */
public class mjtCoaches extends AbstractTableModel implements TableCellRenderer {

    Vector<Coach> _coachs;

    public mjtCoaches(Vector<Coach> coachs) {
        _coachs = coachs;
    }

    public int getColumnCount() {

        if (Tournament.getTournament().getParams()._enableClans) {
            return 8;
        } else {
            return 7;
        }
    }

    public int getRowCount() {
        return _coachs.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "#";
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Coach");
            case 2:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team");
            case 3:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Roster");
            case 4:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF");
            case 5:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Ranking");
            case 7:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ClanKey");
            case 6:
                return "";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        if (_coachs.size() > 0) {
            Coach c = _coachs.get(row);
            switch (col) {
                case 0:
                    return row + 1;
                case 1:
                    return c._name;
                case 2:
                    return c._team;
                case 3:
                    return c._roster._name;
                case 4:
                    return c._naf;
                case 5:
                    return c._rank;
                case 7:
                    return c._clan._name;
                case 6:
                    if (c._active) {
                        return "Actif";
                    } else {
                        return "Inactif";
                    }
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
        if (col > 0) {
            return true;
        }

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

        Coach c = _coachs.get(row);
        if (!c._active) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
        }
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
