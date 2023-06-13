package edu.fiuba.algo3.modelo.parser;

import edu.fiuba.algo3.modelo.enemy.*;
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

        Dictionary enemyStrategy = new Hashtable();
        ArrayList<Enemy> enemies;
        EnemiesParser reader = new EnemiesParser(fileRelativeSource);
        JSONArray enemyObject = reader.getArray();


        for (Object o : enemyObject) {
            ArrayList<Enemy> enemiesStrategy = new ArrayList<Enemy>();

            JSONObject rowObject = (JSONObject) o;

            Object turn = rowObject.get("turno");
            String turnValue = turn.toString();
            int turnNumber = Integer.parseInt(turnValue);

            JSONObject enemiesByTurn = (JSONObject) rowObject.get("enemigos");

            Object ant = enemiesByTurn.get("hormiga");
            String antValue = ant.toString();
            int antAmount = Integer.parseInt(antValue);
            EnemyFactory antFactory = new AntFactory();
            loadEnemies(antAmount,antFactory,enemiesStrategy);

            Object spider = enemiesByTurn.get("arana");
            String spiderValue = spider.toString();
            int spiderAmount = Integer.parseInt(spiderValue);
            EnemyFactory spiderFactory = new SpiderFactory();
            loadEnemies(spiderAmount,spiderFactory,enemiesStrategy);

            /*
            Object mole = enemiesByTurn.get("topo");
            String moleValue = mole.toString();
            int moleAmount = Integer.parseInt(moleValue);
            EnemyFactory moleFactory = new MoleFactory();
            loadEnemies(moleAmount,moleFactory,enemiesStrategy);

            Object owl = enemiesByTurn.get("lechuza");
            String owlValue = owl.toString();
            int owlAmount = Integer.parseInt(owlValue);
            EnemyFactory owlFactory = new OwlFactory();
            loadEnemies(owlAmount,owlFactory,enemiesStrategy);
            */

            System.out.println(enemiesStrategy);
            enemyStrategy.put(turnNumber, enemiesStrategy);
        }
        return enemyStrategy;
    }
    private void loadEnemies(int enemyAmount, EnemyFactory enemyFactory, ArrayList<Enemy> enemiesStrategy){

        while (enemyAmount>0){
            Enemy anEnemy = enemyFactory.createEnemy();
            enemiesStrategy.add(anEnemy);
            enemyAmount --;
        }
    }
}