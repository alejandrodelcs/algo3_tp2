package edu.fiuba.algo3.entrega_1.model;

import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.exceptions.EnemyDoesNotExist;
import edu.fiuba.algo3.modelo.exceptions.TowerDoesNotExist;
import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import edu.fiuba.algo3.modelo.Enemy.*;
import static org.junit.jupiter.api.Assertions.*;
public class TowerTest {
    @Test
    public void test01NewWhiteTowerHas10Credits() {
        TowerFactory factory = new TowerFactory();
        Tower WhiteTower = factory.createTower("WhiteTower");

        assertEquals(10, WhiteTower.getCredits().getQuantity());
    }

    @Test
    public void test02NewSilverTowerHas20Credits() {
        TowerFactory factory = new TowerFactory();
        Tower SilverTower = factory.createTower("SilverTower");

        assertEquals(20, SilverTower.getCredits().getQuantity());
    }
    @Test
    public void test03TryingToCreateADifferentEnemyThrowsExceptions() {
        TowerFactory factory = new TowerFactory();
        assertThrows(TowerDoesNotExist.class, () -> {
            Tower SilverTower = factory.createTower("BlackTower");
        });
    }

}