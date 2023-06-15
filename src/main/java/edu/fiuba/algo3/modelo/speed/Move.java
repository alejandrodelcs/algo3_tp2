package edu.fiuba.algo3.modelo.speed;

import java.awt.*;
import java.util.ArrayList;

public abstract class Move {

    Speed speed;
    public abstract Point execute(int positionInPath, ArrayList<Point> enemyPath);
}
