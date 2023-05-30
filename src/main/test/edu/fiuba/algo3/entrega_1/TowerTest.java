package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Defense.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
public class TowerTest {
    @Test
    public void test01NewWhiteTowerHas10Credits() {
        WhiteTower WhiteTower = new WhiteTower();

        assertEquals(10, WhiteTower.getCredits());
    }
    @Test
    public void test02NewSilverTowerHas20Credits() {
        SilverTower SilverTower = new SilverTower();

        assertEquals(20, SilverTower.getCredits());
    }


    @Test
    void test03NewWhiteTowerCannotAttackTheEnemy() {
        /*
        WhiteTower WhiteTower = new WhiteTower();
        Enemy enemyStub = Mockito.mock(Enemy.class);
        Mockito.when(enemyStub.isWithinAttackRange()).thenReturn(true);
        assertThrows(TowerIsUnderConstruction.class,()->{
            WhiteTower.Attack(enemyStub);});

         */
    }
    @Test
    void test04WhiteTowerOperationalAttacksAnEnemyOutOfRange() {
        /*
        WhiteTower WhiteTower = new WhiteTower();
        Enemy enemyStub = Mockito.mock(Enemy.class);
        Mockito.when(enemyStub.isWithinAttackRange()).thenReturn(false);
        WhiteTower.constructionFinished();
        assertThrows(EnemyIsOutOfRange.class,()->{
            WhiteTower.Attack(enemyStub);});

         */
    }
    @Test
    void test05NewSilverTowerCannotAttackTheEnemy() {
        /*
        SilverTower SilverTower = new SilverTower();
        Enemy enemyStub = Mockito.mock(Enemy.class);
        Mockito.when(enemyStub.isWithinAttackRange()).thenReturn(true);
        assertThrows(TowerIsUnderConstruction.class,()->{
            SilverTower.Attack(enemyStub);});

         */
    }
    @Test
    void test06SilverTowerOperationalAttacksAnEnemyOutOfRange() {
        /*
        SilverTower SilverTower = new SilverTower();
        Enemy enemyStub = Mockito.mock(Enemy.class);
        Mockito.when(enemyStub.isWithinAttackRange()).thenReturn(false);
        SilverTower.constructionFinished();
        assertThrows(EnemyIsOutOfRange.class,()->{
            SilverTower.Attack(enemyStub);});
         */
    }
    /*
    @Test
    void test07WhiteTowerOperationalAttacksAnEnemyWithinRange() {
        WhiteTower WhiteTower = new WhiteTower();
        Enemy enemyStub = Mockito.mock(Enemy.class);
        Mockito.when(enemyStub.isWithinAttackRange()).thenReturn(true);
        Mockito.when(enemyStub.receiveDamage(1)).thenReturn(0);
        WhiteTower.constructionFinished();
        WhiteTower.Attack(enemyStub);
        assertEquals(0, );
    }

    Copy the same tests for the Silver Tower

    Add use case 2
    */
    }


