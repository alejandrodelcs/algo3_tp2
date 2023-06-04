package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.defense.SilverTower;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.defense.WhiteTower;
import edu.fiuba.algo3.modelo.player.Player;

public class PlayerTurn implements Turn{

    private int countTurn;
    private Player player;

    private Tower tower;
    public PlayerTurn(Player player){
        this.countTurn = 0;
        this.player = player;
        this.tower = null;
    }

    public void executeTurn() {
        countTurn += 1;
        if (countTurn % 2 == 0) {
            tower = new SilverTower();
        }else{
            tower = new WhiteTower();
        }
        tower.constructionFinished();
    }

    public boolean passTurn(){
        countTurn = 0;
        return true;
    }
}
