package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.TheEnemyCannotBeOutsideTheRunway;
import edu.fiuba.algo3.modelo.exceptions.ThereCannotBeEnemiesInThisPlot;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.util.ArrayList;

public class Dirt extends Plot{
    public Dirt(){
        state = new Available();
        this.enemyArrayList = new ArrayList<>();
    }
    public ArrayList<Enemy> enemyArrayList;

    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) {this.enemyArrayList = enemyList;}
    @Override
    public String show() {
        return "Dirt";
    }
    @Override
    public ArrayList<Enemy> enemiesInPlot(){
        if (enemyArrayList != null) {
            return enemyArrayList;
        }
        return new ArrayList<Enemy>();
    }
    public void addEnemyToPath(Enemy newEnemy){this.enemyArrayList.add(newEnemy);};
    public void setDefense(Defense defense){
        this.defense = defense;
        this.state = new Occupied();
    }
    @Override
    public void removeDefense(Defense defense){
        this.state = new Available();
        this.defense = null;
    }
}
