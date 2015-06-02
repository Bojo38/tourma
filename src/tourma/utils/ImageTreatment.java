/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author WFMJ7631
 */
public final class ImageTreatment {

    /**
     *
     * @param image
     * @param heigth
     * @param width
     * @return
     */
    public static ImageIcon resize(ImageIcon image, int heigth, int width) {
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    private ImageTreatment() {
    }
}
