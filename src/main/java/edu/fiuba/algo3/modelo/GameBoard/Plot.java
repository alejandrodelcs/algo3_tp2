package edu.fiuba.algo3.modelo.GameBoard;

public abstract class Plot {

    protected boolean occupy;
    public Plot(){
        this.occupy = false;
    }
    public abstract boolean readyToBuild();
    boolean itsOccupied(){ return occupy;}
}
