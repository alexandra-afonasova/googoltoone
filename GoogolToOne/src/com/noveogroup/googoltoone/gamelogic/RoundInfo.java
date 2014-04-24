package com.noveogroup.googoltoone.gamelogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RoundInfo {
    private static Vector<String> googleAnswers;
    private Vector<Integer> indexGuessedAnswers;

    private int roundPoints;

    public RoundInfo() {
        this.googleAnswers = new Vector<String>();
        this.roundPoints = 0;
        this.indexGuessedAnswers = new Vector<Integer>();
    }

    public static void setGoogleAnswers(Vector<String> googleAnswers) {
        RoundInfo.googleAnswers = googleAnswers;
    }

    public boolean checkAnswer( String answer ){
        if( ! googleAnswers.contains(answer) ){
            return false;
        }

        indexGuessedAnswers.add(googleAnswers.indexOf(answer));
        // TODO: create rules
        roundPoints = roundPoints * 10 + (5 - indexGuessedAnswers.lastElement());

        return true;
    }

    public int getRoundPoints() {
        return roundPoints;
    }
}
