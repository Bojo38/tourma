/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Coach;
import tourma.data.Team;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public class MjtTeams extends AbstractTableModel implements TableCellRenderer {

    private final ArrayList<Team> mTeams;

    /**
     *
     * @param teams
     */
    public MjtTeams(final ArrayList<Team> teams) {
        mTeams = teams;
    }

    @Override
    public int getColumnCount() {

        int nbCol = 2;

        for (int i = 0; i < mTeams.size(); i++) {
            nbCol = Math.max(nbCol, mTeams.get(i).getCoachsCount() + 2);
        }

        return nbCol;
    }

    @Override
    public int getRowCount() {
        return mTeams.size();
    }

    @Override
    public String getColumnName(final int col) {
        String val;
        switch (col) {
            case 0:
                val = StringConstants.CS_HASH;
                break;
            case 1:
                val = Translate.translate(Translate.CS_Name);
                break;
            default:
                val = Translate.translate(Translate.CS_Coach) + (col - 1);
        }
        return val;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object object = StringConstants.CS_NULL;
        if (mTeams.size() > 0) {
            final Team t = mTeams.get(row);
            switch (col) {
                case 0:
                    object = row + 1;
                    break;
                case 1:
                    object = t.getName();
                    break;
                default:
            }
            if (t.getCoachsCount() > 0) {
                if (t.getCoachsCount() > (col - 2)) {
                    if (col >= 2) {
                        object = t.getCoach(col - 2).getName();
                    }
                }
            }
        }

        return object;
    }

    @Override
    public Class getColumnClass(final int c) {
        Object obj = getValueAt(0, c);
        Class res = null;
        if (obj != null) {
            res = obj.getClass();
        }
        return res;
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(final int row, final int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return false;
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

        final Team t = mTeams.get(row);
        if (t.getCoachsCount() > column - 2) {
            if (column >= 2) {
                final Coach c = t.getCoach(column - 2);
                if (!c.isActive()) {
                    jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                }
            }
        }

        jlb.setHorizontalAlignment(JTextField.CENTER);

        if (isSelected) {
            jlb.setBackground(Color.LIGHT_GRAY);
        } else {
            jlb.setBackground(Color.WHITE);
        }
        return jlb;
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
