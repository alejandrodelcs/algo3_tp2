package edu.fiuba.algo3.modelo.player;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.exceptions.InsufficientCredits;
import edu.fiuba.algo3.modelo.exceptions.InvalidPlayersName;
import edu.fiuba.algo3.modelo.exceptions.PlayerIsDeadGameOver;
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
        this.playersLifePoints = new Health(20);
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

    public void buildsADefense(Defense defense) {
        Credit creditsToBeCharged =  defense.getCredits();
        this.subtractCredits(creditsToBeCharged);

    }

    public boolean isAlive() {
        System.out.println("Player current life: " + playersLifePoints.getHealthPoints());
        System.out.println("Player current credits: " + playersCredits.getQuantity());
        return(!playersLifePoints.entityDied());
    }

    public boolean hasFunds() {
        return playersCredits.arePositive();
    }

}

