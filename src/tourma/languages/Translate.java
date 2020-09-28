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
@SuppressWarnings({"ClassWithoutLogger", "UtilityClassWithoutPrivateConstructor"})
public class Translate {

    public static final String CS_Touchdowns = "Touchdowns";
    public static final String CS_Injuries = "Injuries";
    public final static String CS_None = "None";
    public final static String CS_Pool = "Pool";
    public final static String CS_Cup = "Cup";
    public final static String CS_Parameters = "Parameters";
    public final static String CS_Round_ = "ROUND ";
    public final static String CS_Clan = "ClanKey";
    public final static String CS_Team = "Team";
    public final static String CS_Round = "Round";
    public final static String CS_Coach = "Coach";
    public final static String CS_Opponent = "Opponent";
    public final static String CS_Roster = "Roster";
    public final static String CS_RosterName = "RosterName";
    public final static String CS_Difference = "Difference";
    public final static String CS_NAF = "NAF";
    public final static String CS_Ranking = "Ranking";
    public final static String CS_Unknown = "Inconnu";
    public final static String CS_Active = "ACTIF";
    public final static String CS_Inactive = "INACTIF";
    public final static String CS_Critera_Name = "NOM CRITÈRE";
    public final static String CS_Critera_Accronym = "ACCRONYM";
    public final static String  CS_Formula_Name ="NOM FORMULE";
    public final static String  CS_Formula_Formula ="FORMULE";
    public final static String CS_Error = "ERROR";
    public final static String CS_Points_Plus = "POINTS +";
    public final static String CS_Points_Minus = "POINTS -";
    public final static String CS_Points_Team_Plus = "POINTS EQUIPE +";
    public final static String CS_Critical_Value_Threshold = "CRITICAL VALUE_THRESHOLD";
    public final static String CS_Points_Team_Minus = "POINTS EQUIPE -";
    public final static String CS_Table = "Table";
    public final static String CS_Score = "Score";
    public final static String CS_Refused = "REFUSED";
    public final static String CS_Conceeded = "CONCEEDED";
    public final static String CS_Points = "Points";
    public final static String CS_TotalPoints = "TotalPoints";
    public final static String CS_Points_Without_Bonus = "Points sans bonus";
    public final static String CS_Bonus_Points = "Bonus Points";
    public final static String CS_Nothing = "Nothing";
    public final static String CS_ELO = "ELO";
    public final static String CS_OpponentsElo = "OpponentsElo";
    public final static String CS_MatchCount = "MatchCount";
    public final static String CS_TablesPoints = "TablesPoints";
    public final static String CS_HeadByHead = "Head by Head";
    
    public final static String CS_Tier="Tier ranking";
    public final static String CS_Teammates_Points="Teammates points";
    public final static String CS_Teammates_VND="Teammates victories";
    
    public final static String CS_Name = "Name";

    public final static String CS_ACCR_Victory1 = "V1";
    public final static String CS_ACCR_Victory2 = "V2";
    public final static String CS_ACCR_Drawn = "N";
    public final static String CS_ACCR_Versus = "VS";
    public final static String CS_ACCR_Opponent_Points = "Opp. Pts";
    public final static String CS_ACCR_Opponent_Points_Without_Bonus = "Opp. Pts wo";
    public final static String CS_ACCR_Victory_Drawn_Lost = "V/D/L";

    public final static String CS_Offensive_Bonus_Threshold = "Offensive_Bonus_Threshold";
    public final static String CS_Offensive_Bonus_Value = "Offensive_Bonus_Value";
    public final static String CS_Diff_Offensive_Bonus_Threshold = "Diff_Offensive_Bonus_Threshold";
    public final static String CS_Diff_Offensive_Bonus_Value = "Diff_Offensive_Bonus_Value";
    public final static String CS_Diff_Defensive_Bonus_Threshold = "Diff_Defensive_Bonus_Threshold";
    public final static String CS_Diff_Defensive_Bonus_Value = "Diff_Defensive_Bonus_Value";
    
    public final static String CS_ForTeam_Offensive_Bonus_Value = "ForTeam_Offensive_Bonus_Value";
    public final static String CS_ForTeam_Diff_Offensive_Bonus_Value = "ForTeam_Diff_Offensive_Bonus_Value";
    public final static String CS_ForTeam_Diff_Defensive_Bonus_Value = "ForTeam_Diff_Defensive_Bonus_Value";
            
    public final static String CS_Team_Offensive_Bonus_Threshold = "Team_Offensive_Bonus_Threshold";
    public final static String CS_Team_Offensive_Bonus_Value = "Team_Offensive_Bonus_Value";
    public final static String CS_Team_Diff_Offensive_Bonus_Threshold = "Team_Diff_Offensive_Bonus_Threshold";
    public final static String CS_Team_Diff_Offensive_Bonus_Value = "Team_Diff_Offensive_Bonus_Value";
    public final static String CS_Team_Diff_Defensive_Bonus_Threshold = "Team_Diff_Defensive_Bonus_Threshold";
    public final static String CS_Team_Diff_Defensive_Bonus_Value = "Team_Diff_Defensive_Bonus_Value";

    private static ResourceBundle sBundle = null;

    public static String translate(String key) {
        if (sBundle == null) {
            sBundle = java.util.ResourceBundle.getBundle("tourma/languages/language");
        }
        String result = key;
        try {
            result = sBundle.getString(key);
        } catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        return result;
    }
}
