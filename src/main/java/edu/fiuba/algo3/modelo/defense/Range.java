package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.awt.*;
import java.util.ArrayList;

public class Range {
    private int distance;
    public Range(int range){
        this.distance = range;}

    public boolean isInRange(Defense defense, Enemy enemy){
        return ( defense.getPoint().distance(enemy.getPoint()) <= distance );
    }

    public ArrayList<Point> plotsInRange(Point originPoint) {
        ArrayList<Point> plotsInRange = new ArrayList<>();
        int originPointRow = (int) originPoint.getX();
        int originPointColumn = (int) originPoint.getY();
        for (int row = (originPointRow - distance); row <= originPointRow + distance; row++) {
            if (row >= 0) {
                for (int column = (originPointColumn - distance); column <= originPointColumn + distance; column++) {
                    if (column >= 0) {
                        Point pointToEvaluate = new Point(row, column);
                        if (originPoint.distance(pointToEvaluate) <= distance) {
                            plotsInRange.add(pointToEvaluate);
                        }
                    }
                }
            }
        }
        return plotsInRange;
    }
}
