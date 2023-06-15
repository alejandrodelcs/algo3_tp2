package edu.fiuba.algo3.modelo.speed;

import edu.fiuba.algo3.modelo.gameboard.Plot;

import java.awt.*;
import java.util.ArrayList;

public class Speed {
    private int velocity;
    private int displacement;
    public Speed(int velocity){
        this.velocity = velocity;
    }

    public int getVelocity(){
        return this.velocity;
    }

    public Point enemyCoordinatesVelocityCalculator(int positionInPath, ArrayList<Point> enemyPath, Plot[][] plots){
        this.displacement = positionInPath + velocity;
        int sizePath = enemyPath.size() - 1;
        int index = Math.min(this.displacement, sizePath);
        long newX = (int) enemyPath.get(index).getX();;
        long newY = (int) enemyPath.get(index).getY();

        return new Point((int)newY,(int)newX);
    }

}
