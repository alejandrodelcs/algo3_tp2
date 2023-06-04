package edu.fiuba.algo3.modelo.parser;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.enemy.EnemyFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EnemiesParser {
    public JSONArray getArray() {
        String fileRelativeSource = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\parser\\enemigos.json";
        JSONArray error = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileRelativeSource));
            return (JSONArray) obj;

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ParseException e) {
        }
        return error;
    }

    public Dictionary parserFile(){

        Dictionary enemyStrategy = new Hashtable();
        List enemies;
        EnemiesParser reader = new EnemiesParser();
        JSONArray enemyObject = reader.getArray();

        for (Object o : enemyObject) {

            JSONObject rowObject = (JSONObject) o;

            Object turn = rowObject.get("turno");
            String turnValue = turn.toString();
            int turnNumber = Integer.parseInt(turnValue);

            //System.out.println(rowObject.get("enemigos"));
            JSONObject enemiesByTurn = (JSONObject) rowObject.get("enemigos");

            Object ant = enemiesByTurn.get("hormiga");
            String antValue = ant.toString();
            int antAmount = Integer.parseInt(antValue);

            Object spider = enemiesByTurn.get("arana");
            String spiderValue = spider.toString();
            int spiderAmount = Integer.parseInt(spiderValue);

            enemies = loadEnemies(antAmount,spiderAmount);
            enemyStrategy.put(turnNumber, enemies);

        }

        return enemyStrategy;
    }
    private List loadEnemies(int antAmount, int spiderAmount){

        List enemiesStrategy = new ArrayList();

        while(antAmount>0){
            EnemyFactory enemyFactory = new EnemyFactory();
            Enemy anAnt = enemyFactory.createEnemy("Ant");
            enemiesStrategy.add(anAnt);
            antAmount--;
        }
        while (spiderAmount>0){
            EnemyFactory enemyFactory = new EnemyFactory();
            Enemy aSpider = enemyFactory.createEnemy("Spider");
            enemiesStrategy.add(aSpider);
            spiderAmount --;
        }

        return enemiesStrategy;

    }
}
