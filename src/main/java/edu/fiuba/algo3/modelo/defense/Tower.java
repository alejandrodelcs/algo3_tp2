package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.attack.SimpleRangeAttack;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.enemy.Enemy;


import java.awt.*;
import java.util.ArrayList;

public abstract class Tower extends Defense{

    protected Range rangeAttack;

    public Tower(Credit credits, Range rangeAttack, Damage damage, int constructionTurns,Point cordinatesTower){
        this.credits = credits;
        this.damage = damage;
        this.state = new ConstructionState(constructionTurns);
        this.coordinates = cordinatesTower;
        this.rangeAttack = rangeAttack;
    }

    public void constructionFinished (){
        state = new OperationalState(new SimpleRangeAttack(rangeAttack));
    }

}
