package edu.fiuba.algo3.entrega_1.model;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.enemy.*;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    public void test09NewMoleIsAlive() {
        EnemyFactory moleFactory = new MoleFactory();
        Enemy aMole = moleFactory.createEnemy();
        assertFalse(aMole.enemyDied());
    }

    @Test
    public void test10MoleIsImmortal() {
        EnemyFactory moleFactory = new MoleFactory();
        Enemy aMole = moleFactory.createEnemy();
        aMole.takeDamage(new Damage(1000));
        assertFalse(aMole.enemyDied());
    }

    @Test
    public void test11NewOwlIsAlive() {
        EnemyFactory owlFactory = new OwlFactory();
        Enemy anOwl = owlFactory.createEnemy();
        assertFalse(anOwl.enemyDied());
    }

    @Test
    public void test12OwlHarmedWith3PointIsStillAlive() {
        EnemyFactory owlFactory = new OwlFactory();
        Enemy anOwl = owlFactory.createEnemy();
        anOwl.takeDamage(new Damage(3));
        assertFalse(anOwl.enemyDied());
    }

    @Test
    public void test13OwlHarmedWith5PointsDies() {
        EnemyFactory owlFactory = new OwlFactory();
        Enemy anOwl = owlFactory.createEnemy();
        anOwl.takeDamage(new Damage(5));
        assertTrue(anOwl.enemyDied());
    }

    @Test
    public void testOwlMovement() {
        Player player = new Player("aNamee");
        AlgoDefense algoDefense = new AlgoDefense(player);
        EnemyFactory owlFac = new OwlFactory();
        Enemy anOwl = owlFac.createEnemy();
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(anOwl);
        algoDefense.spawnAnEnemy(enemies);
        algoDefense.nextTurn();
        anOwl.takeDamage(new Damage(4));
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();

    }
}