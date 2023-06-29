package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import edu.fiuba.algo3.modelo.health.*;
import edu.fiuba.algo3.modelo.speed.Move;


import java.awt.*;
import java.util.ArrayList;

public abstract class Enemy {
    protected Damage damage;
    protected Health health;
    protected Credit creditsReward;
    protected Point enemyCoordinates;
    protected Move move;
    protected Boolean visible;
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

    public abstract Credit generateCredits();

    public abstract String show();

    public Point move(long x, long y, Plot[][] plots, ArrayList<Point> enemyPath){
        enemyCoordinates = move.execute(x, y, plots, enemyPath);
        return enemyCoordinates;
    }

    public void decelerate(double percentage) {
        move.decelerate(percentage);
    }

    public boolean alreadySlowedDown() {
        return move.isSlowedDown();
    }

    public boolean isVisible() {
        return visible;
    }

    public abstract Defense destroyTower(ArrayList<Defense> defenses);
}