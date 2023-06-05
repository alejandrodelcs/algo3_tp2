package edu.fiuba.algo3.modelo.gameboard;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;


import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    private Plot[][] plots;
    private ArrayList<Point> enemyPath;
    private ArrayList<Plot> availableBuildingPlots;

    //private Path firstEnemyPath;

    public GameBoard(Plot[][] expectedPlots) {
        plots = expectedPlots;
        enemyPath = constructPath();
        //plots = new Plot[mapaJsonObject.keySet().size()][mapaJsonObject.values().size()];


    }
    public boolean availableForBuilding(Point coordinates) {

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
        int range = tower.getRange(); // TODO: object attack has range
        tower.setPathRange(expectedRange(range, coordinates));

    }

    private ArrayList<Plot> expectedRange(int range, Point coordinates) {
        ArrayList<Plot> pathInRange = new ArrayList<Plot>();

        for (Point point: enemyPath) {
            double distance = 0;
            distance = point.distance(coordinates.getX(),coordinates.getY());
            if(distance <= range){
                Plot plotToCheck = plots[point.x][point.y];
                pathInRange.add(plotToCheck);
            }
        }
        return pathInRange;
    }

    public void spawnEnemy(ArrayList<Enemy> enemyArrayList){
        int x = (int) Math.round(enemyPath.get(0).getX());
        int y = (int) Math.round(enemyPath.get(0).getY());
        this.plots[x][y].setEnemy(enemyArrayList);

    }

    public ArrayList<Point> constructPath(){
        enemyPath = new ArrayList<Point>();
        Plot aPath = new Path();
        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                //TODO Check this below
                if(plots[i][j].getClass() == aPath.getClass()){
                    Point aPoint = new Point(j, i);
                    enemyPath.add(aPoint);
                }
            }
        }
        /*
        for (Point plot:enemyPath
             ) {
            System.out.print(((int) plot.getX()) + ", ");
            System.out.println(((int) plot.getY()));
        }*/
        return enemyPath;
    }

    public void printMap(){
        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                System.out.print(plots[i][j].Show());
            }
            System.out.println();
        }
    }

    public void moveEnemies(ArrayList<Enemy> enemies) {

        }
    }
}

