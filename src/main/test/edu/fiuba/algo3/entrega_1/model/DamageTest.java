package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Damageable;
import edu.fiuba.algo3.modelo.health.Health;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DamageTest {


    @Test
    public void damageAmountTenAppliedToADamageableEntityWithTenLifePointsEntityEndUpsDead(){
        Damage damage = new Damage(10);
        Health health = new Damageable(10);

        damage.applyDamage(health);

        Assertions.assertTrue(health.entityDied());

    }
}
