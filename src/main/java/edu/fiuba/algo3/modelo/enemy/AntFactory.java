package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Damageable;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Speed;

public class AntFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        Speed someSpeed = new Speed(1);
        Damage someDamage = new Damage(1);
        Health someHealth = new Damageable(1);
        Credit someCredit = new Credit(1);

        return new Ant(someSpeed, someDamage, someHealth, someCredit);
    }
}
