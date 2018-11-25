package com.digitoy.games.actions;

import com.digitoy.games.models.tiles.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TileActions {

    private List<Tile> createTiles(){
        List<Tile> tiles = new ArrayList<Tile>();

        //Creates Color Tiles
        for (int k=0; k<2; k++){
            for (int i=0; i<13; i++){
                tiles.add(new YellowTile(i+1, i, "Yellow"));
                tiles.add(new BlueTile(i+1, i+13, "Blue"));
                tiles.add(new BlackTile(i+1, i + 26, "Black"));
                tiles.add(new RedTile(i+1, i + 39, "Red"));
            }

            //Create False Joker Tiles
            tiles.add(new FalseJoker(k+1, 52, "FalseJoker"));
        }
        return tiles;
    }

    //Create And Shuffle Tiles For Game
    public List<Tile> shuffleTiles(){
        List<Tile> tiles = createTiles();
        Collections.shuffle(tiles);
        return tiles;
    }

    //Select FaceUp Tile for selecting Joker
    public Tile selectFaceUpTile(List<Tile> tiles){
        int random = (int)(Math.random() * 105 + 1);
        Tile tile = tiles.get(random);

        //If faceUp tile equals false joker then select again
        if(tile.getValue() == 52)
            return selectFaceUpTile(tiles);

        //FaceUp tile can not play in game
        tiles.remove(random);
        return tile;
    }

    //It gets Joker Tile
    public Tile getJoker(Tile faceUpTile, List<Tile> tiles){
        final int key;
        if(faceUpTile.getKey() == 13)
            key=1;
        else
            key = faceUpTile.getKey()+1;

        String className = faceUpTile.getClass().toString();

        Tile joker = tiles.stream().filter(x -> x.getKey() == key && className.equals(x.getClass().toString())).findFirst().get();

        return joker;
    }

    //Set false joker real value
    public void setFalseJokerReal (List<Tile> tiles, Tile joker){
        int jokerValue = joker.getValue();
        tiles.stream().filter(x -> x.getValue() == jokerValue).forEach(s -> s.setValue(99));
        ArrayList<Tile> falseJokers = tiles.stream().filter(x -> x.getValue() == 52).collect(Collectors.toCollection(ArrayList::new));
        for (Tile tile : falseJokers ) {
            FalseJoker falseJoker = (FalseJoker)tile;
            falseJoker.setRealValue(falseJoker.getValue());
            falseJoker.setName(falseJoker.getName()+"("+joker.getName()+")");
            falseJoker.setColor(joker.getColor());
            falseJoker.setValue(jokerValue);
            falseJoker.setKey(joker.getKey());
        }

    }

}
