/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.awt.Component;
import java.io.File;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Tournament;
import tourma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public class MainTreeModelNGTest {

    public MainTreeModelNGTest() {
    }

    MainTreeModel mtm;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/team.xml"));
        mtm = new MainTreeModel();

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
        Object result = mtm.getRoot();
        assertTrue(result instanceof DefaultMutableTreeNode);
        Object userObject = ((DefaultMutableTreeNode) result).getUserObject();
        assertEquals((String) userObject, Tournament.getTournament().getParams().getTournamentName());
    }

    /**
     * Test of getChild method, of class MainTreeModel.
     */
    @Test
    public void testGetChild() {
        System.out.println("getChild");
        int nbRounds = Tournament.getTournament().getRoundsCount();
        for (int i = 0; i < mtm.getChildCount(mtm.getRoot()); i++) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) mtm.getChild(mtm.getRoot(), i);
            if (i == 0) {
                assertEquals(node.getUserObject(), Tournament.getTournament().getParams());
            } else {
                if (i == 1) {
                    assertEquals(node.getUserObject(), Translate.translate("STATISTIQUES"));
                } else {
                    if (i < Tournament.getTournament().getRoundsCount() + 2) {
                        assertEquals(node.getUserObject(), Tournament.getTournament().getRound(i-2));
                    }
                    else
                    {
                        assertEquals(node.getUserObject(), Translate.translate("CUP"));
                    }
                }
            }
        }
    }

    /**
     * Test of getChildCount method, of class MainTreeModel.
     */
    @Test
    public void testGetChildCount() {
        System.out.println("getChildCount");
        int expResult = 2+Tournament.getTournament().getRoundsCount();
        if (Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount()-1).isCup())
        {
            expResult++;
        }
        int result = mtm.getChildCount(mtm.getRoot());
        assertEquals(result, expResult);
    }

    /**
     * Test of isLeaf method, of class MainTreeModel.
     */
    @Test
    public void testIsLeaf() {
        System.out.println("isLeaf");

        Assert.assertFalse(mtm.isLeaf(mtm.getRoot()));
        
        for (int i = 0; i < mtm.getChildCount(i); i++) {
            Assert.assertTrue(mtm.isLeaf(mtm.getChild(mtm.getRoot(), i)));
        }}
        

    /**
     * Test of valueForPathChanged method, of class MainTreeModel.
     */
    @Test(enabled=false)
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
        
        for (int i = 0; i < mtm.getChildCount(i); i++) {
            Assert.assertEquals(mtm.getIndexOfChild(mtm.getRoot(),
                    mtm.getChild(mtm.getRoot(), i)),i);
        }

    }

    /**
     * Test of addTreeModelListener method, of class MainTreeModel.
     */
    @Test(enabled=false)
    public void testAddTreeModelListener() {
        System.out.println("addTreeModelListener");
        TreeModelListener l =null;
        MainTreeModel instance = new MainTreeModel();
        instance.addTreeModelListener(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTreeModelListener method, of class MainTreeModel.
     */
    @Test(enabled=false)
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
    @Test(enabled=false)
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
        DefaultMutableTreeNode mParams = new DefaultMutableTreeNode(Tournament.getTournament().getParams());
        MainTreeModel instance = new MainTreeModel();
        instance.setParams(mParams);
        assertEquals(instance.getParams(), mParams);
    }

    /**
     * Test of setParams method, of class MainTreeModel.
     */
    @Test
    public void testSetParams() {
        System.out.println("setParams");
        DefaultMutableTreeNode mParams = new DefaultMutableTreeNode(Tournament.getTournament().getParams());
        MainTreeModel instance = new MainTreeModel();
        instance.setParams(mParams);
        assertEquals(instance.getParams(), mParams);
    }

}
