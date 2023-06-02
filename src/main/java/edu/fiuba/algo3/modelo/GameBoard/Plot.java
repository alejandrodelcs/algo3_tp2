package edu.fiuba.algo3.modelo.GameBoard;

import edu.fiuba.algo3.modelo.Defense.Tower;
import edu.fiuba.algo3.modelo.Enemy.Enemy;

public abstract class Plot {
    protected Tower tower;
    protected Enemy enemy;
    protected Occupiable state;
    public Plot(){;}
    public boolean readyToBuild(){return !state.itsOccupied();}//TODO: See if the boolean can be removed
    public void setDefense(Tower tower){
            this.tower = tower;
            this.state = new Ocuppied();
        }
    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
    }
}
