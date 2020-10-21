/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import bb.tourma.data.Tournament;
import bb.tourma.data.ranking.AnnexRanking;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public abstract class MjtAnnexRank extends MjtRanking {

    boolean mFullRanking = true;

    /**
     *
     * @param round
     * @param criteria
     * @param subtype
     * @param objects
     * @param full
     * @param ranking_type1
     * @param ranking_type2
     * @param ranking_type3
     * @param ranking_type4
     * @param ranking_type5
     * @param round_only
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MjtAnnexRank(AnnexRanking ranking, boolean full, int min, int max) {
        super(ranking, min, max);
        mFullRanking = full;

    }

    public MjtAnnexRank(AnnexRanking ranking) {
        super(ranking, 0, ranking.getCount());
        mFullRanking = true;
    }


    @Override
    abstract public int getColumnCount();

    @Override
    public int getRowCount() {
        int result = Math.min(3, mMax - mMin);
        if (mFullRanking) {
            result = mMax - mMin;
        }
        return result;
    }

    

    @Override
    abstract public String getColumnName(int col);

    @Override
    abstract public Object getValueAt(int row, int col);

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final JLabel jlb = new JLabel();
        jlb.setOpaque(true);
        jlb.setBackground(new Color(255, 255, 255));
        jlb.setForeground(new Color(0, 0, 0));

        boolean useColor = false;

        useColor = Tournament.getTournament().getParams().isUseColor();

        if (value instanceof String) {
            jlb.setText((String) value);
        }
        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }

        if (!useColor) {
            if (row % 2 != 0) {
                jlb.setBackground(new Color(220, 220, 220));
            }

        }

        if (((AnnexRanking)mRanking).getSubtype() == 0) {
            if (row == 0) {
                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                if (useColor) {
                    jlb.setBackground(new Color(200, 50, 50));
                    jlb.setForeground(new Color(255, 255, 255));
                }
            }
        }

        if (((AnnexRanking)mRanking).getSubtype() == 2) {
            if (row == 0) {
                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                if (useColor) {
                    jlb.setBackground(new Color(50, 200, 50));
                    jlb.setForeground(new Color(255, 255, 255));
                }
            }
        }

        if (((AnnexRanking)mRanking).getSubtype() == 1) {
            if (row == 0) {
                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                if (useColor) {
                    jlb.setBackground(new Color(50, 50, 200));
                    jlb.setForeground(new Color(255, 255, 255));
                }
            }
        }

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
