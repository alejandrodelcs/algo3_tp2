package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Health;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DamageTest {

    @Test
    public void damageAmountTenAppliedToADamageableEntityWithTenLifePointsEntityEndUpsDead(){
        //Arrange
        Damage damage = new Damage(10);
        Health health = new Health(10);
        //Act
        damage.applyDamage(health);
        //Assert
        Assertions.assertTrue(health.entityDied());
    }
}
