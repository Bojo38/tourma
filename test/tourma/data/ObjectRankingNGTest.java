/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom.Element;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
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
public class ObjectRankingNGTest {

    public ObjectRankingNGTest() {
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
     * Test of getObject method, of class ObjectRanking.
     */
    @Test
    public void testGetObject() {
        System.out.println("getObject");
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        Coach c = new Coach("toto");
        instance.setObject(c);
        assertEquals(c.getName(), ((Coach) instance.getObject()).getName());
    }

    /**
     * Test of getValue1 method, of class ObjectRanking.
     */
    @Test
    public void testGetValue1() {
        System.out.println("getValue1");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue1(mValue);
        assertEquals(mValue, instance.getValue1());
    }

    /**
     * Test of getValue2 method, of class ObjectRanking.
     */
    @Test
    public void testGetValue2() {
        System.out.println("getValue2");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue2(mValue);
        assertEquals(mValue, instance.getValue2());
    }

    /**
     * Test of getValue3 method, of class ObjectRanking.
     */
    @Test
    public void testGetValue3() {
        System.out.println("getValue3");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue3(mValue);
        assertEquals(mValue, instance.getValue3());
    }

    /**
     * Test of getValue4 method, of class ObjectRanking.
     */
    @Test
    public void testGetValue4() {
        System.out.println("getValue4");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue4(mValue);
        assertEquals(mValue, instance.getValue4());
    }

    /**
     * Test of getValue5 method, of class ObjectRanking.
     */
    @Test
    public void testGetValue5() {
        System.out.println("getValue5");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue5(mValue);
        assertEquals(mValue, instance.getValue5());
    }

    /**
     * Test of compareTo method, of class ObjectRanking.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
         int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        ObjectRanking instance2 = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        
        assertTrue(instance.compareTo(instance2)==0);
        instance2.setValue1(0);
        assertTrue(instance.compareTo(instance2)<0);
        instance2.setValue1(20);
        assertTrue(instance.compareTo(instance2)>0);
        instance2.setValue1(mValue1);
        
        assertTrue(instance.compareTo(instance2)==0);
        instance2.setValue2(0);
        assertTrue(instance.compareTo(instance2)<0);
        instance2.setValue2(20);
        assertTrue(instance.compareTo(instance2)>0);
        instance2.setValue2(mValue2);
        
        assertTrue(instance.compareTo(instance2)==0);
        instance2.setValue3(0);
        assertTrue(instance.compareTo(instance2)<0);
        instance2.setValue3(20);
        assertTrue(instance.compareTo(instance2)>0);
        instance2.setValue3(mValue3);
        
        assertTrue(instance.compareTo(instance2)==0);
        instance2.setValue4(0);
        assertTrue(instance.compareTo(instance2)<0);
        instance2.setValue4(20);
        assertTrue(instance.compareTo(instance2)>0);
        instance2.setValue4(mValue4);
        
        assertTrue(instance.compareTo(instance2)==0);
        instance2.setValue5(0);
        assertTrue(instance.compareTo(instance2)<0);
        instance2.setValue5(20);
        assertTrue(instance.compareTo(instance2)>0);
        instance2.setValue5(mValue5);
    }

    /**
     * Test of hashCode method, of class ObjectRanking.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Coach c=new Coach("toto");
         int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(c, mValue1, mValue2, mValue3, mValue4, mValue5);
        int result=instance.hashCode();
        int hash = 5;
        hash = 17 * hash + c.hashCode();
        hash = 17 * hash + mValue1;
        hash = 17 * hash + mValue2;
        hash = 17 * hash + mValue3;
        hash = 17 * hash + mValue4;
        hash = 17 * hash + mValue5;
        assertEquals(result, hash);
    }

    /**
     * Test of equals method, of class ObjectRanking.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        ObjectRanking instance2 = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        assertEquals(instance, instance2);
    }

    /**
     * Test of getXMLElement method, of class ObjectRanking.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
         int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        Coach c = new Coach("toto");
        
        instance.setObject(c);
        
        Element result = instance.getXMLElement();
        String text=result.toString();
        
        assertEquals(text, "[Element: <Position/>]");
      
    }

    /**
     * Test of setXMLElement method, of class ObjectRanking.
     */
    @Test(enabled=false)
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        ObjectRanking instance = null;
        instance.setXMLElement(e);

    }

    /**
     * Test of setObject method, of class ObjectRanking.
     */
    @Test
    public void testSetObject() {
        System.out.println("setObject");
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        Coach c = new Coach("toto");
        instance.setObject(c);
        assertEquals(c.getName(), ((Coach) instance.getObject()).getName());
    }

    /**
     * Test of setValue1 method, of class ObjectRanking.
     */
    @Test
    public void testSetValue1() {
        System.out.println("setValue1");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue1(mValue);
        assertEquals(mValue, instance.getValue1());
    }

    /**
     * Test of setValue2 method, of class ObjectRanking.
     */
    @Test
    public void testSetValue2() {
        System.out.println("setValue2");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue2(mValue);
        assertEquals(mValue, instance.getValue2());
    }

    /**
     * Test of setValue3 method, of class ObjectRanking.
     */
    @Test
    public void testSetValue3() {
        System.out.println("setValue3");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue3(mValue);
        assertEquals(mValue, instance.getValue3());
    }

    /**
     * Test of setValue4 method, of class ObjectRanking.
     */
    @Test
    public void testSetValue4() {
        System.out.println("setValue4");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue4(mValue);
        assertEquals(mValue, instance.getValue4());
    }

    /**
     * Test of setValue5 method, of class ObjectRanking.
     */
    @Test
    public void testSetValue5() {
        System.out.println("setValue5");
        int mValue = 10;
        int mValue1 = 1;
        int mValue2 = 2;
        int mValue3 = 3;
        int mValue4 = 4;
        int mValue5 = 5;
        ObjectRanking instance = new ObjectRanking(null, mValue1, mValue2, mValue3, mValue4, mValue5);
        instance.setValue5(mValue);
        assertEquals(mValue, instance.getValue5());
    }

    /**
     * Test of setValue method, of class ObjectRanking.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        int index = 0;
        int value = 0;
        ObjectRanking instance = null;
        instance.setValue(index, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class ObjectRanking.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        int index = 0;
        ObjectRanking instance = null;
        int expResult = 0;
        int result = instance.getValue(index);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
