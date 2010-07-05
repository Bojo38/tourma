/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/*class InternalActionListener implements ActionListener, FocusListener {

    int _col;
    Match _m;
    mjtMatches _model;

    public InternalActionListener(int col, Match m, mjtMatches model) {
        _col = col;
        _m = m;
        _model = model;
    }

    private void updateData(JTextField source) {
        int data = 0;
        Match m = _m;
        try {
            data = Integer.parseInt(source.getText());
        } catch (NumberFormatException ex) {
        }
        switch (_col) {
            case 2:
                m._td1 = data;
                break;
            case 3:
                m._td2 = data;
                break;
            case 5:
                m._sor1 = data;
                break;
            case 6:
                m._sor2 = data;
                break;
            case 7:
                m._foul1 = data;
                break;
            case 8:
                m._foul2 = data;
                break;
        }
        _model.stopCellEditing();
        _model.fireTableStructureChanged();
    }

    public void actionPerformed(ActionEvent e) {
        updateData((JTextField) e.getSource());
    }

    public void focusGained(FocusEvent e) {
    }

    public void focusLost(FocusEvent e) {
        updateData((JTextField) e.getSource());
    }
}*/

/**
 *
 * @author Frederic Berger
 */
public class mjtMatches extends AbstractTableModel implements TableCellRenderer/*, CellEditorListener, TableCellEditor*/ {

    Vector<Match> _matchs;
    boolean _locked;

    public mjtMatches(Vector<Match> matchs, boolean locked) {
        _matchs = matchs;
        _locked = locked;
//        this.addCellEditorListener(this);
    }

    public int getColumnCount() {
        return 9;
    }

    public int getRowCount() {
        return _matchs.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Table";
            case 1:
                return "Equipe 1";
            case 2:
                return "Score 1";
            case 3:
                return "Score 2";
            case 4:
                return "Equipe 2";
            case 5:
                return "Sorties 1";
            case 6:
                return "Sorties 2";
            case 7:
                return "Foul 1";
            case 8:
                return "Foul 2";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        Match m = _matchs.get(row);
        switch (col) {
            case 0:
                return row + 1;
            case 1:
                return m._coach1._team + " (" + m._coach1._name + ")";
            case 2:
                return m._td1;
            case 3:
                return m._td2;
            case 4:
                return m._coach2._team + " (" + m._coach2._name + ")";
            case 5:
                return m._sor1;
            case 6:
                return m._sor2;
            case 7:
                return m._foul1;
            case 8:
                return m._foul2;
        }
        return "";
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (value != null) {
            Match m = _matchs.get(row);
            switch (col) {
                case 2:
                    m._td1 = (Integer.valueOf(value.toString()));
                    break;
                case 3:
                    m._td2 = (Integer.valueOf(value.toString()));
                    break;
                case 5:
                    m._sor1 = (Integer.valueOf(value.toString()));
                    break;
                case 6:
                    m._sor2 = (Integer.valueOf(value.toString()));
                    break;
                case 7:
                    m._foul1 = (Integer.valueOf(value.toString()));
                    break;
                case 8:
                    m._foul2 = (Integer.valueOf(value.toString()));
                    break;
            }
        }
        fireTableDataChanged();
    }

    @Override
    public Class getColumnClass(
            int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (_locked) {
            return false;
        } else {
            if ((col == 1) || (col == 4)) {
                return false;
            }

        }
        return true;
    }

    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JTextField jlb = new JTextField();

        jlb.setEditable(false);
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
            switch (column) {
                case 1:
                    bkg = new Color(200, 50, 50);
                    frg =
                            new Color(255, 255, 255);
                    if (_matchs.get(row)._td1 > _matchs.get(row)._td2) {
                        jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                    }

                    if (_matchs.get(row)._td1 == _matchs.get(row)._td2) {
                        jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                    }

                    break;
                case 2:
                    bkg = new Color(250, 200, 200);
                    break;

                case 3:
                    bkg = new Color(200, 200, 250);
                    break;

                case 4:
                    bkg = new Color(50, 50, 250);
                    frg =
                            new Color(255, 255, 255);
                    if (_matchs.get(row)._td1 < _matchs.get(row)._td2) {
                        jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
                    }

                    if (_matchs.get(row)._td1 == _matchs.get(row)._td2) {
                        jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
                    }

                    break;
                case 5:
                    frg = new Color(200, 50, 50);
                    break;

                case 6:
                    frg = new Color(50, 50, 200);
                    break;

                case 7:
                    frg = new Color(200, 50, 50);
                    break;

                case 8:
                    frg = new Color(50, 50, 200);
                    break;

                default:

                    break;
            }

        }
        jlb.setBackground(bkg);
        jlb.setForeground(frg);
        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }

    /* public void editingStopped(ChangeEvent e) {
    int data = 0;
    Match m=_matchs.get(_currentrow);
    try
    {
    data=Integer.parseInt(_editor.getText());
    }
    catch (NumberFormatException ex)
    {

    }
    switch (_currentcol) {
    case 2:
    m._td1 = data;
    break;
    case 3:
    m._td2 = data;
    break;
    case 5:
    m._sor1 = data;
    break;
    case 6:
    m._sor2 = data;
    break;
    case 7:
    m._foul1 = data;
    break;
    case 8:
    m._foul2 = data;
    break;
    }
    }

    public void editingCanceled(ChangeEvent e) {
    _editor = null;
    }*/
  /*  public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
        JTextField jtf = new JTextField();

        jtf.setEditable(true);
        if (value instanceof String) {
            jtf.setText((String) value);
        }

        if (value instanceof Integer) {
            jtf.setText(Integer.toString((Integer) value));
        }

        Color bkg = new Color(255, 255, 255);
        Color frg = new Color(0, 0, 0);
        if (isSelected) {
            bkg = new Color(200, 200, 200);
        } else {
            switch (column) {
                case 1:
                    bkg = new Color(200, 50, 50);
                    frg =
                            new Color(255, 255, 255);
                    if (_matchs.get(row)._td1 > _matchs.get(row)._td2) {
                        jtf.setFont(jtf.getFont().deriveFont(Font.BOLD));
                    }

                    if (_matchs.get(row)._td1 == _matchs.get(row)._td2) {
                        jtf.setFont(jtf.getFont().deriveFont(Font.ITALIC));
                    }

                    break;
                case 2:
                    bkg = new Color(250, 200, 200);
                    break;

                case 3:
                    bkg = new Color(200, 200, 250);
                    break;

                case 4:
                    bkg = new Color(50, 50, 250);
                    frg =
                            new Color(255, 255, 255);
                    if (_matchs.get(row)._td1 < _matchs.get(row)._td2) {
                        jtf.setFont(jtf.getFont().deriveFont(Font.BOLD));
                    }

                    if (_matchs.get(row)._td1 == _matchs.get(row)._td2) {
                        jtf.setFont(jtf.getFont().deriveFont(Font.ITALIC));
                    }

                    break;
                case 5:
                    frg = new Color(200, 50, 50);
                    break;

                case 6:
                    frg = new Color(50, 50, 200);
                    break;

                case 7:
                    frg = new Color(200, 50, 50);
                    break;

                case 8:
                    frg = new Color(50, 50, 200);
                    break;

                default:

                    break;
            }

        }
        jtf.setBackground(bkg);
        jtf.setForeground(frg);
        jtf.setHorizontalAlignment(JTextField.CENTER);
        jtf.addActionListener(new InternalActionListener(column, _matchs.get(row), this));
        jtf.addFocusListener(new InternalActionListener(column, _matchs.get(row), this));
        return jtf;
    }*/

    /*JTextField _editor = null;
     */
  /*  Vector<CellEditorListener> _listeners = new Vector<CellEditorListener>();

    public void addCellEditorListener(CellEditorListener l) {
        _listeners.add(l);
    }

    public void cancelCellEditing() {
    }

    public Object getCellEditorValue() {
        return null;
    }

    public boolean isCellEditable(EventObject anEvent) {
        return true;

    }

    public void removeCellEditorListener(CellEditorListener l) {
        _listeners.remove(l);
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    public boolean stopCellEditing() {
        return true;
    }*/
}
