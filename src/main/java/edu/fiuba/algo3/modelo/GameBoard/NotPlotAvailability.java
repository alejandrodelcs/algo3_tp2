package edu.fiuba.algo3.modelo.gameboard;

import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;

public class NotPlotAvailability implements PlotAvailability {
    @Override
    public boolean itsOccupied(){throw new NonConstructibleArea();}
}
