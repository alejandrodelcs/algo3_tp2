package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.EnemyIsOutOfRange;

import java.util.ArrayList;

public class OperationalState implements State{
    @Override
    public void Attack(Tower tower, Enemy enemy){
       //enemy.takeDamage(tower.getDamage());
    }

}
