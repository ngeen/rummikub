package com.digitoy.games.actions;

import com.digitoy.games.models.Board;
import com.digitoy.games.models.Player;
import com.digitoy.games.models.Table;
import com.digitoy.games.models.tiles.Tile;
import com.digitoy.games.models.tiles.YellowTile;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;

public class GameActions {

    private Table table;

    public GameActions(){
        table = new Table();
    }

    public void createGame(){
        Table t = new Table();
        TableActions ta = new TableActions();
        BoardActions ba = new BoardActions();
        ta.createTable(t);
        System.out.print("Table Id : "+ t.getKey());
        System.out.print(" Joker : "+ t.getJoker().getColor()+" "+t.getJoker().getKey());
        System.out.print(" FaceUp : "+ t.getFaceUp().getColor()+" "+t.getFaceUp().getKey());
        System.out.println();

        for (Player player : t.getPlayers()) {
            System.out.print(player.getName());
            System.out.print(" / Joker Sayısı : "+player.getJokerCount());
            System.out.println();
            player.getBoard().getTiles().sort(Comparator.comparingInt(Tile::getValue));
            for (Tile tile: player.getBoard().getTiles()) {
                System.out.print(tile.getName()+" ");
            }
            System.out.println();
            ba.checkBoard(player.getBoard());
        }

        System.out.println();

        System.out.println(printBestScore(t).getName()+" is best hand");
    }

    private Player printBestScore(Table table){
     return table.getPlayers().stream().max(Comparator.comparing(x -> x.getBoard().getScore())).get();
    }

}
