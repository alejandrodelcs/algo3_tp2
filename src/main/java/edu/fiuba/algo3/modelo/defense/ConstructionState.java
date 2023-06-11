package edu.fiuba.algo3.modelo.defense;
import edu.fiuba.algo3.modelo.enemy.Enemy;
//import edu.fiuba.algo3.modelo.enemy.State;

import java.util.ArrayList;

public class ConstructionState implements State {

    private int remainingConstructionTurns;
    public ConstructionState(int towerConstructionTurns){
        this.remainingConstructionTurns = towerConstructionTurns;
    }
    @Override
    public void attack(Tower tower, ArrayList<Enemy> enemies){
        remainingConstructionTurns -= 1;
        if(remainingConstructionTurns < 0){
            tower.constructionFinished();
            tower.attack(enemies);
        }
    }

}
