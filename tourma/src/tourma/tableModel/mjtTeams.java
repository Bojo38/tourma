/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.Coach;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import tourma.data.Team;

/**
 *
 * @author Frederic Berger
 */
public class mjtTeams extends AbstractTableModel {

    Vector<Team> _teams;

    public mjtTeams(Vector<Team> teams) {
        _teams = teams;
    }

    public int getColumnCount() {

        if (_teams.size()>0)
        {
            return _teams.get(0)._coachs.size()+2;
        }
        else
        {
            return 2;
        }
    }

    public int getRowCount() {
        return _teams.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Nb";
            case 1:
                return "Nom";
        }
        return "Coach "+(col-1);
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
            return t._coachs.get(col-2)._name;
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
