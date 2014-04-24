package com.noveogroup.googoltoone.gamelogic;

public class GameInfo {
    private String playerOneName;
    private String playerTwoName;

    private int totalScoreOnePlayer;
    private int totalScoreTwoPlayer;

    private RoundInfo currentRound;

    public GameInfo(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        totalScoreOnePlayer = 0;
        totalScoreTwoPlayer = 0;
        currentRound = null;
    }

    public void setCurrentRound(RoundInfo currentRound) {
        this.currentRound = currentRound;
    }

    public RoundInfo getCurrentRound() {
        return currentRound;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setTotalScoreOnePlayer(int totalScoreOnePlayer) {
        this.totalScoreOnePlayer = totalScoreOnePlayer;
    }

    public void setTotalScoreTwoPlayer(int totalScoreTwoPlayer) {
        this.totalScoreTwoPlayer = totalScoreTwoPlayer;
    }
}
