package edu.fiuba.algo3.modelo.damage;

import edu.fiuba.algo3.modelo.health.Health;


public class Damage {

    private final int amountOfDamage;
    public Damage(int damage){
        this.amountOfDamage = damage;
    }
    public void applyDamage(Health lifePoints){
        lifePoints.takeDamage(amountOfDamage);
    }

    public int getQuantity(){return amountOfDamage;}
}
