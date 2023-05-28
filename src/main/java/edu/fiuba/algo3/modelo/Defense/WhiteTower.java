package edu.fiuba.algo3.modelo.Defense;

public class WhiteTower extends Tower{

    public WhiteTower(){
            this.credits = 10;
            this.rangeAttack = 3;
            this.damage = 1;
            this.operational_state=false;
        }
    public void Attack(Enemy enemy){
        if (!this.operational_state){
            throw new TowerIsUnderConstruction();
        }else{
            if(!enemy.isWithinAttackRange()){
                throw new EnemyIsOutOfRange();
            }else{
                enemy.receiveDamage(damage);
            }
        }
    }
    public void constructionFinished (){
        this.operational_state=true;
    }
}

