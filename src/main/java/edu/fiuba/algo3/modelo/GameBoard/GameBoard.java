package edu.fiuba.algo3.modelo.GameBoard;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameBoard {
    ArrayList<plot> RunAway = ArrayList<plot>();
}

abstract class plot{
    ArrayList<Enemys> enemys = new ArrayList<Enemys>();
    ArrayList<Defense> defenses = new ArrayList<Defense>();
}

class Stone extends plot{
    public Stone(ArrayList<defense>)
}
