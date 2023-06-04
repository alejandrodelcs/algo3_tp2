package edu.fiuba.algo3.modelo.parser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;


class MapParserTest {
    @Test
    void testPrintMap() {
        MapParser Reader = new MapParser();
        JSONObject mapaObject = Reader.getObject();
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
        EnemiesParser Reader = new EnemiesParser();
        JSONArray enemyObject = Reader.getArray();

        //System.out.println(enemyObject.toString());

        for (Object o : enemyObject) {

            JSONObject rowObject = (JSONObject) o;
            System.out.println(rowObject.toString());
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