package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.attack.SlowDown;
import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.awt.*;
import java.util.ArrayList;

public class TrapSandy extends Trap {
    public void constructionFinished(){}
    public TrapSandy(Credit credits, Point cordinatesTrap, int operativeTurns){
        super(credits,cordinatesTrap, operativeTurns);
        this.attack = new SlowDown(new Range(0));
    }

}
