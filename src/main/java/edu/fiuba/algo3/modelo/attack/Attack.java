package edu.fiuba.algo3.modelo.attack;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import java.awt.Point;


import java.util.ArrayList;

public interface Attack {
    void performAttackOnEnemies(Defense defense, ArrayList<Enemy> enemies);
    ArrayList<Point> getPlotsInRange(Point point);
}
