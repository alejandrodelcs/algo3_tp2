package edu.fiuba.algo3.modelo.enemy;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.speed.Speed;

public class EnemyFactory {
    public Enemy createSpider() {
        return new Spider(new Speed(2), new Damage(2), 2);
    }
    public Enemy createAnt() {
        return new Ant(new Speed(1), new Damage(1), 1);
    }
}
