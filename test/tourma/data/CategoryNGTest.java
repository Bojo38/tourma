/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import org.jdom.Element;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
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
public class CategoryNGTest {

    public CategoryNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament t=Tournament.getTournament();
        t.loadXML(new File("./test/category.xml"));
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
     * Test of putCategory method, of class Category.
     */
    @Test
    public void testPutCategory() {
        System.out.println("putCategory");
        String s = "TestCategory";
        Category c = new Category(s);
        Category.putCategory(s, c);
        Category tmp = Category.getCategory(s);
        assertEquals(c, tmp);
        Category.delCategory(s);
        tmp = Category.getCategory(s);
        Assert.assertNull(tmp);
    }

    /**
     * Test of newCategoryMap method, of class Category.
     */
    @Test
    public void testNewCategoryMap() {
        System.out.println("newCategoryMap");
        ArrayList<Category> list = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
            Category c = Tournament.getTournament().getCategory(i);
            list.add(c);
        }
        Category.newCategoryMap();
        for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
            Category c = Tournament.getTournament().getCategory(i);
            Category tmp = Category.getCategory(c.getName());
            Assert.assertNull(tmp);
            Category.putCategory(c.getName(), c);
            tmp = Category.getCategory(c.getName());
            Assert.assertEquals(c, tmp);
        }
    }

    /**
     * Test of getCategory method, of class Category.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        String s = "TestCategory";
        Category c = new Category(s);
        Category.putCategory(s, c);
        Category tmp = Category.getCategory(s);
        assertEquals(c, tmp);
        Category.delCategory(s);
        tmp = Category.getCategory(s);
        Assert.assertNull(tmp);
    }

    /**
     * Test of equals method, of class Category.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        String s = "TestCategory";
        Category c = new Category(s);
        Category c2 = new Category(s);
        Category c3 = new Category(s + "2");

        Assert.assertTrue(c.equals(c2));
        Assert.assertFalse(c.equals(c3));
    }

    /**
     * Test of hashCode method, of class Category.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");

        String s = "TestCategory";
        Category c = new Category(s);
        // Theorical hashcde
        int h = Objects.hashCode(s) + 29 * 7;
        assertEquals(h, c.hashCode());
    }

    /**
     * Test of compareTo method, of class Category.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");

        Assert.assertTrue(Tournament.getTournament().getCategoriesCount() > 1);
        for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
            Category c1 = Tournament.getTournament().getCategory(i);
            for (int j = 0; j < Tournament.getTournament().getCategoriesCount(); j++) {
                Category c2 = Tournament.getTournament().getCategory(j);
                assertEquals(c1.compareTo(c2), c1.getName().compareTo(c2.getName()));
            }
        }
    }

    /**
     * Test of getXMLElement method, of class Category.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Category instance = new Category("Test");
        Element result = instance.getXMLElement();

        Category cat = new Category("None");
        cat.setXMLElement(result);
        assertEquals(instance, cat);

    }

    /**
     * Test of isCategoryMapNull method, of class Category.
     */
    @Test
    public void testIsCategoryMapNull() {
        System.out.println("isCategoryMapNull");
        Assert.assertFalse(Category.isCategoryMapNull());
    }

    /**
     * Test of setXMLElement method, of class Category.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Category instance = new Category("Test");
        Element result = instance.getXMLElement();

        Category cat = new Category("None");
        cat.setXMLElement(result);
        assertEquals(instance, cat);
    }

    /**
     * Test of getName method, of class Category.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String s = "TestCategory";
        Category c = new Category(s);
        assertEquals(c.getName(), s);
    }

    /**
     * Test of setName method, of class Category.
     */
    @Test
    public void testSetmName() {
        System.out.println("setmName");
        String s = "TestCategory";
        Category c = new Category(s);
        assertEquals(c.getName(), s);
        c.setName("Test");
        assertEquals(c.getName(), "Test");

    }

    /**
     * Test of delCategory method, of class Category.
     */
    @Test
    public void testDelCategory() {
        System.out.println("delCategory");
        String s = "TestCategory";
        Category c = new Category(s);
        Category.putCategory(s, c);
        Category tmp = Category.getCategory(s);
        assertEquals(c, tmp);
        Category.delCategory(s);
        tmp = Category.getCategory(s);
        Assert.assertNull(tmp);
    }

    /**
     * Test of toString method, of class Category.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String s = "TestCategory";
        Category c = new Category(s);
        assertEquals(c.toString(), s);
    }

    /**
     * Test of getUID method, of class Category.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
          Random ran=new Random(1000);
        int UID = ran.nextInt();
        Category instance = new Category("UID");
        instance.setUID(UID);
        assertEquals(UID, instance.getUID());
      
    }

    /**
     * Test of setUID method, of class Category.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        Random ran=new Random(1000);
        int UID = ran.nextInt();
        Category instance = new Category("UID");
        instance.setUID(UID);
        assertEquals(UID, instance.getUID());
    }

    /**
     * Test of pull method, of class Category.
     */
    @Test
    public void testPull() {
        System.out.println("pull - Ignore, RMI test");    
    }

}
