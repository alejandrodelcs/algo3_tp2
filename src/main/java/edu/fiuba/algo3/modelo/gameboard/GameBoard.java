package edu.fiuba.algo3.modelo.gameboard;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;


import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    private Plot[][] plots;
    private ArrayList<Plot> enemyPath;
    private ArrayList<Plot> avalableBuildingPlots;

    //private Path firstEnemyPath;

    public GameBoard() {
        plots = new Plot[5][5];

        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                plots[i][j] = new Dirt();
            }
        }
        plots[3][3] = new Stone();
    }
    public boolean availableForBuilding(Tower tower, Point coordinates) {

        int x = (int) Math.round(coordinates.getX());
        int y = (int) Math.round(coordinates.getY());
        Plot plotToCheck = plots[x][y];
        return plotToCheck.readyToBuild();
    }

    public void buildDefense(Tower tower, Point coordinates){
        int x = (int) Math.round(coordinates.getX());
        int y = (int) Math.round(coordinates.getY());
        Plot plotToCheck = plots[x][y];
        plotToCheck.setDefense(tower);
    }

    public void spawnEnemy(Enemy enemy, Point coordinates){
        int x = (int) Math.round(coordinates.getX());
        int y = (int) Math.round(coordinates.getY());
        Plot plotToCheck = plots[x][y];
        plotToCheck.setEnemy(enemy);
    }

    /*ArrayList<plot> RunAway = ArrayList<plot>();*/
}
