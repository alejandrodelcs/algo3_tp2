package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.gameboard.Plot;

import java.util.ArrayList;

public class EnemyTurn implements Turn{
    public void executeTurn() {
        System.out.println("se mueve enemigo");

    }

    public boolean passTurn(){
        return false;
    }
}
