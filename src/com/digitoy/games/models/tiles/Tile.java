package com.digitoy.games.models.tiles;

public class Tile {

    private int key;
    private int value;
    private String color;
    private String name;

    public Tile(int _key, int _value, String _color){
        this.key = _key;
        this.value = _value;
        this.color = _color;
        this.setName(color+"-"+key);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
