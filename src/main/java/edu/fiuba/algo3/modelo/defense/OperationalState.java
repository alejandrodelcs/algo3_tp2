package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.attack.Attack;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.util.ArrayList;

public class OperationalState implements State {
    private Attack attack;
    public OperationalState(Attack newAttack){
        this.attack = newAttack;
    }
    @Override
    public void attack(Defense defense,ArrayList<Enemy> enemies){
        attack.performAttack(defense,enemies);
    }
}
