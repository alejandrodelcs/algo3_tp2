package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Damageable;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Move;
import edu.fiuba.algo3.modelo.speed.MoveInEnemiesPath;

public class SpiderFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {

        Move someMovement = new MoveInEnemiesPath(2);
        Damage someDamage = new Damage(2);
        Health someHealth = new Damageable(2);
        Credit someCredit = new Credit();

        return new Spider(someDamage, someHealth, someCredit, someMovement);
    }
}
