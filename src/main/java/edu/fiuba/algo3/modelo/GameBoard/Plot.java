package edu.fiuba.algo3.modelo.GameBoard;

import edu.fiuba.algo3.modelo.Defense.Tower;
import edu.fiuba.algo3.modelo.Enemy.Enemy;

public abstract class Plot {
    protected Tower tower;
    protected Enemy enemy;
    protected boolean occupy;
    public Plot(){
        this.occupy = false;
    }
    public abstract boolean readyToBuild();
    boolean itsOccupied(){ return occupy;}
    public void setDefense(Tower tower){
            this.tower = tower;
        }
    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
    }
}
