package edu.fiuba.algo3.modelo.gameboard;

public class Dirt extends Plot{
    public Dirt(){super();}
    @Override
    public boolean readyToBuild(){
        if (this.itsOccupied()){
            return false;
        }
        return true;
    }

    @Override
    public String Show() {
        return "...";
    }
}
