/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import teamma.data.LRB;
import teamma.data.Player;
import teamma.data.Roster;
import teamma.data.Skill;
import teamma.data.SkillType;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class MjtTeamPlayers extends AbstractTableModel implements TableCellRenderer {
    private static final Logger LOG = Logger.getLogger(MjtTeamPlayers.class.getName());

    /**
     *
     */
    private final Roster _roster;

    /**
     *
     * @param roster
     */
    public MjtTeamPlayers(Roster roster) {
        _roster = roster;

    }

    /**
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return 12;
    }

    /**
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return _roster.getPlayerCount();
    }

    /**
     *
     * @param col
     * @return
     */
    @Override
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

    /**
     *
     * @param row
     * @param col
     * @return
     */
    @Override
    public Object getValueAt(int row, int col) {
        int i;
//        int value;
        StringBuilder tmpstring = new StringBuilder(32);
        if (_roster.getPlayerCount() > 0) {
            Player player = _roster.getPlayer(row);
            switch (col) {
                case 0:
                    return row + 1;
                case 1:
                    return (player.getName());
                case 2:
                    return (player.getPlayertype().getPosition());
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
                    ArrayList<String> skills = new ArrayList<>();
                    for (i = 0; i < player.getPlayertype().getSkillCount(); i++) {
                        Skill s = player.getPlayertype().getSkill(i);
                        skills.add("<FONT color=\"000000\">" + s.getmName() + "</FONT>");
                    }
                    for (i = 0; i < player.getSkillCount(); i++) {
                        Skill s = player.getSkill(i);
                        //int rgb=s.mColor.getRGB();
                        int rgb = s.getmColor().getRed() * 65536 + s.getmColor().getGreen() * 256 + s.getmColor().getBlue();
                        skills.add("<FONT color=\"" + Integer.toHexString(rgb) + "\"><I>" + s.getmName() + "</I></FONT>");
                    }

                    for (i = 0; i < skills.size(); i++) {
                        tmpstring.append(skills.get(i));
                        if (i != skills.size() - 1) {
                            tmpstring.append(", ");
                        }
                    }
                    return (tmpstring.toString());
                case 8:
                    /**
                     * SR
                     */
                    StringBuilder sr = new StringBuilder(32);
                    for (i = 0; i < player.getPlayertype().getSingleCount(); i++) {
                        SkillType st = player.getPlayertype().getSingle(i);
                        sr.append(st.getAccronym());
                    }
                    return sr.toString();
                case 9:
                    /**
                     * DR
                     */
                    StringBuilder dr = new StringBuilder(32);
                    for (i = 0; i < player.getPlayertype().getDoubleCount(); i++) {
                        SkillType st = player.getPlayertype().getDouble(i);
                        dr.append(st.getAccronym());
                    }
                    return dr.toString();
                case 10:
                    /**
                     * Base Cost
                     */
                    return player.getPlayertype().getCost();
                case 11:
                    /**
                     * Real Cost
                     */
                    int skillCost = 0;
                    for (i = 0; i < player.getSkillCount(); i++) {
                        int j;
                        SkillType st = player.getSkill(i).getmCategory();

                        /**
                         * If Single Roll, add 20000
                         */
                        for (j = 0; j < player.getPlayertype().getSingleCount(); j++) {
                            if (st.equals(player.getPlayertype().getSingle(j))) {
                                skillCost += 20000;
                            }
                        }
                        /**
                         * If Double Roll, add 30000
                         */
                        for (j = 0; j < player.getPlayertype().getDoubleCount(); j++) {
                            if (st.equals(player.getPlayertype().getDouble(j))) {
                                skillCost += 30000;
                            }
                        }

                        /*
                         * If charactristics, it depends.
                         */
                        if (st.equals(LRB.getLRB().getSkillType("Characteristics"))) {
                            Skill s = player.getSkill(i);
                            if (s.getmName().equals("+1 Movement")) {
                                skillCost += 30000;
                            }
                            if (s.getmName().equals("+1 Armor")) {
                                skillCost += 30000;
                            }
                            if (s.getmName().equals("+1 Agility")) {
                                skillCost += 40000;
                            }
                            if (s.getmName().equals("+1 Strength")) {
                                skillCost += 50000;
                            }
                        }
                    }
                    return skillCost + player.getPlayertype().getCost();

            }
        }
        return "";
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /**
     * Don't need to implement this method unless your table's editable.
     *
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

    /**
     *
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return
     */
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {


        /*JEditorPane jta = new JEditorPane();
         jta.setContentType("text/html");
         jta.setPreferredSize(new Dimension(100,30));*/
        JEditorPane jta = new JEditorPane();
        jta.setContentType("text/html");

        if (isSelected) {
            jta.setBackground(Color.LIGHT_GRAY);
        } else {

            if (row % 2 != 0) {
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

/*    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }*/
}
