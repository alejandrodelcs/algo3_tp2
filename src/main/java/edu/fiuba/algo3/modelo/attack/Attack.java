package edu.fiuba.algo3.modelo.attack;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.player.Player;

import java.util.ArrayList;

public interface Attack {
    void performAttackOnEnemies(Defense defense, ArrayList<Enemy> enemies);

}
