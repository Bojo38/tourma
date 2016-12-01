/*
 * Copyright 2015 Quan Nguyen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sourceforge.lept4j.util;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.Locale;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;

import com.github.jaiimageio.plugins.tiff.TIFFImageWriteParam;
import com.sun.jna.ptr.PointerByReference;

import com.ochafik.lang.jnaerator.runtime.NativeSize;
import com.ochafik.lang.jnaerator.runtime.NativeSizeByReference;

import static net.sourceforge.lept4j.ILeptonica.IFF_TIFF;
import net.sourceforge.lept4j.Leptonica1;
import net.sourceforge.lept4j.Pix;

/**
 * Various utility methods for Leptonica.
 *
 */
public class LeptUtils {

    final static String JAI_IMAGE_WRITER_MESSAGE = "Need to install JAI Image I/O package.\nhttps://java.net/projects/jai-imageio/";
    final static String TIFF_FORMAT = "tiff";
    final static float deg2rad = (float) (3.14159 / 180.);

    /**
     * Converts Leptonica <code>Pix</code> to <code>BufferedImage</code>.
     *
     * @param pix
     * @return BufferedImage
     * @throws IOException
     */
    public static BufferedImage convertPixToImage(Pix pix) throws IOException {
        PointerByReference pdata = new PointerByReference();
        NativeSizeByReference psize = new NativeSizeByReference();
        int format = IFF_TIFF;
        Leptonica1.pixWriteMem(pdata, psize, pix, format);
        byte[] b = pdata.getValue().getByteArray(0, psize.getValue().intValue());
        InputStream in = new ByteArrayInputStream(b);
        BufferedImage bi = ImageIO.read(in);
        in.close();
        return bi;
    }

    /**
     * Converts <code>BufferedImage</code> to Leptonica <code>Pix</code> .
     *
     * @param image
     * @return Pix
     * @throws IOException
     */
    public static Pix convertImageToPix(BufferedImage image) throws IOException {
        ByteBuffer buff = getImageByteBuffer(image);
        Pix pix = Leptonica1.pixReadMem(buff, new NativeSize(buff.capacity()));
        return pix;
    }

    /**
     * Removes horizontal lines from a grayscale image. The algorithm is based
     * on Leptonica <code>lineremoval.c</code> example.
     *
     * @see
     * <a href="http://www.leptonica.com/line-removal.html">line-removal</a>
     *
     * @param pixs input image
     * @return image with lines removed
     */
    public static Pix removeLines(Pix pixs) {
        float angle, conf;
        Pix pix1, pix2, pix3, pix4, pix5;
        Pix pix6, pix7, pix8, pix9;

        /* threshold to binary, extracting much of the lines */
        pix1 = Leptonica1.pixThresholdToBinary(pixs, 170);

        /* find the skew angle and deskew using an interpolated
         * rotator for anti-aliasing (to avoid jaggies) */
        FloatBuffer pangle = FloatBuffer.allocate(1);
        FloatBuffer pconf = FloatBuffer.allocate(1);
        Leptonica1.pixFindSkew(pix1, pangle, pconf);
        angle = pangle.get();
        conf = pconf.get();
        pix2 = Leptonica1.pixRotateAMGray(pixs, (float) (deg2rad * angle), (byte) 255);

        /* extract the lines to be removed */
        pix3 = Leptonica1.pixCloseGray(pix2, 51, 1);

        /* solidify the lines to be removed */
        pix4 = Leptonica1.pixErodeGray(pix3, 1, 5);

        /* clean the background of those lines */
        pix5 = Leptonica1.pixThresholdToValue(null, pix4, 210, 255);

        pix6 = Leptonica1.pixThresholdToValue(null, pix5, 200, 0);

        /* get paint-through mask for changed pixels */
        pix7 = Leptonica1.pixThresholdToBinary(pix6, 210);

        /* add the inverted, cleaned lines to orig.  Because
         * the background was cleaned, the inversion is 0,
         * so when you add, it doesn't lighten those pixels.
         * It only lightens (to white) the pixels in the lines! */
        Leptonica1.pixInvert(pix6, pix6);
        pix8 = Leptonica1.pixAddGray(null, pix2, pix6);

        pix9 = Leptonica1.pixOpenGray(pix8, 1, 9);

        Leptonica1.pixCombineMasked(pix8, pix9, pix7);

        // resource cleanup
        disposePix(pix1);
        disposePix(pix2);
        disposePix(pix3);
        disposePix(pix4);
        disposePix(pix5);
        disposePix(pix6);
        disposePix(pix7);
        disposePix(pix9);

        return pix8;
    }

    /**
     * Disposes of Pix resource.
     *
     * @param pix
     */
    public static void disposePix(Pix pix) {
        if (pix == null) {
            return;
        }
        PointerByReference pRef = new PointerByReference();
        pRef.setValue(pix.getPointer());
        Leptonica1.pixDestroy(pRef);
    }

    /**
     * Gets image data of an <code>RenderedImage</code> object.
     *
     * @param image an <code>RenderedImage</code> object
     * @return a byte buffer of image data
     * @throws IOException
     */
    static ByteBuffer getImageByteBuffer(RenderedImage image) throws IOException {
        //Set up the writeParam
        TIFFImageWriteParam tiffWriteParam = new TIFFImageWriteParam(Locale.US);
        tiffWriteParam.setCompressionMode(ImageWriteParam.MODE_DISABLED);

        //Get tif writer and set output to file
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(TIFF_FORMAT);

        if (!writers.hasNext()) {
            throw new RuntimeException(JAI_IMAGE_WRITER_MESSAGE);
        }

        ImageWriter writer = writers.next();

        //Get the stream metadata
        IIOMetadata streamMetadata = writer.getDefaultStreamMetadata(tiffWriteParam);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageOutputStream ios = ImageIO.createImageOutputStream(outputStream);
        writer.setOutput(ios);
        writer.write(streamMetadata, new IIOImage(image, null, null), tiffWriteParam);
//        writer.write(image);
        writer.dispose();
        ios.seek(0);
        byte[] b = new byte[(int) ios.length()];
        ios.read(b);
        ios.close();

        ByteBuffer buf = ByteBuffer.allocateDirect(b.length);
        buf.order(ByteOrder.nativeOrder());
        buf.put(b);
        buf.flip();
        return buf;
    }
}
