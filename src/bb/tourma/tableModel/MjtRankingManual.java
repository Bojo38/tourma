/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import bb.tourma.data.Competitor;
import bb.tourma.data.ObjectRanking;
import bb.tourma.data.ranking.ManualRanking;

/**
 *
 * @author Frederic Berger
 */
public final class MjtRankingManual extends MjtRanking {

    private static final Logger LOG = Logger.getLogger(MjtRankingManual.class.getName());

    public MjtRankingManual(ManualRanking ranking, int min, int max) {
        super(ranking, min, max);
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public String getColumnName(final int col) {
        return "";
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object object = "";
        return object;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        return obj;
    }
}
