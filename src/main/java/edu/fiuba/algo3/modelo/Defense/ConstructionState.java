package edu.fiuba.algo3.modelo.Defense;

import edu.fiuba.algo3.modelo.Enemy.Enemy;

public class ConstructionState implements State{
    @Override
    public void Attack(Tower tower, Enemy enemy, boolean isOnRange){
        throw new TowerIsUnderConstruction();
    }
}
