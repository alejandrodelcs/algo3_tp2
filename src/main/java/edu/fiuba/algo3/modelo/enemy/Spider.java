package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Move;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


/*
    @Override
    public Point updateCoordinates(int positionInPath, ArrayList<Point> enemyPath, Plot[][] plots){
        enemyCoordinates = speed.enemyCoordinatesVelocityCalculator(positionInPath, enemyPath, plots);
        return enemyCoordinates;
    }
}*/

public class Spider extends Enemy {

    private static final int CREDITS_MIN_RANGE = 0;
    private static final int CREDITS_MAX_RANGE = 10;
    @Override
    public Credit generateCredits() {
        Random random = new Random();
        return new Credit(random.nextInt(CREDITS_MAX_RANGE - CREDITS_MIN_RANGE + 1) + CREDITS_MIN_RANGE);
    }
    public Spider(Damage damage, Health health, Credit credit, Move movement) {
        super(damage, health, credit, movement);
    }
    @Override
    public String Show() {
        return "Spider";
    }
}
