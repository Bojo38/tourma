/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.tableModel;

import java.awt.Component;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import teamma.data.Player;
import teamma.data.Skill;

/**
 *
 * @author Frederic Berger
 */
public class mjtTeamPlayers extends AbstractTableModel implements TableCellRenderer {

    Vector<Player> _players;

    public mjtTeamPlayers(Vector<Player> players) {
        _players = players;
        
          }

    public int getColumnCount() {
        return 14;
    }

    public int getRowCount() {
        return _players.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "#";
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Name");
            case 2:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Position");
            case 3:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("M");
            case 4:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("S");
            case 5:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Ag");
            case 6:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Ar");
            case 7:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Skills");
            case 8:
                return "";
            case 9:
                return "";
            case 10:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SR");
            case 11:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DR");
            case 12:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Base Cost");
            case 13:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Cost");
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        int i;
        int value;
        String tmpstring = "";
        if (_players.size() > 0) {
            Player player = _players.get(row);
            switch (col) {
                case 0:
                    return row + 1;
                case 1:
                    return (player._name);
                case 2:
                    return (player._playertype._position);
                case 3:
                    return player.getMovement();
                case 4:
                    return player.getStrength();
                case 5:
                    return player.getAgility();
                case 6:
                    return player.getArmor();
                case 7:
                    /**
                     * Build Skill string in HTML Begin by player type then
                     * additional
                     */
                    Vector<String> skills = new Vector<String>();
                    for (i = 0; i < player._playertype._skills.size(); i++) {
                        Skill s = player._playertype._skills.get(i);

                        skills.add("<FONT color=\"000000\">" + s._name + "</FONT>");
                    }
                    for (i = 0; i < player._skills.size(); i++) {
                        Skill s = player._skills.get(i);
                        /*
                         * If simple Roll => Blue Italic
                         */
                        boolean sr = false;
                        boolean dr = false;
                        int j;
                        for (j = 0; j < player._playertype._single.size(); j++) {
                            if (s._category._accronym.equals(player._playertype._single.get(j)._accronym)) {
                                sr = true;
                            }
                        }
                        if (sr) {
                            skills.add("<FONT color=\"4b2e9f\"><I>" + s._name + "</I></FONT>");
                        }


                        /*
                         * If double Roll => Bule Bold
                         */
                        for (j = 0; j < player._playertype._double.size(); j++) {
                            if (s._category._accronym.equals(player._playertype._double.get(j)._accronym)) {
                                dr = true;
                            }
                        }
                        if (dr) {
                            skills.add("<FONT color=\"4b2e9f\"><B>" + s._name + "</B></FONT>");
                        }
                        /*
                         * If Char => Green Bold
                         */
                        if (s._category._accronym.equals("C")) {
                            skills.add("<FONT color=\"128043\"><B>" + s._name + "</B></FONT>");
                        }
                        /*
                         * If Inj => Red Bold
                         */
                        if (s._category._accronym.equals("I")) {
                            skills.add("<FONT color=\"c6001c\"><B>" + s._name + "</B></FONT>");
                        }
                        /*
                         * If Extr =>  Gold Bold
                         */
                        if (s._category._accronym.equals("E")) {
                            skills.add("<FONT color=\"c6ab1c\"><B>" + s._name + "</B></FONT>");
                        }
                    }

                    for (i = 0; i < skills.size(); i++) {
                        tmpstring = tmpstring + skills.get(i);
                        if (i != skills.size() - 1) {
                            tmpstring = tmpstring + ", ";
                        }
                    }
                    return ("<html>"+tmpstring+"</html>");
                case 8:
                    /**
                     * Add button to add skill
                     */
                    return "";
                case 9:
                    /**
                     * Add button to remove skill
                     */
                    return "";
                case 10:
                    /**
                     * SR
                     */
                    return "";
                case 11:
                    /**
                     * DR
                     */
                    return "";
                case 12:
                    /**
                     * Base Cost
                     */
                    return "";
                case 13:
                    /**
                     * Real Cost
                     */
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
        if ((col == 8)||(col == 9)) {
            return true;
        }

        return false;
    }

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel jlb = new JLabel();

        if (value instanceof String) {
            jlb.setText((String) value);
        }

        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }

        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
