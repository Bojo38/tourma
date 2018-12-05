/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.RosterType;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public class mjtTeamsAndCoaches extends AbstractTableModel implements TableCellRenderer {

    boolean _byTeam = false;
    Tournament _tour = null;

    public mjtTeamsAndCoaches(boolean byTeam) {
        _byTeam = byTeam;
        _tour = Tournament.getTournament();
    }

    @Override
    public int getRowCount() {
        if (_byTeam) {
            return _tour.getTeamsCount() * _tour.getParams().getTeamMatesNumber();
        }
        return _tour.getCoachsCount();
    }

    @Override
    public int getColumnCount() {
        if (_byTeam) {
            return 7;
        }
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (_byTeam) {
            int teamIndex = rowIndex / _tour.getParams().getTeamMatesNumber();
            Team t = _tour.getTeam(teamIndex);
            if (rowIndex == 25) {
                System.out.println("Middle reached");
            }
            Coach c = t.getCoach(rowIndex % _tour.getParams().getTeamMatesNumber());
            switch (columnIndex) {
                case 0:
                    return t;
                case 1:
                    return t.getClan();
                case 2:
                    return c.getName();
                case 3:
                    return c.getTeam();
                case 4:
                    return c.getRoster();
                case 5:
                    return c.getClan();
                case 6:
                    return c.getNaf();
            }
        } else {
            Coach c = _tour.getCoach(rowIndex);
            switch (columnIndex) {
                case 0:
                    return c;
                case 1:
                    return c.getTeam();
                case 2:
                    return c.getRoster();
                case 3:
                    return c.getClan();
                case 4:
                    return c.getNaf();
            }
        }
        return "";
    }

    @Override
    public String getColumnName(int col) {
        if (_byTeam) {
            switch (col) {
                case 0:
                    return Translate.translate(Translate.CS_Team);
                case 1:
                    return Translate.translate(Translate.CS_Clan);
                case 2:
                    return Translate.translate(Translate.CS_Coach);
                case 3:
                    return Translate.translate(Translate.CS_RosterName);
                case 4:
                    return Translate.translate(Translate.CS_Roster);
                case 5:
                    return Translate.translate(Translate.CS_Clan);
                case 6:
                    return Translate.translate(Translate.CS_NAF);
            }
        } else {
            switch (col) {
                case 0:
                    return Translate.translate(Translate.CS_Coach);
                case 1:
                    return Translate.translate(Translate.CS_RosterName);
                case 2:
                    return Translate.translate(Translate.CS_Roster);
                case 3:
                    return Translate.translate(Translate.CS_Clan);
                case 4:
                    return Translate.translate(Translate.CS_NAF);
            }
        }
        return "";
    }

    void setLabelLF(int row, int col, boolean isSelected, boolean hasFocus, JLabel jlb) {
        Color bkg = new Color(150, 150, 200);

        if (row % 2 != 0) {
            bkg = new Color(220, 220, 220);
        }

        if ((isSelected) || (hasFocus)) {
            bkg = new Color(220, 220, 100);
            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
        }

        if (hasFocus) {
            bkg = new Color(255, 255, 255);
        }

        jlb.setOpaque(true);
        jlb.setBackground(bkg);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof String) {
            JLabel jlb = new JLabel((String) value);
            setLabelLF(row, column, isSelected, hasFocus, jlb);
            return jlb;
        }
        if (value instanceof Integer) {
            JLabel jlb = new JLabel(Integer.toString((Integer) value));
            setLabelLF(row, column, isSelected, hasFocus, jlb);
            return jlb;
        }
        if (value instanceof Clan) {
            JLabel jlb = new JLabel(((Clan) value).getName());
            setLabelLF(row, column, isSelected, hasFocus, jlb);
            return jlb;
        }
        if (value instanceof Team) {
            JLabel jlb = new JLabel(((Team) value).getName());
            setLabelLF(row, column, isSelected, hasFocus, jlb);
            return jlb;
        }
        if (value instanceof Coach) {
            JLabel jlb = new JLabel(((Coach) value).getName());
            setLabelLF(row, column, isSelected, hasFocus, jlb);
            return jlb;
        }
        if (value instanceof RosterType) {
            /*JComboBox jcb = new JComboBox(RosterType.getRostersNames());
            jcb.setSelectedItem(((RosterType) value).getName());
            return jcb;*/
            JLabel jlb = new JLabel(((RosterType) value).getName());
            setLabelLF(row, column, isSelected, hasFocus, jlb);
            return jlb;
        }
        JLabel jlb = new JLabel("");
        setLabelLF(row, column, isSelected, hasFocus, jlb);
        return jlb;
    }

    @Override
    public void setValueAt(final Object value, final int row, final int col) {
        if (_byTeam) {
            int teamIndex = row / _tour.getParams().getTeamMatesNumber();
            Team t = _tour.getTeam(teamIndex);
            Coach c = t.getCoach(row % _tour.getParams().getTeamMatesNumber());
            switch (col) {
                case 0:
                    t.setName(String.valueOf(value));
                    break;
                case 1:
                    if (value instanceof Clan) {
                        t.setClan((Clan) value);
                    }
                    break;
                case 2:
                    c.setName(String.valueOf(value));
                    String team_name = c.getTeam();
                    if (team_name.equals("New Coach " + row + " team")) {
                        c.setTeam(c.getName() + " team");
                    }
                    break;
                case 3:
                    c.setTeam(String.valueOf(value));
                    break;
                case 4:
                    if (value instanceof RosterType) {
                        c.setRoster((RosterType) value);
                    }
                    if (value instanceof String) {
                        c.setRoster((RosterType.getRosterType((String) value)));
                    }
                    break;
                case 5:
                    if (value instanceof Clan) {
                        c.setClan((Clan) value);
                    }
                    break;
                case 6:
                    c.setNaf(Integer.parseInt(String.valueOf(value)));
                    break;
            }
        } else {
            Coach c = _tour.getCoach(row);
            if (c != null) {
                switch (col) {
                    case 0:
                        c.setName(String.valueOf(value));
                        String team_name = c.getTeam();
                        if (team_name.equals("New Coach " + row + " team")) {
                            c.setTeam(c.getName() + " team");
                        }
                        break;
                    case 1:
                        c.setTeam(String.valueOf(value));
                        break;
                    case 2:
                        if (value instanceof RosterType) {
                            c.setRoster((RosterType) value);
                        }
                        if (value instanceof String) {
                            c.setRoster((RosterType.getRosterType((String) value)));
                        }
                        break;
                    case 3:
                        if (value instanceof Clan) {
                            c.setClan((Clan) value);
                        }
                        break;
                    case 4:
                        c.setNaf(Integer.parseInt(String.valueOf(value)));
                        break;
                }
            }
        }
    }

    @Override
    public Class getColumnClass(final int c) {
        Object obj = getValueAt(0, c);
        if (obj != null) {
            return obj.getClass();
        } else {
            System.out.println("Error looking for class of column " + c);
        }
        return String.class;
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(final int row, final int col) {
        return true;
    }
}
