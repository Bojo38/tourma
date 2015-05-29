/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.languages;

import java.util.ResourceBundle;

/**
 *
 * @author WFMJ7631
 */
public class Translate {

    public static final String CS_Touchdowns = "Touchdowns";
    public static final String CS_Injuries = "Injuries";
    public final static String CS_None = "None";
    public final static String CS_Parameters="Parameters";
    public final static String CS_Round_="ROUND ";

    private static ResourceBundle sBundle = null;

    public static String translate(String key) {
        if (sBundle == null) {
            sBundle = java.util.ResourceBundle.getBundle("tourma/languages/language");
        }
        return sBundle.getString(key);
    }
}
