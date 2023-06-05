package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;

public class Ant extends Enemy {
    private static final int REWARD_THRESHOLD = 10;

    public Ant(int speed, Damage damage, int healthPoints) {
        super(speed, damage, healthPoints);
    }

    @Override
    public int getReward() {
        //TODO: missing the logic so that when player kills "REWARD_TRESHHOLD" or more ants, this method returns 2 instead of 1.
        return 1;
    }

    @Override
    public Credit generateCredits() {
        return new Credit(getReward());
    }

    @Override
    public String Show() {
        return "Ant";
    }
}

