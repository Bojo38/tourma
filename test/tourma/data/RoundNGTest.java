/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.jdom2.Element;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public class RoundNGTest {

    public RoundNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/matchs.xml"));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of toString method, of class Round.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        String expResult = Translate.translate(Translate.CS_Round_) + "1";
        String result = instance.toString();
        assertEquals(result, expResult);
    }

    /**
     * Test of getMatch method, of class Round.
     */
    @Test
    public void testGetMatch() {
        System.out.println("getMatch");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        for (int i = 0; i < instance.getMatchsCount(); i++) {
            Assert.assertNotNull(instance.getMatch(i));
        }
    }

    /**
     * Test of getMatchsCount method, of class Round.
     */
    @Test
    public void testGetMatchsCount() {
        System.out.println("getMatchsCount");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        for (int i = 0; i < instance.getMatchsCount(); i++) {
            Assert.assertNotNull(instance.getMatch(i));
        }
    }

    /**
     * Test of addMatch method, of class Round.
     */
    @Test
    public void testAddMatch() {
        System.out.println("addMatch");
        Round instance = new Round();
        Match m = new CoachMatch(instance);
        int nb = instance.getMatchsCount();
        instance.addMatch(m);
        Assert.assertEquals(nb + 1, instance.getMatchsCount());
    }

    /**
     * Test of shuffleMatchs method, of class Round.
     */
    @Test
    public void testShuffleMatchs() {
        System.out.println("shuffleMatchs");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        int nb = instance.getMatchsCount();
        for (int i = 0; i < instance.getMatchsCount(); i++) {
            Assert.assertNotNull(instance.getMatch(i));
        }
        instance.shuffleMatchs();
        assertEquals(nb, instance.getMatchsCount());
        for (int i = 0; i < instance.getMatchsCount(); i++) {
            Assert.assertNotNull(instance.getMatch(i));
        }
    }

    /**
     * Test of containsMatch method, of class Round.
     */
    @Test
    public void testContainsMatch() {
        System.out.println("containsMatch");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        int nb = instance.getMatchsCount();
        for (int i = 0; i < instance.getMatchsCount(); i++) {
            Assert.assertNotNull(instance.getMatch(i));
        }
        instance.shuffleMatchs();
        assertEquals(nb, instance.getMatchsCount());
        for (int i = 0; i < instance.getMatchsCount(); i++) {
            Assert.assertTrue(instance.containsMatch(instance.getMatch(i)));
        }

        CoachMatch cm = new CoachMatch(instance);
        Assert.assertFalse(instance.containsMatch(cm));
    }

    /**
     * Test of clearMatchs method, of class Round.
     */
    @Test
    public void testClearMatchs() {
        System.out.println("clearMatchs");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        instance.clearMatchs();
        int nb = instance.getMatchsCount();
        Assert.assertEquals(nb, 0);
    }

    /**
     * Test of getCoachMatchs method, of class Round.
     */
    @Test
    public void testGetCoachMatchs() {
        System.out.println("getCoachMatchs");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        ArrayList result = instance.getCoachMatchs();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), instance.getMatchsCount());
    }

    /**
     * Test of setHour method, of class Round.
     */
    @Test
    public void testSetHour() {
        System.out.println("setHour");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        Date h = instance.getHour();
        String d = "01/02/2003 04:05:06";
        instance.setHour(d);
        SimpleDateFormat format = new SimpleDateFormat(Translate.translate("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        assertEquals(d, format.format(instance.getHour()));
    }

    /**
     * Test of setCurrentHour method, of class Round.
     */
    @Test
    public void testSetCurrentHour() {
        System.out.println("setCurrentHour");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        instance.setCurrentHour();
        Assert.assertNotNull(instance.getHour());
    }

    /**
     * Test of getHour method, of class Round.
     */
    @Test
    public void testGetHour() {
        System.out.println("getHour");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        Date h = instance.getHour();
        String d = "01/02/2003 04:05:06";
        instance.setHour(d);
        SimpleDateFormat format = new SimpleDateFormat(Translate.translate("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        assertEquals(d, format.format(instance.getHour()));
    }

    /**
     * Test of getXMLElement method, of class Round.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
         if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);        
        Element result = instance.getXMLElement();
        
        Round instance2=new Round();
        instance2.setXMLElement(result);
        assertEquals(instance, instance2);
    }

    /**
     * Test of setXMLElement method, of class Round.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);        
        Element result = instance.getXMLElement();
        
        Round instance2=new Round();
        instance2.setXMLElement(result);
        assertEquals(instance, instance2);
    }

    /**
     * Test of isCup method, of class Round.
     */
    @Test
    public void testIsCup() {
        System.out.println("isCup");
       if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        boolean expResult = false;
        instance.setCup(expResult);
        boolean result = instance.isCup();
        assertEquals(result, expResult);
    }

    /**
     * Test of setCup method, of class Round.
     */
    @Test
    public void testSetCup() {
        System.out.println("setCup");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        boolean expResult = false;
        instance.setCup(expResult);
        boolean result = instance.isCup();
        assertEquals(result, expResult);
    }

    /**
     * Test of getCupTour method, of class Round.
     */
    @Test
    public void testGetCupTour() {
        System.out.println("getCupTour");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        int expResult = 5;
        instance.setCupTour(expResult);
        int result = instance.getCupTour();
        assertEquals(result, expResult);
    }

    /**
     * Test of setCupTour method, of class Round.
     */
    @Test
    public void testSetCupTour() {
        System.out.println("setCupTour");
       if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        int expResult = 5;
        instance.setCupTour(expResult);
        int result = instance.getCupTour();
        assertEquals(result, expResult);
    }

    /**
     * Test of getCupMaxTour method, of class Round.
     */
    @Test
    public void testGetCupMaxTour() {
        System.out.println("getCupMaxTour");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        int expResult = 7;
        instance.setCupMaxTour(expResult);
        int result = instance.getCupMaxTour();
        assertEquals(result, expResult);
    }

    /**
     * Test of setCupMaxTour method, of class Round.
     */
    @Test
    public void testSetCupMaxTour() {
        System.out.println("setCupMaxTour");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        int expResult = 7;
        instance.setCupMaxTour(expResult);
        int result = instance.getCupMaxTour();
        assertEquals(result, expResult);
    }

    /**
     * Test of isLooserCup method, of class Round.
     */
    @Test
    public void testIsLooserCup() {
        System.out.println("isLooserCup");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        boolean expResult = true;
        instance.setLooserCup(expResult);
        boolean result = instance.isLooserCup();
        assertEquals(result, expResult);
    }

    /**
     * Test of setLooserCup method, of class Round.
     */
    @Test
    public void testSetLooserCup() {
        System.out.println("setLooserCup");
         if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        boolean expResult = true;
        instance.setLooserCup(expResult);
        boolean result = instance.isLooserCup();
        assertEquals(result, expResult);
    }

    /**
     * Test of removeMatch method, of class Round.
     */
    @Test
    public void testRemoveMatch_int() {
        System.out.println("removeMatch");
          if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        int nb=instance.getMatchsCount();
        instance.removeMatch(0);
        assertEquals(nb-1,instance.getMatchsCount());
    }

    /**
     * Test of removeMatch method, of class Round.
     */
    @Test
    public void testRemoveMatch_Match() {
        System.out.println("removeMatch");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        int nb=instance.getMatchsCount();
        Match m=instance.getMatch(0);
        instance.removeMatch(m);
        assertEquals(nb-1,instance.getMatchsCount());
    }

    /**
     * Test of getMinBonus method, of class Round.
     */
    @Test
    public void testGetMinBonus() {
        System.out.println("getMinBonus");
         if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        double expResult = 0.75;
        instance.setMinBonus(expResult);
        double result = instance.getMinBonus();
        assertEquals(result, expResult, 0.0001);
    }

    /**
     * Test of getMaxBonus method, of class Round.
     */
    @Test
    public void testGetMaxBonus() {
        System.out.println("getMaxBonus");
         if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        double expResult = 1.75;
        instance.setMaxBonus(expResult);
        double result = instance.getMaxBonus();
        assertEquals(result, expResult, 0.001);
    }

    /**
     * Test of setMinBonus method, of class Round.
     */
    @Test
    public void testSetMinBonus() {
        System.out.println("setMinBonus");
         if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        double expResult = 0.75;
        instance.setMinBonus(expResult);
        double result = instance.getMinBonus();
        assertEquals(result, expResult, 0.0001);
    }

    /**
     * Test of setMaxBonus method, of class Round.
     */
    @Test
    public void testSetMaxBonus() {
        System.out.println("setMaxBonus");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        double expResult = 1.75;
        instance.setMaxBonus(expResult);
        double result = instance.getMaxBonus();
        assertEquals(result, expResult, 0.001);
    }

    /**
     * Test of getCoef method, of class Round.
     */
    @Test
    public void testGetCoef() {
        System.out.println("getCoef");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        Match mLast = instance.getMatch(0);
        Match mFirst = instance.getMatch(instance.getMatchsCount()-1);
        Match mMiddle = instance.getMatch(instance.getMatchsCount()/2);
        
        instance.setMaxBonus(2.0);
        instance.setMinBonus(0.0);
        
        
        double gap = instance.getMaxBonus() - instance.getMinBonus();
        double steps = gap / instance.getMatchsCount();
        double coefFirst = instance.getMinBonus() + steps * instance.getMatchsCount();
        double coefMiddle = instance.getMinBonus() + steps * (instance.getMatchsCount()/2+1);
        double coefLast = instance.getMinBonus() + steps * 1.0;
        
        double resultFirst = instance.getCoef(mFirst);
        double resultLast = instance.getCoef(mLast);
        double resultMiddle = instance.getCoef(mMiddle);
        assertEquals(resultLast, coefLast, 0.0001);
        assertEquals(resultMiddle, coefMiddle, 0.0001);
        assertEquals(resultFirst, coefFirst, 0.0001);
    }

    /**
     * Test of indexOf method, of class Round.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
         if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);
        int nb=instance.getMatchsCount();
        Match m=instance.getMatch(0);        
        assertEquals(0,instance.indexOf(m));
        
        instance.removeMatch(m);
        assertEquals(-1,instance.indexOf(m));
    }

    /**
     * Test of getXMLElementForDisplay method, of class Round.
     */
    @Test
    public void testGetXMLElementForDisplay() {
        System.out.println("getXMLElementForDisplay");
         if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);        
        Element result = instance.getXMLElementForDisplay();
        
        Round instance2=new Round();
        instance2.setXMLElementForDisplay(result);
        assertEquals(instance, instance2);
    }

    /**
     * Test of setXMLElementForDisplay method, of class Round.
     */
    @Test
    public void testSetXMLElementForDisplay() {
        System.out.println("setXMLElementForDisplay");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in file");
        }
        Round instance = Tournament.getTournament().getRound(0);        
        Element result = instance.getXMLElementForDisplay();
        
        Round instance2=new Round();
        instance2.setXMLElementForDisplay(result);
        assertEquals(instance, instance2);
    }

}