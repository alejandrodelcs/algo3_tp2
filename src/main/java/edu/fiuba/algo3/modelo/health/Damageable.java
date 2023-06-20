package edu.fiuba.algo3.modelo.health;

public class Damageable implements Health{
    private int healthPoints;
    public Damageable(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    @Override
    public void takeDamage(int damage) {
        healthPoints -= damage;
    }
    public boolean entityDied() {
        return (healthPoints <= 0);
    }
}
