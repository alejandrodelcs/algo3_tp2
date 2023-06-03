package edu.fiuba.algo3.modelo.GameBoard;

public class Dirt extends Plot{
    public Dirt(){super();}
    public boolean readyToBuild(){
        if (this.itsOccupied()){
            return false;
        }
        return true;
    }
}
