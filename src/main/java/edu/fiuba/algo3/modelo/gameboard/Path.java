package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Trap;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.enemy.EnemyFactory;
import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;
import edu.fiuba.algo3.modelo.exceptions.NonTowerContructibleArea;
import javafx.scene.image.Image;

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
    public String display() {

        if(enemyArrayList == null || enemyArrayList.isEmpty()){
            return "zzz";
        }
        return "ooo";
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
        if(defense.equals(Trap.class)){
            this.defense = defense;
            this.state = new Occupied();
        }
    }

    @Override
    public Image printImage() {
        return new Image(getClass().getResource("/img/path.png").toString(),true);
    }
}
