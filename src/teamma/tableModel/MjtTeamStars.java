/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import teamma.data.Roster;
import teamma.data.Skill;
import teamma.data.StarPlayer;
import teamma.languages.Translate;

/**
 *
 * @author Frederic Berger
 */
public class MjtTeamStars extends AbstractTableModel implements TableCellRenderer {

    /**
     * 
     */
    private final Roster _roster;

    /**
     * 
     * @param roster
     */
    public MjtTeamStars(Roster roster) {
        _roster = roster;

    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public int getRowCount() {
        return _roster.getChampionCount();
    }

        private final static String CS_Name = "Name";
    private final static String CS_Position = "Position";
    private final static String CS_M = "M";
    private final static String CS_S = "S";
    private final static String CS_Ag = "Ag";
    private final static String CS_Ar = "Ar";
    private final static String CS_Skills = "Skills";
    private final static String CS_Cost = "Cost";
    
    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return Translate.translate(CS_Name);
            case 1:
                return Translate.translate(CS_Position);
            case 2:
                return Translate.translate(CS_M);
            case 3:
                return Translate.translate(CS_S);
            case 4:
                return Translate.translate(CS_Ag);
            case 5:
                return Translate.translate(CS_Ar);
            case 6:
                return Translate.translate(CS_Skills);
            case 7:
                return Translate.translate(CS_Cost);
        }
        return "";
    }

    @Override
    public Object getValueAt(int row, int col) {
        int i;
//        int value;
        StringBuilder tmpstring = new StringBuilder(32);
        if (_roster.getChampionCount() > 0) {
            StarPlayer player = _roster.getChampion(row);
            switch (col) {
                case 0:
                    return Translate.translate(player.getName());
                case 1:
                    return Translate.translate(player.getPosition());
                case 2:
                    return player.getMovement();
                case 3:
                    return player.getStrength();
                case 4:
                    return player.getAgility();
                case 5:
                    return player.getArmor();
                case 6:
                    /**
                     * Build Skill string in HTML Begin by player type then
                     * additional
                     */
                    ArrayList<String> skills = new ArrayList<>();
                    for (i = 0; i < player.getSkillCount(); i++) {
                        Skill s = player.getSkill(i);

                        skills.add("<FONT color=\"000000\">" + Translate.translate(s.getmName()) + "</FONT>");
                    }

                    for (i = 0; i < skills.size(); i++) {
                        tmpstring.append(skills.get(i));
                        if (i != skills.size() - 1) {
                            tmpstring.append(", ");
                        }
                    }
                    return (tmpstring.toString());
                case 7:
                    /**
                     * Base Cost
                     */
                    return player.getCost();
            }
        }
        return "";
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /**
     * Don't need to implement this method unless your table's
     * editable.
     * @param row
     * @param col
     * @return 
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return false;
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JEditorPane jta = new JEditorPane();
        jta.setContentType("text/html");

        if (isSelected) {
            jta.setBackground(Color.LIGHT_GRAY);
        } else {
                if (row % 2 !=0) {
                    jta.setBackground(new Color(220, 220, 220));
                } else {
                    jta.setBackground(Color.WHITE);
                }
            
        }

        jta.setEditable(false);
        if (value instanceof String) {
            jta.setText("<center>" +  value + "</center>");
        }

        if (value instanceof Integer) {
            jta.setText("<center>" + Integer.toString((Integer) value) + "</center>");
        }

        int width = table.getTableHeader().getColumnModel().getColumn(column).getWidth();
        jta.setSize(width, 1000);
        int prefH = jta.getPreferredSize().height;

        if (table.getRowHeight(row) < prefH) {
            table.setRowHeight(row, prefH);
        }

        return jta;
    }
    
}
