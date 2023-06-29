package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.enemy.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.awt.*;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class TowerTest {

  @Test
    void test01NewWhiteTowerUnderConstructionCannotAttackEnemy() {
        DefenseFactory factory = new WhiteTowerFactory();
        Point cordenates = new Point(3,3);
        Defense WhiteTower = factory.createDefense(cordenates);
        Enemy enemyMock = mock(Ant.class);
        when(enemyMock.getPoint()).thenReturn(new Point(4,4));
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemyMock);

        WhiteTower.attack(enemies);
        verify(enemyMock, never()).takeDamage(new Damage(1));
        Assertions.assertFalse(enemyMock.enemyDied());
    }
    @Test
    void test02NewSilverTowerUnderConstructionCannotAttackEnemy() {
        DefenseFactory factory = new SilverTowerFactory();
        Point cordenates = new Point(3,3);
        Defense SilverTower = factory.createDefense(cordenates);
        Enemy enemy = mock(Spider.class);
        when(enemy.getPoint()).thenReturn(new Point(9,9));
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        SilverTower.attack(enemies);
        verify(enemy, never()).takeDamage(new Damage(2));
        Assertions.assertFalse(enemy.enemyDied());

        SilverTower.attack(enemies);
        verify(enemy, never()).takeDamage(new Damage(2));
        Assertions.assertFalse(enemy.enemyDied());

    }

    @Test
    void test03WhiteTowerAttackWhenEnemyIsWithinRangeEnemyTakesDamage() {
        DefenseFactory factory = new WhiteTowerFactory();
        Point coordinates = new Point(3, 3);
        Defense whiteTower = factory.createDefense(coordinates);
        Enemy antMock = mock(Ant.class);
        when(antMock.getPoint()).thenReturn(new Point(4, 4));
        when(antMock.enemyDied()).thenReturn(false);
        when(antMock.isVisible()).thenReturn(true);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(antMock);

        whiteTower.constructionFinished();
        whiteTower.attack(enemies);

        ArgumentCaptor<Damage> damageCaptor = ArgumentCaptor.forClass(Damage.class);
        verify(antMock, atLeastOnce()).takeDamage(damageCaptor.capture());
    }

     @Test
    void test04SilverTowerAttackWhenEnemyIsWithinRangeEnemyTakesDamage() {
        DefenseFactory factory = new SilverTowerFactory();
        Point cordenates = new Point(3,3);
        Defense SilverTower = factory.createDefense(cordenates);
        Enemy spiderMock = mock(Spider.class);
        when(spiderMock.getPoint()).thenReturn(new Point(4,4));
        when(spiderMock.enemyDied()).thenReturn(false);
        when(spiderMock.isVisible()).thenReturn(true);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(spiderMock);


        SilverTower.constructionFinished();
        SilverTower.attack(enemies);

         ArgumentCaptor<Damage> damageCaptor = ArgumentCaptor.forClass(Damage.class);
         verify(spiderMock, atLeastOnce()).takeDamage(damageCaptor.capture());

    }

    @Test
    void test05SilverTowerCannotAttackAnEnemyOutOfRange() {
        DefenseFactory factory = new SilverTowerFactory();
        Point cordenates = new Point(3,3);
        Defense SilverTower = factory.createDefense(cordenates);
        Enemy spiderMock = mock(Spider.class);
        when(spiderMock.getPoint()).thenReturn(new Point(4,4));
        when(spiderMock.enemyDied()).thenReturn(false);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(spiderMock);

        SilverTower.constructionFinished();
        SilverTower.attack(enemies);

        verify(spiderMock, never()).takeDamage(new Damage(1));
        Assertions.assertFalse(spiderMock.enemyDied());
    }

    @Test
    void test06SilverTowerCannotAttackAnEnemyOutOfRange() {

        DefenseFactory factory = new SilverTowerFactory();
        Point cordenates = new Point(3,3);
        Defense SilverTower = factory.createDefense(cordenates);
        Enemy spiderMock = mock(Spider.class);
        when(spiderMock.getPoint()).thenReturn(new Point(9,9));
        when(spiderMock.enemyDied()).thenReturn(false);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(spiderMock);

        SilverTower.constructionFinished();
        SilverTower.attack(enemies);

        verify(spiderMock, never()).takeDamage(new Damage(2));
        Assertions.assertFalse(spiderMock.enemyDied());
    }
}