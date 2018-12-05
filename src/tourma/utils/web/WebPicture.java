/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.xerces.impl.dv.util.Base64;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class WebPicture {

    public static String getPictureAsHTML(ImageIcon pic, int width, int heigth) {

        if (pic != null) {
            BufferedImage bi = new BufferedImage(pic.getIconWidth(), pic.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            pic.paintIcon(null, g, 0, 0);
            g.dispose();
            return getPictureAsHTML(bi, width, heigth, Tournament.getTournament().getParams().isUseImage());
        } else {
            return "";
        }

    }

    public static String getPictureAsHTML(BufferedImage pic, int width, int heigth) {

        return getPictureAsHTML(pic, width, heigth, Tournament.getTournament().getParams().isUseImage());

    }

    public static String getPictureAsHTML(BufferedImage pic, int width, int heigth, boolean use_image) {
        String img = "";
        if (use_image) {
            if (pic != null) {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    img = "<IMG SRC=\"data:image/png;base64,";
                    ImageIO.write(pic, "png", baos);
                    baos.flush();
                    img += Base64.encode(baos.toByteArray());
                    img += "\" height=\"" + heigth + "\" ></IMG>";
                } catch (final IOException ioe) {
                    System.err.println(ioe.getLocalizedMessage());
                    img = "";
                }
            }
        }
        return img;
    }

}
