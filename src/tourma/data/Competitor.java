/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public abstract class Competitor implements Comparable<Object>, IWithNameAndPicture {

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
        if (c instanceof Competitor) {
            Competitor comp = (Competitor) c;
            return getName().equals(comp.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
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

    public boolean containsCategory(Category cat) {
        return mCategories.contains(cat);
    }

    public int getCategoryCount() {
        return mCategories.size();
    }

    public Category getCategory(int i) {
        if (i < mCategories.size()) {
            return mCategories.get(i);
        } else {
            return null;
        }
    }

    /**
     * @param mCategory the mCategory to set
     */
    public void addCategory(Category mCategory) {
        mCategories.add(mCategory);
    }

    public void delCategory(Category mCategory) {
        mCategories.remove(mCategory);
    }

    public void clearCategory() {
        mCategories.clear();
    }

    /**
     *
     * @param opponent
     * @param r
     */
    public abstract void addMatch(Competitor opponent, Round r);

    /**
     *
     * @param opponent
     * @param r
     */
    public abstract void addMatchRoundRobin(Competitor opponent, Round r,boolean complete);

    /**
     *
     * @param opponent
     * @return
     */
    public abstract boolean havePlayed(Competitor opponent);

    /**
     *
     * @param opponents
     * @param r
     * @return
     */
    public abstract ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents, Round r);

    /**
     *
     * @return
     */
    public abstract String getDecoratedName();

    /**
     *
     * @param r
     */
    public abstract void roundCheck(Round r);

    /**
     *
     * @param teams
     * @param current
     * @return
     */
    public abstract HashMap<Team, Integer> getTeamOppositionCount(ArrayList<Team> teams, Round current);

    @Override
    public String toString() {
        return getName();
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
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
    public void setName(String mName) {
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
    public void setPicture(BufferedImage picture) {
        this.picture = picture;
    }

    /**
     * @return the mClan
     */
    public Clan getClan() {
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

    public boolean containsMatch(Match m)
    {
        return mMatchs.contains(m);
    }
    
}
