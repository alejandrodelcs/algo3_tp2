package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.util.ArrayList;

public class Path extends Plot{
    public ArrayList<Enemy> enemyArrayList;
    public Path(){this.state = new NotPlotAvailability();
    }
    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) {this.enemyArrayList =enemyList;}
    @Override
    public String Show() {
        return "ooo";
    }
}
