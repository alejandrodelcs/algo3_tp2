package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Defense.Tower;
import edu.fiuba.algo3.modelo.GameBoard.GameBoard;
import java.awt.*;
import java.lang.reflect.Array;
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
        if(gameboard.availableForBuilding(tower, coordinates) && player.canBuy(value)){
            player.chargedCredits(value);
            gameboard.buildDefense(tower, coordinates);
            towers.add(tower);
        }
        //add exception
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
}
