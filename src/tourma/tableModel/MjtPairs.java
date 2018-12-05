/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Team;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public class MjtPairs extends AbstractTableModel implements TableCellRenderer {


    private final ArrayList<Team> mTeams1;
    private final ArrayList<Team> mTeams2;
    private final ArrayList<Boolean> mDone;

    /**
     *
     * @param teams1
     * @param teams2
     * @param done
     */
    public MjtPairs(final ArrayList<Team> teams1, final ArrayList<Team> teams2, final ArrayList<Boolean> done) {
        mTeams1 = teams1;
        mTeams2 = teams2;
        mDone = done;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return mTeams1.size();
    }

    @Override
    public String getColumnName(final int col) {
        String result = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                result = Translate.translate(Translate.CS_Table);
                break;
            case 1:
                result = Translate.translate(Translate.CS_Team)+" 1";
                break;
            case 2:
                result = StringConstants.CS_NULL;
                break;
            case 3:
                result =  Translate.translate(Translate.CS_Team)+" 2";
                break;
            default:
        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object val = StringConstants.CS_NULL;

        switch (col) {
            case 0:
                val = row + 1;
                break;
            case 1:
                val = mTeams1.get(row).getName();
                break;
            case 2:
                val = Translate.translate(Translate.CS_ACCR_Versus);
                break;
            case 3:
                val = mTeams2.get(row).getName();
                break;
            default:
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
            jlb.setText(value.toString());
        }

        Color bkg = new Color(255, 255, 255);
        final Color frg = new Color(0, 0, 0);
        if (mDone.get(row)) {
            bkg = new Color(190, 190, 190);
        }

        jlb.setBackground(bkg);
        jlb.setForeground(frg);
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;

    }

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
