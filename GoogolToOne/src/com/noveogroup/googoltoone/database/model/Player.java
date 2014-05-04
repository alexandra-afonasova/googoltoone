package com.noveogroup.googoltoone.database.model;

public class Player {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public Player(String name) {
        this.name = name;
    }
}
