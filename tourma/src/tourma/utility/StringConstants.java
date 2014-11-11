/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;

import java.util.logging.Logger;

/**
 *
 * @author WFMJ7631
 */
public class StringConstants {

    /**
     *
     */
    public static final String CS_ROSTER = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ROSTER");

    /**
     *
     */
    public static final String CS_NAME = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NAME");

    /**
     *
     */
    public static final String CS_COACH =java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("COACH");

    /**
     *
     */
    public static final String CS_TEAM =java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("TEAM");

    /**
     *
     */
    public static final String CS_CLAN =java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("CLAN");

    /**
     *
     */
    public static final String CS_POOL =java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("POULE");

    /**
     *
     */
    public static final String CS_ROUND =java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ROUND");

    /**
     *
     */
    public static final String CS_RESULT =java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("RESULT");

    /**
     *
     */
    public static final String CS_CUP =java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("COUPE");

    /**
     *
     */
    public static final String CS_CATEGORY="Category";

    /**
     *
     */
    public static final String CS_THICK=" - ";

    /**
     *
     */
    public static final String CS_HTML_EMPTY="&nbsp;";
    
    /**
     *
     */
    public static final String CS_XML="XML";

    /**
     *
     */
    public static final String CS_MINXML="xml";

    /**
     *
     */
    public static final String CS_NONE=java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("AUCUN");

    /**
     *
     */
    public static final String CS_POINTS=java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("POINTS");

    /**
     *
     */
    public static final String CS_GEN_ERROR=java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ERREUR DE GÉNÉRATION");
    
    /**
     *
     */
    public static final String CS_LANGUAGE_RESOURCE="tourma/languages/language";
    private static final Logger LOG = Logger.getLogger(StringConstants.class.getName());
    
   
}
