/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma;
import javax.swing.JTable;

/**
 *
 * @author Frederic Berger
 */
public class JMatchTable extends JTable {
   public JMatchTable () {
       super();
       //ou setModel() pour ceux qui veulent garder le TableModel en variable !!! (pour le clear)
       /*
        * Ici on personalise le composant
        *
        *   setRowHeight -> pour la hauteur des cellules
        *
        *   TableColumnModel model = getColumnModel();
        *   model.getColumn(numColonne).setPreferredWidth(largeur);
        *
        *   Pour écouter quand ça bouge :o)
        *   getSelectionModel().addListSelectionListener(ClasseResponsableDeLaGestionDuTruc);
        */
   }
 }
