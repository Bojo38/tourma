/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.Vector;

/**
 *
 * @author Frederic Berger
 */
public class Team implements Comparable {

    public Vector<Coach> _coachs;
    public String _name;

    public Team() {
        _coachs = new Vector();
    }

    public int compareTo(Object obj) {
        if (obj instanceof Coach) {
            return _name.compareTo(((Coach) obj)._name);
        } else {
            return -1;
        }
    }

    public int getActivePlayerNumber() {
        int nb = 0;

        for (int i = 0; i < _coachs.size(); i++) {
            if (_coachs.get(i)._active) {
                nb++;
            }
        }
        return nb;
    }

    public Vector<Coach> getActivePlayers() {
        Vector<Coach> v = new Vector<Coach>();

        for (int i = 0; i < _coachs.size(); i++) {
            if (_coachs.get(i)._active) {
                v.add(_coachs.get(i));
            }
        }
        return v;
    }
}
