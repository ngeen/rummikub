package com.digitoy.games.models.tiles;

public class FalseJoker extends Tile {

    private int realValue;

    public FalseJoker(int _key, int _value, String _color) {
        super(_key, _value, _color);
    }

    public int getRealValue() {
        return realValue;
    }

    public void setRealValue(int realValue) {
        this.realValue = realValue;
    }
}
