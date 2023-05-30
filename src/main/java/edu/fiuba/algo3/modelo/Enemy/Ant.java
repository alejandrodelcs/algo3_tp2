package edu.fiuba.algo3.modelo.Enemy;

public class Ant extends Enemy {
    private static final int REWARD_THRESHOLD = 10;

    public Ant() {
        super(1, 1, 1);
    }

    @Override
    public int getReward() {
        if (getHealth() > REWARD_THRESHOLD) {
            return 2;
        }
        return 1;
    }

    @Override
    public void destroy() {
        // Destroy logic for edu.fiuba.algo3.modelo.Enemy.Ant enemy
    }

    @Override
    public int generateCredits() {
        return getReward();
    }
}

