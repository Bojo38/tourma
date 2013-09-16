/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;


import java.util.Properties;

/**
 *
 * @author root.106572700130
 */
public class Version {

    private static Version mSingleton = null;
    private Properties mData;

    private Version() {
        mData = new Properties();
        try {
            mData.load(getClass().getResourceAsStream(java.util.ResourceBundle.getBundle("tourma/version").getString("/TOURMA/VERSION.PROPERTIES")));
        } catch (Exception e) {
        }
    }

    public static Version getSingleton() {
        if (mSingleton == null) {
            mSingleton = new Version();
        }
        return mSingleton;
    }

    public String getProperty(final String key) {
        return mData.getProperty(key);
    }
}
