package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.EnemyIsOutOfRange;

import java.util.ArrayList;

public class OperationalState implements State{
    private Range rangeAttack;
    public OperationalState(Range range){
        this.rangeAttack = range;
    }
    @Override
    public void Attack(Tower tower, ArrayList<Enemy> enemies){
        for (Enemy enemy:enemies
             ) {
            rangeAttack.isInRange(tower,enemy);
        }
    }

}
