package edu.fiuba.algo3.modelo.facade;

import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.parser.MapParser;

import java.util.Dictionary;

public class GameboardFacade {

    private String fileSource;

    public GameboardFacade(){
        this.fileSource =  "src\\main\\resources\\files\\mapa.json";
    }
    public GameBoard loadMap() {
        MapParser mapParser = new MapParser(fileSource);
        return mapParser.initializeMap();
    }
}
