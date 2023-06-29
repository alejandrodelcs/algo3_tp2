package edu.fiuba.algo3.modelo.gameboard;
import edu.fiuba.algo3.modelo.Logger;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard {
    private Plot[][] plots;
    private ArrayList<Point> enemyPath;
    public Point start;
    public Point finishLine;

    public GameBoard(Plot[][] expectedPlots) {
        plots = expectedPlots;
        enemyPath = constructPath();
        int x = (int) Math.round(enemyPath.get(enemyPath.size()-1).getX());
        int y = (int) Math.round(enemyPath.get(enemyPath.size()-1).getY());
        plots[y][x].setEnemy(new ArrayList<Enemy>());
/*        Image aTerrainImage = new Image(getClass().getResource("/img/path.png").toString(), true);
        terrainImages.add(aTerrainImage);
        aTerrainImage = new Image(getClass().getResource("/img/dirt.png").toString(), true);
        terrainImages.add(aTerrainImage);
        aTerrainImage = new Image(getClass().getResource("/img/rock2.png").toString(), true);
        terrainImages.add(aTerrainImage);*/
    }
    public boolean availableForBuilding(Point coordinates) {

        int x = (int) Math.round(coordinates.getX());
        int y = (int) Math.round(coordinates.getY());
        Plot plotToCheck = plots[x][y];
        return plotToCheck.readyToBuild();
    }
    public void buildDefense(Defense defense){
        Point coordinates = defense.getPoint();
        int x = (int) Math.round(coordinates.getX());
        int y = (int) Math.round(coordinates.getY());
        Plot plotToCheck = plots[x][y];
        plotToCheck.setDefense(defense);
        Logger.get().log("The player builds a "+defense.getClass().getSimpleName()+" at postion("+x+","+y+")");
    }
    public ArrayList<Enemy> getEnemies(){
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (Point point:enemyPath) {
            Plot plotToCheck = plots[point.y][point.x];
            enemies.addAll(plotToCheck.enemiesInPlot());
        }
        return enemies;
    }

    public Point getNextPath(Point plotCoords) {
        int indexOfOriginalPoint = enemyPath.indexOf(plotCoords);
        if (indexOfOriginalPoint + 1 < enemyPath.size()) {
            return enemyPath.get(indexOfOriginalPoint + 1);
        }
        return enemyPath.get(indexOfOriginalPoint);
    }

    public void spawnEnemy(ArrayList<Enemy> enemyArrayList){
        int x = (int) Math.round(enemyPath.get(0).getX());
        int y = (int) Math.round(enemyPath.get(0).getY());
        Point point = new Point(y,x);
        this.plots[y][x].setEnemy(enemyArrayList);
        if (!(enemyArrayList ==null)){
            for (Enemy enemy:enemyArrayList
                 ) {
//                enemy.updateCoordinates2(point);
            }
        }
    }

   public ArrayList<Point> constructPath(){
        enemyPath = new ArrayList<Point>();
        Plot aPath = new Path();
        Plot finishLine = new FinishLine();
        Plot aFinish = new FinishLine();
        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                //TODO Check this below
                if((plots[i][j].getClass() == aPath.getClass()) || (plots[i][j].getClass() == finishLine.getClass())){
                    Point aPoint = new Point(j, i);
                    enemyPath.add(aPoint);
                }
            }
        }
        setStart(enemyPath.get(0));
        setFinishLine(enemyPath.get(enemyPath.size()-1));
        return enemyPath;
    }

    private void setFinishLine(Point point) {
        this.finishLine = point;
    }

    public Point getFinishLine(){
        return finishLine;
    }
    public Point getStart(){
        return start;
    }
    private void setStart(Point point) {
        this.start = point;
    }

    public void printMap() {
        for (Object[] row : plots) {
            for (Object plot : row) {
                System.out.print(((Plot) plot).show());
            }
            System.out.println();
        }
        for (Point path : enemyPath) {
            int x = (int) Math.round(path.getX());
            int y = (int) Math.round(path.getY());
            System.out.println(plots[y][x].enemiesInPlot());
        }
    }

    public void moveEnemies() {
        long lastX = Math.round(enemyPath.get(enemyPath.size() - 1).getX());
        long lastY = Math.round(enemyPath.get(enemyPath.size() - 1).getY());

        for (int row = ((int)Arrays.stream(plots).count() - 1); row >= 0 ; row--) {
            for (int column = ((int)Arrays.stream(plots[0]).count() - 1); column >= 0 ; column--) {
                boolean shouldClear = false;
                ArrayList<Enemy> enemiesThatStayed = new ArrayList<>();

                long x = row;
                long y = column;
                for (Enemy enemy : plots[(int) y][(int) x].enemiesInPlot()) {
                    Point enemyCoordinates =  enemy.move(x, y, plots, enemyPath);

                    if (!enemy.enemyDied()) {
                        int newx = (int) Math.round(enemyCoordinates.getX());
                        int newy =(int) Math.round(enemyCoordinates.getY());
                        if(((newx != ((int) y)) || (newy != ((int) x))) && (lastY != y || lastX != x)) {
                            plots[newx][newy].addEnemyToPath(enemy);
                        } else {
                            enemiesThatStayed.add(enemy);
                        }
                    }
                }

                if (!shouldClear && (x != lastX || y != lastY)) {
                    shouldClear = true;
                }
                if (shouldClear) {
                    plots[(int) y][(int) x].enemiesInPlot().clear();
                    plots[(int) y][(int) x].enemiesInPlot().addAll(enemiesThatStayed);
                }
            }
        }

//        for (int i = enemyPath.size() - 1; i >= 0; i--) {
//            boolean shouldClear = false;
//
//            long x = Math.round(enemyPath.get(i).getX());
//            long y = Math.round(enemyPath.get(i).getY());
//            for (Enemy enemy : plots[(int) y][(int) x].enemiesInPlot()) {
//                Point enemyCoordinates =  enemy.move(i, enemyPath);
//
//                if (!enemy.enemyDied()) {
//                    int newx = (int) Math.round(enemyCoordinates.getX());
//                    int newy =(int) Math.round(enemyCoordinates.getY());
//                    if(((newx != ((int) y)) || (newy != ((int) x))) && (lastY != y || lastX != x)) {
//                        plots[newx][newy].addEnemyToPath(enemy);
//                    }
//                }
//            }
//
//            if (!shouldClear && (x != lastX || y != lastY)) {
//                shouldClear = true;
//            }
//            if (shouldClear) {
//                plots[(int) y][(int) x].enemiesInPlot().clear();
//
//            }
//        }

    }
    public Plot getPlot(int x, int y){
        return plots[x][y];
    }

    public boolean isEnemyPath(Point expectedEnemyPathCoordinates){
        return enemyPath.contains(expectedEnemyPathCoordinates);
    }
    public boolean isStart(Point point){
        return (point.getY() == enemyPath.get(0).getY()) && (point.getX() == enemyPath.get(0).getX());
    }
    public boolean isFinish(Point point){
        int lastPath = enemyPath.size() - 1;
        return (point.getY() == enemyPath.get(lastPath).getY()) && (point.getX() == enemyPath.get(lastPath).getX());
    }

    public ArrayList<Enemy> getEnemiesInThelastPath(){
        int finalX = (int) Math.round(enemyPath.get(enemyPath.size()-1).getX());
        int finalY = (int) Math.round(enemyPath.get(enemyPath.size()-1).getY());
        ArrayList<Enemy> enemies = new ArrayList<>(plots[finalY][finalX].enemiesInPlot());
        plots[finalY][finalX].enemiesInPlot().clear();
        return enemies;
    }

    public void destroyDefense(ArrayList<Defense> defenses, Enemy owl) {
        Defense defenseDestroyed = owl.destroyTower(defenses);
        if (defenseDestroyed!=null){
            removeDefense(defenseDestroyed);
            Point coordinates = defenseDestroyed.getPoint();
            int x = (int) Math.round(coordinates.getX());
            int y = (int) Math.round(coordinates.getY());
            Logger.get().log("The Owl destroyed a "+defenseDestroyed.getClass().getSimpleName()+" at position("+x+","+y+")");
        }
    }

    public void removeDefense(Defense defense){
        Point coordinates = defense.getPoint();
        int x = (int) Math.round(coordinates.getX());
        int y = (int) Math.round(coordinates.getY());
        Plot plotToCheck = plots[x][y];
        plotToCheck.removeDefense(defense);
    }

    public long height() {
        return Arrays.stream(plots).count();
    }

    public long width() {
        return Arrays.stream(plots[0]).count();
    }
    public ArrayList<Point> getEnemyPath() {
        return enemyPath;
    }

    public ArrayList<Enemy> getEnemiesInTheFirstPath() {
        int firstX = (int) Math.round(enemyPath.get(0).getX());
        int firstY = (int) Math.round(enemyPath.get(0).getY());
        ArrayList<Enemy> enemies = new ArrayList<>(plots[firstY][firstX].enemiesInPlot());
        return enemies;
    }
}