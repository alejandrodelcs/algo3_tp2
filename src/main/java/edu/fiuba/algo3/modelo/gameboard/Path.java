package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.TheEnemyCannotBeOutsideTheRunway;

public class Path extends Plot{
    public Path(){this.state = new NotOccupiable();
    }
    @Override
    public void setEnemy(Enemy enemy) {this.enemy = enemy;}
    @Override
    public String Show() {
        return "ooo";
    }
}
