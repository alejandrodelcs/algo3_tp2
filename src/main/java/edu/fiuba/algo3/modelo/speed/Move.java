package edu.fiuba.algo3.modelo.speed;

import java.awt.*;
import java.util.ArrayList;

public abstract class Move {

    int speed;
    public Move(int speed) {
        this.speed = speed;
    }
    public abstract Point execute(int positionInPath, ArrayList<Point> enemyPath);

    public void accelerate(int newVelocity) {
        speed = newVelocity;
    }
}
