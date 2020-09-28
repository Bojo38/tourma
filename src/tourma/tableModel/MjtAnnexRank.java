/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import tourma.data.Criteria;
import tourma.data.Formula;
import tourma.data.Tournament;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public abstract class MjtAnnexRank extends MjtRanking {

    protected String mType;
    @Override
    public String getDetail()
    {
        return mType;
    }
    
    @Override
    public void setDetail(String d)
    {
        mType=d;
    }
    /**
     * 
     */
    @SuppressWarnings("ProtectedField")
    protected boolean mFullRanking;
    /**
     * 
     */
    @SuppressWarnings("ProtectedField")
    protected Criteria mCriteria;
    protected Formula mFormula;
    /**
     * 
     */
    @SuppressWarnings("ProtectedField")
    protected int mSubtype;

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
    public MjtAnnexRank(final int round, final Criteria criteria, final int subtype, final ArrayList objects, final boolean full, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5,final boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, objects,round_only);
        mCriteria = criteria;
        mSubtype = subtype;
        //_ranking_type = ranking_type;
        mFullRanking = full;
    }

    public MjtAnnexRank(final int round, final Formula formula, final int subtype, final ArrayList objects, final boolean full, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5,final boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, objects,round_only);
        mFormula = formula;
        mCriteria=null;
        mSubtype = subtype;
        //_ranking_type = ranking_type;
        mFullRanking = full;
    }
    
    /**
     * this function sort the data relative to current object.
     */
    @Override
    abstract protected void sortDatas() ;

    @Override
    abstract public int getColumnCount();

    @Override
    public int getRowCount() {
        int result = Math.min(3, mDatas.size());
        if (mFullRanking) {
            result = mDatas.size();
        }
        return result;
    }

    public Criteria getCriteria()
    {
        return mCriteria;
    }
    
    public void setCriteria(Criteria c)
    {
        mCriteria=c;
    }
    
     public Formula getFormula()
    {
        return mFormula;
    }
    
    public void setFormula(Formula f)
    {
        mFormula=f;
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
        
            useColor=Tournament.getTournament().getParams().isUseColor();

        if (value instanceof String) {
            jlb.setText((String) value);
        }
        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }

        if (!useColor) {
            if (row % 2 !=0) {
                jlb.setBackground(new Color(220, 220, 220));
            }
            
        }

        if (mSubtype == 0) {
            if (row == 0) {
                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                if (useColor) {
                    jlb.setBackground(new Color(200, 50, 50));
                    jlb.setForeground(new Color(255, 255, 255));
                }
            }
        }

        if (mSubtype == 2) {
            if (row == 0) {
                jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                if (useColor) {
                    jlb.setBackground(new Color(50, 200, 50));
                    jlb.setForeground(new Color(255, 255, 255));
                }
            }
        }

        if (mSubtype == 1) {
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
