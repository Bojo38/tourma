/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public class MjtManualMatchs extends AbstractTableModel implements TableCellRenderer {

    private final ArrayList<Competitor> mComps;

    /**
     *
     * @param matchs
     * @param locked
     * @param teamTournament
     * @param full
     */
    public MjtManualMatchs(final ArrayList<Competitor> comps) {
        mComps = comps;

    }

    @Override
    public int getColumnCount() {

        return 3;
    }

    @Override
    public int getRowCount() {
        int count = mComps.size() / 2;
        return count;
    }

    @Override
    public String getColumnName(final int col) {

        String result = "";

        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object obj = "";

        if (col == 0) {
            obj = mComps.get(2 * row);
        }

        if (col == 2) {
            obj = mComps.get(2 * row + 1);
        }
        return obj;
    }

    @Override
    public void setValueAt(final Object value, final int row, final int col) {

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

        boolean result = false;

        return result;
    }

    @Override
    public Component getTableCellRendererComponent(
            final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final JTextField jlb = new JTextField();

        jlb.setEditable(false);

        boolean useColor = false;

        useColor = Tournament.getTournament().getParams().isUseColor();

        if (value instanceof String) {
            jlb.setText((String) value);
        }

        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }

        Color bkg = new Color(255, 255, 255);
        Color frg = new Color(0, 0, 0);

        if (isSelected) {
            bkg = new Color(200, 200, 200);
        } else {
            Value val = null;
            switch (column) {
                case 0:
                    if (useColor) {
                        bkg = new Color(200, 150, 150);
                    } else {
                        if (row % 2 != 0) {
                            bkg = new Color(220, 220, 220);
                        }
                    }
                    break;
                case 2:
                    if (useColor) {
                        bkg = new Color(150, 150, 220);
                    } else {
                        if (row % 2 != 0) {
                            bkg = new Color(220, 220, 220);
                        }
                    }
                    break;
            }
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
