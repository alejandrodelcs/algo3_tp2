package edu.fiuba.algo3.entrega_1.model;
import edu.fiuba.algo3.modelo.enemy.*;
import edu.fiuba.algo3.modelo.damage.Damage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    public void test01WhenInstancingAnAntItIsAlive() {
        EnemyFactory antFactory = new AntFactory();
        Enemy anAnt = antFactory.createEnemy();
        assertFalse(anAnt.enemyDied());
    }

    @Test
    public void test02AntIsAttackedAndItDies() {
        EnemyFactory antFactory = new AntFactory();
        Enemy anAnt = antFactory.createEnemy();
        anAnt.takeDamage(new Damage(1));
        assertTrue(anAnt.enemyDied());
    }

    @Test
    public void test03AntAttackedStrongerDies() {
        EnemyFactory antFactory = new AntFactory();
        Enemy anAnt = antFactory.createEnemy();
        anAnt.takeDamage(new Damage(2));
        assertTrue(anAnt.enemyDied());
    }

    @Test
    public void test04NewSpiderIsAlive() {
        EnemyFactory spiderFactory = new SpiderFactory();
        Enemy aSpider = spiderFactory.createEnemy();
        assertFalse(aSpider.enemyDied());
    }

    @Test
    public void test05SpiderAttackedWithOneDamagePointIsAlive() {
        EnemyFactory spiderFactory = new SpiderFactory();
        Enemy aSpider = spiderFactory.createEnemy();
        aSpider.takeDamage(new Damage(1));
        assertFalse(aSpider.enemyDied());
    }

    @Test
    public void test06SpiderAttackedWithTwoDamagePointsDies() {
        EnemyFactory spiderFactory = new SpiderFactory();
        Enemy aSpider = spiderFactory.createEnemy();
        aSpider.takeDamage(new Damage(2));
        assertTrue(aSpider.enemyDied());
    }

    @Test
    public void test07SpiderAttackedWithOneDamagePointTwiceDies() {
        EnemyFactory spiderFactory = new SpiderFactory();
        Enemy aSpider = spiderFactory.createEnemy();
        aSpider.takeDamage(new Damage(1));
        aSpider.takeDamage(new Damage(1));
        assertTrue(aSpider.enemyDied());
    }

    @Test
    public void test08SpiderAttackedWithMoreThanTwoDamagePointsDies() {
        EnemyFactory spiderFactory = new SpiderFactory();
        Enemy aSpider = spiderFactory.createEnemy();
        aSpider.takeDamage(new Damage(4));
        assertTrue(aSpider.enemyDied());
    }

    @Test
    public void test09NewOwlIsAlive() {
        EnemyFactory owlFactory = new OwlFactory();
        Enemy anOwl = owlFactory.createEnemy();
        assertFalse(anOwl.enemyDied());
    }

    @Test
    public void test10OwlHarmedWith3PointIsStillAlive() {
        EnemyFactory owlFactory = new OwlFactory();
        Enemy anOwl = owlFactory.createEnemy();
        anOwl.takeDamage(new Damage(3));
        assertFalse(anOwl.enemyDied());
    }

    @Test
    public void test11OwlHarmedWith5PointsDies() {
        EnemyFactory owlFactory = new OwlFactory();
        Enemy anOwl = owlFactory.createEnemy();
        anOwl.takeDamage(new Damage(5));
        assertTrue(anOwl.enemyDied());
    }


}