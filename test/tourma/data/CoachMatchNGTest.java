/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import org.jdom2.Element;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author WFMJ7631
 */
public class CoachMatchNGTest {

    public CoachMatchNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/coachMatch.xml"));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getXMLElement method, of class CoachMatch.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        Criteria crit = Tournament.getTournament().getParams().getCriteria(0);
        CoachMatch instance = acm.get(0);
        Round r = instance.getRound();
        Element result = instance.getXMLElement();

        CoachMatch cm = new CoachMatch(r);
        cm.setXMLElement(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of setXMLElement method, of class CoachMatch.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        Criteria crit = Tournament.getTournament().getParams().getCriteria(0);
        CoachMatch instance = acm.get(0);
        Round r = instance.getRound();
        Element result = instance.getXMLElement();

        CoachMatch cm = new CoachMatch(r);
        cm.setXMLElement(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of getWinner method, of class CoachMatch.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        Criteria crit = Tournament.getTournament().getParams().getCriteria(0);
        CoachMatch instance = acm.get(0);
        tourma.data.Value val = instance.getValue(crit);

        int save1 = val.getValue1();
        int save2 = val.getValue2();

        val.setValue1(1);
        val.setValue2(0);
        instance.resetWL();
        Competitor comp = instance.getWinner();
        assertEquals(comp, instance.getCompetitor1());
        val.setValue1(0);
        val.setValue2(1);
        instance.resetWL();
        comp = instance.getWinner();
        assertEquals(comp, instance.getCompetitor2());

        val.setValue1(save1);
        val.setValue2(save2);
        instance.resetWL();
    }

    /**
     * Test of getLooser method, of class CoachMatch.
     */
    @Test
    public void testGetLooser() {
        System.out.println("getLooser");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        Criteria crit = Tournament.getTournament().getParams().getCriteria(0);
        CoachMatch instance = acm.get(0);
        tourma.data.Value val = instance.getValue(crit);

        int save1 = val.getValue1();
        int save2 = val.getValue2();

        val.setValue1(1);
        val.setValue2(0);
        instance.resetWL();
        Competitor comp = instance.getLooser();
        assertEquals(comp, instance.getCompetitor2());
        val.setValue1(0);
        val.setValue2(1);
        instance.resetWL();
        comp = instance.getLooser();
        assertEquals(comp, instance.getCompetitor1());

        val.setValue1(save1);
        val.setValue2(save2);
        instance.resetWL();
    }

    /**
     * Test of resetWL method, of class CoachMatch.
     */
    @Test
    public void testResetWL() {
        System.out.println("resetWL");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        Criteria crit = Tournament.getTournament().getParams().getCriteria(0);
        CoachMatch instance = acm.get(0);
        tourma.data.Value val = instance.getValue(crit);

        int save1 = val.getValue1();
        int save2 = val.getValue2();

        val.setValue1(1);
        val.setValue2(0);
        instance.resetWL();
        Competitor comp = instance.getWinner();
        assertEquals(comp, instance.getCompetitor1());
        val.setValue1(0);
        val.setValue2(1);
        assertEquals(comp, instance.getCompetitor1());
        instance.resetWL();
        comp = instance.getWinner();
        assertEquals(comp, instance.getCompetitor2());

        val.setValue1(save1);
        val.setValue2(save2);
        instance.resetWL();
    }

    /**
     * Test of getRoster1 method, of class CoachMatch.
     */
    @Test
    public void testGetRoster1() {
        System.out.println("getRoster1");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        Coach comp = (Coach) instance.getCompetitor1();
        RosterType r = comp.getRoster();
        instance.setRoster1(r);
        assertEquals(r, instance.getRoster1());
    }

    /**
     * Test of setRoster1 method, of class CoachMatch.
     */
    @Test
    public void testSetRoster1() {
        System.out.println("setRoster1");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        Coach comp = (Coach) instance.getCompetitor1();
        RosterType r = comp.getRoster();
        instance.setRoster1(r);
        assertEquals(comp.getRoster(), instance.getRoster1());
    }

    /**
     * Test of getRoster2 method, of class CoachMatch.
     */
    @Test
    public void testGetRoster2() {
        System.out.println("getRoster2");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        Coach comp = (Coach) instance.getCompetitor2();
        RosterType r = comp.getRoster();
        instance.setRoster2(r);
        assertEquals(comp.getRoster(), instance.getRoster2());
    }

    /**
     * Test of setRoster2 method, of class CoachMatch.
     */
    @Test
    public void testSetRoster2() {
        System.out.println("setRoster2");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        Coach comp = (Coach) instance.getCompetitor2();
        RosterType r = comp.getRoster();
        instance.setRoster2(r);
        assertEquals(comp.getRoster(), instance.getRoster2());
    }

    /**
     * Test of getSubstitute1 method, of class CoachMatch.
     */
    @Test
    public void testGetSubstitute1() {
        System.out.println("getSubstitute1");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        Substitute s1 = instance.getSubstitute1();
        if (s1 == null) {
            Coach sub = new Coach();
            Substitute s = new Substitute();
            s.setTitular((Coach) instance.getCompetitor1());
            s.setSubstitute(sub);
            instance.setSubstitute1(s);
            s1 = instance.getSubstitute1();
        }
        assertNotNull(instance.getSubstitute1());
        instance.setSubstitute1(null);
    }

    /**
     * Test of setSubstitute1 method, of class CoachMatch.
     */
    @Test
    public void testSetSubstitute1() {
        System.out.println("setSubstitute1");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        Substitute s1 = instance.getSubstitute1();
        Coach sub = new Coach();
        Substitute s = new Substitute();
        s.setTitular((Coach) instance.getCompetitor1());
        s.setSubstitute(sub);
        instance.setSubstitute1(s);
        Substitute res = instance.getSubstitute1();
        Assert.assertTrue(res == s);
        instance.setSubstitute1(null);
    }

    /**
     * Test of getSubstitute2 method, of class CoachMatch.
     */
    @Test
    public void testGetSubstitute2() {
        System.out.println("getSubstitute2");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        Substitute s1 = instance.getSubstitute2();
        if (s1 == null) {
            Coach sub = new Coach();
            Substitute s = new Substitute();
            s.setTitular((Coach) instance.getCompetitor2());
            s.setSubstitute(sub);
            instance.setSubstitute2(s);
            s1 = instance.getSubstitute2();
        }
        assertNotNull(instance.getSubstitute2());
        instance.setSubstitute2(null);
    }

    /**
     * Test of setSubstitute2 method, of class CoachMatch.
     */
    @Test
    public void testSetSubstitute2() {
        System.out.println("setSubstitute2");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        Substitute s1 = instance.getSubstitute2();
        Coach sub = new Coach();
        Substitute s = new Substitute();
        s.setTitular((Coach) instance.getCompetitor2());
        s.setSubstitute(sub);
        instance.setSubstitute2(s);

        Substitute res = instance.getSubstitute2();
        Assert.assertTrue(res == s);
        instance.setSubstitute2(null);
    }

    /**
     * Test of isRefusedBy1 method, of class CoachMatch.
     */
    @Test
    public void testIsRefusedBy1() {
        System.out.println("isRefusedBy1");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        instance.setRefusedBy1(true);
        Assert.assertTrue(instance.isRefusedBy1());
        instance.setRefusedBy1(false);
        Assert.assertFalse(instance.isRefusedBy1());

    }

    /**
     * Test of setRefusedBy1 method, of class CoachMatch.
     */
    @Test
    public void testSetRefusedBy1() {
        System.out.println("setRefusedBy1");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        instance.setRefusedBy1(true);
        Assert.assertTrue(instance.isRefusedBy1());
        instance.setRefusedBy1(false);
        Assert.assertFalse(instance.isRefusedBy1());
    }

    /**
     * Test of isRefusedBy2 method, of class CoachMatch.
     */
    @Test
    public void testIsRefusedBy2() {
        System.out.println("isRefusedBy2");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        instance.setRefusedBy2(true);
        Assert.assertTrue(instance.isRefusedBy2());
        instance.setRefusedBy2(false);
        Assert.assertFalse(instance.isRefusedBy2());
    }

    /**
     * Test of setRefusedBy2 method, of class CoachMatch.
     */
    @Test
    public void testSetRefusedBy2() {
        System.out.println("setRefusedBy2");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        instance.setRefusedBy2(true);
        Assert.assertTrue(instance.isRefusedBy2());
        instance.setRefusedBy2(false);
        Assert.assertFalse(instance.isRefusedBy2());
    }

    /**
     * Test of isConcedeedBy1 method, of class CoachMatch.
     */
    @Test
    public void testIsConcedeedBy1() {
        System.out.println("isConcedeedBy1");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        instance.setConcedeedBy1(true);
        Assert.assertTrue(instance.isConcedeedBy1());
        instance.setConcedeedBy1(false);
        Assert.assertFalse(instance.isConcedeedBy1());
    }

    /**
     * Test of setConcedeedBy1 method, of class CoachMatch.
     */
    @Test
    public void testSetConcedeedBy1() {
        System.out.println("setConcedeedBy1");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        instance.setConcedeedBy1(true);
        Assert.assertTrue(instance.isConcedeedBy1());
        instance.setConcedeedBy1(false);
        Assert.assertFalse(instance.isConcedeedBy1());
    }

    /**
     * Test of isConcedeedBy2 method, of class CoachMatch.
     */
    @Test
    public void testIsConcedeedBy2() {
        System.out.println("isConcedeedBy2");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        instance.setConcedeedBy2(true);
        Assert.assertTrue(instance.isConcedeedBy2());
        instance.setConcedeedBy2(false);
        Assert.assertFalse(instance.isConcedeedBy2());
    }

    /**
     * Test of setConcedeedBy2 method, of class CoachMatch.
     */
    @Test
    public void testSetConcedeedBy2() {
        System.out.println("setConcedeedBy2");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        instance.setConcedeedBy2(true);
        Assert.assertTrue(instance.isConcedeedBy2());
        instance.setConcedeedBy2(false);
        Assert.assertFalse(instance.isConcedeedBy2());
    }

    /**
     * Test of getValue method, of class CoachMatch.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            assertNotNull(instance.getValue(crit));
        }
    }

    /**
     * Test of getValueCount method, of class CoachMatch.
     */
    @Test
    public void testGetValueCount() {
        System.out.println("getValueCount");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        assertEquals(instance.getValueCount(), Tournament.getTournament().getParams().getCriteriaCount());

    }

    /**
     * Test of putValue method, of class CoachMatch.
     */
    @Test
    public void testPutValue() {
        System.out.println("putValue");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            Value v = instance.getValue(crit);
            Value v2 = new Value(crit);
            v2.setValue1(10);
            v2.setValue2(5);
            instance.putValue(crit, v2);
            assertEquals(instance.getValue(crit), v2);
        }
    }

    /**
     * Test of removeValue method, of class CoachMatch.
     */
    @Test
    public void testRemoveValue() {
        System.out.println("removeValue");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            Value v = instance.getValue(crit);
            assertNotNull(instance.getValue(crit));
            instance.removeValue(crit);
            assertNull(instance.getValue(crit));
            instance.putValue(crit, v);

        }
    }

    /**
     * Test of getXMLElementForDisplay method, of class CoachMatch.
     */
    @Test
    public void testGetXMLElementForDisplay() {
        System.out.println("getXMLElementForDisplay");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        Criteria crit = Tournament.getTournament().getParams().getCriteria(0);
        CoachMatch instance = acm.get(0);
        Round r = instance.getRound();
        Element result = instance.getXMLElementForDisplay();

        CoachMatch cm = new CoachMatch(r);
        cm.setXMLElementForDisplay(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of setXMLElementForDisplay method, of class CoachMatch.
     */
    @Test
    public void testSetXMLElementForDisplay() {
        System.out.println("setXMLElementForDisplay");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        Criteria crit = Tournament.getTournament().getParams().getCriteria(0);
        CoachMatch instance = acm.get(0);
        Round r = instance.getRound();
        Element result = instance.getXMLElementForDisplay();

        CoachMatch cm = new CoachMatch(r);
        cm.setXMLElementForDisplay(result);
        assertEquals(instance, cm);
    }

    /**
     * Test of isFullNaf method, of class CoachMatch.
     */
    @Test
    public void testIsFullNaf() {
        System.out.println("isFullNaf");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        Coach c1 = (Coach) instance.getCompetitor1();
        Coach c2 = (Coach) instance.getCompetitor2();
        c1.setNaf(1);
        c2.setNaf(2);

        Assert.assertTrue(instance.isFullNaf());

        c1.setNaf(0);
        c2.setNaf(2);
        Assert.assertFalse(instance.isFullNaf());

        c1.setNaf(1);
        c2.setNaf(0);
        Assert.assertFalse(instance.isFullNaf());
        c1.setNaf(0);
        c2.setNaf(0);
        Assert.assertFalse(instance.isFullNaf());
    }

    /**
     * Test of equals method, of class CoachMatch.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        CoachMatch compare = new CoachMatch(instance.getRound());

        compare.setCompetitor1(instance.getCompetitor1());
        compare.setCompetitor2(instance.getCompetitor2());
        compare.setConcedeedBy1(instance.isConcedeedBy1());
        compare.setConcedeedBy2(instance.isConcedeedBy2());
        compare.setRefusedBy1(instance.isRefusedBy1());
        compare.setRefusedBy2(instance.isRefusedBy2());
        compare.setRoster1(instance.getRoster1());
        compare.setRoster2(instance.getRoster2());

        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            Value v = instance.getValue(crit);
            Value value = new Value(crit);
            value.setValue1(v.getValue1());
            value.setValue2(v.getValue2());
            compare.putValue(crit, value);
        }

        assertEquals(instance, compare);

    }

    /**
     * Test of hashCode method, of class CoachMatch.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        int result = instance.hashCode();

        int hash = 5;
        hash = 71 * hash + Objects.hashCode(instance.getRoster1());
        hash = 71 * hash + Objects.hashCode(instance.getRoster2());
        hash = 71 * hash + Objects.hashCode(instance.getValues());
        hash = 71 * hash + (instance.isRefusedBy1() ? 1 : 0);
        hash = 71 * hash + (instance.isRefusedBy2() ? 1 : 0);
        hash = 71 * hash + (instance.isConcedeedBy1() ? 1 : 0);
        hash = 71 * hash + (instance.isConcedeedBy2() ? 1 : 0);

        assertEquals(result, hash);
    }

    /**
     * Test of getValues method, of class CoachMatch.
     */
    @Test
    public void testGetValues() {
        System.out.println("getValues");
        if (Tournament.getTournament().getRoundsCount() == 0) {
            fail("No round in tournament");
        }
        ArrayList<CoachMatch> acm = Tournament.getTournament().getRound(0).getCoachMatchs();
        if (acm.isEmpty()) {
            fail("No match in round");
        }
        CoachMatch instance = acm.get(0);
        assertNotNull(instance.getValues());
    }

}
