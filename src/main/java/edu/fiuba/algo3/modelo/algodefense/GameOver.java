package edu.fiuba.algo3.modelo.algodefense;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.exceptions.Gameover;

public class GameOver implements GameState{

    @Override
    public void passTurn(AlgoDefense algoDefense) {
        throw new Gameover();
    }

    @Override
    public boolean isGameOver() {
        return true;
    }
}
