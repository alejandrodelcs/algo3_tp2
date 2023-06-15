package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.health.NotDamageable;
import edu.fiuba.algo3.modelo.speed.Speed;

public class MoleFactory implements EnemyFactory{
    @Override
    public Enemy createEnemy() {
        Speed someSpeed = new Speed(1);
        Damage someDamage = new Damage(2);
        Health someHealth = new NotDamageable(0);
        Credit someCredit = new Credit(0);

        return new Mole(someSpeed, someDamage, someHealth, someCredit);
    }
}