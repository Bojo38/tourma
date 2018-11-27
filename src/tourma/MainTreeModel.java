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
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.Icons;

/**
 *
 * @author WFMJ7631
 */
public class MainTreeModel implements TreeModel, TreeCellRenderer {

    /**
     *
     */
    private ArrayList<TreeModelListener> mListeners;
    private Tournament mTournament;
    private DefaultMutableTreeNode mRoot;
    /**
     *
     */
    private DefaultMutableTreeNode mParams;
    private DefaultMutableTreeNode mCup;
    /**
     *
     */
    private DefaultMutableTreeNode mStats;
    private ArrayList<DefaultMutableTreeNode> mRounds;

    public final static String CS_Tournament = "TOURNOI";
    public final static String CS_Statistics = "STATISTIQUES";
    public final static String CS_Parameters = "PARAMÈTRES";
    public final static String CS_Round = "Round";
    public final static String CS_Cup = "Cup";

    MainTreeModel() {
        mListeners = new ArrayList<>();

            mTournament = Tournament.getTournament();
            mRounds = new ArrayList<>();

            String name = Translate.translate(CS_Tournament);
            if (!mTournament.getParams().getTournamentName().equals(StringConstants.CS_NULL)) {
                name = mTournament.getParams().getTournamentName();
            }

            mRoot = new DefaultMutableTreeNode(name, true);
            mParams = new DefaultMutableTreeNode(
                    Translate.translate(CS_Parameters));
            mStats = new DefaultMutableTreeNode(
                    Translate.translate(CS_Statistics));

            mRoot.add(mParams);
            mParams.setUserObject(mTournament.getParams());

            mRoot.add(mStats);
            mStats.setUserObject(Translate.translate(CS_Statistics));

            boolean cup = false;
            for (int i = 0; i < mTournament.getRoundsCount(); i++) {
                final Round r = mTournament.getRound(i);
                if (r.isCup()) {
                    cup = true;
                }
                final String tmp = Translate.translate(CS_Round);
                final DefaultMutableTreeNode node = new DefaultMutableTreeNode(
                        tmp + " " + (i + 1));
                mRoot.add(node);
                node.setUserObject(r);
                mRounds.add(node);
            }

            if (cup) {
                mCup = new DefaultMutableTreeNode(
                        Translate.translate(CS_Cup));
                mRoot.add(mCup);
                mCup.setUserObject(Translate.translate(CS_Cup));
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

        if (value == getParams()) {
            jlb.setIcon(Icons.getParams());
        }
        for (DefaultMutableTreeNode mRound : mRounds) {
            if (value == mRound) {
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

    /**
     * @return the mParams
     */
    public DefaultMutableTreeNode getParams() {
        return mParams;
    }

    /**
     * @param mParams the mParams to set
     */
    public void setParams(DefaultMutableTreeNode mParams) {
        this.mParams = mParams;
    }
}
