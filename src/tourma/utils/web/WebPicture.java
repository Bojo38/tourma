/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.xerces.impl.dv.util.Base64;

/**
 *
 * @author WFMJ7631
 */
public class WebPicture {

    public static String getPictureAsHTML(BufferedImage pic, int width, int heigth) {
        String img = "";
        if (pic != null) {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                img = "<IMG SRC=\"data:image/png;base64,";
                ImageIO.write(pic, "png", baos);
                baos.flush();
                img += Base64.encode(baos.toByteArray());
                img += "\" height=\"" + heigth + "\" >";
            } catch (final IOException ioe) {
                System.err.println(ioe.getLocalizedMessage());
                img = "";
            }
        }
        return img;
    }
    
}
