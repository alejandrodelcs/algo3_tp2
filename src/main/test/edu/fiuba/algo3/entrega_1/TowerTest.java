package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.defense.*;
import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import edu.fiuba.algo3.modelo.Enemy.*;
import static org.junit.jupiter.api.Assertions.*;
public class TowerTest {
    @Test
    public void test01NewWhiteTowerHas10Credits() {
        WhiteTower WhiteTower = new WhiteTower();

        assertEquals(10, WhiteTower.getCredits().getQuantity());
    }

    @Test
    public void test02NewSilverTowerHas20Credits() {
        SilverTower SilverTower = new SilverTower();

        assertEquals(20, SilverTower.getCredits().getQuantity());
    }

}