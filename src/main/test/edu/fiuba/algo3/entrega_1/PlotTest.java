package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.defense.WhiteTower;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;
import edu.fiuba.algo3.modelo.exceptions.TheEnemyCannotBeOutsideTheRunway;
import edu.fiuba.algo3.modelo.gameboard.Path;
import edu.fiuba.algo3.modelo.gameboard.Stone;
import edu.fiuba.algo3.modelo.gameboard.Dirt;
import edu.fiuba.algo3.modelo.gameboard.Plot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;


public class PlotTest {/*
    @Test
    public void test01canBuildATowerOnAnAvailableDirtPlot(){
        Plot dirt = new Dirt();
        Tower tower = mock(Tower.class);

        Assertions.assertTrue(dirt.readyToBuild());
        dirt.setDefense(tower);

        Assertions.assertFalse(dirt.readyToBuild());
    }
    @Test
    public void test02cannotBuildOnAStonePlot(){
        Plot stone = new Stone();

        assertThrows(NonConstructibleArea.class, () -> {
            stone.readyToBuild();
        });
    }
    @Test
    public void test03cannotBuildOnAPathPlot(){
        Plot path = new Path();

        assertThrows(NonConstructibleArea.class, () -> {
            path.readyToBuild();
        });
    }
    @Test
    public void test04cannotSetEnemyOnAvailableDirtPlot() {
        Plot dirt = new Dirt();
        Enemy enemy = mock(Enemy.class);

        assertThrows(TheEnemyCannotBeOutsideTheRunway.class, () -> {
            dirt.setEnemy(enemy);
        });
    }

    @Test
    public void test05cannotSetEnemyOnAStonePlot() {
        Plot stone = new Stone();
        Enemy enemy = mock(Enemy.class);

        assertThrows(TheEnemyCannotBeOutsideTheRunway.class, () -> {
            stone.setEnemy(enemy);
        });
    }
    /*
    @Test
    public void test06canSetEnemyOnAPathPlot() {
        Plot path = new Path();
        Enemy enemy = mock(Enemy.class);

        assertThrows(NonConstructibleArea.class, () -> {
            path.setEnemy(enemy);
        });
    }
*/
}
