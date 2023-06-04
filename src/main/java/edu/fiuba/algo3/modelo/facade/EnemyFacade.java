package edu.fiuba.algo3.modelo.facade;
import java.util.Dictionary;
import java.util.Hashtable;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.gameboard.GameBoard;
import edu.fiuba.algo3.modelo.parser.EnemiesParser;
import edu.fiuba.algo3.modelo.parser.JSONreader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EnemyFacade {

    public Dictionary loadEnemies() {

        Dictionary enemyStrategy = new Hashtable();


        //Dictionary<Integer, Array<Enemy>>


        return enemyStrategy;
    }

    private void readFile(){

        String fileRelativeSource = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\parser\\enemigos.json";
        String fileName = "enemigos";
        EnemiesParser Reader = new EnemiesParser();
        JSONArray enemyObject = Reader.getArray(fileName, fileRelativeSource);

        for(int i = 0; i < enemyObject.size(); i++) {

            JSONObject rowObject = (JSONObject) enemyObject.get(i);

            Object turn = rowObject.get("turno");
            String turnValue = turn.toString();
            int turnNumber = Integer.parseInt(turnValue);
            System.out.println(turnNumber);

            System.out.println(rowObject.get("enemigos"));
            JSONObject enemiesByTurn = (JSONObject) rowObject.get("enemigos");

            Object ant = enemiesByTurn.get("hormiga");
            String antValue =  ant.toString();
            int antAmount = Integer.parseInt(antValue);
            System.out.println(antAmount);

            Object spider = enemiesByTurn.get("arana");
            String spiderValue = spider.toString();
            int spiderAmount = Integer.parseInt(spiderValue);
            System.out.println(spiderAmount);

        }
    }
}
