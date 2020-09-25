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
import teamma.data.Player;
import teamma.data.Roster;
import teamma.data.Skill;
import teamma.data.SkillType;
import teamma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public class MjtTeamPlayersNGTest {
    
    private static LRB lrb;
    
    public MjtTeamPlayersNGTest() {
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
     * Test of getColumnCount method, of class MjtTeamPlayers.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        MjtTeamPlayers instance = new MjtTeamPlayers(roster);
        Assert.assertNotNull(instance);
        int result = instance.getColumnCount();
        assertEquals(result, 12);
    }

    /**
     * Test of getRowCount method, of class MjtTeamPlayers.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        MjtTeamPlayers instance = new MjtTeamPlayers(roster);
        Assert.assertNotNull(instance);
        int result = instance.getRowCount();
        assertEquals(result, roster.getPlayerCount());
    }
    
    private final static String CS_Name = "Name";
    private final static String CS_Position = "Position";
    private final static String CS_M = "M";
    private final static String CS_S = "S";
    private final static String CS_Ag = "Ag";
    private final static String CS_Ar = "Ar";
    private final static String CS_Skills = "Skills";
    private final static String CS_SR = "SR";
    private final static String CS_DR = "DR";
    private final static String CS_BaseCost = "Base Cost";
    private final static String CS_Cost = "Cost";

    /**
     * Test of getColumnName method, of class MjtTeamPlayers.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        MjtTeamPlayers instance = new MjtTeamPlayers(roster);
        Assert.assertNotNull(instance);
        int result = instance.getColumnCount();
        for (int i = 0; i < result; i++) {
            String txt = instance.getColumnName(i);
            switch (i) {
                case 0:
                    assertEquals(txt, "#");
                    break;
                case 1:
                    assertEquals(txt, Translate.translate(CS_Name));
                    break;
                case 2:
                    assertEquals(txt, Translate.translate(CS_Position));
                    break;
                case 3:
                    assertEquals(txt, Translate.translate(CS_M));
                    break;
                case 4:
                    assertEquals(txt, Translate.translate(CS_S));
                    break;
                case 5:
                    assertEquals(txt, Translate.translate(CS_Ag));
                    break;
                case 6:
                    assertEquals(txt, Translate.translate(CS_Ar));
                    break;
                case 7:
                    assertEquals(txt, Translate.translate(CS_Skills));
                    break;
                case 8:
                    assertEquals(txt, Translate.translate(CS_SR));
                    break;
                case 9:
                    assertEquals(txt, Translate.translate(CS_DR));
                    break;
                case 10:
                    assertEquals(txt, Translate.translate(CS_BaseCost));
                    break;
                case 11:
                    assertEquals(txt, Translate.translate(CS_Cost));
                    break;
            }
        }
    }

    /**
     * Test of getValueAt method, of class MjtTeamPlayers.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        MjtTeamPlayers instance = new MjtTeamPlayers(roster);
        Assert.assertNotNull(instance);
        
        for (int i = 0; i < roster.getPlayerCount(); i++) {
            Player player = roster.getPlayer(i);
            
            Object o0 = instance.getValueAt(i, 0);
            Assert.assertTrue(o0 instanceof Integer);
            Assert.assertEquals(((Integer) o0).intValue(), i + 1);
            
            Object o1 = instance.getValueAt(i, 1);
            Assert.assertTrue(o1 instanceof String);
            Assert.assertEquals((String) o1, player.getName());
            
            Object o2 = instance.getValueAt(i, 2);
            Assert.assertTrue(o2 instanceof String);
            Assert.assertEquals((String) o2, Translate.translate(player.getPlayertype().getPosition()));
            
            Object o3 = instance.getValueAt(i, 3);
            Assert.assertTrue(o3 instanceof Integer);
            Assert.assertEquals(((Integer) o3).intValue(), player.getMovement());
            
            Object o4 = instance.getValueAt(i, 4);
            Assert.assertTrue(o4 instanceof Integer);
            Assert.assertEquals(((Integer) o4).intValue(), player.getStrength());
            
            Object o5 = instance.getValueAt(i, 5);
            Assert.assertTrue(o5 instanceof Integer);
            Assert.assertEquals(((Integer) o5).intValue(), player.getAgility());
            
            Object o6 = instance.getValueAt(i, 6);
            Assert.assertTrue(o6 instanceof Integer);
            Assert.assertEquals(((Integer) o6).intValue(), player.getArmor());
            
            Object o7 = instance.getValueAt(i, 7);
            Assert.assertTrue(o7 instanceof String);
            StringBuilder txt = new StringBuilder(32);
            ArrayList<String> skills = new ArrayList<>();
            for (int j = 0; j < player.getPlayertype().getSkillCount(); j++) {
                Skill s = player.getPlayertype().getSkill(j);
                
                skills.add("<FONT color=\"000000\">" + Translate.translate(s.getmName()) + "</FONT>");
            }
            for (int j = 0; j < player.getSkillCount(); j++) {
                Skill s = player.getSkill(j);
                int rgb = s.getmColor().getRed() * 65536 + s.getmColor().getGreen() * 256 + s.getmColor().getBlue();
                skills.add("<FONT color=\"" + Integer.toHexString(rgb) + "\"><I>" + Translate.translate(s.getmName()) + "</I></FONT>");
            }
            
            for (int j = 0; j < skills.size(); j++) {
                txt.append(skills.get(j));
                if (j != skills.size() - 1) {
                    txt.append(", ");
                }
            }
            
            Assert.assertEquals((String) o7, txt.toString());
            
            Object o8 = instance.getValueAt(i, 8);
            Assert.assertTrue(o8 instanceof String);
            StringBuilder sr = new StringBuilder(32);
            for (int j = 0; j < player.getPlayertype().getSingleCount(); j++) {
                SkillType st = player.getPlayertype().getSingle(j);
                sr.append(Translate.translate(st.getAccronym()));
            }
            Assert.assertEquals((String) o8, sr.toString());
            
            Object o9 = instance.getValueAt(i, 9);
            Assert.assertTrue(o9 instanceof String);
            StringBuilder dr = new StringBuilder(32);
            for (int j = 0; j < player.getPlayertype().getDoubleCount(); j++) {
                SkillType st = player.getPlayertype().getDouble(j);
                dr.append(Translate.translate(st.getAccronym()));
            }
            Assert.assertEquals((String) o9, dr.toString());
                       
            Object o10 = instance.getValueAt(i, 10);
            Assert.assertTrue(o10 instanceof Integer);
            Assert.assertEquals(((Integer) o10).intValue(), player.getValue(false));
            
            Object o11 = instance.getValueAt(i, 11);
            Assert.assertTrue(o11 instanceof Integer);
            Assert.assertEquals(((Integer) o11).intValue(), player.getValue(true));
        }
        
    }

    /**
     * Test of getColumnClass method, of class MjtTeamPlayers.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        int c = 0;
       MjtTeamPlayers instance = new MjtTeamPlayers(roster);
        Assert.assertNotNull(instance);
        int result = instance.getColumnCount();
        for (int i = 0; i < result; i++) {
            String txt = instance.getColumnClass(i).getName();
            switch (i) {
                case 0:
                    assertEquals(txt, "java.lang.Integer");
                    break;
                case 1:
                    assertEquals(txt, "java.lang.String");
                    break;
                case 2:
                    assertEquals(txt, "java.lang.String");
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
                   assertEquals(txt, "java.lang.Integer");
                    break;
                case 7:
                   assertEquals(txt, "java.lang.String");
                    break;
                case 8:
                   assertEquals(txt, "java.lang.String");
                    break;
                case 9:
                   assertEquals(txt, "java.lang.String");
                    break;
                case 10:
                    assertEquals(txt, "java.lang.Integer");
                    break;
                case 11:
                    assertEquals(txt, "java.lang.Integer");
                    break;
            }
        }
    }

    /**
     * Test of isCellEditable method, of class MjtTeamPlayers.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        MjtTeamPlayers instance = new MjtTeamPlayers(roster);
        Assert.assertNotNull(instance);
        for (int i = 0; i < instance.getColumnCount(); i++) {
            for (int j = 0; j < instance.getRowCount(); j++) {
                boolean b = instance.isCellEditable(j, i);
                Assert.assertFalse(b);
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtTeamPlayers.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
        MjtTeamPlayers instance = new MjtTeamPlayers(roster);
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
