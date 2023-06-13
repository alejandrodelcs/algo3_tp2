package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Speed;

import java.awt.*;
import java.util.ArrayList;

public class Mole extends Enemy {
    private int moves;
    private final int MOVES_NEEDED_FOR_SPEED_2;
    private final int MOVES_NEEDED_FOR_SPEED_3;

    public Mole(Speed speed, Damage damage, Health health, Credit credit) {
        super(speed, damage, health, credit);
        this.moves = 0;
        this.MOVES_NEEDED_FOR_SPEED_2 = 6;
        this.MOVES_NEEDED_FOR_SPEED_3 = 11;
    }

    @Override
    public void acelerate() {
        moves++;
        if (moves >= MOVES_NEEDED_FOR_SPEED_2 && moves < MOVES_NEEDED_FOR_SPEED_3) {
            speed = new Speed(2);
        } else if (moves >= MOVES_NEEDED_FOR_SPEED_3){
            speed = new Speed(3);
        }
    }

    @Override
    public Credit generateCredits() {
        return creditsReward;
    }

    @Override
    public String Show() {
        return "Mole";
    }

    @Override
    public Point updateCoordinates(int positionInPath, ArrayList<Point> enemyPath, Plot[][] plots) {
        // Update the coordinates of the mole based on its position in the path
        enemyCoordinates = speed.enemyCoordinatesVelocityCalculator(positionInPath, enemyPath, plots);
        return enemyCoordinates;
    }
}
