package edu.fiuba.algo3.modelo.speed;

import edu.fiuba.algo3.modelo.gameboard.Plot;

import java.awt.*;
import java.util.ArrayList;

public class MoveInLShape extends Move {
    public MoveInLShape(int speed) {
        super(speed);
    }

    public Point execute(long x, long y, Plot[][] plots, ArrayList<Point> enemyPath){
        Point lastEnemyPath = enemyPath.get(enemyPath.size() - 1);
        int lastPathX = (int)lastEnemyPath.getX();
        int lastPathY = (int)lastEnemyPath.getY();

        int enemyX = (int)x;
        int enemyY = (int)y;
        for (int timesMoved = 0; timesMoved < speed; timesMoved++) {
            if (enemyY > lastPathY) {
                enemyX++;
            } else {
                enemyY++;
            }
        }
        enemyY = Math.min(enemyY, lastPathY);
        enemyX = Math.min(enemyX, lastPathX);
        return new Point(enemyY, enemyX);
    }
}
