package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;

import java.util.ArrayList;
import java.util.Dictionary;

public class Turn {
    private int currentTurn;
    private Dictionary enemyDictoinary;

    public Turn(Dictionary enemyStrategy) {
        this.enemyDictoinary = enemyStrategy;
    }
    public ArrayList<Enemy> passTurn() {
        currentTurn++;
        ArrayList<Enemy> newEnemies = (ArrayList<Enemy>) enemyDictoinary.get(currentTurn);
        return newEnemies;
    }

    public void updateTowers(ArrayList<Tower> towers, GameBoard gameBoard) {
        for(Tower singleTower: towers ){
            ArrayList<Enemy> enemiesInRange = gameBoard.enemiesInRange(singleTower);
            for (Enemy enemy: enemiesInRange
                 ) {
                singleTower.attack(enemy);
            }
        }
    }
    public int getTurn(){
        return currentTurn;
    }
}