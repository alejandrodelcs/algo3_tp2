package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.exceptions.NonConstructibleArea;
import edu.fiuba.algo3.modelo.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//import java.awt.*;

public class AlgoDefenseTest {
  /*
     @Test
    public void test01VerifyPlayerStartsWithLifeAndCredits(){
         JSONreader Reader = new JSONreader();
        // Player player = new Player(); //TODO/ Here the player should be created with the corresponding parameters
        GameBoard gameBoard = new GameBoard(Reader.getObject());//TODO/ Have to finish(Constructor parameters inside a JSON)
        ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();//TODO/ Have to finish(Constructor parameters inside a JSON)
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player,gameBoard,enemyArrayList);
        // initialize a game(you need the player, the map of the game, and the enemys)
        Assertions.assertEquals(new Credit(100), player.getPlayerCredits());

        //TODO: When using the interface, it is necessary to add an exception when the player doesn't have enough available credits.
        // In test 1, the player's credits are not deducted because an attempt to build a tower in an occupied location is made
        Assertions.assertEquals(true,algoDefense.canPlayerBuyTower(new WhiteTower()));
        Assertions.assertEquals(20, player.getPlayerLifePoints());
    }

    @Test
  
    public void test03VerifyPlayerHasCredits(){
        JSONreader Reader = new JSONreader();
        // Player player = new Player(); //TODO/ Here the player should be created with the corresponding parameters
        GameBoard gameBoard = new GameBoard(Reader.getObject());//TODO/ Have to finish(Constructor parameters inside a JSON)
        ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();//TODO/ Have to finish(Constructor parameters inside a JSON)
        Player player = new Player("Player");
        AlgoDefense algoDefense = new AlgoDefense(player,gameBoard,enemyArrayList);
        // initialize a game(you need the player, the map of the game, and the enemys)

        algoDefense.buildsATower();
        algoDefense.buildsATower();
        algoDefense.buildsATower();
        algoDefense.buildsATower();
        algoDefense.buildsATower();
        algoDefense.buildsATower();
        algoDefense.buildsATower();
        algoDefense.buildsATower();
        algoDefense.buildsATower();//here the player has 10 credits
        Assertions.assertEquals(false,algoDefense.canPlayerBuyTower(new SilverTower()));//SilverTower
        Assertions.assertEquals(true,algoDefense.canPlayerBuyTower(new WhiteTower()));
        algoDefense.buildsATower();//now the player has not credits so can not buy another tower
        Assertions.assertEquals(false,algoDefense.canPlayerBuyTower(new WhiteTower()));
    }
}

   */
/*
    @Test

    public void test02(){ //supuesto turno 0 inicial se construye white tower turno 1 esta construyendo turno 2 es operativo
        GameBoard gameBoard = new GameBoard();
        Player player = new Player("Zoilo");
        ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();
        WhiteTower whiteTower = new WhiteTower();
        SilverTower silverTower = new SilverTower();
        AlgoDefense algoDefense = new AlgoDefense(player,gameBoard,enemyArrayList);
        ConstructionState construction = new ConstructionState();
        OperationalState operational = new OperationalState();
        Point coordenatesWhiteTower = new Point(2, 3);
        Point coordenatesSilverTower = new Point(1, 1);

        algoDefense.buildsADefense(whiteTower, coordenatesWhiteTower);
        algoDefense.buildsADefense(silverTower, coordenatesSilverTower);
        Assertions.assertEquals(construction.getClass(), whiteTower.getStatus().getClass());
        Assertions.assertEquals(construction.getClass(), silverTower.getStatus().getClass());
        algoDefense.newTurn();
        Assertions.assertEquals(1, algoDefense.getTurn());
        Assertions.assertEquals(operational.getClass(), whiteTower.getStatus().getClass());
        Assertions.assertEquals(construction.getClass(), silverTower.getStatus().getClass());
        algoDefense.newTurn();


        Assertions.assertEquals(2, algoDefense.getTurn());
        Assertions.assertEquals(operational.getClass(), whiteTower.getStatus().getClass());
        Assertions.assertEquals(operational.getClass(), silverTower.getStatus().getClass());

    }


    @Test
    public void test03() {
        Player player = new Player("Player");
        WhiteTower whiteTower = new WhiteTower();

        int value = whiteTower.getCredits();

        Assertions.assertTrue(player.canBuy(value));

        player.chargedCredits(95);
        Assertions.assertFalse(player.canBuy(value));
    }

    @Test
    public void test04() {
        Player player = new Player("Player");
        WhiteTower whiteTower = new WhiteTower();
        SilverTower silverTower = new SilverTower();
        AlgoDefense algoDefense = new AlgoDefense(player);
        Point coordenatesDirtPlot = new Point(2, 3);
        Point coordenatesStonePlot = new Point(3, 3);
        ConstructionState construction = new ConstructionState();

        algoDefense.buildsADefense(whiteTower, coordenatesDirtPlot);

        Assertions.assertEquals(construction.getClass(), whiteTower.getStatus().getClass());
        assertThrows(NonConstructibleArea.class, () -> {
            algoDefense.buildsADefense(silverTower, coordenatesStonePlot);
            ;
        });
    }

    @Test
    public void test05(){
        GameBoard gameBoard = new GameBoard();
        ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();
        WhiteTower whiteTower = new WhiteTower();
        SilverTower silverTower = new SilverTower();
        Spider spider = new Spider();
        AlgoDefense algoDefense = new AlgoDefense("player",gameBoard,enemyArrayList);
        Point coordenatesDirtPlot = new Point(2,3);
        Point coordenatesEnemy = new Point(3,3);
        algoDefense.buildsADefense(whiteTower, coordenatesDirtPlot);
        algoDefense.newTurn();
        algoDefense.newTurn();
        algoDefense.spawnAnEnemy(spider, coordenatesEnemy);
        boolean isOnRange = algoDefense.enemyIsOnRange(whiteTower, coordenatesEnemy, coordenatesDirtPlot);
        whiteTower.Attack(spider, isOnRange);

        assertEquals(1, spider.getHealth());

    }


    @Test
    public void test06() {
        JsonFacade jsonFacade = new JsonFacade();


        try {
            String path = "E:\\Algoritmos3\\algo3_tp2\\src\\main\\java\\edu\\fiuba\\algo3\\modelo\\mapa.json" ; //TODO: change relative path
            JSONObject jsonObject = jsonFacade.readJSONFromFile(path);
            JSONObject mapa = jsonFacade.getMapa(jsonObject);
            jsonFacade.printMapa(mapa);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

*/
    @Test
    public void test04VerifyThatTowerCanNotBeBuildOnStoneButCanBeBuildOnDirt(){
      Player player = new Player("Player");
      AlgoDefense algoDefense = new AlgoDefense(player);
      Point coordenatesToStone = new Point(0,0);
      Point coordenatesToDirt = new Point(3,3);
      String silverTower = "SilverTower";
      algoDefense.buildsATower(coordenatesToDirt, silverTower);

      Assertions.assertTrue(algoDefense.isOccupyByADefense(coordenatesToDirt));
      Assertions.assertThrows(NonConstructibleArea.class,()-> algoDefense.buildsATower(coordenatesToStone, silverTower));

    }
    @Test
    public void test05VerifyThatTowersAttackWithinExpectedRange(){
      Player player = new Player("Player");
      AlgoDefense algoDefense = new AlgoDefense(player);
      Point coordenatesToDirt = new Point(3,3);
      String whiteTower = "WhiteTower";

      algoDefense.buildsATower(coordenatesToDirt, whiteTower);
      //algoDefense.nextTurn();




    }
}

