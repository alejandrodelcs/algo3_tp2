package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;
import edu.fiuba.algo3.modelo.exceptions.TheEnemyCannotBeOutsideTheRunway;
import edu.fiuba.algo3.modelo.exceptions.ThereCannotBeEnemiesInThisPlot;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Stone extends Plot{
    public ArrayList<Enemy> enemyArrayList;
    public Stone(){
        state = new NotPlotAvailability();
        this.enemyArrayList = new ArrayList<Enemy>();
    }

    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) { this.enemyArrayList = enemyList; }
    @Override
    public String display() {

        if(enemyArrayList == null || enemyArrayList.isEmpty()){
            return "xxx";
        }
        return "ooo";
    }
    @Override
    public ArrayList<Enemy> enemiesInPlot(){
        if (enemyArrayList != null) {
            return enemyArrayList;
        }
        return new ArrayList<Enemy>();}
    public void addEnemyToPath(Enemy newEnemy){this.enemyArrayList.add(newEnemy);};

    @Override
    public void setDefense(Defense defense) {
        throw new NonConstructibleArea();
    }
    @Override
    public Image printImage() {
        return new Image(getClass().getResource("/img/rock2.png").toString(),true);
    }
}
