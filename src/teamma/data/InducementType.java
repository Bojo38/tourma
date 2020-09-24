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
public class InducementType {
    private String _name;
    private int _cost;
    private int _nbMax;

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public int getCost() {
        return _cost;
    }

    public void setCost(int _cost) {
        this._cost = _cost;
    }

    public int getNbMax() {
        return _nbMax;
    }

    public void setNbMax(int _nbMax) {
        this._nbMax = _nbMax;
    }
}
