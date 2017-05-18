/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.rmi.RemoteException;
import org.jdom2.Element;

/**
 *
 * @author WFMJ7631
 */
public interface IXMLExport {

    /**
     *
     * @return
     */
    public Element getXMLElement() ;

    /**
     *
     * @param e
     */
    public void setXMLElement(final Element e) ;
}
