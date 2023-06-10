package edu.fiuba.algo3.modelo.gameboard;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.speed.Speed;


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
    public void buildDefense(Tower tower){
        Point coordinates = tower.getPoint();
        int x = (int) Math.round(coordinates.getX());
        int y = (int) Math.round(coordinates.getY());
        Plot plotToCheck = plots[x][y];
        plotToCheck.setDefense(tower);
    }
    public ArrayList<Enemy> getEnemies(){
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (Point point:enemyPath) {
            Plot plotToCheck = plots[point.y][point.x];
            enemies.addAll(plotToCheck.enemiesInPlot());
        }
        return enemies;
    }

    public void spawnEnemy(ArrayList<Enemy> enemyArrayList){
        int x = (int) Math.round(enemyPath.get(0).getX());
        int y = (int) Math.round(enemyPath.get(0).getY());
        Point point = new Point(y,x);
        this.plots[y][x].setEnemy(enemyArrayList);
        if (!(enemyArrayList ==null)){
            for (Enemy enemy:enemyArrayList
                 ) {
                enemy.updateCoordinates(point);
            }
        }
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
        for (Object[] row : plots) {
            for (Object plot : row) {
                System.out.print(((Plot) plot).display());
            }
            System.out.println();
        }
    }

    public void moveEnemies() {
        long lastX = Math.round(enemyPath.get(enemyPath.size() - 1).getX());
        long lastY = Math.round(enemyPath.get(enemyPath.size() - 1).getY());
        boolean shouldClear = false;
        for (int i = enemyPath.size() - 1; i > 0; i--) {
            long x = Math.round(enemyPath.get(i).getX());
            long y = Math.round(enemyPath.get(i).getY());
            for (Enemy enemy : plots[(int) y][(int) x].enemiesInPlot()) {
                Plot enemyCoordinates = enemy.updateCoordinates(i, enemyPath, plots);
                if (!enemy.enemyDied()) {
                    enemyCoordinates.addEnemyToPath(enemy);
                }
            }
            if (!shouldClear && (x != lastX || y != lastY)) {
                shouldClear = true;
            }
        }
        if (shouldClear) {
            plots[(int) lastY][(int) lastX].enemiesInPlot().clear();
        }
    }


    public ArrayList<Enemy> getEnemiesInThelastPath(){
        int finalX = (int) Math.round(enemyPath.get(enemyPath.size()-1).getX());
        int finalY = (int) Math.round(enemyPath.get(enemyPath.size()-1).getY());
        return plots[finalY][finalX].enemiesInPlot();
    }


    public boolean towerOperatingInPlot(Point coordinates) {
        int x = (int) coordinates.getX();
        int y = (int) coordinates.getY();
        Tower defense = plots[x][y].getDefense();
        return defense != null && defense.isItBuild();
    }
}