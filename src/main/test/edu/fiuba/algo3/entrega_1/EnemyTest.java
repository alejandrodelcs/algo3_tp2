package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.enemy.*;
import edu.fiuba.algo3.modelo.exceptions.EnemyDoesNotExist;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    public void test01WhenInstancingAnAntItIsAlive() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy anAnt = enemyFactory.createEnemy("Ant");
        assertFalse(anAnt.enemyDied());
    }

    @Test
    public void test02AntIsAttackedAndItDies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy anAnt = enemyFactory.createEnemy("Ant");
        anAnt.takeDamage(1);
        assertTrue(anAnt.enemyDied());
    }

    @Test
    public void test03AntAttackedStrongerDies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy anAnt = enemyFactory.createEnemy("Ant");
        anAnt.takeDamage(2);
        assertTrue(anAnt.enemyDied());
    }

    @Test
    public void test04NewSpiderIsAlive() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy aSpider = enemyFactory.createEnemy("Spider");
        assertFalse(aSpider.enemyDied());
    }

    @Test
    public void test05SpiderAttackedWithOneDamagePointIsAlive() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy aSpider = enemyFactory.createEnemy("Spider");
        aSpider.takeDamage(1);
        assertFalse(aSpider.enemyDied());
    }

    @Test
    public void test06SpiderAttackedWithTwoDamagePointsDies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy aSpider = enemyFactory.createEnemy("Spider");
        aSpider.takeDamage(2);
        assertTrue(aSpider.enemyDied());
    }

    @Test
    public void test07SpiderAttackedWithOneDamagePointTwiceDies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy aSpider = enemyFactory.createEnemy("Spider");
        aSpider.takeDamage(1);
        aSpider.takeDamage(1);
        assertTrue(aSpider.enemyDied());
    }

    @Test
    public void test08SpiderAttackedWithMoreThanTwoDamagePointsDies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy aSpider = enemyFactory.createEnemy("Spider");
        aSpider.takeDamage(4);
        assertTrue(aSpider.enemyDied());
    }

    @Test
    public void test09TryingToCreateADifferentEnemyThrowsException() {
        EnemyFactory enemyFactory = new EnemyFactory();
        assertThrows(EnemyDoesNotExist.class, () -> {
            enemyFactory.createEnemy("Lechuza");
        });
    }
}