package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.util.ArrayList;

public abstract class Plot {
    protected Defense defense;
    protected Enemy enemy;
    protected PlotAvailability state;

    public boolean readyToBuild(){return !state.itsOccupied();}//TODO: See if the boolean can be removed
    public abstract void setDefense(Defense defense);
    public abstract void  setEnemy(ArrayList<Enemy> enemyList);
    public abstract String show();
    public abstract void addEnemyToPath(Enemy newEnemy);
    public abstract ArrayList<Enemy> enemiesInPlot();
    public Defense getDefense(){return defense;}
    public abstract void removeDefense(Defense defense) ;
}
