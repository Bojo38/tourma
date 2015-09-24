/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.File;
import org.jdom2.Element;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author WFMJ7631
 */
public class SubstituteNGTest {

    public SubstituteNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
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
     * Test of getXMLElement method, of class Substitute.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Tournament.getTournament().loadXML(new File("./test/substitute.xml"));
        Substitute instance = new Substitute();
        Coach s = new Coach("Sub");
        Coach.putCoach("Sub", s);
        instance.setSubstitute(s);

        
        CoachMatch cm=Tournament.getTournament().getRound(0).getCoachMatchs().get(0);
        instance.setTitular((Coach)cm.getCompetitor1());
        
        Element result = instance.getXMLElement();
        
        Substitute s2=new Substitute();
        s2.setXMLElement(result);
        assertEquals(instance, s2);

    }

    /**
     * Test of setXMLElement method, of class Substitute.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
      Tournament.getTournament().loadXML(new File("./test/substitute.xml"));
        Substitute instance = new Substitute();
        Coach s = new Coach("Sub");
        Coach.putCoach("Sub", s);
        instance.setSubstitute(s);

        
        CoachMatch cm=Tournament.getTournament().getRound(0).getCoachMatchs().get(0);
        instance.setTitular((Coach)cm.getCompetitor1());
        
        Element result = instance.getXMLElement();
        
        Substitute s2=new Substitute();
        s2.setXMLElement(result);
        assertEquals(instance, s2);
    }

    /**
     * Test of getMatch method, of class Substitute.
     */
    @Test
    public void testGetMatch() {
        System.out.println("getMatch");
        CoachMatch mMatch = new CoachMatch(new Round());
        Substitute instance = new Substitute();
        instance.setMatch(mMatch);
        assertEquals(mMatch, instance.getMatch());
    }

    /**
     * Test of setMatch method, of class Substitute.
     */
    @Test
    public void testSetMatch() {
        System.out.println("setMatch");
        CoachMatch mMatch = new CoachMatch(new Round());
        Substitute instance = new Substitute();
        instance.setMatch(mMatch);
        assertEquals(mMatch, instance.getMatch());
    }

    /**
     * Test of getSubstitute method, of class Substitute.
     */
    @Test
    public void testGetSubstitute() {
        System.out.println("getSubstitute");
        Substitute instance = new Substitute();
        Coach expResult = new Coach("Sub");
        instance.setSubstitute(expResult);
        Coach result = instance.getSubstitute();
        assertEquals(result, expResult);

    }

    /**
     * Test of setSubstitute method, of class Substitute.
     */
    @Test
    public void testSetSubstitute() {
        System.out.println("setSubstitute");
        Substitute instance = new Substitute();
        Coach expResult = new Coach("Sub");
        instance.setSubstitute(expResult);
        Coach result = instance.getSubstitute();
        assertEquals(result, expResult);
    }

    /**
     * Test of getTitular method, of class Substitute.
     */
    @Test
    public void testGetTitular() {
        System.out.println("getTitular");
        Substitute instance = new Substitute();
        Coach expResult = new Coach("Tit");
        instance.setTitular(expResult);
        Coach result = instance.getTitular();
        assertEquals(result, expResult);
    }

    /**
     * Test of setTitular method, of class Substitute.
     */
    @Test
    public void testSetTitular() {
        System.out.println("setTitular");
        Substitute instance = new Substitute();
        Coach expResult = new Coach("Tit");
        instance.setTitular(expResult);
        Coach result = instance.getTitular();
        assertEquals(result, expResult);
    }

}
