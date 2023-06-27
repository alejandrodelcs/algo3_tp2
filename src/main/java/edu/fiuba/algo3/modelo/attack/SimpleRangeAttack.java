package edu.fiuba.algo3.modelo.attack;

import edu.fiuba.algo3.modelo.Logger;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.Range;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.awt.*;
import java.util.ArrayList;

public class SimpleRangeAttack implements Attack{
    private Damage damage;
    private Range rangeAttack;

    public SimpleRangeAttack(Range range, Damage damage) {
        this.rangeAttack = range;
        this.damage = damage;
    }
    @Override
    public void performAttackOnEnemies(Defense defense, ArrayList<Enemy> enemies){
        int i = enemies.size() - 1;
        boolean attack_performed=false;
        while (i >= 0 && !rangeAttack.isInRange(defense, enemies.get(i)) && !attack_performed) {
            if (enemies.get(i).isVisible() && !enemies.get(i).enemyDied()){
                Enemy enemy = enemies.get(i);
                enemy.takeDamage(damage);
                Logger.get().log(defense.getClass().getSimpleName() + " attacks " + enemy.getClass().getSimpleName()
                        + " at postion(" + (int) enemy.getPoint().getX() + "," + (int) enemy.getPoint().getY() + ")");
                attack_performed=true;
            }
            i--;
        }
    }

    @Override
    public ArrayList<Point> getPlotsInRange(Point point) {
        return rangeAttack.plotsInRange(point);
    }
}
