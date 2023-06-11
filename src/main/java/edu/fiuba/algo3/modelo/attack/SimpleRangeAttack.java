package edu.fiuba.algo3.modelo.attack;

import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.defense.Range;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.util.ArrayList;

public class SimpleRangeAttack implements Attack{
    private Damage damage;
    private Range rangeAttack;

    public SimpleRangeAttack(Range range, Damage damage) {
        this.rangeAttack = range;
        this.damage = damage;
    }

    @Override
    public void performAttack(Defense defense, ArrayList<Enemy> enemies){
        int i = enemies.size() - 1;
        while (i >= 0 && !rangeAttack.isInRange(defense, enemies.get(i))) {
            i--;
        }
        if (i >= 0) {
            Enemy enemy = enemies.get(i);
            enemy.takeDamage(damage);
        }
    }
}