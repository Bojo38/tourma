/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import tourma.data.Competitor;
import tourma.data.ObjectRanking;

/**
 *
 * @author Frederic Berger
 */
public final class MjtRankingManual extends MjtRanking {

    private static final Logger LOG = Logger.getLogger(MjtRankingManual.class.getName());

    public MjtRankingManual(final int round,
            final int ranking_type1,
            final int ranking_type2,
            final int ranking_type3,
            final int ranking_type4,
            final int ranking_type5,
            final ArrayList competitors, boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, competitors, round_only);
        sortDatas();
    }

    /**
     *
     */
    @Override
    protected void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList();

        addDatas(mObjects);

    }

    protected void addData(Competitor obj) {
        if (!mObjects.contains(obj)) {
            mObjects.add(obj);
        }
        ObjectRanking o = new ObjectRanking(obj, mDatas.size(), 0, 0, 0, 0);
        mDatas.add(o);

    }

    protected void delData(Competitor obj) {
        if (mObjects.contains(obj)) {
            mObjects.remove(obj);

            for (Object o : mDatas) {
                if (o instanceof ObjectRanking) {
                    if (((ObjectRanking) o).getObject().equals(obj)) {
                        mDatas.remove(o);
                        break;
                    }
                }
            }

        }

    }

    protected void addDatas(ArrayList<Competitor> objs) {
        for (Competitor obj : objs) {
            addData(obj);
        }

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
