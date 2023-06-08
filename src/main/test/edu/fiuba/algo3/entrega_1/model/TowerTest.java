package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.enemy.*;
import edu.fiuba.algo3.modelo.exceptions.EnemyIsOutOfRange;
import edu.fiuba.algo3.modelo.exceptions.TowerDoesNotExist;
import edu.fiuba.algo3.modelo.exceptions.TowerIsUnderConstruction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TowerTest {

  @Test
    void test01NewWhiteTowerCannotAttackTheEnemy() {

        TowerFactory factory = new WhiteTowerFactory();
        Point cordenates = new Point(3,3);
        Tower WhiteTower = factory.createTower(cordenates);
        Enemy enemy = mock(Ant.class);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        WhiteTower.attack(enemies);
        Assertions.assertFalse(enemy.enemyDied());
    }
    @Test
    void test02NewSilverTowerCannotAttackTheEnemy() {
        TowerFactory factory = new SilverTowerFactory();
        Point cordenates = new Point(3,3);
        Tower SilverTower = factory.createTower(cordenates);
        Enemy enemy = mock(Ant.class);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        SilverTower.attack(enemies);
        Assertions.assertFalse(enemy.enemyDied());
    }

    @Test
    void test03WhiteTowerAttackWhenEnemyIsWithinRangeEnemyTakesDamage() {
        EnemyFactory factoryEnemies = new EnemyFactory();
        Enemy ant = factoryEnemies.createEnemy("Ant");

        Point cordenates = new Point(3,3);
        TowerFactory factory = new WhiteTowerFactory();
        Tower WhiteTower = factory.createTower(cordenates);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(ant);

        WhiteTower.constructionFinished();
        WhiteTower.attack(enemies);

        Assertions.assertTrue(ant.enemyDied());
    }

    @Test
    void test04SilverTowerAttackWhenEnemyIsWithinRangeEnemyTakesDamage() {
        EnemyFactory factoryEnemies = new EnemyFactory();
        Enemy spider = factoryEnemies.createEnemy("Spider");
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(spider);

        TowerFactory factory = new SilverTowerFactory();
        Point cordenates = new Point(3,3);
        Tower SilverTower = factory.createTower(cordenates);

        SilverTower.constructionFinished();
        SilverTower.attack(enemies);

        Assertions.assertTrue(spider.enemyDied());
    }

}