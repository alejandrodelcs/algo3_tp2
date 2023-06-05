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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TowerTest {/*
    @Test
    public void test01TryingToCreateADifferentEnemyThrowsExceptions() {
        TowerFactory factory = new TowerFactory();
        //assertThrows(TowerDoesNotExist.class, () -> {factory.createTower("BlackTower");
        });
    }
    @Test
    void test02NewWhiteTowerCannotAttackTheEnemy() {
        TowerFactory factory = new TowerFactory();
        Tower WhiteTower = factory.createTower("WhiteTower");
        Enemy enemy = mock(Ant.class);

        //assertThrows(TowerIsUnderConstruction.class,()->{WhiteTower.Attack(enemy,true);});
    }
    @Test
    void test03NewSilverTowerCannotAttackTheEnemy() {
        TowerFactory factory = new TowerFactory();
        Tower SilverTower = factory.createTower("SilverTower");
        Enemy enemy = mock(Ant.class);

       // assertThrows(TowerIsUnderConstruction.class,()->{SilverTower.Attack(enemy,true);});
    }
    @Test
    void test04WhiteTowerOperationalAttacksAnEnemyOutOfRange() {
        TowerFactory factory = new TowerFactory();
        Tower WhiteTower = factory.createTower("WhiteTower");
        Enemy enemy = mock(Ant.class);
        WhiteTower.constructionFinished();

        //assertThrows(EnemyIsOutOfRange.class,()->{WhiteTower.Attack(enemy,false);});
    }
    @Test
    void test05SilverTowerOperationalAttacksAnEnemyOutOfRange() {
        TowerFactory factory = new TowerFactory();
        Tower SilverTower = factory.createTower("SilverTower");
        Enemy enemy = mock(Ant.class);
        SilverTower.constructionFinished();

        //assertThrows(EnemyIsOutOfRange.class,()->{SilverTower.Attack(enemy,false);});
    }
    @Test
    void test06WhiteTowerAttackWhenEnemyIsWithinRangeEnemyTakesDamage() {
        Enemy enemy = Mockito.mock(Spider.class);
        TowerFactory factory = new TowerFactory();
        Tower WhiteTower = factory.createTower("WhiteTower");

        WhiteTower.constructionFinished();
        //WhiteTower.Attack(enemy,true);

        //verify(enemy).takeDamage(WhiteTower.getDamage());
    }

    @Test
    void test07SilverTowerAttackWhenEnemyIsWithinRangeEnemyTakesDamage() {
        Enemy enemy = Mockito.mock(Spider.class);
        TowerFactory factory = new TowerFactory();
        Tower SilverTower = factory.createTower("SilverTower");

        SilverTower.constructionFinished();
        //SilverTower.Attack(enemy,true);

        //verify(enemy).takeDamage(SilverTower.getDamage());
    }
*/
}
