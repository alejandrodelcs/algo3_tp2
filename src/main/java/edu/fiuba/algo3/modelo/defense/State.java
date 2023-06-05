package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.enemy.Enemy;

public interface State {
    void Attack(Tower tower, Enemy enemy);

    void update(Tower tower);
}
