/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Frederic Berger
 */
public class mjtCoaches extends AbstractTableModel {

    Vector<Coach> _coachs;

    public mjtCoaches(Vector<Coach> coachs) {
        _coachs = coachs;
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return _coachs.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Nb";
            case 1:
                return "Coach";
            case 2:
                return "Equipe";
            case 3:
                return "Roster";
            case 4:
                return "NAF";
                case 5:
                return "Classement";
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
                    return c._roster;
                case 4:
                    return c._naf;
                     case 5:
                    return c._rank;
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
}
