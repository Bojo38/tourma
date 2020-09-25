/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.views;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.JComboBoxFixture;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.testng.Assert;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teamma.data.LRB;
import teamma.data.Player;
import teamma.data.Roster;

/**
 *
 * @author WFMJ7631
 */
public class JdgSelectSkillNGTest {

    
    private DialogFixture window;
    private Roster roster;
    JdgSelectSkill jdg;
    Player player;

    public JdgSelectSkillNGTest() {
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
        final SAXBuilder sxb = new SAXBuilder();
        final org.jdom.Document document = sxb.build(new File("test/necros.xml"));
        final Element racine = document.getRootElement();
        roster = new Roster();
        roster.setXMLElement(racine);

        // Remove local apothecary, because the team is an unded team

        player = roster.getPlayer(0);
        jdg = new JdgSelectSkill(null, true, player,LRB.getLRB(LRB.E_Version.BB2016));
        window = new DialogFixture(robot, jdg);
        window.show();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        jdg.setVisible(false);
        //window.close();
        window.cleanUp();
        window = null;
    }

    /**
     * Test of getPlayer method, of class JdgSelectSkill.
     */
    @Test
    public void hmiGeneralTest() {
        System.out.println("hmiGeneralTest");
        try {
            LRB lrb = LRB.getLRB(LRB.E_Version.BB2016);
            
            JComboBoxFixture jcbf=window.comboBox("jcbGeneral");
            jcbf.component().setEnabled(true);
            window.comboBox("jcbGeneral").selectItem(1);
            Thread.sleep(500);
            int nb = player.getSkillCount();
            Assert.assertEquals(nb, player.getSkillCount());
            window.button("cancel").click();
            window.show();
            window.comboBox("jcbGeneral").selectItem(1);
            Thread.sleep(200);
            window.button("ok").click();
            Assert.assertEquals(nb + 1, player.getSkillCount());
            Assert.assertEquals(lrb.getSkillType("General").getSkill(0).getmName(), player.getSkill(nb).getmName());
            player.removeSkill(nb);
            Assert.assertEquals(nb, player.getSkillCount());
        } catch (InterruptedException ex) {
            fail("Exception catched");
            Logger.getLogger(JdgSelectSkillNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
