package edu.fiuba.algo3.modelo.speed;

import edu.fiuba.algo3.modelo.gameboard.Plot;

import java.awt.*;
import java.util.ArrayList;

public abstract class Move {
    int initialSpeed;
    int speed;
    int turnsLeftToRestoreSpeed;
    public Move(int speed) {
        this.speed = speed;
        this.initialSpeed = speed;
        this.turnsLeftToRestoreSpeed = 0;
    }
    public abstract Point execute(long x, long y, Plot[][] plots, ArrayList<Point> enemyPath);

    public void accelerate(int newVelocity) {
        speed = newVelocity;
        initialSpeed = newVelocity;
    }

    public void decelerate(double percentage) {
        speed = (int)Math.floor(speed*percentage);
        turnsLeftToRestoreSpeed = 1;
    }

    public int getSpeed() {
        return speed;
    }
    public boolean isSlowedDown() {
        return (speed < initialSpeed);
    }
}
