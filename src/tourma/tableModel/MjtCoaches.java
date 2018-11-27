/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Coach;
import tourma.data.IContainCoachs;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public class MjtCoaches extends AbstractTableModel implements TableCellRenderer {

    private IContainCoachs t = null;

    /**
     *
     * @param container
     */
    public MjtCoaches(IContainCoachs container) {
        t = container;
    }

    @Override
    public int getColumnCount() {

        int result = 8;

        if (Tournament.getTournament().getParams().isEnableClans()) {
            result = 9;
        }

        return result;
    }

    @Override
    public int getRowCount() {
        return t.getCoachsCount();

    }

    @Override
    public String getColumnName(final int col) {
        String val = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                val = StringConstants.CS_HASH;
                break;
            case 1:
                val = Translate.translate(Translate.CS_Coach);
                break;
            case 2:
                val = Translate.translate(Translate.CS_Team);
                break;
            case 3:
                val = Translate.translate(Translate.CS_Roster);
                break;
            case 4:
                val = Translate.translate(Translate.CS_NAF);
                break;
            case 5:
                val = Translate.translate(Translate.CS_Ranking);
                break;
            case 8:
                val = Translate.translate(Translate.CS_Clan);
                break;
            case 6:
                val = StringConstants.CS_NULL;
                break;
            case 7:
                val = Translate.translate(Translate.CS_Ranking);
                break;
            default:
        }
        return val;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object val = StringConstants.CS_NULL;

        if (t.getCoachsCount() > 0) {
            final Coach c = t.getCoach(row);
            switch (col) {
                case 0:
                    val = row + 1;
                    break;
                case 1:
                    val = c.getName();
                    break;
                case 2:
                    val = c.getTeam();
                    break;
                case 3:
                    if (c.getRoster() != null) {
                        val = c.getRoster().getName();
                    } else {
                        val = Translate.translate(Translate.CS_Unknown);
                    }
                    break;
                case 4:
                    val = c.getNaf();
                    break;
                case 5:
                    val = c.getRank();
                    break;
                case 7:
                    val = Double.toString(c.getNafRank());
                    break;
                case 8:
                    if (c.getClan()!=null)
                    {
                        val = c.getClan().getName();
                    }
                    else
                    {
                        val="";
                    }
                    break;
                case 6:
                    if (c.isActive()) {
                        val = Translate.translate(Translate.CS_Active);
                    } else {
                        val = Translate.translate(Translate.CS_Inactive);
                    }
                    break;
                default:
            }
        }

        if (val == null) {
            val = StringConstants.CS_NULL;
        }
        return val;
    }

    @Override
    public Class getColumnClass(final int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(final int row, final int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.

        return col > 0;
    }

    @Override
    public Component getTableCellRendererComponent(
            final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {

        final JTextField jlb = new JTextField();

        jlb.setEditable(false);
        if (value instanceof String) {
            jlb.setText((String) value);
        }

        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }
        final Coach c = t.getCoach(row);
        if (!c.isActive()) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
        }
        jlb.setHorizontalAlignment(JTextField.CENTER);

        if (isSelected) {
            jlb.setBackground(Color.LIGHT_GRAY);
        } else {
            jlb.setBackground(Color.WHITE);
        }
        return jlb;
    }
}
