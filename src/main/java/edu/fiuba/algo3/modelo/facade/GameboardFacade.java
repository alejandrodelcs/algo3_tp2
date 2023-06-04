package edu.fiuba.algo3.modelo.facade;

import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.parser.EnemiesParser;
import edu.fiuba.algo3.modelo.parser.MapParser;

import java.util.Dictionary;

public class GameboardFacade {
    public GameBoard loadMap() {
        MapParser mapParser = new MapParser();
        return mapParser.initializeMap();
    }
}
