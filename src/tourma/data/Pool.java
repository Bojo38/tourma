/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import org.jdom.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Pool implements IXMLExport, Serializable {

        protected static AtomicInteger sGenUID=new AtomicInteger(0);
    protected int UID=sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    private static final Logger LOG = Logger.getLogger(Pool.class.getName());

    /**
     *
     */
    private final ArrayList<Competitor> mCompetitors = new ArrayList<>();

    /**
     *
     */
    private String mName = "";

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element pool = new Element(StringConstants.CS_POOL);
        pool.setAttribute(StringConstants.CS_NAME, this.getName());
        for (Competitor mCompetitor : mCompetitors) {
            final Element coach = new Element(StringConstants.CS_COACH);
            coach.setAttribute(StringConstants.CS_NAME, mCompetitor.getName());
            pool.addContent(coach);
        }
        return pool;
    }

    /**
     *
     * @param pool
     */
    @Override
    public void setXMLElement(final Element pool) {
        setName(pool.getAttributeValue(StringConstants.CS_NAME));

        mCompetitors.clear();
        final List<Element> coachs = pool.getChildren(StringConstants.CS_COACH);
        Iterator<Element> ro = coachs.iterator();
        while (ro.hasNext()) {
            final Element competitor = ro.next();
            final String name = competitor.getAttributeValue(StringConstants.CS_NAME);
            Competitor c = Coach.getCoach(name);
            if (c == null) {
                c = Team.getTeam(name);
            }
            mCompetitors.add(c);
        }
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * @param i
     * @return the mCompetitors
     */
    public Competitor getCompetitor(int i) {
        return mCompetitors.get(i);
    }

    /**
     * @param c
     */
    public void addCompetitor(Competitor c) {
        mCompetitors.add(c);
    }

    /**
     * @return the mCompetitors
     */
    public int getCompetitorCount() {
        return mCompetitors.size();
    }

    /**
     * @return the mCompetitors
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public ArrayList<Competitor> getCompetitors() {
        return mCompetitors;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(final Object obj) {

        boolean result;
        result = false;
        if (obj instanceof Pool) {
            Pool p = (Pool) obj;
            result = this.getName().equals(p.getName());
            result &= this.getCompetitorCount() == p.getCompetitorCount();
            for (Competitor c : mCompetitors) {
                result &= p.getCompetitors().contains(c);
            }
        }
        return result;
    }
}
