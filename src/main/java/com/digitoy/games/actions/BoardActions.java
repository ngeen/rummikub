package com.digitoy.games.actions;

import com.digitoy.games.models.Board;
import com.digitoy.games.models.tiles.Tile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BoardActions {

    private List<Tile> nonConsecutive;

    /* Grouping consecutive numbers   */
    public void checkConsecutive(List<Tile> tiles, Board board){
        int tileSize = tiles.size();

        List<List<Tile>> ListMain = new ArrayList<>();
        List<Tile> temp = new ArrayList<>();

        for (int i = 0; i < tileSize; i++) {
            if ((i + 1 < tileSize)&&( tiles.get(i).getValue() + 1==tiles.get(i + 1).getValue())) {
                temp.add(tiles.get(i));
            } else {
                temp.add(tiles.get(i));
                ListMain.add(temp);
                temp  = new ArrayList<>();
            }

        }

        int groupScore = 0;
        for (List<Tile> ts : ListMain) {
            if(ts.size() >2)
                groupScore += ts.size();
            else
               ts.stream().forEach(x -> nonConsecutive.add(x));
           //ts.stream().forEach(x -> System.out.print(x.getName()+ " "));
           //System.out.println();
        }
        //System.out.println();

        board.setScore(groupScore);
    }

    private void checkDifferentColor(Board board){
        Map<Integer, ArrayList<Tile>> setsOfTiles = nonConsecutive.stream()
                .filter(distinctByKey(Tile::getName))
                .collect(Collectors.groupingBy(Tile::getKey, Collectors.toCollection(ArrayList<Tile>::new)));
        for(Map.Entry<Integer, ArrayList<Tile>> entry : setsOfTiles.entrySet()) {
            Integer key = entry.getKey();
            List<Tile> value = entry.getValue();
            if(value.size() > 2)
                board.setScore(board.getScore() + value.size());
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public void checkBoard(Board board){
        nonConsecutive = new ArrayList<Tile>();
        List<Tile> tiles = board.getTiles();
        Map<String, ArrayList<Tile>> setsOfTiles = tiles.stream().collect(
                Collectors.groupingBy(Tile::getColor, Collectors.toCollection(ArrayList<Tile>::new)));
        for(Map.Entry<String, ArrayList<Tile>> entry : setsOfTiles.entrySet()) {
            String key = entry.getKey();
            List<Tile> value = entry.getValue();
            checkConsecutive(value, board);
        }

        checkDifferentColor(board);
    }
}
