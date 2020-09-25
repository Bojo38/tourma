/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.tableModel;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teamma.data.LRB;
import teamma.data.Roster;
import teamma.data.Skill;
import teamma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public class MjtTeamStarsNGTest {

    private static LRB lrb;

    public MjtTeamStarsNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        lrb = LRB.getLRB(LRB.E_Version.BB2016);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    private Roster roster = null;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        final SAXBuilder sxb = new SAXBuilder();
        final org.jdom.Document document = sxb.build(new File("test/necros.xml"));
        final Element racine = document.getRootElement();
        roster = new Roster();
        roster.setXMLElement(racine);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getColumnCount method, of class MjtTeamStars.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        MjtTeamStars instance = new MjtTeamStars(roster);
        Assert.assertNotNull(instance);
        int result = instance.getColumnCount();
        assertEquals(result, 8);
    }

    /**
     * Test of getRowCount method, of class MjtTeamStars.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        MjtTeamStars instance = new MjtTeamStars(roster);
        Assert.assertNotNull(instance);
        int result = instance.getRowCount();
        assertEquals(result, 2);
    }

    /**
     * Test of getColumnName method, of class MjtTeamStars.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        MjtTeamStars instance = new MjtTeamStars(roster);
        Assert.assertNotNull(instance);
        int result = instance.getColumnCount();
        for (int i = 0; i < result; i++) {
            String txt = instance.getColumnName(i);
            System.out.println("Column " + i + " found: " + txt);
            switch (i) {
                case 0:
                    assertEquals(txt, Translate.translate(CS_Name));
                    break;
                case 1:
                    assertEquals(txt, Translate.translate(CS_Position));
                    break;
                case 2:
                    assertEquals(txt, Translate.translate(CS_M));
                    break;
                case 3:
                    assertEquals(txt, Translate.translate(CS_S));
                    break;
                case 4:
                    assertEquals(txt, Translate.translate(CS_Ag));
                    break;
                case 5:
                    assertEquals(txt, Translate.translate(CS_Ar));
                    break;
                case 6:
                    assertEquals(txt, Translate.translate(CS_Skills));
                    break;
                case 7:
                    assertEquals(txt, Translate.translate(CS_Cost));
                    break;
            }
        }
    }

    private final static String CS_Name = "Name";
    private final static String CS_Position = "Position";
    private final static String CS_M = "M";
    private final static String CS_S = "S";
    private final static String CS_Ag = "Ag";
    private final static String CS_Ar = "Ar";
    private final static String CS_Skills = "Skills";
    private final static String CS_Cost = "Cost";

    /**
     * Test of getValueAt method, of class MjtTeamStars.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        MjtTeamStars instance = new MjtTeamStars(roster);
        Assert.assertNotNull(instance);

        for (int i = 0; i < roster.getChampionCount(); i++) {
            Object o0 = instance.getValueAt(i, 0);
            Assert.assertTrue(o0 instanceof String);
            Assert.assertEquals((String) o0, Translate.translate(roster.getChampion(i).getName()));

            Object o1 = instance.getValueAt(i, 1);
            Assert.assertTrue(o1 instanceof String);
            Assert.assertEquals((String) o1, Translate.translate(roster.getChampion(i).getPosition()));

            Object o2 = instance.getValueAt(i, 2);
            Assert.assertTrue(o2 instanceof Integer);
            Assert.assertEquals(((Integer) o2).intValue(), roster.getChampion(i).getMovement());

            Object o3 = instance.getValueAt(i, 3);
            Assert.assertTrue(o3 instanceof Integer);
            Assert.assertEquals(((Integer) o3).intValue(), roster.getChampion(i).getStrength());

            Object o4 = instance.getValueAt(i, 4);
            Assert.assertTrue(o4 instanceof Integer);
            Assert.assertEquals(((Integer) o4).intValue(), roster.getChampion(i).getAgility());

            Object o5 = instance.getValueAt(i, 5);
            Assert.assertTrue(o5 instanceof Integer);
            Assert.assertEquals(((Integer) o5).intValue(), roster.getChampion(i).getArmor());

            Object o6 = instance.getValueAt(i, 6);
            Assert.assertTrue(o6 instanceof String);
            StringBuilder txt = new StringBuilder(32);
            ArrayList<String> skills = new ArrayList<>();
            for (int j = 0; j < roster.getChampion(i).getSkillCount(); j++) {
                Skill s = roster.getChampion(i).getSkill(j);

                skills.add("<FONT color=\"000000\">" + Translate.translate(s.getmName()) + "</FONT>");
            }

            for (int j = 0; j < skills.size(); j++) {
                txt.append(skills.get(j));
                if (j != skills.size() - 1) {
                    txt.append(", ");
                }
            }
            System.out.println("Found: "+((String)o6));
            System.out.println("Expected: "+txt.toString());
            Assert.assertEquals((String) o6, txt.toString());

            Object o7 = instance.getValueAt(i, 7);
            Assert.assertTrue(o7 instanceof Integer);
            Assert.assertEquals(((Integer) o7).intValue(), roster.getChampion(i).getCost());
        }

    }

    /**
     * Test of getColumnClass method, of class MjtTeamStars.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        MjtTeamStars instance = new MjtTeamStars(roster);
        Assert.assertNotNull(instance);
        int result = instance.getColumnCount();
        for (int i = 0; i < result; i++) {
            String txt = instance.getColumnClass(i).getName();
            System.out.println("Column " + i + ": " + txt);
            switch (i) {
                case 0:
                    assertEquals(txt, "java.lang.String");
                    break;
                case 1:
                    assertEquals(txt, "java.lang.String");
                    break;
                case 2:
                    assertEquals(txt, "java.lang.Integer");
                    break;
                case 3:
                    assertEquals(txt, "java.lang.Integer");
                    break;
                case 4:
                    assertEquals(txt, "java.lang.Integer");
                    break;
                case 5:
                    assertEquals(txt, "java.lang.Integer");
                    break;
                case 6:
                    assertEquals(txt, "java.lang.String");
                    break;
                case 7:
                    assertEquals(txt, "java.lang.Integer");
                    break;
            }
        }
    }

    /**
     * Test of isCellEditable method, of class MjtTeamStars.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        MjtTeamStars instance = new MjtTeamStars(roster);
        Assert.assertNotNull(instance);
        for (int i = 0; i < instance.getColumnCount(); i++) {
            for (int j = 0; j < instance.getRowCount(); j++) {
                boolean b = instance.isCellEditable(j, i);
                Assert.assertFalse(b);
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtTeamStars.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
        MjtTeamStars instance = new MjtTeamStars(roster);
        JTable jtb = new JTable(instance);
        jtb.setDefaultRenderer(Integer.class, instance);
        jtb.setDefaultRenderer(String.class, instance);
        Assert.assertNotNull(instance);
        for (int i = 0; i < instance.getColumnCount(); i++) {
            for (int j = 0; j < instance.getRowCount(); j++) {
                Component comp = instance.getTableCellRendererComponent(jtb,
                        instance.getValueAt(j, i), true, true, j, i);

                Assert.assertTrue(comp instanceof JEditorPane);
            }
        }
    }

}
