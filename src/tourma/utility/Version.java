/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root.106572700130
 */
public class Version {

    private static Version mSingleton = null;
    private static final Object myLock = new Object();

    private static final Logger LOG = Logger.getLogger(Version.class.getName());

    /**
     *
     * @return
     */
    public static Version getSingleton() {
        synchronized (myLock) {
            if (mSingleton == null) {
                mSingleton = new Version();
            }
        }
        return mSingleton;
    }
    private Properties mData;

    private final static String CS_VERSION_RESOURCES="tourma/version";
    private final static String CS_VERSION_KEY="/TOURMA/VERSION.PROPERTIES";
    
    private Version() {
        mData = new Properties();
        InputStream is=null;
        try {
            is=getClass().getResourceAsStream(java.util.ResourceBundle.getBundle(CS_VERSION_RESOURCES).getString(CS_VERSION_KEY));
            mData.load(is);
        } catch (IOException e) {
            LOG.log(Level.FINE,e.getLocalizedMessage());
        }
        finally {
            if (is!=null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                       LOG.log(Level.FINE,e.getLocalizedMessage());
                }
            }
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public String getProperty(final String key) {
        return mData.getProperty(key);
    }
}
