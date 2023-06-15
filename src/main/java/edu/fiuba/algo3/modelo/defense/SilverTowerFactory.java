package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;

import java.awt.*;

public class SilverTowerFactory implements DefenseFactory {
    @Override
    public Defense createDefense(Point cordinates) {
        return new SilverTower( new Credit(20),new Range(5),new Damage(2),2,cordinates);
    }
}
