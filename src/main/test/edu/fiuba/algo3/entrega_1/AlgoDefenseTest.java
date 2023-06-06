package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Credit;
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
        AlgoDefense algoDefense = new AlgoDefense(player);
        Point coordinatesToADirt = new Point(2, 3);
        algoDefense.buildsATower(coordinatesToADirt, "WhiteTower");
        Point coordinatesToADirt0 = new Point(0, 2);
        algoDefense.buildsATower(coordinatesToADirt0, "SilverTower");

        //Act
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();


        //Assert
        assertTrue(algoDefense.towerOperatingInPLot(coordinatesToADirt));
        //assertTrue(algoDefense.towerOperatingInPLot(coordinatesToADirt0));
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
    public void test06VerifyThatEnemyUnitsAreDamagedAccordingToTheReceivedAttack(){
        //Arrange
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);
        ArrayList<Enemy> enemyArray = new ArrayList<Enemy>();
        EnemyFactory eFactory = new EnemyFactory();
        Enemy anAnt = eFactory.createEnemy("Ant");
        enemyArray.add(anAnt);
        Enemy aSpider = eFactory.createEnemy("Spider");
        enemyArray.add(aSpider);

        //Act
        algoDefense.spawnAnEnemy(enemyArray);
        Point coordinatesToADirt = new Point(3, 4);
        algoDefense.buildsATower(coordinatesToADirt, "WhiteTower");
        algoDefense.nextTurn();
        algoDefense.nextTurn();

        //Assert
        Assertions.assertTrue(anAnt.enemyDied());
        Assertions.assertFalse(aSpider.enemyDied());
    }
    @Test
    public void test08VerifyThatWhenDestroyingAnEnemyUnitThePlayerIsAwardedTheCorrespondingCredit(){
        //Arrange
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);
        Credit creditsExpected = new Credit(91);

        //Act
        Point coordinatesToADirt = new Point(4, 0);
        algoDefense.buildsATower(coordinatesToADirt, "WhiteTower");
        algoDefense.nextTurn();
        algoDefense.nextTurn();

        //Assert
        Assertions.assertTrue(creditsExpected.equalTo(player.getPlayerCredits()));
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
        //algoDefense.nextTurn(); //en esta linea el jugador se muere



        //Assert
        assertTrue(player.isAlive());
    }
    @Test
    public void test13PlayerWinsTheGame(){
        //Arrange
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);
        Point coordinatesToADirt = new Point(2, 3);
        algoDefense.buildsATower(coordinatesToADirt, "WhiteTower");
        Point coordinatesToADirt0 = new Point(0, 2);
        algoDefense.buildsATower(coordinatesToADirt0, "WhiteTower");
        Point coordinatesToADirt1 = new Point(1, 2);
        algoDefense.buildsATower(coordinatesToADirt1, "WhiteTower");
        Point coordinatesToADirt2 = new Point(2, 2);
        algoDefense.buildsATower(coordinatesToADirt2, "WhiteTower");
        Point coordinatesToADirt3 = new Point(3, 2);
        algoDefense.buildsATower(coordinatesToADirt3, "WhiteTower");
        Point coordinatesToADirt4 = new Point(1, 0);
        algoDefense.buildsATower(coordinatesToADirt4, "WhiteTower");
        Point coordinatesToADirt5 = new Point(2, 0);
        algoDefense.buildsATower(coordinatesToADirt5, "WhiteTower");
        Point coordinatesToADirt6 = new Point(3, 0);
        algoDefense.buildsATower(coordinatesToADirt6, "WhiteTower");
        Point coordinatesToADirt7 = new Point(4, 0);
        algoDefense.buildsATower(coordinatesToADirt7, "WhiteTower");
        Point coordinatesToADirt8 = new Point(5, 0);
        algoDefense.buildsATower(coordinatesToADirt8, "WhiteTower");





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
        //algoDefense.nextTurn();
        //algoDefense.nextTurn(); en esta linea el jugador se muere

        //Assert
        assertTrue(player.isAlive());
    }

}

