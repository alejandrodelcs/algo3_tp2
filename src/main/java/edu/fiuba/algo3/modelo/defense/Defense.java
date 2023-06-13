package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.attack.Attack;
import edu.fiuba.algo3.modelo.enemy.Enemy;

import java.awt.*;
import java.util.ArrayList;

public abstract class Defense {
    protected Credit credits;
    protected Point coordinates;
    protected State state;
    protected Attack attack;

    public Credit getCredits() {
        return credits;
    }
    public Point getPoint() {
        return coordinates;
    }
    public void attack(ArrayList<Enemy> enemies){
        state.attack(this,enemies);}
    public abstract void constructionFinished();


}
