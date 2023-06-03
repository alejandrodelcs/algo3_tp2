package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;

public class NotOccupiable implements Occupiable{
    @Override
    public boolean itsOccupied(){throw new NonConstructibleArea();}
}
