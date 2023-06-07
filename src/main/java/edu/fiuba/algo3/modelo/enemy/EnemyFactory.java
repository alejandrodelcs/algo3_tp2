package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.WhiteTower;
import edu.fiuba.algo3.modelo.exceptions.EnemyDoesNotExist;
import edu.fiuba.algo3.modelo.health.Damageable;

public class EnemyFactory {

    public Enemy createSpider() {
        return new Spider(2, new Damage(2), 2);
    }
    public Enemy createAnt() {
        return new Ant(1, new Damage(1), 1);
    }
}
