package edu.fiuba.algo3.modelo.attack;

import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Range;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.util.ArrayList;

public class SimpleRangeAttack implements Attack{
    private Range rangeAttack;

    public SimpleRangeAttack(Range range) {
        this.rangeAttack = range;
    }

    @Override
    public void performAttack(Defense defense, ArrayList<Enemy> enemies){
        int i = enemies.size() - 1;
        while (i >= 0 && !rangeAttack.isInRange(defense, enemies.get(i))) {
            i--;
        }
        if (i >= 0) {
            Enemy enemy = enemies.get(i);
            enemy.takeDamage(defense.getDamage());
        }
    }
}
