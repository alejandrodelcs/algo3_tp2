package edu.fiuba.algo3.modelo.speed;

import edu.fiuba.algo3.modelo.gameboard.Plot;

import java.awt.*;
import java.util.ArrayList;

public abstract class Move {

    int speed;
    public Move(int speed) {
        this.speed = speed;
    }
    public abstract Point execute(long x, long y, Plot[][] plots, ArrayList<Point> enemyPath);

    public void accelerate(int newVelocity) {
        speed = newVelocity;
    }

    public void desaccelerate(double percentage) {
        speed = (int)Math.floor(speed*percentage);
    }

    public int getSpeed() {
        return speed;
    }
}
