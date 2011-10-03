/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

import java.util.HashMap;
import tourma.data.Coach;

/**
 *
 * @author Frederic Berger
 */
public class Match {
    public Coach _coach1;
    public Coach _coach2;

    public HashMap<Criteria,Value> _values;

    public Match()
    {
        _values=new HashMap<Criteria,Value>();

        int size=Tournament.getTournament()._params._criterias.size();
        for (int i=0; i<size; i++)
        {
            Criteria crit=Tournament.getTournament()._params._criterias.get(i);
            Value val=new Value(crit);
            if (i==0)
            {
                val._value1=-1;
                val._value2=-1;
            }
            else
            {
                val._value1=0;
                val._value2=0;
            }
            _values.put(crit, val);
        }
    }
}
