package edu.fiuba.algo3.parser;

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

    private String fileRelativeSource;

    public EnemiesParser(String fileSource){
        this.fileRelativeSource = fileSource;
    }

    public JSONArray getArray() {
        JSONArray error = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileRelativeSource));
            return (JSONArray) obj;

        } catch (ParseException | IOException ignored) {
        }
        return error;
    }

    public Dictionary parserFile(){
        String file = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\parser\\enemigos.json";
        Dictionary enemyStrategy = new Hashtable();
        ArrayList<Enemy> enemies;
        EnemiesParser reader = new EnemiesParser(file);
        JSONArray enemyObject = reader.getArray();

        for (Object o : enemyObject) {

            JSONObject rowObject = (JSONObject) o;

            Object turn = rowObject.get("turno");
            String turnValue = turn.toString();
            int turnNumber = Integer.parseInt(turnValue);

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
    private ArrayList<Enemy> loadEnemies(int antAmount, int spiderAmount){

        ArrayList<Enemy> enemiesStrategy = new ArrayList<Enemy>();

        EnemyFactory enemyFactory = new EnemyFactory();
        while(antAmount>0){
            Enemy anAnt = enemyFactory.createEnemy("Ant");
            enemiesStrategy.add(anAnt);
            antAmount--;
        }
        while (spiderAmount>0){
            Enemy aSpider = enemyFactory.createEnemy("Spider");
            enemiesStrategy.add(aSpider);
            spiderAmount --;
        }

        return enemiesStrategy;

    }
}
