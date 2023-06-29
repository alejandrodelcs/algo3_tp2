package edu.fiuba.algo3.modelo.enemy;


import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.HypotenuseMove;
import edu.fiuba.algo3.modelo.speed.Move;


import java.util.ArrayList;

public class Owl extends Enemy {
    //private boolean destroyedTower;
    private Health healthToChangeMovement;
    public Owl(Damage damage, Health health, Credit credit, Move movement, Health healthToChangeMovement) {
        super(damage, health, credit, movement);
        //destroyedTower = false;
        this.healthToChangeMovement = healthToChangeMovement;
        this.visible = true;
    }
    @Override
    public Credit generateCredits() {
        return creditsReward;
    }

    @Override
    public String show() {
        return ("Owl:\n\tSpeed: " + move.getSpeed() + "\n\tDamage: " + damage.getQuantity() + "\n\tHealth: " + health.getHealthPoints());

    }

    @Override
    public void takeDamage(Damage damageReceived) {
        int amount = damageReceived.getQuantity();
        health.takeDamage(amount);
        if (health.isLowerOrEqualThan(healthToChangeMovement)) {
            move = new HypotenuseMove(move.getSpeed());
        }
    }
    @Override
    public Defense destroyTower(ArrayList<Defense> defenses) {
        boolean towerDestroyed=false;
        for (int i=0;i<defenses.size() && !towerDestroyed;i++){
            Defense defense = defenses.get(i);
            if (defense instanceof Tower){
                defenses.remove(i);
                defense.destructed();
                return defense;
            }
        }
        return null;
    }

}