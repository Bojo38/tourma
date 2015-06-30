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
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class MjtTeamsNGTest {

    static ArrayList<Team> teams = new ArrayList<>();
    static MjtTeams instance;

    public MjtTeamsNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            teams.add(Tournament.getTournament().getTeam(i));
        }
        //crit=Tournament.getTournament().getParams().getCriteria(0);
        instance = new MjtTeams(teams);
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
     * Test of getColumnCount method, of class MjtTeams.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 2 + Tournament.getTournament().getParams().getTeamMatesNumber();
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class MjtTeams.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int expResult = teams.size();
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtTeams.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        String expResult = "";
        for (int col = 0; col < 9; col++) {
            if (col == 0) {
                expResult = StringConstants.CS_HASH;
            } else {
                if (col == 1) {
                    expResult = Translate.translate(Translate.CS_Name);
                } else {
                    expResult = Translate.translate(Translate.CS_Coach) + (col - 1);
                }
            }
            String result = instance.getColumnName(col);
            assertEquals(result, expResult);
        }

    }

    /**
     * Test of getValueAt method, of class MjtTeams.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
       Object expResult = "";
        for (int i = 0; i < teams.size(); i++) {
            Team c = teams.get(i);
            for (int col = 0; col < 2+Tournament.getTournament().getParams().getTeamMatesNumber(); col++) {

                switch (col) {
                    case 0:
                        expResult = i + 1;
                        break;
                    case 1:
                        expResult = c.getName();
                        break;                    
                    default:
                        expResult= c.getCoach(col-2).getName();
                        break;
                }
                Object result = instance.getValueAt(i, col);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of getColumnClass method, of class MjtTeams.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        Class expResult = null;
        for (int col = 0; col < 9; col++) {
            switch (col) {
                case 0:
                    expResult = Integer.class;
                    break;
                default:
                    expResult = String.class;
                    break;
            }
            Class result = instance.getColumnClass(col);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of isCellEditable method, of class MjtTeams.
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
     * Test of getTableCellRendererComponent method, of class MjtTeams.
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
