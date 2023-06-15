package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Move;

import java.awt.*;
import java.util.ArrayList;

public class Owl extends Enemy {
    private boolean destroyedTower;

    public Owl(Damage damage, Health health, Credit credit, Move movement) {
        super(damage, health, credit, movement);
        destroyedTower = false;
    }

    @Override
    public void acelerate() {
        // Move the owl towards the end
    }

    @Override
    public Credit generateCredits() {
        return creditsReward;
    }

    @Override
    public String Show() {
        return "Owl";
    }

    public void destroyTower(Tower tower) {
//        if (!destroyedTower) { TODO: Need to implement destroy method in towers and uncomment or refactor this method
//            tower.destroy();
//            destroyedTower = true;
//        }
    }
}