package com.noveogroup.googoltoone.database.model;

import java.util.Date;

public class Game {
    private int id;
    private Player player1;
    private Player player2;
    private Date time;

    public Game(Player player1, Player player2, Date time) {
        this.player1 = player1;
        this.player2 = player2;
        this.time = time;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Date getTime() {
        return time;
    }
}
