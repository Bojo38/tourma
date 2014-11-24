/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.util.logging.Logger;
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
public class MjtCriterias extends AbstractTableModel implements TableCellRenderer {
    private static final Logger LOG = Logger.getLogger(MjtCriterias.class.getName());
    private static final long serialVersionUID = 2L;

    private final Tournament mTour;
    private final Parameters mParams;

    /**
     *
     * @param tour
     */
    public MjtCriterias(final Tournament tour) {
        mParams = tour.getParams();
        mTour = tour;
    }

    @Override
    public int getColumnCount() {
        int result = 3;
        if (mParams.isTeamTournament()) {
            result = 5;
        }
        return result;
    }

    @Override
    public int getRowCount() {
        return mParams.getCriteriaCount();
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
                result = mParams.getCriteria(row).getName();
                break;
            case 1:
                result = mParams.getCriteria(row).getPointsFor();
                break;
            case 2:
                result = mParams.getCriteria(row).getPointsAgainst();
                break;
            case 3:
                result = mParams.getCriteria(row).getPointsTeamFor();
                break;
            case 4:
                result = mParams.getCriteria(row).getPointsTeamAgainst();
                break;
            default:
        }
        return result;
    }

    @Override
    public void setValueAt(final Object value, final int row, final int col) {
        if (value != null) {

            boolean exists = false;
            for (int i = 0; i < mParams.getCriteriaCount(); i++) {
                if (i != row) {
                    if (value.toString().equals(mParams.getCriteria(i).getName())) {
                        exists = true;
                        break;
                    }
                }
            }
            if (exists) {
                JOptionPane.showMessageDialog(null, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ERROR"),java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CriteriaAlreadyExists0")+value.toString(),JOptionPane.ERROR_MESSAGE);
                
            } else {
                String tmp=value.toString();
                final Criteria c = mParams.getCriteria(row);
                int val;
                switch (col) {
                    case 0:
                        c.setName(tmp);
                        break;
                    case 1:
                        val=Integer.parseInt(tmp);
                        c.setPointsFor(val);
                        break;
                    case 2:
                        val=Integer.parseInt(tmp);
                        c.setPointsAgainst(val);
                        break;
                    case 3:
                        val=Integer.parseInt(tmp);
                        c.setPointsTeamFor(val);
                        break;
                    case 4:
                        val=Integer.parseInt(tmp);
                        c.setPointsTeamAgainst(val);
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
        return mTour.getRoundsCount() <= 0;
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
    
    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
