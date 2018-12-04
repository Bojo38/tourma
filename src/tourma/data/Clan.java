/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.xerces.impl.dv.util.Base64;
import org.jdom.Element;
import tourma.utility.StringConstants;

/**
 * This class contains data relative to a clan
 *
 * @author Administrateur
 */
public class Clan implements Comparable<Object>, IXMLExport, IWithNameAndPicture, Serializable {

    protected static AtomicInteger sGenUID = new AtomicInteger(0);
    protected int UID = sGenUID.incrementAndGet();

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }
    protected boolean updated = false;

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public void pull(Clan clan) {
        this.UID = clan.UID;
        this.mName = clan.mName;
        this.picture = clan.picture;
    }

    public void push(Clan clan) {
        if (clan.updated) {
            this.UID = clan.UID;
            this.mName = clan.mName;
            this.picture = clan.picture;
        }
    }

    /**
     *
     */
    private static HashMap<String, Clan> sClanMap = new HashMap<>();

    private static final Logger LOG = Logger.getLogger(Clan.class.getName());

    /**
     *
     * @return
     */
    /*public static HashMap<String, Clan> getsClanMap() {
        return sClanMap;
    }*/
    /**
     *
     * @param key
     * @return
     */
    public static Clan getClan(String key) {
        return sClanMap.get(key);
    }

    public static void delClan(String key) {
        sClanMap.remove(key);
    }

    /**
     *
     * @param key
     * @param c
     */
    public static void putClan(String key, Clan c) {
        sClanMap.put(key, c);
    }

    /**
     * New Clan Map
     */
    public static void newClanMap() {
        sClanMap = new HashMap<>();
    }

    /**
     * Name of the clan
     */
    private String mName;

    /**
     *
     */
    private ImageIcon picture;

    /**
     * Constructor by name
     *
     * @param name Name of the clan
     */
    public Clan(final String name) {
        mName = name;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(final Object obj) {

        boolean result;
        result = false;
        if (obj instanceof Clan) {
            Clan cat = (Clan) obj;
            result = this.getName().equals(cat.getName());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.mName);
        hash = 11 * hash + Objects.hashCode(this.picture);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public int compareTo(final Object obj) {
        int result = -1;
        if (obj instanceof Clan) {
            Clan clan = (Clan) obj;
            result = this.getName().compareTo(clan.getName());
        }
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element clan = new Element(StringConstants.CS_CLAN);
        clan.setAttribute(StringConstants.CS_NAME, mName);

        if (picture != null) {
            try {
                Element image = new Element(StringConstants.CS_PICTURE);
                String encodedImage;
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    BufferedImage bi = new BufferedImage(getPicture().getIconWidth(), getPicture().getIconHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics g = bi.createGraphics();
                    getPicture().paintIcon(null, g, 0, 0);
                    g.dispose();
                    ImageIO.write(bi, "png", baos);
                    baos.flush();
                    //encodedImage = DatatypeConverter.printBase64Binary(baos.toByteArray());
                    encodedImage = Base64.encode(baos.toByteArray());
                    // should be inside a finally block
                }
                image.addContent(encodedImage);
                clan.addContent(image);
            } catch (IOException e) {
            }
        }
        return clan;
    }

    /**
     *
     * @param e
     */
    @Override
    public void setXMLElement(final Element e) {
        this.mName = e.getAttributeValue(StringConstants.CS_NAME);
        if (sClanMap != null) {
            sClanMap.put(mName, this);
        }
        try {
            Element image = e.getChild(StringConstants.CS_PICTURE);
            String encodedImage = image.getText();
            //byte[] bytes = DatatypeConverter.parseBase64Binary(encodedImage);
            byte[] bytes = Base64.decode(encodedImage);
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(bytes));
            ImageIcon ii = new ImageIcon(bi);
            setPicture(ii);

        } catch (IOException e2) {
        } catch (NullPointerException e1) {
            LOG.log(Level.FINE, e1.getLocalizedMessage());
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return mName;
    }

    /**
     *
     * @return
     */
    @Override
    public ImageIcon getPicture() {
        return picture;
    }

    /**
     *
     * @param p
     */
    @Override
    public void setPicture(ImageIcon p) {
        picture = p;
        updated=true;
    }

    /**
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        mName = name;
        updated=true;
    }

    @Override
    public String toString() {
        return mName;
    }
}
