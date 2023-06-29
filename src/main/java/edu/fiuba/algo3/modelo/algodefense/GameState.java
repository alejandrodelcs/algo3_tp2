package edu.fiuba.algo3.modelo.algodefense;

import edu.fiuba.algo3.modelo.AlgoDefense;

public interface GameState {
    void passTurn(AlgoDefense algoDefense);

    boolean isGameOver();
}
