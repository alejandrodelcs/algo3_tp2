package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.algodefense.AlgoDefense;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.SandyTrapFactory;
import edu.fiuba.algo3.modelo.player.Player;
import org.junit.jupiter.api.Test;

import java.awt.*;

class SandyTrapTest {
    @Test
    public void test01createASandyTrap(){
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);
        SandyTrapFactory sandyTrapFactory = new SandyTrapFactory();
        Point coordinatesToAPath = new Point(2, 1);
        Point coordinatesToAPath1 = new Point(3, 1);
        Point coordinatesToAPath2 = new Point(4, 1);
        Point coordinatesToAPath3 = new Point(5, 1);
        Defense SandTrap = sandyTrapFactory.createDefense(coordinatesToAPath);
        Defense SandTrap1 = sandyTrapFactory.createDefense(coordinatesToAPath1);
        Defense SandTrap2 = sandyTrapFactory.createDefense(coordinatesToAPath2);
        Defense SandTrap3 = sandyTrapFactory.createDefense(coordinatesToAPath3);
        algoDefense.buildsADefense(SandTrap);
        algoDefense.buildsADefense(SandTrap1);
        algoDefense.buildsADefense(SandTrap2);
        algoDefense.buildsADefense(SandTrap3);
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();

    }

}