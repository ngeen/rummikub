package com.digitoy.games.models;

import com.digitoy.games.models.tiles.Tile;

import java.util.List;

public class Board {
    private int key;
    private List<Tile> tiles;
    private int score;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
