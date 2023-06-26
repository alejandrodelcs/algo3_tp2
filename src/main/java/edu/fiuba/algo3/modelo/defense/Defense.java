package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.attack.Attack;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import javafx.scene.image.ImageView;

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
        state.attack(this, enemies);}
    public abstract void constructionFinished();
    public void destructed(){
        state = new DestructedState();
    };
    public abstract ImageView getImage();

    public abstract String show();


    public boolean isAvailable() {
        return state.getClass() != DestructedState.class;
    }
}
