/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import tourma.MainFrame;

/**
 *
 * @author WFMJ7631
 */
public abstract class Match implements XMLExport {
    public Competitor mCompetitor1;
    public Competitor mCompetitor2;
    
    public Round mRound;

    protected Competitor mWinner = null;
    protected Competitor mLooser = null;

    public Match(Round round) {
        mRound = round;
    }
   
    public abstract Competitor getWinner();
    public abstract Competitor  getLooser();

    public void resetWL() {
        mWinner = null;
        mLooser = null;
    }

}
