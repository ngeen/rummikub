package com.digitoy.games.models;

import com.digitoy.games.models.tiles.Tile;

import java.util.List;

public class Table {
    private int key;
    private List<Player> players;
    private List<Tile> tiles;
    private Tile joker;
    private Tile faceUp;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public Tile getJoker() {
        return joker;
    }

    public void setJoker(Tile joker) {
        this.joker = joker;
    }

    public Tile getFaceUp() {
        return faceUp;
    }

    public void setFaceUp(Tile faceUp) {
        this.faceUp = faceUp;
    }
}
