/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import fi.iki.elonen.NanoHTTPD;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
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
public class WebServerNGTest {
    
    public WebServerNGTest() {
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
     * Test of getPageText method, of class WebServer.
     */
    @Test
    public void testGetPageText() {
        System.out.println("getPageText");
        String params = "";
        Map<String, String> parms = null;
        boolean withExtension = false;
        String expResult = "";
        String result = WebServer.getPageText(params, parms, withExtension);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of serve method, of class WebServer.
     */
    @Test
    public void testServe() {
        System.out.println("serve");
        NanoHTTPD.IHTTPSession session = null;
        try{
        WebServer instance = new WebServer();
        NanoHTTPD.Response expResult = null;
        NanoHTTPD.Response result = instance.serve(session);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        }
        catch (IOException e)
        {
            
        }
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMenu method, of class WebServer.
     */
    @Test
    public void testCreateMenu() {
        System.out.println("createMenu");
        boolean withExtension = false;
        String expResult = "";
        String result = WebServer.createMenu(withExtension);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createScript method, of class WebServer.
     */
    @Test
    public void testCreateScript() {
        System.out.println("createScript");
        String expResult = "";
        String result = WebServer.createScript();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStyles method, of class WebServer.
     */
    @Test
    public void testCreateStyles() {
        System.out.println("createStyles");
        String color1 = "";
        String color2 = "";
        String border_color = "";
        String forecolor = "";
        String expResult = "";
        String result = WebServer.createStyles(color1, color2, border_color, forecolor);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createHome method, of class WebServer.
     */
    @Test
    public void testCreateHome() {
        System.out.println("createHome");
        String expResult = "";
        String result = WebServer.createHome();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWebpageFile method, of class WebServer.
     */
    @Test
    public void testGetWebpageFile() {
        System.out.println("getWebpageFile");
        String url = "";
        String filename = "";
        File tmpDir = null;
        File expResult = null;
        File result = WebServer.getWebpageFile(url, filename, tmpDir);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTempDirectory method, of class WebServer.
     */
    @Test
    public void testCreateTempDirectory() throws Exception {
        System.out.println("createTempDirectory");
        File expResult = null;
        File result = WebServer.createTempDirectory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWebSiteFiles method, of class WebServer.
     */
    @Test
    public void testGetWebSiteFiles() {
        System.out.println("getWebSiteFiles");
        ArrayList expResult = null;
        ArrayList result = WebServer.getWebSiteFiles();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanFiles method, of class WebServer.
     */
    @Test
    public void testCleanFiles() {
        System.out.println("cleanFiles");
        ArrayList<File> files = null;
        WebServer.cleanFiles(files);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
