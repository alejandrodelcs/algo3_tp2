package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import edu.fiuba.algo3.modelo.health.*;
import edu.fiuba.algo3.modelo.speed.Speed;

import java.awt.*;
import java.util.ArrayList;

public abstract class Enemy implements Cloneable{
    protected Speed speed;
    private Damage damage;
    private Health health;

    protected Point enemyCoordinates;

    public Enemy(Speed speed, Damage damage, int healthPoints) {
        this.speed = speed;
        this.damage = damage;
        this.health = new Damageable(healthPoints);
        this.enemyCoordinates = new Point();
    }


    public int getSpeed() { return speed.getSpeed();  }

    public Damage getDamage() {
        return damage;
    }

    public Point getPoint(){return enemyCoordinates;}

    public Health getHealth() {
        return health;
    }

    public void takeDamage(Damage damageReceive) {
        int amount = damageReceive.getQuantity();
        health.takeDamage(amount);
    }



    public Boolean enemyDied(){
        return health.entityDied();
    };

    public abstract Credit generateCredits();

    public abstract String Show();

    public abstract Point updateCoordinates(int positionInPath, ArrayList<Point> enemyPath, Plot[][] plots);

    public void updateCoordinates2(Point coordinates){
        enemyCoordinates = coordinates;
    }
}