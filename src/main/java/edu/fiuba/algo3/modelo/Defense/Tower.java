package edu.fiuba.algo3.modelo.Defense;

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

    public State getStatus(){return state;}
    public void updateStatus(){
        cont -= 1;
        if(cont == 0){
            state = new OperationalState();
        }
    }

}
