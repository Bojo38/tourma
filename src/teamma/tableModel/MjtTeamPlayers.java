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
import teamma.languages.Translate;

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

    private final static String CS_Name = "Name";
    private final static String CS_Position = "Position";
    private final static String CS_M = "M";
    private final static String CS_S = "S";
    private final static String CS_Ag = "Ag";
    private final static String CS_Ar = "Ar";
    private final static String CS_Skills = "Skills";
    private final static String CS_SR = "SR";
    private final static String CS_DR = "DR";
    private final static String CS_BaseCost = "Base Cost";
    private final static String CS_Cost = "Cost";

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
                return Translate.translate(CS_Name);
            case 2:
                return Translate.translate(CS_Position);
            case 3:
                return Translate.translate(CS_M);
            case 4:
                return Translate.translate(CS_S);
            case 5:
                return Translate.translate(CS_Ag);
            case 6:
                return Translate.translate(CS_Ar);
            case 7:
                return Translate.translate(CS_Skills);
            case 8:
                return Translate.translate(CS_SR);
            case 9:
                return Translate.translate(CS_DR);
            case 10:
                return Translate.translate(CS_BaseCost);
            case 11:
                return Translate.translate(CS_Cost);
        }
        return "";
    }

    private final static String CS_Characteristics = "Characteristics";

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
                    return Translate.translate(player.getPlayertype().getPosition());
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
                        skills.add("<FONT color=\"000000\">" + Translate.translate(s.getmName()) + "</FONT>");
                    }
                    for (i = 0; i < player.getSkillCount(); i++) {
                        Skill s = player.getSkill(i);
                        //int rgb=s.mColor.getRGB();
                        int rgb = s.getmColor().getRed() * 65536 + s.getmColor().getGreen() * 256 + s.getmColor().getBlue();
                        skills.add("<FONT color=\"" + Integer.toHexString(rgb) + "\"><I>" + Translate.translate(s.getmName()) + "</I></FONT>");
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
                        sr.append(Translate.translate(st.getAccronym()));
                    }
                    return sr.toString();
                case 9:
                    /**
                     * DR
                     */
                    StringBuilder dr = new StringBuilder(32);
                    for (i = 0; i < player.getPlayertype().getDoubleCount(); i++) {
                        SkillType st = player.getPlayertype().getDouble(i);
                        dr.append(Translate.translate(st.getAccronym()));
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
                        if (st.equals(LRB.getLRB(_roster.getRoster().getVersion()).getSkillType(CS_Characteristics))) {
                            Skill s = player.getSkill(i);
                            if (s.getmName().equals(Player.CS_Plus1Movement)) {
                                skillCost += 30000;
                            }
                            if (s.getmName().equals(Player.CS_Plus1Armor)) {
                                skillCost += 30000;
                            }
                            if (s.getmName().equals(Player.CS_Plus1Agility)) {
                                skillCost += 40000;
                            }
                            if (s.getmName().equals(Player.CS_Plus1Strength)) {
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
            jta.setText("<center>" + value + "</center>");
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
