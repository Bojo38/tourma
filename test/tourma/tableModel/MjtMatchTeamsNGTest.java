/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class MjtMatchTeamsNGTest {

    static ArrayList<TeamMatch> matchs = new ArrayList<>();
    static MjtMatchTeams instance;
    static ArrayList<Team> teams = new ArrayList<>();

    public MjtMatchTeamsNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        Round r = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
        for (int i = 0; i < r.getMatchsCount(); i++) {
            Match m = r.getMatch(i);
            if (m instanceof TeamMatch) {
                matchs.add((TeamMatch) m);
            }
        }

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            teams.add(Tournament.getTournament().getTeam(i));
        }
        instance = new MjtMatchTeams(teams, r);

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
     * Test of getColumnCount method, of class MjtMatchTeams.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 6;
        int result = instance.getColumnCount();
    }

    /**
     * Test of getRowCount method, of class MjtMatchTeams.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int expResult = matchs.size();
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtMatchTeams.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        for (int col = 0; col < instance.getColumnCount(); col++) {
            String expResult = "";
            switch (col) {
                case 0:
                    expResult = StringConstants.CS_HASH;
                    break;
                case 1:
                    expResult = Translate.translate(Translate.CS_Team) + " 1";
                    break;
                case 2:
                    expResult = Translate.translate(Translate.CS_ACCR_Victory1);
                    break;
                case 3:
                    expResult = Translate.translate(Translate.CS_ACCR_Drawn);
                    break;
                case 4:
                    expResult = Translate.translate(Translate.CS_ACCR_Victory2);
                    break;
                case 5:
                    expResult = Translate.translate(Translate.CS_Team) + " 1";
                    break;
                default:
            }
            String result = instance.getColumnName(col);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getValueAt method, of class MjtMatchTeams.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        for (int row = 0; row < instance.getRowCount(); row++) {
            TeamMatch cm = matchs.get(row);
            for (int col = 0; col < instance.getColumnCount(); col++) {
                Object expResult = null;
                switch (col) {
                    case 0:
                        expResult = row + 1;
                        break;
                    case 1:
                        expResult = cm.getCompetitor1().getName();
                        break;
                    case 2:
                        expResult = cm.getVictories((Team) cm.getCompetitor1());
                        break;
                    case 3:
                        expResult = cm.getDraw((Team) cm.getCompetitor1());
                        break;
                    case 4:
                        expResult = cm.getLoss((Team) cm.getCompetitor1());
                        break;
                    case 5:
                        expResult = cm.getCompetitor2().getName();
                        break;

                }
                Object result = instance.getValueAt(row, col);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of getColumnClass method, of class MjtMatchTeams.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        for (int col = 0; col < instance.getColumnCount(); col++) {
            Class expResult = null;
            switch (col) {
                case 1:
                case 5:
                    expResult = String.class;
                    break;
                case 0:
                case 2:
                case 3:
                case 4:
                    expResult = Integer.class;
                    break;
                default:
            }
            Class result = instance.getColumnClass(col);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of isCellEditable method, of class MjtMatchTeams.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        for (int row = 0; row < instance.getRowCount(); row++) {
            for (int col = 0; col < instance.getColumnCount(); col++) {
                boolean expResult = false;
                boolean result = instance.isCellEditable(row, col);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtMatchTeams.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
        JTable table = new JTable();
        table.setModel(instance);
        for (int i = 0; i < instance.getColumnCount(); i++) {
            for (int j = 0; j < instance.getRowCount(); j++) {
                Component result = instance.getTableCellRendererComponent(table, instance.getValueAt(j, i), false, false, j, i);
                Assert.assertTrue(result instanceof JTextField);
            }
        }
    }

}
