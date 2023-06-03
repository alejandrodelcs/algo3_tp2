package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Damageable;
import edu.fiuba.algo3.modelo.health.Health;

public class DamageTest {

    public void test01(){
        Damage damage = new Damage(10);
        Health health = new Damageable(10);

        damage.applyDamage(10);
        health.equals(0);


    }
}
