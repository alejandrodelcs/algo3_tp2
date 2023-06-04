package edu.fiuba.algo3.modelo.facade;
import java.util.Dictionary;
import java.util.Hashtable;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.parser.EnemiesParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EnemyFacade {

    public Dictionary loadEnemiesStrategy() {
        EnemiesParser enemyParser = new EnemiesParser();
        Dictionary enemyStrategy = null;
        enemyStrategy = enemyParser.parserFile();
        return enemyStrategy;
    }


}
