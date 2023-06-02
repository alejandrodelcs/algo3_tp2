package edu.fiuba.algo3.entrega_1.health;

import edu.fiuba.algo3.modelo.health.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HealthTest {
    @Test
    public void test01WhenInstancingADamageableObjectWith100OfLifeIsAlive() {
        Health health = new Damageable(100);
        assertFalse(health.entityDied());
    }
}
