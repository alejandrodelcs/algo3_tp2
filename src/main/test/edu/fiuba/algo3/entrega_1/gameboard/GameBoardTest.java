package edu.fiuba.algo3.entrega_1.gameboard;

import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.parser.JSONreader;

class GameBoardTest {

    @Test
    public void AGameBoardIsCreated(){
        JSONreader Reader = new JSONreader();
        GameBoard game = new GameBoard(Reader.getObject());
        game.printMap();
    }


}