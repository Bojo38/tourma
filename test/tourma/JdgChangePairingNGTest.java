/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.JOptionPaneFixture;
import org.fest.swing.fixture.JPanelFixture;
import org.testng.Assert;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Competitor;
import tourma.data.Round;
import tourma.data.Tournament;
/**
 *
 * @author WFMJ7631
 */
public class JdgChangePairingNGTest {

    private DialogFixture window;
    JdgChangePairing jdg;
    private static Robot robot;
    Round  round;
    public JdgChangePairingNGTest() {
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
        Tournament.getTournament().loadXML(new File("./test/matchs.xml"));
        round=Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount()-1);
        jdg = new JdgChangePairing(null,false,round);
        window = new DialogFixture(robot,jdg);
        window.show();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of actionPerformed method, of class JdgChangePairing.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(JdgChangePairingNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Competitor c1= round.getMatch(0).getCompetitor1();
        JPanelFixture jpnf=window.panel("jpnMatchs");
        JComboBox jcx=jpnf.comboBox("jcb0").component();
        jcx.setSelectedIndex(jcx.getItemCount()-1);
        ActionEvent e = new ActionEvent(jcx,0, "Action");
        jdg.actionPerformed(e);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(JdgChangePairingNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        window.button("ok").click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(JdgChangePairingNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPaneFixture opf=window.optionPane();
        opf.pressKey(KeyEvent.VK_ENTER);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(JdgChangePairingNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Competitor c2= round.getMatch(round.getMatchsCount()-1).getCompetitor2();
        Assert.assertEquals(c2,c1);
    }

    /**
     * Test of update method, of class JdgChangePairing.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        JdgChangePairing instance = null;
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    
}
