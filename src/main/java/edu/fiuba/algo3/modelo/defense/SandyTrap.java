package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.attack.SlowDown;
import edu.fiuba.algo3.modelo.credit.Credit;

import java.awt.*;

public class SandyTrap extends Trap {
    public void constructionFinished(){}
    public SandyTrap(Credit credits, Point cordinatesTrap, int operativeTurns){
        super(credits,cordinatesTrap, operativeTurns);
        Range cero = new Range(0);
        this.attack = new SlowDown(cero);
    }

}
