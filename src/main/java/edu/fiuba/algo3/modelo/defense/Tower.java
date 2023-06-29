package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.credit.Credit;


import java.awt.*;

public abstract class Tower extends Defense {



    public Tower(Credit credits, int constructionTurns,Point cordinatesTower){
        this.credits = credits;
        this.state = new ConstructionState(constructionTurns);
        this.coordinates = cordinatesTower;
        ;
    }

    public void constructionFinished (){
        state = new OperationalState(attack);
    }

}
