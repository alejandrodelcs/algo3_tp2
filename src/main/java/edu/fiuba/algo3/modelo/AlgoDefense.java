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
import edu.fiuba.algo3.modelo.Turn;

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
        this.towers = new ArrayList<>();
        this.enemyStrategy = new EnemyFacade().loadEnemiesStrategy();
        this.turn = new Turn(enemyStrategy);
        this.towerFactory = new TowerFactory();
        this.enemyFactory = new EnemyFactory();

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

    public void spawnAnEnemy(int numberTurn){
        ArrayList<Enemy> enemyArrayList = (ArrayList<Enemy>) enemyStrategy.get(numberTurn);
        gameboard.spawnEnemy(enemyArrayList);
    }
    public void newTurn() {
        turn.passTurn(towers);
    }


    public boolean canPlayerBuyTower( Tower tower ){//TODO: here a player can buy "anything" not only towers
        return player.canBuy(tower.getCredits().getQuantity());
    }

    public boolean isOccupyByADefense(Point coordenatesToDirt) {
        return (!gameboard.availableForBuilding(coordenatesToDirt));
    }
}
