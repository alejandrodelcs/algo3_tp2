package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;

import java.awt.*;

public class TrapSandyFactory implements TowerFactory{
    @Override
    public Defense createDefense(Point cordinates) {
        return new TrapSandy( new Credit(25),cordinates,3);
    }
}
