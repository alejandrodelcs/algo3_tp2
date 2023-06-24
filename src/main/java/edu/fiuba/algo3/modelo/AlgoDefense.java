package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.algodefense.Active;
import edu.fiuba.algo3.modelo.algodefense.GameOver;
import edu.fiuba.algo3.modelo.algodefense.GameState;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.enemy.*;
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
    private GameState state;
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
        this.state = new Active();
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
        this.state = new Active();
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
    public void nextTurn(){
        state.passTurn(this);
    }
    public void executeTurn() {
        ArrayList<Enemy> newEnemies = turn.passTurn();
        gameboard.moveEnemies();
        spawnAnEnemy(newEnemies);
        turn.updateDefense(defenses,gameboard,player);
        damageThePlayer();
        if(!turn.playerHasEnemies(enemyStrategy,gameboard) && player.isAlive()){
            Logger.get().log("You have won the game");
            state = new GameOver();
        }
    }
    public void buildsADefense(Defense defense) {

        if(!gameboard.availableForBuilding(defense.getPoint())){
            throw new NonConstructibleArea();
        }

        player.subtractCredits(defense.getCredits());
        gameboard.buildDefense(defense);
        defenses.add(defense);
    }
    public void spawnAnEnemy(ArrayList<Enemy> enemyArrayList){
        gameboard.spawnEnemy(enemyArrayList);
    }
    private void  enemyDestroysDefense(ArrayList<Enemy> enemyArrayList){
        if((!defenses.isEmpty()) && (enemyArrayList != null) && (!enemyArrayList.isEmpty())){
            for(Enemy enemy : enemyArrayList){
                if(enemy.getClass() == Owl.class){
                    Defense defense = defenses.get(0);
                    gameboard.destroyDefense(defense);
                    defenses.remove(0);
                }
            }
        }
    }

    public boolean isOccupyByADefense(Point coordenatesToDirt) {
        return (!gameboard.availableForBuilding(coordenatesToDirt));
    }
    private void damageThePlayer(){
        ArrayList<Enemy> finalListOfEnemies = gameboard.getEnemiesInThelastPath();
        for(Enemy enemy : finalListOfEnemies){
            player.getsDamage(enemy.getDamage());
            Logger.get().log("The "+ enemy.getClass().getSimpleName() + " reaches the goal, causing "+ enemy.getDamage().getQuantity()+" damage to the player");
            if (!player.isAlive()){
                Logger.get().log("The player is dead.");
                state = new GameOver();
                break;
            }
        }

        enemyDestroysDefense(finalListOfEnemies);
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void loadEnemies() {
        //TODO: can pick a random JSON file REF
        //this.gameboard = new GameboardFacade().loadMap();
        //EnemyFacade

    }
    public GameBoard getGameboard(){
        return gameboard;
    }

    public String getPlayerInfo() {
        return player.playerInfo();
    }

    public boolean gameOver(){
        return state.isGameOver();
    }
}
