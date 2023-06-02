package edu.fiuba.algo3.modelo.gameboard;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    private Plot[][] plots;
    private ArrayList<Plot> enemyPath;
    private ArrayList<Plot> avalableBuildingPlots;

    //private Path firstEnemyPath;

    public GameBoard(JSONObject mapaJsonObject) {

        plots = new Plot[mapaJsonObject.keySet().size()][mapaJsonObject.values().size()];

        initializeMap(mapaJsonObject);
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
    private void initializeMap(JSONObject mapaJsonObject){
        for (int i = 1; i <= mapaJsonObject.size(); i++) {
            String key = String.valueOf(i);
            JSONArray rowArray = (JSONArray) mapaJsonObject.get(key);
            int j = 0;
            for (Object element : rowArray) {
                String value = element.toString();
                if (value.equals("Tierra")) {
                    plots[i-1][j] = new Dirt();
                } else if (value.equals("Pasarela")) {
                    plots[i-1][j] = new Path();
                } else if (value.equals("Rocoso")) {
                    plots[i-1][j] = new Stone();
                }
                j++;
            }
        }
            System.out.println();
        }
    public void printMap(){
        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                System.out.print(plots[i][j].Show());
            }
            System.out.println();
        }
        }
}

