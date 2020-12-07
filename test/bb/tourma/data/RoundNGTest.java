/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import bb.tourma.data.Round;
import bb.tourma.data.Tournament;
import bb.tourma.data.CoachMatch;
import bb.tourma.data.Match;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.jdom.Element;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import bb.tourma.languages.Translate;
import static org.testng.Assert.assertTrue;

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
        Round instance = new Round(Tournament.getTournament().getRoundsCount(), Tournament.getTournament());
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
        
        Round instance2=new Round(0, Tournament.getTournament());
        instance2.setXMLElement(result);
        
        instance.setFastCompare(false);
        boolean res=instance.equals(instance2);
        
        assertTrue(res);
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
        
        Round instance2=new Round(0, Tournament.getTournament());
        instance.setFastCompare(false);
        instance2.setXMLElement(result);
       boolean res=instance.equals(instance2);
        
        assertTrue(res);
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
        
        m=instance.getMatch(1);        
        assertEquals(1,instance.indexOf(m));
        
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
        
        Round instance2=new Round(0, Tournament.getTournament());        
        instance2.setXMLElementForDisplay(result);
        instance.setFastCompare(false);
        boolean res=instance.equals(instance2);
        
        assertTrue(res);
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
        
        instance.setFastCompare(false);
        Round instance2=new Round(0, Tournament.getTournament());
        instance2.setXMLElementForDisplay(result);
        boolean res=instance.equals(instance2);
        
        assertTrue(res);
    }

    /**
     * Test of getUID method, of class Round.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Round instance = new Round(Tournament.getTournament().getRoundsCount(), Tournament.getTournament());
        int expResult = 0;
        int result = instance.getUID();
        //assertEquals(result, expResult);;
    }

    /**
     * Test of setUID method, of class Round.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Round instance = new Round(Tournament.getTournament().getRoundsCount(), Tournament.getTournament());
        instance.setUID(UID);
    }

    /**
     * Test of pull method, of class Round.
     */
    @Test(enabled=false)
    public void testPull() {
        System.out.println("pull");
        Round round = null;
        Round instance = new Round(Tournament.getTournament().getRoundsCount(), Tournament.getTournament());
        instance.pull(round);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUpdated method, of class Round.
     */
    @Test
    public void testIsUpdated() {
        System.out.println("isUpdated");
        Round instance = Tournament.getTournament().getRound(0);
        boolean expResult = true;
        boolean result = instance.isUpdated();
        assertEquals(result, expResult);

    }

    /**
     * Test of setUpdated method, of class Round.
     */
    @Test
    public void testSetUpdated() {
        System.out.println("setUpdated");
        boolean updated = true;
        Round instance =Tournament.getTournament().getRound(0);
        instance.setUpdated(updated);
        boolean result = instance.isUpdated();
        assertEquals(result, true);
        
        instance.setUpdated(false);
        result = instance.isUpdated();
        assertEquals(result, false);
        
    }

    /**
     * Test of push method, of class Round.
     */
    @Test(enabled=false)
    public void testPush() {
        System.out.println("push");
        Round round = null;
        Round instance = new Round(Tournament.getTournament().getRoundsCount(), Tournament.getTournament());
        instance.push(round);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsCoachMatch method, of class Round.
     */
    @Test
    public void testContainsCoachMatch() {
        System.out.println("containsCoachMatch");
        
        Round instance = Tournament.getTournament().getRound(0);
        CoachMatch m = instance.getCoachMatchs().get(0);
        boolean expResult = true;
        boolean result = instance.containsCoachMatch(m);
        assertEquals(result, expResult);
        
        
         m = null;
        expResult = false;
        result = instance.containsCoachMatch(m);
        assertEquals(result, expResult);
    }

    /**
     * Test of equals method, of class Round.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Round instance = Tournament.getTournament().getRound(0);
        boolean expResult = false;
        
        Round copy=new Round(0,Tournament.getTournament());
        copy.setXMLElement(instance.getXMLElement());
        
        boolean result = instance.equals(copy);
        assertEquals(result, expResult);
    }


    /**
     * Test of recomputeMatchs method, of class Round.
     */
    @Test
    public void testRecomputeMatchs() {
        System.out.println("recomputeMatchs");
        Round instance = Tournament.getTournament().getRound(0);
        instance.recomputeMatchs();
       
    }

    /**
     * Test of allMatchesEntered method, of class Round.
     */
    @Test
    public void testAllMatchesEntered() {
        System.out.println("allMatchesEntered");
        Round instance = Tournament.getTournament().getRound(0);
        boolean expResult = true;
        boolean result = instance.allMatchesEntered();
        assertEquals(result, expResult);
        
        Criterion td=Tournament.getTournament().getParams().getCriterion(0);
        Value val=new Value(td);
        val.setValue1(-1);
        instance.getCoachMatchs().get(0).getValues().put(td,val);
        expResult = false;
        result = instance.allMatchesEntered();
        assertEquals(result, expResult);
        
    }

    /**
     * Test of setMatchPosition method, of class Round.
     */
    @Test
    public void testSetMatchPosition() {
        System.out.println("setMatchPosition");
       
 
        Round instance = Tournament.getTournament().getRound(0);
        Match m=instance.getMatch(0);
        instance.setMatchPosition(m, 1);
        
        assertEquals(instance.indexOf(m),1);
        
    }

    /**
     * Test of isRankingUpdated method, of class Round.
     */
    @Test
    public void testIsRankingUpdated() {
        System.out.println("isRankingUpdated");
        Round instance = Tournament.getTournament().getRound(0);;
        boolean expResult = true;
        boolean result = instance.isRankingUpdated();
        assertEquals(result, expResult);
        
        
       
    }

    /**
     * Test of getRankings method, of class Round.
     */
    @Test
    public void testGetRankings() {
        System.out.println("getRankings");
        boolean ro = false;
        Round instance = Tournament.getTournament().getRound(0);
        Rankings result = instance.getRankings(ro);
        Assert.assertNotNull(result);

    }

    /**
     * Test of update method, of class Round.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Round instance = Tournament.getTournament().getRound(0);
        instance.update();
    }

    /**
     * Test of setRankingsToUpdate method, of class Round.
     */
    @Test
    public void testSetRankingsToUpdate() {
        System.out.println("setRankingsToUpdate");
        Round instance = Tournament.getTournament().getRound(0);;
        instance.setRankingsToUpdate();
       
    }

}
