package com.digitoy.games.actions;

import com.digitoy.games.models.Board;
import com.digitoy.games.models.Player;
import com.digitoy.games.models.Table;
import com.digitoy.games.models.tiles.Tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

public class TableActions {

    private final String[] playerNames = {"Dave","Stuart","Kevin","Jerry"};
    private TileActions tileActions;
    private Table table;

    public TableActions(){
        tileActions = new TileActions();
    }

    private List<Player> createPlayers(){
        List<Player> players = new ArrayList<Player>();
        for (int i=0; i<4; i++) {
            players.add(new Player(i+1, playerNames[i]));
        }

        return players;
    }

    private List<Tile> getTiles(){
        return tileActions.shuffleTiles();
    }

    private void prepareTiles(Table table){
        List<Tile> tiles = getTiles();
        Tile faceUpTile = tileActions.selectFaceUpTile(tiles);
        Tile jokerTile = tileActions.getJoker(faceUpTile, tiles);
        tileActions.setFalseJokerReal(tiles, jokerTile);
        table.setTiles(tiles);
        table.setFaceUp(faceUpTile);
        table.setJoker(jokerTile);
    }

    private void prepareBoards(Table table){
        List<Tile> tiles = table.getTiles();
        List<Tile> removedTiles = new ArrayList<Tile>();
        int activePlayerId = (int)(Math.random() * 4 + 1);
        for (Player player: table.getPlayers()) {

            Board board = player.getBoard();
            board.setKey(player.getKey());

            if(player.getKey() == activePlayerId){

                board.setTiles(tiles.stream().limit(15).collect(toCollection(ArrayList::new)));
                removedTiles.addAll(tiles.stream().limit(15).collect(toCollection(ArrayList::new)));
            } else {
                board.setTiles(tiles.stream().limit(14).collect(toCollection(ArrayList::new)));
                removedTiles = tiles.stream().limit(14).collect(toCollection(ArrayList::new));
            }

            player.setJokerCount(playerJokerCount(player.getBoard().getTiles(), table));

            tiles.removeAll(removedTiles);
        }
    }

    private long playerJokerCount(List<Tile> tiles, Table table){
        return tiles.stream().filter(x -> x.getValue() == table.getJoker().getValue()).count();
    }

    public Table createTable(Table _table){
        table = _table;
        table.setKey(1);
        table.setPlayers(createPlayers());
        prepareTiles(table);
        prepareBoards(table);
        return table;
    }
}
