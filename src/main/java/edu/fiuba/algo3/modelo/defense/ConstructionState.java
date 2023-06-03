package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.TowerIsUnderConstruction;

public class ConstructionState implements State{
    @Override
    public void Attack(Tower tower, Enemy enemy, boolean isOnRange){
        throw new TowerIsUnderConstruction();
    }
}
