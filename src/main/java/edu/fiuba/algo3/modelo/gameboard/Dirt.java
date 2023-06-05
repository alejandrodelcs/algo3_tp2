package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.TheEnemyCannotBeOutsideTheRunway;

import java.util.ArrayList;

public class Dirt extends Plot{
    public Dirt(){state = new Available();}
    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) {throw new TheEnemyCannotBeOutsideTheRunway();}
    @Override
    public String Show() {
        return "...";
    }
}
