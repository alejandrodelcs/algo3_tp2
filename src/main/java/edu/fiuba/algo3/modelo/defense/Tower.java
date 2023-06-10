package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.enemy.Enemy;


import java.awt.*;
import java.util.ArrayList;

public abstract class Tower extends Defense{

    protected Damage damage;

    protected Range rangeAttack;

    public Tower(Credit credits, Range rangeAttack, Damage damage, int constructionTurns,Point cordinatesTower){
        this.credits = credits;
        this.damage = damage;
        this.state = new ConstructionState(constructionTurns);
        this.coordinates = cordinatesTower;
        this.rangeAttack = rangeAttack;
    }

    public void constructionFinished (){
        state = new OperationalState(rangeAttack);
    }

    public Damage getDamage() {
        return damage;
    }
    public void attack(ArrayList<Enemy> enemies){
        state.Attack(this,enemies);}
}
