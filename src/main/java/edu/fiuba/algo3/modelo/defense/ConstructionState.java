package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.TowerIsUnderConstruction;

public class ConstructionState implements State{

    private int cont;
    private boolean state;
    public ConstructionState(int cont){
        this.cont = cont;
    }
    @Override
    public void Attack(Tower tower, Enemy enemy){
        cont -= 1;
        if(cont >= 0){
            return;
            //throw new TowerIsUnderConstruction();
        }
        tower.constructionFinished();
        tower.attack(enemy);
    }
    @Override
    public boolean isItBuild(){
        return false;
    }

}
