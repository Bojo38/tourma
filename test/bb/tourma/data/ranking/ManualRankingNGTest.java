/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import bb.tourma.data.Coach;
import bb.tourma.data.Competitor;
import bb.tourma.data.Tournament;
import java.io.File;
import java.util.ArrayList;
import org.jdom.Element;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author WFMJ7631
 */
public class ManualRankingNGTest {

    public ManualRankingNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
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
     * Test of sortDatas method, of class ManualRanking.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        ArrayList<Competitor> competitors = new ArrayList<Competitor>();
        competitors.add(new Coach("Test1"));
        competitors.add(new Coach("Test2"));
        ManualRanking instance = new ManualRanking(0, 1, 2, 3, 4, 5, competitors, false);
        instance.sortDatas();
    }

    /**
     * Test of addData method, of class ManualRanking.
     */
    @Test
    public void testAddData() {
        System.out.println("addData");
        Competitor obj = new Coach("Test");
        ArrayList<Competitor> competitors = new ArrayList<Competitor>();
        competitors.add(obj);
        ManualRanking instance = new ManualRanking(0, 1, 2, 3, 4, 5, competitors, false);

        assertEquals(instance.getCount(), 1);

        instance.addData(new Coach("Test2"));
        assertEquals(instance.getCount(), 2);
    }

    /**
     * Test of delData method, of class ManualRanking.
     */
    @Test
    public void testDelData() {
        System.out.println("delData");
        Competitor obj = new Coach("Test");
        ArrayList<Competitor> competitors = new ArrayList<Competitor>();
        competitors.add(obj);
        competitors.add(new Coach("Test2"));
        ManualRanking instance = new ManualRanking(0, 1, 2, 3, 4, 5, competitors, false);

        assertEquals(instance.getCount(), 2);

        competitors = new ArrayList<Competitor>();
        competitors.add(new Coach("Test3"));
        competitors.add(new Coach("Test4"));

        instance.addDatas(competitors);
        assertEquals(instance.getCount(), 4);
        instance.delData(obj);

        assertEquals(instance.getCount(), 3);

    }

    /**
     * Test of addDatas method, of class ManualRanking.
     */
    @Test
    public void testAddDatas() {
        System.out.println("addDatas");
        Competitor obj = new Coach("Test");
        ArrayList<Competitor> competitors = new ArrayList<Competitor>();
        competitors.add(obj);
        competitors.add(new Coach("Test2"));
        ManualRanking instance = new ManualRanking(0, 1, 2, 3, 4, 5, competitors, false);

        assertEquals(instance.getCount(), 2);

        competitors = new ArrayList<Competitor>();
        competitors.add(new Coach("Test3"));
        competitors.add(new Coach("Test4"));

        instance.addDatas(competitors);
        assertEquals(instance.getCount(), 4);
    }

    /**
     * Test of getXMLElement method, of class ManualRanking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        ArrayList<Competitor> competitors = new ArrayList<Competitor>();
        competitors.add(new Coach("Fyennij"));
        competitors.add(new Coach("Hebijillan"));
        ManualRanking instance = new ManualRanking(0, 1, 2, 3, 4, 5, competitors, false);
        
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertNotNull(result);

        ManualRanking other = new ManualRanking(0, 1, 2, 3, 4, 5, competitors, false);
        other.setXMLElement(result);

        assertEquals(other.getRankingType1(), instance.getRankingType1());
        assertEquals(other.getRankingType2(), instance.getRankingType2());
        assertEquals(other.getRankingType3(), instance.getRankingType3());
        assertEquals(other.getRankingType4(), instance.getRankingType4());
        assertEquals(other.getRankingType5(), instance.getRankingType5());
        assertEquals(other.getCount(), instance.getCount());
        assertEquals(other.getRound(), instance.getRound());

        other.sortDatas();
        instance.sortDatas();

        for (int i = 0; i < other.getRowCount(); i++) {
            //System.out.println(other.getObject(i).toString()+ " / "+ instance.getObject(i).toString());
            assertEquals(other.getObject(i), instance.getObject(i));
        }
    }

    /**
     * Test of setXMLElement method, of class ManualRanking.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        ArrayList<Competitor> competitors = new ArrayList<Competitor>();
        competitors.add(new Coach("Sran"));
        competitors.add(new Coach("Hebijillan"));
        ManualRanking instance = new ManualRanking(0, 1, 2, 3, 4, 5, competitors, false);
        
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertNotNull(result);

        ManualRanking other = new ManualRanking(0, 1, 2, 3, 4, 5, competitors, false);
        other.setXMLElement(result);

        assertEquals(other.getRankingType1(), instance.getRankingType1());
        assertEquals(other.getRankingType2(), instance.getRankingType2());
        assertEquals(other.getRankingType3(), instance.getRankingType3());
        assertEquals(other.getRankingType4(), instance.getRankingType4());
        assertEquals(other.getRankingType5(), instance.getRankingType5());
        assertEquals(other.getCount(), instance.getCount());
        assertEquals(other.getRound(), instance.getRound());

        other.sortDatas();
        instance.sortDatas();

        for (int i = 0; i < other.getRowCount(); i++) {
            //System.out.println(other.getObject(i).toString()+ " / "+ instance.getObject(i).toString());
            assertEquals(other.getObject(i), instance.getObject(i));
        }
    }

}
