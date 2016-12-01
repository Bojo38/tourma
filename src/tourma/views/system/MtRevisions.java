/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.system;

import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author root
 */
public class MtRevisions extends AbstractTableModel implements TableCellRenderer {
    private static final Logger LOG = Logger.getLogger(MtRevisions.class.getName());

    private final ArrayList mVersions;
    private final ArrayList mDescriptions;

    /**
     *
     * @param versions
     * @param descriptions
     */
    public MtRevisions(final ArrayList versions, final ArrayList descriptions) {
        mVersions = versions;
        mDescriptions = descriptions;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return mVersions.size();
    }

    private static final String CS_Version="Version";
    private static final String CS_Description="Description";
    
    @Override
    public String getColumnName(final int col) {
        String result = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                result = Translate.translate(CS_Version);
                break;
            case 1:
                result = Translate.translate(CS_Description);
                break;
            default:
        }
        return result;
    }

    @Override
    public Object getValueAt(final int row,final int col) {
        String tmp = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                tmp = (String) (mVersions.get(row));
                break;
            case 1:
                tmp = (String) (mDescriptions.get(row));
                break;
            default:
        }
        return tmp;
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
    public Component getTableCellRendererComponent(final JTable table, final Object value,final  boolean isSelected,final  boolean hasFocus, final int row, final int column) {
        return new JLabel((String) value);
    }
}
