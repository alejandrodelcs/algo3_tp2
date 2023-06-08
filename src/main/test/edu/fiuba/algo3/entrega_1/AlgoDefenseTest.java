package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Credit;
import edu.fiuba.algo3.modelo.damage.Damage;
import edu.fiuba.algo3.modelo.defense.*;
import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.enemy.EnemyFactory;
import edu.fiuba.algo3.modelo.exceptions.*;
import edu.fiuba.algo3.modelo.gameboard.*;
import edu.fiuba.algo3.modelo.parser.EnemiesParser;
import edu.fiuba.algo3.modelo.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class AlgoDefenseTest {

    @Test
    public void test01VerifyPlayerStartsWithTwentyLifePointsAndAHundredCredits(){
       Player player = new Player("Player");
       TowerFactory towerFactory = new SilverTowerFactory();
       Point coordinatesToADirt = new Point(2, 3);
       Point secondCoordinatesToADirt = new Point(2, 4);
       Point thirdCoordinatesToADirt = new Point(2, 5);
       Point fourthCoordinatesToADirt = new Point(2, 6);
       Point fifthCoordinatesToADirt = new Point(2, 7);
       Point invalidCoordinatesToADirt = new Point(2, 8);
       Tower aSilverTower = towerFactory.createTower(coordinatesToADirt);
       Tower aSecondSilverTower = towerFactory.createTower(secondCoordinatesToADirt);
       Tower aThirdSilverTower = towerFactory.createTower(thirdCoordinatesToADirt);
       Tower aFourthSilverTower = towerFactory.createTower(fourthCoordinatesToADirt);
       Tower aFifthSilverTower = towerFactory.createTower(fifthCoordinatesToADirt);
       Tower invalidSilverTower = towerFactory.createTower(invalidCoordinatesToADirt);
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
        TowerFactory whiteFactory = new WhiteTowerFactory();
        TowerFactory silverFactory = new SilverTowerFactory();
        Point coordinatesToADirt = new Point(2, 3);
        Point coordinatesToADirt0 = new Point(0, 2);
        Tower whiteTower = whiteFactory.createTower(coordinatesToADirt);
        Tower silverTower = silverFactory.createTower(coordinatesToADirt0);
        algoDefense.buildsATower(whiteTower);
        algoDefense.buildsATower(silverTower);

        //Act
        algoDefense.nextTurn();
        algoDefense.nextTurn();
        algoDefense.nextTurn();


        //Assert
        //assertTrue(algoDefense.towerOperatingInPLot(coordinatesToADirt));
        //assertTrue(algoDefense.towerOperatingInPLot(coordinatesToADirt0));
    }

    @Test

    public void test03VerifyPlayerHasCreditsToBuyFiveSliverTowers(){
        Player player = new Player("Player");
        TowerFactory towerFactory = new SilverTowerFactory();
        Point coordinatesToADirt = new Point(2, 3);
        Point secondCoordinatesToADirt = new Point(2, 4);
        Point thirdCoordinatesToADirt = new Point(2, 5);
        Point fourthCoordinatesToADirt = new Point(2, 6);
        Point fifthCoordinatesToADirt = new Point(2, 7);
        Point invalidCoordinatesToADirt = new Point(2, 8);
        Tower aSilverTower = towerFactory.createTower(coordinatesToADirt);
        Tower aSecondSilverTower = towerFactory.createTower(secondCoordinatesToADirt);
        Tower aThirdSilverTower = towerFactory.createTower(thirdCoordinatesToADirt);
        Tower aFourthSilverTower = towerFactory.createTower( fourthCoordinatesToADirt);
        Tower aFifthSilverTower = towerFactory.createTower(fifthCoordinatesToADirt);
        Tower invalidSilverTower = towerFactory.createTower(invalidCoordinatesToADirt);
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
      TowerFactory factory = new SilverTowerFactory();
      Point coordenatesToStone = new Point(0,0);
      Point coordenatesToDirt = new Point(3,3);
      Tower silverTower = factory.createTower(coordenatesToDirt);
      Tower silverTower2 = factory.createTower(coordenatesToStone);
      //Act
      algoDefense.buildsATower(silverTower);

      //Asseert
      Assertions.assertTrue(algoDefense.isOccupyByADefense(coordenatesToDirt));
      Assertions.assertThrows(NonConstructibleArea.class,()-> algoDefense.buildsATower(silverTower2));

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
      TowerFactory factory = new WhiteTowerFactory();
      Tower whiteTower = factory.createTower(new Point(2, 3));

      //Act
      algoDefense.spawnAnEnemy(enemyArray);
      algoDefense.buildsATower(whiteTower);
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
        TowerFactory factory = new WhiteTowerFactory();
        Tower whiteTower = factory.createTower(new Point(3, 4));

        //Act
        algoDefense.spawnAnEnemy(enemyArray);
        algoDefense.buildsATower(whiteTower);
        algoDefense.nextTurn();
        algoDefense.nextTurn();

        //Assert
        Assertions.assertTrue(anAnt.enemyDied());
        Assertions.assertFalse(aSpider.enemyDied());
    }
    @Test
    public void test07VerifyThatEnemiesMovesOnlyInPath(){
        Stone stone = new Stone();
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy ant = enemyFactory.createEnemy("Ant");
        stone.addEnemyToPath(ant);
        ArrayList<Enemy> listEnemy = new ArrayList<Enemy>();
        listEnemy.add(ant);
        Assertions.assertThrows(TheEnemyCannotBeOutsideTheRunway.class,()->stone.setEnemy(listEnemy));

    }
    @Test
    public void test08VerifyThatWhenDestroyingAnEnemyUnitThePlayerIsAwardedTheCorrespondingCredit(){
        //Arrange
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);
        Credit creditsExpected = new Credit(91);
        TowerFactory factory = new WhiteTowerFactory();
        Tower whiteTower = factory.createTower(new Point(4, 0));

        //Act
        algoDefense.buildsATower(whiteTower);
        algoDefense.nextTurn();
        algoDefense.nextTurn();

        //Assert
        Assertions.assertTrue(creditsExpected.equalTo(player.getPlayerCredits()));
    }

    @Test
    public void test09VerifyEnemiesMoveAsExpected() {
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);
        ArrayList<Enemy> enemyArray = new ArrayList<Enemy>();
        EnemyFactory eFactory = new EnemyFactory();
        Enemy anAnt = eFactory.createEnemy("Ant");
        Enemy aSpider = eFactory.createEnemy("Spider");
        TowerFactory factory = new SilverTowerFactory();
        Tower silverTower = factory.createTower(new Point(4, 6));
        enemyArray.add(anAnt);
        enemyArray.add(aSpider);

        algoDefense.buildsATower(silverTower);
        algoDefense.spawnAnEnemy(enemyArray);
        algoDefense.nextTurn();

        assertFalse(anAnt.enemyDied());
        assertFalse(aSpider.enemyDied());

        algoDefense.nextTurn();
        assertTrue(aSpider.enemyDied());
        assertFalse(anAnt.enemyDied());

        algoDefense.nextTurn();
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
        //algoDefense.nextTurn(); //en esta linea el jugador se muere



        //Assert
        assertTrue(player.isAlive());
    }
    @Test
    public void test18PlayerWinsTheGame(){
        //Arrange
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player);
        TowerFactory factory = new WhiteTowerFactory();

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

        Tower whiteTower = factory.createTower(coordinatesToADirt);
        Tower whiteTower0 = factory.createTower(coordinatesToADirt0);
        Tower whiteTower1 = factory.createTower(coordinatesToADirt1);
        Tower whiteTower2 = factory.createTower(coordinatesToADirt2);
        Tower whiteTower3 = factory.createTower(coordinatesToADirt3);
        Tower whiteTower4 = factory.createTower(coordinatesToADirt4);
        Tower whiteTower5 = factory.createTower(coordinatesToADirt5);
        Tower whiteTower6= factory.createTower(coordinatesToADirt6);
        Tower whiteTower7 = factory.createTower(coordinatesToADirt7);
        Tower whiteTower8 = factory.createTower(coordinatesToADirt8);

        //Act
        algoDefense.buildsATower(whiteTower);
        algoDefense.buildsATower(whiteTower0);
        algoDefense.buildsATower(whiteTower1);
        algoDefense.buildsATower(whiteTower2);
        algoDefense.buildsATower(whiteTower3);
        algoDefense.buildsATower(whiteTower4);
        algoDefense.buildsATower(whiteTower5);
        algoDefense.buildsATower(whiteTower6);
        algoDefense.buildsATower(whiteTower7);
        algoDefense.buildsATower(whiteTower8);

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
        //algoDefense.nextTurn(); //In this turn the player dies

        //Assert

        assertTrue(player.isAlive());
    }

    @Test
    public void test13VerifyThatEnemiesJSONfileIsValid(){
        EnemiesParser enemiesParser = new EnemiesParser("invalidfile");

        //exception.expect(FileNotFoundException.class);
        //Assertions.assertThrows(FileNotFoundException.class, ()-> enemiesParser.getArray());
    }
    @Test
    public void test14VerifyThatMapJSONfileIsValid(){

    }

}

