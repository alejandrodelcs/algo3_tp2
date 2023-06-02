package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Defense.*;
import edu.fiuba.algo3.modelo.Enemy.Spider;
import edu.fiuba.algo3.modelo.GameBoard.NonConstructibleArea;
import edu.fiuba.algo3.modelo.JsonFacade;
import edu.fiuba.algo3.modelo.Player;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import org.json.JSONArray;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//import java.awt.*;

public class AlgoDefenseTest {
    @Test
    public void test01() {
        Player player = new Player("Player");

        Assertions.assertEquals(100, player.getPlayerCredits());
        Assertions.assertEquals(20, player.getPlayerLifePoints());
    }

    @Test
    public void test02() { //supuesto turno 0 inicial se construye white tower turno 1 esta construyendo turno 2 es operativo

        Player player = new Player("Player");
        WhiteTower whiteTower = new WhiteTower();
        SilverTower silverTower = new SilverTower();
        AlgoDefense algoDefense = new AlgoDefense(player);
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
    public void test05() {
        Player player = new Player("Player");
        WhiteTower whiteTower = new WhiteTower();
        SilverTower silverTower = new SilverTower();
        Spider spider = new Spider();
        AlgoDefense algoDefense = new AlgoDefense(player);
        Point coordenatesDirtPlot = new Point(2, 3);
        Point coordenatesEnemy = new Point(3, 3);

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

}


