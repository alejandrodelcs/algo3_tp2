package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import edu.fiuba.algo3.modelo.speed.Speed;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Spider extends Enemy {
    private static final int CREDITS_MIN_RANGE = 0;
    private static final int CREDITS_MAX_RANGE = 10;

    public Spider(Speed speed, Damage damage, int healthPoints) {
        super(speed, damage, healthPoints);
        this.speed = speed;
    }


    @Override
    public Credit generateCredits() {
        Random random = new Random();
        return new Credit(random.nextInt(CREDITS_MAX_RANGE - CREDITS_MIN_RANGE + 1) + CREDITS_MIN_RANGE);
    }
    @Override
    public String Show() {
        return "Spider";
    }

    @Override
    public Point updateCoordinates(int positionInPath, ArrayList<Point> enemyPath, Plot[][] plots){
        enemyCoordinates = speed.enemyCoordinatesVelocityCalculator(positionInPath, enemyPath, plots);
        return enemyCoordinates;
    }
}
