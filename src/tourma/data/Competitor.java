/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JOptionPane;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public abstract class Competitor implements Comparable<Object>, IWithNameAndPicture, Serializable {

    /**
     *
     * @param mix
     * @return
     */
    public static Color generateRandomColor(final Color mix) {
        final Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        // mix the color
        if (mix != null) {
            red = (red + mix.getRed()) / 2;
            green = (green + mix.getGreen()) / 2;
            blue = (blue + mix.getBlue()) / 2;
        }

        return new Color(red, green, blue);
    }

    @Override
    public boolean equals(Object c) {
        try {
            if (c instanceof Competitor) {
                Competitor comp = (Competitor) c;
                return getName().equals(comp.getName());
            }
            return false;
        } catch (RemoteException re) {
            JOptionPane.showMessageDialog(null, re.getLocalizedMessage() );
        }
        return false;
    }

    public String getRawName() {
        return mName;
    }

    @Override
    public int hashCode() {
        try {
            return getName().hashCode();
        } catch (RemoteException re) {
            JOptionPane.showMessageDialog(null, re.getLocalizedMessage());
        }
        return 0;
    }

    /**
     *
     */
    private final ArrayList<Category> mCategories = new ArrayList<>();

    /**
     *
     */
    private String mName;

    /**
     *
     */
    private Color mColor;

    /**
     *
     */
    protected ArrayList<Match> mMatchs = new ArrayList<>();

    /**
     *
     */
    private BufferedImage picture = null;
    /**
     * Clan
     */
    private Clan mClan;

    /**
     * Default constructor
     */
    public Competitor() {
        mColor = generateRandomColor(Color.WHITE);
        mMatchs = new ArrayList<>();
    }

    /**
     *
     * @param name
     */
    public Competitor(final String name) {
        mName = name;
        if (mName.equals(StringConstants.CS_NONE)) {
            mColor = Color.GRAY;
        } else {
            mColor = generateRandomColor(Color.WHITE);
        }
        mMatchs = new ArrayList<>();
    }

    public boolean containsCategory(Category cat) throws RemoteException {
        return mCategories.contains(cat);
    }

    public int getCategoryCount() throws RemoteException {
        return mCategories.size();
    }

    public Category getCategory(int i) throws RemoteException {
        if (i < mCategories.size()) {
            return mCategories.get(i);
        } else {
            return null;
        }
    }

    /**
     * @param mCategory the mCategory to set
     */
    public void addCategory(Category mCategory) throws RemoteException {
        mCategories.add(mCategory);
    }

    public void delCategory(Category mCategory) throws RemoteException {
        mCategories.remove(mCategory);
    }

    public void clearCategory() throws RemoteException {
        mCategories.clear();
    }

    /**
     *
     * @param opponent
     * @param r
     */
    public abstract void addMatch(Competitor opponent, Round r) throws RemoteException;

    /**
     *
     * @param opponent
     * @param r
     */
    public abstract void addMatchRoundRobin(Competitor opponent, Round r, boolean complete) throws RemoteException;

    /**
     *
     * @param opponent
     * @return
     */
    public abstract boolean havePlayed(Competitor opponent) throws RemoteException;

    /**
     *
     * @param opponents
     * @param r
     * @return
     */
    public abstract ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents, Round r) throws RemoteException;

    /**
     *
     * @return
     */
    public abstract String getDecoratedName() throws RemoteException;

    /**
     *
     * @param r
     */
    public abstract void roundCheck(Round r) throws RemoteException;

    /**
     *
     * @param teams
     * @param current
     * @return
     */
    public abstract HashMap<Team, Integer> getTeamOppositionCount(ArrayList<Team> teams, Round current) throws RemoteException;

    @Override
    public String toString() {
        try {
            return getName();
        } catch (RemoteException re) {
            JOptionPane.showMessageDialog(null, re.getLocalizedMessage());
        }
        return "";
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() throws RemoteException {
        return mName;
    }

    /**
     *
     * @return
     */
    @Override
    public BufferedImage getPicture() {
        return picture;
    }

    /**
     * @param mName the mName to set
     */
    @Override
    public void setName(String mName) throws RemoteException {
        this.mName = mName;
    }

    /**
     * @return the mColor
     */
    public Color getColor() {
        return mColor;
    }

    /**
     * @param mColor the mColor to set
     */
    public void setColor(Color mColor) {
        this.mColor = mColor;
    }

    /**
     * @param picture the picture to set
     */
    @Override
    public void setPicture(BufferedImage picture) throws RemoteException {
        this.picture = picture;
    }

    /**
     * @return the mClan
     */
    public Clan getClan() throws RemoteException {
        return mClan;
    }

    /**
     * @param mClan the mClan to set
     */
    public void setClan(Clan mClan) {
        this.mClan = mClan;
    }

    /**
     * @param i
     * @return the mMatchs
     */
    public Match getMatch(int i) {
        return mMatchs.get(i);
    }

    /**
     *
     * @return
     */
    public int getMatchCount() {
        return mMatchs.size();
    }

    /**
     *
     * @param m
     */
    public void addMatch(Match m) {
        mMatchs.add(m);
    }

    /**
     *
     * @param m
     */
    public void removeMatch(Match m) {
        if (mMatchs.contains(m)) {
            mMatchs.remove(m);
        }
    }

    /**
     *
     * @return
     */
    public boolean isMatchsNotNull() {
        return mMatchs != null;
    }

    /**
     * New match arrays
     */
    public void newMatchs() {
        this.mMatchs = new ArrayList<>();
    }

    /**
     *
     * @param m
     * @return
     */
    public int matchIndex(Match m) {
        return mMatchs.indexOf(m);
    }

    /**
     * clear the matchs array
     */
    public void clearMatchs() {
        mMatchs.clear();
    }

    public boolean containsMatch(Match m) {
        return mMatchs.contains(m);
    }

}
