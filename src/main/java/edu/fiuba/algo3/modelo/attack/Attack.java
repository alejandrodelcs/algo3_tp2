package edu.fiuba.algo3.modelo.attack;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.util.ArrayList;

public interface Attack {
    void performAttack(Defense defense, ArrayList<Enemy> enemies);
}
