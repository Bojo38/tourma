/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.io.File;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static teamma.data.Player.CS_Plus1Strength;

/**
 *
 * @author WFMJ7631
 */
public class PlayerNGTest {

    private static LRB lrb;

    public PlayerNGTest() {
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
     * Test of getPlayertype method, of class Player.
     */
    @Test
    public void testGetPlayertype() {
        System.out.println("getPlayertype");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            
            Assert.assertNotNull(p.getPlayertype());
        }
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
       if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            
            Assert.assertNotNull(p.getName());
        }
    }

    /**
     * Test of getSkill method, of class Player.
     */
    @Test
    public void testGetSkill() {
        System.out.println("getSkill");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            int nb=p.getSkillCount();
            for (int j=0; j<nb; j++)
            {
                Assert.assertNotNull(p.getSkill(j));
            }
        }
    }

    /**
     * Test of getSkillCount method, of class Player.
     */
    @Test
    public void testGetSkillCount() {
        System.out.println("getSkillCount");
       if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            int nb=p.getSkillCount();
        }
    }

    /**
     * Test of removeSkill method, of class Player.
     */
    @Test
    public void testRemoveSkill() {
        System.out.println("removeSkill");
       if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            int nb=p.getSkillCount();
            
            Skill s=new Skill("Skill", new SkillType("Test", "T"));
            p.addSkill(s);
            assertEquals(nb+1, p.getSkillCount());
            p.removeSkill(nb);
            assertEquals(nb, p.getSkillCount());
            
            
        }
    }

    /**
     * Test of addSkill method, of class Player.
     */
    @Test
    public void testAddSkill() {
        System.out.println("addSkill");
       if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            int nb=p.getSkillCount();
            
            Skill s=new Skill("Skill", new SkillType("Test", "T"));
            p.addSkill(s);
            assertEquals(nb+1, p.getSkillCount());
            p.removeSkill(nb);
            assertEquals(nb, p.getSkillCount());
            
            
        }
    }

    /**
     * Test of getMovement method, of class Player.
     */
    @Test
    public void testGetMovement() {
        System.out.println("getMovement");
        System.out.println("getStrength");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            int value=p.getMovement();
            Assert.assertTrue(value>0);
            
            Skill s=new Skill(Player.CS_Plus1Movement,new SkillType("Test","T"));
            p.addSkill(s);
            Assert.assertTrue(value+1==p.getMovement());
            p.removeSkill(p.getSkillCount()-1);            
            
            Skill s2=new Skill(Player.CS_Minus1Movement,new SkillType("Test","T"));
            p.addSkill(s2);
            Assert.assertTrue(Math.max(1, value-1)==p.getMovement());
            p.removeSkill(p.getSkillCount()-1);   
        }
    }

    /**
     * Test of getStrength method, of class Player.
     */
    @Test
    public void testGetStrength() {
        System.out.println("getStrength");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            int value=p.getStrength();
            Assert.assertTrue(value>0);
            
            Skill s=new Skill(CS_Plus1Strength,new SkillType("Test","T"));
            p.addSkill(s);
            Assert.assertTrue(value+1==p.getStrength());
            p.removeSkill(p.getSkillCount()-1);    
            
            Skill s2=new Skill(Player.CS_Minus1Strength,new SkillType("Test","T"));
            p.addSkill(s2);
            Assert.assertTrue(Math.max(1, value-1)==p.getStrength());
            p.removeSkill(p.getSkillCount()-1);   
        }
    }

    /**
     * Test of getAgility method, of class Player.
     */
    @Test
    public void testGetAgility() {
        System.out.println("getAgility");
       if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            int value=p.getAgility();
            Assert.assertTrue(value>0);
            
            Skill s=new Skill(Player.CS_Plus1Agility,new SkillType("Test","T"));
            p.addSkill(s);
            Assert.assertTrue(value+1==p.getAgility());
            p.removeSkill(p.getSkillCount()-1);    
            
            Skill s2=new Skill(Player.CS_Minus1Agility,new SkillType("Test","T"));
            p.addSkill(s2);
            Assert.assertTrue(Math.max(1, value-1)==p.getAgility());
            p.removeSkill(p.getSkillCount()-1);   
        }
    }

    /**
     * Test of getArmor method, of class Player.
     */
    @Test
    public void testGetArmor() {
        System.out.println("getArmor");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            int value=p.getArmor();
            Assert.assertTrue(value>0);
            
            Skill s=new Skill(Player.CS_Plus1Armor,new SkillType("Test","T"));
            p.addSkill(s);
            Assert.assertTrue(value+1==p.getArmor());
            p.removeSkill(p.getSkillCount()-1);    
            
            Skill s2=new Skill(Player.CS_Minus1Armor,new SkillType("Test","T"));
            p.addSkill(s2);
            Assert.assertTrue(Math.max(1, value-1)==p.getArmor());
            p.removeSkill(p.getSkillCount()-1);   
        }
    }

    /**
     * Test of getValue method, of class Player.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
         System.out.println("getName");
       if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            int value=p.getValue(true);
            int value2=p.getValue(false);
            Assert.assertTrue(value2>0);
            Assert.assertTrue(value2<=value);
        }
    }

    /**
     * Test of setPlayertype method, of class Player.
     */
    @Test
    public void testSetPlayertype() {
        System.out.println("setPlayertype");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            
            PlayerType save=p.getPlayertype();
            PlayerType pt=new PlayerType("Test");
            p.setPlayertype(pt);
            assertEquals(p.getPlayertype(), pt);
            p.setPlayertype(save);
        }
    }

    /**
     * Test of setName method, of class Player.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            
            String save=p.getName();
            p.setName("Test");
            assertEquals(p.getName(), "Test");
            p.setName(save);
        }
    }

    /**
     * Test of pull method, of class Player.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        System.out.println("setName");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getPlayerCount() == 0) {
            fail("No player loaded");
        }
        
        for (int i=0; i<roster.getPlayerCount(); i++)
        {
            Player p=roster.getPlayer(i);
            Assert.assertNotNull(p);
            
            String save=p.getName();
            p.setName("Test");
            assertEquals(p.getName(), "Test");
            
            p.pull(p, LRB.E_Version.BB2016);
        }
        
        
  
    }

}
