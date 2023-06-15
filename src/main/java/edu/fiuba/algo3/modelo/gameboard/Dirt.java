package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.NonTrapConstructibleArea;
import edu.fiuba.algo3.modelo.exceptions.TheEnemyCannotBeOutsideTheRunway;
import edu.fiuba.algo3.modelo.exceptions.ThereCannotBeEnemiesInThisPlot;

import java.util.ArrayList;

public class Dirt extends Plot{
    public Dirt(){state = new Available();}
    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) {throw new TheEnemyCannotBeOutsideTheRunway();}
    @Override
    public String display() {
        if(state.itsOccupied()){return "|&|";}
        else{return "...";}
    }
    @Override
    public ArrayList<Enemy> enemiesInPlot(){throw new ThereCannotBeEnemiesInThisPlot();}
    public void addEnemyToPath(Enemy newEnemy){};
    public void setDefense(Defense defense){
        this.defense = defense;
        this.state = new Occupied();
    }

}
