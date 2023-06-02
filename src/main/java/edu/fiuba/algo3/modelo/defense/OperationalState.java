package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.EnemyIsOutOfRange;

public class OperationalState implements State{
    @Override
    public void Attack(Tower tower, Enemy enemy, boolean isOnRange){
        if(!isOnRange){
            throw new EnemyIsOutOfRange();
        }else{
            enemy.takeDamage(tower.getDamage());
        }
    }
}
