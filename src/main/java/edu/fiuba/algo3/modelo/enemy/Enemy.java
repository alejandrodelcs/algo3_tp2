package edu.fiuba.algo3.modelo.enemy;/*
// ASUMO que lo llamado "enrgía" en el enunciado, es lo equivalente a los puntos de vida de los enemigos, considerando que a energia no se le da otro uso, y no se habla en mingun lado sobre los puntosd e vida de los enemigos.

abstract class edu.fiuba.algo3.modelo.Enemy.Enemy {
    private int speed;
    private int damagePoints;
    private int energy;

    public edu.fiuba.algo3.modelo.Enemy.Enemy(int speed, int damagePoints, int energy) {
        this.speed = speed;
        this.damagePoints = damagePoints;
        this.energy = energy;
    }

    public abstract int calculateCredits();

    public void recieveDamage(int damageRecieved) {
        this.energy -= damageRecieved;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getDamagePoints() {
        return damagePoints;
    }

    public int getEnergy() {
        return energy;
    }
}

class edu.fiuba.algo3.modelo.Enemy.Ant extends edu.fiuba.algo3.modelo.Enemy.Enemy {
    private static final int CREDITS_PER_ANT = 1;
    private static final int ADITIONAL_CREDITS = 2;
    private static final int ANTS_LIMIT = 10;

    public static int antsCounter = 0;

    public edu.fiuba.algo3.modelo.Enemy.Ant() {
        super(1, 1, 1);
    }

    @Override
    public int calculateCredits() {
        antsCounter++;

        if (antsCounter <= ANTS_LIMIT) {
            return CREDITS_PER_ANT;
        } else {
            return ADITIONAL_CREDITS;
        }
    }
}

class edu.fiuba.algo3.modelo.Enemy.Spider extends edu.fiuba.algo3.modelo.Enemy.Enemy {
    private static final int CREDITS_RANGE_MIN = 0;
    private static final int CREDITS_RANGE_MAX = 10;

    public edu.fiuba.algo3.modelo.Enemy.Spider() {
        super(2, 2, 2);
    }

    @Override
    public int calculateCredits() {
        return (int) (Math.random() * (CREDITS_RANGE_MAX - CREDITS_RANGE_MIN + 1)) + CREDITS_RANGE_MIN;
    }
}*/

public abstract class Enemy {
    private int speed;
    private int damage;
    private int health;

    public Enemy(int speed, int damage, int health) {
        this.speed = speed;
        this.damage = damage;
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    public abstract int getReward();

    public abstract void destroy();

    public abstract int generateCredits();
}
