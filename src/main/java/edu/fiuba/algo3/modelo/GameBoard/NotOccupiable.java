package edu.fiuba.algo3.modelo.GameBoard;

import edu.fiuba.algo3.modelo.Exceptions.NonConstructibleArea;

public class NotOccupiable implements Occupiable{
    @Override
    public boolean itsOccupied(){throw new NonConstructibleArea();}
}
