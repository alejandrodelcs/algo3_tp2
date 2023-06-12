package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Speed;

import java.awt.*;
import java.util.ArrayList;

/*public class Owl extends Enemy{
    public Owl(Speed speed, Damage damage, Health healthPoints, int creditsReward) {
        super(speed, damage, healthPoints, creditsReward);
    }
    @Override
    public Point updateCoordinates(int positionInPath, ArrayList<Point> enemyPath, Plot[][] plots) {
        return speed.enemyCoordinatesVelocityCalculator(positionInPath, enemyPath, plots);
    }

    @Override
    public Credit generateCredits() {
        return null;
    }

    @Override
    public String Show() {
        return null;
    }
}*/

public class Owl extends Enemy {
    private boolean destroyedTower;

    public Owl(Speed speed, Damage damage, Health health, Credit credit) {
        super(speed, damage, health, credit);
        destroyedTower = false;
    }

    @Override
    public void acelerate() {
        // Move the owl towards the end
    }

    @Override
    public Credit generateCredits() {
        return creditsReward;
    }

    @Override
    public String Show() {
        return "Owl";
    }

    @Override
    public Point updateCoordinates(int positionInPath, ArrayList<Point> enemyPath, Plot[][] plots) {
        // Update the coordinates of the owl based on its position in the path
        enemyCoordinates = speed.enemyCoordinatesVelocityCalculator(positionInPath, enemyPath, plots);
        return enemyCoordinates;
    }

    public void destroyTower(Tower tower) {
//        if (!destroyedTower) { TODO: Need to implement destroy method in towers and uncomment or refactor this method
//            tower.destroy();
//            destroyedTower = true;
//        }
    }
}