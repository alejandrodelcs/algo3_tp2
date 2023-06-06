package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.defense.TowerFactory;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.enemy.EnemyFactory;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import edu.fiuba.algo3.modelo.facade.EnemyFacade;
import edu.fiuba.algo3.modelo.facade.GameboardFacade;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;
import edu.fiuba.algo3.modelo.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;

public class AlgoDefense {
    private Player player;
    private GameBoard gameboard;
    private Turn turn;
    private Dictionary enemyStrategy;
    private TowerFactory towerFactory;
    private EnemyFactory enemyFactory;
    private ArrayList<Tower> towers;

    public AlgoDefense(Player aPLayer){
        this.player = aPLayer;
        this.gameboard = new GameboardFacade().loadMap();
        this.towers = new ArrayList<Tower>();
        this.enemyStrategy = new EnemyFacade().loadEnemiesStrategy();
        this.turn = new Turn(enemyStrategy);
        this.towerFactory = new TowerFactory();
        this.enemyFactory = new EnemyFactory();

    }
    public void nextTurn() {
        ArrayList<Enemy> newEnemies = turn.passTurn();
        gameboard.moveEnemies();
        spawnAnEnemy(newEnemies);
        turn.updateTowers(towers,gameboard);
    }
    public void buildsATower(Point coordinatesPosibleConstruction, String typeOfTower) {

        if(!gameboard.availableForBuilding(coordinatesPosibleConstruction)){
            throw new NonConstructibleArea();
        }

        Tower tower = towerFactory.createTower(typeOfTower, coordinatesPosibleConstruction);//TODO: here the player select what to build

        if(!canPlayerBuyTower(tower)){
            throw new InsufficientCredits();
        }
        player.chargedCredits(tower.getCredits());
        gameboard.buildDefense(tower, coordinatesPosibleConstruction);
        towers.add(tower);
    }
    public void spawnAnEnemy(ArrayList<Enemy> enemyArrayList){
        gameboard.spawnEnemy(enemyArrayList);

    }
    public boolean canPlayerBuyTower( Tower tower ){//TODO: here a player can buy "anything" not only towers
        return player.canBuy(tower.getCredits().getQuantity());
    }
    public boolean isOccupyByADefense(Point coordenatesToDirt) {
        return (!gameboard.availableForBuilding(coordenatesToDirt));
    }
}
