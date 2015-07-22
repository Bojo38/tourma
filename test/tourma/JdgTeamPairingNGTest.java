/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.io.File;
import java.util.ArrayList;
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
import tourma.data.Team;
import tourma.data.Tournament;
/**
 *
 * @author WFMJ7631
 */
public class JdgTeamPairingNGTest {
    
    public JdgTeamPairingNGTest() {
    }

private DialogFixture window;
    JdgTeamPairing jdg;
    private static Robot robot;
    Round  round;
    ArrayList<Team> teams1;
    ArrayList<Team> teams2;

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
        Tournament.getTournament().loadXML(new File("./test/team.xml"));
        round=Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount()-1);
        teams1=new ArrayList<>();
        teams2=new ArrayList<>();
        for (int i=0; i<Tournament.getTournament().getTeamsCount()/2; i++)
        {
            teams1.add(Tournament.getTournament().getTeam(i));
            teams2.add(Tournament.getTournament().getTeam(Tournament.getTournament().getTeamsCount()/2+i));
        }
        jdg = new JdgTeamPairing(null, false,teams1,teams2,round);
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
