package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Move;
import java.util.ArrayList;

public class Ant extends Enemy {
    public Ant(Damage damage, Health health, Credit credit, Move movement) {
        super(damage, health, credit, movement);
        this.visible = true;
    }

    @Override
    public Credit generateCredits() {
        return creditsReward;
    }

    @Override
    public String show() {
        return ("Ant:\n\tSpeed: " + move.getSpeed() + "\n\tDamage: " + damage.getQuantity() + "\n\tHealth: " + health.getHealthPoints());
    }

    @Override
    public Defense destroyTower(ArrayList<Defense> defenses) {
        return null;
    }
}

