package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.enemy.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class TowerTest {

  @Test
    void test01NewWhiteTowerUnderConstructionCannotAttackEnemy() {
        TowerFactory factory = new WhiteTowerFactory();
        Point cordenates = new Point(3,3);
        Tower WhiteTower = factory.createTower(cordenates);
        Enemy enemy = mock(Ant.class);
        when(enemy.getPoint()).thenReturn(new Point(4,4));
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        WhiteTower.attack(enemies);
        verify(enemy, never()).takeDamage(WhiteTower.getDamage());
        Assertions.assertFalse(enemy.enemyDied());
    }
    @Test
    void test02NewSilverTowerUnderConstructionCannotAttackEnemy() {
        TowerFactory factory = new SilverTowerFactory();
        Point cordenates = new Point(3,3);
        Tower SilverTower = factory.createTower(cordenates);
        Enemy enemy = mock(Ant.class);
        when(enemy.getPoint()).thenReturn(new Point(4,4));
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        SilverTower.attack(enemies);
        verify(enemy, never()).takeDamage(SilverTower.getDamage());
        Assertions.assertFalse(enemy.enemyDied());

        SilverTower.attack(enemies);
        verify(enemy, never()).takeDamage(SilverTower.getDamage());
        Assertions.assertFalse(enemy.enemyDied());

    }

    @Test
    void test03WhiteTowerAttackWhenEnemyIsWithinRangeEnemyTakesDamage() {
        EnemyFactory factoryEnemies = new EnemyFactory();
        Enemy ant = factoryEnemies.createEnemy("Ant");
        ant.updateCoordinates(new Point(2,2));
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
        spider.updateCoordinates(new Point(2,2));
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