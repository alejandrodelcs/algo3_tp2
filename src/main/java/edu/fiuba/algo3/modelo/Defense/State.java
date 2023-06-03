package edu.fiuba.algo3.modelo.Defense;

import edu.fiuba.algo3.modelo.Enemy.Enemy;

public interface State {
    void Attack(Tower tower, Enemy enemy, boolean isOnRange);
}
