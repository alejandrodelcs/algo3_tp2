package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;
import edu.fiuba.algo3.modelo.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class AlgoDefense {
    private Player player;
    private GameBoard gameboard;
    private int turn;//TODO: Should be a class
    private ArrayList<Enemy> enemyArrayLists;

    private ArrayList<Tower> towers;
    public AlgoDefense(Player aPLayer,GameBoard gameboard, ArrayList<Enemy> enemies){
        this.player = aPLayer;
        this.gameboard = gameboard;
        this.towers = new ArrayList<>();
        this.enemyArrayLists = enemies;
    }
    public void buildsATower() {
        Point coordinatesPosibleConstruction = new Point(4,2);
        Tower tower = player.selectTower();//TODO: here the player select what to build
        if(canPlayerBuyTower(tower)){
            player.chargedCredits(tower.getCredits());
            gameboard.buildDefense(tower, coordinatesPosibleConstruction);
            towers.add(tower);
        }
        if(!gameboard.availableForBuilding(tower, coordinatesPosibleConstruction)){
            throw new NonConstructibleArea();
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
    public boolean canPlayerBuyTower( Tower tower ){//TODO: here a player can buy "anything" not only towers
        return player.canBuy(tower.getCredits().getQuantity());
    }

}
