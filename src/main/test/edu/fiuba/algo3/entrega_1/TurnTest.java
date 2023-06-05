package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.exceptions.TowerIsUnderConstruction;
import edu.fiuba.algo3.modelo.defense.Tower;
import edu.fiuba.algo3.modelo.defense.TowerFactory;
import edu.fiuba.algo3.modelo.defense.WhiteTower;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.enemy.EnemyFactory;
import edu.fiuba.algo3.modelo.facade.EnemyFacade;
import edu.fiuba.algo3.modelo.facade.GameboardFacade;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;
import edu.fiuba.algo3.modelo.player.Player;
import edu.fiuba.algo3.modelo.Turn;

import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TurnTest {
    @Test
    public void test01TowerDoesNotFinishBuild(){
        EnemyFacade enemyFacade = new EnemyFacade();
        Turn turn = new Turn(enemyFacade.loadEnemiesStrategy());
        Tower tower = new TowerFactory().createTower("SilverTower",new Point(2,3));
        ArrayList<Tower> listTower =  new ArrayList<Tower>();
        listTower.add(tower);
        turn.passTurn(listTower);
        Enemy newSpider = new EnemyFactory().createEnemy("Spider");
        //Assertions.assertThrows(TowerIsUnderConstruction.class,() -> {
            //listTower.get(0).attack(newSpider); } );TODO: Tower Attack implementation
    }
    @Test
    public void test02TowerFinishBuilingInTwoTurnAndKillASpider(){
        EnemyFacade enemyFacade = new EnemyFacade();
        Turn turn = new Turn(enemyFacade.loadEnemiesStrategy());
        Tower tower = new TowerFactory().createTower("SilverTower",new Point(2,3));
        ArrayList<Tower> listTower =  new ArrayList<Tower>();
        listTower.add(tower);
        turn.passTurn(listTower);
        turn.passTurn(listTower);
        Enemy newSpider = new EnemyFactory().createEnemy("Spider");
        //listTower.get(0).attack(newSpider);
        //Assertions.assertTrue(newSpider.enemyDied());
    }
}


        /*
        @Test
        public void tes01thePlayerResetTurnItShouldNotBeCounted(){
                Player player = new Player("Player01");
                Turn turn = new PlayerTurn(player);
                //Assertions.assertEquals(true,turn.passTurn());

        }

        @Test
        public void test02duringTheExecutionOfTheTurnTheEnemyShouldMakeAMoveInGameboard(){
                Turn turn = new EnemyTurn();
                turn.executeTurn();

        }

        @Test
        public void test03theTimeRequiredToBuildAWhiteTowerShouldBeLimitedToASingleTurn(){
                Player player = new Player("Player01");
                Turn turn = new PlayerTurn(player);
                turn.executeTurn();

        }

        @Test
        public void test04theTimeRequiredToBuildASilverTowerShouldBeLimitedToATwoTurns(){
                Player player = new Player("Player01");
                Turn turn = new PlayerTurn(player);
                turn.executeTurn();
                turn.executeTurn();

        }
        */

