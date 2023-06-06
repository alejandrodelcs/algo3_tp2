package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.enemy.EnemyFactory;

import java.util.ArrayList;

public class Path extends Plot{
    public ArrayList<Enemy> enemyArrayList;
    public Path(){
        this.state = new NotPlotAvailability();
        this.enemyArrayList = new ArrayList<Enemy>();
    }
    @Override
    public void setEnemy(ArrayList<Enemy> enemyList) {this.enemyArrayList = enemyList;}
    public void addEnemyToPath(Enemy newEnemy){ this.enemyArrayList.add(newEnemy); }


    @Override
    public String Show() {

        if(enemyArrayList == null || enemyArrayList.isEmpty()){
            return "zzz";
        }
        return "ooo";
    }
    @Override
    public ArrayList<Enemy> enemiesInPlot(){return enemyArrayList;}

    public void initilizeArrayList(){
        //enemyArrayList = new;
    }
}
