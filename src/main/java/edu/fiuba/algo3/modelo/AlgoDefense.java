package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.enemy.*;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import edu.fiuba.algo3.modelo.facade.EnemyFacade;
import edu.fiuba.algo3.modelo.facade.GameboardFacade;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;
import edu.fiuba.algo3.modelo.player.Player;
import edu.fiuba.algo3.modelo.turn.Turn;

import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;

public class AlgoDefense {
    private Player player;
    private GameBoard gameboard;
    private Turn turn;
    private Dictionary enemyStrategy;
    private EnemyFactory antFactory;
    private EnemyFactory spiderFactory;
    private EnemyFactory moleFactory;
    private EnemyFactory owlFactory;

    private ArrayList<Defense> defenses;

    public AlgoDefense(){
        this.gameboard = new GameboardFacade().loadMap();
        this.defenses = new ArrayList<Defense>();
        this.enemyStrategy = new EnemyFacade().loadEnemiesStrategy();
        this.turn = new Turn(enemyStrategy);
        this.antFactory = new AntFactory();
        this.spiderFactory = new SpiderFactory();
        this.moleFactory = new MoleFactory();
        this.owlFactory = new OwlFactory();
    }

    public AlgoDefense(Player aPLayer){
        this.player = aPLayer;
        this.gameboard = new GameboardFacade().loadMap();
        this.defenses = new ArrayList<Defense>();
        this.enemyStrategy = new EnemyFacade().loadEnemiesStrategy();
        this.turn = new Turn(enemyStrategy);
        this.antFactory = new AntFactory();
        this.spiderFactory = new SpiderFactory();
        this.moleFactory = new MoleFactory();
        this.owlFactory = new OwlFactory();

    }
    public void nextTurn() {
        ArrayList<Enemy> newEnemies = turn.passTurn();
        gameboard.moveEnemies();
        spawnAnEnemy(newEnemies);
        turn.updateDefense(defenses,gameboard,player);
        gameboard.printMap();
        damageThePlayer();
    }
    public void buildsADefense(Defense defense) {

        if(!gameboard.availableForBuilding(defense.getPoint())){
            throw new NonConstructibleArea();
        }

        if(!canPlayerBuyTower(defense)){
            throw new InsufficientCredits();
        }
        player.subtractCredits(defense.getCredits());
        gameboard.buildDefense(defense);
        defenses.add(defense);
    }
    public void spawnAnEnemy(ArrayList<Enemy> enemyArrayList){
        gameboard.spawnEnemy(enemyArrayList);

    }
    public boolean canPlayerBuyTower( Defense defense ){//TODO: here a player can buy "anything" not only towers
        return player.canBuy(defense.getCredits().getQuantity());
    }
    public boolean isOccupyByADefense(Point coordenatesToDirt) {
        return (!gameboard.availableForBuilding(coordenatesToDirt));
    }
    public void damageThePlayer(){
        ArrayList<Enemy> finalListOfEnemies = gameboard.getEnemiesInThelastPath();
        for(Enemy enemy : finalListOfEnemies){
            player.getsDamage(enemy.getDamage());
        }
        if (!player.isAlive()){
            Logger.get().log("The player is dead.");
        }
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void loadEnemies() {
        //TODO: can pick a random JSON file REF
        //this.gameboard = new GameboardFacade().loadMap();
        //EnemyFacade

    }

    public boolean playerHasEnemies() {
        return !enemyStrategy.isEmpty();
    }
}
