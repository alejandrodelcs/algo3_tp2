package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Move;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.ArrayList;

public class Mole extends Enemy {
    private int moves;
    private Damage damage;
    private final int MOVES_NEEDED_FOR_SPEED_2;
    private final int MOVES_NEEDED_FOR_SPEED_3;

    public Mole(Damage damage, Health health, Credit credit, Move movement) {
        super(damage, health, credit, movement);
        this.moves = 0;
        this.MOVES_NEEDED_FOR_SPEED_2 = 6;
        this.MOVES_NEEDED_FOR_SPEED_3 = 11;
        this.visible = false;
        this.damage = damage;
    }
    @Override
    public Point move(long x, long y, Plot[][] plots, ArrayList<Point> enemyPath) {
        enemyCoordinates = move.execute(x, y, plots, enemyPath);
        updateMole();
        if (moves >= MOVES_NEEDED_FOR_SPEED_2 && moves < MOVES_NEEDED_FOR_SPEED_3) {
            move.accelerate(2);
        } else if (moves >= MOVES_NEEDED_FOR_SPEED_3){
            move.accelerate(3);
        }
        return enemyCoordinates;
    }
    public void acelerate() {
        updateMole();
        if (moves >= MOVES_NEEDED_FOR_SPEED_2 && moves < MOVES_NEEDED_FOR_SPEED_3) {
             move.accelerate(2);
        } else if (moves >= MOVES_NEEDED_FOR_SPEED_3){
            move.accelerate(3);
        }
    }

    public void updateMole(){
        moves ++;
        if ( moves % 2 == 0 ) {
            this.damage = new Damage(2);
        }
        else{
            this.damage = new Damage(5);
        }
    }
    @Override
    public Credit generateCredits() {
        return creditsReward;
    }

    @Override
    public String show() {
        return ("Mole:\n\tSpeed: " + move.getSpeed() + "\n\tDamage: " + damage.getQuantity() + "\n\tHealth: " + health.getHealthPoints());

    }
}
