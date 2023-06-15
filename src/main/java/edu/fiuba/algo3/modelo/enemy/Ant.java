package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Move;

import java.awt.*;
import java.util.ArrayList;

/*public class Ant extends Enemy {
    private static final int REWARD_THRESHOLD = 10;


    public Ant(Speed speed, Damage damage, Health healthPoints, int creditsReward) {
        super(speed, damage, healthPoints, creditsReward);
    }


    private int getReward() {
        //TODO: missing the logic so that when player kills "REWARD_TRESHHOLD" or more ants, this method returns 2 instead of 1.
        return 1;
    }

    @Override
    public Credit generateCredits() {
        return new Credit(getReward());
    }

    @Override
    public String Show() {
        return "Ant";
    }

    @Override
    public Point updateCoordinates(int positionInPath, ArrayList<Point> enemyPath, Plot[][] plots){
        enemyCoordinates = speed.enemyCoordinatesVelocityCalculator(positionInPath, enemyPath, plots);
        return enemyCoordinates;
    }
}*/

public class Ant extends Enemy {
    public Ant(Damage damage, Health health, Credit credit, Move movement) {
        super(damage, health, credit, movement);
    }

    @Override
    public void acelerate() {
        // Move the ant towards the end
    }

    @Override
    public Credit generateCredits() {
        return creditsReward;
    }

    @Override
    public String Show() {
        return "Ant";
    }

    @Override
    public Point updateCoordinates(int positionInPath, ArrayList<Point> enemyPath) {
        // Update the coordinates of the ant based on its position in the path
        enemyCoordinates = move.execute(positionInPath, enemyPath);
        return enemyCoordinates;
    }

}

