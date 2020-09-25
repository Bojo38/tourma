/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.views.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.testng.Assert;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teamma.data.Roster;

/**
 *
 * @author WFMJ7631
 */
public class JdgPrintableRosterNGTest {

    private DialogFixture window;
    private Roster roster;
    JdgPrintableRoster jdg;

    private static Robot robot;

    @BeforeClass
    public static void setUpClass() throws Exception {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(50);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        robot.cleanUp();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {

        final SAXBuilder sxb = new SAXBuilder();
        final org.jdom.Document document = sxb.build(new File("test/necros.xml"));
        final Element racine = document.getRootElement();
        roster = new Roster();
        roster.setXMLElement(racine);


        jdg = new JdgPrintableRoster(null, true,
                roster, null, true);
        window = new DialogFixture(robot, jdg);
        window.show();

    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        window.close();
        window.cleanUp();
        window = null;
    }

    @Test
    public void hmiGeneralTest() {
        try {
            System.out.println("hmiGeneralTest");
            window.button("jbtExport").click();
            Thread.sleep(1000);
            File d = new File(".");
            File f = new File("tmp.html");
            window.fileChooser("jfc").setCurrentDirectory(d);
            window.fileChooser("jfc").selectFile(f);
            window.fileChooser("jfc").approve();
            Thread.sleep(500);
            boolean equals = compareTwoFiles("tmp.html", "test/necros.html");
            Assert.assertTrue(equals);
            //Files.delete(f.toPath());

        } catch (InterruptedException ex) {
            fail("Exception catched");
            Logger.getLogger(JdgPrintableRosterNGTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            fail("Exception catched");
            Logger.getLogger(JdgPrintableRosterNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean compareTwoFiles(String file1Path, String file2Path)
            throws IOException {

        File file1 = new File(file1Path);
        File file2 = new File(file2Path);

        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        BufferedReader br2 = new BufferedReader(new FileReader(file2));

        String thisLine = null;
        String thatLine = null;

        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();

        while ((thisLine = br1.readLine()) != null) {
            list1.add(thisLine);
        }
        while ((thatLine = br2.readLine()) != null) {
            list2.add(thatLine);
        }

        br1.close();
        br2.close();

        return list1.equals(list2);
    }
}
