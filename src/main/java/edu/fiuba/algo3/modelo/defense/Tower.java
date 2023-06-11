package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.attack.Attack;
import edu.fiuba.algo3.modelo.attack.SimpleRangeAttack;
import edu.fiuba.algo3.modelo.damage.Damage;


import java.awt.*;

public abstract class Tower extends Defense {



    public Tower(Credit credits, Range rangeAttack, Damage damage, int constructionTurns,Point cordinatesTower){
        this.credits = credits;
        this.state = new ConstructionState(constructionTurns);
        this.coordinates = cordinatesTower;
        this.attack = new SimpleRangeAttack(rangeAttack,damage);
    }

    public void constructionFinished (){
        state = new OperationalState(attack);
    }

}
