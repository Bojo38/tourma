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
import tourma.utils.Icons;

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
    public DefaultMutableTreeNode mStats;
    ArrayList<DefaultMutableTreeNode> mRounds;

    mainTreeModel() {
        mListeners = new ArrayList<>();
        mTournament = Tournament.getTournament();
        mRounds = new ArrayList<>();

        String name = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TOURNOI");
        if (!mTournament.getParams().mTournamentName.equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""))) {
            name = mTournament.getParams().mTournamentName;
        }

        mRoot = new DefaultMutableTreeNode(name, true);
        mParams = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PARAMÈTRES"));
        mStats = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("STATISTIQUES"));

        mRoot.add(mParams);
        mParams.setUserObject(mTournament.getParams());

        mRoot.add(mStats);
        mStats.setUserObject(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("STATISTICS"));

        boolean cup = false;
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            final Round r = mTournament.getRounds().get(i);
            if (r.mCup) {
                cup = true;
            }
            final String tmp= java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RONDE {0}");
            final DefaultMutableTreeNode node = new DefaultMutableTreeNode(
                    java.text.MessageFormat.format(tmp,  i+1));
            mRoot.add(node);
            node.setUserObject(r);
            mRounds.add(node);
        }

        if (cup) {
            mCup = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COUPE"));
            mRoot.add(mCup);
            mCup.setUserObject(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CUP"));
        }


    }

    @Override
    public Object getRoot() {
        return mRoot;
    }

    @Override
    public Object getChild(final Object parent, final int index) {
        Object o = null;
        if (parent.equals(mRoot)) {
            o = mRoot.getChildAt(index);
        }
        return o;
    }

    @Override
    public int getChildCount(final Object parent) {
        int count = 0;
        if (parent.equals(mRoot)) {
            count = mRoot.getChildCount();
        }
        return count;
    }

    @Override
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

    @Override
    public void valueForPathChanged(final TreePath path, Object newValue) {
        // Nothing to do
    }

    @Override
    public int getIndexOfChild(final Object parent, final Object child) {
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

    @Override
    public void addTreeModelListener(final TreeModelListener l) {
        mListeners.add(l);
    }

    @Override
    public void removeTreeModelListener(final TreeModelListener l) {
        mListeners.remove(l);
    }

    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean selected, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
        final JLabel jlb = new JLabel(value.toString());
        if (selected) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            jlb.setSize(jlb.getWidth() + 100, jlb.getHeight());
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
        if (value == mCup) {
            jlb.setIcon(Icons.getStar());
        }

        if (value == mStats) {
            jlb.setIcon(Icons.getStats());
        }



        return jlb;
    }
}
