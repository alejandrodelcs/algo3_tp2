package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import javafx.scene.shape.Path;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tower {
    protected Credit credits;
    protected Damage damage;
    protected State state;
    protected Point point;
    protected int rangeAttack;



    public Tower(Credit credits, int rangeAttack, Damage damage, int constructionTurns,Point cordinatesTower){
        this.credits = credits;
        this.damage = damage;
        this.state = new ConstructionState(constructionTurns);
        this.point = cordinatesTower;
        this.rangeAttack = rangeAttack;
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


    public void attack(Enemy enemy){
        if(!(enemy == null)){
            state.Attack(this,enemy);}}


    public int getRange() { return rangeAttack;}

    public Point getPoint() {
        return point;
    }

    public boolean isBuild() {}
}
