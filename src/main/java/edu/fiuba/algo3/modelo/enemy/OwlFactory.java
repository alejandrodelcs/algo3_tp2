package edu.fiuba.algo3.modelo.enemy;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.health.Health;
import edu.fiuba.algo3.modelo.health.NotDamageable;
import edu.fiuba.algo3.modelo.speed.Move;
import edu.fiuba.algo3.modelo.speed.MoveInLShape;

public class OwlFactory implements EnemyFactory{
    @Override
    public Enemy createEnemy() {

        Move someMovement = new MoveInLShape(5);
        Damage someDamage = new Damage(0);
        Health someHealth = new Health(5);
        Credit someCredit = new Credit(0); //WE ASSUME THAT OWLS DO NOT GIVE CREDITS WHEN KILLED AS IT IS NOT SPECIFIED

        return new Owl(someDamage, someHealth, someCredit, someMovement, healthToChangeMovement);
    }

}
