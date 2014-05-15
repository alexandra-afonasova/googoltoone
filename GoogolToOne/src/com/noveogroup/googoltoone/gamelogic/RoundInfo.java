package com.noveogroup.googoltoone.gamelogic;

import java.util.Vector;

public class RoundInfo {
    public static final int MAX_NUMBER_ATTEMPTS = 5;
    private static final int MAX_NUMBER_ANSWER = 5;
    private static final int MULTIPLY_FACTOR = 10;

    private String beginRequest;
    private Vector<String> googleAnswers;
    private Vector<Integer> indexGuessedAnswers;
    private int roundScoreAnswerer;
    private int roundScoreQuerier;

    private int lastAddScore;
    private int numberAttempts;
    private int errorsCounter;
    private int currentAnswers;

    public RoundInfo() {
        this.googleAnswers = new Vector<String>();
        beginRequest = "";
        roundScoreAnswerer = 0;
        roundScoreQuerier = 0;
        this.indexGuessedAnswers = new Vector<Integer>();
        this.numberAttempts = MAX_NUMBER_ATTEMPTS;
        this.errorsCounter = 0;
    }

    public void setGoogleAnswers(Vector<String> googleAnswers) {
        this.googleAnswers = googleAnswers;
    }

    // CRDONE names of boolean methods must started by is... was... need... etc.
    public boolean isAnswerCorrect(String answer) {
        if (!googleAnswers.contains(answer)) {
            // add to error's counter
            errorsCounter++;
            return false;
        }

        // check repeat
        int indexOfFoundAnswer = googleAnswers.indexOf(answer);
        if (!indexGuessedAnswers.contains(indexOfFoundAnswer)) { // if same answer was not earlier
            indexGuessedAnswers.add(googleAnswers.indexOf(answer));
            lastAddScore = (MAX_NUMBER_ANSWER - indexGuessedAnswers.lastElement()) * MULTIPLY_FACTOR;
            currentAnswers++;
        } else {
            lastAddScore = 0;
            errorsCounter ++;
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

    //CRDONE Fix typo. Maybe better use one term for attempts/efforts
    public boolean reduceAttempts() {
        return (--numberAttempts == 0);
    }

    public String getBeginRequest() {
        return beginRequest;
    }

    public void setBeginRequest(String beginRequest) {
        this.beginRequest = beginRequest;
    }

    public int getCurrentNumberOfErrors() {
        return errorsCounter;
    }

    public int getCurrentAnswers() {
        return currentAnswers;
    }
}
