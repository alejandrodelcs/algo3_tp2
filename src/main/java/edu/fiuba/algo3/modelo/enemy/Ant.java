package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.speed.Move;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.ArrayList;

/*public class Ant extends Enemy {
    private static final int REWARD_THRESHOLD = 10;

    private int getReward() {
        //TODO: missing the logic so that when player kills "REWARD_TRESHHOLD" or more ants, this method returns 2 instead of 1.
        return 1;
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
        this.visible = true;
    }

    @Override
    public Credit generateCredits() {
        return creditsReward;
    }

    @Override
    public String show() {
        return ("Ant:\n\tSpeed: " + move.getSpeed() + "\n\tDamage: " + damage.getQuantity() + "\n\tHealth: " + health.getHealthPoints());
    }
}

