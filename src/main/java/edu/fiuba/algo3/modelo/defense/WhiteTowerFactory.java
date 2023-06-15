package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;

import java.awt.*;

public class WhiteTowerFactory implements TowerFactory{
    @Override
    public Defense createDefense(Point cordinates) {
        return new WhiteTower( new Credit(10),new Range(3),new Damage(1),1,cordinates);
    }
}
