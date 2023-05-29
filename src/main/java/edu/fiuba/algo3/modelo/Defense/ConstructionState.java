package edu.fiuba.algo3.modelo.Defense;

public class ConstructionState implements State{
    @Override
    public void Attack(Tower tower, Enemy enemy){
        throw new TowerIsUnderConstruction();
    }
}
