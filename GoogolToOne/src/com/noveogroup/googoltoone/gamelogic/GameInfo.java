package com.noveogroup.googoltoone.gamelogic;

public class GameInfo {
    public static final int NUMBER_ROUNDS = 2;

    private int currentScoreOnePlayer;
    private int currentScoreTwoPlayer;

    private int currentNumberRound;

    /*who asking query:
    true - PlayerOne
    false - PlayerTwo*/
    private boolean isQuerierPlayerOne;

    private RoundInfo currentRound;

    private String playerOneName;
    private String playerTwoName;

    public GameInfo( String playerOneName, String playerTwoName ) {
        currentScoreOnePlayer = 0;
        currentScoreTwoPlayer = 0;

        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;

        currentNumberRound = 1;
        currentRound = new RoundInfo();
        isQuerierPlayerOne = false;
    }

    public void setCurrentRound(RoundInfo currentRound) {
        isQuerierPlayerOne = ! isQuerierPlayerOne;
        currentScoreOnePlayer += isQuerierPlayerOne?
                this.currentRound.getRoundScoreAnswerer():
                this.currentRound.getRoundScoreQuerier();
        currentScoreTwoPlayer += isQuerierPlayerOne?
                this.currentRound.getRoundScoreQuerier():
                this.currentRound.getRoundScoreAnswerer();

        this.currentRound = currentRound;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public RoundInfo getCurrentRound() {
        return currentRound;
    }

    public int getCurrentScoreOnePlayer() {
        return currentScoreOnePlayer +
               (isQuerierPlayerOne? currentRound.getRoundScoreQuerier() : currentRound.getRoundScoreAnswerer());
    }

    public int getCurrentScoreTwoPlayer() {
        return currentScoreTwoPlayer +
               (isQuerierPlayerOne? currentRound.getRoundScoreAnswerer() : currentRound.getRoundScoreQuerier());
    }

    public int getAndIncreaseRoundNumber() {
        return currentNumberRound++;
    }

    public boolean getNextPlayer() {return !isQuerierPlayerOne;}

    public int getCurrentNumberRound() {
        return currentNumberRound;
    }
}
