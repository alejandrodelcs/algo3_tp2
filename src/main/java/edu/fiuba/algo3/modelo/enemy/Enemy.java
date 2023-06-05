package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.*;

public abstract class Enemy {
    private int speed;
    private Damage damage;
    private Health health;

    public Enemy(int speed, Damage damage, int healthPoints) {
        this.speed = speed;
        this.damage = damage;
        this.health = new Damageable(healthPoints);
    }

    public int getSpeed() {
        return speed;
    }

    public Damage getDamage() {
        return damage;
    }

    public Health getHealth() {
        return health;
    }

    public void takeDamage(int amount) {
        health.takeDamage(amount);
        if (health.entityDied()) {
            generateCredits();
        }
    }

    public abstract int getReward();

    public Boolean enemyDied(){
        return health.entityDied();
    };

    public abstract Credit generateCredits();

    public abstract String Show();
}