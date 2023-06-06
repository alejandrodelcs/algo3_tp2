package edu.fiuba.algo3.modelo.facade;
import java.util.Dictionary;
import java.util.Hashtable;

import edu.fiuba.algo3.modelo.enemy.Enemy;
import edu.fiuba.algo3.modelo.parser.EnemiesParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EnemyFacade {

    private String fileSource;

    public EnemyFacade(){
        //TODO: access randomfile list pick one enemy JSON
        //        String fileRelativeSource = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\parser\\enemigos.json";
        this.fileSource =  "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\parser\\enemigos.json";

    }

    public Dictionary loadEnemiesStrategy() {
        EnemiesParser enemyParser = new EnemiesParser(fileSource);
        Dictionary enemyStrategy = null;
        enemyStrategy = enemyParser.parserFile();
        return enemyStrategy;
    }

}
