/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import teamma.data.Skill;
import teamma.data.StarPlayer;


/**
 *
 * @author Frederic Berger
 */
public class mjtTeamStars extends AbstractTableModel implements TableCellRenderer {

    Vector<StarPlayer> _stars;

    public mjtTeamStars(Vector<StarPlayer> players) {
        _stars = players;

    }

    public int getColumnCount() {
        return 8;
    }

    public int getRowCount() {
        return _stars.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Name");
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Position");
            case 2:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("M");
            case 3:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("S");
            case 4:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Ag");
            case 5:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Ar");
            case 6:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Skills");
            case 7:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Cost");
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        int i;
        int value;
        String tmpstring = "";
        if (_stars.size() > 0) {
            StarPlayer player = _stars.get(row);
            switch (col) {
                case 0:
                    return (player._name);
                case 1:
                    return (player._position);
                case 2:
                    return player._movement;
                case 3:
                    return player._strength;
                case 4:
                    return player._agility;
                case 5:
                    return player._armor;
                case 6:
                    /**
                     * Build Skill string in HTML Begin by player type then
                     * additional
                     */
                    Vector<String> skills = new Vector<String>();
                    for (i = 0; i < player._skills.size(); i++) {
                        Skill s = player._skills.get(i);

                        skills.add("<FONT color=\"000000\">" + s._name + "</FONT>");
                    }

                    for (i = 0; i < skills.size(); i++) {
                        tmpstring = tmpstring + skills.get(i);
                        if (i != skills.size() - 1) {
                            tmpstring = tmpstring + ", ";
                        }
                    }
                    return (tmpstring);
                 case 7:
                    /**
                     * Base Cost
                     */
                    return player._cost;
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


        /*JEditorPane jta = new JEditorPane();
         jta.setContentType("text/html");
         jta.setPreferredSize(new Dimension(100,30));*/

        JEditorPane jta = new JEditorPane();
        jta.setContentType("text/html");

        if (isSelected)
        {
            jta.setBackground(Color.LIGHT_GRAY);
        }
        else
        {
            if (row % 2==1)
            {
                jta.setBackground(new Color(220,220,220));
            }
            else
            {
                 jta.setBackground(Color.WHITE);
            }
        }
        
        jta.setEditable(false);
        if (value instanceof String) {
            jta.setText("<center>" + (String) value + "</center>");
        }

        if (value instanceof Integer) {
            jta.setText("<center>" + Integer.toString((Integer) value) + "</center>");
        }

        int width = table.getTableHeader().getColumnModel().getColumn(column).getWidth();
        jta.setSize(width, 1000);
        int prefH = jta.getPreferredSize().height;

        if (table.getRowHeight(row) < prefH) {
            table.setRowHeight(row,prefH);
        }

        return jta;
    }
}
