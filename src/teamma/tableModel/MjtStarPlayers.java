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
import teamma.data.StarPlayer;
import teamma.data.Skill;
import teamma.languages.Translate;

/**
 *
 * @author Frederic Berger
 */
public class MjtStarPlayers extends AbstractTableModel implements TableCellRenderer {

    private static final Logger LOG = Logger.getLogger(MjtStarPlayers.class.getName());

    /**
     *
     */
    ArrayList<StarPlayer> mPlayers;

    /**
     *
     * @param roster
     */
    public MjtStarPlayers(ArrayList<StarPlayer> players) {
        mPlayers=players;

    }

    /**
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return 8;
    }

    /**
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return mPlayers.size();
    }

     private final static String CS_Limit = "Limit";
    private final static String CS_Position = "Position";
    private final static String CS_M = "M";
    private final static String CS_S = "S";
    private final static String CS_Ag = "Ag";
    private final static String CS_Ar = "Ar";
    private final static String CS_Skills = "Skills";
    private final static String CS_BaseCost = "Base Cost";
    

    /**
     *
     * @param col
     * @return
     */
    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                 return Translate.translate(tourma.languages.Translate.CS_Name);
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
                return Translate.translate(CS_BaseCost);
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
        if (mPlayers.size() > 0) {
            StarPlayer pt = mPlayers.get(row);
            switch (col) {
                case 0:
                    return pt.getName();
                case 1:
                    return Translate.translate(pt.getPosition());
                case 2:
                    return pt.getMovement();
                case 3:
                    return pt.getStrength();
                case 4:
                    return pt.getAgility();
                case 5:
                    return pt.getArmor();
                case 6:
                    /**
                     * Build Skill string in HTML Begin by player type then
                     * additional
                     */
                    ArrayList<String> skills = new ArrayList<>();
                    for (i = 0; i < pt.getSkillCount(); i++) {
                        Skill s = pt.getSkill(i);
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
                    return pt.getCost();                
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
