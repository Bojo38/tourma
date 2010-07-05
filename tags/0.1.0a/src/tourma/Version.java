/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;


import java.util.Properties;

/**
 *
 * @author root.106572700130
 */
public class Version {

    private static Version _singleton = null;
    private Properties _data;

    private Version() {
        _data = new Properties();
        try {
            _data.load(getClass().getResourceAsStream("/tourma/version.properties"));
            return;
        } catch (Exception e) {
        }
    }

    public static Version getSingleton() {
        if (_singleton == null) {
            _singleton = new Version();
        }
        return _singleton;
    }

    public String getProperty(String key) {
        return _data.getProperty(key);
    }
}
