/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import tourma.languages.Translate;
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

    
    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }
    protected boolean updated = false;

    public void pull(Competitor comp) {
        this.setName(comp.getName());
        this.setColor(comp.getColor());
        this.setPicture(comp.getPicture());
        if (comp.getClan() != null) {
            Clan clan = Clan.getClan(comp.getClan().getName());
            this.setClan(clan);
        }

        for (Category category : comp.mCategories) {
            // Find local instance of Category
            for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
                Category local = Tournament.getTournament().getCategory(i);
                if (local.getUID() == category.getUID()) {
                    // Test if category already contained
                    if (!mCategories.contains(local)) {
                        mCategories.add(local);
                    }
                }
            }
            if (comp.mCategories.size() != mCategories.size()) {
                mCategories.clear();
                pull(comp);
            }
        }

    }

    public void push(Competitor comp) {
        if (comp.isUpdated()) {
            this.setName(comp.getName());
            this.setColor(comp.getColor());
            this.setPicture(comp.getPicture());
            if (comp.getClan() != null) {
                Clan clan = Clan.getClan(comp.getClan().getName());
                this.setClan(clan);
            }

            for (Category category : comp.mCategories) {
                // Find local instance of Category
                for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
                    Category local = Tournament.getTournament().getCategory(i);
                    if (local.getUID() == category.getUID()) {
                        // Test if category already contained
                        if (!mCategories.contains(local)) {
                            mCategories.add(local);
                        }
                    }
                }
                if (comp.mCategories.size() != mCategories.size()) {
                    mCategories.clear();
                    pull(comp);
                }
            }
        }
    }

    @Override
    public boolean equals(Object c) {

        if (c instanceof Competitor) {
            Competitor comp = (Competitor) c;
            return getName().equals(comp.getName());
        }
        return false;

    }

    public String getRawName() {
        return mName;
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
    private ImageIcon picture = null;
    /**
     * Clan
     */
    private Clan mClan;

    protected boolean _naf_avg=false;
    
    /**
     * Default constructor
     */
    public Competitor() {
        mColor = generateRandomColor(Color.WHITE);
        mMatchs = new ArrayList<>();
        if (Tournament.getTournament().getClansCount()==0)
        {
            Tournament.getTournament().addClan(new Clan(Translate.translate(Translate.CS_None)));
        }
        mClan=Tournament.getTournament().getClan(0);
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
        updated=true;
    }

    public void delCategory(Category mCategory) {
        mCategories.remove(mCategory);
        updated=true;
    }

    public void clearCategory() {
        mCategories.clear();
        updated=true;
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
    public abstract void addMatchRoundRobin(Competitor opponent, Round r, boolean complete);

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
    public ImageIcon getPicture() {
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
        updated=true;
    }

    /**
     * @param picture the picture to set
     */
    @Override
    public void setPicture(ImageIcon picture) {
        this.picture = picture;
        updated=true;
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
        updated=true;
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
            updated=true;
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
        updated=true;
    }

    public boolean containsMatch(Match m) {
        return mMatchs.contains(m);
    }

    
    public void enableNafAvg(boolean avg)
    {
        _naf_avg=avg;
    }
}
