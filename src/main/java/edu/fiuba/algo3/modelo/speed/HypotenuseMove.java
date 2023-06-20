package edu.fiuba.algo3.modelo.speed;

import edu.fiuba.algo3.modelo.gameboard.Plot;

import java.awt.*;
import java.util.ArrayList;

public class HypotenuseMove extends Move{
    int specificCoordMoves;
    int specificOtherCoordMoves;
    public HypotenuseMove(int speed) {
        super(speed);
        this.specificCoordMoves = 0;
        this.specificOtherCoordMoves = 0;
    }

    @Override
    public Point execute(long x, long y, Plot[][] plots, ArrayList<Point> enemyPath){
        Point lastPath = enemyPath.get(enemyPath.size() - 1);
        int lastPathX = (int)lastPath.getX();
        int lastPathY = (int)lastPath.getY();

        int dx = Math.abs(lastPathX - (int)x);
        int dy = Math.abs(lastPathY - (int)y);
        int sx = (int)x < lastPathX ? 1 : -1;
        int sy = (int)y < lastPathY ? 1 : -1;
        int err = dx - dy;

        int currentSpeed = 0;
        int newX = (int)x;
        int newY = (int)y;

        while (true) {
            if (currentSpeed == speed) {
                newX = Math.min(newX, lastPathX);
                newY = Math.min(newY, lastPathY);
                return new Point(newY, newX);
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                newX += sx;
            }
            if (e2 < dx) {
                err += dx;
                newY += sy;
            }

            currentSpeed++;
        }
    }
}
