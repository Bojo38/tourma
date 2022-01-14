/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Administrateur
 */
public class Value implements Serializable {

    protected static AtomicInteger sGenUID = new AtomicInteger(0);
    protected int UID = sGenUID.incrementAndGet();

    protected LocalDateTime createDateTime;

    protected LocalDateTime updateDateTime;

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }
    protected boolean updated = false;

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public void pull(Value value) {
        this.UID = value.UID;
        this.mValue1 = value.mValue1;
        this.mValue2 = value.mValue2;
    }

    public void push(Value value) {
        if (value.isUpdated()) {
            this.UID = value.UID;
            if (value.mValue1 > -1) {
                this.mValue1 = value.mValue1;
            }
            if (value.mValue2 > -1) {
                this.mValue2 = value.mValue2;
            }
        }
    }

    /**
     * Name of the criteria
     */
    private Criterion mCriteria;

    /**
     * Name of the criteria
     */
    private Formula mFormula;

    public enum e_ValueType {
        E_CRITERIA,
        E_FORMULA;
    };

    private e_ValueType _valueType;

    /**
     * Value for the coach 1
     */
    private int mValue1;
    /**
     * Value for the coach 2
     */
    private int mValue2;

    /**
     *
     * @param criteria
     */
    public Value(final Criterion criteria) {
        mCriteria = criteria;
        _valueType = e_ValueType.E_CRITERIA;
    }

    public Value(final Formula form) {
        mFormula = form;
        _valueType = e_ValueType.E_FORMULA;
    }

    /**
     * @return the mCriteria
     */
    public Criterion getCriteria() {
        if (_valueType == e_ValueType.E_CRITERIA) {
            return mCriteria;
        } else {
            return null;
        }
    }

    public Formula getFormula() {
        if (_valueType == e_ValueType.E_FORMULA) {
            return mFormula;
        } else {
            return null;
        }
    }

    /**
     * @param mCriteria the mCriteria to set
     */
    public void setCriteria(Criterion mCriteria) {
        this.mCriteria = mCriteria;
        updated = true;
    }

    /**
     * @return the mValue1
     */
    public int getValue1() {
        return mValue1;
    }

    /**
     * @param mValue1 the mValue1 to set
     */
    public void setValue1(int mValue1) {
        updated = true;
        this.mValue1 = mValue1;

    }

    /**
     * @return the mValue2
     */
    public int getValue2() {

        return mValue2;
    }

    /**
     * @param mValue2 the mValue2 to set
     */
    public void setValue2(int mValue2) {
        updated = true;
        this.mValue2 = mValue2;
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        if (createDateTime == null) {
            createDateTime = LocalDateTime.now();
        }
        if (updateDateTime == null) {
            updateDateTime = LocalDateTime.now();
        }

        json.put("createDateTime", createDateTime.toString());
        json.put("updateDateTime", updateDateTime.toString());

        json.put("updated", this.isUpdated());
        json.put("value1", this.getValue1());
        json.put("value2", this.getValue2());
        json.put("criterionName", mCriteria != null ? mCriteria.getName() : null);
        json.put("formulaName", mFormula != null ? mFormula.getName() : null);

        return json;
    }

    public void updateFromJSON(JSONObject object) {
        Object obj = object.get("createDateTime");
        if (obj != JSONObject.NULL) {
            createDateTime = LocalDateTime.parse(object.get("createDateTime").toString());
        }
        obj = object.get("updateDateTime");
        if (obj != JSONObject.NULL) {
            updateDateTime = LocalDateTime.parse(object.get("updateDateTime").toString());
        }

        this.updated = object.getBoolean("updated");

        this.mValue1 = object.getInt("value1");
        this.mValue2 = object.getInt("value2");

        Object tmpObj = object.get("criterionName");
        if (tmpObj != JSONObject.NULL) {
            String criterionName = object.getString("criterionName");
            Tournament tour=Tournament.getTournament();
            Criterion crit = tour.getParams().getCriterion(criterionName);
            if (!crit.equals(this.mCriteria)) {
                this.mCriteria = crit;
            }
        } else {
            tmpObj = object.get("formulaName");
            if (tmpObj != JSONObject.NULL) {
                String formulaName = object.getString("formulaName");
                Formula form = Tournament.getTournament().getParams().getFormula(formulaName);
                if (!form.equals(this.mFormula)) {
                    this.mFormula = form;
                }
            }
        }
    }
}
