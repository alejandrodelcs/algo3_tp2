package edu.fiuba.algo3.modelo.player;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.defense.TowerFactory;
import edu.fiuba.algo3.modelo.defense.WhiteTower;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import edu.fiuba.algo3.modelo.exceptions.InvalidPlayersName;
import edu.fiuba.algo3.modelo.exceptions.PlayerIsDeadGameOver;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.health.Damageable;
import edu.fiuba.algo3.modelo.health.Health;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String playersName;
    private Health playersLifePoints;
    private Credit playersCredits;

    public Player(String playersName) {

        validateName(playersName);
        this.playersName = playersName;
        this.playersLifePoints = new Damageable(20);
        this.playersCredits = new Credit(100);

    }

    private void validateName(String name) {
        int minimumLengthNameCharacters = 6;
        int nameSize = name.length();

        if(nameSize<minimumLengthNameCharacters){
            throw new InvalidPlayersName();
        }
    }

    public Credit getPlayerCredits() {
        return playersCredits;
    }

    public void getsDamage(Damage damage) {

        damage.applyDamage(playersLifePoints);
        if (playersLifePoints.entityDied()) {
            throw new PlayerIsDeadGameOver();
        }
    }

    public void chargeCredits(Credit credits) {
        playersCredits.chargeCredits(credits);
    }

    public void subtractCredits(Credit credits) {
        playersCredits.subtractCredits(credits);
        if (playersCredits.areNegative()) {
            throw new InsufficientCredits();
        }
    }

    public void buildsADefense(Tower tower) {
        Credit creditsToBeCharged =  tower.getCredits();
        this.subtractCredits(creditsToBeCharged);

    }

    public boolean canBuy(int value) { return (this.getPlayerCredits().getQuantity() - value)>=0;}

    public Point selectPlaceDefense(ArrayList<Point> listOfPlacesWhereADefenseCanBeBuild) {
        //TODO//implement
        return null;
    }

    public Tower selectTower() {
        TowerFactory factory = new TowerFactory();
        return factory.createTower("WhiteTower",new Point(2,3));//TODO//Implement(HardCoded)
    }

    public boolean isAlive() {
        return(!playersLifePoints.entityDied());
    }
}

