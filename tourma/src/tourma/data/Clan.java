/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import org.jdom2.Element;

/**
 * This class contains data relative to a clan
 *
 * @author Administrateur
 */
public class Clan implements Comparable, XMLExport {

    public static HashMap<String, Clan> sClanMap = new HashMap();
    /**
     * Name of the clan
     */
    public String mName;

    
    public BufferedImage picture;
    /**
     * Constructor by name
     *
     * @param name Name of the clan
     */
    public Clan(final String name) {
        mName = name;
    }

    @Override
    public int compareTo(final Object obj) {
        int result=-1;
        if (obj instanceof Coach) {
            result=mName.compareTo(((Clan) obj).mName);
        } 
        return result;
    }

    @Override
    public Element getXMLElement() {
        final Element clan = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN"));
        clan.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), mName);
        
        if (picture!=null)
        {
        try {
            Element image=new Element("Picture");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(picture, "png", baos);
            baos.flush();
            String encodedImage = DatatypeConverter.printBase64Binary(baos.toByteArray());
            baos.close(); // should be inside a finally block
            image.addContent(encodedImage);
            clan.addContent(image);
        } catch (IOException e) {
        }
        }
        return clan;
    }

    @Override
    public void setXMLElement(final Element e) {
        this.mName=e.getAttributeValue("Name");
        if (sClanMap!=null)
        {
            sClanMap.put(mName, this);
        }        
        try {
            Element image = e.getChild("Picture");
            String encodedImage = image.getText();
            byte[] bytes = DatatypeConverter.parseBase64Binary(encodedImage);
            picture = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e2) {
        }
        catch (Exception e1)
        {
            
        }
    }
    
    
}
