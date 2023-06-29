package edu.fiuba.algo3.modelo.facade;
import java.util.Dictionary;
import edu.fiuba.algo3.modelo.parser.EnemiesParser;


public class EnemyFacade {

    private String fileSource;

    public EnemyFacade(){
        this.fileSource =  "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\files\\enemigos.json";

    }

    public Dictionary loadEnemiesStrategy() {
        EnemiesParser enemyParser = new EnemiesParser(fileSource);
        Dictionary enemyStrategy = null;
        enemyStrategy = enemyParser.parserFile();
        return enemyStrategy;
    }

}
