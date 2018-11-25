package com.digitoy.games.models;

public class Player {
    private int key;
    private String name;
    private Board board;
    private long jokerCount;

    public Player(int _key, String _name){
        this.key = _key;
        this.name = _name;
        this.board = new Board();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public long getJokerCount() {
        return jokerCount;
    }

    public void setJokerCount(long jokerCount) {
        this.jokerCount = jokerCount;
    }
}
