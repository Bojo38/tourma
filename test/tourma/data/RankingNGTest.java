/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.File;
import java.util.ArrayList;
import org.jdom.Element;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingIndiv;

/**
 *
 * @author WFMJ7631
 */
public class RankingNGTest {

    public RankingNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
                Tournament.getTournament().loadXML(new File("./test/ranking.xml"));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getXMLElement method, of class Ranking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);

        Element e = instance.getXMLElement();
        Ranking instance2 = new Ranking(e);
        for (int i = 0; i < rank.getRowCount(); i++) {
            assertEquals(instance2.getSortedValue(i,0), values.get(i).intValue());
        }
        instance.setXMLElement(e);
        
        for (int i = 0; i < rank.getRowCount(); i++) {
            assertEquals(instance2.getSortedValue(i,0), instance.getSortedValue(i,0));
            assertEquals(instance2.getSortedObject(i), instance.getSortedObject(i));
        }
        
    }

    /**
     * Test of setXMLElement method, of class Ranking.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);

        Element e = instance.getXMLElement();
        Ranking instance2 = new Ranking(e);
        for (int i = 0; i < rank.getRowCount(); i++) {
            assertEquals(instance2.getSortedValue(i,0), values.get(i).intValue());
        }
        instance.setXMLElement(e);
        
        for (int i = 0; i < rank.getRowCount(); i++) {
            assertEquals(instance2.getSortedValue(i,0), instance.getSortedValue(i,0));
            assertEquals(instance2.getSortedObject(i), instance.getSortedObject(i));
        }
    }

    /**
     * Test of getRank method, of class Ranking.
     */
    @Test
    public void testGetRank() {
        System.out.println("getRank");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        instance.setRank(rank);
        assertEquals(rank, instance.getRank());
    }

    /**
     * Test of setRank method, of class Ranking.
     */
    @Test
    public void testSetRank() {
        System.out.println("setRank");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        instance.setRank(rank);
        for (int i = 0; i < rank.getRowCount(); i++) {
            assertEquals(values.get(i).intValue(), instance.getSortedValue(i, 0));
        }
        assertEquals(rank, instance.getRank());

    }

    /**
     * Test of getName method, of class Ranking.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        String expResult = "Name";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(result, expResult);
    }

    /**
     * Test of setName method, of class Ranking.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        String expResult = "Name";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(result, expResult);
    }

    /**
     * Test of getType method, of class Ranking.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        String expResult = "Type";
        instance.setType(expResult);
        String result = instance.getType();
        assertEquals(result, expResult);
    }

    /**
     * Test of setType method, of class Ranking.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        String expResult = "Type";
        instance.setType(expResult);
        String result = instance.getType();
        assertEquals(result, expResult);
    }

    /**
     * Test of getValueType method, of class Ranking.
     */
    @Test
    public void testGetValueType() {
        System.out.println("getValueType");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        String expResult = "ValueType";
        instance.setValueType(expResult);
        String result = instance.getValueType();
        assertEquals(result, expResult);
    }

    /**
     * Test of setValueType method, of class Ranking.
     */
    @Test
    public void testSetValueType() {
        System.out.println("setValueType");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        String expResult = "ValueType";
        instance.setValueType(expResult);
        String result = instance.getValueType();
        assertEquals(result, expResult);
    }

    /**
     * Test of getCriteria method, of class Ranking.
     */
    @Test
    public void testGetCriteria() {
        System.out.println("getCriteria");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        Criteria expResult = new Criteria("Test");
        instance.setCriteria(expResult);
        assertEquals(expResult, instance.getCriteria());
    }

    /**
     * Test of setCriteria method, of class Ranking.
     */
    @Test
    public void testSetCriteria() {
        System.out.println("setCriteria");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        Criteria expResult = new Criteria("Test");
        instance.setCriteria(expResult);
        assertEquals(expResult, instance.getCriteria());
    }

    /**
     * Test of getRankingNumber method, of class Ranking.
     */
    @Test
    public void testGetRankingNumber() {
        System.out.println("getRankingNumber");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        assertEquals(instance.getRankingNumber(), values.size());
    }

    /**
     * Test of getDetail method, of class Ranking.
     */
    @Test
    public void testGetDetail() {
        System.out.println("getDetail");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        String expResult = "Detail";
        instance.setDetail(expResult);
        String result = instance.getDetail();
        assertEquals(result, expResult);
    }

    /**
     * Test of setDetail method, of class Ranking.
     */
    @Test
    public void testSetDetail() {
        System.out.println("setDetail");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        String expResult = "Detail";
        instance.setDetail(expResult);
        String result = instance.getDetail();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class Ranking.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        // if build from ranking, the result is O
        assertEquals(instance.getRowCount(), 0);
        // if build from XML, the result is values.size()
        Element e = instance.getXMLElement();
        Ranking instance2 = new Ranking(e);
        assertEquals(instance2.getRowCount(), values.size());
    }

    /**
     * Test of getSortedObject method, of class Ranking.
     */
    @Test
    public void testGetSortedObject() {
        System.out.println("getSortedObject");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        for (int i = 0; i < rank.getRowCount(); i++) {
            assertEquals(instance.getSortedObject(i), null);
        }

        Element e = instance.getXMLElement();
        Ranking instance2 = new Ranking(e);
        for (int i = 0; i < rank.getRowCount(); i++) {
            assertEquals(instance2.getSortedObject(i), rank.getSortedObject(i));
        }

    }

    /**
     * Test of getSortedValue method, of class Ranking.
     */
    @Test
    public void testGetSortedValue() {
        System.out.println("getSortedValue");
        ArrayList<Integer> values = new ArrayList<>();
        MjtRanking rank = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getActiveCoaches(),
                false, false);
        for (int i = 0; i < rank.getRowCount(); i++) {
            values.add(rank.getSortedValue(i, 0));
        }
        Ranking instance = new Ranking("Test", "INDIV", "notion", rank, values);
        for (int i = 0; i < rank.getRowCount(); i++) {
            assertEquals(instance.getSortedValue(i,0), 0);
        }

        Element e = instance.getXMLElement();
        Ranking instance2 = new Ranking(e);
        for (int i = 0; i < rank.getRowCount(); i++) {
            assertEquals(instance2.getSortedValue(i,0), values.get(i).intValue());
        }
    }

    /**
     * Test of getUID method, of class Ranking.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Ranking instance = null;
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class Ranking.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Ranking instance = null;
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
