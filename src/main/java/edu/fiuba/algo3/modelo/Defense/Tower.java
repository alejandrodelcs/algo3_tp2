package edu.fiuba.algo3.modelo.Defense;

public abstract class Tower {
    protected int credits;
    protected int rangeAttack;
    protected int damage;
    protected State state = new ConstructionState();

    public void Attack(Enemy enemy){
        state.Attack(this,enemy);
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

}
