package edu.fiuba.algo3.modelo.enemy;

public class Ant extends Enemy {
    private static final int REWARD_THRESHOLD = 10;

    public Ant(int speed, int damage, int healthPoints) {
        super(speed, damage, healthPoints);
    }

    @Override
    public int getReward() {
        //TODO: missing the logic so that when player kills "REWARD_TRESHHOLD" or more ants, this method returns 2 instead of 1.
        return 1;
    }

    @Override
    public int generateCredits() {
        return getReward();
    }
}

