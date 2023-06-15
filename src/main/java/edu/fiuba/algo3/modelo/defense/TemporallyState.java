package edu.fiuba.algo3.modelo.defense;
import edu.fiuba.algo3.modelo.attack.Attack;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.turn.Turn;

import java.util.ArrayList;


public class TemporallyState implements State{
    private int TurnAlive;
    private Attack attack;
    public TemporallyState(Attack newAttack,int operativeTurns){
        this.attack = newAttack;
        this.TurnAlive = operativeTurns;
    }
    @Override
    public void attack(Defense defense, ArrayList<Enemy> enemies) {
        TurnAlive--;
        if(TurnAlive != 0) {
            attack.performAttack(defense, enemies);
        }
        else{defense.destructed();};
        }
}
