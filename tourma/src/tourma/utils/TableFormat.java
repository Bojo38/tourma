/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.awt.FontMetrics;
import javax.swing.JTable;

/**
 *
 * @author WFMJ7631
 */
public class TableFormat {

    public static void setColumnSize(final JTable t) {
        final FontMetrics fm = t.getFontMetrics(t.getFont());
        for (int i = 0; i
                < t.getColumnCount(); i++) {
            int max = 0;
            for (int j = 0; j
                    < t.getRowCount(); j++) {
                final Object value = t.getValueAt(j, i);
                String tmp = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
                if (value instanceof String) {
                    tmp = (String) value;
                }

                if (value instanceof Integer) {
                    tmp = ((Integer) value).toString();
                }

                final int taille = fm.stringWidth(tmp);
                if (taille > max) {
                    max = taille;
                }

            }
            final String nom = (String) t.getColumnModel().getColumn(i).getIdentifier();
            final int taille = fm.stringWidth(nom);
            if (taille > max) {
                max = taille;
            }

            t.getColumnModel().getColumn(i).setPreferredWidth(max + 10);
        }
    }
}
