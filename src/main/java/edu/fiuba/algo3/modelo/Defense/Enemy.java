package edu.fiuba.algo3.modelo.Defense;
/*The purpose of this interface is solely to test the functionality of the tower.*/
public interface Enemy {
    boolean isWithinAttackRange();
    int receiveDamage(int damage);
}
