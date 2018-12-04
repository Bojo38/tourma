/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.report;

import com.itextpdf.text.Rectangle;
import java.io.FileOutputStream;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author WFMJ7631
 */
public class HTMLtoPDFNGTest {
    
    public HTMLtoPDFNGTest() {
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
     * Test of exportToPDF_Landscape method, of class HTMLtoPDF.
     */
    @Test
    public void testExportToPDF_Landscape() {
        System.out.println("exportToPDF_Landscape");
        FileOutputStream output = null;
        String source = "";
        String title = "";
        HTMLtoPDF.exportToPDF_Landscape(output, source, title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportToPDF method, of class HTMLtoPDF.
     */
    @Test
    public void testExportToPDF_3args() {
        System.out.println("exportToPDF");
        FileOutputStream output = null;
        String source = "";
        String title = "";
        HTMLtoPDF.exportToPDF(output, source, title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportToPDF method, of class HTMLtoPDF.
     */
    @Test
    public void testExportToPDF_4args() {
        System.out.println("exportToPDF");
        FileOutputStream output = null;
        String source = "";
        String title = "";
        Rectangle size = null;
        HTMLtoPDF.exportToPDF(output, source, title, size);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportToPDF method, of class HTMLtoPDF.
     */
    @Test
    public void testExportToPDF_5args() {
        System.out.println("exportToPDF");
        FileOutputStream output = null;
        String source = "";
        String title = "";
        Rectangle size = null;
        boolean excludeCutTable = false;
        HTMLtoPDF.exportToPDF(output, source, title, size, excludeCutTable);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
