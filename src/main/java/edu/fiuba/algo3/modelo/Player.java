package edu.fiuba.algo3.modelo;

public class Player {
    private final String playersName;
    private int playersLifePoints;
    private int playersCredits;

    public Player(String playersName) {
        this.playersName = playersName;
        this.playersLifePoints = 20;
        this.playersCredits = 100;
    }

    public int getPlayerLifePoints() {
        return playersLifePoints;
    }
    public int getPlayerCredits() {
        return playersCredits;
    }

    public void getsDamage(int damage) {

        playersLifePoints -= damage;

        if (playersLifePoints <= 0) {
            throw new PlayerIsDeadGameOver();
        }
    }

    public void chargedCredits(int credits) {
        playersCredits -= credits;
    }

    public void getsCredit(int credits) {
        playersCredits += credits;
    }

}
