package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.exceptions.TowerDoesNotExist;


public class TowerFactory {

    public Tower createTower(String towerType) {
        switch (towerType) {
            case "WhiteTower":
                return new WhiteTower( new Credit(10),3,1,1);
            case "SilverTower":
                return new SilverTower(new Credit(20),5,2, 2);
            default:
                throw new TowerDoesNotExist();
        }
    }
}
