package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.SandyTrap;
import edu.fiuba.algo3.modelo.defense.Trap;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class Path extends Plot{
    public ArrayList<Enemy> enemyArrayList;

    public Path(){
        this.state = new Available();
        this.enemyArrayList = new ArrayList<Enemy>();
    }
    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) {this.enemyArrayList = enemyList;}
    public void addEnemyToPath(Enemy newEnemy){ this.enemyArrayList.add(newEnemy); }
    @Override
    public String show() {
        return "Path";
    }
    @Override
    public ArrayList<Enemy> enemiesInPlot(){
        if (enemyArrayList != null) {
            return enemyArrayList;
        }
        return new ArrayList<Enemy>();
    }

    @Override
    public void setDefense(Defense defense) {
        if (defense.getClass() == SandyTrap.class) {
            this.defense = defense;
            this.state = new Occupied();
        }
    }

    @Override
    public void removeDefense(Defense defense) {
        this.state = new Available();
    }
}
