/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.fixture.DialogFixture;
import org.testng.Assert;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teamma.data.LRB;

/**
 *
 * @author WFMJ7631
 */
public class JdgRosterNGTest {

    private DialogFixture window;
    JdgRoster jdg;

    public JdgRosterNGTest() {
    }

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

        jdg = new JdgRoster(null, true);
        window = new DialogFixture(robot, jdg);
        window.show();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        window.close();
        window.cleanUp();
    }

    @Test
    public void hmiGeneralTest() {
        try {
            System.out.println("hmiGeneralTest");

            // Select tab and choose roster
            window.tabbedPane("jtpGoods").selectTab(3);
            Thread.sleep(500);
            window.button("jlbIcon").click();
            Thread.sleep(1000);
            window.optionPane().list().selectItem(1);
            window.optionPane().okButton().click();
            Thread.sleep(200);
            String text = window.label("jlbRosterType").text();
            Assert.assertEquals(text, "Roster: Elus du Chaos");

            // Select tab and Add StarPlayers
            window.tabbedPane("jtpGoods").selectTab(2);
            Assert.assertFalse(window.button("jbtDelChampion").component().isEnabled());
            window.button("jbtAddChampion").click();
            Thread.sleep(500);

            DialogFixture window2 = WindowFinder.findDialog(JdgSelectPosition.class).withTimeout(10000).using(robot);
            Thread.sleep(500);
            window2.table("jtPositions").selectRows(1);
            window2.button("ok").click();
            Thread.sleep(200);
            Assert.assertTrue(window.table("jtbStars").rowCount() == 1);
            Assert.assertFalse(window.button("jbtDelChampion").component().isEnabled());
            window.table("jtbStars").selectRows(0);
            Assert.assertTrue(window.button("jbtDelChampion").component().isEnabled());
            window.button("jbtDelChampion").click();
            Assert.assertTrue(window.table("jtbStars").rowCount() == 0);
            Assert.assertFalse(window.button("jbtDelChampion").component().isEnabled());

            // Select tab and Choose Inducements
            window.tabbedPane("jtpGoods").selectTab(1);

            String array[] = {"ExtraReroll", "BribeTheRef", "LocalApothecary", "Wizard", "Igor", "Babes", "Chef", "ChaosWizard", "Horatio", "Kari", "Fink", "Papa", "Galandril", "Krot"};
            for (String ext : array) {
                if (window.slider("jsl" + ext).component().isEnabled()) {
                    window.slider("jsl" + ext).slideToMaximum();
                    String cost = window.label("jlbCost" + ext).text();
                    String nb = window.label("jlbNb" + ext).text();
                    String price = window.label("jlbPrice" + ext).text();
                    System.out.println(ext + ": " + nb + " * " + price + " = " + cost);
                    Assert.assertEquals(Integer.parseInt(cost), Integer.parseInt(nb) * Integer.parseInt(price));
                }
            }

            if (window.slider("jslCardBudget").component().isEnabled()) {
                window.slider("jslCardBudget").slideToMaximum();
                int max = window.slider("jslCardBudget").component().getValue();
                Random r = new Random();

                for (int i = 0; i < 10; i++) {
                    int set = Math.abs(r.nextInt()) % max;
                    window.slider("jslCardBudget").slideTo(set);
                    int nb = set / 50000;
                    int nb2 = set % 50000;
                    if (nb2 >= 25000) {
                        nb++;
                    }
                    int value = nb * 50000;
                    String cost = window.label("jlbCardBudget").text();
                    Assert.assertEquals(Integer.parseInt(cost), value);
                }
            }

            // Select tab and Choose Goods
            window.tabbedPane("jtpGoods").selectTab(0);

            String array2[] = {"FanFactor", "Assists", "Cheerleaders", "Apothecary", "Reroll"};
            for (String ext : array2) {
                if (window.slider("jsl" + ext).component().isEnabled()) {
                    System.out.println(ext);

                    window.slider("jsl" + ext).slideToMaximum();
                    String cost = window.label("jlbCost" + ext).text();
                    String nb = window.label("jlbNb" + ext).text();
                    String price = window.label("jlbPrice" + ext).text();
                    System.out.println(ext + ": " + nb + " * " + price + " = " + cost);
                    Assert.assertEquals(Integer.parseInt(cost), Integer.parseInt(nb) * Integer.parseInt(price));
                }
            }
            // Add Players
            for (int i = 0; i < 5; i++) {
                window.button("jbtAdd").click();
                Thread.sleep(500);
                DialogFixture window3 = WindowFinder.findDialog(JdgSelectPosition.class).withTimeout(10000).using(robot);
                Thread.sleep(500);
                window3.table("jtPositions").selectRows(0);
                window3.button("ok").click();
                Thread.sleep(200);
                Assert.assertTrue(window.table("jtbPlayers").rowCount() == i + 1);
                window.table("jtbPlayers").selectRows(i);
                Assert.assertTrue(window.button("jbtRemove").component().isEnabled());
                window.button("jbtRemove").click();
                Assert.assertTrue(window.table("jtbPlayers").rowCount() == i);
                window.button("jbtAdd").click();
                Thread.sleep(500);
                DialogFixture window4 = WindowFinder.findDialog(JdgSelectPosition.class).withTimeout(10000).using(robot);
                Thread.sleep(500);
                window4.table("jtPositions").selectRows(0);
                window4.button("ok").click();
            }
            LRB lrb = LRB.getLRB(LRB.E_Version.BB2016);

            // Add Skills to Players
            for (int i = 0; i < 5; i++) {
                Thread.sleep(500);
                for (int j = 0; j < lrb.getSkillTypeCount(); j++) {
                    window.table("jtbPlayers").selectRows(i);
                    window.button("jbtAddSkill").click();
                    Thread.sleep(1000);
                    String skText = lrb.getSkillType(j).getName();
                    System.out.println("SkillType: " + skText);
                    if (window.dialog("JdgSelectSkill").comboBox("jcb" + skText).component().isEnabled()) {
                        window.dialog("JdgSelectSkill").comboBox("jcb" + skText).selectItem(1);
                        Thread.sleep(100);
                        window.dialog("JdgSelectSkill").button("ok").click();
                        Thread.sleep(500);

                    } else {
                        window.dialog("JdgSelectSkill").button("cancel").click();
                    }
                }
            }

            // Import Export
            window.button("jbtImport").click();
            Thread.sleep(500);
            File d = new File("./test");
            File f = new File("necros2.xml");
            window.fileChooser().setCurrentDirectory(d);
            window.fileChooser().selectFile(f);
            window.fileChooser().approve();
            Thread.sleep(500);
            window.button("jbtExport").click();
            File d2 = new File(".");
            File f2 = new File("necros_tmp.xml");
            window.fileChooser().setCurrentDirectory(d2);
            window.fileChooser().selectFile(f2);
            window.fileChooser().approve();

            try {
                Assert.assertTrue(compareTwoFiles("./test/necros2.xml", "necros_tmp.xml"));
            } catch (IOException ex) {
                Logger.getLogger(JdgRosterNGTest.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Files.delete(f2.toPath());
            } catch (IOException ex) {
                Logger.getLogger(JdgRosterNGTest.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Select title and choose roster
            // enable
        } catch (InterruptedException ex) {
            Logger.getLogger(JdgRosterNGTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("exception catched");
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
