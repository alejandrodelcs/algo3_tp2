package edu.fiuba.algo3.modelo.speed;

import edu.fiuba.algo3.modelo.gameboard.Plot;

import java.awt.*;
import java.util.ArrayList;

public class MoveInEnemiesPath extends Move{


    public MoveInEnemiesPath(int speed) {
        super(speed);
    }
    @Override
    public Point execute(long x, long y, Plot[][] plots, ArrayList<Point> enemyPath){
        if (turnsLeftToRestoreSpeed == 0) {
            speed =  initialSpeed;
        }
        int sizePath = enemyPath.size() - 1;
        int positionInPath = enemyPath.indexOf(new Point((int)x, (int)y));
        int index = Math.min(positionInPath + speed, sizePath);
        long newX = (int) enemyPath.get(index).getX();;
        long newY = (int) enemyPath.get(index).getY();

        if (turnsLeftToRestoreSpeed > 0) {
            turnsLeftToRestoreSpeed = turnsLeftToRestoreSpeed - 1;
        }
        return new Point((int)newY,(int)newX);
    }
}
