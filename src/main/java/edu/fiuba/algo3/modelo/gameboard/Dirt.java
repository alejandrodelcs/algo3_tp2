package edu.fiuba.algo3.modelo.gameboard;

public class Dirt extends Plot{
    @Override
    public String Show() {
        return "...";
    }
    public Dirt(){state = new Available();}

}
