package edu.fiuba.algo3.modelo.parser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.enemy.Enemy;


import java.util.ArrayList;
import java.util.Iterator;

class JSONreaderTest {
    @Test
    void testPrintMap() {
        String fileRelativeSource = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\parser\\mapa.json";
        String fileName = "Mapa";
        JSONreader Reader = new JSONreader();
        JSONObject mapaObject = Reader.getObject(fileName, fileRelativeSource);
        for (int i = 1; i <= mapaObject.size(); i++) {
            String key = String.valueOf(i);
            JSONArray rowArray = (JSONArray) mapaObject.get(key);
            for (Object element : rowArray) {
                System.out.print(element.toString() + " ");
            }
            System.out.println();
        }
    }

    @Test
    void  testPrintEnemies(){
        String fileRelativeSource = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\parser\\enemigos.json";
        String fileName = "enemigos";
        EnemiesParser Reader = new EnemiesParser();
        JSONArray enemyObject = Reader.getArray(fileName, fileRelativeSource);

        //System.out.println(enemyObject.toString());

        for(int i = 0; i < enemyObject.size(); i++) {

            JSONObject rowObject = (JSONObject) enemyObject.get(i);
            System.out.println(rowObject.toString());

            /*
            System.out.println(rowObject.get("turno"));
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
            */

        }
    }

/*
        boolean startImpresion = false;

        Iterator<String> keys = Obj.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();

            if (key.equals("1")) {
                startImpresion = true;
            }

            if (startImpresion) {
                JSONArray rowArray = (JSONArray) Obj.get(key);
                    System.out.println(rowArray);
            }

        }

 */


}