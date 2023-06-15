package edu.fiuba.algo3.modelo.parser;

import edu.fiuba.algo3.modelo.enemy.*;
import edu.fiuba.algo3.modelo.exceptions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EnemiesParser {

    private String fileRelativeSource;

    public EnemiesParser(String fileSource){
        FileHandler fileHandler = new FileHandler(fileSource);
        this.fileRelativeSource = fileSource;
    }

    public JSONArray getArray() {
        JSONArray error = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileRelativeSource));
            if(obj instanceof JSONObject){
                throw new InvalidJSONArray();
            }
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

            if(!rowObject.containsKey("turno")){
                throw new TurnObjectDoesNotExists();
            }
            Object turn = rowObject.get("turno");
            String turnValue = turn.toString();
            int turnNumber = Integer.parseInt(turnValue);

            if(!rowObject.containsKey("enemigos")){
                throw new EnemyObjectDoesNotExists();
            }
            JSONObject enemiesByTurn = (JSONObject) rowObject.get("enemigos");

            if(!enemiesByTurn.containsKey("hormiga")){
                throw new EnemyObjectDoesNotExists();
            }
            Object ant = enemiesByTurn.get("hormiga");
            String antValue = ant.toString();
            int antAmount = Integer.parseInt(antValue);
            EnemyFactory antFactory = new AntFactory();
            loadEnemies(antAmount,antFactory,enemiesStrategy);

            if(!enemiesByTurn.containsKey("arana")){
                throw new EnemyObjectDoesNotExists();
            }
            Object spider = enemiesByTurn.get("arana");
            String spiderValue = spider.toString();
            int spiderAmount = Integer.parseInt(spiderValue);
            EnemyFactory spiderFactory = new SpiderFactory();
            loadEnemies(spiderAmount,spiderFactory,enemiesStrategy);

/*            if(!enemiesByTurn.containsKey("topo")){
                throw new EnemyObjectDoesNotExists();
            }
            Object mole = enemiesByTurn.get("topo");
            String moleValue = mole.toString();
            int moleAmount = Integer.parseInt(moleValue);
            EnemyFactory moleFactory = new MoleFactory();
            loadEnemies(moleAmount,moleFactory,enemiesStrategy);

            if(!enemiesByTurn.containsKey("lechuza")){
                throw new EnemyObjectDoesNotExists();
            }
            Object owl = enemiesByTurn.get("lechuza");
            String owlValue = owl.toString();
            int owlAmount = Integer.parseInt(owlValue);
            EnemyFactory owlFactory = new OwlFactory();
            loadEnemies(owlAmount,owlFactory,enemiesStrategy);*/

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