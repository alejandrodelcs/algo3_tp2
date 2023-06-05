package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tower {
    protected Credit credits;
    protected Damage damage;
    protected State state;
    protected Point point;
    protected int rangeAttack;

    protected ArrayList<Point> pathsInRange;


    public Tower(Credit credits, int rangeAttack, Damage damage, int cont, ArrayList<Point> pathsInRange){
        this.credits = credits;
        this.rangeAttack = rangeAttack;
        this.damage = damage;
        this.state = new ConstructionState(cont);
        this.pathsInRange = pathsInRange ;

    }
    public void Attack(Enemy enemy){
        state.Attack(this,enemy);
    }
    public void constructionFinished (){
        state = new OperationalState();
    }
    public Credit getCredits() {
        return credits;
    }
    public Damage getDamage() {
        return damage;
    }

    public void updateStatus(){
        state.update(this);
    }

    public int getAttackRange() {
        return rangeAttack;
    }

    public void checkAttack() {
        for (Point pathInRange: pathsInRange) {

        }
    }
}
