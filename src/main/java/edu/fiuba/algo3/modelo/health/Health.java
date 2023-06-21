package edu.fiuba.algo3.modelo.health;

public class Health {
    private int healthPoints;
    public Health(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public void takeDamage(int damage) {
        healthPoints -= damage;
    }
    public boolean entityDied() {
        return (healthPoints <= 0);
    }
    public boolean isLowerOrEqualThan(Health compareintHealth) {
        return (compareintHealth.isGreaterThan(healthPoints));
    }
    public boolean isGreaterThan(int healthPoints) {
        return this.healthPoints > healthPoints;
    }
}
