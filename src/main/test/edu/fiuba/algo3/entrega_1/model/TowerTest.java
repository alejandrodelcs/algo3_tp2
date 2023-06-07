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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TowerTest {

  @Test
    void test02NewWhiteTowerCannotAttackTheEnemy() {

        TowerFactory factory = new WhiteTowerFactory();
        Point cordenates = new Point(3,3);
        Tower WhiteTower = factory.createTower(cordenates);
        Enemy enemy = mock(Ant.class);

        WhiteTower.attack(enemy);
        Assertions.assertFalse(enemy.enemyDied());
    }
    @Test
    void test03NewSilverTowerCannotAttackTheEnemy() {
        TowerFactory factory = new SilverTowerFactory();
        Point cordenates = new Point(3,3);
        Tower SilverTower = factory.createTower(cordenates);
        Enemy enemy = mock(Ant.class);

        SilverTower.attack(enemy);
        Assertions.assertFalse(enemy.enemyDied());
    }

    @Test
    void test06WhiteTowerAttackWhenEnemyIsWithinRangeEnemyTakesDamage() {
        EnemyFactory factoryEnemies = new EnemyFactory();
        Enemy ant = factoryEnemies.createEnemy("Ant");

        Point cordenates = new Point(3,3);
        TowerFactory factory = new WhiteTowerFactory();
        Tower WhiteTower = factory.createTower(cordenates);

        WhiteTower.constructionFinished();
        WhiteTower.attack(ant);

        Assertions.assertTrue(ant.enemyDied());
    }

    @Test
    void test07SilverTowerAttackWhenEnemyIsWithinRangeEnemyTakesDamage() {
        EnemyFactory factoryEnemies = new EnemyFactory();
        Enemy spider = factoryEnemies.createEnemy("Spider");

        TowerFactory factory = new SilverTowerFactory();
        Point cordenates = new Point(3,3);
        Tower SilverTower = factory.createTower(cordenates);

        SilverTower.constructionFinished();
        SilverTower.attack(spider);

        Assertions.assertTrue(spider.enemyDied());
    }

}