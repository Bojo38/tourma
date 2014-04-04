/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public abstract class Competitor implements Comparable {

    public String mName;
    public Color mColor;
    public ArrayList<Match> mMatchs;

    protected Color generateRandomColor(final Color mix) {
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

    public Competitor() {
        mColor = generateRandomColor(Color.WHITE);
                mMatchs = new ArrayList<>();
    }

    public Competitor(final String name) {
        mName = name;
        if (mName.equals(StringConstants.CS_NONE)) {
            mColor = Color.GRAY;
        } else {
            mColor = generateRandomColor(Color.WHITE);
        }
                mMatchs = new ArrayList<>();
    }

    public String getName() {
        return mName;
    }

    public abstract void AddMatch(Competitor opponent, Round r);

    public abstract void AddMatchRoundRobin(Competitor opponent, Round r);

    public abstract boolean havePlayed(Competitor opponent);

    public abstract ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents, Round r);

    public abstract String getDecoratedName();

    public abstract void RoundCheck(Round r);

    public abstract HashMap<Team, Integer> getTeamOppositionCount(ArrayList<Team> teams,Round current);    
    @Override
    public String toString() {
        return getName();
    }
}
