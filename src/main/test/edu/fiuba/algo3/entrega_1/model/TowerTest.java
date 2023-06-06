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
    public void test01TryingToCreateADifferentEnemyThrowsExceptions() {
        TowerFactory factory = new TowerFactory();
        Point cordenates = new Point(3,3);
        assertThrows(TowerDoesNotExist.class, () -> {factory.createTower("BlackTower",cordenates);
        });
    }
/*    @Test
    void test02NewWhiteTowerCannotAttackTheEnemy() {

        TowerFactory factory = new TowerFactory();
        Point cordenates = new Point(3,3);
        Tower WhiteTower = factory.createTower("WhiteTower",cordenates);
        Enemy enemy = mock(Ant.class);

        assertThrows(TowerIsUnderConstruction.class,()->{WhiteTower.attack(enemy);});
    }*/
/*    @Test
    void test03NewSilverTowerCannotAttackTheEnemy() {
        TowerFactory factory = new TowerFactory();
        Point cordenates = new Point(3,3);
        Tower SilverTower = factory.createTower("SilverTower",cordenates);
        Enemy enemy = mock(Ant.class);

       assertThrows(TowerIsUnderConstruction.class,()->{SilverTower.attack(enemy);});
    }*/

    @Test
    void test06WhiteTowerAttackWhenEnemyIsWithinRangeEnemyTakesDamage() {
        EnemyFactory factoryEnemies = new EnemyFactory();
        Enemy ant = factoryEnemies.createEnemy("Ant");
        TowerFactory factory = new TowerFactory();
        Point cordenates = new Point(3,3);
        Tower WhiteTower = factory.createTower("WhiteTower",cordenates);

        WhiteTower.constructionFinished();
        WhiteTower.attack(ant);

        Assertions.assertTrue(ant.enemyDied());
    }

    @Test
    void test07SilverTowerAttackWhenEnemyIsWithinRangeEnemyTakesDamage() {
        EnemyFactory factoryEnemies = new EnemyFactory();
        Enemy spider = factoryEnemies.createEnemy("Spider");
        TowerFactory factory = new TowerFactory();
        Point cordenates = new Point(3,3);
        Tower SilverTower = factory.createTower("SilverTower",cordenates);

        SilverTower.constructionFinished();
        SilverTower.attack(spider);

        Assertions.assertTrue(spider.enemyDied());
    }

}