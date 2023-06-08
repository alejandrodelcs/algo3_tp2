package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.player.Player;

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

    public void updateTowers(ArrayList<Tower> towers, GameBoard gameBoard, Player player) {
        ArrayList<Enemy> enemies = gameBoard.getEnemies();
        for(Tower singleTower: towers ){
                singleTower.attack(enemies);
                /*if(enemy.enemyDied()){
                    player.chargeCredits(enemy.generateCredits());
                }*/
            //TODO: Check if the enemy is dead outside of this function for the credit refund
        }
    }
    public int getTurn(){
        return currentTurn;
    }
}