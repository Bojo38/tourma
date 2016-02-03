/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.languages;

import java.util.ResourceBundle;

/**
 *
 * @author WFMJ7631
 */
@SuppressWarnings({"ClassWithoutLogger", "UtilityClassWithoutPrivateConstructor"})
public class Translate {

    private static ResourceBundle sBundle = null;

    public static String translate(String key) {
        if (sBundle == null) {
            sBundle = java.util.ResourceBundle.getBundle("teamma/languages/language");
        }
        String tmp="";
        
        try {
            tmp=sBundle.getString(key);
        }
        catch(Exception e)
        {
            tmp="?"+key;
        }
        return tmp;
    }
}
