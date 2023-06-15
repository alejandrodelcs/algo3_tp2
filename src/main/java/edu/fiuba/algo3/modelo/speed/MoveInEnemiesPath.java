package edu.fiuba.algo3.modelo.speed;

import edu.fiuba.algo3.modelo.gameboard.Plot;

import java.awt.*;
import java.util.ArrayList;

public class MoveInEnemiesPath extends Move{


    public MoveInEnemiesPath(int speed) {
        super(speed);
    }

    public Point execute(int positionInPath, ArrayList<Point> enemyPath){
        int sizePath = enemyPath.size() - 1;
        int index = Math.min(positionInPath + speed, sizePath);
        long newX = (int) enemyPath.get(index).getX();;
        long newY = (int) enemyPath.get(index).getY();

        return new Point((int)newY,(int)newX);
    }
}
