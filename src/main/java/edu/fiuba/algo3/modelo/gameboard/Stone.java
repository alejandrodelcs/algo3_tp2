package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.TheEnemyCannotBeOutsideTheRunway;
import edu.fiuba.algo3.modelo.exceptions.ThereCannotBeEnemiesInThisPlot;

import java.util.ArrayList;

public class Stone extends Plot{
    public Stone(){state = new NotPlotAvailability();}

    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) {throw new TheEnemyCannotBeOutsideTheRunway();}
    @Override
    public String display() {
        return "xxx";
    }
    @Override
    public ArrayList<Enemy> enemiesInPlot(){throw new ThereCannotBeEnemiesInThisPlot();}
    public void addEnemyToPath(Enemy newEnemy){};


}
