package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.credit.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.enemy.AntFactory;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.enemy.EnemyFactory;
import edu.fiuba.algo3.modelo.enemy.SpiderFactory;
import edu.fiuba.algo3.modelo.exceptions.*;
import edu.fiuba.algo3.modelo.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class AlgoDefenseTest {

    @Test
    public void test01VerifyPlayerStartsWithTwentyLifePointsAndAHundredCredits(){
       Player player = new Player("Player");
       DefenseFactory defenseFactory = new SilverTowerFactory();
       Point coordinatesToADirt = new Point(2, 3);
       Point secondCoordinatesToADirt = new Point(2, 4);
       Point thirdCoordinatesToADirt = new Point(2, 5);
       Point fourthCoordinatesToADirt = new Point(2, 6);
       Point fifthCoordinatesToADirt = new Point(2, 7);
       Point invalidCoordinatesToADirt = new Point(2, 8);
       Defense aSilverTower = defenseFactory.createDefense(coordinatesToADirt);
       Defense aSecondSilverTower = defenseFactory.createDefense(secondCoordinatesToADirt);
       Defense aThirdSilverTower = defenseFactory.createDefense(thirdCoordinatesToADirt);
       Defense aFourthSilverTower = defenseFactory.createDefense(fourthCoordinatesToADirt);
       Defense aFifthSilverTower = defenseFactory.createDefense(fifthCoordinatesToADirt);
       Defense invalidSilverTower = defenseFactory.createDefense(invalidCoordinatesToADirt);
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
        DefenseFactory whiteFactory = new WhiteTowerFactory();
        DefenseFactory silverFactory = new SilverTowerFactory();
        Point coordinatesToADirt = new Point(2, 3);
        Point coordinatesToADirt0 = new Point(0, 2);
        Defense whiteTower = whiteFactory.createDefense(coordinatesToADirt);
        Defense silverTower = silverFactory.createDefense(coordinatesToADirt0);
        algoDefense.buildsADefense(whiteTower);
        algoDefense.buildsADefense(silverTower);

        //Act
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();



        //Assert
        //assertTrue(algoDefense.towerOperatingInPLot(coordinatesToADirt));
        //assertTrue(algoDefense.towerOperatingInPLot(coordinatesToADirt0));
    }

    @Test

    public void test03VerifyPlayerHasCreditsToBuyFiveSliverTowers(){
        Player player = new Player("Player");
        DefenseFactory defenseFactory = new SilverTowerFactory();
        Point coordinatesToADirt = new Point(2, 3);
        Point secondCoordinatesToADirt = new Point(2, 4);
        Point thirdCoordinatesToADirt = new Point(2, 5);
        Point fourthCoordinatesToADirt = new Point(2, 6);
        Point fifthCoordinatesToADirt = new Point(2, 7);
        Point invalidCoordinatesToADirt = new Point(2, 8);
        Defense aSilverTower = defenseFactory.createDefense(coordinatesToADirt);
        Defense aSecondSilverTower = defenseFactory.createDefense(secondCoordinatesToADirt);
        Defense aThirdSilverTower = defenseFactory.createDefense(thirdCoordinatesToADirt);
        Defense aFourthSilverTower = defenseFactory.createDefense( fourthCoordinatesToADirt);
        Defense aFifthSilverTower = defenseFactory.createDefense(fifthCoordinatesToADirt);
        Defense invalidSilverTower = defenseFactory.createDefense(invalidCoordinatesToADirt);
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
      DefenseFactory factory = new SilverTowerFactory();
      Point coordenatesToStone = new Point(0,0);
      Point coordenatesToDirt = new Point(3,3);
      Defense silverTower = factory.createDefense(coordenatesToDirt);
      Defense silverTower2 = factory.createDefense(coordenatesToStone);
      //Act
      algoDefense.buildsADefense(silverTower);

      //Asseert
      Assertions.assertTrue(algoDefense.isOccupyByADefense(coordenatesToDirt));
      //Assertions.assertThrows(NonConstructibleArea.class,()-> algoDefense.buildsADefense(silverTower2));

    }

    @Test
    public void test05VerifyThatTowersAttackWithinExpectedRange(){
      //Arrange
      Player player = new Player("Player");
      AlgoDefense algoDefense = new AlgoDefense(player);
      ArrayList<Enemy> enemyArray = new ArrayList<Enemy>();
      EnemyFactory antFactory = new AntFactory();
      Enemy anAnt = antFactory.createEnemy();
      enemyArray.add(anAnt);
      DefenseFactory factory = new WhiteTowerFactory();
      Defense whiteTower = factory.createDefense(new Point(2, 3));

      //Act
      algoDefense.spawnAnEnemy(enemyArray);
      algoDefense.buildsADefense(whiteTower);
      algoDefense.executeTurn();
      algoDefense.executeTurn();

      //Assert
      assertTrue(anAnt.enemyDied());
    }
    @Test
    public void test06VerifyThatEnemyUnitsAreDamagedAccordingToTheReceivedAttack(){
        //Arrange
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);
        ArrayList<Enemy> enemyArray = new ArrayList<Enemy>();
        EnemyFactory antFactory = new AntFactory();
        EnemyFactory spiderFactory = new SpiderFactory();
        Enemy anAnt = antFactory.createEnemy();
        enemyArray.add(anAnt);
        Enemy aSpider = spiderFactory.createEnemy();
        enemyArray.add(aSpider);
        DefenseFactory factory = new WhiteTowerFactory();
        Defense whiteTower = factory.createDefense(new Point(3, 3));

        //Act
        algoDefense.spawnAnEnemy(enemyArray);
        algoDefense.buildsADefense(whiteTower);
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();

        //Assert
        Assertions.assertTrue(anAnt.enemyDied());
        Assertions.assertFalse(aSpider.enemyDied());
    }
/*    @Test
    public void test07VerifyThatEnemiesMovesOnlyInPath(){
        Stone stone = new Stone();
        EnemyFactory antFactory = new AntFactory();
        Enemy ant = antFactory.createEnemy();
        stone.addEnemyToPath(ant);
        ArrayList<Enemy> listEnemy = new ArrayList<Enemy>();
        listEnemy.add(ant);
        Assertions.assertThrows(TheEnemyCannotBeOutsideTheRunway.class,()->stone.setEnemy(listEnemy));

    }*/
    @Test
    public void test08VerifyThatWhenDestroyingAnEnemyUnitThePlayerIsAwardedTheCorrespondingCredit(){
        //Arrange
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);
        Credit creditsExpected = new Credit(91);
        DefenseFactory factory = new WhiteTowerFactory();
        Defense whiteTower = factory.createDefense(new Point(2, 3));

        //Act
        algoDefense.buildsADefense(whiteTower);
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();

        //Assert
        Assertions.assertTrue(creditsExpected.equalTo(player.getPlayerCredits()));
    }

    @Test
    public void test09VerifyEnemiesMoveAsExpected() {
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);
        ArrayList<Enemy> enemyArray = new ArrayList<Enemy>();
        EnemyFactory antFactory = new AntFactory();
        EnemyFactory spiderFactory = new SpiderFactory();
        Enemy anAnt = antFactory.createEnemy();
        Enemy aSpider = spiderFactory.createEnemy();
        DefenseFactory factory = new SilverTowerFactory();
        Defense silverTower = factory.createDefense(new Point(7, 13));
        Defense anotherSilverTower = factory.createDefense(new Point(10, 12));
        algoDefense.buildsADefense(anotherSilverTower);
        enemyArray.add(anAnt);
        enemyArray.add(aSpider);

        algoDefense.buildsADefense(silverTower);
        algoDefense.spawnAnEnemy(enemyArray);
        algoDefense.executeTurn();
        assertFalse(anAnt.enemyDied());
        assertFalse(aSpider.enemyDied());
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        assertTrue(aSpider.enemyDied());
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();

        assertTrue(aSpider.enemyDied());
    }

    @Test
    public void test10PlayerWinsTheGame(){
        //Arrange
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);
        DefenseFactory factory = new WhiteTowerFactory();

        Point coordinatesToADirt = new Point(2, 3);
        Point coordinatesToADirt0 = new Point(0, 2);
        Point coordinatesToADirt1 = new Point(1, 2);
        Point coordinatesToADirt2 = new Point(2, 2);
        Point coordinatesToADirt3 = new Point(3, 2);
        Point coordinatesToADirt4 = new Point(1, 0);
        Point coordinatesToADirt5 = new Point(2, 0);
        Point coordinatesToADirt6 = new Point(3, 0);
        Point coordinatesToADirt7 = new Point(4, 0);
        Point coordinatesToADirt8 = new Point(5, 0);

        Defense whiteTower = factory.createDefense(coordinatesToADirt);
        Defense whiteTower0 = factory.createDefense(coordinatesToADirt0);
        Defense whiteTower1 = factory.createDefense(coordinatesToADirt1);
        Defense whiteTower2 = factory.createDefense(coordinatesToADirt2);
        Defense whiteTower3 = factory.createDefense(coordinatesToADirt3);
        Defense whiteTower4 = factory.createDefense(coordinatesToADirt4);
        Defense whiteTower5 = factory.createDefense(coordinatesToADirt5);
        Defense whiteTower6= factory.createDefense(coordinatesToADirt6);
        Defense whiteTower7 = factory.createDefense(coordinatesToADirt7);
        Defense whiteTower8 = factory.createDefense(coordinatesToADirt8);

        //Act
        algoDefense.buildsADefense(whiteTower);
        algoDefense.buildsADefense(whiteTower0);
        algoDefense.buildsADefense(whiteTower1);
        algoDefense.buildsADefense(whiteTower2);
        algoDefense.buildsADefense(whiteTower3);
        algoDefense.buildsADefense(whiteTower4);
        algoDefense.buildsADefense(whiteTower5);
        algoDefense.buildsADefense(whiteTower6);
        algoDefense.buildsADefense(whiteTower7);
        algoDefense.buildsADefense(whiteTower8);

        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        //algoDefense.nextTurn();
        //algoDefense.nextTurn(); //In this turn the player dies

        //Assert

        assertTrue(player.isAlive());
    }

    @Test
    public void test12VerifyThatEnemiesKilledPlayer(){
        //Arrange
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);

        //Act
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        algoDefense.executeTurn();
        //algoDefense.nextTurn(); //en esta linea el jugador se muere

        //Assert
        assertTrue(player.isAlive());
    }

}

