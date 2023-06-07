package edu.fiuba.algo3.entrega_1.model;
import edu.fiuba.algo3.modelo.enemy.*;
import edu.fiuba.algo3.modelo.exceptions.EnemyDoesNotExist;
import edu.fiuba.algo3.modelo.damage.Damage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    public void test01WhenInstancingAnAntItIsAlive() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy anAnt = enemyFactory.createAnt();
        assertFalse(anAnt.enemyDied());
    }

    @Test
    public void test02AntIsAttackedAndItDies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy anAnt = enemyFactory.createAnt();
        anAnt.takeDamage(new Damage(1));
        assertTrue(anAnt.enemyDied());
    }

    @Test
    public void test03AntAttackedStrongerDies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy anAnt = enemyFactory.createAnt();
        anAnt.takeDamage(new Damage(2));
        assertTrue(anAnt.enemyDied());
    }

    @Test
    public void test04NewSpiderIsAlive() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy aSpider = enemyFactory.createSpider();
        assertFalse(aSpider.enemyDied());
    }

    @Test
    public void test05SpiderAttackedWithOneDamagePointIsAlive() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy aSpider = enemyFactory.createSpider();
        aSpider.takeDamage(new Damage(1));
        assertFalse(aSpider.enemyDied());
    }

    @Test
    public void test06SpiderAttackedWithTwoDamagePointsDies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy aSpider = enemyFactory.createSpider();
        aSpider.takeDamage(new Damage(2));
        assertTrue(aSpider.enemyDied());
    }

    @Test
    public void test07SpiderAttackedWithOneDamagePointTwiceDies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy aSpider = enemyFactory.createSpider();
        aSpider.takeDamage(new Damage(1));
        aSpider.takeDamage(new Damage(1));
        assertTrue(aSpider.enemyDied());
    }

    @Test
    public void test08SpiderAttackedWithMoreThanTwoDamagePointsDies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy aSpider = enemyFactory.createSpider();
        aSpider.takeDamage(new Damage(4));
        assertTrue(aSpider.enemyDied());
    }
}