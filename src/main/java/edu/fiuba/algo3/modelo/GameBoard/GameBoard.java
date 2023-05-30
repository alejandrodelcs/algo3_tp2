package edu.fiuba.algo3.modelo.GameBoard;
import edu.fiuba.algo3.modelo.Defense.Tower;

import java.awt.*;

public class GameBoard {
    private Plot[][] plots;

    public GameBoard() {
        plots = new Plot[5][5];

        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                plots[i][j] = new Dirt();
            }
        }
        plots[3][3] = new Stone();
    }
    public boolean availableForBuilding(Tower tower, Point coordinates) {

        int x = (int) Math.round(coordinates.getX());
        int y = (int) Math.round(coordinates.getY());
        Plot plotToCheck = plots[x][y];
        return plotToCheck.readyToBuild();
    }

    public void buildDefense(Tower tower, Point coordinates) {

    }
    /*ArrayList<plot> RunAway = ArrayList<plot>();*/
}
/*
abstract class plot{
    ArrayList<Enemys> enemys = new ArrayList<Enemys>();
    ArrayList<Defense> defenses = new ArrayList<Defense>();
}

class Stone extends plot{
    public Stone(ArrayList<defense>);
}
*/