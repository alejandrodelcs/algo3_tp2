package edu.fiuba.algo3.modelo.gameboard;

public class Stone extends Plot{
    public Stone(){super();}
    @Override
    public boolean readyToBuild(){
        return false;
    }

    @Override
    public String Show() {
        return "xxx";
    }
}
