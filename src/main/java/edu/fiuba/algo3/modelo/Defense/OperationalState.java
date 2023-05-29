package edu.fiuba.algo3.modelo.Defense;

public class OperationalState implements State{
    @Override
    public void Attack(Tower tower,Enemy enemy){
        if(!enemy.isWithinAttackRange()){
            throw new EnemyIsOutOfRange();
        }else{
            enemy.receiveDamage(tower.getDamage());
        }
    }
}
