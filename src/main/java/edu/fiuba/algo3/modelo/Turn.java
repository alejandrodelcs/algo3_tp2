package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.util.ArrayList;
import java.util.Dictionary;

public class Turn {
    private int currentTurn;
    private Dictionary enemyDictoinary;

    public Turn(Dictionary enemyStrategy) {
        this.enemyDictoinary = enemyStrategy;
    }
    public ArrayList<Enemy> passTurn(ArrayList<Tower> towers) {
        for(Tower singleTower: towers ){
            singleTower.updateStatus();
        }
        currentTurn++;
        ArrayList<Enemy> newEnemies = (ArrayList<Enemy>) enemyDictoinary.get(currentTurn);
        return newEnemies;
    }
}