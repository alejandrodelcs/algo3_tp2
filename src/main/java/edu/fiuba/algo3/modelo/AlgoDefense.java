package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Tower;
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
import java.util.Enumeration;

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
        if(!turn.playerHasEnemies(enemyStrategy,gameboard) && player.isAlive()){
            Logger.get().log("You have won the game");
        }
    }
    public void buildsADefense(Defense defense) {

        if(!gameboard.availableForBuilding(defense.getPoint())){
            throw new NonConstructibleArea();
        }

        player.subtractCredits(defense.getCredits());
        gameboard.buildDefense(defense);
        defenses.add(defense);
        System.out.println(defenses);
    }
    public void spawnAnEnemy(ArrayList<Enemy> enemyArrayList){
        gameboard.spawnEnemy(enemyArrayList);
        System.out.println(defenses);
        for(Enemy enemy : enemyArrayList){
            if(enemy.getClass() == Owl.class){
                if(defenses.size()>0){
                    System.out.println(defenses);
                    defenses.remove(0);
                }
            }
        }
    }

    public boolean isOccupyByADefense(Point coordenatesToDirt) {
        return (!gameboard.availableForBuilding(coordenatesToDirt));
    }
    public void damageThePlayer(){
        ArrayList<Enemy> finalListOfEnemies = gameboard.getEnemiesInThelastPath();
        //System.out.println(finalListOfEnemies);
        for(Enemy enemy : finalListOfEnemies){
            player.getsDamage(enemy.getDamage());
            Logger.get().log("The "+ enemy.getClass().getSimpleName() + " reaches the goal, causing "+ enemy.getDamage().getQuantity()+" damage to the player");
        }
        if (!player.isAlive()){
            Logger.get().log("The player is dead.");
        }
    }

    public void setPlayer(Player player){
        this.player = player;
    }

}
