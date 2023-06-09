package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.awt.*;

public class Range {
    private int distance;
    public Range(int range){
        this.distance = range;}

    public boolean isInRange(Tower tower, Enemy enemy){
        return ( tower.getPoint().distance(enemy.getPoint()) <= distance );
    }
}
