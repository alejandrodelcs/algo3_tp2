package edu.fiuba.algo3.modelo.speed;

import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Damageable;

import java.awt.*;
import java.util.ArrayList;

public interface Speed {
    public Point moveWithSpeed(int listIndex, ArrayList<Point> coordinatesList);

}

/*package edu.fiuba.algo3.modelo.speed;

import edu.fiuba.algo3.modelo.gameboard.Plot;

import java.awt.*;
import java.util.ArrayList;

public class Speed {
    private int speed;
    private int displacement;
    public Speed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }

    public Point enemyCoordinatesVelocityCalculator(int positionInPath, ArrayList<Point> enemyPath, Plot[][] plots){
        this.displacement = positionInPath + speed;
        int sizePath = enemyPath.size() - 1;
        int index = Math.min(this.displacement, sizePath);
        long newX = (int) enemyPath.get(index).getX();;
        long newY = (int) enemyPath.get(index).getY();

        return new Point((int)newY,(int)newX);
    }

}*/
