/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

/**
 *
 * @author Frederic Berger
 */
public class Parameters {
    public int _victory_points=1000;
    public int _large_victory_points=1100;
    public int _draw_points=400;
    public int _little_lost_points=100;
    public int _lost_points=0;

    public int _bonus_td_points=2;
    public int _bonus_sor_points=1;
    public int _bonus_foul_points=1;

    public int _bonus_neg_td_points=0;
    public int _bonus_neg_sor_points=0;
    public int _bonus_neg_foul_points=0;

    public String _tournament_name;
    public String _tournament_orga;

    public int _ranking1=1;
    public int _ranking2=2;
    public int _ranking3=3;
    public int _ranking4=4;
    public int _ranking5=5;

    public static final int C_RANKING_NONE=0;
    public static final int C_RANKING_POINTS=1;
    public static final int C_RANKING_OPP_POINTS=2;
    public static final int C_RANKING_TD=3;
    public static final int C_RANKING_SOR=4;
    public static final int C_RANKING_FOUL=5;
    public static final int C_RANKING_DIFF_TD=6;
    public static final int C_RANKING_DIFF_SOR=7;
    public static final int C_RANKING_DIFF_FOUL=8;
    public static final int C_RANKING_VND=9;

    public Parameters()
    {
        _tournament_name="";
        _tournament_orga="";
    }

}
