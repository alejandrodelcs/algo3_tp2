package edu.fiuba.algo3.modelo.gameboard;

public class Path extends Plot{
    public Path(){super();}
    @Override
    public boolean readyToBuild(){
        return false;
    }

    @Override
    public String Show() {
        return "ooo";
    }
}
