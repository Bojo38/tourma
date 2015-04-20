/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
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
public class MainTreeModelNGTest {
    
    public MainTreeModelNGTest() {
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
     * Test of getRoot method, of class MainTreeModel.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");
        MainTreeModel instance = new MainTreeModel();
        Object expResult = null;
        Object result = instance.getRoot();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChild method, of class MainTreeModel.
     */
    @Test
    public void testGetChild() {
        System.out.println("getChild");
        Object parent = null;
        int index = 0;
        MainTreeModel instance = new MainTreeModel();
        Object expResult = null;
        Object result = instance.getChild(parent, index);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChildCount method, of class MainTreeModel.
     */
    @Test
    public void testGetChildCount() {
        System.out.println("getChildCount");
        Object parent = null;
        MainTreeModel instance = new MainTreeModel();
        int expResult = 0;
        int result = instance.getChildCount(parent);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLeaf method, of class MainTreeModel.
     */
    @Test
    public void testIsLeaf() {
        System.out.println("isLeaf");
        Object node = null;
        MainTreeModel instance = new MainTreeModel();
        boolean expResult = false;
        boolean result = instance.isLeaf(node);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueForPathChanged method, of class MainTreeModel.
     */
    @Test
    public void testValueForPathChanged() {
        System.out.println("valueForPathChanged");
        TreePath path = null;
        Object newValue = null;
        MainTreeModel instance = new MainTreeModel();
        instance.valueForPathChanged(path, newValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndexOfChild method, of class MainTreeModel.
     */
    @Test
    public void testGetIndexOfChild() {
        System.out.println("getIndexOfChild");
        Object parent = null;
        Object child = null;
        MainTreeModel instance = new MainTreeModel();
        int expResult = 0;
        int result = instance.getIndexOfChild(parent, child);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTreeModelListener method, of class MainTreeModel.
     */
    @Test
    public void testAddTreeModelListener() {
        System.out.println("addTreeModelListener");
        TreeModelListener l = null;
        MainTreeModel instance = new MainTreeModel();
        instance.addTreeModelListener(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTreeModelListener method, of class MainTreeModel.
     */
    @Test
    public void testRemoveTreeModelListener() {
        System.out.println("removeTreeModelListener");
        TreeModelListener l = null;
        MainTreeModel instance = new MainTreeModel();
        instance.removeTreeModelListener(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTreeCellRendererComponent method, of class MainTreeModel.
     */
    @Test
    public void testGetTreeCellRendererComponent() {
        System.out.println("getTreeCellRendererComponent");
        JTree tree = null;
        Object value = null;
        boolean selected = false;
        boolean expanded = false;
        boolean leaf = false;
        int row = 0;
        boolean hasFocus = false;
        MainTreeModel instance = new MainTreeModel();
        Component expResult = null;
        Component result = instance.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParams method, of class MainTreeModel.
     */
    @Test
    public void testGetParams() {
        System.out.println("getParams");
        MainTreeModel instance = new MainTreeModel();
        DefaultMutableTreeNode expResult = null;
        DefaultMutableTreeNode result = instance.getParams();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setParams method, of class MainTreeModel.
     */
    @Test
    public void testSetParams() {
        System.out.println("setParams");
        DefaultMutableTreeNode mParams = null;
        MainTreeModel instance = new MainTreeModel();
        instance.setParams(mParams);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
