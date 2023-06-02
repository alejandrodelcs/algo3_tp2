package edu.fiuba.algo3.modelo.Defense;

import edu.fiuba.algo3.modelo.Enemy.Enemy;
import edu.fiuba.algo3.modelo.Exceptions.EnemyIsOutOfRange;

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
