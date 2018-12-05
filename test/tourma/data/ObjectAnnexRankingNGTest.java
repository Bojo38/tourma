/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom.Element;
import org.testng.Assert;
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
public class ObjectAnnexRankingNGTest {

    public ObjectAnnexRankingNGTest() {
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
     * Test of getValue method, of class ObjectAnnexRanking.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectAnnexRanking instance = new ObjectAnnexRanking(null, mValue, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue(mValue);
        assertEquals(mValue, instance.getValue());
    }

    /**
     * Test of compareTo method, of class ObjectAnnexRanking.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectAnnexRanking instance = new ObjectAnnexRanking(null, mValue, mValue1, mValue2, mValue3, mValue4, mValue5);
        ObjectAnnexRanking instance2 = new ObjectAnnexRanking(null, mValue, mValue1, mValue2, mValue3, mValue4, mValue5);
        assertEquals(instance.compareTo(instance2),0);
        instance2.setValue(12);
        Assert.assertTrue(instance.compareTo(instance2)>0);
        instance2.setValue(8);
        Assert.assertTrue(instance.compareTo(instance2)<0);
    }

    /**
     * Test of equals method, of class ObjectAnnexRanking.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
         int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectAnnexRanking instance = new ObjectAnnexRanking(null, mValue, mValue1, mValue2, mValue3, mValue4, mValue5);
        ObjectAnnexRanking instance2 = new ObjectAnnexRanking(null, mValue, mValue1, mValue2, mValue3, mValue4, mValue5);
        assertEquals(instance, instance2);
    }

    /**
     * Test of hashCode method, of class ObjectAnnexRanking.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectAnnexRanking instance = new ObjectAnnexRanking(null, mValue, mValue1, mValue2, mValue3, mValue4, mValue5);
        int hash = 97 * 7 + mValue;
        int result = instance.hashCode();
        assertEquals(result, hash);
    }

    /**
     * Test of getXMLElement method, of class ObjectAnnexRanking.
     */
    @Test(enabled = false)
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        ObjectAnnexRanking instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);

    }

    /**
     * Test of setXMLElement method, of class ObjectAnnexRanking.
     */
    @Test(enabled = false)
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        ObjectAnnexRanking instance = null;
        instance.setXMLElement(e);

    }

    /**
     * Test of setValue method, of class ObjectAnnexRanking.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectAnnexRanking instance = new ObjectAnnexRanking(null, mValue, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue(mValue);
        assertEquals(mValue, instance.getValue());
    }

}
