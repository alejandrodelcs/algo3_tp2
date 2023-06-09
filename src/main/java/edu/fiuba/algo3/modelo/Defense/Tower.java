package edu.fiuba.algo3.modelo.Defense;

import edu.fiuba.algo3.modelo.Enemy.Enemy;

public abstract class Tower {
    protected int credits;
    protected int rangeAttack;
    protected int damage;
    protected State state = new ConstructionState();
    protected int cont;

    public Tower(int credits, int rangeAttack, int damage, int cont){
        this.credits = credits;
        this.rangeAttack = rangeAttack;
        this.damage = damage;
        this.cont = cont;
    }
    public void Attack(Enemy enemy, boolean isOnRange){
        state.Attack(this,enemy,isOnRange);
    }
    public void constructionFinished (){
        state = new OperationalState();
    }
    public int getCredits() {
        return credits;
    }
    public int getDamage() {
        return damage;
    }

    public State getStatus(){return state;}
    public void updateStatus(){
        cont -= 1;
        if(cont == 0){
            state = new OperationalState();
        }
    }

    public int getAttackRange() {
        return rangeAttack;
    }
}
