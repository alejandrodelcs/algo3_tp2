package edu.fiuba.algo3.modelo.parser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;


import java.util.Iterator;

class JSONreaderTest {
    @Test
    void testPrintMap() {
        JSONreader Reader = new JSONreader();
        JSONObject Obj = Reader.getObject();

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
    }

}