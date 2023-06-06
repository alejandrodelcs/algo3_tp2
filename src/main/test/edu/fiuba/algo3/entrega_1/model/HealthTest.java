package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.health.Damageable;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.health.NotDamageable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HealthTest {
    @Test
    public void test01ADamageableObjectWith100HealthPointsIsAlive() {
        Health health = new Damageable(100);
        assertFalse(health.entityDied());
    }

    @Test
    public void test02ADamageableObjectWith100HealthPointsDamagedWith50PointsIsAlive() {
        Health health = new Damageable(100);
        health.takeDamage(50);
        assertFalse(health.entityDied());
    }

    @Test
    public void test03ADamageableObjectWith100HealthPointsIsDamagedWith50PointsTwiceIsDead() {
        Health health = new Damageable(100);
        health.takeDamage(50);
        health.takeDamage(50);
        assertTrue(health.entityDied());
    }

    @Test
    public void test04ANotDamageableObjectWith100HealthPointsIsAlive() {
        Health health = new NotDamageable(100);
        assertFalse(health.entityDied());
    }

    @Test
    public void test05ANotDamageableObjectWith100HealthPointsDamagedWith50PointsIsAlive() {
        Health health = new NotDamageable(100);
        health.takeDamage(50);
        assertFalse(health.entityDied());
    }

    @Test
    public void test06ANotDamageableObjectWith100HealthPointsDamagedWith50PointsTwiceIsAlive() {
        Health health = new NotDamageable(100);
        health.takeDamage(50);
        health.takeDamage(50);
        assertFalse(health.entityDied());
    }
}