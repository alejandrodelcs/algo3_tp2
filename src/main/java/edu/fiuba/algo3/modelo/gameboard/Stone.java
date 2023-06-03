package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.TheEnemyCannotBeOutsideTheRunway;

public class Stone extends Plot{
    public Stone(){state = new NotOccupiable();}

    @Override
    public void setEnemy(Enemy enemy) {throw new TheEnemyCannotBeOutsideTheRunway();}
    @Override
    public String Show() {
        return "xxx";
    }

}
