package edu.fiuba.algo3.modelo.algodefense;

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
