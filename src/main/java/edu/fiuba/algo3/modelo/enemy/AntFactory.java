package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Move;
import edu.fiuba.algo3.modelo.speed.MoveInEnemiesPath;

public class AntFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {

        Move someMovement = new MoveInEnemiesPath(1);
        Damage someDamage = new Damage(1);
        Health someHealth = new Health(1);
        Credit someCredit = new Credit(1);

        return new Ant(someDamage, someHealth, someCredit, someMovement);
    }
}
