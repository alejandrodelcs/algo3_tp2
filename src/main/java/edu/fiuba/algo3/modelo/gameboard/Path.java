package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.util.ArrayList;

public class Path extends Plot{
    public ArrayList<Enemy> enemyArrayList;
    public Path(){this.state = new NotPlotAvailability();
    }
    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) {this.enemyArrayList =enemyList;}
    public void addEnemyToPath(Enemy newEnemy){ this.enemyArrayList.add(enemy); }
    @Override
    public String Show() {
        return "ooo";
    }
    @Override
    public ArrayList<Enemy> enemiesInPlot(){return enemyArrayList;}
}
