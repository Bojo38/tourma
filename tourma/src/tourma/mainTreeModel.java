/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.images.Icons;

/**
 *
 * @author WFMJ7631
 */
public class mainTreeModel implements TreeModel, TreeCellRenderer {

    public ArrayList<TreeModelListener> mListeners;
    Tournament mTournament;
    DefaultMutableTreeNode mRoot;
    public DefaultMutableTreeNode mParams;
    DefaultMutableTreeNode mCup;
    ArrayList<DefaultMutableTreeNode> mRounds;

    mainTreeModel() {
        mListeners = new ArrayList<TreeModelListener>();
        mTournament = Tournament.getTournament();
        mRounds = new ArrayList<DefaultMutableTreeNode>();

        String name = "Tournoi";
        if (!mTournament.getParams().mTournamentName.equals("")) {
            name = mTournament.getParams().mTournamentName;
        }

        mRoot = new DefaultMutableTreeNode(name, true);
        mParams = new DefaultMutableTreeNode("Paramètres");

        mRoot.add(mParams);
        mParams.setUserObject(mTournament.getParams());

        boolean cup = false;
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            final Round r = mTournament.getRounds().get(i);
            if (r.mCup) {
                cup = true;
            }
            final DefaultMutableTreeNode node = new DefaultMutableTreeNode("Ronde " + (i + 1));
            mRoot.add(node);
            node.setUserObject(r);
            mRounds.add(node);
        }

        if (cup) {
            mCup = new DefaultMutableTreeNode("Coupe");
            mRoot.add(mCup);
            mCup.setUserObject("Cup");
        }


    }

    public Object getRoot() {
        return mRoot;
    }

    public Object getChild(final Object parent, final int index) {
        Object o = null;
        if (parent.equals(mRoot)) {
            o = mRoot.getChildAt(index);
        }
        return o;
    }

    public int getChildCount(final Object parent) {
        int count = 0;
        if (parent.equals(mRoot)) {
            count = mRoot.getChildCount();
        }
        return count;
    }

    public boolean isLeaf(final Object node) {
        boolean isLeaf = false;
        if (node.equals(mRoot)) {
            isLeaf = false;
        }
        for (int i = 0; i < mRoot.getChildCount(); i++) {
            if (node.equals(mRoot.getChildAt(i))) {
                isLeaf = true;
            }
        }
        return isLeaf;
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
        // Nothing to do
    }

    public int getIndexOfChild(Object parent, Object child) {
        int index = -1;
        if (parent.equals(mRoot)) {
            for (int i = 0; i < mRoot.getChildCount(); i++) {
                if (child.equals(mRoot.getChildAt(i))) {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    public void addTreeModelListener(final TreeModelListener l) {
        mListeners.add(l);
    }

    public void removeTreeModelListener(final TreeModelListener l) {
        mListeners.remove(l);
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        final JLabel jlb = new JLabel(value.toString());
        if (selected)
        {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            jlb.setSize(jlb.getWidth()+100,jlb.getHeight());
            jlb.revalidate();
        }
        
        if (value == mParams) {
            jlb.setIcon(Icons.getParams());
        }
        for (int i = 0; i < mRounds.size(); i++) {
            if (value == mRounds.get(i)) {
                jlb.setIcon(Icons.getDices());
            }
        }
        if (value==mCup) {
            jlb.setIcon(Icons.getStar());
        }
        
        
        
        return jlb;
    }
}
