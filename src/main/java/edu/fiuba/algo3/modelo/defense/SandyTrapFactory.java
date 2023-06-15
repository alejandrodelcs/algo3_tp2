package edu.fiuba.algo3.modelo.defense;

import edu.fiuba.algo3.modelo.credit.Credit;

import java.awt.*;

public class SandyTrapFactory implements DefenseFactory {
    @Override
    public Defense createDefense(Point cordinates) {
        return new SandyTrap( new Credit(25),cordinates,3);
    }
}
