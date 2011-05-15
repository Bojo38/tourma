/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.Coach;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import tourma.data.Tournament;

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

        if (Tournament.getTournament().getParams()._enableClans)
        {
            return 7;
        }
        else
        {
            return 6;
        }
    }

    public int getRowCount() {
        return _coachs.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB");
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH");
            case 2:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EQUIPE");
            case 3:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER");
            case 4:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF");
            case 5:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLASSEMENT");
            case 6:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ClanKey");
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
                case 6:
                    return c._clan._name;
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
