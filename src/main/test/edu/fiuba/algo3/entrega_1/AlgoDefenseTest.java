package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Turn;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.enemy.EnemyFactory;
import edu.fiuba.algo3.modelo.exceptions.*;
import edu.fiuba.algo3.modelo.facade.EnemyFacade;
import edu.fiuba.algo3.modelo.facade.GameboardFacade;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;

import static org.junit.jupiter.api.Assertions.*;


public class AlgoDefenseTest {

    @Test
    public void test01VerifyPlayerStartsWithTwentyLifePointsAndAHundredCredits(){
       Player player = new Player("Player");
       TowerFactory towerFactory = new TowerFactory();
       Point coordinatesToADirt = new Point(2, 3);
       Point secondCoordinatesToADirt = new Point(2, 4);
       Point thirdCoordinatesToADirt = new Point(2, 5);
       Point fourthCoordinatesToADirt = new Point(2, 6);
       Point fifthCoordinatesToADirt = new Point(2, 7);
       Point invalidCoordinatesToADirt = new Point(2, 8);
       Tower aSilverTower = towerFactory.createTower("SilverTower", coordinatesToADirt);
       Tower aSecondSilverTower = towerFactory.createTower("SilverTower", secondCoordinatesToADirt);
       Tower aThirdSilverTower = towerFactory.createTower("SilverTower", thirdCoordinatesToADirt);
       Tower aFourthSilverTower = towerFactory.createTower("SilverTower", fourthCoordinatesToADirt);
       Tower aFifthSilverTower = towerFactory.createTower("SilverTower", fifthCoordinatesToADirt);
       Tower invalidSilverTower = towerFactory.createTower("SilverTower", invalidCoordinatesToADirt);
       Damage tenDamage = new Damage(10);
       Damage nineDamage = new Damage(9);
       Damage theOneThatKills = new Damage(1);

       player.buildsADefense(aSilverTower);
       player.buildsADefense(aSecondSilverTower);
       player.buildsADefense(aThirdSilverTower);
       player.buildsADefense(aFourthSilverTower);
       player.buildsADefense(aFifthSilverTower);

       player.getsDamage(tenDamage);
       player.getsDamage(nineDamage);

       assertThrows(InsufficientCredits.class, () -> player.buildsADefense(invalidSilverTower));

       Assertions.assertTrue(player.isAlive());

       assertThrows(PlayerIsDeadGameOver.class, () -> player.getsDamage(theOneThatKills));
    }

    @Test
    public void test02verifyThatEachDefenseBuildsInTheRightAmountOfTurns(){
        //Arrange
        Player player = new Player("Player");
        GameBoard gameboard = new GameboardFacade().loadMap();
        Dictionary enemyStrategy = new EnemyFacade().loadEnemiesStrategy();
        Turn turn = new Turn(enemyStrategy);
        TowerFactory towerFactory = new TowerFactory();
        Point coordinatesToADirt = new Point(2, 3);
        Point secondCoordinatesToADirt = new Point(2,2);
        Tower aSilverTower = towerFactory.createTower("SilverTower", coordinatesToADirt);
        Tower aWhiteTower = towerFactory.createTower("WhiteTower", secondCoordinatesToADirt);

        //Act
        player.buildsADefense(aSilverTower);
        player.buildsADefense(aWhiteTower);

        assertFalse(aSilverTower.isItBuild());
        assertFalse(aWhiteTower.isItBuild());


        turn.passTurn();
        System.out.println(turn.getTurn());
        turn.passTurn();
        System.out.println(turn.getTurn());


        //Assert
        turn.passTurn();
        System.out.println(turn.getTurn());
        assertTrue(aWhiteTower.isItBuild());
        assertFalse(aSilverTower.isItBuild());
        turn.passTurn();
        //assertTrue(aSilverTower.isItBuild());
        //assertTrue(aWhiteTower.isItBuild());
    }

    @Test

    public void test03VerifyPlayerHasCreditsToBuyFiveSliverTowers(){
        Player player = new Player("Player");
        TowerFactory towerFactory = new TowerFactory();
        Point coordinatesToADirt = new Point(2, 3);
        Point secondCoordinatesToADirt = new Point(2, 4);
        Point thirdCoordinatesToADirt = new Point(2, 5);
        Point fourthCoordinatesToADirt = new Point(2, 6);
        Point fifthCoordinatesToADirt = new Point(2, 7);
        Point invalidCoordinatesToADirt = new Point(2, 8);
        Tower aSilverTower = towerFactory.createTower("SilverTower", coordinatesToADirt);
        Tower aSecondSilverTower = towerFactory.createTower("SilverTower", secondCoordinatesToADirt);
        Tower aThirdSilverTower = towerFactory.createTower("SilverTower", thirdCoordinatesToADirt);
        Tower aFourthSilverTower = towerFactory.createTower("SilverTower", fourthCoordinatesToADirt);
        Tower aFifthSilverTower = towerFactory.createTower("SilverTower", fifthCoordinatesToADirt);
        Tower invalidSilverTower = towerFactory.createTower("SilverTower", invalidCoordinatesToADirt);
        Damage tenDamage = new Damage(10);
        Damage nineDamage = new Damage(9);

        player.buildsADefense(aSilverTower);
        player.buildsADefense(aSecondSilverTower);
        player.buildsADefense(aThirdSilverTower);
        player.buildsADefense(aFourthSilverTower);
        player.buildsADefense(aFifthSilverTower);

        assertThrows(InsufficientCredits.class, () -> player.buildsADefense(invalidSilverTower));

        Assertions.assertTrue(player.isAlive());
    }

    @Test
    public void test04VerifyThatTowerCanNotBeBuildOnStoneButCanBeBuildOnDirt(){
      Player player = new Player("Player");
      AlgoDefense algoDefense = new AlgoDefense(player);
      Point coordenatesToStone = new Point(0,0);
      Point coordenatesToDirt = new Point(3,3);
      String silverTower = "SilverTower";

      //Act
      algoDefense.buildsATower(coordenatesToDirt, silverTower);

      //Asseert
      Assertions.assertTrue(algoDefense.isOccupyByADefense(coordenatesToDirt));
      Assertions.assertThrows(NonConstructibleArea.class,()-> algoDefense.buildsATower(coordenatesToStone, silverTower));

    }

    @Test
    public void test05VerifyThatTowersAttackWithinExpectedRange(){
      //Arrange
      Player player = new Player("Player");
      AlgoDefense algoDefense = new AlgoDefense(player);
      ArrayList<Enemy> enemyArray = new ArrayList<Enemy>();
      EnemyFactory eFactory = new EnemyFactory();
      Enemy anAnt = eFactory.createEnemy("Ant");
      enemyArray.add(anAnt);

      //Act
      algoDefense.spawnAnEnemy(enemyArray);
      Point coordinatesToADirt = new Point(2, 3);
      algoDefense.buildsATower(coordinatesToADirt, "WhiteTower");
      algoDefense.nextTurn();
      algoDefense.nextTurn();

      //Assert
      assertTrue(anAnt.enemyDied());
    }

    @Test
    public void test12VerifyThatEnemiesKilledPlayer(){
        //Arrange
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);


        //Act
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();

        //Assert
        assertTrue(player.isAlive());
    }


}

