package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.exceptions.TowerDoesNotExist;
import java.awt.*;


public class TowerFactory {

    public Tower createTower(String towerType, Point coordinates) {
        switch (towerType) {
            case "WhiteTower":
                return new WhiteTower( new Credit(10),3,new Damage(1),1,coordinates);
            case "SilverTower":
                return new SilverTower(new Credit(20),5,new Damage(2), 2,coordinates);
            default:
                throw new TowerDoesNotExist();
        }
    }
}
