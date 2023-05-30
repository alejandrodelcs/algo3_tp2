package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Defense.Tower;
import edu.fiuba.algo3.modelo.Enemy.Enemy;
import edu.fiuba.algo3.modelo.GameBoard.GameBoard;
import edu.fiuba.algo3.modelo.GameBoard.NonConstructibleArea;

import java.awt.*;
import java.util.ArrayList;

public class AlgoDefense {
    private Player player;
    private GameBoard gameboard;
    private int turn;

    private ArrayList<Tower> towers;
    public AlgoDefense(Player player){
        this.player = player;
        this.gameboard = new GameBoard();
        this.towers = new ArrayList<>();
    }
    public void buildsADefense(Tower tower, Point coordinates) {
        int value = tower.getCredits();
        if(!gameboard.availableForBuilding(tower, coordinates)){
            throw new NonConstructibleArea();
        }
        if(player.canBuy(value)){
            player.chargedCredits(value);
            gameboard.buildDefense(tower, coordinates);
            towers.add(tower);
        }
        //add an exception for when the player does not have enough credits
    }

    public void spawnAnEnemy(Enemy enemy, Point coordinates){
        gameboard.spawnEnemy(enemy, coordinates);
    }
    public void newTurn() {
        turn += 1;
        for (Tower tower:towers) {
            tower.updateStatus();
        }
    }
    public int getTurn() {
        return turn;
    }

    public boolean enemyIsOnRange(Tower tower,Point coordenatesEnemy, Point coordenatesDirtPlot) {
        double distance = coordenatesEnemy.distance(coordenatesDirtPlot.getX(),coordenatesDirtPlot.getY());
        return(tower.getAttackRange()>=distance);
    }
}
