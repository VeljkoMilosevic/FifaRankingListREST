/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.services;


/**
 * @author Veljko
 */
public class MatchConstants {
    private MatchConstants() {}
    public static final int WINNER = 3;
    public static final int DRAW = 1;
    public static final int LOSE = 0;

    public static final double THIS_YEAR = 1d;
    public static final double LAST_YEAR = 0.5;
    public static final double TWO_YEARS_AGO = 0.3;
    public static final double THREE_YEARS_AGO = 0.2;
    public static final double INITIAL_OPPONENT_STRENGTH = 200d;
}
