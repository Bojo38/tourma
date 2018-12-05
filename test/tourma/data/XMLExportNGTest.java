/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom.Element;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 *
 * @author WFMJ7631
 */
public class XMLExportNGTest {
    
    public XMLExportNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getXMLElement method, of class IXMLExport.
     */
    @Test(enabled=false)
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        IXMLExport instance = new XMLExportImpl();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);

    }

    /**
     * Test of setXMLElement method, of class IXMLExport.
     */
    @Test(enabled=false)
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        IXMLExport instance = new XMLExportImpl();
        instance.setXMLElement(e);
    }

    public class XMLExportImpl implements IXMLExport {

        public Element getXMLElement() {
            return null;
        }

        public void setXMLElement(Element e) {
        }
    }
    
}
