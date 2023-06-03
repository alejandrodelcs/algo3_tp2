package edu.fiuba.algo3.entrega_1.health;

import edu.fiuba.algo3.modelo.health.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
