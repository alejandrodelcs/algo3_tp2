package edu.fiuba.algo3.modelo.turn;


import edu.fiuba.algo3.modelo.Logger;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.player.Player;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;

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

    public void updateDefense(ArrayList<Defense> defenses, GameBoard gameBoard, Player player) {
        ArrayList<Enemy> enemies = gameBoard.getEnemies();
        for(Defense aDefense: defenses ){
                aDefense.attack(enemies);
        }
        for (Enemy enemy: enemies
             ) {
            if(enemy.enemyDied()){
                player.chargeCredits(enemy.generateCredits());
            }
        }
    }

    public boolean playerHasEnemies(Dictionary enemyStrategy,GameBoard gameBoard){
        ArrayList<Enemy> enemies = gameBoard.getEnemies();
        return (dictionaryHasEnemies(enemyStrategy) || dictionaryHasEnemies(enemyDictoinary) || !enemies.isEmpty());
    }
    public boolean dictionaryHasEnemies(Dictionary dictionary){
        boolean valoresVacios = true;
        Enumeration<ArrayList<Enemy>> values = dictionary.elements();
        while (values.hasMoreElements()) {
            ArrayList<Enemy> enemies = values.nextElement();
            if (!enemies.isEmpty()) {
                valoresVacios = false;
                break;
            }
        }
        return !valoresVacios;
    }

}