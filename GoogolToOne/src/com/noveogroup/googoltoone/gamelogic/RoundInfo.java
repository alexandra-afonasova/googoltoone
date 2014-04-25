package com.noveogroup.googoltoone.gamelogic;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RoundInfo {
    public static final int MAX_NUMBER_ATTEMPTS = 3;
    private static final int MAX_NUMBER_ANSWER = 5;
    private static final int MULTIPLY_FACTOR = 10;

    private String beginRequest;
    private Vector<String> googleAnswers;
    private Vector<Integer> indexGuessedAnswers;
    private int roundScoreAnswerer;
    private int roundScoreQuerier;

    private int lastAddScore;
    private int numberAttempts;

    public RoundInfo() {
        this.googleAnswers = new Vector<String>();
        beginRequest = "";
        roundScoreAnswerer = 0;
        roundScoreQuerier = 0;
        this.indexGuessedAnswers = new Vector<Integer>();
        this.numberAttempts = MAX_NUMBER_ATTEMPTS;
    }

    public void setGoogleAnswers(Vector<String> googleAnswers) {
        this.googleAnswers = googleAnswers;
    }

    public boolean checkAnswer(String answer){
        if( ! googleAnswers.contains(answer) ){
            return false;
        }

        // check repeat
        int indexOfFoundAnswer = googleAnswers.indexOf(answer);
        if( ! indexGuessedAnswers.contains( indexOfFoundAnswer ) ){ // if same answer was not earlier
            indexGuessedAnswers.add(googleAnswers.indexOf(answer));
            lastAddScore = (int) ((MAX_NUMBER_ANSWER - indexGuessedAnswers.lastElement()) * Math.pow(MULTIPLY_FACTOR, numberAttempts-1));
        } else {
            lastAddScore = 0;
            return false;
        }

        // TODO: create rules
        roundScoreAnswerer += lastAddScore;

        return true;
    }

    public int getLastAddScore() {
        return lastAddScore;
    }

    public int getRoundScoreQuerier() {
        return roundScoreQuerier;
    }

    public int getRoundScoreAnswerer() {
        return roundScoreAnswerer;
    }

    public boolean reduceAffort() {
        return (--numberAttempts == 0);
    }

    public String getBeginRequest() {
        return beginRequest;
    }

    public void setBeginRequest(String beginRequest) {
        this.beginRequest = beginRequest;
    }
}
