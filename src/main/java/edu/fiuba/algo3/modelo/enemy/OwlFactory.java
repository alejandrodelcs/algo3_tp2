package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Damageable;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Speed;

public class OwlFactory implements EnemyFactory{
    @Override
    public Enemy createEnemy() {
        Speed someSpeed = new Speed(5);
        Damage someDamage = new Damage(0);
        Health someHealth = new Damageable(5);
        Credit someCredit = new Credit(0); //WE ASSUME THAT OWLS DO NOT GIVE CREDITS WHEN KILLED AS IT IS NOT SPECIFIED

        return new Owl(someSpeed, someDamage, someHealth, someCredit);
    }

}
