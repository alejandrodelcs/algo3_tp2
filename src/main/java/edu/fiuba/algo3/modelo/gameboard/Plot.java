package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Plot {
    protected Tower tower;
    protected Enemy enemy;
    protected PlotAvailability state;

    public boolean readyToBuild(){return !state.itsOccupied();}//TODO: See if the boolean can be removed
    public void setDefense(Tower tower){
            this.tower = tower;
            this.state = new Occupied();
        }
    public abstract void setEnemy(ArrayList<Enemy> enemyList);
    public abstract String Show();
    public abstract void addEnemyToPath(Enemy newEnemy);

    public abstract ArrayList<Enemy> enemiesInPlot();
}
