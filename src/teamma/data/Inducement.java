/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

/**
 *
 * @author WFMJ7631
 */
public class Inducement {

    public InducementType getType() {
        return _type;
    }

    public void setType(InducementType _type) {
        this._type = _type;
    }

    public int getNb() {
        return _nb;
    }

    public void setNb(int _nb) {
        this._nb = _nb;
    }
    
    InducementType _type;
    int _nb;
}
