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
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author Administrateur
 */
@SuppressWarnings("serial")
public class MjtCriteriasIndivBonus extends AbstractTableModel implements TableCellRenderer {
    
    private static final String CS_CriteriaAlreadyExists = "CriteriaAlreadyExists0";
    
    private final Tournament mTour;
    private Parameters mParams;

    /**
     *
     * @param tour
     */
    public MjtCriteriasIndivBonus(final Tournament tour) {
        
        mParams = tour.getParams();
        mTour = tour;
    }
    
    @Override
    public int getColumnCount() {
        int result = 7;
        if (mParams.isTeamTournament()) {
            result = 10;
        }
        return result;
    }
    
    @Override
    public int getRowCount() {
        return mParams.getCriteriaCount();
    }
    
    @Override
    public String getColumnName(final int col) {
        String result = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                result = Translate.translate(Translate.CS_Critera_Name);
                break;
            case 1:
                result = Translate.translate(Translate.CS_Offensive_Bonus_Threshold);
                break;
            case 2:
                result = Translate.translate(Translate.CS_Diff_Offensive_Bonus_Threshold);
                break;
            case 3:
                result = Translate.translate(Translate.CS_Diff_Defensive_Bonus_Threshold);
                break;
            case 4:
                result = Translate.translate(Translate.CS_Offensive_Bonus_Value);
                break;
            case 5:
                result = Translate.translate(Translate.CS_Diff_Offensive_Bonus_Value);
                break;
            case 6:
                result = Translate.translate(Translate.CS_Diff_Defensive_Bonus_Value);
                break;
            case 7:
                result = Translate.translate(Translate.CS_ForTeam_Offensive_Bonus_Value);
                break;
            case 8:
                result = Translate.translate(Translate.CS_ForTeam_Diff_Offensive_Bonus_Value);
                break;
            case 9:
                result = Translate.translate(Translate.CS_ForTeam_Diff_Defensive_Bonus_Value);
                break;
            default:
        }
        return result;
    }
    
    @Override
    public Object getValueAt(final int row, final int col) {
        
        Object result = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                result = mParams.getCriteria(row).getName();
                break;
            case 1:
                result = mParams.getCriteria(row).getOffensiveThreshold();
                break;
            case 2:
                result = mParams.getCriteria(row).getOffensiveDiffThreshold();
                break;
            case 3:
                result = mParams.getCriteria(row).getDefensiveDiffThreshold();
                break;
            case 4:
                result = mParams.getCriteria(row).getOffensiveBonuses();
                break;
            case 5:
                result = mParams.getCriteria(row).getOffensiveDiffBonuses();
                break;
            case 6:
                result = mParams.getCriteria(row).getDefensiveDiffBonuses();
                break;
            case 7:
                result = mParams.getCriteria(row).getOffensiveBonusesForTeam();
                break;
            case 8:
                result = mParams.getCriteria(row).getOffensiveDiffBonusesForTeam();
                break;
            case 9:
                result = mParams.getCriteria(row).getDefensiveDiffBonusesForTeam();
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
                JOptionPane.showMessageDialog(null, Translate.translate(Translate.CS_Error),
                        Translate.translate(CS_CriteriaAlreadyExists), JOptionPane.ERROR_MESSAGE);
                
            } else {
                
                String tmp = value.toString();
                final Criteria c = mParams.getCriteria(row);
                int val;
                switch (col) {
                    case 0:
                        c.setName(tmp);
                        break;
                    case 1:
                        val = Integer.parseInt(tmp);
                        c.setOffensiveThreshold(val);
                        Tournament.getTournament().recomputeAll();
                        break;
                    case 2:
                        val = Integer.parseInt(tmp);
                        c.setOffensiveDiffThreshold(val);
                        Tournament.getTournament().recomputeAll();
                        break;
                    case 3:
                        val = Integer.parseInt(tmp);
                        c.setDefensiveDiffThreshold(val);
                        Tournament.getTournament().recomputeAll();
                        break;
                    case 4:
                        val = Integer.parseInt(tmp);
                        c.setOffensiveBonuses(val);
                        Tournament.getTournament().recomputeAll();
                        break;
                    case 5:
                        val = Integer.parseInt(tmp);
                        c.setOffensiveDiffBonuses(val);
                        Tournament.getTournament().recomputeAll();
                        break;
                    case 6:
                        val = Integer.parseInt(tmp);
                        c.setDefensiveDiffBonuses(val);
                        Tournament.getTournament().recomputeAll();
                        break;
                    case 7:
                        val = Integer.parseInt(tmp);
                        c.setOffensiveBonusesForTeam(val);
                        Tournament.getTournament().recomputeAll();
                        break;
                    case 8:
                        val = Integer.parseInt(tmp);
                        c.setOffensiveDiffBonusesForTeam(val);
                        Tournament.getTournament().recomputeAll();
                        break;
                    case 9:
                        val = Integer.parseInt(tmp);
                        c.setDefensiveDiffBonusesForTeam(val);
                        Tournament.getTournament().recomputeAll();
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
