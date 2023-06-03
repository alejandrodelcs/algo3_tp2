package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.damage.Damage;

import java.util.Random;

public class Spider extends Enemy {
    private static final int CREDITS_MIN_RANGE = 0;
    private static final int CREDITS_MAX_RANGE = 10;

    public Spider(int speed, Damage damage, int healthPoints) {
        super(speed, damage, healthPoints);
    }

    @Override
    public int getReward() {
        return 0; // Not used for edu.fiuba.algo3.modelo.Enemy.Spider enemy
    }

    @Override
    public int generateCredits() {
        Random random = new Random();
        return random.nextInt(CREDITS_MAX_RANGE - CREDITS_MIN_RANGE + 1) + CREDITS_MIN_RANGE;
    }
}
