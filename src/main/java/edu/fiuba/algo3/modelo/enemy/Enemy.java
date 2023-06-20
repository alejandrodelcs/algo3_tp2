package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.Logger;
import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import edu.fiuba.algo3.modelo.health.*;
import edu.fiuba.algo3.modelo.speed.Move;

import java.awt.*;
import java.util.ArrayList;

/*
public abstract class Enemy implements Cloneable{
    protected Speed speed;
    private Damage damage;
    private Health health;
    protected Credit creditsReward;

    protected Point enemyCoordinates;

    public Enemy(Speed speed, Damage damage, Health healthPoints, int creditsReward) {
        this.speed = speed;
        this.damage = damage;
        this.health = healthPoints;
        this.enemyCoordinates = new Point();
        this.creditsReward = new Credit(creditsReward);
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
}*/

public abstract class Enemy {
    protected Damage damage;
    protected Health health;
    protected Credit creditsReward;
    protected Point enemyCoordinates;
    protected Move move;

    public Enemy(Damage damage, Health health, Credit credit, Move movement) {
        this.move = movement;
        this.damage = damage;
        this.health = health;
        this.enemyCoordinates = new Point();
        this.creditsReward = credit;
    }

    public Damage getDamage() {
        return damage;
    }

    public Point getPoint(){return enemyCoordinates;}

    public Health getHealth() {
        return health;
    }

    public void takeDamage(Damage damageReceive) {
        damageReceive.applyDamage(health);
    }

    public boolean enemyDied() {
        return health.entityDied();
    }

    public abstract void acelerate();

    public abstract Credit generateCredits();

    public abstract String Show();

    public Point move(long x, long y, Plot[][] plots, ArrayList<Point> enemyPath){
        enemyCoordinates = move.execute(x, y, plots, enemyPath);
        return enemyCoordinates;
    }

    public void desacelerate(double percentage) {
        move.desaccelerate(percentage);
    }
}