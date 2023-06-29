package edu.fiuba.algo3.modelo.algodefense;

public interface GameState {
    void passTurn(AlgoDefense algoDefense);

    boolean isGameOver();
}
