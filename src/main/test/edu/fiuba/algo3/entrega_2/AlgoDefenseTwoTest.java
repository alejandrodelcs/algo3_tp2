package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Log;
import edu.fiuba.algo3.modelo.defense.Defense;
import edu.fiuba.algo3.modelo.defense.DefenseFactory;
import edu.fiuba.algo3.modelo.defense.WhiteTowerFactory;
import edu.fiuba.algo3.modelo.exceptions.*;
import edu.fiuba.algo3.modelo.facade.GameboardFacade;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.parser.EnemiesParser;
import edu.fiuba.algo3.modelo.parser.MapParser;
import edu.fiuba.algo3.modelo.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlgoDefenseTwoTest {

    @Test
    public void test13VerifyThatEnemiesJSONfileIsValid(){

        Assertions.assertThrows(FileDoesNotExist.class, ()-> new EnemiesParser("invalidfile"));

        Assertions.assertThrows(InvalidExtension.class,()->  new EnemiesParser("src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\csvFile.csv"));

        Assertions.assertThrows(FileIsEmpty.class,()->  new EnemiesParser("src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\empty.json"));

        EnemiesParser enemiesParser = new EnemiesParser("src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\invalidFormat.json");
        enemiesParser.getArray();
        Assertions.assertThrows(EnemyObjectDoesNotExists.class, enemiesParser::parserFile);

        EnemiesParser enemiesParserOfJSONMap = new EnemiesParser("src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\mapa.json");
        Assertions.assertThrows(InvalidJSONArray.class, enemiesParserOfJSONMap::getArray);
    }
    @Test
    public void test14VerifyThatMapJSONfileIsValid(){

       Assertions.assertThrows(FileDoesNotExist.class, ()-> new MapParser("invalidfile"));

       Assertions.assertThrows(InvalidExtension.class,()->  new MapParser("src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\csvFile.csv"));

       Assertions.assertThrows(FileIsEmpty.class,()->  new MapParser("src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\empty.json"));

       MapParser mapParser = new MapParser("src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\enemigos.json");
       Assertions.assertThrows(InvalidJSONObject.class, ()-> mapParser.getObject());
    }

    @Test
    public void test15VerifyReadingAndUnitConversionOfEnemies(){

        //arrange
        String fileWithoutTurn = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\enemyFileWithoutTurnObject.json";
        String fileWithoutEnemies = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\enemyFileWithoutEnemies.json";
        String fileWithoutAnt = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\enemyFileWithoutAnt.json";
        String fileWithoutSpider = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\enemyFileWithoutSpider.json";
/*        String fileWithoutMole = "src\main\java\edu\fiuba\algo3\modelo\files\enemyFileWithoutTurnObject.json";
        String fileWithoutOwl = "src\main\java\edu\fiuba\algo3\modelo\files\enemyFileWithoutTurnObject.json";*/
        String validFile = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\enemigos.json";

        EnemiesParser enemiesParser = new EnemiesParser(fileWithoutTurn);
        enemiesParser.getArray();
        Assertions.assertThrows(TurnObjectDoesNotExists.class, enemiesParser::parserFile);

        EnemiesParser enemiesParserFileMissingEnemies = new EnemiesParser(fileWithoutEnemies);
        enemiesParserFileMissingEnemies.getArray();
        Assertions.assertThrows(EnemyObjectDoesNotExists.class, enemiesParserFileMissingEnemies::parserFile);

        EnemiesParser enemiesParserFileMissingAnt = new EnemiesParser(fileWithoutAnt);
        enemiesParserFileMissingAnt.getArray();
        Assertions.assertThrows(EnemyObjectDoesNotExists.class, enemiesParserFileMissingAnt::parserFile);

        EnemiesParser enemiesParserFileMissingSpider = new EnemiesParser(fileWithoutSpider);
        enemiesParserFileMissingSpider.getArray();
        Assertions.assertThrows(EnemyObjectDoesNotExists.class,()-> enemiesParserFileMissingSpider.parserFile());

        EnemiesParser validEnemiesParser = new EnemiesParser(validFile);
        Assertions.assertFalse(validEnemiesParser.getArray().isEmpty());
    }
    @Test
    public void test16VerifyReadingAndUnitConversionOfMap(){

        String fileWithInvalidPlot = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\mapWithInvalidPlot.json";
        MapParser mapParser = new MapParser(fileWithInvalidPlot);
        mapParser.getObject();
        Assertions.assertThrows(InvalidPlot.class,()-> mapParser.initializeMap());

        String fileWithInvalidObject = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\mapWithoutMapObject.json";
        MapParser mapParserWithoutObject = new MapParser(fileWithInvalidObject);
        Assertions.assertThrows(InvalidMapFile.class,()-> mapParserWithoutObject.getObject());

        String validFile = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\mapa.json";
        MapParser validMapParser = new MapParser(validFile);
        Assertions.assertFalse(validMapParser.getObject().isEmpty());
    }

    @Test
    public void test17GameIsCreatedBasedUponBothJSONFiles(){

        String validEnemyFile = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\enemigos.json";
        String validMapFile = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\mapa.json";

        EnemiesParser validEnemiesParser = new EnemiesParser(validEnemyFile);
        MapParser validMapParser = new MapParser(validMapFile);

        Assertions.assertFalse(validMapParser.getObject().isEmpty());
        Assertions.assertFalse(validEnemiesParser.getArray().isEmpty());

    }

    @Test
    public void test18PlayerWinsTheGame(){
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

        assertTrue(player.isAlive());
    }

    @Test
    public void test19VerifyThatPlayerLost(){
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

        Assertions.assertThrows(PlayerIsDeadGameOver.class,()-> algoDefense.nextTurn());
    }

    @Test
    public void test20VerifyWriteLogFileWithEvent(){
        GameboardFacade gameboardFacade = new GameboardFacade();
        Log log = new Log("GameBoardFacade");
        GameBoard gameboard = gameboardFacade.loadMap();
        gameboard.constructPath();
        log.logToFile("The enemy path has been successfully");

    }
}
