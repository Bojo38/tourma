/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Coach;
import tourma.data.Tournament;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class mjtCoaches extends AbstractTableModel implements TableCellRenderer {

    ArrayList<Coach> mCoachs;

    public mjtCoaches(final ArrayList<Coach> coachs) {
        mCoachs = coachs;
    }

    @Override
    public int getColumnCount() {

        int result = 8;
        if (Tournament.getTournament().getParams().mEnableClans) {
            result = 9;
        }
        return result;
    }

    @Override
    public int getRowCount() {
        return mCoachs.size();
    }

    @Override
    public String getColumnName(final int col) {
        String val = "";
        ResourceBundle bundle=java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);
        switch (col) {
            case 0:
                val = "#";
                break;
            case 1:
                val = bundle.getString("Coach");
                break;
            case 2:
                val = bundle.getString("Team");
                break;
            case 3:
                val = "Roster";
                break;
            case 4:
                val = "NAF";
                break;
            case 5:
                val = bundle.getString("Ranking");
                break;
            case 8:
                val ="Clan";
                break;
            case 6:
                val = "";
                break;
            case 7:
                val = "Classement";
                break;
            default:
        }
        return val;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object val = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        if (mCoachs.size() > 0) {
            final Coach c = mCoachs.get(row);
            switch (col) {
                case 0:
                    val = row + 1;
                    break;
                case 1:
                    val = c.mName;
                    break;
                case 2:
                    val = c.mTeam;
                    break;
                case 3:
                    if (c.mRoster!=null)
                    {
                    val = c.mRoster.mName;
                    }
                    else
                    {
                        val="unknown";
                    }
                    break;
                case 4:
                    val = c.mNaf;
                    break;
                case 5:
                    val = c.mRank;
                    break;
                case 7:
                    val = Double.toString(c.mNafRank);
                    break;
                case 8:
                    val = c.mClan.mName;
                    break;
                case 6:
                    if (c.mActive) {
                        val = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ACTIF");
                    } else {
                        val = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INACTIF");
                    }
                    break;
                default:
            }
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

        final Coach c = mCoachs.get(row);
        if (!c.mActive) {
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
