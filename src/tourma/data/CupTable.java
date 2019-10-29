/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class CupTable implements IXMLExport {

    public ArrayList<CupRound> getCupRounds() {
        return mCupRounds;
    }

    public void setCupRounds(ArrayList<CupRound> mCupRounds) {
        this.mCupRounds = mCupRounds;
    }

    ArrayList<CupRound> mCupRounds = new ArrayList<>();

    public CupTable(int cupRoundsCount, boolean third_place, boolean looser_table) {
        for (int i = 0; i < cupRoundsCount; i++) {
            int nbMatchs = (int) Math.round(Math.pow(2, cupRoundsCount - i - 1));

            if ((i == cupRoundsCount - 1) && (third_place)) {
                mCupRounds.add(new CupRound(nbMatchs + 1));
            } else {
                if (looser_table)
                {
                    if (i!=0)
                    {
                        nbMatchs+=nbMatchs;
                    }
                }                
                mCupRounds.add(new CupRound(nbMatchs));
            }
        }
    }

    @Override
    public Element getXMLElement() {

        final Element table = new Element(StringConstants.CS_CUP_TABLE);

        this.mCupRounds.stream().map((mRound) -> mRound.getXMLElement()).forEachOrdered((round) -> {
            table.addContent(round);
        });
        return table;
    }

    @Override
    public void setXMLElement(Element e) {
        List<Element> tables = e.getChildren(StringConstants.CS_CUP_ROUND);
        final Iterator<Element> k = tables.iterator();
        this.mCupRounds = new ArrayList<>();

        while (k.hasNext()) {
            Element r = k.next();
            try {
                CupRound round = new CupRound();
                round.setXMLElement(r);
                this.mCupRounds.add(round);
            } catch (NullPointerException ne) {
            }
        }
    }
}
