/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import teamma.data.Player;
import teamma.data.Skill;
import teamma.data.SkillType;
import teamma.data.lrb;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class mjtTeamPlayers extends AbstractTableModel implements TableCellRenderer {

    ArrayList<Player> _players;

    public mjtTeamPlayers(ArrayList<Player> players) {
        _players = players;

    }

    public int getColumnCount() {
        return 12;
    }

    public int getRowCount() {
        return _players.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "#";
            case 1:
                return java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Name");
            case 2:
                return java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Position");
            case 3:
                return java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("M");
            case 4:
                return java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("S");
            case 5:
                return java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Ag");
            case 6:
                return java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Ar");
            case 7:
                return java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Skills");
            case 8:
                return java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SR");
            case 9:
                return java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DR");
            case 10:
                return java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Base Cost");
            case 11:
                return java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Cost");
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
                    ArrayList<String> skills = new ArrayList<String>();
                    for (i = 0; i < player._playertype._skills.size(); i++) {
                        Skill s = player._playertype._skills.get(i);

                        skills.add("<FONT color=\"000000\">" + s._name + "</FONT>");
                    }
                    for (i = 0; i < player._skills.size(); i++) {
                        Skill s = player._skills.get(i);
                            //int rgb=s._color.getRGB();
                              int rgb=s._color.getRed()*65536+s._color.getGreen()*256+s._color.getBlue();
                            skills.add("<FONT color=\""+Integer.toHexString(rgb) +"\"><I>" + s._name + "</I></FONT>");
                    }

                    for (i = 0; i < skills.size(); i++) {
                        tmpstring += skills.get(i);
                        if (i != skills.size() - 1) {
                            tmpstring += ", ";
                        }
                    }
                    return (tmpstring);
                case 8:
                    /**
                     * SR
                     */
                    String sr = "";
                    for (i = 0; i < player._playertype._single.size(); i++) {
                        SkillType st = player._playertype._single.get(i);
                        sr += st._accronym;
                    }
                    return sr;
                case 9:
                    /**
                     * DR
                     */
                    String dr = "";
                    for (i = 0; i < player._playertype._double.size(); i++) {
                        SkillType st = player._playertype._double.get(i);
                        dr += st._accronym;
                    }
                    return dr;
                case 10:
                    /**
                     * Base Cost
                     */
                    return player._playertype._cost;
                case 11:
                    /**
                     * Real Cost
                     */
                    int skillCost = 0;
                    for (i = 0; i < player._skills.size(); i++) {
                        int j;
                        SkillType st = player._skills.get(i)._category;

                        /**
                         * If Single Roll, add 20000
                         */
                        for (j = 0; j < player._playertype._single.size(); j++) {
                            if (st.equals(player._playertype._single.get(j))) {
                                skillCost += 20000;
                            }
                        }
                        /**
                         * If Double Roll, add 30000
                         */
                        for (j = 0; j < player._playertype._double.size(); j++) {
                            if (st.equals(player._playertype._double.get(j))) {
                                skillCost += 30000;
                            }
                        }
                        
                        /*
                         * If charactristics, it depends.
                         */
                        if (st.equals(lrb.getLRB().getSkillType("Characteristics"))) {
                            Skill s=player._skills.get(i);
                            if (s._name.equals("+1 Movement"))
                            {
                                skillCost += 30000;
                            }
                            if (s._name.equals("+1 Armor"))
                            {
                                skillCost += 30000;
                            }
                            if (s._name.equals("+1 Agility"))
                            {
                                skillCost += 40000;
                            }
                            if (s._name.equals("+1 Strength"))
                            {
                                skillCost += 50000;
                            }
                        }
                    }
                    return skillCost+player._playertype._cost;

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
