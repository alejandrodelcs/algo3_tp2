package edu.fiuba.algo3.modelo.player;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.defense.WhiteTower;
import edu.fiuba.algo3.modelo.exceptions.InvalidPlayersName;
import edu.fiuba.algo3.modelo.exceptions.PlayerIsDeadGameOver;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String playersName;
    private int playersLifePoints;
    private Credit playersCredits;

    public Player(String playersName) {

        validateName(playersName);
        this.playersName = playersName;
        this.playersLifePoints = 20;
        this.playersCredits = new Credit(100);

    }

    private void validateName(String name) {
        int minimumLengthNameCharacters = 6;
        int nameSize = name.length();

        if(nameSize<minimumLengthNameCharacters){
            throw new InvalidPlayersName();
        }
    }

    public int getPlayerLifePoints() {
        return playersLifePoints;
    }
    public Credit getPlayerCredits() {
        return playersCredits;
    }

    public void getsDamage(int damage) {

        playersLifePoints -= damage;

        if (playersLifePoints <= 0) {
            throw new PlayerIsDeadGameOver();
        }
    }

    public void chargedCredits(Credit credits) {
        playersCredits.chargedCredits(credits);
    }

    public void getsCredit(Credit credits) {
        playersCredits.subtractCredits(credits);
    }

    public void buildsADefense(Tower tower, GameBoard gameboard) {
        Credit creditsToBeCharged =  tower.getCredits();
        this.chargedCredits(creditsToBeCharged);

    }

    public boolean canBuy(int value) { return (this.getPlayerCredits().getQuantity() - value)>=0;}

    public Point selectPlaceDefense(ArrayList<Point> listOfPlacesWhereADefenseCanBeBuild) {
        //TODO//implement
        return null;
    }

    public Tower selectTower() {
        return new WhiteTower();//TODO//Implement(HardCoded)
    }
}

