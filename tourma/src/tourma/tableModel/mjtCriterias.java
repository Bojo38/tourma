/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Criteria;
import tourma.data.Parameters;
import tourma.data.Tournament;

/**
 *
 * @author Administrateur
 */
public class mjtCriterias extends AbstractTableModel implements TableCellRenderer {

    Tournament mTour;
    Parameters mParams;

    public mjtCriterias(final Tournament tour) {
        mParams = tour.getParams();
        mTour = tour;
    }

    @Override
    public int getColumnCount() {
        int result = 3;
        if (mParams.mTeamTournament) {
            result = 5;
        }
        return result;
    }

    @Override
    public int getRowCount() {
        return mParams.mCriterias.size();
    }

    @Override
    public String getColumnName(final int col) {
        String result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        switch (col) {
            case 0:
                result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOM CRITÈRE");
                break;
            case 1:
                result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS +");
                break;
            case 2:
                result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS -");
                break;
            case 3:
                result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS EQUIPE +");
                break;
            case 4:
                result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS EQUIPE -");
                break;
            default:
        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {

        Object result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        switch (col) {
            case 0:
                result = mParams.mCriterias.get(row).mName;
                break;
            case 1:
                result = mParams.mCriterias.get(row).mPointsFor;
                break;
            case 2:
                result = mParams.mCriterias.get(row).mPointsAgainst;
                break;
            case 3:
                result = mParams.mCriterias.get(row).mPointsTeamFor;
                break;
            case 4:
                result = mParams.mCriterias.get(row).mPointsTeamAgainst;
                break;
            default:
        }
        return result;
    }

    @Override
    public void setValueAt(final Object value, final int row, final int col) {
        if (value != null) {

            boolean exists = false;
            for (int i = 0; i < mParams.mCriterias.size(); i++) {
                if (i != row) {
                    if (value.toString().equals(mParams.mCriterias.get(i).mName)) {
                        exists = true;
                        break;
                    }
                }
            }
            if (exists) {
                JOptionPane.showMessageDialog(null, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ERROR"),java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CriteriaAlreadyExists0")+value.toString(),JOptionPane.ERROR_MESSAGE);
                
            } else {

                final Criteria c = mParams.mCriterias.get(row);
                switch (col) {
                    case 0:
                        c.mName = value.toString();
                        break;
                    case 1:
                        c.mPointsFor = Integer.valueOf(value.toString());
                        break;
                    case 2:
                        c.mPointsAgainst = Integer.valueOf(value.toString());
                        break;
                    case 3:
                        c.mPointsTeamFor = Integer.valueOf(value.toString());
                        break;
                    case 4:
                        c.mPointsTeamAgainst = Integer.valueOf(value.toString());
                        break;
                    default:
                }
            }
        }
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
        return mTour.getRounds().size() <= 0;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final JTextField jlb = new JTextField();

        jlb.setEditable(false);
        if (value instanceof String) {
            jlb.setText((String) value);
        }

        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }

        Color bkg = new Color(255, 255, 255);
        final Color frg = new Color(0, 0, 0);
        if (isSelected) {
            bkg = new Color(200, 200, 200);
        }
        jlb.setBackground(bkg);
        jlb.setForeground(frg);
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }
}
