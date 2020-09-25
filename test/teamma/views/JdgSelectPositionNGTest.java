/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.views;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teamma.data.LRB;
import teamma.data.Player;
import teamma.data.PlayerType;
import teamma.data.Roster;
import teamma.data.StarPlayer;

/**
 *
 * @author WFMJ7631
 */
public class JdgSelectPositionNGTest {

    private DialogFixture window;
    private Roster roster;
    JdgSelectPosition jdg1;
    JdgSelectPosition jdg2;
    Player player;

    private static Robot robot;

    public JdgSelectPositionNGTest() {
    }

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

        // Remove local apothecary, because the team is an unded team
        player = roster.getPlayer(0);

        ArrayList<PlayerType> ar = new ArrayList<>();
        for (int i = 0; i < roster.getRoster().getPlayerTypeCount(); i++) {
            PlayerType pt = roster.getRoster().getPlayerType(i);
            ar.add(pt);
        }

        jdg1 = new JdgSelectPosition(null, true, ar, ar.get(0));

        ArrayList<StarPlayer> ar2 = new ArrayList<>();
        for (int i = 0; i < roster.getRoster().getAvailableStarplayerCount(); i++) {
            StarPlayer pt = roster.getRoster().getAvailableStarplayer(i);
            ar2.add(pt);
        }

        jdg2 = new JdgSelectPosition(null, true, ar2, ar2.get(0));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        jdg1.setVisible(false);
        jdg2.setVisible(false);
    }

    /**
     * Test of getPosition method, of class JdgSelectPosition.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");

        try {
            window = new DialogFixture(robot, jdg1);
            window.show();
            Thread.sleep(500);
            window.table("jtPositions").selectRows(1);
            Assert.assertEquals(window.table("jtPositions").component().getRowCount(), roster.getRoster().getPlayerTypeCount());
            Thread.sleep(500);
            window.button("ok").click();

            window.cleanUp();
            window = null;
        } catch (InterruptedException ex) {
            fail("Exception catched");
            Logger.getLogger(JdgSelectSkillNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of getStarPlayer method, of class JdgSelectPosition.
     */
    @Test
    public void testGetStarPlayer() {
        System.out.println("getStarPlayer");
        try {
            window = new DialogFixture(robot, jdg2);
            window.show();
            Thread.sleep(500);
            window.table("jtPositions").selectRows(1);
            Assert.assertEquals(window.table("jtPositions").component().getRowCount(), roster.getRoster().getAvailableStarplayerCount());
            Thread.sleep(500);
            window.button("ok").click();

            window.cleanUp();
            window = null;
        } catch (InterruptedException ex) {
            fail("Exception catched");
            Logger.getLogger(JdgSelectSkillNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
