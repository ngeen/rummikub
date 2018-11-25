package com.digitoy.games;

import com.digitoy.games.actions.GameActions;
import com.digitoy.games.actions.TableActions;
import com.digitoy.games.actions.TileActions;
import com.digitoy.games.models.Player;
import com.digitoy.games.models.Table;
import com.digitoy.games.models.tiles.Tile;

import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GameActions ga = new GameActions();
        ga.createGame();
    }
}
