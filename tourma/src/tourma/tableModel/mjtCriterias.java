/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Criteria;
import tourma.data.Parameters;
import tourma.data.Tournament;

/**
 *
 * @author Administrateur
 */
public class mjtCriterias extends AbstractTableModel implements TableCellRenderer {

    Tournament _tour;
    Parameters _params;

    public mjtCriterias(Tournament tour) {
        _params = tour.getParams();
        _tour = tour;
    }

    public int getColumnCount() {
        if (_params._teamTournament) {
            return 5;
        } else {
            return 3;
        }
    }

    public int getRowCount() {
        return _params._criterias.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Nom Critère";
            case 1:
                return "Points +";
            case 2:
                return "Points -";
            case 3:
                return "Points Equipe +";
            case 4:
                return "Points Equipe -";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {

        switch (col) {
            case 0:
                return _params._criterias.get(row)._name;
            case 1:
                return _params._criterias.get(row)._pointsFor;
            case 2:
                return _params._criterias.get(row)._pointsAgainst;
            case 3:
                return _params._criterias.get(row)._pointsTeamFor;
            case 4:
                return _params._criterias.get(row)._pointsTeamAgainst;
        }
        return "";
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (value != null) {
            Criteria c = _params._criterias.get(row);
            switch (col) {
                case 0:
                    c._name = value.toString();
                    break;
                case 1:
                    c._pointsFor = (Integer.valueOf(value.toString()));
                    break;
                case 2:
                    c._pointsAgainst = (Integer.valueOf(value.toString()));
                    break;
                case 3:
                    c._pointsTeamFor = (Integer.valueOf(value.toString()));
                    break;
                case 4:
                    c._pointsTeamAgainst = (Integer.valueOf(value.toString()));
                    break;
            }

        }
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
    public boolean isCellEditable(
            int row, int col) {
        if (_tour.getRounds().size() > 0) {
            return false;
        }
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return true;
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

        Color bkg = new Color(255, 255, 255);
        Color frg = new Color(0, 0, 0);
        if (isSelected) {
            bkg = new Color(200, 200, 200);
        }
        jlb.setBackground(bkg);
        jlb.setForeground(frg);
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
