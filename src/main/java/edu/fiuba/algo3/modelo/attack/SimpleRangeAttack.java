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
        boolean attack_performed=false;
        for (int i=enemies.size() - 1;i>=0 && !attack_performed;i--){
            if (enemies.get(i).isVisible() && !enemies.get(i).enemyDied() && rangeAttack.isInRange(defense, enemies.get(i))){
                Enemy enemy = enemies.get(i);
                enemy.takeDamage(damage);
                Logger.get().log(defense.getClass().getSimpleName() + " attacks " + enemy.getClass().getSimpleName()
                        + " at postion(" + (int) enemy.getPoint().getX() + "," + (int) enemy.getPoint().getY() + ")");
                attack_performed=true;
            }
        }
    }

    @Override
    public ArrayList<Point> getPlotsInRange(Point point) {
        return rangeAttack.plotsInRange(point);
    }
}
