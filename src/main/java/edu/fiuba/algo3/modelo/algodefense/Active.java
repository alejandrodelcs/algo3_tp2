package edu.fiuba.algo3.modelo.algodefense;

import edu.fiuba.algo3.modelo.AlgoDefense;

public class Active implements GameState{
    @Override
    public void passTurn(AlgoDefense algoDefense) {
        algoDefense.executeTurn();
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
