/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import bb.tourma.data.ranking.IndivRanking;
import bb.tourma.data.ranking.Ranking;
import bb.tourma.utility.StringConstants;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
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
public class RankingForExportNGTest {
    
    static Tournament tour;
    static Round round;
    static RankingForExport instance;
    
    public RankingForExportNGTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        tour = Tournament.getTournament();
        round = tour.getRound(0);
        instance = new RankingForExport(RankingForExport.CS_Individual,
                RankingForExport.CS_General,
                StringConstants.CS_NULL, round.getRankings(false).getIndivRankingSet().getRanking(), tour.getRankingTypes(false));
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
     * Test of getUID method, of class RankingForExport.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        
        int result = instance.getUID();
        assertEquals(result, 1);
    }

    /**
     * Test of setUID method, of class RankingForExport.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int result = instance.getUID();
        assertEquals(result, 1);
        
        instance.setUID(17);
        result = instance.getUID();
        assertEquals(result, 17);
    }

    /**
     * Test of getCriterion method, of class RankingForExport.
     */
    @Test
    public void testGetCriterion() {
        System.out.println("getCriterion");
        
        Criterion result = instance.getCriterion();
        assertEquals(result.getAccronym(), "???");
        
    }

    /**
     * Test of setCriterion method, of class RankingForExport.
     */
    @Test
    public void testSetCriterion() {
        System.out.println("setCriterion");
        Criterion result = instance.getCriterion();
        assertEquals(result.getAccronym(), "???");
        
        Criterion crit = new Criterion("toto");
        instance.setCriterion(crit);
        result = instance.getCriterion();
        assertEquals(result.getAccronym(), "toto");
    }

    /**
     * Test of getFormula method, of class RankingForExport.
     */
    @Test
    public void testGetFormula() {
        System.out.println("getFormula");
        
        Formula result = instance.getFormula();
        assertEquals(result.getName(), "???");
    }

    /**
     * Test of setFormula method, of class RankingForExport.
     */
    @Test
    public void testSetFormula() {
        System.out.println("setFormula");
        Formula result = instance.getFormula();
        assertEquals(result.getName(), "???");
        
        Formula f = new Formula("Toto");
        instance.setFormula(f);
        
        result = instance.getFormula();
        assertEquals(result, f);
    }

    /**
     * Test of getXMLElement method, of class RankingForExport.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        
        Element expResult = instance.getXMLElement();
        assertNotNull(expResult);
        
        instance.setXMLElement(expResult);

        Element expResult2 = instance.getXMLElement();

        Document doc1 = new Document(expResult);
        Document doc2 = new Document(expResult2);

        XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
        OutputStream output1 = new OutputStream() {
            private StringBuilder string = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            //Netbeans IDE automatically overrides this toString()
            public String toString() {
                return this.string.toString();
            }
        };
        OutputStream output2 = new OutputStream() {
            private StringBuilder string = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            //Netbeans IDE automatically overrides this toString()
            public String toString() {
                return this.string.toString();
            }
        };
        try {
            sortie.output(doc1, output1);
        } catch (IOException ioe) {
            fail("Exception while comparing string");
        }
        String s1 = output1.toString();

        try {
            sortie.output(doc2, output2);
        } catch (IOException ioe) {
            fail("Exception while comparing string");
        }
        String s2 = output2.toString();

        System.out.println("S1 ---");
        System.out.println(s1);
        System.out.println("S2 ---");
        System.out.println(s2);
        assertEquals(s1, s2);
        
        
    }

    /**
     * Test of setXMLElement method, of class RankingForExport.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
       Element expResult = instance.getXMLElement();
        assertNotNull(expResult);
        
        instance.setXMLElement(expResult);

        Element expResult2 = instance.getXMLElement();

        Document doc1 = new Document(expResult);
        Document doc2 = new Document(expResult2);

        XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
        OutputStream output1 = new OutputStream() {
            private StringBuilder string = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            //Netbeans IDE automatically overrides this toString()
            public String toString() {
                return this.string.toString();
            }
        };
        OutputStream output2 = new OutputStream() {
            private StringBuilder string = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            //Netbeans IDE automatically overrides this toString()
            public String toString() {
                return this.string.toString();
            }
        };
        try {
            sortie.output(doc1, output1);
        } catch (IOException ioe) {
            fail("Exception while comparing string");
        }
        String s1 = output1.toString();

        try {
            sortie.output(doc2, output2);
        } catch (IOException ioe) {
            fail("Exception while comparing string");
        }
        String s2 = output2.toString();

        System.out.println("S1 ---");
        System.out.println(s1);
        System.out.println("S2 ---");
        System.out.println(s2);
        assertEquals(s1, s2);
    }

    /**
     * Test of getRankingNumber method, of class RankingForExport.
     */
    @Test
    public void testGetRankingNumber() {
        System.out.println("getRankingNumber");
      
        int expResult = 2;
        int result = instance.getRankingNumber();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRank method, of class RankingForExport.
     */
    @Test
    public void testGetRank() {
        System.out.println("getRank");
        
        Ranking r = instance.getRank();
        
        assertNotNull(r);
    }

    /**
     * Test of setRank method, of class RankingForExport.
     */
    @Test
    public void testSetRank() {
        System.out.println("setRank");
        Ranking r = instance.getRank();
        
        assertNotNull(r);
        
        Ranking r2 = round.getRankings(true).getIndivRankingSet().getRanking();
        instance.setRank(r2);
        assertEquals(instance.getRank(), r2);
        
        instance.setRank(round.getRankings(false).getIndivRankingSet().getRanking());
        
    }

    /**
     * Test of getName method, of class RankingForExport.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        String result = instance.getName();
        assertEquals(result, "INDIVIDUAL");
    }

    /**
     * Test of setName method, of class RankingForExport.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String result = instance.getName();
        assertEquals(result, "INDIVIDUAL");
        
        instance.setName("Name");
        
        result = instance.getName();
        assertEquals(result, "Name");
    }

    /**
     * Test of getType method, of class RankingForExport.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
       
        String expResult = "General";
        String result = instance.getType();
        assertEquals(result, expResult);
    }

    /**
     * Test of setType method, of class RankingForExport.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String expResult = "General";
        String result = instance.getType();
        assertEquals(result, expResult);
        
        instance.setType("Toto");
        
        result = instance.getType();
        assertEquals(result, "Toto");
    }

    /**
     * Test of getValueType method, of class RankingForExport.
     */
    @Test
    public void testGetValueType() {
        System.out.println("getValueType");
       
        String expResult = "";
        String result = instance.getValueType();
        assertEquals(result, expResult);
    }

    /**
     * Test of setValueType method, of class RankingForExport.
     */
    @Test
    public void testSetValueType() {
        System.out.println("setValueType");
        String expResult = "toto";
        String result = instance.getValueType();
        assertEquals(result, "");
        
        instance.setValueType("toto");
        
        result = instance.getValueType();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class RankingForExport.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
       
        int result = instance.getRowCount();
        assertEquals(result, 0);
    }

    /**
     * Test of getSortedObject method, of class RankingForExport.
     */
    @Test
    public void testGetSortedObject() {
        System.out.println("getSortedObject");
        int i = 0;
      
        ObjectRanking expResult = null;
        ObjectRanking result = instance.getSortedObject(i);
        assertEquals(result, expResult);
    }

    /**
     * Test of getSortedValue method, of class RankingForExport.
     */
    @Test
    public void testGetSortedValue() {
        System.out.println("getSortedValue");
        int i = 0;
        int valIndex = 0;
      
        int expResult = 0;
        int result = instance.getSortedValue(i, valIndex);
        assertEquals(result, expResult);
    }

    /**
     * Test of getDetail method, of class RankingForExport.
     */
    @Test
    public void testGetDetail() {
        System.out.println("getDetail");
        
        String result = instance.getDetail();
        assertEquals(result, "");
    }

    /**
     * Test of setDetail method, of class RankingForExport.
     */
    @Test
    public void testSetDetail() {
        System.out.println("setDetail");
        
        String result = instance.getDetail();
        assertEquals(result, "");
        
        instance.setDetail("Toto");
        
        result = instance.getDetail();
        assertEquals(result, "Toto");
    }
    
}
