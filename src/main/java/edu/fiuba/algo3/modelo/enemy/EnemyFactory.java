package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.exceptions.EnemyDoesNotExist;

public class EnemyFactory {

    public Enemy createEnemy(String enemyType) {
        switch (enemyType) {
            case "Spider":
                return new Spider(2, new Damage(2), 2);
            case "Ant":
                return new Ant(1, new Damage(1), 1);
            default:
                throw new EnemyDoesNotExist();
        }
    }
}
