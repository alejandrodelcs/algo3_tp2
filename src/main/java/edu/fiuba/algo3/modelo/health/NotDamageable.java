package edu.fiuba.algo3.modelo.health;

public class NotDamageable implements Health{
    private int healthPoints;
    public NotDamageable(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    @Override
    public void takeDamage(int damage) {
        // Do nothing
    }

    @Override
    public boolean entityDied() {
        return false;
    }

}
