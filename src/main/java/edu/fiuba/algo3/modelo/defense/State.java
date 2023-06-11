package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.util.ArrayList;

public interface State {
    void attack(Defense defense, ArrayList<Enemy> enemies);
}