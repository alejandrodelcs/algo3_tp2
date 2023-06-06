package edu.fiuba.algo3.modelo.gameboard;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;


import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    private Plot[][] plots;
    private ArrayList<Point> enemyPath;

    public GameBoard(Plot[][] expectedPlots) {
        plots = expectedPlots;
        enemyPath = constructPath();

        int x = (int) Math.round(enemyPath.get(enemyPath.size()-1).getX());
        int y = (int) Math.round(enemyPath.get(enemyPath.size()-1).getY());
        plots[y][x].setEnemy(new ArrayList<Enemy>());
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
    }
    public ArrayList<Enemy> enemiesInRange(Tower tower){
        ArrayList<Enemy> enemiesInrange = new ArrayList<Enemy>();
        ArrayList<Plot> pathsInRange = expectedRange(tower.getRange(),tower.getPoint());
        for (Plot path:pathsInRange) {
            enemiesInrange.addAll(path.enemiesInPlot());
        }
        return enemiesInrange;
    }
    public ArrayList<Plot> expectedRange(int range, Point coordinates) {
        ArrayList<Plot> pathInRange = new ArrayList<Plot>();

        for (Point point: enemyPath) {
            double distance = 0;
            distance = point.distance(coordinates.getX(),coordinates.getY());
            if(distance <= range){
                Plot plotToCheck = plots[point.y][point.x];
                pathInRange.add(plotToCheck);
            }
        }
        return pathInRange;
    }
    public void spawnEnemy(ArrayList<Enemy> enemyArrayList){
        int x = (int) Math.round(enemyPath.get(0).getX());
        int y = (int) Math.round(enemyPath.get(0).getY());
        this.plots[y][x].setEnemy(enemyArrayList);

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
//        for (Point plot:enemyPath
//             ) {
//            System.out.print(((int) plot.getX()) + ", ");
//            System.out.println(((int) plot.getY()));
//        }
        return enemyPath;
    }
    public void printMap(){
        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                System.out.print(plots[i][j].display());
            }
            System.out.println();
        }
    }
    public void moveEnemies(){
        int pathListIndex = enemyPath.size() - 1;

        for (int i = (enemyPath.size() - 1); i >= 0; i--) {
            int x = (int) Math.round(enemyPath.get(i).getX());
            int y = (int) Math.round(enemyPath.get(i).getY());
            ArrayList<Enemy> enemiesInPath = new ArrayList<>(plots[y][x].enemiesInPlot());
            for (Enemy enemy : enemiesInPath) {
                int listEnemyIndex = pathListIndex + enemy.getSpeed();
                if (listEnemyIndex <= enemyPath.size() - 1) {
                    Point newPathCoordinates = enemyPath.get(listEnemyIndex);
                    int newX = (int) Math.round(newPathCoordinates.getX());
                    int newY = (int) Math.round(newPathCoordinates.getY());
                    if(!(enemy.enemyDied())) {
                        plots[newY][newX].addEnemyToPath(enemy);
                    }
                } else {
                    if ((listEnemyIndex - enemyPath.size() + 1) < enemy.getSpeed()) {
                        Point newPathCoordinates = enemyPath.get(enemyPath.size() - 1);
                        int newX = (int) Math.round(newPathCoordinates.getX());
                        int newY = (int) Math.round(newPathCoordinates.getY());
                        plots[newY][newX].addEnemyToPath(enemy);
                    }
                }
            }
            if (enemyPath.get(enemyPath.size() - 1).getX() != x || enemyPath.get(enemyPath.size() - 1).getY() != y) {
                plots[y][x].enemiesInPlot().clear();
            }
            pathListIndex--;
        }
    }
    public ArrayList<Enemy> getEnemiesInThelastPath(){
        int finalX = (int) Math.round(enemyPath.get(enemyPath.size()-1).getX());
        int finalY = (int) Math.round(enemyPath.get(enemyPath.size()-1).getY());
        return plots[finalY][finalX].enemiesInPlot();
    }
}