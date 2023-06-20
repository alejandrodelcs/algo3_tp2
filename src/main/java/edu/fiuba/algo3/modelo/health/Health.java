package edu.fiuba.algo3.modelo.health;

public interface Health {
    void takeDamage(int damage);
    boolean entityDied();
    boolean isLowerOrEqualThan(Health compareingHealth);

    boolean isGreaterThan(int healthPoints);
}
