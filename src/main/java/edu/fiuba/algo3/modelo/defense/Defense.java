package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.awt.*;
import java.util.ArrayList;

public abstract class Defense {
    protected Credit credits;
    protected Point coordinates;
    protected State state;

    public Credit getCredits() {
        return credits;
    }
    public Point getPoint() {
        return coordinates;
    }
}
