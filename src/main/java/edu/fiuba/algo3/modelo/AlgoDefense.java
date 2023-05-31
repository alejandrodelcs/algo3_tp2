package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Defense.Tower;
import edu.fiuba.algo3.modelo.Enemy.Enemy;
import edu.fiuba.algo3.modelo.GameBoard.GameBoard;
import edu.fiuba.algo3.modelo.GameBoard.NonConstructibleArea;
import edu.fiuba.algo3.modelo.Player.Player;

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
        //ArrayList<Poins>listOfPlacesWhereADefenseCanBeBuild = gameboard.AskBuidlDefence()//TODO: here the game board have to ask where the player want to build a defense(the return is a list of posibles coordinates)
        Point ExampleOfCoordinates = new Point(2,3);//"Example" of avalable place where a tower can be build
        ArrayList<Point>listOfPlacesWhereADefenseCanBeBuild = new ArrayList<Point>();
        //Point coordinates = player.selectPlaceDefense( listOfPlacesWhereADefenseCanBeBuild ) //TODO: here the player select the coordinate
        listOfPlacesWhereADefenseCanBeBuild.add(ExampleOfCoordinates);//this should be implemented in "selectPlaceDefense"
        Point coordinatesPosibleConstruction = listOfPlacesWhereADefenseCanBeBuild.get(0);//this should be implemented in "s"electPlaceDefense"(here the player select the coordinates)
        Tower tower = player.selectTower();//TODO: here the player select what to build

        if(!gameboard.availableForBuilding(tower, coordinatesPosibleConstruction)){
            throw new NonConstructibleArea();
        }
        if(canPlayerBuyTower(tower)){
            player.chargedCredits(tower.getCredits());
            gameboard.buildDefense(tower, coordinatesPosibleConstruction);
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
    public boolean canPlayerBuyTower( Tower tower ){//TODO: here a player can buy "anything" not only towers
        return player.canBuy(tower.getCredits());
    }

}
