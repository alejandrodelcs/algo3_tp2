package edu.fiuba.algo3.modelo.attack;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Range;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.util.ArrayList;

public class SlowDown implements Attack{
    private final Range rangeAttack;
    private final double slowDownPercenatage = 0.5;
    public SlowDown(Range range){
        this.rangeAttack = range;
    }
    @Override

    public void performAttack(Defense defense, ArrayList<Enemy> enemies) {
        for(Enemy enemy : enemies) {
            if (rangeAttack.isInRange(defense, enemy)) {
                enemy.desacelerate(slowDownPercenatage);
            }
        }
    }
}

