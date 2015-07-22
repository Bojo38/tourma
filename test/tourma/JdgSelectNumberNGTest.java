/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.io.File;
import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Round;
import tourma.data.Tournament;
/**
 *
 * @author WFMJ7631
 */
public class JdgSelectNumberNGTest {
    
    public JdgSelectNumberNGTest() {
    }

      private DialogFixture window;
    JdgSelectNumber jdg;
    private static Robot robot;
    Round  round;
    

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
        jdg = new JdgSelectNumber(null, false, Tournament.getTournament());
        window = new DialogFixture(robot,jdg);
        window.show();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(enabled=false)
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }    
}
