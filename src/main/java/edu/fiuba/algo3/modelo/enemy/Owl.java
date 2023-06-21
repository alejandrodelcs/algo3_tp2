package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.HypotenuseMove;
import edu.fiuba.algo3.modelo.speed.Move;

public class Owl extends Enemy {
    private boolean destroyedTower;
    private Health healthToChangeMovement;
    public Owl(Damage damage, Health health, Credit credit, Move movement, Health healthToChangeMovement) {
        super(damage, health, credit, movement);
        destroyedTower = false;
        this.healthToChangeMovement = healthToChangeMovement;
    }
    @Override
    public Credit generateCredits() {
        return creditsReward;
    }

    @Override
    public String Show() {
        return "Owl";
    }

    @Override
    public void takeDamage(Damage damageReceived) {
        int amount = damageReceived.getQuantity();
        health.takeDamage(amount);
        if (health.isLowerOrEqualThan(healthToChangeMovement)) {
            move = new HypotenuseMove(move.getSpeed());
        }
    }

    public void destroyTower(Tower tower) {
//        if (!destroyedTower) { TODO: Need to implement destroy method in towers and uncomment or refactor this method
//            tower.destroy();
//            destroyedTower = true;
//        }
    }
}